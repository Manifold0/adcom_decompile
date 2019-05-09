package com.unity3d.services.core.webview.bridge;

import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class Invocation {
    private static AtomicInteger _idCount = new AtomicInteger(0);
    private static Map<Integer, Invocation> _invocationSets;
    private int _invocationId = _idCount.getAndIncrement();
    private ArrayList<ArrayList<Object>> _invocations;
    private ArrayList<ArrayList<Object>> _responses;

    public Invocation() {
        if (_invocationSets == null) {
            _invocationSets = new HashMap();
        }
        _invocationSets.put(Integer.valueOf(this._invocationId), this);
    }

    public void addInvocation(String className, String methodName, Object[] parameters, WebViewCallback callback) {
        if (this._invocations == null) {
            this._invocations = new ArrayList();
        }
        ArrayList<Object> invocation = new ArrayList();
        invocation.add(className);
        invocation.add(methodName);
        invocation.add(parameters);
        invocation.add(callback);
        this._invocations.add(invocation);
    }

    public boolean nextInvocation() {
        if (this._invocations == null || this._invocations.size() <= 0) {
            return false;
        }
        ArrayList<Object> invocation = (ArrayList) this._invocations.remove(0);
        try {
            WebViewBridge.handleInvocation((String) invocation.get(0), (String) invocation.get(1), (Object[]) invocation.get(2), (WebViewCallback) invocation.get(3));
        } catch (Exception e) {
            DeviceLog.exception("Error handling invocation", e);
        }
        return true;
    }

    public void setInvocationResponse(CallbackStatus status, Enum error, Object... params) {
        if (this._responses == null) {
            this._responses = new ArrayList();
        }
        ArrayList<Object> response = new ArrayList();
        response.add(status);
        response.add(error);
        response.add(params);
        this._responses.add(response);
    }

    public void sendInvocationCallback() {
        _invocationSets.remove(Integer.valueOf(getId()));
        WebViewApp.getCurrentApp().invokeCallback(this);
    }

    public int getId() {
        return this._invocationId;
    }

    public ArrayList<ArrayList<Object>> getResponses() {
        return this._responses;
    }

    public static synchronized Invocation getInvocationById(int id) {
        Invocation invocation;
        synchronized (Invocation.class) {
            if (_invocationSets == null || !_invocationSets.containsKey(Integer.valueOf(id))) {
                invocation = null;
            } else {
                invocation = (Invocation) _invocationSets.get(Integer.valueOf(id));
            }
        }
        return invocation;
    }
}
