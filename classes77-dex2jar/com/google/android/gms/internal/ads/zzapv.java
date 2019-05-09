// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzapv
{
    private final boolean zzczu;
    private final int zzczv;
    private final int zzczw;
    private final int zzczx;
    private final String zzczy;
    private final int zzczz;
    private final int zzdaa;
    private final int zzdab;
    private final boolean zzdac;
    
    public zzapv(final String s) {
        final JSONObject jsonObject = null;
        while (true) {
            Label_0143: {
                if (s == null) {
                    break Label_0143;
                }
                try {
                    final JSONObject jsonObject2 = new JSONObject(s);
                    this.zzczu = zza(jsonObject2, "aggressive_media_codec_release", (zzna<Boolean>)zznk.zzavl);
                    this.zzczv = zzb(jsonObject2, "byte_buffer_precache_limit", (zzna<Integer>)zznk.zzauv);
                    this.zzczw = zzb(jsonObject2, "exo_cache_buffer_size", (zzna<Integer>)zznk.zzauz);
                    this.zzczx = zzb(jsonObject2, "exo_connect_timeout_millis", (zzna<Integer>)zznk.zzaur);
                    this.zzczy = zzc(jsonObject2, "exo_player_version", (zzna<String>)zznk.zzauq);
                    this.zzczz = zzb(jsonObject2, "exo_read_timeout_millis", (zzna<Integer>)zznk.zzaus);
                    this.zzdaa = zzb(jsonObject2, "load_check_interval_bytes", (zzna<Integer>)zznk.zzaut);
                    this.zzdab = zzb(jsonObject2, "player_precache_limit", (zzna<Integer>)zznk.zzauu);
                    this.zzdac = zza(jsonObject2, "use_cache_data_source", (zzna<Boolean>)zznk.zzbdr);
                    return;
                }
                catch (JSONException ex) {
                    final JSONObject jsonObject2 = jsonObject;
                    continue;
                }
            }
            final JSONObject jsonObject2 = null;
            continue;
        }
    }
    
    private static boolean zza(final JSONObject jsonObject, final String s, final zzna<Boolean> zzna) {
        if (jsonObject != null) {
            try {
                return jsonObject.getBoolean(s);
            }
            catch (JSONException ex) {}
        }
        return (boolean)zzkb.zzik().zzd((zzna)zzna);
    }
    
    private static int zzb(final JSONObject jsonObject, final String s, final zzna<Integer> zzna) {
        if (jsonObject != null) {
            try {
                return jsonObject.getInt(s);
            }
            catch (JSONException ex) {}
        }
        return (int)zzkb.zzik().zzd((zzna)zzna);
    }
    
    private static String zzc(final JSONObject jsonObject, final String s, final zzna<String> zzna) {
        if (jsonObject != null) {
            try {
                return jsonObject.getString(s);
            }
            catch (JSONException ex) {}
        }
        return (String)zzkb.zzik().zzd((zzna)zzna);
    }
}
