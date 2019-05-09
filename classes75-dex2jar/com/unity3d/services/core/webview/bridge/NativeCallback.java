// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview.bridge;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.Locale;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class NativeCallback
{
    private static AtomicInteger _callbackCount;
    private Method _callback;
    private String _id;
    
    static {
        NativeCallback._callbackCount = new AtomicInteger(0);
    }
    
    public NativeCallback(final Method callback) {
        this._callback = callback;
        this._id = this._callback.getName().toUpperCase(Locale.US) + "_" + NativeCallback._callbackCount.getAndIncrement();
    }
    
    public String getId() {
        return this._id;
    }
    
    public void invoke(final String s, final Object... array) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException {
        while (true) {
            while (true) {
                CallbackStatus value;
                try {
                    value = CallbackStatus.valueOf(s);
                    if (array == null) {
                        final Object[] array2 = { value };
                        this._callback.invoke(null, array2);
                        WebViewApp.getCurrentApp().removeCallback(this);
                        return;
                    }
                }
                catch (Exception ex) {
                    DeviceLog.error("Illegal status");
                    WebViewApp.getCurrentApp().removeCallback(this);
                    throw ex;
                }
                final ArrayList<CallbackStatus> list = new ArrayList<CallbackStatus>((Collection<? extends CallbackStatus>)Arrays.asList(array));
                list.add(0, value);
                final Object[] array2 = list.toArray();
                continue;
            }
        }
    }
}
