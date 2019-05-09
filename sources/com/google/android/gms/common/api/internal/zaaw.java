package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.internal.GmsClientEventManager.GmsClientEventState;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.service.Common;
import com.google.android.gms.common.util.ClientLibraryUtils;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.tapjoy.TapjoyConstants;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zaaw extends GoogleApiClient implements zabt {
    private final Context mContext;
    private final Looper zabj;
    private final int zacb;
    private final GoogleApiAvailability zacd;
    private final AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private boolean zach;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    @VisibleForTesting
    final Queue<ApiMethodImpl<?, ?>> zafc = new LinkedList();
    private final GmsClientEventManager zags;
    private zabs zagt = null;
    private volatile boolean zagu;
    private long zagv;
    private long zagw;
    private final zabb zagx;
    @VisibleForTesting
    private zabq zagy;
    final Map<AnyClientKey<?>, Client> zagz;
    Set<Scope> zaha;
    private final ListenerHolders zahb;
    private final ArrayList<zaq> zahc;
    private Integer zahd;
    Set<zacm> zahe;
    final zacp zahf;
    private final GmsClientEventState zahg;

    public zaaw(Context context, Lock lock, Looper looper, ClientSettings clientSettings, GoogleApiAvailability googleApiAvailability, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Map<Api<?>, Boolean> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<AnyClientKey<?>, Client> map2, int i, int i2, ArrayList<zaq> arrayList, boolean z) {
        this.zagv = ClientLibraryUtils.isPackageSide() ? TapjoyConstants.TIMER_INCREMENT : 120000;
        this.zagw = 5000;
        this.zaha = new HashSet();
        this.zahb = new ListenerHolders();
        this.zahd = null;
        this.zahe = null;
        this.zahg = new zaax(this);
        this.mContext = context;
        this.zaeo = lock;
        this.zach = false;
        this.zags = new GmsClientEventManager(looper, this.zahg);
        this.zabj = looper;
        this.zagx = new zabb(this, looper);
        this.zacd = googleApiAvailability;
        this.zacb = i;
        if (this.zacb >= 0) {
            this.zahd = Integer.valueOf(i2);
        }
        this.zaew = map;
        this.zagz = map2;
        this.zahc = arrayList;
        this.zahf = new zacp(this.zagz);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zags.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zags.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zaet = clientSettings;
        this.zace = abstractClientBuilder;
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        Preconditions.checkArgument(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                this.zafc.add(t);
            } else {
                t = this.zagt.enqueue(t);
                this.zaeo.unlock();
            }
            return t;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        Preconditions.checkArgument(t.getClientKey() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zagz.containsKey(t.getClientKey());
        String name = t.getApi() != null ? t.getApi().getName() : "the API";
        Preconditions.checkArgument(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zaeo.lock();
        try {
            if (this.zagt == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (this.zagu) {
                this.zafc.add(t);
                while (!this.zafc.isEmpty()) {
                    ApiMethodImpl apiMethodImpl = (ApiMethodImpl) this.zafc.remove();
                    this.zahf.zab(apiMethodImpl);
                    apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                }
            } else {
                t = this.zagt.execute(t);
                this.zaeo.unlock();
            }
            return t;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final <L> ListenerHolder<L> registerListener(@NonNull L l) {
        this.zaeo.lock();
        try {
            ListenerHolder<L> zaa = this.zahb.zaa(l, this.zabj, "NO_TYPE");
            return zaa;
        } finally {
            this.zaeo.unlock();
        }
    }

    @NonNull
    public final <C extends Client> C getClient(@NonNull AnyClientKey<C> anyClientKey) {
        Client client = (Client) this.zagz.get(anyClientKey);
        Preconditions.checkNotNull(client, "Appropriate Api was not requested.");
        return client;
    }

    public final boolean hasApi(@NonNull Api<?> api) {
        return this.zagz.containsKey(api.getClientKey());
    }

    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        if (!isConnected()) {
            return false;
        }
        Client client = (Client) this.zagz.get(api.getClientKey());
        return client != null && client.isConnected();
    }

    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.zaeo.lock();
        try {
            if (!isConnected() && !this.zagu) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zagz.containsKey(api.getClientKey())) {
                ConnectionResult connectionResult = this.zagt.getConnectionResult(api);
                if (connectionResult != null) {
                    this.zaeo.unlock();
                } else if (this.zagu) {
                    connectionResult = ConnectionResult.RESULT_SUCCESS;
                } else {
                    Log.w("GoogleApiClientImpl", zaay());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.zaeo.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void connect() {
        boolean z = false;
        this.zaeo.lock();
        try {
            if (this.zacb >= 0) {
                if (this.zahd != null) {
                    z = true;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zahd.intValue());
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void connect(int i) {
        boolean z = true;
        this.zaeo.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            Preconditions.checkArgument(z, "Illegal sign-in mode: " + i);
            zae(i);
            zaau();
        } finally {
            this.zaeo.unlock();
        }
    }

    public final ConnectionResult blockingConnect() {
        boolean z = true;
        Preconditions.checkState(Looper.myLooper() != Looper.getMainLooper(), "blockingConnect must not be called on the UI thread");
        this.zaeo.lock();
        try {
            if (this.zacb >= 0) {
                if (this.zahd == null) {
                    z = false;
                }
                Preconditions.checkState(z, "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            ConnectionResult blockingConnect = this.zagt.blockingConnect();
            return blockingConnect;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        Preconditions.checkState(z, "blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zaeo.lock();
        try {
            if (this.zahd == null) {
                this.zahd = Integer.valueOf(zaa(this.zagz.values(), false));
            } else if (this.zahd.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zae(this.zahd.intValue());
            this.zags.enableCallbacks();
            ConnectionResult blockingConnect = this.zagt.blockingConnect(j, timeUnit);
            return blockingConnect;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zahf.release();
            if (this.zagt != null) {
                this.zagt.disconnect();
            }
            this.zahb.release();
            for (ApiMethodImpl apiMethodImpl : this.zafc) {
                apiMethodImpl.zaa(null);
                apiMethodImpl.cancel();
            }
            this.zafc.clear();
            if (this.zagt != null) {
                zaaw();
                this.zags.disableCallbacks();
                this.zaeo.unlock();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(isConnected(), "GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahd.intValue() != 2, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        PendingResult statusPendingResult = new StatusPendingResult((GoogleApiClient) this);
        if (this.zagz.containsKey(Common.CLIENT_KEY)) {
            zaa(this, statusPendingResult, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zaay(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaaz(this, statusPendingResult)).setHandler(this.zagx).build();
            atomicReference.set(build);
            build.connect();
        }
        return statusPendingResult;
    }

    private final void zaa(GoogleApiClient googleApiClient, StatusPendingResult statusPendingResult, boolean z) {
        Common.zapi.zaa(googleApiClient).setResultCallback(new zaba(this, statusPendingResult, z, googleApiClient));
    }

    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        LifecycleActivity lifecycleActivity = new LifecycleActivity(fragmentActivity);
        if (this.zacb >= 0) {
            zaj.zaa(lifecycleActivity).zaa(this.zacb);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public final boolean isConnected() {
        return this.zagt != null && this.zagt.isConnected();
    }

    public final boolean isConnecting() {
        return this.zagt != null && this.zagt.isConnecting();
    }

    private final void zae(int i) {
        if (this.zahd == null) {
            this.zahd = Integer.valueOf(i);
        } else if (this.zahd.intValue() != i) {
            String zaf = zaf(i);
            String zaf2 = zaf(this.zahd.intValue());
            throw new IllegalStateException(new StringBuilder((String.valueOf(zaf).length() + 51) + String.valueOf(zaf2).length()).append("Cannot use sign-in mode: ").append(zaf).append(". Mode was already set to ").append(zaf2).toString());
        }
        if (this.zagt == null) {
            boolean z = false;
            boolean z2 = false;
            for (Client client : this.zagz.values()) {
                boolean z3;
                if (client.requiresSignIn()) {
                    z2 = true;
                }
                if (client.providesSignIn()) {
                    z3 = true;
                } else {
                    z3 = z;
                }
                z = z3;
            }
            switch (this.zahd.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        if (this.zach) {
                            this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, true);
                            return;
                        } else {
                            this.zagt = zas.zaa(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc);
                            return;
                        }
                    }
                    break;
            }
            if (!this.zach || z) {
                this.zagt = new zabe(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this);
            } else {
                this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, false);
            }
        }
    }

    @GuardedBy("mLock")
    private final void zaau() {
        this.zags.enableCallbacks();
        this.zagt.connect();
    }

    private final void resume() {
        this.zaeo.lock();
        try {
            if (this.zagu) {
                zaau();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    private final void zaav() {
        this.zaeo.lock();
        try {
            if (zaaw()) {
                zaau();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    @GuardedBy("mLock")
    final boolean zaaw() {
        if (!this.zagu) {
            return false;
        }
        this.zagu = false;
        this.zagx.removeMessages(2);
        this.zagx.removeMessages(1);
        if (this.zagy != null) {
            this.zagy.unregister();
            this.zagy = null;
        }
        return true;
    }

    public final void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }

    public final boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public final void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public final void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public final boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public final void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @GuardedBy("mLock")
    public final void zab(Bundle bundle) {
        while (!this.zafc.isEmpty()) {
            execute((ApiMethodImpl) this.zafc.remove());
        }
        this.zags.onConnectionSuccess(bundle);
    }

    @GuardedBy("mLock")
    public final void zac(ConnectionResult connectionResult) {
        if (!this.zacd.isPlayServicesPossiblyUpdating(this.mContext, connectionResult.getErrorCode())) {
            zaaw();
        }
        if (!this.zagu) {
            this.zags.onConnectionFailure(connectionResult);
            this.zags.disableCallbacks();
        }
    }

    @GuardedBy("mLock")
    public final void zab(int i, boolean z) {
        if (!(i != 1 || z || this.zagu)) {
            this.zagu = true;
            if (this.zagy == null && !ClientLibraryUtils.isPackageSide()) {
                this.zagy = this.zacd.zaa(this.mContext.getApplicationContext(), new zabc(this));
            }
            this.zagx.sendMessageDelayed(this.zagx.obtainMessage(1), this.zagv);
            this.zagx.sendMessageDelayed(this.zagx.obtainMessage(2), this.zagw);
        }
        this.zahf.zabx();
        this.zags.onUnintentionalDisconnection(i);
        this.zags.disableCallbacks();
        if (i == 2) {
            zaau();
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zabj;
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        return this.zagt != null && this.zagt.maybeSignIn(signInConnectionListener);
    }

    public final void maybeSignOut() {
        if (this.zagt != null) {
            this.zagt.maybeSignOut();
        }
    }

    public final void zaa(zacm zacm) {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                this.zahe = new HashSet();
            }
            this.zahe.add(zacm);
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void zab(zacm zacm) {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zahe.remove(zacm)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zaax()) {
                this.zagt.zaw();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    final boolean zaax() {
        boolean z = false;
        this.zaeo.lock();
        try {
            if (this.zahe != null) {
                if (!this.zahe.isEmpty()) {
                    z = true;
                }
                this.zaeo.unlock();
            }
            return z;
        } finally {
            this.zaeo.unlock();
        }
    }

    final String zaay() {
        Writer stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zagu);
        printWriter.append(" mWorkQueue.size()=").print(this.zafc.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zahf.zakz.size());
        if (this.zagt != null) {
            this.zagt.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    public static int zaa(Iterable<Client> iterable, boolean z) {
        int i = 0;
        int i2 = 0;
        for (Client client : iterable) {
            int i3;
            if (client.requiresSignIn()) {
                i2 = 1;
            }
            if (client.providesSignIn()) {
                i3 = 1;
            } else {
                i3 = i;
            }
            i = i3;
        }
        if (i2 == 0) {
            return 3;
        }
        if (i == 0 || !z) {
            return 1;
        }
        return 2;
    }

    private static String zaf(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }
}
