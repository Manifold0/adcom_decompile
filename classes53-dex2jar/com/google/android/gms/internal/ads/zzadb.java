// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.ArrayList;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import android.os.Looper;
import android.net.Uri$Builder;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;
import java.util.WeakHashMap;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzadb implements zzadf
{
    private static final Object sLock;
    @VisibleForTesting
    private static zzadf zzcbw;
    @VisibleForTesting
    private static zzadf zzcbx;
    private final Context zzatx;
    private final Object zzcby;
    private final WeakHashMap<Thread, Boolean> zzcbz;
    private final ExecutorService zzru;
    private final zzang zzzw;
    
    static {
        sLock = new Object();
        zzadb.zzcbw = null;
        zzadb.zzcbx = null;
    }
    
    private zzadb(final Context context) {
        this(context, zzang.zzsl());
    }
    
    private zzadb(final Context context, final zzang zzzw) {
        this.zzcby = new Object();
        this.zzcbz = new WeakHashMap<Thread, Boolean>();
        this.zzru = Executors.newCachedThreadPool();
        Context applicationContext = context;
        if (context.getApplicationContext() != null) {
            applicationContext = context.getApplicationContext();
        }
        this.zzatx = applicationContext;
        this.zzzw = zzzw;
    }
    
    @VisibleForTesting
    private final Uri$Builder zza(final String p0, final String p1, final String p2, final int p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore          5
        //     3: aload_0        
        //     4: getfield        com/google/android/gms/internal/ads/zzadb.zzatx:Landroid/content/Context;
        //     7: invokestatic    com/google/android/gms/common/wrappers/Wrappers.packageManager:(Landroid/content/Context;)Lcom/google/android/gms/common/wrappers/PackageManagerWrapper;
        //    10: invokevirtual   com/google/android/gms/common/wrappers/PackageManagerWrapper.isCallerInstantApp:()Z
        //    13: istore          6
        //    15: iload           6
        //    17: istore          5
        //    19: ldc             "unknown"
        //    21: astore          7
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/ads/zzadb.zzatx:Landroid/content/Context;
        //    27: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    30: astore          8
        //    32: aload           8
        //    34: astore          7
        //    36: new             Landroid/net/Uri$Builder;
        //    39: dup            
        //    40: invokespecial   android/net/Uri$Builder.<init>:()V
        //    43: ldc             "https"
        //    45: invokevirtual   android/net/Uri$Builder.scheme:(Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    48: ldc             "//pagead2.googlesyndication.com/pagead/gen_204"
        //    50: invokevirtual   android/net/Uri$Builder.path:(Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    53: ldc             "is_aia"
        //    55: iload           5
        //    57: invokestatic    java/lang/Boolean.toString:(Z)Ljava/lang/String;
        //    60: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    63: ldc             "id"
        //    65: ldc             "gmob-apps-report-exception"
        //    67: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    70: ldc             "os"
        //    72: getstatic       android/os/Build$VERSION.RELEASE:Ljava/lang/String;
        //    75: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    78: ldc             "api"
        //    80: getstatic       android/os/Build$VERSION.SDK_INT:I
        //    83: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //    86: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //    89: astore          9
        //    91: getstatic       android/os/Build.MANUFACTURER:Ljava/lang/String;
        //    94: astore          10
        //    96: getstatic       android/os/Build.MODEL:Ljava/lang/String;
        //    99: astore          8
        //   101: aload           8
        //   103: aload           10
        //   105: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   108: ifeq            246
        //   111: aload           9
        //   113: ldc             "device"
        //   115: aload           8
        //   117: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   120: ldc             "js"
        //   122: aload_0        
        //   123: getfield        com/google/android/gms/internal/ads/zzadb.zzzw:Lcom/google/android/gms/internal/ads/zzang;
        //   126: getfield        com/google/android/gms/internal/ads/zzang.zzcw:Ljava/lang/String;
        //   129: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   132: ldc             "appid"
        //   134: aload           7
        //   136: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   139: ldc             "exceptiontype"
        //   141: aload_1        
        //   142: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   145: ldc             "stacktrace"
        //   147: aload_2        
        //   148: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   151: ldc             "eids"
        //   153: ldc             ","
        //   155: invokestatic    com/google/android/gms/internal/ads/zznk.zzjb:()Ljava/util/List;
        //   158: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;Ljava/lang/Iterable;)Ljava/lang/String;
        //   161: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   164: ldc             "exceptionkey"
        //   166: aload_3        
        //   167: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   170: ldc             "cl"
        //   172: ldc             "191880412"
        //   174: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   177: ldc             "rc"
        //   179: ldc             "dev"
        //   181: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   184: ldc             "session_id"
        //   186: invokestatic    com/google/android/gms/internal/ads/zzkb.zzih:()Ljava/lang/String;
        //   189: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   192: ldc             "sampling_rate"
        //   194: iload           4
        //   196: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   199: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   202: astore_1       
        //   203: getstatic       com/google/android/gms/internal/ads/zznk.zzbfo:Lcom/google/android/gms/internal/ads/zzna;
        //   206: astore_2       
        //   207: aload_1        
        //   208: ldc             "pb_tm"
        //   210: invokestatic    com/google/android/gms/internal/ads/zzkb.zzik:()Lcom/google/android/gms/internal/ads/zzni;
        //   213: aload_2        
        //   214: invokevirtual   com/google/android/gms/internal/ads/zzni.zzd:(Lcom/google/android/gms/internal/ads/zzna;)Ljava/lang/Object;
        //   217: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   220: invokevirtual   android/net/Uri$Builder.appendQueryParameter:(Ljava/lang/String;Ljava/lang/String;)Landroid/net/Uri$Builder;
        //   223: areturn        
        //   224: astore          7
        //   226: ldc             "Error fetching instant app info"
        //   228: aload           7
        //   230: invokestatic    com/google/android/gms/internal/ads/zzane.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   233: goto            19
        //   236: astore          8
        //   238: ldc             "Cannot obtain package name, proceeding."
        //   240: invokestatic    com/google/android/gms/internal/ads/zzane.zzdk:(Ljava/lang/String;)V
        //   243: goto            36
        //   246: new             Ljava/lang/StringBuilder;
        //   249: dup            
        //   250: aload           10
        //   252: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   255: invokevirtual   java/lang/String.length:()I
        //   258: iconst_1       
        //   259: iadd           
        //   260: aload           8
        //   262: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   265: invokevirtual   java/lang/String.length:()I
        //   268: iadd           
        //   269: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   272: aload           10
        //   274: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   277: ldc             " "
        //   279: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   282: aload           8
        //   284: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   287: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   290: astore          8
        //   292: goto            111
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  3      15     224    236    Ljava/lang/Throwable;
        //  23     32     236    246    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0036:
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
    
    public static zzadf zzc(final Context context, final zzang zzang) {
        while (true) {
            while (true) {
                Label_0125: {
                    synchronized (zzadb.sLock) {
                        if (zzadb.zzcbx != null) {
                            break Label_0109;
                        }
                        if (!zzkb.zzik().zzd(zznk.zzauh)) {
                            break Label_0125;
                        }
                        final zzadb zzcbx = new zzadb(context, zzang);
                        final Thread thread = Looper.getMainLooper().getThread();
                        Label_0091: {
                            if (thread == null) {
                                break Label_0091;
                            }
                            synchronized (zzcbx.zzcby) {
                                zzcbx.zzcbz.put(thread, true);
                                // monitorexit(zzcbx.zzcby)
                                thread.setUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new zzadd(zzcbx, thread.getUncaughtExceptionHandler()));
                                Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new zzadc(zzcbx, Thread.getDefaultUncaughtExceptionHandler()));
                                zzadb.zzcbx = zzcbx;
                                return zzadb.zzcbx;
                            }
                        }
                    }
                }
                zzadb.zzcbx = new zzadg();
                continue;
            }
        }
    }
    
    public static zzadf zzl(final Context context) {
        synchronized (zzadb.sLock) {
            if (zzadb.zzcbw == null) {
                if (zzkb.zzik().zzd(zznk.zzauh)) {
                    zzadb.zzcbw = new zzadb(context);
                }
                else {
                    zzadb.zzcbw = new zzadg();
                }
            }
            return zzadb.zzcbw;
        }
    }
    
    protected final void zza(final Thread thread, final Throwable t) {
        final int n = 1;
        while (true) {
            Label_0121: {
                if (t == null) {
                    break Label_0121;
                }
                boolean b = false;
                boolean b2 = false;
                for (Throwable cause = t; cause != null; cause = cause.getCause()) {
                    final StackTraceElement[] stackTrace = cause.getStackTrace();
                    for (int length = stackTrace.length, i = 0; i < length; ++i) {
                        final StackTraceElement stackTraceElement = stackTrace[i];
                        if (zzamu.zzdf(stackTraceElement.getClassName())) {
                            b2 = true;
                        }
                        if (this.getClass().getName().equals(stackTraceElement.getClassName())) {
                            b = true;
                        }
                    }
                }
                if (!b2 || b) {
                    break Label_0121;
                }
                final int n2 = n;
                if (n2 != 0) {
                    this.zza(t, "", 1.0f);
                }
                return;
            }
            final int n2 = 0;
            continue;
        }
    }
    
    @Override
    public final void zza(final Throwable t, final String s) {
        this.zza(t, s, 1.0f);
    }
    
    @Override
    public final void zza(final Throwable t, String s, final float n) {
        if (zzamu.zzc(t) != null) {
            final String name = t.getClass().getName();
            final StringWriter stringWriter = new StringWriter();
            zzazr.zza(t, new PrintWriter(stringWriter));
            final String string = stringWriter.toString();
            int n2;
            if (Math.random() < n) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            int n3;
            if (n > 0.0f) {
                n3 = (int)(1.0f / n);
            }
            else {
                n3 = 1;
            }
            if (n2 != 0) {
                final ArrayList<String> list = new ArrayList<String>();
                list.add(this.zza(name, string, s, n3).toString());
                final ArrayList<String> list2 = list;
                final int size = list2.size();
                int i = 0;
                while (i < size) {
                    final String value = list2.get(i);
                    ++i;
                    s = value;
                    this.zzru.submit(new zzade(this, new zzanf(), s));
                }
            }
        }
    }
}
