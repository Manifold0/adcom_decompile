// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.Context;

@zzadh
public class zzabn extends zzabf
{
    zzabn(final Context context, final zzaji zzaji, final zzaqw zzaqw, final zzabm zzabm) {
        super(context, zzaji, zzaqw, zzabm);
    }
    
    @Override
    protected final void zzns() {
        if (this.zzbzf.errorCode != -2) {
            return;
        }
        this.zzbnd.zzuf().zza(this);
        this.zznu();
        zzakb.zzck("Loading HTML in WebView.");
        this.zzbnd.zzc(this.zzbzf.zzbyq, this.zzbzf.zzceo, null);
    }
    
    protected void zznu() {
    }
}
