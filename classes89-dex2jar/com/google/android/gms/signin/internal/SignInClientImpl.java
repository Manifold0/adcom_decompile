// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.accounts.Account;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.common.internal.Preconditions;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.IAccountAccessor;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient;
import com.google.android.gms.common.internal.BaseGmsClient$LegacyClientCallbackAdapter;
import android.os.Parcelable;
import com.google.android.gms.signin.SignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.signin.zad;
import com.google.android.gms.common.internal.GmsClient;

@KeepForSdk
public class SignInClientImpl extends GmsClient<zaf> implements zad
{
    private final ClientSettings zaet;
    private Integer zaoe;
    private final boolean zasb;
    private final Bundle zasc;
    
    private SignInClientImpl(final Context context, final Looper looper, final boolean b, final ClientSettings zaet, final Bundle zasc, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 44, zaet, connectionCallbacks, onConnectionFailedListener);
        this.zasb = true;
        this.zaet = zaet;
        this.zasc = zasc;
        this.zaoe = zaet.getClientSessionId();
    }
    
    public SignInClientImpl(final Context context, final Looper looper, final boolean b, final ClientSettings clientSettings, final SignInOptions signInOptions, final GoogleApiClient.ConnectionCallbacks connectionCallbacks, final GoogleApiClient.OnConnectionFailedListener onConnectionFailedListener) {
        this(context, looper, true, clientSettings, createBundleFromClientSettings(clientSettings), connectionCallbacks, onConnectionFailedListener);
    }
    
    @KeepForSdk
    public static Bundle createBundleFromClientSettings(final ClientSettings clientSettings) {
        final SignInOptions signInOptions = clientSettings.getSignInOptions();
        final Integer clientSessionId = clientSettings.getClientSessionId();
        final Bundle bundle = new Bundle();
        bundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", (Parcelable)clientSettings.getAccount());
        if (clientSessionId != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", (int)clientSessionId);
        }
        if (signInOptions != null) {
            bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", signInOptions.isOfflineAccessRequested());
            bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", signInOptions.isIdTokenRequested());
            bundle.putString("com.google.android.gms.signin.internal.serverClientId", signInOptions.getServerClientId());
            bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
            bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", signInOptions.isForceCodeForRefreshToken());
            bundle.putString("com.google.android.gms.signin.internal.hostedDomain", signInOptions.getHostedDomain());
            bundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", signInOptions.waitForAccessTokenRefresh());
            if (signInOptions.getAuthApiSignInModuleVersion() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", (long)signInOptions.getAuthApiSignInModuleVersion());
            }
            if (signInOptions.getRealClientLibraryVersion() != null) {
                bundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", (long)signInOptions.getRealClientLibraryVersion());
            }
        }
        return bundle;
    }
    
    @Override
    public final void connect() {
        ((Api.Client)this).connect((BaseGmsClient$ConnectionProgressReportCallbacks)new BaseGmsClient$LegacyClientCallbackAdapter((BaseGmsClient)this));
    }
    
    protected Bundle getGetServiceRequestExtraArgs() {
        if (!this.getContext().getPackageName().equals(this.zaet.getRealClientPackageName())) {
            this.zasc.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zaet.getRealClientPackageName());
        }
        return this.zasc;
    }
    
    @Override
    public int getMinApkVersion() {
        return 12451000;
    }
    
    protected String getServiceDescriptor() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }
    
    protected String getStartServiceAction() {
        return "com.google.android.gms.signin.service.START";
    }
    
    public boolean requiresSignIn() {
        return this.zasb;
    }
    
    @Override
    public final void zaa(final IAccountAccessor accountAccessor, final boolean b) {
        try {
            ((zaf)this.getService()).zaa(accountAccessor, this.zaoe, b);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }
    
    @Override
    public final void zaa(final com.google.android.gms.signin.internal.zad zad) {
        Preconditions.checkNotNull((Object)zad, (Object)"Expecting a valid ISignInCallbacks");
        try {
            final Account accountOrDefault = this.zaet.getAccountOrDefault();
            GoogleSignInAccount savedDefaultGoogleSignInAccount = null;
            if ("<<default account>>".equals(accountOrDefault.name)) {
                savedDefaultGoogleSignInAccount = Storage.getInstance(this.getContext()).getSavedDefaultGoogleSignInAccount();
            }
            ((zaf)this.getService()).zaa(new zah(new ResolveAccountRequest(accountOrDefault, this.zaoe, savedDefaultGoogleSignInAccount)), zad);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
            try {
                zad.zab(new zaj(8));
            }
            catch (RemoteException ex2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public final void zacw() {
        try {
            ((zaf)this.getService()).zam(this.zaoe);
        }
        catch (RemoteException ex) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }
}
