package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
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
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.base.zap;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import javax.annotation.concurrent.GuardedBy;

final class zas implements zabs {
    private final Context mContext;
    private final Looper zabj;
    private final zaaw zaee;
    private final zabe zaef;
    private final zabe zaeg;
    private final Map<AnyClientKey<?>, zabe> zaeh;
    private final Set<SignInConnectionListener> zaei = Collections.newSetFromMap(new WeakHashMap());
    private final Client zaej;
    private Bundle zaek;
    private ConnectionResult zael = null;
    private ConnectionResult zaem = null;
    private boolean zaen = false;
    private final Lock zaeo;
    @GuardedBy("mLock")
    private int zaep = 0;

    public static zas zaa(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<AnyClientKey<?>, Client> map, ClientSettings clientSettings, Map<Api<?>, Boolean> map2, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, ArrayList<zaq> arrayList) {
        Client client = null;
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            Client client2 = (Client) entry.getValue();
            if (client2.providesSignIn()) {
                client = client2;
            }
            if (client2.requiresSignIn()) {
                arrayMap.put((AnyClientKey) entry.getKey(), client2);
            } else {
                arrayMap2.put((AnyClientKey) entry.getKey(), client2);
            }
        }
        Preconditions.checkState(!arrayMap.isEmpty(), "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        Map arrayMap3 = new ArrayMap();
        Map arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            AnyClientKey clientKey = api.getClientKey();
            if (arrayMap.containsKey(clientKey)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else if (arrayMap2.containsKey(clientKey)) {
                arrayMap4.put(api, (Boolean) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        arrayList = arrayList;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            zaq zaq = (zaq) obj;
            if (arrayMap3.containsKey(zaq.mApi)) {
                arrayList2.add(zaq);
            } else if (arrayMap4.containsKey(zaq.mApi)) {
                arrayList3.add(zaq);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zas(context, zaaw, lock, looper, googleApiAvailabilityLight, arrayMap, arrayMap2, clientSettings, abstractClientBuilder, client, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private zas(Context context, zaaw zaaw, Lock lock, Looper looper, GoogleApiAvailabilityLight googleApiAvailabilityLight, Map<AnyClientKey<?>, Client> map, Map<AnyClientKey<?>, Client> map2, ClientSettings clientSettings, AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, Client client, ArrayList<zaq> arrayList, ArrayList<zaq> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zaee = zaaw;
        this.zaeo = lock;
        this.zabj = looper;
        this.zaej = client;
        this.zaef = new zabe(context, this.zaee, lock, looper, googleApiAvailabilityLight, map2, null, map4, null, arrayList2, new zau());
        this.zaeg = new zabe(context, this.zaee, lock, looper, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, arrayList, new zav());
        Map arrayMap = new ArrayMap();
        for (AnyClientKey put : map2.keySet()) {
            arrayMap.put(put, this.zaef);
        }
        for (AnyClientKey put2 : map.keySet()) {
            arrayMap.put(put2, this.zaeg);
        }
        this.zaeh = Collections.unmodifiableMap(arrayMap);
    }

    @GuardedBy("mLock")
    public final <A extends AnyClient, R extends Result, T extends ApiMethodImpl<R, A>> T enqueue(@NonNull T t) {
        if (!zaa((ApiMethodImpl) t)) {
            return this.zaef.enqueue(t);
        }
        if (!zaz()) {
            return this.zaeg.enqueue(t);
        }
        t.setFailedResult(new Status(4, null, zaaa()));
        return t;
    }

    @GuardedBy("mLock")
    public final <A extends AnyClient, T extends ApiMethodImpl<? extends Result, A>> T execute(@NonNull T t) {
        if (!zaa((ApiMethodImpl) t)) {
            return this.zaef.execute(t);
        }
        if (!zaz()) {
            return this.zaeg.execute(t);
        }
        t.setFailedResult(new Status(4, null, zaaa()));
        return t;
    }

    @Nullable
    @GuardedBy("mLock")
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        if (!((zabe) this.zaeh.get(api.getClientKey())).equals(this.zaeg)) {
            return this.zaef.getConnectionResult(api);
        }
        if (zaz()) {
            return new ConnectionResult(4, zaaa());
        }
        return this.zaeg.getConnectionResult(api);
    }

    @GuardedBy("mLock")
    public final void connect() {
        this.zaep = 2;
        this.zaen = false;
        this.zaem = null;
        this.zael = null;
        this.zaef.connect();
        this.zaeg.connect();
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    @GuardedBy("mLock")
    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    @GuardedBy("mLock")
    public final void disconnect() {
        this.zaem = null;
        this.zael = null;
        this.zaep = 0;
        this.zaef.disconnect();
        this.zaeg.disconnect();
        zay();
    }

    public final boolean isConnected() {
        boolean z = true;
        this.zaeo.lock();
        try {
            if (!(this.zaef.isConnected() && (this.zaeg.isConnected() || zaz() || this.zaep == 1))) {
                z = false;
            }
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            boolean z = this.zaep == 2;
            this.zaeo.unlock();
            return z;
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    public final boolean maybeSignIn(SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zaeg.isConnected()) {
                this.zaei.add(signInConnectionListener);
                if (this.zaep == 0) {
                    this.zaep = 1;
                }
                this.zaem = null;
                this.zaeg.connect();
                return true;
            }
            this.zaeo.unlock();
            return false;
        } finally {
            this.zaeo.unlock();
        }
    }

    @GuardedBy("mLock")
    public final void zaw() {
        this.zaef.zaw();
        this.zaeg.zaw();
    }

    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zaeg.disconnect();
            this.zaem = new ConnectionResult(4);
            if (isConnecting) {
                new zap(this.zabj).post(new zat(this));
            } else {
                zay();
            }
            this.zaeo.unlock();
        } catch (Throwable th) {
            this.zaeo.unlock();
        }
    }

    @GuardedBy("mLock")
    private final void zax() {
        if (zab(this.zael)) {
            if (zab(this.zaem) || zaz()) {
                switch (this.zaep) {
                    case 1:
                        break;
                    case 2:
                        this.zaee.zab(this.zaek);
                        break;
                    default:
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        break;
                }
                zay();
                this.zaep = 0;
            } else if (this.zaem == null) {
            } else {
                if (this.zaep == 1) {
                    zay();
                    return;
                }
                zaa(this.zaem);
                this.zaef.disconnect();
            }
        } else if (this.zael != null && zab(this.zaem)) {
            this.zaeg.disconnect();
            zaa(this.zael);
        } else if (this.zael != null && this.zaem != null) {
            ConnectionResult connectionResult = this.zael;
            if (this.zaeg.zahs < this.zaef.zahs) {
                connectionResult = this.zaem;
            }
            zaa(connectionResult);
        }
    }

    @GuardedBy("mLock")
    private final void zaa(ConnectionResult connectionResult) {
        switch (this.zaep) {
            case 1:
                break;
            case 2:
                this.zaee.zac(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zay();
        this.zaep = 0;
    }

    @GuardedBy("mLock")
    private final void zay() {
        for (SignInConnectionListener onComplete : this.zaei) {
            onComplete.onComplete();
        }
        this.zaei.clear();
    }

    @GuardedBy("mLock")
    private final void zaa(int i, boolean z) {
        this.zaee.zab(i, z);
        this.zaem = null;
        this.zael = null;
    }

    @GuardedBy("mLock")
    private final boolean zaz() {
        return this.zaem != null && this.zaem.getErrorCode() == 4;
    }

    private final boolean zaa(ApiMethodImpl<? extends Result, ? extends AnyClient> apiMethodImpl) {
        AnyClientKey clientKey = apiMethodImpl.getClientKey();
        Preconditions.checkArgument(this.zaeh.containsKey(clientKey), "GoogleApiClient is not configured to use the API required for this call.");
        return ((zabe) this.zaeh.get(clientKey)).equals(this.zaeg);
    }

    @Nullable
    private final PendingIntent zaaa() {
        if (this.zaej == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zaee), this.zaej.getSignInIntent(), 134217728);
    }

    private final void zaa(Bundle bundle) {
        if (this.zaek == null) {
            this.zaek = bundle;
        } else if (bundle != null) {
            this.zaek.putAll(bundle);
        }
    }

    private static boolean zab(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zaeg.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zaef.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }
}
