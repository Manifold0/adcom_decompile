// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Result;
import android.support.annotation.NonNull;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.concurrent.TimeUnit;
import javax.annotation.concurrent.GuardedBy;
import android.app.PendingIntent;
import java.util.HashMap;
import java.util.ArrayList;
import android.os.Looper;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.locks.Condition;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import java.util.Map;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.api.Api;
import android.content.Context;

public final class zabe implements zabs, zar
{
    private final Context mContext;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    final zaaw zaee;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    private final GoogleApiAvailabilityLight zaey;
    final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    private final Condition zahn;
    private final zabg zaho;
    final Map<Api.AnyClientKey<?>, ConnectionResult> zahp;
    private volatile zabd zahq;
    private ConnectionResult zahr;
    int zahs;
    final zabt zaht;
    
    public zabe(final Context mContext, final zaaw zaee, final Lock zaeo, final Looper looper, final GoogleApiAvailabilityLight zaey, final Map<Api.AnyClientKey<?>, Api.Client> zagz, final ClientSettings zaet, final Map<Api<?>, Boolean> zaew, final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace, final ArrayList<zaq> list, final zabt zaht) {
        this.zahp = new HashMap<Api.AnyClientKey<?>, ConnectionResult>();
        this.zahr = null;
        this.mContext = mContext;
        this.zaeo = zaeo;
        this.zaey = zaey;
        this.zagz = zagz;
        this.zaet = zaet;
        this.zaew = zaew;
        this.zace = zace;
        this.zaee = zaee;
        this.zaht = zaht;
        final ArrayList<zaq> list2 = list;
        final int size = list2.size();
        int i = 0;
        while (i < size) {
            final zaq value = list2.get(i);
            ++i;
            value.zaa(this);
        }
        this.zaho = new zabg(this, looper);
        this.zahn = zaeo.newCondition();
        this.zahq = new zaav(this);
    }
    
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult blockingConnect() {
        this.connect();
        while (this.isConnecting()) {
            try {
                this.zahn.await();
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
        if (this.zahr != null) {
            return this.zahr;
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
                        n = this.zahn.awaitNanos(n);
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
        if (this.zahr != null) {
            return this.zahr;
        }
        return new ConnectionResult(13, (PendingIntent)null);
    }
    
    @GuardedBy("mLock")
    @Override
    public final void connect() {
        this.zahq.connect();
    }
    
    @GuardedBy("mLock")
    @Override
    public final void disconnect() {
        if (this.zahq.disconnect()) {
            this.zahp.clear();
        }
    }
    
    @Override
    public final void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        final String concat = String.valueOf(s).concat("  ");
        printWriter.append(s).append("mState=").println(this.zahq);
        for (final Api<?> api : this.zaew.keySet()) {
            printWriter.append(s).append(api.getName()).println(":");
            this.zagz.get(api.getClientKey()).dump(concat, fileDescriptor, printWriter, array);
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        t.zau();
        return this.zahq.enqueue(t);
    }
    
    @GuardedBy("mLock")
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        t.zau();
        return this.zahq.execute(t);
    }
    
    @Nullable
    @GuardedBy("mLock")
    @Override
    public final ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        final Api.AnyClientKey<?> clientKey = (Api.AnyClientKey<?>)api.getClientKey();
        if (this.zagz.containsKey(clientKey)) {
            if (this.zagz.get(clientKey).isConnected()) {
                return ConnectionResult.RESULT_SUCCESS;
            }
            if (this.zahp.containsKey(clientKey)) {
                return this.zahp.get(clientKey);
            }
        }
        return null;
    }
    
    @Override
    public final boolean isConnected() {
        return this.zahq instanceof zaah;
    }
    
    @Override
    public final boolean isConnecting() {
        return this.zahq instanceof zaak;
    }
    
    @Override
    public final boolean maybeSignIn(final SignInConnectionListener signInConnectionListener) {
        return false;
    }
    
    @Override
    public final void maybeSignOut() {
    }
    
    @Override
    public final void onConnected(@Nullable final Bundle bundle) {
        this.zaeo.lock();
        try {
            this.zahq.onConnected(bundle);
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final void onConnectionSuspended(final int n) {
        this.zaeo.lock();
        try {
            this.zahq.onConnectionSuspended(n);
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final void zaa(@NonNull final ConnectionResult connectionResult, @NonNull final Api<?> api, final boolean b) {
        this.zaeo.lock();
        try {
            this.zahq.zaa(connectionResult, api, b);
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    final void zaa(final zabf zabf) {
        this.zaho.sendMessage(this.zaho.obtainMessage(1, (Object)zabf));
    }
    
    final void zaaz() {
        this.zaeo.lock();
        try {
            (this.zahq = new zaak(this, this.zaet, this.zaew, this.zaey, this.zace, this.zaeo, this.mContext)).begin();
            this.zahn.signalAll();
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    final void zab(final RuntimeException ex) {
        this.zaho.sendMessage(this.zaho.obtainMessage(2, (Object)ex));
    }
    
    final void zaba() {
        this.zaeo.lock();
        try {
            this.zaee.zaaw();
            (this.zahq = new zaah(this)).begin();
            this.zahn.signalAll();
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    final void zaf(final ConnectionResult zahr) {
        this.zaeo.lock();
        try {
            this.zahr = zahr;
            (this.zahq = new zaav(this)).begin();
            this.zahn.signalAll();
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zaw() {
        if (this.isConnected()) {
            ((zaah)this.zahq).zaam();
        }
    }
}
