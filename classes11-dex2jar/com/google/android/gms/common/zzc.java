// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import android.util.Log;
import android.os.StrictMode$ThreadPolicy;
import android.os.StrictMode;
import android.content.Context;
import com.google.android.gms.common.internal.zzm;
import javax.annotation.CheckReturnValue;

@CheckReturnValue
final class zzc
{
    private static volatile zzm zzn;
    private static final Object zzo;
    private static Context zzp;
    
    static {
        zzo = new Object();
    }
    
    static com.google.android.gms.common.zzm zza(final String s, final zze zze, final boolean b, final boolean b2) {
        final StrictMode$ThreadPolicy allowThreadDiskReads = StrictMode.allowThreadDiskReads();
        try {
            return zzb(s, zze, b, b2);
        }
        finally {
            StrictMode.setThreadPolicy(allowThreadDiskReads);
        }
    }
    
    static void zza(final Context context) {
        synchronized (zzc.class) {
            if (zzc.zzp == null) {
                if (context != null) {
                    zzc.zzp = context.getApplicationContext();
                }
            }
            else {
                Log.w("GoogleCertificates", "GoogleCertificates has been initialized already");
            }
        }
    }
    
    private static com.google.android.gms.common.zzm zzb(final String p0, final zze p1, final boolean p2, final boolean p3) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifnonnull       52
        //     6: getstatic       com/google/android/gms/common/zzc.zzp:Landroid/content/Context;
        //     9: invokestatic    com/google/android/gms/common/internal/Preconditions.checkNotNull:(Ljava/lang/Object;)Ljava/lang/Object;
        //    12: pop            
        //    13: getstatic       com/google/android/gms/common/zzc.zzo:Ljava/lang/Object;
        //    16: astore          4
        //    18: aload           4
        //    20: monitorenter   
        //    21: getstatic       com/google/android/gms/common/zzc.zzn:Lcom/google/android/gms/common/internal/zzm;
        //    24: ifnonnull       49
        //    27: getstatic       com/google/android/gms/common/zzc.zzp:Landroid/content/Context;
        //    30: getstatic       com/google/android/gms/dynamite/DynamiteModule.PREFER_HIGHEST_OR_LOCAL_VERSION_NO_FORCE_STAGING:Lcom/google/android/gms/dynamite/DynamiteModule$VersionPolicy;
        //    33: ldc             "com.google.android.gms.googlecertificates"
        //    35: invokestatic    com/google/android/gms/dynamite/DynamiteModule.load:(Landroid/content/Context;Lcom/google/android/gms/dynamite/DynamiteModule$VersionPolicy;Ljava/lang/String;)Lcom/google/android/gms/dynamite/DynamiteModule;
        //    38: ldc             "com.google.android.gms.common.GoogleCertificatesImpl"
        //    40: invokevirtual   com/google/android/gms/dynamite/DynamiteModule.instantiate:(Ljava/lang/String;)Landroid/os/IBinder;
        //    43: invokestatic    com/google/android/gms/common/internal/zzn.zzc:(Landroid/os/IBinder;)Lcom/google/android/gms/common/internal/zzm;
        //    46: putstatic       com/google/android/gms/common/zzc.zzn:Lcom/google/android/gms/common/internal/zzm;
        //    49: aload           4
        //    51: monitorexit    
        //    52: getstatic       com/google/android/gms/common/zzc.zzp:Landroid/content/Context;
        //    55: invokestatic    com/google/android/gms/common/internal/Preconditions.checkNotNull:(Ljava/lang/Object;)Ljava/lang/Object;
        //    58: pop            
        //    59: new             Lcom/google/android/gms/common/zzk;
        //    62: dup            
        //    63: aload_0        
        //    64: aload_1        
        //    65: iload_2        
        //    66: iload_3        
        //    67: invokespecial   com/google/android/gms/common/zzk.<init>:(Ljava/lang/String;Lcom/google/android/gms/common/zze;ZZ)V
        //    70: astore          4
        //    72: getstatic       com/google/android/gms/common/zzc.zzn:Lcom/google/android/gms/common/internal/zzm;
        //    75: aload           4
        //    77: getstatic       com/google/android/gms/common/zzc.zzp:Landroid/content/Context;
        //    80: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    83: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.wrap:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    86: invokeinterface com/google/android/gms/common/internal/zzm.zza:(Lcom/google/android/gms/common/zzk;Lcom/google/android/gms/dynamic/IObjectWrapper;)Z
        //    91: istore_3       
        //    92: iload_3        
        //    93: ifeq            174
        //    96: invokestatic    com/google/android/gms/common/zzm.zze:()Lcom/google/android/gms/common/zzm;
        //    99: areturn        
        //   100: astore_0       
        //   101: aload           4
        //   103: monitorexit    
        //   104: aload_0        
        //   105: athrow         
        //   106: astore_1       
        //   107: ldc             "GoogleCertificates"
        //   109: ldc             "Failed to get Google certificates from remote"
        //   111: aload_1        
        //   112: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   115: pop            
        //   116: aload_1        
        //   117: invokevirtual   com/google/android/gms/dynamite/DynamiteModule$LoadingException.getMessage:()Ljava/lang/String;
        //   120: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   123: astore_0       
        //   124: aload_0        
        //   125: invokevirtual   java/lang/String.length:()I
        //   128: ifeq            144
        //   131: ldc             "module init: "
        //   133: aload_0        
        //   134: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   137: astore_0       
        //   138: aload_0        
        //   139: aload_1        
        //   140: invokestatic    com/google/android/gms/common/zzm.zza:(Ljava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/common/zzm;
        //   143: areturn        
        //   144: new             Ljava/lang/String;
        //   147: dup            
        //   148: ldc             "module init: "
        //   150: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   153: astore_0       
        //   154: goto            138
        //   157: astore_0       
        //   158: ldc             "GoogleCertificates"
        //   160: ldc             "Failed to get Google certificates from remote"
        //   162: aload_0        
        //   163: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   166: pop            
        //   167: ldc             "module call"
        //   169: aload_0        
        //   170: invokestatic    com/google/android/gms/common/zzm.zza:(Ljava/lang/String;Ljava/lang/Throwable;)Lcom/google/android/gms/common/zzm;
        //   173: areturn        
        //   174: new             Lcom/google/android/gms/common/zzd;
        //   177: dup            
        //   178: iload_2        
        //   179: aload_0        
        //   180: aload_1        
        //   181: invokespecial   com/google/android/gms/common/zzd.<init>:(ZLjava/lang/String;Lcom/google/android/gms/common/zze;)V
        //   184: invokestatic    com/google/android/gms/common/zzm.zza:(Ljava/util/concurrent/Callable;)Lcom/google/android/gms/common/zzm;
        //   187: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                             
        //  -----  -----  -----  -----  -----------------------------------------------------------------
        //  0      21     106    157    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        //  21     49     100    106    Any
        //  49     52     100    106    Any
        //  72     92     157    174    Landroid/os/RemoteException;
        //  101    104    100    106    Any
        //  104    106    106    157    Lcom/google/android/gms/dynamite/DynamiteModule$LoadingException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0138:
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
