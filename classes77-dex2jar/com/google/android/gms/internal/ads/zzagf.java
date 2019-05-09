// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import android.content.SharedPreferences;
import android.content.Context;

@zzadh
public final class zzagf extends zzagh
{
    private final Object mLock;
    private final Context zzaeo;
    @Nullable
    private SharedPreferences zzckn;
    private final zzwf<JSONObject, JSONObject> zzcko;
    
    public zzagf(final Context context, final zzwf<JSONObject, JSONObject> zzcko) {
        this.mLock = new Object();
        this.zzaeo = context.getApplicationContext();
        this.zzcko = zzcko;
    }
    
    @Override
    public final zzanz<Void> zzop() {
        Object o = this.mLock;
        synchronized (o) {
            if (this.zzckn == null) {
                this.zzckn = this.zzaeo.getSharedPreferences("google_ads_flags_meta", 0);
            }
            final long long1 = this.zzckn.getLong("js_last_update", 0L);
            final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            o = zznk.zzbbl;
            if (currentTimeMillis - long1 < (long)zzkb.zzik().zzd((zzna)o)) {
                return (zzanz<Void>)zzano.zzi((Object)null);
            }
        }
        final JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("js", (Object)zzang.zzsl().zzcw);
            jsonObject.put("mf", zzkb.zzik().zzd(zznk.zzbbm));
            jsonObject.put("cl", (Object)"191880412");
            jsonObject.put("rapid_rc", (Object)"dev");
            jsonObject.put("rapid_rollup", (Object)"HEAD");
            jsonObject.put("dynamite_version", 279);
            return zzano.zza(this.zzcko.zzf(jsonObject), (zzank<JSONObject, Void>)new zzagg(this), zzaoe.zzcvz);
        }
        catch (JSONException ex) {
            zzakb.zzb("Unable to populate SDK Core Constants parameters.", (Throwable)ex);
            return (zzanz<Void>)zzano.zzi((Object)null);
        }
    }
}
