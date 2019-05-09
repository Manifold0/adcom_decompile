// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.operators;

import java.util.concurrent.atomic.AtomicReference;
import rx.Observer;
import java.util.concurrent.atomic.AtomicLong;
import rx.Producer;
import rx.Subscription;
import rx.Subscriber;
import rx.Observable;

public final class OperatorOnBackpressureLatest<T> implements Operator<T, T>
{
    public static <T> OperatorOnBackpressureLatest<T> instance() {
        return (OperatorOnBackpressureLatest<T>)Holder.INSTANCE;
    }
    
    @Override
    public Subscriber<? super T> call(final Subscriber<? super T> subscriber) {
        final LatestEmitter producer = new LatestEmitter((Subscriber<? super Object>)subscriber);
        final LatestSubscriber parent = new LatestSubscriber((LatestEmitter<Object>)producer);
        subscriber.add(producer.parent = (LatestSubscriber<? super T>)parent);
        subscriber.add(producer);
        subscriber.setProducer(producer);
        return (Subscriber<? super T>)parent;
    }
    
    static final class Holder
    {
        static final OperatorOnBackpressureLatest<Object> INSTANCE;
        
        static {
            INSTANCE = new OperatorOnBackpressureLatest<Object>();
        }
    }
    
    static final class LatestEmitter<T> extends AtomicLong implements Producer, Subscription, Observer<T>
    {
        static final Object EMPTY;
        static final long NOT_REQUESTED = -4611686018427387904L;
        private static final long serialVersionUID = -1364393685005146274L;
        final Subscriber<? super T> child;
        volatile boolean done;
        boolean emitting;
        boolean missed;
        LatestSubscriber<? super T> parent;
        Throwable terminal;
        final AtomicReference<Object> value;
        
        static {
            EMPTY = new Object();
        }
        
        public LatestEmitter(final Subscriber<? super T> child) {
            this.child = child;
            this.value = new AtomicReference<Object>(LatestEmitter.EMPTY);
            this.lazySet(-4611686018427387904L);
        }
        
        void emit() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: aload_0        
            //     3: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.emitting:Z
            //     6: ifeq            17
            //     9: aload_0        
            //    10: iconst_1       
            //    11: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.missed:Z
            //    14: aload_0        
            //    15: monitorexit    
            //    16: return         
            //    17: aload_0        
            //    18: iconst_1       
            //    19: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.emitting:Z
            //    22: aload_0        
            //    23: iconst_0       
            //    24: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.missed:Z
            //    27: aload_0        
            //    28: monitorexit    
            //    29: iconst_0       
            //    30: istore_3       
            //    31: iconst_0       
            //    32: istore_2       
            //    33: iload_3        
            //    34: istore_1       
            //    35: aload_0        
            //    36: invokevirtual   rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.get:()J
            //    39: lstore          4
            //    41: lload           4
            //    43: ldc2_w          -9223372036854775808
            //    46: lcmp           
            //    47: ifne            78
            //    50: iconst_1       
            //    51: ifne            283
            //    54: aload_0        
            //    55: monitorenter   
            //    56: aload_0        
            //    57: iconst_0       
            //    58: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.emitting:Z
            //    61: aload_0        
            //    62: monitorexit    
            //    63: return         
            //    64: astore          6
            //    66: aload_0        
            //    67: monitorexit    
            //    68: aload           6
            //    70: athrow         
            //    71: astore          6
            //    73: aload_0        
            //    74: monitorexit    
            //    75: aload           6
            //    77: athrow         
            //    78: iload_3        
            //    79: istore_1       
            //    80: aload_0        
            //    81: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.value:Ljava/util/concurrent/atomic/AtomicReference;
            //    84: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //    87: astore          7
            //    89: aload           7
            //    91: astore          6
            //    93: lload           4
            //    95: lconst_0       
            //    96: lcmp           
            //    97: ifle            155
            //   100: aload           7
            //   102: astore          6
            //   104: iload_3        
            //   105: istore_1       
            //   106: aload           7
            //   108: getstatic       rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.EMPTY:Ljava/lang/Object;
            //   111: if_acmpeq       155
            //   114: iload_3        
            //   115: istore_1       
            //   116: aload_0        
            //   117: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.child:Lrx/Subscriber;
            //   120: aload           7
            //   122: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
            //   125: iload_3        
            //   126: istore_1       
            //   127: aload_0        
            //   128: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.value:Ljava/util/concurrent/atomic/AtomicReference;
            //   131: aload           7
            //   133: getstatic       rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.EMPTY:Ljava/lang/Object;
            //   136: invokevirtual   java/util/concurrent/atomic/AtomicReference.compareAndSet:(Ljava/lang/Object;Ljava/lang/Object;)Z
            //   139: pop            
            //   140: iload_3        
            //   141: istore_1       
            //   142: aload_0        
            //   143: lconst_1       
            //   144: invokevirtual   rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.produced:(J)J
            //   147: pop2           
            //   148: iload_3        
            //   149: istore_1       
            //   150: getstatic       rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.EMPTY:Ljava/lang/Object;
            //   153: astore          6
            //   155: iload_3        
            //   156: istore_1       
            //   157: aload           6
            //   159: getstatic       rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.EMPTY:Ljava/lang/Object;
            //   162: if_acmpne       198
            //   165: iload_3        
            //   166: istore_1       
            //   167: aload_0        
            //   168: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.done:Z
            //   171: ifeq            198
            //   174: iload_3        
            //   175: istore_1       
            //   176: aload_0        
            //   177: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.terminal:Ljava/lang/Throwable;
            //   180: astore          6
            //   182: aload           6
            //   184: ifnull          250
            //   187: iload_3        
            //   188: istore_1       
            //   189: aload_0        
            //   190: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.child:Lrx/Subscriber;
            //   193: aload           6
            //   195: invokevirtual   rx/Subscriber.onError:(Ljava/lang/Throwable;)V
            //   198: iload_3        
            //   199: istore_1       
            //   200: aload_0        
            //   201: monitorenter   
            //   202: iload_2        
            //   203: istore_1       
            //   204: aload_0        
            //   205: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.missed:Z
            //   208: ifne            262
            //   211: iload_2        
            //   212: istore_1       
            //   213: aload_0        
            //   214: iconst_0       
            //   215: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.emitting:Z
            //   218: iconst_1       
            //   219: istore_1       
            //   220: aload_0        
            //   221: monitorexit    
            //   222: goto            50
            //   225: astore          6
            //   227: aload_0        
            //   228: monitorexit    
            //   229: aload           6
            //   231: athrow         
            //   232: astore          6
            //   234: iload_1        
            //   235: ifne            247
            //   238: aload_0        
            //   239: monitorenter   
            //   240: aload_0        
            //   241: iconst_0       
            //   242: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.emitting:Z
            //   245: aload_0        
            //   246: monitorexit    
            //   247: aload           6
            //   249: athrow         
            //   250: iload_3        
            //   251: istore_1       
            //   252: aload_0        
            //   253: getfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.child:Lrx/Subscriber;
            //   256: invokevirtual   rx/Subscriber.onCompleted:()V
            //   259: goto            198
            //   262: iload_2        
            //   263: istore_1       
            //   264: aload_0        
            //   265: iconst_0       
            //   266: putfield        rx/internal/operators/OperatorOnBackpressureLatest$LatestEmitter.missed:Z
            //   269: iload_2        
            //   270: istore_1       
            //   271: aload_0        
            //   272: monitorexit    
            //   273: goto            33
            //   276: astore          6
            //   278: aload_0        
            //   279: monitorexit    
            //   280: aload           6
            //   282: athrow         
            //   283: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  2      16     71     78     Any
            //  17     29     71     78     Any
            //  35     41     232    283    Any
            //  56     63     64     71     Any
            //  66     68     64     71     Any
            //  73     75     71     78     Any
            //  80     89     232    283    Any
            //  106    114    232    283    Any
            //  116    125    232    283    Any
            //  127    140    232    283    Any
            //  142    148    232    283    Any
            //  150    155    232    283    Any
            //  157    165    232    283    Any
            //  167    174    232    283    Any
            //  176    182    232    283    Any
            //  189    198    232    283    Any
            //  200    202    232    283    Any
            //  204    211    225    232    Any
            //  213    218    225    232    Any
            //  220    222    225    232    Any
            //  227    229    225    232    Any
            //  229    232    232    283    Any
            //  240    247    276    283    Any
            //  252    259    232    283    Any
            //  264    269    225    232    Any
            //  271    273    225    232    Any
            //  278    280    276    283    Any
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
        public boolean isUnsubscribed() {
            return this.get() == Long.MIN_VALUE;
        }
        
        @Override
        public void onCompleted() {
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onError(final Throwable terminal) {
            this.terminal = terminal;
            this.done = true;
            this.emit();
        }
        
        @Override
        public void onNext(final T t) {
            this.value.lazySet(t);
            this.emit();
        }
        
        long produced(final long n) {
            long value;
            long n2;
            do {
                value = this.get();
                if (value < 0L) {
                    return value;
                }
                n2 = value - n;
            } while (!this.compareAndSet(value, n2));
            return n2;
        }
        
        @Override
        public void request(final long n) {
            if (n >= 0L) {
                long value;
                long n2;
                do {
                    value = this.get();
                    if (value == Long.MIN_VALUE) {
                        return;
                    }
                    if (value == -4611686018427387904L) {
                        n2 = n;
                    }
                    else if ((n2 = value + n) < 0L) {
                        n2 = Long.MAX_VALUE;
                    }
                } while (!this.compareAndSet(value, n2));
                if (value == -4611686018427387904L) {
                    this.parent.requestMore(Long.MAX_VALUE);
                }
                this.emit();
            }
        }
        
        @Override
        public void unsubscribe() {
            if (this.get() >= 0L) {
                this.getAndSet(Long.MIN_VALUE);
            }
        }
    }
    
    static final class LatestSubscriber<T> extends Subscriber<T>
    {
        private final LatestEmitter<T> producer;
        
        LatestSubscriber(final LatestEmitter<T> producer) {
            this.producer = producer;
        }
        
        @Override
        public void onCompleted() {
            this.producer.onCompleted();
        }
        
        @Override
        public void onError(final Throwable t) {
            this.producer.onError(t);
        }
        
        @Override
        public void onNext(final T t) {
            this.producer.onNext(t);
        }
        
        @Override
        public void onStart() {
            this.request(0L);
        }
        
        void requestMore(final long n) {
            this.request(n);
        }
    }
}
