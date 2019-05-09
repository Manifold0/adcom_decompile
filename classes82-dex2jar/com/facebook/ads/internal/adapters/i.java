// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.adapters;

import java.util.StringTokenizer;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.a.b;
import android.util.Log;
import com.facebook.ads.internal.w.b.z;
import java.util.Iterator;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import com.facebook.ads.internal.protocol.a;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.w.b.k;
import java.util.Map;
import org.json.JSONObject;
import java.util.List;
import com.facebook.ads.internal.t.j;
import com.facebook.ads.internal.t.l;
import java.util.Collection;
import com.facebook.ads.internal.a.d;
import com.facebook.ads.internal.t.g;
import java.util.HashMap;
import android.net.Uri;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.a.e;

public class i implements a, AdAdapter
{
    private static final String a;
    private int A;
    private String B;
    private boolean C;
    private boolean D;
    private boolean E;
    private boolean F;
    private boolean G;
    @Nullable
    private c H;
    private com.facebook.ads.internal.t.e.c I;
    private Context b;
    private q c;
    private Uri d;
    private HashMap<String, String> e;
    private g f;
    private g g;
    private com.facebook.ads.internal.t.i h;
    private String i;
    private d j;
    private Collection<String> k;
    private boolean l;
    private boolean m;
    private int n;
    private int o;
    private int p;
    private int q;
    private String r;
    private String s;
    private l t;
    private int u;
    private g v;
    private String w;
    private j x;
    private List<com.facebook.ads.internal.t.e> y;
    private int z;
    
    static {
        a = i.class.getSimpleName();
    }
    
    public i() {
        this.e = new HashMap<String, String>();
        this.u = 200;
        this.z = -1;
    }
    
    private void C() {
        if (!this.G) {
            if (this.H != null) {
                this.H.a(this.i);
            }
            this.G = true;
        }
    }
    
    private void a(final JSONObject p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: aload_0        
        //     4: getfield        com/facebook/ads/internal/adapters/i.D:Z
        //     7: ifeq            20
        //    10: new             Ljava/lang/IllegalStateException;
        //    13: dup            
        //    14: ldc             "Adapter already loaded data"
        //    16: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    19: athrow         
        //    20: aload_1        
        //    21: ifnonnull       25
        //    24: return         
        //    25: aload_0        
        //    26: getfield        com/facebook/ads/internal/adapters/i.b:Landroid/content/Context;
        //    29: ldc             "Audience Network Loaded"
        //    31: invokestatic    com/facebook/ads/internal/w/b/c.a:(Landroid/content/Context;Ljava/lang/String;)V
        //    34: aload_0        
        //    35: aload_2        
        //    36: putfield        com/facebook/ads/internal/adapters/i.B:Ljava/lang/String;
        //    39: aload_1        
        //    40: ldc             "fbad_command"
        //    42: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //    45: astore          6
        //    47: aload           6
        //    49: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    52: ifeq            177
        //    55: aconst_null    
        //    56: astore          6
        //    58: aload_0        
        //    59: aload           6
        //    61: putfield        com/facebook/ads/internal/adapters/i.d:Landroid/net/Uri;
        //    64: bipush          10
        //    66: anewarray       Ljava/lang/String;
        //    69: astore          6
        //    71: aload           6
        //    73: iconst_0       
        //    74: ldc             "advertiser_name"
        //    76: aastore        
        //    77: aload           6
        //    79: iconst_1       
        //    80: ldc             "title"
        //    82: aastore        
        //    83: aload           6
        //    85: iconst_2       
        //    86: ldc             "subtitle"
        //    88: aastore        
        //    89: aload           6
        //    91: iconst_3       
        //    92: ldc             "headline"
        //    94: aastore        
        //    95: aload           6
        //    97: iconst_4       
        //    98: ldc             "body"
        //   100: aastore        
        //   101: aload           6
        //   103: iconst_5       
        //   104: ldc             "social_context"
        //   106: aastore        
        //   107: aload           6
        //   109: bipush          6
        //   111: ldc             "link_description"
        //   113: aastore        
        //   114: aload           6
        //   116: bipush          7
        //   118: ldc             "sponsored_translation"
        //   120: aastore        
        //   121: aload           6
        //   123: bipush          8
        //   125: ldc             "ad_translation"
        //   127: aastore        
        //   128: aload           6
        //   130: bipush          9
        //   132: ldc             "promoted_translation"
        //   134: aastore        
        //   135: aload           6
        //   137: arraylength    
        //   138: istore          4
        //   140: iconst_0       
        //   141: istore_3       
        //   142: iload_3        
        //   143: iload           4
        //   145: if_icmpge       187
        //   148: aload           6
        //   150: iload_3        
        //   151: aaload         
        //   152: astore          8
        //   154: aload_0        
        //   155: getfield        com/facebook/ads/internal/adapters/i.e:Ljava/util/HashMap;
        //   158: aload           8
        //   160: aload_1        
        //   161: aload           8
        //   163: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   166: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   169: pop            
        //   170: iload_3        
        //   171: iconst_1       
        //   172: iadd           
        //   173: istore_3       
        //   174: goto            142
        //   177: aload           6
        //   179: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   182: astore          6
        //   184: goto            58
        //   187: aload_1        
        //   188: ldc             "call_to_action"
        //   190: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   193: astore          6
        //   195: aload           6
        //   197: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   200: ifne            215
        //   203: aload_0        
        //   204: getfield        com/facebook/ads/internal/adapters/i.e:Ljava/util/HashMap;
        //   207: ldc             "call_to_action"
        //   209: aload           6
        //   211: invokevirtual   java/util/HashMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   214: pop            
        //   215: aload_0        
        //   216: aload_1        
        //   217: ldc             "icon"
        //   219: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   222: invokestatic    com/facebook/ads/internal/t/g.a:(Lorg/json/JSONObject;)Lcom/facebook/ads/internal/t/g;
        //   225: putfield        com/facebook/ads/internal/adapters/i.f:Lcom/facebook/ads/internal/t/g;
        //   228: aload_0        
        //   229: aload_1        
        //   230: ldc             "image"
        //   232: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   235: invokestatic    com/facebook/ads/internal/t/g.a:(Lorg/json/JSONObject;)Lcom/facebook/ads/internal/t/g;
        //   238: putfield        com/facebook/ads/internal/adapters/i.g:Lcom/facebook/ads/internal/t/g;
        //   241: aload_0        
        //   242: aload_1        
        //   243: ldc             "star_rating"
        //   245: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   248: invokestatic    com/facebook/ads/internal/t/i.a:(Lorg/json/JSONObject;)Lcom/facebook/ads/internal/t/i;
        //   251: putfield        com/facebook/ads/internal/adapters/i.h:Lcom/facebook/ads/internal/t/i;
        //   254: aload_0        
        //   255: aload_1        
        //   256: ldc             "used_report_url"
        //   258: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   261: putfield        com/facebook/ads/internal/adapters/i.i:Ljava/lang/String;
        //   264: aload_0        
        //   265: aload_1        
        //   266: ldc             "enable_view_log"
        //   268: invokevirtual   org/json/JSONObject.optBoolean:(Ljava/lang/String;)Z
        //   271: putfield        com/facebook/ads/internal/adapters/i.l:Z
        //   274: aload_0        
        //   275: aload_1        
        //   276: ldc             "enable_snapshot_log"
        //   278: invokevirtual   org/json/JSONObject.optBoolean:(Ljava/lang/String;)Z
        //   281: putfield        com/facebook/ads/internal/adapters/i.m:Z
        //   284: aload_0        
        //   285: aload_1        
        //   286: ldc             "snapshot_log_delay_second"
        //   288: iconst_4       
        //   289: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   292: putfield        com/facebook/ads/internal/adapters/i.n:I
        //   295: aload_0        
        //   296: aload_1        
        //   297: ldc             "snapshot_compress_quality"
        //   299: iconst_0       
        //   300: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   303: putfield        com/facebook/ads/internal/adapters/i.o:I
        //   306: aload_0        
        //   307: aload_1        
        //   308: ldc             "viewability_check_initial_delay"
        //   310: iconst_0       
        //   311: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   314: putfield        com/facebook/ads/internal/adapters/i.p:I
        //   317: aload_0        
        //   318: aload_1        
        //   319: ldc             "viewability_check_interval"
        //   321: sipush          1000
        //   324: invokevirtual   org/json/JSONObject.optInt:(Ljava/lang/String;I)I
        //   327: putfield        com/facebook/ads/internal/adapters/i.q:I
        //   330: aload_1        
        //   331: ldc             "ad_choices_icon"
        //   333: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   336: astore          8
        //   338: aload_1        
        //   339: ldc             "native_ui_config"
        //   341: invokevirtual   org/json/JSONObject.optJSONObject:(Ljava/lang/String;)Lorg/json/JSONObject;
        //   344: astore          6
        //   346: aload           6
        //   348: ifnull          806
        //   351: aload           6
        //   353: invokevirtual   org/json/JSONObject.length:()I
        //   356: ifne            613
        //   359: goto            806
        //   362: aload_0        
        //   363: aload           6
        //   365: putfield        com/facebook/ads/internal/adapters/i.x:Lcom/facebook/ads/internal/t/j;
        //   368: aload           8
        //   370: ifnull          382
        //   373: aload_0        
        //   374: aload           8
        //   376: invokestatic    com/facebook/ads/internal/t/g.a:(Lorg/json/JSONObject;)Lcom/facebook/ads/internal/t/g;
        //   379: putfield        com/facebook/ads/internal/adapters/i.v:Lcom/facebook/ads/internal/t/g;
        //   382: aload_0        
        //   383: aload_1        
        //   384: ldc             "ad_choices_link_url"
        //   386: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   389: putfield        com/facebook/ads/internal/adapters/i.w:Ljava/lang/String;
        //   392: aload_0        
        //   393: aload_1        
        //   394: ldc             "invalidation_behavior"
        //   396: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   399: invokestatic    com/facebook/ads/internal/a/d.a:(Ljava/lang/String;)Lcom/facebook/ads/internal/a/d;
        //   402: putfield        com/facebook/ads/internal/adapters/i.j:Lcom/facebook/ads/internal/a/d;
        //   405: new             Lorg/json/JSONArray;
        //   408: dup            
        //   409: aload_1        
        //   410: ldc_w           "detection_strings"
        //   413: invokevirtual   org/json/JSONObject.optString:(Ljava/lang/String;)Ljava/lang/String;
        //   416: invokespecial   org/json/JSONArray.<init>:(Ljava/lang/String;)V
        //   419: astore          6
        //   421: aload_0        
        //   422: aload           6
        //   424: invokestatic    com/facebook/ads/internal/a/e.a:(Lorg/json/JSONArray;)Ljava/util/Collection;
        //   427: putfield        com/facebook/ads/internal/adapters/i.k:Ljava/util/Collection;
        //   430: aload_0        
        //   431: aload_1        
        //   432: ldc_w           "video_url"
        //   435: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   438: putfield        com/facebook/ads/internal/adapters/i.r:Ljava/lang/String;
        //   441: aload_0        
        //   442: aload_1        
        //   443: ldc_w           "video_mpd"
        //   446: invokestatic    com/facebook/ads/internal/w/b/k.a:(Lorg/json/JSONObject;Ljava/lang/String;)Ljava/lang/String;
        //   449: putfield        com/facebook/ads/internal/adapters/i.s:Ljava/lang/String;
        //   452: aload_1        
        //   453: ldc_w           "video_autoplay_enabled"
        //   456: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   459: ifne            651
        //   462: aload_0        
        //   463: getstatic       com/facebook/ads/internal/t/l.a:Lcom/facebook/ads/internal/t/l;
        //   466: putfield        com/facebook/ads/internal/adapters/i.t:Lcom/facebook/ads/internal/t/l;
        //   469: aload_1        
        //   470: ldc_w           "carousel"
        //   473: invokevirtual   org/json/JSONObject.optJSONArray:(Ljava/lang/String;)Lorg/json/JSONArray;
        //   476: astore_1       
        //   477: aload_1        
        //   478: ifnull          689
        //   481: aload_1        
        //   482: invokevirtual   org/json/JSONArray.length:()I
        //   485: ifle            689
        //   488: aload_1        
        //   489: invokevirtual   org/json/JSONArray.length:()I
        //   492: istore          4
        //   494: new             Ljava/util/ArrayList;
        //   497: dup            
        //   498: iload           4
        //   500: invokespecial   java/util/ArrayList.<init>:(I)V
        //   503: astore          6
        //   505: iconst_0       
        //   506: istore_3       
        //   507: iload_3        
        //   508: iload           4
        //   510: if_icmpge       683
        //   513: new             Lcom/facebook/ads/internal/adapters/i;
        //   516: dup            
        //   517: invokespecial   com/facebook/ads/internal/adapters/i.<init>:()V
        //   520: astore          7
        //   522: aload_0        
        //   523: getfield        com/facebook/ads/internal/adapters/i.b:Landroid/content/Context;
        //   526: astore          8
        //   528: aload_1        
        //   529: iload_3        
        //   530: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   533: astore          9
        //   535: aload_0        
        //   536: getfield        com/facebook/ads/internal/adapters/i.H:Lcom/facebook/ads/internal/s/c;
        //   539: astore          10
        //   541: aload           7
        //   543: iconst_1       
        //   544: putfield        com/facebook/ads/internal/adapters/i.C:Z
        //   547: aload           7
        //   549: aload           8
        //   551: putfield        com/facebook/ads/internal/adapters/i.b:Landroid/content/Context;
        //   554: aload           7
        //   556: aload           10
        //   558: putfield        com/facebook/ads/internal/adapters/i.H:Lcom/facebook/ads/internal/s/c;
        //   561: aload           7
        //   563: iload_3        
        //   564: putfield        com/facebook/ads/internal/adapters/i.z:I
        //   567: aload           7
        //   569: iload           4
        //   571: putfield        com/facebook/ads/internal/adapters/i.A:I
        //   574: aload           7
        //   576: aload           9
        //   578: aload_2        
        //   579: invokespecial   com/facebook/ads/internal/adapters/i.a:(Lorg/json/JSONObject;Ljava/lang/String;)V
        //   582: aload           6
        //   584: new             Lcom/facebook/ads/internal/t/e;
        //   587: dup            
        //   588: aload_0        
        //   589: getfield        com/facebook/ads/internal/adapters/i.b:Landroid/content/Context;
        //   592: aload           7
        //   594: aconst_null    
        //   595: aload_0        
        //   596: getfield        com/facebook/ads/internal/adapters/i.I:Lcom/facebook/ads/internal/t/e$c;
        //   599: invokespecial   com/facebook/ads/internal/t/e.<init>:(Landroid/content/Context;Lcom/facebook/ads/internal/adapters/i;Lcom/facebook/ads/internal/m/d;Lcom/facebook/ads/internal/t/e$c;)V
        //   602: invokevirtual   java/util/ArrayList.add:(Ljava/lang/Object;)Z
        //   605: pop            
        //   606: iload_3        
        //   607: iconst_1       
        //   608: iadd           
        //   609: istore_3       
        //   610: goto            507
        //   613: new             Lcom/facebook/ads/internal/t/j;
        //   616: dup            
        //   617: aload           6
        //   619: invokespecial   com/facebook/ads/internal/t/j.<init>:(Lorg/json/JSONObject;)V
        //   622: astore          6
        //   624: goto            362
        //   627: astore          6
        //   629: aload_0        
        //   630: aconst_null    
        //   631: putfield        com/facebook/ads/internal/adapters/i.x:Lcom/facebook/ads/internal/t/j;
        //   634: goto            368
        //   637: astore          6
        //   639: aload           6
        //   641: invokevirtual   org/json/JSONException.printStackTrace:()V
        //   644: aload           7
        //   646: astore          6
        //   648: goto            421
        //   651: aload_1        
        //   652: ldc_w           "video_autoplay_enabled"
        //   655: invokevirtual   org/json/JSONObject.optBoolean:(Ljava/lang/String;)Z
        //   658: ifeq            675
        //   661: getstatic       com/facebook/ads/internal/t/l.b:Lcom/facebook/ads/internal/t/l;
        //   664: astore          6
        //   666: aload_0        
        //   667: aload           6
        //   669: putfield        com/facebook/ads/internal/adapters/i.t:Lcom/facebook/ads/internal/t/l;
        //   672: goto            469
        //   675: getstatic       com/facebook/ads/internal/t/l.c:Lcom/facebook/ads/internal/t/l;
        //   678: astore          6
        //   680: goto            666
        //   683: aload_0        
        //   684: aload           6
        //   686: putfield        com/facebook/ads/internal/adapters/i.y:Ljava/util/List;
        //   689: aload_0        
        //   690: iconst_1       
        //   691: putfield        com/facebook/ads/internal/adapters/i.D:Z
        //   694: aload_0        
        //   695: getfield        com/facebook/ads/internal/adapters/i.C:Z
        //   698: ifne            719
        //   701: aload_0        
        //   702: getfield        com/facebook/ads/internal/adapters/i.e:Ljava/util/HashMap;
        //   705: ldc             "advertiser_name"
        //   707: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   710: checkcast       Ljava/lang/CharSequence;
        //   713: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   716: ifeq            744
        //   719: aload_0        
        //   720: getfield        com/facebook/ads/internal/adapters/i.e:Ljava/util/HashMap;
        //   723: ldc             "title"
        //   725: invokevirtual   java/util/HashMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   728: checkcast       Ljava/lang/CharSequence;
        //   731: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   734: ifne            800
        //   737: aload_0        
        //   738: getfield        com/facebook/ads/internal/adapters/i.C:Z
        //   741: ifeq            800
        //   744: aload_0        
        //   745: getfield        com/facebook/ads/internal/adapters/i.f:Lcom/facebook/ads/internal/t/g;
        //   748: ifnonnull       758
        //   751: aload_0        
        //   752: getfield        com/facebook/ads/internal/adapters/i.C:Z
        //   755: ifeq            800
        //   758: aload_0        
        //   759: getfield        com/facebook/ads/internal/adapters/i.g:Lcom/facebook/ads/internal/t/g;
        //   762: ifnonnull       775
        //   765: aload_0        
        //   766: invokevirtual   com/facebook/ads/internal/adapters/i.getPlacementType:()Lcom/facebook/ads/internal/protocol/AdPlacementType;
        //   769: getstatic       com/facebook/ads/internal/protocol/AdPlacementType.NATIVE_BANNER:Lcom/facebook/ads/internal/protocol/AdPlacementType;
        //   772: if_acmpne       800
        //   775: iconst_1       
        //   776: istore          5
        //   778: aload_0        
        //   779: iload           5
        //   781: putfield        com/facebook/ads/internal/adapters/i.E:Z
        //   784: return         
        //   785: astore_1       
        //   786: getstatic       com/facebook/ads/internal/adapters/i.a:Ljava/lang/String;
        //   789: ldc_w           "Unable to parse carousel data."
        //   792: aload_1        
        //   793: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   796: pop            
        //   797: goto            689
        //   800: iconst_0       
        //   801: istore          5
        //   803: goto            778
        //   806: aconst_null    
        //   807: astore          6
        //   809: goto            362
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  351    359    627    637    Lorg/json/JSONException;
        //  362    368    627    637    Lorg/json/JSONException;
        //  405    421    637    651    Lorg/json/JSONException;
        //  469    477    785    800    Lorg/json/JSONException;
        //  481    505    785    800    Lorg/json/JSONException;
        //  513    606    785    800    Lorg/json/JSONException;
        //  613    624    627    637    Lorg/json/JSONException;
        //  683    689    785    800    Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 396, Size: 396
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    public boolean A() {
        return this.D && this.E;
    }
    
    public boolean B() {
        return this.C;
    }
    
    @Override
    public d a() {
        return this.j;
    }
    
    @Nullable
    public String a(final String s) {
        if (!this.A()) {
            return null;
        }
        this.C();
        return this.e.get(s);
    }
    
    public void a(final int n) {
    }
    
    public void a(final Context b, final q c, final c h, final Map<String, Object> map, final com.facebook.ads.internal.t.e.c i) {
        this.b = b;
        this.c = c;
        this.H = h;
        this.I = i;
        final JSONObject jsonObject = map.get("data");
        final com.facebook.ads.internal.m.d d = (com.facebook.ads.internal.m.d)map.get("definition");
        int k;
        if (d != null) {
            k = d.k();
        }
        else {
            k = 200;
        }
        this.u = k;
        this.a(jsonObject, com.facebook.ads.internal.w.b.k.a(jsonObject, "ct"));
        if (com.facebook.ads.internal.a.e.a(b, (e.a)this, h)) {
            c.a(this, new com.facebook.ads.internal.protocol.a(AdErrorType.NO_FILL, "No Fill"));
        }
        else if (c != null) {
            c.a(this);
        }
    }
    
    public void a(final View view, final List<View> list) {
    }
    
    public void a(final q c) {
        this.c = c;
    }
    
    public void a(final Map<String, String> map) {
        if (!this.A() || this.F) {
            return;
        }
        if (this.c != null) {
            this.c.b(this);
        }
        final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
        if (map != null) {
            hashMap.putAll(map);
        }
        if (this.C) {
            hashMap.put("cardind", String.valueOf(this.z));
            hashMap.put("cardcnt", String.valueOf(this.A));
        }
        if (!TextUtils.isEmpty((CharSequence)this.getClientToken()) && this.H != null) {
            this.H.a(this.getClientToken(), (Map<String, String>)hashMap);
        }
        while (true) {
            if (!this.d()) {
                if (!this.h()) {
                    break Label_0237;
                }
            }
            try {
                final HashMap<String, String> hashMap2 = new HashMap<String, String>();
                if (map.containsKey("view")) {
                    hashMap2.put("view", map.get("view"));
                }
                if (map.containsKey("snapshot")) {
                    hashMap2.put("snapshot", map.get("snapshot"));
                }
                new Handler().postDelayed((Runnable)new Runnable() {
                    final /* synthetic */ Map a = hashMap;
                    final /* synthetic */ Map b = hashMap2;
                    
                    @Override
                    public void run() {
                        if (!TextUtils.isEmpty((CharSequence)com.facebook.ads.internal.adapters.i.this.B)) {
                            final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
                            hashMap.putAll(this.a);
                            hashMap.putAll(this.b);
                            if (com.facebook.ads.internal.adapters.i.this.H != null) {
                                com.facebook.ads.internal.adapters.i.this.H.f(com.facebook.ads.internal.adapters.i.this.B, (Map<String, String>)hashMap);
                            }
                        }
                    }
                }, (long)(this.n * 1000));
                this.F = true;
            }
            catch (Exception ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public Collection<String> b() {
        return this.k;
    }
    
    public void b(final Map<String, String> map) {
        if (this.H != null) {
            this.H.k(this.B, map);
        }
    }
    
    public void c() {
        if (this.y != null && !this.y.isEmpty()) {
            final Iterator<com.facebook.ads.internal.t.e> iterator = this.y.iterator();
            while (iterator.hasNext()) {
                iterator.next().z();
            }
        }
    }
    
    public void c(final Map<String, String> map) {
        if (this.H != null) {
            this.H.j(this.B, map);
        }
    }
    
    public void d(final Map<String, String> map) {
        if (this.H != null) {
            this.H.i(this.B, map);
        }
    }
    
    public boolean d() {
        return this.A() && this.l;
    }
    
    public void e(final Map<String, String> map) {
        if (this.A()) {
            if (com.facebook.ads.internal.r.a.j(this.b) && com.facebook.ads.internal.w.b.z.a(map)) {
                Log.e(com.facebook.ads.internal.adapters.i.a, "Click happened on lockscreen ad");
                return;
            }
            final HashMap<Object, Object> hashMap = new HashMap<Object, Object>();
            if (map != null) {
                hashMap.putAll(map);
            }
            com.facebook.ads.internal.w.b.c.a(this.b, "Click logged");
            if (this.c != null) {
                this.c.c(this);
            }
            if (this.C) {
                hashMap.put("cardind", String.valueOf(this.z));
                hashMap.put("cardcnt", String.valueOf(this.A));
            }
            final b a = com.facebook.ads.internal.a.c.a(this.b, this.H, this.B, this.d, (Map<String, String>)hashMap);
            if (a != null) {
                try {
                    a.a();
                }
                catch (Exception ex) {
                    Log.e(com.facebook.ads.internal.adapters.i.a, "Error executing action", (Throwable)ex);
                }
            }
        }
    }
    
    public boolean e() {
        return this.A() && this.x != null;
    }
    
    public boolean f() {
        return this.A() && this.d != null;
    }
    
    public boolean g() {
        return true;
    }
    
    @Override
    public String getClientToken() {
        return this.B;
    }
    
    @Override
    public AdPlacementType getPlacementType() {
        return AdPlacementType.NATIVE;
    }
    
    public boolean h() {
        return this.A() && this.m;
    }
    
    public int i() {
        if (this.o < 0 || this.o > 100) {
            return 0;
        }
        return this.o;
    }
    
    public int j() {
        return this.p;
    }
    
    public int k() {
        return this.q;
    }
    
    public g l() {
        if (!this.A()) {
            return null;
        }
        return this.f;
    }
    
    public g m() {
        if (!this.A()) {
            return null;
        }
        return this.g;
    }
    
    public j n() {
        if (!this.A()) {
            return null;
        }
        return this.x;
    }
    
    public String o() {
        String s;
        if (!this.A()) {
            s = null;
        }
        else {
            this.C();
            final String s2 = this.e.get("body");
            if ((s = s2) != null) {
                final StringTokenizer stringTokenizer = new StringTokenizer(s2, " ", true);
                s = s2;
                if (s2.length() > 90) {
                    if (s2.length() <= 93) {
                        s = s2;
                        if (s2.endsWith("...")) {
                            return s;
                        }
                    }
                    int n = 0;
                    while (stringTokenizer.hasMoreTokens()) {
                        final int length = stringTokenizer.nextToken().length();
                        if (n + length < 90) {
                            n += length;
                        }
                    }
                    if (n == 0) {
                        return s2.substring(0, 90) + "...";
                    }
                    return s2.substring(0, n) + "...";
                }
            }
        }
        return s;
    }
    
    @Override
    public void onDestroy() {
    }
    
    public com.facebook.ads.internal.t.i p() {
        if (!this.A()) {
            return null;
        }
        this.C();
        return this.h;
    }
    
    public g q() {
        if (!this.A()) {
            return null;
        }
        return this.v;
    }
    
    public String r() {
        if (!this.A()) {
            return null;
        }
        return this.w;
    }
    
    public String s() {
        if (!this.A()) {
            return null;
        }
        return "AdChoices";
    }
    
    public String t() {
        if (!this.A()) {
            return null;
        }
        return this.r;
    }
    
    public String u() {
        if (!this.A()) {
            return null;
        }
        return this.s;
    }
    
    public l v() {
        if (!this.A()) {
            return com.facebook.ads.internal.t.l.a;
        }
        return this.t;
    }
    
    public int w() {
        return this.u;
    }
    
    public List<com.facebook.ads.internal.t.e> x() {
        if (!this.A()) {
            return null;
        }
        return this.y;
    }
    
    public int y() {
        return this.z;
    }
    
    public int z() {
        return this.A;
    }
}
