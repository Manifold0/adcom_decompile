// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;
import com.adjust.sdk.plugin.Plugin;
import java.util.HashMap;
import java.util.Map;
import java.util.Locale;
import android.content.res.Configuration;
import android.telephony.TelephonyManager;
import android.content.Context;

public class Reflection
{
    public static Object createDefaultInstance(final Class clazz) {
        try {
            return clazz.newInstance();
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Object createDefaultInstance(final String s) {
        return createDefaultInstance(forName(s));
    }
    
    public static Object createInstance(final String s, final Class[] array, final Object... array2) {
        try {
            return Class.forName(s).getConstructor((Class<?>[])array).newInstance(array2);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Class forName(final String s) {
        try {
            return Class.forName(s);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    private static Object getAdvertisingInfoObject(final Context context) throws Exception {
        return invokeStaticMethod("com.google.android.gms.ads.identifier.AdvertisingIdClient", "getAdvertisingIdInfo", new Class[] { Context.class }, context);
    }
    
    public static String getAndroidId(final Context context) {
        try {
            return (String)invokeStaticMethod("com.adjust.sdk.plugin.AndroidIdUtil", "getAndroidId", new Class[] { Context.class }, context);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String getCpuAbi() {
        try {
            return (String)readField("android.os.Build", "CPU_ABI");
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String getImei(final TelephonyManager telephonyManager) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getImei", null, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getImei(final TelephonyManager telephonyManager, final int n) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getImei", new Class[] { Integer.TYPE }, n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Locale getLocaleFromField(final Configuration configuration) {
        try {
            return (Locale)readField("android.content.res.Configuration", "locale", configuration);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Locale getLocaleFromLocaleList(final Configuration configuration) {
        final Locale locale = null;
        try {
            final Object invokeInstanceMethod = invokeInstanceMethod(configuration, "getLocales", null, new Object[0]);
            if (invokeInstanceMethod == null) {
                return null;
            }
            return (Locale)invokeInstanceMethod(invokeInstanceMethod, "get", new Class[] { Integer.TYPE }, 0);
        }
        catch (Throwable t) {
            return locale;
        }
    }
    
    public static String getMacAddress(final Context context) {
        try {
            return (String)invokeStaticMethod("com.adjust.sdk.plugin.MacAddressUtil", "getMacAddress", new Class[] { Context.class }, context);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String getMeid(final TelephonyManager telephonyManager) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getMeid", null, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getMeid(final TelephonyManager telephonyManager, final int n) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getMeid", new Class[] { Integer.TYPE }, n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getPlayAdId(final Context context) {
        try {
            return (String)invokeInstanceMethod(getAdvertisingInfoObject(context), "getId", null, new Object[0]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Map<String, String> getPluginKeys(final Context context) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final Iterator<Plugin> iterator = getPlugins().iterator();
        while (iterator.hasNext()) {
            final Map.Entry<String, String> parameter = iterator.next().getParameter(context);
            if (parameter != null) {
                hashMap.put(parameter.getKey(), (V)parameter.getValue());
            }
        }
        HashMap<String, String> hashMap2 = hashMap;
        if (hashMap.size() == 0) {
            hashMap2 = null;
        }
        return hashMap2;
    }
    
    private static List<Plugin> getPlugins() {
        final ArrayList<Plugin> list = new ArrayList<Plugin>(Constants.PLUGINS.size());
        final Iterator<String> iterator = Constants.PLUGINS.iterator();
        while (iterator.hasNext()) {
            final Object defaultInstance = createDefaultInstance(iterator.next());
            if (defaultInstance != null && defaultInstance instanceof Plugin) {
                list.add((Plugin)defaultInstance);
            }
        }
        return list;
    }
    
    public static String[] getSupportedAbis() {
        try {
            return (String[])readField("android.os.Build", "SUPPORTED_ABIS");
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String getTelephonyId(final TelephonyManager telephonyManager) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getDeviceId", null, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static String getTelephonyId(final TelephonyManager telephonyManager, final int n) {
        try {
            return (String)invokeInstanceMethod(telephonyManager, "getDeviceId", new Class[] { Integer.TYPE }, n);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Object getVMRuntimeObject() {
        try {
            return invokeStaticMethod("dalvik.system.VMRuntime", "getRuntime", null, new Object[0]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static String getVmInstructionSet() {
        try {
            return (String)invokeInstanceMethod(getVMRuntimeObject(), "vmInstructionSet", null, new Object[0]);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Object invokeInstanceMethod(final Object o, final String s, final Class[] array, final Object... array2) throws Exception {
        return invokeMethod(o.getClass(), s, o, array, array2);
    }
    
    public static Object invokeMethod(final Class clazz, final String s, final Object o, final Class[] array, final Object... array2) throws Exception {
        final Method method = clazz.getMethod(s, (Class[])array);
        if (method == null) {
            return null;
        }
        return method.invoke(o, array2);
    }
    
    public static Object invokeStaticMethod(final String s, final String s2, final Class[] array, final Object... array2) throws Exception {
        return invokeMethod(Class.forName(s), s2, null, array, array2);
    }
    
    public static Boolean isPlayTrackingEnabled(final Context context) {
        boolean b = false;
        try {
            final Boolean b2 = (Boolean)invokeInstanceMethod(getAdvertisingInfoObject(context), "isLimitAdTrackingEnabled", null, new Object[0]);
            if (b2 == null) {
                return null;
            }
            if (!b2) {
                b = true;
            }
            return b;
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public static Object readField(final String s, final String s2) throws Exception {
        return readField(s, s2, null);
    }
    
    public static Object readField(final String s, final String s2, final Object o) throws Exception {
        final Class forName = forName(s);
        if (forName != null) {
            final Field field = forName.getField(s2);
            if (field != null) {
                return field.get(o);
            }
        }
        return null;
    }
}
