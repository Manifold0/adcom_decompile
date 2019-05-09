package com.google.android.gms.internal.ads;

import android.support.annotation.Nullable;
import org.json.JSONObject;

@zzadh
public final class zzaji {
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

    public zzaji(zzaef zzaef, zzaej zzaej, zzwy zzwy, zzjn zzjn, int i, long j, long j2, JSONObject jSONObject, zzhs zzhs, @Nullable Boolean bool) {
        this.zzcgs = zzaef;
        this.zzcos = zzaej;
        this.zzcod = zzwy;
        this.zzacv = zzjn;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = jSONObject;
        this.zzcoq = zzhs;
        if (bool != null) {
            this.zzcor = bool.booleanValue();
        } else if (zzamm.zzo(zzaef.zzccv)) {
            this.zzcor = true;
        } else {
            this.zzcor = false;
        }
    }

    public zzaji(zzaef zzaef, zzaej zzaej, zzwy zzwy, zzjn zzjn, int i, long j, long j2, JSONObject jSONObject, zzhx zzhx) {
        this.zzcgs = zzaef;
        this.zzcos = zzaej;
        this.zzcod = null;
        this.zzacv = null;
        this.errorCode = i;
        this.zzcoh = j;
        this.zzcoi = j2;
        this.zzcob = null;
        this.zzcoq = new zzhs(zzhx);
        this.zzcor = false;
    }
}
