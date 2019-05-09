package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.internal.gmsg.zzag;
import javax.annotation.Nullable;
import org.json.JSONException;
import org.json.JSONObject;

final class zzwt implements zzag {
    private final /* synthetic */ zzwq zzbro;
    private final zzvs zzbrp;
    private final zzaoj zzbrq;

    public zzwt(zzwq zzwq, zzvs zzvs, zzaoj zzaoj) {
        this.zzbro = zzwq;
        this.zzbrp = zzvs;
        this.zzbrq = zzaoj;
    }

    public final void zzau(@Nullable String str) {
        if (str == null) {
            try {
                this.zzbrq.setException(new zzwe());
            } catch (IllegalStateException e) {
                this.zzbrp.release();
                return;
            } catch (Throwable th) {
                this.zzbrp.release();
            }
        } else {
            this.zzbrq.setException(new zzwe(str));
        }
        this.zzbrp.release();
    }

    public final void zzd(JSONObject jSONObject) {
        try {
            this.zzbrq.set(this.zzbro.zzbri.zze(jSONObject));
        } catch (IllegalStateException e) {
        } catch (JSONException e2) {
            this.zzbrq.set(e2);
        } finally {
            this.zzbrp.release();
        }
    }
}
