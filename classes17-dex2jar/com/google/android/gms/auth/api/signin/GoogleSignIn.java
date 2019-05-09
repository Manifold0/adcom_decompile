// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin;

import android.text.TextUtils;
import android.support.v4.app.Fragment;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import com.google.android.gms.tasks.Tasks;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.auth.api.signin.internal.zzh;
import com.google.android.gms.tasks.Task;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.internal.zzp;
import android.app.Activity;
import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import android.content.Context;

public final class GoogleSignIn
{
    private GoogleSignIn() {
    }
    
    @NonNull
    public static GoogleSignInAccount getAccountForExtension(@NonNull final Context context, @NonNull final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull((Object)context, (Object)"please provide a valid Context object");
        Preconditions.checkNotNull((Object)googleSignInOptionsExtension, (Object)"please provide valid GoogleSignInOptionsExtension");
        GoogleSignInAccount googleSignInAccount;
        if ((googleSignInAccount = getLastSignedInAccount(context)) == null) {
            googleSignInAccount = GoogleSignInAccount.createDefault();
        }
        return googleSignInAccount.requestExtraScopes(zzc(googleSignInOptionsExtension.getImpliedScopes()));
    }
    
    @NonNull
    public static GoogleSignInAccount getAccountForScopes(@NonNull final Context context, @NonNull final Scope scope, final Scope... array) {
        Preconditions.checkNotNull((Object)context, (Object)"please provide a valid Context object");
        Preconditions.checkNotNull((Object)scope, (Object)"please provide at least one valid scope");
        GoogleSignInAccount googleSignInAccount;
        if ((googleSignInAccount = getLastSignedInAccount(context)) == null) {
            googleSignInAccount = GoogleSignInAccount.createDefault();
        }
        googleSignInAccount.requestExtraScopes(new Scope[] { scope });
        googleSignInAccount.requestExtraScopes(array);
        return googleSignInAccount;
    }
    
    public static GoogleSignInClient getClient(@NonNull final Activity activity, @NonNull final GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(activity, (GoogleSignInOptions)Preconditions.checkNotNull((Object)googleSignInOptions));
    }
    
    public static GoogleSignInClient getClient(@NonNull final Context context, @NonNull final GoogleSignInOptions googleSignInOptions) {
        return new GoogleSignInClient(context, (GoogleSignInOptions)Preconditions.checkNotNull((Object)googleSignInOptions));
    }
    
    @Nullable
    public static GoogleSignInAccount getLastSignedInAccount(final Context context) {
        return zzp.zzd(context).zzh();
    }
    
    public static Task<GoogleSignInAccount> getSignedInAccountFromIntent(@Nullable final Intent intent) {
        final GoogleSignInResult signInResultFromIntent = zzh.getSignInResultFromIntent(intent);
        if (signInResultFromIntent == null) {
            return (Task<GoogleSignInAccount>)Tasks.forException((Exception)ApiExceptionUtil.fromStatus(Status.RESULT_INTERNAL_ERROR));
        }
        if (!signInResultFromIntent.getStatus().isSuccess() || signInResultFromIntent.getSignInAccount() == null) {
            return (Task<GoogleSignInAccount>)Tasks.forException((Exception)ApiExceptionUtil.fromStatus(signInResultFromIntent.getStatus()));
        }
        return (Task<GoogleSignInAccount>)Tasks.forResult((Object)signInResultFromIntent.getSignInAccount());
    }
    
    public static boolean hasPermissions(@Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull((Object)googleSignInOptionsExtension, (Object)"Please provide a non-null GoogleSignInOptionsExtension");
        return hasPermissions(googleSignInAccount, zzc(googleSignInOptionsExtension.getImpliedScopes()));
    }
    
    public static boolean hasPermissions(@Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final Scope... array) {
        if (googleSignInAccount == null) {
            return false;
        }
        final HashSet<Object> set = new HashSet<Object>();
        Collections.addAll(set, array);
        return googleSignInAccount.getGrantedScopes().containsAll(set);
    }
    
    public static void requestPermissions(@NonNull final Activity activity, final int n, @Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull((Object)activity, (Object)"Please provide a non-null Activity");
        Preconditions.checkNotNull((Object)googleSignInOptionsExtension, (Object)"Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(activity, n, googleSignInAccount, zzc(googleSignInOptionsExtension.getImpliedScopes()));
    }
    
    public static void requestPermissions(@NonNull final Activity activity, final int n, @Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final Scope... array) {
        Preconditions.checkNotNull((Object)activity, (Object)"Please provide a non-null Activity");
        Preconditions.checkNotNull((Object)array, (Object)"Please provide at least one scope");
        activity.startActivityForResult(zzc(activity, googleSignInAccount, array), n);
    }
    
    public static void requestPermissions(@NonNull final Fragment fragment, final int n, @Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        Preconditions.checkNotNull((Object)fragment, (Object)"Please provide a non-null Fragment");
        Preconditions.checkNotNull((Object)googleSignInOptionsExtension, (Object)"Please provide a non-null GoogleSignInOptionsExtension");
        requestPermissions(fragment, n, googleSignInAccount, zzc(googleSignInOptionsExtension.getImpliedScopes()));
    }
    
    public static void requestPermissions(@NonNull final Fragment fragment, final int n, @Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final Scope... array) {
        Preconditions.checkNotNull((Object)fragment, (Object)"Please provide a non-null Fragment");
        Preconditions.checkNotNull((Object)array, (Object)"Please provide at least one scope");
        fragment.startActivityForResult(zzc((Activity)fragment.getActivity(), googleSignInAccount, array), n);
    }
    
    @NonNull
    private static Intent zzc(@NonNull final Activity activity, @Nullable final GoogleSignInAccount googleSignInAccount, @NonNull final Scope... array) {
        final GoogleSignInOptions$Builder googleSignInOptions$Builder = new GoogleSignInOptions$Builder();
        if (array.length > 0) {
            googleSignInOptions$Builder.requestScopes(array[0], array);
        }
        if (googleSignInAccount != null && !TextUtils.isEmpty((CharSequence)googleSignInAccount.getEmail())) {
            googleSignInOptions$Builder.setAccountName(googleSignInAccount.getEmail());
        }
        return new GoogleSignInClient(activity, googleSignInOptions$Builder.build()).getSignInIntent();
    }
    
    @NonNull
    private static Scope[] zzc(@Nullable final List<Scope> list) {
        if (list == null) {
            return new Scope[0];
        }
        return list.toArray(new Scope[list.size()]);
    }
}
