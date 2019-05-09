// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.Binder;
import com.google.android.gms.common.internal.BaseGmsClient$BaseOnConnectionFailedListener;
import com.google.android.gms.common.internal.BaseGmsClient$BaseConnectionCallbacks;
import com.google.android.gms.ads.internal.zzbv;
import javax.annotation.concurrent.GuardedBy;
import android.support.annotation.Nullable;
import android.content.Context;

@zzadh
public final class zzhd
{
    @Nullable
    @GuardedBy("mLock")
    private Context mContext;
    private final Object mLock;
    private final Runnable zzajq;
    @Nullable
    @GuardedBy("mLock")
    private zzhk zzajr;
    @Nullable
    @GuardedBy("mLock")
    private zzho zzajs;
    
    public zzhd() {
        this.zzajq = new zzhe(this);
        this.mLock = new Object();
    }
    
    private final void connect() {
        synchronized (this.mLock) {
            if (this.mContext == null || this.zzajr != null) {
                return;
            }
            (this.zzajr = new zzhk(this.mContext, zzbv.zzez().zzsa(), (BaseGmsClient$BaseConnectionCallbacks)new zzhg(this), (BaseGmsClient$BaseOnConnectionFailedListener)new zzhh(this))).checkAvailabilityAndConnect();
        }
    }
    
    private final void disconnect() {
        synchronized (this.mLock) {
            if (this.zzajr == null) {
                return;
            }
            if (this.zzajr.isConnected() || this.zzajr.isConnecting()) {
                this.zzajr.disconnect();
            }
            this.zzajr = null;
            this.zzajs = null;
            Binder.flushPendingCommands();
        }
    }
    
    public final void initialize(final Context context) {
        if (context == null) {
            return;
        }
        synchronized (this.mLock) {
            if (this.mContext != null) {
                return;
            }
        }
        final Context context2;
        this.mContext = context2.getApplicationContext();
        if (zzkb.zzik().zzd(zznk.zzbdo)) {
            this.connect();
        }
        else if (zzkb.zzik().zzd(zznk.zzbdn)) {
            zzbv.zzen().zza(new zzhf(this));
        }
    }
    // monitorexit(o)
    
    public final zzhi zza(final zzhl p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzhd.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzhd.zzajs:Lcom/google/android/gms/internal/ads/zzho;
        //    11: ifnonnull       26
        //    14: new             Lcom/google/android/gms/internal/ads/zzhi;
        //    17: dup            
        //    18: invokespecial   com/google/android/gms/internal/ads/zzhi.<init>:()V
        //    21: astore_1       
        //    22: aload_2        
        //    23: monitorexit    
        //    24: aload_1        
        //    25: areturn        
        //    26: aload_0        
        //    27: getfield        com/google/android/gms/internal/ads/zzhd.zzajs:Lcom/google/android/gms/internal/ads/zzho;
        //    30: aload_1        
        //    31: invokeinterface com/google/android/gms/internal/ads/zzho.zza:(Lcom/google/android/gms/internal/ads/zzhl;)Lcom/google/android/gms/internal/ads/zzhi;
        //    36: astore_1       
        //    37: aload_2        
        //    38: monitorexit    
        //    39: aload_1        
        //    40: areturn        
        //    41: astore_1       
        //    42: aload_2        
        //    43: monitorexit    
        //    44: aload_1        
        //    45: athrow         
        //    46: astore_1       
        //    47: ldc             "Unable to call into cache service."
        //    49: aload_1        
        //    50: invokestatic    com/google/android/gms/internal/ads/zzakb.zzb:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    53: new             Lcom/google/android/gms/internal/ads/zzhi;
        //    56: dup            
        //    57: invokespecial   com/google/android/gms/internal/ads/zzhi.<init>:()V
        //    60: astore_1       
        //    61: aload_2        
        //    62: monitorexit    
        //    63: aload_1        
        //    64: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      24     41     46     Any
        //  26     37     46     65     Landroid/os/RemoteException;
        //  26     37     41     46     Any
        //  37     39     41     46     Any
        //  42     44     41     46     Any
        //  47     63     41     46     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0026:
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
    
    public final void zzhh() {
        if (zzkb.zzik().zzd(zznk.zzbdp)) {
            synchronized (this.mLock) {
                this.connect();
                zzbv.zzek();
                zzakk.zzcrm.removeCallbacks(this.zzajq);
                zzbv.zzek();
                zzakk.zzcrm.postDelayed(this.zzajq, (long)zzkb.zzik().zzd(zznk.zzbdq));
            }
        }
    }
}
