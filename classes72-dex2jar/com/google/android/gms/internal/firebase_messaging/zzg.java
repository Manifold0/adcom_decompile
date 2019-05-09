// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.firebase_messaging;

final class zzg extends zzd
{
    private final zze zzh;
    
    zzg() {
        this.zzh = new zze();
    }
    
    @Override
    public final void zza(final Throwable t, final Throwable t2) {
        if (t2 == t) {
            throw new IllegalArgumentException("Self suppression is not allowed.", t2);
        }
        if (t2 == null) {
            throw new NullPointerException("The suppressed exception cannot be null.");
        }
        this.zzh.zza(t, true).add(t2);
    }
}
