// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.webview.bridge;

import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.log.DeviceLog;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Invocation
{
    private static AtomicInteger _idCount;
    private static Map<Integer, Invocation> _invocationSets;
    private int _invocationId;
    private ArrayList<ArrayList<Object>> _invocations;
    private ArrayList<ArrayList<Object>> _responses;
    
    static {
        Invocation._idCount = new AtomicInteger(0);
    }
    
    public Invocation() {
        this._invocationId = Invocation._idCount.getAndIncrement();
        if (Invocation._invocationSets == null) {
            Invocation._invocationSets = new HashMap<Integer, Invocation>();
        }
        Invocation._invocationSets.put(this._invocationId, this);
    }
    
    public static Invocation getInvocationById(final int n) {
        synchronized (Invocation.class) {
            Invocation invocation;
            if (Invocation._invocationSets != null && Invocation._invocationSets.containsKey(n)) {
                invocation = Invocation._invocationSets.get(n);
            }
            else {
                invocation = null;
            }
            return invocation;
        }
    }
    
    public void addInvocation(final String s, final String s2, final Object[] array, final WebViewCallback webViewCallback) {
        if (this._invocations == null) {
            this._invocations = new ArrayList<ArrayList<Object>>();
        }
        final ArrayList<WebViewCallback> list = new ArrayList<WebViewCallback>();
        list.add((WebViewCallback)s);
        list.add((WebViewCallback)s2);
        list.add((WebViewCallback)array);
        list.add(webViewCallback);
        this._invocations.add((ArrayList<Object>)list);
    }
    
    public int getId() {
        return this._invocationId;
    }
    
    public ArrayList<ArrayList<Object>> getResponses() {
        return this._responses;
    }
    
    public boolean nextInvocation() {
        boolean b = false;
        if (this._invocations == null) {
            return b;
        }
        b = b;
        if (this._invocations.size() <= 0) {
            return b;
        }
        final ArrayList<Object> list = this._invocations.remove(0);
        try {
            WebViewBridge.handleInvocation((String)list.get(0), (String)list.get(1), (Object[])list.get(2), (WebViewCallback)list.get(3));
            b = true;
            return b;
        }
        catch (Exception ex) {
            DeviceLog.exception("Error handling invocation", ex);
            return true;
        }
    }
    
    public void sendInvocationCallback() {
        Invocation._invocationSets.remove(this.getId());
        WebViewApp.getCurrentApp().invokeCallback(this);
    }
    
    public void setInvocationResponse(final CallbackStatus callbackStatus, final Enum enum1, final Object... array) {
        if (this._responses == null) {
            this._responses = new ArrayList<ArrayList<Object>>();
        }
        final ArrayList<Object[]> list = new ArrayList<Object[]>();
        list.add((Object[])(Object)callbackStatus);
        list.add((Object[])(Object)enum1);
        list.add(array);
        this._responses.add((ArrayList<Object>)list);
    }
}
