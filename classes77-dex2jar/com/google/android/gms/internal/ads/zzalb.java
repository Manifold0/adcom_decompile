// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.media.AudioManager;
import android.content.Context;

@zzadh
public final class zzalb
{
    private float zzcdn;
    private boolean zzcdt;
    
    public zzalb() {
        this.zzcdt = false;
        this.zzcdn = 1.0f;
    }
    
    public static float zzay(final Context context) {
        final AudioManager audioManager = (AudioManager)context.getSystemService("audio");
        if (audioManager == null) {
            return 0.0f;
        }
        final int streamMaxVolume = audioManager.getStreamMaxVolume(3);
        final int streamVolume = audioManager.getStreamVolume(3);
        if (streamMaxVolume == 0) {
            return 0.0f;
        }
        return streamVolume / (float)streamMaxVolume;
    }
    
    private final boolean zzrr() {
        synchronized (this) {
            return this.zzcdn >= 0.0f;
        }
    }
    
    public final void setAppMuted(final boolean zzcdt) {
        synchronized (this) {
            this.zzcdt = zzcdt;
        }
    }
    
    public final void setAppVolume(final float zzcdn) {
        synchronized (this) {
            this.zzcdn = zzcdn;
        }
    }
    
    public final float zzdo() {
        synchronized (this) {
            float zzcdn;
            if (this.zzrr()) {
                zzcdn = this.zzcdn;
            }
            else {
                zzcdn = 1.0f;
            }
            return zzcdn;
        }
    }
    
    public final boolean zzdp() {
        synchronized (this) {
            return this.zzcdt;
        }
    }
}
