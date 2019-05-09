// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.log;

import java.io.File;
import java.util.HashMap;

public class DeviceLog
{
    private static boolean FORCE_DEBUG_LOG = false;
    public static final int LOGLEVEL_DEBUG = 8;
    private static final int LOGLEVEL_ERROR = 1;
    public static final int LOGLEVEL_INFO = 4;
    private static final int LOGLEVEL_WARNING = 2;
    private static boolean LOG_DEBUG = false;
    private static boolean LOG_ERROR = false;
    private static boolean LOG_INFO = false;
    private static boolean LOG_WARNING = false;
    private static final int MAX_DEBUG_MSG_LENGTH = 3072;
    private static final HashMap<UnityAdsLogLevel, DeviceLogLevel> _deviceLogLevel;
    
    static {
        DeviceLog.LOG_ERROR = true;
        DeviceLog.LOG_WARNING = true;
        DeviceLog.LOG_INFO = true;
        DeviceLog.LOG_DEBUG = true;
        DeviceLog.FORCE_DEBUG_LOG = false;
        _deviceLogLevel = new HashMap<UnityAdsLogLevel, DeviceLogLevel>();
        if (DeviceLog._deviceLogLevel.size() == 0) {
            DeviceLog._deviceLogLevel.put(UnityAdsLogLevel.INFO, new DeviceLogLevel("i"));
            DeviceLog._deviceLogLevel.put(UnityAdsLogLevel.DEBUG, new DeviceLogLevel("d"));
            DeviceLog._deviceLogLevel.put(UnityAdsLogLevel.WARNING, new DeviceLogLevel("w"));
            DeviceLog._deviceLogLevel.put(UnityAdsLogLevel.ERROR, new DeviceLogLevel("e"));
        }
        if (new File("/data/local/tmp/UnityAdsForceDebugMode").exists()) {
            DeviceLog.FORCE_DEBUG_LOG = true;
        }
    }
    
    private static String checkMessage(final String s) {
        if (s == null || s.length() == 0) {
            return "DO NOT USE EMPTY MESSAGES, use DeviceLog.entered() instead";
        }
        return s;
    }
    
    private static DeviceLogEntry createLogEntry(final UnityAdsLogLevel unityAdsLogLevel, final String s) {
        final StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        final DeviceLogLevel logLevel = getLogLevel(unityAdsLogLevel);
        DeviceLogEntry deviceLogEntry = null;
        if (logLevel != null) {
            boolean b = false;
            int i;
            for (i = 0; i < stackTrace.length; ++i) {
                final StackTraceElement stackTraceElement = stackTrace[i];
                if (stackTraceElement.getClassName().equals(DeviceLog.class.getName())) {
                    b = true;
                }
                if (!stackTraceElement.getClassName().equals(DeviceLog.class.getName()) && b) {
                    break;
                }
            }
            StackTraceElement stackTraceElement2 = null;
            if (i < stackTrace.length) {
                stackTraceElement2 = stackTrace[i];
            }
            deviceLogEntry = deviceLogEntry;
            if (stackTraceElement2 != null) {
                deviceLogEntry = new DeviceLogEntry(logLevel, s, stackTraceElement2);
            }
        }
        return deviceLogEntry;
    }
    
    public static void debug(final String s) {
        if (DeviceLog.LOG_DEBUG || DeviceLog.FORCE_DEBUG_LOG) {
            if (s.length() <= 3072) {
                write(UnityAdsLogLevel.DEBUG, checkMessage(s));
                return;
            }
            debug(s.substring(0, 3072));
            if (s.length() < 30720) {
                debug(s.substring(3072));
            }
        }
    }
    
    public static void debug(final String s, final Object... array) {
        debug(String.format(s, array));
    }
    
    public static void entered() {
        debug("ENTERED METHOD");
    }
    
    public static void error(final String s) {
        write(UnityAdsLogLevel.ERROR, checkMessage(s));
    }
    
    public static void error(final String s, final Object... array) {
        error(String.format(s, array));
    }
    
    public static void exception(String string, final Exception ex) {
        String string2 = "";
        if (string != null) {
            string2 = "" + string;
        }
        string = string2;
        if (ex != null) {
            string = string2 + ": " + ex.getMessage();
        }
        String string3 = string;
        if (ex != null) {
            string3 = string;
            if (ex.getCause() != null) {
                string3 = string + ": " + ex.getCause().getMessage();
            }
        }
        write(UnityAdsLogLevel.ERROR, string3);
    }
    
    private static DeviceLogLevel getLogLevel(final UnityAdsLogLevel unityAdsLogLevel) {
        return DeviceLog._deviceLogLevel.get(unityAdsLogLevel);
    }
    
    public static void info(final String s) {
        write(UnityAdsLogLevel.INFO, checkMessage(s));
    }
    
    public static void info(final String s, final Object... array) {
        info(String.format(s, array));
    }
    
    public static void setLogLevel(final int n) {
        if (n >= 8) {
            DeviceLog.LOG_ERROR = true;
            DeviceLog.LOG_WARNING = true;
            DeviceLog.LOG_INFO = true;
            DeviceLog.LOG_DEBUG = true;
            return;
        }
        if (n >= 4) {
            DeviceLog.LOG_ERROR = true;
            DeviceLog.LOG_WARNING = true;
            DeviceLog.LOG_INFO = true;
            DeviceLog.LOG_DEBUG = false;
            return;
        }
        if (n >= 2) {
            DeviceLog.LOG_ERROR = true;
            DeviceLog.LOG_WARNING = true;
            DeviceLog.LOG_INFO = false;
            DeviceLog.LOG_DEBUG = false;
            return;
        }
        if (n >= 1) {
            DeviceLog.LOG_ERROR = true;
            DeviceLog.LOG_WARNING = false;
            DeviceLog.LOG_INFO = false;
            DeviceLog.LOG_DEBUG = false;
            return;
        }
        DeviceLog.LOG_ERROR = false;
        DeviceLog.LOG_WARNING = false;
        DeviceLog.LOG_INFO = false;
        DeviceLog.LOG_DEBUG = false;
    }
    
    public static void warning(final String s) {
        write(UnityAdsLogLevel.WARNING, checkMessage(s));
    }
    
    public static void warning(final String s, final Object... array) {
        warning(String.format(s, array));
    }
    
    private static void write(final UnityAdsLogLevel unityAdsLogLevel, final String s) {
        boolean b = true;
        switch (unityAdsLogLevel) {
            case INFO: {
                b = DeviceLog.LOG_INFO;
                break;
            }
            case DEBUG: {
                b = DeviceLog.LOG_DEBUG;
                break;
            }
            case WARNING: {
                b = DeviceLog.LOG_WARNING;
                break;
            }
            case ERROR: {
                b = DeviceLog.LOG_ERROR;
                break;
            }
        }
        if (DeviceLog.FORCE_DEBUG_LOG) {
            b = true;
        }
        if (b) {
            writeToLog(createLogEntry(unityAdsLogLevel, s));
        }
    }
    
    private static void writeToLog(final DeviceLogEntry p0) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1       
        //     2: aload_0        
        //     3: ifnull          73
        //     6: aload_0        
        //     7: invokevirtual   com/unity3d/services/core/log/DeviceLogEntry.getLogLevel:()Lcom/unity3d/services/core/log/DeviceLogLevel;
        //    10: ifnull          73
        //    13: ldc             Landroid/util/Log;.class
        //    15: aload_0        
        //    16: invokevirtual   com/unity3d/services/core/log/DeviceLogEntry.getLogLevel:()Lcom/unity3d/services/core/log/DeviceLogLevel;
        //    19: invokevirtual   com/unity3d/services/core/log/DeviceLogLevel.getReceivingMethodName:()Ljava/lang/String;
        //    22: iconst_2       
        //    23: anewarray       Ljava/lang/Class;
        //    26: dup            
        //    27: iconst_0       
        //    28: ldc             Ljava/lang/String;.class
        //    30: aastore        
        //    31: dup            
        //    32: iconst_1       
        //    33: ldc             Ljava/lang/String;.class
        //    35: aastore        
        //    36: invokevirtual   java/lang/Class.getMethod:(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
        //    39: astore_2       
        //    40: aload_2        
        //    41: astore_1       
        //    42: aload_1        
        //    43: ifnull          73
        //    46: aload_1        
        //    47: aconst_null    
        //    48: iconst_2       
        //    49: anewarray       Ljava/lang/Object;
        //    52: dup            
        //    53: iconst_0       
        //    54: aload_0        
        //    55: invokevirtual   com/unity3d/services/core/log/DeviceLogEntry.getLogLevel:()Lcom/unity3d/services/core/log/DeviceLogLevel;
        //    58: invokevirtual   com/unity3d/services/core/log/DeviceLogLevel.getLogTag:()Ljava/lang/String;
        //    61: aastore        
        //    62: dup            
        //    63: iconst_1       
        //    64: aload_0        
        //    65: invokevirtual   com/unity3d/services/core/log/DeviceLogEntry.getParsedMessage:()Ljava/lang/String;
        //    68: aastore        
        //    69: invokevirtual   java/lang/reflect/Method.invoke:(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
        //    72: pop            
        //    73: return         
        //    74: astore_2       
        //    75: ldc             "UnityAds"
        //    77: ldc             "Writing to log failed!"
        //    79: aload_2        
        //    80: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    83: pop            
        //    84: goto            42
        //    87: astore_0       
        //    88: ldc             "UnityAds"
        //    90: ldc             "Writing to log failed!"
        //    92: aload_0        
        //    93: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
        //    96: pop            
        //    97: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  13     40     74     87     Ljava/lang/Exception;
        //  46     73     87     98     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0073:
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
    
    public enum UnityAdsLogLevel
    {
        DEBUG, 
        ERROR, 
        INFO, 
        WARNING;
    }
}
