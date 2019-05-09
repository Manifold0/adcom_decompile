// 
// Decompiled by Procyon v0.5.34
// 

package rx.internal.util;

import rx.annotations.Experimental;
import rx.Producer;
import java.util.concurrent.atomic.AtomicLong;

@Experimental
public final class BackpressureDrainManager extends AtomicLong implements Producer
{
    private static final long serialVersionUID = 2826241102729529449L;
    final BackpressureQueueCallback actual;
    boolean emitting;
    Throwable exception;
    volatile boolean terminated;
    
    public BackpressureDrainManager(final BackpressureQueueCallback actual) {
        this.actual = actual;
    }
    
    public void drain() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: monitorenter   
        //     2: aload_0        
        //     3: getfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //     6: ifeq            12
        //     9: aload_0        
        //    10: monitorexit    
        //    11: return         
        //    12: aload_0        
        //    13: iconst_1       
        //    14: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //    17: aload_0        
        //    18: getfield        rx/internal/util/BackpressureDrainManager.terminated:Z
        //    21: istore          6
        //    23: aload_0        
        //    24: monitorexit    
        //    25: aload_0        
        //    26: invokevirtual   rx/internal/util/BackpressureDrainManager.get:()J
        //    29: lstore          8
        //    31: iconst_0       
        //    32: istore          5
        //    34: iconst_0       
        //    35: istore          4
        //    37: iload           5
        //    39: istore_1       
        //    40: aload_0        
        //    41: getfield        rx/internal/util/BackpressureDrainManager.actual:Lrx/internal/util/BackpressureDrainManager$BackpressureQueueCallback;
        //    44: astore          12
        //    46: goto            390
        //    49: iload           6
        //    51: ifeq            201
        //    54: iload           5
        //    56: istore_1       
        //    57: aload           12
        //    59: invokeinterface rx/internal/util/BackpressureDrainManager$BackpressureQueueCallback.peek:()Ljava/lang/Object;
        //    64: ifnonnull       108
        //    67: iconst_1       
        //    68: istore_1       
        //    69: aload           12
        //    71: aload_0        
        //    72: getfield        rx/internal/util/BackpressureDrainManager.exception:Ljava/lang/Throwable;
        //    75: invokeinterface rx/internal/util/BackpressureDrainManager$BackpressureQueueCallback.complete:(Ljava/lang/Throwable;)V
        //    80: iconst_1       
        //    81: ifne            389
        //    84: aload_0        
        //    85: monitorenter   
        //    86: aload_0        
        //    87: iconst_0       
        //    88: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //    91: aload_0        
        //    92: monitorexit    
        //    93: return         
        //    94: astore          12
        //    96: aload_0        
        //    97: monitorexit    
        //    98: aload           12
        //   100: athrow         
        //   101: astore          12
        //   103: aload_0        
        //   104: monitorexit    
        //   105: aload           12
        //   107: athrow         
        //   108: lload           8
        //   110: lconst_0       
        //   111: lcmp           
        //   112: ifne            201
        //   115: iload           5
        //   117: istore_1       
        //   118: aload_0        
        //   119: monitorenter   
        //   120: iload           4
        //   122: istore_1       
        //   123: aload_0        
        //   124: getfield        rx/internal/util/BackpressureDrainManager.terminated:Z
        //   127: istore          6
        //   129: iload           4
        //   131: istore_1       
        //   132: aload           12
        //   134: invokeinterface rx/internal/util/BackpressureDrainManager$BackpressureQueueCallback.peek:()Ljava/lang/Object;
        //   139: ifnull          271
        //   142: iconst_1       
        //   143: istore_3       
        //   144: iload           4
        //   146: istore_1       
        //   147: aload_0        
        //   148: invokevirtual   rx/internal/util/BackpressureDrainManager.get:()J
        //   151: ldc2_w          9223372036854775807
        //   154: lcmp           
        //   155: ifne            314
        //   158: iload_3        
        //   159: ifne            276
        //   162: iload           6
        //   164: ifne            276
        //   167: iconst_1       
        //   168: istore_2       
        //   169: iload_2        
        //   170: istore_1       
        //   171: aload_0        
        //   172: iconst_0       
        //   173: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   176: iload_2        
        //   177: istore_1       
        //   178: aload_0        
        //   179: monitorexit    
        //   180: iconst_1       
        //   181: ifne            389
        //   184: aload_0        
        //   185: monitorenter   
        //   186: aload_0        
        //   187: iconst_0       
        //   188: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   191: aload_0        
        //   192: monitorexit    
        //   193: return         
        //   194: astore          12
        //   196: aload_0        
        //   197: monitorexit    
        //   198: aload           12
        //   200: athrow         
        //   201: iload           5
        //   203: istore_1       
        //   204: aload           12
        //   206: invokeinterface rx/internal/util/BackpressureDrainManager$BackpressureQueueCallback.poll:()Ljava/lang/Object;
        //   211: astore          13
        //   213: aload           13
        //   215: ifnull          115
        //   218: iload           5
        //   220: istore_1       
        //   221: aload           12
        //   223: aload           13
        //   225: invokeinterface rx/internal/util/BackpressureDrainManager$BackpressureQueueCallback.accept:(Ljava/lang/Object;)Z
        //   230: istore          7
        //   232: iload           7
        //   234: ifeq            258
        //   237: iconst_1       
        //   238: ifne            389
        //   241: aload_0        
        //   242: monitorenter   
        //   243: aload_0        
        //   244: iconst_0       
        //   245: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   248: aload_0        
        //   249: monitorexit    
        //   250: return         
        //   251: astore          12
        //   253: aload_0        
        //   254: monitorexit    
        //   255: aload           12
        //   257: athrow         
        //   258: lload           8
        //   260: lconst_1       
        //   261: lsub           
        //   262: lstore          8
        //   264: iload_2        
        //   265: iconst_1       
        //   266: iadd           
        //   267: istore_2       
        //   268: goto            392
        //   271: iconst_0       
        //   272: istore_3       
        //   273: goto            144
        //   276: ldc2_w          9223372036854775807
        //   279: lstore          8
        //   281: iload           4
        //   283: istore_1       
        //   284: aload_0        
        //   285: monitorexit    
        //   286: goto            390
        //   289: astore          12
        //   291: aload_0        
        //   292: monitorexit    
        //   293: aload           12
        //   295: athrow         
        //   296: astore          12
        //   298: iload_1        
        //   299: ifne            311
        //   302: aload_0        
        //   303: monitorenter   
        //   304: aload_0        
        //   305: iconst_0       
        //   306: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   309: aload_0        
        //   310: monitorexit    
        //   311: aload           12
        //   313: athrow         
        //   314: iload_2        
        //   315: ineg           
        //   316: i2l            
        //   317: lstore          8
        //   319: iload           4
        //   321: istore_1       
        //   322: aload_0        
        //   323: lload           8
        //   325: invokevirtual   rx/internal/util/BackpressureDrainManager.addAndGet:(J)J
        //   328: lstore          10
        //   330: lload           10
        //   332: lconst_0       
        //   333: lcmp           
        //   334: ifeq            407
        //   337: lload           10
        //   339: lstore          8
        //   341: iload_3        
        //   342: ifne            281
        //   345: goto            407
        //   348: iconst_1       
        //   349: istore_2       
        //   350: iload_2        
        //   351: istore_1       
        //   352: aload_0        
        //   353: iconst_0       
        //   354: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   357: iload_2        
        //   358: istore_1       
        //   359: aload_0        
        //   360: monitorexit    
        //   361: iconst_1       
        //   362: ifne            389
        //   365: aload_0        
        //   366: monitorenter   
        //   367: aload_0        
        //   368: iconst_0       
        //   369: putfield        rx/internal/util/BackpressureDrainManager.emitting:Z
        //   372: aload_0        
        //   373: monitorexit    
        //   374: return         
        //   375: astore          12
        //   377: aload_0        
        //   378: monitorexit    
        //   379: aload           12
        //   381: athrow         
        //   382: astore          12
        //   384: aload_0        
        //   385: monitorexit    
        //   386: aload           12
        //   388: athrow         
        //   389: return         
        //   390: iconst_0       
        //   391: istore_2       
        //   392: lload           8
        //   394: lconst_0       
        //   395: lcmp           
        //   396: ifgt            49
        //   399: iload           6
        //   401: ifeq            115
        //   404: goto            49
        //   407: iload           6
        //   409: ifeq            348
        //   412: lload           10
        //   414: lstore          8
        //   416: iload_3        
        //   417: ifeq            281
        //   420: goto            348
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  2      11     101    108    Any
        //  12     25     101    108    Any
        //  40     46     296    389    Any
        //  57     67     296    389    Any
        //  69     80     296    389    Any
        //  86     93     94     101    Any
        //  96     98     94     101    Any
        //  103    105    101    108    Any
        //  118    120    296    389    Any
        //  123    129    289    296    Any
        //  132    142    289    296    Any
        //  147    158    289    296    Any
        //  171    176    289    296    Any
        //  178    180    289    296    Any
        //  186    193    194    201    Any
        //  196    198    194    201    Any
        //  204    213    296    389    Any
        //  221    232    296    389    Any
        //  243    250    251    258    Any
        //  253    255    251    258    Any
        //  284    286    289    296    Any
        //  291    293    289    296    Any
        //  293    296    296    389    Any
        //  304    311    382    389    Any
        //  322    330    289    296    Any
        //  352    357    289    296    Any
        //  359    361    289    296    Any
        //  367    374    375    382    Any
        //  377    379    375    382    Any
        //  384    386    382    389    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0049:
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
    
    public boolean isTerminated() {
        return this.terminated;
    }
    
    @Override
    public void request(final long n) {
        if (n != 0L) {
            long value;
            int n2;
            long n3;
            do {
                value = this.get();
                if (value == 0L) {
                    n2 = 1;
                }
                else {
                    n2 = 0;
                }
                if (value == Long.MAX_VALUE) {
                    break;
                }
                if (n == Long.MAX_VALUE) {
                    n3 = n;
                    n2 = 1;
                }
                else if (value > Long.MAX_VALUE - n) {
                    n3 = Long.MAX_VALUE;
                }
                else {
                    n3 = value + n;
                }
            } while (!this.compareAndSet(value, n3));
            while (true) {
                if (n2 != 0) {
                    this.drain();
                    return;
                }
                return;
                continue;
            }
        }
    }
    
    public void terminate() {
        this.terminated = true;
    }
    
    public void terminate(final Throwable exception) {
        if (!this.terminated) {
            this.exception = exception;
            this.terminated = true;
        }
    }
    
    public void terminateAndDrain() {
        this.terminated = true;
        this.drain();
    }
    
    public void terminateAndDrain(final Throwable exception) {
        if (!this.terminated) {
            this.exception = exception;
            this.terminated = true;
            this.drain();
        }
    }
    
    public interface BackpressureQueueCallback
    {
        boolean accept(final Object p0);
        
        void complete(final Throwable p0);
        
        Object peek();
        
        Object poll();
    }
}
