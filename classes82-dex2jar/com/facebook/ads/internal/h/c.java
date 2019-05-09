// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.h;

import java.util.HashMap;
import java.io.File;
import java.util.Map;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import android.content.Context;
import java.util.concurrent.Future;

public class c
{
    private static final String a;
    private static c b;
    private final Future<a> c;
    
    static {
        a = c.class.getSimpleName();
    }
    
    private c(final Context context) {
        this.c = Executors.newSingleThreadExecutor().submit((Callable<a>)new Callable<a>() {
            public a a() {
                return new a(context);
            }
        });
    }
    
    public static c a(final Context context) {
        Label_0032: {
            if (c.b != null) {
                break Label_0032;
            }
            synchronized (e.class) {
                if (c.b == null) {
                    c.b = new c(context.getApplicationContext());
                }
                return c.b;
            }
        }
    }
    
    @Nullable
    private a b() {
        try {
            return this.c.get(500L, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException a) {
            goto Label_0022;
        }
        catch (TimeoutException a) {
            goto Label_0022;
        }
    }
    
    public boolean a(final String s) {
        final a b = this.b();
        return b != null && b.b(s);
    }
    
    @Nullable
    public String b(final String s) {
        final a b = this.b();
        if (b == null) {
            return null;
        }
        return b.a(s);
    }
    
    private static class a
    {
        private static final Map<String, File> a;
        private final Context b;
        
        static {
            a = new HashMap<String, File>();
        }
        
        a(final Context b) {
            this.b = b;
        }
        
        @Nullable
        String a(final String s) {
            final File file = com.facebook.ads.internal.h.c.a.a.get(s);
            if (file == null) {
                return null;
            }
            return "file://" + file.getPath();
        }
        
        boolean b(final String p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: aconst_null    
            //     3: astore          4
            //     5: aconst_null    
            //     6: astore          6
            //     8: aload           4
            //    10: astore          5
            //    12: new             Ljava/io/File;
            //    15: dup            
            //    16: aload_0        
            //    17: getfield        com/facebook/ads/internal/h/c$a.b:Landroid/content/Context;
            //    20: invokestatic    com/facebook/ads/internal/v/b/o.a:(Landroid/content/Context;)Ljava/io/File;
            //    23: new             Lcom/facebook/ads/internal/v/b/a/f;
            //    26: dup            
            //    27: invokespecial   com/facebook/ads/internal/v/b/a/f.<init>:()V
            //    30: aload_1        
            //    31: invokevirtual   com/facebook/ads/internal/v/b/a/f.a:(Ljava/lang/String;)Ljava/lang/String;
            //    34: invokespecial   java/io/File.<init>:(Ljava/io/File;Ljava/lang/String;)V
            //    37: astore          8
            //    39: aload           4
            //    41: astore          5
            //    43: new             Lcom/facebook/ads/internal/v/b/a/b;
            //    46: dup            
            //    47: aload           8
            //    49: new             Lcom/facebook/ads/internal/v/b/a/g;
            //    52: dup            
            //    53: ldc2_w          67108864
            //    56: invokespecial   com/facebook/ads/internal/v/b/a/g.<init>:(J)V
            //    59: invokespecial   com/facebook/ads/internal/v/b/a/b.<init>:(Ljava/io/File;Lcom/facebook/ads/internal/v/b/a/a;)V
            //    62: astore          7
            //    64: aload           4
            //    66: astore          5
            //    68: aload           7
            //    70: invokevirtual   com/facebook/ads/internal/v/b/a/b.d:()Z
            //    73: ifeq            128
            //    76: aload           4
            //    78: astore          5
            //    80: getstatic       com/facebook/ads/internal/h/c$a.a:Ljava/util/Map;
            //    83: aload_1        
            //    84: aload           8
            //    86: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //    91: pop            
            //    92: aload           4
            //    94: astore          5
            //    96: aload           7
            //    98: invokevirtual   com/facebook/ads/internal/v/b/a/b.b:()V
            //   101: iconst_0       
            //   102: ifeq            113
            //   105: new             Ljava/lang/NullPointerException;
            //   108: dup            
            //   109: invokespecial   java/lang/NullPointerException.<init>:()V
            //   112: athrow         
            //   113: iload_3        
            //   114: ireturn        
            //   115: astore_1       
            //   116: invokestatic    com/facebook/ads/internal/h/c.a:()Ljava/lang/String;
            //   119: ldc             "Error closing the file"
            //   121: aload_1        
            //   122: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   125: pop            
            //   126: iconst_1       
            //   127: ireturn        
            //   128: aload           4
            //   130: astore          5
            //   132: new             Ljava/net/URL;
            //   135: dup            
            //   136: aload_1        
            //   137: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
            //   140: invokevirtual   java/net/URL.openConnection:()Ljava/net/URLConnection;
            //   143: astore          9
            //   145: aload           4
            //   147: astore          5
            //   149: aload           9
            //   151: invokevirtual   java/net/URLConnection.connect:()V
            //   154: aload           4
            //   156: astore          5
            //   158: new             Ljava/io/BufferedInputStream;
            //   161: dup            
            //   162: aload           9
            //   164: invokevirtual   java/net/URLConnection.getInputStream:()Ljava/io/InputStream;
            //   167: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
            //   170: astore          4
            //   172: sipush          8192
            //   175: newarray        B
            //   177: astore          5
            //   179: aload           4
            //   181: aload           5
            //   183: invokevirtual   java/io/InputStream.read:([B)I
            //   186: istore_2       
            //   187: iload_2        
            //   188: iconst_m1      
            //   189: if_icmpeq       245
            //   192: aload           7
            //   194: aload           5
            //   196: iload_2        
            //   197: invokevirtual   com/facebook/ads/internal/v/b/a/b.a:([BI)V
            //   200: goto            179
            //   203: astore_1       
            //   204: aload           4
            //   206: astore          5
            //   208: invokestatic    com/facebook/ads/internal/h/c.a:()Ljava/lang/String;
            //   211: ldc             "Error caching the file"
            //   213: aload_1        
            //   214: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   217: pop            
            //   218: iconst_0       
            //   219: istore_3       
            //   220: aload           4
            //   222: ifnull          113
            //   225: aload           4
            //   227: invokevirtual   java/io/InputStream.close:()V
            //   230: iconst_0       
            //   231: ireturn        
            //   232: astore_1       
            //   233: invokestatic    com/facebook/ads/internal/h/c.a:()Ljava/lang/String;
            //   236: ldc             "Error closing the file"
            //   238: aload_1        
            //   239: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   242: pop            
            //   243: iconst_0       
            //   244: ireturn        
            //   245: aload           7
            //   247: invokevirtual   com/facebook/ads/internal/v/b/a/b.c:()V
            //   250: getstatic       com/facebook/ads/internal/h/c$a.a:Ljava/util/Map;
            //   253: aload_1        
            //   254: aload           8
            //   256: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
            //   261: pop            
            //   262: aload           4
            //   264: ifnull          113
            //   267: aload           4
            //   269: invokevirtual   java/io/InputStream.close:()V
            //   272: iconst_1       
            //   273: ireturn        
            //   274: astore_1       
            //   275: invokestatic    com/facebook/ads/internal/h/c.a:()Ljava/lang/String;
            //   278: ldc             "Error closing the file"
            //   280: aload_1        
            //   281: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   284: pop            
            //   285: iconst_1       
            //   286: ireturn        
            //   287: astore_1       
            //   288: aload           5
            //   290: astore          4
            //   292: aload           4
            //   294: ifnull          302
            //   297: aload           4
            //   299: invokevirtual   java/io/InputStream.close:()V
            //   302: aload_1        
            //   303: athrow         
            //   304: astore          4
            //   306: invokestatic    com/facebook/ads/internal/h/c.a:()Ljava/lang/String;
            //   309: ldc             "Error closing the file"
            //   311: aload           4
            //   313: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   316: pop            
            //   317: goto            302
            //   320: astore_1       
            //   321: goto            292
            //   324: astore_1       
            //   325: aload           6
            //   327: astore          4
            //   329: goto            204
            //   332: astore_1       
            //   333: goto            204
            //   336: astore_1       
            //   337: aload           6
            //   339: astore          4
            //   341: goto            204
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                             
            //  -----  -----  -----  -----  ---------------------------------
            //  12     39     336    344    Ljava/io/IOException;
            //  12     39     324    332    Lcom/facebook/ads/internal/v/b/l;
            //  12     39     287    292    Any
            //  43     64     336    344    Ljava/io/IOException;
            //  43     64     324    332    Lcom/facebook/ads/internal/v/b/l;
            //  43     64     287    292    Any
            //  68     76     336    344    Ljava/io/IOException;
            //  68     76     324    332    Lcom/facebook/ads/internal/v/b/l;
            //  68     76     287    292    Any
            //  80     92     336    344    Ljava/io/IOException;
            //  80     92     324    332    Lcom/facebook/ads/internal/v/b/l;
            //  80     92     287    292    Any
            //  96     101    336    344    Ljava/io/IOException;
            //  96     101    324    332    Lcom/facebook/ads/internal/v/b/l;
            //  96     101    287    292    Any
            //  105    113    115    128    Ljava/io/IOException;
            //  132    145    336    344    Ljava/io/IOException;
            //  132    145    324    332    Lcom/facebook/ads/internal/v/b/l;
            //  132    145    287    292    Any
            //  149    154    336    344    Ljava/io/IOException;
            //  149    154    324    332    Lcom/facebook/ads/internal/v/b/l;
            //  149    154    287    292    Any
            //  158    172    336    344    Ljava/io/IOException;
            //  158    172    324    332    Lcom/facebook/ads/internal/v/b/l;
            //  158    172    287    292    Any
            //  172    179    203    204    Ljava/io/IOException;
            //  172    179    332    336    Lcom/facebook/ads/internal/v/b/l;
            //  172    179    320    324    Any
            //  179    187    203    204    Ljava/io/IOException;
            //  179    187    332    336    Lcom/facebook/ads/internal/v/b/l;
            //  179    187    320    324    Any
            //  192    200    203    204    Ljava/io/IOException;
            //  192    200    332    336    Lcom/facebook/ads/internal/v/b/l;
            //  192    200    320    324    Any
            //  208    218    287    292    Any
            //  225    230    232    245    Ljava/io/IOException;
            //  245    262    203    204    Ljava/io/IOException;
            //  245    262    332    336    Lcom/facebook/ads/internal/v/b/l;
            //  245    262    320    324    Any
            //  267    272    274    287    Ljava/io/IOException;
            //  297    302    304    320    Ljava/io/IOException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0302:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
}
