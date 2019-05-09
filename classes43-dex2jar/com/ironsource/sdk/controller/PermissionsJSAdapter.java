// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.controller;

import com.ironsource.environment.ApplicationContext;
import com.ironsource.sdk.data.SSAObj;
import com.ironsource.sdk.utils.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import android.content.Context;

public class PermissionsJSAdapter
{
    private static final String TAG;
    private static final String fail = "fail";
    private static final String getPermissions = "getPermissions";
    private static final String isPermissionGranted = "isPermissionGranted";
    private static final String permissionsFunction = "functionName";
    private static final String permissionsGetPermissionsParam = "permissions";
    private static final String permissionsParameters = "functionParams";
    private static final String permissionsisPermissionGrantedParam = "permission";
    private static final String permissionsisPermissionGrantedStatus = "status";
    private static final String success = "success";
    private static final String unhandled = "unhandledPermission";
    private Context mContext;
    
    static {
        TAG = PermissionsJSAdapter.class.getSimpleName();
    }
    
    public PermissionsJSAdapter(final Context mContext) {
        this.mContext = mContext;
    }
    
    private FunctionCall fetchFunctionCall(final String s) throws JSONException {
        final JSONObject jsonObject = new JSONObject(s);
        final FunctionCall functionCall = new FunctionCall();
        functionCall.name = jsonObject.optString("functionName");
        functionCall.params = jsonObject.optJSONObject("functionParams");
        functionCall.successCallback = jsonObject.optString("success");
        functionCall.failureCallback = jsonObject.optString("fail");
        return functionCall;
    }
    
    void call(final String s, final IronSourceWebView.JSInterface.JSCallbackTask jsCallbackTask) throws Exception {
        final FunctionCall fetchFunctionCall = this.fetchFunctionCall(s);
        if ("getPermissions".equals(fetchFunctionCall.name)) {
            this.getPermissions(fetchFunctionCall.params, fetchFunctionCall, jsCallbackTask);
            return;
        }
        if ("isPermissionGranted".equals(fetchFunctionCall.name)) {
            this.isPermissionGranted(fetchFunctionCall.params, fetchFunctionCall, jsCallbackTask);
            return;
        }
        Logger.i(PermissionsJSAdapter.TAG, "PermissionsJSAdapter unhandled API request " + s);
    }
    
    public void getPermissions(final JSONObject jsonObject, final FunctionCall functionCall, final IronSourceWebView.JSInterface.JSCallbackTask jsCallbackTask) {
        final SSAObj ssaObj = new SSAObj();
        try {
            ssaObj.put("permissions", ApplicationContext.getPermissions(this.mContext, jsonObject.getJSONArray("permissions")));
            jsCallbackTask.sendMessage(true, functionCall.successCallback, ssaObj);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            Logger.i(PermissionsJSAdapter.TAG, "PermissionsJSAdapter getPermissions JSON Exception when getting permissions parameter " + ex.getMessage());
            if (ssaObj != null) {
                ssaObj.put("errMsg", ex.getMessage());
            }
            jsCallbackTask.sendMessage(false, functionCall.failureCallback, ssaObj);
        }
    }
    
    public void isPermissionGranted(final JSONObject jsonObject, final FunctionCall functionCall, final IronSourceWebView.JSInterface.JSCallbackTask jsCallbackTask) {
        final SSAObj ssaObj = new SSAObj();
        try {
            final String string = jsonObject.getString("permission");
            ssaObj.put("permission", string);
            if (ApplicationContext.isValidPermission(this.mContext, string)) {
                ssaObj.put("status", String.valueOf(ApplicationContext.isPermissionGranted(this.mContext, string)));
                jsCallbackTask.sendMessage(true, functionCall.successCallback, ssaObj);
                return;
            }
            ssaObj.put("status", "unhandledPermission");
            jsCallbackTask.sendMessage(false, functionCall.failureCallback, ssaObj);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            if (ssaObj != null) {
                ssaObj.put("errMsg", ex.getMessage());
            }
            jsCallbackTask.sendMessage(false, functionCall.failureCallback, ssaObj);
        }
    }
    
    private static class FunctionCall
    {
        String failureCallback;
        String name;
        JSONObject params;
        String successCallback;
    }
}
