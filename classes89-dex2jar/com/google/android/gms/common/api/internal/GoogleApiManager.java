// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;
import android.os.Bundle;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.Objects;
import android.os.RemoteException;
import android.os.DeadObjectException;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.common.util.ArrayUtils;
import android.support.v4.util.ArrayMap;
import android.support.annotation.Nullable;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.internal.SimpleClientAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.google.android.gms.common.api.GoogleApiClient;
import java.util.Collection;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.gms.tasks.Task;
import android.support.annotation.NonNull;
import com.google.android.gms.signin.zad;
import android.app.PendingIntent;
import java.util.Iterator;
import com.google.android.gms.common.api.Api;
import android.app.Application;
import com.google.android.gms.common.util.PlatformVersion;
import com.google.android.gms.common.ConnectionResult;
import android.util.Log;
import android.os.Message;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApi;
import android.os.HandlerThread;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.internal.base.zap;
import android.support.v4.util.ArraySet;
import java.util.concurrent.ConcurrentHashMap;
import android.os.Looper;
import java.util.Set;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import com.google.android.gms.common.internal.GoogleApiAvailabilityCache;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import android.os.Handler;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Handler$Callback;

@KeepForSdk
public class GoogleApiManager implements Handler$Callback
{
    private static final Object lock;
    public static final Status zahx;
    private static final Status zahy;
    @GuardedBy("lock")
    private static GoogleApiManager zaic;
    private final Handler handler;
    private long zahz;
    private long zaia;
    private long zaib;
    private final Context zaid;
    private final GoogleApiAvailability zaie;
    private final GoogleApiAvailabilityCache zaif;
    private final AtomicInteger zaig;
    private final AtomicInteger zaih;
    private final Map<zai<?>, zaa<?>> zaii;
    @GuardedBy("lock")
    private zaae zaij;
    @GuardedBy("lock")
    private final Set<zai<?>> zaik;
    private final Set<zai<?>> zail;
    
    static {
        zahx = new Status(4, "Sign-out occurred while this API call was in progress.");
        zahy = new Status(4, "The user must be signed in to make this API call.");
        lock = new Object();
    }
    
    @KeepForSdk
    private GoogleApiManager(final Context zaid, final Looper looper, final GoogleApiAvailability zaie) {
        this.zahz = 5000L;
        this.zaia = 120000L;
        this.zaib = 10000L;
        this.zaig = new AtomicInteger(1);
        this.zaih = new AtomicInteger(0);
        this.zaii = new ConcurrentHashMap<zai<?>, zaa<?>>(5, 0.75f, 1);
        this.zaij = null;
        this.zaik = (Set<zai<?>>)new ArraySet();
        this.zail = (Set<zai<?>>)new ArraySet();
        this.zaid = zaid;
        this.handler = new zap(looper, (Handler$Callback)this);
        this.zaie = zaie;
        this.zaif = new GoogleApiAvailabilityCache(zaie);
        this.handler.sendMessage(this.handler.obtainMessage(6));
    }
    
    @KeepForSdk
    public static void reportSignOut() {
        synchronized (GoogleApiManager.lock) {
            if (GoogleApiManager.zaic != null) {
                final GoogleApiManager zaic = GoogleApiManager.zaic;
                zaic.zaih.incrementAndGet();
                zaic.handler.sendMessageAtFrontOfQueue(zaic.handler.obtainMessage(10));
            }
        }
    }
    
    public static GoogleApiManager zab(final Context context) {
        synchronized (GoogleApiManager.lock) {
            if (GoogleApiManager.zaic == null) {
                final HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                GoogleApiManager.zaic = new GoogleApiManager(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            return GoogleApiManager.zaic;
        }
    }
    
    @WorkerThread
    private final void zab(final GoogleApi<?> googleApi) {
        final zai<O> zak = googleApi.zak();
        zaa<?> zaa;
        if ((zaa = this.zaii.get(zak)) == null) {
            zaa = new zaa<Object>(googleApi);
            this.zaii.put(zak, zaa);
        }
        if (zaa.requiresSignIn()) {
            this.zail.add(zak);
        }
        zaa.connect();
    }
    
    public static GoogleApiManager zabc() {
        synchronized (GoogleApiManager.lock) {
            Preconditions.checkNotNull((Object)GoogleApiManager.zaic, (Object)"Must guarantee manager is non-null before using getInstance");
            return GoogleApiManager.zaic;
        }
    }
    
    @WorkerThread
    public boolean handleMessage(final Message message) {
        Label_0295: {
            switch (message.what) {
                default: {
                    Log.w("GoogleApiManager", new StringBuilder(31).append("Unknown message id: ").append(message.what).toString());
                    return false;
                }
                case 1: {
                    long zaib;
                    if (message.obj) {
                        zaib = 10000L;
                    }
                    else {
                        zaib = 300000L;
                    }
                    this.zaib = zaib;
                    this.handler.removeMessages(12);
                    final Iterator<zai<?>> iterator = this.zaii.keySet().iterator();
                    while (iterator.hasNext()) {
                        this.handler.sendMessageDelayed(this.handler.obtainMessage(12, (Object)iterator.next()), this.zaib);
                    }
                    break;
                }
                case 2: {
                    final zak zak = (zak)message.obj;
                    for (final zai<?> zai : zak.zap()) {
                        final zaa<?> zaa = this.zaii.get(zai);
                        if (zaa == null) {
                            zak.zaa(zai, new ConnectionResult(13), null);
                            break;
                        }
                        if (zaa.isConnected()) {
                            zak.zaa(zai, ConnectionResult.RESULT_SUCCESS, zaa.zaab().getEndpointPackageName());
                        }
                        else if (zaa.zabm() != null) {
                            zak.zaa(zai, zaa.zabm(), null);
                        }
                        else {
                            zaa.zaa(zak);
                            zaa.connect();
                        }
                    }
                    break;
                }
                case 3: {
                    for (final zaa<?> zaa2 : this.zaii.values()) {
                        zaa2.zabl();
                        zaa2.connect();
                    }
                    break;
                }
                case 4:
                case 8:
                case 13: {
                    final zabv zabv = (zabv)message.obj;
                    zaa<?> zaa3;
                    if ((zaa3 = this.zaii.get(zabv.zajt.zak())) == null) {
                        this.zab(zabv.zajt);
                        zaa3 = this.zaii.get(zabv.zajt.zak());
                    }
                    if (zaa3.requiresSignIn() && this.zaih.get() != zabv.zajs) {
                        zabv.zajr.zaa(GoogleApiManager.zahx);
                        zaa3.zabj();
                        break;
                    }
                    zaa3.zaa(zabv.zajr);
                    break;
                }
                case 5: {
                    final int arg1 = message.arg1;
                    final ConnectionResult connectionResult = (ConnectionResult)message.obj;
                    while (true) {
                        for (final zaa<?> zaa4 : this.zaii.values()) {
                            if (zaa4.getInstanceId() == arg1) {
                                if (zaa4 != null) {
                                    final String errorString = this.zaie.getErrorString(connectionResult.getErrorCode());
                                    final String errorMessage = connectionResult.getErrorMessage();
                                    zaa4.zac(new Status(17, new StringBuilder(String.valueOf(errorString).length() + 69 + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                                    break Label_0295;
                                }
                                Log.wtf("GoogleApiManager", new StringBuilder(76).append("Could not find API instance ").append(arg1).append(" while trying to fail enqueued calls.").toString(), (Throwable)new Exception());
                                break Label_0295;
                            }
                        }
                        zaa<?> zaa4 = null;
                        continue;
                    }
                }
                case 6: {
                    if (!PlatformVersion.isAtLeastIceCreamSandwich() || !(this.zaid.getApplicationContext() instanceof Application)) {
                        break;
                    }
                    BackgroundDetector.initialize((Application)this.zaid.getApplicationContext());
                    BackgroundDetector.getInstance().addListener((BackgroundDetector$BackgroundStateChangeListener)new zabi(this));
                    if (!BackgroundDetector.getInstance().readCurrentStateIfPossible(true)) {
                        this.zaib = 300000L;
                        break;
                    }
                    break;
                }
                case 7: {
                    this.zab((GoogleApi<?>)message.obj);
                    break;
                }
                case 9: {
                    if (this.zaii.containsKey(message.obj)) {
                        this.zaii.get(message.obj).resume();
                        break;
                    }
                    break;
                }
                case 10: {
                    final Iterator<zai<?>> iterator5 = this.zail.iterator();
                    while (iterator5.hasNext()) {
                        this.zaii.remove(iterator5.next()).zabj();
                    }
                    this.zail.clear();
                    break;
                }
                case 11: {
                    if (this.zaii.containsKey(message.obj)) {
                        this.zaii.get(message.obj).zaav();
                        break;
                    }
                    break;
                }
                case 12: {
                    if (this.zaii.containsKey(message.obj)) {
                        this.zaii.get(message.obj).zabp();
                        break;
                    }
                    break;
                }
                case 14: {
                    final zaaf zaaf = (zaaf)message.obj;
                    final zai<?> zak2 = zaaf.zak();
                    if (!this.zaii.containsKey(zak2)) {
                        zaaf.zaal().setResult((Object)false);
                        break;
                    }
                    zaaf.zaal().setResult((Object)zaa.zaa((zaa<Api.ApiOptions>)this.zaii.get(zak2), false));
                    break;
                }
                case 15: {
                    final zab zab = (zab)message.obj;
                    if (this.zaii.containsKey(zab.zajb)) {
                        ((zaa<Api.ApiOptions>)this.zaii.get(zab.zajb)).zaa(zab);
                        break;
                    }
                    break;
                }
                case 16: {
                    final zab zab2 = (zab)message.obj;
                    if (this.zaii.containsKey(zab2.zajb)) {
                        ((zaa<Api.ApiOptions>)this.zaii.get(zab2.zajb)).zab(zab2);
                        break;
                    }
                    break;
                }
            }
        }
        return true;
    }
    
    final void maybeSignOut() {
        this.zaih.incrementAndGet();
        this.handler.sendMessage(this.handler.obtainMessage(10));
    }
    
    final PendingIntent zaa(final zai<?> zai, final int n) {
        final zaa<?> zaa = this.zaii.get(zai);
        if (zaa == null) {
            return null;
        }
        final zad zabq = zaa.zabq();
        if (zabq == null) {
            return null;
        }
        return PendingIntent.getActivity(this.zaid, n, ((Api.Client)zabq).getSignInIntent(), 134217728);
    }
    
    public final <O extends Api.ApiOptions> Task<Boolean> zaa(@NonNull final GoogleApi<O> googleApi, @NonNull final ListenerHolder.ListenerKey<?> listenerKey) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(13, (Object)new zabv(new zah(listenerKey, (TaskCompletionSource<Boolean>)taskCompletionSource), this.zaih.get(), googleApi)));
        return (Task<Boolean>)taskCompletionSource.getTask();
    }
    
    public final <O extends Api.ApiOptions> Task<Void> zaa(@NonNull final GoogleApi<O> googleApi, @NonNull final RegisterListenerMethod<Api.AnyClient, ?> registerListenerMethod, @NonNull final UnregisterListenerMethod<Api.AnyClient, ?> unregisterListenerMethod) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.handler.sendMessage(this.handler.obtainMessage(8, (Object)new zabv(new zaf(new zabw(registerListenerMethod, unregisterListenerMethod), (TaskCompletionSource<Void>)taskCompletionSource), this.zaih.get(), googleApi)));
        return (Task<Void>)taskCompletionSource.getTask();
    }
    
    public final Task<Map<zai<?>, String>> zaa(final Iterable<? extends GoogleApi<?>> iterable) {
        final zak zak = new zak(iterable);
        this.handler.sendMessage(this.handler.obtainMessage(2, (Object)zak));
        return zak.getTask();
    }
    
    public final void zaa(final ConnectionResult connectionResult, final int n) {
        if (!this.zac(connectionResult, n)) {
            this.handler.sendMessage(this.handler.obtainMessage(5, n, 0, (Object)connectionResult));
        }
    }
    
    public final void zaa(final GoogleApi<?> googleApi) {
        this.handler.sendMessage(this.handler.obtainMessage(7, (Object)googleApi));
    }
    
    public final <O extends Api.ApiOptions> void zaa(final GoogleApi<O> googleApi, final int n, final BaseImplementation.ApiMethodImpl<? extends Result, Api.AnyClient> apiMethodImpl) {
        this.handler.sendMessage(this.handler.obtainMessage(4, (Object)new zabv(new zae<Object>(n, apiMethodImpl), this.zaih.get(), googleApi)));
    }
    
    public final <O extends Api.ApiOptions, ResultT> void zaa(final GoogleApi<O> googleApi, final int n, final TaskApiCall<Api.AnyClient, ResultT> taskApiCall, final TaskCompletionSource<ResultT> taskCompletionSource, final StatusExceptionMapper statusExceptionMapper) {
        this.handler.sendMessage(this.handler.obtainMessage(4, (Object)new zabv(new zag<Object>(n, taskApiCall, taskCompletionSource, statusExceptionMapper), this.zaih.get(), googleApi)));
    }
    
    public final void zaa(@NonNull final zaae zaij) {
        synchronized (GoogleApiManager.lock) {
            if (this.zaij != zaij) {
                this.zaij = zaij;
                this.zaik.clear();
            }
            this.zaik.addAll((Collection<? extends zai<?>>)zaij.zaaj());
        }
    }
    
    final void zab(@NonNull final zaae zaae) {
        synchronized (GoogleApiManager.lock) {
            if (this.zaij == zaae) {
                this.zaij = null;
                this.zaik.clear();
            }
        }
    }
    
    public final int zabd() {
        return this.zaig.getAndIncrement();
    }
    
    public final Task<Boolean> zac(final GoogleApi<?> googleApi) {
        final zaaf zaaf = new zaaf(googleApi.zak());
        this.handler.sendMessage(this.handler.obtainMessage(14, (Object)zaaf));
        return (Task<Boolean>)zaaf.zaal().getTask();
    }
    
    final boolean zac(final ConnectionResult connectionResult, final int n) {
        return this.zaie.zaa(this.zaid, connectionResult, n);
    }
    
    public final void zao() {
        this.handler.sendMessage(this.handler.obtainMessage(3));
    }
    
    public final class zaa<O extends Api.ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zar
    {
        private final zai<O> zafq;
        private final Queue<com.google.android.gms.common.api.internal.zab> zain;
        private final Api.Client zaio;
        private final Api.AnyClient zaip;
        private final zaab zaiq;
        private final Set<zak> zair;
        private final Map<ListenerHolder.ListenerKey<?>, zabw> zais;
        private final int zait;
        private final zace zaiu;
        private boolean zaiv;
        private final List<zab> zaiw;
        private ConnectionResult zaix;
        
        @WorkerThread
        public zaa(final GoogleApi<O> googleApi) {
            this.zain = new LinkedList<com.google.android.gms.common.api.internal.zab>();
            this.zair = new HashSet<zak>();
            this.zais = new HashMap<ListenerHolder.ListenerKey<?>, zabw>();
            this.zaiw = new ArrayList<zab>();
            this.zaix = null;
            this.zaio = googleApi.zaa(GoogleApiManager.this.handler.getLooper(), this);
            if (this.zaio instanceof SimpleClientAdapter) {
                this.zaip = ((SimpleClientAdapter)this.zaio).getClient();
            }
            else {
                this.zaip = this.zaio;
            }
            this.zafq = googleApi.zak();
            this.zaiq = new zaab();
            this.zait = googleApi.getInstanceId();
            if (this.zaio.requiresSignIn()) {
                this.zaiu = googleApi.zaa(GoogleApiManager.this.zaid, GoogleApiManager.this.handler);
                return;
            }
            this.zaiu = null;
        }
        
        @Nullable
        @WorkerThread
        private final Feature zaa(@Nullable final Feature[] array) {
            if (array == null || array.length == 0) {
                return null;
            }
            Feature[] availableFeatures;
            if ((availableFeatures = this.zaio.getAvailableFeatures()) == null) {
                availableFeatures = new Feature[0];
            }
            final ArrayMap arrayMap = new ArrayMap(availableFeatures.length);
            for (int length = availableFeatures.length, i = 0; i < length; ++i) {
                final Feature feature = availableFeatures[i];
                ((Map<String, Long>)arrayMap).put(feature.getName(), Long.valueOf(feature.getVersion()));
            }
            for (int length2 = array.length, j = 0; j < length2; ++j) {
                final Feature feature2 = array[j];
                if (!((Map)arrayMap).containsKey(feature2.getName()) || ((Map<String, Long>)arrayMap).get(feature2.getName()) < feature2.getVersion()) {
                    return feature2;
                }
            }
            return null;
        }
        
        @WorkerThread
        private final void zaa(final zab zab) {
            if (!this.zaiw.contains(zab) || this.zaiv) {
                return;
            }
            if (!this.zaio.isConnected()) {
                this.connect();
                return;
            }
            this.zabi();
        }
        
        static /* synthetic */ boolean zaa(final zaa zaa, final boolean b) {
            return zaa.zac(false);
        }
        
        @WorkerThread
        private final void zab(final zab zab) {
            if (this.zaiw.remove(zab)) {
                GoogleApiManager.this.handler.removeMessages(15, (Object)zab);
                GoogleApiManager.this.handler.removeMessages(16, (Object)zab);
                final Feature zad = zab.zajc;
                final ArrayList<com.google.android.gms.common.api.internal.zac> list = new ArrayList<com.google.android.gms.common.api.internal.zac>(this.zain.size());
                for (final com.google.android.gms.common.api.internal.zab zab2 : this.zain) {
                    if (zab2 instanceof com.google.android.gms.common.api.internal.zac) {
                        final Feature[] zab3 = ((com.google.android.gms.common.api.internal.zac)zab2).zab((zaa<?>)this);
                        if (zab3 == null || !ArrayUtils.contains((Object[])zab3, (Object)zad)) {
                            continue;
                        }
                        list.add((com.google.android.gms.common.api.internal.zac)zab2);
                    }
                }
                final ArrayList<com.google.android.gms.common.api.internal.zac> list2 = list;
                final int size = list2.size();
                int i = 0;
                while (i < size) {
                    final com.google.android.gms.common.api.internal.zac value = list2.get(i);
                    ++i;
                    final com.google.android.gms.common.api.internal.zac zac = value;
                    this.zain.remove(zac);
                    zac.zaa((RuntimeException)new UnsupportedApiCallException(zad));
                }
            }
        }
        
        @WorkerThread
        private final boolean zab(final com.google.android.gms.common.api.internal.zab zab) {
            if (!(zab instanceof com.google.android.gms.common.api.internal.zac)) {
                this.zac(zab);
                return true;
            }
            final com.google.android.gms.common.api.internal.zac zac = (com.google.android.gms.common.api.internal.zac)zab;
            final Feature zaa = this.zaa(zac.zab((zaa<?>)this));
            if (zaa == null) {
                this.zac(zab);
                return true;
            }
            if (zac.zac((zaa<?>)this)) {
                final zab zab2 = new zab(this.zafq, zaa, null);
                final int index = this.zaiw.indexOf(zab2);
                if (index >= 0) {
                    final zab zab3 = this.zaiw.get(index);
                    GoogleApiManager.this.handler.removeMessages(15, (Object)zab3);
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, (Object)zab3), GoogleApiManager.this.zahz);
                }
                else {
                    this.zaiw.add(zab2);
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 15, (Object)zab2), GoogleApiManager.this.zahz);
                    GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 16, (Object)zab2), GoogleApiManager.this.zaia);
                    final ConnectionResult connectionResult = new ConnectionResult(2, (PendingIntent)null);
                    if (!this.zah(connectionResult)) {
                        GoogleApiManager.this.zac(connectionResult, this.zait);
                    }
                }
            }
            else {
                zac.zaa((RuntimeException)new UnsupportedApiCallException(zaa));
            }
            return false;
        }
        
        @WorkerThread
        private final void zabg() {
            this.zabl();
            this.zai(ConnectionResult.RESULT_SUCCESS);
            this.zabn();
            final Iterator<zabw> iterator = this.zais.values().iterator();
            while (iterator.hasNext()) {
                final zabw zabw = iterator.next();
                if (this.zaa(zabw.zajx.getRequiredFeatures()) != null) {
                    iterator.remove();
                }
                else {
                    try {
                        zabw.zajx.registerListener(this.zaip, (TaskCompletionSource<Void>)new TaskCompletionSource());
                    }
                    catch (DeadObjectException ex) {
                        this.onConnectionSuspended(1);
                        this.zaio.disconnect();
                    }
                    catch (RemoteException ex2) {
                        iterator.remove();
                    }
                }
            }
            goto Label_0108;
        }
        
        @WorkerThread
        private final void zabh() {
            this.zabl();
            this.zaiv = true;
            this.zaiq.zaai();
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, (Object)this.zafq), GoogleApiManager.this.zahz);
            GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 11, (Object)this.zafq), GoogleApiManager.this.zaia);
            GoogleApiManager.this.zaif.flush();
        }
        
        @WorkerThread
        private final void zabi() {
            final ArrayList<Object> list = new ArrayList<Object>(this.zain);
            final int size = list.size();
            int i = 0;
            while (i < size) {
                final com.google.android.gms.common.api.internal.zab value = list.get(i);
                final int n = i + 1;
                final com.google.android.gms.common.api.internal.zab zab = value;
                if (!this.zaio.isConnected()) {
                    break;
                }
                i = n;
                if (!this.zab(zab)) {
                    continue;
                }
                this.zain.remove(zab);
                i = n;
            }
        }
        
        @WorkerThread
        private final void zabn() {
            if (this.zaiv) {
                GoogleApiManager.this.handler.removeMessages(11, (Object)this.zafq);
                GoogleApiManager.this.handler.removeMessages(9, (Object)this.zafq);
                this.zaiv = false;
            }
        }
        
        private final void zabo() {
            GoogleApiManager.this.handler.removeMessages(12, (Object)this.zafq);
            GoogleApiManager.this.handler.sendMessageDelayed(GoogleApiManager.this.handler.obtainMessage(12, (Object)this.zafq), GoogleApiManager.this.zaib);
        }
        
        @WorkerThread
        private final void zac(final com.google.android.gms.common.api.internal.zab zab) {
            zab.zaa(this.zaiq, this.requiresSignIn());
            try {
                zab.zaa((zaa<?>)this);
            }
            catch (DeadObjectException ex) {
                this.onConnectionSuspended(1);
                this.zaio.disconnect();
            }
        }
        
        @WorkerThread
        private final boolean zac(final boolean b) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaio.isConnected() && this.zais.size() == 0) {
                if (!this.zaiq.zaag()) {
                    this.zaio.disconnect();
                    return true;
                }
                if (b) {
                    this.zabo();
                }
            }
            return false;
        }
        
        @WorkerThread
        private final boolean zah(@NonNull final ConnectionResult connectionResult) {
            synchronized (GoogleApiManager.lock) {
                if (GoogleApiManager.this.zaij != null && GoogleApiManager.this.zaik.contains(this.zafq)) {
                    GoogleApiManager.this.zaij.zab(connectionResult, this.zait);
                    return true;
                }
                return false;
            }
        }
        
        @WorkerThread
        private final void zai(final ConnectionResult connectionResult) {
            for (final zak zak : this.zair) {
                String endpointPackageName = null;
                if (Objects.equal((Object)connectionResult, (Object)ConnectionResult.RESULT_SUCCESS)) {
                    endpointPackageName = this.zaio.getEndpointPackageName();
                }
                zak.zaa(this.zafq, connectionResult, endpointPackageName);
            }
            this.zair.clear();
        }
        
        @WorkerThread
        public final void connect() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaio.isConnected() || this.zaio.isConnecting()) {
                return;
            }
            final int clientAvailability = GoogleApiManager.this.zaif.getClientAvailability(GoogleApiManager.this.zaid, this.zaio);
            if (clientAvailability != 0) {
                this.onConnectionFailed(new ConnectionResult(clientAvailability, (PendingIntent)null));
                return;
            }
            final zac zac = new zac(this.zaio, this.zafq);
            if (this.zaio.requiresSignIn()) {
                this.zaiu.zaa(zac);
            }
            this.zaio.connect((BaseGmsClient$ConnectionProgressReportCallbacks)zac);
        }
        
        public final int getInstanceId() {
            return this.zait;
        }
        
        final boolean isConnected() {
            return this.zaio.isConnected();
        }
        
        @Override
        public final void onConnected(@Nullable final Bundle bundle) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                this.zabg();
                return;
            }
            GoogleApiManager.this.handler.post((Runnable)new zabj(this));
        }
        
        @WorkerThread
        @Override
        public final void onConnectionFailed(@NonNull final ConnectionResult zaix) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiu != null) {
                this.zaiu.zabs();
            }
            this.zabl();
            GoogleApiManager.this.zaif.flush();
            this.zai(zaix);
            if (zaix.getErrorCode() == 4) {
                this.zac(GoogleApiManager.zahy);
            }
            else {
                if (this.zain.isEmpty()) {
                    this.zaix = zaix;
                    return;
                }
                if (!this.zah(zaix) && !GoogleApiManager.this.zac(zaix, this.zait)) {
                    if (zaix.getErrorCode() == 18) {
                        this.zaiv = true;
                    }
                    if (this.zaiv) {
                        GoogleApiManager.this.handler.sendMessageDelayed(Message.obtain(GoogleApiManager.this.handler, 9, (Object)this.zafq), GoogleApiManager.this.zahz);
                        return;
                    }
                    final String zan = this.zafq.zan();
                    this.zac(new Status(17, new StringBuilder(String.valueOf(zan).length() + 38).append("API: ").append(zan).append(" is not available on this device.").toString()));
                }
            }
        }
        
        @Override
        public final void onConnectionSuspended(final int n) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                this.zabh();
                return;
            }
            GoogleApiManager.this.handler.post((Runnable)new zabk(this));
        }
        
        public final boolean requiresSignIn() {
            return this.zaio.requiresSignIn();
        }
        
        @WorkerThread
        public final void resume() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiv) {
                this.connect();
            }
        }
        
        @Override
        public final void zaa(final ConnectionResult connectionResult, final Api<?> api, final boolean b) {
            if (Looper.myLooper() == GoogleApiManager.this.handler.getLooper()) {
                this.onConnectionFailed(connectionResult);
                return;
            }
            GoogleApiManager.this.handler.post((Runnable)new zabl(this, connectionResult));
        }
        
        @WorkerThread
        public final void zaa(final com.google.android.gms.common.api.internal.zab zab) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaio.isConnected()) {
                if (this.zab(zab)) {
                    this.zabo();
                    return;
                }
                this.zain.add(zab);
            }
            else {
                this.zain.add(zab);
                if (this.zaix != null && this.zaix.hasResolution()) {
                    this.onConnectionFailed(this.zaix);
                    return;
                }
                this.connect();
            }
        }
        
        @WorkerThread
        public final void zaa(final zak zak) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zair.add(zak);
        }
        
        public final Api.Client zaab() {
            return this.zaio;
        }
        
        @WorkerThread
        public final void zaav() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            if (this.zaiv) {
                this.zabn();
                Status status;
                if (GoogleApiManager.this.zaie.isGooglePlayServicesAvailable(GoogleApiManager.this.zaid) == 18) {
                    status = new Status(8, "Connection timed out while waiting for Google Play services update to complete.");
                }
                else {
                    status = new Status(8, "API failed to connect while resuming due to an unknown error.");
                }
                this.zac(status);
                this.zaio.disconnect();
            }
        }
        
        @WorkerThread
        public final void zabj() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zac(GoogleApiManager.zahx);
            this.zaiq.zaah();
            final ListenerHolder.ListenerKey[] array = this.zais.keySet().toArray((ListenerHolder.ListenerKey[])new ListenerHolder.ListenerKey[this.zais.size()]);
            for (int length = array.length, i = 0; i < length; ++i) {
                this.zaa(new zah(array[i], (TaskCompletionSource<Boolean>)new TaskCompletionSource()));
            }
            this.zai(new ConnectionResult(4));
            if (this.zaio.isConnected()) {
                this.zaio.onUserSignOut((BaseGmsClient$SignOutCallbacks)new zabm(this));
            }
        }
        
        public final Map<ListenerHolder.ListenerKey<?>, zabw> zabk() {
            return this.zais;
        }
        
        @WorkerThread
        public final void zabl() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaix = null;
        }
        
        @WorkerThread
        public final ConnectionResult zabm() {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            return this.zaix;
        }
        
        @WorkerThread
        public final boolean zabp() {
            return this.zac(true);
        }
        
        final zad zabq() {
            if (this.zaiu == null) {
                return null;
            }
            return this.zaiu.zabq();
        }
        
        @WorkerThread
        public final void zac(final Status status) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            final Iterator<com.google.android.gms.common.api.internal.zab> iterator = this.zain.iterator();
            while (iterator.hasNext()) {
                iterator.next().zaa(status);
            }
            this.zain.clear();
        }
        
        @WorkerThread
        public final void zag(@NonNull final ConnectionResult connectionResult) {
            Preconditions.checkHandlerThread(GoogleApiManager.this.handler);
            this.zaio.disconnect();
            this.onConnectionFailed(connectionResult);
        }
    }
    
    private static final class zab
    {
        private final zai<?> zajb;
        private final Feature zajc;
        
        private zab(final zai<?> zajb, final Feature zajc) {
            this.zajb = zajb;
            this.zajc = zajc;
        }
        
        @Override
        public final boolean equals(final Object o) {
            boolean b2;
            final boolean b = b2 = false;
            if (o != null) {
                b2 = b;
                if (o instanceof zab) {
                    final zab zab = (zab)o;
                    b2 = b;
                    if (Objects.equal((Object)this.zajb, (Object)zab.zajb)) {
                        b2 = b;
                        if (Objects.equal((Object)this.zajc, (Object)zab.zajc)) {
                            b2 = true;
                        }
                    }
                }
            }
            return b2;
        }
        
        @Override
        public final int hashCode() {
            return Objects.hashCode(new Object[] { this.zajb, this.zajc });
        }
        
        @Override
        public final String toString() {
            return Objects.toStringHelper((Object)this).add("key", (Object)this.zajb).add("feature", (Object)this.zajc).toString();
        }
    }
    
    private final class zac implements zach, BaseGmsClient$ConnectionProgressReportCallbacks
    {
        private final zai<?> zafq;
        private final Api.Client zaio;
        private IAccountAccessor zajd;
        private Set<Scope> zaje;
        private boolean zajf;
        
        public zac(final Api.Client zaio, final zai<?> zafq) {
            this.zajd = null;
            this.zaje = null;
            this.zajf = false;
            this.zaio = zaio;
            this.zafq = zafq;
        }
        
        @WorkerThread
        private final void zabr() {
            if (this.zajf && this.zajd != null) {
                this.zaio.getRemoteService(this.zajd, this.zaje);
            }
        }
        
        public final void onReportServiceBinding(@NonNull final ConnectionResult connectionResult) {
            GoogleApiManager.this.handler.post((Runnable)new zabo(this, connectionResult));
        }
        
        @WorkerThread
        @Override
        public final void zaa(final IAccountAccessor zajd, final Set<Scope> zaje) {
            if (zajd == null || zaje == null) {
                Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", (Throwable)new Exception());
                this.zag(new ConnectionResult(4));
                return;
            }
            this.zajd = zajd;
            this.zaje = zaje;
            this.zabr();
        }
        
        @WorkerThread
        @Override
        public final void zag(final ConnectionResult connectionResult) {
            GoogleApiManager.this.zaii.get(this.zafq).zag(connectionResult);
        }
    }
}
