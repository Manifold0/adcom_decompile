package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzaig;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzkb;
import com.google.android.gms.internal.ads.zznk;
import java.util.Map;

@zzadh
public final class zzah implements zzv<Object> {
    private final zzai zzbng;

    public zzah(zzai zzai) {
        this.zzbng = zzai;
    }

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("action");
        if ("grant".equals(str)) {
            zzaig zzaig;
            try {
                int parseInt = Integer.parseInt((String) map.get("amount"));
                str = (String) map.get("type");
                if (!TextUtils.isEmpty(str)) {
                    zzaig = new zzaig(str, parseInt);
                    this.zzbng.zzb(zzaig);
                }
            } catch (Throwable e) {
                zzakb.zzc("Unable to parse reward amount.", e);
            }
            zzaig = null;
            this.zzbng.zzb(zzaig);
        } else if ("video_start".equals(str)) {
            this.zzbng.zzdk();
        } else if ("video_complete".equals(str)) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                    this.zzbng.zzdl();
                }
            }
        }
    }
}
