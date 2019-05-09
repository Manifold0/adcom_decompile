package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.zzbv;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final /* synthetic */ class zzup {
    public static void zza(zzuo zzuo, String str, String str2) {
        zzuo.zzbe(new StringBuilder((String.valueOf(str).length() + 3) + String.valueOf(str2).length()).append(str).append("(").append(str2).append(");").toString());
    }

    public static void zza(zzuo zzuo, String str, Map map) {
        try {
            zzuo.zza(str, zzbv.zzek().zzn(map));
        } catch (JSONException e) {
            zzakb.zzdk("Could not convert parameters to JSON.");
        }
    }

    public static void zza(zzuo zzuo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        zzuo.zzf(str, jSONObject.toString());
    }

    public static void zzb(zzuo zzuo, String str, JSONObject jSONObject) {
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        String jSONObject2 = jSONObject.toString();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("(window.AFMA_ReceiveMessage || function() {})('");
        stringBuilder.append(str);
        stringBuilder.append("'");
        stringBuilder.append(",");
        stringBuilder.append(jSONObject2);
        stringBuilder.append(");");
        String str2 = "Dispatching AFMA event: ";
        jSONObject2 = String.valueOf(stringBuilder.toString());
        zzakb.zzck(jSONObject2.length() != 0 ? str2.concat(jSONObject2) : new String(str2));
        zzuo.zzbe(stringBuilder.toString());
    }
}
