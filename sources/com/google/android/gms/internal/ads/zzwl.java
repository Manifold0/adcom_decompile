package com.google.android.gms.internal.ads;

import java.io.ByteArrayInputStream;
import org.json.JSONObject;

final /* synthetic */ class zzwl implements zzwh {
    static final zzwh zzbre = new zzwl();

    private zzwl() {
    }

    public final Object zze(JSONObject jSONObject) {
        return new ByteArrayInputStream(jSONObject.toString().getBytes(zzwk.UTF_8));
    }
}
