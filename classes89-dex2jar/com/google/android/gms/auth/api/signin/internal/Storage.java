// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import javax.annotation.Nullable;
import org.json.JSONException;
import android.text.TextUtils;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import android.content.Context;
import java.util.concurrent.locks.ReentrantLock;
import android.content.SharedPreferences;
import javax.annotation.concurrent.GuardedBy;
import java.util.concurrent.locks.Lock;

public class Storage
{
    private static final Lock zaaj;
    @GuardedBy("sLk")
    private static Storage zaak;
    private final Lock zaal;
    @GuardedBy("mLk")
    private final SharedPreferences zaam;
    
    static {
        zaaj = new ReentrantLock();
    }
    
    @VisibleForTesting
    private Storage(final Context context) {
        this.zaal = new ReentrantLock();
        this.zaam = context.getSharedPreferences("com.google.android.gms.signin", 0);
    }
    
    @KeepForSdk
    public static Storage getInstance(final Context context) {
        Preconditions.checkNotNull((Object)context);
        Storage.zaaj.lock();
        try {
            if (Storage.zaak == null) {
                Storage.zaak = new Storage(context.getApplicationContext());
            }
            return Storage.zaak;
        }
        finally {
            Storage.zaaj.unlock();
        }
    }
    
    private final void zaa(final String s, final String s2) {
        this.zaal.lock();
        try {
            this.zaam.edit().putString(s, s2).apply();
        }
        finally {
            this.zaal.unlock();
        }
    }
    
    private static String zab(final String s, final String s2) {
        return new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(s2).length()).append(s).append(":").append(s2).toString();
    }
    
    @Nullable
    @VisibleForTesting
    private final GoogleSignInAccount zad(String zaf) {
        if (!TextUtils.isEmpty((CharSequence)zaf)) {
            zaf = this.zaf(zab("googleSignInAccount", zaf));
            if (zaf != null) {
                try {
                    return GoogleSignInAccount.zaa(zaf);
                }
                catch (JSONException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    @Nullable
    @VisibleForTesting
    private final GoogleSignInOptions zae(String zaf) {
        if (!TextUtils.isEmpty((CharSequence)zaf)) {
            zaf = this.zaf(zab("googleSignInOptions", zaf));
            if (zaf != null) {
                try {
                    return GoogleSignInOptions.zab(zaf);
                }
                catch (JSONException ex) {
                    return null;
                }
            }
        }
        return null;
    }
    
    @Nullable
    private final String zaf(String string) {
        this.zaal.lock();
        try {
            string = this.zaam.getString(string, (String)null);
            return string;
        }
        finally {
            this.zaal.unlock();
        }
    }
    
    private final void zag(final String s) {
        this.zaal.lock();
        try {
            this.zaam.edit().remove(s).apply();
        }
        finally {
            this.zaal.unlock();
        }
    }
    
    @KeepForSdk
    public void clear() {
        this.zaal.lock();
        try {
            this.zaam.edit().clear().apply();
        }
        finally {
            this.zaal.unlock();
        }
    }
    
    @Nullable
    @KeepForSdk
    public GoogleSignInAccount getSavedDefaultGoogleSignInAccount() {
        return this.zad(this.zaf("defaultGoogleSignInAccount"));
    }
    
    @Nullable
    @KeepForSdk
    public GoogleSignInOptions getSavedDefaultGoogleSignInOptions() {
        return this.zae(this.zaf("defaultGoogleSignInAccount"));
    }
    
    @Nullable
    @KeepForSdk
    public String getSavedRefreshToken() {
        return this.zaf("refreshToken");
    }
    
    @KeepForSdk
    public void saveDefaultGoogleSignInAccount(final GoogleSignInAccount googleSignInAccount, final GoogleSignInOptions googleSignInOptions) {
        Preconditions.checkNotNull((Object)googleSignInAccount);
        Preconditions.checkNotNull((Object)googleSignInOptions);
        this.zaa("defaultGoogleSignInAccount", googleSignInAccount.zab());
        Preconditions.checkNotNull((Object)googleSignInAccount);
        Preconditions.checkNotNull((Object)googleSignInOptions);
        final String zab = googleSignInAccount.zab();
        this.zaa(zab("googleSignInAccount", zab), googleSignInAccount.zac());
        this.zaa(zab("googleSignInOptions", zab), googleSignInOptions.zae());
    }
    
    public final void zaf() {
        final String zaf = this.zaf("defaultGoogleSignInAccount");
        this.zag("defaultGoogleSignInAccount");
        if (!TextUtils.isEmpty((CharSequence)zaf)) {
            this.zag(zab("googleSignInAccount", zaf));
            this.zag(zab("googleSignInOptions", zaf));
        }
    }
}
