package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.api.internal.ListenerHolder.ListenerKey;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import com.google.android.gms.common.util.ArrayUtils;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.zad;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TapjoyConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.concurrent.GuardedBy;

@KeepForSdk
public class GoogleApiManager implements Callback {
    private static final Object lock = new Object();
    public static final Status zahx = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zahy = new Status(4, "The user must be signed in to make this API call.");
    @GuardedBy("lock")
    private static GoogleApiManager zaic;
    private final Handler handler;
    private long zahz = 5000;
    private long zaia = 120000;
    private long zaib = TapjoyConstants.TIMER_INCREMENT;
    private final Context zaid;
    private final GoogleApiAvailability zaie;
    private final GoogleApiAvailabilityCache zaif;
    private final AtomicInteger zaig = new AtomicInteger(1);
    private final AtomicInteger zaih = new AtomicInteger(0);
    private final Map<zai<?>, zaa<?>> zaii = new ConcurrentHashMap(5, 0.75f, 1);
    @GuardedBy("lock")
    private zaae zaij = null;
    @GuardedBy("lock")
    private final Set<zai<?>> zaik = new ArraySet();
    private final Set<zai<?>> zail = new ArraySet();

    public class zaa<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zar {
        private final zai<O> zafq;
        final /* synthetic */ GoogleApiManager zaim;
        private final Queue<zab> zain = new LinkedList();
        private final Client zaio;
        private final AnyClient zaip;
        private final zaab zaiq;
        private final Set<zak> zair = new HashSet();
        private final Map<ListenerKey<?>, zabw> zais = new HashMap();
        private final int zait;
        private final zace zaiu;
        private boolean zaiv;
        private final List<zab> zaiw = new ArrayList();
        private ConnectionResult zaix = null;

        @WorkerThread
        public zaa(GoogleApiManager googleApiManager, GoogleApi<O> googleApi) {
            this.zaim = googleApiManager;
            this.zaio = googleApi.zaa(googleApiManager.handler.getLooper(), this);
            if (this.zaio instanceof SimpleClientAdapter) {
                this.zaip = ((SimpleClientAdapter) this.zaio).getClient();
            } else {
                this.zaip = this.zaio;
            }
            this.zafq = googleApi.zak();
            this.zaiq = new zaab();
            this.zait = googleApi.getInstanceId();
            if (this.zaio.requiresSignIn()) {
                this.zaiu = googleApi.zaa(googleApiManager.zaid, googleApiManager.handler);
            } else {
                this.zaiu = null;
            }
        }

        public final void onConnected(@Nullable Bundle bundle) {
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                zabg();
            } else {
                this.zaim.handler.post(new zabj(this));
            }
        }

        @WorkerThread
        private final void zabg() {
            zabl();
            zai(ConnectionResult.RESULT_SUCCESS);
            zabn();
            Iterator it = this.zais.values().iterator();
            while (it.hasNext()) {
                zabw zabw = (zabw) it.next();
                if (zaa(zabw.zajx.getRequiredFeatures()) != null) {
                    it.remove();
                } else {
                    try {
                        zabw.zajx.registerListener(this.zaip, new TaskCompletionSource());
                    } catch (DeadObjectException e) {
                        onConnectionSuspended(1);
                        this.zaio.disconnect();
                    } catch (RemoteException e2) {
                        it.remove();
                    }
                }
            }
            zabi();
            zabo();
        }

        public final void onConnectionSuspended(int i) {
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                zabh();
            } else {
                this.zaim.handler.post(new zabk(this));
            }
        }

        @WorkerThread
        private final void zabh() {
            zabl();
            this.zaiv = true;
            this.zaiq.zaai();
            this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 9, this.zafq), this.zaim.zahz);
            this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 11, this.zafq), this.zaim.zaia);
            this.zaim.zaif.flush();
        }

        @WorkerThread
        public final void zag(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            this.zaio.disconnect();
            onConnectionFailed(connectionResult);
        }

        @WorkerThread
        private final boolean zah(@NonNull ConnectionResult connectionResult) {
            boolean z;
            synchronized (GoogleApiManager.lock) {
                if (this.zaim.zaij == null || !this.zaim.zaik.contains(this.zafq)) {
                    z = false;
                } else {
                    this.zaim.zaij.zab(connectionResult, this.zait);
                    z = true;
                }
            }
            return z;
        }

        public final void zaa(ConnectionResult connectionResult, Api<?> api, boolean z) {
            if (Looper.myLooper() == this.zaim.handler.getLooper()) {
                onConnectionFailed(connectionResult);
            } else {
                this.zaim.handler.post(new zabl(this, connectionResult));
            }
        }

        @WorkerThread
        public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiu != null) {
                this.zaiu.zabs();
            }
            zabl();
            this.zaim.zaif.flush();
            zai(connectionResult);
            if (connectionResult.getErrorCode() == 4) {
                zac(GoogleApiManager.zahy);
            } else if (this.zain.isEmpty()) {
                this.zaix = connectionResult;
            } else if (!zah(connectionResult) && !this.zaim.zac(connectionResult, this.zait)) {
                if (connectionResult.getErrorCode() == 18) {
                    this.zaiv = true;
                }
                if (this.zaiv) {
                    this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 9, this.zafq), this.zaim.zahz);
                    return;
                }
                String zan = this.zafq.zan();
                zac(new Status(17, new StringBuilder(String.valueOf(zan).length() + 38).append("API: ").append(zan).append(" is not available on this device.").toString()));
            }
        }

        @WorkerThread
        private final void zabi() {
            ArrayList arrayList = new ArrayList(this.zain);
            int size = arrayList.size();
            int i = 0;
            while (i < size) {
                Object obj = arrayList.get(i);
                i++;
                zab zab = (zab) obj;
                if (!this.zaio.isConnected()) {
                    return;
                }
                if (zab(zab)) {
                    this.zain.remove(zab);
                }
            }
        }

        @WorkerThread
        public final void zaa(zab zab) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (!this.zaio.isConnected()) {
                this.zain.add(zab);
                if (this.zaix == null || !this.zaix.hasResolution()) {
                    connect();
                } else {
                    onConnectionFailed(this.zaix);
                }
            } else if (zab(zab)) {
                zabo();
            } else {
                this.zain.add(zab);
            }
        }

        @WorkerThread
        public final void zabj() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            zac(GoogleApiManager.zahx);
            this.zaiq.zaah();
            for (ListenerKey zah : (ListenerKey[]) this.zais.keySet().toArray(new ListenerKey[this.zais.size()])) {
                zaa(new zah(zah, new TaskCompletionSource()));
            }
            zai(new ConnectionResult(4));
            if (this.zaio.isConnected()) {
                this.zaio.onUserSignOut(new zabm(this));
            }
        }

        public final Client zaab() {
            return this.zaio;
        }

        public final Map<ListenerKey<?>, zabw> zabk() {
            return this.zais;
        }

        @WorkerThread
        public final void zabl() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            this.zaix = null;
        }

        @WorkerThread
        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            return this.zaix;
        }

        @WorkerThread
        private final boolean zab(zab zab) {
            if (zab instanceof zac) {
                zac zac = (zac) zab;
                Feature zaa = zaa(zac.zab(this));
                if (zaa == null) {
                    zac(zab);
                    return true;
                }
                if (zac.zac(this)) {
                    zab zab2 = new zab(this.zafq, zaa);
                    int indexOf = this.zaiw.indexOf(zab2);
                    if (indexOf >= 0) {
                        zab2 = (zab) this.zaiw.get(indexOf);
                        this.zaim.handler.removeMessages(15, zab2);
                        this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 15, zab2), this.zaim.zahz);
                    } else {
                        this.zaiw.add(zab2);
                        this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 15, zab2), this.zaim.zahz);
                        this.zaim.handler.sendMessageDelayed(Message.obtain(this.zaim.handler, 16, zab2), this.zaim.zaia);
                        ConnectionResult connectionResult = new ConnectionResult(2, null);
                        if (!zah(connectionResult)) {
                            this.zaim.zac(connectionResult, this.zait);
                        }
                    }
                } else {
                    zac.zaa(new UnsupportedApiCallException(zaa));
                }
                return false;
            }
            zac(zab);
            return true;
        }

        @WorkerThread
        private final void zac(zab zab) {
            zab.zaa(this.zaiq, requiresSignIn());
            try {
                zab.zaa(this);
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zaio.disconnect();
            }
        }

        @WorkerThread
        public final void zac(Status status) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            for (zab zaa : this.zain) {
                zaa.zaa(status);
            }
            this.zain.clear();
        }

        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiv) {
                connect();
            }
        }

        @WorkerThread
        private final void zabn() {
            if (this.zaiv) {
                this.zaim.handler.removeMessages(11, this.zafq);
                this.zaim.handler.removeMessages(9, this.zafq);
                this.zaiv = false;
            }
        }

        @WorkerThread
        public final void zaav() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (this.zaiv) {
                Status status;
                zabn();
                if (this.zaim.zaie.isGooglePlayServicesAvailable(this.zaim.zaid) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                } else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                zac(status);
                this.zaio.disconnect();
            }
        }

        private final void zabo() {
            this.zaim.handler.removeMessages(12, this.zafq);
            this.zaim.handler.sendMessageDelayed(this.zaim.handler.obtainMessage(12, this.zafq), this.zaim.zaib);
        }

        @WorkerThread
        public final boolean zabp() {
            return zac(true);
        }

        @WorkerThread
        private final boolean zac(boolean z) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (!this.zaio.isConnected() || this.zais.size() != 0) {
                return false;
            }
            if (!this.zaiq.zaag()) {
                this.zaio.disconnect();
                return true;
            } else if (!z) {
                return false;
            } else {
                zabo();
                return false;
            }
        }

        @WorkerThread
        public final void connect() {
            Preconditions.checkHandlerThread(this.zaim.handler);
            if (!this.zaio.isConnected() && !this.zaio.isConnecting()) {
                int clientAvailability = this.zaim.zaif.getClientAvailability(this.zaim.zaid, this.zaio);
                if (clientAvailability != 0) {
                    onConnectionFailed(new ConnectionResult(clientAvailability, null));
                    return;
                }
                zach zac = new zac(this.zaim, this.zaio, this.zafq);
                if (this.zaio.requiresSignIn()) {
                    this.zaiu.zaa(zac);
                }
                this.zaio.connect(zac);
            }
        }

        @WorkerThread
        public final void zaa(zak zak) {
            Preconditions.checkHandlerThread(this.zaim.handler);
            this.zair.add(zak);
        }

        @WorkerThread
        private final void zai(ConnectionResult connectionResult) {
            for (zak zak : this.zair) {
                String str = null;
                if (Objects.equal(connectionResult, ConnectionResult.RESULT_SUCCESS)) {
                    str = this.zaio.getEndpointPackageName();
                }
                zak.zaa(this.zafq, connectionResult, str);
            }
            this.zair.clear();
        }

        final boolean isConnected() {
            return this.zaio.isConnected();
        }

        public final boolean requiresSignIn() {
            return this.zaio.requiresSignIn();
        }

        public final int getInstanceId() {
            return this.zait;
        }

        final zad zabq() {
            return this.zaiu == null ? null : this.zaiu.zabq();
        }

        @Nullable
        @WorkerThread
        private final Feature zaa(@Nullable Feature[] featureArr) {
            if (featureArr == null || featureArr.length == 0) {
                return null;
            }
            Feature[] availableFeatures = this.zaio.getAvailableFeatures();
            if (availableFeatures == null) {
                availableFeatures = new Feature[0];
            }
            Map arrayMap = new ArrayMap(availableFeatures.length);
            for (Feature feature : availableFeatures) {
                arrayMap.put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (Feature feature2 : featureArr) {
                if (!arrayMap.containsKey(feature2.getName()) || ((Long) arrayMap.get(feature2.getName())).longValue() < feature2.getVersion()) {
                    return feature2;
                }
            }
            return null;
        }

        @WorkerThread
        private final void zaa(zab zab) {
            if (!this.zaiw.contains(zab) || this.zaiv) {
                return;
            }
            if (this.zaio.isConnected()) {
                zabi();
            } else {
                connect();
            }
        }

        @WorkerThread
        private final void zab(zab zab) {
            if (this.zaiw.remove(zab)) {
                this.zaim.handler.removeMessages(15, zab);
                this.zaim.handler.removeMessages(16, zab);
                Feature zad = zab.zajc;
                ArrayList arrayList = new ArrayList(this.zain.size());
                for (zab zab2 : this.zain) {
                    if (zab2 instanceof zac) {
                        Feature[] zab3 = ((zac) zab2).zab(this);
                        if (zab3 != null && ArrayUtils.contains(zab3, zad)) {
                            arrayList.add(zab2);
                        }
                    }
                }
                ArrayList arrayList2 = arrayList;
                int size = arrayList2.size();
                int i = 0;
                while (i < size) {
                    Object obj = arrayList2.get(i);
                    i++;
                    zab zab4 = (zab) obj;
                    this.zain.remove(zab4);
                    zab4.zaa(new UnsupportedApiCallException(zad));
                }
            }
        }
    }

    private static class zab {
        private final zai<?> zajb;
        private final Feature zajc;

        private zab(zai<?> zai, Feature feature) {
            this.zajb = zai;
            this.zajc = feature;
        }

        public final boolean equals(Object obj) {
            if (obj == null || !(obj instanceof zab)) {
                return false;
            }
            zab zab = (zab) obj;
            if (Objects.equal(this.zajb, zab.zajb) && Objects.equal(this.zajc, zab.zajc)) {
                return true;
            }
            return false;
        }

        public final int hashCode() {
            return Objects.hashCode(new Object[]{this.zajb, this.zajc});
        }

        public final String toString() {
            return Objects.toStringHelper(this).add(ParametersKeys.KEY, this.zajb).add("feature", this.zajc).toString();
        }
    }

    private class zac implements zach, ConnectionProgressReportCallbacks {
        private final zai<?> zafq;
        final /* synthetic */ GoogleApiManager zaim;
        private final Client zaio;
        private IAccountAccessor zajd = null;
        private Set<Scope> zaje = null;
        private boolean zajf = false;

        public zac(GoogleApiManager googleApiManager, Client client, zai<?> zai) {
            this.zaim = googleApiManager;
            this.zaio = client;
            this.zafq = zai;
        }

        public final void onReportServiceBinding(@NonNull ConnectionResult connectionResult) {
            this.zaim.handler.post(new zabo(this, connectionResult));
        }

        @WorkerThread
        public final void zag(ConnectionResult connectionResult) {
            ((zaa) this.zaim.zaii.get(this.zafq)).zag(connectionResult);
        }

        @WorkerThread
        public final void zaa(IAccountAccessor iAccountAccessor, Set<Scope> set) {
            if (iAccountAccessor == null || set == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
                zag(new ConnectionResult(4));
                return;
            }
            this.zajd = iAccountAccessor;
            this.zaje = set;
            zabr();
        }

        @WorkerThread
        private final void zabr() {
            if (this.zajf && this.zajd != null) {
                this.zaio.getRemoteService(this.zajd, this.zaje);
            }
        }
    }

    public static GoogleApiManager zab(Context context) {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            if (zaic == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zaic = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            googleApiManager = zaic;
        }
        return googleApiManager;
    }

    public static GoogleApiManager zabc() {
        GoogleApiManager googleApiManager;
        synchronized (lock) {
            Preconditions.checkNotNull(zaic, "Must guarantee manager is non-null before using getInstance");
            googleApiManager = zaic;
        }
        return googleApiManager;
    }

    @KeepForSdk
    public static void reportSignOut() {
        synchronized (lock) {
            if (zaic != null) {
                GoogleApiManager googleApiManager = zaic;
                googleApiManager.zaih.incrementAndGet();
                googleApiManager.handler.sendMessageAtFrontOfQueue(googleApiManager.handler.obtainMessage(10));
            }
        }
    }

    @KeepForSdk
    private GoogleApiManager(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.zaid = context;
        this.handler = new zap(looper, this);
        this.zaie = googleApiAvailability;
        this.zaif = new GoogleApiAvailabilityCache(googleApiAvailability);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }

    public final int zabd() {
        return this.zaig.getAndIncrement();
    }

    public final void zaa(GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, googleApi));
    }

    @WorkerThread
    private final void zab(GoogleApi<?> googleApi) {
        zai zak = googleApi.zak();
        zaa zaa = (zaa) this.zaii.get(zak);
        if (zaa == null) {
            zaa = new zaa(this, googleApi);
            this.zaii.put(zak, zaa);
        }
        if (zaa.requiresSignIn()) {
            this.zail.add(zak);
        }
        zaa.connect();
    }

    public final void zaa(@NonNull zaae zaae) {
        synchronized (lock) {
            if (this.zaij != zaae) {
                this.zaij = zaae;
                this.zaik.clear();
            }
            this.zaik.addAll(zaae.zaaj());
        }
    }

    final void zab(@NonNull zaae zaae) {
        synchronized (lock) {
            if (this.zaij == zaae) {
                this.zaij = null;
                this.zaik.clear();
            }
        }
    }

    public final Task<Map<zai<?>, String>> zaa(Iterable<? extends GoogleApi<?>> iterable) {
        zak zak = new zak(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, zak));
        return zak.getTask();
    }

    public final void zao() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }

    final void maybeSignOut() {
        this.zaih.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }

    public final Task<Boolean> zac(GoogleApi<?> googleApi) {
        zaaf zaaf = new zaaf(googleApi.zak());
        this.handler.sendMessage(this.handler.obtainMessage(14, zaaf));
        return zaaf.zaal().getTask();
    }

    public final <O extends ApiOptions> void zaa(GoogleApi<O> googleApi, int i, ApiMethodImpl<? extends Result, AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zae(i, apiMethodImpl), this.zaih.get(), googleApi)));
    }

    public final <O extends ApiOptions, ResultT> void zaa(GoogleApi<O> googleApi, int i, TaskApiCall<AnyClient, ResultT> taskApiCall, TaskCompletionSource<ResultT> taskCompletionSource, StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, new zabv(new zag(i, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zaih.get(), googleApi)));
    }

    public final <O extends ApiOptions> Task<Void> zaa(@NonNull GoogleApi<O> googleApi, @NonNull RegisterListenerMethod<AnyClient, ?> registerListenerMethod, @NonNull UnregisterListenerMethod<AnyClient, ?> unregisterListenerMethod) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, new zabv(new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), taskCompletionSource), this.zaih.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Boolean> zaa(@NonNull GoogleApi<O> googleApi, @NonNull ListenerKey<?> listenerKey) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, new zabv(new zah(listenerKey, taskCompletionSource), this.zaih.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    @WorkerThread
    public boolean handleMessage(Message message) {
        zai zak;
        zaa zaa;
        zab zab;
        switch (message.what) {
            case 1:
                long j;
                if (((Boolean) message.obj).booleanValue()) {
                    j = TapjoyConstants.TIMER_INCREMENT;
                } else {
                    j = 300000;
                }
                this.zaib = j;
                this.handler.removeMessages(12);
                for (zai obtainMessage : this.zaii.keySet()) {
                    this.handler.sendMessageDelayed(this.handler.obtainMessage(12, obtainMessage), this.zaib);
                }
                break;
            case 2:
                zak zak2 = (zak) message.obj;
                for (zai zak3 : zak2.zap()) {
                    zaa zaa2 = (zaa) this.zaii.get(zak3);
                    if (zaa2 == null) {
                        zak2.zaa(zak3, new ConnectionResult(13), null);
                        break;
                    } else if (zaa2.isConnected()) {
                        zak2.zaa(zak3, ConnectionResult.RESULT_SUCCESS, zaa2.zaab().getEndpointPackageName());
                    } else if (zaa2.zabm() != null) {
                        zak2.zaa(zak3, zaa2.zabm(), null);
                    } else {
                        zaa2.zaa(zak2);
                        zaa2.connect();
                    }
                }
                break;
            case 3:
                for (zaa zaa3 : this.zaii.values()) {
                    zaa3.zabl();
                    zaa3.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zabv zabv = (zabv) message.obj;
                zaa = (zaa) this.zaii.get(zabv.zajt.zak());
                if (zaa == null) {
                    zab(zabv.zajt);
                    zaa = (zaa) this.zaii.get(zabv.zajt.zak());
                }
                if (zaa.requiresSignIn() && this.zaih.get() != zabv.zajs) {
                    zabv.zajr.zaa(zahx);
                    zaa.zabj();
                    break;
                }
                zaa.zaa(zabv.zajr);
                break;
                break;
            case 5:
                String errorString;
                String errorMessage;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (zaa zaa4 : this.zaii.values()) {
                    if (zaa4.getInstanceId() == i) {
                        if (zaa4 != null) {
                            Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                            break;
                        }
                        errorString = this.zaie.getErrorString(connectionResult.getErrorCode());
                        errorMessage = connectionResult.getErrorMessage();
                        zaa4.zac(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                        break;
                    }
                }
                zaa4 = null;
                if (zaa4 != null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                } else {
                    errorString = this.zaie.getErrorString(connectionResult.getErrorCode());
                    errorMessage = connectionResult.getErrorMessage();
                    zaa4.zac(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                }
            case 6:
                if (PlatformVersion.isAtLeastIceCreamSandwich() && (this.zaid.getApplicationContext() instanceof Application)) {
                    BackgroundDetector.initialize((Application) this.zaid.getApplicationContext());
                    BackgroundDetector.getInstance().addListener(new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaib = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zab((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zaii.containsKey(message.obj)) {
                    ((zaa) this.zaii.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                for (zai obtainMessage2 : this.zail) {
                    ((zaa) this.zaii.remove(obtainMessage2)).zabj();
                }
                this.zail.clear();
                break;
            case 11:
                if (this.zaii.containsKey(message.obj)) {
                    ((zaa) this.zaii.get(message.obj)).zaav();
                    break;
                }
                break;
            case 12:
                if (this.zaii.containsKey(message.obj)) {
                    ((zaa) this.zaii.get(message.obj)).zabp();
                    break;
                }
                break;
            case 14:
                zaaf zaaf = (zaaf) message.obj;
                zak3 = zaaf.zak();
                if (!this.zaii.containsKey(zak3)) {
                    zaaf.zaal().setResult(Boolean.valueOf(false));
                    break;
                }
                zaaf.zaal().setResult(Boolean.valueOf(((zaa) this.zaii.get(zak3)).zac(false)));
                break;
            case 15:
                zab = (zab) message.obj;
                if (this.zaii.containsKey(zab.zajb)) {
                    ((zaa) this.zaii.get(zab.zajb)).zaa(zab);
                    break;
                }
                break;
            case 16:
                zab = (zab) message.obj;
                if (this.zaii.containsKey(zab.zajb)) {
                    ((zaa) this.zaii.get(zab.zajb)).zab(zab);
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    final PendingIntent zaa(zai<?> zai, int i) {
        zaa zaa = (zaa) this.zaii.get(zai);
        if (zaa == null) {
            return null;
        }
        zad zabq = zaa.zabq();
        if (zabq == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaid, i, zabq.getSignInIntent(), 134217728);
    }

    final boolean zac(ConnectionResult connectionResult, int i) {
        return this.zaie.zaa(this.zaid, connectionResult, i);
    }

    public final void zaa(ConnectionResult connectionResult, int i) {
        if (!zac(connectionResult, i)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, i, 0, connectionResult));
        }
    }
}
