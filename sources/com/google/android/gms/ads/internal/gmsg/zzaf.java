package com.google.android.gms.ads.internal.gmsg;

import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzakb;
import com.kongregate.android.internal.sdk.C0525o;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.ParametersAreNonnullByDefault;
import javax.annotation.concurrent.GuardedBy;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
@ParametersAreNonnullByDefault
public final class zzaf implements zzv<Object> {
    private final Object mLock = new Object();
    @GuardedBy("mLock")
    private final Map<String, zzag> zzbnf = new HashMap();

    public final void zza(Object obj, Map<String, String> map) {
        String str = (String) map.get("id");
        String str2 = (String) map.get("fail");
        String str3 = (String) map.get(C0525o.f629o);
        String str4 = (String) map.get("fail_stack");
        String str5 = (String) map.get("result");
        if (TextUtils.isEmpty(str4)) {
            Object obj2 = "Unknown Fail Reason.";
        } else {
            String str6 = str3;
        }
        if (TextUtils.isEmpty(str4)) {
            Object obj3 = "";
        } else {
            String str7 = "\n";
            str3 = String.valueOf(str4);
            str4 = str3.length() != 0 ? str7.concat(str3) : new String(str7);
        }
        synchronized (this.mLock) {
            zzag zzag = (zzag) this.zzbnf.remove(str);
            if (zzag == null) {
                str2 = "Received result for unexpected method invocation: ";
                str = String.valueOf(str);
                zzakb.zzdk(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (!TextUtils.isEmpty(str2)) {
                str2 = String.valueOf(obj2);
                str = String.valueOf(obj3);
                zzag.zzau(str.length() != 0 ? str2.concat(str) : new String(str2));
            } else if (str5 == null) {
                zzag.zzd(null);
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str5);
                    if (zzakb.zzqp()) {
                        str4 = "Result GMSG: ";
                        str = String.valueOf(jSONObject.toString(2));
                        zzakb.m3915v(str.length() != 0 ? str4.concat(str) : new String(str4));
                    }
                    zzag.zzd(jSONObject);
                } catch (JSONException e) {
                    zzag.zzau(e.getMessage());
                }
            }
        }
    }

    public final void zza(String str, zzag zzag) {
        synchronized (this.mLock) {
            this.zzbnf.put(str, zzag);
        }
    }
}
