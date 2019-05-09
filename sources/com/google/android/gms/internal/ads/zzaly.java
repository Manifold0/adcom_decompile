package com.google.android.gms.internal.ads;

import java.util.Map;

final class zzaly extends zzav {
    private final /* synthetic */ byte[] zzctk;
    private final /* synthetic */ Map zzctl;
    private final /* synthetic */ zzamy zzctm;

    zzaly(zzalt zzalt, int i, String str, zzz zzz, zzy zzy, byte[] bArr, Map map, zzamy zzamy) {
        this.zzctk = bArr;
        this.zzctl = map;
        this.zzctm = zzamy;
        super(i, str, zzz, zzy);
    }

    public final Map<String, String> getHeaders() throws zza {
        return this.zzctl == null ? super.getHeaders() : this.zzctl;
    }

    protected final /* synthetic */ void zza(Object obj) {
        zzh((String) obj);
    }

    public final byte[] zzg() throws zza {
        return this.zzctk == null ? super.zzg() : this.zzctk;
    }

    protected final void zzh(String str) {
        this.zzctm.zzdg(str);
        super.zzh(str);
    }
}
