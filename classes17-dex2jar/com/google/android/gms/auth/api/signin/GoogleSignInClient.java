// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.PendingResultUtil;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.auth.api.signin.internal.zzh;
import android.content.Intent;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.common.GoogleApiAvailability;
import android.content.Context;
import com.google.android.gms.common.api.internal.StatusExceptionMapper;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.internal.ApiExceptionMapper;
import com.google.android.gms.auth.api.Auth;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.api.GoogleApi;

public class GoogleSignInClient extends GoogleApi<GoogleSignInOptions>
{
    private static final zzc zzar;
    @VisibleForTesting
    private static int zzas;
    
    static {
        zzar = new zzc(null);
        GoogleSignInClient.zzas = zzd.zzau;
    }
    
    GoogleSignInClient(@NonNull final Activity activity, final GoogleSignInOptions googleSignInOptions) {
        super(activity, (Api)Auth.GOOGLE_SIGN_IN_API, (Api$ApiOptions)googleSignInOptions, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    GoogleSignInClient(@NonNull final Context context, final GoogleSignInOptions googleSignInOptions) {
        super(context, (Api)Auth.GOOGLE_SIGN_IN_API, (Api$ApiOptions)googleSignInOptions, (StatusExceptionMapper)new ApiExceptionMapper());
    }
    
    private final int zze() {
        while (true) {
            while (true) {
                Label_0079: {
                    synchronized (this) {
                        if (GoogleSignInClient.zzas == zzd.zzau) {
                            final Context applicationContext = this.getApplicationContext();
                            final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
                            final int googlePlayServicesAvailable = instance.isGooglePlayServicesAvailable(applicationContext, 12451000);
                            if (googlePlayServicesAvailable == 0) {
                                GoogleSignInClient.zzas = zzd.zzax;
                            }
                            else {
                                if (instance.getErrorResolutionIntent(applicationContext, googlePlayServicesAvailable, (String)null) != null || DynamiteModule.getLocalVersion(applicationContext, "com.google.android.gms.auth.api.fallback") == 0) {
                                    break Label_0079;
                                }
                                GoogleSignInClient.zzas = zzd.zzaw;
                            }
                        }
                        return GoogleSignInClient.zzas;
                    }
                }
                GoogleSignInClient.zzas = zzd.zzav;
                continue;
            }
        }
    }
    
    @NonNull
    public Intent getSignInIntent() {
        final Context applicationContext = this.getApplicationContext();
        switch (com.google.android.gms.auth.api.signin.zzc.zzat[this.zze() - 1]) {
            default: {
                return zzh.zze(applicationContext, (GoogleSignInOptions)this.getApiOptions());
            }
            case 1: {
                return zzh.zzd(applicationContext, (GoogleSignInOptions)this.getApiOptions());
            }
            case 2: {
                return zzh.zzc(applicationContext, (GoogleSignInOptions)this.getApiOptions());
            }
        }
    }
    
    public Task<Void> revokeAccess() {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)zzh.zzd(this.asGoogleApiClient(), this.getApplicationContext(), this.zze() == zzd.zzaw));
    }
    
    public Task<Void> signOut() {
        return (Task<Void>)PendingResultUtil.toVoidTask((PendingResult)zzh.zzc(this.asGoogleApiClient(), this.getApplicationContext(), this.zze() == zzd.zzaw));
    }
    
    public Task<GoogleSignInAccount> silentSignIn() {
        return (Task<GoogleSignInAccount>)PendingResultUtil.toTask((PendingResult)zzh.zzc(this.asGoogleApiClient(), this.getApplicationContext(), (GoogleSignInOptions)this.getApiOptions(), this.zze() == zzd.zzaw), (PendingResultUtil$ResultConverter)GoogleSignInClient.zzar);
    }
    
    private static final class zzc implements PendingResultUtil$ResultConverter<GoogleSignInResult, GoogleSignInAccount>
    {
    }
    
    @VisibleForTesting
    enum zzd
    {
        public static final int zzau;
        public static final int zzav;
        public static final int zzaw;
        public static final int zzax;
        private static final /* synthetic */ int[] zzay;
        
        static {
            zzau = 1;
            zzav = 2;
            zzaw = 3;
            zzax = 4;
            zzay = new int[] { zzd.zzau, zzd.zzav, zzd.zzaw, zzd.zzax };
        }
        
        public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FC5N68SJFD5I2UPRDECNM2TBKD0NM2S395TPMIPRED5N2UHRFDTJMOPAJD5JMSIBE8DM6IPBEEGI4IRBGDHIMQPBEEHGN8QBFDOTG____0() {
            return zzd.zzay.clone();
        }
    }
}
