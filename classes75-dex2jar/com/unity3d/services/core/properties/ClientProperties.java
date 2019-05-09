// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.properties;

import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import com.unity3d.services.core.log.DeviceLog;
import android.content.Context;
import android.app.Application;
import android.app.Activity;
import java.lang.ref.WeakReference;
import javax.security.auth.x500.X500Principal;

public class ClientProperties
{
    private static final X500Principal DEBUG_CERT;
    private static WeakReference<Activity> _activity;
    private static Application _application;
    private static Context _applicationContext;
    private static String _gameId;
    
    static {
        DEBUG_CERT = new X500Principal("CN=Android Debug,O=Android,C=US");
    }
    
    public static Activity getActivity() {
        return ClientProperties._activity.get();
    }
    
    public static String getAppName() {
        return ClientProperties._applicationContext.getPackageName();
    }
    
    public static String getAppVersion() {
        final String packageName = getApplicationContext().getPackageName();
        final PackageManager packageManager = getApplicationContext().getPackageManager();
        try {
            return packageManager.getPackageInfo(packageName, 0).versionName;
        }
        catch (PackageManager$NameNotFoundException ex) {
            DeviceLog.exception("Error getting package info", (Exception)ex);
            return null;
        }
    }
    
    public static Application getApplication() {
        return ClientProperties._application;
    }
    
    public static Context getApplicationContext() {
        return ClientProperties._applicationContext;
    }
    
    public static String getGameId() {
        return ClientProperties._gameId;
    }
    
    public static boolean isAppDebuggable() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1       
        //     2: iconst_0       
        //     3: istore          4
        //     5: iconst_0       
        //     6: istore_2       
        //     7: invokestatic    com/unity3d/services/core/properties/ClientProperties.getApplicationContext:()Landroid/content/Context;
        //    10: ifnull          177
        //    13: invokestatic    com/unity3d/services/core/properties/ClientProperties.getApplicationContext:()Landroid/content/Context;
        //    16: invokevirtual   android/content/Context.getPackageManager:()Landroid/content/pm/PackageManager;
        //    19: astore          7
        //    21: invokestatic    com/unity3d/services/core/properties/ClientProperties.getApplicationContext:()Landroid/content/Context;
        //    24: invokevirtual   android/content/Context.getPackageName:()Ljava/lang/String;
        //    27: astore          8
        //    29: aload           7
        //    31: aload           8
        //    33: iconst_0       
        //    34: invokevirtual   android/content/pm/PackageManager.getApplicationInfo:(Ljava/lang/String;I)Landroid/content/pm/ApplicationInfo;
        //    37: astore          9
        //    39: aload           9
        //    41: getfield        android/content/pm/ApplicationInfo.flags:I
        //    44: iconst_2       
        //    45: iand           
        //    46: istore_3       
        //    47: aload           9
        //    49: iload_3        
        //    50: putfield        android/content/pm/ApplicationInfo.flags:I
        //    53: iload_2        
        //    54: istore_0       
        //    55: iload_3        
        //    56: ifeq            64
        //    59: iconst_1       
        //    60: istore          4
        //    62: iload_2        
        //    63: istore_0       
        //    64: iload           4
        //    66: istore          5
        //    68: iload_0        
        //    69: ifeq            174
        //    72: iload           4
        //    74: istore          5
        //    76: iload           4
        //    78: istore          6
        //    80: aload           7
        //    82: aload           8
        //    84: bipush          64
        //    86: invokevirtual   android/content/pm/PackageManager.getPackageInfo:(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
        //    89: getfield        android/content/pm/PackageInfo.signatures:[Landroid/content/pm/Signature;
        //    92: astore          7
        //    94: iload           4
        //    96: istore          5
        //    98: iload           4
        //   100: istore          6
        //   102: aload           7
        //   104: arraylength    
        //   105: istore_2       
        //   106: iload_1        
        //   107: istore_0       
        //   108: iload           4
        //   110: istore          5
        //   112: iload_0        
        //   113: iload_2        
        //   114: if_icmpge       174
        //   117: aload           7
        //   119: iload_0        
        //   120: aaload         
        //   121: astore          8
        //   123: iload           4
        //   125: istore          5
        //   127: iload           4
        //   129: istore          6
        //   131: ldc             "X.509"
        //   133: invokestatic    java/security/cert/CertificateFactory.getInstance:(Ljava/lang/String;)Ljava/security/cert/CertificateFactory;
        //   136: new             Ljava/io/ByteArrayInputStream;
        //   139: dup            
        //   140: aload           8
        //   142: invokevirtual   android/content/pm/Signature.toByteArray:()[B
        //   145: invokespecial   java/io/ByteArrayInputStream.<init>:([B)V
        //   148: invokevirtual   java/security/cert/CertificateFactory.generateCertificate:(Ljava/io/InputStream;)Ljava/security/cert/Certificate;
        //   151: checkcast       Ljava/security/cert/X509Certificate;
        //   154: invokevirtual   java/security/cert/X509Certificate.getSubjectX500Principal:()Ljavax/security/auth/x500/X500Principal;
        //   157: getstatic       com/unity3d/services/core/properties/ClientProperties.DEBUG_CERT:Ljavax/security/auth/x500/X500Principal;
        //   160: invokevirtual   javax/security/auth/x500/X500Principal.equals:(Ljava/lang/Object;)Z
        //   163: istore          4
        //   165: iload           4
        //   167: ifeq            193
        //   170: iload           4
        //   172: istore          5
        //   174: iload           5
        //   176: ireturn        
        //   177: iconst_0       
        //   178: ireturn        
        //   179: astore          9
        //   181: ldc             "Could not find name"
        //   183: aload           9
        //   185: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   188: iconst_1       
        //   189: istore_0       
        //   190: goto            64
        //   193: iload_0        
        //   194: iconst_1       
        //   195: iadd           
        //   196: istore_0       
        //   197: goto            108
        //   200: astore          7
        //   202: ldc             "Could not find name"
        //   204: aload           7
        //   206: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   209: iload           5
        //   211: ireturn        
        //   212: astore          7
        //   214: ldc             "Certificate exception"
        //   216: aload           7
        //   218: invokestatic    com/unity3d/services/core/log/DeviceLog.exception:(Ljava/lang/String;Ljava/lang/Exception;)V
        //   221: iload           6
        //   223: ireturn        
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                                                     
        //  -----  -----  -----  -----  ---------------------------------------------------------
        //  29     53     179    193    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  80     94     200    212    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  80     94     212    224    Ljava/security/cert/CertificateException;
        //  102    106    200    212    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  102    106    212    224    Ljava/security/cert/CertificateException;
        //  131    165    200    212    Landroid/content/pm/PackageManager$NameNotFoundException;
        //  131    165    212    224    Ljava/security/cert/CertificateException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0108:
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
    
    public static void setActivity(final Activity activity) {
        ClientProperties._activity = new WeakReference<Activity>(activity);
    }
    
    public static void setApplication(final Application application) {
        ClientProperties._application = application;
    }
    
    public static void setApplicationContext(final Context applicationContext) {
        ClientProperties._applicationContext = applicationContext;
    }
    
    public static void setGameId(final String gameId) {
        ClientProperties._gameId = gameId;
    }
}
