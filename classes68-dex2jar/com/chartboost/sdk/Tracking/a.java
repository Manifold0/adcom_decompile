// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.Tracking;

import android.text.TextUtils;
import com.chartboost.sdk.Libraries.CBLogging;
import org.json.JSONObject;
import java.util.concurrent.TimeUnit;
import com.chartboost.sdk.Model.e;
import java.util.concurrent.atomic.AtomicReference;
import com.chartboost.sdk.Libraries.b;

public class a implements b
{
    private static a d;
    private static final Long g;
    private final AtomicReference<e> e;
    private boolean f;
    private long h;
    
    static {
        g = TimeUnit.MINUTES.toMillis(5L);
    }
    
    public a(final AtomicReference<e> e) {
        this.f = false;
        this.h = System.currentTimeMillis() - a.g;
        a.d = this;
        this.e = e;
    }
    
    public static void a(final Class clazz, final String s, final Exception ex) {
        ex.printStackTrace();
        final a d = a.d;
        if (d != null) {
            d.b(clazz, s, ex);
        }
    }
    
    private void a(final String s) {
        if (this.e.get().n) {
            this.a("session", s, null, null, null, false);
        }
    }
    
    private void a(final String s, final String s2, final String s3, final String s4, final String s5, final boolean b) {
        this.a(s, s2, s3, s4, s5, null, new JSONObject(), b);
    }
    
    private void b(final Class p0, final String p1, final Exception p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          6
        //     3: aload_0        
        //     4: monitorenter   
        //     5: aload_0        
        //     6: getfield        com/chartboost/sdk/Tracking/a.e:Ljava/util/concurrent/atomic/AtomicReference;
        //     9: astore          7
        //    11: aload           7
        //    13: ifnonnull       19
        //    16: aload_0        
        //    17: monitorexit    
        //    18: return         
        //    19: aload_0        
        //    20: getfield        com/chartboost/sdk/Tracking/a.e:Ljava/util/concurrent/atomic/AtomicReference;
        //    23: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //    26: checkcast       Lcom/chartboost/sdk/Model/e;
        //    29: astore          7
        //    31: aload           7
        //    33: ifnull          16
        //    36: aload           7
        //    38: getfield        com/chartboost/sdk/Model/e.k:Z
        //    41: ifeq            16
        //    44: aload_0        
        //    45: getfield        com/chartboost/sdk/Tracking/a.f:Z
        //    48: ifne            16
        //    51: aload_0        
        //    52: iconst_1       
        //    53: putfield        com/chartboost/sdk/Tracking/a.f:Z
        //    56: invokestatic    java/lang/System.currentTimeMillis:()J
        //    59: lstore          4
        //    61: lload           4
        //    63: aload_0        
        //    64: getfield        com/chartboost/sdk/Tracking/a.h:J
        //    67: lsub           
        //    68: getstatic       com/chartboost/sdk/Tracking/a.g:Ljava/lang/Long;
        //    71: invokevirtual   java/lang/Long.longValue:()J
        //    74: lcmp           
        //    75: iflt            124
        //    78: aload           7
        //    80: getfield        com/chartboost/sdk/Model/e.r:Z
        //    83: ifeq            92
        //    86: aload_3        
        //    87: invokestatic    android/util/Log.getStackTraceString:(Ljava/lang/Throwable;)Ljava/lang/String;
        //    90: astore          6
        //    92: aload_0        
        //    93: ldc             "exception"
        //    95: aload_1        
        //    96: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //    99: aload_2        
        //   100: aload_3        
        //   101: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   104: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   107: aload_3        
        //   108: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
        //   111: aload           6
        //   113: aconst_null    
        //   114: iconst_1       
        //   115: invokevirtual   com/chartboost/sdk/Tracking/a.a:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lorg/json/JSONObject;Z)V
        //   118: aload_0        
        //   119: lload           4
        //   121: putfield        com/chartboost/sdk/Tracking/a.h:J
        //   124: aload_0        
        //   125: iconst_0       
        //   126: putfield        com/chartboost/sdk/Tracking/a.f:Z
        //   129: goto            16
        //   132: astore_1       
        //   133: aload_0        
        //   134: monitorexit    
        //   135: aload_1        
        //   136: athrow         
        //   137: astore_1       
        //   138: aload_1        
        //   139: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   142: aload_0        
        //   143: iconst_0       
        //   144: putfield        com/chartboost/sdk/Tracking/a.f:Z
        //   147: goto            16
        //   150: astore_1       
        //   151: aload_0        
        //   152: iconst_0       
        //   153: putfield        com/chartboost/sdk/Tracking/a.f:Z
        //   156: aload_1        
        //   157: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  5      11     132    137    Any
        //  19     31     132    137    Any
        //  36     56     132    137    Any
        //  56     78     137    150    Ljava/lang/Exception;
        //  56     78     150    158    Any
        //  78     92     137    150    Ljava/lang/Exception;
        //  78     92     150    158    Any
        //  92     124    137    150    Ljava/lang/Exception;
        //  92     124    150    158    Any
        //  124    129    132    137    Any
        //  138    142    150    158    Any
        //  142    147    132    137    Any
        //  151    158    132    137    Any
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
    
    private void c() {
        if (this.e.get().n) {
            this.a("session", "end", null, null, null, null, null, false);
            this.a("did-become-active");
        }
    }
    
    public void a() {
        this.a("start");
        this.a("did-become-active");
    }
    
    public void a(final String s, final long n, final long n2, final long n3) {
        if (this.e.get().p) {
            this.a("download-asset-success", s, null, null, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("processingMs", n), com.chartboost.sdk.Libraries.e.a("getResposeCodeMs", n2), com.chartboost.sdk.Libraries.e.a("readDataMs", n3)), false);
        }
    }
    
    public void a(final String s, final String s2) {
        final e e = this.e.get();
        if (e.o) {
            this.a("download-asset-start", com.chartboost.sdk.b.a(e), s, s2, null, null, null, false);
        }
    }
    
    public void a(final String s, final String s2, final long n, final long n2, final long n3) {
        if (this.e.get().p) {
            this.a("download-asset-failure", s, s2, null, null, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("processingMs", n), com.chartboost.sdk.Libraries.e.a("getResponseCodeMs", n2), com.chartboost.sdk.Libraries.e.a("readDataMs", n3)), false);
        }
    }
    
    public void a(final String s, final String s2, final String s3) {
        if (this.e.get().o) {
            this.a("load", s, s2, s3, null, false);
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4) {
        if (this.e.get().o) {
            this.a("webview-track", s, s2, s3, s4, null, null, false);
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final String s7) {
        if (this.e.get().l) {
            this.a("ad-unit-error", s, s2, s3, s4, null, com.chartboost.sdk.Libraries.e.a(com.chartboost.sdk.Libraries.e.a("adId", s5), com.chartboost.sdk.Libraries.e.a("location", s6), com.chartboost.sdk.Libraries.e.a("state", s7)), true);
        }
    }
    
    public void a(final String s, final String s2, final String s3, final String s4, final String s5, final String s6, final JSONObject jsonObject) {
        if (this.e.get().o) {
            this.a(s, s2, s3, s4, s5, s6, jsonObject, false);
        }
    }
    
    public void a(String s, final String s2, final String s3, final String s4, final String s5, final String s6, final JSONObject jsonObject, final boolean b) {
        if (this.e.get().o) {
            if (s == null) {
                s = "unknown event";
            }
            CBLogging.a("CBTrack", s);
        }
    }
    
    public void a(final String s, final String s2, String s3, final String s4, final boolean b) {
        if (this.e.get().o) {
            if (TextUtils.isEmpty((CharSequence)s3)) {
                s3 = "empty-adid";
            }
            this.a("ad-error", s, s2, s3, s4, b);
        }
    }
    
    public void a(final JSONObject jsonObject) {
        final e e = this.e.get();
        if (e.o) {
            this.a("folder", com.chartboost.sdk.b.a(e), null, null, null, null, jsonObject, false);
        }
    }
    
    public void b() {
        this.c();
    }
    
    public void b(final String s, final String s2) {
        if (this.e.get().o) {
            this.a("playback-complete", s, s2, null, null, false);
        }
    }
    
    public void b(final String s, final String s2, final String s3) {
        if (this.e.get().o) {
            this.a("ad-show", s, s2, s3, null, false);
        }
    }
    
    public void b(final String s, final String s2, String s3, final String s4) {
        if (this.e.get().o) {
            if (TextUtils.isEmpty((CharSequence)s3)) {
                s3 = "empty-adid";
            }
            this.a("ad-warning", s, s2, s3, s4, false);
        }
    }
    
    public void c(final String s, final String s2) {
        if (this.e.get().o) {
            this.a("replay", s, s2, null, null, false);
        }
    }
    
    public void c(final String s, final String s2, final String s3) {
        if (this.e.get().o) {
            this.a("ad-click", s, s2, s3, null, false);
        }
    }
    
    public void d(final String s, final String s2) {
        if (this.e.get().o) {
            this.a("playback-start", s, s2, null, null, false);
        }
    }
    
    public void d(final String s, final String s2, final String s3) {
        if (this.e.get().o) {
            this.a("ad-close", s, s2, s3, null, false);
        }
    }
    
    public void e(final String s, final String s2) {
        if (this.e.get().o) {
            this.a("playback-stop", s, s2, null, null, false);
        }
    }
    
    public void e(final String s, final String s2, final String s3) {
        if (this.e.get().o) {
            this.a("ad-dismiss", s, s2, s3, null, false);
        }
    }
}
