// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;

public final class zzh
{
    @NonNull
    private final String mPackageName;
    private final int zzdt;
    @NonNull
    private final String zzej;
    private final boolean zzek;
    
    public zzh(@NonNull final String mPackageName, @NonNull final String zzej, final boolean zzek, final int n) {
        this.mPackageName = mPackageName;
        this.zzej = zzej;
        this.zzek = zzek;
        this.zzdt = 129;
    }
    
    @NonNull
    final String getPackageName() {
        return this.mPackageName;
    }
    
    final int zzq() {
        return this.zzdt;
    }
    
    @NonNull
    final String zzt() {
        return this.zzej;
    }
}
