// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.support.annotation.Nullable;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.api.internal.zaj;
import java.util.concurrent.locks.Lock;
import com.google.android.gms.common.api.internal.zaaw;
import java.util.concurrent.locks.ReentrantLock;
import com.google.android.gms.common.api.internal.zaq;
import java.util.List;
import java.util.Collection;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.signin.zaa;
import android.support.v4.util.ArrayMap;
import java.util.HashSet;
import android.accounts.Account;
import java.util.ArrayList;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.LifecycleActivity;
import com.google.android.gms.common.internal.ClientSettings;
import android.view.View;
import com.google.android.gms.common.api.internal.zacm;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.SignInConnectionListener;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.internal.BaseImplementation;
import android.support.annotation.NonNull;
import java.util.concurrent.TimeUnit;
import com.google.android.gms.common.ConnectionResult;
import java.util.Iterator;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import javax.annotation.concurrent.GuardedBy;
import java.util.Set;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GoogleApiClient
{
    @KeepForSdk
    public static final String DEFAULT_ACCOUNT = "<<default account>>";
    public static final int SIGN_IN_MODE_OPTIONAL = 2;
    public static final int SIGN_IN_MODE_REQUIRED = 1;
    @GuardedBy("sAllClients")
    private static final Set<GoogleApiClient> zabq;
    
    static {
        zabq = Collections.newSetFromMap(new WeakHashMap<GoogleApiClient, Boolean>());
    }
    
    public static void dumpAll(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        synchronized (GoogleApiClient.zabq) {
            final String concat = String.valueOf(s).concat("  ");
            final Iterator<GoogleApiClient> iterator = GoogleApiClient.zabq.iterator();
            int n = 0;
            while (iterator.hasNext()) {
                final GoogleApiClient googleApiClient = iterator.next();
                printWriter.append(s).append("GoogleApiClient#").println(n);
                googleApiClient.dump(concat, fileDescriptor, printWriter, array);
                ++n;
            }
        }
    }
    
    @KeepForSdk
    public static Set<GoogleApiClient> getAllClients() {
        synchronized (GoogleApiClient.zabq) {
            return GoogleApiClient.zabq;
        }
    }
    
    public abstract ConnectionResult blockingConnect();
    
    public abstract ConnectionResult blockingConnect(final long p0, @NonNull final TimeUnit p1);
    
    public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
    
    public abstract void connect();
    
    public void connect(final int n) {
        throw new UnsupportedOperationException();
    }
    
    public abstract void disconnect();
    
    public abstract void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
    
    @KeepForSdk
    public <A extends Api.AnyClient, R extends Result, T extends BaseImplementation.ApiMethodImpl<R, A>> T enqueue(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public <A extends Api.AnyClient, T extends BaseImplementation.ApiMethodImpl<? extends Result, A>> T execute(@NonNull final T t) {
        throw new UnsupportedOperationException();
    }
    
    @NonNull
    @KeepForSdk
    public <C extends Api.Client> C getClient(@NonNull final Api.AnyClientKey<C> anyClientKey) {
        throw new UnsupportedOperationException();
    }
    
    @NonNull
    public abstract ConnectionResult getConnectionResult(@NonNull final Api<?> p0);
    
    @KeepForSdk
    public Context getContext() {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public Looper getLooper() {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public boolean hasApi(@NonNull final Api<?> api) {
        throw new UnsupportedOperationException();
    }
    
    public abstract boolean hasConnectedApi(@NonNull final Api<?> p0);
    
    public abstract boolean isConnected();
    
    public abstract boolean isConnecting();
    
    public abstract boolean isConnectionCallbacksRegistered(@NonNull final ConnectionCallbacks p0);
    
    public abstract boolean isConnectionFailedListenerRegistered(@NonNull final OnConnectionFailedListener p0);
    
    @KeepForSdk
    public boolean maybeSignIn(final SignInConnectionListener signInConnectionListener) {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public void maybeSignOut() {
        throw new UnsupportedOperationException();
    }
    
    public abstract void reconnect();
    
    public abstract void registerConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void registerConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    @KeepForSdk
    public <L> ListenerHolder<L> registerListener(@NonNull final L l) {
        throw new UnsupportedOperationException();
    }
    
    public abstract void stopAutoManage(@NonNull final FragmentActivity p0);
    
    public abstract void unregisterConnectionCallbacks(@NonNull final ConnectionCallbacks p0);
    
    public abstract void unregisterConnectionFailedListener(@NonNull final OnConnectionFailedListener p0);
    
    public void zaa(final zacm zacm) {
        throw new UnsupportedOperationException();
    }
    
    public void zab(final zacm zacm) {
        throw new UnsupportedOperationException();
    }
    
    @KeepForSdk
    public static final class Builder
    {
        private final Context mContext;
        private Looper zabj;
        private final Set<Scope> zabr;
        private final Set<Scope> zabs;
        private int zabt;
        private View zabu;
        private String zabv;
        private String zabw;
        private final Map<Api<?>, ClientSettings.OptionalApiSettings> zabx;
        private boolean zaby;
        private final Map<Api<?>, Api.ApiOptions> zabz;
        private LifecycleActivity zaca;
        private int zacb;
        private OnConnectionFailedListener zacc;
        private GoogleApiAvailability zacd;
        private Api.AbstractClientBuilder<? extends zad, SignInOptions> zace;
        private final ArrayList<ConnectionCallbacks> zacf;
        private final ArrayList<OnConnectionFailedListener> zacg;
        private boolean zach;
        private Account zax;
        
        @KeepForSdk
        public Builder(@NonNull final Context mContext) {
            this.zabr = new HashSet<Scope>();
            this.zabs = new HashSet<Scope>();
            this.zabx = (Map<Api<?>, ClientSettings.OptionalApiSettings>)new ArrayMap();
            this.zaby = false;
            this.zabz = (Map<Api<?>, Api.ApiOptions>)new ArrayMap();
            this.zacb = -1;
            this.zacd = GoogleApiAvailability.getInstance();
            this.zace = zaa.zaph;
            this.zacf = new ArrayList<ConnectionCallbacks>();
            this.zacg = new ArrayList<OnConnectionFailedListener>();
            this.zach = false;
            this.mContext = mContext;
            this.zabj = mContext.getMainLooper();
            this.zabv = mContext.getPackageName();
            this.zabw = mContext.getClass().getName();
        }
        
        @KeepForSdk
        public Builder(@NonNull final Context context, @NonNull final ConnectionCallbacks connectionCallbacks, @NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            this(context);
            Preconditions.checkNotNull((Object)connectionCallbacks, (Object)"Must provide a connected listener");
            this.zacf.add(connectionCallbacks);
            Preconditions.checkNotNull((Object)onConnectionFailedListener, (Object)"Must provide a connection failed listener");
            this.zacg.add(onConnectionFailedListener);
        }
        
        private final <O extends Api.ApiOptions> void zaa(final Api<O> api, final O o, final Scope... array) {
            final HashSet<Scope> set = new HashSet<Scope>(api.zah().getImpliedScopes(o));
            for (int length = array.length, i = 0; i < length; ++i) {
                set.add(array[i]);
            }
            this.zabx.put(api, new ClientSettings.OptionalApiSettings(set));
        }
        
        public final Builder addApi(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api) {
            Preconditions.checkNotNull((Object)api, (Object)"Api must not be null");
            this.zabz.put(api, null);
            final List<Scope> impliedScopes = api.zah().getImpliedScopes((Api.ApiOptions.NotRequiredOptions)null);
            this.zabs.addAll(impliedScopes);
            this.zabr.addAll(impliedScopes);
            return this;
        }
        
        public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull final Api<O> api, @NonNull final O o) {
            Preconditions.checkNotNull((Object)api, (Object)"Api must not be null");
            Preconditions.checkNotNull((Object)o, (Object)"Null options are not permitted for this Api");
            this.zabz.put(api, (Api.ApiOptions)o);
            final List<Scope> impliedScopes = api.zah().getImpliedScopes(o);
            this.zabs.addAll(impliedScopes);
            this.zabr.addAll(impliedScopes);
            return this;
        }
        
        public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull final Api<O> api, @NonNull final O o, final Scope... array) {
            Preconditions.checkNotNull((Object)api, (Object)"Api must not be null");
            Preconditions.checkNotNull((Object)o, (Object)"Null options are not permitted for this Api");
            this.zabz.put(api, (Api.ApiOptions)o);
            this.zaa(api, o, array);
            return this;
        }
        
        public final Builder addApiIfAvailable(@NonNull final Api<? extends Api.ApiOptions.NotRequiredOptions> api, final Scope... array) {
            Preconditions.checkNotNull((Object)api, (Object)"Api must not be null");
            this.zabz.put(api, null);
            this.zaa((Api<Api.ApiOptions>)api, null, array);
            return this;
        }
        
        public final Builder addConnectionCallbacks(@NonNull final ConnectionCallbacks connectionCallbacks) {
            Preconditions.checkNotNull((Object)connectionCallbacks, (Object)"Listener must not be null");
            this.zacf.add(connectionCallbacks);
            return this;
        }
        
        public final Builder addOnConnectionFailedListener(@NonNull final OnConnectionFailedListener onConnectionFailedListener) {
            Preconditions.checkNotNull((Object)onConnectionFailedListener, (Object)"Listener must not be null");
            this.zacg.add(onConnectionFailedListener);
            return this;
        }
        
        public final Builder addScope(@NonNull final Scope scope) {
            Preconditions.checkNotNull((Object)scope, (Object)"Scope must not be null");
            this.zabr.add(scope);
            return this;
        }
        
        @KeepForSdk
        public final Builder addScopeNames(final String[] array) {
            for (int i = 0; i < array.length; ++i) {
                this.zabr.add(new Scope(array[i]));
            }
            return this;
        }
        
        public final GoogleApiClient build() {
            boolean b = !this.zabz.isEmpty();
            Preconditions.checkArgument(b, (Object)"must call addApi() to add at least one API");
            final ClientSettings buildClientSettings = this.buildClientSettings();
            Api api = null;
            final Map<Api<?>, ClientSettings.OptionalApiSettings> optionalApiSettings = buildClientSettings.getOptionalApiSettings();
            final ArrayMap arrayMap = new ArrayMap();
            final ArrayMap arrayMap2 = new ArrayMap();
            final ArrayList<zaq> list = new ArrayList<zaq>();
            final Iterator<Api<Object>> iterator = this.zabz.keySet().iterator();
            boolean b2 = false;
        Label_0224_Outer:
            while (true) {
                Api<Object> api2 = null;
                Object buildClient = null;
                if (iterator.hasNext()) {
                    api2 = iterator.next();
                    final Api.ApiOptions value = this.zabz.get(api2);
                    b = (optionalApiSettings.get(api2) != null);
                    ((Map<Api<Object>, Boolean>)arrayMap).put(api2, Boolean.valueOf(b));
                    final zaq zaq = new zaq(api2, b);
                    list.add(zaq);
                    final Api.AbstractClientBuilder<?, Api.ApiOptions> zai = api2.zai();
                    buildClient = zai.buildClient(this.mContext, this.zabj, buildClientSettings, value, zaq, zaq);
                    ((Map<Api.AnyClientKey<?>, Api.Client>)arrayMap2).put(api2.getClientKey(), (Api.Client)buildClient);
                    if (zai.getPriority() == 1) {
                        b2 = (value != null);
                    }
                }
                else {
                    Label_0559: {
                        while (true) {
                            Label_0444: {
                                if (api == null) {
                                    break Label_0444;
                                }
                                if (b2) {
                                    final String name = api.getName();
                                    throw new IllegalStateException(new StringBuilder(String.valueOf(name).length() + 82).append("With using ").append(name).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder").toString());
                                }
                                if (this.zax != null) {
                                    break Label_0559;
                                }
                                b = true;
                                Preconditions.checkState(b, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { api.getName() });
                                Preconditions.checkState(this.zabr.equals(this.zabs), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { api.getName() });
                            }
                            final zaaw zaaw = new zaaw(this.mContext, new ReentrantLock(), this.zabj, buildClientSettings, this.zacd, this.zace, (Map<Api<?>, Boolean>)arrayMap, this.zacf, this.zacg, (Map<Api.AnyClientKey<?>, Api.Client>)arrayMap2, this.zacb, com.google.android.gms.common.api.internal.zaaw.zaa(((Map<Api.AnyClientKey<?>, Api.Client>)arrayMap2).values(), true), list, false);
                            synchronized (GoogleApiClient.zabq) {
                                GoogleApiClient.zabq.add(zaaw);
                                // monitorexit(GoogleApiClient.zal())
                                if (this.zacb >= 0) {
                                    zaj.zaa(this.zaca).zaa(this.zacb, zaaw, this.zacc);
                                }
                                return zaaw;
                                b = false;
                                continue Label_0224_Outer;
                            }
                            break;
                        }
                    }
                }
                while (true) {
                    Api<Object> api3;
                    if (((Api.Client)buildClient).providesSignIn()) {
                        api3 = api2;
                        if (api != null) {
                            final String name2 = api2.getName();
                            final String name3 = api.getName();
                            throw new IllegalStateException(new StringBuilder(String.valueOf(name2).length() + 21 + String.valueOf(name3).length()).append(name2).append(" cannot be used with ").append(name3).toString());
                        }
                    }
                    else {
                        api3 = (Api<Object>)api;
                    }
                    api = api3;
                    continue Label_0224_Outer;
                    continue;
                }
            }
        }
        
        @KeepForSdk
        @VisibleForTesting
        public final ClientSettings buildClientSettings() {
            SignInOptions default1 = SignInOptions.DEFAULT;
            if (this.zabz.containsKey(zaa.API)) {
                default1 = (SignInOptions)this.zabz.get(zaa.API);
            }
            return new ClientSettings(this.zax, this.zabr, this.zabx, this.zabt, this.zabu, this.zabv, this.zabw, default1, false);
        }
        
        public final Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, final int zacb, @Nullable final OnConnectionFailedListener zacc) {
            final LifecycleActivity zaca = new LifecycleActivity((Activity)fragmentActivity);
            Preconditions.checkArgument(zacb >= 0, (Object)"clientId must be non-negative");
            this.zacb = zacb;
            this.zacc = zacc;
            this.zaca = zaca;
            return this;
        }
        
        public final Builder enableAutoManage(@NonNull final FragmentActivity fragmentActivity, @Nullable final OnConnectionFailedListener onConnectionFailedListener) {
            return this.enableAutoManage(fragmentActivity, 0, onConnectionFailedListener);
        }
        
        public final Builder setAccountName(final String s) {
            Account zax;
            if (s == null) {
                zax = null;
            }
            else {
                zax = new Account(s, "com.google");
            }
            this.zax = zax;
            return this;
        }
        
        public final Builder setGravityForPopups(final int zabt) {
            this.zabt = zabt;
            return this;
        }
        
        public final Builder setHandler(@NonNull final Handler handler) {
            Preconditions.checkNotNull((Object)handler, (Object)"Handler must not be null");
            this.zabj = handler.getLooper();
            return this;
        }
        
        public final Builder setViewForPopups(@NonNull final View zabu) {
            Preconditions.checkNotNull((Object)zabu, (Object)"View must not be null");
            this.zabu = zabu;
            return this;
        }
        
        public final Builder useDefaultAccount() {
            return this.setAccountName("<<default account>>");
        }
    }
    
    public interface ConnectionCallbacks
    {
        public static final int CAUSE_NETWORK_LOST = 2;
        public static final int CAUSE_SERVICE_DISCONNECTED = 1;
        
        void onConnected(@Nullable final Bundle p0);
        
        void onConnectionSuspended(final int p0);
    }
    
    public interface OnConnectionFailedListener
    {
        void onConnectionFailed(@NonNull final ConnectionResult p0);
    }
}
