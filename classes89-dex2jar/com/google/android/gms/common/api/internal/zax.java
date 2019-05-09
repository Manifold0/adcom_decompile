// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.tasks.OnCompleteListener;
import java.util.concurrent.Executor;
import com.google.android.gms.common.util.concurrent.HandlerExecutor;
import com.google.android.gms.common.api.GoogleApi;
import java.util.concurrent.TimeUnit;
import android.app.PendingIntent;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.Result;
import android.os.Bundle;
import java.util.Collection;
import com.google.android.gms.common.api.Scope;
import java.util.HashSet;
import java.util.Collections;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.ArrayList;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import android.content.Context;
import com.google.android.gms.common.ConnectionResult;
import javax.annotation.concurrent.GuardedBy;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.api.Api;
import java.util.Map;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.concurrent.locks.Lock;
import android.os.Looper;

public final class zax implements zabs
{
    private final Looper zabj;
    private final GoogleApiManager zabm;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaeu;
    private final Map<Api.AnyClientKey<?>, zaw<?>> zaev;
    private final Map<Api<?>, Boolean> zaew;
    private final zaaw zaex;
    private final GoogleApiAvailabilityLight zaey;
    private final Condition zaez;
    private final boolean zafa;
    private final boolean zafb;
    private final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc;
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
    
    public zax(final Context context, final Lock zaeo, final Looper zabj, final GoogleApiAvailabilityLight zaey, final Map<Api.AnyClientKey<?>, Api.Client> map, final ClientSettings zaet, final Map<Api<?>, Boolean> zaew, final Api.AbstractClientBuilder<? extends zad, SignInOptions> abstractClientBuilder, final ArrayList<zaq> list, final zaaw zaex, final boolean zafa) {
        this.zaeu = new HashMap<Api.AnyClientKey<?>, zaw<?>>();
        this.zaev = new HashMap<Api.AnyClientKey<?>, zaw<?>>();
        this.zafc = new LinkedList<BaseImplementation.ApiMethodImpl<?, ?>>();
        this.zaeo = zaeo;
        this.zabj = zabj;
        this.zaez = zaeo.newCondition();
        this.zaey = zaey;
        this.zaex = zaex;
        this.zaew = zaew;
        this.zaet = zaet;
        this.zafa = zafa;
        final HashMap<Object, Api<?>> hashMap = new HashMap<Object, Api<?>>();
        for (final Api<?> api : zaew.keySet()) {
            hashMap.put(api.getClientKey(), api);
        }
        final HashMap<Object, Object> hashMap2 = new HashMap<Object, Object>();
        final ArrayList<zaq> list2 = list;
        final int size = list2.size();
        int i = 0;
        while (i < size) {
            final zaq value = list2.get(i);
            ++i;
            final zaq zaq = value;
            hashMap2.put(zaq.mApi, zaq);
        }
        final Iterator<Map.Entry<Api.AnyClientKey<?>, Api.Client>> iterator2 = map.entrySet().iterator();
        int n = 1;
        int n2 = 0;
        boolean b = false;
        while (iterator2.hasNext()) {
            final Map.Entry<Api.AnyClientKey<?>, Api.Client> entry = iterator2.next();
            final Api<?> api2 = hashMap.get(entry.getKey());
            final Api.Client client = entry.getValue();
            int n3;
            int n4;
            if (client.requiresGooglePlayServices()) {
                b = true;
                if (!this.zaew.get(api2)) {
                    n3 = n;
                    n4 = 1;
                }
                else {
                    final int n5 = n2;
                    n3 = n;
                    n4 = n5;
                }
            }
            else {
                final int n6 = 0;
                n4 = n2;
                n3 = n6;
            }
            final zaw zaw = new zaw<Object>(context, (Api<Api.ApiOptions>)api2, zabj, client, hashMap2.get(api2), zaet, abstractClientBuilder);
            this.zaeu.put((Api.AnyClientKey)entry.getKey(), zaw);
            if (client.requiresSignIn()) {
                this.zaev.put((Api.AnyClientKey)entry.getKey(), zaw);
            }
            final int n7 = n4;
            n = n3;
            n2 = n7;
        }
        this.zafb = (b && n == 0 && n2 == 0);
        this.zabm = GoogleApiManager.zabc();
    }
    
    @Nullable
    private final ConnectionResult zaa(@NonNull final Api.AnyClientKey<?> anyClientKey) {
        this.zaeo.lock();
        try {
            final zaw<?> zaw = this.zaeu.get(anyClientKey);
            if (this.zafe != null && zaw != null) {
                return this.zafe.get(zaw.zak());
            }
            return null;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    private final boolean zaa(final zaw<?> zaw, final ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && this.zaew.get(zaw.getApi()) && zaw.zaab().requiresGooglePlayServices() && this.zaey.isUserResolvableError(connectionResult.getErrorCode());
    }
    
    private final boolean zaac() {
        this.zaeo.lock();
        try {
            if (!this.zafd || !this.zafa) {
                return false;
            }
            final Iterator<Api.AnyClientKey<?>> iterator = this.zaev.keySet().iterator();
            while (iterator.hasNext()) {
                final ConnectionResult zaa = this.zaa((Api.AnyClientKey)iterator.next());
                if (zaa == null || !zaa.isSuccess()) {
                    return false;
                }
            }
            return true;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @GuardedBy("mLock")
    private final void zaad() {
        if (this.zaet == null) {
            this.zaex.zaha = Collections.emptySet();
            return;
        }
        final HashSet<Scope> zaha = new HashSet<Scope>(this.zaet.getRequiredScopes());
        final Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = this.zaet.getOptionalApiSettings();
        for (final Api<?> api : optionalApiSettings.keySet()) {
            final ConnectionResult connectionResult = this.getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                zaha.addAll((Collection<?>)((ClientSettings.OptionalApiSettings)optionalApiSettings.get(api)).mScopes);
            }
        }
        this.zaex.zaha = zaha;
    }
    
    @GuardedBy("mLock")
    private final void zaae() {
        while (!this.zafc.isEmpty()) {
            this.execute(this.zafc.remove());
        }
        this.zaex.zab((Bundle)null);
    }
    
    @Nullable
    @GuardedBy("mLock")
    private final ConnectionResult zaaf() {
        final Iterator<zaw<?>> iterator = this.zaeu.values().iterator();
        int n = 0;
        ConnectionResult connectionResult = null;
        int n2 = 0;
        ConnectionResult connectionResult2 = null;
        while (iterator.hasNext()) {
            final zaw<?> zaw = iterator.next();
            final Api<Api.ApiOptions> api = zaw.getApi();
            final ConnectionResult connectionResult3 = this.zafe.get(zaw.zak());
            if (!connectionResult3.isSuccess() && (!this.zaew.get(api) || connectionResult3.hasResolution() || this.zaey.isUserResolvableError(connectionResult3.getErrorCode()))) {
                if (connectionResult3.getErrorCode() == 4 && this.zafa) {
                    final int priority = api.zah().getPriority();
                    if (connectionResult != null && n <= priority) {
                        continue;
                    }
                    n = priority;
                    connectionResult = connectionResult3;
                }
                else {
                    final int priority2 = api.zah().getPriority();
                    if (connectionResult2 != null && n2 <= priority2) {
                        continue;
                    }
                    connectionResult2 = connectionResult3;
                    n2 = priority2;
                }
            }
        }
        if (connectionResult2 != null && connectionResult != null && n2 > n) {
            return connectionResult;
        }
        return connectionResult2;
    }
    
    private final <T extends BaseImplementation.ApiMethodImpl<? extends Result, ? extends Api.AnyClient>> boolean zab(@NonNull final T t) {
        final Api.AnyClientKey<?> clientKey = ((BaseImplementation.ApiMethodImpl<R, ?>)t).getClientKey();
        final ConnectionResult zaa = this.zaa(clientKey);
        if (zaa != null && zaa.getErrorCode() == 4) {
            ((BaseImplementation.ApiMethodImpl)t).setFailedResult(new Status(4, (String)null, this.zabm.zaa(this.zaeu.get(clientKey).zak(), System.identityHashCode(this.zaex))));
            return true;
        }
        return false;
    }
    
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult blockingConnect() {
        this.connect();
        while (this.isConnecting()) {
            try {
                this.zaez.await();
                continue;
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, (PendingIntent)null);
            }
            break;
        }
        if (this.isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafh != null) {
            return this.zafh;
        }
        return new ConnectionResult(13, (PendingIntent)null);
    }
    
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult blockingConnect(long n, final TimeUnit timeUnit) {
        this.connect();
        n = timeUnit.toNanos(n);
    Label_0038_Outer:
        while (this.isConnecting()) {
            while (true) {
                if (n <= 0L) {
                    try {
                        this.disconnect();
                        return new ConnectionResult(14, (PendingIntent)null);
                        n = this.zaez.awaitNanos(n);
                        continue Label_0038_Outer;
                    }
                    catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                        return new ConnectionResult(15, (PendingIntent)null);
                    }
                    break;
                }
                continue;
            }
        }
        if (this.isConnected()) {
            return ConnectionResult.RESULT_SUCCESS;
        }
        if (this.zafh != null) {
            return this.zafh;
        }
        return new ConnectionResult(13, (PendingIntent)null);
    }
    
    @Override
    public final void connect() {
        this.zaeo.lock();
        try {
            if (this.zafd) {
                return;
            }
            this.zafd = true;
            this.zafe = null;
            this.zaff = null;
            this.zafg = null;
            this.zafh = null;
            this.zabm.zao();
            this.zabm.zaa(this.zaeu.values()).addOnCompleteListener((Executor)new HandlerExecutor(this.zabj), (OnCompleteListener)new zaz(this, null));
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
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
                final BaseImplementation.ApiMethodImpl apiMethodImpl = this.zafc.remove();
                apiMethodImpl.zaa((zacs)null);
                apiMethodImpl.cancel();
            }
        }
        finally {
            this.zaeo.unlock();
        }
        this.zaez.signalAll();
        this.zaeo.unlock();
    }
    
    @Override
    public final void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        if (this.zafa && this.zab(t)) {
            return t;
        }
        if (!this.isConnected()) {
            this.zafc.add(t);
            return t;
        }
        this.zaex.zahf.zab(t);
        return this.zaeu.get(t.getClientKey()).doRead(t);
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        final Api.AnyClientKey<A> clientKey = ((BaseImplementation.ApiMethodImpl<R, A>)t).getClientKey();
        if (this.zafa && this.zab(t)) {
            return t;
        }
        this.zaex.zahf.zab(t);
        return this.zaeu.get(clientKey).doWrite(t);
    }
    
    @Nullable
    @Override
    public final ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        return this.zaa(api.getClientKey());
    }
    
    @Override
    public final boolean isConnected() {
        this.zaeo.lock();
        try {
            return this.zafe != null && this.zafh == null;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final boolean isConnecting() {
        this.zaeo.lock();
        try {
            return this.zafe == null && this.zafd;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final boolean maybeSignIn(final SignInConnectionListener signInConnectionListener) {
        this.zaeo.lock();
        try {
            if (this.zafd && !this.zaac()) {
                this.zabm.zao();
                this.zafg = new zaaa(this, signInConnectionListener);
                this.zabm.zaa(this.zaev.values()).addOnCompleteListener((Executor)new HandlerExecutor(this.zabj), (OnCompleteListener)this.zafg);
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
            this.zabm.maybeSignOut();
            if (this.zafg != null) {
                this.zafg.cancel();
                this.zafg = null;
            }
            if (this.zaff == null) {
                this.zaff = (Map<zai<?>, ConnectionResult>)new ArrayMap(this.zaev.size());
            }
            final ConnectionResult connectionResult = new ConnectionResult(4);
            final Iterator<zaw<?>> iterator = this.zaev.values().iterator();
            while (iterator.hasNext()) {
                this.zaff.put(iterator.next().zak(), connectionResult);
            }
        }
        finally {
            this.zaeo.unlock();
        }
        if (this.zafe != null) {
            this.zafe.putAll(this.zaff);
        }
        this.zaeo.unlock();
    }
    
    @Override
    public final void zaw() {
    }
}
