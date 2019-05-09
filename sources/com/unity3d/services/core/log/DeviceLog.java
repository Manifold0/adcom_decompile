package com.unity3d.services.core.log;

import android.util.Log;
import java.io.File;
import java.lang.reflect.Method;
import java.util.HashMap;

public class DeviceLog {
    private static boolean FORCE_DEBUG_LOG = false;
    public static final int LOGLEVEL_DEBUG = 8;
    private static final int LOGLEVEL_ERROR = 1;
    public static final int LOGLEVEL_INFO = 4;
    private static final int LOGLEVEL_WARNING = 2;
    private static boolean LOG_DEBUG = true;
    private static boolean LOG_ERROR = true;
    private static boolean LOG_INFO = true;
    private static boolean LOG_WARNING = true;
    private static final int MAX_DEBUG_MSG_LENGTH = 3072;
    private static final HashMap<UnityAdsLogLevel, DeviceLogLevel> _deviceLogLevel = new HashMap();

    public enum UnityAdsLogLevel {
        INFO,
        DEBUG,
        WARNING,
        ERROR
    }

    static {
        FORCE_DEBUG_LOG = false;
        if (_deviceLogLevel.size() == 0) {
            _deviceLogLevel.put(UnityAdsLogLevel.INFO, new DeviceLogLevel("i"));
            _deviceLogLevel.put(UnityAdsLogLevel.DEBUG, new DeviceLogLevel("d"));
            _deviceLogLevel.put(UnityAdsLogLevel.WARNING, new DeviceLogLevel("w"));
            _deviceLogLevel.put(UnityAdsLogLevel.ERROR, new DeviceLogLevel("e"));
        }
        if (new File("/data/local/tmp/UnityAdsForceDebugMode").exists()) {
            FORCE_DEBUG_LOG = true;
        }
    }

    public static void setLogLevel(int newLevel) {
        if (newLevel >= 8) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = true;
        } else if (newLevel >= 4) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = true;
            LOG_DEBUG = false;
        } else if (newLevel >= 2) {
            LOG_ERROR = true;
            LOG_WARNING = true;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else if (newLevel >= 1) {
            LOG_ERROR = true;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        } else {
            LOG_ERROR = false;
            LOG_WARNING = false;
            LOG_INFO = false;
            LOG_DEBUG = false;
        }
    }

    public static void entered() {
        debug("ENTERED METHOD");
    }

    public static void info(String message) {
        write(UnityAdsLogLevel.INFO, checkMessage(message));
    }

    public static void info(String format, Object... args) {
        info(String.format(format, args));
    }

    public static void debug(String message) {
        if (!LOG_DEBUG && !FORCE_DEBUG_LOG) {
            return;
        }
        if (message.length() > MAX_DEBUG_MSG_LENGTH) {
            debug(message.substring(0, MAX_DEBUG_MSG_LENGTH));
            if (message.length() < 30720) {
                debug(message.substring(MAX_DEBUG_MSG_LENGTH));
                return;
            }
            return;
        }
        write(UnityAdsLogLevel.DEBUG, checkMessage(message));
    }

    public static void debug(String format, Object... args) {
        debug(String.format(format, args));
    }

    public static void warning(String message) {
        write(UnityAdsLogLevel.WARNING, checkMessage(message));
    }

    public static void warning(String format, Object... args) {
        warning(String.format(format, args));
    }

    public static void error(String message) {
        write(UnityAdsLogLevel.ERROR, checkMessage(message));
    }

    public static void exception(String message, Exception exception) {
        String finalMessage = "";
        if (message != null) {
            finalMessage = finalMessage + message;
        }
        if (exception != null) {
            finalMessage = finalMessage + ": " + exception.getMessage();
        }
        if (!(exception == null || exception.getCause() == null)) {
            finalMessage = finalMessage + ": " + exception.getCause().getMessage();
        }
        write(UnityAdsLogLevel.ERROR, finalMessage);
    }

    public static void error(String format, Object... args) {
        error(String.format(format, args));
    }

    private static void write(UnityAdsLogLevel level, String message) {
        boolean logThisMessage = true;
        switch (level) {
            case INFO:
                logThisMessage = LOG_INFO;
                break;
            case DEBUG:
                logThisMessage = LOG_DEBUG;
                break;
            case WARNING:
                logThisMessage = LOG_WARNING;
                break;
            case ERROR:
                logThisMessage = LOG_ERROR;
                break;
        }
        if (FORCE_DEBUG_LOG) {
            logThisMessage = true;
        }
        if (logThisMessage) {
            writeToLog(createLogEntry(level, message));
        }
    }

    private static String checkMessage(String message) {
        if (message == null || message.length() == 0) {
            return "DO NOT USE EMPTY MESSAGES, use DeviceLog.entered() instead";
        }
        return message;
    }

    private static DeviceLogLevel getLogLevel(UnityAdsLogLevel logLevel) {
        return (DeviceLogLevel) _deviceLogLevel.get(logLevel);
    }

    private static DeviceLogEntry createLogEntry(UnityAdsLogLevel level, String message) {
        StackTraceElement[] stack = Thread.currentThread().getStackTrace();
        DeviceLogLevel logLevel = getLogLevel(level);
        if (logLevel == null) {
            return null;
        }
        StackTraceElement e;
        boolean markedIndex = false;
        int callerIndex = 0;
        while (callerIndex < stack.length) {
            e = stack[callerIndex];
            if (e.getClassName().equals(DeviceLog.class.getName())) {
                markedIndex = true;
            }
            if (!e.getClassName().equals(DeviceLog.class.getName()) && markedIndex) {
                break;
            }
            callerIndex++;
        }
        e = null;
        if (callerIndex < stack.length) {
            e = stack[callerIndex];
        }
        if (e != null) {
            return new DeviceLogEntry(logLevel, message, e);
        }
        return null;
    }

    private static void writeToLog(DeviceLogEntry logEntry) {
        Method receivingMethod = null;
        if (logEntry != null && logEntry.getLogLevel() != null) {
            try {
                receivingMethod = Log.class.getMethod(logEntry.getLogLevel().getReceivingMethodName(), new Class[]{String.class, String.class});
            } catch (Exception e) {
                Log.e("UnityAds", "Writing to log failed!", e);
            }
            if (receivingMethod != null) {
                try {
                    receivingMethod.invoke(null, new Object[]{logEntry.getLogLevel().getLogTag(), logEntry.getParsedMessage()});
                } catch (Exception e2) {
                    Log.e("UnityAds", "Writing to log failed!", e2);
                }
            }
        }
    }
}
