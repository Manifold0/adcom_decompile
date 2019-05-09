// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;
import android.annotation.TargetApi;
import android.view.TextureView;

@zzadh
@TargetApi(14)
public abstract class zzapg extends TextureView implements zzaqa
{
    protected final zzapp zzcxk;
    protected final zzapz zzcxl;
    
    public zzapg(final Context context) {
        super(context);
        this.zzcxk = new zzapp();
        this.zzcxl = new zzapz(context, this);
    }
    
    public abstract int getCurrentPosition();
    
    public abstract int getDuration();
    
    public abstract int getVideoHeight();
    
    public abstract int getVideoWidth();
    
    public abstract void pause();
    
    public abstract void play();
    
    public abstract void seekTo(final int p0);
    
    public abstract void setVideoPath(final String p0);
    
    public abstract void stop();
    
    public abstract void zza(final float p0, final float p1);
    
    public abstract void zza(final zzapf p0);
    
    public abstract String zzsp();
    
    public abstract void zzst();
}
