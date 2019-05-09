// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.api;

import android.annotation.TargetApi;
import java.util.ArrayList;
import com.unity3d.services.ads.adunit.AdUnitError;
import com.unity3d.services.ads.api.AdUnit;
import android.content.pm.PackageInfo;
import org.json.JSONArray;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import android.content.Context;
import com.unity3d.services.core.device.DeviceError;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;

public class Permissions
{
    @WebViewExposed
    public static void checkPermission(final String s, final WebViewCallback webViewCallback) {
        if (ClientProperties.getApplicationContext() == null) {
            webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[0]);
            return;
        }
        try {
            final Context applicationContext = ClientProperties.getApplicationContext();
            webViewCallback.invoke(applicationContext.getPackageManager().checkPermission(s, applicationContext.getPackageName()));
        }
        catch (Exception ex) {
            webViewCallback.error(PermissionsError.ERROR_CHECKING_PERMISSION, ex.getMessage());
        }
    }
    
    @WebViewExposed
    public static void getPermissions(final WebViewCallback webViewCallback) {
        if (ClientProperties.getApplicationContext() == null) {
            webViewCallback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[0]);
            return;
        }
        try {
            final JSONArray jsonArray = new JSONArray();
            final Context applicationContext = ClientProperties.getApplicationContext();
            final PackageInfo packageInfo = applicationContext.getPackageManager().getPackageInfo(applicationContext.getPackageName(), 4096);
            if (packageInfo.requestedPermissions != null) {
                final String[] requestedPermissions = packageInfo.requestedPermissions;
                for (int length = requestedPermissions.length, i = 0; i < length; ++i) {
                    jsonArray.put((Object)requestedPermissions[i]);
                }
                webViewCallback.invoke(jsonArray);
                return;
            }
        }
        catch (Exception ex) {
            webViewCallback.error(PermissionsError.COULDNT_GET_PERMISSIONS, ex.getMessage());
            return;
        }
        webViewCallback.error(PermissionsError.NO_REQUESTED_PERMISSIONS, new Object[0]);
    }
    
    @WebViewExposed
    @TargetApi(23)
    public static void requestPermissions(final JSONArray jsonArray, final Integer n, final WebViewCallback webViewCallback) {
        if (AdUnit.getAdUnitActivity() == null) {
            webViewCallback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
            return;
        }
        if (jsonArray == null || jsonArray.length() < 1) {
            webViewCallback.error(PermissionsError.NO_REQUESTED_PERMISSIONS, new Object[0]);
            return;
        }
        try {
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < jsonArray.length(); ++i) {
                list.add(jsonArray.getString(i));
            }
            AdUnit.getAdUnitActivity().requestPermissions((String[])list.toArray(new String[list.size()]), (int)n);
            webViewCallback.invoke(new Object[0]);
        }
        catch (Exception ex) {
            webViewCallback.error(PermissionsError.ERROR_REQUESTING_PERMISSIONS, ex.getMessage());
        }
    }
}
