// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.wrappers;

import com.google.android.gms.common.util.VisibleForTesting;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class Wrappers
{
    private static Wrappers zzhz;
    private PackageManagerWrapper zzhy;
    
    static {
        Wrappers.zzhz = new Wrappers();
    }
    
    public Wrappers() {
        this.zzhy = null;
    }
    
    @KeepForSdk
    public static PackageManagerWrapper packageManager(final Context context) {
        return Wrappers.zzhz.zzi(context);
    }
    
    @VisibleForTesting
    private final PackageManagerWrapper zzi(Context applicationContext) {
        synchronized (this) {
            if (this.zzhy == null) {
                if (applicationContext.getApplicationContext() != null) {
                    applicationContext = applicationContext.getApplicationContext();
                }
                this.zzhy = new PackageManagerWrapper(applicationContext);
            }
            return this.zzhy;
        }
    }
}
