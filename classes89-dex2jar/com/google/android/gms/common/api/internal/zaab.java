// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.WeakHashMap;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;

public final class zaab
{
    private final Map<BasePendingResult<?>, Boolean> zafk;
    private final Map<TaskCompletionSource<?>, Boolean> zafl;
    
    public zaab() {
        this.zafk = Collections.synchronizedMap(new WeakHashMap<BasePendingResult<?>, Boolean>());
        this.zafl = Collections.synchronizedMap(new WeakHashMap<TaskCompletionSource<?>, Boolean>());
    }
    
    private final void zaa(final boolean p0, final Status p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/android/gms/common/api/internal/zaab.zafk:Ljava/util/Map;
        //     4: astore_3       
        //     5: aload_3        
        //     6: monitorenter   
        //     7: new             Ljava/util/HashMap;
        //    10: dup            
        //    11: aload_0        
        //    12: getfield        com/google/android/gms/common/api/internal/zaab.zafk:Ljava/util/Map;
        //    15: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
        //    18: astore          4
        //    20: aload_3        
        //    21: monitorexit    
        //    22: aload_0        
        //    23: getfield        com/google/android/gms/common/api/internal/zaab.zafl:Ljava/util/Map;
        //    26: astore          5
        //    28: aload           5
        //    30: monitorenter   
        //    31: new             Ljava/util/HashMap;
        //    34: dup            
        //    35: aload_0        
        //    36: getfield        com/google/android/gms/common/api/internal/zaab.zafl:Ljava/util/Map;
        //    39: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
        //    42: astore_3       
        //    43: aload           5
        //    45: monitorexit    
        //    46: aload           4
        //    48: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //    53: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //    58: astore          4
        //    60: aload           4
        //    62: invokeinterface java/util/Iterator.hasNext:()Z
        //    67: ifeq            130
        //    70: aload           4
        //    72: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    77: checkcast       Ljava/util/Map$Entry;
        //    80: astore          5
        //    82: iload_1        
        //    83: ifne            102
        //    86: aload           5
        //    88: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //    93: checkcast       Ljava/lang/Boolean;
        //    96: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    99: ifeq            60
        //   102: aload           5
        //   104: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   109: checkcast       Lcom/google/android/gms/common/api/internal/BasePendingResult;
        //   112: aload_2        
        //   113: invokevirtual   com/google/android/gms/common/api/internal/BasePendingResult.zab:(Lcom/google/android/gms/common/api/Status;)V
        //   116: goto            60
        //   119: astore_2       
        //   120: aload_3        
        //   121: monitorexit    
        //   122: aload_2        
        //   123: athrow         
        //   124: astore_2       
        //   125: aload           5
        //   127: monitorexit    
        //   128: aload_2        
        //   129: athrow         
        //   130: aload_3        
        //   131: invokeinterface java/util/Map.entrySet:()Ljava/util/Set;
        //   136: invokeinterface java/util/Set.iterator:()Ljava/util/Iterator;
        //   141: astore_3       
        //   142: aload_3        
        //   143: invokeinterface java/util/Iterator.hasNext:()Z
        //   148: ifeq            207
        //   151: aload_3        
        //   152: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //   157: checkcast       Ljava/util/Map$Entry;
        //   160: astore          4
        //   162: iload_1        
        //   163: ifne            182
        //   166: aload           4
        //   168: invokeinterface java/util/Map$Entry.getValue:()Ljava/lang/Object;
        //   173: checkcast       Ljava/lang/Boolean;
        //   176: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   179: ifeq            142
        //   182: aload           4
        //   184: invokeinterface java/util/Map$Entry.getKey:()Ljava/lang/Object;
        //   189: checkcast       Lcom/google/android/gms/tasks/TaskCompletionSource;
        //   192: new             Lcom/google/android/gms/common/api/ApiException;
        //   195: dup            
        //   196: aload_2        
        //   197: invokespecial   com/google/android/gms/common/api/ApiException.<init>:(Lcom/google/android/gms/common/api/Status;)V
        //   200: invokevirtual   com/google/android/gms/tasks/TaskCompletionSource.trySetException:(Ljava/lang/Exception;)Z
        //   203: pop            
        //   204: goto            142
        //   207: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  7      22     119    124    Any
        //  31     46     124    130    Any
        //  120    122    119    124    Any
        //  125    128    124    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0060:
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
    
    final void zaa(final BasePendingResult<? extends Result> basePendingResult, final boolean b) {
        this.zafk.put(basePendingResult, b);
        basePendingResult.addStatusListener((PendingResult.StatusListener)new zaac(this, basePendingResult));
    }
    
    final <TResult> void zaa(final TaskCompletionSource<TResult> taskCompletionSource, final boolean b) {
        this.zafl.put(taskCompletionSource, b);
        taskCompletionSource.getTask().addOnCompleteListener((OnCompleteListener)new zaad(this, taskCompletionSource));
    }
    
    final boolean zaag() {
        return !this.zafk.isEmpty() || !this.zafl.isEmpty();
    }
    
    public final void zaah() {
        this.zaa(false, GoogleApiManager.zahx);
    }
    
    public final void zaai() {
        this.zaa(true, zacp.zakx);
    }
}
