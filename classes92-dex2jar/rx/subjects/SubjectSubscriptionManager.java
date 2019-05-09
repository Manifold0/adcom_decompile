// 
// Decompiled by Procyon v0.5.34
// 

package rx.subjects;

import java.util.ArrayList;
import java.util.List;
import rx.Observer;
import rx.subscriptions.Subscriptions;
import rx.functions.Action0;
import rx.Subscriber;
import rx.functions.Actions;
import rx.functions.Action1;
import rx.internal.operators.NotificationLite;
import rx.Observable;
import java.util.concurrent.atomic.AtomicReference;

final class SubjectSubscriptionManager<T> extends AtomicReference<State<T>> implements OnSubscribe<T>
{
    private static final long serialVersionUID = 6035251036011671568L;
    boolean active;
    volatile Object latest;
    public final NotificationLite<T> nl;
    Action1<SubjectObserver<T>> onAdded;
    Action1<SubjectObserver<T>> onStart;
    Action1<SubjectObserver<T>> onTerminated;
    
    public SubjectSubscriptionManager() {
        super(State.EMPTY);
        this.active = true;
        this.onStart = (Action1<SubjectObserver<T>>)Actions.empty();
        this.onAdded = (Action1<SubjectObserver<T>>)Actions.empty();
        this.onTerminated = (Action1<SubjectObserver<T>>)Actions.empty();
        this.nl = NotificationLite.instance();
    }
    
    boolean add(final SubjectObserver<T> subjectObserver) {
        State<T> state;
        do {
            state = this.get();
            if (state.terminated) {
                this.onTerminated.call(subjectObserver);
                return false;
            }
        } while (!this.compareAndSet(state, state.add((SubjectObserver)subjectObserver)));
        this.onAdded.call(subjectObserver);
        return true;
    }
    
    void addUnsubscriber(final Subscriber<? super T> subscriber, final SubjectObserver<T> subjectObserver) {
        subscriber.add(Subscriptions.create(new Action0() {
            @Override
            public void call() {
                SubjectSubscriptionManager.this.remove(subjectObserver);
            }
        }));
    }
    
    @Override
    public void call(final Subscriber<? super T> subscriber) {
        final SubjectObserver subjectObserver = new SubjectObserver<T>((Subscriber<? super Object>)subscriber);
        this.addUnsubscriber(subscriber, (SubjectObserver<T>)subjectObserver);
        this.onStart.call((SubjectObserver<T>)subjectObserver);
        if (!subscriber.isUnsubscribed() && this.add((SubjectObserver<T>)subjectObserver) && subscriber.isUnsubscribed()) {
            this.remove((SubjectObserver<T>)subjectObserver);
        }
    }
    
    Object getLatest() {
        return this.latest;
    }
    
    SubjectObserver<T>[] next(final Object latest) {
        this.setLatest(latest);
        return (SubjectObserver<T>[])this.get().observers;
    }
    
    SubjectObserver<T>[] observers() {
        return (SubjectObserver<T>[])this.get().observers;
    }
    
    void remove(final SubjectObserver<T> subjectObserver) {
        State<T> state;
        State<T> remove = null;
        do {
            state = this.get();
            if (!state.terminated) {
                remove = state.remove((SubjectObserver)subjectObserver);
                if (remove != state) {
                    continue;
                }
            }
        } while (!this.compareAndSet(state, remove));
    }
    
    void setLatest(final Object latest) {
        this.latest = latest;
    }
    
    SubjectObserver<T>[] terminate(final Object latest) {
        this.setLatest(latest);
        this.active = false;
        if (this.get().terminated) {
            return (SubjectObserver<T>[])State.NO_OBSERVERS;
        }
        return (SubjectObserver<T>[])this.getAndSet(State.TERMINATED).observers;
    }
    
    protected static final class State<T>
    {
        static final State EMPTY;
        static final SubjectObserver[] NO_OBSERVERS;
        static final State TERMINATED;
        final SubjectObserver[] observers;
        final boolean terminated;
        
        static {
            NO_OBSERVERS = new SubjectObserver[0];
            TERMINATED = new State(true, State.NO_OBSERVERS);
            EMPTY = new State(false, State.NO_OBSERVERS);
        }
        
        public State(final boolean terminated, final SubjectObserver[] observers) {
            this.terminated = terminated;
            this.observers = observers;
        }
        
        public State add(final SubjectObserver subjectObserver) {
            final int length = this.observers.length;
            final SubjectObserver[] array = new SubjectObserver[length + 1];
            System.arraycopy(this.observers, 0, array, 0, length);
            array[length] = subjectObserver;
            return new State(this.terminated, array);
        }
        
        public State remove(final SubjectObserver subjectObserver) {
            final SubjectObserver[] observers = this.observers;
            final int length = observers.length;
            State empty;
            if (length == 1 && observers[0] == subjectObserver) {
                empty = State.EMPTY;
            }
            else {
                empty = this;
                if (length != 0) {
                    final SubjectObserver[] array = new SubjectObserver[length - 1];
                    int i = 0;
                    int n = 0;
                    while (i < length) {
                        final SubjectObserver subjectObserver2 = observers[i];
                        if (subjectObserver2 != subjectObserver) {
                            empty = this;
                            if (n == length - 1) {
                                return empty;
                            }
                            final int n2 = n + 1;
                            array[n] = subjectObserver2;
                            n = n2;
                        }
                        ++i;
                    }
                    if (n == 0) {
                        return State.EMPTY;
                    }
                    SubjectObserver[] array2 = array;
                    if (n < length - 1) {
                        array2 = new SubjectObserver[n];
                        System.arraycopy(array, 0, array2, 0, n);
                    }
                    return new State(this.terminated, array2);
                }
            }
            return empty;
        }
    }
    
    protected static final class SubjectObserver<T> implements Observer<T>
    {
        final Subscriber<? super T> actual;
        volatile boolean caughtUp;
        boolean emitting;
        boolean fastPath;
        boolean first;
        private volatile Object index;
        List<Object> queue;
        
        public SubjectObserver(final Subscriber<? super T> actual) {
            this.first = true;
            this.actual = actual;
        }
        
        void accept(final Object o, final NotificationLite<T> notificationLite) {
            if (o != null) {
                notificationLite.accept(this.actual, o);
            }
        }
        
        void emitFirst(final Object o, final NotificationLite<T> notificationLite) {
            boolean emitting = false;
            synchronized (this) {
                if (!this.first || this.emitting) {
                    return;
                }
                this.first = false;
                if (o != null) {
                    emitting = true;
                }
                this.emitting = emitting;
                // monitorexit(this)
                if (o != null) {
                    this.emitLoop(null, o, notificationLite);
                }
            }
        }
        
        void emitLoop(final List<Object> p0, final Object p1, final NotificationLite<T> p2) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: iconst_0       
            //     4: istore          8
            //     6: iconst_0       
            //     7: istore          7
            //     9: aload_1        
            //    10: ifnull          72
            //    13: iload           7
            //    15: istore          4
            //    17: aload_1        
            //    18: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
            //    23: astore_1       
            //    24: iload           7
            //    26: istore          4
            //    28: aload_1        
            //    29: invokeinterface java/util/Iterator.hasNext:()Z
            //    34: ifeq            72
            //    37: iload           7
            //    39: istore          4
            //    41: aload_0        
            //    42: aload_1        
            //    43: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
            //    48: aload_3        
            //    49: invokevirtual   rx/subjects/SubjectSubscriptionManager$SubjectObserver.accept:(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
            //    52: goto            24
            //    55: astore_1       
            //    56: iload           4
            //    58: ifne            70
            //    61: aload_0        
            //    62: monitorenter   
            //    63: aload_0        
            //    64: iconst_0       
            //    65: putfield        rx/subjects/SubjectSubscriptionManager$SubjectObserver.emitting:Z
            //    68: aload_0        
            //    69: monitorexit    
            //    70: aload_1        
            //    71: athrow         
            //    72: iload           6
            //    74: istore          5
            //    76: iload           6
            //    78: ifeq            94
            //    81: iconst_0       
            //    82: istore          5
            //    84: iload           7
            //    86: istore          4
            //    88: aload_0        
            //    89: aload_2        
            //    90: aload_3        
            //    91: invokevirtual   rx/subjects/SubjectSubscriptionManager$SubjectObserver.accept:(Ljava/lang/Object;Lrx/internal/operators/NotificationLite;)V
            //    94: iload           7
            //    96: istore          4
            //    98: aload_0        
            //    99: monitorenter   
            //   100: iload           8
            //   102: istore          4
            //   104: aload_0        
            //   105: getfield        rx/subjects/SubjectSubscriptionManager$SubjectObserver.queue:Ljava/util/List;
            //   108: astore_1       
            //   109: iload           8
            //   111: istore          4
            //   113: aload_0        
            //   114: aconst_null    
            //   115: putfield        rx/subjects/SubjectSubscriptionManager$SubjectObserver.queue:Ljava/util/List;
            //   118: aload_1        
            //   119: ifnonnull       150
            //   122: iload           8
            //   124: istore          4
            //   126: aload_0        
            //   127: iconst_0       
            //   128: putfield        rx/subjects/SubjectSubscriptionManager$SubjectObserver.emitting:Z
            //   131: iconst_1       
            //   132: istore          4
            //   134: aload_0        
            //   135: monitorexit    
            //   136: iconst_1       
            //   137: ifne            149
            //   140: aload_0        
            //   141: monitorenter   
            //   142: aload_0        
            //   143: iconst_0       
            //   144: putfield        rx/subjects/SubjectSubscriptionManager$SubjectObserver.emitting:Z
            //   147: aload_0        
            //   148: monitorexit    
            //   149: return         
            //   150: iload           8
            //   152: istore          4
            //   154: aload_0        
            //   155: monitorexit    
            //   156: iload           5
            //   158: istore          6
            //   160: goto            9
            //   163: astore_1       
            //   164: aload_0        
            //   165: monitorexit    
            //   166: aload_1        
            //   167: athrow         
            //   168: astore_1       
            //   169: aload_0        
            //   170: monitorexit    
            //   171: aload_1        
            //   172: athrow         
            //   173: astore_1       
            //   174: aload_0        
            //   175: monitorexit    
            //   176: aload_1        
            //   177: athrow         
            //    Signature:
            //  (Ljava/util/List<Ljava/lang/Object;>;Ljava/lang/Object;Lrx/internal/operators/NotificationLite<TT;>;)V
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  17     24     55     178    Any
            //  28     37     55     178    Any
            //  41     52     55     178    Any
            //  63     70     173    178    Any
            //  88     94     55     178    Any
            //  98     100    55     178    Any
            //  104    109    163    168    Any
            //  113    118    163    168    Any
            //  126    131    163    168    Any
            //  134    136    163    168    Any
            //  142    149    168    173    Any
            //  154    156    163    168    Any
            //  164    166    163    168    Any
            //  166    168    55     178    Any
            //  169    171    168    173    Any
            //  174    176    173    178    Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0149:
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
        
        void emitNext(final Object o, final NotificationLite<T> notificationLite) {
            Label_0060: {
                if (this.fastPath) {
                    break Label_0060;
                }
                synchronized (this) {
                    this.first = false;
                    if (this.emitting) {
                        if (this.queue == null) {
                            this.queue = new ArrayList<Object>();
                        }
                        this.queue.add(o);
                        return;
                    }
                    // monitorexit(this)
                    this.fastPath = true;
                    notificationLite.accept(this.actual, o);
                }
            }
        }
        
        Observer<? super T> getActual() {
            return this.actual;
        }
        
        public <I> I index() {
            return (I)this.index;
        }
        
        public void index(final Object index) {
            this.index = index;
        }
        
        @Override
        public void onCompleted() {
            this.actual.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.actual.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.actual.onNext((Object)t);
        }
    }
}
