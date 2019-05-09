// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.util.VisibleForTesting;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import android.net.Uri;

@zzadh
public final class zzajb
{
    public static Uri zzb(final Uri uri, final Context context) {
        Uri zzb = uri;
        if (zzbv.zzfh().zzx(context)) {
            zzb = uri;
            if (TextUtils.isEmpty((CharSequence)uri.getQueryParameter("fbs_aeid"))) {
                final String zzab = zzbv.zzfh().zzab(context);
                zzb = zzb(uri.toString(), "fbs_aeid", zzab);
                zzbv.zzfh().zze(context, zzab);
            }
        }
        return zzb;
    }
    
    @VisibleForTesting
    private static Uri zzb(final String s, final String s2, final String s3) {
        int n;
        if ((n = s.indexOf("&adurl")) == -1) {
            n = s.indexOf("?adurl");
        }
        if (n != -1) {
            return Uri.parse(s.substring(0, n + 1) + s2 + "=" + s3 + "&" + s.substring(n + 1));
        }
        return Uri.parse(s).buildUpon().appendQueryParameter(s2, s3).build();
    }
    
    public static String zzb(final String s, final Context context) {
        if (zzbv.zzfh().zzs(context) && !TextUtils.isEmpty((CharSequence)s)) {
            final String zzab = zzbv.zzfh().zzab(context);
            if (zzab != null) {
                if (zzkb.zzik().zzd(zznk.zzaxr)) {
                    final String s2 = (String)zzkb.zzik().zzd(zznk.zzaxs);
                    if (s.contains(s2)) {
                        if (zzbv.zzek().zzcx(s)) {
                            zzbv.zzfh().zze(context, zzab);
                            return s.replace(s2, zzab);
                        }
                        if (zzbv.zzek().zzcy(s)) {
                            zzbv.zzfh().zzf(context, zzab);
                            return s.replace(s2, zzab);
                        }
                    }
                }
                else if (!s.contains("fbs_aeid")) {
                    if (zzbv.zzek().zzcx(s)) {
                        zzbv.zzfh().zze(context, zzab);
                        return zzb(s, "fbs_aeid", zzab).toString();
                    }
                    if (zzbv.zzek().zzcy(s)) {
                        zzbv.zzfh().zzf(context, zzab);
                        return zzb(s, "fbs_aeid", zzab).toString();
                    }
                }
            }
        }
        return s;
    }
    
    public static String zzc(final String s, final Context context) {
        if (zzbv.zzfh().zzs(context) && !TextUtils.isEmpty((CharSequence)s)) {
            final String zzab = zzbv.zzfh().zzab(context);
            if (zzab != null && zzbv.zzek().zzcy(s)) {
                if (zzkb.zzik().zzd(zznk.zzaxr)) {
                    final String s2 = (String)zzkb.zzik().zzd(zznk.zzaxs);
                    if (s.contains(s2)) {
                        return s.replace(s2, zzab);
                    }
                }
                else if (!s.contains("fbs_aeid")) {
                    return zzb(s, "fbs_aeid", zzab).toString();
                }
            }
        }
        return s;
    }
}
