package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.os.Binder;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.Api.ApiOptions.HasOptions;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;

public final class zzw extends zzr {
    private final Context mContext;

    public zzw(Context context) {
        this.mContext = context;
    }

    public final void zzj() {
        zzl();
        Storage instance = Storage.getInstance(this.mContext);
        GoogleSignInAccount savedDefaultGoogleSignInAccount = instance.getSavedDefaultGoogleSignInAccount();
        HasOptions hasOptions = GoogleSignInOptions.DEFAULT_SIGN_IN;
        if (savedDefaultGoogleSignInAccount != null) {
            hasOptions = instance.getSavedDefaultGoogleSignInOptions();
        }
        GoogleApiClient build = new Builder(this.mContext).addApi(Auth.GOOGLE_SIGN_IN_API, hasOptions).build();
        try {
            if (build.blockingConnect().isSuccess()) {
                if (savedDefaultGoogleSignInAccount != null) {
                    Auth.GoogleSignInApi.revokeAccess(build);
                } else {
                    build.clearDefaultAccountAndReconnect();
                }
            }
            build.disconnect();
        } catch (Throwable th) {
            build.disconnect();
        }
    }

    public final void zzk() {
        zzl();
        zzp.zzd(this.mContext).clear();
    }

    private final void zzl() {
        if (!GooglePlayServicesUtil.isGooglePlayServicesUid(this.mContext, Binder.getCallingUid())) {
            throw new SecurityException("Calling UID " + Binder.getCallingUid() + " is not Google Play services.");
        }
    }
}
