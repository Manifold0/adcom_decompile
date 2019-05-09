// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

@zzadh
public final class zzafx
{
    private int mOrientation;
    private String zzbhy;
    private boolean zzbtn;
    private final zzaef zzbuc;
    private List<String> zzcab;
    private String zzcae;
    private String zzchw;
    private String zzchx;
    private List<String> zzchy;
    private String zzchz;
    private String zzcia;
    private String zzcib;
    private List<String> zzcic;
    private List<String> zzcid;
    private long zzcie;
    private boolean zzcif;
    private final long zzcig;
    private long zzcih;
    private boolean zzcii;
    private boolean zzcij;
    private boolean zzcik;
    private boolean zzcil;
    private boolean zzcim;
    private String zzcin;
    private boolean zzcio;
    private zzaig zzcip;
    private List<String> zzciq;
    private List<String> zzcir;
    private boolean zzcis;
    private boolean zzcit;
    private String zzciu;
    private List<String> zzciv;
    private boolean zzciw;
    private String zzcix;
    private zzaiq zzciy;
    private boolean zzciz;
    private boolean zzcja;
    private boolean zzcjb;
    private boolean zzcjc;
    private zzael zzxe;
    
    public zzafx(final zzaef zzbuc, final String zzchx) {
        this.zzcie = -1L;
        this.zzcif = false;
        this.zzcig = -1L;
        this.zzcih = -1L;
        this.mOrientation = -1;
        this.zzcii = false;
        this.zzcij = false;
        this.zzcik = false;
        this.zzcil = true;
        this.zzcim = true;
        this.zzcin = "";
        this.zzcio = false;
        this.zzbtn = false;
        this.zzcis = false;
        this.zzcit = false;
        this.zzchx = zzchx;
        this.zzbuc = zzbuc;
    }
    
    private static String zza(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    private static long zzb(Map<String, List<String>> s, final String s2) {
        final List<String> list = ((Map<String, List<String>>)s).get(s2);
        if (list != null && !list.isEmpty()) {
            s = list.get(0);
            try {
                return (long)(Float.parseFloat(s) * 1000.0f);
            }
            catch (NumberFormatException ex) {
                zzakb.zzdk(new StringBuilder(String.valueOf(s2).length() + 36 + String.valueOf(s).length()).append("Could not parse float from ").append(s2).append(" header: ").append(s).toString());
            }
        }
        return -1L;
    }
    
    private static List<String> zzc(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            final String s2 = list.get(0);
            if (s2 != null) {
                return Arrays.asList(s2.trim().split("\\s+"));
            }
        }
        return null;
    }
    
    private static boolean zzd(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        return list != null && !list.isEmpty() && Boolean.valueOf(list.get(0));
    }
    
    public final zzaej zza(final long n, final boolean b) {
        final zzaef zzbuc = this.zzbuc;
        final String zzchx = this.zzchx;
        final String zzbhy = this.zzbhy;
        final List<String> zzchy = this.zzchy;
        final List<String> zzcic = this.zzcic;
        final long zzcie = this.zzcie;
        final boolean zzcif = this.zzcif;
        final List<String> zzcab = this.zzcab;
        final long zzcih = this.zzcih;
        final int mOrientation = this.mOrientation;
        final String zzchw = this.zzchw;
        final String zzcia = this.zzcia;
        final String zzcib = this.zzcib;
        final boolean zzcii = this.zzcii;
        final boolean zzcij = this.zzcij;
        final boolean zzcik = this.zzcik;
        final boolean zzcil = this.zzcil;
        final String zzcin = this.zzcin;
        final boolean zzcio = this.zzcio;
        final boolean zzbtn = this.zzbtn;
        final zzaig zzcip = this.zzcip;
        final List<String> zzciq = this.zzciq;
        final List<String> zzcir = this.zzcir;
        final boolean zzcis = this.zzcis;
        final zzael zzxe = this.zzxe;
        final boolean zzcit = this.zzcit;
        final String zzciu = this.zzciu;
        final List<String> zzciv = this.zzciv;
        final boolean zzciw = this.zzciw;
        final String zzcix = this.zzcix;
        final zzaiq zzciy = this.zzciy;
        final String zzchz = this.zzchz;
        final boolean zzcim = this.zzcim;
        final boolean zzciz = this.zzciz;
        final boolean zzcja = this.zzcja;
        int n2;
        if (b) {
            n2 = 2;
        }
        else {
            n2 = 1;
        }
        return new zzaej(zzbuc, zzchx, zzbhy, zzchy, zzcic, zzcie, zzcif, -1L, zzcab, zzcih, mOrientation, zzchw, n, zzcia, zzcib, zzcii, zzcij, zzcik, zzcil, false, zzcin, zzcio, zzbtn, zzcip, zzciq, zzcir, zzcis, zzxe, zzcit, zzciu, zzciv, zzciw, zzcix, zzciy, zzchz, zzcim, zzciz, zzcja, n2, this.zzcjb, this.zzcid, this.zzcjc, this.zzcae);
    }
    
    public final void zza(final String s, final Map<String, List<String>> map, final String zzbhy) {
        this.zzbhy = zzbhy;
        this.zzl(map);
    }
    
    public final void zzl(final Map<String, List<String>> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: aload_1        
        //     2: ldc             "X-Afma-Ad-Size"
        //     4: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //     7: putfield        com/google/android/gms/internal/ads/zzafx.zzchw:Ljava/lang/String;
        //    10: aload_0        
        //    11: aload_1        
        //    12: ldc_w           "X-Afma-Ad-Slot-Size"
        //    15: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //    18: putfield        com/google/android/gms/internal/ads/zzafx.zzcix:Ljava/lang/String;
        //    21: aload_1        
        //    22: ldc_w           "X-Afma-Click-Tracking-Urls"
        //    25: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //    28: astore          5
        //    30: aload           5
        //    32: ifnull          41
        //    35: aload_0        
        //    36: aload           5
        //    38: putfield        com/google/android/gms/internal/ads/zzafx.zzchy:Ljava/util/List;
        //    41: aload_0        
        //    42: aload_1        
        //    43: ldc_w           "X-Afma-Debug-Signals"
        //    46: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //    49: putfield        com/google/android/gms/internal/ads/zzafx.zzchz:Ljava/lang/String;
        //    52: aload_1        
        //    53: ldc_w           "X-Afma-Debug-Dialog"
        //    56: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    61: checkcast       Ljava/util/List;
        //    64: astore          5
        //    66: aload           5
        //    68: ifnull          96
        //    71: aload           5
        //    73: invokeinterface java/util/List.isEmpty:()Z
        //    78: ifne            96
        //    81: aload_0        
        //    82: aload           5
        //    84: iconst_0       
        //    85: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //    90: checkcast       Ljava/lang/String;
        //    93: putfield        com/google/android/gms/internal/ads/zzafx.zzcia:Ljava/lang/String;
        //    96: aload_1        
        //    97: ldc_w           "X-Afma-Tracking-Urls"
        //   100: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   103: astore          5
        //   105: aload           5
        //   107: ifnull          116
        //   110: aload_0        
        //   111: aload           5
        //   113: putfield        com/google/android/gms/internal/ads/zzafx.zzcic:Ljava/util/List;
        //   116: aload_1        
        //   117: ldc_w           "X-Afma-Downloaded-Impression-Urls"
        //   120: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   123: astore          5
        //   125: aload           5
        //   127: ifnull          136
        //   130: aload_0        
        //   131: aload           5
        //   133: putfield        com/google/android/gms/internal/ads/zzafx.zzcid:Ljava/util/List;
        //   136: aload_1        
        //   137: ldc_w           "X-Afma-Interstitial-Timeout"
        //   140: invokestatic    com/google/android/gms/internal/ads/zzafx.zzb:(Ljava/util/Map;Ljava/lang/String;)J
        //   143: lstore_2       
        //   144: lload_2        
        //   145: ldc2_w          -1
        //   148: lcmp           
        //   149: ifeq            157
        //   152: aload_0        
        //   153: lload_2        
        //   154: putfield        com/google/android/gms/internal/ads/zzafx.zzcie:J
        //   157: aload_0        
        //   158: aload_0        
        //   159: getfield        com/google/android/gms/internal/ads/zzafx.zzcif:Z
        //   162: aload_1        
        //   163: ldc_w           "X-Afma-Mediation"
        //   166: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   169: ior            
        //   170: putfield        com/google/android/gms/internal/ads/zzafx.zzcif:Z
        //   173: aload_1        
        //   174: ldc_w           "X-Afma-Manual-Tracking-Urls"
        //   177: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   180: astore          5
        //   182: aload           5
        //   184: ifnull          193
        //   187: aload_0        
        //   188: aload           5
        //   190: putfield        com/google/android/gms/internal/ads/zzafx.zzcab:Ljava/util/List;
        //   193: aload_1        
        //   194: ldc_w           "X-Afma-Refresh-Rate"
        //   197: invokestatic    com/google/android/gms/internal/ads/zzafx.zzb:(Ljava/util/Map;Ljava/lang/String;)J
        //   200: lstore_2       
        //   201: lload_2        
        //   202: ldc2_w          -1
        //   205: lcmp           
        //   206: ifeq            214
        //   209: aload_0        
        //   210: lload_2        
        //   211: putfield        com/google/android/gms/internal/ads/zzafx.zzcih:J
        //   214: aload_1        
        //   215: ldc_w           "X-Afma-Orientation"
        //   218: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   223: checkcast       Ljava/util/List;
        //   226: astore          5
        //   228: aload           5
        //   230: ifnull          277
        //   233: aload           5
        //   235: invokeinterface java/util/List.isEmpty:()Z
        //   240: ifne            277
        //   243: aload           5
        //   245: iconst_0       
        //   246: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   251: checkcast       Ljava/lang/String;
        //   254: astore          5
        //   256: ldc_w           "portrait"
        //   259: aload           5
        //   261: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   264: ifeq            956
        //   267: aload_0        
        //   268: invokestatic    com/google/android/gms/ads/internal/zzbv.zzem:()Lcom/google/android/gms/internal/ads/zzakq;
        //   271: invokevirtual   com/google/android/gms/internal/ads/zzakq.zzrm:()I
        //   274: putfield        com/google/android/gms/internal/ads/zzafx.mOrientation:I
        //   277: aload_0        
        //   278: aload_1        
        //   279: ldc_w           "X-Afma-ActiveView"
        //   282: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   285: putfield        com/google/android/gms/internal/ads/zzafx.zzcib:Ljava/lang/String;
        //   288: aload_1        
        //   289: ldc_w           "X-Afma-Use-HTTPS"
        //   292: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   297: checkcast       Ljava/util/List;
        //   300: astore          5
        //   302: aload           5
        //   304: ifnull          338
        //   307: aload           5
        //   309: invokeinterface java/util/List.isEmpty:()Z
        //   314: ifne            338
        //   317: aload_0        
        //   318: aload           5
        //   320: iconst_0       
        //   321: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   326: checkcast       Ljava/lang/String;
        //   329: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //   332: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   335: putfield        com/google/android/gms/internal/ads/zzafx.zzcik:Z
        //   338: aload_0        
        //   339: aload_0        
        //   340: getfield        com/google/android/gms/internal/ads/zzafx.zzcii:Z
        //   343: aload_1        
        //   344: ldc_w           "X-Afma-Custom-Rendering-Allowed"
        //   347: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   350: ior            
        //   351: putfield        com/google/android/gms/internal/ads/zzafx.zzcii:Z
        //   354: aload_0        
        //   355: ldc_w           "native"
        //   358: aload_1        
        //   359: ldc_w           "X-Afma-Ad-Format"
        //   362: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   365: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   368: putfield        com/google/android/gms/internal/ads/zzafx.zzcij:Z
        //   371: aload_1        
        //   372: ldc_w           "X-Afma-Content-Url-Opted-Out"
        //   375: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   380: checkcast       Ljava/util/List;
        //   383: astore          5
        //   385: aload           5
        //   387: ifnull          421
        //   390: aload           5
        //   392: invokeinterface java/util/List.isEmpty:()Z
        //   397: ifne            421
        //   400: aload_0        
        //   401: aload           5
        //   403: iconst_0       
        //   404: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   409: checkcast       Ljava/lang/String;
        //   412: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //   415: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   418: putfield        com/google/android/gms/internal/ads/zzafx.zzcil:Z
        //   421: aload_1        
        //   422: ldc_w           "X-Afma-Content-Vertical-Opted-Out"
        //   425: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   430: checkcast       Ljava/util/List;
        //   433: astore          5
        //   435: aload           5
        //   437: ifnull          471
        //   440: aload           5
        //   442: invokeinterface java/util/List.isEmpty:()Z
        //   447: ifne            471
        //   450: aload_0        
        //   451: aload           5
        //   453: iconst_0       
        //   454: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   459: checkcast       Ljava/lang/String;
        //   462: invokestatic    java/lang/Boolean.valueOf:(Ljava/lang/String;)Ljava/lang/Boolean;
        //   465: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   468: putfield        com/google/android/gms/internal/ads/zzafx.zzcim:Z
        //   471: aload_1        
        //   472: ldc_w           "X-Afma-Gws-Query-Id"
        //   475: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   480: checkcast       Ljava/util/List;
        //   483: astore          5
        //   485: aload           5
        //   487: ifnull          515
        //   490: aload           5
        //   492: invokeinterface java/util/List.isEmpty:()Z
        //   497: ifne            515
        //   500: aload_0        
        //   501: aload           5
        //   503: iconst_0       
        //   504: invokeinterface java/util/List.get:(I)Ljava/lang/Object;
        //   509: checkcast       Ljava/lang/String;
        //   512: putfield        com/google/android/gms/internal/ads/zzafx.zzcin:Ljava/lang/String;
        //   515: aload_1        
        //   516: ldc_w           "X-Afma-Fluid"
        //   519: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   522: astore          5
        //   524: aload           5
        //   526: ifnull          545
        //   529: aload           5
        //   531: ldc_w           "height"
        //   534: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   537: ifeq            545
        //   540: aload_0        
        //   541: iconst_1       
        //   542: putfield        com/google/android/gms/internal/ads/zzafx.zzcio:Z
        //   545: aload_0        
        //   546: ldc_w           "native_express"
        //   549: aload_1        
        //   550: ldc_w           "X-Afma-Ad-Format"
        //   553: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   556: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   559: putfield        com/google/android/gms/internal/ads/zzafx.zzbtn:Z
        //   562: aload_0        
        //   563: aload_1        
        //   564: ldc_w           "X-Afma-Rewards"
        //   567: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   570: invokestatic    com/google/android/gms/internal/ads/zzaig.zzce:(Ljava/lang/String;)Lcom/google/android/gms/internal/ads/zzaig;
        //   573: putfield        com/google/android/gms/internal/ads/zzafx.zzcip:Lcom/google/android/gms/internal/ads/zzaig;
        //   576: aload_0        
        //   577: getfield        com/google/android/gms/internal/ads/zzafx.zzciq:Ljava/util/List;
        //   580: ifnonnull       594
        //   583: aload_0        
        //   584: aload_1        
        //   585: ldc_w           "X-Afma-Reward-Video-Start-Urls"
        //   588: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   591: putfield        com/google/android/gms/internal/ads/zzafx.zzciq:Ljava/util/List;
        //   594: aload_0        
        //   595: getfield        com/google/android/gms/internal/ads/zzafx.zzcir:Ljava/util/List;
        //   598: ifnonnull       612
        //   601: aload_0        
        //   602: aload_1        
        //   603: ldc_w           "X-Afma-Reward-Video-Complete-Urls"
        //   606: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   609: putfield        com/google/android/gms/internal/ads/zzafx.zzcir:Ljava/util/List;
        //   612: aload_0        
        //   613: aload_0        
        //   614: getfield        com/google/android/gms/internal/ads/zzafx.zzcis:Z
        //   617: aload_1        
        //   618: ldc_w           "X-Afma-Use-Displayed-Impression"
        //   621: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   624: ior            
        //   625: putfield        com/google/android/gms/internal/ads/zzafx.zzcis:Z
        //   628: aload_0        
        //   629: aload_0        
        //   630: getfield        com/google/android/gms/internal/ads/zzafx.zzcit:Z
        //   633: aload_1        
        //   634: ldc_w           "X-Afma-Auto-Collect-Location"
        //   637: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   640: ior            
        //   641: putfield        com/google/android/gms/internal/ads/zzafx.zzcit:Z
        //   644: aload_0        
        //   645: aload_1        
        //   646: ldc_w           "Set-Cookie"
        //   649: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   652: putfield        com/google/android/gms/internal/ads/zzafx.zzciu:Ljava/lang/String;
        //   655: aload_1        
        //   656: ldc_w           "X-Afma-Auto-Protection-Configuration"
        //   659: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   662: astore          5
        //   664: aload           5
        //   666: ifnull          677
        //   669: aload           5
        //   671: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   674: ifeq            980
        //   677: ldc_w           "https://pagead2.googlesyndication.com/pagead/gen_204"
        //   680: invokestatic    android/net/Uri.parse:(Ljava/lang/String;)Landroid/net/Uri;
        //   683: invokevirtual   android/net/Uri.buildUpon:()Landroid/net/Uri$Builder;
        //   686: astore          5
        //   688: aload           5
        //   690: ldc_w           "id"
        //   693: ldc_w           "gmob-apps-blocked-navigation"
        //   696: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   699: pop            
        //   700: aload_0        
        //   701: getfield        com/google/android/gms/internal/ads/zzafx.zzcia:Ljava/lang/String;
        //   704: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   707: ifne            723
        //   710: aload           5
        //   712: ldc_w           "debugDialog"
        //   715: aload_0        
        //   716: getfield        com/google/android/gms/internal/ads/zzafx.zzcia:Ljava/lang/String;
        //   719: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   722: pop            
        //   723: getstatic       com/google/android/gms/internal/ads/zznk.zzaum:Lcom/google/android/gms/internal/ads/zzna;
        //   726: astore          6
        //   728: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   731: aload           6
        //   733: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   736: checkcast       Ljava/lang/Boolean;
        //   739: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   742: istore          4
        //   744: aload           5
        //   746: invokevirtual   android/net/Uri$Builder.toString:()Ljava/lang/String;
        //   749: astore          5
        //   751: aload_0        
        //   752: new             Lcom/google/android/gms/internal/ads/zzael;
        //   755: dup            
        //   756: iload           4
        //   758: iconst_1       
        //   759: anewarray       Ljava/lang/String;
        //   762: dup            
        //   763: iconst_0       
        //   764: new             Ljava/lang/StringBuilder;
        //   767: dup            
        //   768: aload           5
        //   770: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   773: invokevirtual   java/lang/String.length:()I
        //   776: bipush          31
        //   778: iadd           
        //   779: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   782: aload           5
        //   784: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   787: ldc_w           "&navigationURL={NAVIGATION_URL}"
        //   790: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   793: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   796: aastore        
        //   797: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //   800: invokespecial   com/google/android/gms/internal/ads/zzael.<init>:(ZLjava/util/List;)V
        //   803: putfield        com/google/android/gms/internal/ads/zzafx.zzxe:Lcom/google/android/gms/internal/ads/zzael;
        //   806: aload_1        
        //   807: ldc_w           "X-Afma-Remote-Ping-Urls"
        //   810: invokestatic    com/google/android/gms/internal/ads/zzafx.zzc:(Ljava/util/Map;Ljava/lang/String;)Ljava/util/List;
        //   813: astore          5
        //   815: aload           5
        //   817: ifnull          826
        //   820: aload_0        
        //   821: aload           5
        //   823: putfield        com/google/android/gms/internal/ads/zzafx.zzciv:Ljava/util/List;
        //   826: aload_1        
        //   827: ldc_w           "X-Afma-Safe-Browsing"
        //   830: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   833: astore          5
        //   835: aload           5
        //   837: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   840: ifne            859
        //   843: aload_0        
        //   844: new             Lorg/json/JSONObject;
        //   847: dup            
        //   848: aload           5
        //   850: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   853: invokestatic    com/google/android/gms/internal/ads/zzaiq.zzo:(Lorg/json/JSONObject;)Lcom/google/android/gms/internal/ads/zzaiq;
        //   856: putfield        com/google/android/gms/internal/ads/zzafx.zzciy:Lcom/google/android/gms/internal/ads/zzaiq;
        //   859: aload_0        
        //   860: aload_0        
        //   861: getfield        com/google/android/gms/internal/ads/zzafx.zzciw:Z
        //   864: aload_1        
        //   865: ldc_w           "X-Afma-Render-In-Browser"
        //   868: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   871: ior            
        //   872: putfield        com/google/android/gms/internal/ads/zzafx.zzciw:Z
        //   875: aload_1        
        //   876: ldc_w           "X-Afma-Pool"
        //   879: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   882: astore          5
        //   884: aload           5
        //   886: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   889: ifne            911
        //   892: aload_0        
        //   893: new             Lorg/json/JSONObject;
        //   896: dup            
        //   897: aload           5
        //   899: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   902: ldc_w           "never_pool"
        //   905: invokevirtual   org/json/JSONObject.getBoolean:(Ljava/lang/String;)Z
        //   908: putfield        com/google/android/gms/internal/ads/zzafx.zzciz:Z
        //   911: aload_0        
        //   912: aload_1        
        //   913: ldc_w           "X-Afma-Custom-Close-Blocked"
        //   916: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   919: putfield        com/google/android/gms/internal/ads/zzafx.zzcja:Z
        //   922: aload_0        
        //   923: aload_1        
        //   924: ldc_w           "X-Afma-Enable-Omid"
        //   927: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   930: putfield        com/google/android/gms/internal/ads/zzafx.zzcjb:Z
        //   933: aload_0        
        //   934: aload_1        
        //   935: ldc_w           "X-Afma-Disable-Closable-Area"
        //   938: invokestatic    com/google/android/gms/internal/ads/zzafx.zzd:(Ljava/util/Map;Ljava/lang/String;)Z
        //   941: putfield        com/google/android/gms/internal/ads/zzafx.zzcjc:Z
        //   944: aload_0        
        //   945: aload_1        
        //   946: ldc_w           "X-Afma-Omid-Settings"
        //   949: invokestatic    com/google/android/gms/internal/ads/zzafx.zza:(Ljava/util/Map;Ljava/lang/String;)Ljava/lang/String;
        //   952: putfield        com/google/android/gms/internal/ads/zzafx.zzcae:Ljava/lang/String;
        //   955: return         
        //   956: ldc_w           "landscape"
        //   959: aload           5
        //   961: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   964: ifeq            277
        //   967: aload_0        
        //   968: invokestatic    com/google/android/gms/ads/internal/zzbv.zzem:()Lcom/google/android/gms/internal/ads/zzakq;
        //   971: invokevirtual   com/google/android/gms/internal/ads/zzakq.zzrl:()I
        //   974: putfield        com/google/android/gms/internal/ads/zzafx.mOrientation:I
        //   977: goto            277
        //   980: aload_0        
        //   981: new             Lorg/json/JSONObject;
        //   984: dup            
        //   985: aload           5
        //   987: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   990: invokestatic    com/google/android/gms/internal/ads/zzael.zzl:(Lorg/json/JSONObject;)Lcom/google/android/gms/internal/ads/zzael;
        //   993: putfield        com/google/android/gms/internal/ads/zzafx.zzxe:Lcom/google/android/gms/internal/ads/zzael;
        //   996: goto            806
        //   999: astore          5
        //  1001: ldc_w           "Error parsing configuration JSON"
        //  1004: aload           5
        //  1006: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1009: aload_0        
        //  1010: new             Lcom/google/android/gms/internal/ads/zzael;
        //  1013: dup            
        //  1014: invokespecial   com/google/android/gms/internal/ads/zzael.<init>:()V
        //  1017: putfield        com/google/android/gms/internal/ads/zzafx.zzxe:Lcom/google/android/gms/internal/ads/zzael;
        //  1020: goto            806
        //  1023: astore          5
        //  1025: ldc_w           "Error parsing safe browsing header"
        //  1028: aload           5
        //  1030: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1033: goto            859
        //  1036: astore          5
        //  1038: ldc_w           "Error parsing interstitial pool header"
        //  1041: aload           5
        //  1043: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  1046: goto            911
        //    Signature:
        //  (Ljava/util/Map<Ljava/lang/String;Ljava/util/List<Ljava/lang/String;>;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  843    859    1023   1036   Lorg/json/JSONException;
        //  892    911    1036   1049   Lorg/json/JSONException;
        //  980    996    999    1023   Lorg/json/JSONException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0911:
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
}
