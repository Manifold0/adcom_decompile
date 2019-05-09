// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import android.os.SystemClock;
import java.util.List;
import java.util.HashMap;
import android.os.Build$VERSION;
import android.graphics.Color;
import android.text.TextUtils;
import org.json.JSONObject;
import java.util.Map;
import android.content.Context;
import com.google.android.gms.ads.internal.gmsg.zzv;

@zzadh
public final class zzaqc implements zzv<zzapw>
{
    private boolean zzdau;
    
    private static int zza(final Context context, Map<String, String> s, final String s2, final int n) {
        s = ((Map<String, String>)s).get(s2);
        int zza = n;
        if (s == null) {
            return zza;
        }
        try {
            zzkb.zzif();
            zza = zzamu.zza(context, Integer.parseInt(s));
            return zza;
        }
        catch (NumberFormatException ex) {
            zzakb.zzdk(new StringBuilder(String.valueOf(s2).length() + 34 + String.valueOf(s).length()).append("Could not parse ").append(s2).append(" in a video GMSG: ").append(s).toString());
            return n;
        }
    }
    
    private static void zza(zzapi zzapi, final Map<String, String> map) {
        zzapi = (zzapi)map.get("minBufferMs");
        final String s = map.get("maxBufferMs");
        final String s2 = map.get("bufferForPlaybackMs");
        final String s3 = map.get("bufferForPlaybackAfterRebufferMs");
        Label_0057: {
            if (zzapi == null) {
                break Label_0057;
            }
            try {
                Integer.parseInt((String)zzapi);
                if (s != null) {
                    Integer.parseInt(s);
                }
                if (s2 != null) {
                    Integer.parseInt(s2);
                }
                if (s3 != null) {
                    Integer.parseInt(s3);
                }
            }
            catch (NumberFormatException ex) {
                zzakb.zzdk(String.format("Could not parse buffer parameters in loadControl video GMSG: (%s, %s)", zzapi, s));
            }
        }
    }
}
