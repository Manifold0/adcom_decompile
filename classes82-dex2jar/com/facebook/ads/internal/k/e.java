// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.k;

import android.content.SharedPreferences$Editor;
import java.io.FileOutputStream;
import com.facebook.ads.internal.w.b.v;
import java.util.UUID;
import org.json.JSONObject;
import android.util.Log;
import java.io.File;
import com.facebook.ads.internal.w.f.a;
import android.support.annotation.WorkerThread;
import org.json.JSONArray;
import com.facebook.ads.internal.w.b.s;
import com.facebook.ads.internal.w.b.o;
import android.content.Context;
import java.util.HashMap;
import java.util.Collections;
import java.util.HashSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.Map;
import java.util.Set;

public class e
{
    private static final String a;
    private static final Object b;
    private static final Set<String> c;
    private static final Map<String, Integer> d;
    private static AtomicInteger e;
    
    static {
        a = e.class.getName();
        b = new Object();
        c = Collections.synchronizedSet(new HashSet<String>());
        d = Collections.synchronizedMap(new HashMap<String, Integer>());
        com.facebook.ads.internal.k.e.e = new AtomicInteger();
    }
    
    public static d a(Exception ex, final Context context, final Map<String, String> map) {
        try {
            final Object o;
            ex = (Exception)(o = new d(com.facebook.ads.internal.w.b.o.b(), com.facebook.ads.internal.w.b.o.c(), new b(s.a(ex), map, true).a()));
            final Context context2 = context;
            a((d)o, context2);
            final Exception ex2 = ex;
            return (d)ex2;
        }
        catch (Exception ex) {
            return null;
        }
        try {
            final Object o = ex;
            final Context context2 = context;
            a((d)o, context2);
            final Exception ex2 = ex;
            return (d)ex2;
        }
        catch (Exception ex3) {
            return (d)ex;
        }
    }
    
    @WorkerThread
    public static JSONArray a(final Context context) {
        return a(context, -1);
    }
    
    @WorkerThread
    public static JSONArray a(final Context p0, final int p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: new             Lorg/json/JSONArray;
        //     6: dup            
        //     7: invokespecial   org/json/JSONArray.<init>:()V
        //    10: astore          11
        //    12: getstatic       com/facebook/ads/internal/k/e.b:Ljava/lang/Object;
        //    15: astore          10
        //    17: aload           10
        //    19: monitorenter   
        //    20: new             Ljava/io/File;
        //    23: dup            
        //    24: aload_0        
        //    25: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    28: ldc             "debuglogs"
        //    30: aload_0        
        //    31: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //    34: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    37: invokevirtual   java/io/File.exists:()Z
        //    40: ifeq            523
        //    43: aload_0        
        //    44: ldc             "debuglogs"
        //    46: aload_0        
        //    47: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //    50: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    53: astore_0       
        //    54: new             Ljava/io/InputStreamReader;
        //    57: dup            
        //    58: aload_0        
        //    59: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    62: astore_3       
        //    63: new             Ljava/io/BufferedReader;
        //    66: dup            
        //    67: aload_3        
        //    68: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    71: astore          4
        //    73: aload           4
        //    75: astore          9
        //    77: aload_3        
        //    78: astore          8
        //    80: aload_0        
        //    81: astore          7
        //    83: aload           4
        //    85: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    88: astore          12
        //    90: aload           4
        //    92: astore          7
        //    94: aload_3        
        //    95: astore          6
        //    97: aload_0        
        //    98: astore          5
        //   100: aload           12
        //   102: ifnull          532
        //   105: aload           4
        //   107: astore          7
        //   109: aload_3        
        //   110: astore          6
        //   112: aload_0        
        //   113: astore          5
        //   115: iload_1        
        //   116: ifeq            532
        //   119: aload           4
        //   121: astore          9
        //   123: aload_3        
        //   124: astore          8
        //   126: aload_0        
        //   127: astore          7
        //   129: new             Lorg/json/JSONObject;
        //   132: dup            
        //   133: aload           12
        //   135: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   138: astore          5
        //   140: aload           4
        //   142: astore          9
        //   144: aload_3        
        //   145: astore          8
        //   147: aload_0        
        //   148: astore          7
        //   150: aload           5
        //   152: ldc             "attempt"
        //   154: invokevirtual   org/json/JSONObject.has:(Ljava/lang/String;)Z
        //   157: ifne            182
        //   160: aload           4
        //   162: astore          9
        //   164: aload_3        
        //   165: astore          8
        //   167: aload_0        
        //   168: astore          7
        //   170: aload           5
        //   172: ldc             "attempt"
        //   174: iconst_0       
        //   175: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   178: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   181: pop            
        //   182: aload           4
        //   184: astore          9
        //   186: aload_3        
        //   187: astore          8
        //   189: aload_0        
        //   190: astore          7
        //   192: aload           5
        //   194: ldc             "id"
        //   196: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   199: astore          6
        //   201: aload           4
        //   203: astore          9
        //   205: aload_3        
        //   206: astore          8
        //   208: aload_0        
        //   209: astore          7
        //   211: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //   214: aload           6
        //   216: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   221: ifne            73
        //   224: aload           4
        //   226: astore          9
        //   228: aload_3        
        //   229: astore          8
        //   231: aload_0        
        //   232: astore          7
        //   234: aload           5
        //   236: ldc             "attempt"
        //   238: invokevirtual   org/json/JSONObject.getInt:(Ljava/lang/String;)I
        //   241: istore_2       
        //   242: aload           4
        //   244: astore          9
        //   246: aload_3        
        //   247: astore          8
        //   249: aload_0        
        //   250: astore          7
        //   252: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   255: aload           6
        //   257: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   262: ifeq            325
        //   265: aload           4
        //   267: astore          9
        //   269: aload_3        
        //   270: astore          8
        //   272: aload_0        
        //   273: astore          7
        //   275: aload           5
        //   277: ldc             "attempt"
        //   279: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   282: aload           6
        //   284: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   289: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   292: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   295: pop            
        //   296: aload           4
        //   298: astore          9
        //   300: aload_3        
        //   301: astore          8
        //   303: aload_0        
        //   304: astore          7
        //   306: aload           11
        //   308: aload           5
        //   310: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   313: pop            
        //   314: iload_1        
        //   315: ifle            73
        //   318: iload_1        
        //   319: iconst_1       
        //   320: isub           
        //   321: istore_1       
        //   322: goto            73
        //   325: aload           4
        //   327: astore          9
        //   329: aload_3        
        //   330: astore          8
        //   332: aload_0        
        //   333: astore          7
        //   335: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //   338: aload           6
        //   340: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   345: ifeq            443
        //   348: aload           4
        //   350: astore          9
        //   352: aload_3        
        //   353: astore          8
        //   355: aload_0        
        //   356: astore          7
        //   358: new             Ljava/lang/RuntimeException;
        //   361: dup            
        //   362: ldc             "finished event should not be updated to OngoingEvent."
        //   364: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   367: athrow         
        //   368: astore          6
        //   370: aload_3        
        //   371: astore          5
        //   373: aload_0        
        //   374: astore_3       
        //   375: aload           6
        //   377: astore_0       
        //   378: aload_3        
        //   379: astore          6
        //   381: aload           5
        //   383: astore_3       
        //   384: aload_0        
        //   385: astore          5
        //   387: aload           4
        //   389: astore          9
        //   391: aload_3        
        //   392: astore          8
        //   394: aload           6
        //   396: astore          7
        //   398: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //   401: ldc             "Failed to read crashes"
        //   403: aload           5
        //   405: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   408: pop            
        //   409: aload           4
        //   411: ifnull          419
        //   414: aload           4
        //   416: invokevirtual   java/io/BufferedReader.close:()V
        //   419: aload_3        
        //   420: ifnull          427
        //   423: aload_3        
        //   424: invokevirtual   java/io/InputStreamReader.close:()V
        //   427: aload           6
        //   429: ifnull          437
        //   432: aload           6
        //   434: invokevirtual   java/io/FileInputStream.close:()V
        //   437: aload           10
        //   439: monitorexit    
        //   440: aload           11
        //   442: areturn        
        //   443: aload           4
        //   445: astore          9
        //   447: aload_3        
        //   448: astore          8
        //   450: aload_0        
        //   451: astore          7
        //   453: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   456: aload           6
        //   458: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   463: ifeq            487
        //   466: aload           4
        //   468: astore          9
        //   470: aload_3        
        //   471: astore          8
        //   473: aload_0        
        //   474: astore          7
        //   476: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   479: aload           6
        //   481: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   486: pop            
        //   487: aload           4
        //   489: astore          9
        //   491: aload_3        
        //   492: astore          8
        //   494: aload_0        
        //   495: astore          7
        //   497: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   500: aload           6
        //   502: iload_2        
        //   503: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   506: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   511: pop            
        //   512: goto            296
        //   515: astore          5
        //   517: aload_0        
        //   518: astore          6
        //   520: goto            387
        //   523: aconst_null    
        //   524: astore          7
        //   526: aconst_null    
        //   527: astore          6
        //   529: aconst_null    
        //   530: astore          5
        //   532: aload           7
        //   534: ifnull          542
        //   537: aload           7
        //   539: invokevirtual   java/io/BufferedReader.close:()V
        //   542: aload           6
        //   544: ifnull          552
        //   547: aload           6
        //   549: invokevirtual   java/io/InputStreamReader.close:()V
        //   552: aload           5
        //   554: ifnull          437
        //   557: aload           5
        //   559: invokevirtual   java/io/FileInputStream.close:()V
        //   562: goto            437
        //   565: astore_0       
        //   566: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //   569: ldc             "Failed to close buffers"
        //   571: aload_0        
        //   572: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   575: pop            
        //   576: goto            437
        //   579: astore_0       
        //   580: aload           10
        //   582: monitorexit    
        //   583: aload_0        
        //   584: athrow         
        //   585: astore_0       
        //   586: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //   589: ldc             "Failed to close buffers"
        //   591: aload_0        
        //   592: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   595: pop            
        //   596: goto            437
        //   599: astore          4
        //   601: aconst_null    
        //   602: astore          5
        //   604: aconst_null    
        //   605: astore_3       
        //   606: aconst_null    
        //   607: astore_0       
        //   608: aload           5
        //   610: ifnull          618
        //   613: aload           5
        //   615: invokevirtual   java/io/BufferedReader.close:()V
        //   618: aload_3        
        //   619: ifnull          626
        //   622: aload_3        
        //   623: invokevirtual   java/io/InputStreamReader.close:()V
        //   626: aload_0        
        //   627: ifnull          634
        //   630: aload_0        
        //   631: invokevirtual   java/io/FileInputStream.close:()V
        //   634: aload           4
        //   636: athrow         
        //   637: astore_0       
        //   638: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //   641: ldc             "Failed to close buffers"
        //   643: aload_0        
        //   644: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   647: pop            
        //   648: goto            634
        //   651: astore          4
        //   653: aconst_null    
        //   654: astore          5
        //   656: aconst_null    
        //   657: astore_3       
        //   658: goto            608
        //   661: astore          4
        //   663: aconst_null    
        //   664: astore          5
        //   666: goto            608
        //   669: astore          4
        //   671: aload           9
        //   673: astore          5
        //   675: aload           8
        //   677: astore_3       
        //   678: aload           7
        //   680: astore_0       
        //   681: goto            608
        //   684: astore          5
        //   686: aconst_null    
        //   687: astore          4
        //   689: aconst_null    
        //   690: astore_3       
        //   691: aconst_null    
        //   692: astore          6
        //   694: goto            387
        //   697: astore          5
        //   699: aconst_null    
        //   700: astore          4
        //   702: aconst_null    
        //   703: astore_3       
        //   704: aload_0        
        //   705: astore          6
        //   707: goto            387
        //   710: astore          5
        //   712: aconst_null    
        //   713: astore          4
        //   715: aload_0        
        //   716: astore          6
        //   718: goto            387
        //   721: astore_0       
        //   722: aconst_null    
        //   723: astore          4
        //   725: aconst_null    
        //   726: astore_3       
        //   727: goto            378
        //   730: astore          6
        //   732: aconst_null    
        //   733: astore          4
        //   735: aload_0        
        //   736: astore_3       
        //   737: aload           6
        //   739: astore_0       
        //   740: goto            378
        //   743: astore          5
        //   745: aconst_null    
        //   746: astore          4
        //   748: aload_0        
        //   749: astore          6
        //   751: aload           5
        //   753: astore_0       
        //   754: aload_3        
        //   755: astore          5
        //   757: aload           6
        //   759: astore_3       
        //   760: goto            378
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  20     54     721    730    Ljava/io/IOException;
        //  20     54     684    697    Lorg/json/JSONException;
        //  20     54     599    608    Any
        //  54     63     730    743    Ljava/io/IOException;
        //  54     63     697    710    Lorg/json/JSONException;
        //  54     63     651    661    Any
        //  63     73     743    763    Ljava/io/IOException;
        //  63     73     710    721    Lorg/json/JSONException;
        //  63     73     661    669    Any
        //  83     90     368    378    Ljava/io/IOException;
        //  83     90     515    523    Lorg/json/JSONException;
        //  83     90     669    684    Any
        //  129    140    368    378    Ljava/io/IOException;
        //  129    140    515    523    Lorg/json/JSONException;
        //  129    140    669    684    Any
        //  150    160    368    378    Ljava/io/IOException;
        //  150    160    515    523    Lorg/json/JSONException;
        //  150    160    669    684    Any
        //  170    182    368    378    Ljava/io/IOException;
        //  170    182    515    523    Lorg/json/JSONException;
        //  170    182    669    684    Any
        //  192    201    368    378    Ljava/io/IOException;
        //  192    201    515    523    Lorg/json/JSONException;
        //  192    201    669    684    Any
        //  211    224    368    378    Ljava/io/IOException;
        //  211    224    515    523    Lorg/json/JSONException;
        //  211    224    669    684    Any
        //  234    242    368    378    Ljava/io/IOException;
        //  234    242    515    523    Lorg/json/JSONException;
        //  234    242    669    684    Any
        //  252    265    368    378    Ljava/io/IOException;
        //  252    265    515    523    Lorg/json/JSONException;
        //  252    265    669    684    Any
        //  275    296    368    378    Ljava/io/IOException;
        //  275    296    515    523    Lorg/json/JSONException;
        //  275    296    669    684    Any
        //  306    314    368    378    Ljava/io/IOException;
        //  306    314    515    523    Lorg/json/JSONException;
        //  306    314    669    684    Any
        //  335    348    368    378    Ljava/io/IOException;
        //  335    348    515    523    Lorg/json/JSONException;
        //  335    348    669    684    Any
        //  358    368    368    378    Ljava/io/IOException;
        //  358    368    515    523    Lorg/json/JSONException;
        //  358    368    669    684    Any
        //  398    409    669    684    Any
        //  414    419    585    599    Ljava/io/IOException;
        //  414    419    579    585    Any
        //  423    427    585    599    Ljava/io/IOException;
        //  423    427    579    585    Any
        //  432    437    585    599    Ljava/io/IOException;
        //  432    437    579    585    Any
        //  437    440    579    585    Any
        //  453    466    368    378    Ljava/io/IOException;
        //  453    466    515    523    Lorg/json/JSONException;
        //  453    466    669    684    Any
        //  476    487    368    378    Ljava/io/IOException;
        //  476    487    515    523    Lorg/json/JSONException;
        //  476    487    669    684    Any
        //  497    512    368    378    Ljava/io/IOException;
        //  497    512    515    523    Lorg/json/JSONException;
        //  497    512    669    684    Any
        //  537    542    565    579    Ljava/io/IOException;
        //  537    542    579    585    Any
        //  547    552    565    579    Ljava/io/IOException;
        //  547    552    579    585    Any
        //  557    562    565    579    Ljava/io/IOException;
        //  557    562    579    585    Any
        //  566    576    579    585    Any
        //  580    583    579    585    Any
        //  586    596    579    585    Any
        //  613    618    637    651    Ljava/io/IOException;
        //  613    618    579    585    Any
        //  622    626    637    651    Ljava/io/IOException;
        //  622    626    579    585    Any
        //  630    634    637    651    Ljava/io/IOException;
        //  630    634    579    585    Any
        //  634    637    579    585    Any
        //  638    648    579    585    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 395, Size: 395
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
    
    @WorkerThread
    public static void a(final d d, final Context context) {
        if (d == null || context == null) {
            return;
        }
        final Object b = com.facebook.ads.internal.k.e.b;
        // monitorenter(b)
        try {
            while (true) {
                while (true) {
                    try {
                        final String a = com.facebook.ads.internal.w.f.a.a("debuglogs", context);
                        Object o = new File(context.getFilesDir(), a);
                        if (((File)o).exists()) {
                            final int ae = com.facebook.ads.internal.r.a.ae(context);
                            final long length = ((File)o).length();
                            if (ae > 0 && length > ae) {
                                final boolean delete = ((File)o).delete();
                                b(context, 0);
                                com.facebook.ads.internal.k.e.c.clear();
                                com.facebook.ads.internal.k.e.d.clear();
                                if (delete) {
                                    a(new Exception("Purge debug events file. File size: " + length + ", DropCounter: " + com.facebook.ads.internal.k.e.e.getAndIncrement()), context, new HashMap<String, String>());
                                    return;
                                }
                                Log.e("FBAudienceNetwork", "Can't delete debug events file.");
                            }
                        }
                        o = new JSONObject();
                        ((JSONObject)o).put("id", (Object)UUID.randomUUID().toString());
                        ((JSONObject)o).put("type", (Object)d.a());
                        ((JSONObject)o).put("time", (Object)v.a(d.b()));
                        ((JSONObject)o).put("session_time", (Object)v.a(d.c()));
                        ((JSONObject)o).put("session_id", (Object)d.d());
                        if (d.e() != null) {
                            final JSONObject jsonObject = new JSONObject((Map)d.e());
                            ((JSONObject)o).put("data", (Object)jsonObject);
                            ((JSONObject)o).put("attempt", (Object)String.valueOf(0));
                            final FileOutputStream openFileOutput = context.openFileOutput(a, 32768);
                            openFileOutput.write((((JSONObject)o).toString() + "\n").getBytes());
                            openFileOutput.close();
                            b(context, context.getApplicationContext().getSharedPreferences(com.facebook.ads.internal.w.f.a.a("DEBUG_PREF", context), 0).getInt("EventCount", 0) + 1);
                            return;
                        }
                    }
                    finally {
                    }
                    // monitorexit(b)
                    final JSONObject jsonObject = new JSONObject();
                    continue;
                }
            }
        }
        catch (Exception ex) {
            Log.e(com.facebook.ads.internal.k.e.a, "Failed to store crash", (Throwable)ex);
        }
    }
    
    public static void a(final String s) {
        Integer value = com.facebook.ads.internal.k.e.d.get(s);
        if (value == null) {
            value = 0;
        }
        else {
            com.facebook.ads.internal.k.e.d.remove(s);
        }
        com.facebook.ads.internal.k.e.d.put(s, value + 1);
    }
    
    @WorkerThread
    public static void b(final Context context) {
        synchronized (com.facebook.ads.internal.k.e.b) {
            final File file = new File(context.getFilesDir(), com.facebook.ads.internal.w.f.a.a("debuglogs", context));
            if (file.exists()) {
                file.delete();
            }
            b(context, 0);
            com.facebook.ads.internal.k.e.c.clear();
            com.facebook.ads.internal.k.e.d.clear();
        }
    }
    
    private static void b(final Context context, final int n) {
        final SharedPreferences$Editor edit = context.getApplicationContext().getSharedPreferences(com.facebook.ads.internal.w.f.a.a("DEBUG_PREF", context), 0).edit();
        int n2 = n;
        if (n < 0) {
            n2 = 0;
        }
        edit.putInt("EventCount", n2).apply();
    }
    
    public static void b(final String s) {
        if (com.facebook.ads.internal.k.e.d.containsKey(s)) {
            com.facebook.ads.internal.k.e.d.remove(s);
        }
        com.facebook.ads.internal.k.e.c.add(s);
    }
    
    public static int c(final Context context) {
        return context.getApplicationContext().getSharedPreferences(com.facebook.ads.internal.w.f.a.a("DEBUG_PREF", context), 0).getInt("EventCount", 0) - com.facebook.ads.internal.k.e.c.size();
    }
    
    public static boolean c(final String s) {
        return com.facebook.ads.internal.k.e.c.contains(s) || com.facebook.ads.internal.k.e.d.containsKey(s);
    }
    
    @WorkerThread
    public static boolean d(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          11
        //     3: aconst_null    
        //     4: astore_3       
        //     5: aconst_null    
        //     6: astore          6
        //     8: aconst_null    
        //     9: astore          13
        //    11: aconst_null    
        //    12: astore          20
        //    14: aconst_null    
        //    15: astore          7
        //    17: new             Lorg/json/JSONArray;
        //    20: dup            
        //    21: invokespecial   org/json/JSONArray.<init>:()V
        //    24: astore          22
        //    26: getstatic       com/facebook/ads/internal/k/e.b:Ljava/lang/Object;
        //    29: astore          21
        //    31: aload           21
        //    33: monitorenter   
        //    34: new             Ljava/io/File;
        //    37: dup            
        //    38: aload_0        
        //    39: invokevirtual   android/content/Context.getFilesDir:()Ljava/io/File;
        //    42: ldc             "debuglogs"
        //    44: aload_0        
        //    45: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //    48: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //    51: invokevirtual   java/io/File.exists:()Z
        //    54: ifeq            1235
        //    57: aload_0        
        //    58: ldc             "debuglogs"
        //    60: aload_0        
        //    61: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //    64: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    67: astore          5
        //    69: new             Ljava/io/InputStreamReader;
        //    72: dup            
        //    73: aload           5
        //    75: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    78: astore          4
        //    80: new             Ljava/io/BufferedReader;
        //    83: dup            
        //    84: aload           4
        //    86: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    89: astore_3       
        //    90: aload           7
        //    92: astore          11
        //    94: aload_3        
        //    95: astore          16
        //    97: aload           4
        //    99: astore          18
        //   101: aload           5
        //   103: astore          19
        //   105: aload           13
        //   107: astore          6
        //   109: aload_3        
        //   110: astore          10
        //   112: aload           4
        //   114: astore          9
        //   116: aload           5
        //   118: astore          8
        //   120: aload           20
        //   122: astore          12
        //   124: aload_3        
        //   125: astore          17
        //   127: aload           4
        //   129: astore          15
        //   131: aload           5
        //   133: astore          14
        //   135: aload_3        
        //   136: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //   139: astore          23
        //   141: aload           23
        //   143: ifnull          591
        //   146: aload           7
        //   148: astore          11
        //   150: aload_3        
        //   151: astore          16
        //   153: aload           4
        //   155: astore          18
        //   157: aload           5
        //   159: astore          19
        //   161: aload           13
        //   163: astore          6
        //   165: aload_3        
        //   166: astore          10
        //   168: aload           4
        //   170: astore          9
        //   172: aload           5
        //   174: astore          8
        //   176: aload           20
        //   178: astore          12
        //   180: aload_3        
        //   181: astore          17
        //   183: aload           4
        //   185: astore          15
        //   187: aload           5
        //   189: astore          14
        //   191: new             Lorg/json/JSONObject;
        //   194: dup            
        //   195: aload           23
        //   197: invokespecial   org/json/JSONObject.<init>:(Ljava/lang/String;)V
        //   200: astore          23
        //   202: aload           7
        //   204: astore          11
        //   206: aload_3        
        //   207: astore          16
        //   209: aload           4
        //   211: astore          18
        //   213: aload           5
        //   215: astore          19
        //   217: aload           13
        //   219: astore          6
        //   221: aload_3        
        //   222: astore          10
        //   224: aload           4
        //   226: astore          9
        //   228: aload           5
        //   230: astore          8
        //   232: aload           20
        //   234: astore          12
        //   236: aload_3        
        //   237: astore          17
        //   239: aload           4
        //   241: astore          15
        //   243: aload           5
        //   245: astore          14
        //   247: aload           23
        //   249: ldc             "id"
        //   251: invokevirtual   org/json/JSONObject.getString:(Ljava/lang/String;)Ljava/lang/String;
        //   254: astore          24
        //   256: aload           7
        //   258: astore          11
        //   260: aload_3        
        //   261: astore          16
        //   263: aload           4
        //   265: astore          18
        //   267: aload           5
        //   269: astore          19
        //   271: aload           13
        //   273: astore          6
        //   275: aload_3        
        //   276: astore          10
        //   278: aload           4
        //   280: astore          9
        //   282: aload           5
        //   284: astore          8
        //   286: aload           20
        //   288: astore          12
        //   290: aload_3        
        //   291: astore          17
        //   293: aload           4
        //   295: astore          15
        //   297: aload           5
        //   299: astore          14
        //   301: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //   304: aload           24
        //   306: invokeinterface java/util/Set.contains:(Ljava/lang/Object;)Z
        //   311: ifne            90
        //   314: aload           7
        //   316: astore          11
        //   318: aload_3        
        //   319: astore          16
        //   321: aload           4
        //   323: astore          18
        //   325: aload           5
        //   327: astore          19
        //   329: aload           13
        //   331: astore          6
        //   333: aload_3        
        //   334: astore          10
        //   336: aload           4
        //   338: astore          9
        //   340: aload           5
        //   342: astore          8
        //   344: aload           20
        //   346: astore          12
        //   348: aload_3        
        //   349: astore          17
        //   351: aload           4
        //   353: astore          15
        //   355: aload           5
        //   357: astore          14
        //   359: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   362: aload           24
        //   364: invokeinterface java/util/Map.containsKey:(Ljava/lang/Object;)Z
        //   369: ifeq            438
        //   372: aload           7
        //   374: astore          11
        //   376: aload_3        
        //   377: astore          16
        //   379: aload           4
        //   381: astore          18
        //   383: aload           5
        //   385: astore          19
        //   387: aload           13
        //   389: astore          6
        //   391: aload_3        
        //   392: astore          10
        //   394: aload           4
        //   396: astore          9
        //   398: aload           5
        //   400: astore          8
        //   402: aload           20
        //   404: astore          12
        //   406: aload_3        
        //   407: astore          17
        //   409: aload           4
        //   411: astore          15
        //   413: aload           5
        //   415: astore          14
        //   417: aload           23
        //   419: ldc             "attempt"
        //   421: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   424: aload           24
        //   426: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   431: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   434: invokevirtual   org/json/JSONObject.put:(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
        //   437: pop            
        //   438: aload           7
        //   440: astore          11
        //   442: aload_3        
        //   443: astore          16
        //   445: aload           4
        //   447: astore          18
        //   449: aload           5
        //   451: astore          19
        //   453: aload           13
        //   455: astore          6
        //   457: aload_3        
        //   458: astore          10
        //   460: aload           4
        //   462: astore          9
        //   464: aload           5
        //   466: astore          8
        //   468: aload           20
        //   470: astore          12
        //   472: aload_3        
        //   473: astore          17
        //   475: aload           4
        //   477: astore          15
        //   479: aload           5
        //   481: astore          14
        //   483: aload           22
        //   485: aload           23
        //   487: invokevirtual   org/json/JSONArray.put:(Ljava/lang/Object;)Lorg/json/JSONArray;
        //   490: pop            
        //   491: goto            90
        //   494: astore_3       
        //   495: aload           19
        //   497: astore          5
        //   499: aload           18
        //   501: astore          4
        //   503: aload           16
        //   505: astore_0       
        //   506: aload           11
        //   508: astore          6
        //   510: aload_0        
        //   511: astore          10
        //   513: aload           4
        //   515: astore          9
        //   517: aload           5
        //   519: astore          8
        //   521: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //   524: ldc_w           "Failed to rewrite File."
        //   527: aload_3        
        //   528: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   531: pop            
        //   532: aload_0        
        //   533: ifnull          540
        //   536: aload_0        
        //   537: invokevirtual   java/io/BufferedReader.close:()V
        //   540: aload           4
        //   542: ifnull          550
        //   545: aload           4
        //   547: invokevirtual   java/io/InputStreamReader.close:()V
        //   550: aload           5
        //   552: ifnull          560
        //   555: aload           5
        //   557: invokevirtual   java/io/FileInputStream.close:()V
        //   560: aload           11
        //   562: ifnull          570
        //   565: aload           11
        //   567: invokevirtual   java/io/FileOutputStream.close:()V
        //   570: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //   573: invokeinterface java/util/Set.clear:()V
        //   578: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //   581: invokeinterface java/util/Map.clear:()V
        //   586: aload           21
        //   588: monitorexit    
        //   589: iconst_0       
        //   590: ireturn        
        //   591: aload           7
        //   593: astore          11
        //   595: aload_3        
        //   596: astore          16
        //   598: aload           4
        //   600: astore          18
        //   602: aload           5
        //   604: astore          19
        //   606: aload           13
        //   608: astore          6
        //   610: aload_3        
        //   611: astore          10
        //   613: aload           4
        //   615: astore          9
        //   617: aload           5
        //   619: astore          8
        //   621: aload           20
        //   623: astore          12
        //   625: aload_3        
        //   626: astore          17
        //   628: aload           4
        //   630: astore          15
        //   632: aload           5
        //   634: astore          14
        //   636: new             Ljava/lang/StringBuilder;
        //   639: dup            
        //   640: invokespecial   java/lang/StringBuilder.<init>:()V
        //   643: astore          23
        //   645: aload           7
        //   647: astore          11
        //   649: aload_3        
        //   650: astore          16
        //   652: aload           4
        //   654: astore          18
        //   656: aload           5
        //   658: astore          19
        //   660: aload           13
        //   662: astore          6
        //   664: aload_3        
        //   665: astore          10
        //   667: aload           4
        //   669: astore          9
        //   671: aload           5
        //   673: astore          8
        //   675: aload           20
        //   677: astore          12
        //   679: aload_3        
        //   680: astore          17
        //   682: aload           4
        //   684: astore          15
        //   686: aload           5
        //   688: astore          14
        //   690: aload           22
        //   692: invokevirtual   org/json/JSONArray.length:()I
        //   695: istore_2       
        //   696: iconst_0       
        //   697: istore_1       
        //   698: iload_1        
        //   699: iload_2        
        //   700: if_icmpge       775
        //   703: aload           7
        //   705: astore          11
        //   707: aload_3        
        //   708: astore          16
        //   710: aload           4
        //   712: astore          18
        //   714: aload           5
        //   716: astore          19
        //   718: aload           13
        //   720: astore          6
        //   722: aload_3        
        //   723: astore          10
        //   725: aload           4
        //   727: astore          9
        //   729: aload           5
        //   731: astore          8
        //   733: aload           20
        //   735: astore          12
        //   737: aload_3        
        //   738: astore          17
        //   740: aload           4
        //   742: astore          15
        //   744: aload           5
        //   746: astore          14
        //   748: aload           23
        //   750: aload           22
        //   752: iload_1        
        //   753: invokevirtual   org/json/JSONArray.getJSONObject:(I)Lorg/json/JSONObject;
        //   756: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   759: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   762: bipush          10
        //   764: invokevirtual   java/lang/StringBuilder.append:(C)Ljava/lang/StringBuilder;
        //   767: pop            
        //   768: iload_1        
        //   769: iconst_1       
        //   770: iadd           
        //   771: istore_1       
        //   772: goto            698
        //   775: aload           7
        //   777: astore          11
        //   779: aload_3        
        //   780: astore          16
        //   782: aload           4
        //   784: astore          18
        //   786: aload           5
        //   788: astore          19
        //   790: aload           13
        //   792: astore          6
        //   794: aload_3        
        //   795: astore          10
        //   797: aload           4
        //   799: astore          9
        //   801: aload           5
        //   803: astore          8
        //   805: aload           20
        //   807: astore          12
        //   809: aload_3        
        //   810: astore          17
        //   812: aload           4
        //   814: astore          15
        //   816: aload           5
        //   818: astore          14
        //   820: aload_0        
        //   821: ldc             "debuglogs"
        //   823: aload_0        
        //   824: invokestatic    com/facebook/ads/internal/w/f/a.a:(Ljava/lang/String;Landroid/content/Context;)Ljava/lang/String;
        //   827: iconst_0       
        //   828: invokevirtual   android/content/Context.openFileOutput:(Ljava/lang/String;I)Ljava/io/FileOutputStream;
        //   831: astore          13
        //   833: aload           13
        //   835: astore          11
        //   837: aload_3        
        //   838: astore          16
        //   840: aload           4
        //   842: astore          18
        //   844: aload           5
        //   846: astore          19
        //   848: aload           13
        //   850: astore          6
        //   852: aload_3        
        //   853: astore          10
        //   855: aload           4
        //   857: astore          9
        //   859: aload           5
        //   861: astore          8
        //   863: aload           13
        //   865: astore          12
        //   867: aload_3        
        //   868: astore          17
        //   870: aload           4
        //   872: astore          15
        //   874: aload           5
        //   876: astore          14
        //   878: aload           13
        //   880: aload           23
        //   882: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   885: invokevirtual   java/lang/String.getBytes:()[B
        //   888: invokevirtual   java/io/FileOutputStream.write:([B)V
        //   891: aload           5
        //   893: astore          7
        //   895: aload           4
        //   897: astore          5
        //   899: aload_3        
        //   900: astore          4
        //   902: aload           13
        //   904: astore_3       
        //   905: aload_3        
        //   906: astore          11
        //   908: aload           4
        //   910: astore          16
        //   912: aload           5
        //   914: astore          18
        //   916: aload           7
        //   918: astore          19
        //   920: aload_3        
        //   921: astore          6
        //   923: aload           4
        //   925: astore          10
        //   927: aload           5
        //   929: astore          9
        //   931: aload           7
        //   933: astore          8
        //   935: aload_3        
        //   936: astore          12
        //   938: aload           4
        //   940: astore          17
        //   942: aload           5
        //   944: astore          15
        //   946: aload           7
        //   948: astore          14
        //   950: aload_0        
        //   951: aload_0        
        //   952: invokestatic    com/facebook/ads/internal/k/e.c:(Landroid/content/Context;)I
        //   955: invokestatic    com/facebook/ads/internal/k/e.b:(Landroid/content/Context;I)V
        //   958: aload           4
        //   960: ifnull          968
        //   963: aload           4
        //   965: invokevirtual   java/io/BufferedReader.close:()V
        //   968: aload           5
        //   970: ifnull          978
        //   973: aload           5
        //   975: invokevirtual   java/io/InputStreamReader.close:()V
        //   978: aload           7
        //   980: ifnull          988
        //   983: aload           7
        //   985: invokevirtual   java/io/FileInputStream.close:()V
        //   988: aload_3        
        //   989: ifnull          996
        //   992: aload_3        
        //   993: invokevirtual   java/io/FileOutputStream.close:()V
        //   996: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //   999: invokeinterface java/util/Set.clear:()V
        //  1004: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //  1007: invokeinterface java/util/Map.clear:()V
        //  1012: aload           21
        //  1014: monitorexit    
        //  1015: iconst_1       
        //  1016: ireturn        
        //  1017: astore_0       
        //  1018: aload           21
        //  1020: monitorexit    
        //  1021: aload_0        
        //  1022: athrow         
        //  1023: astore_0       
        //  1024: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //  1027: ldc             "Failed to close buffers"
        //  1029: aload_0        
        //  1030: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1033: pop            
        //  1034: goto            996
        //  1037: astore_0       
        //  1038: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //  1041: ldc             "Failed to close buffers"
        //  1043: aload_0        
        //  1044: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1047: pop            
        //  1048: goto            570
        //  1051: astore_0       
        //  1052: aconst_null    
        //  1053: astore          10
        //  1055: aconst_null    
        //  1056: astore          4
        //  1058: aconst_null    
        //  1059: astore          5
        //  1061: aload           10
        //  1063: ifnull          1071
        //  1066: aload           10
        //  1068: invokevirtual   java/io/BufferedReader.close:()V
        //  1071: aload           4
        //  1073: ifnull          1081
        //  1076: aload           4
        //  1078: invokevirtual   java/io/InputStreamReader.close:()V
        //  1081: aload           5
        //  1083: ifnull          1091
        //  1086: aload           5
        //  1088: invokevirtual   java/io/FileInputStream.close:()V
        //  1091: aload           6
        //  1093: ifnull          1101
        //  1096: aload           6
        //  1098: invokevirtual   java/io/FileOutputStream.close:()V
        //  1101: getstatic       com/facebook/ads/internal/k/e.c:Ljava/util/Set;
        //  1104: invokeinterface java/util/Set.clear:()V
        //  1109: getstatic       com/facebook/ads/internal/k/e.d:Ljava/util/Map;
        //  1112: invokeinterface java/util/Map.clear:()V
        //  1117: aload_0        
        //  1118: athrow         
        //  1119: astore_3       
        //  1120: getstatic       com/facebook/ads/internal/k/e.a:Ljava/lang/String;
        //  1123: ldc             "Failed to close buffers"
        //  1125: aload_3        
        //  1126: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //  1129: pop            
        //  1130: goto            1101
        //  1133: astore_0       
        //  1134: aconst_null    
        //  1135: astore          10
        //  1137: aconst_null    
        //  1138: astore          4
        //  1140: goto            1061
        //  1143: astore_0       
        //  1144: aconst_null    
        //  1145: astore          10
        //  1147: goto            1061
        //  1150: astore_0       
        //  1151: aload           9
        //  1153: astore          4
        //  1155: aload           8
        //  1157: astore          5
        //  1159: goto            1061
        //  1162: astore_3       
        //  1163: aconst_null    
        //  1164: astore_0       
        //  1165: aconst_null    
        //  1166: astore          4
        //  1168: aconst_null    
        //  1169: astore          5
        //  1171: goto            506
        //  1174: astore_3       
        //  1175: aconst_null    
        //  1176: astore_0       
        //  1177: aconst_null    
        //  1178: astore          4
        //  1180: goto            506
        //  1183: astore_3       
        //  1184: aconst_null    
        //  1185: astore_0       
        //  1186: goto            506
        //  1189: astore_3       
        //  1190: aload           12
        //  1192: astore          11
        //  1194: aload           17
        //  1196: astore_0       
        //  1197: aload           15
        //  1199: astore          4
        //  1201: aload           14
        //  1203: astore          5
        //  1205: goto            506
        //  1208: astore_3       
        //  1209: aconst_null    
        //  1210: astore_0       
        //  1211: aconst_null    
        //  1212: astore          4
        //  1214: aconst_null    
        //  1215: astore          5
        //  1217: goto            506
        //  1220: astore_3       
        //  1221: aconst_null    
        //  1222: astore_0       
        //  1223: aconst_null    
        //  1224: astore          4
        //  1226: goto            506
        //  1229: astore_3       
        //  1230: aconst_null    
        //  1231: astore_0       
        //  1232: goto            506
        //  1235: aconst_null    
        //  1236: astore          4
        //  1238: aconst_null    
        //  1239: astore          5
        //  1241: aconst_null    
        //  1242: astore          7
        //  1244: goto            905
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                    
        //  -----  -----  -----  -----  ------------------------
        //  34     69     1208   1220   Ljava/io/IOException;
        //  34     69     1162   1174   Lorg/json/JSONException;
        //  34     69     1051   1061   Any
        //  69     80     1220   1229   Ljava/io/IOException;
        //  69     80     1174   1183   Lorg/json/JSONException;
        //  69     80     1133   1143   Any
        //  80     90     1229   1235   Ljava/io/IOException;
        //  80     90     1183   1189   Lorg/json/JSONException;
        //  80     90     1143   1150   Any
        //  135    141    494    506    Ljava/io/IOException;
        //  135    141    1189   1208   Lorg/json/JSONException;
        //  135    141    1150   1162   Any
        //  191    202    494    506    Ljava/io/IOException;
        //  191    202    1189   1208   Lorg/json/JSONException;
        //  191    202    1150   1162   Any
        //  247    256    494    506    Ljava/io/IOException;
        //  247    256    1189   1208   Lorg/json/JSONException;
        //  247    256    1150   1162   Any
        //  301    314    494    506    Ljava/io/IOException;
        //  301    314    1189   1208   Lorg/json/JSONException;
        //  301    314    1150   1162   Any
        //  359    372    494    506    Ljava/io/IOException;
        //  359    372    1189   1208   Lorg/json/JSONException;
        //  359    372    1150   1162   Any
        //  417    438    494    506    Ljava/io/IOException;
        //  417    438    1189   1208   Lorg/json/JSONException;
        //  417    438    1150   1162   Any
        //  483    491    494    506    Ljava/io/IOException;
        //  483    491    1189   1208   Lorg/json/JSONException;
        //  483    491    1150   1162   Any
        //  521    532    1150   1162   Any
        //  536    540    1037   1051   Ljava/io/IOException;
        //  536    540    1017   1023   Any
        //  545    550    1037   1051   Ljava/io/IOException;
        //  545    550    1017   1023   Any
        //  555    560    1037   1051   Ljava/io/IOException;
        //  555    560    1017   1023   Any
        //  565    570    1037   1051   Ljava/io/IOException;
        //  565    570    1017   1023   Any
        //  570    589    1017   1023   Any
        //  636    645    494    506    Ljava/io/IOException;
        //  636    645    1189   1208   Lorg/json/JSONException;
        //  636    645    1150   1162   Any
        //  690    696    494    506    Ljava/io/IOException;
        //  690    696    1189   1208   Lorg/json/JSONException;
        //  690    696    1150   1162   Any
        //  748    768    494    506    Ljava/io/IOException;
        //  748    768    1189   1208   Lorg/json/JSONException;
        //  748    768    1150   1162   Any
        //  820    833    494    506    Ljava/io/IOException;
        //  820    833    1189   1208   Lorg/json/JSONException;
        //  820    833    1150   1162   Any
        //  878    891    494    506    Ljava/io/IOException;
        //  878    891    1189   1208   Lorg/json/JSONException;
        //  878    891    1150   1162   Any
        //  950    958    494    506    Ljava/io/IOException;
        //  950    958    1189   1208   Lorg/json/JSONException;
        //  950    958    1150   1162   Any
        //  963    968    1023   1037   Ljava/io/IOException;
        //  963    968    1017   1023   Any
        //  973    978    1023   1037   Ljava/io/IOException;
        //  973    978    1017   1023   Any
        //  983    988    1023   1037   Ljava/io/IOException;
        //  983    988    1017   1023   Any
        //  992    996    1023   1037   Ljava/io/IOException;
        //  992    996    1017   1023   Any
        //  996    1015   1017   1023   Any
        //  1018   1021   1017   1023   Any
        //  1024   1034   1017   1023   Any
        //  1038   1048   1017   1023   Any
        //  1066   1071   1119   1133   Ljava/io/IOException;
        //  1066   1071   1017   1023   Any
        //  1076   1081   1119   1133   Ljava/io/IOException;
        //  1076   1081   1017   1023   Any
        //  1086   1091   1119   1133   Ljava/io/IOException;
        //  1086   1091   1017   1023   Any
        //  1096   1101   1119   1133   Ljava/io/IOException;
        //  1096   1101   1017   1023   Any
        //  1101   1119   1017   1023   Any
        //  1120   1130   1017   1023   Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 635, Size: 635
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
}
