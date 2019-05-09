package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwq<I, O> implements zzwf<I, O> {
    private final zzvf zzbrh;
    private final zzwh<O> zzbri;
    private final zzwi<I> zzbrj;
    private final String zzbrk;

    zzwq(zzvf zzvf, String str, zzwi<I> zzwi, zzwh<O> zzwh) {
        this.zzbrh = zzvf;
        this.zzbrk = str;
        this.zzbrj = zzwi;
        this.zzbri = zzwh;
    }

    private final void zza(zzvs zzvs, zzwb zzwb, I i, zzaoj<O> zzaoj) {
        try {
            zzbv.zzek();
            String zzrh = zzakk.zzrh();
            zzf.zzbmc.zza(zzrh, new zzwt(this, zzvs, zzaoj));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", zzrh);
            jSONObject.put("args", this.zzbrj.zzg(i));
            zzwb.zzb(this.zzbrk, jSONObject);
        } catch (Throwable e) {
            zzaoj.setException(e);
            zzakb.zzb("Unable to invokeJavaScript", e);
        } finally {
            zzvs.release();
        }
    }

    public final zzanz<O> zzc(@Nullable I i) throws Exception {
        return zzf(i);
    }

    public final zzanz<O> zzf(I i) {
        zzanz zzaoj = new zzaoj();
        zzaop zzb = this.zzbrh.zzb(null);
        zzb.zza(new zzwr(this, zzb, i, zzaoj), new zzws(this, zzaoj, zzb));
        return zzaoj;
    }
}
