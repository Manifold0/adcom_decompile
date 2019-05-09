package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzv;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;

@zzadh
public final class zzaql implements zzv<zzapw> {
    private static Integer zze(Map<String, String> map, String str) {
        if (!map.containsKey(str)) {
            return null;
        }
        try {
            return Integer.valueOf(Integer.parseInt((String) map.get(str)));
        } catch (NumberFormatException e) {
            String str2 = (String) map.get(str);
            zzakb.zzdk(new StringBuilder((String.valueOf(str).length() + 39) + String.valueOf(str2).length()).append("Precache invalid numeric parameter '").append(str).append("': ").append(str2).toString());
            return null;
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        zzapw zzapw = (zzapw) obj;
        zzbv.zzff();
        if (!map.containsKey("abort")) {
            String str = (String) map.get("src");
            if (str != null) {
                if (zzaqg.zzc(zzapw) != null) {
                    zzakb.zzdk("Precache task is already running.");
                    return;
                } else if (zzapw.zzbi() == null) {
                    zzakb.zzdk("Precache requires a dependency provider.");
                    return;
                } else {
                    zzapv zzapv = new zzapv((String) map.get("flags"));
                    Integer zze = zze(map, "player");
                    if (zze == null) {
                        zze = Integer.valueOf(0);
                    }
                    new zzaqe(zzapw, zzapw.zzbi().zzwy.zza(zzapw, zze.intValue(), null, zzapv), str).zznt();
                }
            } else if (zzaqg.zzc(zzapw) == null) {
                zzakb.zzdk("Precache must specify a source.");
                return;
            }
            Integer zze2 = zze(map, "minBufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "maxBufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "bufferForPlaybackMs");
            if (zze2 != null) {
                zze2.intValue();
            }
            zze2 = zze(map, "bufferForPlaybackAfterRebufferMs");
            if (zze2 != null) {
                zze2.intValue();
            }
        } else if (!zzaqg.zzb(zzapw)) {
            zzakb.zzdk("Precache abort but no precache task running.");
        }
    }
}
