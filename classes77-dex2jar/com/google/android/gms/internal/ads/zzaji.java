// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONObject;
import android.support.annotation.Nullable;

@zzadh
public final class zzaji
{
    public final int errorCode;
    @Nullable
    public final zzjn zzacv;
    public final zzaef zzcgs;
    @Nullable
    public final JSONObject zzcob;
    public final zzwy zzcod;
    public final long zzcoh;
    public final long zzcoi;
    public final zzhs zzcoq;
    public final boolean zzcor;
    public final zzaej zzcos;
    
    public zzaji(final zzaef zzcgs, final zzaej zzcos, final zzwy zzcod, final zzjn zzacv, final int errorCode, final long zzcoh, final long zzcoi, final JSONObject zzcob, final zzhs zzcoq, @Nullable final Boolean b) {
        this.zzcgs = zzcgs;
        this.zzcos = zzcos;
        this.zzcod = zzcod;
        this.zzacv = zzacv;
        this.errorCode = errorCode;
        this.zzcoh = zzcoh;
        this.zzcoi = zzcoi;
        this.zzcob = zzcob;
        this.zzcoq = zzcoq;
        if (b != null) {
            this.zzcor = b;
            return;
        }
        if (zzamm.zzo(zzcgs.zzccv)) {
            this.zzcor = true;
            return;
        }
        this.zzcor = false;
    }
    
    public zzaji(final zzaef zzcgs, final zzaej zzcos, final zzwy zzwy, final zzjn zzjn, final int errorCode, final long zzcoh, final long zzcoi, final JSONObject jsonObject, final zzhx zzhx) {
        this.zzcgs = zzcgs;
        this.zzcos = zzcos;
        this.zzcod = null;
        this.zzacv = null;
        this.errorCode = errorCode;
        this.zzcoh = zzcoh;
        this.zzcoi = zzcoi;
        this.zzcob = null;
        this.zzcoq = new zzhs(zzhx);
        this.zzcor = false;
    }
}
