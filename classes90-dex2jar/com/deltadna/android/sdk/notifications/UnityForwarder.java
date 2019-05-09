// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import java.lang.reflect.InvocationTargetException;
import android.util.Log;
import java.util.LinkedList;
import java.util.Queue;

final class UnityForwarder
{
    private static final Class<?> PLAYER;
    private static final Class<?> PLAYER_ACTIVITY;
    private static final String TAG;
    private static UnityForwarder instance;
    private final Queue<Message> deferred;
    private boolean loaded;
    
    static {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   java/lang/StringBuilder.<init>:()V
        //     7: ldc             "deltaDNA "
        //     9: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    12: ldc             Lcom/deltadna/android/sdk/notifications/UnityForwarder;.class
        //    14: invokevirtual   java/lang/Class.getSimpleName:()Ljava/lang/String;
        //    17: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    20: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    23: putstatic       com/deltadna/android/sdk/notifications/UnityForwarder.TAG:Ljava/lang/String;
        //    26: ldc             "com.unity3d.player.UnityPlayerActivity"
        //    28: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    31: astore_0       
        //    32: aload_0        
        //    33: putstatic       com/deltadna/android/sdk/notifications/UnityForwarder.PLAYER_ACTIVITY:Ljava/lang/Class;
        //    36: ldc             "com.unity3d.player.UnityPlayer"
        //    38: invokestatic    java/lang/Class.forName:(Ljava/lang/String;)Ljava/lang/Class;
        //    41: astore_0       
        //    42: aload_0        
        //    43: putstatic       com/deltadna/android/sdk/notifications/UnityForwarder.PLAYER:Ljava/lang/Class;
        //    46: return         
        //    47: astore_0       
        //    48: aconst_null    
        //    49: astore_0       
        //    50: goto            32
        //    53: astore_0       
        //    54: aconst_null    
        //    55: astore_0       
        //    56: goto            42
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                              
        //  -----  -----  -----  -----  ----------------------------------
        //  26     32     47     53     Ljava/lang/ClassNotFoundException;
        //  36     42     53     59     Ljava/lang/ClassNotFoundException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0042:
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
    
    private UnityForwarder() {
        this.deferred = new LinkedList<Message>();
    }
    
    static UnityForwarder getInstance() {
        synchronized (UnityForwarder.class) {
            if (UnityForwarder.instance == null) {
                UnityForwarder.instance = new UnityForwarder();
            }
            return UnityForwarder.instance;
        }
    }
    
    static boolean isPresent() {
        return UnityForwarder.PLAYER_ACTIVITY != null;
    }
    
    private static void sendMessage(final String s, final String s2, final String s3) {
        try {
            UnityForwarder.PLAYER.getDeclaredMethod("UnitySendMessage", String.class, String.class, String.class).invoke(UnityForwarder.PLAYER, s, s2, s3);
        }
        catch (NoSuchMethodException ex) {
            Log.e(UnityForwarder.TAG, "Failed sending message to Unity", (Throwable)ex);
        }
        catch (InvocationTargetException ex2) {
            Log.e(UnityForwarder.TAG, "Failed sending message to Unity", (Throwable)ex2);
        }
        catch (IllegalAccessException ex3) {
            Log.e(UnityForwarder.TAG, "Failed sending message to Unity", (Throwable)ex3);
        }
    }
    
    void forward(final String s, final String s2, final String s3) {
        if (this.loaded) {
            sendMessage(s, s2, s3);
            return;
        }
        Log.d(UnityForwarder.TAG, "Deferring message due to not loaded");
        this.deferred.add(new Message(s, s2, s3));
    }
    
    void markLoaded() {
        Log.d(UnityForwarder.TAG, "Marked as loaded");
        this.loaded = true;
        if (!this.deferred.isEmpty()) {
            final Message message = this.deferred.remove();
            sendMessage(message.gameObject, message.methodName, message.message);
        }
    }
    
    private static final class Message
    {
        final String gameObject;
        final String message;
        final String methodName;
        
        Message(final String gameObject, final String methodName, final String message) {
            this.gameObject = gameObject;
            this.methodName = methodName;
            this.message = message;
        }
    }
}
