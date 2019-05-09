// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import javax.annotation.Nullable;
import org.json.JSONObject;
import com.google.android.gms.ads.internal.gmsg.zzag;
import com.google.android.gms.ads.internal.gmsg.zzf;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwq<I, O> implements zzwf<I, O>
{
    private final zzvf zzbrh;
    private final zzwh<O> zzbri;
    private final zzwi<I> zzbrj;
    private final String zzbrk;
    
    zzwq(final zzvf zzbrh, final String zzbrk, final zzwi<I> zzbrj, final zzwh<O> zzbri) {
        this.zzbrh = zzbrh;
        this.zzbrk = zzbrk;
        this.zzbrj = zzbrj;
        this.zzbri = zzbri;
    }
    
    private final void zza(final zzvs zzvs, final zzwb zzwb, final I n, final zzaoj<O> zzaoj) {
        try {
            zzbv.zzek();
            final String zzrh = zzakk.zzrh();
            zzf.zzbmc.zza(zzrh, new zzwt(this, zzvs, zzaoj));
            final JSONObject jsonObject = new JSONObject();
            jsonObject.put("id", (Object)zzrh);
            jsonObject.put("args", (Object)this.zzbrj.zzg(n));
            zzwb.zzb(this.zzbrk, jsonObject);
        }
        catch (Exception exception) {
            try {
                zzaoj.setException(exception);
                zzakb.zzb("Unable to invokeJavaScript", (Throwable)exception);
            }
            finally {
                zzvs.release();
            }
        }
    }
    
    @Override
    public final zzanz<O> zzc(@Nullable final I n) throws Exception {
        return this.zzf(n);
    }
    
    @Override
    public final zzanz<O> zzf(final I n) {
        final zzaoj<O> zzaoj = new zzaoj<O>();
        final zzvs zzb = this.zzbrh.zzb((zzci)null);
        zzb.zza(new zzwr(this, zzb, n, zzaoj), new zzws(this, zzaoj, zzb));
        return zzaoj;
    }
}
