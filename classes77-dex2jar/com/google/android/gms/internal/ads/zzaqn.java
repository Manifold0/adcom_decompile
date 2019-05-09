// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Collections;
import java.util.HashSet;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Set;

@zzadh
public final class zzaqn extends zzaqh
{
    private static final Set<String> zzdbg;
    private static final DecimalFormat zzdbh;
    private File zzdbi;
    private boolean zzdbj;
    
    static {
        zzdbg = Collections.synchronizedSet(new HashSet<String>());
        zzdbh = new DecimalFormat("#,###");
    }
    
    public zzaqn(final zzapw zzapw) {
        super(zzapw);
        final File cacheDir = this.mContext.getCacheDir();
        if (cacheDir == null) {
            zzakb.zzdk("Context.getCacheDir() returned null");
        }
        else {
            this.zzdbi = new File(cacheDir, "admobVideoStreams");
            if (!this.zzdbi.isDirectory() && !this.zzdbi.mkdirs()) {
                final String value = String.valueOf(this.zzdbi.getAbsolutePath());
                String concat;
                if (value.length() != 0) {
                    concat = "Could not create preload cache directory at ".concat(value);
                }
                else {
                    concat = new String("Could not create preload cache directory at ");
                }
                zzakb.zzdk(concat);
                this.zzdbi = null;
                return;
            }
            if (!this.zzdbi.setReadable(true, false) || !this.zzdbi.setExecutable(true, false)) {
                final String value2 = String.valueOf(this.zzdbi.getAbsolutePath());
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Could not set cache file permissions at ".concat(value2);
                }
                else {
                    concat2 = new String("Could not set cache file permissions at ");
                }
                zzakb.zzdk(concat2);
                this.zzdbi = null;
            }
        }
    }
    
    private final File zzc(final File file) {
        return new File(this.zzdbi, String.valueOf(file.getName()).concat(".done"));
    }
    
    @Override
    public final void abort() {
        this.zzdbj = true;
    }
    
    @Override
    public final boolean zzdp(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //     4: ifnonnull       18
        //     7: aload_0        
        //     8: aload_1        
        //     9: aconst_null    
        //    10: ldc             "noCacheDir"
        //    12: aconst_null    
        //    13: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //    22: ifnonnull       81
        //    25: iconst_0       
        //    26: istore_2       
        //    27: getstatic       com/google/android/gms/internal/ads/zznk.zzaux:Lcom/google/android/gms/internal/ads/zzna;
        //    30: astore          12
        //    32: iload_2        
        //    33: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //    36: aload           12
        //    38: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //    41: checkcast       Ljava/lang/Integer;
        //    44: invokevirtual   java/lang/Integer.intValue:()I
        //    47: if_icmple       266
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //    54: ifnonnull       141
        //    57: iconst_0       
        //    58: istore          6
        //    60: iload           6
        //    62: ifne            18
        //    65: ldc             "Unable to expire stream cache"
        //    67: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //    70: aload_0        
        //    71: aload_1        
        //    72: aconst_null    
        //    73: ldc             "expireFailed"
        //    75: aconst_null    
        //    76: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //    79: iconst_0       
        //    80: ireturn        
        //    81: aload_0        
        //    82: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //    85: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //    88: astore          12
        //    90: aload           12
        //    92: arraylength    
        //    93: istore          5
        //    95: iconst_0       
        //    96: istore_2       
        //    97: iconst_0       
        //    98: istore_3       
        //    99: iload_3        
        //   100: iload           5
        //   102: if_icmpge       138
        //   105: iload_2        
        //   106: istore          4
        //   108: aload           12
        //   110: iload_3        
        //   111: aaload         
        //   112: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   115: ldc             ".done"
        //   117: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   120: ifne            128
        //   123: iload_2        
        //   124: iconst_1       
        //   125: iadd           
        //   126: istore          4
        //   128: iload_3        
        //   129: iconst_1       
        //   130: iadd           
        //   131: istore_3       
        //   132: iload           4
        //   134: istore_2       
        //   135: goto            99
        //   138: goto            27
        //   141: aconst_null    
        //   142: astore          12
        //   144: ldc2_w          9223372036854775807
        //   147: lstore          8
        //   149: aload_0        
        //   150: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //   153: invokevirtual   java/io/File.listFiles:()[Ljava/io/File;
        //   156: astore          14
        //   158: aload           14
        //   160: arraylength    
        //   161: istore_3       
        //   162: iconst_0       
        //   163: istore_2       
        //   164: iload_2        
        //   165: iload_3        
        //   166: if_icmpge       218
        //   169: aload           14
        //   171: iload_2        
        //   172: aaload         
        //   173: astore          13
        //   175: aload           13
        //   177: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   180: ldc             ".done"
        //   182: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //   185: ifne            2221
        //   188: aload           13
        //   190: invokevirtual   java/io/File.lastModified:()J
        //   193: lstore          10
        //   195: lload           10
        //   197: lload           8
        //   199: lcmp           
        //   200: ifge            2221
        //   203: aload           13
        //   205: astore          12
        //   207: lload           10
        //   209: lstore          8
        //   211: iload_2        
        //   212: iconst_1       
        //   213: iadd           
        //   214: istore_2       
        //   215: goto            164
        //   218: iconst_0       
        //   219: istore          6
        //   221: aload           12
        //   223: ifnull          60
        //   226: aload           12
        //   228: invokevirtual   java/io/File.delete:()Z
        //   231: istore          7
        //   233: aload_0        
        //   234: aload           12
        //   236: invokespecial   com/google/android/gms/internal/ads/zzaqn.zzc:(Ljava/io/File;)Ljava/io/File;
        //   239: astore          12
        //   241: iload           7
        //   243: istore          6
        //   245: aload           12
        //   247: invokevirtual   java/io/File.isFile:()Z
        //   250: ifeq            60
        //   253: iload           7
        //   255: aload           12
        //   257: invokevirtual   java/io/File.delete:()Z
        //   260: iand           
        //   261: istore          6
        //   263: goto            60
        //   266: invokestatic    com/google/android/gms/internal/ads/zzkb.zzif:()Lcom/google/android/gms/internal/ads/zzamu;
        //   269: pop            
        //   270: aload_1        
        //   271: invokestatic    com/google/android/gms/internal/ads/zzamu.zzde:(Ljava/lang/String;)Ljava/lang/String;
        //   274: astore          12
        //   276: new             Ljava/io/File;
        //   279: dup            
        //   280: aload_0        
        //   281: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //   284: aload           12
        //   286: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   289: astore          19
        //   291: aload_0        
        //   292: aload           19
        //   294: invokespecial   com/google/android/gms/internal/ads/zzaqn.zzc:(Ljava/io/File;)Ljava/io/File;
        //   297: astore          14
        //   299: aload           19
        //   301: invokevirtual   java/io/File.isFile:()Z
        //   304: ifeq            377
        //   307: aload           14
        //   309: invokevirtual   java/io/File.isFile:()Z
        //   312: ifeq            377
        //   315: aload           19
        //   317: invokevirtual   java/io/File.length:()J
        //   320: l2i            
        //   321: istore_2       
        //   322: aload_1        
        //   323: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   326: astore          12
        //   328: aload           12
        //   330: invokevirtual   java/lang/String.length:()I
        //   333: ifeq            363
        //   336: ldc             "Stream cache hit at "
        //   338: aload           12
        //   340: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   343: astore          12
        //   345: aload           12
        //   347: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   350: aload_0        
        //   351: aload_1        
        //   352: aload           19
        //   354: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   357: iload_2        
        //   358: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;I)V
        //   361: iconst_1       
        //   362: ireturn        
        //   363: new             Ljava/lang/String;
        //   366: dup            
        //   367: ldc             "Stream cache hit at "
        //   369: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   372: astore          12
        //   374: goto            345
        //   377: aload_0        
        //   378: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbi:Ljava/io/File;
        //   381: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   384: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   387: astore          12
        //   389: aload_1        
        //   390: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   393: astore          13
        //   395: aload           13
        //   397: invokevirtual   java/lang/String.length:()I
        //   400: ifeq            485
        //   403: aload           12
        //   405: aload           13
        //   407: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   410: astore          18
        //   412: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //   415: astore          13
        //   417: aload           13
        //   419: monitorenter   
        //   420: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //   423: aload           18
        //   425: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   430: ifeq            513
        //   433: aload_1        
        //   434: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   437: astore          12
        //   439: aload           12
        //   441: invokevirtual   java/lang/String.length:()I
        //   444: ifeq            499
        //   447: ldc             "Stream cache already in progress at "
        //   449: aload           12
        //   451: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   454: astore          12
        //   456: aload           12
        //   458: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   461: aload_0        
        //   462: aload_1        
        //   463: aload           19
        //   465: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   468: ldc             "inProgress"
        //   470: aconst_null    
        //   471: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   474: aload           13
        //   476: monitorexit    
        //   477: iconst_0       
        //   478: ireturn        
        //   479: astore_1       
        //   480: aload           13
        //   482: monitorexit    
        //   483: aload_1        
        //   484: athrow         
        //   485: new             Ljava/lang/String;
        //   488: dup            
        //   489: aload           12
        //   491: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   494: astore          18
        //   496: goto            412
        //   499: new             Ljava/lang/String;
        //   502: dup            
        //   503: ldc             "Stream cache already in progress at "
        //   505: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   508: astore          12
        //   510: goto            456
        //   513: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //   516: aload           18
        //   518: invokeinterface java/util/Set.add:(Ljava/lang/Object;)Z
        //   523: pop            
        //   524: aload           13
        //   526: monitorexit    
        //   527: aconst_null    
        //   528: astore          15
        //   530: invokestatic    com/google/android/gms/ads/internal/zzbv.zzew:()Lcom/google/android/gms/internal/ads/zzaok;
        //   533: pop            
        //   534: getstatic       com/google/android/gms/internal/ads/zznk.zzavc:Lcom/google/android/gms/internal/ads/zzna;
        //   537: astore          12
        //   539: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   542: aload           12
        //   544: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   547: checkcast       Ljava/lang/Integer;
        //   550: invokevirtual   java/lang/Integer.intValue:()I
        //   553: istore_3       
        //   554: new             Ljava/net/URL;
        //   557: dup            
        //   558: aload_1        
        //   559: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   562: astore          12
        //   564: iconst_0       
        //   565: istore_2       
        //   566: iload_2        
        //   567: iconst_1       
        //   568: iadd           
        //   569: istore_2       
        //   570: iload_2        
        //   571: bipush          20
        //   573: if_icmpgt       1013
        //   576: aload           12
        //   578: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //   581: astore          13
        //   583: aload           13
        //   585: iload_3        
        //   586: invokevirtual   java/net/URLConnection.setConnectTimeout:(I)V
        //   589: aload           13
        //   591: iload_3        
        //   592: invokevirtual   java/net/URLConnection.setReadTimeout:(I)V
        //   595: aload           13
        //   597: instanceof      Ljava/net/HttpURLConnection;
        //   600: ifne            767
        //   603: new             Ljava/io/IOException;
        //   606: dup            
        //   607: ldc             "Invalid protocol."
        //   609: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   612: athrow         
        //   613: astore          12
        //   615: aconst_null    
        //   616: astore          13
        //   618: ldc             "error"
        //   620: astore          14
        //   622: aload           12
        //   624: instanceof      Ljava/lang/RuntimeException;
        //   627: ifeq            640
        //   630: invokestatic    com/google/android/gms/ads/internal/zzbv.zzeo:()Lcom/google/android/gms/internal/ads/zzajm;
        //   633: aload           12
        //   635: ldc             "VideoStreamFullFileCache.preload"
        //   637: invokevirtual   com/google/android/gms/internal/ads/zzajm.zza:(Ljava/lang/Throwable;Ljava/lang/String;)V
        //   640: aload           15
        //   642: invokevirtual   java/io/FileOutputStream.close:()V
        //   645: aload_0        
        //   646: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbj:Z
        //   649: ifeq            2119
        //   652: new             Ljava/lang/StringBuilder;
        //   655: dup            
        //   656: aload_1        
        //   657: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   660: invokevirtual   java/lang/String.length:()I
        //   663: bipush          26
        //   665: iadd           
        //   666: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   669: ldc_w           "Preload aborted for URL \""
        //   672: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   675: aload_1        
        //   676: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: ldc_w           "\""
        //   682: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   685: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   688: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdj:(Ljava/lang/String;)V
        //   691: aload           19
        //   693: invokevirtual   java/io/File.exists:()Z
        //   696: ifeq            740
        //   699: aload           19
        //   701: invokevirtual   java/io/File.delete:()Z
        //   704: ifne            740
        //   707: aload           19
        //   709: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   712: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   715: astore          12
        //   717: aload           12
        //   719: invokevirtual   java/lang/String.length:()I
        //   722: ifeq            2163
        //   725: ldc_w           "Could not delete partial cache file at "
        //   728: aload           12
        //   730: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   733: astore          12
        //   735: aload           12
        //   737: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //   740: aload_0        
        //   741: aload_1        
        //   742: aload           19
        //   744: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   747: aload           14
        //   749: aload           13
        //   751: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //   754: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //   757: aload           18
        //   759: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //   764: pop            
        //   765: iconst_0       
        //   766: ireturn        
        //   767: aload           13
        //   769: checkcast       Ljava/net/HttpURLConnection;
        //   772: astore          16
        //   774: new             Lcom/google/android/gms/internal/ads/zzamy;
        //   777: dup            
        //   778: invokespecial   com/google/android/gms/internal/ads/zzamy.<init>:()V
        //   781: astore          13
        //   783: aload           13
        //   785: aload           16
        //   787: aconst_null    
        //   788: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;[B)V
        //   791: aload           16
        //   793: iconst_0       
        //   794: invokevirtual   java/net/HttpURLConnection.setInstanceFollowRedirects:(Z)V
        //   797: aload           16
        //   799: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   802: istore          4
        //   804: aload           13
        //   806: aload           16
        //   808: iload           4
        //   810: invokevirtual   com/google/android/gms/internal/ads/zzamy.zza:(Ljava/net/HttpURLConnection;I)V
        //   813: iload           4
        //   815: bipush          100
        //   817: idiv           
        //   818: iconst_3       
        //   819: if_icmpne       1024
        //   822: aload           16
        //   824: ldc_w           "Location"
        //   827: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   830: astore          17
        //   832: aload           17
        //   834: ifnonnull       848
        //   837: new             Ljava/io/IOException;
        //   840: dup            
        //   841: ldc_w           "Missing Location header in redirect"
        //   844: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   847: athrow         
        //   848: new             Ljava/net/URL;
        //   851: dup            
        //   852: aload           12
        //   854: aload           17
        //   856: invokespecial   java/net/URL.<init>:(Ljava/net/URL;Ljava/lang/String;)V
        //   859: astore          13
        //   861: aload           13
        //   863: invokevirtual   java/net/URL.getProtocol:()Ljava/lang/String;
        //   866: astore          12
        //   868: aload           12
        //   870: ifnonnull       884
        //   873: new             Ljava/io/IOException;
        //   876: dup            
        //   877: ldc_w           "Protocol is null"
        //   880: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   883: athrow         
        //   884: aload           12
        //   886: ldc_w           "http"
        //   889: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   892: ifne            956
        //   895: aload           12
        //   897: ldc_w           "https"
        //   900: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   903: ifne            956
        //   906: aload           12
        //   908: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   911: astore          12
        //   913: aload           12
        //   915: invokevirtual   java/lang/String.length:()I
        //   918: ifeq            941
        //   921: ldc_w           "Unsupported scheme: "
        //   924: aload           12
        //   926: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   929: astore          12
        //   931: new             Ljava/io/IOException;
        //   934: dup            
        //   935: aload           12
        //   937: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   940: athrow         
        //   941: new             Ljava/lang/String;
        //   944: dup            
        //   945: ldc_w           "Unsupported scheme: "
        //   948: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   951: astore          12
        //   953: goto            931
        //   956: aload           17
        //   958: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   961: astore          12
        //   963: aload           12
        //   965: invokevirtual   java/lang/String.length:()I
        //   968: ifeq            998
        //   971: ldc_w           "Redirecting to "
        //   974: aload           12
        //   976: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   979: astore          12
        //   981: aload           12
        //   983: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //   986: aload           16
        //   988: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   991: aload           13
        //   993: astore          12
        //   995: goto            566
        //   998: new             Ljava/lang/String;
        //  1001: dup            
        //  1002: ldc_w           "Redirecting to "
        //  1005: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1008: astore          12
        //  1010: goto            981
        //  1013: new             Ljava/io/IOException;
        //  1016: dup            
        //  1017: ldc_w           "Too many redirects (20)"
        //  1020: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1023: athrow         
        //  1024: aload           16
        //  1026: instanceof      Ljava/net/HttpURLConnection;
        //  1029: ifeq            1156
        //  1032: aload           16
        //  1034: checkcast       Ljava/net/HttpURLConnection;
        //  1037: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //  1040: istore_2       
        //  1041: iload_2        
        //  1042: sipush          400
        //  1045: if_icmplt       1156
        //  1048: ldc_w           "badUrl"
        //  1051: astore          14
        //  1053: iload_2        
        //  1054: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  1057: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1060: astore          12
        //  1062: aload           12
        //  1064: invokevirtual   java/lang/String.length:()I
        //  1067: ifeq            1133
        //  1070: ldc_w           "HTTP request failed. Code: "
        //  1073: aload           12
        //  1075: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1078: astore          13
        //  1080: new             Ljava/io/IOException;
        //  1083: dup            
        //  1084: new             Ljava/lang/StringBuilder;
        //  1087: dup            
        //  1088: aload_1        
        //  1089: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1092: invokevirtual   java/lang/String.length:()I
        //  1095: bipush          32
        //  1097: iadd           
        //  1098: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1101: ldc_w           "HTTP status code "
        //  1104: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1107: iload_2        
        //  1108: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //  1111: ldc_w           " at "
        //  1114: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1117: aload_1        
        //  1118: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1121: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1124: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1127: athrow         
        //  1128: astore          12
        //  1130: goto            622
        //  1133: new             Ljava/lang/String;
        //  1136: dup            
        //  1137: ldc_w           "HTTP request failed. Code: "
        //  1140: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1143: astore          13
        //  1145: goto            1080
        //  1148: astore          12
        //  1150: aconst_null    
        //  1151: astore          13
        //  1153: goto            622
        //  1156: aload           16
        //  1158: invokevirtual   java/net/URLConnection.getContentLength:()I
        //  1161: istore          4
        //  1163: iload           4
        //  1165: ifge            1239
        //  1168: aload_1        
        //  1169: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1172: astore          12
        //  1174: aload           12
        //  1176: invokevirtual   java/lang/String.length:()I
        //  1179: ifeq            1224
        //  1182: ldc_w           "Stream cache aborted, missing content-length header at "
        //  1185: aload           12
        //  1187: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1190: astore          12
        //  1192: aload           12
        //  1194: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //  1197: aload_0        
        //  1198: aload_1        
        //  1199: aload           19
        //  1201: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  1204: ldc_w           "contentLengthMissing"
        //  1207: aconst_null    
        //  1208: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //  1211: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //  1214: aload           18
        //  1216: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //  1221: pop            
        //  1222: iconst_0       
        //  1223: ireturn        
        //  1224: new             Ljava/lang/String;
        //  1227: dup            
        //  1228: ldc_w           "Stream cache aborted, missing content-length header at "
        //  1231: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1234: astore          12
        //  1236: goto            1192
        //  1239: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbh:Ljava/text/DecimalFormat;
        //  1242: iload           4
        //  1244: i2l            
        //  1245: invokevirtual   java/text/DecimalFormat.format:(J)Ljava/lang/String;
        //  1248: astore          12
        //  1250: getstatic       com/google/android/gms/internal/ads/zznk.zzauy:Lcom/google/android/gms/internal/ads/zzna;
        //  1253: astore          13
        //  1255: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  1258: aload           13
        //  1260: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  1263: checkcast       Ljava/lang/Integer;
        //  1266: invokevirtual   java/lang/Integer.intValue:()I
        //  1269: istore          5
        //  1271: iload           4
        //  1273: iload           5
        //  1275: if_icmple       1399
        //  1278: new             Ljava/lang/StringBuilder;
        //  1281: dup            
        //  1282: aload           12
        //  1284: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1287: invokevirtual   java/lang/String.length:()I
        //  1290: bipush          33
        //  1292: iadd           
        //  1293: aload_1        
        //  1294: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1297: invokevirtual   java/lang/String.length:()I
        //  1300: iadd           
        //  1301: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1304: ldc_w           "Content length "
        //  1307: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1310: aload           12
        //  1312: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1315: ldc_w           " exceeds limit at "
        //  1318: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1321: aload_1        
        //  1322: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1325: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1328: invokestatic    com/google/android/gms/internal/ads/zzakb.zzdk:(Ljava/lang/String;)V
        //  1331: aload           12
        //  1333: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1336: astore          12
        //  1338: aload           12
        //  1340: invokevirtual   java/lang/String.length:()I
        //  1343: ifeq            1384
        //  1346: ldc_w           "File too big for full file cache. Size: "
        //  1349: aload           12
        //  1351: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1354: astore          12
        //  1356: aload_0        
        //  1357: aload_1        
        //  1358: aload           19
        //  1360: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  1363: ldc_w           "sizeExceeded"
        //  1366: aload           12
        //  1368: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V
        //  1371: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //  1374: aload           18
        //  1376: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //  1381: pop            
        //  1382: iconst_0       
        //  1383: ireturn        
        //  1384: new             Ljava/lang/String;
        //  1387: dup            
        //  1388: ldc_w           "File too big for full file cache. Size: "
        //  1391: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1394: astore          12
        //  1396: goto            1356
        //  1399: new             Ljava/lang/StringBuilder;
        //  1402: dup            
        //  1403: aload           12
        //  1405: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1408: invokevirtual   java/lang/String.length:()I
        //  1411: bipush          20
        //  1413: iadd           
        //  1414: aload_1        
        //  1415: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1418: invokevirtual   java/lang/String.length:()I
        //  1421: iadd           
        //  1422: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1425: ldc_w           "Caching "
        //  1428: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1431: aload           12
        //  1433: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1436: ldc_w           " bytes from "
        //  1439: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1442: aload_1        
        //  1443: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1446: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1449: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //  1452: aload           16
        //  1454: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //  1457: invokestatic    java/nio/channels/Channels.newChannel:(Ljava/io/InputStream;)Ljava/nio/channels/ReadableByteChannel;
        //  1460: astore          13
        //  1462: new             Ljava/io/FileOutputStream;
        //  1465: dup            
        //  1466: aload           19
        //  1468: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //  1471: astore          12
        //  1473: aload           12
        //  1475: invokevirtual   java/io/FileOutputStream.getChannel:()Ljava/nio/channels/FileChannel;
        //  1478: astore          15
        //  1480: ldc_w           1048576
        //  1483: invokestatic    java/nio/ByteBuffer.allocate:(I)Ljava/nio/ByteBuffer;
        //  1486: astore          16
        //  1488: invokestatic    com/google/android/gms/ads/internal/zzbv.zzer:()Lcom/google/android/gms/common/util/Clock;
        //  1491: astore          17
        //  1493: iconst_0       
        //  1494: istore_2       
        //  1495: aload           17
        //  1497: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //  1502: lstore          8
        //  1504: getstatic       com/google/android/gms/internal/ads/zznk.zzavb:Lcom/google/android/gms/internal/ads/zzna;
        //  1507: astore          20
        //  1509: new             Lcom/google/android/gms/internal/ads/zzamj;
        //  1512: dup            
        //  1513: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  1516: aload           20
        //  1518: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  1521: checkcast       Ljava/lang/Long;
        //  1524: invokevirtual   java/lang/Long.longValue:()J
        //  1527: invokespecial   com/google/android/gms/internal/ads/zzamj.<init>:(J)V
        //  1530: astore          20
        //  1532: getstatic       com/google/android/gms/internal/ads/zznk.zzava:Lcom/google/android/gms/internal/ads/zzna;
        //  1535: astore          21
        //  1537: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //  1540: aload           21
        //  1542: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //  1545: checkcast       Ljava/lang/Long;
        //  1548: invokevirtual   java/lang/Long.longValue:()J
        //  1551: lstore          10
        //  1553: aload           13
        //  1555: aload           16
        //  1557: invokeinterface java/nio/channels/ReadableByteChannel.read:(Ljava/nio/ByteBuffer;)I
        //  1562: istore_3       
        //  1563: iload_3        
        //  1564: iflt            1981
        //  1567: iload_2        
        //  1568: iload_3        
        //  1569: iadd           
        //  1570: istore_3       
        //  1571: iload_3        
        //  1572: iload           5
        //  1574: if_icmple       1728
        //  1577: ldc_w           "sizeExceeded"
        //  1580: astore          13
        //  1582: aload           13
        //  1584: astore          14
        //  1586: aload           13
        //  1588: astore          15
        //  1590: iload_3        
        //  1591: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //  1594: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1597: astore          16
        //  1599: aload           13
        //  1601: astore          14
        //  1603: aload           13
        //  1605: astore          15
        //  1607: aload           16
        //  1609: invokevirtual   java/lang/String.length:()I
        //  1612: ifeq            1685
        //  1615: aload           13
        //  1617: astore          14
        //  1619: aload           13
        //  1621: astore          15
        //  1623: ldc_w           "File too big for full file cache. Size: "
        //  1626: aload           16
        //  1628: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //  1631: astore          16
        //  1633: aload           16
        //  1635: astore          14
        //  1637: aload           14
        //  1639: astore          16
        //  1641: aload           13
        //  1643: astore          15
        //  1645: aload           13
        //  1647: astore          17
        //  1649: new             Ljava/io/IOException;
        //  1652: dup            
        //  1653: ldc_w           "stream cache file size limit exceeded"
        //  1656: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1659: athrow         
        //  1660: astore          13
        //  1662: aload           12
        //  1664: astore          17
        //  1666: aload           13
        //  1668: astore          12
        //  1670: aload           16
        //  1672: astore          13
        //  1674: aload           15
        //  1676: astore          14
        //  1678: aload           17
        //  1680: astore          15
        //  1682: goto            622
        //  1685: aload           13
        //  1687: astore          14
        //  1689: aload           13
        //  1691: astore          15
        //  1693: new             Ljava/lang/String;
        //  1696: dup            
        //  1697: ldc_w           "File too big for full file cache. Size: "
        //  1700: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  1703: astore          16
        //  1705: aload           16
        //  1707: astore          14
        //  1709: goto            1637
        //  1712: astore          16
        //  1714: aconst_null    
        //  1715: astore          13
        //  1717: aload           12
        //  1719: astore          15
        //  1721: aload           16
        //  1723: astore          12
        //  1725: goto            622
        //  1728: aload           16
        //  1730: invokevirtual   java/nio/ByteBuffer.flip:()Ljava/nio/Buffer;
        //  1733: pop            
        //  1734: aload           15
        //  1736: aload           16
        //  1738: invokevirtual   java/nio/channels/FileChannel.write:(Ljava/nio/ByteBuffer;)I
        //  1741: ifgt            1734
        //  1744: aload           16
        //  1746: invokevirtual   java/nio/ByteBuffer.clear:()Ljava/nio/Buffer;
        //  1749: pop            
        //  1750: aload           17
        //  1752: invokeinterface com/google/android/gms/common/util/Clock.currentTimeMillis:()J
        //  1757: lload           8
        //  1759: lsub           
        //  1760: ldc2_w          1000
        //  1763: lload           10
        //  1765: lmul           
        //  1766: lcmp           
        //  1767: ifle            1886
        //  1770: ldc_w           "downloadTimeout"
        //  1773: astore          13
        //  1775: aload           13
        //  1777: astore          14
        //  1779: aload           13
        //  1781: astore          15
        //  1783: lload           10
        //  1785: invokestatic    java/lang/Long.toString:(J)Ljava/lang/String;
        //  1788: astore          16
        //  1790: aload           13
        //  1792: astore          14
        //  1794: aload           13
        //  1796: astore          15
        //  1798: new             Ljava/lang/StringBuilder;
        //  1801: dup            
        //  1802: aload           16
        //  1804: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  1807: invokevirtual   java/lang/String.length:()I
        //  1810: bipush          29
        //  1812: iadd           
        //  1813: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  1816: ldc_w           "Timeout exceeded. Limit: "
        //  1819: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1822: aload           16
        //  1824: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1827: ldc_w           " sec"
        //  1830: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  1833: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  1836: astore          16
        //  1838: aload           16
        //  1840: astore          14
        //  1842: aload           14
        //  1844: astore          16
        //  1846: aload           13
        //  1848: astore          15
        //  1850: aload           13
        //  1852: astore          17
        //  1854: new             Ljava/io/IOException;
        //  1857: dup            
        //  1858: ldc_w           "stream cache time limit exceeded"
        //  1861: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1864: athrow         
        //  1865: astore          13
        //  1867: aload           12
        //  1869: astore          15
        //  1871: aload           13
        //  1873: astore          12
        //  1875: aload           14
        //  1877: astore          13
        //  1879: aload           17
        //  1881: astore          14
        //  1883: goto            622
        //  1886: aload_0        
        //  1887: getfield        com/google/android/gms/internal/ads/zzaqn.zzdbj:Z
        //  1890: ifeq            1937
        //  1893: ldc_w           "externalAbort"
        //  1896: astore          15
        //  1898: aload           15
        //  1900: astore          14
        //  1902: new             Ljava/io/IOException;
        //  1905: dup            
        //  1906: ldc_w           "abort requested"
        //  1909: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //  1912: athrow         
        //  1913: astore          14
        //  1915: aconst_null    
        //  1916: astore          13
        //  1918: aload           12
        //  1920: astore          16
        //  1922: aload           14
        //  1924: astore          12
        //  1926: aload           15
        //  1928: astore          14
        //  1930: aload           16
        //  1932: astore          15
        //  1934: goto            622
        //  1937: iload_3        
        //  1938: istore_2       
        //  1939: aload           20
        //  1941: invokevirtual   com/google/android/gms/internal/ads/zzamj.tryAcquire:()Z
        //  1944: ifeq            1553
        //  1947: aload           19
        //  1949: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  1952: astore          21
        //  1954: getstatic       com/google/android/gms/internal/ads/zzamu.zzsy:Landroid/os/Handler;
        //  1957: new             Lcom/google/android/gms/internal/ads/zzaqi;
        //  1960: dup            
        //  1961: aload_0        
        //  1962: aload_1        
        //  1963: aload           21
        //  1965: iload_3        
        //  1966: iload           4
        //  1968: iconst_0       
        //  1969: invokespecial   com/google/android/gms/internal/ads/zzaqi.<init>:(Lcom/google/android/gms/internal/ads/zzaqh;Ljava/lang/String;Ljava/lang/String;IIZ)V
        //  1972: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
        //  1975: pop            
        //  1976: iload_3        
        //  1977: istore_2       
        //  1978: goto            1553
        //  1981: aload           12
        //  1983: invokevirtual   java/io/FileOutputStream.close:()V
        //  1986: iconst_3       
        //  1987: invokestatic    com/google/android/gms/internal/ads/zzakb.isLoggable:(I)Z
        //  1990: ifeq            2056
        //  1993: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbh:Ljava/text/DecimalFormat;
        //  1996: iload_2        
        //  1997: i2l            
        //  1998: invokevirtual   java/text/DecimalFormat.format:(J)Ljava/lang/String;
        //  2001: astore          13
        //  2003: new             Ljava/lang/StringBuilder;
        //  2006: dup            
        //  2007: aload           13
        //  2009: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  2012: invokevirtual   java/lang/String.length:()I
        //  2015: bipush          22
        //  2017: iadd           
        //  2018: aload_1        
        //  2019: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  2022: invokevirtual   java/lang/String.length:()I
        //  2025: iadd           
        //  2026: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  2029: ldc_w           "Preloaded "
        //  2032: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2035: aload           13
        //  2037: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2040: ldc_w           " bytes from "
        //  2043: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2046: aload_1        
        //  2047: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2050: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2053: invokestatic    com/google/android/gms/internal/ads/zzakb.zzck:(Ljava/lang/String;)V
        //  2056: aload           19
        //  2058: iconst_1       
        //  2059: iconst_0       
        //  2060: invokevirtual   java/io/File.setReadable:(ZZ)Z
        //  2063: pop            
        //  2064: aload           14
        //  2066: invokevirtual   java/io/File.isFile:()Z
        //  2069: ifeq            2105
        //  2072: aload           14
        //  2074: invokestatic    java/lang/System.currentTimeMillis:()J
        //  2077: invokevirtual   java/io/File.setLastModified:(J)Z
        //  2080: pop            
        //  2081: aload_0        
        //  2082: aload_1        
        //  2083: aload           19
        //  2085: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //  2088: iload_2        
        //  2089: invokevirtual   com/google/android/gms/internal/ads/zzaqh.zza:(Ljava/lang/String;Ljava/lang/String;I)V
        //  2092: getstatic       com/google/android/gms/internal/ads/zzaqn.zzdbg:Ljava/util/Set;
        //  2095: aload           18
        //  2097: invokeinterface java/util/Set.remove:(Ljava/lang/Object;)Z
        //  2102: pop            
        //  2103: iconst_1       
        //  2104: ireturn        
        //  2105: aload           14
        //  2107: invokevirtual   java/io/File.createNewFile:()Z
        //  2110: pop            
        //  2111: goto            2081
        //  2114: astore          13
        //  2116: goto            2081
        //  2119: new             Ljava/lang/StringBuilder;
        //  2122: dup            
        //  2123: aload_1        
        //  2124: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //  2127: invokevirtual   java/lang/String.length:()I
        //  2130: bipush          25
        //  2132: iadd           
        //  2133: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //  2136: ldc_w           "Preload failed for URL \""
        //  2139: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2142: aload_1        
        //  2143: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2146: ldc_w           "\""
        //  2149: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //  2152: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //  2155: aload           12
        //  2157: invokestatic    com/google/android/gms/internal/ads/zzakb.zzc:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //  2160: goto            691
        //  2163: new             Ljava/lang/String;
        //  2166: dup            
        //  2167: ldc_w           "Could not delete partial cache file at "
        //  2170: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //  2173: astore          12
        //  2175: goto            735
        //  2178: astore          15
        //  2180: goto            645
        //  2183: astore          15
        //  2185: goto            645
        //  2188: astore          12
        //  2190: aconst_null    
        //  2191: astore          13
        //  2193: goto            622
        //  2196: astore          12
        //  2198: goto            622
        //  2201: astore          16
        //  2203: aconst_null    
        //  2204: astore          13
        //  2206: ldc             "error"
        //  2208: astore          14
        //  2210: aload           12
        //  2212: astore          15
        //  2214: aload           16
        //  2216: astore          12
        //  2218: goto            622
        //  2221: goto            211
        //  2224: astore          12
        //  2226: aconst_null    
        //  2227: astore          13
        //  2229: ldc             "error"
        //  2231: astore          14
        //  2233: goto            622
        //  2236: astore          16
        //  2238: aconst_null    
        //  2239: astore          13
        //  2241: ldc             "error"
        //  2243: astore          14
        //  2245: aload           12
        //  2247: astore          15
        //  2249: aload           16
        //  2251: astore          12
        //  2253: goto            622
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  420    456    479    485    Any
        //  456    477    479    485    Any
        //  480    483    479    485    Any
        //  499    510    479    485    Any
        //  513    527    479    485    Any
        //  530    564    613    622    Ljava/io/IOException;
        //  530    564    2224   2236   Ljava/lang/RuntimeException;
        //  576    613    613    622    Ljava/io/IOException;
        //  576    613    2224   2236   Ljava/lang/RuntimeException;
        //  640    645    2178   2183   Ljava/io/IOException;
        //  640    645    2183   2188   Ljava/lang/NullPointerException;
        //  767    832    613    622    Ljava/io/IOException;
        //  767    832    2224   2236   Ljava/lang/RuntimeException;
        //  837    848    613    622    Ljava/io/IOException;
        //  837    848    2224   2236   Ljava/lang/RuntimeException;
        //  848    868    613    622    Ljava/io/IOException;
        //  848    868    2224   2236   Ljava/lang/RuntimeException;
        //  873    884    613    622    Ljava/io/IOException;
        //  873    884    2224   2236   Ljava/lang/RuntimeException;
        //  884    931    613    622    Ljava/io/IOException;
        //  884    931    2224   2236   Ljava/lang/RuntimeException;
        //  931    941    613    622    Ljava/io/IOException;
        //  931    941    2224   2236   Ljava/lang/RuntimeException;
        //  941    953    613    622    Ljava/io/IOException;
        //  941    953    2224   2236   Ljava/lang/RuntimeException;
        //  956    981    613    622    Ljava/io/IOException;
        //  956    981    2224   2236   Ljava/lang/RuntimeException;
        //  981    991    613    622    Ljava/io/IOException;
        //  981    991    2224   2236   Ljava/lang/RuntimeException;
        //  998    1010   613    622    Ljava/io/IOException;
        //  998    1010   2224   2236   Ljava/lang/RuntimeException;
        //  1013   1024   613    622    Ljava/io/IOException;
        //  1013   1024   2224   2236   Ljava/lang/RuntimeException;
        //  1024   1041   613    622    Ljava/io/IOException;
        //  1024   1041   2224   2236   Ljava/lang/RuntimeException;
        //  1053   1080   1148   1156   Ljava/io/IOException;
        //  1053   1080   2188   2196   Ljava/lang/RuntimeException;
        //  1080   1128   1128   1133   Ljava/io/IOException;
        //  1080   1128   2196   2201   Ljava/lang/RuntimeException;
        //  1133   1145   1148   1156   Ljava/io/IOException;
        //  1133   1145   2188   2196   Ljava/lang/RuntimeException;
        //  1156   1163   613    622    Ljava/io/IOException;
        //  1156   1163   2224   2236   Ljava/lang/RuntimeException;
        //  1168   1192   613    622    Ljava/io/IOException;
        //  1168   1192   2224   2236   Ljava/lang/RuntimeException;
        //  1192   1222   613    622    Ljava/io/IOException;
        //  1192   1222   2224   2236   Ljava/lang/RuntimeException;
        //  1224   1236   613    622    Ljava/io/IOException;
        //  1224   1236   2224   2236   Ljava/lang/RuntimeException;
        //  1239   1271   613    622    Ljava/io/IOException;
        //  1239   1271   2224   2236   Ljava/lang/RuntimeException;
        //  1278   1356   613    622    Ljava/io/IOException;
        //  1278   1356   2224   2236   Ljava/lang/RuntimeException;
        //  1356   1382   613    622    Ljava/io/IOException;
        //  1356   1382   2224   2236   Ljava/lang/RuntimeException;
        //  1384   1396   613    622    Ljava/io/IOException;
        //  1384   1396   2224   2236   Ljava/lang/RuntimeException;
        //  1399   1473   613    622    Ljava/io/IOException;
        //  1399   1473   2224   2236   Ljava/lang/RuntimeException;
        //  1473   1493   2236   2256   Ljava/io/IOException;
        //  1473   1493   2201   2221   Ljava/lang/RuntimeException;
        //  1495   1553   2236   2256   Ljava/io/IOException;
        //  1495   1553   2201   2221   Ljava/lang/RuntimeException;
        //  1553   1563   2236   2256   Ljava/io/IOException;
        //  1553   1563   2201   2221   Ljava/lang/RuntimeException;
        //  1590   1599   1712   1728   Ljava/io/IOException;
        //  1590   1599   1913   1937   Ljava/lang/RuntimeException;
        //  1607   1615   1712   1728   Ljava/io/IOException;
        //  1607   1615   1913   1937   Ljava/lang/RuntimeException;
        //  1623   1633   1712   1728   Ljava/io/IOException;
        //  1623   1633   1913   1937   Ljava/lang/RuntimeException;
        //  1649   1660   1660   1685   Ljava/io/IOException;
        //  1649   1660   1865   1886   Ljava/lang/RuntimeException;
        //  1693   1705   1712   1728   Ljava/io/IOException;
        //  1693   1705   1913   1937   Ljava/lang/RuntimeException;
        //  1728   1734   2236   2256   Ljava/io/IOException;
        //  1728   1734   2201   2221   Ljava/lang/RuntimeException;
        //  1734   1770   2236   2256   Ljava/io/IOException;
        //  1734   1770   2201   2221   Ljava/lang/RuntimeException;
        //  1783   1790   1712   1728   Ljava/io/IOException;
        //  1783   1790   1913   1937   Ljava/lang/RuntimeException;
        //  1798   1838   1712   1728   Ljava/io/IOException;
        //  1798   1838   1913   1937   Ljava/lang/RuntimeException;
        //  1854   1865   1660   1685   Ljava/io/IOException;
        //  1854   1865   1865   1886   Ljava/lang/RuntimeException;
        //  1886   1893   2236   2256   Ljava/io/IOException;
        //  1886   1893   2201   2221   Ljava/lang/RuntimeException;
        //  1902   1913   1712   1728   Ljava/io/IOException;
        //  1902   1913   1913   1937   Ljava/lang/RuntimeException;
        //  1939   1976   2236   2256   Ljava/io/IOException;
        //  1939   1976   2201   2221   Ljava/lang/RuntimeException;
        //  1981   2056   2236   2256   Ljava/io/IOException;
        //  1981   2056   2201   2221   Ljava/lang/RuntimeException;
        //  2056   2081   2236   2256   Ljava/io/IOException;
        //  2056   2081   2201   2221   Ljava/lang/RuntimeException;
        //  2081   2103   2236   2256   Ljava/io/IOException;
        //  2081   2103   2201   2221   Ljava/lang/RuntimeException;
        //  2105   2111   2114   2119   Ljava/io/IOException;
        //  2105   2111   2201   2221   Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 1025, Size: 1025
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
}
