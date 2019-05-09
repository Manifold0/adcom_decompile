// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import java.util.concurrent.Future;
import org.json.JSONException;
import com.google.android.gms.internal.ads.zzakb;
import java.util.Map;
import com.google.android.gms.common.util.VisibleForTesting;
import org.json.JSONObject;
import com.google.android.gms.internal.ads.zzaoj;
import java.util.HashMap;
import com.google.android.gms.internal.ads.zzadh;

@zzadh
public final class zzaa implements zzv<Object>
{
    @VisibleForTesting
    private final HashMap<String, zzaoj<JSONObject>> zzbmv;
    
    public zzaa() {
        this.zzbmv = new HashMap<String, zzaoj<JSONObject>>();
    }
    
    @Override
    public final void zza(Object o, Map<String, String> zzaoj) {
        o = ((Map<Object, Object>)zzaoj).get("request_id");
        final String s = ((Map<Object, String>)zzaoj).get("fetched_ad");
        zzakb.zzck("Received ad from the cache.");
        zzaoj = this.zzbmv.get(o);
        if (zzaoj == null) {
            zzakb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        try {
            zzaoj.set(new JSONObject(s));
        }
        catch (JSONException ex) {
            zzakb.zzb("Failed constructing JSON object from value passed from javascript", (Throwable)ex);
            zzaoj.set(null);
        }
        finally {
            this.zzbmv.remove(o);
        }
    }
    
    public final Future<JSONObject> zzas(final String s) {
        final zzaoj<JSONObject> zzaoj = new zzaoj<JSONObject>();
        this.zzbmv.put(s, zzaoj);
        return zzaoj;
    }
    
    public final void zzat(final String s) {
        final zzaoj<JSONObject> zzaoj = this.zzbmv.get(s);
        if (zzaoj == null) {
            zzakb.e("Could not find the ad request for the corresponding ad response.");
            return;
        }
        if (!zzaoj.isDone()) {
            zzaoj.cancel(true);
        }
        this.zzbmv.remove(s);
    }
}
