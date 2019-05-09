// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import java.util.Map;

@zzadh
public final class zzamd
{
    private static final zzamf<Map<String, ?>> zzctr;
    
    static {
        zzctr = (zzamf)new zzame();
    }
    
    @NonNull
    public static List<String> zza(@Nullable final JSONArray jsonArray, @Nullable final List<String> list) throws JSONException {
        final ArrayList<String> list2 = new ArrayList<String>();
        if (jsonArray == null) {
            return list2;
        }
        for (int i = 0; i < jsonArray.length(); ++i) {
            list2.add(jsonArray.getString(i));
        }
        return list2;
    }
}
