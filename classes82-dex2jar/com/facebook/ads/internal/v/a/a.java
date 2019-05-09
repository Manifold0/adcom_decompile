// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.v.a;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import android.support.annotation.AnyThread;
import java.util.TreeMap;
import java.net.CookieManager;
import java.net.CookieHandler;
import android.os.Build$VERSION;
import java.util.Set;
import java.util.Map;
import android.support.annotation.WorkerThread;

@WorkerThread
public class a
{
    private static int[] f;
    private static final String g;
    private static a j;
    protected final q a;
    protected final d b;
    protected r c;
    protected int d;
    protected int e;
    private int h;
    private Map<String, String> i;
    private boolean k;
    private Set<String> l;
    private Set<String> m;
    
    static {
        a.f = new int[20];
        g = a.class.getSimpleName();
        if (Build$VERSION.SDK_INT < 8) {
            System.setProperty("http.keepAlive", "false");
        }
        if (Build$VERSION.SDK_INT > 8 && CookieHandler.getDefault() == null) {
            CookieHandler.setDefault(new CookieManager());
        }
    }
    
    @AnyThread
    public a() {
        this.b = new e();
        this.c = new g();
        this.d = 2000;
        this.e = 8000;
        this.h = 3;
        this.i = new TreeMap<String, String>();
        this.a = new f() {};
    }
    
    public static void a(final a j) {
        synchronized (a.class) {
            a.j = j;
        }
    }
    
    protected int a(final int n) {
        return com.facebook.ads.internal.v.a.a.f[n + 2] * 1000;
    }
    
    protected int a(final HttpURLConnection p0, final byte[] p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          4
        //     3: aload_0        
        //     4: getfield        com/facebook/ads/internal/v/a/a.a:Lcom/facebook/ads/internal/v/a/q;
        //     7: aload_1        
        //     8: invokeinterface com/facebook/ads/internal/v/a/q.a:(Ljava/net/HttpURLConnection;)Ljava/io/OutputStream;
        //    13: astore          5
        //    15: aload           5
        //    17: ifnull          36
        //    20: aload           5
        //    22: astore          4
        //    24: aload_0        
        //    25: getfield        com/facebook/ads/internal/v/a/a.a:Lcom/facebook/ads/internal/v/a/q;
        //    28: aload           5
        //    30: aload_2        
        //    31: invokeinterface com/facebook/ads/internal/v/a/q.a:(Ljava/io/OutputStream;[B)V
        //    36: aload           5
        //    38: astore          4
        //    40: aload_1        
        //    41: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //    44: istore_3       
        //    45: aload           5
        //    47: ifnull          55
        //    50: aload           5
        //    52: invokevirtual   java/io/OutputStream.close:()V
        //    55: iload_3        
        //    56: ireturn        
        //    57: astore_1       
        //    58: aload           4
        //    60: ifnull          68
        //    63: aload           4
        //    65: invokevirtual   java/io/OutputStream.close:()V
        //    68: aload_1        
        //    69: athrow         
        //    70: astore_1       
        //    71: iload_3        
        //    72: ireturn        
        //    73: astore_2       
        //    74: goto            68
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      15     57     70     Any
        //  24     36     57     70     Any
        //  40     45     57     70     Any
        //  50     55     70     73     Ljava/lang/Exception;
        //  63     68     73     77     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 39, Size: 39
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
    
    @AnyThread
    public a a(final String s, final String s2) {
        this.i.put(s, s2);
        return this;
    }
    
    public n a(final l l) {
        int i = 0;
        long currentTimeMillis = System.currentTimeMillis();
        while (i < this.h) {
            long currentTimeMillis2 = currentTimeMillis;
            Label_0160: {
                try {
                    this.c(this.a(i));
                    currentTimeMillis2 = currentTimeMillis;
                    if (this.c.a()) {
                        currentTimeMillis2 = currentTimeMillis;
                        this.c.a(i + 1 + "of" + this.h + ", trying " + l.a());
                    }
                    currentTimeMillis2 = currentTimeMillis;
                    final long n = currentTimeMillis2 = System.currentTimeMillis();
                    final n a = this.a(l.a(), l.b(), l.c(), l.d());
                    currentTimeMillis2 = n;
                    if (a != null) {
                        return a;
                    }
                    break Label_0160;
                }
                catch (m m) {
                    if (!this.a(m, currentTimeMillis2) || i >= this.h - 1) {
                        if (this.a.a(m) && i < this.h - 1) {
                            try {
                                Thread.sleep(this.d);
                                break Label_0160;
                            }
                            catch (InterruptedException ex) {
                                throw m;
                            }
                        }
                        throw m;
                    }
                    ++i;
                    currentTimeMillis = currentTimeMillis2;
                    continue;
                }
            }
            break;
        }
        return null;
    }
    
    protected n a(final String p0, final j p1, final String p2, final byte[] p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          9
        //     3: aload_0        
        //     4: iconst_0       
        //     5: putfield        com/facebook/ads/internal/v/a/a.k:Z
        //     8: aload_0        
        //     9: aload_1        
        //    10: invokevirtual   com/facebook/ads/internal/v/a/a.a:(Ljava/lang/String;)Ljava/net/HttpURLConnection;
        //    13: astore_1       
        //    14: aload_1        
        //    15: astore          8
        //    17: aload_0        
        //    18: aload_1        
        //    19: aload_2        
        //    20: aload_3        
        //    21: invokevirtual   com/facebook/ads/internal/v/a/a.a:(Ljava/net/HttpURLConnection;Lcom/facebook/ads/internal/v/a/j;Ljava/lang/String;)V
        //    24: aload_1        
        //    25: astore          8
        //    27: aload_0        
        //    28: getfield        com/facebook/ads/internal/v/a/a.i:Ljava/util/Map;
        //    31: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    36: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    41: astore_2       
        //    42: aload_1        
        //    43: astore          8
        //    45: aload_2        
        //    46: invokeinterface java/util/Iterator.hasNext:()Z
        //    51: ifeq            149
        //    54: aload_1        
        //    55: astore          8
        //    57: aload_2        
        //    58: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    63: checkcast       Ljava/lang/String;
        //    66: astore_3       
        //    67: aload_1        
        //    68: astore          8
        //    70: aload_1        
        //    71: aload_3        
        //    72: aload_0        
        //    73: getfield        com/facebook/ads/internal/v/a/a.i:Ljava/util/Map;
        //    76: aload_3        
        //    77: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    82: checkcast       Ljava/lang/String;
        //    85: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //    88: goto            42
        //    91: astore_3       
        //    92: aload_0        
        //    93: aload_1        
        //    94: invokevirtual   com/facebook/ads/internal/v/a/a.b:(Ljava/net/HttpURLConnection;)Lcom/facebook/ads/internal/v/a/n;
        //    97: astore_2       
        //    98: aload_2        
        //    99: ifnull          541
        //   102: aload_2        
        //   103: invokevirtual   com/facebook/ads/internal/v/a/n.a:()I
        //   106: istore          5
        //   108: iload           5
        //   110: ifle            541
        //   113: aload_0        
        //   114: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   117: invokeinterface com/facebook/ads/internal/v/a/r.a:()Z
        //   122: ifeq            135
        //   125: aload_0        
        //   126: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   129: aload_2        
        //   130: invokeinterface com/facebook/ads/internal/v/a/r.a:(Lcom/facebook/ads/internal/v/a/n;)V
        //   135: aload_2        
        //   136: astore_3       
        //   137: aload_1        
        //   138: ifnull          147
        //   141: aload_1        
        //   142: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   145: aload_2        
        //   146: astore_3       
        //   147: aload_3        
        //   148: areturn        
        //   149: aload_1        
        //   150: astore          8
        //   152: ldc             Lcom/facebook/ads/internal/v/a/a;.class
        //   154: monitorenter   
        //   155: getstatic       com/facebook/ads/internal/v/a/a.j:Lcom/facebook/ads/internal/v/a/a$a;
        //   158: ifnull          270
        //   161: getstatic       com/facebook/ads/internal/v/a/a.j:Lcom/facebook/ads/internal/v/a/a$a;
        //   164: invokeinterface com/facebook/ads/internal/v/a/a$a.a:()Ljava/util/Map;
        //   169: astore_3       
        //   170: aload_3        
        //   171: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //   176: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   181: astore          8
        //   183: aload           8
        //   185: invokeinterface java/util/Iterator.hasNext:()Z
        //   190: ifeq            270
        //   193: aload           8
        //   195: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   200: checkcast       Ljava/lang/String;
        //   203: astore_2       
        //   204: aload_1        
        //   205: aload_2        
        //   206: aload_3        
        //   207: aload_2        
        //   208: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   213: checkcast       Ljava/lang/String;
        //   216: invokevirtual   java/net/HttpURLConnection.setRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   219: goto            183
        //   222: astore_2       
        //   223: ldc             Lcom/facebook/ads/internal/v/a/a;.class
        //   225: monitorexit    
        //   226: aload_1        
        //   227: astore          8
        //   229: aload_2        
        //   230: athrow         
        //   231: astore_2       
        //   232: aload           9
        //   234: astore_3       
        //   235: aload           8
        //   237: astore_1       
        //   238: aload_0        
        //   239: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   242: invokeinterface com/facebook/ads/internal/v/a/r.a:()Z
        //   247: ifeq            260
        //   250: aload_0        
        //   251: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   254: aload_3        
        //   255: invokeinterface com/facebook/ads/internal/v/a/r.a:(Lcom/facebook/ads/internal/v/a/n;)V
        //   260: aload_1        
        //   261: ifnull          268
        //   264: aload_1        
        //   265: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   268: aload_2        
        //   269: athrow         
        //   270: ldc             Lcom/facebook/ads/internal/v/a/a;.class
        //   272: monitorexit    
        //   273: aload_1        
        //   274: astore          8
        //   276: aload_0        
        //   277: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   280: invokeinterface com/facebook/ads/internal/v/a/r.a:()Z
        //   285: ifeq            303
        //   288: aload_1        
        //   289: astore          8
        //   291: aload_0        
        //   292: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   295: aload_1        
        //   296: aload           4
        //   298: invokeinterface com/facebook/ads/internal/v/a/r.a:(Ljava/net/HttpURLConnection;Ljava/lang/Object;)V
        //   303: aload_1        
        //   304: astore          8
        //   306: aload_1        
        //   307: invokevirtual   java/net/HttpURLConnection.connect:()V
        //   310: aload_1        
        //   311: astore          8
        //   313: aload_0        
        //   314: iconst_1       
        //   315: putfield        com/facebook/ads/internal/v/a/a.k:Z
        //   318: aload_1        
        //   319: astore          8
        //   321: aload_0        
        //   322: getfield        com/facebook/ads/internal/v/a/a.m:Ljava/util/Set;
        //   325: ifnull          495
        //   328: aload_1        
        //   329: astore          8
        //   331: aload_0        
        //   332: getfield        com/facebook/ads/internal/v/a/a.m:Ljava/util/Set;
        //   335: invokeinterface java/util/Set.isEmpty:()Z
        //   340: ifne            495
        //   343: iconst_1       
        //   344: istore          5
        //   346: aload_1        
        //   347: astore          8
        //   349: aload_0        
        //   350: getfield        com/facebook/ads/internal/v/a/a.l:Ljava/util/Set;
        //   353: ifnull          501
        //   356: aload_1        
        //   357: astore          8
        //   359: aload_0        
        //   360: getfield        com/facebook/ads/internal/v/a/a.l:Ljava/util/Set;
        //   363: invokeinterface java/util/Set.isEmpty:()Z
        //   368: ifne            501
        //   371: iconst_1       
        //   372: istore          6
        //   374: aload_1        
        //   375: astore          8
        //   377: aload_1        
        //   378: instanceof      Ljavax/net/ssl/HttpsURLConnection;
        //   381: istore          7
        //   383: iload           7
        //   385: ifeq            416
        //   388: iload           5
        //   390: ifne            398
        //   393: iload           6
        //   395: ifeq            416
        //   398: aload_1        
        //   399: astore          8
        //   401: aload_1        
        //   402: checkcast       Ljavax/net/ssl/HttpsURLConnection;
        //   405: aload_0        
        //   406: getfield        com/facebook/ads/internal/v/a/a.m:Ljava/util/Set;
        //   409: aload_0        
        //   410: getfield        com/facebook/ads/internal/v/a/a.l:Ljava/util/Set;
        //   413: invokestatic    com/facebook/ads/internal/v/a/o.a:(Ljavax/net/ssl/HttpsURLConnection;Ljava/util/Set;Ljava/util/Set;)V
        //   416: aload_1        
        //   417: astore          8
        //   419: aload_1        
        //   420: invokevirtual   java/net/HttpURLConnection.getDoOutput:()Z
        //   423: ifeq            442
        //   426: aload           4
        //   428: ifnull          442
        //   431: aload_1        
        //   432: astore          8
        //   434: aload_0        
        //   435: aload_1        
        //   436: aload           4
        //   438: invokevirtual   com/facebook/ads/internal/v/a/a.a:(Ljava/net/HttpURLConnection;[B)I
        //   441: pop            
        //   442: aload_1        
        //   443: astore          8
        //   445: aload_1        
        //   446: invokevirtual   java/net/HttpURLConnection.getDoInput:()Z
        //   449: ifeq            525
        //   452: aload_1        
        //   453: astore          8
        //   455: aload_0        
        //   456: aload_1        
        //   457: invokevirtual   com/facebook/ads/internal/v/a/a.a:(Ljava/net/HttpURLConnection;)Lcom/facebook/ads/internal/v/a/n;
        //   460: astore_2       
        //   461: aload_0        
        //   462: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   465: invokeinterface com/facebook/ads/internal/v/a/r.a:()Z
        //   470: ifeq            483
        //   473: aload_0        
        //   474: getfield        com/facebook/ads/internal/v/a/a.c:Lcom/facebook/ads/internal/v/a/r;
        //   477: aload_2        
        //   478: invokeinterface com/facebook/ads/internal/v/a/r.a:(Lcom/facebook/ads/internal/v/a/n;)V
        //   483: aload_2        
        //   484: astore_3       
        //   485: aload_1        
        //   486: ifnull          147
        //   489: aload_1        
        //   490: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   493: aload_2        
        //   494: areturn        
        //   495: iconst_0       
        //   496: istore          5
        //   498: goto            346
        //   501: iconst_0       
        //   502: istore          6
        //   504: goto            374
        //   507: astore_2       
        //   508: aload_1        
        //   509: astore          8
        //   511: getstatic       com/facebook/ads/internal/v/a/a.g:Ljava/lang/String;
        //   514: ldc_w           "Unable to validate SSL certificates."
        //   517: aload_2        
        //   518: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   521: pop            
        //   522: goto            416
        //   525: aload_1        
        //   526: astore          8
        //   528: new             Lcom/facebook/ads/internal/v/a/n;
        //   531: dup            
        //   532: aload_1        
        //   533: aconst_null    
        //   534: invokespecial   com/facebook/ads/internal/v/a/n.<init>:(Ljava/net/HttpURLConnection;[B)V
        //   537: astore_2       
        //   538: goto            461
        //   541: new             Lcom/facebook/ads/internal/v/a/m;
        //   544: dup            
        //   545: aload_3        
        //   546: aload_2        
        //   547: invokespecial   com/facebook/ads/internal/v/a/m.<init>:(Ljava/lang/Exception;Lcom/facebook/ads/internal/v/a/n;)V
        //   550: athrow         
        //   551: astore          4
        //   553: aload_2        
        //   554: astore_3       
        //   555: aload           4
        //   557: astore_2       
        //   558: goto            238
        //   561: astore_2       
        //   562: aload_3        
        //   563: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   566: iconst_0       
        //   567: ifeq            581
        //   570: aload_1        
        //   571: astore          8
        //   573: new             Ljava/lang/NullPointerException;
        //   576: dup            
        //   577: invokespecial   java/lang/NullPointerException.<init>:()V
        //   580: athrow         
        //   581: aload_1        
        //   582: astore          8
        //   584: new             Lcom/facebook/ads/internal/v/a/m;
        //   587: dup            
        //   588: aload_3        
        //   589: aconst_null    
        //   590: invokespecial   com/facebook/ads/internal/v/a/m.<init>:(Ljava/lang/Exception;Lcom/facebook/ads/internal/v/a/n;)V
        //   593: athrow         
        //   594: astore_2       
        //   595: iconst_0       
        //   596: ifeq            610
        //   599: aload_1        
        //   600: astore          8
        //   602: new             Ljava/lang/NullPointerException;
        //   605: dup            
        //   606: invokespecial   java/lang/NullPointerException.<init>:()V
        //   609: athrow         
        //   610: aload_1        
        //   611: astore          8
        //   613: new             Lcom/facebook/ads/internal/v/a/m;
        //   616: dup            
        //   617: aload_3        
        //   618: aconst_null    
        //   619: invokespecial   com/facebook/ads/internal/v/a/m.<init>:(Ljava/lang/Exception;Lcom/facebook/ads/internal/v/a/n;)V
        //   622: athrow         
        //   623: astore_2       
        //   624: aconst_null    
        //   625: astore_1       
        //   626: aload           9
        //   628: astore_3       
        //   629: goto            238
        //   632: astore_3       
        //   633: aconst_null    
        //   634: astore_1       
        //   635: goto            92
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      14     632    638    Ljava/lang/Exception;
        //  3      14     623    632    Any
        //  17     24     91     92     Ljava/lang/Exception;
        //  17     24     231    238    Any
        //  27     42     91     92     Ljava/lang/Exception;
        //  27     42     231    238    Any
        //  45     54     91     92     Ljava/lang/Exception;
        //  45     54     231    238    Any
        //  57     67     91     92     Ljava/lang/Exception;
        //  57     67     231    238    Any
        //  70     88     91     92     Ljava/lang/Exception;
        //  70     88     231    238    Any
        //  92     98     561    594    Ljava/lang/Exception;
        //  92     98     594    623    Any
        //  102    108    551    561    Any
        //  152    155    91     92     Ljava/lang/Exception;
        //  152    155    231    238    Any
        //  155    183    222    231    Any
        //  183    219    222    231    Any
        //  223    226    222    231    Any
        //  229    231    91     92     Ljava/lang/Exception;
        //  229    231    231    238    Any
        //  270    273    222    231    Any
        //  276    288    91     92     Ljava/lang/Exception;
        //  276    288    231    238    Any
        //  291    303    91     92     Ljava/lang/Exception;
        //  291    303    231    238    Any
        //  306    310    91     92     Ljava/lang/Exception;
        //  306    310    231    238    Any
        //  313    318    91     92     Ljava/lang/Exception;
        //  313    318    231    238    Any
        //  321    328    91     92     Ljava/lang/Exception;
        //  321    328    231    238    Any
        //  331    343    91     92     Ljava/lang/Exception;
        //  331    343    231    238    Any
        //  349    356    91     92     Ljava/lang/Exception;
        //  349    356    231    238    Any
        //  359    371    91     92     Ljava/lang/Exception;
        //  359    371    231    238    Any
        //  377    383    91     92     Ljava/lang/Exception;
        //  377    383    231    238    Any
        //  401    416    507    525    Ljava/lang/Exception;
        //  401    416    231    238    Any
        //  419    426    91     92     Ljava/lang/Exception;
        //  419    426    231    238    Any
        //  434    442    91     92     Ljava/lang/Exception;
        //  434    442    231    238    Any
        //  445    452    91     92     Ljava/lang/Exception;
        //  445    452    231    238    Any
        //  455    461    91     92     Ljava/lang/Exception;
        //  455    461    231    238    Any
        //  511    522    91     92     Ljava/lang/Exception;
        //  511    522    231    238    Any
        //  528    538    91     92     Ljava/lang/Exception;
        //  528    538    231    238    Any
        //  541    551    551    561    Any
        //  562    566    594    623    Any
        //  573    581    231    238    Any
        //  584    594    231    238    Any
        //  602    610    231    238    Any
        //  613    623    231    238    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0092:
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
    
    public n a(final String s, final p p2) {
        return this.b(new i(s, p2));
    }
    
    public n a(final String s, final String s2, final byte[] array) {
        return this.b(new k(s, null, s2, array));
    }
    
    protected n a(final HttpURLConnection p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aload_0        
        //     3: getfield        com/facebook/ads/internal/v/a/a.a:Lcom/facebook/ads/internal/v/a/q;
        //     6: aload_1        
        //     7: invokeinterface com/facebook/ads/internal/v/a/q.b:(Ljava/net/HttpURLConnection;)Ljava/io/InputStream;
        //    12: astore_2       
        //    13: aload_2        
        //    14: ifnull          28
        //    17: aload_0        
        //    18: getfield        com/facebook/ads/internal/v/a/a.a:Lcom/facebook/ads/internal/v/a/q;
        //    21: aload_2        
        //    22: invokeinterface com/facebook/ads/internal/v/a/q.a:(Ljava/io/InputStream;)[B
        //    27: astore_3       
        //    28: new             Lcom/facebook/ads/internal/v/a/n;
        //    31: dup            
        //    32: aload_1        
        //    33: aload_3        
        //    34: invokespecial   com/facebook/ads/internal/v/a/n.<init>:(Ljava/net/HttpURLConnection;[B)V
        //    37: astore_1       
        //    38: aload_2        
        //    39: ifnull          46
        //    42: aload_2        
        //    43: invokevirtual   java/io/InputStream.close:()V
        //    46: aload_1        
        //    47: areturn        
        //    48: astore_1       
        //    49: aconst_null    
        //    50: astore_2       
        //    51: aload_2        
        //    52: ifnull          59
        //    55: aload_2        
        //    56: invokevirtual   java/io/InputStream.close:()V
        //    59: aload_1        
        //    60: athrow         
        //    61: astore_2       
        //    62: aload_1        
        //    63: areturn        
        //    64: astore_2       
        //    65: goto            59
        //    68: astore_1       
        //    69: goto            51
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      13     48     51     Any
        //  17     28     68     72     Any
        //  28     38     68     72     Any
        //  42     46     61     64     Ljava/lang/Exception;
        //  55     59     64     68     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0028:
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
    
    public p a() {
        return new p();
    }
    
    protected HttpURLConnection a(final String s) {
        try {
            new URL(s);
            return this.a.a(s);
        }
        catch (MalformedURLException ex) {
            throw new IllegalArgumentException(s + " is not a valid URL", ex);
        }
    }
    
    protected void a(final l l, final b b) {
        this.b.a(this, b).a(l);
    }
    
    public void a(final String s, final p p3, final b b) {
        this.a(new i(s, p3), b);
    }
    
    protected void a(final HttpURLConnection httpURLConnection, final j j, final String s) {
        httpURLConnection.setConnectTimeout(this.d);
        httpURLConnection.setReadTimeout(this.e);
        this.a.a(httpURLConnection, j, s);
    }
    
    @AnyThread
    public void a(final Set<String> m) {
        this.m = m;
    }
    
    protected boolean a(final Throwable t, long n) {
        n = System.currentTimeMillis() - n + 10L;
        if (this.c.a()) {
            this.c.a("ELAPSED TIME = " + n + ", CT = " + this.d + ", RT = " + this.e);
        }
        if (this.k) {
            if (n < this.e) {
                return false;
            }
        }
        else if (n < this.d) {
            return false;
        }
        return true;
    }
    
    public n b(final l l) {
        try {
            return this.a(l.a(), l.b(), l.c(), l.d());
        }
        catch (m m) {
            this.a.a(m);
            return null;
        }
        catch (Exception ex) {
            this.a.a(new m(ex, null));
            return null;
        }
    }
    
    public n b(final String s, final p p2) {
        return this.b(new k(s, p2));
    }
    
    protected n b(final HttpURLConnection p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aload_1        
        //     3: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //     6: astore_2       
        //     7: aload_2        
        //     8: ifnull          22
        //    11: aload_0        
        //    12: getfield        com/facebook/ads/internal/v/a/a.a:Lcom/facebook/ads/internal/v/a/q;
        //    15: aload_2        
        //    16: invokeinterface com/facebook/ads/internal/v/a/q.a:(Ljava/io/InputStream;)[B
        //    21: astore_3       
        //    22: new             Lcom/facebook/ads/internal/v/a/n;
        //    25: dup            
        //    26: aload_1        
        //    27: aload_3        
        //    28: invokespecial   com/facebook/ads/internal/v/a/n.<init>:(Ljava/net/HttpURLConnection;[B)V
        //    31: astore_1       
        //    32: aload_2        
        //    33: ifnull          40
        //    36: aload_2        
        //    37: invokevirtual   java/io/InputStream.close:()V
        //    40: aload_1        
        //    41: areturn        
        //    42: astore_1       
        //    43: aconst_null    
        //    44: astore_2       
        //    45: aload_2        
        //    46: ifnull          53
        //    49: aload_2        
        //    50: invokevirtual   java/io/InputStream.close:()V
        //    53: aload_1        
        //    54: athrow         
        //    55: astore_2       
        //    56: aload_1        
        //    57: areturn        
        //    58: astore_2       
        //    59: goto            53
        //    62: astore_1       
        //    63: goto            45
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  2      7      42     45     Any
        //  11     22     62     66     Any
        //  22     32     62     66     Any
        //  36     40     55     58     Ljava/lang/Exception;
        //  49     53     58     62     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
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
    
    public void b() {
        this.i.clear();
    }
    
    @AnyThread
    public void b(final int h) {
        if (h < 1 || h > 18) {
            throw new IllegalArgumentException("Maximum retries must be between 1 and 18");
        }
        this.h = h;
    }
    
    public void b(final String s, final p p3, final b b) {
        this.a(new k(s, p3), b);
    }
    
    @AnyThread
    public void b(final Set<String> l) {
        this.l = l;
    }
    
    @AnyThread
    public void c(final int d) {
        this.d = d;
    }
    
    @AnyThread
    public void d(final int e) {
        this.e = e;
    }
    
    public interface a
    {
        @WorkerThread
        Map<String, String> a();
    }
}
