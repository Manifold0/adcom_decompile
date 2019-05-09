// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.Collections;
import java.util.ArrayList;
import java.util.Iterator;
import rx.subjects.UnicastSubject;
import rx.observers.SerializedSubscriber;
import rx.subscriptions.SerialSubscription;
import java.util.List;
import rx.Observer;
import rx.Subscription;
import rx.Subscriber;
import rx.functions.Func0;
import rx.Observable;

public final class OperatorWindowWithObservableFactory<T, U> implements Operator<Observable<T>, T>
{
    static final Object NEXT_SUBJECT;
    static final NotificationLite<Object> NL;
    final Func0<? extends Observable<? extends U>> otherFactory;
    
    static {
        NEXT_SUBJECT = new Object();
        NL = NotificationLite.instance();
    }
    
    public OperatorWindowWithObservableFactory(final Func0<? extends Observable<? extends U>> otherFactory) {
        this.otherFactory = otherFactory;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super Observable<T>> subscriber) {
        final SourceSubscriber sourceSubscriber = new SourceSubscriber(subscriber, this.otherFactory);
        subscriber.add(sourceSubscriber);
        sourceSubscriber.replaceWindow();
        return (Subscriber<? super T>)sourceSubscriber;
    }
    
    static final class BoundarySubscriber<T, U> extends Subscriber<U>
    {
        boolean done;
        final SourceSubscriber<T, U> sub;
        
        public BoundarySubscriber(final SourceSubscriber<T, U> sub) {
            this.sub = sub;
        }
        
        @Override
        public void onCompleted() {
            if (!this.done) {
                this.done = true;
                this.sub.onCompleted();
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            this.sub.onError(t);
        }
        
        @Override
        public void onNext(final U u) {
            if (!this.done) {
                this.done = true;
                this.sub.replaceWindow();
            }
        }
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
    }
    
    static final class SourceSubscriber<T, U> extends Subscriber<T>
    {
        final Subscriber<? super Observable<T>> child;
        Observer<T> consumer;
        boolean emitting;
        final Object guard;
        final Func0<? extends Observable<? extends U>> otherFactory;
        Observable<T> producer;
        List<Object> queue;
        final SerialSubscription serial;
        
        public SourceSubscriber(final Subscriber<? super Observable<T>> subscriber, final Func0<? extends Observable<? extends U>> otherFactory) {
            this.child = new SerializedSubscriber<Object>(subscriber);
            this.guard = new Object();
            this.serial = new SerialSubscription();
            this.otherFactory = otherFactory;
            this.add(this.serial);
        }
        
        void complete() {
            final Observer<T> consumer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (consumer != null) {
                consumer.onCompleted();
            }
            this.child.onCompleted();
            this.unsubscribe();
        }
        
        void createNewWindow() {
            final UnicastSubject<Object> create = UnicastSubject.create();
            this.consumer = (Observer<T>)create;
            this.producer = (Observable<T>)create;
            try {
                final Observable observable = (Observable)this.otherFactory.call();
                final BoundarySubscriber boundarySubscriber = new BoundarySubscriber((SourceSubscriber<T, U>)this);
                this.serial.set(boundarySubscriber);
                observable.unsafeSubscribe(boundarySubscriber);
            }
            catch (Throwable t) {
                this.child.onError(t);
                this.unsubscribe();
            }
        }
        
        void drain(final List<Object> list) {
            if (list != null) {
                for (final T next : list) {
                    if (next == OperatorWindowWithObservableFactory.NEXT_SUBJECT) {
                        this.replaceSubject();
                    }
                    else {
                        if (OperatorWindowWithObservableFactory.NL.isError(next)) {
                            this.error(OperatorWindowWithObservableFactory.NL.getError(next));
                            return;
                        }
                        if (OperatorWindowWithObservableFactory.NL.isCompleted(next)) {
                            this.complete();
                            return;
                        }
                        this.emitValue(next);
                    }
                }
            }
        }
        
        void emitValue(final T t) {
            final Observer<T> consumer = this.consumer;
            if (consumer != null) {
                consumer.onNext(t);
            }
        }
        
        void error(final Throwable t) {
            final Observer<T> consumer = this.consumer;
            this.consumer = null;
            this.producer = null;
            if (consumer != null) {
                consumer.onError(t);
            }
            this.child.onError(t);
            this.unsubscribe();
        }
        
        @Override
        public void onCompleted() {
            final Object guard = this.guard;
            final List<Object> queue;
            synchronized (guard) {
                if (this.emitting) {
                    if (this.queue == null) {
                        this.queue = new ArrayList<Object>();
                    }
                    this.queue.add(OperatorWindowWithObservableFactory.NL.completed());
                    return;
                }
                queue = this.queue;
                this.queue = null;
                this.emitting = true;
                // monitorexit(guard)
                final SourceSubscriber sourceSubscriber = this;
                final List<Object> list = queue;
                sourceSubscriber.drain(list);
                final SourceSubscriber sourceSubscriber2 = this;
                sourceSubscriber2.complete();
                return;
            }
            try {
                final SourceSubscriber sourceSubscriber = this;
                final List<Object> list = queue;
                sourceSubscriber.drain(list);
                final SourceSubscriber sourceSubscriber2 = this;
                sourceSubscriber2.complete();
            }
            catch (Throwable t) {
                this.error(t);
            }
        }
        
        @Override
        public void onError(final Throwable t) {
            synchronized (this.guard) {
                if (this.emitting) {
                    this.queue = Collections.singletonList(OperatorWindowWithObservableFactory.NL.error(t));
                    return;
                }
                this.queue = null;
                this.emitting = true;
                // monitorexit(this.guard)
                this.error(t);
            }
        }
        
        @Override
        public void onNext(final T p0) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //     4: astore          9
            //     6: aload           9
            //     8: monitorenter   
            //     9: aload_0        
            //    10: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //    13: ifeq            49
            //    16: aload_0        
            //    17: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    20: ifnonnull       34
            //    23: aload_0        
            //    24: new             Ljava/util/ArrayList;
            //    27: dup            
            //    28: invokespecial   java/util/ArrayList.<init>:()V
            //    31: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    34: aload_0        
            //    35: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    38: aload_1        
            //    39: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    44: pop            
            //    45: aload           9
            //    47: monitorexit    
            //    48: return         
            //    49: aload_0        
            //    50: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    53: astore          8
            //    55: aload_0        
            //    56: aconst_null    
            //    57: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    60: aload_0        
            //    61: iconst_1       
            //    62: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //    65: aload           9
            //    67: monitorexit    
            //    68: iconst_1       
            //    69: istore          4
            //    71: iconst_0       
            //    72: istore          6
            //    74: iconst_0       
            //    75: istore          5
            //    77: iload           6
            //    79: istore_2       
            //    80: aload_0        
            //    81: aload           8
            //    83: invokevirtual   rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.drain:(Ljava/util/List;)V
            //    86: iload           4
            //    88: istore_3       
            //    89: iload           4
            //    91: ifeq            104
            //    94: iconst_0       
            //    95: istore_3       
            //    96: iload           6
            //    98: istore_2       
            //    99: aload_0        
            //   100: aload_1        
            //   101: invokevirtual   rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitValue:(Ljava/lang/Object;)V
            //   104: iload           6
            //   106: istore_2       
            //   107: aload_0        
            //   108: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   111: astore          9
            //   113: iload           6
            //   115: istore_2       
            //   116: aload           9
            //   118: monitorenter   
            //   119: iload           5
            //   121: istore_2       
            //   122: aload_0        
            //   123: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //   126: astore          8
            //   128: iload           5
            //   130: istore_2       
            //   131: aload_0        
            //   132: aconst_null    
            //   133: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //   136: aload           8
            //   138: ifnonnull       186
            //   141: iload           5
            //   143: istore_2       
            //   144: aload_0        
            //   145: iconst_0       
            //   146: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   149: iconst_1       
            //   150: istore_2       
            //   151: aload           9
            //   153: monitorexit    
            //   154: iconst_1       
            //   155: ifne            274
            //   158: aload_0        
            //   159: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   162: astore_1       
            //   163: aload_1        
            //   164: monitorenter   
            //   165: aload_0        
            //   166: iconst_0       
            //   167: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   170: aload_1        
            //   171: monitorexit    
            //   172: return         
            //   173: astore          8
            //   175: aload_1        
            //   176: monitorexit    
            //   177: aload           8
            //   179: athrow         
            //   180: astore_1       
            //   181: aload           9
            //   183: monitorexit    
            //   184: aload_1        
            //   185: athrow         
            //   186: iload           5
            //   188: istore_2       
            //   189: aload           9
            //   191: monitorexit    
            //   192: iload           6
            //   194: istore_2       
            //   195: aload_0        
            //   196: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.child:Lrx/Subscriber;
            //   199: invokevirtual   rx/Subscriber.isUnsubscribed:()Z
            //   202: istore          7
            //   204: iload_3        
            //   205: istore          4
            //   207: iload           7
            //   209: ifeq            77
            //   212: iconst_0       
            //   213: ifne            274
            //   216: aload_0        
            //   217: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   220: astore_1       
            //   221: aload_1        
            //   222: monitorenter   
            //   223: aload_0        
            //   224: iconst_0       
            //   225: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   228: aload_1        
            //   229: monitorexit    
            //   230: return         
            //   231: astore          8
            //   233: aload_1        
            //   234: monitorexit    
            //   235: aload           8
            //   237: athrow         
            //   238: astore_1       
            //   239: aload           9
            //   241: monitorexit    
            //   242: aload_1        
            //   243: athrow         
            //   244: astore          8
            //   246: iload_2        
            //   247: ifne            264
            //   250: aload_0        
            //   251: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   254: astore_1       
            //   255: aload_1        
            //   256: monitorenter   
            //   257: aload_0        
            //   258: iconst_0       
            //   259: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   262: aload_1        
            //   263: monitorexit    
            //   264: aload           8
            //   266: athrow         
            //   267: astore          8
            //   269: aload_1        
            //   270: monitorexit    
            //   271: aload           8
            //   273: athrow         
            //   274: return         
            //    Signature:
            //  (TT;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  9      34     180    186    Any
            //  34     48     180    186    Any
            //  49     68     180    186    Any
            //  80     86     244    274    Any
            //  99     104    244    274    Any
            //  107    113    244    274    Any
            //  116    119    244    274    Any
            //  122    128    238    244    Any
            //  131    136    238    244    Any
            //  144    149    238    244    Any
            //  151    154    238    244    Any
            //  165    172    173    180    Any
            //  175    177    173    180    Any
            //  181    184    180    186    Any
            //  189    192    238    244    Any
            //  195    204    244    274    Any
            //  223    230    231    238    Any
            //  233    235    231    238    Any
            //  239    242    238    244    Any
            //  242    244    244    274    Any
            //  257    264    267    274    Any
            //  269    271    267    274    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0104:
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
        
        @Override
        public void onStart() {
            this.request(Long.MAX_VALUE);
        }
        
        void replaceSubject() {
            final Observer<T> consumer = this.consumer;
            if (consumer != null) {
                consumer.onCompleted();
            }
            this.createNewWindow();
            this.child.onNext(this.producer);
        }
        
        void replaceWindow() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     4: astore          8
            //     6: aload           8
            //     8: monitorenter   
            //     9: aload_0        
            //    10: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //    13: ifeq            51
            //    16: aload_0        
            //    17: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    20: ifnonnull       34
            //    23: aload_0        
            //    24: new             Ljava/util/ArrayList;
            //    27: dup            
            //    28: invokespecial   java/util/ArrayList.<init>:()V
            //    31: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    34: aload_0        
            //    35: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    38: getstatic       rx/internal/operators/OperatorWindowWithObservableFactory.NEXT_SUBJECT:Ljava/lang/Object;
            //    41: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
            //    46: pop            
            //    47: aload           8
            //    49: monitorexit    
            //    50: return         
            //    51: aload_0        
            //    52: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    55: astore          7
            //    57: aload_0        
            //    58: aconst_null    
            //    59: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //    62: aload_0        
            //    63: iconst_1       
            //    64: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //    67: aload           8
            //    69: monitorexit    
            //    70: iconst_1       
            //    71: istore_3       
            //    72: iconst_0       
            //    73: istore          5
            //    75: iconst_0       
            //    76: istore          4
            //    78: iload           5
            //    80: istore_1       
            //    81: aload_0        
            //    82: aload           7
            //    84: invokevirtual   rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.drain:(Ljava/util/List;)V
            //    87: iload_3        
            //    88: istore_2       
            //    89: iload_3        
            //    90: ifeq            102
            //    93: iconst_0       
            //    94: istore_2       
            //    95: iload           5
            //    97: istore_1       
            //    98: aload_0        
            //    99: invokevirtual   rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.replaceSubject:()V
            //   102: iload           5
            //   104: istore_1       
            //   105: aload_0        
            //   106: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   109: astore          8
            //   111: iload           5
            //   113: istore_1       
            //   114: aload           8
            //   116: monitorenter   
            //   117: iload           4
            //   119: istore_1       
            //   120: aload_0        
            //   121: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //   124: astore          7
            //   126: iload           4
            //   128: istore_1       
            //   129: aload_0        
            //   130: aconst_null    
            //   131: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.queue:Ljava/util/List;
            //   134: aload           7
            //   136: ifnonnull       190
            //   139: iload           4
            //   141: istore_1       
            //   142: aload_0        
            //   143: iconst_0       
            //   144: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   147: iconst_1       
            //   148: istore_1       
            //   149: aload           8
            //   151: monitorexit    
            //   152: iconst_1       
            //   153: ifne            287
            //   156: aload_0        
            //   157: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   160: astore          7
            //   162: aload           7
            //   164: monitorenter   
            //   165: aload_0        
            //   166: iconst_0       
            //   167: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   170: aload           7
            //   172: monitorexit    
            //   173: return         
            //   174: astore          8
            //   176: aload           7
            //   178: monitorexit    
            //   179: aload           8
            //   181: athrow         
            //   182: astore          7
            //   184: aload           8
            //   186: monitorexit    
            //   187: aload           7
            //   189: athrow         
            //   190: iload           4
            //   192: istore_1       
            //   193: aload           8
            //   195: monitorexit    
            //   196: iload           5
            //   198: istore_1       
            //   199: aload_0        
            //   200: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.child:Lrx/Subscriber;
            //   203: invokevirtual   rx/Subscriber.isUnsubscribed:()Z
            //   206: istore          6
            //   208: iload_2        
            //   209: istore_3       
            //   210: iload           6
            //   212: ifeq            78
            //   215: iconst_0       
            //   216: ifne            287
            //   219: aload_0        
            //   220: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   223: astore          7
            //   225: aload           7
            //   227: monitorenter   
            //   228: aload_0        
            //   229: iconst_0       
            //   230: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   233: aload           7
            //   235: monitorexit    
            //   236: return         
            //   237: astore          8
            //   239: aload           7
            //   241: monitorexit    
            //   242: aload           8
            //   244: athrow         
            //   245: astore          7
            //   247: aload           8
            //   249: monitorexit    
            //   250: aload           7
            //   252: athrow         
            //   253: astore          8
            //   255: iload_1        
            //   256: ifne            276
            //   259: aload_0        
            //   260: getfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.guard:Ljava/lang/Object;
            //   263: astore          7
            //   265: aload           7
            //   267: monitorenter   
            //   268: aload_0        
            //   269: iconst_0       
            //   270: putfield        rx/internal/operators/OperatorWindowWithObservableFactory$SourceSubscriber.emitting:Z
            //   273: aload           7
            //   275: monitorexit    
            //   276: aload           8
            //   278: athrow         
            //   279: astore          8
            //   281: aload           7
            //   283: monitorexit    
            //   284: aload           8
            //   286: athrow         
            //   287: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  9      34     182    190    Any
            //  34     50     182    190    Any
            //  51     70     182    190    Any
            //  81     87     253    287    Any
            //  98     102    253    287    Any
            //  105    111    253    287    Any
            //  114    117    253    287    Any
            //  120    126    245    253    Any
            //  129    134    245    253    Any
            //  142    147    245    253    Any
            //  149    152    245    253    Any
            //  165    173    174    182    Any
            //  176    179    174    182    Any
            //  184    187    182    190    Any
            //  193    196    245    253    Any
            //  199    208    253    287    Any
            //  228    236    237    245    Any
            //  239    242    237    245    Any
            //  247    250    245    253    Any
            //  250    253    253    287    Any
            //  268    276    279    287    Any
            //  281    284    279    287    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0102:
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
    }
}
