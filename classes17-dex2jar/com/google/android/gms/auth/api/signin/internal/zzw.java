// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api$ApiOptions$HasOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient$Builder;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import android.os.Binder;
import android.content.Context;

public final class zzw extends zzr
{
    private final Context mContext;
    
    public zzw(final Context mContext) {
        this.mContext = mContext;
    }
    
    private final void zzl() {
        if (!GooglePlayServicesUtil.isGooglePlayServicesUid(this.mContext, Binder.getCallingUid())) {
            throw new SecurityException(new StringBuilder(52).append("Calling UID ").append(Binder.getCallingUid()).append(" is not Google Play services.").toString());
        }
    }
    
    @Override
    public final void zzj() {
        this.zzl();
        final Storage instance = Storage.getInstance(this.mContext);
        final GoogleSignInAccount savedDefaultGoogleSignInAccount = instance.getSavedDefaultGoogleSignInAccount();
        GoogleSignInOptions googleSignInOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            googleSignInOptions = instance.getSavedDefaultGoogleSignInOptions();
        }
        final GoogleApiClient build = new GoogleApiClient$Builder(this.mContext).addApi((Api)Auth.GOOGLE_SIGN_IN_API, (Api$ApiOptions$HasOptions)googleSignInOptions).build();
        try {
            if (build.blockingConnect().isSuccess()) {
                if (savedDefaultGoogleSignInAccount != null) {
                    Auth.GoogleSignInApi.revokeAccess(build);
                }
                else {
                    build.clearDefaultAccountAndReconnect();
                }
            }
        }
        finally {
            build.disconnect();
        }
    }
    
    @Override
    public final void zzk() {
        this.zzl();
        zzp.zzd(this.mContext).clear();
    }
}
