// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.n;

import java.lang.reflect.Constructor;
import java.lang.reflect.AccessibleObject;
import com.facebook.ads.internal.w.b.k;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.w.b.h;
import com.facebook.ads.internal.w.b.v;
import com.facebook.ads.internal.w.b.u;
import com.facebook.ads.internal.w.b.x;
import android.webkit.WebView;
import android.webkit.WebSettings;
import android.os.Build$VERSION;
import com.facebook.ads.internal.w.b.i;
import java.io.File;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import android.content.SharedPreferences;
import java.util.concurrent.Callable;
import com.facebook.ads.internal.w.b.o;
import android.support.annotation.WorkerThread;
import java.util.HashMap;
import java.util.Map;
import com.facebook.ads.internal.v.a.a;
import java.util.Locale;
import java.lang.reflect.Method;
import com.facebook.ads.internal.l.b;
import android.content.Context;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.w.b.f;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicBoolean;

public class d
{
    private static final AtomicBoolean a;
    private static final AtomicInteger b;
    private static String c;
    private static final f.a d;
    @Nullable
    private static String e;
    private final Context f;
    private final b g;
    
    static {
        a = new AtomicBoolean();
        b = new AtomicInteger(0);
        com.facebook.ads.internal.n.d.c = null;
        d = f.a();
        com.facebook.ads.internal.n.d.e = null;
    }
    
    public d(final Context f, final boolean b) {
        this.f = f;
        this.g = new b(f);
        a(f, b);
    }
    
    private static String a(final Context context, String declaredConstructor, final String s) {
        final Class<?> forName = Class.forName(declaredConstructor);
        declaredConstructor = (String)forName.getDeclaredConstructor(Context.class, Class.forName(s));
        ((AccessibleObject)declaredConstructor).setAccessible(true);
        final Method method = forName.getMethod("getUserAgentString", (Class<?>[])new Class[0]);
        try {
            return (String)method.invoke(((Constructor<Object>)declaredConstructor).newInstance(context, null), new Object[0]);
        }
        finally {
            ((AccessibleObject)declaredConstructor).setAccessible(false);
        }
    }
    
    public static String a(final b b, final Context context, final boolean b2) {
        return b(context, b2) + " [FBAN/AudienceNetworkForAndroid;FBSN/" + "Android" + ";FBSV/" + b.a + ";FBAB/" + b.f() + ";FBAV/" + b.g() + ";FBBV/" + b.h() + ";FBVS/" + "5.1.0" + ";FBLC/" + Locale.getDefault().toString() + "]";
    }
    
    @WorkerThread
    public static void a(final Context context) {
        if (context == null) {
            return;
        }
        com.facebook.ads.internal.v.a.a.a((a.a)new a.a() {
            final /* synthetic */ Context a = context.getApplicationContext();
            
            @Override
            public Map<String, String> a() {
                final HashMap<String, String> hashMap = new HashMap<String, String>();
                if (!com.facebook.ads.internal.g.b.c) {
                    hashMap.put("X-FB-Pool-Routing-Token", new d(this.a, true).a());
                }
                return hashMap;
            }
        });
    }
    
    private static void a(final Context context, final boolean b) {
        if (com.facebook.ads.internal.n.d.b.compareAndSet(0, 1)) {
            try {
                o.a();
                final SharedPreferences sharedPreferences = context.getSharedPreferences(com.facebook.ads.internal.w.f.a.a("FBAdPrefs", context), 0);
                final String string = "AFP;" + new b(context).g();
                com.facebook.ads.internal.n.d.c = sharedPreferences.getString(string, (String)null);
                final FutureTask futureTask = new FutureTask(new Callable<Boolean>() {
                    public Boolean a() {
                        com.facebook.ads.internal.n.d.c = b(context, context.getPackageName());
                        sharedPreferences.edit().putString(string, com.facebook.ads.internal.n.d.c).apply();
                        com.facebook.ads.internal.n.d.b.set(2);
                        return true;
                    }
                });
                Executors.newSingleThreadExecutor().submit(futureTask);
                if (b) {
                    futureTask.get();
                }
            }
            catch (Exception ex) {
                com.facebook.ads.internal.n.d.b.set(0);
            }
        }
    }
    
    @Nullable
    private static String b(final Context context, String a) {
        try {
            a = i.a(new File(context.getPackageManager().getApplicationInfo(a, 0).sourceDir));
            return a;
        }
        catch (Exception ex) {
            if (com.facebook.ads.internal.n.d.a.compareAndSet(false, true)) {
                com.facebook.ads.internal.w.h.a.b(context.getApplicationContext(), "generic", com.facebook.ads.internal.w.h.b.A, ex);
            }
            return null;
        }
    }
    
    @Nullable
    private static String b(Context context, final boolean b) {
        if (context == null) {
            return "Unknown";
        }
        if (b) {
            return System.getProperty("http.agent");
        }
        if (com.facebook.ads.internal.n.d.e != null) {
            return com.facebook.ads.internal.n.d.e;
        }
        synchronized (d.class) {
            if (com.facebook.ads.internal.n.d.e != null) {
                return com.facebook.ads.internal.n.d.e;
            }
        }
        if (Build$VERSION.SDK_INT >= 17) {
            try {
                com.facebook.ads.internal.n.d.e = WebSettings.getDefaultUserAgent(context);
                // monitorexit(d.class)
                return com.facebook.ads.internal.n.d.e;
            }
            catch (Exception ex) {}
        }
        try {
            com.facebook.ads.internal.n.d.e = a(context, "android.webkit.WebSettings", "android.webkit.WebView");
            // monitorexit(d.class)
            return com.facebook.ads.internal.n.d.e;
        }
        catch (Exception ex2) {
            try {
                com.facebook.ads.internal.n.d.e = a(context, "android.webkit.WebSettingsClassic", "android.webkit.WebViewClassic");
            }
            catch (Exception ex3) {
                context = (Context)new WebView(context.getApplicationContext());
                com.facebook.ads.internal.n.d.e = ((WebView)context).getSettings().getUserAgentString();
                ((WebView)context).destroy();
            }
        }
    }
    
    public static Map<String, String> b(final Context context) {
        try {
            return new d(context, false).b();
        }
        catch (Throwable t) {
            com.facebook.ads.internal.w.h.a.a(t);
            return new HashMap<String, String>();
        }
    }
    
    @WorkerThread
    public String a() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore          5
        //     3: aload_0        
        //     4: getfield        com/facebook/ads/internal/n/d.f:Landroid/content/Context;
        //     7: iconst_1       
        //     8: invokestatic    com/facebook/ads/internal/n/d.a:(Landroid/content/Context;Z)V
        //    11: new             Ljava/io/ByteArrayOutputStream;
        //    14: dup            
        //    15: invokespecial   java/io/ByteArrayOutputStream.<init>:()V
        //    18: astore_3       
        //    19: new             Landroid/util/Base64OutputStream;
        //    22: dup            
        //    23: aload_3        
        //    24: iconst_0       
        //    25: invokespecial   android/util/Base64OutputStream.<init>:(Ljava/io/OutputStream;I)V
        //    28: astore          4
        //    30: new             Ljava/util/zip/DeflaterOutputStream;
        //    33: dup            
        //    34: aload           4
        //    36: invokespecial   java/util/zip/DeflaterOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    39: astore_2       
        //    40: aload_0        
        //    41: invokevirtual   com/facebook/ads/internal/n/d.b:()Ljava/util/Map;
        //    44: astore_1       
        //    45: getstatic       com/facebook/ads/internal/g/b.b:Ljava/lang/String;
        //    48: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    51: ifeq            68
        //    54: aload_0        
        //    55: getfield        com/facebook/ads/internal/n/d.f:Landroid/content/Context;
        //    58: invokestatic    com/facebook/ads/internal/g/b.a:(Landroid/content/Context;)V
        //    61: aload_0        
        //    62: getfield        com/facebook/ads/internal/n/d.f:Landroid/content/Context;
        //    65: invokestatic    com/facebook/ads/internal/n/d.a:(Landroid/content/Context;)V
        //    68: aload_1        
        //    69: ldc_w           "IDFA"
        //    72: getstatic       com/facebook/ads/internal/g/b.b:Ljava/lang/String;
        //    75: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    80: pop            
        //    81: aload_1        
        //    82: ldc_w           "USER_AGENT"
        //    85: aload_0        
        //    86: getfield        com/facebook/ads/internal/n/d.g:Lcom/facebook/ads/internal/l/b;
        //    89: aload_0        
        //    90: getfield        com/facebook/ads/internal/n/d.f:Landroid/content/Context;
        //    93: iconst_0       
        //    94: invokestatic    com/facebook/ads/internal/n/d.a:(Lcom/facebook/ads/internal/l/b;Landroid/content/Context;Z)Ljava/lang/String;
        //    97: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   102: pop            
        //   103: aload_2        
        //   104: new             Lorg/json/JSONObject;
        //   107: dup            
        //   108: aload_1        
        //   109: invokespecial   org/json/JSONObject.<init>:(Ljava/util/Map;)V
        //   112: invokevirtual   org/json/JSONObject.toString:()Ljava/lang/String;
        //   115: invokevirtual   java/lang/String.getBytes:()[B
        //   118: invokevirtual   java/util/zip/DeflaterOutputStream.write:([B)V
        //   121: aload_2        
        //   122: invokevirtual   java/util/zip/DeflaterOutputStream.close:()V
        //   125: aload_3        
        //   126: invokevirtual   java/io/ByteArrayOutputStream.toString:()Ljava/lang/String;
        //   129: ldc_w           "\n"
        //   132: ldc_w           ""
        //   135: invokevirtual   java/lang/String.replaceAll:(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   138: astore_1       
        //   139: aload_2        
        //   140: ifnull          147
        //   143: aload_2        
        //   144: invokevirtual   java/util/zip/DeflaterOutputStream.close:()V
        //   147: aload           4
        //   149: ifnull          157
        //   152: aload           4
        //   154: invokevirtual   android/util/Base64OutputStream.close:()V
        //   157: aload_3        
        //   158: ifnull          165
        //   161: aload_3        
        //   162: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   165: aload_1        
        //   166: areturn        
        //   167: astore_3       
        //   168: aconst_null    
        //   169: astore_1       
        //   170: aconst_null    
        //   171: astore_2       
        //   172: aload           5
        //   174: astore          4
        //   176: new             Ljava/lang/RuntimeException;
        //   179: dup            
        //   180: ldc_w           "Failed to build user token"
        //   183: aload_3        
        //   184: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   187: athrow         
        //   188: astore          5
        //   190: aload_2        
        //   191: astore_3       
        //   192: aload_1        
        //   193: astore_2       
        //   194: aload           5
        //   196: astore_1       
        //   197: aload_2        
        //   198: ifnull          205
        //   201: aload_2        
        //   202: invokevirtual   java/util/zip/DeflaterOutputStream.close:()V
        //   205: aload           4
        //   207: ifnull          215
        //   210: aload           4
        //   212: invokevirtual   android/util/Base64OutputStream.close:()V
        //   215: aload_3        
        //   216: ifnull          223
        //   219: aload_3        
        //   220: invokevirtual   java/io/ByteArrayOutputStream.close:()V
        //   223: aload_1        
        //   224: athrow         
        //   225: astore_2       
        //   226: goto            223
        //   229: astore_1       
        //   230: aconst_null    
        //   231: astore_2       
        //   232: aconst_null    
        //   233: astore          4
        //   235: aconst_null    
        //   236: astore_3       
        //   237: goto            197
        //   240: astore_1       
        //   241: aconst_null    
        //   242: astore_2       
        //   243: aconst_null    
        //   244: astore          4
        //   246: goto            197
        //   249: astore_1       
        //   250: aconst_null    
        //   251: astore_2       
        //   252: goto            197
        //   255: astore_1       
        //   256: goto            197
        //   259: astore          4
        //   261: aconst_null    
        //   262: astore_1       
        //   263: aload_3        
        //   264: astore_2       
        //   265: aload           4
        //   267: astore_3       
        //   268: aload           5
        //   270: astore          4
        //   272: goto            176
        //   275: astore          5
        //   277: aconst_null    
        //   278: astore_1       
        //   279: aload_3        
        //   280: astore_2       
        //   281: aload           5
        //   283: astore_3       
        //   284: goto            176
        //   287: astore_1       
        //   288: aload_3        
        //   289: astore          5
        //   291: aload_1        
        //   292: astore_3       
        //   293: aload_2        
        //   294: astore_1       
        //   295: aload           5
        //   297: astore_2       
        //   298: goto            176
        //   301: astore_2       
        //   302: aload_1        
        //   303: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  11     19     167    176    Ljava/io/IOException;
        //  11     19     229    240    Any
        //  19     30     259    275    Ljava/io/IOException;
        //  19     30     240    249    Any
        //  30     40     275    287    Ljava/io/IOException;
        //  30     40     249    255    Any
        //  40     68     287    301    Ljava/io/IOException;
        //  40     68     255    259    Any
        //  68     139    287    301    Ljava/io/IOException;
        //  68     139    255    259    Any
        //  143    147    301    304    Ljava/io/IOException;
        //  152    157    301    304    Ljava/io/IOException;
        //  161    165    301    304    Ljava/io/IOException;
        //  176    188    188    197    Any
        //  201    205    225    229    Ljava/io/IOException;
        //  210    215    225    229    Ljava/io/IOException;
        //  219    223    225    229    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 165, Size: 165
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
    
    public Map<String, String> b() {
        a(this.f, false);
        com.facebook.ads.internal.l.a.a(this.f);
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("SDK", "android");
        hashMap.put("SDK_VERSION", "5.1.0");
        hashMap.put("LOCALE", Locale.getDefault().toString());
        final float b = x.b;
        final int widthPixels = this.f.getResources().getDisplayMetrics().widthPixels;
        final int heightPixels = this.f.getResources().getDisplayMetrics().heightPixels;
        hashMap.put("DENSITY", String.valueOf(b));
        hashMap.put("SCREEN_WIDTH", String.valueOf((int)(widthPixels / b)));
        hashMap.put("SCREEN_HEIGHT", String.valueOf((int)(heightPixels / b)));
        hashMap.put("ATTRIBUTION_ID", com.facebook.ads.internal.g.b.a);
        hashMap.put("ID_SOURCE", com.facebook.ads.internal.g.b.d);
        hashMap.put("OS", "Android");
        hashMap.put("OSVERS", com.facebook.ads.internal.l.b.a);
        hashMap.put("BUNDLE", this.g.f());
        hashMap.put("APPNAME", this.g.d());
        hashMap.put("APPVERS", this.g.g());
        hashMap.put("APPBUILD", String.valueOf(this.g.h()));
        hashMap.put("CARRIER", this.g.c());
        hashMap.put("MAKE", this.g.a());
        hashMap.put("MODEL", this.g.b());
        hashMap.put("ROOTED", String.valueOf(com.facebook.ads.internal.n.d.d.d));
        hashMap.put("INSTALLER", this.g.e());
        hashMap.put("SDK_CAPABILITY", com.facebook.ads.internal.w.b.b.b());
        hashMap.put("NETWORK_TYPE", String.valueOf(u.a(this.f).g));
        hashMap.put("SESSION_TIME", v.a(o.b()));
        hashMap.put("SESSION_ID", o.c());
        if (com.facebook.ads.internal.n.d.c != null) {
            hashMap.put("AFP", com.facebook.ads.internal.n.d.c);
        }
        final String a = com.facebook.ads.internal.w.b.f.a(this.f);
        if (a != null) {
            hashMap.put("ASHAS", a);
        }
        hashMap.put("UNITY", String.valueOf(h.a(this.f)));
        final String mediationService = AdInternalSettings.getMediationService();
        if (mediationService != null) {
            hashMap.put("MEDIATION_SERVICE", mediationService);
        }
        hashMap.put("ACCESSIBILITY_ENABLED", String.valueOf(this.g.i()));
        if (this.g.j() != -1) {
            hashMap.put("APP_MIN_SDK_VERSION", String.valueOf(this.g.j()));
        }
        hashMap.put("VALPARAMS", com.facebook.ads.internal.n.b.a(this.f));
        hashMap.put("ANALOG", k.a(com.facebook.ads.internal.l.a.a()));
        hashMap.put("PROCESS", com.facebook.ads.internal.n.c.a(this.f));
        return hashMap;
    }
}
