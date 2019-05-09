// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import org.json.JSONArray;
import org.json.JSONException;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;

@zzadh
public final class zzajl
{
    private final long zzcpe;
    private final List<String> zzcpf;
    private final List<String> zzcpg;
    private final Map<String, zzwy> zzcph;
    private String zzcpi;
    private String zzcpj;
    private boolean zzcpk;
    
    public zzajl(final String zzcpj, final long zzcpe) {
        final int n = 0;
        this.zzcpf = new ArrayList<String>();
        this.zzcpg = new ArrayList<String>();
        this.zzcph = new HashMap<String, zzwy>();
        this.zzcpk = false;
        this.zzcpj = zzcpj;
        this.zzcpe = zzcpe;
        if (!TextUtils.isEmpty((CharSequence)zzcpj)) {
            JSONObject jsonObject;
            while (true) {
                while (true) {
                    int n2 = 0;
                    Label_0328: {
                        JSONObject jsonObject2;
                        String optString;
                        String optString2;
                        try {
                            jsonObject = new JSONObject(zzcpj);
                            if (jsonObject.optInt("status", -1) != 1) {
                                this.zzcpk = false;
                                zzakb.zzdk("App settings could not be fetched successfully.");
                                return;
                            }
                            this.zzcpk = true;
                            this.zzcpi = jsonObject.optString("app_id");
                            final JSONArray optJSONArray = jsonObject.optJSONArray("ad_unit_id_settings");
                            if (optJSONArray == null) {
                                break;
                            }
                            n2 = 0;
                            if (n2 >= optJSONArray.length()) {
                                break;
                            }
                            jsonObject2 = optJSONArray.getJSONObject(n2);
                            optString = jsonObject2.optString("format");
                            optString2 = jsonObject2.optString("ad_unit_id");
                            if (TextUtils.isEmpty((CharSequence)optString)) {
                                break Label_0328;
                            }
                            if (TextUtils.isEmpty((CharSequence)optString2)) {
                                break Label_0328;
                            }
                            if ("interstitial".equalsIgnoreCase(optString)) {
                                this.zzcpg.add(optString2);
                                break Label_0328;
                            }
                        }
                        catch (JSONException ex) {
                            zzakb.zzc("Exception occurred while processing app setting json", (Throwable)ex);
                            zzbv.zzeo().zza((Throwable)ex, "AppSettings.parseAppSettingsJson");
                            return;
                        }
                        if ("rewarded".equalsIgnoreCase(optString)) {
                            final JSONObject optJSONObject = jsonObject2.optJSONObject("mediation_config");
                            if (optJSONObject != null) {
                                this.zzcph.put(optString2, new zzwy(optJSONObject));
                            }
                        }
                    }
                    ++n2;
                    continue;
                }
            }
            final JSONArray optJSONArray2 = jsonObject.optJSONArray("persistable_banner_ad_unit_ids");
            if (optJSONArray2 != null) {
                for (int i = n; i < optJSONArray2.length(); ++i) {
                    this.zzcpf.add(optJSONArray2.optString(i));
                }
            }
        }
    }
    
    public final long zzps() {
        return this.zzcpe;
    }
    
    public final boolean zzpt() {
        return this.zzcpk;
    }
    
    public final String zzpu() {
        return this.zzcpj;
    }
    
    public final String zzpv() {
        return this.zzcpi;
    }
    
    public final Map<String, zzwy> zzpw() {
        return this.zzcph;
    }
}
