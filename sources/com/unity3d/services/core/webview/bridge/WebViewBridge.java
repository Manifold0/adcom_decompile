package com.unity3d.services.core.webview.bridge;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import org.json.JSONException;

public class WebViewBridge {
    private static HashMap<String, HashMap<String, HashMap<Integer, Method>>> _classTable;

    public static void setClassTable(Class[] apiClassList) {
        if (apiClassList != null) {
            _classTable = new HashMap();
            for (Class cls : apiClassList) {
                if (cls != null && (cls.getPackage().getName().startsWith("com.unity3d.services") || cls.getPackage().getName().startsWith("com.unity3d.ads.test"))) {
                    HashMap<String, HashMap<Integer, Method>> methodTable = new HashMap();
                    for (Method method : cls.getMethods()) {
                        if (method.getAnnotation(WebViewExposed.class) != null) {
                            HashMap<Integer, Method> overrideTable;
                            String methodName = method.getName();
                            if (methodTable.containsKey(methodName)) {
                                overrideTable = (HashMap) methodTable.get(methodName);
                            } else {
                                overrideTable = new HashMap();
                            }
                            overrideTable.put(Integer.valueOf(Arrays.deepHashCode(method.getParameterTypes())), method);
                            methodTable.put(methodName, overrideTable);
                        }
                    }
                    _classTable.put(cls.getName(), methodTable);
                }
            }
        }
    }

    private static Method findMethod(String className, String methodName, Object[] parameters) throws JSONException, NoSuchMethodException {
        if (_classTable.containsKey(className)) {
            HashMap<String, HashMap<Integer, Method>> methodTable = (HashMap) _classTable.get(className);
            if (methodTable.containsKey(methodName)) {
                return (Method) ((HashMap) methodTable.get(methodName)).get(Integer.valueOf(Arrays.deepHashCode(getTypes(parameters))));
            }
            throw new NoSuchMethodException();
        }
        throw new NoSuchMethodException();
    }

    private static Class<?>[] getTypes(Object[] parameters) throws JSONException {
        Class<?>[] types;
        if (parameters == null) {
            types = new Class[1];
        } else {
            types = new Class[(parameters.length + 1)];
        }
        if (parameters != null) {
            for (int i = 0; i < parameters.length; i++) {
                types[i] = parameters[i].getClass();
            }
        }
        types[types.length - 1] = WebViewCallback.class;
        return types;
    }

    private static Object[] getValues(Object[] parameters, WebViewCallback callback) throws JSONException {
        Object[] values;
        int i = 1;
        if (parameters != null) {
            int length = parameters.length;
            if (callback == null) {
                i = 0;
            }
            values = new Object[(i + length)];
        } else if (callback == null) {
            return null;
        } else {
            values = new Object[1];
        }
        if (parameters != null) {
            System.arraycopy(parameters, 0, values, 0, parameters.length);
        }
        if (callback == null) {
            return values;
        }
        values[values.length - 1] = callback;
        return values;
    }

    public static void handleInvocation(String className, String methodName, Object[] parameters, WebViewCallback callback) throws Exception {
        Exception e;
        try {
            try {
                findMethod(className, methodName, parameters).invoke(null, getValues(parameters, callback));
            } catch (JSONException e2) {
                e = e2;
                callback.error(WebViewBridgeError.INVOCATION_FAILED, className, methodName, parameters, e.getMessage());
                throw e;
            } catch (InvocationTargetException e3) {
                e = e3;
                callback.error(WebViewBridgeError.INVOCATION_FAILED, className, methodName, parameters, e.getMessage());
                throw e;
            } catch (IllegalAccessException e4) {
                e = e4;
                callback.error(WebViewBridgeError.INVOCATION_FAILED, className, methodName, parameters, e.getMessage());
                throw e;
            } catch (IllegalArgumentException e5) {
                e = e5;
                callback.error(WebViewBridgeError.INVOCATION_FAILED, className, methodName, parameters, e.getMessage());
                throw e;
            }
        } catch (JSONException e6) {
            e = e6;
            callback.error(WebViewBridgeError.METHOD_NOT_FOUND, className, methodName, parameters);
            throw e;
        } catch (NoSuchMethodException e7) {
            e = e7;
            callback.error(WebViewBridgeError.METHOD_NOT_FOUND, className, methodName, parameters);
            throw e;
        }
    }

    public static void handleCallback(String callbackId, String callbackStatus, Object[] parameters) throws Exception {
        Exception e;
        try {
            WebViewApp.getCurrentApp().getCallback(callbackId).invoke(callbackStatus, getValues(parameters, null));
        } catch (InvocationTargetException e2) {
            e = e2;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (IllegalAccessException e3) {
            e = e3;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (JSONException e4) {
            e = e4;
            DeviceLog.error("Error while invoking method");
            throw e;
        } catch (IllegalArgumentException e5) {
            e = e5;
            DeviceLog.error("Error while invoking method");
            throw e;
        }
    }
}
