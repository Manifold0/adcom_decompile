// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.producers;

import rx.Producer;

public final class ProducerArbiter implements Producer
{
    static final Producer NULL_PRODUCER;
    Producer currentProducer;
    boolean emitting;
    long missedProduced;
    Producer missedProducer;
    long missedRequested;
    long requested;
    
    static {
        NULL_PRODUCER = new Producer() {
            @Override
            public void request(final long n) {
            }
        };
    }
    
    public void emitLoop() {
    Label_0109_Outer:
        while (true) {
            long missedRequested = 0L;
            Label_0185: {
                Producer missedProducer = null;
                long requested = 0L;
            Label_0168:
                while (true) {
                    final long missedProduced;
                    Label_0137: {
                        synchronized (this) {
                            missedRequested = this.missedRequested;
                            missedProduced = this.missedProduced;
                            missedProducer = this.missedProducer;
                            if (missedRequested == 0L && missedProduced == 0L && missedProducer == null) {
                                this.emitting = false;
                                return;
                            }
                            this.missedRequested = 0L;
                            this.missedProduced = 0L;
                            this.missedProducer = null;
                            // monitorexit(this)
                            final long n = requested = this.requested;
                            if (n != Long.MAX_VALUE) {
                                requested = n + missedRequested;
                                if (requested >= 0L && requested != Long.MAX_VALUE) {
                                    break Label_0137;
                                }
                                requested = Long.MAX_VALUE;
                                this.requested = Long.MAX_VALUE;
                            }
                            if (missedProducer == null) {
                                break Label_0185;
                            }
                            if (missedProducer == ProducerArbiter.NULL_PRODUCER) {
                                this.currentProducer = null;
                                continue Label_0109_Outer;
                            }
                            break Label_0168;
                        }
                    }
                    final long requested2 = requested - missedProduced;
                    if (requested2 < 0L) {
                        break;
                    }
                    requested = requested2;
                    this.requested = requested2;
                    continue;
                }
                (this.currentProducer = missedProducer).request(requested);
                continue Label_0109_Outer;
            }
            final Producer currentProducer = this.currentProducer;
            if (currentProducer != null && missedRequested != 0L) {
                currentProducer.request(missedRequested);
            }
        }
        throw new IllegalStateException("more produced than requested");
    }
    
    public void produced(final long p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: lconst_0       
        //     2: lcmp           
        //     3: ifgt            16
        //     6: new             Ljava/lang/IllegalArgumentException;
        //     9: dup            
        //    10: ldc             "n > 0 required"
        //    12: invokespecial   java/lang/IllegalArgumentException.<init>:(Ljava/lang/String;)V
        //    15: athrow         
        //    16: aload_0        
        //    17: monitorenter   
        //    18: aload_0        
        //    19: getfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    22: ifeq            38
        //    25: aload_0        
        //    26: aload_0        
        //    27: getfield        rx/internal/producers/ProducerArbiter.missedProduced:J
        //    30: lload_1        
        //    31: ladd           
        //    32: putfield        rx/internal/producers/ProducerArbiter.missedProduced:J
        //    35: aload_0        
        //    36: monitorexit    
        //    37: return         
        //    38: aload_0        
        //    39: iconst_1       
        //    40: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    43: aload_0        
        //    44: monitorexit    
        //    45: aload_0        
        //    46: getfield        rx/internal/producers/ProducerArbiter.requested:J
        //    49: lstore_3       
        //    50: lload_3        
        //    51: ldc2_w          9223372036854775807
        //    54: lcmp           
        //    55: ifeq            108
        //    58: lload_3        
        //    59: lload_1        
        //    60: lsub           
        //    61: lstore_1       
        //    62: lload_1        
        //    63: lconst_0       
        //    64: lcmp           
        //    65: ifge            103
        //    68: new             Ljava/lang/IllegalStateException;
        //    71: dup            
        //    72: ldc             "more items arrived than were requested"
        //    74: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //    77: athrow         
        //    78: astore          5
        //    80: iconst_0       
        //    81: ifne            93
        //    84: aload_0        
        //    85: monitorenter   
        //    86: aload_0        
        //    87: iconst_0       
        //    88: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    91: aload_0        
        //    92: monitorexit    
        //    93: aload           5
        //    95: athrow         
        //    96: astore          5
        //    98: aload_0        
        //    99: monitorexit    
        //   100: aload           5
        //   102: athrow         
        //   103: aload_0        
        //   104: lload_1        
        //   105: putfield        rx/internal/producers/ProducerArbiter.requested:J
        //   108: aload_0        
        //   109: invokevirtual   rx/internal/producers/ProducerArbiter.emitLoop:()V
        //   112: iconst_1       
        //   113: ifne            140
        //   116: aload_0        
        //   117: monitorenter   
        //   118: aload_0        
        //   119: iconst_0       
        //   120: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //   123: aload_0        
        //   124: monitorexit    
        //   125: return         
        //   126: astore          5
        //   128: aload_0        
        //   129: monitorexit    
        //   130: aload           5
        //   132: athrow         
        //   133: astore          5
        //   135: aload_0        
        //   136: monitorexit    
        //   137: aload           5
        //   139: athrow         
        //   140: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  18     37     96     103    Any
        //  38     45     96     103    Any
        //  45     50     78     140    Any
        //  68     78     78     140    Any
        //  86     93     133    140    Any
        //  98     100    96     103    Any
        //  103    108    78     140    Any
        //  108    112    78     140    Any
        //  118    125    126    133    Any
        //  128    130    126    133    Any
        //  135    137    133    140    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0093:
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
            try {
                long requested;
                if ((requested = this.requested + n) < 0L) {
                    requested = Long.MAX_VALUE;
                }
                this.requested = requested;
                final Producer currentProducer = this.currentProducer;
                if (currentProducer != null) {
                    currentProducer.request(n);
                }
                this.emitLoop();
                if (!true) {
                    synchronized (this) {
                        this.emitting = false;
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
        //     3: getfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //     6: ifeq            27
        //     9: aload_1        
        //    10: astore_2       
        //    11: aload_1        
        //    12: ifnonnull       19
        //    15: getstatic       rx/internal/producers/ProducerArbiter.NULL_PRODUCER:Lrx/Producer;
        //    18: astore_2       
        //    19: aload_0        
        //    20: aload_2        
        //    21: putfield        rx/internal/producers/ProducerArbiter.missedProducer:Lrx/Producer;
        //    24: aload_0        
        //    25: monitorexit    
        //    26: return         
        //    27: aload_0        
        //    28: iconst_1       
        //    29: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    32: aload_0        
        //    33: monitorexit    
        //    34: aload_0        
        //    35: aload_1        
        //    36: putfield        rx/internal/producers/ProducerArbiter.currentProducer:Lrx/Producer;
        //    39: aload_1        
        //    40: ifnull          53
        //    43: aload_1        
        //    44: aload_0        
        //    45: getfield        rx/internal/producers/ProducerArbiter.requested:J
        //    48: invokeinterface rx/Producer.request:(J)V
        //    53: aload_0        
        //    54: invokevirtual   rx/internal/producers/ProducerArbiter.emitLoop:()V
        //    57: iconst_1       
        //    58: ifne            102
        //    61: aload_0        
        //    62: monitorenter   
        //    63: aload_0        
        //    64: iconst_0       
        //    65: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    68: aload_0        
        //    69: monitorexit    
        //    70: return         
        //    71: astore_1       
        //    72: aload_0        
        //    73: monitorexit    
        //    74: aload_1        
        //    75: athrow         
        //    76: astore_1       
        //    77: aload_0        
        //    78: monitorexit    
        //    79: aload_1        
        //    80: athrow         
        //    81: astore_1       
        //    82: iconst_0       
        //    83: ifne            95
        //    86: aload_0        
        //    87: monitorenter   
        //    88: aload_0        
        //    89: iconst_0       
        //    90: putfield        rx/internal/producers/ProducerArbiter.emitting:Z
        //    93: aload_0        
        //    94: monitorexit    
        //    95: aload_1        
        //    96: athrow         
        //    97: astore_1       
        //    98: aload_0        
        //    99: monitorexit    
        //   100: aload_1        
        //   101: athrow         
        //   102: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      9      76     81     Any
        //  15     19     76     81     Any
        //  19     26     76     81     Any
        //  27     34     76     81     Any
        //  34     39     81     102    Any
        //  43     53     81     102    Any
        //  53     57     81     102    Any
        //  63     70     71     76     Any
        //  72     74     71     76     Any
        //  77     79     76     81     Any
        //  88     95     97     102    Any
        //  98     100    97     102    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0053:
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
