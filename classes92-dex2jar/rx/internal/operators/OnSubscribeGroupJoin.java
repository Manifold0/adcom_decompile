// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import rx.subscriptions.CompositeSubscription;
import rx.subscriptions.RefCountSubscription;
import rx.Observer;
import java.util.HashMap;
import rx.Subscription;
import rx.observers.SerializedSubscriber;
import rx.Subscriber;
import rx.functions.Func2;
import rx.functions.Func1;
import rx.Observable;

public final class OnSubscribeGroupJoin<T1, T2, D1, D2, R> implements OnSubscribe<R>
{
    final Observable<T1> left;
    final Func1<? super T1, ? extends Observable<D1>> leftDuration;
    final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector;
    final Observable<T2> right;
    final Func1<? super T2, ? extends Observable<D2>> rightDuration;
    
    public OnSubscribeGroupJoin(final Observable<T1> left, final Observable<T2> right, final Func1<? super T1, ? extends Observable<D1>> leftDuration, final Func1<? super T2, ? extends Observable<D2>> rightDuration, final Func2<? super T1, ? super Observable<T2>, ? extends R> resultSelector) {
        this.left = left;
        this.right = right;
        this.leftDuration = leftDuration;
        this.rightDuration = rightDuration;
        this.resultSelector = resultSelector;
    }
    
    @Override
    public void call(final Subscriber<? super R> subscriber) {
        final ResultManager resultManager = new ResultManager(new SerializedSubscriber<Object>(subscriber));
        subscriber.add(resultManager);
        resultManager.init();
    }
    
    final class ResultManager extends HashMap<Integer, Observer<T2>> implements Subscription
    {
        private static final long serialVersionUID = -3035156013812425335L;
        final RefCountSubscription cancel;
        final CompositeSubscription group;
        boolean leftDone;
        int leftIds;
        boolean rightDone;
        int rightIds;
        final Map<Integer, T2> rightMap;
        final Subscriber<? super R> subscriber;
        
        public ResultManager(final Subscriber<? super R> subscriber) {
            this.rightMap = new HashMap<Integer, T2>();
            this.subscriber = subscriber;
            this.group = new CompositeSubscription();
            this.cancel = new RefCountSubscription(this.group);
        }
        
        void complete(final List<Observer<T2>> list) {
            if (list != null) {
                final Iterator<Observer<T2>> iterator = list.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onCompleted();
                }
                this.subscriber.onCompleted();
                this.cancel.unsubscribe();
            }
        }
        
        void errorAll(final Throwable t) {
            synchronized (this) {
                final ArrayList<Observer> list = (ArrayList<Observer>)new ArrayList<Object>(this.leftMap().values());
                this.leftMap().clear();
                this.rightMap.clear();
                // monitorexit(this)
                final Iterator<Object> iterator = list.iterator();
                while (iterator.hasNext()) {
                    iterator.next().onError(t);
                }
            }
            this.subscriber.onError(t);
            this.cancel.unsubscribe();
        }
        
        void errorMain(final Throwable t) {
            synchronized (this) {
                this.leftMap().clear();
                this.rightMap.clear();
                // monitorexit(this)
                this.subscriber.onError(t);
                this.cancel.unsubscribe();
            }
        }
        
        public void init() {
            final LeftObserver leftObserver = new LeftObserver();
            final RightObserver rightObserver = new RightObserver();
            this.group.add(leftObserver);
            this.group.add(rightObserver);
            OnSubscribeGroupJoin.this.left.unsafeSubscribe(leftObserver);
            OnSubscribeGroupJoin.this.right.unsafeSubscribe(rightObserver);
        }
        
        @Override
        public boolean isUnsubscribed() {
            return this.cancel.isUnsubscribed();
        }
        
        Map<Integer, Observer<T2>> leftMap() {
            return this;
        }
        
        @Override
        public void unsubscribe() {
            this.cancel.unsubscribe();
        }
        
        final class LeftDurationObserver extends Subscriber<D1>
        {
            final int id;
            boolean once;
            
            public LeftDurationObserver(final int id) {
                this.once = true;
                this.id = id;
            }
            
            @Override
            public void onCompleted() {
                if (!this.once) {
                    return;
                }
                this.once = false;
                synchronized (ResultManager.this) {
                    final Observer<T2> observer = ResultManager.this.leftMap().remove(this.id);
                    // monitorexit(this.this$1)
                    if (observer != null) {
                        observer.onCompleted();
                    }
                    ResultManager.this.group.remove(this);
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                ResultManager.this.errorMain(t);
            }
            
            @Override
            public void onNext(final D1 d1) {
                this.onCompleted();
            }
        }
        
        final class LeftObserver extends Subscriber<T1>
        {
            @Override
            public void onCompleted() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: astore_1       
                //     2: aload_0        
                //     3: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //     6: astore_2       
                //     7: aload_2        
                //     8: monitorenter   
                //     9: aload_0        
                //    10: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    13: iconst_1       
                //    14: putfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftDone:Z
                //    17: aload_0        
                //    18: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    21: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightDone:Z
                //    24: ifeq            71
                //    27: new             Ljava/util/ArrayList;
                //    30: dup            
                //    31: aload_0        
                //    32: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    35: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //    38: invokeinterface java/util/Map.values:()Ljava/util/Collection;
                //    43: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
                //    46: astore_1       
                //    47: aload_0        
                //    48: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    51: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //    54: invokeinterface java/util/Map.clear:()V
                //    59: aload_0        
                //    60: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    63: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightMap:Ljava/util/Map;
                //    66: invokeinterface java/util/Map.clear:()V
                //    71: aload_2        
                //    72: monitorexit    
                //    73: aload_0        
                //    74: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    77: aload_1        
                //    78: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.complete:(Ljava/util/List;)V
                //    81: return         
                //    82: astore_1       
                //    83: aload_2        
                //    84: monitorexit    
                //    85: aload_1        
                //    86: athrow         
                //    87: astore_1       
                //    88: goto            83
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type
                //  -----  -----  -----  -----  ----
                //  9      17     82     83     Any
                //  17     47     82     83     Any
                //  47     71     87     91     Any
                //  71     73     82     83     Any
                //  83     85     82     83     Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
            
            @Override
            public void onError(final Throwable t) {
                ResultManager.this.errorAll(t);
            }
            
            @Override
            public void onNext(final T1 p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: astore          5
                //     5: new             Lrx/observers/SerializedObserver;
                //     8: dup            
                //     9: aload           5
                //    11: invokespecial   rx/observers/SerializedObserver.<init>:(Lrx/Observer;)V
                //    14: astore_3       
                //    15: aload_0        
                //    16: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    19: astore          4
                //    21: aload           4
                //    23: monitorenter   
                //    24: aload_0        
                //    25: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    28: astore          6
                //    30: aload           6
                //    32: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftIds:I
                //    35: istore_2       
                //    36: aload           6
                //    38: iload_2        
                //    39: iconst_1       
                //    40: iadd           
                //    41: putfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftIds:I
                //    44: aload_0        
                //    45: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    48: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //    51: iload_2        
                //    52: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //    55: aload_3        
                //    56: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //    61: pop            
                //    62: aload           4
                //    64: monitorexit    
                //    65: new             Lrx/internal/operators/OnSubscribeGroupJoin$WindowObservableFunc;
                //    68: dup            
                //    69: aload           5
                //    71: aload_0        
                //    72: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    75: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.cancel:Lrx/subscriptions/RefCountSubscription;
                //    78: invokespecial   rx/internal/operators/OnSubscribeGroupJoin$WindowObservableFunc.<init>:(Lrx/Observable;Lrx/subscriptions/RefCountSubscription;)V
                //    81: invokestatic    rx/Observable.create:(Lrx/Observable$OnSubscribe;)Lrx/Observable;
                //    84: astore          4
                //    86: aload_0        
                //    87: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    90: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.this$0:Lrx/internal/operators/OnSubscribeGroupJoin;
                //    93: getfield        rx/internal/operators/OnSubscribeGroupJoin.leftDuration:Lrx/functions/Func1;
                //    96: aload_1        
                //    97: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
                //   102: checkcast       Lrx/Observable;
                //   105: astore          5
                //   107: new             Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftDurationObserver;
                //   110: dup            
                //   111: aload_0        
                //   112: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   115: iload_2        
                //   116: invokespecial   rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftDurationObserver.<init>:(Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;I)V
                //   119: astore          6
                //   121: aload_0        
                //   122: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   125: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.group:Lrx/subscriptions/CompositeSubscription;
                //   128: aload           6
                //   130: invokevirtual   rx/subscriptions/CompositeSubscription.add:(Lrx/Subscription;)V
                //   133: aload           5
                //   135: aload           6
                //   137: invokevirtual   rx/Observable.unsafeSubscribe:(Lrx/Subscriber;)Lrx/Subscription;
                //   140: pop            
                //   141: aload_0        
                //   142: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   145: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.this$0:Lrx/internal/operators/OnSubscribeGroupJoin;
                //   148: getfield        rx/internal/operators/OnSubscribeGroupJoin.resultSelector:Lrx/functions/Func2;
                //   151: aload_1        
                //   152: aload           4
                //   154: invokeinterface rx/functions/Func2.call:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //   159: astore          4
                //   161: aload_0        
                //   162: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   165: astore_1       
                //   166: aload_1        
                //   167: monitorenter   
                //   168: new             Ljava/util/ArrayList;
                //   171: dup            
                //   172: aload_0        
                //   173: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   176: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightMap:Ljava/util/Map;
                //   179: invokeinterface java/util/Map.values:()Ljava/util/Collection;
                //   184: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
                //   187: astore          5
                //   189: aload_1        
                //   190: monitorexit    
                //   191: aload_0        
                //   192: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$LeftObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   195: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.subscriber:Lrx/Subscriber;
                //   198: aload           4
                //   200: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
                //   203: aload           5
                //   205: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
                //   210: astore_1       
                //   211: aload_1        
                //   212: invokeinterface java/util/Iterator.hasNext:()Z
                //   217: ifeq            241
                //   220: aload_3        
                //   221: aload_1        
                //   222: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                //   227: invokeinterface rx/Observer.onNext:(Ljava/lang/Object;)V
                //   232: goto            211
                //   235: astore_1       
                //   236: aload_1        
                //   237: aload_0        
                //   238: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;)V
                //   241: return         
                //   242: astore_1       
                //   243: aload           4
                //   245: monitorexit    
                //   246: aload_1        
                //   247: athrow         
                //   248: astore_3       
                //   249: aload_1        
                //   250: monitorexit    
                //   251: aload_3        
                //   252: athrow         
                //    Signature:
                //  (TT1;)V
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      24     235    241    Ljava/lang/Throwable;
                //  24     65     242    248    Any
                //  65     168    235    241    Ljava/lang/Throwable;
                //  168    191    248    253    Any
                //  191    211    235    241    Ljava/lang/Throwable;
                //  211    232    235    241    Ljava/lang/Throwable;
                //  243    246    242    248    Any
                //  246    248    235    241    Ljava/lang/Throwable;
                //  249    251    248    253    Any
                //  251    253    235    241    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 120, Size: 120
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        }
        
        final class RightDurationObserver extends Subscriber<D2>
        {
            final int id;
            boolean once;
            
            public RightDurationObserver(final int id) {
                this.once = true;
                this.id = id;
            }
            
            @Override
            public void onCompleted() {
                if (!this.once) {
                    return;
                }
                this.once = false;
                synchronized (ResultManager.this) {
                    ResultManager.this.rightMap.remove(this.id);
                    // monitorexit(this.this$1)
                    ResultManager.this.group.remove(this);
                }
            }
            
            @Override
            public void onError(final Throwable t) {
                ResultManager.this.errorMain(t);
            }
            
            @Override
            public void onNext(final D2 d2) {
                this.onCompleted();
            }
        }
        
        final class RightObserver extends Subscriber<T2>
        {
            @Override
            public void onCompleted() {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: astore_1       
                //     2: aload_0        
                //     3: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //     6: astore_2       
                //     7: aload_2        
                //     8: monitorenter   
                //     9: aload_0        
                //    10: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    13: iconst_1       
                //    14: putfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightDone:Z
                //    17: aload_0        
                //    18: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    21: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftDone:Z
                //    24: ifeq            71
                //    27: new             Ljava/util/ArrayList;
                //    30: dup            
                //    31: aload_0        
                //    32: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    35: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //    38: invokeinterface java/util/Map.values:()Ljava/util/Collection;
                //    43: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
                //    46: astore_1       
                //    47: aload_0        
                //    48: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    51: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //    54: invokeinterface java/util/Map.clear:()V
                //    59: aload_0        
                //    60: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    63: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightMap:Ljava/util/Map;
                //    66: invokeinterface java/util/Map.clear:()V
                //    71: aload_2        
                //    72: monitorexit    
                //    73: aload_0        
                //    74: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    77: aload_1        
                //    78: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.complete:(Ljava/util/List;)V
                //    81: return         
                //    82: astore_1       
                //    83: aload_2        
                //    84: monitorexit    
                //    85: aload_1        
                //    86: athrow         
                //    87: astore_1       
                //    88: goto            83
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type
                //  -----  -----  -----  -----  ----
                //  9      17     82     83     Any
                //  17     47     82     83     Any
                //  47     71     87     91     Any
                //  71     73     82     83     Any
                //  83     85     82     83     Any
                // 
                // The error that occurred was:
                // 
                // java.lang.IllegalStateException: Expression is linked from several locations: Label_0071:
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
            
            @Override
            public void onError(final Throwable t) {
                ResultManager.this.errorAll(t);
            }
            
            @Override
            public void onNext(final T2 p0) {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     1: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //     4: astore_3       
                //     5: aload_3        
                //     6: monitorenter   
                //     7: aload_0        
                //     8: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    11: astore          4
                //    13: aload           4
                //    15: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightIds:I
                //    18: istore_2       
                //    19: aload           4
                //    21: iload_2        
                //    22: iconst_1       
                //    23: iadd           
                //    24: putfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightIds:I
                //    27: aload_0        
                //    28: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    31: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.rightMap:Ljava/util/Map;
                //    34: iload_2        
                //    35: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
                //    38: aload_1        
                //    39: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
                //    44: pop            
                //    45: aload_3        
                //    46: monitorexit    
                //    47: aload_0        
                //    48: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    51: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.this$0:Lrx/internal/operators/OnSubscribeGroupJoin;
                //    54: getfield        rx/internal/operators/OnSubscribeGroupJoin.rightDuration:Lrx/functions/Func1;
                //    57: aload_1        
                //    58: invokeinterface rx/functions/Func1.call:(Ljava/lang/Object;)Ljava/lang/Object;
                //    63: checkcast       Lrx/Observable;
                //    66: astore_3       
                //    67: new             Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightDurationObserver;
                //    70: dup            
                //    71: aload_0        
                //    72: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    75: iload_2        
                //    76: invokespecial   rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightDurationObserver.<init>:(Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;I)V
                //    79: astore          4
                //    81: aload_0        
                //    82: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //    85: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager.group:Lrx/subscriptions/CompositeSubscription;
                //    88: aload           4
                //    90: invokevirtual   rx/subscriptions/CompositeSubscription.add:(Lrx/Subscription;)V
                //    93: aload_3        
                //    94: aload           4
                //    96: invokevirtual   rx/Observable.unsafeSubscribe:(Lrx/Subscriber;)Lrx/Subscription;
                //    99: pop            
                //   100: aload_0        
                //   101: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   104: astore_3       
                //   105: aload_3        
                //   106: monitorenter   
                //   107: new             Ljava/util/ArrayList;
                //   110: dup            
                //   111: aload_0        
                //   112: getfield        rx/internal/operators/OnSubscribeGroupJoin$ResultManager$RightObserver.this$1:Lrx/internal/operators/OnSubscribeGroupJoin$ResultManager;
                //   115: invokevirtual   rx/internal/operators/OnSubscribeGroupJoin$ResultManager.leftMap:()Ljava/util/Map;
                //   118: invokeinterface java/util/Map.values:()Ljava/util/Collection;
                //   123: invokespecial   java/util/ArrayList.<init>:(Ljava/util/Collection;)V
                //   126: astore          4
                //   128: aload_3        
                //   129: monitorexit    
                //   130: aload           4
                //   132: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
                //   137: astore_3       
                //   138: aload_3        
                //   139: invokeinterface java/util/Iterator.hasNext:()Z
                //   144: ifeq            171
                //   147: aload_3        
                //   148: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
                //   153: checkcast       Lrx/Observer;
                //   156: aload_1        
                //   157: invokeinterface rx/Observer.onNext:(Ljava/lang/Object;)V
                //   162: goto            138
                //   165: astore_1       
                //   166: aload_1        
                //   167: aload_0        
                //   168: invokestatic    rx/exceptions/Exceptions.throwOrReport:(Ljava/lang/Throwable;Lrx/Observer;)V
                //   171: return         
                //   172: astore_1       
                //   173: aload_3        
                //   174: monitorexit    
                //   175: aload_1        
                //   176: athrow         
                //   177: astore_1       
                //   178: aload_3        
                //   179: monitorexit    
                //   180: aload_1        
                //   181: athrow         
                //    Signature:
                //  (TT2;)V
                //    Exceptions:
                //  Try           Handler
                //  Start  End    Start  End    Type                 
                //  -----  -----  -----  -----  ---------------------
                //  0      7      165    171    Ljava/lang/Throwable;
                //  7      47     172    177    Any
                //  47     107    165    171    Ljava/lang/Throwable;
                //  107    130    177    182    Any
                //  130    138    165    171    Ljava/lang/Throwable;
                //  138    162    165    171    Ljava/lang/Throwable;
                //  173    175    172    177    Any
                //  175    177    165    171    Ljava/lang/Throwable;
                //  178    180    177    182    Any
                //  180    182    165    171    Ljava/lang/Throwable;
                // 
                // The error that occurred was:
                // 
                // java.lang.IndexOutOfBoundsException: Index: 92, Size: 92
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
                //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        }
    }
    
    static final class WindowObservableFunc<T> implements OnSubscribe<T>
    {
        final RefCountSubscription refCount;
        final Observable<T> underlying;
        
        public WindowObservableFunc(final Observable<T> underlying, final RefCountSubscription refCount) {
            this.refCount = refCount;
            this.underlying = underlying;
        }
        
        @Override
        public void call(final Subscriber<? super T> subscriber) {
            final Subscription value = this.refCount.get();
            final WindowSubscriber windowSubscriber = new WindowSubscriber(subscriber, value);
            windowSubscriber.add(value);
            this.underlying.unsafeSubscribe(windowSubscriber);
        }
        
        final class WindowSubscriber extends Subscriber<T>
        {
            private final Subscription ref;
            final Subscriber<? super T> subscriber;
            
            public WindowSubscriber(final Subscriber<? super T> subscriber, final Subscription ref) {
                super(subscriber);
                this.subscriber = subscriber;
                this.ref = ref;
            }
            
            @Override
            public void onCompleted() {
                this.subscriber.onCompleted();
                this.ref.unsubscribe();
            }
            
            @Override
            public void onError(final Throwable t) {
                this.subscriber.onError(t);
                this.ref.unsubscribe();
            }
            
            @Override
            public void onNext(final T t) {
                this.subscriber.onNext((Object)t);
            }
        }
    }
}
