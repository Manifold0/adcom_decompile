// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.content.SharedPreferences;
import java.net.HttpURLConnection;
import java.util.Map;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import java.util.HashMap;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.text.TextUtils;
import net.hockeyapp.android.objects.CrashMetaData;
import net.hockeyapp.android.objects.CrashManagerUserInput;
import java.io.IOException;
import java.io.FilenameFilter;
import java.io.File;
import net.hockeyapp.android.objects.CrashDetails;
import java.util.Arrays;
import java.util.List;
import net.hockeyapp.android.utils.Util;
import android.preference.PreferenceManager;
import android.app.Activity;
import net.hockeyapp.android.utils.HockeyLog;
import android.content.SharedPreferences$Editor;
import android.content.Context;
import java.lang.ref.WeakReference;

public class CrashManager
{
    private static final String ALWAYS_SEND_KEY = "always_send_crash_reports";
    private static final int STACK_TRACES_FOUND_CONFIRMED = 2;
    private static final int STACK_TRACES_FOUND_NEW = 1;
    private static final int STACK_TRACES_FOUND_NONE = 0;
    private static boolean didCrashInLastSession;
    private static String identifier;
    private static long initializeTimestamp;
    private static boolean submitting;
    private static String urlString;
    
    static {
        CrashManager.identifier = null;
        CrashManager.urlString = null;
        CrashManager.submitting = false;
        CrashManager.didCrashInLastSession = false;
    }
    
    private static String contentsOfFile(final WeakReference<Context> p0, final String p1) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ifnull          149
        //     4: aload_0        
        //     5: invokevirtual   java/lang/ref/WeakReference.get:()Ljava/lang/Object;
        //     8: checkcast       Landroid/content/Context;
        //    11: astore          5
        //    13: aload           5
        //    15: ifnull          149
        //    18: new             Ljava/lang/StringBuilder;
        //    21: dup            
        //    22: invokespecial   java/lang/StringBuilder.<init>:()V
        //    25: astore          4
        //    27: aconst_null    
        //    28: astore_3       
        //    29: aconst_null    
        //    30: astore_0       
        //    31: aconst_null    
        //    32: astore_2       
        //    33: new             Ljava/io/BufferedReader;
        //    36: dup            
        //    37: new             Ljava/io/InputStreamReader;
        //    40: dup            
        //    41: aload           5
        //    43: aload_1        
        //    44: invokevirtual   android/content/Context.openFileInput:(Ljava/lang/String;)Ljava/io/FileInputStream;
        //    47: invokespecial   java/io/InputStreamReader.<init>:(Ljava/io/InputStream;)V
        //    50: invokespecial   java/io/BufferedReader.<init>:(Ljava/io/Reader;)V
        //    53: astore_1       
        //    54: aload_1        
        //    55: invokevirtual   java/io/BufferedReader.readLine:()Ljava/lang/String;
        //    58: astore_0       
        //    59: aload_0        
        //    60: ifnull          99
        //    63: aload           4
        //    65: aload_0        
        //    66: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    69: pop            
        //    70: aload           4
        //    72: ldc             "line.separator"
        //    74: invokestatic    java/lang/System.getProperty:(Ljava/lang/String;)Ljava/lang/String;
        //    77: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    80: pop            
        //    81: goto            54
        //    84: astore_0       
        //    85: aload_1        
        //    86: ifnull          93
        //    89: aload_1        
        //    90: invokevirtual   java/io/BufferedReader.close:()V
        //    93: aload           4
        //    95: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //    98: areturn        
        //    99: aload_1        
        //   100: ifnull          177
        //   103: aload_1        
        //   104: invokevirtual   java/io/BufferedReader.close:()V
        //   107: goto            93
        //   110: astore_0       
        //   111: goto            93
        //   114: astore_2       
        //   115: aload_3        
        //   116: astore_1       
        //   117: aload_1        
        //   118: astore_0       
        //   119: aload_2        
        //   120: invokevirtual   java/io/IOException.printStackTrace:()V
        //   123: aload_1        
        //   124: ifnull          93
        //   127: aload_1        
        //   128: invokevirtual   java/io/BufferedReader.close:()V
        //   131: goto            93
        //   134: astore_0       
        //   135: goto            93
        //   138: astore_1       
        //   139: aload_0        
        //   140: ifnull          147
        //   143: aload_0        
        //   144: invokevirtual   java/io/BufferedReader.close:()V
        //   147: aload_1        
        //   148: athrow         
        //   149: aconst_null    
        //   150: areturn        
        //   151: astore_0       
        //   152: goto            93
        //   155: astore_0       
        //   156: goto            147
        //   159: astore_2       
        //   160: aload_1        
        //   161: astore_0       
        //   162: aload_2        
        //   163: astore_1       
        //   164: goto            139
        //   167: astore_2       
        //   168: goto            117
        //   171: astore_0       
        //   172: aload_2        
        //   173: astore_1       
        //   174: goto            85
        //   177: goto            93
        //    Signature:
        //  (Ljava/lang/ref/WeakReference<Landroid/content/Context;>;Ljava/lang/String;)Ljava/lang/String;
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                           
        //  -----  -----  -----  -----  -------------------------------
        //  33     54     171    177    Ljava/io/FileNotFoundException;
        //  33     54     114    117    Ljava/io/IOException;
        //  33     54     138    139    Any
        //  54     59     84     85     Ljava/io/FileNotFoundException;
        //  54     59     167    171    Ljava/io/IOException;
        //  54     59     159    167    Any
        //  63     81     84     85     Ljava/io/FileNotFoundException;
        //  63     81     167    171    Ljava/io/IOException;
        //  63     81     159    167    Any
        //  89     93     151    155    Ljava/io/IOException;
        //  103    107    110    114    Ljava/io/IOException;
        //  119    123    138    139    Any
        //  127    131    134    138    Ljava/io/IOException;
        //  143    147    155    159    Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.IndexOutOfBoundsException: Index: 98, Size: 98
        //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
        //     at java.util.ArrayList.get(ArrayList.java:429)
        //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
        //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
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
    
    private static void deleteRetryCounter(final WeakReference<Context> weakReference, final String s, final int n) {
        if (weakReference != null) {
            final Context context = weakReference.get();
            if (context != null) {
                final SharedPreferences$Editor edit = context.getSharedPreferences("HockeySDK", 0).edit();
                edit.remove("RETRY_COUNT: " + s);
                edit.apply();
            }
        }
    }
    
    private static void deleteStackTrace(final WeakReference<Context> weakReference, final String s) {
        if (weakReference != null) {
            final Context context = weakReference.get();
            if (context != null) {
                context.deleteFile(s);
                context.deleteFile(s.replace(".stacktrace", ".user"));
                context.deleteFile(s.replace(".stacktrace", ".contact"));
                context.deleteFile(s.replace(".stacktrace", ".description"));
            }
        }
    }
    
    public static void deleteStackTraces(final WeakReference<Context> weakReference) {
        final String[] searchForStackTraces = searchForStackTraces();
        if (searchForStackTraces != null && searchForStackTraces.length > 0) {
            HockeyLog.debug("Found " + searchForStackTraces.length + " stacktrace(s).");
            int i = 0;
        Label_0109_Outer:
            while (i < searchForStackTraces.length) {
                while (true) {
                    if (weakReference != null) {
                        try {
                            HockeyLog.debug("Delete stacktrace " + searchForStackTraces[i] + ".");
                            deleteStackTrace(weakReference, searchForStackTraces[i]);
                            final Context context = weakReference.get();
                            if (context != null) {
                                context.deleteFile(searchForStackTraces[i]);
                            }
                            ++i;
                            continue Label_0109_Outer;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                    continue;
                }
            }
        }
    }
    
    public static boolean didCrashInLastSession() {
        return CrashManager.didCrashInLastSession;
    }
    
    public static void execute(final Context context, final CrashManagerListener crashManagerListener) {
        final boolean b = true;
        final Boolean value = crashManagerListener != null && crashManagerListener.ignoreDefaultHandler();
        final WeakReference<Context> weakReference = new WeakReference<Context>(context);
        final int hasStackTraces = hasStackTraces(weakReference);
        if (hasStackTraces == 1) {
            CrashManager.didCrashInLastSession = true;
            Boolean b2 = (boolean)(!(context instanceof Activity) && b) | PreferenceManager.getDefaultSharedPreferences(context).getBoolean("always_send_crash_reports", false);
            if (crashManagerListener != null) {
                b2 = ((boolean)(b2 | crashManagerListener.shouldAutoUploadCrashes()) | crashManagerListener.onCrashesFound());
                crashManagerListener.onNewCrashesFound();
            }
            if (!b2) {
                showDialog(weakReference, crashManagerListener, value);
                return;
            }
            sendCrashes(weakReference, crashManagerListener, value);
        }
        else {
            if (hasStackTraces == 2) {
                if (crashManagerListener != null) {
                    crashManagerListener.onConfirmedCrashesFound();
                }
                sendCrashes(weakReference, crashManagerListener, value);
                return;
            }
            registerHandler(weakReference, crashManagerListener, value);
        }
    }
    
    private static String getAlertTitle(final Context context) {
        return String.format(context.getString(R$string.hockeyapp_crash_dialog_title), Util.getAppName(context));
    }
    
    private static List<String> getConfirmedFilenames(final WeakReference<Context> weakReference) {
        List<String> list = null;
        if (weakReference != null) {
            final Context context = weakReference.get();
            list = list;
            if (context != null) {
                list = Arrays.asList(context.getSharedPreferences("HockeySDK", 0).getString("ConfirmedFilenames", "").split("\\|"));
            }
        }
        return list;
    }
    
    public static long getInitializeTimestamp() {
        return CrashManager.initializeTimestamp;
    }
    
    public static CrashDetails getLastCrashDetails() {
        if (Constants.FILES_PATH != null && didCrashInLastSession()) {
            final File[] listFiles = new File(Constants.FILES_PATH + "/").listFiles(new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.endsWith(".stacktrace");
                }
            });
            long n = 0L;
            File file = null;
            long lastModified;
            for (int length = listFiles.length, i = 0; i < length; ++i, n = lastModified) {
                final File file2 = listFiles[i];
                lastModified = n;
                if (file2.lastModified() > n) {
                    lastModified = file2.lastModified();
                    file = file2;
                }
            }
            if (file != null && file.exists()) {
                try {
                    return CrashDetails.fromFile(file);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        return null;
    }
    
    private static String getURLString() {
        return CrashManager.urlString + "api/2/apps/" + CrashManager.identifier + "/crashes/";
    }
    
    public static boolean handleUserInput(final CrashManagerUserInput crashManagerUserInput, final CrashMetaData crashMetaData, final CrashManagerListener crashManagerListener, final WeakReference<Context> weakReference, final boolean b) {
        switch (crashManagerUserInput) {
            default: {
                return false;
            }
            case CrashManagerUserInputDontSend: {
                if (crashManagerListener != null) {
                    crashManagerListener.onUserDeniedCrashes();
                }
                deleteStackTraces(weakReference);
                registerHandler(weakReference, crashManagerListener, b);
                return true;
            }
            case CrashManagerUserInputAlwaysSend: {
                Context context = null;
                if (weakReference != null) {
                    context = weakReference.get();
                }
                if (context == null) {
                    return false;
                }
                PreferenceManager.getDefaultSharedPreferences(context).edit().putBoolean("always_send_crash_reports", true).apply();
                sendCrashes(weakReference, crashManagerListener, b, crashMetaData);
                return true;
            }
            case CrashManagerUserInputSend: {
                sendCrashes(weakReference, crashManagerListener, b, crashMetaData);
                return true;
            }
        }
    }
    
    public static int hasStackTraces(final WeakReference<Context> weakReference) {
        final String[] searchForStackTraces = searchForStackTraces();
        final List list = null;
        int n = 0;
        if (searchForStackTraces == null) {
            return n;
        }
        n = n;
        if (searchForStackTraces.length <= 0) {
            return n;
        }
        while (true) {
            try {
                final List<String> confirmedFilenames = getConfirmedFilenames(weakReference);
                if (confirmedFilenames != null) {
                    final int n2 = 2;
                    final int length = searchForStackTraces.length;
                    int n3 = 0;
                    while (true) {
                        n = n2;
                        if (n3 >= length) {
                            break;
                        }
                        if (!confirmedFilenames.contains(searchForStackTraces[n3])) {
                            n = 1;
                            break;
                        }
                        ++n3;
                    }
                    return n;
                }
                return 1;
            }
            catch (Exception ex) {
                final List<String> confirmedFilenames = (List<String>)list;
                continue;
            }
            break;
        }
    }
    
    public static void initialize(final Context context, final String s, final String s2, final CrashManagerListener crashManagerListener) {
        initialize(context, s, s2, crashManagerListener, true);
    }
    
    private static void initialize(final Context context, final String urlString, final String s, final CrashManagerListener crashManagerListener, final boolean b) {
        final boolean b2 = false;
        if (context != null) {
            if (CrashManager.initializeTimestamp == 0L) {
                CrashManager.initializeTimestamp = System.currentTimeMillis();
            }
            CrashManager.urlString = urlString;
            CrashManager.identifier = Util.sanitizeAppIdentifier(s);
            CrashManager.didCrashInLastSession = false;
            Constants.loadFromContext(context);
            if (CrashManager.identifier == null) {
                CrashManager.identifier = Constants.APP_PACKAGE;
            }
            if (b) {
                boolean b3 = b2;
                if (crashManagerListener != null) {
                    b3 = b2;
                    if (crashManagerListener.ignoreDefaultHandler()) {
                        b3 = true;
                    }
                }
                registerHandler(new WeakReference<Context>(context), crashManagerListener, (boolean)b3);
            }
        }
    }
    
    public static void initialize(final Context context, final String s, final CrashManagerListener crashManagerListener) {
        initialize(context, "https://sdk.hockeyapp.net/", s, crashManagerListener, true);
    }
    
    private static String joinArray(final String[] array, final String s) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            sb.append(array[i]);
            if (i < array.length - 1) {
                sb.append(s);
            }
        }
        return sb.toString();
    }
    
    public static void register(final Context context) {
        final String appIdentifier = Util.getAppIdentifier(context);
        if (TextUtils.isEmpty((CharSequence)appIdentifier)) {
            throw new IllegalArgumentException("HockeyApp app identifier was not configured correctly in manifest or build configuration.");
        }
        register(context, appIdentifier);
    }
    
    public static void register(final Context context, final String s) {
        register(context, "https://sdk.hockeyapp.net/", s, null);
    }
    
    public static void register(final Context context, final String s, final String s2, final CrashManagerListener crashManagerListener) {
        initialize(context, s, s2, crashManagerListener, false);
        execute(context, crashManagerListener);
    }
    
    public static void register(final Context context, final String s, final CrashManagerListener crashManagerListener) {
        register(context, "https://sdk.hockeyapp.net/", s, crashManagerListener);
    }
    
    private static void registerHandler(final WeakReference<Context> weakReference, final CrashManagerListener listener, final boolean b) {
        if (TextUtils.isEmpty((CharSequence)Constants.APP_VERSION) || TextUtils.isEmpty((CharSequence)Constants.APP_PACKAGE)) {
            HockeyLog.debug("Exception handler not set because version or package is null.");
            return;
        }
        final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        if (defaultUncaughtExceptionHandler != null) {
            HockeyLog.debug("Current handler class = " + ((ExceptionHandler)defaultUncaughtExceptionHandler).getClass().getName());
        }
        if (defaultUncaughtExceptionHandler instanceof ExceptionHandler) {
            ((ExceptionHandler)defaultUncaughtExceptionHandler).setListener(listener);
            return;
        }
        Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)new ExceptionHandler(defaultUncaughtExceptionHandler, listener, b));
    }
    
    public static void resetAlwaysSend(final WeakReference<Context> weakReference) {
        if (weakReference != null) {
            final Context context = weakReference.get();
            if (context != null) {
                PreferenceManager.getDefaultSharedPreferences(context).edit().remove("always_send_crash_reports").apply();
            }
        }
    }
    
    private static void saveConfirmedStackTraces(final WeakReference<Context> weakReference) {
        if (weakReference == null) {
            return;
        }
        final Context context = weakReference.get();
        if (context == null) {
            return;
        }
        try {
            final String[] searchForStackTraces = searchForStackTraces();
            final SharedPreferences$Editor edit = context.getSharedPreferences("HockeySDK", 0).edit();
            edit.putString("ConfirmedFilenames", joinArray(searchForStackTraces, "|"));
            edit.apply();
        }
        catch (Exception ex) {}
    }
    
    private static String[] searchForStackTraces() {
        if (Constants.FILES_PATH == null) {
            HockeyLog.debug("Can't search for exception as file path is null.");
            return null;
        }
        HockeyLog.debug("Looking for exceptions in: " + Constants.FILES_PATH);
        final File file = new File(Constants.FILES_PATH + "/");
        if (!file.mkdir() && !file.exists()) {
            return new String[0];
        }
        return file.list(new FilenameFilter() {
            @Override
            public boolean accept(final File file, final String s) {
                return s.endsWith(".stacktrace");
            }
        });
    }
    
    private static void sendCrashes(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, final boolean b) {
        sendCrashes(weakReference, crashManagerListener, b, null);
    }
    
    private static void sendCrashes(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, final boolean b, final CrashMetaData crashMetaData) {
        saveConfirmedStackTraces(weakReference);
        registerHandler(weakReference, crashManagerListener, b);
        final Context context = weakReference.get();
        if ((context == null || Util.isConnectedToNetwork(context)) && !CrashManager.submitting) {
            CrashManager.submitting = true;
            new Thread() {
                @Override
                public void run() {
                    CrashManager.submitStackTraces(weakReference, crashManagerListener, crashMetaData);
                    CrashManager.submitting = false;
                }
            }.start();
        }
    }
    
    private static void showDialog(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, final boolean b) {
        Context context = null;
        if (weakReference != null) {
            context = weakReference.get();
        }
        if (context != null && (crashManagerListener == null || !crashManagerListener.onHandleAlertView())) {
            final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder(context);
            alertDialog$Builder.setTitle((CharSequence)getAlertTitle(context));
            alertDialog$Builder.setMessage(R$string.hockeyapp_crash_dialog_message);
            alertDialog$Builder.setNegativeButton(R$string.hockeyapp_crash_dialog_negative_button, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputDontSend, null, crashManagerListener, weakReference, b);
                }
            });
            alertDialog$Builder.setNeutralButton(R$string.hockeyapp_crash_dialog_neutral_button, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputAlwaysSend, null, crashManagerListener, weakReference, b);
                }
            });
            alertDialog$Builder.setPositiveButton(R$string.hockeyapp_crash_dialog_positive_button, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    CrashManager.handleUserInput(CrashManagerUserInput.CrashManagerUserInputSend, null, crashManagerListener, weakReference, b);
                }
            });
            alertDialog$Builder.create().show();
        }
    }
    
    public static void submitStackTraces(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener) {
        submitStackTraces(weakReference, crashManagerListener, null);
    }
    
    public static void submitStackTraces(final WeakReference<Context> weakReference, final CrashManagerListener crashManagerListener, final CrashMetaData crashMetaData) {
        final String[] searchForStackTraces = searchForStackTraces();
        Object o = false;
        Label_0960: {
            if (searchForStackTraces != null && searchForStackTraces.length > 0) {
                HockeyLog.debug("Found " + searchForStackTraces.length + " stacktrace(s).");
                Object o2;
                for (int i = 0; i < searchForStackTraces.length; ++i, o = o2) {
                    while (true) {
                        final Exception ex = null;
                        final Exception ex2 = null;
                        HttpURLConnection httpURLConnection = null;
                        final String s = searchForStackTraces[i];
                        Object build = ex;
                        o2 = ex2;
                        while (true) {
                            Label_0961: {
                                while (true) {
                                    try {
                                        final String contentsOfFile = contentsOfFile(weakReference, s);
                                        Object o3 = o;
                                        build = ex;
                                        o2 = ex2;
                                        if (contentsOfFile.length() > 0) {
                                            build = ex;
                                            o2 = ex2;
                                            HockeyLog.debug("Transmitting crash data: \n" + contentsOfFile);
                                            build = ex;
                                            o2 = ex2;
                                            o3 = contentsOfFile(weakReference, s.replace(".stacktrace", ".user"));
                                            build = ex;
                                            o2 = ex2;
                                            String contentsOfFile2;
                                            final String s2 = contentsOfFile2 = contentsOfFile(weakReference, s.replace(".stacktrace", ".contact"));
                                            Object o4 = o3;
                                            if (crashMetaData != null) {
                                                build = ex;
                                                o2 = ex2;
                                                final String userID = crashMetaData.getUserID();
                                                build = ex;
                                                o2 = ex2;
                                                if (!TextUtils.isEmpty((CharSequence)userID)) {
                                                    o3 = userID;
                                                }
                                                build = ex;
                                                o2 = ex2;
                                                final String userEmail = crashMetaData.getUserEmail();
                                                contentsOfFile2 = s2;
                                                o4 = o3;
                                                build = ex;
                                                o2 = ex2;
                                                if (!TextUtils.isEmpty((CharSequence)userEmail)) {
                                                    contentsOfFile2 = userEmail;
                                                    o4 = o3;
                                                }
                                            }
                                            build = ex;
                                            o2 = ex2;
                                            final String contentsOfFile3 = contentsOfFile(weakReference, s.replace(".stacktrace", ".description"));
                                            String userDescription;
                                            if (crashMetaData != null) {
                                                build = ex;
                                                o2 = ex2;
                                                userDescription = crashMetaData.getUserDescription();
                                            }
                                            else {
                                                userDescription = "";
                                            }
                                            o3 = userDescription;
                                            build = ex;
                                            o2 = ex2;
                                            if (!TextUtils.isEmpty((CharSequence)contentsOfFile3)) {
                                                build = ex;
                                                o2 = ex2;
                                                if (!TextUtils.isEmpty((CharSequence)userDescription)) {
                                                    build = ex;
                                                    o2 = ex2;
                                                    o3 = String.format("%s\n\nLog:\n%s", userDescription, contentsOfFile3);
                                                }
                                                else {
                                                    build = ex;
                                                    o2 = ex2;
                                                    o3 = String.format("Log:\n%s", contentsOfFile3);
                                                }
                                            }
                                            build = ex;
                                            o2 = ex2;
                                            final HashMap<String, String> hashMap = new HashMap<String, String>();
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("raw", contentsOfFile);
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("userID", (String)o4);
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("contact", contentsOfFile2);
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("description", (String)o3);
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("sdk", "HockeySDK");
                                            build = ex;
                                            o2 = ex2;
                                            hashMap.put("sdk_version", "4.1.2");
                                            build = ex;
                                            o2 = ex2;
                                            o3 = (o2 = (build = new HttpURLConnectionBuilder(getURLString()).setRequestMethod("POST").writeFormFields(hashMap).build()));
                                            final int responseCode = ((HttpURLConnection)o3).getResponseCode();
                                            if (responseCode == 202 || responseCode == 201) {
                                                break Label_0961;
                                            }
                                            final boolean b = false;
                                            o = b;
                                            httpURLConnection = (HttpURLConnection)o3;
                                            o3 = o;
                                        }
                                        if (httpURLConnection != null) {
                                            httpURLConnection.disconnect();
                                        }
                                        if (o3) {
                                            HockeyLog.debug("Transmission succeeded");
                                            deleteStackTrace(weakReference, searchForStackTraces[i]);
                                            o2 = o3;
                                            if (crashManagerListener != null) {
                                                crashManagerListener.onCrashesSent();
                                                deleteRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                o2 = o3;
                                            }
                                        }
                                        else {
                                            HockeyLog.debug("Transmission failed, will retry on next register() call");
                                            o2 = o3;
                                            if (crashManagerListener != null) {
                                                crashManagerListener.onCrashesNotSent();
                                                updateRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                o2 = o3;
                                            }
                                        }
                                        break;
                                    }
                                    catch (Exception o3) {
                                        o2 = build;
                                        ((Exception)o3).printStackTrace();
                                        if (build != null) {
                                            ((HttpURLConnection)build).disconnect();
                                        }
                                        if (o) {
                                            HockeyLog.debug("Transmission succeeded");
                                            deleteStackTrace(weakReference, searchForStackTraces[i]);
                                            o2 = o;
                                            if (crashManagerListener != null) {
                                                crashManagerListener.onCrashesSent();
                                                deleteRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                o2 = o;
                                            }
                                            continue;
                                        }
                                        else {
                                            HockeyLog.debug("Transmission failed, will retry on next register() call");
                                            o2 = o;
                                            if (crashManagerListener != null) {
                                                crashManagerListener.onCrashesNotSent();
                                                updateRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                o2 = o;
                                            }
                                            continue;
                                        }
                                    }
                                    finally {
                                        if (o2 != null) {
                                            ((HttpURLConnection)o2).disconnect();
                                        }
                                        while (true) {
                                            if (o) {
                                                HockeyLog.debug("Transmission succeeded");
                                                deleteStackTrace(weakReference, searchForStackTraces[i]);
                                                if (crashManagerListener != null) {
                                                    crashManagerListener.onCrashesSent();
                                                    deleteRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                    break Label_0929;
                                                }
                                                break Label_0929;
                                            }
                                            else {
                                                HockeyLog.debug("Transmission failed, will retry on next register() call");
                                                if (crashManagerListener != null) {
                                                    crashManagerListener.onCrashesNotSent();
                                                    updateRetryCounter(weakReference, searchForStackTraces[i], crashManagerListener.getMaxRetryAttempts());
                                                    break Label_0929;
                                                }
                                                break Label_0929;
                                            }
                                            continue;
                                        }
                                    }
                                    break;
                                }
                                break Label_0960;
                            }
                            final boolean b = true;
                            continue;
                        }
                    }
                }
            }
        }
    }
    
    private static void updateRetryCounter(final WeakReference<Context> weakReference, final String s, final int n) {
        if (n != -1 && weakReference != null) {
            final Context context = weakReference.get();
            if (context != null) {
                final SharedPreferences sharedPreferences = context.getSharedPreferences("HockeySDK", 0);
                final SharedPreferences$Editor edit = sharedPreferences.edit();
                final int int1 = sharedPreferences.getInt("RETRY_COUNT: " + s, 0);
                if (int1 >= n) {
                    deleteStackTrace(weakReference, s);
                    deleteRetryCounter(weakReference, s, n);
                    return;
                }
                edit.putInt("RETRY_COUNT: " + s, int1 + 1);
                edit.apply();
            }
        }
    }
}
