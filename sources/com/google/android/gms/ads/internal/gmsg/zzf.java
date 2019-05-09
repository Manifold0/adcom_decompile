package com.google.android.gms.ads.internal.gmsg;

import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.internal.ads.zzadh;
import com.google.android.gms.internal.ads.zzajb;
import com.google.android.gms.internal.ads.zzakb;
import com.google.android.gms.internal.ads.zzami;
import com.google.android.gms.internal.ads.zzapw;
import com.google.android.gms.internal.ads.zzaqc;
import com.google.android.gms.internal.ads.zzaqd;
import com.google.android.gms.internal.ads.zzaqw;
import com.google.android.gms.internal.ads.zzarr;
import com.google.android.gms.internal.ads.zzarz;
import com.google.android.gms.internal.ads.zzasa;
import com.google.android.gms.internal.ads.zzasb;
import com.google.android.gms.internal.ads.zzci;
import com.google.android.gms.internal.ads.zzcj;
import com.google.android.gms.internal.ads.zzue;
import com.vungle.warren.ui.VungleWebViewActivity;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzf {
    private static final zzv<Object> zzbln = new zzl();
    public static final zzv<zzaqw> zzblo = zzg.zzbmg;
    public static final zzv<zzaqw> zzblp = zzh.zzbmg;
    public static final zzv<zzaqw> zzblq = zzi.zzbmg;
    public static final zzv<zzaqw> zzblr = new zzn();
    public static final zzv<zzaqw> zzbls = new zzo();
    public static final zzv<zzaqw> zzblt = zzj.zzbmg;
    public static final zzv<Object> zzblu = new zzp();
    public static final zzv<zzaqw> zzblv = new zzq();
    public static final zzv<zzaqw> zzblw = zzk.zzbmg;
    public static final zzv<zzaqw> zzblx = new zzr();
    public static final zzv<zzaqw> zzbly = new zzs();
    public static final zzv<zzapw> zzblz = new zzaqc();
    public static final zzv<zzapw> zzbma = new zzaqd();
    public static final zzv<zzaqw> zzbmb = new zze();
    public static final zzaf zzbmc = new zzaf();
    public static final zzv<zzaqw> zzbmd = new zzt();
    public static final zzv<zzaqw> zzbme = new zzu();
    public static final zzv<zzaqw> zzbmf = new zzm();

    static final /* synthetic */ void zza(zzarr zzarr, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzakb.zzdk("URL missing from httpTrack GMSG.");
        } else {
            new zzami(zzarr.getContext(), ((zzasa) zzarr).zztq().zzcw, str).zzqo();
        }
    }

    static final /* synthetic */ void zza(zzarz zzarz, Map map) {
        String str = (String) map.get("ty");
        String str2 = (String) map.get("td");
        try {
            int parseInt = Integer.parseInt((String) map.get("tx"));
            int parseInt2 = Integer.parseInt(str);
            int parseInt3 = Integer.parseInt(str2);
            zzci zzui = zzarz.zzui();
            if (zzui != null) {
                zzui.zzaa().zza(parseInt, parseInt2, parseInt3);
            }
        } catch (NumberFormatException e) {
            zzakb.zzdk("Could not parse touch parameters from gmsg.");
        }
    }

    static final /* synthetic */ void zza(zzue zzue, Map map) {
        String str = (String) map.get("u");
        if (str == null) {
            zzakb.zzdk("URL missing from click GMSG.");
            return;
        }
        Uri parse = Uri.parse(str);
        try {
            zzci zzui = ((zzarz) zzue).zzui();
            Uri zza = (zzui == null || !zzui.zzb(parse)) ? parse : zzui.zza(parse, ((zzarr) zzue).getContext(), ((zzasb) zzue).getView(), ((zzarr) zzue).zzto());
            parse = zza;
        } catch (zzcj e) {
            String str2 = "Unable to append parameter to URL: ";
            str = String.valueOf(str);
            zzakb.zzdk(str.length() != 0 ? str2.concat(str) : new String(str2));
        }
        new zzami(((zzarr) zzue).getContext(), ((zzasa) zzue).zztq().zzcw, zzajb.zzb(parse, ((zzarr) zzue).getContext()).toString()).zzqo();
    }

    static final /* synthetic */ void zzb(zzarr zzarr, Map map) {
        PackageManager packageManager = zzarr.getContext().getPackageManager();
        try {
            try {
                JSONArray jSONArray = new JSONObject((String) map.get("data")).getJSONArray("intents");
                JSONObject jSONObject = new JSONObject();
                for (int i = 0; i < jSONArray.length(); i++) {
                    try {
                        Intent parseUri;
                        String[] split;
                        JSONObject jSONObject2 = jSONArray.getJSONObject(i);
                        String optString = jSONObject2.optString("id");
                        Object optString2 = jSONObject2.optString("u");
                        Object optString3 = jSONObject2.optString("i");
                        Object optString4 = jSONObject2.optString("m");
                        Object optString5 = jSONObject2.optString("p");
                        Object optString6 = jSONObject2.optString("c");
                        jSONObject2.optString("f");
                        jSONObject2.optString("e");
                        Object optString7 = jSONObject2.optString(VungleWebViewActivity.INTENT_URL);
                        if (!TextUtils.isEmpty(optString7)) {
                            try {
                                parseUri = Intent.parseUri(optString7, 0);
                            } catch (Throwable e) {
                                String str = "Error parsing the url: ";
                                String valueOf = String.valueOf(optString7);
                                zzakb.zzb(valueOf.length() != 0 ? str.concat(valueOf) : new String(str), e);
                            }
                            if (parseUri == null) {
                                parseUri = new Intent();
                                if (!TextUtils.isEmpty(optString2)) {
                                    parseUri.setData(Uri.parse(optString2));
                                }
                                if (!TextUtils.isEmpty(optString3)) {
                                    parseUri.setAction(optString3);
                                }
                                if (!TextUtils.isEmpty(optString4)) {
                                    parseUri.setType(optString4);
                                }
                                if (!TextUtils.isEmpty(optString5)) {
                                    parseUri.setPackage(optString5);
                                }
                                if (!TextUtils.isEmpty(optString6)) {
                                    split = optString6.split("/", 2);
                                    if (split.length == 2) {
                                        parseUri.setComponent(new ComponentName(split[0], split[1]));
                                    }
                                }
                            }
                            jSONObject.put(optString, packageManager.resolveActivity(parseUri, 65536) == null);
                        }
                        parseUri = null;
                        if (parseUri == null) {
                            parseUri = new Intent();
                            if (TextUtils.isEmpty(optString2)) {
                                parseUri.setData(Uri.parse(optString2));
                            }
                            if (TextUtils.isEmpty(optString3)) {
                                parseUri.setAction(optString3);
                            }
                            if (TextUtils.isEmpty(optString4)) {
                                parseUri.setType(optString4);
                            }
                            if (TextUtils.isEmpty(optString5)) {
                                parseUri.setPackage(optString5);
                            }
                            if (TextUtils.isEmpty(optString6)) {
                                split = optString6.split("/", 2);
                                if (split.length == 2) {
                                    parseUri.setComponent(new ComponentName(split[0], split[1]));
                                }
                            }
                        }
                        if (packageManager.resolveActivity(parseUri, 65536) == null) {
                        }
                        try {
                            jSONObject.put(optString, packageManager.resolveActivity(parseUri, 65536) == null);
                        } catch (Throwable e2) {
                            zzakb.zzb("Error constructing openable urls response.", e2);
                        }
                    } catch (Throwable e22) {
                        zzakb.zzb("Error parsing the intent data.", e22);
                    }
                }
                ((zzue) zzarr).zza("openableIntents", jSONObject);
            } catch (JSONException e3) {
                ((zzue) zzarr).zza("openableIntents", new JSONObject());
            }
        } catch (JSONException e4) {
            ((zzue) zzarr).zza("openableIntents", new JSONObject());
        }
    }

    static final /* synthetic */ void zzc(zzarr zzarr, Map map) {
        String str = (String) map.get("urls");
        if (TextUtils.isEmpty(str)) {
            zzakb.zzdk("URLs missing in canOpenURLs GMSG.");
            return;
        }
        String[] split = str.split(",");
        Map hashMap = new HashMap();
        PackageManager packageManager = zzarr.getContext().getPackageManager();
        for (String str2 : split) {
            String[] split2 = str2.split(";", 2);
            hashMap.put(str2, Boolean.valueOf(packageManager.resolveActivity(new Intent(split2.length > 1 ? split2[1].trim() : "android.intent.action.VIEW", Uri.parse(split2[0].trim())), 65536) != null));
        }
        ((zzue) zzarr).zza("openableURLs", hashMap);
    }
}
