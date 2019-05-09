// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONException;
import org.json.JSONArray;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Collections;
import java.util.ArrayList;
import org.json.JSONObject;
import android.support.annotation.Nullable;
import java.util.List;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzwx
{
    public final String zzbrr;
    public final String zzbrs;
    public final List<String> zzbrt;
    public final String zzbru;
    public final String zzbrv;
    public final List<String> zzbrw;
    public final List<String> zzbrx;
    public final List<String> zzbry;
    public final List<String> zzbrz;
    public final List<String> zzbsa;
    public final String zzbsb;
    public final List<String> zzbsc;
    public final List<String> zzbsd;
    public final List<String> zzbse;
    @Nullable
    public final String zzbsf;
    @Nullable
    public final String zzbsg;
    public final String zzbsh;
    @Nullable
    public final List<String> zzbsi;
    public final String zzbsj;
    @Nullable
    private final String zzbsk;
    public final long zzbsl;
    
    public zzwx(final String zzbrr, final String s, final List<String> zzbrt, final String s2, final String s3, final List<String> zzbrw, final List<String> zzbrx, final List<String> zzbry, final List<String> zzbrz, final String zzbsb, final String s4, final List<String> zzbsc, final List<String> zzbsd, final List<String> zzbse, final String s5, final String s6, final String s7, final List<String> list, final String s8, final List<String> zzbsa, final String s9, final long n) {
        this.zzbrr = zzbrr;
        this.zzbrs = null;
        this.zzbrt = zzbrt;
        this.zzbru = null;
        this.zzbrv = null;
        this.zzbrw = zzbrw;
        this.zzbrx = zzbrx;
        this.zzbry = zzbry;
        this.zzbrz = zzbrz;
        this.zzbsb = zzbsb;
        this.zzbsc = zzbsc;
        this.zzbsd = zzbsd;
        this.zzbse = zzbse;
        this.zzbsf = null;
        this.zzbsg = null;
        this.zzbsh = null;
        this.zzbsi = null;
        this.zzbsj = null;
        this.zzbsa = zzbsa;
        this.zzbsk = null;
        this.zzbsl = -1L;
    }
    
    public zzwx(final JSONObject jsonObject) throws JSONException {
        this.zzbrs = jsonObject.optString("id");
        final JSONArray jsonArray = jsonObject.getJSONArray("adapters");
        final ArrayList list = new ArrayList<String>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); ++i) {
            list.add(jsonArray.getString(i));
        }
        this.zzbrt = Collections.unmodifiableList((List<? extends String>)list);
        this.zzbru = jsonObject.optString("allocation_id", (String)null);
        zzbv.zzfd();
        this.zzbrw = zzxg.zza(jsonObject, "clickurl");
        zzbv.zzfd();
        this.zzbrx = zzxg.zza(jsonObject, "imp_urls");
        zzbv.zzfd();
        this.zzbry = zzxg.zza(jsonObject, "downloaded_imp_urls");
        zzbv.zzfd();
        this.zzbsa = zzxg.zza(jsonObject, "fill_urls");
        zzbv.zzfd();
        this.zzbsc = zzxg.zza(jsonObject, "video_start_urls");
        zzbv.zzfd();
        this.zzbse = zzxg.zza(jsonObject, "video_complete_urls");
        zzbv.zzfd();
        List<String> zzbsd = zzxg.zza(jsonObject, "video_reward_urls");
        if (!(boolean)zzkb.zzik().zzd(zznk.zzaxv)) {
            zzbsd = this.zzbse;
        }
        this.zzbsd = zzbsd;
        final JSONObject optJSONObject = jsonObject.optJSONObject("ad");
        List<String> zza;
        if (optJSONObject != null) {
            zzbv.zzfd();
            zza = zzxg.zza(optJSONObject, "manual_impression_urls");
        }
        else {
            zza = null;
        }
        this.zzbrz = zza;
        String string;
        if (optJSONObject != null) {
            string = optJSONObject.toString();
        }
        else {
            string = null;
        }
        this.zzbrr = string;
        final JSONObject optJSONObject2 = jsonObject.optJSONObject("data");
        String string2;
        if (optJSONObject2 != null) {
            string2 = optJSONObject2.toString();
        }
        else {
            string2 = null;
        }
        this.zzbsb = string2;
        String optString;
        if (optJSONObject2 != null) {
            optString = optJSONObject2.optString("class_name");
        }
        else {
            optString = null;
        }
        this.zzbrv = optString;
        this.zzbsf = jsonObject.optString("html_template", (String)null);
        this.zzbsg = jsonObject.optString("ad_base_url", (String)null);
        final JSONObject optJSONObject3 = jsonObject.optJSONObject("assets");
        String string3;
        if (optJSONObject3 != null) {
            string3 = optJSONObject3.toString();
        }
        else {
            string3 = null;
        }
        this.zzbsh = string3;
        zzbv.zzfd();
        this.zzbsi = zzxg.zza(jsonObject, "template_ids");
        final JSONObject optJSONObject4 = jsonObject.optJSONObject("ad_loader_options");
        String string4;
        if (optJSONObject4 != null) {
            string4 = optJSONObject4.toString();
        }
        else {
            string4 = null;
        }
        this.zzbsj = string4;
        this.zzbsk = jsonObject.optString("response_type", (String)null);
        this.zzbsl = jsonObject.optLong("ad_network_timeout_millis", -1L);
    }
    
    public final boolean zzmf() {
        return "banner".equalsIgnoreCase(this.zzbsk);
    }
    
    public final boolean zzmg() {
        return "native".equalsIgnoreCase(this.zzbsk);
    }
}
