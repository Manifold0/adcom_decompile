// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;

@zzadh
public final class zzami extends zzajx
{
    private final String zzag;
    private final zzanf zzctw;
    
    public zzami(final Context context, final String s, final String s2) {
        this(s2, zzbv.zzek().zzm(context, s));
    }
    
    private zzami(final String zzag, final String s) {
        this.zzctw = new zzanf(s);
        this.zzag = zzag;
    }
    
    @Override
    public final void onStop() {
    }
    
    @Override
    public final void zzdn() {
        this.zzctw.zzcz(this.zzag);
    }
}
