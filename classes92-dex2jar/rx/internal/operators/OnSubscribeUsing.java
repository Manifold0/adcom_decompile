// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import java.util.concurrent.atomic.AtomicBoolean;
import rx.Subscriber;
import rx.functions.Action0;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.functions.Action1;
import rx.Observable;

public final class OnSubscribeUsing<T, Resource> implements OnSubscribe<T>
{
    private final Action1<? super Resource> dispose;
    private final boolean disposeEagerly;
    private final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory;
    private final Func0<Resource> resourceFactory;
    
    public OnSubscribeUsing(final Func0<Resource> resourceFactory, final Func1<? super Resource, ? extends Observable<? extends T>> observableFactory, final Action1<? super Resource> dispose, final boolean disposeEagerly) {
        this.resourceFactory = resourceFactory;
        this.observableFactory = observableFactory;
        this.dispose = dispose;
        this.disposeEagerly = disposeEagerly;
    }
    
    private Throwable dispose(final Action0 action0) {
        try {
            action0.call();
            return null;
        }
        catch (Throwable t) {
            return t;
        }
    }
    
    @Override
    public void call(final Subscriber<? super T> p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        rx/internal/operators/OnSubscribeUsing.resourceFactory:Lrx/functions/Func0;
        //     4: invokeinterface rx/functions/Func0.call:()Ljava/lang/Object;
        //     9: astore_2       
        //    10: new             Lrx/internal/operators/OnSubscribeUsing$DisposeAction;
        //    13: dup            
        //    14: aload_0        
        //    15: getfield        rx/internal/operators/OnSubscribeUsing.dispose:Lrx/functions/Action1;
        //    18: aload_2        
        //    19: invokespecial   rx/internal/operators/OnSubscribeUsing$DisposeAction.<init>:(Lrx/functions/Action1;Ljava/lang/Object;)V
        //    22: astore_3       
        //    23: aload_1        
        //    24: aload_3        
        //    25: invokevirtual   rx/Subscriber.add:(Lrx/Subscription;)V
        //    28: aload_0        
        //    29: getfield        rx/internal/operators/OnSubscribeUsing.observableFactory:Lrx/functions/Func1;
        //    32: aload_2        
        //    33: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
        //    38: checkcast       Lrx/Observable;
        //    41: astore_2       
        //    42: aload_0        
        //    43: getfield        rx/internal/operators/OnSubscribeUsing.disposeEagerly:Z
        //    46: ifeq            121
        //    49: aload_2        
        //    50: aload_3        
        //    51: invokevirtual   rx/Observable.doOnTerminate:(Lrx/functions/Action0;)Lrx/Observable;
        //    54: astore_2       
        //    55: aload_2        
        //    56: aload_1        
        //    57: invokestatic    rx/observers/Subscribers.wrap:(Lrx/Subscriber;)Lrx/Subscriber;
        //    60: invokevirtual   rx/Observable.unsafeSubscribe:(Lrx/Subscriber;)Lrx/Subscription;
        //    63: pop            
        //    64: return         
        //    65: astore_2       
        //    66: aload_0        
        //    67: aload_3        
        //    68: invokespecial   rx/internal/operators/OnSubscribeUsing.dispose:(Lrx/functions/Action0;)Ljava/lang/Throwable;
        //    71: astore_3       
        //    72: aload_2        
        //    73: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    76: aload_3        
        //    77: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    80: aload_3        
        //    81: ifnull          115
        //    84: aload_1        
        //    85: new             Lrx/exceptions/CompositeException;
        //    88: dup            
        //    89: iconst_2       
        //    90: anewarray       Ljava/lang/Throwable;
        //    93: dup            
        //    94: iconst_0       
        //    95: aload_2        
        //    96: aastore        
        //    97: dup            
        //    98: iconst_1       
        //    99: aload_3        
        //   100: aastore        
        //   101: invokespecial   rx/exceptions/CompositeException.<init>:([Ljava/lang/Throwable;)V
        //   104: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
        //   107: return         
        //   108: astore_2       
        //   109: aload_2        
        //   110: aload_1        
        //   111: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;)V
        //   114: return         
        //   115: aload_1        
        //   116: aload_2        
        //   117: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
        //   120: return         
        //   121: aload_2        
        //   122: aload_3        
        //   123: invokevirtual   rx/Observable.doAfterTerminate:(Lrx/functions/Action0;)Lrx/Observable;
        //   126: astore_2       
        //   127: goto            55
        //   130: astore_2       
        //   131: aload_0        
        //   132: aload_3        
        //   133: invokespecial   rx/internal/operators/OnSubscribeUsing.dispose:(Lrx/functions/Action0;)Ljava/lang/Throwable;
        //   136: astore_3       
        //   137: aload_2        
        //   138: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //   141: aload_3        
        //   142: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //   145: aload_3        
        //   146: ifnull          173
        //   149: aload_1        
        //   150: new             Lrx/exceptions/CompositeException;
        //   153: dup            
        //   154: iconst_2       
        //   155: anewarray       Ljava/lang/Throwable;
        //   158: dup            
        //   159: iconst_0       
        //   160: aload_2        
        //   161: aastore        
        //   162: dup            
        //   163: iconst_1       
        //   164: aload_3        
        //   165: aastore        
        //   166: invokespecial   rx/exceptions/CompositeException.<init>:([Ljava/lang/Throwable;)V
        //   169: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
        //   172: return         
        //   173: aload_1        
        //   174: aload_2        
        //   175: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
        //   178: return         
        //    Signature:
        //  (Lrx/Subscriber<-TT;>;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      28     108    115    Ljava/lang/Throwable;
        //  28     42     65     121    Ljava/lang/Throwable;
        //  42     55     108    115    Ljava/lang/Throwable;
        //  55     64     130    179    Ljava/lang/Throwable;
        //  66     80     108    115    Ljava/lang/Throwable;
        //  84     107    108    115    Ljava/lang/Throwable;
        //  115    120    108    115    Ljava/lang/Throwable;
        //  121    127    108    115    Ljava/lang/Throwable;
        //  131    145    108    115    Ljava/lang/Throwable;
        //  149    172    108    115    Ljava/lang/Throwable;
        //  173    178    108    115    Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0055:
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
    
    static final class DisposeAction<Resource> extends AtomicBoolean implements Action0, Subscription
    {
        private static final long serialVersionUID = 4262875056400218316L;
        private Action1<? super Resource> dispose;
        private Resource resource;
        
        DisposeAction(final Action1<? super Resource> dispose, final Resource resource) {
            this.dispose = dispose;
            this.resource = resource;
            this.lazySet(false);
        }
        
        @Override
        public void call() {
            if (!this.compareAndSet(false, true)) {
                return;
            }
            try {
                this.dispose.call((Object)this.resource);
            }
            finally {
                this.resource = null;
                this.dispose = null;
            }
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.get();
        }
        
        @Override
        public void unsubscribe() {
            this.call();
        }
    }
}
