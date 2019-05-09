// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.u;

import android.util.Base64;
import com.facebook.ads.internal.w.b.u;
import org.json.JSONException;
import com.facebook.ads.internal.protocol.AdErrorType;
import com.facebook.ads.internal.v.a.m;
import java.util.Locale;
import android.text.TextUtils;
import com.facebook.ads.internal.settings.AdInternalSettings;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.Map;
import com.facebook.ads.internal.r.a;
import android.content.Context;
import java.util.concurrent.ThreadPoolExecutor;
import com.facebook.ads.internal.w.b.n;
import android.support.annotation.Nullable;

public class c
{
    @Nullable
    private static a a;
    private static final n j;
    private static final ThreadPoolExecutor k;
    private final Context b;
    private final d c;
    private final com.facebook.ads.internal.r.a d;
    private Map<String, String> e;
    private b f;
    private com.facebook.ads.internal.u.b g;
    private com.facebook.ads.internal.v.a.a h;
    private final String i;
    
    static {
        j = new n();
        k = (ThreadPoolExecutor)Executors.newCachedThreadPool(c.j);
    }
    
    public c(final Context context) {
        this.b = context.getApplicationContext();
        this.c = com.facebook.ads.internal.u.d.a();
        this.d = com.facebook.ads.internal.r.a.af(this.b);
        final String urlPrefix = AdInternalSettings.getUrlPrefix();
        String format;
        if (TextUtils.isEmpty((CharSequence)urlPrefix)) {
            format = "https://graph.facebook.com/network_ads_common";
        }
        else {
            format = String.format(Locale.US, "https://graph.%s.facebook.com/network_ads_common", urlPrefix);
        }
        this.i = format;
    }
    
    private void a(final com.facebook.ads.internal.protocol.a a) {
        if (this.f != null) {
            this.f.a(a);
        }
        this.a();
    }
    
    private void a(final f f) {
        if (this.f != null) {
            this.f.a(f);
        }
        this.a();
    }
    
    private void a(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          6
        //     3: iconst_1       
        //     4: istore          5
        //     6: iconst_0       
        //     7: istore          4
        //     9: aload_0        
        //    10: getfield        com/facebook/ads/internal/u/c.c:Lcom/facebook/ads/internal/u/d;
        //    13: aload_1        
        //    14: invokevirtual   com/facebook/ads/internal/u/d.a:(Ljava/lang/String;)Lcom/facebook/ads/internal/u/e;
        //    17: astore          8
        //    19: aload           8
        //    21: invokevirtual   com/facebook/ads/internal/u/e.a:()Lcom/facebook/ads/internal/m/c;
        //    24: astore          9
        //    26: aload           9
        //    28: ifnull          189
        //    31: aload_0        
        //    32: getfield        com/facebook/ads/internal/u/c.d:Lcom/facebook/ads/internal/r/a;
        //    35: aload           9
        //    37: invokevirtual   com/facebook/ads/internal/m/c.b:()Ljava/lang/String;
        //    40: invokevirtual   com/facebook/ads/internal/r/a.a:(Ljava/lang/String;)V
        //    43: getstatic       com/facebook/ads/internal/settings/AdInternalSettings.d:Z
        //    46: ifeq            150
        //    49: aload_0        
        //    50: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //    53: invokestatic    com/facebook/ads/internal/r/a.V:(Landroid/content/Context;)Z
        //    56: ifeq            236
        //    59: aload_0        
        //    60: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //    63: astore          10
        //    65: aload           10
        //    67: ifnonnull       73
        //    70: iconst_0       
        //    71: istore          5
        //    73: iload           5
        //    75: istore          6
        //    77: iload           5
        //    79: ifeq            117
        //    82: new             Ljava/io/File;
        //    85: dup            
        //    86: aload           10
        //    88: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    91: ldc             "com.facebook.ads.ipc"
        //    93: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    96: astore          7
        //    98: iload           5
        //   100: istore          6
        //   102: aload           7
        //   104: invokevirtual   java/io/File.exists:()Z
        //   107: ifne            117
        //   110: aload           7
        //   112: invokevirtual   java/io/File.createNewFile:()Z
        //   115: istore          6
        //   117: iload           6
        //   119: ifne            789
        //   122: new             Ljava/lang/Exception;
        //   125: dup            
        //   126: ldc             "Can't create ipc marker."
        //   128: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //   131: astore          7
        //   133: aload           7
        //   135: ifnull          150
        //   138: aload           10
        //   140: ldc             "ipc"
        //   142: getstatic       com/facebook/ads/internal/w/h/b.ac:I
        //   145: aload           7
        //   147: invokestatic    com/facebook/ads/internal/w/h/a.a:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
        //   150: aload_0        
        //   151: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   154: aload           9
        //   156: invokevirtual   com/facebook/ads/internal/m/c.c:()Ljava/lang/String;
        //   159: invokestatic    com/facebook/ads/internal/f/a.a:(Landroid/content/Context;Ljava/lang/String;)V
        //   162: aload           9
        //   164: invokevirtual   com/facebook/ads/internal/m/c.a:()Lcom/facebook/ads/internal/m/d;
        //   167: invokevirtual   com/facebook/ads/internal/m/d.d:()J
        //   170: aload_0        
        //   171: getfield        com/facebook/ads/internal/u/c.g:Lcom/facebook/ads/internal/u/b;
        //   174: invokestatic    com/facebook/ads/internal/u/a.a:(JLcom/facebook/ads/internal/u/b;)V
        //   177: aload_0        
        //   178: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   181: getstatic       com/facebook/ads/internal/u/c.k:Ljava/util/concurrent/ThreadPoolExecutor;
        //   184: aload           9
        //   186: invokestatic    com/facebook/ads/internal/w/g/a.a:(Landroid/content/Context;Ljava/util/concurrent/ThreadPoolExecutor;Lcom/facebook/ads/internal/m/c;)V
        //   189: getstatic       com/facebook/ads/internal/u/c$3.a:[I
        //   192: aload           8
        //   194: invokevirtual   com/facebook/ads/internal/u/e.b:()Lcom/facebook/ads/internal/u/e$a;
        //   197: invokevirtual   com/facebook/ads/internal/u/e$a.ordinal:()I
        //   200: iaload         
        //   201: tableswitch {
        //                2: 350
        //                3: 727
        //          default: 795
        //        }
        //   224: aload_0        
        //   225: getstatic       com/facebook/ads/internal/protocol/AdErrorType.UNKNOWN_RESPONSE:Lcom/facebook/ads/internal/protocol/AdErrorType;
        //   228: aload_1        
        //   229: invokestatic    com/facebook/ads/internal/protocol/a.a:(Lcom/facebook/ads/internal/protocol/AdErrorType;Ljava/lang/String;)Lcom/facebook/ads/internal/protocol/a;
        //   232: invokespecial   com/facebook/ads/internal/u/c.a:(Lcom/facebook/ads/internal/protocol/a;)V
        //   235: return         
        //   236: aload_0        
        //   237: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   240: astore          10
        //   242: iload           6
        //   244: istore          5
        //   246: aload           10
        //   248: ifnonnull       254
        //   251: iconst_0       
        //   252: istore          5
        //   254: iload           5
        //   256: istore          6
        //   258: iload           5
        //   260: ifeq            298
        //   263: new             Ljava/io/File;
        //   266: dup            
        //   267: aload           10
        //   269: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //   272: ldc             "com.facebook.ads.ipc"
        //   274: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   277: astore          7
        //   279: iload           5
        //   281: istore          6
        //   283: aload           7
        //   285: invokevirtual   java/io/File.exists:()Z
        //   288: ifeq            298
        //   291: aload           7
        //   293: invokevirtual   java/io/File.delete:()Z
        //   296: istore          6
        //   298: iload           6
        //   300: ifne            783
        //   303: new             Ljava/lang/Exception;
        //   306: dup            
        //   307: ldc             "Can't delete ipc marker."
        //   309: invokespecial   java/lang/Exception.<init>:(Ljava/lang/String;)V
        //   312: astore          7
        //   314: aload           7
        //   316: ifnull          150
        //   319: aload           10
        //   321: ldc             "ipc"
        //   323: getstatic       com/facebook/ads/internal/w/h/b.ac:I
        //   326: aload           7
        //   328: invokestatic    com/facebook/ads/internal/w/h/a.a:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
        //   331: goto            150
        //   334: astore_1       
        //   335: aload_0        
        //   336: getstatic       com/facebook/ads/internal/protocol/AdErrorType.PARSER_FAILURE:Lcom/facebook/ads/internal/protocol/AdErrorType;
        //   339: aload_1        
        //   340: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   343: invokestatic    com/facebook/ads/internal/protocol/a.a:(Lcom/facebook/ads/internal/protocol/AdErrorType;Ljava/lang/String;)Lcom/facebook/ads/internal/protocol/a;
        //   346: invokespecial   com/facebook/ads/internal/u/c.a:(Lcom/facebook/ads/internal/protocol/a;)V
        //   349: return         
        //   350: aload_0        
        //   351: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   354: invokestatic    com/facebook/ads/internal/r/a.z:(Landroid/content/Context;)Z
        //   357: ifeq            371
        //   360: aload_0        
        //   361: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   364: aload_0        
        //   365: invokespecial   com/facebook/ads/internal/u/c.c:()Lcom/facebook/ads/internal/v/a/b;
        //   368: invokestatic    com/facebook/ads/internal/p/a.a:(Landroid/content/Context;Lcom/facebook/ads/internal/v/a/b;)V
        //   371: aload           8
        //   373: checkcast       Lcom/facebook/ads/internal/u/f;
        //   376: astore          7
        //   378: aload           9
        //   380: ifnull          720
        //   383: aload           9
        //   385: invokevirtual   com/facebook/ads/internal/m/c.a:()Lcom/facebook/ads/internal/m/d;
        //   388: invokevirtual   com/facebook/ads/internal/m/d.e:()Z
        //   391: ifeq            402
        //   394: aload_1        
        //   395: aload_0        
        //   396: getfield        com/facebook/ads/internal/u/c.g:Lcom/facebook/ads/internal/u/b;
        //   399: invokestatic    com/facebook/ads/internal/u/a.a:(Ljava/lang/String;Lcom/facebook/ads/internal/u/b;)V
        //   402: aload_0        
        //   403: getfield        com/facebook/ads/internal/u/c.e:Ljava/util/Map;
        //   406: ifnull          819
        //   409: aload_0        
        //   410: getfield        com/facebook/ads/internal/u/c.e:Ljava/util/Map;
        //   413: ldc_w           "CLIENT_REQUEST_ID"
        //   416: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   421: checkcast       Ljava/lang/String;
        //   424: astore_1       
        //   425: aload           8
        //   427: invokevirtual   com/facebook/ads/internal/u/e.c:()Ljava/lang/String;
        //   430: astore          9
        //   432: aload           9
        //   434: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   437: ifne            682
        //   440: aload_1        
        //   441: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   444: ifne            682
        //   447: new             Ljava/lang/StringBuilder;
        //   450: dup            
        //   451: invokespecial   java/lang/StringBuilder.<init>:()V
        //   454: astore          10
        //   456: iload           4
        //   458: ldc_w           "73q8p304q6q511r89s8os2801s1o9sq1"
        //   461: invokevirtual   java/lang/String.length:()I
        //   464: if_icmpge       507
        //   467: ldc_w           "73q8p304q6q511r89s8os2801s1o9sq1"
        //   470: iload           4
        //   472: invokevirtual   java/lang/String.charAt:(I)C
        //   475: istore_3       
        //   476: iload_3        
        //   477: bipush          97
        //   479: if_icmplt       798
        //   482: iload_3        
        //   483: bipush          109
        //   485: if_icmple       810
        //   488: goto            798
        //   491: aload           10
        //   493: iload_2        
        //   494: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   497: pop            
        //   498: iload           4
        //   500: iconst_1       
        //   501: iadd           
        //   502: istore          4
        //   504: goto            456
        //   507: new             Ljava/lang/StringBuilder;
        //   510: dup            
        //   511: invokespecial   java/lang/StringBuilder.<init>:()V
        //   514: aload_1        
        //   515: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   518: aload           9
        //   520: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   523: aload           10
        //   525: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   528: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   531: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   534: ldc_w           "iso-8859-1"
        //   537: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //   540: astore          11
        //   542: ldc_w           "SHA-1"
        //   545: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   548: astore          12
        //   550: aload           12
        //   552: aload           11
        //   554: iconst_0       
        //   555: aload           11
        //   557: arraylength    
        //   558: invokevirtual   java/security/MessageDigest.update:([BII)V
        //   561: aload           12
        //   563: invokevirtual   java/security/MessageDigest.digest:()[B
        //   566: invokestatic    com/facebook/ads/internal/w/b/i.a:([B)Ljava/lang/String;
        //   569: astore          11
        //   571: aload           8
        //   573: invokevirtual   com/facebook/ads/internal/u/e.d:()Ljava/lang/String;
        //   576: aload           11
        //   578: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   581: ifne            604
        //   584: aload_0        
        //   585: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   588: ldc_w           "network"
        //   591: getstatic       com/facebook/ads/internal/w/h/b.t:I
        //   594: new             Lcom/facebook/ads/internal/protocol/h;
        //   597: dup            
        //   598: invokespecial   com/facebook/ads/internal/protocol/h.<init>:()V
        //   601: invokestatic    com/facebook/ads/internal/w/h/a.b:(Landroid/content/Context;Ljava/lang/String;ILjava/lang/Exception;)V
        //   604: new             Ljava/lang/StringBuilder;
        //   607: dup            
        //   608: invokespecial   java/lang/StringBuilder.<init>:()V
        //   611: aload           9
        //   613: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   616: aload_1        
        //   617: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   620: aload           10
        //   622: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   625: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   628: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   631: ldc_w           "iso-8859-1"
        //   634: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //   637: astore          10
        //   639: ldc_w           "SHA-1"
        //   642: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //   645: astore          11
        //   647: aload           11
        //   649: aload           10
        //   651: iconst_0       
        //   652: aload           10
        //   654: arraylength    
        //   655: invokevirtual   java/security/MessageDigest.update:([BII)V
        //   658: new             Lcom/facebook/ads/internal/k/a;
        //   661: dup            
        //   662: aload           9
        //   664: aload           11
        //   666: invokevirtual   java/security/MessageDigest.digest:()[B
        //   669: invokestatic    com/facebook/ads/internal/w/b/i.a:([B)Ljava/lang/String;
        //   672: invokespecial   com/facebook/ads/internal/k/a.<init>:(Ljava/lang/String;Ljava/lang/String;)V
        //   675: aload_0        
        //   676: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   679: invokestatic    com/facebook/ads/internal/k/e.a:(Lcom/facebook/ads/internal/k/d;Landroid/content/Context;)V
        //   682: aload           8
        //   684: invokevirtual   com/facebook/ads/internal/u/e.e:()Ljava/lang/String;
        //   687: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   690: ifne            720
        //   693: aload_1        
        //   694: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //   697: ifne            720
        //   700: new             Lcom/facebook/ads/internal/q/a;
        //   703: dup            
        //   704: aload_0        
        //   705: getfield        com/facebook/ads/internal/u/c.b:Landroid/content/Context;
        //   708: aload_1        
        //   709: aload           8
        //   711: invokevirtual   com/facebook/ads/internal/u/e.e:()Ljava/lang/String;
        //   714: invokespecial   com/facebook/ads/internal/q/a.<init>:(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;)V
        //   717: invokevirtual   com/facebook/ads/internal/q/a.a:()V
        //   720: aload_0        
        //   721: aload           7
        //   723: invokespecial   com/facebook/ads/internal/u/c.a:(Lcom/facebook/ads/internal/u/f;)V
        //   726: return         
        //   727: aload           8
        //   729: checkcast       Lcom/facebook/ads/internal/u/g;
        //   732: astore          8
        //   734: aload           8
        //   736: invokevirtual   com/facebook/ads/internal/u/g.f:()Ljava/lang/String;
        //   739: astore          7
        //   741: aload           8
        //   743: invokevirtual   com/facebook/ads/internal/u/g.g:()I
        //   746: getstatic       com/facebook/ads/internal/protocol/AdErrorType.ERROR_MESSAGE:Lcom/facebook/ads/internal/protocol/AdErrorType;
        //   749: invokestatic    com/facebook/ads/internal/protocol/AdErrorType.adErrorTypeFromCode:(ILcom/facebook/ads/internal/protocol/AdErrorType;)Lcom/facebook/ads/internal/protocol/AdErrorType;
        //   752: astore          8
        //   754: aload           7
        //   756: ifnull          762
        //   759: aload           7
        //   761: astore_1       
        //   762: aload_0        
        //   763: aload           8
        //   765: aload_1        
        //   766: invokestatic    com/facebook/ads/internal/protocol/a.a:(Lcom/facebook/ads/internal/protocol/AdErrorType;Ljava/lang/String;)Lcom/facebook/ads/internal/protocol/a;
        //   769: invokespecial   com/facebook/ads/internal/u/c.a:(Lcom/facebook/ads/internal/protocol/a;)V
        //   772: return         
        //   773: astore          7
        //   775: goto            314
        //   778: astore          7
        //   780: goto            133
        //   783: aconst_null    
        //   784: astore          7
        //   786: goto            314
        //   789: aconst_null    
        //   790: astore          7
        //   792: goto            133
        //   795: goto            224
        //   798: iload_3        
        //   799: bipush          65
        //   801: if_icmplt       824
        //   804: iload_3        
        //   805: bipush          77
        //   807: if_icmpgt       824
        //   810: iload_3        
        //   811: bipush          13
        //   813: iadd           
        //   814: i2c            
        //   815: istore_2       
        //   816: goto            491
        //   819: aconst_null    
        //   820: astore_1       
        //   821: goto            425
        //   824: iload_3        
        //   825: bipush          110
        //   827: if_icmplt       836
        //   830: iload_3        
        //   831: bipush          122
        //   833: if_icmple       852
        //   836: iload_3        
        //   837: istore_2       
        //   838: iload_3        
        //   839: bipush          78
        //   841: if_icmplt       491
        //   844: iload_3        
        //   845: istore_2       
        //   846: iload_3        
        //   847: bipush          90
        //   849: if_icmpgt       491
        //   852: iload_3        
        //   853: bipush          13
        //   855: isub           
        //   856: i2c            
        //   857: istore_2       
        //   858: goto            491
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  9      26     334    350    Ljava/lang/Exception;
        //  31     65     334    350    Ljava/lang/Exception;
        //  82     98     778    783    Ljava/lang/Exception;
        //  102    117    778    783    Ljava/lang/Exception;
        //  122    133    778    783    Ljava/lang/Exception;
        //  138    150    334    350    Ljava/lang/Exception;
        //  150    189    334    350    Ljava/lang/Exception;
        //  189    224    334    350    Ljava/lang/Exception;
        //  224    235    334    350    Ljava/lang/Exception;
        //  236    242    334    350    Ljava/lang/Exception;
        //  263    279    773    778    Ljava/lang/Exception;
        //  283    298    773    778    Ljava/lang/Exception;
        //  303    314    773    778    Ljava/lang/Exception;
        //  319    331    334    350    Ljava/lang/Exception;
        //  350    371    334    350    Ljava/lang/Exception;
        //  371    378    334    350    Ljava/lang/Exception;
        //  383    402    334    350    Ljava/lang/Exception;
        //  402    425    334    350    Ljava/lang/Exception;
        //  425    456    334    350    Ljava/lang/Exception;
        //  456    476    334    350    Ljava/lang/Exception;
        //  491    498    334    350    Ljava/lang/Exception;
        //  507    604    334    350    Ljava/lang/Exception;
        //  604    682    334    350    Ljava/lang/Exception;
        //  682    720    334    350    Ljava/lang/Exception;
        //  720    726    334    350    Ljava/lang/Exception;
        //  727    754    334    350    Ljava/lang/Exception;
        //  762    772    334    350    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0117:
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
    
    private com.facebook.ads.internal.v.a.b c() {
        return new com.facebook.ads.internal.v.a.b() {
            void a(final m m) {
                com.facebook.ads.internal.u.a.b(com.facebook.ads.internal.u.c.this.g);
                com.facebook.ads.internal.u.c.this.h = null;
                try {
                    final com.facebook.ads.internal.v.a.n a = m.a();
                    if (a != null) {
                        String e = a.e();
                        final e a2 = com.facebook.ads.internal.u.c.this.c.a(e);
                        if (a2.b() == com.facebook.ads.internal.u.e.a.b) {
                            final g g = (g)a2;
                            final String f = g.f();
                            final AdErrorType adErrorTypeFromCode = AdErrorType.adErrorTypeFromCode(g.g(), AdErrorType.ERROR_MESSAGE);
                            final c a3 = com.facebook.ads.internal.u.c.this;
                            if (f != null) {
                                e = f;
                            }
                            a3.a(com.facebook.ads.internal.protocol.a.a(adErrorTypeFromCode, e));
                            return;
                        }
                    }
                }
                catch (JSONException ex) {}
                com.facebook.ads.internal.u.c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, m.getMessage()));
            }
            
            @Override
            public void a(final com.facebook.ads.internal.v.a.n n) {
                if (n != null) {
                    final String e = n.e();
                    com.facebook.ads.internal.u.a.b(com.facebook.ads.internal.u.c.this.g);
                    com.facebook.ads.internal.u.c.this.h = null;
                    com.facebook.ads.internal.u.c.this.a(e);
                }
            }
            
            @Override
            public void a(final Exception ex) {
                if (m.class.equals(ex.getClass())) {
                    this.a((m)ex);
                    return;
                }
                com.facebook.ads.internal.u.c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.NETWORK_ERROR, ex.getMessage()));
            }
        };
    }
    
    public void a() {
        if (this.h != null) {
            this.h.c(1);
            this.h.b(1);
            this.h = null;
        }
    }
    
    public void a(final com.facebook.ads.internal.u.b b) {
        this.a(b, false);
    }
    
    public void a(final com.facebook.ads.internal.u.b g, final boolean b) {
        this.a();
        if (!b && com.facebook.ads.internal.u.c.a != null) {
            final c a = com.facebook.ads.internal.u.c.a.a(this, g);
            if (a != null) {
                if (a.a != null) {
                    this.a(a.a);
                    return;
                }
                if (a.b != null) {
                    this.a(a.b);
                    return;
                }
            }
        }
        if (u.a(this.b) == u.a.b) {
            this.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NETWORK_ERROR, "No network connection"));
            return;
        }
        this.g = g;
        com.facebook.ads.internal.l.a.a(this.b);
        if (!com.facebook.ads.internal.u.a.a(g)) {
            com.facebook.ads.internal.u.c.k.submit(new Runnable() {
                @Override
                public void run() {
                    com.facebook.ads.internal.g.b.a(com.facebook.ads.internal.u.c.this.b);
                    com.facebook.ads.internal.n.d.a(com.facebook.ads.internal.u.c.this.b);
                    if (g.f().a()) {
                        while (true) {
                            try {
                                g.f().a(com.facebook.ads.internal.g.b.b);
                                com.facebook.ads.internal.u.c.this.a(g.f().b());
                                return;
                            }
                            catch (com.facebook.ads.internal.protocol.b b) {
                                com.facebook.ads.internal.u.c.this.a(com.facebook.ads.internal.protocol.a.a(b));
                                continue;
                            }
                            break;
                        }
                    }
                    com.facebook.ads.internal.u.c.this.e = g.g();
                    if (b && com.facebook.ads.internal.u.c.a != null) {
                        com.facebook.ads.internal.u.c.a.a(com.facebook.ads.internal.u.c.this, com.facebook.ads.internal.u.c.this.e);
                    }
                Label_0261_Outer:
                    while (true) {
                    Label_0261:
                        while (true) {
                            while (true) {
                                try {
                                    com.facebook.ads.internal.u.c.this.e.put("M_BANNER_KEY", new String(Base64.encode((com.facebook.ads.internal.u.c.this.b.getPackageName() + " " + com.facebook.ads.internal.u.c.this.b.getPackageManager().getInstallerPackageName(com.facebook.ads.internal.u.c.this.b.getPackageName())).getBytes(), 2)));
                                    while (true) {
                                        Label_0344: {
                                            try {
                                                if (g.a() == com.facebook.ads.internal.protocol.e.l || g.a() == com.facebook.ads.internal.protocol.e.j || g.a() == com.facebook.ads.internal.protocol.e.k) {
                                                    break;
                                                }
                                                if (g.a() == null) {
                                                    break;
                                                }
                                                break Label_0344;
                                                final boolean b2;
                                                com.facebook.ads.internal.u.c.this.h = com.facebook.ads.internal.w.e.d.a(com.facebook.ads.internal.u.c.this.b, b2);
                                                com.facebook.ads.internal.u.c.this.h.b(com.facebook.ads.internal.u.c.this.i, com.facebook.ads.internal.u.c.this.h.a().a(com.facebook.ads.internal.u.c.this.e), com.facebook.ads.internal.u.c.this.c());
                                                return;
                                            }
                                            catch (Exception ex) {
                                                com.facebook.ads.internal.u.c.this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.AD_REQUEST_FAILED, ex.getMessage()));
                                                return;
                                            }
                                        }
                                        final boolean b2 = false;
                                        continue Label_0261;
                                    }
                                }
                                catch (Exception ex2) {
                                    continue Label_0261_Outer;
                                }
                                break;
                            }
                            final boolean b2 = true;
                            continue Label_0261;
                        }
                    }
                }
            });
            return;
        }
        final String c = com.facebook.ads.internal.u.a.c(g);
        if (c != null) {
            this.a(c);
            return;
        }
        this.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.LOAD_TOO_FREQUENTLY, null));
    }
    
    public void a(final b f) {
        this.f = f;
    }
    
    public interface a
    {
        c a(final c p0, final com.facebook.ads.internal.u.b p1);
        
        void a(final c p0, final Map<String, String> p1);
    }
    
    public interface b
    {
        void a(final com.facebook.ads.internal.protocol.a p0);
        
        void a(final f p0);
    }
    
    public static class c
    {
        @Nullable
        public final f a;
        @Nullable
        public final com.facebook.ads.internal.protocol.a b;
    }
}
