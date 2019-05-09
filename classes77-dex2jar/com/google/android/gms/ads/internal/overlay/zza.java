// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.overlay;

import com.google.android.gms.internal.ads.zzkb;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zznk;
import android.content.ActivityNotFoundException;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.ads.internal.zzbv;
import com.google.android.gms.internal.ads.zzakb;
import android.content.Intent;
import android.content.Context;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zza
{
    private static boolean zza(final Context context, final Intent intent, final zzt zzt) {
        try {
            final String value = String.valueOf(intent.toURI());
            String concat;
            if (value.length() != 0) {
                concat = "Launching an intent: ".concat(value);
            }
            else {
                concat = new String("Launching an intent: ");
            }
            zzakb.v(concat);
            zzbv.zzek();
            zzakk.zza(context, intent);
            if (zzt != null) {
                zzt.zzbl();
            }
        }
        catch (ActivityNotFoundException ex) {
            zzakb.zzdk(ex.getMessage());
            return false;
        }
        return true;
    }
    
    public static boolean zza(final Context context, final zzc zzc, final zzt zzt) {
        if (zzc == null) {
            zzakb.zzdk("No intent data for launcher overlay.");
            return false;
        }
        zznk.initialize(context);
        if (zzc.intent != null) {
            return zza(context, zzc.intent, zzt);
        }
        final Intent intent = new Intent();
        if (TextUtils.isEmpty((CharSequence)zzc.url)) {
            zzakb.zzdk("Open GMSG did not contain a URL.");
            return false;
        }
        if (!TextUtils.isEmpty((CharSequence)zzc.mimeType)) {
            intent.setDataAndType(Uri.parse(zzc.url), zzc.mimeType);
        }
        else {
            intent.setData(Uri.parse(zzc.url));
        }
        intent.setAction("android.intent.action.VIEW");
        if (!TextUtils.isEmpty((CharSequence)zzc.packageName)) {
            intent.setPackage(zzc.packageName);
        }
        if (!TextUtils.isEmpty((CharSequence)zzc.zzbxj)) {
            final String[] split = zzc.zzbxj.split("/", 2);
            if (split.length < 2) {
                final String value = String.valueOf(zzc.zzbxj);
                String concat;
                if (value.length() != 0) {
                    concat = "Could not parse component name from open GMSG: ".concat(value);
                }
                else {
                    concat = new String("Could not parse component name from open GMSG: ");
                }
                zzakb.zzdk(concat);
                return false;
            }
            intent.setClassName(split[0], split[1]);
        }
        final String zzbxk = zzc.zzbxk;
        Label_0237: {
            if (TextUtils.isEmpty((CharSequence)zzbxk)) {
                break Label_0237;
            }
        Label_0274_Outer:
            while (true) {
                while (true) {
                    while (true) {
                        try {
                            final int int1 = Integer.parseInt(zzbxk);
                            intent.addFlags(int1);
                            if (zzkb.zzik().zzd(zznk.zzbea)) {
                                intent.addFlags(268435456);
                                intent.putExtra("android.support.customtabs.extra.user_opt_out", true);
                                return zza(context, intent, zzt);
                            }
                        }
                        catch (NumberFormatException ex) {
                            zzakb.zzdk("Could not parse intent flags.");
                            final int int1 = 0;
                            continue Label_0274_Outer;
                        }
                        break;
                    }
                    if (zzkb.zzik().zzd(zznk.zzbdz)) {
                        zzbv.zzek();
                        zzakk.zzb(context, intent);
                        continue;
                    }
                    continue;
                }
            }
        }
    }
}
