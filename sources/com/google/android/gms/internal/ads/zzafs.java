package com.google.android.gms.internal.ads;

import android.content.Context;
import android.location.Location;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Debug.MemoryInfo;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.adjust.sdk.Constants;
import com.facebook.share.internal.MessengerShareContentUtility;
import com.google.android.gms.ads.internal.zzbv;
import com.ironsource.sdk.constants.LocationConst;
import com.tapjoy.TapjoyConstants;
import com.unity.purchasing.googleplay.Consts;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@zzadh
public final class zzafs {
    private static final SimpleDateFormat zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);

    public static zzaej zza(Context context, zzaef zzaef, String str) {
        String optString;
        try {
            String str2;
            JSONObject jSONObject = new JSONObject(str);
            String optString2 = jSONObject.optString("ad_base_url", null);
            Object optString3 = jSONObject.optString("ad_url", null);
            String optString4 = jSONObject.optString("ad_size", null);
            String optString5 = jSONObject.optString("ad_slot_size", optString4);
            boolean z = (zzaef == null || zzaef.zzcdb == 0) ? false : true;
            CharSequence optString6 = jSONObject.optString("ad_json", null);
            if (optString6 == null) {
                optString6 = jSONObject.optString("ad_html", null);
            }
            if (optString6 == null) {
                optString6 = jSONObject.optString("body", null);
            }
            if (optString6 == null) {
                if (jSONObject.has("ads")) {
                    optString6 = jSONObject.toString();
                }
            }
            long j = -1;
            String optString7 = jSONObject.optString("debug_dialog", null);
            String optString8 = jSONObject.optString("debug_signals", null);
            long j2 = jSONObject.has("interstitial_timeout") ? (long) (jSONObject.getDouble("interstitial_timeout") * 1000.0d) : -1;
            optString = jSONObject.optString("orientation", null);
            int i = -1;
            if ("portrait".equals(optString)) {
                i = zzbv.zzem().zzrm();
            } else if ("landscape".equals(optString)) {
                i = zzbv.zzem().zzrl();
            }
            zzaej zzaej = null;
            if (!TextUtils.isEmpty(optString6) || TextUtils.isEmpty(optString3)) {
                CharSequence charSequence = optString6;
            } else {
                zzaej = zzafn.zza(zzaef, context, zzaef.zzacr.zzcw, optString3, null, null, null, null, null);
                optString2 = zzaej.zzbyq;
                str2 = zzaej.zzceo;
                j = zzaej.zzceu;
            }
            if (str2 == null) {
                return new zzaej(0);
            }
            long j3;
            String optString9;
            String str3;
            boolean optBoolean;
            JSONArray optJSONArray = jSONObject.optJSONArray("click_urls");
            List list = zzaej == null ? null : zzaej.zzbsn;
            if (optJSONArray != null) {
                list = zza(optJSONArray, list);
            }
            optJSONArray = jSONObject.optJSONArray("impression_urls");
            List list2 = zzaej == null ? null : zzaej.zzbso;
            if (optJSONArray != null) {
                list2 = zza(optJSONArray, list2);
            }
            optJSONArray = jSONObject.optJSONArray("downloaded_impression_urls");
            List list3 = zzaej == null ? null : zzaej.zzbsp;
            if (optJSONArray != null) {
                list3 = zza(optJSONArray, list3);
            }
            optJSONArray = jSONObject.optJSONArray("manual_impression_urls");
            List list4 = zzaej == null ? null : zzaej.zzces;
            if (optJSONArray != null) {
                list4 = zza(optJSONArray, list4);
            }
            if (zzaej != null) {
                if (zzaej.orientation != -1) {
                    i = zzaej.orientation;
                }
                if (zzaej.zzcep > 0) {
                    j3 = zzaej.zzcep;
                    optString9 = jSONObject.optString("active_view");
                    str3 = null;
                    optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
                    if (optBoolean) {
                        str3 = jSONObject.optString("ad_passback_url", null);
                    }
                    return new zzaej(zzaef, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list4, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", zzaef.zzbss), optString5, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), list3, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
                }
            }
            j3 = j2;
            optString9 = jSONObject.optString("active_view");
            str3 = null;
            optBoolean = jSONObject.optBoolean("ad_is_javascript", false);
            if (optBoolean) {
                str3 = jSONObject.optString("ad_passback_url", null);
            }
            return new zzaej(zzaef, optString2, str2, list, list2, j3, jSONObject.optBoolean("mediation", false), jSONObject.optLong("mediation_config_cache_time_milliseconds", -1), list4, jSONObject.optLong("refresh_interval_milliseconds", -1), i, optString4, j, optString7, optBoolean, str3, optString9, jSONObject.optBoolean("custom_render_allowed", false), z, zzaef.zzcdd, jSONObject.optBoolean("content_url_opted_out", true), jSONObject.optBoolean("prefetch", false), jSONObject.optString("gws_query_id", ""), "height".equals(jSONObject.optString("fluid", "")), jSONObject.optBoolean("native_express", false), zzaig.zza(jSONObject.optJSONArray("rewards")), zza(jSONObject.optJSONArray("video_start_urls"), null), zza(jSONObject.optJSONArray("video_complete_urls"), null), jSONObject.optBoolean("use_displayed_impression", false), zzael.zzl(jSONObject.optJSONObject("auto_protection_configuration")), zzaef.zzcdr, jSONObject.optString("set_cookie", ""), zza(jSONObject.optJSONArray("remote_ping_urls"), null), jSONObject.optBoolean("render_in_browser", zzaef.zzbss), optString5, zzaiq.zzo(jSONObject.optJSONObject("safe_browsing")), optString8, jSONObject.optBoolean("content_vertical_opted_out", true), zzaef.zzced, jSONObject.optBoolean("custom_close_blocked"), 0, jSONObject.optBoolean("enable_omid", false), list3, jSONObject.optBoolean("disable_closable_area", false), jSONObject.optString("omid_settings", null));
        } catch (JSONException e) {
            String str4 = "Could not parse the inline ad response: ";
            optString = String.valueOf(e.getMessage());
            zzakb.zzdk(optString.length() != 0 ? str4.concat(optString) : new String(str4));
            return new zzaej(0);
        }
    }

    @Nullable
    private static List<String> zza(@Nullable JSONArray jSONArray, @Nullable List<String> list) throws JSONException {
        if (jSONArray == null) {
            return null;
        }
        if (list == null) {
            list = new ArrayList();
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            list.add(jSONArray.getString(i));
        }
        return list;
    }

    @Nullable
    public static JSONObject zza(Context context, zzafl zzafl) {
        Object obj;
        Object obj2;
        zzaef zzaef = zzafl.zzcgs;
        Location location = zzafl.zzaqe;
        zzaga zzaga = zzafl.zzcgt;
        Bundle bundle = zzafl.zzcdc;
        JSONObject jSONObject = zzafl.zzcgu;
        HashMap hashMap = new HashMap();
        hashMap.put("extra_caps", zzkb.zzik().zzd(zznk.zzbbk));
        if (zzafl.zzcdj.size() > 0) {
            hashMap.put("eid", TextUtils.join(",", zzafl.zzcdj));
        }
        if (zzaef.zzccu != null) {
            hashMap.put("ad_pos", zzaef.zzccu);
        }
        zzjj zzjj = zzaef.zzccv;
        String zzqn = zzajw.zzqn();
        if (zzqn != null) {
            hashMap.put("abf", zzqn);
        }
        if (zzjj.zzapw != -1) {
            hashMap.put("cust_age", zzcho.format(new Date(zzjj.zzapw)));
        }
        if (zzjj.extras != null) {
            hashMap.put("extras", zzjj.extras);
        }
        if (zzjj.zzapx != -1) {
            hashMap.put("cust_gender", Integer.valueOf(zzjj.zzapx));
        }
        if (zzjj.zzapy != null) {
            hashMap.put("kw", zzjj.zzapy);
        }
        if (zzjj.zzaqa != -1) {
            hashMap.put("tag_for_child_directed_treatment", Integer.valueOf(zzjj.zzaqa));
        }
        if (zzjj.zzapz) {
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbfp)).booleanValue()) {
                hashMap.put("test_request", Boolean.valueOf(true));
            } else {
                hashMap.put("adtest", "on");
            }
        }
        if (zzjj.versionCode >= 2) {
            if (zzjj.zzaqb) {
                hashMap.put("d_imp_hdr", Integer.valueOf(1));
            }
            if (!TextUtils.isEmpty(zzjj.zzaqc)) {
                hashMap.put("ppid", zzjj.zzaqc);
            }
        }
        if (zzjj.versionCode >= 3 && zzjj.zzaqf != null) {
            hashMap.put("url", zzjj.zzaqf);
        }
        if (zzjj.versionCode >= 5) {
            if (zzjj.zzaqh != null) {
                hashMap.put("custom_targeting", zzjj.zzaqh);
            }
            if (zzjj.zzaqi != null) {
                hashMap.put("category_exclusions", zzjj.zzaqi);
            }
            if (zzjj.zzaqj != null) {
                hashMap.put("request_agent", zzjj.zzaqj);
            }
        }
        if (zzjj.versionCode >= 6 && zzjj.zzaqk != null) {
            hashMap.put("request_pkg", zzjj.zzaqk);
        }
        if (zzjj.versionCode >= 7) {
            hashMap.put("is_designed_for_families", Boolean.valueOf(zzjj.zzaql));
        }
        if (zzaef.zzacv.zzard != null) {
            obj = null;
            obj2 = null;
            for (zzjn zzjn : zzaef.zzacv.zzard) {
                if (!zzjn.zzarf && r3 == null) {
                    hashMap.put("format", zzjn.zzarb);
                    obj2 = 1;
                }
                if (zzjn.zzarf && r2 == null) {
                    hashMap.put("fluid", "height");
                    obj = 1;
                }
                if (obj2 != null && r2 != null) {
                    break;
                }
            }
        } else {
            hashMap.put("format", zzaef.zzacv.zzarb);
            if (zzaef.zzacv.zzarf) {
                hashMap.put("fluid", "height");
            }
        }
        if (zzaef.zzacv.width == -1) {
            hashMap.put("smart_w", MessengerShareContentUtility.WEBVIEW_RATIO_FULL);
        }
        if (zzaef.zzacv.height == -2) {
            hashMap.put("smart_h", "auto");
        }
        if (zzaef.zzacv.zzard != null) {
            StringBuilder stringBuilder = new StringBuilder();
            obj = null;
            for (zzjn zzjn2 : zzaef.zzacv.zzard) {
                if (zzjn2.zzarf) {
                    obj = 1;
                } else {
                    if (stringBuilder.length() != 0) {
                        stringBuilder.append("|");
                    }
                    stringBuilder.append(zzjn2.width == -1 ? (int) (((float) zzjn2.widthPixels) / zzaga.zzagu) : zzjn2.width);
                    stringBuilder.append("x");
                    stringBuilder.append(zzjn2.height == -2 ? (int) (((float) zzjn2.heightPixels) / zzaga.zzagu) : zzjn2.height);
                }
            }
            if (obj != null) {
                if (stringBuilder.length() != 0) {
                    stringBuilder.insert(0, "|");
                }
                stringBuilder.insert(0, "320x50");
            }
            hashMap.put("sz", stringBuilder);
        }
        if (zzaef.zzcdb != 0) {
            hashMap.put("native_version", Integer.valueOf(zzaef.zzcdb));
            hashMap.put("native_templates", zzaef.zzads);
            String str = "native_image_orientation";
            zzpl zzpl = zzaef.zzadj;
            if (zzpl != null) {
                switch (zzpl.zzbjo) {
                    case 0:
                        obj = "any";
                        break;
                    case 1:
                        obj = "portrait";
                        break;
                    case 2:
                        obj = "landscape";
                        break;
                    default:
                        obj = "not_set";
                        break;
                }
            }
            obj = "any";
            hashMap.put(str, obj);
            if (!zzaef.zzcdk.isEmpty()) {
                hashMap.put("native_custom_templates", zzaef.zzcdk);
            }
            if (zzaef.versionCode >= 24) {
                hashMap.put("max_num_ads", Integer.valueOf(zzaef.zzceg));
            }
            if (!TextUtils.isEmpty(zzaef.zzcee)) {
                try {
                    hashMap.put("native_advanced_settings", new JSONArray(zzaef.zzcee));
                } catch (Throwable e) {
                    zzakb.zzc("Problem creating json from native advanced settings", e);
                }
            }
        }
        try {
            Bundle bundle2;
            if (zzaef.zzadn != null && zzaef.zzadn.size() > 0) {
                for (Integer num : zzaef.zzadn) {
                    if (num.intValue() == 2) {
                        hashMap.put("iba", Boolean.valueOf(true));
                    } else if (num.intValue() == 1) {
                        hashMap.put("ina", Boolean.valueOf(true));
                    }
                }
            }
            if (zzaef.zzacv.zzarg) {
                hashMap.put("ene", Boolean.valueOf(true));
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzaxv)).booleanValue()) {
                hashMap.put("xsrve", Boolean.valueOf(true));
            }
            if (zzaef.zzadl != null) {
                hashMap.put("is_icon_ad", Boolean.valueOf(true));
                hashMap.put("icon_ad_expansion_behavior", Integer.valueOf(zzaef.zzadl.zzasj));
            }
            hashMap.put("slotname", zzaef.zzacp);
            hashMap.put("pn", zzaef.applicationInfo.packageName);
            if (zzaef.zzccw != null) {
                hashMap.put("vc", Integer.valueOf(zzaef.zzccw.versionCode));
            }
            hashMap.put("ms", zzafl.zzccx);
            hashMap.put("seq_num", zzaef.zzccy);
            hashMap.put(TapjoyConstants.TJC_SESSION_ID, zzaef.zzccz);
            hashMap.put("js", zzaef.zzacr.zzcw);
            zzagk zzagk = zzafl.zzcgo;
            Bundle bundle3 = zzaef.zzcdw;
            Bundle bundle4 = zzafl.zzcgn;
            hashMap.put("am", Integer.valueOf(zzaga.zzcjk));
            hashMap.put("cog", zzv(zzaga.zzcjl));
            hashMap.put("coh", zzv(zzaga.zzcjm));
            if (!TextUtils.isEmpty(zzaga.zzcjn)) {
                hashMap.put("carrier", zzaga.zzcjn);
            }
            hashMap.put("gl", zzaga.zzcjo);
            if (zzaga.zzcjp) {
                hashMap.put("simulator", Integer.valueOf(1));
            }
            if (zzaga.zzcjq) {
                hashMap.put("is_sidewinder", Integer.valueOf(1));
            }
            hashMap.put("ma", zzv(zzaga.zzcjr));
            hashMap.put("sp", zzv(zzaga.zzcjs));
            hashMap.put("hl", zzaga.zzcjt);
            if (!TextUtils.isEmpty(zzaga.zzcju)) {
                hashMap.put("mv", zzaga.zzcju);
            }
            hashMap.put("muv", Integer.valueOf(zzaga.zzcjw));
            if (zzaga.zzcjx != -2) {
                hashMap.put("cnt", Integer.valueOf(zzaga.zzcjx));
            }
            hashMap.put("gnt", Integer.valueOf(zzaga.zzcjy));
            hashMap.put("pt", Integer.valueOf(zzaga.zzcjz));
            hashMap.put("rm", Integer.valueOf(zzaga.zzcka));
            hashMap.put("riv", Integer.valueOf(zzaga.zzckb));
            Bundle bundle5 = new Bundle();
            bundle5.putString("build_build", zzaga.zzckg);
            bundle5.putString("build_device", zzaga.zzckh);
            Bundle bundle6 = new Bundle();
            bundle6.putBoolean("is_charging", zzaga.zzckd);
            bundle6.putDouble("battery_level", zzaga.zzckc);
            bundle5.putBundle("battery", bundle6);
            bundle6 = new Bundle();
            bundle6.putInt("active_network_state", zzaga.zzckf);
            bundle6.putBoolean("active_network_metered", zzaga.zzcke);
            if (zzagk != null) {
                bundle2 = new Bundle();
                bundle2.putInt("predicted_latency_micros", zzagk.zzckq);
                bundle2.putLong("predicted_down_throughput_bps", zzagk.zzckr);
                bundle2.putLong("predicted_up_throughput_bps", zzagk.zzcks);
                bundle6.putBundle("predictions", bundle2);
            }
            bundle5.putBundle("network", bundle6);
            Bundle bundle7 = new Bundle();
            bundle7.putBoolean("is_browser_custom_tabs_capable", zzaga.zzcki);
            bundle5.putBundle("browser", bundle7);
            if (bundle3 != null) {
                String str2 = "android_mem_info";
                bundle2 = new Bundle();
                bundle2.putString("runtime_free", Long.toString(bundle3.getLong("runtime_free_memory", -1)));
                bundle2.putString("runtime_max", Long.toString(bundle3.getLong("runtime_max_memory", -1)));
                bundle2.putString("runtime_total", Long.toString(bundle3.getLong("runtime_total_memory", -1)));
                bundle2.putString("web_view_count", Integer.toString(bundle3.getInt("web_view_count", 0)));
                MemoryInfo memoryInfo = (MemoryInfo) bundle3.getParcelable("debug_memory_info");
                if (memoryInfo != null) {
                    bundle2.putString("debug_info_dalvik_private_dirty", Integer.toString(memoryInfo.dalvikPrivateDirty));
                    bundle2.putString("debug_info_dalvik_pss", Integer.toString(memoryInfo.dalvikPss));
                    bundle2.putString("debug_info_dalvik_shared_dirty", Integer.toString(memoryInfo.dalvikSharedDirty));
                    bundle2.putString("debug_info_native_private_dirty", Integer.toString(memoryInfo.nativePrivateDirty));
                    bundle2.putString("debug_info_native_pss", Integer.toString(memoryInfo.nativePss));
                    bundle2.putString("debug_info_native_shared_dirty", Integer.toString(memoryInfo.nativeSharedDirty));
                    bundle2.putString("debug_info_other_private_dirty", Integer.toString(memoryInfo.otherPrivateDirty));
                    bundle2.putString("debug_info_other_pss", Integer.toString(memoryInfo.otherPss));
                    bundle2.putString("debug_info_other_shared_dirty", Integer.toString(memoryInfo.otherSharedDirty));
                }
                bundle5.putBundle(str2, bundle2);
            }
            bundle7 = new Bundle();
            bundle7.putBundle("parental_controls", bundle4);
            if (!TextUtils.isEmpty(zzaga.zzcjv)) {
                bundle7.putString("package_version", zzaga.zzcjv);
            }
            bundle5.putBundle("play_store", bundle7);
            hashMap.put("device", bundle5);
            bundle4 = new Bundle();
            bundle4.putString("doritos", zzafl.zzcgp);
            bundle4.putString("doritos_v2", zzafl.zzcgq);
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayj)).booleanValue()) {
                obj2 = null;
                boolean z = false;
                if (zzafl.zzcgr != null) {
                    obj2 = zzafl.zzcgr.getId();
                    z = zzafl.zzcgr.isLimitAdTrackingEnabled();
                }
                if (TextUtils.isEmpty(obj2)) {
                    zzkb.zzif();
                    bundle4.putString("pdid", zzamu.zzbd(context));
                    bundle4.putString("pdidtype", "ssaid");
                } else {
                    bundle4.putString("rdid", obj2);
                    bundle4.putBoolean("is_lat", z);
                    bundle4.putString("idtype", "adid");
                }
            }
            hashMap.put("pii", bundle4);
            hashMap.put(TapjoyConstants.TJC_PLATFORM, Build.MANUFACTURER);
            hashMap.put("submodel", Build.MODEL);
            if (location != null) {
                zza(hashMap, location);
            } else if (zzaef.zzccv.versionCode >= 2 && zzaef.zzccv.zzaqe != null) {
                zza(hashMap, zzaef.zzccv.zzaqe);
            }
            if (zzaef.versionCode >= 2) {
                hashMap.put("quality_signals", zzaef.zzcda);
            }
            if (zzaef.versionCode >= 4 && zzaef.zzcdd) {
                hashMap.put("forceHttps", Boolean.valueOf(zzaef.zzcdd));
            }
            if (bundle != null) {
                hashMap.put("content_info", bundle);
            }
            if (zzaef.versionCode >= 5) {
                hashMap.put("u_sd", Float.valueOf(zzaef.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaef.zzcdf));
                hashMap.put("sw", Integer.valueOf(zzaef.zzcde));
            } else {
                hashMap.put("u_sd", Float.valueOf(zzaga.zzagu));
                hashMap.put("sh", Integer.valueOf(zzaga.zzcdf));
                hashMap.put("sw", Integer.valueOf(zzaga.zzcde));
            }
            if (zzaef.versionCode >= 6) {
                if (!TextUtils.isEmpty(zzaef.zzcdg)) {
                    try {
                        hashMap.put("view_hierarchy", new JSONObject(zzaef.zzcdg));
                    } catch (Throwable e2) {
                        zzakb.zzc("Problem serializing view hierarchy to JSON", e2);
                    }
                }
                hashMap.put("correlation_id", Long.valueOf(zzaef.zzcdh));
            }
            if (zzaef.versionCode >= 7) {
                hashMap.put(Consts.INAPP_REQUEST_ID, zzaef.zzcdi);
            }
            if (zzaef.versionCode >= 12 && !TextUtils.isEmpty(zzaef.zzcdm)) {
                hashMap.put("anchor", zzaef.zzcdm);
            }
            if (zzaef.versionCode >= 13) {
                hashMap.put("android_app_volume", Float.valueOf(zzaef.zzcdn));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_app_muted", Boolean.valueOf(zzaef.zzcdt));
            }
            if (zzaef.versionCode >= 14 && zzaef.zzcdo > 0) {
                hashMap.put("target_api", Integer.valueOf(zzaef.zzcdo));
            }
            if (zzaef.versionCode >= 15) {
                hashMap.put("scroll_index", Integer.valueOf(zzaef.zzcdp == -1 ? -1 : zzaef.zzcdp));
            }
            if (zzaef.versionCode >= 16) {
                hashMap.put("_activity_context", Boolean.valueOf(zzaef.zzcdq));
            }
            if (zzaef.versionCode >= 18) {
                if (!TextUtils.isEmpty(zzaef.zzcdu)) {
                    try {
                        hashMap.put("app_settings", new JSONObject(zzaef.zzcdu));
                    } catch (Throwable e22) {
                        zzakb.zzc("Problem creating json from app settings", e22);
                    }
                }
                hashMap.put("render_in_browser", Boolean.valueOf(zzaef.zzbss));
            }
            if (zzaef.versionCode >= 18) {
                hashMap.put("android_num_video_cache_tasks", Integer.valueOf(zzaef.zzcdv));
            }
            zzang zzang = zzaef.zzacr;
            boolean z2 = zzaef.zzceh;
            boolean z3 = zzafl.zzcgv;
            boolean z4 = zzaef.zzcej;
            bundle = new Bundle();
            bundle7 = new Bundle();
            bundle7.putString("cl", "191880412");
            bundle7.putString("rapid_rc", "dev");
            bundle7.putString("rapid_rollup", "HEAD");
            bundle.putBundle("build_meta", bundle7);
            bundle.putString("mf", Boolean.toString(((Boolean) zzkb.zzik().zzd(zznk.zzbbm)).booleanValue()));
            bundle.putBoolean("instant_app", z2);
            bundle.putBoolean("lite", zzang.zzcvh);
            bundle.putBoolean("local_service", z3);
            bundle.putBoolean("is_privileged_process", z4);
            hashMap.put("sdk_env", bundle);
            hashMap.put("cache_state", jSONObject);
            if (zzaef.versionCode >= 19) {
                hashMap.put("gct", zzaef.zzcdx);
            }
            if (zzaef.versionCode >= 21 && zzaef.zzcdy) {
                hashMap.put("de", "1");
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzayy)).booleanValue()) {
                zzqn = zzaef.zzacv.zzarb;
                obj2 = (zzqn.equals("interstitial_mb") || zzqn.equals("reward_mb")) ? 1 : null;
                bundle4 = zzaef.zzcdz;
                obj = bundle4 != null ? 1 : null;
                if (!(obj2 == null || obj == null)) {
                    bundle7 = new Bundle();
                    bundle7.putBundle("interstitial_pool", bundle4);
                    hashMap.put("counters", bundle7);
                }
            }
            if (zzaef.zzcea != null) {
                hashMap.put("gmp_app_id", zzaef.zzcea);
            }
            if (zzaef.zzceb == null) {
                hashMap.put("fbs_aiid", "");
            } else if ("TIME_OUT".equals(zzaef.zzceb)) {
                hashMap.put("sai_timeout", zzkb.zzik().zzd(zznk.zzaxt));
            } else {
                hashMap.put("fbs_aiid", zzaef.zzceb);
            }
            if (zzaef.zzcec != null) {
                hashMap.put("fbs_aeid", zzaef.zzcec);
            }
            if (zzaef.versionCode >= 24) {
                hashMap.put("disable_ml", Boolean.valueOf(zzaef.zzcei));
            }
            zzqn = (String) zzkb.zzik().zzd(zznk.zzavo);
            if (!(zzqn == null || zzqn.isEmpty())) {
                if (VERSION.SDK_INT >= ((Integer) zzkb.zzik().zzd(zznk.zzavp)).intValue()) {
                    HashMap hashMap2 = new HashMap();
                    for (String str3 : zzqn.split(",")) {
                        hashMap2.put(str3, zzams.zzdd(str3));
                    }
                    hashMap.put("video_decoders", hashMap2);
                }
            }
            if (((Boolean) zzkb.zzik().zzd(zznk.zzbet)).booleanValue()) {
                hashMap.put("omid_v", zzbv.zzfa().getVersion(context));
            }
            if (!(zzaef.zzcek == null || zzaef.zzcek.isEmpty())) {
                hashMap.put("android_permissions", zzaef.zzcek);
            }
            if (zzakb.isLoggable(2)) {
                str = "Ad Request JSON: ";
                zzqn = String.valueOf(zzbv.zzek().zzn(hashMap).toString(2));
                zzakb.m3915v(zzqn.length() != 0 ? str.concat(zzqn) : new String(str));
            }
            return zzbv.zzek().zzn(hashMap);
        } catch (JSONException e3) {
            str = "Problem serializing ad request to JSON: ";
            zzqn = String.valueOf(e3.getMessage());
            zzakb.zzdk(zzqn.length() != 0 ? str.concat(zzqn) : new String(str));
            return null;
        }
    }

    private static void zza(HashMap<String, Object> hashMap, Location location) {
        HashMap hashMap2 = new HashMap();
        Float valueOf = Float.valueOf(location.getAccuracy() * 1000.0f);
        Long valueOf2 = Long.valueOf(location.getTime() * 1000);
        Long valueOf3 = Long.valueOf((long) (location.getLatitude() * 1.0E7d));
        Long valueOf4 = Long.valueOf((long) (location.getLongitude() * 1.0E7d));
        hashMap2.put("radius", valueOf);
        hashMap2.put("lat", valueOf3);
        hashMap2.put(Constants.LONG, valueOf4);
        hashMap2.put(LocationConst.TIME, valueOf2);
        hashMap.put("uule", hashMap2);
    }

    public static JSONObject zzb(zzaej zzaej) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        if (zzaej.zzbyq != null) {
            jSONObject.put("ad_base_url", zzaej.zzbyq);
        }
        if (zzaej.zzcet != null) {
            jSONObject.put("ad_size", zzaej.zzcet);
        }
        jSONObject.put("native", zzaej.zzare);
        if (zzaej.zzare) {
            jSONObject.put("ad_json", zzaej.zzceo);
        } else {
            jSONObject.put("ad_html", zzaej.zzceo);
        }
        if (zzaej.zzcev != null) {
            jSONObject.put("debug_dialog", zzaej.zzcev);
        }
        if (zzaej.zzcfl != null) {
            jSONObject.put("debug_signals", zzaej.zzcfl);
        }
        if (zzaej.zzcep != -1) {
            jSONObject.put("interstitial_timeout", ((double) zzaej.zzcep) / 1000.0d);
        }
        if (zzaej.orientation == zzbv.zzem().zzrm()) {
            jSONObject.put("orientation", "portrait");
        } else if (zzaej.orientation == zzbv.zzem().zzrl()) {
            jSONObject.put("orientation", "landscape");
        }
        if (zzaej.zzbsn != null) {
            jSONObject.put("click_urls", zzm(zzaej.zzbsn));
        }
        if (zzaej.zzbso != null) {
            jSONObject.put("impression_urls", zzm(zzaej.zzbso));
        }
        if (zzaej.zzbsp != null) {
            jSONObject.put("downloaded_impression_urls", zzm(zzaej.zzbsp));
        }
        if (zzaej.zzces != null) {
            jSONObject.put("manual_impression_urls", zzm(zzaej.zzces));
        }
        if (zzaej.zzcey != null) {
            jSONObject.put("active_view", zzaej.zzcey);
        }
        jSONObject.put("ad_is_javascript", zzaej.zzcew);
        if (zzaej.zzcex != null) {
            jSONObject.put("ad_passback_url", zzaej.zzcex);
        }
        jSONObject.put("mediation", zzaej.zzceq);
        jSONObject.put("custom_render_allowed", zzaej.zzcez);
        jSONObject.put("content_url_opted_out", zzaej.zzcfa);
        jSONObject.put("content_vertical_opted_out", zzaej.zzcfm);
        jSONObject.put("prefetch", zzaej.zzcfb);
        if (zzaej.zzbsu != -1) {
            jSONObject.put("refresh_interval_milliseconds", zzaej.zzbsu);
        }
        if (zzaej.zzcer != -1) {
            jSONObject.put("mediation_config_cache_time_milliseconds", zzaej.zzcer);
        }
        if (!TextUtils.isEmpty(zzaej.zzamj)) {
            jSONObject.put("gws_query_id", zzaej.zzamj);
        }
        jSONObject.put("fluid", zzaej.zzarf ? "height" : "");
        jSONObject.put("native_express", zzaej.zzarg);
        if (zzaej.zzcff != null) {
            jSONObject.put("video_start_urls", zzm(zzaej.zzcff));
        }
        if (zzaej.zzcfg != null) {
            jSONObject.put("video_complete_urls", zzm(zzaej.zzcfg));
        }
        if (zzaej.zzcfe != null) {
            zzaig zzaig = zzaej.zzcfe;
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("rb_type", zzaig.type);
            jSONObject2.put("rb_amount", zzaig.zzcmk);
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(jSONObject2);
            jSONObject.put("rewards", jSONArray);
        }
        jSONObject.put("use_displayed_impression", zzaej.zzcfh);
        jSONObject.put("auto_protection_configuration", zzaej.zzcfi);
        jSONObject.put("render_in_browser", zzaej.zzbss);
        jSONObject.put("disable_closable_area", zzaej.zzzm);
        return jSONObject;
    }

    @Nullable
    private static JSONArray zzm(List<String> list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        for (String put : list) {
            jSONArray.put(put);
        }
        return jSONArray;
    }

    private static Integer zzv(boolean z) {
        return Integer.valueOf(z ? 1 : 0);
    }
}
