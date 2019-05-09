// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import android.location.Location;
import java.util.HashMap;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.List;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import org.json.JSONObject;
import android.content.Context;
import java.util.Locale;
import java.text.SimpleDateFormat;

@zzadh
public final class zzafs
{
    private static final SimpleDateFormat zzcho;
    
    static {
        zzcho = new SimpleDateFormat("yyyyMMdd", Locale.US);
    }
    
    public static zzaej zza(final Context context, final zzaef zzaef, String s) {
    Label_0185_Outer:
        while (true) {
            while (true) {
            Label_0984:
                while (true) {
                    JSONObject jsonObject;
                    String optString = null;
                    String optString2;
                    String optString3;
                    String optString4;
                    boolean b;
                    String optString5;
                    long zzceu;
                    String optString6;
                    String optString7;
                    long zzcep;
                    String optString8;
                    int n;
                    zzaej zza = null;
                    String zzceo;
                    JSONArray optJSONArray;
                    List<String> zzbsn;
                    List<String> zza2;
                    JSONArray optJSONArray2;
                    List<String> zzbso;
                    List<String> zza3;
                    JSONArray optJSONArray3;
                    List<String> zzbsp;
                    List<String> zza4;
                    JSONArray optJSONArray4;
                    List<String> zzces;
                    List<String> zza5;
                    int n2 = 0;
                    String optString9;
                    String optString10;
                    boolean optBoolean;
                    String value;
                    String concat;
                    Label_0355_Outer:Label_0387_Outer:Label_0419_Outer:Label_0478_Outer:
                    while (true) {
                        Label_0969: {
                            while (true) {
                                Label_0963: {
                                    while (true) {
                                    Label_0949:
                                        while (true) {
                                        Label_0940:
                                            while (true) {
                                            Label_0931:
                                                while (true) {
                                                Label_0922:
                                                    while (true) {
                                                        try {
                                                            jsonObject = new JSONObject(s);
                                                            optString = jsonObject.optString("ad_base_url", (String)null);
                                                            optString2 = jsonObject.optString("ad_url", (String)null);
                                                            optString3 = jsonObject.optString("ad_size", (String)null);
                                                            optString4 = jsonObject.optString("ad_slot_size", optString3);
                                                            if (zzaef == null || zzaef.zzcdb == 0) {
                                                                break Label_0355_Outer;
                                                            }
                                                            b = true;
                                                            if ((s = jsonObject.optString("ad_json", (String)null)) == null) {
                                                                s = jsonObject.optString("ad_html", (String)null);
                                                            }
                                                            if ((optString5 = s) == null) {
                                                                optString5 = jsonObject.optString("body", (String)null);
                                                            }
                                                            if ((s = optString5) == null) {
                                                                s = optString5;
                                                                if (jsonObject.has("ads")) {
                                                                    s = jsonObject.toString();
                                                                }
                                                            }
                                                            zzceu = -1L;
                                                            optString6 = jsonObject.optString("debug_dialog", (String)null);
                                                            optString7 = jsonObject.optString("debug_signals", (String)null);
                                                            if (!jsonObject.has("interstitial_timeout")) {
                                                                break Label_0984;
                                                            }
                                                            zzcep = (long)(jsonObject.getDouble("interstitial_timeout") * 1000.0);
                                                            optString8 = jsonObject.optString("orientation", (String)null);
                                                            n = -1;
                                                            if ("portrait".equals(optString8)) {
                                                                n = zzbv.zzem().zzrm();
                                                            }
                                                            else if ("landscape".equals(optString8)) {
                                                                n = zzbv.zzem().zzrl();
                                                            }
                                                            zza = null;
                                                            if (!TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)optString2)) {
                                                                break Label_0969;
                                                            }
                                                            zza = zzafn.zza(zzaef, context, zzaef.zzacr.zzcw, optString2, null, null, null, null, null);
                                                            s = zza.zzbyq;
                                                            zzceo = zza.zzceo;
                                                            zzceu = zza.zzceu;
                                                            if (zzceo == null) {
                                                                return new zzaej(0);
                                                            }
                                                            optJSONArray = jsonObject.optJSONArray("click_urls");
                                                            if (zza == null) {
                                                                zzbsn = null;
                                                                zza2 = zzbsn;
                                                                if (optJSONArray != null) {
                                                                    zza2 = zza(optJSONArray, zzbsn);
                                                                }
                                                                optJSONArray2 = jsonObject.optJSONArray("impression_urls");
                                                                if (zza != null) {
                                                                    break Label_0922;
                                                                }
                                                                zzbso = null;
                                                                zza3 = zzbso;
                                                                if (optJSONArray2 != null) {
                                                                    zza3 = zza(optJSONArray2, zzbso);
                                                                }
                                                                optJSONArray3 = jsonObject.optJSONArray("downloaded_impression_urls");
                                                                if (zza != null) {
                                                                    break Label_0931;
                                                                }
                                                                zzbsp = null;
                                                                zza4 = zzbsp;
                                                                if (optJSONArray3 != null) {
                                                                    zza4 = zza(optJSONArray3, zzbsp);
                                                                }
                                                                optJSONArray4 = jsonObject.optJSONArray("manual_impression_urls");
                                                                if (zza != null) {
                                                                    break Label_0940;
                                                                }
                                                                zzces = null;
                                                                zza5 = zzces;
                                                                if (optJSONArray4 != null) {
                                                                    zza5 = zza(optJSONArray4, zzces);
                                                                }
                                                                n2 = n;
                                                                if (zza == null) {
                                                                    break Label_0963;
                                                                }
                                                                if (zza.orientation != -1) {
                                                                    n = zza.orientation;
                                                                }
                                                                n2 = n;
                                                                if (zza.zzcep > 0L) {
                                                                    zzcep = zza.zzcep;
                                                                    optString9 = jsonObject.optString("active_view");
                                                                    optString10 = null;
                                                                    optBoolean = jsonObject.optBoolean("ad_is_javascript", false);
                                                                    if (optBoolean) {
                                                                        optString10 = jsonObject.optString("ad_passback_url", (String)null);
                                                                    }
                                                                    return new zzaej(zzaef, s, zzceo, zza2, zza3, zzcep, jsonObject.optBoolean("mediation", false), jsonObject.optLong("mediation_config_cache_time_milliseconds", -1L), zza5, jsonObject.optLong("refresh_interval_milliseconds", -1L), n, optString3, zzceu, optString6, optBoolean, optString10, optString9, jsonObject.optBoolean("custom_render_allowed", false), b, zzaef.zzcdd, jsonObject.optBoolean("content_url_opted_out", true), jsonObject.optBoolean("prefetch", false), jsonObject.optString("gws_query_id", ""), "height".equals(jsonObject.optString("fluid", "")), jsonObject.optBoolean("native_express", false), zzaig.zza(jsonObject.optJSONArray("rewards")), zza(jsonObject.optJSONArray("video_start_urls"), null), zza(jsonObject.optJSONArray("video_complete_urls"), null), jsonObject.optBoolean("use_displayed_impression", false), zzael.zzl(jsonObject.optJSONObject("auto_protection_configuration")), zzaef.zzcdr, jsonObject.optString("set_cookie", ""), zza(jsonObject.optJSONArray("remote_ping_urls"), null), jsonObject.optBoolean("render_in_browser", zzaef.zzbss), optString4, zzaiq.zzo(jsonObject.optJSONObject("safe_browsing")), optString7, jsonObject.optBoolean("content_vertical_opted_out", true), zzaef.zzced, jsonObject.optBoolean("custom_close_blocked"), 0, jsonObject.optBoolean("enable_omid", false), zza4, jsonObject.optBoolean("disable_closable_area", false), jsonObject.optString("omid_settings", (String)null));
                                                                }
                                                                break Label_0963;
                                                            }
                                                        }
                                                        catch (JSONException ex) {
                                                            value = String.valueOf(ex.getMessage());
                                                            if (value.length() != 0) {
                                                                concat = "Could not parse the inline ad response: ".concat(value);
                                                                zzakb.zzdk(concat);
                                                                return new zzaej(0);
                                                            }
                                                            break Label_0949;
                                                        }
                                                        zzbsn = zza.zzbsn;
                                                        continue Label_0355_Outer;
                                                    }
                                                    zzbso = zza.zzbso;
                                                    continue Label_0387_Outer;
                                                }
                                                zzbsp = zza.zzbsp;
                                                continue Label_0419_Outer;
                                            }
                                            zzces = zza.zzces;
                                            continue Label_0478_Outer;
                                        }
                                        concat = new String("Could not parse the inline ad response: ");
                                        continue;
                                    }
                                }
                                n = n2;
                                continue;
                            }
                        }
                        zzceo = s;
                        s = optString;
                        continue;
                    }
                    b = false;
                    continue Label_0185_Outer;
                }
                long zzcep = -1L;
                continue;
            }
        }
    }
    
    @Nullable
    private static List<String> zza(@Nullable final JSONArray jsonArray, @Nullable List<String> list) throws JSONException {
        if (jsonArray == null) {
            list = null;
        }
        else {
            List<String> list2;
            if ((list2 = list) == null) {
                list2 = new ArrayList<String>();
            }
            int n = 0;
            while (true) {
                list = list2;
                if (n >= jsonArray.length()) {
                    break;
                }
                list2.add(jsonArray.getString(n));
                ++n;
            }
        }
        return list;
    }
    
    @Nullable
    public static JSONObject zza(final Context p0, final zzafl p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzafl.zzcgs:Lcom/google/android/gms/internal/ads/zzaef;
        //     4: astore          12
        //     6: aload_1        
        //     7: getfield        com/google/android/gms/internal/ads/zzafl.zzaqe:Landroid/location/Location;
        //    10: astore          14
        //    12: aload_1        
        //    13: getfield        com/google/android/gms/internal/ads/zzafl.zzcgt:Lcom/google/android/gms/internal/ads/zzaga;
        //    16: astore          15
        //    18: aload_1        
        //    19: getfield        com/google/android/gms/internal/ads/zzafl.zzcdc:Landroid/os/Bundle;
        //    22: astore          16
        //    24: aload_1        
        //    25: getfield        com/google/android/gms/internal/ads/zzafl.zzcgu:Lorg/json/JSONObject;
        //    28: astore          13
        //    30: new             Ljava/util/HashMap;
        //    33: dup            
        //    34: invokespecial   java/util/HashMap.<init>:()V
        //    37: astore          11
        //    39: getstatic       com/google/android/gms/internal/ads/zznk.zzbbk:Lcom/google/android/gms/internal/ads/zzna;
        //    42: astore          10
        //    44: aload           11
        //    46: ldc_w           "extra_caps"
        //    49: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //    52: aload           10
        //    54: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //    57: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    60: pop            
        //    61: aload_1        
        //    62: getfield        com/google/android/gms/internal/ads/zzafl.zzcdj:Ljava/util/List;
        //    65: invokeinterface java/util/List.size:()I
        //    70: ifle            92
        //    73: aload           11
        //    75: ldc_w           "eid"
        //    78: ldc_w           ","
        //    81: aload_1        
        //    82: getfield        com/google/android/gms/internal/ads/zzafl.zzcdj:Ljava/util/List;
        //    85: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //    88: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    91: pop            
        //    92: aload           12
        //    94: getfield        com/google/android/gms/internal/ads/zzaef.zzccu:Landroid/os/Bundle;
        //    97: ifnull          114
        //   100: aload           11
        //   102: ldc_w           "ad_pos"
        //   105: aload           12
        //   107: getfield        com/google/android/gms/internal/ads/zzaef.zzccu:Landroid/os/Bundle;
        //   110: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   113: pop            
        //   114: aload           12
        //   116: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //   119: astore          10
        //   121: invokestatic    com/google/android/gms/internal/ads/zzajw.zzqn:()Ljava/lang/String;
        //   124: astore          17
        //   126: aload           17
        //   128: ifnull          142
        //   131: aload           11
        //   133: ldc_w           "abf"
        //   136: aload           17
        //   138: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   141: pop            
        //   142: aload           10
        //   144: getfield        com/google/android/gms/internal/ads/zzjj.zzapw:J
        //   147: ldc2_w          -1
        //   150: lcmp           
        //   151: ifeq            181
        //   154: aload           11
        //   156: ldc_w           "cust_age"
        //   159: getstatic       com/google/android/gms/internal/ads/zzafs.zzcho:Ljava/text/SimpleDateFormat;
        //   162: new             Ljava/util/Date;
        //   165: dup            
        //   166: aload           10
        //   168: getfield        com/google/android/gms/internal/ads/zzjj.zzapw:J
        //   171: invokespecial   java/util/Date.<init>:(J)V
        //   174: invokevirtual   java/text/SimpleDateFormat.format:(Ljava/util/Date;)Ljava/lang/String;
        //   177: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   180: pop            
        //   181: aload           10
        //   183: getfield        com/google/android/gms/internal/ads/zzjj.extras:Landroid/os/Bundle;
        //   186: ifnull          203
        //   189: aload           11
        //   191: ldc_w           "extras"
        //   194: aload           10
        //   196: getfield        com/google/android/gms/internal/ads/zzjj.extras:Landroid/os/Bundle;
        //   199: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   202: pop            
        //   203: aload           10
        //   205: getfield        com/google/android/gms/internal/ads/zzjj.zzapx:I
        //   208: iconst_m1      
        //   209: if_icmpeq       229
        //   212: aload           11
        //   214: ldc_w           "cust_gender"
        //   217: aload           10
        //   219: getfield        com/google/android/gms/internal/ads/zzjj.zzapx:I
        //   222: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   225: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   228: pop            
        //   229: aload           10
        //   231: getfield        com/google/android/gms/internal/ads/zzjj.zzapy:Ljava/util/List;
        //   234: ifnull          251
        //   237: aload           11
        //   239: ldc_w           "kw"
        //   242: aload           10
        //   244: getfield        com/google/android/gms/internal/ads/zzjj.zzapy:Ljava/util/List;
        //   247: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   250: pop            
        //   251: aload           10
        //   253: getfield        com/google/android/gms/internal/ads/zzjj.zzaqa:I
        //   256: iconst_m1      
        //   257: if_icmpeq       277
        //   260: aload           11
        //   262: ldc_w           "tag_for_child_directed_treatment"
        //   265: aload           10
        //   267: getfield        com/google/android/gms/internal/ads/zzjj.zzaqa:I
        //   270: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   273: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   276: pop            
        //   277: aload           10
        //   279: getfield        com/google/android/gms/internal/ads/zzjj.zzapz:Z
        //   282: ifeq            320
        //   285: getstatic       com/google/android/gms/internal/ads/zznk.zzbfp:Lcom/google/android/gms/internal/ads/zzna;
        //   288: astore          17
        //   290: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   293: aload           17
        //   295: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   298: checkcast       Ljava/lang/Boolean;
        //   301: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   304: ifeq            702
        //   307: aload           11
        //   309: ldc_w           "test_request"
        //   312: iconst_1       
        //   313: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   316: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   319: pop            
        //   320: aload           10
        //   322: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //   325: iconst_2       
        //   326: if_icmplt       375
        //   329: aload           10
        //   331: getfield        com/google/android/gms/internal/ads/zzjj.zzaqb:Z
        //   334: ifeq            350
        //   337: aload           11
        //   339: ldc_w           "d_imp_hdr"
        //   342: iconst_1       
        //   343: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   346: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   349: pop            
        //   350: aload           10
        //   352: getfield        com/google/android/gms/internal/ads/zzjj.zzaqc:Ljava/lang/String;
        //   355: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   358: ifne            375
        //   361: aload           11
        //   363: ldc_w           "ppid"
        //   366: aload           10
        //   368: getfield        com/google/android/gms/internal/ads/zzjj.zzaqc:Ljava/lang/String;
        //   371: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   374: pop            
        //   375: aload           10
        //   377: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //   380: iconst_3       
        //   381: if_icmplt       406
        //   384: aload           10
        //   386: getfield        com/google/android/gms/internal/ads/zzjj.zzaqf:Ljava/lang/String;
        //   389: ifnull          406
        //   392: aload           11
        //   394: ldc_w           "url"
        //   397: aload           10
        //   399: getfield        com/google/android/gms/internal/ads/zzjj.zzaqf:Ljava/lang/String;
        //   402: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   405: pop            
        //   406: aload           10
        //   408: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //   411: iconst_5       
        //   412: if_icmplt       481
        //   415: aload           10
        //   417: getfield        com/google/android/gms/internal/ads/zzjj.zzaqh:Landroid/os/Bundle;
        //   420: ifnull          437
        //   423: aload           11
        //   425: ldc_w           "custom_targeting"
        //   428: aload           10
        //   430: getfield        com/google/android/gms/internal/ads/zzjj.zzaqh:Landroid/os/Bundle;
        //   433: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   436: pop            
        //   437: aload           10
        //   439: getfield        com/google/android/gms/internal/ads/zzjj.zzaqi:Ljava/util/List;
        //   442: ifnull          459
        //   445: aload           11
        //   447: ldc_w           "category_exclusions"
        //   450: aload           10
        //   452: getfield        com/google/android/gms/internal/ads/zzjj.zzaqi:Ljava/util/List;
        //   455: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   458: pop            
        //   459: aload           10
        //   461: getfield        com/google/android/gms/internal/ads/zzjj.zzaqj:Ljava/lang/String;
        //   464: ifnull          481
        //   467: aload           11
        //   469: ldc_w           "request_agent"
        //   472: aload           10
        //   474: getfield        com/google/android/gms/internal/ads/zzjj.zzaqj:Ljava/lang/String;
        //   477: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   480: pop            
        //   481: aload           10
        //   483: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //   486: bipush          6
        //   488: if_icmplt       513
        //   491: aload           10
        //   493: getfield        com/google/android/gms/internal/ads/zzjj.zzaqk:Ljava/lang/String;
        //   496: ifnull          513
        //   499: aload           11
        //   501: ldc_w           "request_pkg"
        //   504: aload           10
        //   506: getfield        com/google/android/gms/internal/ads/zzjj.zzaqk:Ljava/lang/String;
        //   509: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   512: pop            
        //   513: aload           10
        //   515: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //   518: bipush          7
        //   520: if_icmplt       540
        //   523: aload           11
        //   525: ldc_w           "is_designed_for_families"
        //   528: aload           10
        //   530: getfield        com/google/android/gms/internal/ads/zzjj.zzaql:Z
        //   533: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   536: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   539: pop            
        //   540: aload           12
        //   542: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   545: getfield        com/google/android/gms/internal/ads/zzjn.zzard:[Lcom/google/android/gms/internal/ads/zzjn;
        //   548: ifnonnull       747
        //   551: aload           11
        //   553: ldc_w           "format"
        //   556: aload           12
        //   558: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   561: getfield        com/google/android/gms/internal/ads/zzjn.zzarb:Ljava/lang/String;
        //   564: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   567: pop            
        //   568: aload           12
        //   570: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   573: getfield        com/google/android/gms/internal/ads/zzjn.zzarf:Z
        //   576: ifeq            589
        //   579: aload           11
        //   581: ldc             "fluid"
        //   583: ldc             "height"
        //   585: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   588: pop            
        //   589: aload           12
        //   591: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   594: getfield        com/google/android/gms/internal/ads/zzjn.width:I
        //   597: iconst_m1      
        //   598: if_icmpne       613
        //   601: aload           11
        //   603: ldc_w           "smart_w"
        //   606: ldc_w           "full"
        //   609: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   612: pop            
        //   613: aload           12
        //   615: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   618: getfield        com/google/android/gms/internal/ads/zzjn.height:I
        //   621: bipush          -2
        //   623: if_icmpne       638
        //   626: aload           11
        //   628: ldc_w           "smart_h"
        //   631: ldc_w           "auto"
        //   634: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   637: pop            
        //   638: aload           12
        //   640: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   643: getfield        com/google/android/gms/internal/ads/zzjn.zzard:[Lcom/google/android/gms/internal/ads/zzjn;
        //   646: ifnull          1010
        //   649: new             Ljava/lang/StringBuilder;
        //   652: dup            
        //   653: invokespecial   java/lang/StringBuilder.<init>:()V
        //   656: astore          10
        //   658: aload           12
        //   660: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   663: getfield        com/google/android/gms/internal/ads/zzjn.zzard:[Lcom/google/android/gms/internal/ads/zzjn;
        //   666: astore          17
        //   668: aload           17
        //   670: arraylength    
        //   671: istore          5
        //   673: iconst_0       
        //   674: istore_2       
        //   675: iconst_0       
        //   676: istore_3       
        //   677: iload_2        
        //   678: iload           5
        //   680: if_icmpge       967
        //   683: aload           17
        //   685: iload_2        
        //   686: aaload         
        //   687: astore          18
        //   689: aload           18
        //   691: getfield        com/google/android/gms/internal/ads/zzjn.zzarf:Z
        //   694: ifeq            853
        //   697: iconst_1       
        //   698: istore_3       
        //   699: goto            3991
        //   702: aload           11
        //   704: ldc_w           "adtest"
        //   707: ldc_w           "on"
        //   710: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   713: pop            
        //   714: goto            320
        //   717: astore_0       
        //   718: aload_0        
        //   719: invokevirtual   org/json/JSONException.getMessage:()Ljava/lang/String;
        //   722: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   725: astore_0       
        //   726: aload_0        
        //   727: invokevirtual   java/lang/String.length:()I
        //   730: ifeq            3977
        //   733: ldc_w           "Problem serializing ad request to JSON: "
        //   736: aload_0        
        //   737: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   740: astore_0       
        //   741: aload_0        
        //   742: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   745: aconst_null    
        //   746: areturn        
        //   747: aload           12
        //   749: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //   752: getfield        com/google/android/gms/internal/ads/zzjn.zzard:[Lcom/google/android/gms/internal/ads/zzjn;
        //   755: astore          10
        //   757: aload           10
        //   759: arraylength    
        //   760: istore          6
        //   762: iconst_0       
        //   763: istore_3       
        //   764: iconst_0       
        //   765: istore          5
        //   767: iconst_0       
        //   768: istore_2       
        //   769: iload_2        
        //   770: iload           6
        //   772: if_icmpge       589
        //   775: aload           10
        //   777: iload_2        
        //   778: aaload         
        //   779: astore          17
        //   781: iload           5
        //   783: istore          4
        //   785: aload           17
        //   787: getfield        com/google/android/gms/internal/ads/zzjn.zzarf:Z
        //   790: ifne            819
        //   793: iload           5
        //   795: istore          4
        //   797: iload           5
        //   799: ifne            819
        //   802: aload           11
        //   804: ldc_w           "format"
        //   807: aload           17
        //   809: getfield        com/google/android/gms/internal/ads/zzjn.zzarb:Ljava/lang/String;
        //   812: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   815: pop            
        //   816: iconst_1       
        //   817: istore          4
        //   819: iload_3        
        //   820: istore          5
        //   822: aload           17
        //   824: getfield        com/google/android/gms/internal/ads/zzjn.zzarf:Z
        //   827: ifeq            3998
        //   830: iload_3        
        //   831: istore          5
        //   833: iload_3        
        //   834: ifne            3998
        //   837: aload           11
        //   839: ldc             "fluid"
        //   841: ldc             "height"
        //   843: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   846: pop            
        //   847: iconst_1       
        //   848: istore          5
        //   850: goto            3998
        //   853: aload           10
        //   855: invokevirtual   java/lang/StringBuilder.length:()I
        //   858: ifeq            870
        //   861: aload           10
        //   863: ldc_w           "|"
        //   866: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   869: pop            
        //   870: aload           18
        //   872: getfield        com/google/android/gms/internal/ads/zzjn.width:I
        //   875: iconst_m1      
        //   876: if_icmpne       947
        //   879: aload           18
        //   881: getfield        com/google/android/gms/internal/ads/zzjn.widthPixels:I
        //   884: i2f            
        //   885: aload           15
        //   887: getfield        com/google/android/gms/internal/ads/zzaga.zzagu:F
        //   890: fdiv           
        //   891: f2i            
        //   892: istore          4
        //   894: aload           10
        //   896: iload           4
        //   898: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   901: pop            
        //   902: aload           10
        //   904: ldc_w           "x"
        //   907: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   910: pop            
        //   911: aload           18
        //   913: getfield        com/google/android/gms/internal/ads/zzjn.height:I
        //   916: bipush          -2
        //   918: if_icmpne       957
        //   921: aload           18
        //   923: getfield        com/google/android/gms/internal/ads/zzjn.heightPixels:I
        //   926: i2f            
        //   927: aload           15
        //   929: getfield        com/google/android/gms/internal/ads/zzaga.zzagu:F
        //   932: fdiv           
        //   933: f2i            
        //   934: istore          4
        //   936: aload           10
        //   938: iload           4
        //   940: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   943: pop            
        //   944: goto            3991
        //   947: aload           18
        //   949: getfield        com/google/android/gms/internal/ads/zzjn.width:I
        //   952: istore          4
        //   954: goto            894
        //   957: aload           18
        //   959: getfield        com/google/android/gms/internal/ads/zzjn.height:I
        //   962: istore          4
        //   964: goto            936
        //   967: iload_3        
        //   968: ifeq            999
        //   971: aload           10
        //   973: invokevirtual   java/lang/StringBuilder.length:()I
        //   976: ifeq            989
        //   979: aload           10
        //   981: iconst_0       
        //   982: ldc_w           "|"
        //   985: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   988: pop            
        //   989: aload           10
        //   991: iconst_0       
        //   992: ldc_w           "320x50"
        //   995: invokevirtual   java/lang/StringBuilder.insert:(ILjava/lang/String;)Ljava/lang/StringBuilder;
        //   998: pop            
        //   999: aload           11
        //  1001: ldc_w           "sz"
        //  1004: aload           10
        //  1006: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1009: pop            
        //  1010: aload           12
        //  1012: getfield        com/google/android/gms/internal/ads/zzaef.zzcdb:I
        //  1015: ifeq            1167
        //  1018: aload           11
        //  1020: ldc_w           "native_version"
        //  1023: aload           12
        //  1025: getfield        com/google/android/gms/internal/ads/zzaef.zzcdb:I
        //  1028: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1031: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1034: pop            
        //  1035: aload           11
        //  1037: ldc_w           "native_templates"
        //  1040: aload           12
        //  1042: getfield        com/google/android/gms/internal/ads/zzaef.zzads:Ljava/util/List;
        //  1045: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1048: pop            
        //  1049: aload           12
        //  1051: getfield        com/google/android/gms/internal/ads/zzaef.zzadj:Lcom/google/android/gms/internal/ads/zzpl;
        //  1054: astore          10
        //  1056: aload           10
        //  1058: ifnonnull       1247
        //  1061: ldc_w           "any"
        //  1064: astore          10
        //  1066: aload           11
        //  1068: ldc_w           "native_image_orientation"
        //  1071: aload           10
        //  1073: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1076: pop            
        //  1077: aload           12
        //  1079: getfield        com/google/android/gms/internal/ads/zzaef.zzcdk:Ljava/util/List;
        //  1082: invokeinterface java/util/List.isEmpty:()Z
        //  1087: ifne            1104
        //  1090: aload           11
        //  1092: ldc_w           "native_custom_templates"
        //  1095: aload           12
        //  1097: getfield        com/google/android/gms/internal/ads/zzaef.zzcdk:Ljava/util/List;
        //  1100: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1103: pop            
        //  1104: aload           12
        //  1106: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  1109: bipush          24
        //  1111: if_icmplt       1131
        //  1114: aload           11
        //  1116: ldc_w           "max_num_ads"
        //  1119: aload           12
        //  1121: getfield        com/google/android/gms/internal/ads/zzaef.zzceg:I
        //  1124: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1127: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1130: pop            
        //  1131: aload           12
        //  1133: getfield        com/google/android/gms/internal/ads/zzaef.zzcee:Ljava/lang/String;
        //  1136: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  1139: istore          7
        //  1141: iload           7
        //  1143: ifne            1167
        //  1146: aload           11
        //  1148: ldc_w           "native_advanced_settings"
        //  1151: new             Lorg/json/JSONArray;
        //  1154: dup            
        //  1155: aload           12
        //  1157: getfield        com/google/android/gms/internal/ads/zzaef.zzcee:Ljava/lang/String;
        //  1160: invokespecial   org/json/JSONArray.<init>:(Ljava/lang/String;)V
        //  1163: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1166: pop            
        //  1167: aload           12
        //  1169: getfield        com/google/android/gms/internal/ads/zzaef.zzadn:Ljava/util/List;
        //  1172: ifnull          1318
        //  1175: aload           12
        //  1177: getfield        com/google/android/gms/internal/ads/zzaef.zzadn:Ljava/util/List;
        //  1180: invokeinterface java/util/List.size:()I
        //  1185: ifle            1318
        //  1188: aload           12
        //  1190: getfield        com/google/android/gms/internal/ads/zzaef.zzadn:Ljava/util/List;
        //  1193: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //  1198: astore          10
        //  1200: aload           10
        //  1202: invokeinterface java/util/Iterator.hasNext:()Z
        //  1207: ifeq            1318
        //  1210: aload           10
        //  1212: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //  1217: checkcast       Ljava/lang/Integer;
        //  1220: astore          17
        //  1222: aload           17
        //  1224: invokevirtual   java/lang/Integer.intValue:()I
        //  1227: iconst_2       
        //  1228: if_icmpne       1293
        //  1231: aload           11
        //  1233: ldc_w           "iba"
        //  1236: iconst_1       
        //  1237: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1240: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1243: pop            
        //  1244: goto            1200
        //  1247: aload           10
        //  1249: getfield        com/google/android/gms/internal/ads/zzpl.zzbjo:I
        //  1252: tableswitch {
        //                0: 4044
        //                1: 4030
        //                2: 4037
        //          default: 4022
        //        }
        //  1280: astore          10
        //  1282: ldc_w           "Problem creating json from native advanced settings"
        //  1285: aload           10
        //  1287: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1290: goto            1167
        //  1293: aload           17
        //  1295: invokevirtual   java/lang/Integer.intValue:()I
        //  1298: iconst_1       
        //  1299: if_icmpne       1200
        //  1302: aload           11
        //  1304: ldc_w           "ina"
        //  1307: iconst_1       
        //  1308: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1311: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1314: pop            
        //  1315: goto            1200
        //  1318: aload           12
        //  1320: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //  1323: getfield        com/google/android/gms/internal/ads/zzjn.zzarg:Z
        //  1326: ifeq            1342
        //  1329: aload           11
        //  1331: ldc_w           "ene"
        //  1334: iconst_1       
        //  1335: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1338: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1341: pop            
        //  1342: getstatic       com/google/android/gms/internal/ads/zznk.zzaxv:Lcom/google/android/gms/internal/ads/zzna;
        //  1345: astore          10
        //  1347: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  1350: aload           10
        //  1352: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  1355: checkcast       Ljava/lang/Boolean;
        //  1358: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  1361: ifeq            1377
        //  1364: aload           11
        //  1366: ldc_w           "xsrve"
        //  1369: iconst_1       
        //  1370: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1373: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1376: pop            
        //  1377: aload           12
        //  1379: getfield        com/google/android/gms/internal/ads/zzaef.zzadl:Lcom/google/android/gms/internal/ads/zzlu;
        //  1382: ifnull          1418
        //  1385: aload           11
        //  1387: ldc_w           "is_icon_ad"
        //  1390: iconst_1       
        //  1391: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  1394: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1397: pop            
        //  1398: aload           11
        //  1400: ldc_w           "icon_ad_expansion_behavior"
        //  1403: aload           12
        //  1405: getfield        com/google/android/gms/internal/ads/zzaef.zzadl:Lcom/google/android/gms/internal/ads/zzlu;
        //  1408: getfield        com/google/android/gms/internal/ads/zzlu.zzasj:I
        //  1411: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1414: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1417: pop            
        //  1418: aload           11
        //  1420: ldc_w           "slotname"
        //  1423: aload           12
        //  1425: getfield        com/google/android/gms/internal/ads/zzaef.zzacp:Ljava/lang/String;
        //  1428: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1431: pop            
        //  1432: aload           11
        //  1434: ldc_w           "pn"
        //  1437: aload           12
        //  1439: getfield        com/google/android/gms/internal/ads/zzaef.applicationInfo:Landroid/content/pm/ApplicationInfo;
        //  1442: getfield        android/content/pm/ApplicationInfo.packageName:Ljava/lang/String;
        //  1445: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1448: pop            
        //  1449: aload           12
        //  1451: getfield        com/google/android/gms/internal/ads/zzaef.zzccw:Landroid/content/pm/PackageInfo;
        //  1454: ifnull          1477
        //  1457: aload           11
        //  1459: ldc_w           "vc"
        //  1462: aload           12
        //  1464: getfield        com/google/android/gms/internal/ads/zzaef.zzccw:Landroid/content/pm/PackageInfo;
        //  1467: getfield        android/content/pm/PackageInfo.versionCode:I
        //  1470: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1473: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1476: pop            
        //  1477: aload           11
        //  1479: ldc_w           "ms"
        //  1482: aload_1        
        //  1483: getfield        com/google/android/gms/internal/ads/zzafl.zzccx:Ljava/lang/String;
        //  1486: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1489: pop            
        //  1490: aload           11
        //  1492: ldc_w           "seq_num"
        //  1495: aload           12
        //  1497: getfield        com/google/android/gms/internal/ads/zzaef.zzccy:Ljava/lang/String;
        //  1500: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1503: pop            
        //  1504: aload           11
        //  1506: ldc_w           "session_id"
        //  1509: aload           12
        //  1511: getfield        com/google/android/gms/internal/ads/zzaef.zzccz:Ljava/lang/String;
        //  1514: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1517: pop            
        //  1518: aload           11
        //  1520: ldc_w           "js"
        //  1523: aload           12
        //  1525: getfield        com/google/android/gms/internal/ads/zzaef.zzacr:Lcom/google/android/gms/internal/ads/zzang;
        //  1528: getfield        com/google/android/gms/internal/ads/zzang.zzcw:Ljava/lang/String;
        //  1531: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1534: pop            
        //  1535: aload_1        
        //  1536: getfield        com/google/android/gms/internal/ads/zzafl.zzcgo:Lcom/google/android/gms/internal/ads/zzagk;
        //  1539: astore          19
        //  1541: aload           12
        //  1543: getfield        com/google/android/gms/internal/ads/zzaef.zzcdw:Landroid/os/Bundle;
        //  1546: astore          18
        //  1548: aload_1        
        //  1549: getfield        com/google/android/gms/internal/ads/zzafl.zzcgn:Landroid/os/Bundle;
        //  1552: astore          10
        //  1554: aload           11
        //  1556: ldc_w           "am"
        //  1559: aload           15
        //  1561: getfield        com/google/android/gms/internal/ads/zzaga.zzcjk:I
        //  1564: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1567: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1570: pop            
        //  1571: aload           11
        //  1573: ldc_w           "cog"
        //  1576: aload           15
        //  1578: getfield        com/google/android/gms/internal/ads/zzaga.zzcjl:Z
        //  1581: invokestatic    com/google/android/gms/internal/ads/zzafs.zzv:(Z)Ljava/lang/Integer;
        //  1584: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1587: pop            
        //  1588: aload           11
        //  1590: ldc_w           "coh"
        //  1593: aload           15
        //  1595: getfield        com/google/android/gms/internal/ads/zzaga.zzcjm:Z
        //  1598: invokestatic    com/google/android/gms/internal/ads/zzafs.zzv:(Z)Ljava/lang/Integer;
        //  1601: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1604: pop            
        //  1605: aload           15
        //  1607: getfield        com/google/android/gms/internal/ads/zzaga.zzcjn:Ljava/lang/String;
        //  1610: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  1613: ifne            1630
        //  1616: aload           11
        //  1618: ldc_w           "carrier"
        //  1621: aload           15
        //  1623: getfield        com/google/android/gms/internal/ads/zzaga.zzcjn:Ljava/lang/String;
        //  1626: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1629: pop            
        //  1630: aload           11
        //  1632: ldc_w           "gl"
        //  1635: aload           15
        //  1637: getfield        com/google/android/gms/internal/ads/zzaga.zzcjo:Ljava/lang/String;
        //  1640: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1643: pop            
        //  1644: aload           15
        //  1646: getfield        com/google/android/gms/internal/ads/zzaga.zzcjp:Z
        //  1649: ifeq            1665
        //  1652: aload           11
        //  1654: ldc_w           "simulator"
        //  1657: iconst_1       
        //  1658: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1661: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1664: pop            
        //  1665: aload           15
        //  1667: getfield        com/google/android/gms/internal/ads/zzaga.zzcjq:Z
        //  1670: ifeq            1686
        //  1673: aload           11
        //  1675: ldc_w           "is_sidewinder"
        //  1678: iconst_1       
        //  1679: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1682: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1685: pop            
        //  1686: aload           11
        //  1688: ldc_w           "ma"
        //  1691: aload           15
        //  1693: getfield        com/google/android/gms/internal/ads/zzaga.zzcjr:Z
        //  1696: invokestatic    com/google/android/gms/internal/ads/zzafs.zzv:(Z)Ljava/lang/Integer;
        //  1699: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1702: pop            
        //  1703: aload           11
        //  1705: ldc_w           "sp"
        //  1708: aload           15
        //  1710: getfield        com/google/android/gms/internal/ads/zzaga.zzcjs:Z
        //  1713: invokestatic    com/google/android/gms/internal/ads/zzafs.zzv:(Z)Ljava/lang/Integer;
        //  1716: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1719: pop            
        //  1720: aload           11
        //  1722: ldc_w           "hl"
        //  1725: aload           15
        //  1727: getfield        com/google/android/gms/internal/ads/zzaga.zzcjt:Ljava/lang/String;
        //  1730: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1733: pop            
        //  1734: aload           15
        //  1736: getfield        com/google/android/gms/internal/ads/zzaga.zzcju:Ljava/lang/String;
        //  1739: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  1742: ifne            1759
        //  1745: aload           11
        //  1747: ldc_w           "mv"
        //  1750: aload           15
        //  1752: getfield        com/google/android/gms/internal/ads/zzaga.zzcju:Ljava/lang/String;
        //  1755: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1758: pop            
        //  1759: aload           11
        //  1761: ldc_w           "muv"
        //  1764: aload           15
        //  1766: getfield        com/google/android/gms/internal/ads/zzaga.zzcjw:I
        //  1769: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1772: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1775: pop            
        //  1776: aload           15
        //  1778: getfield        com/google/android/gms/internal/ads/zzaga.zzcjx:I
        //  1781: bipush          -2
        //  1783: if_icmpeq       1803
        //  1786: aload           11
        //  1788: ldc_w           "cnt"
        //  1791: aload           15
        //  1793: getfield        com/google/android/gms/internal/ads/zzaga.zzcjx:I
        //  1796: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1799: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1802: pop            
        //  1803: aload           11
        //  1805: ldc_w           "gnt"
        //  1808: aload           15
        //  1810: getfield        com/google/android/gms/internal/ads/zzaga.zzcjy:I
        //  1813: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1816: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1819: pop            
        //  1820: aload           11
        //  1822: ldc_w           "pt"
        //  1825: aload           15
        //  1827: getfield        com/google/android/gms/internal/ads/zzaga.zzcjz:I
        //  1830: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1833: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1836: pop            
        //  1837: aload           11
        //  1839: ldc_w           "rm"
        //  1842: aload           15
        //  1844: getfield        com/google/android/gms/internal/ads/zzaga.zzcka:I
        //  1847: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1850: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1853: pop            
        //  1854: aload           11
        //  1856: ldc_w           "riv"
        //  1859: aload           15
        //  1861: getfield        com/google/android/gms/internal/ads/zzaga.zzckb:I
        //  1864: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  1867: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  1870: pop            
        //  1871: new             Landroid/os/Bundle;
        //  1874: dup            
        //  1875: invokespecial   android/os/Bundle.<init>:()V
        //  1878: astore          17
        //  1880: aload           17
        //  1882: ldc_w           "build_build"
        //  1885: aload           15
        //  1887: getfield        com/google/android/gms/internal/ads/zzaga.zzckg:Ljava/lang/String;
        //  1890: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  1893: aload           17
        //  1895: ldc_w           "build_device"
        //  1898: aload           15
        //  1900: getfield        com/google/android/gms/internal/ads/zzaga.zzckh:Ljava/lang/String;
        //  1903: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  1906: new             Landroid/os/Bundle;
        //  1909: dup            
        //  1910: invokespecial   android/os/Bundle.<init>:()V
        //  1913: astore          20
        //  1915: aload           20
        //  1917: ldc_w           "is_charging"
        //  1920: aload           15
        //  1922: getfield        com/google/android/gms/internal/ads/zzaga.zzckd:Z
        //  1925: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  1928: aload           20
        //  1930: ldc_w           "battery_level"
        //  1933: aload           15
        //  1935: getfield        com/google/android/gms/internal/ads/zzaga.zzckc:D
        //  1938: invokevirtual   android/os/Bundle.putDouble:(Ljava/lang/String;D)V
        //  1941: aload           17
        //  1943: ldc_w           "battery"
        //  1946: aload           20
        //  1948: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  1951: new             Landroid/os/Bundle;
        //  1954: dup            
        //  1955: invokespecial   android/os/Bundle.<init>:()V
        //  1958: astore          20
        //  1960: aload           20
        //  1962: ldc_w           "active_network_state"
        //  1965: aload           15
        //  1967: getfield        com/google/android/gms/internal/ads/zzaga.zzckf:I
        //  1970: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //  1973: aload           20
        //  1975: ldc_w           "active_network_metered"
        //  1978: aload           15
        //  1980: getfield        com/google/android/gms/internal/ads/zzaga.zzcke:Z
        //  1983: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  1986: aload           19
        //  1988: ifnull          2049
        //  1991: new             Landroid/os/Bundle;
        //  1994: dup            
        //  1995: invokespecial   android/os/Bundle.<init>:()V
        //  1998: astore          21
        //  2000: aload           21
        //  2002: ldc_w           "predicted_latency_micros"
        //  2005: aload           19
        //  2007: getfield        com/google/android/gms/internal/ads/zzagk.zzckq:I
        //  2010: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //  2013: aload           21
        //  2015: ldc_w           "predicted_down_throughput_bps"
        //  2018: aload           19
        //  2020: getfield        com/google/android/gms/internal/ads/zzagk.zzckr:J
        //  2023: invokevirtual   android/os/Bundle.putLong:(Ljava/lang/String;J)V
        //  2026: aload           21
        //  2028: ldc_w           "predicted_up_throughput_bps"
        //  2031: aload           19
        //  2033: getfield        com/google/android/gms/internal/ads/zzagk.zzcks:J
        //  2036: invokevirtual   android/os/Bundle.putLong:(Ljava/lang/String;J)V
        //  2039: aload           20
        //  2041: ldc_w           "predictions"
        //  2044: aload           21
        //  2046: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2049: aload           17
        //  2051: ldc_w           "network"
        //  2054: aload           20
        //  2056: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2059: new             Landroid/os/Bundle;
        //  2062: dup            
        //  2063: invokespecial   android/os/Bundle.<init>:()V
        //  2066: astore          19
        //  2068: aload           19
        //  2070: ldc_w           "is_browser_custom_tabs_capable"
        //  2073: aload           15
        //  2075: getfield        com/google/android/gms/internal/ads/zzaga.zzcki:Z
        //  2078: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  2081: aload           17
        //  2083: ldc_w           "browser"
        //  2086: aload           19
        //  2088: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2091: aload           18
        //  2093: ifnull          2363
        //  2096: new             Landroid/os/Bundle;
        //  2099: dup            
        //  2100: invokespecial   android/os/Bundle.<init>:()V
        //  2103: astore          19
        //  2105: aload           19
        //  2107: ldc_w           "runtime_free"
        //  2110: aload           18
        //  2112: ldc_w           "runtime_free_memory"
        //  2115: ldc2_w          -1
        //  2118: invokevirtual   android/os/Bundle.getLong:(Ljava/lang/String;J)J
        //  2121: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //  2124: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2127: aload           19
        //  2129: ldc_w           "runtime_max"
        //  2132: aload           18
        //  2134: ldc_w           "runtime_max_memory"
        //  2137: ldc2_w          -1
        //  2140: invokevirtual   android/os/Bundle.getLong:(Ljava/lang/String;J)J
        //  2143: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //  2146: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2149: aload           19
        //  2151: ldc_w           "runtime_total"
        //  2154: aload           18
        //  2156: ldc_w           "runtime_total_memory"
        //  2159: ldc2_w          -1
        //  2162: invokevirtual   android/os/Bundle.getLong:(Ljava/lang/String;J)J
        //  2165: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //  2168: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2171: aload           19
        //  2173: ldc_w           "web_view_count"
        //  2176: aload           18
        //  2178: ldc_w           "web_view_count"
        //  2181: iconst_0       
        //  2182: invokevirtual   android/os/Bundle.getInt:(Ljava/lang/String;I)I
        //  2185: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2188: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2191: aload           18
        //  2193: ldc_w           "debug_memory_info"
        //  2196: invokevirtual   android/os/Bundle.getParcelable:(Ljava/lang/String;)Landroid/os/Parcelable;
        //  2199: checkcast       Landroid/os/Debug$MemoryInfo;
        //  2202: astore          18
        //  2204: aload           18
        //  2206: ifnull          2353
        //  2209: aload           19
        //  2211: ldc_w           "debug_info_dalvik_private_dirty"
        //  2214: aload           18
        //  2216: getfield        android/os/Debug$MemoryInfo.dalvikPrivateDirty:I
        //  2219: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2222: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2225: aload           19
        //  2227: ldc_w           "debug_info_dalvik_pss"
        //  2230: aload           18
        //  2232: getfield        android/os/Debug$MemoryInfo.dalvikPss:I
        //  2235: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2238: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2241: aload           19
        //  2243: ldc_w           "debug_info_dalvik_shared_dirty"
        //  2246: aload           18
        //  2248: getfield        android/os/Debug$MemoryInfo.dalvikSharedDirty:I
        //  2251: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2254: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2257: aload           19
        //  2259: ldc_w           "debug_info_native_private_dirty"
        //  2262: aload           18
        //  2264: getfield        android/os/Debug$MemoryInfo.nativePrivateDirty:I
        //  2267: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2270: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2273: aload           19
        //  2275: ldc_w           "debug_info_native_pss"
        //  2278: aload           18
        //  2280: getfield        android/os/Debug$MemoryInfo.nativePss:I
        //  2283: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2286: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2289: aload           19
        //  2291: ldc_w           "debug_info_native_shared_dirty"
        //  2294: aload           18
        //  2296: getfield        android/os/Debug$MemoryInfo.nativeSharedDirty:I
        //  2299: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2302: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2305: aload           19
        //  2307: ldc_w           "debug_info_other_private_dirty"
        //  2310: aload           18
        //  2312: getfield        android/os/Debug$MemoryInfo.otherPrivateDirty:I
        //  2315: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2318: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2321: aload           19
        //  2323: ldc_w           "debug_info_other_pss"
        //  2326: aload           18
        //  2328: getfield        android/os/Debug$MemoryInfo.otherPss:I
        //  2331: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2334: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2337: aload           19
        //  2339: ldc_w           "debug_info_other_shared_dirty"
        //  2342: aload           18
        //  2344: getfield        android/os/Debug$MemoryInfo.otherSharedDirty:I
        //  2347: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  2350: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2353: aload           17
        //  2355: ldc_w           "android_mem_info"
        //  2358: aload           19
        //  2360: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2363: new             Landroid/os/Bundle;
        //  2366: dup            
        //  2367: invokespecial   android/os/Bundle.<init>:()V
        //  2370: astore          18
        //  2372: aload           18
        //  2374: ldc_w           "parental_controls"
        //  2377: aload           10
        //  2379: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2382: aload           15
        //  2384: getfield        com/google/android/gms/internal/ads/zzaga.zzcjv:Ljava/lang/String;
        //  2387: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  2390: ifne            2406
        //  2393: aload           18
        //  2395: ldc_w           "package_version"
        //  2398: aload           15
        //  2400: getfield        com/google/android/gms/internal/ads/zzaga.zzcjv:Ljava/lang/String;
        //  2403: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2406: aload           17
        //  2408: ldc_w           "play_store"
        //  2411: aload           18
        //  2413: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  2416: aload           11
        //  2418: ldc_w           "device"
        //  2421: aload           17
        //  2423: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2426: pop            
        //  2427: new             Landroid/os/Bundle;
        //  2430: dup            
        //  2431: invokespecial   android/os/Bundle.<init>:()V
        //  2434: astore          17
        //  2436: aload           17
        //  2438: ldc_w           "doritos"
        //  2441: aload_1        
        //  2442: getfield        com/google/android/gms/internal/ads/zzafl.zzcgp:Ljava/lang/String;
        //  2445: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2448: aload           17
        //  2450: ldc_w           "doritos_v2"
        //  2453: aload_1        
        //  2454: getfield        com/google/android/gms/internal/ads/zzafl.zzcgq:Ljava/lang/String;
        //  2457: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2460: getstatic       com/google/android/gms/internal/ads/zznk.zzayj:Lcom/google/android/gms/internal/ads/zzna;
        //  2463: astore          10
        //  2465: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  2468: aload           10
        //  2470: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  2473: checkcast       Ljava/lang/Boolean;
        //  2476: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  2479: ifeq            2552
        //  2482: aconst_null    
        //  2483: astore          10
        //  2485: iconst_0       
        //  2486: istore          7
        //  2488: aload_1        
        //  2489: getfield        com/google/android/gms/internal/ads/zzafl.zzcgr:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //  2492: ifnull          2513
        //  2495: aload_1        
        //  2496: getfield        com/google/android/gms/internal/ads/zzafl.zzcgr:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //  2499: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.getId:()Ljava/lang/String;
        //  2502: astore          10
        //  2504: aload_1        
        //  2505: getfield        com/google/android/gms/internal/ads/zzafl.zzcgr:Lcom/google/android/gms/ads/identifier/AdvertisingIdClient$Info;
        //  2508: invokevirtual   com/google/android/gms/ads/identifier/AdvertisingIdClient$Info.isLimitAdTrackingEnabled:()Z
        //  2511: istore          7
        //  2513: aload           10
        //  2515: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  2518: ifne            3644
        //  2521: aload           17
        //  2523: ldc_w           "rdid"
        //  2526: aload           10
        //  2528: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2531: aload           17
        //  2533: ldc_w           "is_lat"
        //  2536: iload           7
        //  2538: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  2541: aload           17
        //  2543: ldc_w           "idtype"
        //  2546: ldc_w           "adid"
        //  2549: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  2552: aload           11
        //  2554: ldc_w           "pii"
        //  2557: aload           17
        //  2559: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2562: pop            
        //  2563: aload           11
        //  2565: ldc_w           "platform"
        //  2568: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //  2571: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2574: pop            
        //  2575: aload           11
        //  2577: ldc_w           "submodel"
        //  2580: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //  2583: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2586: pop            
        //  2587: aload           14
        //  2589: ifnull          3674
        //  2592: aload           11
        //  2594: aload           14
        //  2596: invokestatic    com/google/android/gms/internal/ads/zzafs.zza:(Ljava/util/HashMap;Landroid/location/Location;)V
        //  2599: aload           12
        //  2601: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2604: iconst_2       
        //  2605: if_icmplt       2622
        //  2608: aload           11
        //  2610: ldc_w           "quality_signals"
        //  2613: aload           12
        //  2615: getfield        com/google/android/gms/internal/ads/zzaef.zzcda:Landroid/os/Bundle;
        //  2618: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2621: pop            
        //  2622: aload           12
        //  2624: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2627: iconst_4       
        //  2628: if_icmplt       2656
        //  2631: aload           12
        //  2633: getfield        com/google/android/gms/internal/ads/zzaef.zzcdd:Z
        //  2636: ifeq            2656
        //  2639: aload           11
        //  2641: ldc_w           "forceHttps"
        //  2644: aload           12
        //  2646: getfield        com/google/android/gms/internal/ads/zzaef.zzcdd:Z
        //  2649: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  2652: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2655: pop            
        //  2656: aload           16
        //  2658: ifnull          2672
        //  2661: aload           11
        //  2663: ldc_w           "content_info"
        //  2666: aload           16
        //  2668: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2671: pop            
        //  2672: aload           12
        //  2674: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2677: iconst_5       
        //  2678: if_icmplt       3713
        //  2681: aload           11
        //  2683: ldc_w           "u_sd"
        //  2686: aload           12
        //  2688: getfield        com/google/android/gms/internal/ads/zzaef.zzagu:F
        //  2691: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //  2694: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2697: pop            
        //  2698: aload           11
        //  2700: ldc_w           "sh"
        //  2703: aload           12
        //  2705: getfield        com/google/android/gms/internal/ads/zzaef.zzcdf:I
        //  2708: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  2711: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2714: pop            
        //  2715: aload           11
        //  2717: ldc_w           "sw"
        //  2720: aload           12
        //  2722: getfield        com/google/android/gms/internal/ads/zzaef.zzcde:I
        //  2725: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  2728: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2731: pop            
        //  2732: aload           12
        //  2734: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2737: bipush          6
        //  2739: if_icmplt       2795
        //  2742: aload           12
        //  2744: getfield        com/google/android/gms/internal/ads/zzaef.zzcdg:Ljava/lang/String;
        //  2747: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  2750: istore          7
        //  2752: iload           7
        //  2754: ifne            2778
        //  2757: aload           11
        //  2759: ldc_w           "view_hierarchy"
        //  2762: new             Lorg/json/JSONObject;
        //  2765: dup            
        //  2766: aload           12
        //  2768: getfield        com/google/android/gms/internal/ads/zzaef.zzcdg:Ljava/lang/String;
        //  2771: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //  2774: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2777: pop            
        //  2778: aload           11
        //  2780: ldc_w           "correlation_id"
        //  2783: aload           12
        //  2785: getfield        com/google/android/gms/internal/ads/zzaef.zzcdh:J
        //  2788: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //  2791: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2794: pop            
        //  2795: aload           12
        //  2797: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2800: bipush          7
        //  2802: if_icmplt       2819
        //  2805: aload           11
        //  2807: ldc_w           "request_id"
        //  2810: aload           12
        //  2812: getfield        com/google/android/gms/internal/ads/zzaef.zzcdi:Ljava/lang/String;
        //  2815: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2818: pop            
        //  2819: aload           12
        //  2821: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2824: bipush          12
        //  2826: if_icmplt       2854
        //  2829: aload           12
        //  2831: getfield        com/google/android/gms/internal/ads/zzaef.zzcdm:Ljava/lang/String;
        //  2834: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  2837: ifne            2854
        //  2840: aload           11
        //  2842: ldc_w           "anchor"
        //  2845: aload           12
        //  2847: getfield        com/google/android/gms/internal/ads/zzaef.zzcdm:Ljava/lang/String;
        //  2850: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2853: pop            
        //  2854: aload           12
        //  2856: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2859: bipush          13
        //  2861: if_icmplt       2881
        //  2864: aload           11
        //  2866: ldc_w           "android_app_volume"
        //  2869: aload           12
        //  2871: getfield        com/google/android/gms/internal/ads/zzaef.zzcdn:F
        //  2874: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //  2877: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2880: pop            
        //  2881: aload           12
        //  2883: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2886: bipush          18
        //  2888: if_icmplt       2908
        //  2891: aload           11
        //  2893: ldc_w           "android_app_muted"
        //  2896: aload           12
        //  2898: getfield        com/google/android/gms/internal/ads/zzaef.zzcdt:Z
        //  2901: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  2904: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2907: pop            
        //  2908: aload           12
        //  2910: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2913: bipush          14
        //  2915: if_icmplt       2943
        //  2918: aload           12
        //  2920: getfield        com/google/android/gms/internal/ads/zzaef.zzcdo:I
        //  2923: ifle            2943
        //  2926: aload           11
        //  2928: ldc_w           "target_api"
        //  2931: aload           12
        //  2933: getfield        com/google/android/gms/internal/ads/zzaef.zzcdo:I
        //  2936: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  2939: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2942: pop            
        //  2943: aload           12
        //  2945: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2948: bipush          15
        //  2950: if_icmplt       2977
        //  2953: aload           12
        //  2955: getfield        com/google/android/gms/internal/ads/zzaef.zzcdp:I
        //  2958: iconst_m1      
        //  2959: if_icmpne       3780
        //  2962: iconst_m1      
        //  2963: istore_2       
        //  2964: aload           11
        //  2966: ldc_w           "scroll_index"
        //  2969: iload_2        
        //  2970: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  2973: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  2976: pop            
        //  2977: aload           12
        //  2979: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  2982: bipush          16
        //  2984: if_icmplt       3004
        //  2987: aload           11
        //  2989: ldc_w           "_activity_context"
        //  2992: aload           12
        //  2994: getfield        com/google/android/gms/internal/ads/zzaef.zzcdq:Z
        //  2997: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  3000: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3003: pop            
        //  3004: aload           12
        //  3006: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  3009: bipush          18
        //  3011: if_icmplt       3066
        //  3014: aload           12
        //  3016: getfield        com/google/android/gms/internal/ads/zzaef.zzcdu:Ljava/lang/String;
        //  3019: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //  3022: istore          7
        //  3024: iload           7
        //  3026: ifne            3050
        //  3029: aload           11
        //  3031: ldc_w           "app_settings"
        //  3034: new             Lorg/json/JSONObject;
        //  3037: dup            
        //  3038: aload           12
        //  3040: getfield        com/google/android/gms/internal/ads/zzaef.zzcdu:Ljava/lang/String;
        //  3043: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //  3046: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3049: pop            
        //  3050: aload           11
        //  3052: ldc             "render_in_browser"
        //  3054: aload           12
        //  3056: getfield        com/google/android/gms/internal/ads/zzaef.zzbss:Z
        //  3059: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  3062: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3065: pop            
        //  3066: aload           12
        //  3068: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  3071: bipush          18
        //  3073: if_icmplt       3093
        //  3076: aload           11
        //  3078: ldc_w           "android_num_video_cache_tasks"
        //  3081: aload           12
        //  3083: getfield        com/google/android/gms/internal/ads/zzaef.zzcdv:I
        //  3086: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  3089: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3092: pop            
        //  3093: aload           12
        //  3095: getfield        com/google/android/gms/internal/ads/zzaef.zzacr:Lcom/google/android/gms/internal/ads/zzang;
        //  3098: astore          10
        //  3100: aload           12
        //  3102: getfield        com/google/android/gms/internal/ads/zzaef.zzceh:Z
        //  3105: istore          7
        //  3107: aload_1        
        //  3108: getfield        com/google/android/gms/internal/ads/zzafl.zzcgv:Z
        //  3111: istore          8
        //  3113: aload           12
        //  3115: getfield        com/google/android/gms/internal/ads/zzaef.zzcej:Z
        //  3118: istore          9
        //  3120: new             Landroid/os/Bundle;
        //  3123: dup            
        //  3124: invokespecial   android/os/Bundle.<init>:()V
        //  3127: astore_1       
        //  3128: new             Landroid/os/Bundle;
        //  3131: dup            
        //  3132: invokespecial   android/os/Bundle.<init>:()V
        //  3135: astore          14
        //  3137: aload           14
        //  3139: ldc_w           "cl"
        //  3142: ldc_w           "191880412"
        //  3145: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3148: aload           14
        //  3150: ldc_w           "rapid_rc"
        //  3153: ldc_w           "dev"
        //  3156: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3159: aload           14
        //  3161: ldc_w           "rapid_rollup"
        //  3164: ldc_w           "HEAD"
        //  3167: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3170: aload_1        
        //  3171: ldc_w           "build_meta"
        //  3174: aload           14
        //  3176: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  3179: getstatic       com/google/android/gms/internal/ads/zznk.zzbbm:Lcom/google/android/gms/internal/ads/zzna;
        //  3182: astore          14
        //  3184: aload_1        
        //  3185: ldc_w           "mf"
        //  3188: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3191: aload           14
        //  3193: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3196: checkcast       Ljava/lang/Boolean;
        //  3199: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  3202: invokestatic    java/lang/Boolean.toString:(Z)Ljava/lang/String;
        //  3205: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3208: aload_1        
        //  3209: ldc_w           "instant_app"
        //  3212: iload           7
        //  3214: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  3217: aload_1        
        //  3218: ldc_w           "lite"
        //  3221: aload           10
        //  3223: getfield        com/google/android/gms/internal/ads/zzang.zzcvh:Z
        //  3226: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  3229: aload_1        
        //  3230: ldc_w           "local_service"
        //  3233: iload           8
        //  3235: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  3238: aload_1        
        //  3239: ldc_w           "is_privileged_process"
        //  3242: iload           9
        //  3244: invokevirtual   android/os/Bundle.putBoolean:(Ljava/lang/String;Z)V
        //  3247: aload           11
        //  3249: ldc_w           "sdk_env"
        //  3252: aload_1        
        //  3253: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3256: pop            
        //  3257: aload           11
        //  3259: ldc_w           "cache_state"
        //  3262: aload           13
        //  3264: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3267: pop            
        //  3268: aload           12
        //  3270: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  3273: bipush          19
        //  3275: if_icmplt       3292
        //  3278: aload           11
        //  3280: ldc_w           "gct"
        //  3283: aload           12
        //  3285: getfield        com/google/android/gms/internal/ads/zzaef.zzcdx:Ljava/lang/String;
        //  3288: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3291: pop            
        //  3292: aload           12
        //  3294: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  3297: bipush          21
        //  3299: if_icmplt       3322
        //  3302: aload           12
        //  3304: getfield        com/google/android/gms/internal/ads/zzaef.zzcdy:Z
        //  3307: ifeq            3322
        //  3310: aload           11
        //  3312: ldc_w           "de"
        //  3315: ldc_w           "1"
        //  3318: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3321: pop            
        //  3322: getstatic       com/google/android/gms/internal/ads/zznk.zzayy:Lcom/google/android/gms/internal/ads/zzna;
        //  3325: astore_1       
        //  3326: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3329: aload_1        
        //  3330: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3333: checkcast       Ljava/lang/Boolean;
        //  3336: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  3339: ifeq            3423
        //  3342: aload           12
        //  3344: getfield        com/google/android/gms/internal/ads/zzaef.zzacv:Lcom/google/android/gms/internal/ads/zzjn;
        //  3347: getfield        com/google/android/gms/internal/ads/zzjn.zzarb:Ljava/lang/String;
        //  3350: astore_1       
        //  3351: aload_1        
        //  3352: ldc_w           "interstitial_mb"
        //  3355: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  3358: ifne            4052
        //  3361: aload_1        
        //  3362: ldc_w           "reward_mb"
        //  3365: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  3368: ifeq            4057
        //  3371: goto            4052
        //  3374: aload           12
        //  3376: getfield        com/google/android/gms/internal/ads/zzaef.zzcdz:Landroid/os/Bundle;
        //  3379: astore_1       
        //  3380: aload_1        
        //  3381: ifnull          4062
        //  3384: iconst_1       
        //  3385: istore_3       
        //  3386: iload_2        
        //  3387: ifeq            3423
        //  3390: iload_3        
        //  3391: ifeq            3423
        //  3394: new             Landroid/os/Bundle;
        //  3397: dup            
        //  3398: invokespecial   android/os/Bundle.<init>:()V
        //  3401: astore          10
        //  3403: aload           10
        //  3405: ldc_w           "interstitial_pool"
        //  3408: aload_1        
        //  3409: invokevirtual   android/os/Bundle.putBundle:(Ljava/lang/String;Landroid/os/Bundle;)V
        //  3412: aload           11
        //  3414: ldc_w           "counters"
        //  3417: aload           10
        //  3419: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3422: pop            
        //  3423: aload           12
        //  3425: getfield        com/google/android/gms/internal/ads/zzaef.zzcea:Ljava/lang/String;
        //  3428: ifnull          3445
        //  3431: aload           11
        //  3433: ldc_w           "gmp_app_id"
        //  3436: aload           12
        //  3438: getfield        com/google/android/gms/internal/ads/zzaef.zzcea:Ljava/lang/String;
        //  3441: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3444: pop            
        //  3445: aload           12
        //  3447: getfield        com/google/android/gms/internal/ads/zzaef.zzceb:Ljava/lang/String;
        //  3450: ifnull          3819
        //  3453: ldc_w           "TIME_OUT"
        //  3456: aload           12
        //  3458: getfield        com/google/android/gms/internal/ads/zzaef.zzceb:Ljava/lang/String;
        //  3461: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //  3464: ifeq            3802
        //  3467: getstatic       com/google/android/gms/internal/ads/zznk.zzaxt:Lcom/google/android/gms/internal/ads/zzna;
        //  3470: astore_1       
        //  3471: aload           11
        //  3473: ldc_w           "sai_timeout"
        //  3476: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3479: aload_1        
        //  3480: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3483: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3486: pop            
        //  3487: aload           12
        //  3489: getfield        com/google/android/gms/internal/ads/zzaef.zzcec:Ljava/lang/String;
        //  3492: ifnull          3509
        //  3495: aload           11
        //  3497: ldc_w           "fbs_aeid"
        //  3500: aload           12
        //  3502: getfield        com/google/android/gms/internal/ads/zzaef.zzcec:Ljava/lang/String;
        //  3505: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3508: pop            
        //  3509: aload           12
        //  3511: getfield        com/google/android/gms/internal/ads/zzaef.versionCode:I
        //  3514: bipush          24
        //  3516: if_icmplt       3536
        //  3519: aload           11
        //  3521: ldc_w           "disable_ml"
        //  3524: aload           12
        //  3526: getfield        com/google/android/gms/internal/ads/zzaef.zzcei:Z
        //  3529: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //  3532: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3535: pop            
        //  3536: getstatic       com/google/android/gms/internal/ads/zznk.zzavo:Lcom/google/android/gms/internal/ads/zzna;
        //  3539: astore_1       
        //  3540: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3543: aload_1        
        //  3544: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3547: checkcast       Ljava/lang/String;
        //  3550: astore          10
        //  3552: aload           10
        //  3554: ifnull          3843
        //  3557: aload           10
        //  3559: invokevirtual   java/lang/String.isEmpty:()Z
        //  3562: ifne            3843
        //  3565: getstatic       android/os/Build$VERSION.SDK_INT:I
        //  3568: istore_2       
        //  3569: getstatic       com/google/android/gms/internal/ads/zznk.zzavp:Lcom/google/android/gms/internal/ads/zzna;
        //  3572: astore_1       
        //  3573: iload_2        
        //  3574: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3577: aload_1        
        //  3578: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3581: checkcast       Ljava/lang/Integer;
        //  3584: invokevirtual   java/lang/Integer.intValue:()I
        //  3587: if_icmplt       3843
        //  3590: new             Ljava/util/HashMap;
        //  3593: dup            
        //  3594: invokespecial   java/util/HashMap.<init>:()V
        //  3597: astore_1       
        //  3598: aload           10
        //  3600: ldc_w           ","
        //  3603: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //  3606: astore          10
        //  3608: aload           10
        //  3610: arraylength    
        //  3611: istore_3       
        //  3612: iconst_0       
        //  3613: istore_2       
        //  3614: iload_2        
        //  3615: iload_3        
        //  3616: if_icmpge       3833
        //  3619: aload           10
        //  3621: iload_2        
        //  3622: aaload         
        //  3623: astore          13
        //  3625: aload_1        
        //  3626: aload           13
        //  3628: aload           13
        //  3630: invokestatic    com/google/android/gms/internal/ads/zzams.zzdd:(Ljava/lang/String;)Ljava/util/List;
        //  3633: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3636: pop            
        //  3637: iload_2        
        //  3638: iconst_1       
        //  3639: iadd           
        //  3640: istore_2       
        //  3641: goto            3614
        //  3644: invokestatic    com/google/android/gms/internal/ads/zzkb.zzif:()Lcom/google/android/gms/internal/ads/zzamu;
        //  3647: pop            
        //  3648: aload           17
        //  3650: ldc_w           "pdid"
        //  3653: aload_0        
        //  3654: invokestatic    com/google/android/gms/internal/ads/zzamu.zzbd:(Landroid/content/Context;)Ljava/lang/String;
        //  3657: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3660: aload           17
        //  3662: ldc_w           "pdidtype"
        //  3665: ldc_w           "ssaid"
        //  3668: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //  3671: goto            2552
        //  3674: aload           12
        //  3676: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //  3679: getfield        com/google/android/gms/internal/ads/zzjj.versionCode:I
        //  3682: iconst_2       
        //  3683: if_icmplt       2599
        //  3686: aload           12
        //  3688: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //  3691: getfield        com/google/android/gms/internal/ads/zzjj.zzaqe:Landroid/location/Location;
        //  3694: ifnull          2599
        //  3697: aload           11
        //  3699: aload           12
        //  3701: getfield        com/google/android/gms/internal/ads/zzaef.zzccv:Lcom/google/android/gms/internal/ads/zzjj;
        //  3704: getfield        com/google/android/gms/internal/ads/zzjj.zzaqe:Landroid/location/Location;
        //  3707: invokestatic    com/google/android/gms/internal/ads/zzafs.zza:(Ljava/util/HashMap;Landroid/location/Location;)V
        //  3710: goto            2599
        //  3713: aload           11
        //  3715: ldc_w           "u_sd"
        //  3718: aload           15
        //  3720: getfield        com/google/android/gms/internal/ads/zzaga.zzagu:F
        //  3723: invokestatic    java/lang/Float.valueOf:(F)Ljava/lang/Float;
        //  3726: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3729: pop            
        //  3730: aload           11
        //  3732: ldc_w           "sh"
        //  3735: aload           15
        //  3737: getfield        com/google/android/gms/internal/ads/zzaga.zzcdf:I
        //  3740: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  3743: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3746: pop            
        //  3747: aload           11
        //  3749: ldc_w           "sw"
        //  3752: aload           15
        //  3754: getfield        com/google/android/gms/internal/ads/zzaga.zzcde:I
        //  3757: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //  3760: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3763: pop            
        //  3764: goto            2732
        //  3767: astore          10
        //  3769: ldc_w           "Problem serializing view hierarchy to JSON"
        //  3772: aload           10
        //  3774: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  3777: goto            2778
        //  3780: aload           12
        //  3782: getfield        com/google/android/gms/internal/ads/zzaef.zzcdp:I
        //  3785: istore_2       
        //  3786: goto            2964
        //  3789: astore          10
        //  3791: ldc_w           "Problem creating json from app settings"
        //  3794: aload           10
        //  3796: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  3799: goto            3050
        //  3802: aload           11
        //  3804: ldc_w           "fbs_aiid"
        //  3807: aload           12
        //  3809: getfield        com/google/android/gms/internal/ads/zzaef.zzceb:Ljava/lang/String;
        //  3812: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3815: pop            
        //  3816: goto            3487
        //  3819: aload           11
        //  3821: ldc_w           "fbs_aiid"
        //  3824: ldc             ""
        //  3826: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3829: pop            
        //  3830: goto            3487
        //  3833: aload           11
        //  3835: ldc_w           "video_decoders"
        //  3838: aload_1        
        //  3839: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3842: pop            
        //  3843: getstatic       com/google/android/gms/internal/ads/zznk.zzbet:Lcom/google/android/gms/internal/ads/zzna;
        //  3846: astore_1       
        //  3847: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  3850: aload_1        
        //  3851: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  3854: checkcast       Ljava/lang/Boolean;
        //  3857: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //  3860: ifeq            3879
        //  3863: aload           11
        //  3865: ldc_w           "omid_v"
        //  3868: invokestatic    com/google/android/gms/ads/internal/zzbv.zzfa:()Lcom/google/android/gms/internal/ads/zzaan;
        //  3871: aload_0        
        //  3872: invokevirtual   com/google/android/gms/internal/ads/zzaan.getVersion:(Landroid/content/Context;)Ljava/lang/String;
        //  3875: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3878: pop            
        //  3879: aload           12
        //  3881: getfield        com/google/android/gms/internal/ads/zzaef.zzcek:Ljava/util/ArrayList;
        //  3884: ifnull          3912
        //  3887: aload           12
        //  3889: getfield        com/google/android/gms/internal/ads/zzaef.zzcek:Ljava/util/ArrayList;
        //  3892: invokevirtual   java/util/ArrayList.isEmpty:()Z
        //  3895: ifne            3912
        //  3898: aload           11
        //  3900: ldc_w           "android_permissions"
        //  3903: aload           12
        //  3905: getfield        com/google/android/gms/internal/ads/zzaef.zzcek:Ljava/util/ArrayList;
        //  3908: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //  3911: pop            
        //  3912: iconst_2       
        //  3913: invokestatic    com/google/android/gms/internal/ads/zzakb.isLoggable:(I)Z
        //  3916: ifeq            3954
        //  3919: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //  3922: aload           11
        //  3924: invokevirtual   com/google/android/gms/internal/ads/zzakk.zzn:(Ljava/util/Map;)Lorg/json/JSONObject;
        //  3927: iconst_2       
        //  3928: invokevirtual   org/json/JSONObject.toString:(I)Ljava/lang/String;
        //  3931: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  3934: astore_0       
        //  3935: aload_0        
        //  3936: invokevirtual   java/lang/String.length:()I
        //  3939: ifeq            3963
        //  3942: ldc_w           "Ad Request JSON: "
        //  3945: aload_0        
        //  3946: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  3949: astore_0       
        //  3950: aload_0        
        //  3951: invokestatic    com/google/android/gms/internal/ads/zzakb.v:(Ljava/lang/String;)V
        //  3954: invokestatic    com/google/android/gms/ads/internal/zzbv.zzek:()Lcom/google/android/gms/internal/ads/zzakk;
        //  3957: aload           11
        //  3959: invokevirtual   com/google/android/gms/internal/ads/zzakk.zzn:(Ljava/util/Map;)Lorg/json/JSONObject;
        //  3962: areturn        
        //  3963: new             Ljava/lang/String;
        //  3966: dup            
        //  3967: ldc_w           "Ad Request JSON: "
        //  3970: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  3973: astore_0       
        //  3974: goto            3950
        //  3977: new             Ljava/lang/String;
        //  3980: dup            
        //  3981: ldc_w           "Problem serializing ad request to JSON: "
        //  3984: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  3987: astore_0       
        //  3988: goto            741
        //  3991: iload_2        
        //  3992: iconst_1       
        //  3993: iadd           
        //  3994: istore_2       
        //  3995: goto            677
        //  3998: iload           4
        //  4000: ifeq            4008
        //  4003: iload           5
        //  4005: ifne            589
        //  4008: iload_2        
        //  4009: iconst_1       
        //  4010: iadd           
        //  4011: istore_2       
        //  4012: iload           5
        //  4014: istore_3       
        //  4015: iload           4
        //  4017: istore          5
        //  4019: goto            769
        //  4022: ldc_w           "not_set"
        //  4025: astore          10
        //  4027: goto            1066
        //  4030: ldc             "portrait"
        //  4032: astore          10
        //  4034: goto            1066
        //  4037: ldc             "landscape"
        //  4039: astore          10
        //  4041: goto            1066
        //  4044: ldc_w           "any"
        //  4047: astore          10
        //  4049: goto            1066
        //  4052: iconst_1       
        //  4053: istore_2       
        //  4054: goto            3374
        //  4057: iconst_0       
        //  4058: istore_2       
        //  4059: goto            3374
        //  4062: iconst_0       
        //  4063: istore_3       
        //  4064: goto            3386
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  30     92     717    747    Lorg/json/JSONException;
        //  92     114    717    747    Lorg/json/JSONException;
        //  114    126    717    747    Lorg/json/JSONException;
        //  131    142    717    747    Lorg/json/JSONException;
        //  142    181    717    747    Lorg/json/JSONException;
        //  181    203    717    747    Lorg/json/JSONException;
        //  203    229    717    747    Lorg/json/JSONException;
        //  229    251    717    747    Lorg/json/JSONException;
        //  251    277    717    747    Lorg/json/JSONException;
        //  277    320    717    747    Lorg/json/JSONException;
        //  320    350    717    747    Lorg/json/JSONException;
        //  350    375    717    747    Lorg/json/JSONException;
        //  375    406    717    747    Lorg/json/JSONException;
        //  406    437    717    747    Lorg/json/JSONException;
        //  437    459    717    747    Lorg/json/JSONException;
        //  459    481    717    747    Lorg/json/JSONException;
        //  481    513    717    747    Lorg/json/JSONException;
        //  513    540    717    747    Lorg/json/JSONException;
        //  540    589    717    747    Lorg/json/JSONException;
        //  589    613    717    747    Lorg/json/JSONException;
        //  613    638    717    747    Lorg/json/JSONException;
        //  638    673    717    747    Lorg/json/JSONException;
        //  689    697    717    747    Lorg/json/JSONException;
        //  702    714    717    747    Lorg/json/JSONException;
        //  747    762    717    747    Lorg/json/JSONException;
        //  785    793    717    747    Lorg/json/JSONException;
        //  802    816    717    747    Lorg/json/JSONException;
        //  822    830    717    747    Lorg/json/JSONException;
        //  837    847    717    747    Lorg/json/JSONException;
        //  853    870    717    747    Lorg/json/JSONException;
        //  870    894    717    747    Lorg/json/JSONException;
        //  894    936    717    747    Lorg/json/JSONException;
        //  936    944    717    747    Lorg/json/JSONException;
        //  947    954    717    747    Lorg/json/JSONException;
        //  957    964    717    747    Lorg/json/JSONException;
        //  971    989    717    747    Lorg/json/JSONException;
        //  989    999    717    747    Lorg/json/JSONException;
        //  999    1010   717    747    Lorg/json/JSONException;
        //  1010   1056   717    747    Lorg/json/JSONException;
        //  1066   1104   717    747    Lorg/json/JSONException;
        //  1104   1131   717    747    Lorg/json/JSONException;
        //  1131   1141   717    747    Lorg/json/JSONException;
        //  1146   1167   1280   1293   Lorg/json/JSONException;
        //  1167   1200   717    747    Lorg/json/JSONException;
        //  1200   1244   717    747    Lorg/json/JSONException;
        //  1247   1280   717    747    Lorg/json/JSONException;
        //  1282   1290   717    747    Lorg/json/JSONException;
        //  1293   1315   717    747    Lorg/json/JSONException;
        //  1318   1342   717    747    Lorg/json/JSONException;
        //  1342   1377   717    747    Lorg/json/JSONException;
        //  1377   1418   717    747    Lorg/json/JSONException;
        //  1418   1477   717    747    Lorg/json/JSONException;
        //  1477   1630   717    747    Lorg/json/JSONException;
        //  1630   1665   717    747    Lorg/json/JSONException;
        //  1665   1686   717    747    Lorg/json/JSONException;
        //  1686   1759   717    747    Lorg/json/JSONException;
        //  1759   1803   717    747    Lorg/json/JSONException;
        //  1803   1986   717    747    Lorg/json/JSONException;
        //  1991   2049   717    747    Lorg/json/JSONException;
        //  2049   2091   717    747    Lorg/json/JSONException;
        //  2096   2204   717    747    Lorg/json/JSONException;
        //  2209   2353   717    747    Lorg/json/JSONException;
        //  2353   2363   717    747    Lorg/json/JSONException;
        //  2363   2406   717    747    Lorg/json/JSONException;
        //  2406   2482   717    747    Lorg/json/JSONException;
        //  2488   2513   717    747    Lorg/json/JSONException;
        //  2513   2552   717    747    Lorg/json/JSONException;
        //  2552   2587   717    747    Lorg/json/JSONException;
        //  2592   2599   717    747    Lorg/json/JSONException;
        //  2599   2622   717    747    Lorg/json/JSONException;
        //  2622   2656   717    747    Lorg/json/JSONException;
        //  2661   2672   717    747    Lorg/json/JSONException;
        //  2672   2732   717    747    Lorg/json/JSONException;
        //  2732   2752   717    747    Lorg/json/JSONException;
        //  2757   2778   3767   3780   Lorg/json/JSONException;
        //  2778   2795   717    747    Lorg/json/JSONException;
        //  2795   2819   717    747    Lorg/json/JSONException;
        //  2819   2854   717    747    Lorg/json/JSONException;
        //  2854   2881   717    747    Lorg/json/JSONException;
        //  2881   2908   717    747    Lorg/json/JSONException;
        //  2908   2943   717    747    Lorg/json/JSONException;
        //  2943   2962   717    747    Lorg/json/JSONException;
        //  2964   2977   717    747    Lorg/json/JSONException;
        //  2977   3004   717    747    Lorg/json/JSONException;
        //  3004   3024   717    747    Lorg/json/JSONException;
        //  3029   3050   3789   3802   Lorg/json/JSONException;
        //  3050   3066   717    747    Lorg/json/JSONException;
        //  3066   3093   717    747    Lorg/json/JSONException;
        //  3093   3292   717    747    Lorg/json/JSONException;
        //  3292   3322   717    747    Lorg/json/JSONException;
        //  3322   3371   717    747    Lorg/json/JSONException;
        //  3374   3380   717    747    Lorg/json/JSONException;
        //  3394   3423   717    747    Lorg/json/JSONException;
        //  3423   3445   717    747    Lorg/json/JSONException;
        //  3445   3487   717    747    Lorg/json/JSONException;
        //  3487   3509   717    747    Lorg/json/JSONException;
        //  3509   3536   717    747    Lorg/json/JSONException;
        //  3536   3552   717    747    Lorg/json/JSONException;
        //  3557   3612   717    747    Lorg/json/JSONException;
        //  3625   3637   717    747    Lorg/json/JSONException;
        //  3644   3671   717    747    Lorg/json/JSONException;
        //  3674   3710   717    747    Lorg/json/JSONException;
        //  3713   3764   717    747    Lorg/json/JSONException;
        //  3769   3777   717    747    Lorg/json/JSONException;
        //  3780   3786   717    747    Lorg/json/JSONException;
        //  3791   3799   717    747    Lorg/json/JSONException;
        //  3802   3816   717    747    Lorg/json/JSONException;
        //  3819   3830   717    747    Lorg/json/JSONException;
        //  3833   3843   717    747    Lorg/json/JSONException;
        //  3843   3879   717    747    Lorg/json/JSONException;
        //  3879   3912   717    747    Lorg/json/JSONException;
        //  3912   3950   717    747    Lorg/json/JSONException;
        //  3950   3954   717    747    Lorg/json/JSONException;
        //  3954   3963   717    747    Lorg/json/JSONException;
        //  3963   3974   717    747    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_1167:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private static void zza(final HashMap<String, Object> hashMap, final Location location) {
        final HashMap<String, Float> hashMap2 = new HashMap<String, Float>();
        final float accuracy = location.getAccuracy();
        final long time = location.getTime();
        final long n = (long)(location.getLatitude() * 1.0E7);
        final long n2 = (long)(location.getLongitude() * 1.0E7);
        hashMap2.put("radius", accuracy * 1000.0f);
        hashMap2.put("lat", Float.valueOf(n));
        hashMap2.put("long", Float.valueOf(n2));
        hashMap2.put("time", Float.valueOf(time * 1000L));
        hashMap.put("uule", hashMap2);
    }
    
    public static JSONObject zzb(final zzaej zzaej) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        if (zzaej.zzbyq != null) {
            jsonObject.put("ad_base_url", (Object)zzaej.zzbyq);
        }
        if (zzaej.zzcet != null) {
            jsonObject.put("ad_size", (Object)zzaej.zzcet);
        }
        jsonObject.put("native", zzaej.zzare);
        if (zzaej.zzare) {
            jsonObject.put("ad_json", (Object)zzaej.zzceo);
        }
        else {
            jsonObject.put("ad_html", (Object)zzaej.zzceo);
        }
        if (zzaej.zzcev != null) {
            jsonObject.put("debug_dialog", (Object)zzaej.zzcev);
        }
        if (zzaej.zzcfl != null) {
            jsonObject.put("debug_signals", (Object)zzaej.zzcfl);
        }
        if (zzaej.zzcep != -1L) {
            jsonObject.put("interstitial_timeout", zzaej.zzcep / 1000.0);
        }
        if (zzaej.orientation == zzbv.zzem().zzrm()) {
            jsonObject.put("orientation", (Object)"portrait");
        }
        else if (zzaej.orientation == zzbv.zzem().zzrl()) {
            jsonObject.put("orientation", (Object)"landscape");
        }
        if (zzaej.zzbsn != null) {
            jsonObject.put("click_urls", (Object)zzm(zzaej.zzbsn));
        }
        if (zzaej.zzbso != null) {
            jsonObject.put("impression_urls", (Object)zzm(zzaej.zzbso));
        }
        if (zzaej.zzbsp != null) {
            jsonObject.put("downloaded_impression_urls", (Object)zzm(zzaej.zzbsp));
        }
        if (zzaej.zzces != null) {
            jsonObject.put("manual_impression_urls", (Object)zzm(zzaej.zzces));
        }
        if (zzaej.zzcey != null) {
            jsonObject.put("active_view", (Object)zzaej.zzcey);
        }
        jsonObject.put("ad_is_javascript", zzaej.zzcew);
        if (zzaej.zzcex != null) {
            jsonObject.put("ad_passback_url", (Object)zzaej.zzcex);
        }
        jsonObject.put("mediation", zzaej.zzceq);
        jsonObject.put("custom_render_allowed", zzaej.zzcez);
        jsonObject.put("content_url_opted_out", zzaej.zzcfa);
        jsonObject.put("content_vertical_opted_out", zzaej.zzcfm);
        jsonObject.put("prefetch", zzaej.zzcfb);
        if (zzaej.zzbsu != -1L) {
            jsonObject.put("refresh_interval_milliseconds", zzaej.zzbsu);
        }
        if (zzaej.zzcer != -1L) {
            jsonObject.put("mediation_config_cache_time_milliseconds", zzaej.zzcer);
        }
        if (!TextUtils.isEmpty((CharSequence)zzaej.zzamj)) {
            jsonObject.put("gws_query_id", (Object)zzaej.zzamj);
        }
        String s;
        if (zzaej.zzarf) {
            s = "height";
        }
        else {
            s = "";
        }
        jsonObject.put("fluid", (Object)s);
        jsonObject.put("native_express", zzaej.zzarg);
        if (zzaej.zzcff != null) {
            jsonObject.put("video_start_urls", (Object)zzm(zzaej.zzcff));
        }
        if (zzaej.zzcfg != null) {
            jsonObject.put("video_complete_urls", (Object)zzm(zzaej.zzcfg));
        }
        if (zzaej.zzcfe != null) {
            final zzaig zzcfe = zzaej.zzcfe;
            final JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("rb_type", (Object)zzcfe.type);
            jsonObject2.put("rb_amount", zzcfe.zzcmk);
            final JSONArray jsonArray = new JSONArray();
            jsonArray.put((Object)jsonObject2);
            jsonObject.put("rewards", (Object)jsonArray);
        }
        jsonObject.put("use_displayed_impression", zzaej.zzcfh);
        jsonObject.put("auto_protection_configuration", (Object)zzaej.zzcfi);
        jsonObject.put("render_in_browser", zzaej.zzbss);
        jsonObject.put("disable_closable_area", zzaej.zzzm);
        return jsonObject;
    }
    
    @Nullable
    private static JSONArray zzm(final List<String> list) throws JSONException {
        final JSONArray jsonArray = new JSONArray();
        final Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            jsonArray.put((Object)iterator.next());
        }
        return jsonArray;
    }
    
    private static Integer zzv(final boolean b) {
        int n;
        if (b) {
            n = 1;
        }
        else {
            n = 0;
        }
        return n;
    }
}
