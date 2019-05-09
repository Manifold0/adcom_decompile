// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import java.util.Iterator;
import rx.internal.operators.BackpressureUtils;
import rx.exceptions.Exceptions;
import java.util.List;
import rx.Subscriber;
import rx.Observer;
import rx.Producer;

public final class ProducerObserverArbiter<T> implements Producer, Observer<T>
{
    static final Producer NULL_PRODUCER;
    final Subscriber<? super T> child;
    Producer currentProducer;
    boolean emitting;
    volatile boolean hasError;
    Producer missedProducer;
    long missedRequested;
    Object missedTerminal;
    List<T> queue;
    long requested;
    
    static {
        NULL_PRODUCER = new Producer() {
            @Override
            public void request(final long n) {
            }
        };
    }
    
    public ProducerObserverArbiter(final Subscriber<? super T> child) {
        this.child = child;
    }
    
    void emitLoop() {
        final Subscriber<? super T> child = this.child;
        long n = 0L;
        Producer producer = null;
    Label_0011:
        while (true) {
            boolean b = false;
            final long missedRequested;
            Producer currentProducer;
            Object o;
            final List<T> queue;
            synchronized (this) {
                missedRequested = this.missedRequested;
                currentProducer = this.missedProducer;
                o = this.missedTerminal;
                queue = this.queue;
                if (missedRequested == 0L && currentProducer == null && queue == null && o == null) {
                    this.emitting = false;
                    b = true;
                }
                else {
                    this.missedRequested = 0L;
                    this.missedProducer = null;
                    this.queue = null;
                    this.missedTerminal = null;
                }
                // monitorexit(this)
                if (b) {
                    if (n != 0L && producer != null) {
                        producer.request(n);
                    }
                    return;
                }
            }
            final boolean b2 = queue == null || queue.isEmpty();
            if (o != null) {
                if (o != Boolean.TRUE) {
                    child.onError((Throwable)o);
                    return;
                }
                if (b2) {
                    child.onCompleted();
                    return;
                }
            }
            long n2 = 0L;
            if (queue != null) {
                final Iterator<T> iterator = queue.iterator();
                while (iterator.hasNext()) {
                    o = iterator.next();
                    if (child.isUnsubscribed()) {
                        return;
                    }
                    if (!this.hasError) {
                        try {
                            child.onNext((Throwable)o);
                            continue;
                        }
                        catch (Throwable t) {
                            Exceptions.throwOrReport(t, child, o);
                            return;
                        }
                        break;
                    }
                    continue Label_0011;
                }
                n2 = 0L + queue.size();
            }
            long requested;
            final long n3 = requested = this.requested;
            if (n3 != Long.MAX_VALUE) {
                long n4 = n3;
                if (missedRequested != 0L && (n4 = n3 + missedRequested) < 0L) {
                    n4 = Long.MAX_VALUE;
                }
                long requested2 = n4;
                if (n2 != 0L) {
                    requested2 = n4;
                    if (n4 != Long.MAX_VALUE) {
                        requested2 = n4 - n2;
                        if (requested2 < 0L) {
                            throw new IllegalStateException("More produced than requested");
                        }
                    }
                }
                this.requested = requested2;
                requested = requested2;
            }
            if (currentProducer != null) {
                if (currentProducer == ProducerObserverArbiter.NULL_PRODUCER) {
                    this.currentProducer = null;
                }
                else {
                    this.currentProducer = currentProducer;
                    if (requested == 0L) {
                        continue;
                    }
                    n = BackpressureUtils.addCap(n, requested);
                    producer = currentProducer;
                }
            }
            else {
                currentProducer = this.currentProducer;
                if (currentProducer == null || missedRequested == 0L) {
                    continue;
                }
                n = BackpressureUtils.addCap(n, missedRequested);
                producer = currentProducer;
            }
        }
    }
    
    @Override
    public void onCompleted() {
        synchronized (this) {
            if (this.emitting) {
                this.missedTerminal = true;
                return;
            }
            this.emitting = true;
            // monitorexit(this)
            this.child.onCompleted();
        }
    }
    
    @Override
    public void onError(final Throwable missedTerminal) {
        synchronized (this) {
            int n;
            if (this.emitting) {
                this.missedTerminal = missedTerminal;
                n = 0;
            }
            else {
                this.emitting = true;
                n = 1;
            }
            // monitorexit(this)
            if (n != 0) {
                this.child.onError(missedTerminal);
                return;
            }
        }
        this.hasError = true;
    }
    
    @Override
    public void onNext(final T p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //     6: ifeq            52
        //     9: aload_0        
        //    10: getfield        rx/internal/producers/ProducerObserverArbiter.queue:Ljava/util/List;
        //    13: astore          5
        //    15: aload           5
        //    17: astore          4
        //    19: aload           5
        //    21: ifnonnull       40
        //    24: new             Ljava/util/ArrayList;
        //    27: dup            
        //    28: iconst_4       
        //    29: invokespecial   java/util/ArrayList.<init>:(I)V
        //    32: astore          4
        //    34: aload_0        
        //    35: aload           4
        //    37: putfield        rx/internal/producers/ProducerObserverArbiter.queue:Ljava/util/List;
        //    40: aload           4
        //    42: aload_1        
        //    43: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //    48: pop            
        //    49: aload_0        
        //    50: monitorexit    
        //    51: return         
        //    52: aload_0        
        //    53: iconst_1       
        //    54: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //    57: aload_0        
        //    58: monitorexit    
        //    59: aload_0        
        //    60: getfield        rx/internal/producers/ProducerObserverArbiter.child:Lrx/Subscriber;
        //    63: aload_1        
        //    64: invokevirtual   rx/Subscriber.onNext:(Ljava/lang/Object;)V
        //    67: aload_0        
        //    68: getfield        rx/internal/producers/ProducerObserverArbiter.requested:J
        //    71: lstore_2       
        //    72: lload_2        
        //    73: ldc2_w          9223372036854775807
        //    76: lcmp           
        //    77: ifeq            87
        //    80: aload_0        
        //    81: lload_2        
        //    82: lconst_1       
        //    83: lsub           
        //    84: putfield        rx/internal/producers/ProducerObserverArbiter.requested:J
        //    87: aload_0        
        //    88: invokevirtual   rx/internal/producers/ProducerObserverArbiter.emitLoop:()V
        //    91: iconst_1       
        //    92: ifne            136
        //    95: aload_0        
        //    96: monitorenter   
        //    97: aload_0        
        //    98: iconst_0       
        //    99: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //   102: aload_0        
        //   103: monitorexit    
        //   104: return         
        //   105: astore_1       
        //   106: aload_0        
        //   107: monitorexit    
        //   108: aload_1        
        //   109: athrow         
        //   110: astore_1       
        //   111: aload_0        
        //   112: monitorexit    
        //   113: aload_1        
        //   114: athrow         
        //   115: astore_1       
        //   116: iconst_0       
        //   117: ifne            129
        //   120: aload_0        
        //   121: monitorenter   
        //   122: aload_0        
        //   123: iconst_0       
        //   124: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //   127: aload_0        
        //   128: monitorexit    
        //   129: aload_1        
        //   130: athrow         
        //   131: astore_1       
        //   132: aload_0        
        //   133: monitorexit    
        //   134: aload_1        
        //   135: athrow         
        //   136: return         
        //    Signature:
        //  (TT;)V
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      15     110    115    Any
        //  24     40     110    115    Any
        //  40     51     110    115    Any
        //  52     59     110    115    Any
        //  59     72     115    136    Any
        //  80     87     115    136    Any
        //  87     91     115    136    Any
        //  97     104    105    110    Any
        //  106    108    105    110    Any
        //  111    113    110    115    Any
        //  122    129    131    136    Any
        //  132    134    131    136    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0087:
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
    public void request(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException("n >= 0 required");
        }
        if (n != 0L) {
            synchronized (this) {
                if (this.emitting) {
                    this.missedRequested += n;
                    return;
                }
            }
            this.emitting = true;
            // monitorexit(this)
            final Producer currentProducer = this.currentProducer;
            try {
                long requested;
                if ((requested = this.requested + n) < 0L) {
                    requested = Long.MAX_VALUE;
                }
                this.requested = requested;
                this.emitLoop();
                Label_0109: {
                    if (true) {
                        break Label_0109;
                    }
                    synchronized (this) {
                        this.emitting = false;
                        // monitorexit(this)
                        if (currentProducer != null) {
                            currentProducer.request(n);
                        }
                    }
                }
            }
            finally {
                Label_0145: {
                    if (false) {
                        break Label_0145;
                    }
                    synchronized (this) {
                        this.emitting = false;
                    }
                    // monitorexit(this)
                }
            }
        }
    }
    
    public void setProducer(final Producer p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //     6: ifeq            28
        //     9: aload_1        
        //    10: ifnull          21
        //    13: aload_0        
        //    14: aload_1        
        //    15: putfield        rx/internal/producers/ProducerObserverArbiter.missedProducer:Lrx/Producer;
        //    18: aload_0        
        //    19: monitorexit    
        //    20: return         
        //    21: getstatic       rx/internal/producers/ProducerObserverArbiter.NULL_PRODUCER:Lrx/Producer;
        //    24: astore_1       
        //    25: goto            13
        //    28: aload_0        
        //    29: iconst_1       
        //    30: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //    33: aload_0        
        //    34: monitorexit    
        //    35: aload_0        
        //    36: aload_1        
        //    37: putfield        rx/internal/producers/ProducerObserverArbiter.currentProducer:Lrx/Producer;
        //    40: aload_0        
        //    41: getfield        rx/internal/producers/ProducerObserverArbiter.requested:J
        //    44: lstore_2       
        //    45: aload_0        
        //    46: invokevirtual   rx/internal/producers/ProducerObserverArbiter.emitLoop:()V
        //    49: iconst_1       
        //    50: ifne            62
        //    53: aload_0        
        //    54: monitorenter   
        //    55: aload_0        
        //    56: iconst_0       
        //    57: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //    60: aload_0        
        //    61: monitorexit    
        //    62: aload_1        
        //    63: ifnull          111
        //    66: lload_2        
        //    67: lconst_0       
        //    68: lcmp           
        //    69: ifeq            111
        //    72: aload_1        
        //    73: lload_2        
        //    74: invokeinterface rx/Producer.request:(J)V
        //    79: return         
        //    80: astore_1       
        //    81: aload_0        
        //    82: monitorexit    
        //    83: aload_1        
        //    84: athrow         
        //    85: astore_1       
        //    86: aload_0        
        //    87: monitorexit    
        //    88: aload_1        
        //    89: athrow         
        //    90: astore_1       
        //    91: iconst_0       
        //    92: ifne            104
        //    95: aload_0        
        //    96: monitorenter   
        //    97: aload_0        
        //    98: iconst_0       
        //    99: putfield        rx/internal/producers/ProducerObserverArbiter.emitting:Z
        //   102: aload_0        
        //   103: monitorexit    
        //   104: aload_1        
        //   105: athrow         
        //   106: astore_1       
        //   107: aload_0        
        //   108: monitorexit    
        //   109: aload_1        
        //   110: athrow         
        //   111: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      9      80     85     Any
        //  13     20     80     85     Any
        //  21     25     80     85     Any
        //  28     35     80     85     Any
        //  45     49     90     111    Any
        //  55     62     85     90     Any
        //  81     83     80     85     Any
        //  86     88     85     90     Any
        //  97     104    106    111    Any
        //  107    109    106    111    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0062:
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
