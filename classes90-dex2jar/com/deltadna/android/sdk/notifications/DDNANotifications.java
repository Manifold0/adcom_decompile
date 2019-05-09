// 
// Decompiled by Procyon v0.5.34
// 

package com.deltadna.android.sdk.notifications;

import android.util.Log;
import android.content.Context;
import com.deltadna.android.sdk.DDNA;
import android.os.Bundle;
import java.util.concurrent.Executors;
import android.support.annotation.Nullable;
import java.util.concurrent.Executor;

public final class DDNANotifications
{
    private static final Executor EXECUTOR;
    private static final String NAME = "deltadna-sdk-notifications";
    private static final String TAG;
    @Nullable
    static Class<? extends EventReceiver> receiver;
    
    static {
        TAG = "deltaDNA " + DDNANotifications.class.getSimpleName();
        EXECUTOR = Executors.newSingleThreadExecutor();
        DDNANotifications.receiver = null;
    }
    
    private DDNANotifications() {
    }
    
    public static void markUnityLoaded() {
        UnityForwarder.getInstance().markLoaded();
    }
    
    public static void recordNotificationOpened(Bundle bundle, final boolean b) {
        if (UnityForwarder.isPresent()) {
            bundle = new Bundle(bundle);
            bundle.putBoolean("_ddLaunch", b);
            UnityForwarder.getInstance().forward("DeltaDNA.AndroidNotifications", "DidReceivePushNotification", Utils.convert(bundle));
            return;
        }
        DDNA.instance().recordNotificationOpened(b, bundle);
    }
    
    public static void register(final Context context) {
        register(context, false);
    }
    
    public static void register(final Context p0, final boolean p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ldc             "Registering for push notifications"
        //     5: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //     8: pop            
        //     9: aload_0        
        //    10: invokestatic    com/deltadna/android/sdk/notifications/MetaData.get:(Landroid/content/Context;)Landroid/os/Bundle;
        //    13: astore_3       
        //    14: aload_0        
        //    15: aload_3        
        //    16: ldc             "ddna_application_id"
        //    18: invokevirtual   android/os/Bundle.getInt:(Ljava/lang/String;)I
        //    21: invokevirtual   android/content/Context.getString:(I)Ljava/lang/String;
        //    24: astore          4
        //    26: aload_0        
        //    27: aload_3        
        //    28: ldc             "ddna_sender_id"
        //    30: invokevirtual   android/os/Bundle.getInt:(Ljava/lang/String;)I
        //    33: invokevirtual   android/content/Context.getString:(I)Ljava/lang/String;
        //    36: astore          5
        //    38: ldc             Lcom/deltadna/android/sdk/notifications/DDNANotifications;.class
        //    40: monitorenter   
        //    41: iload_1        
        //    42: ifeq            130
        //    45: ldc             "deltadna-sdk-notifications"
        //    47: astore_3       
        //    48: iconst_0       
        //    49: istore_2       
        //    50: aload_0        
        //    51: invokestatic    com/google/firebase/FirebaseApp.getApps:(Landroid/content/Context;)Ljava/util/List;
        //    54: invokeinterface java/util/List.iterator:()Ljava/util/Iterator;
        //    59: astore          6
        //    61: aload           6
        //    63: invokeinterface java/util/Iterator.hasNext:()Z
        //    68: ifeq            136
        //    71: aload           6
        //    73: invokeinterface java/util/Iterator.next:()Ljava/lang/Object;
        //    78: checkcast       Lcom/google/firebase/FirebaseApp;
        //    81: invokevirtual   com/google/firebase/FirebaseApp.getName:()Ljava/lang/String;
        //    84: aload_3        
        //    85: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    88: istore_1       
        //    89: iload_1        
        //    90: ifeq            61
        //    93: iconst_1       
        //    94: istore_2       
        //    95: goto            61
        //    98: astore_0       
        //    99: new             Ljava/lang/IllegalStateException;
        //   102: dup            
        //   103: getstatic       java/util/Locale.US:Ljava/util/Locale;
        //   106: ldc             "Failed to find configuration meta-data, have %s and %s been defined in the manifest?"
        //   108: iconst_2       
        //   109: anewarray       Ljava/lang/Object;
        //   112: dup            
        //   113: iconst_0       
        //   114: ldc             "ddna_application_id"
        //   116: aastore        
        //   117: dup            
        //   118: iconst_1       
        //   119: ldc             "ddna_sender_id"
        //   121: aastore        
        //   122: invokestatic    java/lang/String.format:(Ljava/util/Locale;Ljava/lang/String;[Ljava/lang/Object;)Ljava/lang/String;
        //   125: aload_0        
        //   126: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
        //   129: athrow         
        //   130: ldc             "[DEFAULT]"
        //   132: astore_3       
        //   133: goto            48
        //   136: iload_2        
        //   137: ifne            170
        //   140: aload_0        
        //   141: new             Lcom/google/firebase/FirebaseOptions$Builder;
        //   144: dup            
        //   145: invokespecial   com/google/firebase/FirebaseOptions$Builder.<init>:()V
        //   148: aload           4
        //   150: invokevirtual   com/google/firebase/FirebaseOptions$Builder.setApplicationId:(Ljava/lang/String;)Lcom/google/firebase/FirebaseOptions$Builder;
        //   153: aload           5
        //   155: invokevirtual   com/google/firebase/FirebaseOptions$Builder.setGcmSenderId:(Ljava/lang/String;)Lcom/google/firebase/FirebaseOptions$Builder;
        //   158: invokevirtual   com/google/firebase/FirebaseOptions$Builder.build:()Lcom/google/firebase/FirebaseOptions;
        //   161: aload_3        
        //   162: invokestatic    com/google/firebase/FirebaseApp.initializeApp:(Landroid/content/Context;Lcom/google/firebase/FirebaseOptions;Ljava/lang/String;)Lcom/google/firebase/FirebaseApp;
        //   165: pop            
        //   166: ldc             Lcom/deltadna/android/sdk/notifications/DDNANotifications;.class
        //   168: monitorexit    
        //   169: return         
        //   170: getstatic       com/deltadna/android/sdk/notifications/DDNANotifications.EXECUTOR:Ljava/util/concurrent/Executor;
        //   173: new             Lcom/deltadna/android/sdk/notifications/DDNANotifications$1;
        //   176: dup            
        //   177: aload_0        
        //   178: invokespecial   com/deltadna/android/sdk/notifications/DDNANotifications$1.<init>:(Landroid/content/Context;)V
        //   181: invokeinterface java/util/concurrent/Executor.execute:(Ljava/lang/Runnable;)V
        //   186: goto            166
        //   189: astore_0       
        //   190: ldc             Lcom/deltadna/android/sdk/notifications/DDNANotifications;.class
        //   192: monitorexit    
        //   193: aload_0        
        //   194: athrow         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                             
        //  -----  -----  -----  -----  -------------------------------------------------
        //  9      38     98     130    Landroid/content/res/Resources$NotFoundException;
        //  50     61     189    195    Any
        //  61     89     189    195    Any
        //  140    166    189    195    Any
        //  166    169    189    195    Any
        //  170    186    189    195    Any
        //  190    193    189    195    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0061:
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
    
    public static void setReceiver(@Nullable final Class<? extends EventReceiver> receiver) {
        synchronized (DDNANotifications.class) {
            DDNANotifications.receiver = receiver;
        }
    }
    
    public static void unregister() {
        if (UnityForwarder.isPresent()) {
            throw new UnsupportedOperationException("Unity SDK should unregister from its own code");
        }
        Log.d(DDNANotifications.TAG, "Unregistering from push notifications");
        DDNA.instance().clearRegistrationId();
    }
}
