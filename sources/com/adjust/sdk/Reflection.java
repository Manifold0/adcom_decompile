package com.adjust.sdk;

import android.content.Context;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import com.adjust.sdk.plugin.Plugin;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

public class Reflection {
    public static Object getVMRuntimeObject() {
        Object obj = null;
        try {
            obj = invokeStaticMethod("dalvik.system.VMRuntime", "getRuntime", null, new Object[0]);
        } catch (Throwable th) {
        }
        return obj;
    }

    public static String getVmInstructionSet() {
        try {
            return (String) invokeInstanceMethod(getVMRuntimeObject(), "vmInstructionSet", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getPlayAdId(Context context) {
        try {
            return (String) invokeInstanceMethod(getAdvertisingInfoObject(context), "getId", null, new Object[0]);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Boolean isPlayTrackingEnabled(Context context) {
        boolean z = false;
        Boolean bool = null;
        try {
            Boolean isLimitedTrackingEnabled = (Boolean) invokeInstanceMethod(getAdvertisingInfoObject(context), RequestParameters.isLAT, null, new Object[0]);
            if (isLimitedTrackingEnabled != null) {
                if (!isLimitedTrackingEnabled.booleanValue()) {
                    z = true;
                }
                bool = Boolean.valueOf(z);
            }
        } catch (Throwable th) {
        }
        return bool;
    }

    public static String getMacAddress(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.MacAddressUtil", "getMacAddress", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getAndroidId(Context context) {
        try {
            return (String) invokeStaticMethod("com.adjust.sdk.plugin.AndroidIdUtil", "getAndroidId", new Class[]{Context.class}, context);
        } catch (Throwable th) {
            return null;
        }
    }

    public static String getImei(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getImei", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getImei(TelephonyManager telephonyManager, int index) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getImei", new Class[]{Integer.TYPE}, Integer.valueOf(index));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMeid(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getMeid", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getMeid(TelephonyManager telephonyManager, int index) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getMeid", new Class[]{Integer.TYPE}, Integer.valueOf(index));
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTelephonyId(TelephonyManager telephonyManager) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getDeviceId", null, new Object[0]);
        } catch (Exception e) {
            return null;
        }
    }

    public static String getTelephonyId(TelephonyManager telephonyManager, int index) {
        try {
            return (String) invokeInstanceMethod(telephonyManager, "getDeviceId", new Class[]{Integer.TYPE}, Integer.valueOf(index));
        } catch (Exception e) {
            return null;
        }
    }

    private static Object getAdvertisingInfoObject(Context context) throws Exception {
        return invokeStaticMethod("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[]{Context.class}, context);
    }

    public static String[] getSupportedAbis() {
        String[] supportedAbis = null;
        try {
            return (String[]) readField("android.os.Build", "SUPPORTED_ABIS");
        } catch (Throwable th) {
            return supportedAbis;
        }
    }

    public static String getCpuAbi() {
        String cpuAbi = null;
        try {
            return (String) readField("android.os.Build", "CPU_ABI");
        } catch (Throwable th) {
            return cpuAbi;
        }
    }

    public static Locale getLocaleFromLocaleList(Configuration configuration) {
        Locale locale = null;
        try {
            Object localesList = invokeInstanceMethod(configuration, "getLocales", null, new Object[0]);
            if (localesList == null) {
                return null;
            }
            locale = (Locale) invokeInstanceMethod(localesList, "get", new Class[]{Integer.TYPE}, Integer.valueOf(0));
            return locale;
        } catch (Throwable th) {
        }
    }

    public static Locale getLocaleFromField(Configuration configuration) {
        Locale locale = null;
        try {
            return (Locale) readField("android.content.res.Configuration", "locale", configuration);
        } catch (Throwable th) {
            return locale;
        }
    }

    public static Class forName(String className) {
        try {
            return Class.forName(className);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createDefaultInstance(String className) {
        return createDefaultInstance(forName(className));
    }

    public static Object createDefaultInstance(Class classObject) {
        try {
            return classObject.newInstance();
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object createInstance(String className, Class[] cArgs, Object... args) {
        try {
            return Class.forName(className).getConstructor(cArgs).newInstance(args);
        } catch (Throwable th) {
            return null;
        }
    }

    public static Object invokeStaticMethod(String className, String methodName, Class[] cArgs, Object... args) throws Exception {
        return invokeMethod(Class.forName(className), methodName, null, cArgs, args);
    }

    public static Object invokeInstanceMethod(Object instance, String methodName, Class[] cArgs, Object... args) throws Exception {
        return invokeMethod(instance.getClass(), methodName, instance, cArgs, args);
    }

    public static Object invokeMethod(Class classObject, String methodName, Object instance, Class[] cArgs, Object... args) throws Exception {
        Method methodObject = classObject.getMethod(methodName, cArgs);
        if (methodObject == null) {
            return null;
        }
        return methodObject.invoke(instance, args);
    }

    public static Object readField(String className, String fieldName) throws Exception {
        return readField(className, fieldName, null);
    }

    public static Object readField(String className, String fieldName, Object instance) throws Exception {
        Class classObject = forName(className);
        if (classObject == null) {
            return null;
        }
        Field fieldObject = classObject.getField(fieldName);
        if (fieldObject != null) {
            return fieldObject.get(instance);
        }
        return null;
    }

    public static Map<String, String> getPluginKeys(Context context) {
        Map<String, String> pluginKeys = new HashMap();
        for (Plugin plugin : getPlugins()) {
            Entry<String, String> pluginEntry = plugin.getParameter(context);
            if (pluginEntry != null) {
                pluginKeys.put(pluginEntry.getKey(), pluginEntry.getValue());
            }
        }
        if (pluginKeys.size() == 0) {
            return null;
        }
        return pluginKeys;
    }

    private static List<Plugin> getPlugins() {
        List<Plugin> plugins = new ArrayList(Constants.PLUGINS.size());
        for (String pluginName : Constants.PLUGINS) {
            Object pluginObject = createDefaultInstance(pluginName);
            if (pluginObject != null && (pluginObject instanceof Plugin)) {
                plugins.add((Plugin) pluginObject);
            }
        }
        return plugins;
    }
}
