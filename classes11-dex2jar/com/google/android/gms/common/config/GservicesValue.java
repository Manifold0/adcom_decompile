// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.config;

import com.google.android.gms.common.util.VisibleForTesting;
import android.util.Log;
import javax.annotation.concurrent.GuardedBy;
import java.util.HashSet;
import android.content.Context;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class GservicesValue<T>
{
    private static final Object sLock;
    private static zza zzbm;
    private static int zzbn;
    private static Context zzbo;
    @GuardedBy("sLock")
    private static HashSet<String> zzbp;
    protected final String mKey;
    protected final T zzbq;
    private T zzbr;
    
    static {
        sLock = new Object();
        GservicesValue.zzbm = null;
        GservicesValue.zzbn = 0;
    }
    
    protected GservicesValue(final String mKey, final T zzbq) {
        this.zzbr = null;
        this.mKey = mKey;
        this.zzbq = zzbq;
    }
    
    @KeepForSdk
    public static boolean isInitialized() {
        synchronized (GservicesValue.sLock) {
            // monitorexit(GservicesValue.sLock)
            return false;
        }
    }
    
    @KeepForSdk
    public static GservicesValue<Float> value(final String s, final Float n) {
        return new zzd(s, n);
    }
    
    @KeepForSdk
    public static GservicesValue<Integer> value(final String s, final Integer n) {
        return new zzc(s, n);
    }
    
    @KeepForSdk
    public static GservicesValue<Long> value(final String s, final Long n) {
        return new zzb(s, n);
    }
    
    @KeepForSdk
    public static GservicesValue<String> value(final String s, final String s2) {
        return new zze(s, s2);
    }
    
    @KeepForSdk
    public static GservicesValue<Boolean> value(final String s, final boolean b) {
        return new com.google.android.gms.common.config.zza(s, b);
    }
    
    private static boolean zzi() {
        synchronized (GservicesValue.sLock) {
            // monitorexit(GservicesValue.sLock)
            return false;
        }
    }
    
    @KeepForSdk
    public final T get() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/common/config/GservicesValue.zzbr:Ljava/lang/Object;
        //     4: ifnull          12
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/common/config/GservicesValue.zzbr:Ljava/lang/Object;
        //    11: areturn        
        //    12: invokestatic    android/os/StrictMode.allowThreadDiskReads:()Landroid/os/StrictMode$ThreadPolicy;
        //    15: astore_3       
        //    16: getstatic       com/google/android/gms/common/config/GservicesValue.sLock:Ljava/lang/Object;
        //    19: astore          4
        //    21: aload           4
        //    23: monitorenter   
        //    24: aload           4
        //    26: monitorexit    
        //    27: getstatic       com/google/android/gms/common/config/GservicesValue.sLock:Ljava/lang/Object;
        //    30: astore          4
        //    32: aload           4
        //    34: monitorenter   
        //    35: aconst_null    
        //    36: putstatic       com/google/android/gms/common/config/GservicesValue.zzbp:Ljava/util/HashSet;
        //    39: aconst_null    
        //    40: putstatic       com/google/android/gms/common/config/GservicesValue.zzbo:Landroid/content/Context;
        //    43: aload           4
        //    45: monitorexit    
        //    46: aload_0        
        //    47: aload_0        
        //    48: getfield        com/google/android/gms/common/config/GservicesValue.mKey:Ljava/lang/String;
        //    51: invokevirtual   com/google/android/gms/common/config/GservicesValue.zzd:(Ljava/lang/String;)Ljava/lang/Object;
        //    54: astore          4
        //    56: aload_3        
        //    57: invokestatic    android/os/StrictMode.setThreadPolicy:(Landroid/os/StrictMode$ThreadPolicy;)V
        //    60: aload           4
        //    62: areturn        
        //    63: astore_3       
        //    64: aload           4
        //    66: monitorexit    
        //    67: aload_3        
        //    68: athrow         
        //    69: astore_3       
        //    70: aload           4
        //    72: monitorexit    
        //    73: aload_3        
        //    74: athrow         
        //    75: astore          4
        //    77: invokestatic    android/os/Binder.clearCallingIdentity:()J
        //    80: lstore_1       
        //    81: aload_0        
        //    82: aload_0        
        //    83: getfield        com/google/android/gms/common/config/GservicesValue.mKey:Ljava/lang/String;
        //    86: invokevirtual   com/google/android/gms/common/config/GservicesValue.zzd:(Ljava/lang/String;)Ljava/lang/Object;
        //    89: astore          4
        //    91: lload_1        
        //    92: invokestatic    android/os/Binder.restoreCallingIdentity:(J)V
        //    95: aload_3        
        //    96: invokestatic    android/os/StrictMode.setThreadPolicy:(Landroid/os/StrictMode$ThreadPolicy;)V
        //    99: aload           4
        //   101: areturn        
        //   102: astore          4
        //   104: lload_1        
        //   105: invokestatic    android/os/Binder.restoreCallingIdentity:(J)V
        //   108: aload           4
        //   110: athrow         
        //   111: astore          4
        //   113: aload_3        
        //   114: invokestatic    android/os/StrictMode.setThreadPolicy:(Landroid/os/StrictMode$ThreadPolicy;)V
        //   117: aload           4
        //   119: athrow         
        //    Signature:
        //  ()TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                         
        //  -----  -----  -----  -----  -----------------------------
        //  24     27     63     69     Any
        //  35     46     69     75     Any
        //  46     56     75     111    Ljava/lang/SecurityException;
        //  46     56     111    120    Any
        //  64     67     63     69     Any
        //  70     73     69     75     Any
        //  77     81     111    120    Any
        //  81     91     102    111    Any
        //  91     95     111    120    Any
        //  104    111    111    120    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 67, Size: 67
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
    
    @Deprecated
    @KeepForSdk
    public final T getBinderSafe() {
        return this.get();
    }
    
    @KeepForSdk
    @VisibleForTesting
    public void override(final T zzbr) {
        Log.w("GservicesValue", "GservicesValue.override(): test should probably call initForTests() first");
        this.zzbr = zzbr;
        synchronized (GservicesValue.sLock) {
            zzi();
        }
    }
    
    @KeepForSdk
    @VisibleForTesting
    public void resetOverride() {
        this.zzbr = null;
    }
    
    protected abstract T zzd(final String p0);
    
    private interface zza
    {
        Long getLong(final String p0, final Long p1);
        
        String getString(final String p0, final String p1);
        
        Boolean zza(final String p0, final Boolean p1);
        
        Float zza(final String p0, final Float p1);
        
        Integer zza(final String p0, final Integer p1);
    }
}
