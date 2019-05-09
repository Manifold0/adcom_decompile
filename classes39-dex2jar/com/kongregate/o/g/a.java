// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.g;

import android.content.SharedPreferences$Editor;
import com.kongregate.android.internal.util.g;
import android.os.Build$VERSION;
import java.util.Iterator;
import java.net.CookieStore;
import com.kongregate.android.internal.util.StringUtils;
import java.net.HttpCookie;
import java.util.LinkedList;
import java.net.CookieManager;
import java.net.CookieHandler;
import com.kongregate.android.internal.util.p;
import java.util.Map;
import java.io.IOException;
import javax.net.ssl.HttpsURLConnection;
import com.kongregate.android.internal.util.j;
import javax.net.ssl.SSLSession;
import javax.net.ssl.HostnameVerifier;
import com.kongregate.o.d.b;
import java.net.URI;
import java.util.concurrent.atomic.AtomicReference;

public class a
{
    public static final String a = "application/json";
    public static final String b = "HttpClient/4.0";
    private static volatile a c;
    private final f d;
    private final AtomicReference<String> e;
    
    static {
        com.kongregate.o.g.a.c = null;
    }
    
    private a() {
        this.e = new AtomicReference<String>("HttpClient/4.0");
        System.setProperty("http.keepAlive", "false");
        final URI create = URI.create(k());
        final boolean k = com.kongregate.o.d.b.a().k();
        this.d = new f(create.getHost());
        if (!k) {
            HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(final String s, final SSLSession sslSession) {
                    j.c("Allowing insecure cerfiticate!");
                    return true;
                }
            });
        }
        try {
            HttpsURLConnection.setDefaultSSLSocketFactory(com.kongregate.o.g.e.a().getSocketFactory());
        }
        catch (IOException ex) {
            j.c("IOException setting socket factory", ex);
        }
        catch (Exception ex2) {
            j.c("Exception setting socket factory", ex2);
        }
    }
    
    public static a a() {
        synchronized (a.class) {
            if (com.kongregate.o.g.a.c == null) {
                com.kongregate.o.g.a.c = new a();
            }
            return com.kongregate.o.g.a.c;
        }
    }
    
    private c a(final String s, final String s2, final Map<String, Object> map) {
        return this.a(s, s2, map, "application/x-www-form-urlencoded", null);
    }
    
    private c a(final String p0, final String p1, final Map<String, Object> p2, final String p3, final Map<String, String> p4) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          11
        //     3: aconst_null    
        //     4: astore          10
        //     6: aload_0        
        //     7: invokespecial   com/kongregate/o/g/a.m:()V
        //    10: ldc             ""
        //    12: astore          9
        //    14: aload           9
        //    16: astore          8
        //    18: aload_3        
        //    19: ifnull          917
        //    22: aload           4
        //    24: ldc             "application/json"
        //    26: if_acmpne       229
        //    29: aload           11
        //    31: astore          7
        //    33: new             Lcom/google/gson/Gson;
        //    36: dup            
        //    37: invokespecial   com/google/gson/Gson.<init>:()V
        //    40: new             Ljava/util/HashMap;
        //    43: dup            
        //    44: aload_3        
        //    45: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
        //    48: invokevirtual   com/google/gson/Gson.toJson:(Ljava/lang/Object;)Ljava/lang/String;
        //    51: astore_3       
        //    52: aload           11
        //    54: astore          7
        //    56: new             Ljava/net/URL;
        //    59: dup            
        //    60: aload_1        
        //    61: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //    64: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
        //    67: checkcast       Ljava/net/HttpURLConnection;
        //    70: astore_1       
        //    71: aload_1        
        //    72: sipush          15000
        //    75: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    78: aload_1        
        //    79: sipush          15000
        //    82: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //    85: aload_1        
        //    86: aload_2        
        //    87: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //    90: aload_1        
        //    91: ldc             "Content-Type"
        //    93: aload           4
        //    95: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    98: aload_1        
        //    99: ldc             "Content-Language"
        //   101: ldc             "en-US"
        //   103: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   106: aload_1        
        //   107: ldc             "User-Agent"
        //   109: aload_0        
        //   110: getfield        com/kongregate/o/g/a.e:Ljava/util/concurrent/atomic/AtomicReference;
        //   113: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //   116: checkcast       Ljava/lang/String;
        //   119: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   122: aload_1        
        //   123: ldc             "Cookie"
        //   125: aload_0        
        //   126: getfield        com/kongregate/o/g/a.d:Lcom/kongregate/o/g/f;
        //   129: invokevirtual   com/kongregate/o/g/f.e:()Ljava/lang/String;
        //   132: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   135: aload           5
        //   137: ifnull          400
        //   140: aload           5
        //   142: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   147: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   152: astore          4
        //   154: aload           4
        //   156: invokeinterface java/util/Iterator.hasNext:()Z
        //   161: ifeq            400
        //   164: aload           4
        //   166: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   171: checkcast       Ljava/lang/String;
        //   174: astore          7
        //   176: aload_1        
        //   177: aload           7
        //   179: aload           5
        //   181: aload           7
        //   183: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   188: checkcast       Ljava/lang/String;
        //   191: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   194: goto            154
        //   197: astore_2       
        //   198: ldc             "IOException in executeUrlRequest"
        //   200: aload_2        
        //   201: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   204: new             Lcom/kongregate/o/g/c;
        //   207: dup            
        //   208: getstatic       com/kongregate/android/internal/util/p.d:Lcom/kongregate/android/internal/util/p;
        //   211: invokespecial   com/kongregate/o/g/c.<init>:(Lcom/kongregate/android/internal/util/p;)V
        //   214: astore_2       
        //   215: aload_1        
        //   216: ifnull          223
        //   219: aload_1        
        //   220: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   223: aload_0        
        //   224: invokespecial   com/kongregate/o/g/a.m:()V
        //   227: aload_2        
        //   228: areturn        
        //   229: aload           11
        //   231: astore          7
        //   233: aload_3        
        //   234: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   239: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   244: astore          12
        //   246: aload           9
        //   248: astore_3       
        //   249: aload           11
        //   251: astore          7
        //   253: aload_3        
        //   254: astore          8
        //   256: aload           12
        //   258: invokeinterface java/util/Iterator.hasNext:()Z
        //   263: ifeq            917
        //   266: aload           11
        //   268: astore          7
        //   270: aload           12
        //   272: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   277: checkcast       Ljava/util/Map$Entry;
        //   280: astore          8
        //   282: aload           11
        //   284: astore          7
        //   286: aload           8
        //   288: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   293: checkcast       Ljava/lang/CharSequence;
        //   296: invokestatic    com/kongregate/android/internal/util/StringUtils.b:(Ljava/lang/CharSequence;)Z
        //   299: ifeq            914
        //   302: aload           11
        //   304: astore          7
        //   306: aload           8
        //   308: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   313: ifnull          914
        //   316: aload           11
        //   318: astore          7
        //   320: aload           8
        //   322: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   327: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   330: invokestatic    com/kongregate/android/internal/util/StringUtils.b:(Ljava/lang/CharSequence;)Z
        //   333: ifeq            914
        //   336: aload           11
        //   338: astore          7
        //   340: new             Ljava/lang/StringBuilder;
        //   343: dup            
        //   344: invokespecial   java/lang/StringBuilder.<init>:()V
        //   347: aload_3        
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: aload           8
        //   353: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   358: checkcast       Ljava/lang/String;
        //   361: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   364: ldc             "="
        //   366: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   369: aload           8
        //   371: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   376: invokevirtual   java/lang/Object.toString:()Ljava/lang/String;
        //   379: ldc             "UTF-8"
        //   381: invokestatic    java/net/URLEncoder.encode:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   384: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   387: ldc_w           "&"
        //   390: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   393: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   396: astore_3       
        //   397: goto            249
        //   400: aload_1        
        //   401: iconst_0       
        //   402: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
        //   405: aload_1        
        //   406: iconst_1       
        //   407: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //   410: aload_1        
        //   411: ldc_w           "POST"
        //   414: aload_2        
        //   415: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   418: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   421: aload_1        
        //   422: ldc_w           "Connection"
        //   425: ldc_w           "close"
        //   428: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   431: aload_1        
        //   432: invokevirtual   java/net/HttpURLConnection.getDoOutput:()Z
        //   435: ifeq            463
        //   438: new             Ljava/io/DataOutputStream;
        //   441: dup            
        //   442: aload_1        
        //   443: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   446: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   449: astore_2       
        //   450: aload_2        
        //   451: aload_3        
        //   452: invokevirtual   java/io/DataOutputStream.writeBytes:(Ljava/lang/String;)V
        //   455: aload_2        
        //   456: invokevirtual   java/io/DataOutputStream.flush:()V
        //   459: aload_2        
        //   460: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   463: aload_1        
        //   464: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   467: astore_2       
        //   468: new             Ljava/io/BufferedReader;
        //   471: dup            
        //   472: new             Ljava/io/InputStreamReader;
        //   475: dup            
        //   476: aload_2        
        //   477: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //   480: sipush          1024
        //   483: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //   486: astore_2       
        //   487: new             Ljava/lang/StringBuilder;
        //   490: dup            
        //   491: sipush          1024
        //   494: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   497: astore          4
        //   499: aload_2        
        //   500: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   503: astore          5
        //   505: aload           5
        //   507: ifnull          617
        //   510: aload           4
        //   512: aload           5
        //   514: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   517: pop            
        //   518: aload           4
        //   520: bipush          10
        //   522: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   525: pop            
        //   526: goto            499
        //   529: astore_2       
        //   530: aload_1        
        //   531: astore          7
        //   533: ldc_w           "NumberFormatException in executeUrlRequest"
        //   536: aload_2        
        //   537: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   540: aload_1        
        //   541: astore          7
        //   543: new             Lcom/kongregate/o/g/c;
        //   546: dup            
        //   547: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   550: invokespecial   com/kongregate/o/g/c.<init>:(Lcom/kongregate/android/internal/util/p;)V
        //   553: astore_2       
        //   554: aload_1        
        //   555: ifnull          562
        //   558: aload_1        
        //   559: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   562: aload_0        
        //   563: invokespecial   com/kongregate/o/g/a.m:()V
        //   566: aload_2        
        //   567: areturn        
        //   568: astore          5
        //   570: ldc_w           "HTTP Post error"
        //   573: aload           5
        //   575: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   578: aload_1        
        //   579: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   582: astore          4
        //   584: aload           4
        //   586: astore_2       
        //   587: aload           4
        //   589: ifnonnull       468
        //   592: aload           5
        //   594: athrow         
        //   595: astore_2       
        //   596: aload_1        
        //   597: astore          7
        //   599: aload_2        
        //   600: astore_1       
        //   601: aload           7
        //   603: ifnull          611
        //   606: aload           7
        //   608: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   611: aload_0        
        //   612: invokespecial   com/kongregate/o/g/a.m:()V
        //   615: aload_1        
        //   616: athrow         
        //   617: aload_2        
        //   618: invokestatic    com/kongregate/android/internal/util/g.a:(Ljava/io/Closeable;)V
        //   621: aload_1        
        //   622: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   625: istore          6
        //   627: aload           4
        //   629: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   632: ldc             "UTF-8"
        //   634: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //   637: astore_2       
        //   638: iload           6
        //   640: sipush          200
        //   643: if_icmpne       813
        //   646: aload_1        
        //   647: invokevirtual   java/net/HttpURLConnection.getHeaderFields:()Ljava/util/Map;
        //   650: astore_3       
        //   651: aload_3        
        //   652: ifnull          785
        //   655: new             Ljava/util/LinkedList;
        //   658: dup            
        //   659: invokespecial   java/util/LinkedList.<init>:()V
        //   662: astore          4
        //   664: aload_3        
        //   665: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   670: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   675: astore          5
        //   677: aload           5
        //   679: invokeinterface java/util/Iterator.hasNext:()Z
        //   684: ifeq            739
        //   687: aload           5
        //   689: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   694: checkcast       Ljava/lang/String;
        //   697: astore          7
        //   699: ldc_w           "Set-Cookie"
        //   702: aload           7
        //   704: invokevirtual   java/lang/String.equalsIgnoreCase:(Ljava/lang/String;)Z
        //   707: ifeq            677
        //   710: aload_3        
        //   711: aload           7
        //   713: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   718: checkcast       Ljava/util/List;
        //   721: astore          7
        //   723: aload           7
        //   725: ifnull          677
        //   728: aload           4
        //   730: aload           7
        //   732: invokevirtual   java/util/LinkedList.addAll:(Ljava/util/Collection;)Z
        //   735: pop            
        //   736: goto            677
        //   739: aload           4
        //   741: invokevirtual   java/util/LinkedList.isEmpty:()Z
        //   744: ifne            785
        //   747: aload           4
        //   749: invokevirtual   java/util/LinkedList.iterator:()Ljava/util/Iterator;
        //   752: astore_3       
        //   753: aload_3        
        //   754: invokeinterface java/util/Iterator.hasNext:()Z
        //   759: ifeq            785
        //   762: aload_3        
        //   763: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   768: checkcast       Ljava/lang/String;
        //   771: astore          4
        //   773: aload_0        
        //   774: getfield        com/kongregate/o/g/a.d:Lcom/kongregate/o/g/f;
        //   777: aload           4
        //   779: invokevirtual   com/kongregate/o/g/f.a:(Ljava/lang/String;)V
        //   782: goto            753
        //   785: new             Lcom/kongregate/o/g/c;
        //   788: dup            
        //   789: getstatic       com/kongregate/android/internal/util/p.a:Lcom/kongregate/android/internal/util/p;
        //   792: aload_2        
        //   793: iload           6
        //   795: invokespecial   com/kongregate/o/g/c.<init>:(Lcom/kongregate/android/internal/util/p;[BI)V
        //   798: astore_2       
        //   799: aload_1        
        //   800: ifnull          807
        //   803: aload_1        
        //   804: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   807: aload_0        
        //   808: invokespecial   com/kongregate/o/g/a.m:()V
        //   811: aload_2        
        //   812: areturn        
        //   813: new             Ljava/lang/StringBuilder;
        //   816: dup            
        //   817: invokespecial   java/lang/StringBuilder.<init>:()V
        //   820: ldc_w           "HttpUrlConnection failed: "
        //   823: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   826: iload           6
        //   828: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   831: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   834: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;)V
        //   837: new             Ljava/lang/StringBuilder;
        //   840: dup            
        //   841: invokespecial   java/lang/StringBuilder.<init>:()V
        //   844: ldc_w           "Connection parameters: "
        //   847: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   850: aload_3        
        //   851: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   854: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   857: invokestatic    com/kongregate/android/internal/util/j.a:(Ljava/lang/String;)V
        //   860: new             Lcom/kongregate/o/g/c;
        //   863: dup            
        //   864: getstatic       com/kongregate/android/internal/util/p.c:Lcom/kongregate/android/internal/util/p;
        //   867: aload_2        
        //   868: iload           6
        //   870: invokespecial   com/kongregate/o/g/c.<init>:(Lcom/kongregate/android/internal/util/p;[BI)V
        //   873: astore_2       
        //   874: aload_1        
        //   875: ifnull          882
        //   878: aload_1        
        //   879: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   882: aload_0        
        //   883: invokespecial   com/kongregate/o/g/a.m:()V
        //   886: aload_2        
        //   887: areturn        
        //   888: astore_1       
        //   889: goto            601
        //   892: astore_2       
        //   893: aload_1        
        //   894: astore          7
        //   896: aload_2        
        //   897: astore_1       
        //   898: goto            601
        //   901: astore_2       
        //   902: aload           10
        //   904: astore_1       
        //   905: goto            530
        //   908: astore_2       
        //   909: aconst_null    
        //   910: astore_1       
        //   911: goto            198
        //   914: goto            397
        //   917: aload           8
        //   919: astore_3       
        //   920: goto            52
        //    Signature:
        //  (Ljava/lang/String;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/Object;>;Ljava/lang/String;Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Lcom/kongregate/o/g/c;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  33     52     908    914    Ljava/io/IOException;
        //  33     52     901    908    Ljava/lang/NumberFormatException;
        //  33     52     888    892    Any
        //  56     71     908    914    Ljava/io/IOException;
        //  56     71     901    908    Ljava/lang/NumberFormatException;
        //  56     71     888    892    Any
        //  71     135    197    198    Ljava/io/IOException;
        //  71     135    529    530    Ljava/lang/NumberFormatException;
        //  71     135    595    601    Any
        //  140    154    197    198    Ljava/io/IOException;
        //  140    154    529    530    Ljava/lang/NumberFormatException;
        //  140    154    595    601    Any
        //  154    194    197    198    Ljava/io/IOException;
        //  154    194    529    530    Ljava/lang/NumberFormatException;
        //  154    194    595    601    Any
        //  198    215    892    901    Any
        //  233    246    908    914    Ljava/io/IOException;
        //  233    246    901    908    Ljava/lang/NumberFormatException;
        //  233    246    888    892    Any
        //  256    266    908    914    Ljava/io/IOException;
        //  256    266    901    908    Ljava/lang/NumberFormatException;
        //  256    266    888    892    Any
        //  270    282    908    914    Ljava/io/IOException;
        //  270    282    901    908    Ljava/lang/NumberFormatException;
        //  270    282    888    892    Any
        //  286    302    908    914    Ljava/io/IOException;
        //  286    302    901    908    Ljava/lang/NumberFormatException;
        //  286    302    888    892    Any
        //  306    316    908    914    Ljava/io/IOException;
        //  306    316    901    908    Ljava/lang/NumberFormatException;
        //  306    316    888    892    Any
        //  320    336    908    914    Ljava/io/IOException;
        //  320    336    901    908    Ljava/lang/NumberFormatException;
        //  320    336    888    892    Any
        //  340    397    908    914    Ljava/io/IOException;
        //  340    397    901    908    Ljava/lang/NumberFormatException;
        //  340    397    888    892    Any
        //  400    463    197    198    Ljava/io/IOException;
        //  400    463    529    530    Ljava/lang/NumberFormatException;
        //  400    463    595    601    Any
        //  463    468    568    595    Ljava/io/FileNotFoundException;
        //  463    468    197    198    Ljava/io/IOException;
        //  463    468    529    530    Ljava/lang/NumberFormatException;
        //  463    468    595    601    Any
        //  468    499    197    198    Ljava/io/IOException;
        //  468    499    529    530    Ljava/lang/NumberFormatException;
        //  468    499    595    601    Any
        //  499    505    197    198    Ljava/io/IOException;
        //  499    505    529    530    Ljava/lang/NumberFormatException;
        //  499    505    595    601    Any
        //  510    526    197    198    Ljava/io/IOException;
        //  510    526    529    530    Ljava/lang/NumberFormatException;
        //  510    526    595    601    Any
        //  533    540    888    892    Any
        //  543    554    888    892    Any
        //  570    584    197    198    Ljava/io/IOException;
        //  570    584    529    530    Ljava/lang/NumberFormatException;
        //  570    584    595    601    Any
        //  592    595    197    198    Ljava/io/IOException;
        //  592    595    529    530    Ljava/lang/NumberFormatException;
        //  592    595    595    601    Any
        //  617    638    197    198    Ljava/io/IOException;
        //  617    638    529    530    Ljava/lang/NumberFormatException;
        //  617    638    595    601    Any
        //  646    651    197    198    Ljava/io/IOException;
        //  646    651    529    530    Ljava/lang/NumberFormatException;
        //  646    651    595    601    Any
        //  655    677    197    198    Ljava/io/IOException;
        //  655    677    529    530    Ljava/lang/NumberFormatException;
        //  655    677    595    601    Any
        //  677    723    197    198    Ljava/io/IOException;
        //  677    723    529    530    Ljava/lang/NumberFormatException;
        //  677    723    595    601    Any
        //  728    736    197    198    Ljava/io/IOException;
        //  728    736    529    530    Ljava/lang/NumberFormatException;
        //  728    736    595    601    Any
        //  739    753    197    198    Ljava/io/IOException;
        //  739    753    529    530    Ljava/lang/NumberFormatException;
        //  739    753    595    601    Any
        //  753    782    197    198    Ljava/io/IOException;
        //  753    782    529    530    Ljava/lang/NumberFormatException;
        //  753    782    595    601    Any
        //  785    799    197    198    Ljava/io/IOException;
        //  785    799    529    530    Ljava/lang/NumberFormatException;
        //  785    799    595    601    Any
        //  813    874    197    198    Ljava/io/IOException;
        //  813    874    529    530    Ljava/lang/NumberFormatException;
        //  813    874    595    601    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0463:
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
    
    public static URI a(final String s, final boolean b) {
        try {
            if (s.indexOf("http") != 0) {
                final StringBuilder sb = new StringBuilder();
                String s2;
                if (b) {
                    s2 = l();
                }
                else {
                    s2 = k();
                }
                return URI.create(sb.append(s2).append(s).toString());
            }
            return URI.create(s);
        }
        catch (IllegalArgumentException ex) {
            j.c("Illegal URI: " + s, ex);
            return null;
        }
    }
    
    public static a b() {
        synchronized (a.class) {
            return com.kongregate.o.g.a.c;
        }
    }
    
    private c b(final String s, final boolean b) {
        if (a(s, b) == null) {
            return new c(p.c, null, 404);
        }
        return this.a(a(s, b).toString(), "GET", null);
    }
    
    private static String j() {
        return com.kongregate.o.d.b.a().c();
    }
    
    private static String k() {
        return "http://" + j();
    }
    
    private static String l() {
        if (com.kongregate.o.d.b.a().j()) {
            return "https://" + j();
        }
        return k();
    }
    
    private void m() {
        final CookieHandler default1 = CookieHandler.getDefault();
        if (default1 instanceof CookieManager) {
            final CookieStore cookieStore = ((CookieManager)default1).getCookieStore();
            if (cookieStore != null) {
                final LinkedList<HttpCookie> list = new LinkedList<HttpCookie>();
                for (final HttpCookie httpCookie : cookieStore.getCookies()) {
                    if (StringUtils.b(j(), httpCookie.getDomain())) {
                        list.add(httpCookie);
                    }
                }
                for (final HttpCookie httpCookie2 : list) {
                    final StringBuilder sb = new StringBuilder();
                    String s;
                    if (httpCookie2.getSecure()) {
                        s = "https://";
                    }
                    else {
                        s = "http://";
                    }
                    final URI create = URI.create(sb.append(s).append(httpCookie2.getDomain()).append(httpCookie2.getPath()).toString());
                    if (cookieStore.remove(create, httpCookie2)) {
                        j.a("cleanDefaultCookieStore: cookie removed: " + httpCookie2.getName() + " from domain " + create.toString());
                    }
                    else {
                        j.a("cleanDefaultCookieStore: cookie not removed: " + httpCookie2.getName() + " from domain " + create.toString());
                    }
                }
            }
        }
    }
    
    public c a(final String s, final Map<String, Object> map) {
        return this.a(a(s, true).toString(), "POST", map);
    }
    
    public c a(final String s, final Map<String, Object> map, final String s2, final Map<String, String> map2) {
        return this.a(a(s, true).toString(), "POST", map, s2, map2);
    }
    
    public String a(final String s) {
        return this.d.b(s);
    }
    
    public void a(final long n, final String s) {
        final HttpCookie httpCookie = new HttpCookie("kongregateMobileApi", "api:3.0.5:" + String.valueOf(n) + ":android:" + Build$VERSION.RELEASE + ":" + s);
        httpCookie.setDomain(com.kongregate.o.d.b.a().c());
        httpCookie.setPath("/");
        this.d.a(httpCookie, true);
        j.b("API cookie set to: " + this.d.b("kongregateMobileApi"));
    }
    
    public void a(final String s, final String s2) {
        this.d.a(this.d.a("_kongregate_session", s));
        this.d.a(this.d.a("kong_sec", s2, true));
    }
    
    public void a(final boolean b) {
        final String b2 = this.d.b("_kongregate_session");
        final String b3 = this.d.b("kong_sec");
        if (StringUtils.b((CharSequence)b2)) {
            j.a("Session cookie found in WebView");
            final SharedPreferences$Editor edit = g.e().edit();
            if (b) {
                j.c("User is a guest but we have a session cookie, removing all cookies");
                edit.remove("session_backup");
                edit.remove("secure_backup");
                this.g();
            }
            else {
                j.a("Backing up session cookie");
                edit.putString("session_backup", b2);
                if (StringUtils.b((CharSequence)b3)) {
                    edit.putString("secure_backup", b3);
                }
            }
            edit.commit();
        }
        else if (!b) {
            final String string = g.e().getString("session_backup", (String)null);
            if (StringUtils.b((CharSequence)string)) {
                j.c("Session cookie not found, restoring from backup");
                this.d.a(this.d.a("_kongregate_session", string));
                final String string2 = g.e().getString("secure_backup", (String)null);
                if (StringUtils.a((CharSequence)b3) && StringUtils.b((CharSequence)string2)) {
                    j.a("Restoring secure cookie from backup");
                    this.d.a(this.d.a("_kongregate_session", string2, true));
                }
            }
        }
    }
    
    public c b(final String s, final Map<String, Object> map) {
        return this.a(a(s, false).toString(), "POST", map);
    }
    
    public c b(final String s, final Map<String, Object> map, final String s2, final Map<String, String> map2) {
        return this.a(a(s, false).toString(), "POST", map, s2, map2);
    }
    
    public void b(final String s) {
        this.e.set(s);
        j.a("User agent set to: " + s);
    }
    
    public String c() {
        return this.a("kong_svid");
    }
    
    public void c(final String s) {
        this.d.c(s);
    }
    
    public void d() {
        this.d.a();
    }
    
    public void d(final String s) {
        this.d.d(s);
    }
    
    public c e(final String s) {
        return this.b(s, false);
    }
    
    public void e() {
        final SharedPreferences$Editor edit = g.e().edit();
        edit.remove("session_backup");
        edit.remove("secure_backup");
        edit.commit();
        this.d.b();
    }
    
    public c f(final String s) {
        return this.b(s, true);
    }
    
    public String f() {
        return this.d.c();
    }
    
    public void g() {
        final SharedPreferences$Editor edit = g.e().edit();
        edit.remove("session_backup");
        edit.remove("secure_backup");
        edit.commit();
        this.d.d();
    }
    
    public boolean h() {
        return StringUtils.b((CharSequence)this.d.b("kong_sec"));
    }
    
    public void i() {
        this.d.a(true);
    }
}
