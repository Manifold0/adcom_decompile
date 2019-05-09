// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.AdRequest;
import android.os.RemoteException;
import com.google.android.gms.ads.reward.RewardedVideoAdListener;
import android.content.Context;
import com.google.android.gms.ads.reward.RewardedVideoAd;

@zzadh
public final class zzahm implements RewardedVideoAd
{
    private final Context mContext;
    private final Object mLock;
    private String zzadr;
    private final zzagz zzclj;
    private final zzahj zzclk;
    
    public zzahm(final Context mContext, final zzagz zzclj) {
        this.mLock = new Object();
        this.zzclk = new zzahj(null);
        this.zzclj = zzclj;
        this.mContext = mContext;
    }
    
    private final void zza(final String p0, final zzlw p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    11: ifnonnull       17
        //    14: aload_3        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    21: new             Lcom/google/android/gms/internal/ads/zzahk;
        //    24: dup            
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/internal/ads/zzahm.mContext:Landroid/content/Context;
        //    29: aload_2        
        //    30: invokestatic    com/google/android/gms/internal/ads/zzjm.zza:(Landroid/content/Context;Lcom/google/android/gms/internal/ads/zzlw;)Lcom/google/android/gms/internal/ads/zzjj;
        //    33: aload_1        
        //    34: invokespecial   com/google/android/gms/internal/ads/zzahk.<init>:(Lcom/google/android/gms/internal/ads/zzjj;Ljava/lang/String;)V
        //    37: invokeinterface com/google/android/gms/internal/ads/zzagz.zza:(Lcom/google/android/gms/internal/ads/zzahk;)V
        //    42: aload_3        
        //    43: monitorexit    
        //    44: return         
        //    45: astore_1       
        //    46: aload_3        
        //    47: monitorexit    
        //    48: aload_1        
        //    49: athrow         
        //    50: astore_1       
        //    51: ldc             "#007 Could not call remote method."
        //    53: aload_1        
        //    54: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    57: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     45     50     Any
        //  17     42     50     60     Landroid/os/RemoteException;
        //  17     42     45     50     Any
        //  42     44     45     50     Any
        //  46     48     45     50     Any
        //  51     57     45     50     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public final void destroy() {
        this.destroy(null);
    }
    
    @Override
    public final void destroy(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclk:Lcom/google/android/gms/internal/ads/zzahj;
        //    11: aconst_null    
        //    12: invokevirtual   com/google/android/gms/internal/ads/zzahj.setRewardedVideoAdListener:(Lcom/google/android/gms/ads/reward/RewardedVideoAdListener;)V
        //    15: aload_0        
        //    16: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    19: ifnonnull       25
        //    22: aload_2        
        //    23: monitorexit    
        //    24: return         
        //    25: aload_0        
        //    26: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    29: aload_1        
        //    30: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.wrap:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    33: invokeinterface com/google/android/gms/internal/ads/zzagz.zzf:(Lcom/google/android/gms/dynamic/IObjectWrapper;)V
        //    38: aload_2        
        //    39: monitorexit    
        //    40: return         
        //    41: astore_1       
        //    42: aload_2        
        //    43: monitorexit    
        //    44: aload_1        
        //    45: athrow         
        //    46: astore_1       
        //    47: ldc             "#007 Could not call remote method."
        //    49: aload_1        
        //    50: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    53: goto            38
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      24     41     46     Any
        //  25     38     46     56     Landroid/os/RemoteException;
        //  25     38     41     46     Any
        //  38     40     41     46     Any
        //  42     44     41     46     Any
        //  47     53     41     46     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0025:
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
    public final String getMediationAdapterClassName() {
        try {
            if (this.zzclj != null) {
                return this.zzclj.getMediationAdapterClassName();
            }
        }
        catch (RemoteException ex) {
            zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
        }
        return null;
    }
    
    @Override
    public final RewardedVideoAdListener getRewardedVideoAdListener() {
        synchronized (this.mLock) {
            return this.zzclk.getRewardedVideoAdListener();
        }
    }
    
    @Override
    public final String getUserId() {
        synchronized (this.mLock) {
            return this.zzadr;
        }
    }
    
    @Override
    public final boolean isLoaded() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    11: ifnonnull       18
        //    14: aload_2        
        //    15: monitorexit    
        //    16: iconst_0       
        //    17: ireturn        
        //    18: aload_0        
        //    19: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    22: invokeinterface com/google/android/gms/internal/ads/zzagz.isLoaded:()Z
        //    27: istore_1       
        //    28: aload_2        
        //    29: monitorexit    
        //    30: iload_1        
        //    31: ireturn        
        //    32: astore_3       
        //    33: aload_2        
        //    34: monitorexit    
        //    35: aload_3        
        //    36: athrow         
        //    37: astore_3       
        //    38: ldc             "#007 Could not call remote method."
        //    40: aload_3        
        //    41: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    44: aload_2        
        //    45: monitorexit    
        //    46: iconst_0       
        //    47: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     32     37     Any
        //  18     28     37     48     Landroid/os/RemoteException;
        //  18     28     32     37     Any
        //  28     30     32     37     Any
        //  33     35     32     37     Any
        //  38     46     32     37     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0018:
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
    public final void loadAd(final String s, final AdRequest adRequest) {
        this.zza(s, adRequest.zzay());
    }
    
    @Override
    public final void loadAd(final String s, final PublisherAdRequest publisherAdRequest) {
        this.zza(s, publisherAdRequest.zzay());
    }
    
    @Override
    public final void pause() {
        this.pause(null);
    }
    
    @Override
    public final void pause(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    21: aload_1        
        //    22: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.wrap:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    25: invokeinterface com/google/android/gms/internal/ads/zzagz.zzd:(Lcom/google/android/gms/dynamic/IObjectWrapper;)V
        //    30: aload_2        
        //    31: monitorexit    
        //    32: return         
        //    33: astore_1       
        //    34: aload_2        
        //    35: monitorexit    
        //    36: aload_1        
        //    37: athrow         
        //    38: astore_1       
        //    39: ldc             "#007 Could not call remote method."
        //    41: aload_1        
        //    42: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    45: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     33     38     Any
        //  17     30     38     48     Landroid/os/RemoteException;
        //  17     30     33     38     Any
        //  30     32     33     38     Any
        //  34     36     33     38     Any
        //  39     45     33     38     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public final void resume() {
        this.resume(null);
    }
    
    @Override
    public final void resume(final Context p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_2       
        //     5: aload_2        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    11: ifnonnull       17
        //    14: aload_2        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    21: aload_1        
        //    22: invokestatic    com/google/android/gms/dynamic/ObjectWrapper.wrap:(Ljava/lang/Object;)Lcom/google/android/gms/dynamic/IObjectWrapper;
        //    25: invokeinterface com/google/android/gms/internal/ads/zzagz.zze:(Lcom/google/android/gms/dynamic/IObjectWrapper;)V
        //    30: aload_2        
        //    31: monitorexit    
        //    32: return         
        //    33: astore_1       
        //    34: aload_2        
        //    35: monitorexit    
        //    36: aload_1        
        //    37: athrow         
        //    38: astore_1       
        //    39: ldc             "#007 Could not call remote method."
        //    41: aload_1        
        //    42: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    45: goto            30
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     33     38     Any
        //  17     30     38     48     Landroid/os/RemoteException;
        //  17     30     33     38     Any
        //  30     32     33     38     Any
        //  34     36     33     38     Any
        //  39     45     33     38     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
    public final void setImmersiveMode(final boolean immersiveMode) {
        synchronized (this.mLock) {
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.setImmersiveMode(immersiveMode);
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public final void setRewardedVideoAdListener(final RewardedVideoAdListener rewardedVideoAdListener) {
        synchronized (this.mLock) {
            this.zzclk.setRewardedVideoAdListener(rewardedVideoAdListener);
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.zza(this.zzclk);
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public final void setUserId(final String s) {
        synchronized (this.mLock) {
            this.zzadr = s;
            if (this.zzclj == null) {
                return;
            }
            try {
                this.zzclj.setUserId(s);
            }
            catch (RemoteException ex) {
                zzane.zzd("#007 Could not call remote method.", (Throwable)ex);
            }
        }
    }
    
    @Override
    public final void show() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/internal/ads/zzahm.mLock:Ljava/lang/Object;
        //     4: astore_1       
        //     5: aload_1        
        //     6: monitorenter   
        //     7: aload_0        
        //     8: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    11: ifnonnull       17
        //    14: aload_1        
        //    15: monitorexit    
        //    16: return         
        //    17: aload_0        
        //    18: getfield        com/google/android/gms/internal/ads/zzahm.zzclj:Lcom/google/android/gms/internal/ads/zzagz;
        //    21: invokeinterface com/google/android/gms/internal/ads/zzagz.show:()V
        //    26: aload_1        
        //    27: monitorexit    
        //    28: return         
        //    29: astore_2       
        //    30: aload_1        
        //    31: monitorexit    
        //    32: aload_2        
        //    33: athrow         
        //    34: astore_2       
        //    35: ldc             "#007 Could not call remote method."
        //    37: aload_2        
        //    38: invokestatic    com/google/android/gms/internal/ads/zzane.zzd:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //    41: goto            26
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  7      16     29     34     Any
        //  17     26     34     44     Landroid/os/RemoteException;
        //  17     26     29     34     Any
        //  26     28     29     34     Any
        //  30     32     29     34     Any
        //  35     41     29     34     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0017:
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
