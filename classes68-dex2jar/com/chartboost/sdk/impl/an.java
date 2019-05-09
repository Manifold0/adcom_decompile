// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.Model.CBError;
import com.chartboost.sdk.Tracking.a;
import java.io.IOException;
import java.net.SocketTimeoutException;
import android.os.Handler;
import com.chartboost.sdk.Libraries.i;
import java.util.concurrent.Executor;

public class an<T> implements Comparable<an>, Runnable
{
    public final ad<T> a;
    private final Executor b;
    private final ao c;
    private final ai d;
    private final i e;
    private final Handler f;
    private af<T> g;
    private ag h;
    
    an(final Executor b, final ao c, final ai d, final i e, final Handler f, final ad<T> a) {
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        this.f = f;
        this.a = a;
    }
    
    private ag a(final ad<T> ad) throws IOException {
        int n = 10000;
        int n2 = 0;
        try {
            return this.a(ad, n);
        }
        catch (SocketTimeoutException ex) {
            if (n2 < 1) {
                n *= 2;
                ++n2;
                return this.a(ad, n);
            }
            throw ex;
        }
    }
    
    private ag a(final ad<T> p0, final int p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          7
        //     3: aconst_null    
        //     4: astore          9
        //     6: aload_1        
        //     7: invokevirtual   com/chartboost/sdk/impl/ad.a:()Lcom/chartboost/sdk/impl/ae;
        //    10: astore          10
        //    12: aload           10
        //    14: getfield        com/chartboost/sdk/impl/ae.a:Ljava/util/Map;
        //    17: astore          8
        //    19: aload_0        
        //    20: getfield        com/chartboost/sdk/impl/an.c:Lcom/chartboost/sdk/impl/ao;
        //    23: aload_1        
        //    24: invokevirtual   com/chartboost/sdk/impl/ao.a:(Lcom/chartboost/sdk/impl/ad;)Ljava/net/HttpURLConnection;
        //    27: astore          12
        //    29: aload           12
        //    31: iload_2        
        //    32: invokevirtual   java/net/HttpURLConnection.setConnectTimeout:(I)V
        //    35: aload           12
        //    37: iload_2        
        //    38: invokevirtual   java/net/HttpURLConnection.setReadTimeout:(I)V
        //    41: aload           12
        //    43: iconst_0       
        //    44: invokevirtual   java/net/HttpURLConnection.setUseCaches:(Z)V
        //    47: aload           12
        //    49: iconst_1       
        //    50: invokevirtual   java/net/HttpURLConnection.setDoInput:(Z)V
        //    53: aload           8
        //    55: ifnull          124
        //    58: aload           8
        //    60: invokeinterface java/util/Map.keySet:()Ljava/util/Set;
        //    65: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    70: astore          11
        //    72: aload           11
        //    74: invokeinterface java/util/Iterator.hasNext:()Z
        //    79: ifeq            124
        //    82: aload           11
        //    84: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    89: checkcast       Ljava/lang/String;
        //    92: astore          13
        //    94: aload           12
        //    96: aload           13
        //    98: aload           8
        //   100: aload           13
        //   102: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //   107: checkcast       Ljava/lang/String;
        //   110: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   113: goto            72
        //   116: astore_1       
        //   117: aload           12
        //   119: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   122: aload_1        
        //   123: athrow         
        //   124: aload           12
        //   126: aload_1        
        //   127: getfield        com/chartboost/sdk/impl/ad.b:Ljava/lang/String;
        //   130: invokevirtual   java/net/HttpURLConnection.setRequestMethod:(Ljava/lang/String;)V
        //   133: aload_1        
        //   134: getfield        com/chartboost/sdk/impl/ad.b:Ljava/lang/String;
        //   137: ldc             "POST"
        //   139: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   142: ifeq            224
        //   145: aload           10
        //   147: getfield        com/chartboost/sdk/impl/ae.b:[B
        //   150: ifnull          224
        //   153: aload           12
        //   155: iconst_1       
        //   156: invokevirtual   java/net/HttpURLConnection.setDoOutput:(Z)V
        //   159: aload           12
        //   161: aload           10
        //   163: getfield        com/chartboost/sdk/impl/ae.b:[B
        //   166: arraylength    
        //   167: invokevirtual   java/net/HttpURLConnection.setFixedLengthStreamingMode:(I)V
        //   170: aload           10
        //   172: getfield        com/chartboost/sdk/impl/ae.c:Ljava/lang/String;
        //   175: ifnull          190
        //   178: aload           12
        //   180: ldc             "Content-Type"
        //   182: aload           10
        //   184: getfield        com/chartboost/sdk/impl/ae.c:Ljava/lang/String;
        //   187: invokevirtual   java/net/HttpURLConnection.addRequestProperty:(Ljava/lang/String;Ljava/lang/String;)V
        //   190: new             Ljava/io/DataOutputStream;
        //   193: dup            
        //   194: aload           12
        //   196: invokevirtual   java/net/HttpURLConnection.getOutputStream:()Ljava/io/OutputStream;
        //   199: invokespecial   java/io/DataOutputStream.<init>:(Ljava/io/OutputStream;)V
        //   202: astore          8
        //   204: aload           8
        //   206: aload           10
        //   208: getfield        com/chartboost/sdk/impl/ae.b:[B
        //   211: invokevirtual   java/io/DataOutputStream.write:([B)V
        //   214: aload           8
        //   216: ifnull          224
        //   219: aload           8
        //   221: invokevirtual   java/io/DataOutputStream.close:()V
        //   224: aload_0        
        //   225: getfield        com/chartboost/sdk/impl/an.e:Lcom/chartboost/sdk/Libraries/i;
        //   228: invokevirtual   com/chartboost/sdk/Libraries/i.b:()J
        //   231: lstore_3       
        //   232: aload           12
        //   234: invokevirtual   java/net/HttpURLConnection.getResponseCode:()I
        //   237: istore_2       
        //   238: aload_0        
        //   239: getfield        com/chartboost/sdk/impl/an.e:Lcom/chartboost/sdk/Libraries/i;
        //   242: invokevirtual   com/chartboost/sdk/Libraries/i.b:()J
        //   245: lstore          5
        //   247: aload_1        
        //   248: lload           5
        //   250: lload_3        
        //   251: lsub           
        //   252: putfield        com/chartboost/sdk/impl/ad.h:J
        //   255: iload_2        
        //   256: iconst_m1      
        //   257: if_icmpne       304
        //   260: new             Ljava/io/IOException;
        //   263: dup            
        //   264: ldc             "Could not retrieve response code from HttpUrlConnection."
        //   266: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   269: athrow         
        //   270: astore_1       
        //   271: aconst_null    
        //   272: astore          7
        //   274: aload           7
        //   276: ifnull          284
        //   279: aload           7
        //   281: invokevirtual   java/io/DataOutputStream.close:()V
        //   284: aload_1        
        //   285: athrow         
        //   286: astore          7
        //   288: aload_1        
        //   289: aload_0        
        //   290: getfield        com/chartboost/sdk/impl/an.e:Lcom/chartboost/sdk/Libraries/i;
        //   293: invokevirtual   com/chartboost/sdk/Libraries/i.b:()J
        //   296: lload_3        
        //   297: lsub           
        //   298: putfield        com/chartboost/sdk/impl/ad.h:J
        //   301: aload           7
        //   303: athrow         
        //   304: iload_2        
        //   305: invokestatic    com/chartboost/sdk/impl/an.a:(I)Z
        //   308: ifeq            712
        //   311: aload_1        
        //   312: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   315: ifnull          573
        //   318: new             Ljava/io/File;
        //   321: dup            
        //   322: aload_1        
        //   323: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   326: invokevirtual   java/io/File.getParentFile:()Ljava/io/File;
        //   329: new             Ljava/lang/StringBuilder;
        //   332: dup            
        //   333: invokespecial   java/lang/StringBuilder.<init>:()V
        //   336: aload_1        
        //   337: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   340: invokevirtual   java/io/File.getName:()Ljava/lang/String;
        //   343: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   346: ldc             ".tmp"
        //   348: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   351: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   354: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
        //   357: astore          11
        //   359: iconst_0       
        //   360: newarray        B
        //   362: astore          8
        //   364: aload           12
        //   366: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   369: astore          7
        //   371: new             Ljava/io/FileOutputStream;
        //   374: dup            
        //   375: aload           11
        //   377: invokespecial   java/io/FileOutputStream.<init>:(Ljava/io/File;)V
        //   380: astore          10
        //   382: aload           7
        //   384: aload           10
        //   386: invokestatic    com/chartboost/sdk/impl/bi.a:(Ljava/io/InputStream;Ljava/io/OutputStream;)I
        //   389: pop            
        //   390: aload           7
        //   392: ifnull          400
        //   395: aload           7
        //   397: invokevirtual   java/io/InputStream.close:()V
        //   400: aload           10
        //   402: ifnull          410
        //   405: aload           10
        //   407: invokevirtual   java/io/FileOutputStream.close:()V
        //   410: aload           8
        //   412: astore          9
        //   414: aload           11
        //   416: aload_1        
        //   417: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   420: invokevirtual   java/io/File.renameTo:(Ljava/io/File;)Z
        //   423: ifne            633
        //   426: aload           11
        //   428: invokevirtual   java/io/File.delete:()Z
        //   431: ifne            527
        //   434: new             Ljava/io/IOException;
        //   437: dup            
        //   438: new             Ljava/lang/StringBuilder;
        //   441: dup            
        //   442: invokespecial   java/lang/StringBuilder.<init>:()V
        //   445: ldc             "Unable to delete "
        //   447: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   450: aload           11
        //   452: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   455: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   458: ldc             " after failing to rename to "
        //   460: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   463: aload_1        
        //   464: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   467: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   470: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   476: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   479: athrow         
        //   480: astore          7
        //   482: aload_1        
        //   483: aload_0        
        //   484: getfield        com/chartboost/sdk/impl/an.e:Lcom/chartboost/sdk/Libraries/i;
        //   487: invokevirtual   com/chartboost/sdk/Libraries/i.b:()J
        //   490: lload           5
        //   492: lsub           
        //   493: putfield        com/chartboost/sdk/impl/ad.i:J
        //   496: aload           7
        //   498: athrow         
        //   499: astore          7
        //   501: aconst_null    
        //   502: astore          8
        //   504: aload           8
        //   506: ifnull          514
        //   509: aload           8
        //   511: invokevirtual   java/io/InputStream.close:()V
        //   514: aload           9
        //   516: ifnull          524
        //   519: aload           9
        //   521: invokevirtual   java/io/FileOutputStream.close:()V
        //   524: aload           7
        //   526: athrow         
        //   527: new             Ljava/io/IOException;
        //   530: dup            
        //   531: new             Ljava/lang/StringBuilder;
        //   534: dup            
        //   535: invokespecial   java/lang/StringBuilder.<init>:()V
        //   538: ldc             "Unable to move "
        //   540: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   543: aload           11
        //   545: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   548: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   551: ldc             " to "
        //   553: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   556: aload_1        
        //   557: getfield        com/chartboost/sdk/impl/ad.f:Ljava/io/File;
        //   560: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //   563: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   566: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   569: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   572: athrow         
        //   573: aload           7
        //   575: astore          8
        //   577: aload           12
        //   579: invokevirtual   java/net/HttpURLConnection.getInputStream:()Ljava/io/InputStream;
        //   582: astore          9
        //   584: aload           9
        //   586: astore          7
        //   588: aload           7
        //   590: ifnull          681
        //   593: aload           7
        //   595: astore          8
        //   597: new             Ljava/io/BufferedInputStream;
        //   600: dup            
        //   601: aload           7
        //   603: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //   606: invokestatic    com/chartboost/sdk/impl/bi.b:(Ljava/io/InputStream;)[B
        //   609: astore          9
        //   611: aload           9
        //   613: astore          8
        //   615: aload           8
        //   617: astore          9
        //   619: aload           7
        //   621: ifnull          633
        //   624: aload           7
        //   626: invokevirtual   java/io/InputStream.close:()V
        //   629: aload           8
        //   631: astore          9
        //   633: aload_1        
        //   634: aload_0        
        //   635: getfield        com/chartboost/sdk/impl/an.e:Lcom/chartboost/sdk/Libraries/i;
        //   638: invokevirtual   com/chartboost/sdk/Libraries/i.b:()J
        //   641: lload           5
        //   643: lsub           
        //   644: putfield        com/chartboost/sdk/impl/ad.i:J
        //   647: new             Lcom/chartboost/sdk/impl/ag;
        //   650: dup            
        //   651: iload_2        
        //   652: aload           9
        //   654: invokespecial   com/chartboost/sdk/impl/ag.<init>:(I[B)V
        //   657: astore_1       
        //   658: aload           12
        //   660: invokevirtual   java/net/HttpURLConnection.disconnect:()V
        //   663: aload_1        
        //   664: areturn        
        //   665: astore          8
        //   667: aload           7
        //   669: astore          8
        //   671: aload           12
        //   673: invokevirtual   java/net/HttpURLConnection.getErrorStream:()Ljava/io/InputStream;
        //   676: astore          7
        //   678: goto            588
        //   681: aload           7
        //   683: astore          8
        //   685: iconst_0       
        //   686: newarray        B
        //   688: astore          9
        //   690: aload           9
        //   692: astore          8
        //   694: goto            615
        //   697: astore          7
        //   699: aload           8
        //   701: ifnull          709
        //   704: aload           8
        //   706: invokevirtual   java/io/InputStream.close:()V
        //   709: aload           7
        //   711: athrow         
        //   712: iconst_0       
        //   713: newarray        B
        //   715: astore          9
        //   717: goto            633
        //   720: astore          8
        //   722: goto            224
        //   725: astore          7
        //   727: goto            284
        //   730: astore          7
        //   732: goto            400
        //   735: astore          7
        //   737: goto            410
        //   740: astore          8
        //   742: goto            514
        //   745: astore          8
        //   747: goto            524
        //   750: astore          7
        //   752: aload           8
        //   754: astore          9
        //   756: goto            633
        //   759: astore          8
        //   761: goto            709
        //   764: astore          10
        //   766: aload           7
        //   768: astore          8
        //   770: aload           10
        //   772: astore          7
        //   774: goto            504
        //   777: astore          11
        //   779: aload           10
        //   781: astore          9
        //   783: aload           7
        //   785: astore          8
        //   787: aload           11
        //   789: astore          7
        //   791: goto            504
        //   794: astore_1       
        //   795: aload           8
        //   797: astore          7
        //   799: goto            274
        //    Exceptions:
        //  throws java.io.IOException
        //    Signature:
        //  (Lcom/chartboost/sdk/impl/ad<TT;>;I)Lcom/chartboost/sdk/impl/ag;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  58     72     116    124    Any
        //  72     113    116    124    Any
        //  124    190    116    124    Any
        //  190    204    270    274    Any
        //  204    214    794    802    Any
        //  219    224    720    725    Ljava/io/IOException;
        //  219    224    116    124    Any
        //  224    232    116    124    Any
        //  232    238    286    304    Any
        //  238    255    116    124    Any
        //  260    270    116    124    Any
        //  279    284    725    730    Ljava/io/IOException;
        //  279    284    116    124    Any
        //  284    286    116    124    Any
        //  288    304    116    124    Any
        //  304    364    480    499    Any
        //  364    371    499    504    Any
        //  371    382    764    777    Any
        //  382    390    777    794    Any
        //  395    400    730    735    Ljava/io/IOException;
        //  395    400    480    499    Any
        //  405    410    735    740    Ljava/io/IOException;
        //  405    410    480    499    Any
        //  414    480    480    499    Any
        //  482    499    116    124    Any
        //  509    514    740    745    Ljava/io/IOException;
        //  509    514    480    499    Any
        //  519    524    745    750    Ljava/io/IOException;
        //  519    524    480    499    Any
        //  524    527    480    499    Any
        //  527    573    480    499    Any
        //  577    584    665    681    Ljava/io/IOException;
        //  577    584    697    712    Any
        //  597    611    697    712    Any
        //  624    629    750    759    Ljava/io/IOException;
        //  624    629    480    499    Any
        //  633    658    116    124    Any
        //  671    678    697    712    Any
        //  685    690    697    712    Any
        //  704    709    759    764    Ljava/io/IOException;
        //  704    709    480    499    Any
        //  709    712    480    499    Any
        //  712    717    480    499    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 361, Size: 361
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
    
    private static boolean a(final int n) {
        boolean b;
        if (100 <= n && n < 200) {
            b = true;
        }
        else {
            b = false;
        }
        return !b && n != 204 && n != 304;
    }
    
    public int a(final an an) {
        return this.a.d - an.a.d;
    }
    
    @Override
    public void run() {
        if (this.g != null) {
            try {
                if (this.g.b == null) {
                    this.a.a(this.g.a, this.h);
                    return;
                }
                this.a.a(this.g.b, this.h);
                return;
            }
            catch (Exception ex) {
                com.chartboost.sdk.Tracking.a.a(this.getClass(), "deliver result", ex);
                return;
            }
        }
        if (this.a.e.compareAndSet(0, 1)) {
            final long b = this.e.b();
            Label_0327: {
                while (true) {
                    Label_0410: {
                        try {
                            if (!this.d.b()) {
                                break Label_0327;
                            }
                            this.h = this.a(this.a);
                            final int a = this.h.a;
                            if (a >= 200 && a < 300) {
                                this.g = this.a.a(this.h);
                            }
                            else {
                                this.g = (af<T>)af.a(new CBError(CBError.a.e, "Failure due to HTTP status code " + a));
                            }
                            this.a.g = this.e.b() - b;
                            switch (this.a.j) {
                                default: {}
                                case 0: {
                                    this.f.post((Runnable)this);
                                }
                                case 1: {
                                    break Label_0410;
                                }
                            }
                        }
                        catch (Throwable t) {
                            this.g = af.a(new CBError(CBError.a.e, t.toString()));
                            this.a.g = this.e.b() - b;
                            switch (this.a.j) {
                                default: {
                                    return;
                                }
                                case 0: {
                                    this.f.post((Runnable)this);
                                    return;
                                }
                                case 1: {
                                    break Label_0410;
                                }
                            }
                            this.g = af.a(new CBError(CBError.a.b, "Internet Unavailable"));
                            continue;
                        }
                        finally {
                            this.a.g = this.e.b() - b;
                            while (true) {
                                switch (this.a.j) {
                                    case 0: {
                                        this.f.post((Runnable)this);
                                        break Label_0396;
                                    }
                                    case 1: {
                                        this.b.execute(this);
                                        break Label_0396;
                                    }
                                }
                                continue;
                            }
                            this.b.execute(this);
                            return;
                            this.b.execute(this);
                        }
                    }
                    break;
                }
            }
        }
    }
}
