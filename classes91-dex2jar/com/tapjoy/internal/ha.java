// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.io.OutputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import android.graphics.Bitmap;
import java.net.URL;

public final class ha
{
    public static final bn e;
    private static final as f;
    public URL a;
    public Bitmap b;
    public byte[] c;
    public hh d;
    
    static {
        as f2 = new aw();
        if (!(f2 instanceof ax)) {
            f2 = new au.a((av)f2);
        }
        f = f2;
        e = new bn() {};
    }
    
    ha(final bs bs) {
        int n;
        if (bs.k() == bx.f) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (n != 0) {
            this.a = bs.e();
            return;
        }
        bs.h();
        final String l = bs.l();
        while (bs.j()) {
            if ("url".equals(l)) {
                this.a = bs.e();
            }
            else {
                bs.s();
            }
        }
        bs.i();
    }
    
    public ha(final URL a) {
        this.a = a;
    }
    
    private ByteArrayInputStream a(final InputStream inputStream) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        da.a(inputStream, byteArrayOutputStream);
        byteArrayOutputStream.close();
        final byte[] byteArray = byteArrayOutputStream.toByteArray();
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        final hi hi = new hi();
        hi.a(byteArray);
        final hh a = hi.a();
        if (a.b == 0) {
            this.c = byteArray;
            this.d = a;
            return byteArrayInputStream;
        }
        final u a2 = u.a;
        this.b = u.a(byteArrayInputStream);
        byteArrayInputStream.reset();
        return byteArrayInputStream;
    }
    
    public final boolean a() {
        return this.b != null || this.c != null;
    }
    
    public final void b() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc             "mm_external_cache_enabled"
        //     5: iconst_1       
        //     6: invokevirtual   com/tapjoy/internal/fb.a:(Ljava/lang/String;Z)Z
        //     9: istore          4
        //    11: iload           4
        //    13: ifne            49
        //    16: iconst_1       
        //    17: istore_1       
        //    18: iload_1        
        //    19: ifeq            54
        //    22: aload_0        
        //    23: getstatic       com/tapjoy/internal/ha.f:Lcom/tapjoy/internal/as;
        //    26: aload_0        
        //    27: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //    30: invokeinterface com/tapjoy/internal/as.a:(Ljava/lang/Object;)Ljava/lang/Object;
        //    35: checkcast       Landroid/graphics/Bitmap;
        //    38: putfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //    41: aload_0        
        //    42: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //    45: ifnull          54
        //    48: return         
        //    49: iconst_0       
        //    50: istore_1       
        //    51: goto            18
        //    54: iload           4
        //    56: ifeq            181
        //    59: getstatic       com/tapjoy/internal/gw.a:Lcom/tapjoy/internal/gw;
        //    62: aload_0        
        //    63: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //    66: invokevirtual   com/tapjoy/internal/gw.a:(Ljava/net/URL;)Ljava/io/File;
        //    69: astore          11
        //    71: aload           11
        //    73: ifnull          181
        //    76: aconst_null    
        //    77: astore          10
        //    79: new             Ljava/io/FileInputStream;
        //    82: dup            
        //    83: aload           11
        //    85: invokespecial   java/io/FileInputStream.<init>:(Ljava/io/File;)V
        //    88: astore          9
        //    90: aload_0        
        //    91: aload           9
        //    93: invokespecial   com/tapjoy/internal/ha.a:(Ljava/io/InputStream;)Ljava/io/ByteArrayInputStream;
        //    96: pop            
        //    97: aload           9
        //    99: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   102: aload_0        
        //   103: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   106: ifnonnull       116
        //   109: aload_0        
        //   110: getfield        com/tapjoy/internal/ha.c:[B
        //   113: ifnull          175
        //   116: iload_1        
        //   117: ifeq            48
        //   120: aload_0        
        //   121: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   124: ifnull          48
        //   127: getstatic       com/tapjoy/internal/ha.f:Lcom/tapjoy/internal/as;
        //   130: aload_0        
        //   131: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //   134: aload_0        
        //   135: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   138: invokeinterface com/tapjoy/internal/as.a:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   143: return         
        //   144: astore          9
        //   146: aconst_null    
        //   147: astore          9
        //   149: aload           9
        //   151: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   154: goto            102
        //   157: astore          11
        //   159: aload           10
        //   161: astore          9
        //   163: aload           11
        //   165: astore          10
        //   167: aload           9
        //   169: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   172: aload           10
        //   174: athrow         
        //   175: aload           11
        //   177: invokevirtual   java/io/File.delete:()Z
        //   180: pop            
        //   181: aload_0        
        //   182: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //   185: invokestatic    com/tapjoy/internal/em.a:(Ljava/net/URL;)Ljava/net/URLConnection;
        //   188: astore          9
        //   190: lconst_0       
        //   191: lstore          7
        //   193: aload           9
        //   195: ldc             "Cache-Control"
        //   197: invokevirtual   java/net/URLConnection.getHeaderField:(Ljava/lang/String;)Ljava/lang/String;
        //   200: astore          10
        //   202: lload           7
        //   204: lstore          5
        //   206: aload           10
        //   208: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   211: ifne            273
        //   214: aload           10
        //   216: ldc             ","
        //   218: invokevirtual   java/lang/String.split:(Ljava/lang/String;)[Ljava/lang/String;
        //   221: astore          10
        //   223: aload           10
        //   225: arraylength    
        //   226: istore_3       
        //   227: iconst_0       
        //   228: istore_2       
        //   229: lload           7
        //   231: lstore          5
        //   233: iload_2        
        //   234: iload_3        
        //   235: if_icmpge       273
        //   238: aload           10
        //   240: iload_2        
        //   241: aaload         
        //   242: invokevirtual   java/lang/String.trim:()Ljava/lang/String;
        //   245: astore          11
        //   247: aload           11
        //   249: ldc             "max-age="
        //   251: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   254: ifeq            398
        //   257: aload           11
        //   259: bipush          8
        //   261: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //   264: astore          10
        //   266: aload           10
        //   268: invokestatic    java/lang/Long.parseLong:(Ljava/lang/String;)J
        //   271: lstore          5
        //   273: aload           9
        //   275: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
        //   278: astore          10
        //   280: aload_0        
        //   281: aload           10
        //   283: invokespecial   com/tapjoy/internal/ha.a:(Ljava/io/InputStream;)Ljava/io/ByteArrayInputStream;
        //   286: astore          9
        //   288: aload           10
        //   290: invokestatic    com/tapjoy/internal/dc.a:(Ljava/io/Closeable;)V
        //   293: getstatic       com/tapjoy/internal/gw.a:Lcom/tapjoy/internal/gw;
        //   296: astore          10
        //   298: lload           5
        //   300: invokestatic    com/tapjoy/internal/gw.a:(J)Z
        //   303: ifeq            370
        //   306: iload           4
        //   308: ifeq            370
        //   311: aload_0        
        //   312: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   315: ifnonnull       325
        //   318: aload_0        
        //   319: getfield        com/tapjoy/internal/ha.c:[B
        //   322: ifnull          370
        //   325: getstatic       com/tapjoy/internal/gw.a:Lcom/tapjoy/internal/gw;
        //   328: astore          10
        //   330: aload_0        
        //   331: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //   334: astore          11
        //   336: aload           10
        //   338: getfield        com/tapjoy/internal/gw.b:Landroid/content/Context;
        //   341: ifnull          370
        //   344: aload           10
        //   346: getfield        com/tapjoy/internal/gw.e:Ljava/util/concurrent/ExecutorService;
        //   349: new             Lcom/tapjoy/internal/gw$2;
        //   352: dup            
        //   353: aload           10
        //   355: aload           11
        //   357: aload           9
        //   359: lload           5
        //   361: invokespecial   com/tapjoy/internal/gw$2.<init>:(Lcom/tapjoy/internal/gw;Ljava/net/URL;Ljava/io/InputStream;J)V
        //   364: invokeinterface java/util/concurrent/ExecutorService.submit:(Ljava/lang/Runnable;)Ljava/util/concurrent/Future;
        //   369: pop            
        //   370: iload_1        
        //   371: ifeq            48
        //   374: aload_0        
        //   375: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   378: ifnull          48
        //   381: getstatic       com/tapjoy/internal/ha.f:Lcom/tapjoy/internal/as;
        //   384: aload_0        
        //   385: getfield        com/tapjoy/internal/ha.a:Ljava/net/URL;
        //   388: aload_0        
        //   389: getfield        com/tapjoy/internal/ha.b:Landroid/graphics/Bitmap;
        //   392: invokeinterface com/tapjoy/internal/as.a:(Ljava/lang/Object;Ljava/lang/Object;)V
        //   397: return         
        //   398: iload_2        
        //   399: iconst_1       
        //   400: iadd           
        //   401: istore_2       
        //   402: goto            229
        //   405: astore          10
        //   407: lload           7
        //   409: lstore          5
        //   411: goto            273
        //   414: astore          10
        //   416: goto            167
        //   419: astore          10
        //   421: goto            149
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  79     90     144    149    Ljava/io/IOException;
        //  79     90     157    167    Any
        //  90     97     419    424    Ljava/io/IOException;
        //  90     97     414    419    Any
        //  266    273    405    414    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0102:
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
