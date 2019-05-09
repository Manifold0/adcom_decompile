package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.Api.AnyClientKey;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.ClientSettings.OptionalApiSettings;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

public final class zax implements zabs {
    private final Looper zabj;
    private final GoogleApiManager zabm;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<AnyClientKey<?>, zaw<?>> zaeu = new HashMap();
    private final Map<AnyClientKey<?>, zaw<?>> zaev = new HashMap();
    private final Map<Api<?>, Boolean> zaew;
    private final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    private final Condition zaez;
    private final boolean zafa;
    private final boolean zafb;
    private final Queue<ApiMethodImpl<?, ?>> zafc = new LinkedList();
    @GuardedBy("mLock")
    private boolean zafd;
    @GuardedBy("mLock")
    private Map<zai<?>, ConnectionResult> zafe;
    @GuardedBy("mLock")
    private Map<zai<?>, ConnectionResult> zaff;
    @GuardedBy("mLock")
    private zaaa zafg;
    @GuardedBy("mLock")
    private ConnectionResult zafh;

    public zax(Context context, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<AnyClientKey<?>, Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList, zaaw zaaw, boolean z) {
        this.zaeo = lock;
        this.zabj = looper;
        this.zaez = lock.newCondition();
        this.zaey = googleApiAvailabilityLight;
        this.zaex = zaaw;
        this.zaew = map2;
        this.zaet = clientSettings;
        this.zafa = z;
        Map hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        Map hashMap2 = new HashMap();
        arrayList = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            zaq zaq = (zaq) obj;
            hashMap2.put(zaq.mApi, zaq);
        }
        Object obj2 = 1;
        Object obj3 = null;
        Object obj4 = null;
        for (Entry entry : map.entrySet()) {
            Object obj5;
            Object obj6;
            Object obj7;
            Api api2 = (Api) hashMap.get(entry.getKey());
            Client client = (Client) entry.getValue();
            if (client.requiresGooglePlayServices()) {
                obj5 = 1;
                if (((Boolean) this.zaew.get(api2)).booleanValue()) {
                    obj6 = obj2;
                    obj7 = obj3;
                } else {
                    obj6 = obj2;
                    obj7 = 1;
                }
            } else {
                obj5 = obj4;
                obj6 = null;
                obj7 = obj3;
            }
            zaw zaw = new zaw(context, api2, looper, client, (zaq) hashMap2.get(api2), clientSettings, abstractClientBuilder);
            this.zaeu.put((AnyClientKey) entry.getKey(), zaw);
            if (client.requiresSignIn()) {
                this.zaev.put((AnyClientKey) entry.getKey(), zaw);
            }
            obj4 = obj5;
            obj2 = obj6;
            obj3 = obj7;
        }
        boolean z2 = obj4 != null && obj2 == null && obj3 == null;
        this.zafb = z2;
        this.zabm = GoogleApiManager.zabc();
    }

    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (this.zafa && zab((ApiMethodImpl) t)) {
            return t;
        }
        if (isConnected()) {
            this.zaex.zahf.zab(t);
            return ((zaw) this.zaeu.get(t.getClientKey())).doRead((ApiMethodImpl) t);
        }
        this.zafc.add(t);
        return t;
    }

    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        AnyClientKey clientKey = t.getClientKey();
        if (this.zafa && zab((ApiMethodImpl) t)) {
            return t;
        }
        this.zaex.zahf.zab(t);
        return ((zaw) this.zaeu.get(clientKey)).doWrite((ApiMethodImpl) t);
    }

    private final <T extends ApiMethodImpl<? extends Result, ? extends AnyClient>> boolean zab(@NonNull T t) {
        AnyClientKey clientKey = t.getClientKey();
        ConnectionResult zaa = zaa(clientKey);
        if (zaa == null || zaa.getErrorCode() != 4) {
            return false;
        }
        t.setFailedResult(new Status(4, null, this.zabm.zaa(((zaw) this.zaeu.get(clientKey)).zak(), System.identityHashCode(this.zaex))));
        return true;
    }

    public final void connect() {
        this.zaeo.lock();
        try {
            if (!this.zafd) {
                this.zafd = true;
                this.zafe = null;
                this.zaff = null;
                this.zafg = null;
                this.zafh = null;
                this.zabm.zao();
                this.zabm.zaa(this.zaeu.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), new zaz());
                this.zaeo.unlock();
            }
        } finally {
            this.zaeo.unlock();
        }
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zaez.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafh != null) {
            return this.zafh;
        }
        return new ConnectionResult(13, null);
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long toNanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (toNanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            }
            toNanos = this.zaez.awaitNanos(toNanos);
        }
        if (isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafh != null) {
            return this.zafh;
        }
        return new ConnectionResult(13, null);
    }

    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zafd = false;
            this.zafe = null;
            this.zaff = null;
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            this.zafh = null;
            while (!this.zafc.isEmpty()) {
                ApiMethodImpl apiMethodImpl = (ApiMethodImpl) this.zafc.remove();
                apiMethodImpl.zaa(null);
                apiMethodImpl.cancel();
            }
            this.zaez.signalAll();
        } finally {
            this.zaeo.unlock();
        }
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zaa(api.getClientKey());
    }

    @Nullable
    private final ConnectionResult zaa(@NonNull AnyClientKey<?> anyClientKey) {
        this.zaeo.lock();
        try {
            zaw zaw = (zaw) this.zaeu.get(anyClientKey);
            if (this.zafe == null || zaw == null) {
                this.zaeo.unlock();
                return null;
            }
            ConnectionResult connectionResult = (ConnectionResult) this.zafe.get(zaw.zak());
            return connectionResult;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            boolean z = this.zafe != null && this.zafh == null;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            boolean z = this.zafe == null && this.zafd;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    private final boolean zaac() {
        this.zaeo.lock();
        try {
            if (this.zafd && this.zafa) {
                for (AnyClientKey zaa : this.zaev.keySet()) {
                    ConnectionResult zaa2 = zaa(zaa);
                    if (zaa2 != null) {
                        if (!zaa2.isSuccess()) {
                        }
                    }
                    this.zaeo.unlock();
                    return false;
                }
                this.zaeo.unlock();
                return true;
            }
            this.zaeo.unlock();
            return false;
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if (!this.zafd || zaac()) {
                this.zaeo.unlock();
                return false;
            }
            this.zabm.zao();
            this.zafg = new zaaa(this, signInConnectionListener);
            this.zabm.zaa(this.zaev.values()).addOnCompleteListener(new HandlerExecutor(this.zabj), this.zafg);
            return true;
        } finally {
            this.zaeo.unlock();
        }
    }

    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            this.zabm.maybeSignOut();
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            if (this.zaff == null) {
                this.zaff = new ArrayMap(this.zaev.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zaw zak : this.zaev.values()) {
                this.zaff.put(zak.zak(), connectionResult);
            }
            if (this.zafe != null) {
                this.zafe.putAll(this.zaff);
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    public final void zaw() {
    }

    @GuardedBy("mLock")
    private final void zaad() {
        if (this.zaet == null) {
            this.zaex.zaha = Collections.emptySet();
            return;
        }
        Set hashSet = new HashSet(this.zaet.getRequiredScopes());
        Map optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (Api api : optionalApiSettings.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((OptionalApiSettings) optionalApiSettings.get(api)).mScopes);
            }
        }
        this.zaex.zaha = hashSet;
    }

    @GuardedBy("mLock")
    private final void zaae() {
        while (!this.zafc.isEmpty()) {
            execute((ApiMethodImpl) this.zafc.remove());
        }
        this.zaex.zab(null);
    }

    private final boolean zaa(zaw<?> zaw, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zaew.get(zaw.getApi())).booleanValue() && zaw.zaab().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }

    @Nullable
    @GuardedBy("mLock")
    private final ConnectionResult zaaf() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zaw zaw : this.zaeu.values()) {
            Api api = zaw.getApi();
            ConnectionResult connectionResult3 = (ConnectionResult) this.zafe.get(zaw.zak());
            if (!connectionResult3.isSuccess() && (!((Boolean) this.zaew.get(api)).booleanValue() || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                int priority;
                if (connectionResult3.getErrorCode() == 4 && this.zafa) {
                    priority = api.zah().getPriority();
                    if (connectionResult == null || i > priority) {
                        i = priority;
                        connectionResult = connectionResult3;
                    }
                } else {
                    ConnectionResult connectionResult4;
                    int i3;
                    priority = api.zah().getPriority();
                    if (connectionResult2 == null || i2 > priority) {
                        int i4 = priority;
                        connectionResult4 = connectionResult3;
                        i3 = i4;
                    } else {
                        i3 = i2;
                        connectionResult4 = connectionResult2;
                    }
                    i2 = i3;
                    connectionResult2 = connectionResult4;
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }
}
