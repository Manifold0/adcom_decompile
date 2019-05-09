// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.util;

import java.util.zip.GZIPOutputStream;
import android.annotation.TargetApi;
import android.os.StatFs;
import java.io.FileNotFoundException;
import java.util.zip.GZIPInputStream;
import java.io.FileInputStream;
import android.os.Build$VERSION;
import java.io.BufferedInputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.Bitmap;
import android.content.SharedPreferences;
import android.text.TextUtils;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Closeable;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.File;
import android.os.Environment;
import android.content.res.Resources;
import com.kongregate.android.internal.sdk.R$id;
import android.content.res.Resources$NotFoundException;
import android.content.pm.PackageInfo;
import android.content.Context;
import java.util.concurrent.atomic.AtomicReference;

public class g
{
    public static final String a = "icons";
    public static final int b = 8192;
    private static final AtomicReference<Context> c;
    private static PackageInfo d;
    
    static {
        c = new AtomicReference<Context>(null);
    }
    
    public static float a(final int n) {
        try {
            return g.c.get().getResources().getDimension(n);
        }
        catch (Resources$NotFoundException ex) {
            j.c("Dimension id " + n + " not found", (Throwable)ex);
            return 0.0f;
        }
    }
    
    public static int a(final String s, final String s2, final Context context) {
        final Resources resources = context.getResources();
        try {
            return resources.getIdentifier(s, s2, resources.getResourcePackageName(R$id.resources_package_name_lookup));
        }
        catch (Resources$NotFoundException ex) {
            j.c("token resource not found (R.id.resources_package_name_lookup) unable to lookup: " + s, (Throwable)ex);
            return 0;
        }
    }
    
    public static Resources a() {
        return g.c.get().getResources();
    }
    
    private static p a(final a a, final p p2) {
        if (a != null) {
            a.a(p2);
        }
        return p2;
    }
    
    public static p a(final String s, final String s2, final int n, final long n2) {
        int n3 = 0;
        p p4 = p.b;
    Label_0041_Outer:
        while (n3 <= n && !p4.a()) {
            p4 = c(s, s2);
            while (true) {
                if (!p4.a()) {
                    try {
                        Thread.sleep(n2);
                        ++n3;
                        continue Label_0041_Outer;
                    }
                    catch (InterruptedException ex) {
                        continue;
                    }
                    break;
                }
                continue;
            }
        }
        return p4;
    }
    
    public static p a(final String p0, final String p1, final a p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          6
        //     3: aload_1        
        //     4: ldc             ".gz"
        //     6: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //     9: aload_1        
        //    10: invokevirtual   java/lang/String.length:()I
        //    13: iconst_3       
        //    14: isub           
        //    15: if_icmpne       179
        //    18: iconst_1       
        //    19: istore          5
        //    21: iload           5
        //    23: ifeq            185
        //    26: aload_0        
        //    27: ldc             ".gz"
        //    29: invokevirtual   java/lang/String.lastIndexOf:(Ljava/lang/String;)I
        //    32: aload_0        
        //    33: invokevirtual   java/lang/String.length:()I
        //    36: iconst_3       
        //    37: isub           
        //    38: if_icmpeq       185
        //    41: iconst_1       
        //    42: istore          5
        //    44: ldc             "MD5"
        //    46: invokestatic    java/security/MessageDigest.getInstance:(Ljava/lang/String;)Ljava/security/MessageDigest;
        //    49: astore          8
        //    51: aload           8
        //    53: invokevirtual   java/security/MessageDigest.reset:()V
        //    56: new             Ljava/net/URL;
        //    59: dup            
        //    60: aload_0        
        //    61: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    64: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    67: checkcast       Ljava/net/HttpURLConnection;
        //    70: astore          11
        //    72: aload           11
        //    74: ldc             "GET"
        //    76: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    79: aload           11
        //    81: ldc             "Accept-Encoding"
        //    83: ldc             "gzip, deflate"
        //    85: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    88: aload           11
        //    90: ldc             "Connection"
        //    92: ldc             "Keep-Alive"
        //    94: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    97: aload           11
        //    99: sipush          15000
        //   102: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //   105: aload           11
        //   107: sipush          15000
        //   110: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //   113: aload           11
        //   115: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   118: aload           11
        //   120: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   123: sipush          200
        //   126: if_icmpeq       204
        //   129: new             Ljava/lang/StringBuilder;
        //   132: dup            
        //   133: invokespecial   java/lang/StringBuilder.<init>:()V
        //   136: ldc             "Connection to "
        //   138: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   141: aload_0        
        //   142: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   145: ldc             " yielded response code "
        //   147: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   150: aload           11
        //   152: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   155: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   158: ldc             ", aborting"
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   166: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   169: aload_2        
        //   170: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   173: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   176: astore_1       
        //   177: aload_1        
        //   178: areturn        
        //   179: iconst_0       
        //   180: istore          5
        //   182: goto            21
        //   185: iconst_0       
        //   186: istore          5
        //   188: goto            44
        //   191: astore          8
        //   193: aconst_null    
        //   194: astore          8
        //   196: ldc             "MD5 algorithm not found, checksum verification disabled"
        //   198: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   201: goto            56
        //   204: aload           11
        //   206: ldc             "ETag"
        //   208: invokevirtual   java/net/HttpURLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   211: astore          9
        //   213: aload           9
        //   215: ifnull          758
        //   218: aload           9
        //   220: invokevirtual   java/lang/String.length:()I
        //   223: bipush          34
        //   225: if_icmpeq       462
        //   228: goto            758
        //   231: aload           11
        //   233: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   236: astore          8
        //   238: aload           11
        //   240: invokevirtual   java/net/HttpURLConnection.getContentEncoding:()Ljava/lang/String;
        //   243: astore          12
        //   245: ldc             "gzip"
        //   247: aload           12
        //   249: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   252: ifeq            482
        //   255: new             Ljava/util/zip/GZIPInputStream;
        //   258: dup            
        //   259: aload           8
        //   261: invokespecial   java/util/zip/GZIPInputStream.<init>:(Ljava/io/InputStream;)V
        //   264: astore          8
        //   266: aload           8
        //   268: invokestatic    com/kongregate/android/internal/util/g.c:(Ljava/io/InputStream;)Ljava/io/InputStream;
        //   271: astore          8
        //   273: aload_1        
        //   274: invokestatic    com/kongregate/android/internal/util/g.i:(Ljava/lang/String;)Z
        //   277: pop            
        //   278: new             Ljava/io/FileOutputStream;
        //   281: dup            
        //   282: new             Ljava/io/File;
        //   285: dup            
        //   286: aload_1        
        //   287: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //   290: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   293: astore          12
        //   295: new             Ljava/io/BufferedOutputStream;
        //   298: dup            
        //   299: aload           12
        //   301: sipush          8192
        //   304: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //   307: astore_1       
        //   308: iload           5
        //   310: ifeq            752
        //   313: new             Lcom/kongregate/android/internal/util/g$b;
        //   316: dup            
        //   317: aload_1        
        //   318: invokespecial   com/kongregate/android/internal/util/g$b.<init>:(Ljava/io/OutputStream;)V
        //   321: astore_1       
        //   322: aload           11
        //   324: invokevirtual   java/net/HttpURLConnection.getContentLength:()I
        //   327: istore          7
        //   329: sipush          8192
        //   332: newarray        B
        //   334: astore          11
        //   336: iload           6
        //   338: istore          5
        //   340: aload           8
        //   342: aload           11
        //   344: invokevirtual   java/io/InputStream.read:([B)I
        //   347: istore          6
        //   349: iload           6
        //   351: iconst_m1      
        //   352: if_icmpeq       565
        //   355: aload           10
        //   357: ifnull          370
        //   360: aload           10
        //   362: aload           11
        //   364: iconst_0       
        //   365: iload           6
        //   367: invokevirtual   java/security/MessageDigest.update:([BII)V
        //   370: aload_1        
        //   371: aload           11
        //   373: iconst_0       
        //   374: iload           6
        //   376: invokevirtual   java/io/OutputStream.write:([BII)V
        //   379: iload           5
        //   381: iload           6
        //   383: iadd           
        //   384: istore          6
        //   386: iload           6
        //   388: istore          5
        //   390: aload_2        
        //   391: ifnull          340
        //   394: iload           6
        //   396: istore          5
        //   398: iload           7
        //   400: ifle            340
        //   403: iload           6
        //   405: i2d            
        //   406: iload           7
        //   408: i2d            
        //   409: ddiv           
        //   410: dstore_3       
        //   411: aload_2        
        //   412: iload           6
        //   414: iload           7
        //   416: dload_3        
        //   417: invokeinterface com/kongregate/android/internal/util/g$a.a:(IID)V
        //   422: iload           6
        //   424: istore          5
        //   426: goto            340
        //   429: astore_1       
        //   430: new             Ljava/lang/StringBuilder;
        //   433: dup            
        //   434: invokespecial   java/lang/StringBuilder.<init>:()V
        //   437: ldc_w           "FileUtils - Incorrect url: "
        //   440: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   443: aload_0        
        //   444: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   447: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   450: aload_1        
        //   451: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   454: aload_2        
        //   455: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   458: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   461: areturn        
        //   462: aload           9
        //   464: ldc_w           "\""
        //   467: ldc_w           ""
        //   470: invokevirtual   java/lang/String.replace:(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Ljava/lang/String;
        //   473: astore          9
        //   475: aload           8
        //   477: astore          10
        //   479: goto            231
        //   482: ldc_w           "deflate"
        //   485: aload           12
        //   487: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   490: ifeq            755
        //   493: new             Ljava/util/zip/InflaterInputStream;
        //   496: dup            
        //   497: aload           8
        //   499: invokespecial   java/util/zip/InflaterInputStream.<init>:(Ljava/io/InputStream;)V
        //   502: astore          8
        //   504: goto            266
        //   507: astore_1       
        //   508: new             Ljava/lang/StringBuilder;
        //   511: dup            
        //   512: invokespecial   java/lang/StringBuilder.<init>:()V
        //   515: ldc_w           "FileUtils - IOException downloading: "
        //   518: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   521: aload_0        
        //   522: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   525: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   528: aload_1        
        //   529: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   532: aload_2        
        //   533: getstatic       com/kongregate/android/internal/util/p.d:Lcom/kongregate/android/internal/util/p;
        //   536: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   539: areturn        
        //   540: astore_1       
        //   541: ldc_w           "IOException while opening output file"
        //   544: aload_1        
        //   545: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   548: aload_2        
        //   549: getstatic       com/kongregate/android/internal/util/p.f:Lcom/kongregate/android/internal/util/p;
        //   552: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   555: areturn        
        //   556: astore_1       
        //   557: aload_2        
        //   558: getstatic       com/kongregate/android/internal/util/p.f:Lcom/kongregate/android/internal/util/p;
        //   561: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   564: areturn        
        //   565: aload           12
        //   567: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/FileOutputStream;)V
        //   570: aload           8
        //   572: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   575: aload_1        
        //   576: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   579: aload           10
        //   581: ifnull          704
        //   584: aload           10
        //   586: invokevirtual   java/security/MessageDigest.digest:()[B
        //   589: astore_1       
        //   590: new             Ljava/math/BigInteger;
        //   593: dup            
        //   594: iconst_1       
        //   595: aload_1        
        //   596: invokespecial   java/math/BigInteger.<init>:(I[B)V
        //   599: astore          8
        //   601: new             Ljava/lang/StringBuilder;
        //   604: dup            
        //   605: invokespecial   java/lang/StringBuilder.<init>:()V
        //   608: ldc_w           "%0"
        //   611: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   614: aload_1        
        //   615: arraylength    
        //   616: iconst_1       
        //   617: ishl           
        //   618: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   621: ldc_w           "x"
        //   624: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   627: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   630: iconst_1       
        //   631: anewarray       Ljava/lang/Object;
        //   634: dup            
        //   635: iconst_0       
        //   636: aload           8
        //   638: aastore        
        //   639: invokestatic    java/lang/String.format:(Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   642: astore_1       
        //   643: aload_1        
        //   644: aload           9
        //   646: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   649: ifne            704
        //   652: new             Ljava/lang/StringBuilder;
        //   655: dup            
        //   656: invokespecial   java/lang/StringBuilder.<init>:()V
        //   659: ldc_w           "Download failed due to checksum mismatch! MD5("
        //   662: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   665: aload_0        
        //   666: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   669: ldc_w           "): "
        //   672: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   675: aload_1        
        //   676: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   679: ldc_w           ", expected: "
        //   682: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   685: aload           9
        //   687: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   690: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   693: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;)V
        //   696: aload_2        
        //   697: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   700: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   703: areturn        
        //   704: aload_2        
        //   705: getstatic       com/kongregate/android/internal/util/p.a:Lcom/kongregate/android/internal/util/p;
        //   708: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   711: astore_1       
        //   712: aload_1        
        //   713: areturn        
        //   714: astore_1       
        //   715: new             Ljava/lang/StringBuilder;
        //   718: dup            
        //   719: invokespecial   java/lang/StringBuilder.<init>:()V
        //   722: ldc_w           "NumberFormatException while downloading: "
        //   725: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   728: aload_0        
        //   729: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   732: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   735: aload_1        
        //   736: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   739: aload_2        
        //   740: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   743: invokestatic    com/kongregate/android/internal/util/g.a:(Lcom/kongregate/android/internal/util/g$a;Lcom/kongregate/android/internal/util/p;)Lcom/kongregate/android/internal/util/p;
        //   746: areturn        
        //   747: astore          9
        //   749: goto            196
        //   752: goto            322
        //   755: goto            266
        //   758: aconst_null    
        //   759: astore          9
        //   761: aconst_null    
        //   762: astore          10
        //   764: goto            231
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  44     51     191    196    Ljava/security/NoSuchAlgorithmException;
        //  51     56     747    752    Ljava/security/NoSuchAlgorithmException;
        //  56     177    429    462    Ljava/net/MalformedURLException;
        //  56     177    507    540    Ljava/io/IOException;
        //  56     177    714    747    Ljava/lang/NumberFormatException;
        //  204    213    429    462    Ljava/net/MalformedURLException;
        //  204    213    507    540    Ljava/io/IOException;
        //  204    213    714    747    Ljava/lang/NumberFormatException;
        //  218    228    429    462    Ljava/net/MalformedURLException;
        //  218    228    507    540    Ljava/io/IOException;
        //  218    228    714    747    Ljava/lang/NumberFormatException;
        //  231    266    429    462    Ljava/net/MalformedURLException;
        //  231    266    507    540    Ljava/io/IOException;
        //  231    266    714    747    Ljava/lang/NumberFormatException;
        //  266    273    429    462    Ljava/net/MalformedURLException;
        //  266    273    507    540    Ljava/io/IOException;
        //  266    273    714    747    Ljava/lang/NumberFormatException;
        //  273    308    540    556    Ljava/io/IOException;
        //  273    308    429    462    Ljava/net/MalformedURLException;
        //  273    308    714    747    Ljava/lang/NumberFormatException;
        //  313    322    540    556    Ljava/io/IOException;
        //  313    322    429    462    Ljava/net/MalformedURLException;
        //  313    322    714    747    Ljava/lang/NumberFormatException;
        //  322    336    429    462    Ljava/net/MalformedURLException;
        //  322    336    507    540    Ljava/io/IOException;
        //  322    336    714    747    Ljava/lang/NumberFormatException;
        //  340    349    429    462    Ljava/net/MalformedURLException;
        //  340    349    507    540    Ljava/io/IOException;
        //  340    349    714    747    Ljava/lang/NumberFormatException;
        //  360    370    429    462    Ljava/net/MalformedURLException;
        //  360    370    507    540    Ljava/io/IOException;
        //  360    370    714    747    Ljava/lang/NumberFormatException;
        //  370    379    556    565    Ljava/io/IOException;
        //  370    379    429    462    Ljava/net/MalformedURLException;
        //  370    379    714    747    Ljava/lang/NumberFormatException;
        //  411    422    429    462    Ljava/net/MalformedURLException;
        //  411    422    507    540    Ljava/io/IOException;
        //  411    422    714    747    Ljava/lang/NumberFormatException;
        //  462    475    429    462    Ljava/net/MalformedURLException;
        //  462    475    507    540    Ljava/io/IOException;
        //  462    475    714    747    Ljava/lang/NumberFormatException;
        //  482    504    429    462    Ljava/net/MalformedURLException;
        //  482    504    507    540    Ljava/io/IOException;
        //  482    504    714    747    Ljava/lang/NumberFormatException;
        //  541    556    429    462    Ljava/net/MalformedURLException;
        //  541    556    507    540    Ljava/io/IOException;
        //  541    556    714    747    Ljava/lang/NumberFormatException;
        //  557    565    429    462    Ljava/net/MalformedURLException;
        //  557    565    507    540    Ljava/io/IOException;
        //  557    565    714    747    Ljava/lang/NumberFormatException;
        //  565    579    429    462    Ljava/net/MalformedURLException;
        //  565    579    507    540    Ljava/io/IOException;
        //  565    579    714    747    Ljava/lang/NumberFormatException;
        //  584    704    429    462    Ljava/net/MalformedURLException;
        //  584    704    507    540    Ljava/io/IOException;
        //  584    704    714    747    Ljava/lang/NumberFormatException;
        //  704    712    429    462    Ljava/net/MalformedURLException;
        //  704    712    507    540    Ljava/io/IOException;
        //  704    712    714    747    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 353, Size: 353
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3569)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3435)
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
    
    public static String a(final int n, final String s) {
        return c("userdata/" + n + "/" + s);
    }
    
    public static String a(final long n, final long n2, final String s) {
        return a(o(), n, n2, s);
    }
    
    public static String a(final String s) {
        t();
        if (c()) {
            File externalFilesDir;
            if ((externalFilesDir = g.c.get().getExternalFilesDir((String)null)) == null) {
                externalFilesDir = new File(Environment.getExternalStorageDirectory().toString() + "/Android/data/" + g.c.get().getPackageName() + "/files");
                i(externalFilesDir.getAbsolutePath() + "/");
            }
            return externalFilesDir.getAbsolutePath() + "/arcade/" + s;
        }
        return null;
    }
    
    public static String a(final String s, final long n, final long n2, final String s2) {
        final int index = s2.indexOf("?");
        String substring = s2;
        if (index > 0) {
            substring = s2.substring(0, index);
        }
        final StringBuilder sb = new StringBuilder(256);
        final String f = f(substring);
        sb.append(s).append("/").append(n).append("_").append(n2);
        sb.append(".").append(f);
        return sb.toString();
    }
    
    public static void a(final Context context) {
        Label_0082: {
            if (context == null) {
                break Label_0082;
            }
            g.c.set(context.getApplicationContext());
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return;
            }
            try {
                g.d = packageManager.getPackageInfo(context.getPackageName(), 0);
                if (g.d != null && g.d.applicationInfo != null) {
                    j.b("SourceDir: " + g.d.applicationInfo.sourceDir);
                }
                return;
                throw new IllegalArgumentException("Context is required");
            }
            catch (PackageManager$NameNotFoundException ex) {
                j.d("Package " + context.getPackageName() + " not found!", (Throwable)ex);
            }
            catch (RuntimeException ex2) {
                j.d("RuntimeException getting package info", ex2);
            }
        }
    }
    
    public static void a(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
    
    public static void a(final FileOutputStream fileOutputStream) {
        if (fileOutputStream == null) {
            return;
        }
        try {
            fileOutputStream.getFD().sync();
        }
        catch (IOException ex) {}
    }
    
    public static void a(final InputStream inputStream) {
        if (inputStream == null) {
            return;
        }
        try {
            inputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public static void a(final OutputStream outputStream) {
        if (outputStream == null) {
            return;
        }
        try {
            if (outputStream instanceof FileOutputStream) {
                a((FileOutputStream)outputStream);
            }
            outputStream.close();
        }
        catch (IOException ex) {}
    }
    
    public static boolean a(final File file) {
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    final File file2 = listFiles[i];
                    if (file2.isDirectory()) {
                        a(file2);
                    }
                    else if (!file2.delete()) {
                        return false;
                    }
                }
            }
        }
        return file.delete();
    }
    
    public static boolean a(final InputStream p0, final String p1, final boolean p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aload_1        
        //     5: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //     8: astore          4
        //    10: aload           4
        //    12: invokevirtual   java/io/File.exists:()Z
        //    15: ifeq            24
        //    18: iload_2        
        //    19: ifne            24
        //    22: iconst_1       
        //    23: ireturn        
        //    24: aload_1        
        //    25: invokestatic    com/kongregate/android/internal/util/g.i:(Ljava/lang/String;)Z
        //    28: pop            
        //    29: aload           4
        //    31: invokevirtual   java/io/File.exists:()Z
        //    34: ifne            43
        //    37: aload           4
        //    39: invokevirtual   java/io/File.createNewFile:()Z
        //    42: pop            
        //    43: new             Ljava/io/FileOutputStream;
        //    46: dup            
        //    47: new             Ljava/io/File;
        //    50: dup            
        //    51: aload_1        
        //    52: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    55: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //    58: astore          6
        //    60: new             Ljava/io/BufferedOutputStream;
        //    63: dup            
        //    64: aload           6
        //    66: sipush          8192
        //    69: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    72: astore          5
        //    74: aload           5
        //    76: astore          4
        //    78: sipush          8192
        //    81: newarray        B
        //    83: astore          7
        //    85: aload           5
        //    87: astore          4
        //    89: aload_0        
        //    90: aload           7
        //    92: invokevirtual   java/io/InputStream.read:([B)I
        //    95: istore_3       
        //    96: iload_3        
        //    97: iconst_m1      
        //    98: if_icmpeq       162
        //   101: aload           5
        //   103: astore          4
        //   105: aload           5
        //   107: aload           7
        //   109: iconst_0       
        //   110: iload_3        
        //   111: invokevirtual   java/io/OutputStream.write:([BII)V
        //   114: goto            85
        //   117: astore          6
        //   119: aload           5
        //   121: astore          4
        //   123: ldc_w           "IOException while writing"
        //   126: aload           6
        //   128: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   131: aload_0        
        //   132: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   135: aload           5
        //   137: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   140: iconst_0       
        //   141: ireturn        
        //   142: astore          4
        //   144: ldc_w           "IOException while opening output file"
        //   147: aload           4
        //   149: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   152: aload_0        
        //   153: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   156: aconst_null    
        //   157: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   160: iconst_0       
        //   161: ireturn        
        //   162: aload           5
        //   164: astore          4
        //   166: aload           5
        //   168: invokevirtual   java/io/OutputStream.flush:()V
        //   171: aload           5
        //   173: astore          4
        //   175: aload           6
        //   177: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/FileOutputStream;)V
        //   180: aload_0        
        //   181: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   184: aload           5
        //   186: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   189: iconst_1       
        //   190: ireturn        
        //   191: astore          6
        //   193: aconst_null    
        //   194: astore          5
        //   196: aload           5
        //   198: astore          4
        //   200: new             Ljava/lang/StringBuilder;
        //   203: dup            
        //   204: invokespecial   java/lang/StringBuilder.<init>:()V
        //   207: ldc_w           "Error while copying file to "
        //   210: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   213: aload_1        
        //   214: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   217: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   220: aload           6
        //   222: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   225: aload_0        
        //   226: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   229: aload           5
        //   231: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   234: iconst_0       
        //   235: ireturn        
        //   236: astore_1       
        //   237: aconst_null    
        //   238: astore          4
        //   240: aload_0        
        //   241: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   244: aload           4
        //   246: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   249: aload_1        
        //   250: athrow         
        //   251: astore_1       
        //   252: goto            240
        //   255: astore          6
        //   257: goto            196
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  24     43     142    162    Ljava/io/IOException;
        //  24     43     236    240    Any
        //  43     74     142    162    Ljava/io/IOException;
        //  43     74     236    240    Any
        //  78     85     255    260    Ljava/io/IOException;
        //  78     85     251    255    Any
        //  89     96     255    260    Ljava/io/IOException;
        //  89     96     251    255    Any
        //  105    114    117    142    Ljava/io/IOException;
        //  105    114    251    255    Any
        //  123    131    255    260    Ljava/io/IOException;
        //  123    131    251    255    Any
        //  144    152    191    196    Ljava/io/IOException;
        //  144    152    236    240    Any
        //  166    171    255    260    Ljava/io/IOException;
        //  166    171    251    255    Any
        //  175    180    255    260    Ljava/io/IOException;
        //  175    180    251    255    Any
        //  200    225    251    255    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0085:
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
    
    public static boolean a(final String s, final String s2) {
        if (TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            return s.equals(s2);
        }
        final File file = new File(s);
        final File file2 = new File(s2);
        try {
            return file.getCanonicalPath().equals(file2.getCanonicalPath());
        }
        catch (IOException ex) {
            j.c("exception comparing files. they must not be the same");
            return false;
        }
    }
    
    public static SharedPreferences b(final String s) {
        return g.c.get().getSharedPreferences(s, 0);
    }
    
    public static Bitmap b(final int n) {
        final Drawable c = c(n);
        if (c instanceof BitmapDrawable) {
            return ((BitmapDrawable)c).getBitmap();
        }
        j.c("Resource id " + n + " is not a BitmapDrawable");
        return null;
    }
    
    public static String b(final InputStream inputStream) {
        StringWriter stringWriter;
        InputStreamReader inputStreamReader;
        try {
            final char[] array = new char[8192];
            stringWriter = new StringWriter(8192);
            inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
            while (true) {
                final int read = inputStreamReader.read(array);
                if (read == -1) {
                    break;
                }
                stringWriter.write(array, 0, read);
            }
        }
        catch (IOException ex) {
            j.d("IOException", ex);
            return null;
        }
        inputStreamReader.close();
        a(inputStream);
        return stringWriter.toString();
    }
    
    public static void b(final File file) {
        if (file.exists() && file.isDirectory()) {
            final File[] listFiles = file.listFiles();
            if (listFiles != null) {
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    listFiles[i].delete();
                }
            }
        }
    }
    
    public static boolean b() {
        return g.d != null && g.d.applicationInfo != null && (g.d.applicationInfo.flags & 0x40000) != 0x0;
    }
    
    public static boolean b(final Context context) {
        return d() && context.checkCallingOrSelfPermission("android.permission.WRITE_EXTERNAL_STORAGE") == 0;
    }
    
    public static boolean b(final String s, final String s2) {
        return new File(s).renameTo(new File(s2));
    }
    
    public static Drawable c(final int n) {
        try {
            return g.c.get().getResources().getDrawable(n);
        }
        catch (Resources$NotFoundException ex) {
            j.c("Resource id " + n + " not found", (Throwable)ex);
            return null;
        }
    }
    
    public static p c(final String s, final String s2) {
        return a(s, s2, (a)null);
    }
    
    public static InputStream c(final InputStream inputStream) {
        if (inputStream instanceof BufferedInputStream || inputStream == null) {
            return inputStream;
        }
        return new BufferedInputStream(inputStream, 8192);
    }
    
    public static String c(final String s) {
        t();
        return g.c.get().getFilesDir().getPath() + "/" + s;
    }
    
    public static boolean c() {
        try {
            return StringUtils.e(Build$VERSION.RELEASE, "2.2.1");
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    public static boolean c(final File file) {
        boolean b2;
        final boolean b = b2 = false;
        if (file.exists()) {
            b2 = b;
            if (file.isDirectory()) {
                final File[] listFiles = file.listFiles();
                b2 = b;
                if (listFiles != null) {
                    b2 = b;
                    if (listFiles.length == 0) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    public static InputStream d(final int n) {
        try {
            return g.c.get().getResources().openRawResource(n);
        }
        catch (Resources$NotFoundException ex) {
            return null;
        }
    }
    
    public static String d(final String s) {
        t();
        File cacheDir;
        if ((cacheDir = g.c.get().getCacheDir()) == null) {
            cacheDir = new File("/data/data/" + g.c.get().getPackageName() + "/cache");
            if (!cacheDir.exists()) {
                cacheDir.mkdirs();
            }
        }
        return cacheDir.toString() + "/" + s;
    }
    
    public static void d(final String p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: iload_3        
        //     3: istore_2       
        //     4: aload_1        
        //     5: ldc             ".gz"
        //     7: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    10: ifeq            26
        //    13: iload_3        
        //    14: istore_2       
        //    15: aload_0        
        //    16: ldc             ".gz"
        //    18: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    21: ifne            26
        //    24: iconst_1       
        //    25: istore_2       
        //    26: aload_1        
        //    27: invokestatic    com/kongregate/android/internal/util/g.g:(Ljava/lang/String;)Z
        //    30: pop            
        //    31: new             Ljava/io/File;
        //    34: dup            
        //    35: aload_1        
        //    36: invokespecial   java/io/File.<init>:(Ljava/lang/String;)V
        //    39: astore          9
        //    41: aload           9
        //    43: invokevirtual   java/io/File.exists:()Z
        //    46: ifne            55
        //    49: aload           9
        //    51: invokevirtual   java/io/File.createNewFile:()Z
        //    54: pop            
        //    55: aconst_null    
        //    56: astore          6
        //    58: aconst_null    
        //    59: astore_1       
        //    60: aload_0        
        //    61: invokestatic    com/kongregate/android/internal/util/g.l:(Ljava/lang/String;)Ljava/io/InputStream;
        //    64: invokestatic    com/kongregate/android/internal/util/g.c:(Ljava/io/InputStream;)Ljava/io/InputStream;
        //    67: astore          7
        //    69: aload           7
        //    71: ifnonnull       86
        //    74: return         
        //    75: astore_1       
        //    76: ldc_w           "IOException while copying file"
        //    79: aload_1        
        //    80: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    83: goto            55
        //    86: aload_1        
        //    87: astore          5
        //    89: aload           6
        //    91: astore          4
        //    93: sipush          8192
        //    96: newarray        B
        //    98: astore          8
        //   100: aload_1        
        //   101: astore          5
        //   103: aload           6
        //   105: astore          4
        //   107: new             Ljava/io/FileOutputStream;
        //   110: dup            
        //   111: aload           9
        //   113: iconst_0       
        //   114: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;Z)V
        //   117: astore_1       
        //   118: iload_2        
        //   119: ifeq            296
        //   122: new             Lcom/kongregate/android/internal/util/g$b;
        //   125: dup            
        //   126: aload_1        
        //   127: invokespecial   com/kongregate/android/internal/util/g$b.<init>:(Ljava/io/OutputStream;)V
        //   130: astore_0       
        //   131: aload_0        
        //   132: astore          5
        //   134: aload_0        
        //   135: astore          4
        //   137: aload           7
        //   139: aload           8
        //   141: invokevirtual   java/io/InputStream.read:([B)I
        //   144: istore_2       
        //   145: iload_2        
        //   146: iconst_m1      
        //   147: if_icmpeq       175
        //   150: aload_0        
        //   151: astore          4
        //   153: aload_0        
        //   154: aload           8
        //   156: iconst_0       
        //   157: iload_2        
        //   158: invokevirtual   java/io/OutputStream.write:([BII)V
        //   161: goto            131
        //   164: astore_1       
        //   165: aload           7
        //   167: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   170: aload_0        
        //   171: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   174: return         
        //   175: aload_0        
        //   176: astore          5
        //   178: aload_0        
        //   179: astore          4
        //   181: aload_0        
        //   182: invokevirtual   java/io/OutputStream.flush:()V
        //   185: aload_0        
        //   186: astore          5
        //   188: aload_0        
        //   189: astore          4
        //   191: aload_1        
        //   192: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/FileOutputStream;)V
        //   195: aload           7
        //   197: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   200: aload_0        
        //   201: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   204: return         
        //   205: astore_0       
        //   206: aconst_null    
        //   207: astore_1       
        //   208: ldc_w           "FileNotFound"
        //   211: aload_0        
        //   212: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   215: aload           7
        //   217: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   220: aload_1        
        //   221: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   224: return         
        //   225: astore_0       
        //   226: aload           5
        //   228: astore          4
        //   230: ldc_w           "IOException"
        //   233: aload_0        
        //   234: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   237: aload           7
        //   239: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   242: aload           5
        //   244: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   247: return         
        //   248: astore_0       
        //   249: aload           7
        //   251: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/InputStream;)V
        //   254: aload           4
        //   256: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/OutputStream;)V
        //   259: aload_0        
        //   260: athrow         
        //   261: astore_0       
        //   262: aload_1        
        //   263: astore          4
        //   265: goto            249
        //   268: astore_0       
        //   269: aload_1        
        //   270: astore          4
        //   272: goto            249
        //   275: astore_0       
        //   276: aload_1        
        //   277: astore          5
        //   279: goto            226
        //   282: astore_0       
        //   283: goto            208
        //   286: astore          4
        //   288: aload_0        
        //   289: astore_1       
        //   290: aload           4
        //   292: astore_0       
        //   293: goto            208
        //   296: aload_1        
        //   297: astore_0       
        //   298: goto            131
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  49     55     75     86     Ljava/io/IOException;
        //  93     100    205    208    Ljava/io/FileNotFoundException;
        //  93     100    225    226    Ljava/io/IOException;
        //  93     100    248    249    Any
        //  107    118    205    208    Ljava/io/FileNotFoundException;
        //  107    118    225    226    Ljava/io/IOException;
        //  107    118    248    249    Any
        //  122    131    282    286    Ljava/io/FileNotFoundException;
        //  122    131    275    282    Ljava/io/IOException;
        //  122    131    261    268    Any
        //  137    145    286    296    Ljava/io/FileNotFoundException;
        //  137    145    225    226    Ljava/io/IOException;
        //  137    145    248    249    Any
        //  153    161    164    175    Ljava/io/IOException;
        //  153    161    286    296    Ljava/io/FileNotFoundException;
        //  153    161    248    249    Any
        //  181    185    286    296    Ljava/io/FileNotFoundException;
        //  181    185    225    226    Ljava/io/IOException;
        //  181    185    248    249    Any
        //  191    195    286    296    Ljava/io/FileNotFoundException;
        //  191    195    225    226    Ljava/io/IOException;
        //  191    195    248    249    Any
        //  208    215    268    275    Any
        //  230    237    248    249    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 164, Size: 164
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
    
    public static boolean d() {
        return "mounted".equals(Environment.getExternalStorageState());
    }
    
    public static SharedPreferences e() {
        return g.c.get().getSharedPreferences("shared_preferences", 0);
    }
    
    public static String e(final int n) {
        final InputStream d = d(n);
        if (d != null) {
            return b(d);
        }
        return null;
    }
    
    public static boolean e(final String s) {
        return new File(s).exists();
    }
    
    public static long f() {
        if (d()) {
            return o(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return 0L;
    }
    
    public static String f(final String s) {
        if (s == null) {
            return "";
        }
        final String[] split = s.split("\\.");
        if (split.length >= 2) {
            return split[split.length - 1];
        }
        return "";
    }
    
    public static long g() {
        if (d()) {
            return p(Environment.getExternalStorageDirectory().getAbsolutePath());
        }
        return 0L;
    }
    
    public static boolean g(final String s) {
        return a(new File(s));
    }
    
    public static long h() {
        return o(c(""));
    }
    
    public static boolean h(final String s) {
        if (!StringUtils.a((CharSequence)s)) {
            final File file = new File(s);
            if (file.exists() && !file.isDirectory()) {
                return file.delete();
            }
        }
        return false;
    }
    
    public static long i() {
        return p(c(""));
    }
    
    public static boolean i(final String s) {
        final int lastIndex = s.lastIndexOf("/");
        String substring = s;
        if (lastIndex != s.length() - 1) {
            substring = s.substring(0, lastIndex + 1);
        }
        return new File(substring).exists() || new File(substring).mkdirs();
    }
    
    public static InputStream j(final String s) {
        final File file = new File(s);
        if (!file.exists()) {
            return null;
        }
        InputStream c = null;
        try {
            if (s.lastIndexOf(".gz") != s.length() - 3) {
                goto Label_0089;
            }
            final boolean b = true;
            c = c(new FileInputStream(file));
            if (b) {
                return new GZIPInputStream(c);
            }
        }
        catch (FileNotFoundException ex) {
            j.d("FileUtils - File not found: " + s, ex);
        }
        catch (IOException ex2) {
            j.d("FileUtils - IOException in openTextFile", ex2);
            goto Label_0087;
        }
        return c;
    }
    
    public static String j() {
        return d("icons");
    }
    
    public static String k() {
        return d("badges");
    }
    
    public static String k(final String s) {
        try {
            final InputStream l = l(s);
            if (l == null) {
                return null;
            }
            final String b = b(l);
            l.close();
            return b;
        }
        catch (IOException ex) {
            j.d("FileUtils - Couldn't close asset: " + s, ex);
            return null;
        }
    }
    
    public static InputStream l(final String s) {
        try {
            return c(g.c.get().getAssets().open(s));
        }
        catch (FileNotFoundException ex2) {
            j.a("Asset not found: " + s);
        }
        catch (IOException ex) {
            j.d("FileUtils - Couldn't open asset: " + s, ex);
            goto Label_0046;
        }
    }
    
    public static String l() {
        return d("avatar");
    }
    
    public static long m(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore          5
        //     5: getstatic       com/kongregate/android/internal/util/g.c:Ljava/util/concurrent/atomic/AtomicReference;
        //     8: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //    11: checkcast       Landroid/content/Context;
        //    14: invokevirtual   android/content/Context.getAssets:()Landroid/content/res/AssetManager;
        //    17: aload_0        
        //    18: invokevirtual   android/content/res/AssetManager.openFd:(Ljava/lang/String;)Landroid/content/res/AssetFileDescriptor;
        //    21: astore          4
        //    23: aload           4
        //    25: invokevirtual   android/content/res/AssetFileDescriptor.getLength:()J
        //    28: lstore_1       
        //    29: aload           4
        //    31: ifnull          39
        //    34: aload           4
        //    36: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //    39: lload_1        
        //    40: lreturn        
        //    41: astore_3       
        //    42: aload           5
        //    44: astore          4
        //    46: aload_3        
        //    47: astore          5
        //    49: aload           4
        //    51: astore_3       
        //    52: new             Ljava/lang/StringBuilder;
        //    55: dup            
        //    56: invokespecial   java/lang/StringBuilder.<init>:()V
        //    59: ldc_w           "Failed to get asset size: "
        //    62: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    65: aload_0        
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    72: aload           5
        //    74: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    77: aload           4
        //    79: ifnull          87
        //    82: aload           4
        //    84: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //    87: ldc2_w          -1
        //    90: lreturn        
        //    91: astore_0       
        //    92: aload_3        
        //    93: ifnull          100
        //    96: aload_3        
        //    97: invokevirtual   android/content/res/AssetFileDescriptor.close:()V
        //   100: aload_0        
        //   101: athrow         
        //   102: astore_0       
        //   103: lload_1        
        //   104: lreturn        
        //   105: astore_0       
        //   106: goto            87
        //   109: astore_3       
        //   110: goto            100
        //   113: astore_0       
        //   114: aload           4
        //   116: astore_3       
        //   117: goto            92
        //   120: astore          5
        //   122: goto            49
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      23     41     49     Ljava/io/IOException;
        //  5      23     91     92     Any
        //  23     29     120    125    Ljava/io/IOException;
        //  23     29     113    120    Any
        //  34     39     102    105    Ljava/io/IOException;
        //  52     77     91     92     Any
        //  82     87     105    109    Ljava/io/IOException;
        //  96     100    109    113    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 63, Size: 63
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
    
    public static String m() {
        return c("games");
    }
    
    public static String n() {
        return c("avatars");
    }
    
    public static boolean n(final String s) {
        t();
        if (StringUtils.c((CharSequence)s)) {
            return false;
        }
        final PackageManager packageManager = g.c.get().getPackageManager();
        if (packageManager != null) {
            try {
                packageManager.getPackageInfo(s, 1);
                return true;
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
        return false;
    }
    
    private static long o(final String s) {
        try {
            final StatFs statFs = new StatFs(s);
            return statFs.getBlockSize() * (long)statFs.getAvailableBlocks();
        }
        catch (IllegalArgumentException ex) {
            return 0L;
        }
    }
    
    public static String o() {
        return c("app_images");
    }
    
    private static long p(final String s) {
        try {
            final StatFs statFs = new StatFs(s);
            return statFs.getBlockSize() * (long)statFs.getBlockCount();
        }
        catch (IllegalArgumentException ex) {
            return 0L;
        }
    }
    
    public static String p() {
        return c("promotion_images");
    }
    
    public static String q() {
        return c("badge_images");
    }
    
    @TargetApi(9)
    public static String r() {
        final File file = new File(c("downloads"));
        if (!file.exists()) {
            file.mkdirs();
        }
        if (com.kongregate.android.internal.util.a.a(9)) {
            file.setReadable(true, false);
        }
        return file.getPath();
    }
    
    public static String s() {
        return c("userdata");
    }
    
    private static void t() {
        synchronized (g.class) {
            if (g.c.get() == null) {
                throw new IllegalStateException("You must call FileUtils.initialize first!");
            }
        }
    }
    // monitorexit(g.class)
    
    public interface a
    {
        void a(final int p0, final int p1, final double p2);
        
        void a(final p p0);
    }
    
    private static class b extends GZIPOutputStream
    {
        public b(final OutputStream outputStream) throws IOException {
            super(outputStream);
            this.a(9);
        }
        
        public void a(final int level) {
            this.def.setLevel(level);
        }
    }
}
