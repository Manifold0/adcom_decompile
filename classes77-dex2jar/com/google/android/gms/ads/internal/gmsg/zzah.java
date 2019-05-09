// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaig;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import java.util.Map;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzah implements zzv<Object>
{
    private final zzai zzbng;
    
    public zzah(final zzai zzbng) {
        this.zzbng = zzbng;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final String s = map.get("action");
        if (!"grant".equals(s)) {
            if ("video_start".equals(s)) {
                this.zzbng.zzdk();
                return;
            }
            if ("video_complete".equals(s) && (boolean)zzkb.zzik().zzd(zznk.zzaxv) && (boolean)zzkb.zzik().zzd(zznk.zzaxv)) {
                this.zzbng.zzdl();
            }
            return;
        }
        while (true) {
            while (true) {
                try {
                    final int int1 = Integer.parseInt(map.get("amount"));
                    final String s2 = map.get("type");
                    if (!TextUtils.isEmpty((CharSequence)s2)) {
                        final zzaig zzaig = new zzaig(s2, int1);
                        this.zzbng.zzb(zzaig);
                        return;
                    }
                }
                catch (NumberFormatException ex) {
                    zzakb.zzc("Unable to parse reward amount.", (Throwable)ex);
                }
                final zzaig zzaig = null;
                continue;
            }
        }
    }
}
