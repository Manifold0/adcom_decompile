// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.tasks;

public class CancellationTokenSource
{
    private final zza zzc;
    
    public CancellationTokenSource() {
        this.zzc = new zza();
    }
    
    public void cancel() {
        this.zzc.cancel();
    }
    
    public CancellationToken getToken() {
        return this.zzc;
    }
}
