// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import java.util.Iterator;
import com.google.android.gms.common.api.internal.GoogleApiManager;
import android.accounts.Account;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.internal.OptionalPendingResultImpl;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.PendingResults;
import java.util.Collection;
import java.util.HashSet;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.OptionalPendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import android.os.Parcelable;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import android.content.Context;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import android.content.Intent;
import com.google.android.gms.common.logging.Logger;

public final class zzh
{
    private static Logger zzbd;
    
    static {
        zzh.zzbd = new Logger("GoogleSignInCommon", new String[0]);
    }
    
    @Nullable
    public static GoogleSignInResult getSignInResultFromIntent(final Intent intent) {
        if (intent == null || (!intent.hasExtra("googleSignInStatus") && !intent.hasExtra("googleSignInAccount"))) {
            return null;
        }
        final GoogleSignInAccount googleSignInAccount = (GoogleSignInAccount)intent.getParcelableExtra("googleSignInAccount");
        Status result_SUCCESS = (Status)intent.getParcelableExtra("googleSignInStatus");
        if (googleSignInAccount != null) {
            result_SUCCESS = Status.RESULT_SUCCESS;
        }
        return new GoogleSignInResult(googleSignInAccount, result_SUCCESS);
    }
    
    public static Intent zzc(final Context context, final GoogleSignInOptions googleSignInOptions) {
        zzh.zzbd.d("getSignInIntent()", new Object[0]);
        final SignInConfiguration signInConfiguration = new SignInConfiguration(context.getPackageName(), googleSignInOptions);
        final Intent intent = new Intent("com.google.android.gms.auth.GOOGLE_SIGN_IN");
        intent.setPackage(context.getPackageName());
        intent.setClass(context, (Class)SignInHubActivity.class);
        final Bundle bundle = new Bundle();
        bundle.putParcelable("config", (Parcelable)signInConfiguration);
        intent.putExtra("config", bundle);
        return intent;
    }
    
    public static OptionalPendingResult<GoogleSignInResult> zzc(final GoogleApiClient googleApiClient, final Context context, final GoogleSignInOptions googleSignInOptions, final boolean b) {
        zzh.zzbd.d("silentSignIn()", new Object[0]);
        zzh.zzbd.d("getEligibleSavedSignInResult()", new Object[0]);
        Preconditions.checkNotNull((Object)googleSignInOptions);
        final GoogleSignInOptions zzi = zzp.zzd(context).zzi();
        while (true) {
            Label_0220: {
                if (zzi == null) {
                    break Label_0220;
                }
                final Account account = zzi.getAccount();
                final Account account2 = googleSignInOptions.getAccount();
                boolean equals;
                if (account == null) {
                    if (account2 == null) {
                        equals = true;
                    }
                    else {
                        equals = false;
                    }
                }
                else {
                    equals = account.equals((Object)account2);
                }
                if (!equals || googleSignInOptions.isServerAuthCodeRequested() || (googleSignInOptions.isIdTokenRequested() && (!zzi.isIdTokenRequested() || !googleSignInOptions.getServerClientId().equals(zzi.getServerClientId()))) || !new HashSet(zzi.getScopes()).containsAll(new HashSet(googleSignInOptions.getScopes()))) {
                    break Label_0220;
                }
                final GoogleSignInAccount zzh = zzp.zzd(context).zzh();
                if (zzh == null || zzh.isExpired()) {
                    break Label_0220;
                }
                final Object o = new GoogleSignInResult(zzh, Status.RESULT_SUCCESS);
                if (o != null) {
                    com.google.android.gms.auth.api.signin.internal.zzh.zzbd.d("Eligible saved sign in result found", new Object[0]);
                    return (OptionalPendingResult<GoogleSignInResult>)PendingResults.immediatePendingResult((Result)o, googleApiClient);
                }
                if (b) {
                    return (OptionalPendingResult<GoogleSignInResult>)PendingResults.immediatePendingResult((Result)new GoogleSignInResult(null, new Status(4)), googleApiClient);
                }
                com.google.android.gms.auth.api.signin.internal.zzh.zzbd.d("trySilentSignIn()", new Object[0]);
                return (OptionalPendingResult<GoogleSignInResult>)new OptionalPendingResultImpl((PendingResult)googleApiClient.enqueue((BaseImplementation$ApiMethodImpl)new zzi(googleApiClient, context, googleSignInOptions)));
            }
            final Object o = null;
            continue;
        }
    }
    
    public static PendingResult<Status> zzc(final GoogleApiClient googleApiClient, final Context context, final boolean b) {
        zzh.zzbd.d("Signing out", new Object[0]);
        zzc(context);
        if (b) {
            return (PendingResult<Status>)PendingResults.immediatePendingResult(Status.RESULT_SUCCESS, googleApiClient);
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzk(googleApiClient));
    }
    
    private static void zzc(final Context context) {
        zzp.zzd(context).clear();
        final Iterator<GoogleApiClient> iterator = GoogleApiClient.getAllClients().iterator();
        while (iterator.hasNext()) {
            iterator.next().maybeSignOut();
        }
        GoogleApiManager.reportSignOut();
    }
    
    public static Intent zzd(final Context context, final GoogleSignInOptions googleSignInOptions) {
        zzh.zzbd.d("getFallbackSignInIntent()", new Object[0]);
        final Intent zzc = zzc(context, googleSignInOptions);
        zzc.setAction("com.google.android.gms.auth.APPAUTH_SIGN_IN");
        return zzc;
    }
    
    public static PendingResult<Status> zzd(final GoogleApiClient googleApiClient, final Context context, final boolean b) {
        zzh.zzbd.d("Revoking access", new Object[0]);
        final String savedRefreshToken = Storage.getInstance(context).getSavedRefreshToken();
        zzc(context);
        if (b) {
            return zzd.zzc(savedRefreshToken);
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzm(googleApiClient));
    }
    
    public static Intent zze(final Context context, final GoogleSignInOptions googleSignInOptions) {
        zzh.zzbd.d("getNoImplementationSignInIntent()", new Object[0]);
        final Intent zzc = zzc(context, googleSignInOptions);
        zzc.setAction("com.google.android.gms.auth.NO_IMPL");
        return zzc;
    }
}
