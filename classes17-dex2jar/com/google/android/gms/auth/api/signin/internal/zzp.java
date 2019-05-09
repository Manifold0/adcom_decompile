// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import android.support.annotation.NonNull;
import android.content.Context;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzp
{
    private static zzp zzbn;
    @VisibleForTesting
    private Storage zzbo;
    @VisibleForTesting
    private GoogleSignInAccount zzbp;
    @VisibleForTesting
    private GoogleSignInOptions zzbq;
    
    static {
        zzp.zzbn = null;
    }
    
    private zzp(final Context context) {
        this.zzbo = Storage.getInstance(context);
        this.zzbp = this.zzbo.getSavedDefaultGoogleSignInAccount();
        this.zzbq = this.zzbo.getSavedDefaultGoogleSignInOptions();
    }
    
    public static zzp zzd(@NonNull final Context context) {
        synchronized (zzp.class) {
            return zze(context.getApplicationContext());
        }
    }
    
    private static zzp zze(final Context context) {
        synchronized (zzp.class) {
            if (zzp.zzbn == null) {
                zzp.zzbn = new zzp(context);
            }
            return zzp.zzbn;
        }
    }
    
    public final void clear() {
        synchronized (this) {
            this.zzbo.clear();
            this.zzbp = null;
            this.zzbq = null;
        }
    }
    
    public final void zzc(final GoogleSignInOptions zzbq, final GoogleSignInAccount zzbp) {
        synchronized (this) {
            this.zzbo.saveDefaultGoogleSignInAccount(zzbp, zzbq);
            this.zzbp = zzbp;
            this.zzbq = zzbq;
        }
    }
    
    public final GoogleSignInAccount zzh() {
        synchronized (this) {
            return this.zzbp;
        }
    }
    
    public final GoogleSignInOptions zzi() {
        synchronized (this) {
            return this.zzbq;
        }
    }
}
