// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.provider;

import java.util.concurrent.Callable;
import android.support.annotation.VisibleForTesting;
import android.os.Message;
import android.os.HandlerThread;
import android.os.Handler;
import android.support.annotation.GuardedBy;
import android.os.Handler$Callback;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;

@RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
public class SelfDestructiveThread
{
    private static final int MSG_DESTRUCTION = 0;
    private static final int MSG_INVOKE_RUNNABLE = 1;
    private Handler$Callback mCallback;
    private final int mDestructAfterMillisec;
    @GuardedBy("mLock")
    private int mGeneration;
    @GuardedBy("mLock")
    private Handler mHandler;
    private final Object mLock;
    private final int mPriority;
    @GuardedBy("mLock")
    private HandlerThread mThread;
    private final String mThreadName;
    
    public SelfDestructiveThread(final String mThreadName, final int mPriority, final int mDestructAfterMillisec) {
        this.mLock = new Object();
        this.mCallback = (Handler$Callback)new Handler$Callback() {
            public boolean handleMessage(final Message message) {
                switch (message.what) {
                    default: {
                        return true;
                    }
                    case 1: {
                        SelfDestructiveThread.this.onInvokeRunnable((Runnable)message.obj);
                        return true;
                    }
                    case 0: {
                        SelfDestructiveThread.this.onDestruction();
                        return true;
                    }
                }
            }
        };
        this.mThreadName = mThreadName;
        this.mPriority = mPriority;
        this.mDestructAfterMillisec = mDestructAfterMillisec;
        this.mGeneration = 0;
    }
    
    private void onDestruction() {
        synchronized (this.mLock) {
            if (this.mHandler.hasMessages(1)) {
                return;
            }
            this.mThread.quit();
            this.mThread = null;
            this.mHandler = null;
        }
    }
    
    private void onInvokeRunnable(final Runnable runnable) {
        runnable.run();
        synchronized (this.mLock) {
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(0), (long)this.mDestructAfterMillisec);
        }
    }
    
    private void post(final Runnable runnable) {
        synchronized (this.mLock) {
            if (this.mThread == null) {
                (this.mThread = new HandlerThread(this.mThreadName, this.mPriority)).start();
                this.mHandler = new Handler(this.mThread.getLooper(), this.mCallback);
                ++this.mGeneration;
            }
            this.mHandler.removeMessages(0);
            this.mHandler.sendMessage(this.mHandler.obtainMessage(1, (Object)runnable));
        }
    }
    
    @VisibleForTesting
    public int getGeneration() {
        synchronized (this.mLock) {
            return this.mGeneration;
        }
    }
    
    @VisibleForTesting
    public boolean isRunning() {
        while (true) {
            synchronized (this.mLock) {
                if (this.mThread != null) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public <T> void postAndReply(final Callable<T> callable, final ReplyCallback<T> replyCallback) {
        this.post(new Runnable() {
            final /* synthetic */ Handler val$callingHandler = new Handler();
            
            @Override
            public void run() {
                while (true) {
                    try {
                        final Object call = callable.call();
                        this.val$callingHandler.post((Runnable)new Runnable() {
                            @Override
                            public void run() {
                                replyCallback.onReply(call);
                            }
                        });
                    }
                    catch (Exception ex) {
                        final Object call = null;
                        continue;
                    }
                    break;
                }
            }
        });
    }
    
    public <T> T postAndWait(final Callable<T> p0, final int p1) throws InterruptedException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/util/concurrent/locks/ReentrantLock.<init>:()V
        //     7: astore          7
        //     9: aload           7
        //    11: invokevirtual   java/util/concurrent/locks/ReentrantLock.newCondition:()Ljava/util/concurrent/locks/Condition;
        //    14: astore          8
        //    16: new             Ljava/util/concurrent/atomic/AtomicReference;
        //    19: dup            
        //    20: invokespecial   java/util/concurrent/atomic/AtomicReference.<init>:()V
        //    23: astore          9
        //    25: new             Ljava/util/concurrent/atomic/AtomicBoolean;
        //    28: dup            
        //    29: iconst_1       
        //    30: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:(Z)V
        //    33: astore          10
        //    35: aload_0        
        //    36: new             Landroid/support/v4/provider/SelfDestructiveThread$3;
        //    39: dup            
        //    40: aload_0        
        //    41: aload           9
        //    43: aload_1        
        //    44: aload           7
        //    46: aload           10
        //    48: aload           8
        //    50: invokespecial   android/support/v4/provider/SelfDestructiveThread$3.<init>:(Landroid/support/v4/provider/SelfDestructiveThread;Ljava/util/concurrent/atomic/AtomicReference;Ljava/util/concurrent/Callable;Ljava/util/concurrent/locks/ReentrantLock;Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/locks/Condition;)V
        //    53: invokespecial   android/support/v4/provider/SelfDestructiveThread.post:(Ljava/lang/Runnable;)V
        //    56: aload           7
        //    58: invokevirtual   java/util/concurrent/locks/ReentrantLock.lock:()V
        //    61: aload           10
        //    63: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
        //    66: ifne            82
        //    69: aload           9
        //    71: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //    74: astore_1       
        //    75: aload           7
        //    77: invokevirtual   java/util/concurrent/locks/ReentrantLock.unlock:()V
        //    80: aload_1        
        //    81: areturn        
        //    82: getstatic       java/util/concurrent/TimeUnit.MILLISECONDS:Ljava/util/concurrent/TimeUnit;
        //    85: iload_2        
        //    86: i2l            
        //    87: invokevirtual   java/util/concurrent/TimeUnit.toNanos:(J)J
        //    90: lstore_3       
        //    91: aload           8
        //    93: lload_3        
        //    94: invokeinterface java/util/concurrent/locks/Condition.awaitNanos:(J)J
        //    99: lstore          5
        //   101: aload           10
        //   103: invokevirtual   java/util/concurrent/atomic/AtomicBoolean.get:()Z
        //   106: ifne            122
        //   109: aload           9
        //   111: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
        //   114: astore_1       
        //   115: aload           7
        //   117: invokevirtual   java/util/concurrent/locks/ReentrantLock.unlock:()V
        //   120: aload_1        
        //   121: areturn        
        //   122: lload           5
        //   124: lstore_3       
        //   125: lload           5
        //   127: lconst_0       
        //   128: lcmp           
        //   129: ifgt            91
        //   132: new             Ljava/lang/InterruptedException;
        //   135: dup            
        //   136: ldc             "timeout"
        //   138: invokespecial   java/lang/InterruptedException.<init>:(Ljava/lang/String;)V
        //   141: athrow         
        //   142: astore_1       
        //   143: aload           7
        //   145: invokevirtual   java/util/concurrent/locks/ReentrantLock.unlock:()V
        //   148: aload_1        
        //   149: athrow         
        //   150: astore_1       
        //   151: lload_3        
        //   152: lstore          5
        //   154: goto            101
        //    Exceptions:
        //  throws java.lang.InterruptedException
        //    Signature:
        //  <T:Ljava/lang/Object;>(Ljava/util/concurrent/Callable<TT;>;I)TT;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  61     75     142    150    Any
        //  82     91     142    150    Any
        //  91     101    150    157    Ljava/lang/InterruptedException;
        //  91     101    142    150    Any
        //  101    115    142    150    Any
        //  132    142    142    150    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0091:
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
    
    public interface ReplyCallback<T>
    {
        void onReply(final T p0);
    }
}
