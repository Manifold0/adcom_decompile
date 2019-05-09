// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.messaging;

import android.os.Bundle;
import android.util.Log;
import com.google.firebase.FirebaseApp;
import com.google.firebase.analytics.connector.AnalyticsConnector;
import android.content.Intent;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class MessagingAnalytics
{
    @KeepForSdk
    public static void logNotificationDismiss(final Intent intent) {
        zza("_nd", intent);
    }
    
    @KeepForSdk
    public static void logNotificationForeground(final Intent intent) {
        zza("_nf", intent);
    }
    
    @KeepForSdk
    public static void logNotificationOpen(final Intent intent) {
        if (intent != null) {
            if ("1".equals(intent.getStringExtra("google.c.a.tc"))) {
                final AnalyticsConnector analyticsConnector = (AnalyticsConnector)FirebaseApp.getInstance().get((Class)AnalyticsConnector.class);
                if (Log.isLoggable("FirebaseMessaging", 3)) {
                    Log.d("FirebaseMessaging", "Received event with track-conversion=true. Setting user property and reengagement event");
                }
                if (analyticsConnector != null) {
                    final String stringExtra = intent.getStringExtra("google.c.a.c_id");
                    analyticsConnector.setUserProperty("fcm", "_ln", (Object)stringExtra);
                    final Bundle bundle = new Bundle();
                    bundle.putString("source", "Firebase");
                    bundle.putString("medium", "notification");
                    bundle.putString("campaign", stringExtra);
                    analyticsConnector.logEvent("fcm", "_cmp", bundle);
                }
                else {
                    Log.w("FirebaseMessaging", "Unable to set user property for conversion tracking:  analytics library is missing");
                }
            }
            else if (Log.isLoggable("FirebaseMessaging", 3)) {
                Log.d("FirebaseMessaging", "Received event with track-conversion=false. Do not set user property");
            }
        }
        zza("_no", intent);
    }
    
    @KeepForSdk
    public static void logNotificationReceived(final Intent intent) {
        zza("_nr", intent);
    }
    
    @KeepForSdk
    public static boolean shouldUploadMetrics(final Intent intent) {
        return intent != null && !"com.google.firebase.messaging.RECEIVE_DIRECT_BOOT".equals(intent.getAction()) && "1".equals(intent.getStringExtra("google.c.a.e"));
    }
    
    private static void zza(final String p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   android/os/Bundle.<init>:()V
        //     7: astore_3       
        //     8: aload_1        
        //     9: ldc             "google.c.a.c_id"
        //    11: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    14: astore_2       
        //    15: aload_2        
        //    16: ifnull          26
        //    19: aload_3        
        //    20: ldc             "_nmid"
        //    22: aload_2        
        //    23: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    26: aload_1        
        //    27: ldc             "google.c.a.c_l"
        //    29: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    32: astore_2       
        //    33: aload_2        
        //    34: ifnull          44
        //    37: aload_3        
        //    38: ldc             "_nmn"
        //    40: aload_2        
        //    41: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    44: aload_1        
        //    45: ldc             "google.c.a.m_l"
        //    47: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    50: astore_2       
        //    51: aload_2        
        //    52: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    55: ifne            65
        //    58: aload_3        
        //    59: ldc             "label"
        //    61: aload_2        
        //    62: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    65: aload_1        
        //    66: ldc             "google.c.a.m_c"
        //    68: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    71: astore_2       
        //    72: aload_2        
        //    73: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    76: ifne            86
        //    79: aload_3        
        //    80: ldc             "message_channel"
        //    82: aload_2        
        //    83: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //    86: aload_1        
        //    87: ldc             "from"
        //    89: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //    92: astore_2       
        //    93: aload_2        
        //    94: ifnull          258
        //    97: aload_2        
        //    98: ldc             "/topics/"
        //   100: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   103: ifeq            258
        //   106: aload_2        
        //   107: ifnull          117
        //   110: aload_3        
        //   111: ldc             "_nt"
        //   113: aload_2        
        //   114: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   117: aload_1        
        //   118: ldc             "google.c.a.ts"
        //   120: invokevirtual   android/content/Intent.hasExtra:(Ljava/lang/String;)Z
        //   123: ifeq            141
        //   126: aload_3        
        //   127: ldc             "_nmt"
        //   129: aload_1        
        //   130: ldc             "google.c.a.ts"
        //   132: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   135: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   138: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   141: aload_1        
        //   142: ldc             "google.c.a.udt"
        //   144: invokevirtual   android/content/Intent.hasExtra:(Ljava/lang/String;)Z
        //   147: ifeq            165
        //   150: aload_3        
        //   151: ldc             "_ndt"
        //   153: aload_1        
        //   154: ldc             "google.c.a.udt"
        //   156: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   159: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
        //   162: invokevirtual   android/os/Bundle.putInt:(Ljava/lang/String;I)V
        //   165: ldc             "FirebaseMessaging"
        //   167: iconst_3       
        //   168: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   171: ifeq            231
        //   174: aload_3        
        //   175: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   178: astore_1       
        //   179: ldc             "FirebaseMessaging"
        //   181: new             Ljava/lang/StringBuilder;
        //   184: dup            
        //   185: aload_0        
        //   186: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   189: invokevirtual   java/lang/String.length:()I
        //   192: bipush          22
        //   194: iadd           
        //   195: aload_1        
        //   196: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   199: invokevirtual   java/lang/String.length:()I
        //   202: iadd           
        //   203: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   206: ldc             "Sending event="
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: aload_0        
        //   212: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   215: ldc             " params="
        //   217: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   220: aload_1        
        //   221: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   224: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   227: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   230: pop            
        //   231: invokestatic    com/google/firebase/FirebaseApp.getInstance:()Lcom/google/firebase/FirebaseApp;
        //   234: ldc             Lcom/google/firebase/analytics/connector/AnalyticsConnector;.class
        //   236: invokevirtual   com/google/firebase/FirebaseApp.get:(Ljava/lang/Class;)Ljava/lang/Object;
        //   239: checkcast       Lcom/google/firebase/analytics/connector/AnalyticsConnector;
        //   242: astore_1       
        //   243: aload_1        
        //   244: ifnull          289
        //   247: aload_1        
        //   248: ldc             "fcm"
        //   250: aload_0        
        //   251: aload_3        
        //   252: invokeinterface com/google/firebase/analytics/connector/AnalyticsConnector.logEvent:(Ljava/lang/String;Ljava/lang/String;Landroid/os/Bundle;)V
        //   257: return         
        //   258: aconst_null    
        //   259: astore_2       
        //   260: goto            106
        //   263: astore_2       
        //   264: ldc             "FirebaseMessaging"
        //   266: ldc             "Error while parsing timestamp in GCM event"
        //   268: aload_2        
        //   269: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   272: pop            
        //   273: goto            141
        //   276: astore_1       
        //   277: ldc             "FirebaseMessaging"
        //   279: ldc             "Error while parsing use_device_time in GCM event"
        //   281: aload_1        
        //   282: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   285: pop            
        //   286: goto            165
        //   289: ldc             "FirebaseMessaging"
        //   291: ldc             "Unable to log event: analytics library is missing"
        //   293: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   296: pop            
        //   297: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  126    141    263    276    Ljava/lang/NumberFormatException;
        //  150    165    276    289    Ljava/lang/NumberFormatException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0165:
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
