// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.os.IInterface;
import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.os.IBinder;
import java.util.Set;
import com.google.android.gms.common.internal.IAccountAccessor;
import com.google.android.gms.common.Feature;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import java.util.Collections;
import java.util.List;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.accounts.Account;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;

public final class Api<O extends ApiOptions>
{
    private final String mName;
    private final AbstractClientBuilder<?, O> zaau;
    private final zaa<?, O> zaav;
    private final ClientKey<?> zaaw;
    private final zab<?> zaax;
    
    public <C extends Client> Api(final String mName, final AbstractClientBuilder<C, O> zaau, final ClientKey<C> zaaw) {
        Preconditions.checkNotNull((Object)zaau, (Object)"Cannot construct an Api with a null ClientBuilder");
        Preconditions.checkNotNull((Object)zaaw, (Object)"Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.zaau = zaau;
        this.zaav = null;
        this.zaaw = zaaw;
        this.zaax = null;
    }
    
    public final AnyClientKey<?> getClientKey() {
        if (this.zaaw != null) {
            return (AnyClientKey<?>)this.zaaw;
        }
        throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
    }
    
    public final String getName() {
        return this.mName;
    }
    
    public final BaseClientBuilder<?, O> zah() {
        return (BaseClientBuilder<?, O>)this.zaau;
    }
    
    public final AbstractClientBuilder<?, O> zai() {
        Preconditions.checkState(this.zaau != null, (Object)"This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zaau;
    }
    
    @KeepForSdk
    @VisibleForTesting
    public abstract static class AbstractClientBuilder<T extends Client, O> extends BaseClientBuilder<T, O>
    {
        @KeepForSdk
        public abstract T buildClient(final Context p0, final Looper p1, final ClientSettings p2, final O p3, final GoogleApiClient.ConnectionCallbacks p4, final GoogleApiClient.OnConnectionFailedListener p5);
    }
    
    @KeepForSdk
    public interface AnyClient
    {
    }
    
    @KeepForSdk
    public static class AnyClientKey<C extends AnyClient>
    {
    }
    
    public interface ApiOptions
    {
        public interface HasAccountOptions extends HasOptions, NotRequiredOptions
        {
            Account getAccount();
        }
        
        public interface HasGoogleSignInAccountOptions extends HasOptions
        {
            GoogleSignInAccount getGoogleSignInAccount();
        }
        
        public interface HasOptions extends ApiOptions
        {
        }
        
        public static final class NoOptions implements NotRequiredOptions
        {
            private NoOptions() {
            }
        }
        
        public interface NotRequiredOptions extends ApiOptions
        {
        }
        
        public interface Optional extends HasOptions, NotRequiredOptions
        {
        }
    }
    
    @KeepForSdk
    @VisibleForTesting
    public static class BaseClientBuilder<T extends AnyClient, O>
    {
        @KeepForSdk
        public static final int API_PRIORITY_GAMES = 1;
        @KeepForSdk
        public static final int API_PRIORITY_OTHER = Integer.MAX_VALUE;
        @KeepForSdk
        public static final int API_PRIORITY_PLUS = 2;
        
        @KeepForSdk
        public List<Scope> getImpliedScopes(final O o) {
            return Collections.emptyList();
        }
        
        @KeepForSdk
        public int getPriority() {
            return Integer.MAX_VALUE;
        }
    }
    
    @KeepForSdk
    public interface Client extends AnyClient
    {
        @KeepForSdk
        void connect(final BaseGmsClient$ConnectionProgressReportCallbacks p0);
        
        @KeepForSdk
        void disconnect();
        
        @KeepForSdk
        void dump(final String p0, final FileDescriptor p1, final PrintWriter p2, final String[] p3);
        
        @KeepForSdk
        Feature[] getAvailableFeatures();
        
        @KeepForSdk
        String getEndpointPackageName();
        
        @KeepForSdk
        int getMinApkVersion();
        
        @KeepForSdk
        void getRemoteService(final IAccountAccessor p0, final Set<Scope> p1);
        
        @KeepForSdk
        Feature[] getRequiredFeatures();
        
        @Nullable
        @KeepForSdk
        IBinder getServiceBrokerBinder();
        
        @KeepForSdk
        Intent getSignInIntent();
        
        @KeepForSdk
        boolean isConnected();
        
        @KeepForSdk
        boolean isConnecting();
        
        @KeepForSdk
        void onUserSignOut(final BaseGmsClient$SignOutCallbacks p0);
        
        @KeepForSdk
        boolean providesSignIn();
        
        @KeepForSdk
        boolean requiresAccount();
        
        @KeepForSdk
        boolean requiresGooglePlayServices();
        
        @KeepForSdk
        boolean requiresSignIn();
    }
    
    @KeepForSdk
    @VisibleForTesting
    public static final class ClientKey<C extends Client> extends AnyClientKey<C>
    {
    }
    
    public interface SimpleClient<T extends IInterface> extends AnyClient
    {
        T createServiceInterface(final IBinder p0);
        
        Context getContext();
        
        String getServiceDescriptor();
        
        String getStartServiceAction();
        
        void setState(final int p0, final T p1);
    }
    
    @VisibleForTesting
    public static class zaa<T extends SimpleClient, O> extends BaseClientBuilder<T, O>
    {
    }
    
    @VisibleForTesting
    public static final class zab<C extends SimpleClient> extends AnyClientKey<C>
    {
    }
}
