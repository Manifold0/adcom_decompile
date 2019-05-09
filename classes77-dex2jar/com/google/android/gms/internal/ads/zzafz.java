// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Map;
import org.json.JSONObject;
import java.util.List;

@zzadh
public final class zzafz
{
    private final int errorCode;
    private final String type;
    private String url;
    private final String zzcdi;
    private final List<String> zzcjd;
    private final String zzcje;
    private final String zzcjf;
    private final boolean zzcjg;
    private final String zzcjh;
    private final boolean zzcji;
    private final JSONObject zzcjj;
    
    public zzafz(final int errorCode, final Map<String, String> map) {
        this.url = map.get("url");
        this.zzcje = map.get("base_uri");
        this.zzcjf = map.get("post_parameters");
        this.zzcjg = parseBoolean(map.get("drt_include"));
        this.zzcdi = map.get("request_id");
        this.type = map.get("type");
        this.zzcjd = zzbz(map.get("errors"));
        this.errorCode = errorCode;
        this.zzcjh = map.get("fetched_ad");
        this.zzcji = parseBoolean(map.get("render_test_ad_label"));
        this.zzcjj = new JSONObject();
    }
    
    public zzafz(JSONObject optJSONObject) {
        int errorCode = 1;
        this.url = ((JSONObject)optJSONObject).optString("url");
        this.zzcje = ((JSONObject)optJSONObject).optString("base_uri");
        this.zzcjf = ((JSONObject)optJSONObject).optString("post_parameters");
        this.zzcjg = parseBoolean(((JSONObject)optJSONObject).optString("drt_include"));
        this.zzcdi = ((JSONObject)optJSONObject).optString("request_id");
        this.type = ((JSONObject)optJSONObject).optString("type");
        this.zzcjd = zzbz(((JSONObject)optJSONObject).optString("errors"));
        if (((JSONObject)optJSONObject).optInt("valid", 0) == 1) {
            errorCode = -2;
        }
        this.errorCode = errorCode;
        this.zzcjh = ((JSONObject)optJSONObject).optString("fetched_ad");
        this.zzcji = ((JSONObject)optJSONObject).optBoolean("render_test_ad_label");
        optJSONObject = ((JSONObject)optJSONObject).optJSONObject("preprocessor_flags");
        if (optJSONObject == null) {
            optJSONObject = new JSONObject();
        }
        this.zzcjj = (JSONObject)optJSONObject;
    }
    
    private static boolean parseBoolean(final String s) {
        return s != null && (s.equals("1") || s.equals("true"));
    }
    
    private static List<String> zzbz(final String s) {
        if (s == null) {
            return null;
        }
        return Arrays.asList(s.split(","));
    }
    
    public final int getErrorCode() {
        return this.errorCode;
    }
    
    public final String getType() {
        return this.type;
    }
    
    public final String getUrl() {
        return this.url;
    }
    
    public final void setUrl(final String url) {
        this.url = url;
    }
    
    public final List<String> zzoh() {
        return this.zzcjd;
    }
    
    public final String zzoi() {
        return this.zzcje;
    }
    
    public final String zzoj() {
        return this.zzcjf;
    }
    
    public final boolean zzok() {
        return this.zzcjg;
    }
    
    public final String zzol() {
        return this.zzcdi;
    }
    
    public final String zzom() {
        return this.zzcjh;
    }
    
    public final boolean zzon() {
        return this.zzcji;
    }
}
