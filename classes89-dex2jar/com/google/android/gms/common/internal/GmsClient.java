// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import com.google.android.gms.common.Feature;
import java.util.Iterator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Looper;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.GoogleApiAvailability;
import android.os.Handler;
import android.content.Context;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import android.os.IInterface;

@KeepForSdk
public abstract class GmsClient<T extends IInterface> extends BaseGmsClient<T> implements Client, GmsClientEventState
{
    private final Set<Scope> mScopes;
    private final ClientSettings zaet;
    private final Account zax;
    
    @KeepForSdk
    @VisibleForTesting
    protected GmsClient(final Context context, final Handler handler, final int n, final ClientSettings clientSettings) {
        this(context, handler, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), n, clientSettings, null, null);
    }
    
    @VisibleForTesting
    protected GmsClient(final Context context, final Handler handler, final GmsClientSupervisor gmsClientSupervisor, final GoogleApiAvailability googleApiAvailability, final int n, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, handler, gmsClientSupervisor, (GoogleApiAvailabilityLight)googleApiAvailability, n, zaa(connectionCallbacks), zaa(onConnectionFailedListener));
        this.zaet = (ClientSettings)Preconditions.checkNotNull((Object)clientSettings);
        this.zax = clientSettings.getAccount();
        this.mScopes = this.zaa(clientSettings.getAllRequestedScopes());
    }
    
    @KeepForSdk
    protected GmsClient(final Context context, final Looper looper, final int n, final ClientSettings clientSettings) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), n, clientSettings, null, null);
    }
    
    @KeepForSdk
    protected GmsClient(final Context context, final Looper looper, final int n, final ClientSettings clientSettings, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, GmsClientSupervisor.getInstance(context), GoogleApiAvailability.getInstance(), n, clientSettings, (GoogleApiClient.ConnectionCallbacks)Preconditions.checkNotNull((Object)connectionCallbacks), (GoogleApiClient.OnConnectionFailedListener)Preconditions.checkNotNull((Object)onConnectionFailedListener));
    }
    
    @VisibleForTesting
    protected GmsClient(final Context context, final Looper looper, final GmsClientSupervisor gmsClientSupervisor, final GoogleApiAvailability googleApiAvailability, final int n, final ClientSettings zaet, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, gmsClientSupervisor, (GoogleApiAvailabilityLight)googleApiAvailability, n, zaa(connectionCallbacks), zaa(onConnectionFailedListener), zaet.getRealClientClassName());
        this.zaet = zaet;
        this.zax = zaet.getAccount();
        this.mScopes = this.zaa(zaet.getAllRequestedScopes());
    }
    
    @Nullable
    private static BaseGmsClient$BaseConnectionCallbacks zaa(final GoogleApiClient.ConnectionCallbacks connectionCallbacks) {
        if (connectionCallbacks == null) {
            return null;
        }
        return (BaseGmsClient$BaseConnectionCallbacks)new zaf(connectionCallbacks);
    }
    
    @Nullable
    private static BaseGmsClient$BaseOnConnectionFailedListener zaa(final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        if (onConnectionFailedListener == null) {
            return null;
        }
        return (BaseGmsClient$BaseOnConnectionFailedListener)new zag(onConnectionFailedListener);
    }
    
    private final Set<Scope> zaa(@NonNull final Set<Scope> set) {
        final Set<Scope> validateScopes = this.validateScopes(set);
        final Iterator<Scope> iterator = validateScopes.iterator();
        while (iterator.hasNext()) {
            if (!set.contains(iterator.next())) {
                throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
            }
        }
        return validateScopes;
    }
    
    public final Account getAccount() {
        return this.zax;
    }
    
    @KeepForSdk
    protected final ClientSettings getClientSettings() {
        return this.zaet;
    }
    
    public int getMinApkVersion() {
        return super.getMinApkVersion();
    }
    
    @KeepForSdk
    public Feature[] getRequiredFeatures() {
        return new Feature[0];
    }
    
    protected final Set<Scope> getScopes() {
        return this.mScopes;
    }
    
    @NonNull
    @KeepForSdk
    protected Set<Scope> validateScopes(@NonNull final Set<Scope> set) {
        return set;
    }
}
