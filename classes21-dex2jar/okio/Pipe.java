// 
// Decompiled by Procyon v0.5.34
// 

package okio;

import java.io.IOException;

public final class Pipe
{
    final Buffer buffer;
    final long maxBufferSize;
    private final Sink sink;
    boolean sinkClosed;
    private final Source source;
    boolean sourceClosed;
    
    public Pipe(final long maxBufferSize) {
        this.buffer = new Buffer();
        this.sink = new PipeSink();
        this.source = new PipeSource();
        if (maxBufferSize < 1L) {
            throw new IllegalArgumentException("maxBufferSize < 1: " + maxBufferSize);
        }
        this.maxBufferSize = maxBufferSize;
    }
    
    public Sink sink() {
        return this.sink;
    }
    
    public Source source() {
        return this.source;
    }
    
    final class PipeSink implements Sink
    {
        final Timeout timeout;
        
        PipeSink() {
            this.timeout = new Timeout();
        }
        
        @Override
        public void close() throws IOException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //     4: getfield        okio/Pipe.buffer:Lokio/Buffer;
            //     7: astore_1       
            //     8: aload_1        
            //     9: monitorenter   
            //    10: aload_0        
            //    11: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //    14: getfield        okio/Pipe.sinkClosed:Z
            //    17: ifeq            23
            //    20: aload_1        
            //    21: monitorexit    
            //    22: return         
            //    23: aload_0        
            //    24: invokevirtual   okio/Pipe$PipeSink.flush:()V
            //    27: aload_0        
            //    28: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //    31: iconst_1       
            //    32: putfield        okio/Pipe.sinkClosed:Z
            //    35: aload_0        
            //    36: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //    39: getfield        okio/Pipe.buffer:Lokio/Buffer;
            //    42: invokevirtual   java/lang/Object.notifyAll:()V
            //    45: aload_1        
            //    46: monitorexit    
            //    47: return         
            //    48: astore_2       
            //    49: aload_1        
            //    50: monitorexit    
            //    51: aload_2        
            //    52: athrow         
            //    53: astore_2       
            //    54: aload_0        
            //    55: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //    58: iconst_1       
            //    59: putfield        okio/Pipe.sinkClosed:Z
            //    62: aload_0        
            //    63: getfield        okio/Pipe$PipeSink.this$0:Lokio/Pipe;
            //    66: getfield        okio/Pipe.buffer:Lokio/Buffer;
            //    69: invokevirtual   java/lang/Object.notifyAll:()V
            //    72: aload_2        
            //    73: athrow         
            //    Exceptions:
            //  throws java.io.IOException
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type
            //  -----  -----  -----  -----  ----
            //  10     22     48     53     Any
            //  23     27     53     74     Any
            //  27     47     48     53     Any
            //  49     51     48     53     Any
            //  54     74     48     53     Any
            // 
            // The error that occurred was:
            // 
            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0023:
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
        public void flush() throws IOException {
            while (true) {
                Label_0049: {
                    synchronized (Pipe.this.buffer) {
                        if (Pipe.this.sinkClosed) {
                            throw new IllegalStateException("closed");
                        }
                        break Label_0049;
                    }
                    this.timeout.waitUntilNotified(Pipe.this.buffer);
                }
                if (Pipe.this.buffer.size() <= 0L) {
                    // monitorexit(buffer)
                    return;
                }
                if (Pipe.this.sourceClosed) {
                    throw new IOException("source is closed");
                }
                continue;
            }
        }
        
        @Override
        public Timeout timeout() {
            return this.timeout;
        }
        
        @Override
        public void write(final Buffer buffer, long n) throws IOException {
            while (true) {
                Label_0079: {
                    synchronized (Pipe.this.buffer) {
                        if (Pipe.this.sinkClosed) {
                            throw new IllegalStateException("closed");
                        }
                        break Label_0079;
                    }
                    final long n2 = Pipe.this.maxBufferSize - Pipe.this.buffer.size();
                    if (n2 == 0L) {
                        this.timeout.waitUntilNotified(Pipe.this.buffer);
                    }
                    else {
                        final long min = Math.min(n2, n);
                        final Buffer buffer2;
                        Pipe.this.buffer.write(buffer2, min);
                        n -= min;
                        Pipe.this.buffer.notifyAll();
                    }
                }
                if (n <= 0L) {
                    // monitorexit(buffer3)
                    return;
                }
                if (Pipe.this.sourceClosed) {
                    throw new IOException("source is closed");
                }
                continue;
            }
        }
    }
    
    final class PipeSource implements Source
    {
        final Timeout timeout;
        
        PipeSource() {
            this.timeout = new Timeout();
        }
        
        @Override
        public void close() throws IOException {
            synchronized (Pipe.this.buffer) {
                Pipe.this.sourceClosed = true;
                Pipe.this.buffer.notifyAll();
            }
        }
        
        @Override
        public long read(final Buffer buffer, long read) throws IOException {
            while (true) {
                Label_0052: {
                    synchronized (Pipe.this.buffer) {
                        if (Pipe.this.sourceClosed) {
                            throw new IllegalStateException("closed");
                        }
                        break Label_0052;
                    }
                    this.timeout.waitUntilNotified(Pipe.this.buffer);
                }
                if (Pipe.this.buffer.size() != 0L) {
                    final Buffer buffer2;
                    read = Pipe.this.buffer.read(buffer2, read);
                    Pipe.this.buffer.notifyAll();
                    // monitorexit(buffer3)
                    return read;
                }
                if (Pipe.this.sinkClosed) {
                    // monitorexit(buffer3)
                    return -1L;
                }
                continue;
            }
        }
        
        @Override
        public Timeout timeout() {
            return this.timeout;
        }
    }
}
