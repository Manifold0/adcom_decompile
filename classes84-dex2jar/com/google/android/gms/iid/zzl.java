// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.iid;

import java.util.Random;
import android.os.SystemClock;
import android.os.Parcelable;
import com.google.android.gms.common.util.zzq;
import android.os.Process;
import android.os.RemoteException;
import android.os.Message;
import android.os.ConditionVariable;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import java.io.IOException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.content.pm.PackageManager;
import java.security.KeyPair;
import java.util.HashMap;
import java.util.Map;
import android.os.Messenger;
import android.app.PendingIntent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class zzl
{
    private static String zzict;
    private static boolean zzicu;
    private static int zzicv;
    private static int zzicw;
    private static int zzicx;
    private static BroadcastReceiver zzicy;
    private Context zzaif;
    private PendingIntent zzhzr;
    private Messenger zzhzv;
    private Map<String, Object> zzicz;
    private Messenger zzida;
    private MessengerCompat zzidb;
    private long zzidc;
    private long zzidd;
    private int zzide;
    private int zzidf;
    private long zzidg;
    
    static {
        zzl.zzict = null;
        zzl.zzicu = false;
        zzl.zzicv = 0;
        zzl.zzicw = 0;
        zzl.zzicx = 0;
        zzl.zzicy = null;
    }
    
    public zzl(final Context zzaif) {
        this.zzicz = new HashMap<String, Object>();
        this.zzaif = zzaif;
    }
    
    private static String zza(final KeyPair p0, final String... p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     2: aload_1        
        //     3: invokestatic    android/text/TextUtils.join:(Ljava/lang/CharSequence;[Ljava/lang/Object;)Ljava/lang/String;
        //     6: ldc             "UTF-8"
        //     8: invokevirtual   java/lang/String.getBytes:(Ljava/lang/String;)[B
        //    11: astore_1       
        //    12: aload_0        
        //    13: invokevirtual   java/security/KeyPair.getPrivate:()Ljava/security/PrivateKey;
        //    16: astore_2       
        //    17: aload_2        
        //    18: instanceof      Ljava/security/interfaces/RSAPrivateKey;
        //    21: ifeq            64
        //    24: ldc             "SHA256withRSA"
        //    26: astore_0       
        //    27: aload_0        
        //    28: invokestatic    java/security/Signature.getInstance:(Ljava/lang/String;)Ljava/security/Signature;
        //    31: astore_0       
        //    32: aload_0        
        //    33: aload_2        
        //    34: invokevirtual   java/security/Signature.initSign:(Ljava/security/PrivateKey;)V
        //    37: aload_0        
        //    38: aload_1        
        //    39: invokevirtual   java/security/Signature.update:([B)V
        //    42: aload_0        
        //    43: invokevirtual   java/security/Signature.sign:()[B
        //    46: invokestatic    com/google/android/gms/iid/InstanceID.zzn:([B)Ljava/lang/String;
        //    49: astore_0       
        //    50: aload_0        
        //    51: areturn        
        //    52: astore_0       
        //    53: ldc             "InstanceID/Rpc"
        //    55: ldc             "Unable to encode string"
        //    57: aload_0        
        //    58: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    61: pop            
        //    62: aconst_null    
        //    63: areturn        
        //    64: ldc             "SHA256withECDSA"
        //    66: astore_0       
        //    67: goto            27
        //    70: astore_0       
        //    71: ldc             "InstanceID/Rpc"
        //    73: ldc             "Unable to sign registration request"
        //    75: aload_0        
        //    76: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    79: pop            
        //    80: aconst_null    
        //    81: areturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                    
        //  -----  -----  -----  -----  ----------------------------------------
        //  0      12     52     64     Ljava/io/UnsupportedEncodingException;
        //  12     24     70     82     Ljava/security/GeneralSecurityException;
        //  27     50     70     82     Ljava/security/GeneralSecurityException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0027:
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
    
    private static boolean zza(final PackageManager packageManager) {
        final Iterator<ResolveInfo> iterator = packageManager.queryBroadcastReceivers(new Intent("com.google.iid.TOKEN_REQUEST"), 0).iterator();
        while (iterator.hasNext()) {
            if (zza(packageManager, iterator.next().activityInfo.packageName, "com.google.iid.TOKEN_REQUEST")) {
                return zzl.zzicu = true;
            }
        }
        return false;
    }
    
    private static boolean zza(final PackageManager packageManager, final String s, final String s2) {
        if (packageManager.checkPermission("com.google.android.c2dm.permission.SEND", s) == 0) {
            return zzb(packageManager, s);
        }
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(s).length() + 56 + String.valueOf(s2).length()).append("Possible malicious package ").append(s).append(" declares ").append(s2).append(" without permission").toString());
        return false;
    }
    
    private final void zzad(final Object o) {
        synchronized (this.getClass()) {
            for (final String s : this.zzicz.keySet()) {
                final Object value = this.zzicz.get(s);
                this.zzicz.put(s, o);
                zzd(value, o);
            }
        }
    }
    // monitorexit(clazz)
    
    private final void zzauv() {
        if (this.zzhzv != null) {
            return;
        }
        zzdm(this.zzaif);
        this.zzhzv = new Messenger((Handler)new zzm(this, Looper.getMainLooper()));
    }
    
    private static String zzauw() {
        synchronized (zzl.class) {
            final int zzicx = zzl.zzicx;
            zzl.zzicx = zzicx + 1;
            return Integer.toString(zzicx);
        }
    }
    
    private final Intent zzb(final Bundle p0, final KeyPair p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: invokespecial   android/os/ConditionVariable.<init>:()V
        //     7: astore          11
        //     9: invokestatic    com/google/android/gms/iid/zzl.zzauw:()Ljava/lang/String;
        //    12: astore          10
        //    14: aload_0        
        //    15: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //    18: astore          9
        //    20: aload           9
        //    22: monitorenter   
        //    23: aload_0        
        //    24: getfield        com/google/android/gms/iid/zzl.zzicz:Ljava/util/Map;
        //    27: aload           10
        //    29: aload           11
        //    31: invokeinterface java/util/Map.put:(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        //    36: pop            
        //    37: aload           9
        //    39: monitorexit    
        //    40: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //    43: lstore          4
        //    45: aload_0        
        //    46: getfield        com/google/android/gms/iid/zzl.zzidg:J
        //    49: lconst_0       
        //    50: lcmp           
        //    51: ifeq            134
        //    54: lload           4
        //    56: aload_0        
        //    57: getfield        com/google/android/gms/iid/zzl.zzidg:J
        //    60: lcmp           
        //    61: ifgt            134
        //    64: aload_0        
        //    65: getfield        com/google/android/gms/iid/zzl.zzidg:J
        //    68: lstore          6
        //    70: aload_0        
        //    71: getfield        com/google/android/gms/iid/zzl.zzidf:I
        //    74: istore_3       
        //    75: ldc             "InstanceID/Rpc"
        //    77: new             Ljava/lang/StringBuilder;
        //    80: dup            
        //    81: bipush          78
        //    83: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //    86: ldc_w           "Backoff mode, next request attempt: "
        //    89: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    92: lload           6
        //    94: lload           4
        //    96: lsub           
        //    97: invokevirtual   java/lang/StringBuilder.append:(J)Ljava/lang/StringBuilder;
        //   100: ldc_w           " interval: "
        //   103: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   106: iload_3        
        //   107: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   110: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   113: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   116: pop            
        //   117: new             Ljava/io/IOException;
        //   120: dup            
        //   121: ldc_w           "RETRY_LATER"
        //   124: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   127: athrow         
        //   128: astore_1       
        //   129: aload           9
        //   131: monitorexit    
        //   132: aload_1        
        //   133: athrow         
        //   134: aload_0        
        //   135: invokespecial   com/google/android/gms/iid/zzl.zzauv:()V
        //   138: getstatic       com/google/android/gms/iid/zzl.zzict:Ljava/lang/String;
        //   141: ifnonnull       155
        //   144: new             Ljava/io/IOException;
        //   147: dup            
        //   148: ldc_w           "MISSING_INSTANCEID_SERVICE"
        //   151: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   154: athrow         
        //   155: aload_0        
        //   156: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   159: putfield        com/google/android/gms/iid/zzl.zzidc:J
        //   162: getstatic       com/google/android/gms/iid/zzl.zzicu:Z
        //   165: ifeq            608
        //   168: ldc             "com.google.iid.TOKEN_REQUEST"
        //   170: astore          9
        //   172: new             Landroid/content/Intent;
        //   175: dup            
        //   176: aload           9
        //   178: invokespecial   android/content/Intent.<init>:(Ljava/lang/String;)V
        //   181: astore          9
        //   183: aload           9
        //   185: getstatic       com/google/android/gms/iid/zzl.zzict:Ljava/lang/String;
        //   188: invokevirtual   android/content/Intent.setPackage:(Ljava/lang/String;)Landroid/content/Intent;
        //   191: pop            
        //   192: aload_1        
        //   193: ldc_w           "gmsv"
        //   196: aload_0        
        //   197: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   200: invokestatic    com/google/android/gms/iid/zzl.zzdn:(Landroid/content/Context;)I
        //   203: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   206: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   209: aload_1        
        //   210: ldc_w           "osv"
        //   213: getstatic       android/os/Build$VERSION.SDK_INT:I
        //   216: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   219: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   222: aload_1        
        //   223: ldc_w           "app_ver"
        //   226: aload_0        
        //   227: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   230: invokestatic    com/google/android/gms/iid/InstanceID.zzdj:(Landroid/content/Context;)I
        //   233: invokestatic    java/lang/Integer.toString:(I)Ljava/lang/String;
        //   236: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   239: aload_1        
        //   240: ldc_w           "app_ver_name"
        //   243: aload_0        
        //   244: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   247: invokestatic    com/google/android/gms/iid/InstanceID.zzdk:(Landroid/content/Context;)Ljava/lang/String;
        //   250: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   253: aload_1        
        //   254: ldc_w           "cliv"
        //   257: ldc_w           "iid-11720000"
        //   260: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   263: aload_1        
        //   264: ldc_w           "appid"
        //   267: aload_2        
        //   268: invokestatic    com/google/android/gms/iid/InstanceID.zza:(Ljava/security/KeyPair;)Ljava/lang/String;
        //   271: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   274: aload_2        
        //   275: invokevirtual   java/security/KeyPair.getPublic:()Ljava/security/PublicKey;
        //   278: invokeinterface java/security/PublicKey.getEncoded:()[B
        //   283: invokestatic    com/google/android/gms/iid/InstanceID.zzn:([B)Ljava/lang/String;
        //   286: astore          12
        //   288: aload_1        
        //   289: ldc_w           "pub2"
        //   292: aload           12
        //   294: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   297: aload_1        
        //   298: ldc_w           "sig"
        //   301: aload_2        
        //   302: iconst_2       
        //   303: anewarray       Ljava/lang/String;
        //   306: dup            
        //   307: iconst_0       
        //   308: aload_0        
        //   309: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   312: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   315: aastore        
        //   316: dup            
        //   317: iconst_1       
        //   318: aload           12
        //   320: aastore        
        //   321: invokestatic    com/google/android/gms/iid/zzl.zza:(Ljava/security/KeyPair;[Ljava/lang/String;)Ljava/lang/String;
        //   324: invokevirtual   android/os/Bundle.putString:(Ljava/lang/String;Ljava/lang/String;)V
        //   327: aload           9
        //   329: aload_1        
        //   330: invokevirtual   android/content/Intent.putExtras:(Landroid/os/Bundle;)Landroid/content/Intent;
        //   333: pop            
        //   334: aload_0        
        //   335: aload           9
        //   337: invokespecial   com/google/android/gms/iid/zzl.zzi:(Landroid/content/Intent;)V
        //   340: aload_0        
        //   341: invokestatic    android/os/SystemClock.elapsedRealtime:()J
        //   344: putfield        com/google/android/gms/iid/zzl.zzidc:J
        //   347: aload           9
        //   349: ldc_w           "kid"
        //   352: new             Ljava/lang/StringBuilder;
        //   355: dup            
        //   356: aload           10
        //   358: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   361: invokevirtual   java/lang/String.length:()I
        //   364: iconst_5       
        //   365: iadd           
        //   366: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   369: ldc_w           "|ID|"
        //   372: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   375: aload           10
        //   377: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   380: ldc_w           "|"
        //   383: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   386: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   389: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   392: pop            
        //   393: aload           9
        //   395: ldc_w           "X-kid"
        //   398: new             Ljava/lang/StringBuilder;
        //   401: dup            
        //   402: aload           10
        //   404: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   407: invokevirtual   java/lang/String.length:()I
        //   410: iconst_5       
        //   411: iadd           
        //   412: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   415: ldc_w           "|ID|"
        //   418: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   421: aload           10
        //   423: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   426: ldc_w           "|"
        //   429: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   432: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   435: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   438: pop            
        //   439: ldc_w           "com.google.android.gsf"
        //   442: getstatic       com/google/android/gms/iid/zzl.zzict:Ljava/lang/String;
        //   445: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   448: istore          8
        //   450: aload           9
        //   452: ldc_w           "useGsf"
        //   455: invokevirtual   android/content/Intent.getStringExtra:(Ljava/lang/String;)Ljava/lang/String;
        //   458: astore_1       
        //   459: aload_1        
        //   460: ifnull          472
        //   463: ldc_w           "1"
        //   466: aload_1        
        //   467: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   470: istore          8
        //   472: ldc             "InstanceID/Rpc"
        //   474: iconst_3       
        //   475: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   478: ifeq            526
        //   481: aload           9
        //   483: invokevirtual   android/content/Intent.getExtras:()Landroid/os/Bundle;
        //   486: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   489: astore_1       
        //   490: ldc             "InstanceID/Rpc"
        //   492: new             Ljava/lang/StringBuilder;
        //   495: dup            
        //   496: aload_1        
        //   497: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   500: invokevirtual   java/lang/String.length:()I
        //   503: bipush          8
        //   505: iadd           
        //   506: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   509: ldc_w           "Sending "
        //   512: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   515: aload_1        
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   522: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   525: pop            
        //   526: aload_0        
        //   527: getfield        com/google/android/gms/iid/zzl.zzida:Landroid/os/Messenger;
        //   530: ifnull          635
        //   533: aload           9
        //   535: ldc_w           "google.messenger"
        //   538: aload_0        
        //   539: getfield        com/google/android/gms/iid/zzl.zzhzv:Landroid/os/Messenger;
        //   542: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
        //   545: pop            
        //   546: invokestatic    android/os/Message.obtain:()Landroid/os/Message;
        //   549: astore_1       
        //   550: aload_1        
        //   551: aload           9
        //   553: putfield        android/os/Message.obj:Ljava/lang/Object;
        //   556: aload_0        
        //   557: getfield        com/google/android/gms/iid/zzl.zzida:Landroid/os/Messenger;
        //   560: aload_1        
        //   561: invokevirtual   android/os/Messenger.send:(Landroid/os/Message;)V
        //   564: aload           11
        //   566: ldc2_w          30000
        //   569: invokevirtual   android/os/ConditionVariable.block:(J)Z
        //   572: pop            
        //   573: aload_0        
        //   574: invokevirtual   java/lang/Object.getClass:()Ljava/lang/Class;
        //   577: astore_1       
        //   578: aload_1        
        //   579: monitorenter   
        //   580: aload_0        
        //   581: getfield        com/google/android/gms/iid/zzl.zzicz:Ljava/util/Map;
        //   584: aload           10
        //   586: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //   591: astore_2       
        //   592: aload_2        
        //   593: instanceof      Landroid/content/Intent;
        //   596: ifeq            836
        //   599: aload_2        
        //   600: checkcast       Landroid/content/Intent;
        //   603: astore_2       
        //   604: aload_1        
        //   605: monitorexit    
        //   606: aload_2        
        //   607: areturn        
        //   608: ldc_w           "com.google.android.c2dm.intent.REGISTER"
        //   611: astore          9
        //   613: goto            172
        //   616: astore_1       
        //   617: ldc             "InstanceID/Rpc"
        //   619: iconst_3       
        //   620: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   623: ifeq            635
        //   626: ldc             "InstanceID/Rpc"
        //   628: ldc_w           "Messenger failed, fallback to startService"
        //   631: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   634: pop            
        //   635: iload           8
        //   637: ifeq            733
        //   640: aload_0        
        //   641: monitorenter   
        //   642: getstatic       com/google/android/gms/iid/zzl.zzicy:Landroid/content/BroadcastReceiver;
        //   645: ifnonnull       714
        //   648: new             Lcom/google/android/gms/iid/zzn;
        //   651: dup            
        //   652: aload_0        
        //   653: invokespecial   com/google/android/gms/iid/zzn.<init>:(Lcom/google/android/gms/iid/zzl;)V
        //   656: putstatic       com/google/android/gms/iid/zzl.zzicy:Landroid/content/BroadcastReceiver;
        //   659: ldc             "InstanceID/Rpc"
        //   661: iconst_3       
        //   662: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   665: ifeq            677
        //   668: ldc             "InstanceID/Rpc"
        //   670: ldc_w           "Registered GSF callback receiver"
        //   673: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   676: pop            
        //   677: new             Landroid/content/IntentFilter;
        //   680: dup            
        //   681: ldc_w           "com.google.android.c2dm.intent.REGISTRATION"
        //   684: invokespecial   android/content/IntentFilter.<init>:(Ljava/lang/String;)V
        //   687: astore_1       
        //   688: aload_1        
        //   689: aload_0        
        //   690: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   693: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //   696: invokevirtual   android/content/IntentFilter.addCategory:(Ljava/lang/String;)V
        //   699: aload_0        
        //   700: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   703: getstatic       com/google/android/gms/iid/zzl.zzicy:Landroid/content/BroadcastReceiver;
        //   706: aload_1        
        //   707: ldc             "com.google.android.c2dm.permission.SEND"
        //   709: aconst_null    
        //   710: invokevirtual   android/content/Context.registerReceiver:(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;Ljava/lang/String;Landroid/os/Handler;)Landroid/content/Intent;
        //   713: pop            
        //   714: aload_0        
        //   715: monitorexit    
        //   716: aload_0        
        //   717: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   720: aload           9
        //   722: invokevirtual   android/content/Context.sendBroadcast:(Landroid/content/Intent;)V
        //   725: goto            564
        //   728: astore_1       
        //   729: aload_0        
        //   730: monitorexit    
        //   731: aload_1        
        //   732: athrow         
        //   733: aload           9
        //   735: ldc_w           "google.messenger"
        //   738: aload_0        
        //   739: getfield        com/google/android/gms/iid/zzl.zzhzv:Landroid/os/Messenger;
        //   742: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Landroid/os/Parcelable;)Landroid/content/Intent;
        //   745: pop            
        //   746: aload           9
        //   748: ldc_w           "messenger2"
        //   751: ldc_w           "1"
        //   754: invokevirtual   android/content/Intent.putExtra:(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
        //   757: pop            
        //   758: aload_0        
        //   759: getfield        com/google/android/gms/iid/zzl.zzidb:Lcom/google/android/gms/iid/MessengerCompat;
        //   762: ifnull          805
        //   765: invokestatic    android/os/Message.obtain:()Landroid/os/Message;
        //   768: astore_1       
        //   769: aload_1        
        //   770: aload           9
        //   772: putfield        android/os/Message.obj:Ljava/lang/Object;
        //   775: aload_0        
        //   776: getfield        com/google/android/gms/iid/zzl.zzidb:Lcom/google/android/gms/iid/MessengerCompat;
        //   779: aload_1        
        //   780: invokevirtual   com/google/android/gms/iid/MessengerCompat.send:(Landroid/os/Message;)V
        //   783: goto            564
        //   786: astore_1       
        //   787: ldc             "InstanceID/Rpc"
        //   789: iconst_3       
        //   790: invokestatic    android/util/Log.isLoggable:(Ljava/lang/String;I)Z
        //   793: ifeq            805
        //   796: ldc             "InstanceID/Rpc"
        //   798: ldc_w           "Messenger failed, fallback to startService"
        //   801: invokestatic    android/util/Log.d:(Ljava/lang/String;Ljava/lang/String;)I
        //   804: pop            
        //   805: getstatic       com/google/android/gms/iid/zzl.zzicu:Z
        //   808: ifeq            823
        //   811: aload_0        
        //   812: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   815: aload           9
        //   817: invokevirtual   android/content/Context.sendBroadcast:(Landroid/content/Intent;)V
        //   820: goto            564
        //   823: aload_0        
        //   824: getfield        com/google/android/gms/iid/zzl.zzaif:Landroid/content/Context;
        //   827: aload           9
        //   829: invokevirtual   android/content/Context.startService:(Landroid/content/Intent;)Landroid/content/ComponentName;
        //   832: pop            
        //   833: goto            564
        //   836: aload_2        
        //   837: instanceof      Ljava/lang/String;
        //   840: ifeq            860
        //   843: new             Ljava/io/IOException;
        //   846: dup            
        //   847: aload_2        
        //   848: checkcast       Ljava/lang/String;
        //   851: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   854: athrow         
        //   855: astore_2       
        //   856: aload_1        
        //   857: monitorexit    
        //   858: aload_2        
        //   859: athrow         
        //   860: aload_2        
        //   861: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   864: astore_2       
        //   865: ldc             "InstanceID/Rpc"
        //   867: new             Ljava/lang/StringBuilder;
        //   870: dup            
        //   871: aload_2        
        //   872: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
        //   875: invokevirtual   java/lang/String.length:()I
        //   878: bipush          12
        //   880: iadd           
        //   881: invokespecial   java/lang/StringBuilder.<init>:(I)V
        //   884: ldc_w           "No response "
        //   887: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   890: aload_2        
        //   891: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   894: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   897: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
        //   900: pop            
        //   901: new             Ljava/io/IOException;
        //   904: dup            
        //   905: ldc_w           "TIMEOUT"
        //   908: invokespecial   java/io/IOException.<init>:(Ljava/lang/String;)V
        //   911: athrow         
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  23     40     128    134    Any
        //  129    132    128    134    Any
        //  556    564    616    635    Landroid/os/RemoteException;
        //  580    606    855    860    Any
        //  642    677    728    733    Any
        //  677    714    728    733    Any
        //  714    716    728    733    Any
        //  729    731    728    733    Any
        //  775    783    786    805    Landroid/os/RemoteException;
        //  836    855    855    860    Any
        //  856    858    855    860    Any
        //  860    912    855    860    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0608:
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
    
    private static boolean zzb(final PackageManager packageManager, final String s) {
        try {
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(s, 0);
            zzl.zzict = applicationInfo.packageName;
            zzl.zzicw = applicationInfo.uid;
            return true;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return false;
        }
    }
    
    private static void zzd(final Object o, final Object obj) {
        if (o instanceof ConditionVariable) {
            ((ConditionVariable)o).open();
        }
        if (!(o instanceof Messenger)) {
            return;
        }
        final Messenger messenger = (Messenger)o;
        final Message obtain = Message.obtain();
        obtain.obj = obj;
        try {
            messenger.send(obtain);
        }
        catch (RemoteException ex) {
            final String value = String.valueOf(ex);
            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(value).length() + 24).append("Failed to send response ").append(value).toString());
        }
    }
    
    public static boolean zzdl(final Context context) {
        if (zzl.zzict != null) {
            zzdm(context);
        }
        return zzl.zzicu;
    }
    
    public static String zzdm(final Context context) {
        if (zzl.zzict != null) {
            return zzl.zzict;
        }
        zzl.zzicv = Process.myUid();
        final PackageManager packageManager = context.getPackageManager();
        Label_0101: {
            if (!zzq.isAtLeastO()) {
                final Iterator iterator = packageManager.queryIntentServices(new Intent("com.google.android.c2dm.intent.REGISTER"), 0).iterator();
                while (true) {
                    while (iterator.hasNext()) {
                        if (zza(packageManager, iterator.next().serviceInfo.packageName, "com.google.android.c2dm.intent.REGISTER")) {
                            zzl.zzicu = false;
                            final int n = 1;
                            if (n != 0) {
                                return zzl.zzict;
                            }
                            break Label_0101;
                        }
                    }
                    final int n = 0;
                    continue;
                }
            }
        }
        if (zza(packageManager)) {
            return zzl.zzict;
        }
        Log.w("InstanceID/Rpc", "Failed to resolve IID implementation package, falling back");
        if (zzb(packageManager, "com.google.android.gms")) {
            zzl.zzicu = zzq.isAtLeastO();
            return zzl.zzict;
        }
        if (!zzq.zzamb() && zzb(packageManager, "com.google.android.gsf")) {
            zzl.zzicu = false;
            return zzl.zzict;
        }
        Log.w("InstanceID/Rpc", "Google Play services is missing, unable to get tokens");
        return null;
    }
    
    private static int zzdn(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        try {
            return packageManager.getPackageInfo(zzdm(context), 0).versionCode;
        }
        catch (PackageManager$NameNotFoundException ex) {
            return -1;
        }
    }
    
    private final void zzi(final Intent intent) {
        synchronized (this) {
            if (this.zzhzr == null) {
                final Intent intent2 = new Intent();
                intent2.setPackage("com.google.example.invalidpackage");
                this.zzhzr = PendingIntent.getBroadcast(this.zzaif, 0, intent2, 0);
            }
            intent.putExtra("app", (Parcelable)this.zzhzr);
        }
    }
    
    private final void zzi(final String s, final Object o) {
        synchronized (this.getClass()) {
            final Object value = this.zzicz.get(s);
            this.zzicz.put(s, o);
            zzd(value, o);
        }
    }
    
    static String zzj(final Intent intent) throws IOException {
        if (intent == null) {
            throw new IOException("SERVICE_NOT_AVAILABLE");
        }
        String s;
        if ((s = intent.getStringExtra("registration_id")) == null) {
            s = intent.getStringExtra("unregistered");
        }
        intent.getLongExtra("Retry-After", 0L);
        if (s != null) {
            return s;
        }
        final String stringExtra = intent.getStringExtra("error");
        if (stringExtra != null) {
            throw new IOException(stringExtra);
        }
        final String value = String.valueOf(intent.getExtras());
        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(value).length() + 29).append("Unexpected response from GCM ").append(value).toString(), new Throwable());
        throw new IOException("SERVICE_NOT_AVAILABLE");
    }
    
    final Intent zza(final Bundle bundle, final KeyPair keyPair) throws IOException {
        Intent zzb;
        final Intent intent = zzb = this.zzb(bundle, keyPair);
        if (intent != null) {
            zzb = intent;
            if (intent.hasExtra("google.messenger")) {
                final Intent zzb2 = this.zzb(bundle, keyPair);
                if ((zzb = zzb2) != null) {
                    zzb = zzb2;
                    if (zzb2.hasExtra("google.messenger")) {
                        zzb = null;
                    }
                }
            }
        }
        return zzb;
    }
    
    public final void zzc(final Message message) {
        if (message == null) {
            return;
        }
        if (message.obj instanceof Intent) {
            final Intent intent = (Intent)message.obj;
            intent.setExtrasClassLoader(MessengerCompat.class.getClassLoader());
            if (intent.hasExtra("google.messenger")) {
                final Parcelable parcelableExtra = intent.getParcelableExtra("google.messenger");
                if (parcelableExtra instanceof MessengerCompat) {
                    this.zzidb = (MessengerCompat)parcelableExtra;
                }
                if (parcelableExtra instanceof Messenger) {
                    this.zzida = (Messenger)parcelableExtra;
                }
            }
            this.zzk((Intent)message.obj);
            return;
        }
        Log.w("InstanceID/Rpc", "Dropping invalid message");
    }
    
    public final void zzk(final Intent intent) {
        if (intent == null) {
            if (Log.isLoggable("InstanceID/Rpc", 3)) {
                Log.d("InstanceID/Rpc", "Unexpected response: null");
            }
        }
        else {
            final String action = intent.getAction();
            if (!"com.google.android.c2dm.intent.REGISTRATION".equals(action) && !"com.google.android.gms.iid.InstanceID".equals(action)) {
                if (Log.isLoggable("InstanceID/Rpc", 3)) {
                    final String value = String.valueOf(intent.getAction());
                    String concat;
                    if (value.length() != 0) {
                        concat = "Unexpected response ".concat(value);
                    }
                    else {
                        concat = new String("Unexpected response ");
                    }
                    Log.d("InstanceID/Rpc", concat);
                }
            }
            else {
                String s = intent.getStringExtra("registration_id");
                if (s == null) {
                    s = intent.getStringExtra("unregistered");
                }
                if (s == null) {
                    final String stringExtra = intent.getStringExtra("error");
                    if (stringExtra == null) {
                        final String value2 = String.valueOf(intent.getExtras());
                        Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(value2).length() + 49).append("Unexpected response, no error or registration id ").append(value2).toString());
                        return;
                    }
                    if (Log.isLoggable("InstanceID/Rpc", 3)) {
                        final String value3 = String.valueOf(stringExtra);
                        String concat2;
                        if (value3.length() != 0) {
                            concat2 = "Received InstanceID error ".concat(value3);
                        }
                        else {
                            concat2 = new String("Received InstanceID error ");
                        }
                        Log.d("InstanceID/Rpc", concat2);
                    }
                    String s4;
                    String substring;
                    if (stringExtra.startsWith("|")) {
                        final String[] split = stringExtra.split("\\|");
                        if (!"ID".equals(split[1])) {
                            final String value4 = String.valueOf(stringExtra);
                            String concat3;
                            if (value4.length() != 0) {
                                concat3 = "Unexpected structured response ".concat(value4);
                            }
                            else {
                                concat3 = new String("Unexpected structured response ");
                            }
                            Log.w("InstanceID/Rpc", concat3);
                        }
                        if (split.length > 2) {
                            final String s2 = split[2];
                            final String s3 = split[3];
                            s4 = s2;
                            substring = s3;
                            if (s3.startsWith(":")) {
                                substring = s3.substring(1);
                                s4 = s2;
                            }
                        }
                        else {
                            substring = "UNKNOWN";
                            s4 = null;
                        }
                        intent.putExtra("error", substring);
                    }
                    else {
                        final String s5 = null;
                        substring = stringExtra;
                        s4 = s5;
                    }
                    if (s4 == null) {
                        this.zzad(substring);
                    }
                    else {
                        this.zzi(s4, substring);
                    }
                    final long longExtra = intent.getLongExtra("Retry-After", 0L);
                    if (longExtra > 0L) {
                        this.zzidd = SystemClock.elapsedRealtime();
                        this.zzidf = (int)longExtra * 1000;
                        this.zzidg = SystemClock.elapsedRealtime() + this.zzidf;
                        Log.w("InstanceID/Rpc", new StringBuilder(52).append("Explicit request from server to backoff: ").append(this.zzidf).toString());
                        return;
                    }
                    if (("SERVICE_NOT_AVAILABLE".equals(substring) || "AUTHENTICATION_FAILED".equals(substring)) && "com.google.android.gsf".equals(zzl.zzict)) {
                        ++this.zzide;
                        if (this.zzide >= 3) {
                            if (this.zzide == 3) {
                                this.zzidf = new Random().nextInt(1000) + 1000;
                            }
                            this.zzidf <<= 1;
                            this.zzidg = SystemClock.elapsedRealtime() + this.zzidf;
                            Log.w("InstanceID/Rpc", new StringBuilder(String.valueOf(substring).length() + 31).append("Backoff due to ").append(substring).append(" for ").append(this.zzidf).toString());
                        }
                    }
                }
                else {
                    this.zzidc = SystemClock.elapsedRealtime();
                    this.zzidg = 0L;
                    this.zzide = 0;
                    this.zzidf = 0;
                    String s6 = null;
                    if (s.startsWith("|")) {
                        final String[] split2 = s.split("\\|");
                        if (!"ID".equals(split2[1])) {
                            final String value5 = String.valueOf(s);
                            String concat4;
                            if (value5.length() != 0) {
                                concat4 = "Unexpected structured response ".concat(value5);
                            }
                            else {
                                concat4 = new String("Unexpected structured response ");
                            }
                            Log.w("InstanceID/Rpc", concat4);
                        }
                        s6 = split2[2];
                        if (split2.length > 4) {
                            if ("SYNC".equals(split2[3])) {
                                final Context zzaif = this.zzaif;
                                final Intent intent2 = new Intent("com.google.android.gms.iid.InstanceID");
                                intent2.putExtra("CMD", "SYNC");
                                intent2.setClassName(zzaif, "com.google.android.gms.gcm.GcmReceiver");
                                zzaif.sendBroadcast(intent2);
                            }
                            else if ("RST".equals(split2[3])) {
                                final Context zzaif2 = this.zzaif;
                                InstanceID.getInstance(this.zzaif);
                                InstanceIDListenerService.zza(zzaif2, InstanceID.zzauu());
                                intent.removeExtra("registration_id");
                                this.zzi(s6, intent);
                                return;
                            }
                        }
                        String substring2;
                        final String s7 = substring2 = split2[split2.length - 1];
                        if (s7.startsWith(":")) {
                            substring2 = s7.substring(1);
                        }
                        intent.putExtra("registration_id", substring2);
                    }
                    if (s6 == null) {
                        this.zzad(intent);
                        return;
                    }
                    this.zzi(s6, intent);
                }
            }
        }
    }
}
