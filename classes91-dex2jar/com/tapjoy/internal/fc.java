// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.internal;

import java.util.Observable;
import com.tapjoy.TJError;
import com.tapjoy.TJActionRequest;
import java.util.Observer;
import com.tapjoy.TapjoyConnectCore;
import com.tapjoy.TJPlacement;
import com.tapjoy.TJPlacementListener;
import android.content.Context;

abstract class fc
{
    volatile a b;
    
    protected abstract TJPlacement a(final Context p0, final TJPlacementListener p1, final Object p2);
    
    protected abstract String a(final Object p0);
    
    protected boolean a() {
        return !TapjoyConnectCore.isFullScreenViewOpen();
    }
    
    protected boolean a(final Observer observer) {
        Label_0028: {
            if (!TapjoyConnectCore.isFullScreenViewOpen()) {
                break Label_0028;
            }
            ev.e.addObserver(observer);
            if (!TapjoyConnectCore.isFullScreenViewOpen()) {
                ev.e.deleteObserver(observer);
                break Label_0028;
            }
            return false;
        }
        if (!gc.a().d()) {
            ev.c.addObserver(observer);
            if (!gc.a().d()) {
                return false;
            }
            ev.c.deleteObserver(observer);
        }
        return true;
    }
    
    protected a b(final Object o) {
        return new a(o);
    }
    
    public final boolean c(final Object o) {
        if (this.a()) {
            a b = null;
            synchronized (this) {
                if (this.b == null) {
                    b = this.b(o);
                    this.b = b;
                }
                // monitorexit(this)
                if (b != null) {
                    b.a();
                    return true;
                }
            }
        }
        return false;
    }
    
    final class a implements TJPlacementListener, Observer
    {
        private final Object b;
        private final el c;
        private volatile boolean d;
        private TJPlacement e;
        
        a(final fc fc, final Object o) {
            this(fc, o, new el(10000L));
        }
        
        a(final Object b, final el c) {
            this.b = b;
            this.c = c;
        }
        
        private void a(final String p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: monitorenter   
            //     2: aload_0        
            //     3: getfield        com/tapjoy/internal/fc$a.a:Lcom/tapjoy/internal/fc;
            //     6: aload_0        
            //     7: getfield        com/tapjoy/internal/fc$a.b:Ljava/lang/Object;
            //    10: invokevirtual   com/tapjoy/internal/fc.a:(Ljava/lang/Object;)Ljava/lang/String;
            //    13: astore_2       
            //    14: aload_1        
            //    15: ifnonnull       100
            //    18: ldc             "SystemPlacement"
            //    20: new             Ljava/lang/StringBuilder;
            //    23: dup            
            //    24: ldc             "Placement "
            //    26: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //    29: aload_2        
            //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    33: ldc             " is presented now"
            //    35: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    38: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    41: invokestatic    com/tapjoy/TapjoyLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //    44: aload_0        
            //    45: iconst_1       
            //    46: putfield        com/tapjoy/internal/fc$a.d:Z
            //    49: aload_0        
            //    50: aconst_null    
            //    51: putfield        com/tapjoy/internal/fc$a.e:Lcom/tapjoy/TJPlacement;
            //    54: getstatic       com/tapjoy/internal/ev.a:Lcom/tapjoy/internal/ev$a;
            //    57: aload_0        
            //    58: invokevirtual   com/tapjoy/internal/ev$a.deleteObserver:(Ljava/util/Observer;)V
            //    61: getstatic       com/tapjoy/internal/ev.e:Lcom/tapjoy/internal/ev$a;
            //    64: aload_0        
            //    65: invokevirtual   com/tapjoy/internal/ev$a.deleteObserver:(Ljava/util/Observer;)V
            //    68: getstatic       com/tapjoy/internal/ev.c:Lcom/tapjoy/internal/ev$a;
            //    71: aload_0        
            //    72: invokevirtual   com/tapjoy/internal/ev$a.deleteObserver:(Ljava/util/Observer;)V
            //    75: aload_0        
            //    76: monitorexit    
            //    77: aload_0        
            //    78: getfield        com/tapjoy/internal/fc$a.a:Lcom/tapjoy/internal/fc;
            //    81: astore_1       
            //    82: aload_1        
            //    83: monitorenter   
            //    84: aload_1        
            //    85: getfield        com/tapjoy/internal/fc.b:Lcom/tapjoy/internal/fc$a;
            //    88: aload_0        
            //    89: if_acmpne       97
            //    92: aload_1        
            //    93: aconst_null    
            //    94: putfield        com/tapjoy/internal/fc.b:Lcom/tapjoy/internal/fc$a;
            //    97: aload_1        
            //    98: monitorexit    
            //    99: return         
            //   100: ldc             "SystemPlacement"
            //   102: new             Ljava/lang/StringBuilder;
            //   105: dup            
            //   106: ldc             "Cannot show placement "
            //   108: invokespecial   java/lang/StringBuilder.<init>:(Ljava/lang/String;)V
            //   111: aload_2        
            //   112: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   115: ldc             " now ("
            //   117: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   120: aload_1        
            //   121: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   124: ldc             ")"
            //   126: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   129: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   132: invokestatic    com/tapjoy/TapjoyLog.i:(Ljava/lang/String;Ljava/lang/String;)V
            //   135: goto            44
            //   138: astore_1       
            //   139: aload_0        
            //   140: monitorexit    
            //   141: aload_1        
            //   142: athrow         
            //   143: astore_2       
            //   144: aload_1        
            //   145: monitorexit    
            //   146: aload_2        
            //   147: athrow         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  2      14     138    143    Any
            //  18     44     138    143    Any
            //  44     77     138    143    Any
            //  84     97     143    148    Any
            //  97     99     143    148    Any
            //  100    135    138    143    Any
            //  139    141    138    143    Any
            //  144    146    143    148    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0097:
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        final void a() {
            synchronized (this) {
                if (this.d) {
                    return;
                }
                if (this.c.a()) {
                    this.a("Timed out");
                    return;
                }
            }
            if (!TapjoyConnectCore.isConnected()) {
                ev.a.addObserver(this);
                if (!TapjoyConnectCore.isConnected()) {
                    // monitorexit(this)
                    return;
                }
                ev.a.deleteObserver(this);
            }
            if (this.e == null) {
                if (!fc.this.a()) {
                    this.a("Cannot request");
                    // monitorexit(this)
                    return;
                }
                (this.e = fc.this.a(TapjoyConnectCore.getContext(), this, this.b)).requestContent();
            }
            // monitorexit(this)
            else {
                if (!this.e.isContentReady()) {
                    // monitorexit(this)
                    return;
                }
                if (!fc.this.a(this)) {
                    // monitorexit(this)
                    return;
                }
                this.e.showContent();
                this.a(null);
            }
            // monitorexit(this)
        }
        
        @Override
        public final void onContentDismiss(final TJPlacement tjPlacement) {
        }
        
        @Override
        public final void onContentReady(final TJPlacement tjPlacement) {
            this.a();
        }
        
        @Override
        public final void onContentShow(final TJPlacement tjPlacement) {
        }
        
        @Override
        public final void onPurchaseRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s) {
        }
        
        @Override
        public final void onRequestFailure(final TJPlacement tjPlacement, final TJError tjError) {
            this.a(tjError.message);
        }
        
        @Override
        public final void onRequestSuccess(final TJPlacement tjPlacement) {
        }
        
        @Override
        public final void onRewardRequest(final TJPlacement tjPlacement, final TJActionRequest tjActionRequest, final String s, final int n) {
        }
        
        @Override
        public final void update(final Observable observable, final Object o) {
            this.a();
        }
    }
}
