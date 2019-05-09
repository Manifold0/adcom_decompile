// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import java.util.Map;
import java.util.Iterator;
import org.json.JSONException;
import com.google.android.gms.internal.ads.zzakb;
import org.json.JSONObject;
import android.os.Bundle;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zza implements zzv<Object>
{
    private final zzb zzbll;
    
    public zza(final zzb zzbll) {
        this.zzbll = zzbll;
    }
    
    private static Bundle zzar(String s) {
        if (s == null) {
            return null;
        }
        Bundle bundle = null;
        while (true) {
            while (true) {
                Object value = null;
                Label_0092: {
                    try {
                        final JSONObject jsonObject = new JSONObject(s);
                        final Iterator keys = jsonObject.keys();
                        bundle = new Bundle();
                        while (keys.hasNext()) {
                            s = keys.next();
                            value = jsonObject.get(s);
                            if (value != null) {
                                if (!(value instanceof Boolean)) {
                                    break Label_0092;
                                }
                                bundle.putBoolean(s, (boolean)value);
                            }
                        }
                        break;
                    }
                    catch (JSONException ex) {
                        zzakb.zzb("Failed to convert ad metadata to JSON.", (Throwable)ex);
                        return null;
                    }
                }
                if (value instanceof Double) {
                    bundle.putDouble(s, (double)value);
                    continue;
                }
                if (value instanceof Integer) {
                    bundle.putInt(s, (int)value);
                    continue;
                }
                if (value instanceof Long) {
                    bundle.putLong(s, (long)value);
                    continue;
                }
                if (value instanceof String) {
                    bundle.putString(s, (String)value);
                    continue;
                }
                s = String.valueOf(s);
                if (s.length() != 0) {
                    s = "Unsupported type for key:".concat(s);
                }
                else {
                    s = new String("Unsupported type for key:");
                }
                zzakb.zzdk(s);
                continue;
            }
        }
        return bundle;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        if (this.zzbll == null) {
            return;
        }
        String s = map.get("name");
        if (s == null) {
            zzakb.zzdj("Ad metadata with no name parameter.");
            s = "";
        }
        final Bundle zzar = zzar(map.get("info"));
        if (zzar == null) {
            zzakb.e("Failed to convert ad metadata to Bundle.");
            return;
        }
        this.zzbll.zza(s, zzar);
    }
}
