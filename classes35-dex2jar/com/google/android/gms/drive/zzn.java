// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import android.support.annotation.Nullable;

@Deprecated
public final class zzn extends ExecutionOptions
{
    private boolean zzar;
    
    private zzn(@Nullable final String s, final boolean b, final int n, final boolean zzar) {
        super(s, b, n);
        this.zzar = zzar;
    }
    
    public static zzn zza(@Nullable final ExecutionOptions executionOptions) {
        final zzp zzp = new zzp();
        if (executionOptions != null) {
            ((Builder)zzp).setConflictStrategy(executionOptions.zzm());
            ((Builder)zzp).setNotifyOnCompletion(executionOptions.zzl());
            final String zzk = executionOptions.zzk();
            if (zzk != null) {
                ((Builder)zzp).setTrackingTag(zzk);
            }
        }
        return (zzn)((Builder)zzp).build();
    }
    
    public final boolean zzo() {
        return this.zzar;
    }
}
