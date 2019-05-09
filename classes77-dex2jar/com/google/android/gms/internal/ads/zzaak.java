// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.view.Display;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzv;

@zzadh
public final class zzaak extends zzaal implements zzv<zzaqw>
{
    private final Context mContext;
    private final WindowManager zzaeu;
    private DisplayMetrics zzagj;
    private final zzaqw zzbnd;
    private final zzmw zzbww;
    private float zzbwx;
    private int zzbwy;
    private int zzbwz;
    private int zzbxa;
    private int zzbxb;
    private int zzbxc;
    private int zzbxd;
    private int zzbxe;
    
    public zzaak(final zzaqw zzbnd, final Context mContext, final zzmw zzbww) {
        super(zzbnd);
        this.zzbwy = -1;
        this.zzbwz = -1;
        this.zzbxb = -1;
        this.zzbxc = -1;
        this.zzbxd = -1;
        this.zzbxe = -1;
        this.zzbnd = zzbnd;
        this.mContext = mContext;
        this.zzbww = zzbww;
        this.zzaeu = (WindowManager)mContext.getSystemService("window");
    }
    
    public final void zzc(final int n, final int n2) {
        int n3;
        if (this.mContext instanceof Activity) {
            n3 = zzbv.zzek().zzh((Activity)this.mContext)[0];
        }
        else {
            n3 = 0;
        }
        if (this.zzbnd.zzud() == null || !this.zzbnd.zzud().zzvs()) {
            zzkb.zzif();
            this.zzbxd = zzamu.zzb(this.mContext, this.zzbnd.getWidth());
            zzkb.zzif();
            this.zzbxe = zzamu.zzb(this.mContext, this.zzbnd.getHeight());
        }
        this.zzc(n, n2 - n3, this.zzbxd, this.zzbxe);
        this.zzbnd.zzuf().zzb(n, n2);
    }
}
