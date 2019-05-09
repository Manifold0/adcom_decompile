// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.browser;

import java.util.Map;
import com.kongregate.o.j.a;
import java.util.HashMap;
import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.TimeUnit;
import com.kongregate.android.internal.util.StringUtils;
import android.content.MutableContextWrapper;
import org.json.JSONObject;
import android.webkit.WebView;
import com.kongregate.android.internal.util.j;
import com.kongregate.o.c.d;
import java.util.concurrent.ScheduledFuture;
import java.util.Set;
import android.content.Context;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class b
{
    public static final int a = 120;
    private static AtomicReference<b> b;
    private static final AtomicReference<String> c;
    private static final AtomicInteger d;
    private MobileApiWebView e;
    private final Context f;
    private final String g;
    private final boolean h;
    private final boolean i;
    private final Set<String> j;
    private long k;
    private int l;
    private String m;
    private final AtomicReference<ScheduledFuture<?>> n;
    
    static {
        com.kongregate.android.internal.browser.b.b = new AtomicReference<b>(null);
        c = new AtomicReference<String>(null);
        d = new AtomicInteger(7200000);
    }
    
    private b(final Context context, final String g, final boolean h, final boolean i, final Set<String> j) {
        this.k = 0L;
        this.l = 0;
        this.m = null;
        this.n = new AtomicReference<ScheduledFuture<?>>(null);
        this.h = h;
        this.i = i;
        this.j = j;
        this.g = g;
        this.f = context.getApplicationContext();
        this.b(true);
        this.d();
    }
    
    public static b a() {
        synchronized (b.class) {
            return com.kongregate.android.internal.browser.b.b.get();
        }
    }
    
    public static b a(final Context context, final String s, final boolean b, final boolean b2, final Set<String> set) {
        synchronized (b.class) {
            if (!com.kongregate.o.c.d.a()) {
                throw new IllegalStateException("WebViewManager must be initialized from the main thread");
            }
        }
        if (b.b.get() == null) {
            final Context context2;
            b.b.set(new b(context2, s, b, b2, set));
        }
        // monitorexit(b.class)
        return a();
    }
    
    public static void a(final int n) {
        j.a("Setting WebView reload delay to " + n + " minutes");
        com.kongregate.android.internal.browser.b.d.set(60000 * n);
    }
    
    private static void a(final MobileApiWebView mobileApiWebView) {
        mobileApiWebView.getSettings().setJavaScriptEnabled(true);
        mobileApiWebView.getSettings().setDomStorageEnabled(false);
        mobileApiWebView.getSettings().setUserAgentString(MobileApiWebView.a((WebView)mobileApiWebView) + " KongregateCacheWarmer/1.0");
    }
    
    public static void a(final String s) {
        j.a("Latest panel fingerprint received: " + s);
        com.kongregate.android.internal.browser.b.c.set(s);
    }
    
    public static boolean a(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_3       
        //     2: iconst_1       
        //     3: istore_2       
        //     4: aload_0        
        //     5: ldc             "webview.db"
        //     7: invokevirtual   android/content/Context.getDatabasePath:(Ljava/lang/String;)Ljava/io/File;
        //    10: astore          6
        //    12: aload_0        
        //    13: ldc             "webviewCache.db"
        //    15: invokevirtual   android/content/Context.getDatabasePath:(Ljava/lang/String;)Ljava/io/File;
        //    18: astore          5
        //    20: aload           6
        //    22: ifnull          34
        //    25: aload           6
        //    27: invokevirtual   java/io/File.getAbsolutePath:()Ljava/lang/String;
        //    30: invokestatic    com/kongregate/android/internal/util/g.i:(Ljava/lang/String;)Z
        //    33: pop            
        //    34: aload_0        
        //    35: invokestatic    android/webkit/CookieSyncManager.createInstance:(Landroid/content/Context;)Landroid/webkit/CookieSyncManager;
        //    38: pop            
        //    39: iload_3        
        //    40: istore_1       
        //    41: invokestatic    com/kongregate/android/internal/util/a.a:()Z
        //    44: ifne            191
        //    47: ldc             Landroid/webkit/WebViewDatabase;.class
        //    49: ldc             "mDatabase"
        //    51: invokestatic    com/kongregate/android/internal/util/n.a:(Ljava/lang/Class;Ljava/lang/String;)Z
        //    54: ifeq            193
        //    57: ldc             Landroid/webkit/WebViewDatabase;.class
        //    59: ldc             "mDatabase"
        //    61: invokestatic    com/kongregate/android/internal/util/n.b:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
        //    64: ifnonnull       193
        //    67: new             Ljava/lang/RuntimeException;
        //    70: dup            
        //    71: ldc             "Failed to open WebViewDatabase, mDatabase is null"
        //    73: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //    76: athrow         
        //    77: astore          4
        //    79: ldc             "Error opening CookieSyncManager database, retrying"
        //    81: aload           4
        //    83: invokestatic    com/kongregate/android/internal/util/j.c:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    86: iload_2        
        //    87: istore_1       
        //    88: invokestatic    com/kongregate/android/internal/util/a.a:()Z
        //    91: ifne            186
        //    94: ldc             Landroid/webkit/WebViewDatabase;.class
        //    96: ldc             "mDatabase"
        //    98: invokestatic    com/kongregate/android/internal/util/n.b:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
        //   101: checkcast       Landroid/database/sqlite/SQLiteDatabase;
        //   104: astore          7
        //   106: aload           7
        //   108: ifnull          124
        //   111: aload           7
        //   113: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   116: ldc             Landroid/webkit/WebViewDatabase;.class
        //   118: ldc             "mDatabase"
        //   120: aconst_null    
        //   121: invokestatic    com/kongregate/android/internal/util/n.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V
        //   124: aload           6
        //   126: ifnull          135
        //   129: aload           6
        //   131: invokevirtual   java/io/File.delete:()Z
        //   134: pop            
        //   135: ldc             Landroid/webkit/WebViewDatabase;.class
        //   137: ldc             "mCacheDatabase"
        //   139: invokestatic    com/kongregate/android/internal/util/n.b:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
        //   142: checkcast       Landroid/database/sqlite/SQLiteDatabase;
        //   145: astore          6
        //   147: aload           6
        //   149: ifnull          165
        //   152: aload           6
        //   154: invokevirtual   android/database/sqlite/SQLiteDatabase.close:()V
        //   157: ldc             Landroid/webkit/WebViewDatabase;.class
        //   159: ldc             "mCacheDatabase"
        //   161: aconst_null    
        //   162: invokestatic    com/kongregate/android/internal/util/n.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V
        //   165: aload           5
        //   167: ifnull          176
        //   170: aload           5
        //   172: invokevirtual   java/io/File.delete:()Z
        //   175: pop            
        //   176: ldc             Landroid/webkit/WebViewDatabase;.class
        //   178: ldc             "mInstance"
        //   180: aconst_null    
        //   181: invokestatic    com/kongregate/android/internal/util/n.a:(Ljava/lang/Class;Ljava/lang/String;Ljava/lang/Object;)V
        //   184: iconst_0       
        //   185: istore_1       
        //   186: aload_0        
        //   187: invokestatic    android/webkit/CookieSyncManager.createInstance:(Landroid/content/Context;)Landroid/webkit/CookieSyncManager;
        //   190: pop            
        //   191: iload_1        
        //   192: ireturn        
        //   193: iload_3        
        //   194: istore_1       
        //   195: ldc             Landroid/webkit/WebViewDatabase;.class
        //   197: ldc             "mCacheDatabase"
        //   199: invokestatic    com/kongregate/android/internal/util/n.a:(Ljava/lang/Class;Ljava/lang/String;)Z
        //   202: ifeq            191
        //   205: iload_3        
        //   206: istore_1       
        //   207: ldc             Landroid/webkit/WebViewDatabase;.class
        //   209: ldc             "mCacheDatabase"
        //   211: invokestatic    com/kongregate/android/internal/util/n.b:(Ljava/lang/Class;Ljava/lang/String;)Ljava/lang/Object;
        //   214: ifnonnull       191
        //   217: new             Ljava/lang/RuntimeException;
        //   220: dup            
        //   221: ldc             "Failed to open WebViewDatabase, mCacheDatabase is null"
        //   223: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;)V
        //   226: athrow         
        //   227: astore_0       
        //   228: ldc             "Error during CookieSyncManager for the 2nd time"
        //   230: aload           4
        //   232: invokestatic    com/kongregate/android/internal/util/j.d:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   235: new             Ljava/lang/RuntimeException;
        //   238: dup            
        //   239: ldc             "Unable to initialize CookieSyncManager"
        //   241: aload           4
        //   243: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   246: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  34     39     77     191    Ljava/lang/RuntimeException;
        //  41     77     77     191    Ljava/lang/RuntimeException;
        //  186    191    227    247    Ljava/lang/RuntimeException;
        //  195    205    77     191    Ljava/lang/RuntimeException;
        //  207    227    77     191    Ljava/lang/RuntimeException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0186:
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
    
    private void b(final String s, final JSONObject jsonObject) {
        synchronized (this) {
            if (this.e == null) {
                com.kongregate.android.internal.util.j.c("WebViewManager - no webview, not sending api message: " + s);
            }
            else {
                this.e.a(s, jsonObject);
            }
        }
    }
    
    private void b(final boolean b) {
        synchronized (this) {
            this.b();
            boolean b2;
            if (b && !this.h) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            this.e = new MobileApiWebView((Context)new MutableContextWrapper(this.f), this, this.g, this.h, b2, this.i, this.j);
            if (b2) {
                a(this.e);
            }
        }
    }
    
    private boolean g() {
        synchronized (this) {
            final String s = com.kongregate.android.internal.browser.b.c.get();
            return !StringUtils.a((CharSequence)this.m) && !StringUtils.a((CharSequence)s) && !StringUtils.a(this.m, s);
        }
    }
    
    private ScheduledFuture<?> h() {
        return com.kongregate.o.c.d.a(0L, 300000L, TimeUnit.MILLISECONDS, new a());
    }
    
    private void i() {
        synchronized (this) {
            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                @Override
                public void run() {
                    synchronized (com.kongregate.android.internal.browser.b.this) {
                        final MobileApiWebView a = com.kongregate.android.internal.browser.b.this.a(true);
                        if (a != null && !a.n()) {
                            a.a(false);
                            com.kongregate.android.internal.browser.b.this.k = System.currentTimeMillis();
                        }
                    }
                }
            });
        }
    }
    
    public MobileApiWebView a(final boolean b) {
        synchronized (this) {
            if (!com.kongregate.o.c.d.a()) {
                throw new IllegalStateException("WebViewManager.getWebView must be called from the main thread");
            }
        }
        if ((!this.h && !b) || this.e == null) {
            this.b(b);
        }
        // monitorexit(this)
        return this.e;
    }
    
    public void a(final String m, final String s) {
        synchronized (this) {
            com.kongregate.android.internal.util.j.a("Fingerprint info received, current=" + m + ", latest=" + s);
            com.kongregate.android.internal.browser.b.c.set(s);
            this.m = m;
        }
    }
    
    public void a(final String s, final JSONObject jsonObject) {
        com.kongregate.o.c.d.b(new Runnable() {
            @Override
            public void run() {
                com.kongregate.android.internal.browser.b.this.b(s, jsonObject);
            }
        });
    }
    
    public void b() {
        synchronized (this) {
            if (this.e != null) {
                final MobileApiWebView e = this.e;
                this.e = null;
                com.kongregate.o.c.d.b(new Runnable() {
                    @Override
                    public void run() {
                        e.stopLoading();
                        e.removeAllViews();
                        e.destroy();
                    }
                });
            }
        }
    }
    
    public void c() {
        synchronized (this.n) {
            final ScheduledFuture<?> scheduledFuture = this.n.get();
            if (scheduledFuture != null && !scheduledFuture.isCancelled()) {
                scheduledFuture.cancel(false);
            }
        }
    }
    
    public void d() {
        synchronized (this.n) {
            final ScheduledFuture<?> scheduledFuture = this.n.get();
            if (scheduledFuture == null || scheduledFuture.isCancelled()) {
                this.n.set(this.h());
            }
        }
    }
    
    public void e() {
        synchronized (this) {
            com.kongregate.android.internal.util.j.a("Broadcasting character token");
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("value", com.kongregate.o.j.a.a().w());
            this.a("character-token", new JSONObject((Map)hashMap));
        }
    }
    
    public class a implements Runnable
    {
        @Override
        public void run() {
            long currentTimeMillis;
            try {
                currentTimeMillis = System.currentTimeMillis();
                synchronized (com.kongregate.android.internal.browser.b.this) {
                    if (com.kongregate.android.internal.browser.b.this.e == null) {
                        return;
                    }
                    if (com.kongregate.android.internal.browser.b.this.e.k() && com.kongregate.android.internal.browser.b.this.l > 0) {
                        com.kongregate.android.internal.util.j.a("Panel is loaded, resetting reload attempt count");
                        com.kongregate.android.internal.browser.b.this.l = 0;
                    }
                    if (com.kongregate.android.internal.browser.b.this.e.n()) {
                        com.kongregate.android.internal.util.j.a("Ignoring cache sync request, WebView is currently attached");
                        return;
                    }
                }
            }
            catch (Throwable t) {
                com.kongregate.android.internal.util.j.c("Exception while warming cache", t);
                return;
            }
            if (com.kongregate.android.internal.browser.b.this.h && com.kongregate.android.internal.browser.b.this.l <= 5 && !com.kongregate.android.internal.browser.b.this.e.k()) {
                com.kongregate.android.internal.browser.b.this.l++;
                com.kongregate.android.internal.util.j.a("Panel has not loaded, attempting a reload, reloadAttempt=" + com.kongregate.android.internal.browser.b.this.l);
                com.kongregate.android.internal.browser.b.this.i();
                // monitorexit(b)
                return;
            }
            final boolean f = com.kongregate.android.internal.browser.b.this.g();
            if (f || currentTimeMillis - com.kongregate.android.internal.browser.b.this.k >= com.kongregate.android.internal.browser.b.d.get()) {
                com.kongregate.android.internal.util.j.a("Reloading WebView, needsReload=" + f);
                com.kongregate.android.internal.browser.b.this.l = 0;
                com.kongregate.android.internal.browser.b.this.i();
            }
            else {
                com.kongregate.android.internal.util.j.a("Ignoring cache sync request, too soon");
            }
        }
        // monitorexit(b)
    }
}
