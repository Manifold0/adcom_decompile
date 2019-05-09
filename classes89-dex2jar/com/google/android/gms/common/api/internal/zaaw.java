// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import java.io.Writer;
import java.io.StringWriter;
import android.app.Activity;
import android.support.v4.app.FragmentActivity;
import android.app.PendingIntent;
import android.util.Log;
import com.google.android.gms.common.api.Result;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicReference;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import javax.annotation.concurrent.GuardedBy;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.internal.service.Common;
import java.util.Iterator;
import java.util.HashSet;
import com.google.android.gms.common.util.ClientLibraryUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.internal.GmsClientEventManager;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.Queue;
import java.util.Map;
import com.google.android.gms.common.internal.ClientSettings;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;

public final class zaaw extends GoogleApiClient implements zabt
{
    private final Context mContext;
    private final Looper zabj;
    private final int zacb;
    private final GoogleApiAvailability zacd;
    private final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
    private boolean zach;
    private final Lock zaeo;
    private final ClientSettings zaet;
    private final Map<Api<?>, Boolean> zaew;
    @VisibleForTesting
    final Queue<BaseImplementation.ApiMethodImpl<?, ?>> zafc;
    private final GmsClientEventManager zags;
    private zabs zagt;
    private volatile boolean zagu;
    private long zagv;
    private long zagw;
    private final zabb zagx;
    @VisibleForTesting
    private zabq zagy;
    final Map<Api.AnyClientKey<?>, Api.Client> zagz;
    Set<Scope> zaha;
    private final ListenerHolders zahb;
    private final ArrayList<zaq> zahc;
    private Integer zahd;
    Set<zacm> zahe;
    final zacp zahf;
    private final GmsClientEventManager.GmsClientEventState zahg;
    
    public zaaw(final Context mContext, final Lock zaeo, final Looper zabj, final ClientSettings zaet, final GoogleApiAvailability zacd, final Api.AbstractClientBuilder<? extends zad, SignInOptions> zace, final Map<Api<?>, Boolean> zaew, final List<ConnectionCallbacks> list, final List<OnConnectionFailedListener> list2, final Map<Api.AnyClientKey<?>, Api.Client> zagz, final int zacb, final int n, final ArrayList<zaq> zahc, final boolean b) {
        this.zagt = null;
        this.zafc = new LinkedList<BaseImplementation.ApiMethodImpl<?, ?>>();
        long zagv;
        if (ClientLibraryUtils.isPackageSide()) {
            zagv = 10000L;
        }
        else {
            zagv = 120000L;
        }
        this.zagv = zagv;
        this.zagw = 5000L;
        this.zaha = new HashSet<Scope>();
        this.zahb = new ListenerHolders();
        this.zahd = null;
        this.zahe = null;
        this.zahg = new zaax(this);
        this.mContext = mContext;
        this.zaeo = zaeo;
        this.zach = false;
        this.zags = new GmsClientEventManager(zabj, this.zahg);
        this.zabj = zabj;
        this.zagx = new zabb(this, zabj);
        this.zacd = zacd;
        this.zacb = zacb;
        if (this.zacb >= 0) {
            this.zahd = n;
        }
        this.zaew = zaew;
        this.zagz = zagz;
        this.zahc = zahc;
        this.zahf = new zacp(this.zagz);
        final Iterator<ConnectionCallbacks> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.zags.registerConnectionCallbacks(iterator.next());
        }
        final Iterator<OnConnectionFailedListener> iterator2 = list2.iterator();
        while (iterator2.hasNext()) {
            this.zags.registerConnectionFailedListener(iterator2.next());
        }
        this.zaet = zaet;
        this.zace = zace;
    }
    
    private final void resume() {
        this.zaeo.lock();
        try {
            if (this.zagu) {
                this.zaau();
            }
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    public static int zaa(final Iterable<Api.Client> iterable, final boolean b) {
        final boolean b2 = true;
        final Iterator<Api.Client> iterator = iterable.iterator();
        boolean b3 = false;
        boolean b4 = false;
        while (iterator.hasNext()) {
            final Api.Client client = iterator.next();
            if (client.requiresSignIn()) {
                b4 = true;
            }
            if (client.providesSignIn()) {
                b3 = true;
            }
        }
        if (b4) {
            int n = b2 ? 1 : 0;
            if (b3) {
                n = (b2 ? 1 : 0);
                if (b) {
                    n = 2;
                }
            }
            return n;
        }
        return 3;
    }
    
    private final void zaa(final GoogleApiClient googleApiClient, final StatusPendingResult statusPendingResult, final boolean b) {
        Common.zapi.zaa(googleApiClient).setResultCallback((com.google.android.gms.common.api.ResultCallback<? super Status>)new zaba(this, statusPendingResult, b, googleApiClient));
    }
    
    @GuardedBy("mLock")
    private final void zaau() {
        this.zags.enableCallbacks();
        this.zagt.connect();
    }
    
    private final void zaav() {
        this.zaeo.lock();
        try {
            if (this.zaaw()) {
                this.zaau();
            }
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    private final void zae(int n) {
        if (this.zahd == null) {
            this.zahd = n;
        }
        else if (this.zahd != n) {
            final String zaf = zaf(n);
            final String zaf2 = zaf(this.zahd);
            throw new IllegalStateException(new StringBuilder(String.valueOf(zaf).length() + 51 + String.valueOf(zaf2).length()).append("Cannot use sign-in mode: ").append(zaf).append(". Mode was already set to ").append(zaf2).toString());
        }
        if (this.zagt != null) {
            return;
        }
        final Iterator<Api.Client> iterator = this.zagz.values().iterator();
        n = 0;
        boolean b = false;
        while (iterator.hasNext()) {
            final Api.Client client = iterator.next();
            if (client.requiresSignIn()) {
                b = true;
            }
            if (client.providesSignIn()) {
                n = 1;
            }
        }
        switch (this.zahd) {
            case 1: {
                if (!b) {
                    throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                }
                if (n != 0) {
                    throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                }
                break;
            }
            case 2: {
                if (!b) {
                    break;
                }
                if (this.zach) {
                    this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, true);
                    return;
                }
                this.zagt = zas.zaa(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc);
                return;
            }
        }
        if (this.zach && n == 0) {
            this.zagt = new zax(this.mContext, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this, false);
            return;
        }
        this.zagt = new zabe(this.mContext, this, this.zaeo, this.zabj, this.zacd, this.zagz, this.zaet, this.zaew, this.zace, this.zahc, this);
    }
    
    private static String zaf(final int n) {
        switch (n) {
            default: {
                return "UNKNOWN";
            }
            case 3: {
                return "SIGN_IN_MODE_NONE";
            }
            case 1: {
                return "SIGN_IN_MODE_REQUIRED";
            }
            case 2: {
                return "SIGN_IN_MODE_OPTIONAL";
            }
        }
    }
    
    @Override
    public final ConnectionResult blockingConnect() {
        final boolean b = true;
        Label_0091: {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                break Label_0091;
            }
            boolean b2 = true;
        Label_0052_Outer:
            while (true) {
                Preconditions.checkState(b2, (Object)"blockingConnect must not be called on the UI thread");
                this.zaeo.lock();
                while (true) {
                    Label_0143: {
                        try {
                            if (this.zacb >= 0) {
                                b2 = (this.zahd != null && b);
                                Preconditions.checkState(b2, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                            }
                            else {
                                if (this.zahd != null) {
                                    break Label_0143;
                                }
                                this.zahd = zaa(this.zagz.values(), false);
                            }
                            this.zae(this.zahd);
                            this.zags.enableCallbacks();
                            return this.zagt.blockingConnect();
                            b2 = false;
                            continue Label_0052_Outer;
                        }
                        finally {
                            this.zaeo.unlock();
                        }
                    }
                    if (this.zahd == 2) {
                        break;
                    }
                    continue;
                }
            }
        }
        throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public final ConnectionResult blockingConnect(final long n, @NonNull final TimeUnit timeUnit) {
        boolean b = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            b = true;
        }
        Preconditions.checkState(b, (Object)"blockingConnect must not be called on the UI thread");
        Preconditions.checkNotNull((Object)timeUnit, (Object)"TimeUnit must not be null");
        this.zaeo.lock();
        try {
            if (this.zahd == null) {
                this.zahd = zaa(this.zagz.values(), false);
            }
            else if (this.zahd == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            this.zae(this.zahd);
            this.zags.enableCallbacks();
            return this.zagt.blockingConnect(n, timeUnit);
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        Preconditions.checkState(this.isConnected(), (Object)"GoogleApiClient is not connected yet.");
        Preconditions.checkState(this.zahd != 2, (Object)"Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        final StatusPendingResult statusPendingResult = new StatusPendingResult(this);
        if (this.zagz.containsKey(Common.CLIENT_KEY)) {
            this.zaa(this, statusPendingResult, false);
            return statusPendingResult;
        }
        final AtomicReference<GoogleApiClient> atomicReference = new AtomicReference<GoogleApiClient>();
        final GoogleApiClient build = new Builder(this.mContext).addApi(Common.API).addConnectionCallbacks(new zaay(this, atomicReference, statusPendingResult)).addOnConnectionFailedListener(new zaaz(this, statusPendingResult)).setHandler(this.zagx).build();
        atomicReference.set(build);
        build.connect();
        return statusPendingResult;
    }
    
    @Override
    public final void connect() {
        while (true) {
            boolean b = false;
            this.zaeo.lock();
            while (true) {
                Label_0097: {
                    try {
                        if (this.zacb >= 0) {
                            if (this.zahd != null) {
                                b = true;
                            }
                            Preconditions.checkState(b, (Object)"Sign-in mode should have been set explicitly by auto-manage.");
                        }
                        else {
                            if (this.zahd != null) {
                                break Label_0097;
                            }
                            this.zahd = zaa(this.zagz.values(), false);
                        }
                        this.connect(this.zahd);
                        return;
                    }
                    finally {
                        this.zaeo.unlock();
                    }
                }
                if (this.zahd == 2) {
                    break;
                }
                continue;
            }
        }
        throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    
    @Override
    public final void connect(final int n) {
        final boolean b = true;
        this.zaeo.lock();
        boolean b2 = b;
        Label_0077: {
            if (n != 3) {
                b2 = b;
                if (n != 1) {
                    if (n != 2) {
                        break Label_0077;
                    }
                    b2 = b;
                }
            }
            try {
                while (true) {
                    Preconditions.checkArgument(b2, (Object)new StringBuilder(33).append("Illegal sign-in mode: ").append(n).toString());
                    this.zae(n);
                    this.zaau();
                    return;
                    b2 = false;
                    continue;
                }
            }
            finally {
                this.zaeo.unlock();
            }
        }
    }
    
    @Override
    public final void disconnect() {
        this.zaeo.lock();
        try {
            this.zahf.release();
            if (this.zagt != null) {
                this.zagt.disconnect();
            }
            this.zahb.release();
            for (final BaseImplementation.ApiMethodImpl apiMethodImpl : this.zafc) {
                apiMethodImpl.zaa((zacs)null);
                apiMethodImpl.cancel();
            }
        }
        finally {
            this.zaeo.unlock();
        }
        this.zafc.clear();
        if (this.zagt == null) {
            this.zaeo.unlock();
            return;
        }
        this.zaaw();
        this.zags.disableCallbacks();
        this.zaeo.unlock();
    }
    
    @Override
    public final void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        printWriter.append(s).append("mContext=").println(this.mContext);
        printWriter.append(s).append("mResuming=").print(this.zagu);
        printWriter.append(" mWorkQueue.size()=").print(this.zafc.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zahf.zakz.size());
        if (this.zagt != null) {
            this.zagt.dump(s, fileDescriptor, printWriter, array);
        }
    }
    
    @Override
    public final <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        while (true) {
            while (true) {
                boolean containsKey = false;
                Label_0009: {
                    if (t.getClientKey() != null) {
                        containsKey = true;
                        break Label_0009;
                    }
                    Label_0123: {
                        break Label_0123;
                        while (true) {
                            String name = null;
                            Preconditions.checkArgument(containsKey, (Object)new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
                            this.zaeo.lock();
                            try {
                                if (this.zagt == null) {
                                    this.zafc.add(t);
                                    return t;
                                }
                                return this.zagt.enqueue(t);
                                name = "the API";
                                continue;
                                containsKey = false;
                                break;
                            }
                            finally {
                                this.zaeo.unlock();
                            }
                        }
                    }
                }
                Preconditions.checkArgument(containsKey, (Object)"This task can not be enqueued (it's probably a Batch or malformed)");
                containsKey = this.zagz.containsKey(t.getClientKey());
                if (((BaseImplementation.ApiMethodImpl)t).getApi() != null) {
                    final String name = ((BaseImplementation.ApiMethodImpl)t).getApi().getName();
                    continue;
                }
                break;
            }
            continue;
        }
    }
    
    @Override
    public final <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        while (true) {
            Label_0124: {
                if (t.getClientKey() == null) {
                    break Label_0124;
                }
                final boolean b = true;
                Preconditions.checkArgument(b, (Object)"This task can not be executed (it's probably a Batch or malformed)");
                final boolean containsKey = this.zagz.containsKey(t.getClientKey());
                String name;
                if (((BaseImplementation.ApiMethodImpl)t).getApi() != null) {
                    name = ((BaseImplementation.ApiMethodImpl)t).getApi().getName();
                }
                else {
                    name = "the API";
                }
                Preconditions.checkArgument(containsKey, (Object)new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
                this.zaeo.lock();
                Label_0136: {
                    try {
                        if (this.zagt == null) {
                            throw new IllegalStateException("GoogleApiClient is not connected yet.");
                        }
                        break Label_0136;
                    }
                    finally {
                        this.zaeo.unlock();
                    }
                    break Label_0124;
                }
                final T t2;
                if (this.zagu) {
                    this.zafc.add(t2);
                    while (!this.zafc.isEmpty()) {
                        final BaseImplementation.ApiMethodImpl apiMethodImpl = this.zafc.remove();
                        this.zahf.zab(apiMethodImpl);
                        apiMethodImpl.setFailedResult(Status.RESULT_INTERNAL_ERROR);
                    }
                    this.zaeo.unlock();
                    return t2;
                }
                final BaseImplementation.ApiMethodImpl<? extends Result, A> execute = this.zagt.execute(t2);
                this.zaeo.unlock();
                return (T)execute;
            }
            final boolean b = false;
            continue;
        }
    }
    
    @NonNull
    @Override
    public final <C extends Api.Client> C getClient(@NonNull final Api.AnyClientKey<C> anyClientKey) {
        final Api.Client client = this.zagz.get(anyClientKey);
        Preconditions.checkNotNull((Object)client, (Object)"Appropriate Api was not requested.");
        return (C)client;
    }
    
    @NonNull
    @Override
    public final ConnectionResult getConnectionResult(@NonNull final Api<?> api) {
        this.zaeo.lock();
        try {
            if (!this.isConnected() && !this.zagu) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            }
        }
        finally {
            this.zaeo.unlock();
        }
        final Api<?> api2;
        if (!this.zagz.containsKey(api2.getClientKey())) {
            throw new IllegalArgumentException(String.valueOf(api2.getName()).concat(" was never registered with GoogleApiClient"));
        }
        final ConnectionResult connectionResult = this.zagt.getConnectionResult(api2);
        if (connectionResult != null) {
            this.zaeo.unlock();
            return connectionResult;
        }
        if (this.zagu) {
            final ConnectionResult result_SUCCESS = ConnectionResult.RESULT_SUCCESS;
            this.zaeo.unlock();
            return result_SUCCESS;
        }
        Log.w("GoogleApiClientImpl", this.zaay());
        Log.wtf("GoogleApiClientImpl", String.valueOf(api2.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), (Throwable)new Exception());
        final ConnectionResult connectionResult2 = new ConnectionResult(8, (PendingIntent)null);
        this.zaeo.unlock();
        return connectionResult2;
    }
    
    @Override
    public final Context getContext() {
        return this.mContext;
    }
    
    @Override
    public final Looper getLooper() {
        return this.zabj;
    }
    
    @Override
    public final boolean hasApi(@NonNull final Api<?> api) {
        return this.zagz.containsKey(api.getClientKey());
    }
    
    @Override
    public final boolean hasConnectedApi(@NonNull final Api<?> api) {
        if (!this.isConnected()) {
            return false;
        }
        final Api.Client client = this.zagz.get(api.getClientKey());
        return client != null && client.isConnected();
    }
    
    @Override
    public final boolean isConnected() {
        return this.zagt != null && this.zagt.isConnected();
    }
    
    @Override
    public final boolean isConnecting() {
        return this.zagt != null && this.zagt.isConnecting();
    }
    
    @Override
    public final boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks connectionCallbacks) {
        return this.zags.isConnectionCallbacksRegistered(connectionCallbacks);
    }
    
    @Override
    public final boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        return this.zags.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }
    
    @Override
    public final boolean maybeSignIn(final SignInConnectionListener signInConnectionListener) {
        return this.zagt != null && this.zagt.maybeSignIn(signInConnectionListener);
    }
    
    @Override
    public final void maybeSignOut() {
        if (this.zagt != null) {
            this.zagt.maybeSignOut();
        }
    }
    
    @Override
    public final void reconnect() {
        this.disconnect();
        this.connect();
    }
    
    @Override
    public final void registerConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.zags.registerConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public final void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.registerConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public final <L> ListenerHolder<L> registerListener(@NonNull final L l) {
        this.zaeo.lock();
        try {
            return this.zahb.zaa(l, this.zabj, "NO_TYPE");
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    @Override
    public final void stopAutoManage(@NonNull final FragmentActivity fragmentActivity) {
        final LifecycleActivity lifecycleActivity = new LifecycleActivity((Activity)fragmentActivity);
        if (this.zacb >= 0) {
            zaj.zaa(lifecycleActivity).zaa(this.zacb);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }
    
    @Override
    public final void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
        this.zags.unregisterConnectionCallbacks(connectionCallbacks);
    }
    
    @Override
    public final void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
        this.zags.unregisterConnectionFailedListener(onConnectionFailedListener);
    }
    
    @Override
    public final void zaa(final zacm zacm) {
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                this.zahe = new HashSet<zacm>();
            }
            this.zahe.add(zacm);
        }
        finally {
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
    
    final boolean zaax() {
        boolean b = false;
        this.zaeo.lock();
        try {
            if (this.zahe == null) {
                return false;
            }
            if (!this.zahe.isEmpty()) {
                b = true;
            }
            return b;
        }
        finally {
            this.zaeo.unlock();
        }
    }
    
    final String zaay() {
        final StringWriter stringWriter = new StringWriter();
        this.dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zab(final int n, final boolean b) {
        if (n == 1 && !b && !this.zagu) {
            this.zagu = true;
            if (this.zagy == null && !ClientLibraryUtils.isPackageSide()) {
                this.zagy = this.zacd.zaa(this.mContext.getApplicationContext(), new zabc(this));
            }
            this.zagx.sendMessageDelayed(this.zagx.obtainMessage(1), this.zagv);
            this.zagx.sendMessageDelayed(this.zagx.obtainMessage(2), this.zagw);
        }
        this.zahf.zabx();
        this.zags.onUnintentionalDisconnection(n);
        this.zags.disableCallbacks();
        if (n == 2) {
            this.zaau();
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zab(final Bundle bundle) {
        while (!this.zafc.isEmpty()) {
            this.execute(this.zafc.remove());
        }
        this.zags.onConnectionSuccess(bundle);
    }
    
    @Override
    public final void zab(final zacm zacm) {
        while (true) {
            this.zaeo.lock();
            Label_0088: {
                try {
                    if (this.zahe == null) {
                        Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", (Throwable)new Exception());
                    }
                    else {
                        if (this.zahe.remove(zacm)) {
                            break Label_0088;
                        }
                        Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", (Throwable)new Exception());
                    }
                    return;
                }
                finally {
                    this.zaeo.unlock();
                }
            }
            if (!this.zaax()) {
                this.zagt.zaw();
            }
        }
    }
    
    @GuardedBy("mLock")
    @Override
    public final void zac(final ConnectionResult connectionResult) {
        if (!this.zacd.isPlayServicesPossiblyUpdating(this.mContext, connectionResult.getErrorCode())) {
            this.zaaw();
        }
        if (!this.zagu) {
            this.zags.onConnectionFailure(connectionResult);
            this.zags.disableCallbacks();
        }
    }
}
