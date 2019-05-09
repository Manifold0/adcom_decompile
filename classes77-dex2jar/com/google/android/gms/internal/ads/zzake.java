// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Set;
import android.content.SharedPreferences$Editor;
import android.content.SharedPreferences;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

final class zzake extends zzakg
{
    private final /* synthetic */ Context zzcrg;
    private final /* synthetic */ zzakd zzcrh;
    
    zzake(final zzakd zzcrh, final Context zzcrg) {
        this.zzcrh = zzcrh;
        this.zzcrg = zzcrg;
        super(null);
    }
    
    @Override
    public final void zzdn() {
        final SharedPreferences sharedPreferences = this.zzcrg.getSharedPreferences("admob", 0);
        final SharedPreferences$Editor edit = sharedPreferences.edit();
        synchronized (this.zzcrh.mLock) {
            this.zzcrh.zzatw = sharedPreferences;
            this.zzcrh.zzcqw = edit;
            this.zzcrh.zzcqx = zzakd.zzb(this.zzcrh);
            this.zzcrh.zzcik = this.zzcrh.zzatw.getBoolean("use_https", this.zzcrh.zzcik);
            this.zzcrh.zzcil = this.zzcrh.zzatw.getBoolean("content_url_opted_out", this.zzcrh.zzcil);
            this.zzcrh.zzcqy = this.zzcrh.zzatw.getString("content_url_hashes", this.zzcrh.zzcqy);
            this.zzcrh.zzcit = this.zzcrh.zzatw.getBoolean("auto_collect_location", this.zzcrh.zzcit);
            this.zzcrh.zzcim = this.zzcrh.zzatw.getBoolean("content_vertical_opted_out", this.zzcrh.zzcim);
            this.zzcrh.zzcqz = this.zzcrh.zzatw.getString("content_vertical_hashes", this.zzcrh.zzcqz);
            this.zzcrh.zzcrd = this.zzcrh.zzatw.getInt("version_code", this.zzcrh.zzcrd);
            this.zzcrh.zzcpj = this.zzcrh.zzatw.getString("app_settings_json", this.zzcrh.zzcpj);
            this.zzcrh.zzcra = this.zzcrh.zzatw.getLong("app_settings_last_update_ms", this.zzcrh.zzcra);
            this.zzcrh.zzcrb = this.zzcrh.zzatw.getLong("app_last_background_time_ms", this.zzcrh.zzcrb);
            this.zzcrh.zzcqg = this.zzcrh.zzatw.getInt("request_in_session_count", this.zzcrh.zzcqg);
            this.zzcrh.zzcrc = this.zzcrh.zzatw.getLong("first_ad_req_time_ms", this.zzcrh.zzcrc);
            this.zzcrh.zzcre = (Set<String>)this.zzcrh.zzatw.getStringSet("never_pool_slots", this.zzcrh.zzcre);
            try {
                this.zzcrh.zzcrf = new JSONObject(this.zzcrh.zzatw.getString("native_advanced_settings", "{}"));
                this.zzcrh.zze(this.zzcrh.zzqs());
            }
            catch (JSONException ex) {
                zzakb.zzc("Could not convert native advanced settings to json object", (Throwable)ex);
            }
        }
    }
}
