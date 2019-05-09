package com.google.android.gms.internal.ads;

import android.content.Context;
import android.graphics.Color;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.text.TextUtils;
import android.view.MotionEvent;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.gmsg.zzv;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TJAdUnitConstants.String;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

@zzadh
public final class zzaqc implements zzv<zzapw> {
    private boolean zzdau;

    private static int zza(Context context, Map<String, String> map, String str, int i) {
        String str2 = (String) map.get(str);
        if (str2 != null) {
            try {
                zzkb.zzif();
                i = zzamu.zza(context, Integer.parseInt(str2));
            } catch (NumberFormatException e) {
                zzakb.zzdk(new StringBuilder((String.valueOf(str).length() + 34) + String.valueOf(str2).length()).append("Could not parse ").append(str).append(" in a video GMSG: ").append(str2).toString());
            }
        }
        return i;
    }

    private static void zza(zzapi zzapi, Map<String, String> map) {
        String str = (String) map.get("minBufferMs");
        String str2 = (String) map.get("maxBufferMs");
        String str3 = (String) map.get("bufferForPlaybackMs");
        String str4 = (String) map.get("bufferForPlaybackAfterRebufferMs");
        if (str != null) {
            try {
                Integer.parseInt(str);
            } catch (NumberFormatException e) {
                zzakb.zzdk(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", new Object[]{str, str2}));
                return;
            }
        }
        if (str2 != null) {
            Integer.parseInt(str2);
        }
        if (str3 != null) {
            Integer.parseInt(str3);
        }
        if (str4 != null) {
            Integer.parseInt(str4);
        }
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        int i = 0;
        zzapw zzapw = (zzapw) obj;
        String str = (String) map.get("action");
        if (str == null) {
            zzakb.zzdk("Action missing from video GMSG.");
            return;
        }
        if (zzakb.isLoggable(3)) {
            JSONObject jSONObject = new JSONObject(map);
            jSONObject.remove("google.afma.Notify_dt");
            String jSONObject2 = jSONObject.toString();
            zzakb.zzck(new StringBuilder((String.valueOf(str).length() + 13) + String.valueOf(jSONObject2).length()).append("Video GMSG: ").append(str).append(" ").append(jSONObject2).toString());
        }
        if ("background".equals(str)) {
            jSONObject2 = (String) map.get(ParametersKeys.COLOR);
            if (TextUtils.isEmpty(jSONObject2)) {
                zzakb.zzdk("Color parameter missing from color video GMSG.");
                return;
            }
            try {
                zzapw.setBackgroundColor(Color.parseColor(jSONObject2));
            } catch (IllegalArgumentException e) {
                zzakb.zzdk("Invalid color parameter in video GMSG.");
            }
        } else if ("decoderProps".equals(str)) {
            jSONObject2 = (String) map.get("mimeTypes");
            if (jSONObject2 == null) {
                zzakb.zzdk("No MIME types specified for decoder properties inspection.");
                zzapi.zza(zzapw, "missingMimeTypes");
            } else if (VERSION.SDK_INT < 16) {
                zzakb.zzdk("Video decoder properties available on API versions >= 16.");
                zzapi.zza(zzapw, "deficientApiVersion");
            } else {
                Map hashMap = new HashMap();
                String[] split = jSONObject2.split(",");
                r2 = split.length;
                while (i < r2) {
                    String str2 = split[i];
                    hashMap.put(str2, zzams.zzdd(str2.trim()));
                    i++;
                }
                zzapi.zza(zzapw, hashMap);
            }
        } else {
            zzapn zztl = zzapw.zztl();
            if (zztl == null) {
                zzakb.zzdk("Could not get underlay container for a video GMSG.");
                return;
            }
            boolean equals = "new".equals(str);
            boolean equals2 = ParametersKeys.POSITION.equals(str);
            int min;
            if (equals || equals2) {
                Context context = zzapw.getContext();
                int zza = zza(context, map, "x", 0);
                r2 = zza(context, map, "y", 0);
                int zza2 = zza(context, map, "w", -1);
                int zza3 = zza(context, map, "h", -1);
                if (((Boolean) zzkb.zzik().zzd(zznk.zzbca)).booleanValue()) {
                    min = Math.min(zza2, zzapw.zzts() - zza);
                    zza3 = Math.min(zza3, zzapw.zztr() - r2);
                } else {
                    min = zza2;
                }
                try {
                    zza2 = Integer.parseInt((String) map.get("player"));
                } catch (NumberFormatException e2) {
                    zza2 = 0;
                }
                boolean parseBoolean = Boolean.parseBoolean((String) map.get("spherical"));
                if (equals && zztl.zzth() == null) {
                    zztl.zza(zza, r2, min, zza3, zza2, parseBoolean, new zzapv((String) map.get("flags")));
                    zzapi zzth = zztl.zzth();
                    if (zzth != null) {
                        zza(zzth, map);
                        return;
                    }
                    return;
                }
                zztl.zze(zza, r2, min, zza3);
                return;
            }
            zzapi zzth2 = zztl.zzth();
            if (zzth2 == null) {
                zzapi.zza(zzapw);
            } else if ("click".equals(str)) {
                r0 = zzapw.getContext();
                r2 = zza(r0, map, "x", 0);
                min = zza(r0, map, "y", 0);
                long uptimeMillis = SystemClock.uptimeMillis();
                MotionEvent obtain = MotionEvent.obtain(uptimeMillis, uptimeMillis, 0, (float) r2, (float) min, 0);
                zzth2.zzf(obtain);
                obtain.recycle();
            } else if (String.VIDEO_CURRENT_TIME.equals(str)) {
                jSONObject2 = (String) map.get(LocationConst.TIME);
                if (jSONObject2 == null) {
                    zzakb.zzdk("Time parameter missing from currentTime video GMSG.");
                    return;
                }
                try {
                    zzth2.seekTo((int) (Float.parseFloat(jSONObject2) * 1000.0f));
                } catch (NumberFormatException e3) {
                    str = "Could not parse time parameter from currentTime video GMSG: ";
                    jSONObject2 = String.valueOf(jSONObject2);
                    zzakb.zzdk(jSONObject2.length() != 0 ? str.concat(jSONObject2) : new String(str));
                }
            } else if (MessengerShareContentUtility.SHARE_BUTTON_HIDE.equals(str)) {
                zzth2.setVisibility(4);
            } else if ("load".equals(str)) {
                zzth2.zzta();
            } else if ("loadControl".equals(str)) {
                zza(zzth2, map);
            } else if ("muted".equals(str)) {
                if (Boolean.parseBoolean((String) map.get("muted"))) {
                    zzth2.zztb();
                } else {
                    zzth2.zztc();
                }
            } else if ("pause".equals(str)) {
                zzth2.pause();
            } else if ("play".equals(str)) {
                zzth2.play();
            } else if ("show".equals(str)) {
                zzth2.setVisibility(0);
            } else if ("src".equals(str)) {
                zzth2.zzdn((String) map.get("src"));
            } else if ("touchMove".equals(str)) {
                r0 = zzapw.getContext();
                zzth2.zza((float) zza(r0, map, "dx", 0), (float) zza(r0, map, "dy", 0));
                if (!this.zzdau) {
                    zzapw.zznp();
                    this.zzdau = true;
                }
            } else if ("volume".equals(str)) {
                jSONObject2 = (String) map.get("volume");
                if (jSONObject2 == null) {
                    zzakb.zzdk("Level parameter missing from volume video GMSG.");
                    return;
                }
                try {
                    zzth2.setVolume(Float.parseFloat(jSONObject2));
                } catch (NumberFormatException e4) {
                    str = "Could not parse volume parameter from volume video GMSG: ";
                    jSONObject2 = String.valueOf(jSONObject2);
                    zzakb.zzdk(jSONObject2.length() != 0 ? str.concat(jSONObject2) : new String(str));
                }
            } else if ("watermark".equals(str)) {
                zzth2.zztd();
            } else {
                String str3 = "Unknown video action: ";
                jSONObject2 = String.valueOf(str);
                zzakb.zzdk(jSONObject2.length() != 0 ? str3.concat(jSONObject2) : new String(str3));
            }
        }
    }
}
