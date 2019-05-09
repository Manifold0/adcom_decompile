// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import android.util.Log;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.content.Context;
import java.util.ArrayDeque;
import android.support.annotation.VisibleForTesting;
import android.content.Intent;
import java.util.Queue;
import javax.annotation.concurrent.GuardedBy;
import android.support.v4.util.SimpleArrayMap;

public final class zzav
{
    private static zzav zzcx;
    @GuardedBy("serviceClassNames")
    private final SimpleArrayMap<String, String> zzcy;
    private Boolean zzcz;
    @VisibleForTesting
    final Queue<Intent> zzda;
    @VisibleForTesting
    private final Queue<Intent> zzdb;
    
    private zzav() {
        this.zzcy = (SimpleArrayMap<String, String>)new SimpleArrayMap();
        this.zzcz = null;
        this.zzda = new ArrayDeque<Intent>();
        this.zzdb = new ArrayDeque<Intent>();
    }
    
    public static PendingIntent zza(final Context context, final int n, final Intent intent, final int n2) {
        return PendingIntent.getBroadcast(context, n, zza(context, "com.google.firebase.MESSAGING_EVENT", intent), 1073741824);
    }
    
    private static Intent zza(final Context context, final String action, final Intent intent) {
        final Intent intent2 = new Intent(context, (Class)FirebaseInstanceIdReceiver.class);
        intent2.setAction(action);
        intent2.putExtra("wrapped_intent", (Parcelable)intent);
        return intent2;
    }
    
    public static zzav zzai() {
        synchronized (zzav.class) {
            if (zzav.zzcx == null) {
                zzav.zzcx = new zzav();
            }
            return zzav.zzcx;
        }
    }
    
    public static void zzb(final Context context, final Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.INSTANCE_ID_EVENT", intent));
    }
    
    public static void zzc(final Context context, final Intent intent) {
        context.sendBroadcast(zza(context, "com.google.firebase.MESSAGING_EVENT", intent));
    }
    
    private final int zzd(final Context p0, final Intent p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        com/google/firebase/iid/zzav.zzcy:Landroid/support/v4/util/SimpleArrayMap;
        //     4: astore          4
        //     6: aload           4
        //     8: monitorenter   
        //     9: aload_0        
        //    10: getfield        com/google/firebase/iid/zzav.zzcy:Landroid/support/v4/util/SimpleArrayMap;
        //    13: aload_2        
        //    14: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //    17: invokevirtual   android/support/v4/util/SimpleArrayMap.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    20: checkcast       Ljava/lang/String;
        //    23: astore          5
        //    25: aload           4
        //    27: monitorexit    
        //    28: aload           5
        //    30: astore          4
        //    32: aload           5
        //    34: ifnonnull       316
        //    37: aload_1        
        //    38: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    41: aload_2        
        //    42: iconst_0       
        //    43: invokevirtual   android/content/pm/PackageManager.resolveService:(Landroid/content/Intent;I)Landroid/content/pm/ResolveInfo;
        //    46: astore          4
        //    48: aload           4
        //    50: ifnull          61
        //    53: aload           4
        //    55: getfield        android/content/pm/ResolveInfo.serviceInfo:Landroid/content/pm/ServiceInfo;
        //    58: ifnonnull       133
        //    61: ldc             "FirebaseInstanceId"
        //    63: ldc             "Failed to resolve target intent service, skipping classname enforcement"
        //    65: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //    68: pop            
        //    69: aload_0        
        //    70: getfield        com/google/firebase/iid/zzav.zzcz:Ljava/lang/Boolean;
        //    73: ifnonnull       95
        //    76: aload_1        
        //    77: ldc             "android.permission.WAKE_LOCK"
        //    79: invokevirtual   android/content/Context.checkCallingOrSelfPermission:(Ljava/lang/String;)I
        //    82: ifne            405
        //    85: iconst_1       
        //    86: istore_3       
        //    87: aload_0        
        //    88: iload_3        
        //    89: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    92: putfield        com/google/firebase/iid/zzav.zzcz:Ljava/lang/Boolean;
        //    95: aload_0        
        //    96: getfield        com/google/firebase/iid/zzav.zzcz:Ljava/lang/Boolean;
        //    99: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //   102: ifeq            410
        //   105: aload_1        
        //   106: aload_2        
        //   107: invokestatic    android/support/v4/content/WakefulBroadcastReceiver.startWakefulService:(Landroid/content/Context;Landroid/content/Intent;)Landroid/content/ComponentName;
        //   110: astore_1       
        //   111: aload_1        
        //   112: ifnonnull       441
        //   115: ldc             "FirebaseInstanceId"
        //   117: ldc             "Error while delivering the message: ServiceIntent not found."
        //   119: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   122: pop            
        //   123: sipush          404
        //   126: ireturn        
        //   127: astore_1       
        //   128: aload           4
        //   130: monitorexit    
        //   131: aload_1        
        //   132: athrow         
        //   133: aload           4
        //   135: getfield        android/content/pm/ResolveInfo.serviceInfo:Landroid/content/pm/ServiceInfo;
        //   138: astore          5
        //   140: aload_1        
        //   141: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   144: aload           5
        //   146: getfield        android/content/pm/ServiceInfo.packageName:Ljava/lang/String;
        //   149: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   152: ifeq            163
        //   155: aload           5
        //   157: getfield        android/content/pm/ServiceInfo.name:Ljava/lang/String;
        //   160: ifnonnull       236
        //   163: aload           5
        //   165: getfield        android/content/pm/ServiceInfo.packageName:Ljava/lang/String;
        //   168: astore          4
        //   170: aload           5
        //   172: getfield        android/content/pm/ServiceInfo.name:Ljava/lang/String;
        //   175: astore          5
        //   177: ldc             "FirebaseInstanceId"
        //   179: new             Ljava/lang/StringBuilder;
        //   182: dup            
        //   183: aload           4
        //   185: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   188: invokevirtual   java/lang/String.length:()I
        //   191: bipush          94
        //   193: iadd           
        //   194: aload           5
        //   196: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   199: invokevirtual   java/lang/String.length:()I
        //   202: iadd           
        //   203: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   206: ldc             "Error resolving target intent service, skipping classname enforcement. Resolved service was: "
        //   208: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   211: aload           4
        //   213: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   216: ldc             "/"
        //   218: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   221: aload           5
        //   223: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   226: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   229: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   232: pop            
        //   233: goto            69
        //   236: aload           5
        //   238: getfield        android/content/pm/ServiceInfo.name:Ljava/lang/String;
        //   241: astore          5
        //   243: aload           5
        //   245: astore          4
        //   247: aload           5
        //   249: ldc             "."
        //   251: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //   254: ifeq            290
        //   257: aload_1        
        //   258: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   261: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   264: astore          4
        //   266: aload           5
        //   268: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   271: astore          5
        //   273: aload           5
        //   275: invokevirtual   java/lang/String.length:()I
        //   278: ifeq            371
        //   281: aload           4
        //   283: aload           5
        //   285: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   288: astore          4
        //   290: aload_0        
        //   291: getfield        com/google/firebase/iid/zzav.zzcy:Landroid/support/v4/util/SimpleArrayMap;
        //   294: astore          5
        //   296: aload           5
        //   298: monitorenter   
        //   299: aload_0        
        //   300: getfield        com/google/firebase/iid/zzav.zzcy:Landroid/support/v4/util/SimpleArrayMap;
        //   303: aload_2        
        //   304: invokevirtual   android/content/Intent.getAction:()Ljava/lang/String;
        //   307: aload           4
        //   309: invokevirtual   android/support/v4/util/SimpleArrayMap.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //   312: pop            
        //   313: aload           5
        //   315: monitorexit    
        //   316: ldc             "FirebaseInstanceId"
        //   318: iconst_3       
        //   319: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   322: ifeq            357
        //   325: aload           4
        //   327: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   330: astore          5
        //   332: aload           5
        //   334: invokevirtual   java/lang/String.length:()I
        //   337: ifeq            391
        //   340: ldc             "Restricting intent to a specific service: "
        //   342: aload           5
        //   344: invokevirtual   java/lang/String.concat:(Ljava/lang/String;)Ljava/lang/String;
        //   347: astore          5
        //   349: ldc             "FirebaseInstanceId"
        //   351: aload           5
        //   353: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   356: pop            
        //   357: aload_2        
        //   358: aload_1        
        //   359: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   362: aload           4
        //   364: invokevirtual   android/content/Intent.setClassName:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   367: pop            
        //   368: goto            69
        //   371: new             Ljava/lang/String;
        //   374: dup            
        //   375: aload           4
        //   377: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   380: astore          4
        //   382: goto            290
        //   385: astore_1       
        //   386: aload           5
        //   388: monitorexit    
        //   389: aload_1        
        //   390: athrow         
        //   391: new             Ljava/lang/String;
        //   394: dup            
        //   395: ldc             "Restricting intent to a specific service: "
        //   397: invokespecial   java/lang/String.<init>:(Ljava/lang/String;)V
        //   400: astore          5
        //   402: goto            349
        //   405: iconst_0       
        //   406: istore_3       
        //   407: goto            87
        //   410: aload_1        
        //   411: aload_2        
        //   412: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //   415: astore_1       
        //   416: ldc             "FirebaseInstanceId"
        //   418: ldc             "Missing wake lock permission, service start may be delayed"
        //   420: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   423: pop            
        //   424: goto            111
        //   427: astore_1       
        //   428: ldc             "FirebaseInstanceId"
        //   430: ldc             "Error while delivering the message to the serviceIntent"
        //   432: aload_1        
        //   433: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //   436: pop            
        //   437: sipush          401
        //   440: ireturn        
        //   441: iconst_m1      
        //   442: ireturn        
        //   443: astore_1       
        //   444: aload_1        
        //   445: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   448: astore_1       
        //   449: ldc             "FirebaseInstanceId"
        //   451: new             Ljava/lang/StringBuilder;
        //   454: dup            
        //   455: aload_1        
        //   456: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   459: invokevirtual   java/lang/String.length:()I
        //   462: bipush          45
        //   464: iadd           
        //   465: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   468: ldc             "Failed to start service while in background: "
        //   470: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   473: aload_1        
        //   474: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   477: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   480: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;)I
        //   483: pop            
        //   484: sipush          402
        //   487: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  9      28     127    133    Any
        //  69     85     427    441    Ljava/lang/SecurityException;
        //  69     85     443    488    Ljava/lang/IllegalStateException;
        //  87     95     427    441    Ljava/lang/SecurityException;
        //  87     95     443    488    Ljava/lang/IllegalStateException;
        //  95     111    427    441    Ljava/lang/SecurityException;
        //  95     111    443    488    Ljava/lang/IllegalStateException;
        //  115    123    427    441    Ljava/lang/SecurityException;
        //  115    123    443    488    Ljava/lang/IllegalStateException;
        //  128    131    127    133    Any
        //  299    316    385    391    Any
        //  386    389    385    391    Any
        //  410    424    427    441    Ljava/lang/SecurityException;
        //  410    424    443    488    Ljava/lang/IllegalStateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0069:
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
    
    public final Intent zzaj() {
        return this.zzdb.poll();
    }
    
    public final int zzb(final Context context, final String s, final Intent intent) {
        if (Log.isLoggable("FirebaseInstanceId", 3)) {
            final String value = String.valueOf(s);
            String concat;
            if (value.length() != 0) {
                concat = "Starting service: ".concat(value);
            }
            else {
                concat = new String("Starting service: ");
            }
            Log.d("FirebaseInstanceId", concat);
        }
        switch (s) {
            default: {
                final String value2 = String.valueOf(s);
                String concat2;
                if (value2.length() != 0) {
                    concat2 = "Unknown service action: ".concat(value2);
                }
                else {
                    concat2 = new String("Unknown service action: ");
                }
                Log.w("FirebaseInstanceId", concat2);
                return 500;
            }
            case "com.google.firebase.INSTANCE_ID_EVENT": {
                this.zzda.offer(intent);
                break;
            }
            case "com.google.firebase.MESSAGING_EVENT": {
                this.zzdb.offer(intent);
                break;
            }
        }
        final Intent intent2 = new Intent(s);
        intent2.setPackage(context.getPackageName());
        return this.zzd(context, intent2);
    }
}
