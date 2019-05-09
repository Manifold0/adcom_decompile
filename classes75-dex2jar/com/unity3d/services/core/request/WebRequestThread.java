// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.request;

import java.util.List;
import java.util.Map;
import com.unity3d.services.core.log.DeviceLog;
import java.util.concurrent.TimeUnit;
import java.util.Iterator;
import java.util.concurrent.LinkedBlockingQueue;

public class WebRequestThread
{
    private static int _corePoolSize;
    private static long _keepAliveTime;
    private static int _maximumPoolSize;
    private static CancelableThreadPoolExecutor _pool;
    private static LinkedBlockingQueue<Runnable> _queue;
    private static boolean _ready;
    private static final Object _readyLock;
    
    static {
        WebRequestThread._ready = false;
        WebRequestThread._corePoolSize = 1;
        WebRequestThread._maximumPoolSize = 1;
        WebRequestThread._keepAliveTime = 1000L;
        _readyLock = new Object();
    }
    
    public static void cancel() {
        Label_0077: {
            synchronized (WebRequestThread.class) {
                if (WebRequestThread._pool == null) {
                    break Label_0077;
                }
                WebRequestThread._pool.cancel();
                for (final Runnable runnable : WebRequestThread._queue) {
                    if (runnable instanceof WebRequestRunnable) {
                        ((WebRequestRunnable)runnable).setCancelStatus(true);
                    }
                }
            }
            WebRequestThread._queue.clear();
            WebRequestThread._pool.purge();
        }
    }
    // monitorexit(WebRequestThread.class)
    
    private static void init() {
        synchronized (WebRequestThread.class) {
            WebRequestThread._queue = new LinkedBlockingQueue<Runnable>();
            (WebRequestThread._pool = new CancelableThreadPoolExecutor(WebRequestThread._corePoolSize, WebRequestThread._maximumPoolSize, WebRequestThread._keepAliveTime, TimeUnit.MILLISECONDS, WebRequestThread._queue)).prestartAllCoreThreads();
            WebRequestThread._queue.add(new Runnable() {
                @Override
                public void run() {
                    WebRequestThread._ready = true;
                    synchronized (WebRequestThread._readyLock) {
                        WebRequestThread._readyLock.notify();
                    }
                }
            });
            while (!WebRequestThread._ready) {
                try {
                    synchronized (WebRequestThread._readyLock) {
                        WebRequestThread._readyLock.wait();
                    }
                }
                catch (InterruptedException ex) {
                    DeviceLog.debug("Couldn't synchronize thread");
                }
                break;
            }
        }
    }
    
    public static void request(final String s, final WebRequest.RequestType requestType, final Map<String, List<String>> map, final Integer n, final Integer n2, final IWebRequestListener webRequestListener) {
        synchronized (WebRequestThread.class) {
            request(s, requestType, map, null, n, n2, webRequestListener);
        }
    }
    
    public static void request(final String s, final WebRequest.RequestType requestType, final Map<String, List<String>> map, final String s2, final Integer n, final Integer n2, final IWebRequestListener webRequestListener) {
        synchronized (WebRequestThread.class) {
            if (!WebRequestThread._ready) {
                init();
            }
            if (s == null || s.length() < 3) {
                webRequestListener.onFailed(s, "Request is NULL or too short");
            }
            else {
                WebRequestThread._queue.add(new WebRequestRunnable(s, requestType.name(), s2, n, n2, map, webRequestListener));
            }
        }
    }
    
    public static void reset() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: monitorenter   
        //     3: invokestatic    com/unity3d/services/core/request/WebRequestThread.cancel:()V
        //     6: getstatic       com/unity3d/services/core/request/WebRequestThread._pool:Lcom/unity3d/services/core/request/CancelableThreadPoolExecutor;
        //     9: ifnull          49
        //    12: getstatic       com/unity3d/services/core/request/WebRequestThread._pool:Lcom/unity3d/services/core/request/CancelableThreadPoolExecutor;
        //    15: invokevirtual   com/unity3d/services/core/request/CancelableThreadPoolExecutor.shutdown:()V
        //    18: getstatic       com/unity3d/services/core/request/WebRequestThread._pool:Lcom/unity3d/services/core/request/CancelableThreadPoolExecutor;
        //    21: ldc2_w          9223372036854775807
        //    24: getstatic       java/util/concurrent/TimeUnit.NANOSECONDS:Ljava/util/concurrent/TimeUnit;
        //    27: invokevirtual   com/unity3d/services/core/request/CancelableThreadPoolExecutor.awaitTermination:(JLjava/util/concurrent/TimeUnit;)Z
        //    30: pop            
        //    31: getstatic       com/unity3d/services/core/request/WebRequestThread._queue:Ljava/util/concurrent/LinkedBlockingQueue;
        //    34: invokevirtual   java/util/concurrent/LinkedBlockingQueue.clear:()V
        //    37: aconst_null    
        //    38: putstatic       com/unity3d/services/core/request/WebRequestThread._pool:Lcom/unity3d/services/core/request/CancelableThreadPoolExecutor;
        //    41: aconst_null    
        //    42: putstatic       com/unity3d/services/core/request/WebRequestThread._queue:Ljava/util/concurrent/LinkedBlockingQueue;
        //    45: iconst_0       
        //    46: putstatic       com/unity3d/services/core/request/WebRequestThread._ready:Z
        //    49: ldc             Lcom/unity3d/services/core/request/WebRequestThread;.class
        //    51: monitorexit    
        //    52: return         
        //    53: astore_0       
        //    54: ldc             Lcom/unity3d/services/core/request/WebRequestThread;.class
        //    56: monitorexit    
        //    57: aload_0        
        //    58: athrow         
        //    59: astore_0       
        //    60: goto            31
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  3      18     53     59     Any
        //  18     31     59     63     Ljava/lang/InterruptedException;
        //  18     31     53     59     Any
        //  31     49     53     59     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0031:
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
    
    public static boolean resolve(final String s, final IResolveHostListener resolveHostListener) {
        // monitorenter(WebRequestThread.class)
        Label_0015: {
            if (s == null) {
                break Label_0015;
            }
            try {
                boolean b;
                if (s.length() < 3) {
                    resolveHostListener.onFailed(s, ResolveHostError.INVALID_HOST, "Host is NULL");
                    b = false;
                }
                else {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            // 
                            // This method could not be decompiled.
                            // 
                            // Original Bytecode:
                            // 
                            //     3: dup            
                            //     4: invokespecial   android/os/ConditionVariable.<init>:()V
                            //     7: astore          4
                            //     9: aconst_null    
                            //    10: astore_2       
                            //    11: new             Ljava/lang/Thread;
                            //    14: dup            
                            //    15: new             Lcom/unity3d/services/core/request/WebRequestThread$2$1;
                            //    18: dup            
                            //    19: aload_0        
                            //    20: aload           4
                            //    22: invokespecial   com/unity3d/services/core/request/WebRequestThread$2$1.<init>:(Lcom/unity3d/services/core/request/WebRequestThread$2;Landroid/os/ConditionVariable;)V
                            //    25: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;)V
                            //    28: astore_1       
                            //    29: aload_1        
                            //    30: invokevirtual   java/lang/Thread.start:()V
                            //    33: aload           4
                            //    35: ldc2_w          20000
                            //    38: invokevirtual   android/os/ConditionVariable.block:(J)Z
                            //    41: ifne            70
                            //    44: aload_1        
                            //    45: ifnull          70
                            //    48: aload_1        
                            //    49: invokevirtual   java/lang/Thread.interrupt:()V
                            //    52: aload_0        
                            //    53: getfield        com/unity3d/services/core/request/WebRequestThread$2.val$listener:Lcom/unity3d/services/core/request/IResolveHostListener;
                            //    56: aload_0        
                            //    57: getfield        com/unity3d/services/core/request/WebRequestThread$2.val$host:Ljava/lang/String;
                            //    60: getstatic       com/unity3d/services/core/request/ResolveHostError.TIMEOUT:Lcom/unity3d/services/core/request/ResolveHostError;
                            //    63: ldc             "Timeout"
                            //    65: invokeinterface com/unity3d/services/core/request/IResolveHostListener.onFailed:(Ljava/lang/String;Lcom/unity3d/services/core/request/ResolveHostError;Ljava/lang/String;)V
                            //    70: return         
                            //    71: astore_3       
                            //    72: aload_2        
                            //    73: astore_1       
                            //    74: aload_3        
                            //    75: astore_2       
                            //    76: ldc             "Exception while resolving host"
                            //    78: aload_2        
                            //    79: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
                            //    82: aload_0        
                            //    83: getfield        com/unity3d/services/core/request/WebRequestThread$2.val$listener:Lcom/unity3d/services/core/request/IResolveHostListener;
                            //    86: aload_0        
                            //    87: getfield        com/unity3d/services/core/request/WebRequestThread$2.val$host:Ljava/lang/String;
                            //    90: getstatic       com/unity3d/services/core/request/ResolveHostError.UNEXPECTED_EXCEPTION:Lcom/unity3d/services/core/request/ResolveHostError;
                            //    93: aload_2        
                            //    94: invokevirtual   java/lang/Exception.getMessage:()Ljava/lang/String;
                            //    97: invokeinterface com/unity3d/services/core/request/IResolveHostListener.onFailed:(Ljava/lang/String;Lcom/unity3d/services/core/request/ResolveHostError;Ljava/lang/String;)V
                            //   102: goto            33
                            //   105: astore_2       
                            //   106: goto            76
                            //    Exceptions:
                            //  Try           Handler
                            //  Start  End    Start  End    Type                 
                            //  -----  -----  -----  -----  ---------------------
                            //  11     29     71     76     Ljava/lang/Exception;
                            //  29     33     105    109    Ljava/lang/Exception;
                            // 
                            // The error that occurred was:
                            // 
                            // java.lang.IllegalStateException: Expression is linked from several locations: Label_0033:
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
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:441)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
                            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:494)
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
                    }).start();
                    b = true;
                }
                return b;
            }
            finally {
            }
            // monitorexit(WebRequestThread.class)
        }
    }
    
    public static void setConcurrentRequestCount(final int corePoolSize) {
        synchronized (WebRequestThread.class) {
            WebRequestThread._corePoolSize = corePoolSize;
            WebRequestThread._maximumPoolSize = WebRequestThread._corePoolSize;
            if (WebRequestThread._pool != null) {
                WebRequestThread._pool.setCorePoolSize(WebRequestThread._corePoolSize);
                WebRequestThread._pool.setMaximumPoolSize(WebRequestThread._maximumPoolSize);
            }
        }
    }
    
    public static void setKeepAliveTime(final long keepAliveTime) {
        synchronized (WebRequestThread.class) {
            WebRequestThread._keepAliveTime = keepAliveTime;
            if (WebRequestThread._pool != null) {
                WebRequestThread._pool.setKeepAliveTime(WebRequestThread._keepAliveTime, TimeUnit.MILLISECONDS);
            }
        }
    }
    
    public static void setMaximumPoolSize(final int maximumPoolSize) {
        synchronized (WebRequestThread.class) {
            WebRequestThread._maximumPoolSize = maximumPoolSize;
            if (WebRequestThread._pool != null) {
                WebRequestThread._pool.setMaximumPoolSize(WebRequestThread._maximumPoolSize);
            }
        }
    }
}
