// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.ads.zzaiv;
import com.google.android.gms.internal.ads.zzaio;
import com.google.android.gms.internal.ads.zzaip;
import com.google.android.gms.internal.ads.zzapo;
import com.google.android.gms.internal.ads.zzaqb;
import android.content.Context;
import com.google.android.gms.internal.ads.zzhx;
import com.google.android.gms.internal.ads.zzaiu;
import com.google.android.gms.internal.ads.zzaph;
import com.google.android.gms.internal.ads.zzaqm;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzw
{
    public final zzaqm zzwy;
    public final zzaph zzwz;
    public final zzaiu zzxa;
    public final zzhx zzxb;
    
    private zzw(final zzaqm zzwy, final zzaph zzwz, final zzaiu zzxa, final zzhx zzxb) {
        this.zzwy = zzwy;
        this.zzwz = zzwz;
        this.zzxa = zzxa;
        this.zzxb = zzxb;
    }
    
    public static zzw zzc(final Context context) {
        return new zzw(new zzaqb(), new zzapo(), new zzaio(new zzaip()), new zzhx(context));
    }
}
