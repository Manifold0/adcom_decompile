// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.Map;
import com.google.android.gms.ads.internal.gmsg.zzv;

final class zzpk implements zzv<Object>
{
    private final /* synthetic */ zzacm zzbji;
    private final /* synthetic */ zzpf zzbjj;
    
    zzpk(final zzpf zzbjj, final zzacm zzbji) {
        this.zzbjj = zzbjj;
        this.zzbji = zzbji;
    }
    
    @Override
    public final void zza(final Object o, final Map<String, String> map) {
        final JSONObject jsonObject = new JSONObject();
        try {
            for (final String s : map.keySet()) {
                jsonObject.put(s, (Object)map.get(s));
            }
        }
        catch (JSONException ex) {
            zzakb.zzb("Unable to dispatch sendMessageToNativeJs event", (Throwable)ex);
            return;
        }
        jsonObject.put("id", (Object)this.zzbjj.zzbjh);
        this.zzbji.zza("sendMessageToNativeJs", jsonObject);
    }
}
