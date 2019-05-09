// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import android.os.SystemClock;
import java.util.Iterator;
import android.util.Base64;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.LinkedHashMap;
import java.util.Map;
import java.sql.Timestamp;
import android.os.Looper;
import android.opengl.GLSurfaceView;
import android.content.Intent;
import android.content.Context;
import java.io.File;
import android.os.Handler;

public final class gc
{
    private static final gc q;
    private static gc r;
    private static Handler w;
    private static File x;
    public final gk a;
    public gl b;
    public boolean c;
    public String d;
    public Context e;
    public gf f;
    public gb g;
    public gp h;
    public ga i;
    public String j;
    public boolean k;
    public String l;
    public String m;
    public boolean n;
    public String o;
    public gd p;
    private boolean s;
    private boolean t;
    private String u;
    private String v;
    
    static {
        gc.r = (q = new gc());
    }
    
    private gc() {
        this.c = false;
        this.s = false;
        this.d = null;
        this.t = false;
        this.n = false;
        this.p = gd.a((fr)null);
        this.a = new gk(this);
    }
    
    public static gc a() {
        return gc.r;
    }
    
    public static gc a(final Context context) {
        final gc r = gc.r;
        r.b(context);
        return r;
    }
    
    public static String a(Context context, final Intent intent) {
        final String a = f.a(intent);
        if (a == null) {
            return a;
        }
        final gc r = gc.r;
        r.b(context);
        if (!ct.c(r.f.c()) && !intent.getBooleanExtra("fiverocks:force", false)) {
            return a;
        }
        context = (Context)r.f;
        synchronized (context) {
            ((gf)context).c.d.a(a);
            ((gf)context).b.d = a;
            // monitorexit(context)
            if (a.length() > 0) {
                context = (Context)r.g;
                ((gb)context).a(((gb)context).a(eb.APP, "referrer"));
            }
            return a;
        }
    }
    
    public static void a(final GLSurfaceView glSurfaceView) {
        if (!fz.a(glSurfaceView, "setGLSurfaceView: The given GLSurfaceView was null")) {
            return;
        }
        fu.a(glSurfaceView);
    }
    
    public static void a(final Runnable runnable) {
        synchronized (gc.class) {
            if (gc.w == null) {
                gc.w = new Handler(Looper.getMainLooper());
            }
            gc.w.post(runnable);
        }
    }
    
    public static File c(final Context context) {
        synchronized (gc.class) {
            if (gc.x == null) {
                gc.x = context.getDir("fiverocks", 0);
            }
            return gc.x;
        }
    }
    
    static File d(final Context context) {
        return new File(c(context), "install");
    }
    
    public final ee a(final boolean b) {
        if (b) {
            this.f.a();
        }
        return this.f.b();
    }
    
    public final void a(final Context p0, final String p1, final String p2, final String p3, final String p4, final String p5) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          8
        //     3: aload_0        
        //     4: monitorenter   
        //     5: aload_0        
        //     6: getfield        com/tapjoy/internal/gc.k:Z
        //     9: istore          9
        //    11: iload           9
        //    13: ifeq            19
        //    16: aload_0        
        //    17: monitorexit    
        //    18: return         
        //    19: aload_0        
        //    20: aload_1        
        //    21: invokevirtual   com/tapjoy/internal/gc.b:(Landroid/content/Context;)V
        //    24: aload_0        
        //    25: getfield        com/tapjoy/internal/gc.e:Landroid/content/Context;
        //    28: ifnull          364
        //    31: iconst_1       
        //    32: istore          9
        //    34: iload           9
        //    36: ldc             "The given context was null"
        //    38: invokestatic    com/tapjoy/internal/fz.a:(ZLjava/lang/String;)Z
        //    41: ifeq            16
        //    44: aload           5
        //    46: ifnull          370
        //    49: aload           5
        //    51: invokevirtual   java/lang/String.length:()I
        //    54: bipush          24
        //    56: if_icmpne       370
        //    59: aload           5
        //    61: ldc             "[0-9a-f]{24}"
        //    63: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    66: ifeq            370
        //    69: iconst_1       
        //    70: istore          7
        //    72: iload           7
        //    74: ifeq            16
        //    77: aload           6
        //    79: ifnull          391
        //    82: aload           6
        //    84: invokevirtual   java/lang/String.length:()I
        //    87: bipush          20
        //    89: if_icmpne       391
        //    92: aload           6
        //    94: ldc             "[0-9A-Za-z\\-_]{20}"
        //    96: invokevirtual   java/lang/String.matches:(Ljava/lang/String;)Z
        //    99: ifeq            391
        //   102: iconst_1       
        //   103: istore          7
        //   105: iload           7
        //   107: ifeq            16
        //   110: aload_0        
        //   111: aload_2        
        //   112: putfield        com/tapjoy/internal/gc.l:Ljava/lang/String;
        //   115: aload_0        
        //   116: aload_3        
        //   117: putfield        com/tapjoy/internal/gc.m:Ljava/lang/String;
        //   120: aload_0        
        //   121: aload           5
        //   123: putfield        com/tapjoy/internal/gc.u:Ljava/lang/String;
        //   126: aload_0        
        //   127: aload           6
        //   129: putfield        com/tapjoy/internal/gc.v:Ljava/lang/String;
        //   132: new             Ljava/net/URL;
        //   135: dup            
        //   136: aload           4
        //   138: invokespecial   java/net/URL.<init>:(Ljava/lang/String;)V
        //   141: astore_1       
        //   142: new             Lcom/tapjoy/internal/cj;
        //   145: dup            
        //   146: new             Ljava/lang/StringBuilder;
        //   149: dup            
        //   150: invokespecial   java/lang/StringBuilder.<init>:()V
        //   153: ldc             "TapjoySDK"
        //   155: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   158: ldc             " "
        //   160: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   163: aload_3        
        //   164: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   167: ldc             " ("
        //   169: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   172: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //   175: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   178: ldc_w           "; Android "
        //   181: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   184: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //   187: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   190: ldc_w           "; "
        //   193: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   196: invokestatic    java/util/Locale.getDefault:()Ljava/util/Locale;
        //   199: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   202: ldc_w           ")"
        //   205: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   208: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   211: aload_1        
        //   212: invokespecial   com/tapjoy/internal/cj.<init>:(Ljava/lang/String;Ljava/net/URL;)V
        //   215: astore_1       
        //   216: aload_1        
        //   217: putstatic       com/tapjoy/internal/cf.b:Lcom/tapjoy/internal/ci;
        //   220: invokestatic    java/util/concurrent/Executors.newCachedThreadPool:()Ljava/util/concurrent/ExecutorService;
        //   223: putstatic       com/tapjoy/internal/cf.a:Ljava/util/concurrent/ExecutorService;
        //   226: aload_0        
        //   227: getfield        com/tapjoy/internal/gc.i:Lcom/tapjoy/internal/ga;
        //   230: astore_2       
        //   231: aload_2        
        //   232: aload_1        
        //   233: putfield        com/tapjoy/internal/ga.b:Lcom/tapjoy/internal/ci;
        //   236: aload_2        
        //   237: invokevirtual   com/tapjoy/internal/ga.a:()V
        //   240: aload_0        
        //   241: iconst_1       
        //   242: putfield        com/tapjoy/internal/gc.k:Z
        //   245: new             Lcom/tapjoy/internal/gg;
        //   248: dup            
        //   249: aload_0        
        //   250: getfield        com/tapjoy/internal/gc.e:Landroid/content/Context;
        //   253: invokestatic    com/tapjoy/internal/gc.d:(Landroid/content/Context;)Ljava/io/File;
        //   256: invokespecial   com/tapjoy/internal/gg.<init>:(Ljava/io/File;)V
        //   259: astore_1       
        //   260: aload_1        
        //   261: invokevirtual   com/tapjoy/internal/gg.b:()Ljava/lang/String;
        //   264: ifnull          422
        //   267: iload           8
        //   269: istore          7
        //   271: iload           7
        //   273: ifne            301
        //   276: aload_1        
        //   277: invokevirtual   com/tapjoy/internal/gg.a:()Z
        //   280: ifeq            301
        //   283: aload_0        
        //   284: getfield        com/tapjoy/internal/gc.g:Lcom/tapjoy/internal/gb;
        //   287: astore_1       
        //   288: aload_1        
        //   289: aload_1        
        //   290: getstatic       com/tapjoy/internal/eb.APP:Lcom/tapjoy/internal/eb;
        //   293: ldc             "install"
        //   295: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/eb;Ljava/lang/String;)Lcom/tapjoy/internal/dy$a;
        //   298: invokevirtual   com/tapjoy/internal/gb.a:(Lcom/tapjoy/internal/dy$a;)V
        //   301: aload_0        
        //   302: getfield        com/tapjoy/internal/gc.f:Lcom/tapjoy/internal/gf;
        //   305: astore_1       
        //   306: aload           5
        //   308: invokestatic    com/tapjoy/internal/ct.c:(Ljava/lang/String;)Z
        //   311: ifne            352
        //   314: aload           5
        //   316: aload_1        
        //   317: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   320: getfield        com/tapjoy/internal/gm.D:Lcom/tapjoy/internal/q;
        //   323: invokevirtual   com/tapjoy/internal/q.a:()Ljava/lang/String;
        //   326: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   329: ifne            352
        //   332: aload_1        
        //   333: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   336: getfield        com/tapjoy/internal/gm.D:Lcom/tapjoy/internal/q;
        //   339: aload           5
        //   341: invokevirtual   com/tapjoy/internal/q.a:(Ljava/lang/String;)V
        //   344: aload_1        
        //   345: getfield        com/tapjoy/internal/gf.c:Lcom/tapjoy/internal/gm;
        //   348: iconst_0       
        //   349: invokevirtual   com/tapjoy/internal/gm.a:(Z)V
        //   352: aload_0        
        //   353: invokevirtual   com/tapjoy/internal/gc.b:()V
        //   356: goto            16
        //   359: astore_1       
        //   360: aload_0        
        //   361: monitorexit    
        //   362: aload_1        
        //   363: athrow         
        //   364: iconst_0       
        //   365: istore          9
        //   367: goto            34
        //   370: ldc_w           "Invalid App ID: {}"
        //   373: iconst_1       
        //   374: anewarray       Ljava/lang/Object;
        //   377: dup            
        //   378: iconst_0       
        //   379: aload           5
        //   381: aastore        
        //   382: invokestatic    com/tapjoy/internal/fz.b:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   385: iconst_0       
        //   386: istore          7
        //   388: goto            72
        //   391: ldc_w           "Invalid App Key: {}"
        //   394: iconst_1       
        //   395: anewarray       Ljava/lang/Object;
        //   398: dup            
        //   399: iconst_0       
        //   400: aload           6
        //   402: aastore        
        //   403: invokestatic    com/tapjoy/internal/fz.b:(Ljava/lang/String;[Ljava/lang/Object;)V
        //   406: iconst_0       
        //   407: istore          7
        //   409: goto            105
        //   412: astore_1       
        //   413: new             Ljava/lang/IllegalArgumentException;
        //   416: dup            
        //   417: aload_1        
        //   418: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/Throwable;)V
        //   421: athrow         
        //   422: iconst_0       
        //   423: istore          7
        //   425: goto            271
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  5      11     359    364    Any
        //  19     31     359    364    Any
        //  34     44     359    364    Any
        //  49     69     359    364    Any
        //  82     102    359    364    Any
        //  110    132    359    364    Any
        //  132    142    412    422    Ljava/net/MalformedURLException;
        //  132    142    359    364    Any
        //  142    267    359    364    Any
        //  276    301    359    364    Any
        //  301    352    359    364    Any
        //  352    356    359    364    Any
        //  370    385    359    364    Any
        //  391    406    359    364    Any
        //  413    422    359    364    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0271:
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
    
    public final void a(final String o) {
        synchronized (this) {
            if (this.k) {
                String o2;
                if ((o2 = o) == null) {
                    o2 = o;
                    if (this.o != null) {
                        o2 = this.o;
                    }
                }
                this.o = null;
                if (o2 != null) {
                    final ee b = this.f.b();
                    fz.a("GCM registration id of device {} updated for sender {}: {}", b.d.h, this.d, o2);
                    new ho(b, o2).a(new ck() {
                        @Override
                        public final void a(final cf cf) {
                        }
                    }, cf.a);
                }
            }
            else if (o != null) {
                this.o = o;
            }
        }
    }
    
    final void a(final Map map) {
        final gb g = this.g;
        final dy.a a = g.a(eb.CAMPAIGN, "impression");
        if (map != null) {
            a.r = bm.a((Object)map);
        }
        g.a(a);
    }
    
    final void a(final Map map, final long n) {
        final gb g = this.g;
        final dy.a a = g.a(eb.CAMPAIGN, "view");
        a.i = n;
        if (map != null) {
            a.r = bm.a((Object)map);
        }
        g.a(a);
    }
    
    final void a(final Map map, final String s) {
        final gb g = this.g;
        final dy.a a = g.a(eb.CAMPAIGN, "click");
        final LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(map);
        linkedHashMap.put("region", s);
        a.r = bm.a((Object)linkedHashMap);
        g.a(a);
    }
    
    public final void a(Set f) {
        if (!this.c("setUserTags")) {
            return;
        }
        Set<String> set;
        if ((set = (Set<String>)f) != null) {
            set = (Set<String>)f;
            if (!((Set)f).isEmpty()) {
                set = new HashSet<String>();
                for (final String s : f) {
                    if (s != null) {
                        final String trim = s.trim();
                        if (trim.isEmpty() || trim.length() > 200) {
                            continue;
                        }
                        set.add(trim);
                        if (set.size() >= 200) {
                            break;
                        }
                        continue;
                    }
                }
            }
        }
        f = this.f;
        // monitorenter(f)
        Label_0199: {
            if (set == null) {
                break Label_0199;
            }
            try {
                if (!set.isEmpty()) {
                    f.c.z.a(Base64.encodeToString(ej.c.b(new ej(new ArrayList(set))), 2));
                    f.b.A.clear();
                    f.b.A.addAll(set);
                    return;
                }
            }
            finally {
            }
            // monitorexit(f)
        }
        f.c.z.c();
        f.b.A.clear();
    }
    
    public final void b() {
        synchronized (this) {
            if (this.k) {
                ge.b(this.e).e(this.d);
                this.a((String)null);
            }
        }
    }
    
    final void b(Context applicationContext) {
        synchronized (this) {
            if (this.e == null) {
                applicationContext = applicationContext.getApplicationContext();
                this.e = applicationContext;
                fd.a().a(applicationContext);
                this.f = gf.a(applicationContext);
                final File file = new File(c(applicationContext), "events2");
                if (this.i == null) {
                    this.i = new ga(file);
                }
                this.g = new gb(this.f, this.i);
                this.h = new gp(this.g);
                this.b = new gl(applicationContext);
                fi.a(new fk(new File(c(applicationContext), "usages"), this.g));
                final gw a = gw.a;
                a.b = applicationContext.getApplicationContext();
                a.c = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2E", 0);
                a.d = applicationContext.getSharedPreferences("tapjoyCacheDataMMF2U", 0);
                a.a();
            }
        }
    }
    
    public final boolean b(final String s) {
        if ((!this.k && this.j == null) || this.e == null) {
            if (fz.a) {
                fz.b(s + ": Should be called after initializing the SDK");
            }
            return false;
        }
        return true;
    }
    
    public final Set c() {
        if (!this.c("getUserTags")) {
            return new HashSet();
        }
        return this.f.e();
    }
    
    public final boolean c(final String s) {
        if (this.e == null) {
            if (fz.a) {
                fz.b(s + ": Should be called after initializing the SDK");
            }
            return false;
        }
        return true;
    }
    
    public final boolean d() {
        return this.h != null && this.h.b.get();
    }
    
    public final boolean e() {
        Object o = this.h;
        if (((gp)o).c != null) {
            ((gp)o).c.cancel(false);
            ((gp)o).c = null;
        }
        while (true) {
            if (((gp)o).b.compareAndSet(false, true)) {
                fz.a("New session started");
                final gb a = ((gp)o).a;
                final ef d = a.a.d();
                Object o2 = a.a;
                // monitorenter(o2)
                while (true) {
                    try {
                        int n = ((gf)o2).c.h.b() + 1;
                        ((gf)o2).c.h.a(n);
                        ((gf)o2).b.h = n;
                        // monitorexit(o2)
                        o2 = a.a(eb.APP, "bootup");
                        a.c = SystemClock.elapsedRealtime();
                        if (d != null) {
                            ((dy.a)o2).s = d;
                        }
                        a.a((dy.a)o2);
                        ev.c.notifyObservers();
                        n = 1;
                        if (n != 0) {
                            o = this.a;
                            // monitorenter(o)
                            final gp gp = (gp)o;
                            final Set set = null;
                            ((gk)gp).b = set;
                            final gp gp2 = (gp)o;
                            // monitorexit(gp2)
                            final gw gw = com.tapjoy.internal.gw.a;
                            gw.a();
                            return true;
                        }
                        return false;
                    }
                    finally {
                        final gp gp3;
                        o = gp3;
                    }
                    // monitorexit(o2)
                    try {
                        final gp gp = (gp)o;
                        final Set set = null;
                        ((gk)gp).b = set;
                        final gp gp2 = (gp)o;
                        // monitorexit(gp2)
                        final gw gw = com.tapjoy.internal.gw.a;
                        gw.a();
                        return true;
                        final int n = 0;
                        continue;
                    }
                    finally {
                    }
                    // monitorexit(o)
                    break;
                }
                return false;
            }
            continue;
        }
    }
}
