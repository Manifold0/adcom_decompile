// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import rx.Subscription;
import rx.exceptions.Exceptions;
import rx.SingleSubscriber;
import rx.functions.Func1;
import rx.functions.Func0;
import rx.functions.Action1;
import rx.Single;

public final class SingleOnSubscribeUsing<T, Resource> implements OnSubscribe<T>
{
    final Action1<? super Resource> disposeAction;
    final boolean disposeEagerly;
    final Func0<Resource> resourceFactory;
    final Func1<? super Resource, ? extends Single<? extends T>> singleFactory;
    
    public SingleOnSubscribeUsing(final Func0<Resource> resourceFactory, final Func1<? super Resource, ? extends Single<? extends T>> singleFactory, final Action1<? super Resource> disposeAction, final boolean disposeEagerly) {
        this.resourceFactory = resourceFactory;
        this.singleFactory = singleFactory;
        this.disposeAction = disposeAction;
        this.disposeEagerly = disposeEagerly;
    }
    
    @Override
    public void call(final SingleSubscriber<? super T> singleSubscriber) {
        Resource call = null;
        Single<? extends T> single3 = null;
        Label_0064: {
            try {
                call = this.resourceFactory.call();
                final SingleOnSubscribeUsing singleOnSubscribeUsing = this;
                final Func1<? super Resource, ? extends Single<? extends T>> func1 = singleOnSubscribeUsing.singleFactory;
                final Object o = call;
                final Single<? extends T> single = (Single<? extends T>)func1.call(o);
                final Single<? extends T> single2 = single;
                final Single<? extends T> single4;
                single3 = (single4 = single2);
                if (single4 == null) {
                    final SingleOnSubscribeUsing singleOnSubscribeUsing2 = this;
                    final SingleSubscriber<? super T> singleSubscriber2 = singleSubscriber;
                    final Resource resource = call;
                    final String s = "The single";
                    final NullPointerException ex = new NullPointerException(s);
                    singleOnSubscribeUsing2.handleSubscriptionTimeError(singleSubscriber2, resource, ex);
                    return;
                }
                break Label_0064;
            }
            catch (Throwable call) {
                Exceptions.throwIfFatal((Throwable)call);
                singleSubscriber.onError((Throwable)call);
                return;
            }
            try {
                final SingleOnSubscribeUsing singleOnSubscribeUsing = this;
                final Func1<? super Resource, ? extends Single<? extends T>> func1 = singleOnSubscribeUsing.singleFactory;
                final Object o = call;
                final Single<? extends T> single = (Single<? extends T>)func1.call(o);
                final Single<? extends T> single2 = single;
                final Single<? extends T> single4;
                single3 = (single4 = single2);
                if (single4 == null) {
                    final SingleOnSubscribeUsing singleOnSubscribeUsing2 = this;
                    final SingleSubscriber<? super T> singleSubscriber2 = singleSubscriber;
                    final Resource resource = call;
                    final String s = "The single";
                    final NullPointerException ex = new NullPointerException(s);
                    singleOnSubscribeUsing2.handleSubscriptionTimeError(singleSubscriber2, resource, ex);
                    return;
                }
            }
            catch (Throwable t) {
                this.handleSubscriptionTimeError(singleSubscriber, call, t);
                return;
            }
        }
        final SingleSubscriber<T> singleSubscriber3 = new SingleSubscriber<T>() {
            @Override
            public void onError(final Throwable t) {
                SingleOnSubscribeUsing.this.handleSubscriptionTimeError(singleSubscriber, call, t);
            }
            
            @Override
            public void onSuccess(final T p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.this$0:Lrx/internal/operators/SingleOnSubscribeUsing;
                //     4: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeEagerly:Z
                //     7: ifeq            26
                //    10: aload_0        
                //    11: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.this$0:Lrx/internal/operators/SingleOnSubscribeUsing;
                //    14: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeAction:Lrx/functions/Action1;
                //    17: aload_0        
                //    18: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.val$resource:Ljava/lang/Object;
                //    21: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
                //    26: aload_0        
                //    27: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.val$child:Lrx/SingleSubscriber;
                //    30: aload_1        
                //    31: invokevirtual   rx/SingleSubscriber.onSuccess:(Ljava/lang/Object;)V
                //    34: aload_0        
                //    35: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.this$0:Lrx/internal/operators/SingleOnSubscribeUsing;
                //    38: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeEagerly:Z
                //    41: ifne            60
                //    44: aload_0        
                //    45: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.this$0:Lrx/internal/operators/SingleOnSubscribeUsing;
                //    48: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeAction:Lrx/functions/Action1;
                //    51: aload_0        
                //    52: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.val$resource:Ljava/lang/Object;
                //    55: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
                //    60: return         
                //    61: astore_1       
                //    62: aload_1        
                //    63: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //    66: aload_0        
                //    67: getfield        rx/internal/operators/SingleOnSubscribeUsing$1.val$child:Lrx/SingleSubscriber;
                //    70: aload_1        
                //    71: invokevirtual   rx/SingleSubscriber.onError:(Ljava/lang/Throwable;)V
                //    74: return         
                //    75: astore_1       
                //    76: aload_1        
                //    77: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
                //    80: aload_1        
                //    81: invokestatic    rx/plugins/RxJavaHooks.onError:(Ljava/lang/Throwable;)V
                //    84: return         
                //    Signature:
                //  (TT;)V
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  10     26     61     75     Ljava/lang/Throwable;
                //  44     60     75     85     Ljava/lang/Throwable;
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
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1164)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
        };
        singleSubscriber.add(singleSubscriber3);
        single3.subscribe(singleSubscriber3);
    }
    
    void handleSubscriptionTimeError(final SingleSubscriber<? super T> p0, final Resource p1, final Throwable p2) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     4: aload_3        
        //     5: astore          4
        //     7: aload_0        
        //     8: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeEagerly:Z
        //    11: ifeq            27
        //    14: aload_0        
        //    15: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeAction:Lrx/functions/Action1;
        //    18: aload_2        
        //    19: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
        //    24: aload_3        
        //    25: astore          4
        //    27: aload_1        
        //    28: aload           4
        //    30: invokevirtual   rx/SingleSubscriber.onError:(Ljava/lang/Throwable;)V
        //    33: aload_0        
        //    34: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeEagerly:Z
        //    37: ifne            50
        //    40: aload_0        
        //    41: getfield        rx/internal/operators/SingleOnSubscribeUsing.disposeAction:Lrx/functions/Action1;
        //    44: aload_2        
        //    45: invokeinterface rx/functions/Action1.call:(Ljava/lang/Object;)V
        //    50: return         
        //    51: astore          4
        //    53: aload           4
        //    55: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    58: new             Lrx/exceptions/CompositeException;
        //    61: dup            
        //    62: iconst_2       
        //    63: anewarray       Ljava/lang/Throwable;
        //    66: dup            
        //    67: iconst_0       
        //    68: aload_3        
        //    69: aastore        
        //    70: dup            
        //    71: iconst_1       
        //    72: aload           4
        //    74: aastore        
        //    75: invokestatic    java/util/Arrays.asList:([Ljava/lang/Object;)Ljava/util/List;
        //    78: invokespecial   rx/exceptions/CompositeException.<init>:(Ljava/util/Collection;)V
        //    81: astore          4
        //    83: goto            27
        //    86: astore_1       
        //    87: aload_1        
        //    88: invokestatic    rx/exceptions/Exceptions.throwIfFatal:(Ljava/lang/Throwable;)V
        //    91: aload_1        
        //    92: invokestatic    rx/plugins/RxJavaHooks.onError:(Ljava/lang/Throwable;)V
        //    95: return         
        //    Signature:
        //  (Lrx/SingleSubscriber<-TT;>;TResource;Ljava/lang/Throwable;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  14     24     51     86     Ljava/lang/Throwable;
        //  40     50     86     96     Ljava/lang/Throwable;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0050:
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
