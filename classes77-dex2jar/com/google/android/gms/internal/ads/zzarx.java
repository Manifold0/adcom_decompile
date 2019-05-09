// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.regex.Matcher;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import java.util.regex.Pattern;

@zzadh
public final class zzarx
{
    private static final Pattern zzdeo;
    private static final Pattern zzdep;
    
    static {
        zzdeo = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*\\s*<!DOCTYPE(\\s)+html(|(\\s)+[^>]*)>", 2);
        zzdep = Pattern.compile("^\\uFEFF?\\s*(\\s*<!--([^-]|(?!-->))*-->)*?\\s*<!DOCTYPE[^>]*>", 2);
    }
    
    public static String zzb(@NonNull final String s, @Nullable final String... array) {
        final int n = 0;
        int i = 0;
        if (array.length == 0) {
            return s;
        }
        final StringBuilder sb = new StringBuilder();
        final Matcher matcher = zzarx.zzdeo.matcher(s);
        if (matcher.find()) {
            final int end = matcher.end();
            sb.append(s.substring(0, end));
            while (i < array.length) {
                final String s2 = array[i];
                if (s2 != null) {
                    sb.append(s2);
                }
                ++i;
            }
            sb.append(s.substring(end));
        }
        else {
            if (!zzarx.zzdep.matcher(s).find()) {
                for (int length = array.length, j = n; j < length; ++j) {
                    final String s3 = array[j];
                    if (s3 != null) {
                        sb.append(s3);
                    }
                }
            }
            sb.append(s);
        }
        return sb.toString();
    }
    
    public static String zzvp() {
        final String s = (String)zzkb.zzik().zzd(zznk.zzawf);
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("version", (Object)s);
            jsonObject.put("sdk", (Object)"Google Mobile Ads");
            jsonObject.put("sdkVersion", (Object)"12.4.51-000");
            final StringBuilder sb = new StringBuilder();
            sb.append("<script>");
            sb.append("Object.defineProperty(window,'MRAID_ENV',{get:function(){return ").append(jsonObject.toString()).append("}});");
            sb.append("</script>");
            return sb.toString();
        }
        catch (JSONException ex) {
            zzakb.zzc("Unable to build MRAID_ENV", (Throwable)ex);
            return null;
        }
    }
}
