// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.concurrent.Executor;
import com.google.android.gms.ads.internal.zzbv;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CancellationException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public class zzaoj<T> implements zzanz<T>
{
    private final Object mLock;
    @GuardedBy("mLock")
    private T mValue;
    @GuardedBy("mLock")
    private boolean zzbuf;
    @GuardedBy("mLock")
    private Throwable zzcwf;
    @GuardedBy("mLock")
    private boolean zzcwg;
    private final zzaoa zzcwh;
    
    public zzaoj() {
        this.mLock = new Object();
        this.zzcwh = new zzaoa();
    }
    
    @GuardedBy("mLock")
    private final boolean zzso() {
        return this.zzcwf != null || this.zzcwg;
    }
    
    @Override
    public boolean cancel(final boolean b) {
        if (!b) {
            return false;
        }
        synchronized (this.mLock) {
            if (this.zzso()) {
                return false;
            }
        }
        this.zzbuf = true;
        this.zzcwg = true;
        this.mLock.notifyAll();
        this.zzcwh.zzsm();
        // monitorexit(o)
        return true;
    }
    
    @Override
    public T get() throws CancellationException, ExecutionException, InterruptedException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzaoj.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: invokespecial   com/google/android/gms/internal/ads/zzaoj.zzso:()Z
        //    11: istore_1       
        //    12: iload_1        
        //    13: ifne            23
        //    16: aload_0        
        //    17: getfield        com/google/android/gms/internal/ads/zzaoj.mLock:Ljava/lang/Object;
        //    20: invokevirtual   java/lang/Object.wait:()V
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/internal/ads/zzaoj.zzcwf:Ljava/lang/Throwable;
        //    27: ifnull          50
        //    30: new             Ljava/util/concurrent/ExecutionException;
        //    33: dup            
        //    34: aload_0        
        //    35: getfield        com/google/android/gms/internal/ads/zzaoj.zzcwf:Ljava/lang/Throwable;
        //    38: invokespecial   java/util/concurrent/ExecutionException.<init>:(Ljava/lang/Throwable;)V
        //    41: athrow         
        //    42: astore_3       
        //    43: aload_2        
        //    44: monitorexit    
        //    45: aload_3        
        //    46: athrow         
        //    47: astore_3       
        //    48: aload_3        
        //    49: athrow         
        //    50: aload_0        
        //    51: getfield        com/google/android/gms/internal/ads/zzaoj.zzbuf:Z
        //    54: ifeq            67
        //    57: new             Ljava/util/concurrent/CancellationException;
        //    60: dup            
        //    61: ldc             "SettableFuture was cancelled."
        //    63: invokespecial   java/util/concurrent/CancellationException.<init>:(Ljava/lang/String;)V
        //    66: athrow         
        //    67: aload_0        
        //    68: getfield        com/google/android/gms/internal/ads/zzaoj.mValue:Ljava/lang/Object;
        //    71: astore_3       
        //    72: aload_2        
        //    73: monitorexit    
        //    74: aload_3        
        //    75: areturn        
        //    Exceptions:
        //  throws java.util.concurrent.CancellationException
        //  throws java.util.concurrent.ExecutionException
        //  throws java.lang.InterruptedException
        //    Signature:
        //  ()TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  7      12     42     47     Any
        //  16     23     47     50     Ljava/lang/InterruptedException;
        //  16     23     42     47     Any
        //  23     42     42     47     Any
        //  43     45     42     47     Any
        //  48     50     42     47     Any
        //  50     67     42     47     Any
        //  67     74     42     47     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
    
    @Override
    public T get(final long p0, final TimeUnit p1) throws CancellationException, ExecutionException, InterruptedException, TimeoutException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzaoj.mLock:Ljava/lang/Object;
        //     4: astore          5
        //     6: aload           5
        //     8: monitorenter   
        //     9: aload_0        
        //    10: invokespecial   com/google/android/gms/internal/ads/zzaoj.zzso:()Z
        //    13: istore          4
        //    15: iload           4
        //    17: ifne            40
        //    20: aload_3        
        //    21: lload_1        
        //    22: invokevirtual   java/util/concurrent/TimeUnit.toMillis:(J)J
        //    25: lstore_1       
        //    26: lload_1        
        //    27: lconst_0       
        //    28: lcmp           
        //    29: ifeq            40
        //    32: aload_0        
        //    33: getfield        com/google/android/gms/internal/ads/zzaoj.mLock:Ljava/lang/Object;
        //    36: lload_1        
        //    37: invokevirtual   java/lang/Object.wait:(J)V
        //    40: aload_0        
        //    41: getfield        com/google/android/gms/internal/ads/zzaoj.zzcwf:Ljava/lang/Throwable;
        //    44: ifnull          68
        //    47: new             Ljava/util/concurrent/ExecutionException;
        //    50: dup            
        //    51: aload_0        
        //    52: getfield        com/google/android/gms/internal/ads/zzaoj.zzcwf:Ljava/lang/Throwable;
        //    55: invokespecial   java/util/concurrent/ExecutionException.<init>:(Ljava/lang/Throwable;)V
        //    58: athrow         
        //    59: astore_3       
        //    60: aload           5
        //    62: monitorexit    
        //    63: aload_3        
        //    64: athrow         
        //    65: astore_3       
        //    66: aload_3        
        //    67: athrow         
        //    68: aload_0        
        //    69: getfield        com/google/android/gms/internal/ads/zzaoj.zzcwg:Z
        //    72: ifne            85
        //    75: new             Ljava/util/concurrent/TimeoutException;
        //    78: dup            
        //    79: ldc             "SettableFuture timed out."
        //    81: invokespecial   java/util/concurrent/TimeoutException.<init>:(Ljava/lang/String;)V
        //    84: athrow         
        //    85: aload_0        
        //    86: getfield        com/google/android/gms/internal/ads/zzaoj.zzbuf:Z
        //    89: ifeq            102
        //    92: new             Ljava/util/concurrent/CancellationException;
        //    95: dup            
        //    96: ldc             "SettableFuture was cancelled."
        //    98: invokespecial   java/util/concurrent/CancellationException.<init>:(Ljava/lang/String;)V
        //   101: athrow         
        //   102: aload_0        
        //   103: getfield        com/google/android/gms/internal/ads/zzaoj.mValue:Ljava/lang/Object;
        //   106: astore_3       
        //   107: aload           5
        //   109: monitorexit    
        //   110: aload_3        
        //   111: areturn        
        //    Exceptions:
        //  throws java.util.concurrent.CancellationException
        //  throws java.util.concurrent.ExecutionException
        //  throws java.lang.InterruptedException
        //  throws java.util.concurrent.TimeoutException
        //    Signature:
        //  (JLjava/util/concurrent/TimeUnit;)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  9      15     59     65     Any
        //  20     26     65     68     Ljava/lang/InterruptedException;
        //  20     26     59     65     Any
        //  32     40     65     68     Ljava/lang/InterruptedException;
        //  32     40     59     65     Any
        //  40     59     59     65     Any
        //  60     63     59     65     Any
        //  66     68     59     65     Any
        //  68     85     59     65     Any
        //  85     102    59     65     Any
        //  102    110    59     65     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0040:
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
    
    @Override
    public boolean isCancelled() {
        synchronized (this.mLock) {
            return this.zzbuf;
        }
    }
    
    @Override
    public boolean isDone() {
        synchronized (this.mLock) {
            return this.zzso();
        }
    }
    
    public final void set(@Nullable final T t) {
        synchronized (this.mLock) {
            if (this.zzbuf) {
                return;
            }
            if (this.zzso()) {
                zzbv.zzeo().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.set");
                return;
            }
        }
        this.zzcwg = true;
        final T mValue;
        this.mValue = mValue;
        this.mLock.notifyAll();
        this.zzcwh.zzsm();
    }
    // monitorexit(o)
    
    public final void setException(final Throwable zzcwf) {
        synchronized (this.mLock) {
            if (this.zzbuf) {
                return;
            }
            if (this.zzso()) {
                zzbv.zzeo().zzb(new IllegalStateException("Provided SettableFuture with multiple values."), "SettableFuture.setException");
                return;
            }
        }
        this.zzcwf = zzcwf;
        this.mLock.notifyAll();
        this.zzcwh.zzsm();
    }
    // monitorexit(o)
    
    @Override
    public final void zza(final Runnable runnable, final Executor executor) {
        this.zzcwh.zza(runnable, executor);
    }
}
