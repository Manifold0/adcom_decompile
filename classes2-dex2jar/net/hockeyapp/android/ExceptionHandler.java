// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.Process;
import java.io.IOException;
import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.objects.CrashDetails;
import java.util.UUID;
import java.io.Writer;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Date;
import android.text.TextUtils;

public class ExceptionHandler implements UncaughtExceptionHandler
{
    private CrashManagerListener mCrashManagerListener;
    private UncaughtExceptionHandler mDefaultExceptionHandler;
    private boolean mIgnoreDefaultHandler;
    
    public ExceptionHandler(final UncaughtExceptionHandler mDefaultExceptionHandler, final CrashManagerListener mCrashManagerListener, final boolean mIgnoreDefaultHandler) {
        this.mIgnoreDefaultHandler = false;
        this.mDefaultExceptionHandler = mDefaultExceptionHandler;
        this.mIgnoreDefaultHandler = mIgnoreDefaultHandler;
        this.mCrashManagerListener = mCrashManagerListener;
    }
    
    private static String limitedString(final String s) {
        String substring = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            substring = s;
            if (s.length() > 255) {
                substring = s.substring(0, 255);
            }
        }
        return substring;
    }
    
    public static void saveException(final Throwable t, final Thread thread, final CrashManagerListener crashManagerListener) {
        final Date appCrashDate = new Date();
        final Date appStartDate = new Date(CrashManager.getInitializeTimestamp());
        t.printStackTrace(new PrintWriter(new StringWriter()));
        final String string = UUID.randomUUID().toString();
        final CrashDetails crashDetails = new CrashDetails(string, t);
        crashDetails.setAppPackage(Constants.APP_PACKAGE);
        crashDetails.setAppVersionCode(Constants.APP_VERSION);
        crashDetails.setAppVersionName(Constants.APP_VERSION_NAME);
        crashDetails.setAppStartDate(appStartDate);
        crashDetails.setAppCrashDate(appCrashDate);
        if (crashManagerListener == null || crashManagerListener.includeDeviceData()) {
            crashDetails.setOsVersion(Constants.ANDROID_VERSION);
            crashDetails.setOsBuild(Constants.ANDROID_BUILD);
            crashDetails.setDeviceManufacturer(Constants.PHONE_MANUFACTURER);
            crashDetails.setDeviceModel(Constants.PHONE_MODEL);
        }
        if (thread != null && (crashManagerListener == null || crashManagerListener.includeThreadDetails())) {
            crashDetails.setThreadName(thread.getName() + "-" + thread.getId());
        }
        if (Constants.CRASH_IDENTIFIER != null && (crashManagerListener == null || crashManagerListener.includeDeviceIdentifier())) {
            crashDetails.setReporterKey(Constants.CRASH_IDENTIFIER);
        }
        crashDetails.writeCrashReport();
        if (crashManagerListener == null) {
            return;
        }
        try {
            writeValueToFile(limitedString(crashManagerListener.getUserID()), string + ".user");
            writeValueToFile(limitedString(crashManagerListener.getContact()), string + ".contact");
            writeValueToFile(crashManagerListener.getDescription(), string + ".description");
        }
        catch (IOException ex) {
            HockeyLog.error("Error saving crash meta data!", ex);
        }
    }
    
    @Deprecated
    public static void saveException(final Throwable t, final CrashManagerListener crashManagerListener) {
        saveException(t, null, crashManagerListener);
    }
    
    public static void saveManagedException(final Throwable t, final Thread thread, final CrashManagerListener crashManagerListener) {
        saveXamarinException(t, thread, null, true, crashManagerListener);
    }
    
    public static void saveNativeException(final Throwable t, final String s, final Thread thread, final CrashManagerListener crashManagerListener) {
        String s2 = s;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final String[] split = s.split("--- End of managed exception stack trace ---", 2);
            s2 = s;
            if (split != null) {
                s2 = s;
                if (split.length > 0) {
                    s2 = split[0];
                }
            }
        }
        saveXamarinException(t, thread, s2, false, crashManagerListener);
    }
    
    private static void saveXamarinException(final Throwable t, final Thread thread, final String s, final Boolean b, final CrashManagerListener crashManagerListener) {
        final Date appStartDate = new Date(CrashManager.getInitializeTimestamp());
        final String string = UUID.randomUUID().toString();
        final Date appCrashDate = new Date();
        final PrintWriter printWriter = new PrintWriter(new StringWriter());
        if (t != null) {
            t.printStackTrace(printWriter);
        }
        final CrashDetails crashDetails = new CrashDetails(string, t, s, b);
        crashDetails.setAppPackage(Constants.APP_PACKAGE);
        crashDetails.setAppVersionCode(Constants.APP_VERSION);
        crashDetails.setAppVersionName(Constants.APP_VERSION_NAME);
        crashDetails.setAppStartDate(appStartDate);
        crashDetails.setAppCrashDate(appCrashDate);
        if (crashManagerListener == null || crashManagerListener.includeDeviceData()) {
            crashDetails.setOsVersion(Constants.ANDROID_VERSION);
            crashDetails.setOsBuild(Constants.ANDROID_BUILD);
            crashDetails.setDeviceManufacturer(Constants.PHONE_MANUFACTURER);
            crashDetails.setDeviceModel(Constants.PHONE_MODEL);
        }
        if (thread != null && (crashManagerListener == null || crashManagerListener.includeThreadDetails())) {
            crashDetails.setThreadName(thread.getName() + "-" + thread.getId());
        }
        if (Constants.CRASH_IDENTIFIER != null && (crashManagerListener == null || crashManagerListener.includeDeviceIdentifier())) {
            crashDetails.setReporterKey(Constants.CRASH_IDENTIFIER);
        }
        crashDetails.writeCrashReport();
        if (crashManagerListener == null) {
            return;
        }
        try {
            writeValueToFile(limitedString(crashManagerListener.getUserID()), string + ".user");
            writeValueToFile(limitedString(crashManagerListener.getContact()), string + ".contact");
            writeValueToFile(crashManagerListener.getDescription(), string + ".description");
        }
        catch (IOException ex) {
            HockeyLog.error("Error saving crash meta data!", ex);
        }
    }
    
    private static void writeValueToFile(final String p0, final String p1) throws IOException {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //     4: ifeq            8
        //     7: return         
        //     8: aconst_null    
        //     9: astore_3       
        //    10: aconst_null    
        //    11: astore_2       
        //    12: aconst_null    
        //    13: astore          4
        //    15: new             Ljava/lang/StringBuilder;
        //    18: dup            
        //    19: invokespecial   java/lang/StringBuilder.<init>:()V
        //    22: getstatic       net/hockeyapp/android/Constants.FILES_PATH:Ljava/lang/String;
        //    25: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    28: ldc             "/"
        //    30: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    33: aload_1        
        //    34: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    37: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    40: astore          5
        //    42: aload           4
        //    44: astore_1       
        //    45: aload_0        
        //    46: invokestatic    android/text/TextUtils.isEmpty:(Ljava/lang/CharSequence;)Z
        //    49: ifne            88
        //    52: aload           4
        //    54: astore_1       
        //    55: aload_0        
        //    56: invokestatic    android/text/TextUtils.getTrimmedLength:(Ljava/lang/CharSequence;)I
        //    59: ifle            88
        //    62: new             Ljava/io/BufferedWriter;
        //    65: dup            
        //    66: new             Ljava/io/FileWriter;
        //    69: dup            
        //    70: aload           5
        //    72: invokespecial   java/io/FileWriter.<init>:(Ljava/lang/String;)V
        //    75: invokespecial   java/io/BufferedWriter.<init>:(Ljava/io/Writer;)V
        //    78: astore_1       
        //    79: aload_1        
        //    80: aload_0        
        //    81: invokevirtual   java/io/BufferedWriter.write:(Ljava/lang/String;)V
        //    84: aload_1        
        //    85: invokevirtual   java/io/BufferedWriter.flush:()V
        //    88: aload_1        
        //    89: ifnull          7
        //    92: aload_1        
        //    93: invokevirtual   java/io/BufferedWriter.close:()V
        //    96: return         
        //    97: astore_0       
        //    98: aload_3        
        //    99: astore_0       
        //   100: aload_0        
        //   101: ifnull          7
        //   104: aload_0        
        //   105: invokevirtual   java/io/BufferedWriter.close:()V
        //   108: return         
        //   109: astore_1       
        //   110: aload_2        
        //   111: astore_0       
        //   112: aload_0        
        //   113: ifnull          120
        //   116: aload_0        
        //   117: invokevirtual   java/io/BufferedWriter.close:()V
        //   120: aload_1        
        //   121: athrow         
        //   122: astore_2       
        //   123: aload_1        
        //   124: astore_0       
        //   125: aload_2        
        //   126: astore_1       
        //   127: goto            112
        //   130: astore_0       
        //   131: aload_1        
        //   132: astore_0       
        //   133: goto            100
        //    Exceptions:
        //  throws java.io.IOException
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  15     42     97     100    Ljava/io/IOException;
        //  15     42     109    112    Any
        //  45     52     97     100    Ljava/io/IOException;
        //  45     52     109    112    Any
        //  55     79     97     100    Ljava/io/IOException;
        //  55     79     109    112    Any
        //  79     88     130    136    Ljava/io/IOException;
        //  79     88     122    130    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0088:
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
    
    public void setListener(final CrashManagerListener mCrashManagerListener) {
        this.mCrashManagerListener = mCrashManagerListener;
    }
    
    @Override
    public void uncaughtException(final Thread thread, final Throwable t) {
        if (Constants.FILES_PATH == null) {
            this.mDefaultExceptionHandler.uncaughtException(thread, t);
            return;
        }
        saveException(t, thread, this.mCrashManagerListener);
        if (!this.mIgnoreDefaultHandler) {
            this.mDefaultExceptionHandler.uncaughtException(thread, t);
            return;
        }
        Process.killProcess(Process.myPid());
        System.exit(10);
    }
}
