// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview.bridge;

import java.lang.reflect.InvocationTargetException;
import com.unity3d.services.core.webview.WebViewApp;
import org.json.JSONException;
import java.util.Arrays;
import java.lang.reflect.Method;
import java.util.HashMap;

public class WebViewBridge
{
    private static HashMap<String, HashMap<String, HashMap<Integer, Method>>> _classTable;
    
    private static Method findMethod(final String s, final String s2, final Object[] array) throws JSONException, NoSuchMethodException {
        if (!WebViewBridge._classTable.containsKey(s)) {
            throw new NoSuchMethodException();
        }
        final HashMap<String, HashMap<Integer, Method>> hashMap = WebViewBridge._classTable.get(s);
        if (!hashMap.containsKey(s2)) {
            throw new NoSuchMethodException();
        }
        return hashMap.get(s2).get(Arrays.deepHashCode(getTypes(array)));
    }
    
    private static Class<?>[] getTypes(final Object[] array) throws JSONException {
        Class[] array2;
        if (array == null) {
            array2 = new Class[] { null };
        }
        else {
            array2 = new Class[array.length + 1];
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                array2[i] = array[i].getClass();
            }
        }
        array2[array2.length - 1] = WebViewCallback.class;
        return (Class<?>[])array2;
    }
    
    private static Object[] getValues(Object[] array, final WebViewCallback webViewCallback) throws JSONException {
        int n = 1;
        Object[] array2 = null;
        Label_0020: {
            if (array != null) {
                final int length = array.length;
                if (webViewCallback == null) {
                    n = 0;
                }
                array2 = new Object[n + length];
                break Label_0020;
            }
            if (webViewCallback != null) {
                array2 = new Object[] { null };
                break Label_0020;
            }
            array = null;
            return array;
        }
        if (array != null) {
            System.arraycopy(array, 0, array2, 0, array.length);
        }
        array = array2;
        if (webViewCallback != null) {
            array2[array2.length - 1] = webViewCallback;
            return array2;
        }
        return array;
    }
    
    public static void handleCallback(String callback, final String s, final Object[] array) throws Exception {
        callback = (JSONException)WebViewApp.getCurrentApp().getCallback((String)callback);
        try {
            ((NativeCallback)callback).invoke(s, getValues(array, null));
        }
        catch (IllegalAccessException ex) {}
        catch (JSONException callback) {
            goto Label_0020;
        }
        catch (IllegalArgumentException callback) {
            goto Label_0020;
        }
        catch (InvocationTargetException callback) {
            goto Label_0020;
        }
    }
    
    public static void handleInvocation(final String s, final String s2, final Object[] array, final WebViewCallback webViewCallback) throws Exception {
        Label_0023: {
            Method method = null;
            try {
                final Method method2;
                method = (method2 = findMethod(s, s2, array));
                final Object o = null;
                final Object[] array2 = array;
                final WebViewCallback webViewCallback2 = webViewCallback;
                final Object[] array3 = getValues(array2, webViewCallback2);
                method2.invoke(o, array3);
                return;
            }
            catch (JSONException ex) {}
            catch (NoSuchMethodException method) {
                break Label_0023;
            }
            try {
                final Method method2 = method;
                final Object o = null;
                final Object[] array2 = array;
                final WebViewCallback webViewCallback2 = webViewCallback;
                final Object[] array3 = getValues(array2, webViewCallback2);
                method2.invoke(o, array3);
                return;
                webViewCallback.error(WebViewBridgeError.METHOD_NOT_FOUND, s, s2, array);
                throw method;
            }
            catch (IllegalAccessException ex2) {}
            catch (JSONException method) {
                goto Label_0051;
            }
            catch (IllegalArgumentException method) {
                goto Label_0051;
            }
            catch (InvocationTargetException method) {
                goto Label_0051;
            }
        }
    }
    
    public static void setClassTable(final Class[] array) {
        if (array != null) {
            WebViewBridge._classTable = new HashMap<String, HashMap<String, HashMap<Integer, Method>>>();
            for (int length = array.length, i = 0; i < length; ++i) {
                final Class clazz = array[i];
                if (clazz != null && (clazz.getPackage().getName().startsWith("com.unity3d.services") || clazz.getPackage().getName().startsWith("com.unity3d.ads.test"))) {
                    final HashMap<String, HashMap<Integer, Method>> hashMap = new HashMap<String, HashMap<Integer, Method>>();
                    final Method[] methods = clazz.getMethods();
                    for (int length2 = methods.length, j = 0; j < length2; ++j) {
                        final Method method = methods[j];
                        if (method.getAnnotation(WebViewExposed.class) != null) {
                            final String name = method.getName();
                            HashMap<Integer, Method> hashMap2;
                            if (hashMap.containsKey(name)) {
                                hashMap2 = hashMap.get(name);
                            }
                            else {
                                hashMap2 = new HashMap<Integer, Method>();
                            }
                            hashMap2.put((Object)Arrays.deepHashCode(method.getParameterTypes()), (Object)method);
                            hashMap.put(name, hashMap2);
                        }
                    }
                    WebViewBridge._classTable.put(clazz.getName(), hashMap);
                }
            }
        }
    }
}
