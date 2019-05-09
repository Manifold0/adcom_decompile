// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

final class zze implements Runnable
{
    private final /* synthetic */ zzr zzn;
    private final /* synthetic */ zzd zzo;
    
    zze(final zzd zzo, final zzr zzn) {
        this.zzo = zzo;
        this.zzn = zzn;
    }
    
    @Override
    public final void run() {
        try {
            this.zzo.zzi.put(this.zzn);
        }
        catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }
}
