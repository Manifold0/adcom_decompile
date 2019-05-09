package com.google.android.gms.internal.ads;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.gmsg.zzv;
import java.util.Map;
import net.hockeyapp.android.UpdateActivity;
import org.json.JSONObject;

final class zzacw implements zzv<zzaqw> {
    private final /* synthetic */ zzaqw zzcbq;
    private final /* synthetic */ zzaoj zzcbr;
    private final /* synthetic */ zzacq zzcbs;

    zzacw(zzacq zzacq, zzaqw zzaqw, zzaoj zzaoj) {
        this.zzcbs = zzacq;
        this.zzcbq = zzaqw;
        this.zzcbr = zzaoj;
    }

    public final /* synthetic */ void zza(Object obj, Map map) {
        try {
            JSONObject jSONObject;
            boolean z;
            String str = (String) map.get("success");
            String str2 = (String) map.get("failure");
            if (TextUtils.isEmpty(str2)) {
                jSONObject = new JSONObject(str);
                z = true;
            } else {
                z = false;
                jSONObject = new JSONObject(str2);
            }
            if (this.zzcbs.zzaae.equals(jSONObject.optString("ads_id", ""))) {
                this.zzcbq.zzb("/nativeAdPreProcess", this);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.put("success", z);
                jSONObject2.put(UpdateActivity.EXTRA_JSON, jSONObject);
                this.zzcbr.set(jSONObject2);
            }
        } catch (Throwable th) {
            zzakb.zzb("Error while preprocessing json.", th);
            this.zzcbr.setException(th);
        }
    }
}
