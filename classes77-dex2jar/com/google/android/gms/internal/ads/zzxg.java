// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.net.Uri;
import android.support.annotation.Nullable;
import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import org.json.JSONException;
import org.json.JSONArray;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;
import android.text.TextUtils;

@zzadh
public final class zzxg
{
    private static String zza(final String s, final String s2, final String s3) {
        String s4 = s3;
        if (TextUtils.isEmpty((CharSequence)s3)) {
            s4 = "";
        }
        return s.replaceAll(s2, s4);
    }
    
    public static List<String> zza(final JSONObject jsonObject, final String s) throws JSONException {
        final JSONArray optJSONArray = jsonObject.optJSONArray(s);
        if (optJSONArray != null) {
            final ArrayList<String> list = new ArrayList<String>(optJSONArray.length());
            for (int i = 0; i < optJSONArray.length(); ++i) {
                list.add(optJSONArray.getString(i));
            }
            return (List<String>)Collections.unmodifiableList((List<?>)list);
        }
        return null;
    }
    
    public static void zza(final Context context, final String s, final zzajh zzajh, final String s2, final boolean b, final List<String> list) {
        if (list != null && !list.isEmpty()) {
            String s3;
            if (b) {
                s3 = "1";
            }
            else {
                s3 = "0";
            }
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String s4 = zza(zza(zza(zza(zza(zza(zza(iterator.next(), "@gw_adlocid@", s2), "@gw_adnetrefresh@", s3), "@gw_qdata@", zzajh.zzcod.zzbst), "@gw_sdkver@", s), "@gw_sessid@", zzkb.zzih()), "@gw_seqnum@", zzajh.zzccy), "@gw_adnetstatus@", zzajh.zzcoe);
                if (zzajh.zzbtw != null) {
                    s4 = zza(zza(s4, "@gw_adnetid@", zzajh.zzbtw.zzbrs), "@gw_allocid@", zzajh.zzbtw.zzbru);
                }
                final String zzb = zzajb.zzb(s4, context);
                zzbv.zzek();
                zzakk.zzd(context, s, zzb);
            }
        }
    }
    
    public static void zza(final Context context, final String s, final List<String> list, String s2, @Nullable final zzaig zzaig) {
        if (list != null && !list.isEmpty()) {
            String s3 = s2;
            if (!TextUtils.isEmpty((CharSequence)s2)) {
                s3 = s2;
                if (zzamy.isEnabled()) {
                    s3 = "fakeUserForAdDebugLog";
                }
            }
            final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                String s4;
                s2 = (s4 = zza(zza(iterator.next(), "@gw_rwd_userid@", Uri.encode(s3)), "@gw_tmstmp@", Long.toString(currentTimeMillis)));
                if (zzaig != null) {
                    s4 = zza(zza(s2, "@gw_rwd_itm@", Uri.encode(zzaig.type)), "@gw_rwd_amt@", Integer.toString(zzaig.zzcmk));
                }
                zzbv.zzek();
                zzakk.zzd(context, s, s4);
            }
        }
    }
    
    public static boolean zza(final String s, final int[] array) {
        if (!TextUtils.isEmpty((CharSequence)s) && array.length == 2) {
            final String[] split = s.split("x");
            if (split.length == 2) {
                try {
                    array[0] = Integer.parseInt(split[0]);
                    array[1] = Integer.parseInt(split[1]);
                    return true;
                }
                catch (NumberFormatException ex) {
                    return false;
                }
            }
        }
        return false;
    }
}
