package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zznx;
import java.util.Map;

@zzadh
public final class zze implements zzv<zzaqw> {
    public final /* synthetic */ void zza(Object obj, Map map) {
        zzaqw zzaqw = (zzaqw) obj;
        String str = (String) map.get("action");
        String str2;
        if ("tick".equals(str)) {
            str = (String) map.get("label");
            str2 = (String) map.get("start_label");
            String str3 = (String) map.get("timestamp");
            if (TextUtils.isEmpty(str)) {
                zzakb.zzdk("No label given for CSI tick.");
            } else if (TextUtils.isEmpty(str3)) {
                zzakb.zzdk("No timestamp given for CSI tick.");
            } else {
                try {
                    long parseLong = (Long.parseLong(str3) - zzbv.zzer().currentTimeMillis()) + zzbv.zzer().elapsedRealtime();
                    if (TextUtils.isEmpty(str2)) {
                        str2 = "native:view_load";
                    }
                    zzaqw.zztp().zza(str, str2, parseLong);
                } catch (Throwable e) {
                    zzakb.zzc("Malformed timestamp for CSI tick.", e);
                }
            }
        } else if ("experiment".equals(str)) {
            str = (String) map.get("value");
            if (TextUtils.isEmpty(str)) {
                zzakb.zzdk("No value given for CSI experiment.");
                return;
            }
            zznx zzji = zzaqw.zztp().zzji();
            if (zzji == null) {
                zzakb.zzdk("No ticker for WebView, dropping experiment ID.");
            } else {
                zzji.zze("e", str);
            }
        } else if ("extra".equals(str)) {
            str = (String) map.get("name");
            str2 = (String) map.get("value");
            if (TextUtils.isEmpty(str2)) {
                zzakb.zzdk("No value given for CSI extra.");
            } else if (TextUtils.isEmpty(str)) {
                zzakb.zzdk("No name given for CSI extra.");
            } else {
                zznx zzji2 = zzaqw.zztp().zzji();
                if (zzji2 == null) {
                    zzakb.zzdk("No ticker for WebView, dropping extra parameter.");
                } else {
                    zzji2.zze(str, str2);
                }
            }
        }
    }
}
