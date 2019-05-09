// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.base.zap;
import com.google.android.gms.common.api.Status;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Result;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Iterator;
import android.support.v4.util.ArrayMap;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.ArrayList;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.common.ConnectionResult;
import android.os.Bundle;
import java.util.Set;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import android.os.Looper;
import android.content.Context;

final class zas implements zabs
{
    private final Context mContext;
    private final Looper zabj;
    private final zaaw zaee;
    private final zabe zaef;
    private final zabe zaeg;
    private final Map<Api.AnyClientKey<?>, zabe> zaeh;
    private final Set<SignInConnectionListener> zaei;
    private final Api.Client zaej;
    private Bundle zaek;
    private ConnectionResult zael;
    private ConnectionResult zaem;
    private boolean zaen;
    private final Lock zaeo;
    @GuardedBy("mLock")
    private int zaep;
    
    private zas(final Context mContext, final zaaw zaee, final Lock zaeo, final Looper zabj, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final Map<Api.AnyClientKey<?>, Api.Client> map, final Map<Api.AnyClientKey<?>, Api.Client> map2, final ClientSettings clientSettings, final Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, final Api.Client zaej, final ArrayList<zaq> list, final ArrayList<zaq> list2, final Map<Api<?>, Boolean> map3, final Map<Api<?>, Boolean> map4) {
        this.zaei = Collections.newSetFromMap(new WeakHashMap<SignInConnectionListener, Boolean>());
        this.zael = null;
        this.zaem = null;
        this.zaen = false;
        this.zaep = 0;
        this.mContext = mContext;
        this.zaee = zaee;
        this.zaeo = zaeo;
        this.zabj = zabj;
        this.zaej = zaej;
        this.zaef = new zabe(mContext, this.zaee, zaeo, zabj, googleApiAvailabilityLight, map2, null, map4, null, list2, new zau(this, null));
        this.zaeg = new zabe(mContext, this.zaee, zaeo, zabj, googleApiAvailabilityLight, map, clientSettings, map3, abstractClientBuilder, list, new zav(this, null));
        final ArrayMap arrayMap = new ArrayMap();
        final Iterator<Api.AnyClientKey<?>> iterator = map2.keySet().iterator();
        while (iterator.hasNext()) {
            arrayMap.put((Object)iterator.next(), (Object)this.zaef);
        }
        final Iterator<Api.AnyClientKey<?>> iterator2 = map.keySet().iterator();
        while (iterator2.hasNext()) {
            arrayMap.put((Object)iterator2.next(), (Object)this.zaeg);
        }
        this.zaeh = Collections.unmodifiableMap((Map<? extends Api.AnyClientKey<?>, ? extends zabe>)arrayMap);
    }
    
    public static zas zaa(final Context context, final zaaw zaaw, final Lock lock, final Looper looper, final GoogleApiAvailabilityLight googleApiAvailabilityLight, final Map<Api.AnyClientKey<?>, Api.Client> map, final ClientSettings clientSettings, final Map<Api<?>, Boolean> map2, final Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, final ArrayList<zaq> list) {
        final Api.Client client = null;
        final ArrayMap arrayMap = new ArrayMap();
        final ArrayMap arrayMap2 = new ArrayMap();
        final Iterator<Map.Entry<Api.AnyClientKey<?>, Api.Client>> iterator = map.entrySet().iterator();
        Api.Client client2 = client;
        while (iterator.hasNext()) {
            final Map.Entry<Api.AnyClientKey<?>, Api.Client> entry = iterator.next();
            final Api.Client client3 = entry.getValue();
            if (client3.providesSignIn()) {
                client2 = client3;
            }
            if (client3.requiresSignIn()) {
                ((Map<Api.AnyClientKey<?>, Api.Client>)arrayMap).put((Api.AnyClientKey<?>)entry.getKey(), client3);
            }
            else {
                ((Map<Api.AnyClientKey<?>, Api.Client>)arrayMap2).put((Api.AnyClientKey<?>)entry.getKey(), client3);
            }
        }
        Preconditions.checkState(!((Map)arrayMap).isEmpty(), (Object)"CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        final ArrayMap arrayMap3 = new ArrayMap();
        final ArrayMap arrayMap4 = new ArrayMap();
        for (final Api<?> api : map2.keySet()) {
            final Api.AnyClientKey<?> clientKey = api.getClientKey();
            if (((Map)arrayMap).containsKey(clientKey)) {
                ((Map<Api<?>, Boolean>)arrayMap3).put(api, Boolean.valueOf(map2.get(api)));
            }
            else {
                if (!((Map)arrayMap2).containsKey(clientKey)) {
                    throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
                }
                ((Map<Api<?>, Boolean>)arrayMap4).put(api, Boolean.valueOf(map2.get(api)));
            }
        }
        final ArrayList<zaq> list2 = new ArrayList<zaq>();
        final ArrayList<zaq> list3 = new ArrayList<zaq>();
        final ArrayList<zaq> list4 = list;
        final int size = list4.size();
        int i = 0;
        while (i < size) {
            final zaq value = list4.get(i);
            ++i;
            final zaq zaq = value;
            if (((Map)arrayMap3).containsKey(zaq.mApi)) {
                list2.add(zaq);
            }
            else {
                if (!((Map)arrayMap4).containsKey(zaq.mApi)) {
                    throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
                }
                list3.add(zaq);
            }
        }
        return new zas(context, zaaw, lock, looper, googleApiAvailabilityLight, (Map<Api.AnyClientKey<?>, Api.Client>)arrayMap, (Map<Api.AnyClientKey<?>, Api.Client>)arrayMap2, clientSettings, abstractClientBuilder, client2, list2, list3, (Map<Api<?>, Boolean>)arrayMap3, (Map<Api<?>, Boolean>)arrayMap4);
    }
    
    @GuardedBy("mLock")
    private final void zaa(final int n, final boolean b) {
        this.zaee.zab(n, b);
        this.zaem = null;
        this.zael = null;
    }
    
    private final void zaa(final Bundle zaek) {
        if (this.zaek == null) {
            this.zaek = zaek;
        }
        else if (zaek != null) {
            this.zaek.putAll(zaek);
        }
    }
    
    @GuardedBy("mLock")
    private final void zaa(final ConnectionResult connectionResult) {
        switch (this.zaep) {
            default: {
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new Exception());
                break;
            }
            case 2: {
                this.zaee.zac(connectionResult);
            }
            case 1: {
                this.zay();
                break;
            }
        }
        this.zaep = 0;
    }
    
    private final boolean zaa(final BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient> apiMethodImpl) {
        final Api.AnyClientKey<? extends Api.AnyClient> clientKey = apiMethodImpl.getClientKey();
        Preconditions.checkArgument(this.zaeh.containsKey(clientKey), (Object)"GoogleApiClient is not configured to use the API required for this call.");
        return this.zaeh.get(clientKey).equals(this.zaeg);
    }
    
    @Nullable
    private final PendingIntent zaaa() {
        if (this.zaej == null) {
            return null;
        }
        return PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zaee), this.zaej.getSignInIntent(), 134217728);
    }
    
    private static boolean zab(final ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }
    
    @GuardedBy("mLock")
    private final void zax() {
        if (zab(this.zael)) {
            if (zab(this.zaem) || this.zaz()) {
                switch (this.zaep) {
                    default: {
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", (Throwable)new AssertionError());
                        break;
                    }
                    case 2: {
                        this.zaee.zab(this.zaek);
                    }
                    case 1: {
                        this.zay();
                        break;
                    }
                }
                this.zaep = 0;
            }
            else if (this.zaem != null) {
                if (this.zaep == 1) {
                    this.zay();
                    return;
                }
                this.zaa(this.zaem);
                this.zaef.disconnect();
            }
        }
        else {
            if (this.zael != null && zab(this.zaem)) {
                this.zaeg.disconnect();
                this.zaa(this.zael);
                return;
            }
            if (this.zael != null && this.zaem != null) {
                ConnectionResult connectionResult = this.zael;
                if (this.zaeg.zahs < this.zaef.zahs) {
                    connectionResult = this.zaem;
                }
                this.zaa(connectionResult);
            }
        }
    }
    
    @GuardedBy("mLock")
    private final void zay() {
        final Iterator<SignInConnectionListener> iterator = this.zaei.iterator();
        while (iterator.hasNext()) {
            iterator.next().onComplete();
        }
        this.zaei.clear();
    }
    
    @GuardedBy("mLock")
    private final boolean zaz() {
        return this.zaem != null && this.zaem.getErrorCode() == 4;
    }
    
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }
    
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @GuardedBy("mLock")
    @Override
    public final void connect() {
        this.zaep = 2;
        this.zaen = false;
        this.zaem = null;
        this.zael = null;
        this.zaef.connect();
        this.zaeg.connect();
    }
    
    @GuardedBy("mLock")
    @Override
    public final void disconnect() {
        this.zaem = null;
        this.zael = null;
        this.zaep = 0;
        this.zaef.disconnect();
        this.zaeg.disconnect();
        this.zay();
    }
    
    @Override
    public final void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("authClient").println(":");
        this.zaeg.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
        printWriter.append(s).append("anonClient").println(":");
        this.zaef.dump(String.valueOf(s).concat("  "), fileDescriptor, printWriter, array);
    }
    
    @GuardedBy("mLock")
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        if (!this.zaa(t)) {
            return this.zaef.enqueue(t);
        }
        if (this.zaz()) {
            ((BaseImplementation.ApiMethodImpl)t).setFailedResult(new Status(4, (String)null, this.zaaa()));
            return t;
        }
        return this.zaeg.enqueue(t);
    }
    
    @GuardedBy("mLock")
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        if (!this.zaa(t)) {
            return this.zaef.execute(t);
        }
        if (this.zaz()) {
            ((BaseImplementation.ApiMethodImpl)t).setFailedResult(new Status(4, (String)null, this.zaaa()));
            return t;
        }
        return this.zaeg.execute(t);
    }
    
    @Nullable
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        if (!this.zaeh.get(api.getClientKey()).equals(this.zaeg)) {
            return this.zaef.getConnectionResult(api);
        }
        if (this.zaz()) {
            return new ConnectionResult(4, this.zaaa());
        }
        return this.zaeg.getConnectionResult(api);
    }
    
    @Override
    public final boolean isConnected() {
        final boolean b = true;
        this.zaeo.lock();
        try {
            if (!this.zaef.isConnected()) {
                return false;
            }
            boolean b2 = b;
            if (!this.zaeg.isConnected()) {
                b2 = b;
                if (!this.zaz()) {
                    if (this.zaep != 1) {
                        return false;
                    }
                    b2 = b;
                }
            }
            return b2;
            b2 = false;
            return b2;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            return this.zaep == 2;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final boolean maybeSignIn(final SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if ((this.isConnecting() || this.isConnected()) && !this.zaeg.isConnected()) {
                this.zaei.add(signInConnectionListener);
                if (this.zaep == 0) {
                    this.zaep = 1;
                }
                this.zaem = null;
                this.zaeg.connect();
                return true;
            }
            return false;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final void maybeSignOut() {
        this.zaeo.lock();
        try {
            final boolean connecting = this.isConnecting();
            this.zaeg.disconnect();
            this.zaem = new ConnectionResult(4);
            if (connecting) {
                new zap(this.zabj).post((Runnable)new zat(this));
            }
            else {
                this.zay();
            }
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zaw() {
        this.zaef.zaw();
        this.zaeg.zaw();
    }
}
