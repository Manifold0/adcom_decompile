// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.impl;

import java.util.Collections;
import java.util.List;
import android.annotation.TargetApi;
import com.onesignal.shortcutbadger.ShortcutBadgeException;
import android.net.Uri;
import android.os.Bundle;
import com.onesignal.shortcutbadger.util.BroadcastHelper;
import android.content.Intent;
import android.content.ComponentName;
import android.content.Context;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;
import com.onesignal.shortcutbadger.Badger;

public class OPPOHomeBader implements Badger
{
    private static final String INTENT_ACTION = "com.oppo.unsettledevent";
    private static final String INTENT_EXTRA_BADGEUPGRADE_COUNT = "app_badge_count";
    private static final String INTENT_EXTRA_BADGE_COUNT = "number";
    private static final String INTENT_EXTRA_BADGE_UPGRADENUMBER = "upgradeNumber";
    private static final String INTENT_EXTRA_PACKAGENAME = "pakeageName";
    private static final String PROVIDER_CONTENT_URI = "content://com.android.badge/badge";
    private static int ROMVERSION;
    
    static {
        OPPOHomeBader.ROMVERSION = -1;
    }
    
    private boolean checkObjExists(final Object o) {
        return o == null || o.toString().equals("") || o.toString().trim().equals("null");
    }
    
    private Object executeClassLoad(final Class clazz, final String s, final Class[] array, final Object[] array2) {
        Object invoke;
        final Object o = invoke = null;
        if (clazz == null) {
            return invoke;
        }
        invoke = o;
        if (this.checkObjExists(s)) {
            return invoke;
        }
        final Method method = this.getMethod(clazz, s, array);
        invoke = o;
        if (method == null) {
            return invoke;
        }
        method.setAccessible(true);
        try {
            invoke = method.invoke(null, array2);
            return invoke;
        }
        catch (IllegalAccessException ex) {
            ex.printStackTrace();
            return null;
        }
        catch (InvocationTargetException ex2) {
            ex2.printStackTrace();
            return null;
        }
    }
    
    private Class getClass(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    private Method getMethod(final Class clazz, final String s, final Class[] array) {
        if (clazz != null && !this.checkObjExists(s)) {
            try {
                clazz.getMethods();
                clazz.getDeclaredMethods();
                return clazz.getDeclaredMethod(s, (Class[])array);
            }
            catch (Exception ex) {
                try {
                    return clazz.getMethod(s, (Class[])array);
                }
                catch (Exception ex2) {
                    if (clazz.getSuperclass() != null) {
                        return this.getMethod(clazz.getSuperclass(), s, array);
                    }
                    return null;
                }
            }
        }
        return null;
    }
    
    private int getSupportVersion() {
        if (OPPOHomeBader.ROMVERSION >= 0) {
            return OPPOHomeBader.ROMVERSION;
        }
        while (true) {
            try {
                final int intValue = (int)this.executeClassLoad(this.getClass("com.color.os.ColorBuild"), "getColorOSVERSION", null, null);
                if (intValue == 0) {
                    try {
                        final String systemProperty = this.getSystemProperty("ro.build.version.opporom");
                        if (systemProperty.startsWith("V1.4")) {
                            return 3;
                        }
                        if (systemProperty.startsWith("V2.0")) {
                            return 4;
                        }
                        if (systemProperty.startsWith("V2.1")) {
                            return 5;
                        }
                    }
                    catch (Exception ex) {}
                }
                return OPPOHomeBader.ROMVERSION = intValue;
            }
            catch (Exception ex2) {
                final int intValue = 0;
                continue;
            }
            break;
        }
    }
    
    private String getSystemProperty(final String p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_3       
        //     2: aconst_null    
        //     3: astore_2       
        //     4: new             Ljava/io/BufferedReader;
        //     7: dup            
        //     8: new             Ljava/io/InputStreamReader;
        //    11: dup            
        //    12: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
        //    15: new             Ljava/lang/StringBuilder;
        //    18: dup            
        //    19: invokespecial   java/lang/StringBuilder.<init>:()V
        //    22: ldc             "getprop "
        //    24: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    27: aload_1        
        //    28: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    31: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    34: invokevirtual   java/lang/Runtime.exec:(Ljava/lang/String;)Ljava/lang/Process;
        //    37: invokevirtual   java/lang/Process.getInputStream:()Ljava/io/InputStream;
        //    40: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    43: sipush          1024
        //    46: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;I)V
        //    49: astore_1       
        //    50: aload_1        
        //    51: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    54: astore_2       
        //    55: aload_1        
        //    56: invokevirtual   java/io/BufferedReader.close:()V
        //    59: aload_1        
        //    60: invokestatic    com/onesignal/shortcutbadger/util/CloseHelper.closeQuietly:(Ljava/io/Closeable;)V
        //    63: aload_2        
        //    64: areturn        
        //    65: astore_1       
        //    66: aload_2        
        //    67: astore_1       
        //    68: aload_1        
        //    69: invokestatic    com/onesignal/shortcutbadger/util/CloseHelper.closeQuietly:(Ljava/io/Closeable;)V
        //    72: aconst_null    
        //    73: areturn        
        //    74: astore_2       
        //    75: aload_3        
        //    76: astore_1       
        //    77: aload_1        
        //    78: invokestatic    com/onesignal/shortcutbadger/util/CloseHelper.closeQuietly:(Ljava/io/Closeable;)V
        //    81: aload_2        
        //    82: athrow         
        //    83: astore_2       
        //    84: goto            77
        //    87: astore_2       
        //    88: goto            68
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  4      50     65     68     Ljava/io/IOException;
        //  4      50     74     77     Any
        //  50     59     87     91     Ljava/io/IOException;
        //  50     59     83     87     Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0068:
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
    
    @TargetApi(11)
    @Override
    public void executeBadge(final Context context, final ComponentName componentName, final int n) throws ShortcutBadgeException {
        int n2 = n;
        if (n == 0) {
            n2 = -1;
        }
        final Intent intent = new Intent("com.oppo.unsettledevent");
        intent.putExtra("pakeageName", componentName.getPackageName());
        intent.putExtra("number", n2);
        intent.putExtra("upgradeNumber", n2);
        if (BroadcastHelper.canResolveBroadcast(context, intent)) {
            context.sendBroadcast(intent);
        }
        else if (this.getSupportVersion() == 6) {
            try {
                final Bundle bundle = new Bundle();
                bundle.putInt("app_badge_count", n2);
                context.getContentResolver().call(Uri.parse("content://com.android.badge/badge"), "setAppBadgeCount", (String)null, bundle);
            }
            catch (Throwable t) {
                throw new ShortcutBadgeException("unable to resolve intent: " + intent.toString());
            }
        }
    }
    
    @Override
    public List<String> getSupportLaunchers() {
        return Collections.singletonList("com.oppo.launcher");
    }
}
