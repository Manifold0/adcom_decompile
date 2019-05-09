// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;
import android.media.AudioManager;
import android.annotation.TargetApi;
import android.media.AudioManager$OnAudioFocusChangeListener;

@zzadh
@TargetApi(14)
public final class zzapz implements AudioManager$OnAudioFocusChangeListener
{
    private final AudioManager mAudioManager;
    private boolean zzcxs;
    private final zzaqa zzdaq;
    private boolean zzdar;
    private boolean zzdas;
    private float zzdat;
    
    public zzapz(final Context context, final zzaqa zzdaq) {
        this.zzdat = 1.0f;
        this.mAudioManager = (AudioManager)context.getSystemService("audio");
        this.zzdaq = zzdaq;
    }
    
    private final void zztw() {
        final boolean b = true;
        boolean zzdar = true;
        boolean b2;
        if (this.zzcxs && !this.zzdas && this.zzdat > 0.0f) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (b2 && !this.zzdar) {
            if (this.mAudioManager != null && !this.zzdar) {
                if (this.mAudioManager.requestAudioFocus((AudioManager$OnAudioFocusChangeListener)this, 3, 2) != 1) {
                    zzdar = false;
                }
                this.zzdar = zzdar;
            }
            this.zzdaq.zzst();
        }
        else if (!b2 && this.zzdar) {
            if (this.mAudioManager != null && this.zzdar) {
                this.zzdar = (this.mAudioManager.abandonAudioFocus((AudioManager$OnAudioFocusChangeListener)this) == 0 && b);
            }
            this.zzdaq.zzst();
        }
    }
    
    public final float getVolume() {
        float zzdat;
        if (this.zzdas) {
            zzdat = 0.0f;
        }
        else {
            zzdat = this.zzdat;
        }
        if (this.zzdar) {
            return zzdat;
        }
        return 0.0f;
    }
    
    public final void onAudioFocusChange(final int n) {
        this.zzdar = (n > 0);
        this.zzdaq.zzst();
    }
    
    public final void setMuted(final boolean zzdas) {
        this.zzdas = zzdas;
        this.zztw();
    }
    
    public final void setVolume(final float zzdat) {
        this.zzdat = zzdat;
        this.zztw();
    }
    
    public final void zztt() {
        this.zzcxs = true;
        this.zztw();
    }
    
    public final void zztu() {
        this.zzcxs = false;
        this.zztw();
    }
}
