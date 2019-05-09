package com.unity3d.services.core.api;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import com.unity3d.services.ads.adunit.AdUnitError;
import com.unity3d.services.ads.api.AdUnit;
import com.unity3d.services.core.device.DeviceError;
import com.unity3d.services.core.properties.ClientProperties;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;

public class Permissions {
    @WebViewExposed
    public static void getPermissions(WebViewCallback callback) {
        if (ClientProperties.getApplicationContext() == null) {
            callback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[0]);
            return;
        }
        try {
            JSONArray retArray = new JSONArray();
            Context context = ClientProperties.getApplicationContext();
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
            if (info.requestedPermissions != null) {
                for (String p : info.requestedPermissions) {
                    retArray.put(p);
                }
                callback.invoke(retArray);
                return;
            }
            callback.error(PermissionsError.NO_REQUESTED_PERMISSIONS, new Object[0]);
        } catch (Exception e) {
            callback.error(PermissionsError.COULDNT_GET_PERMISSIONS, e.getMessage());
        }
    }

    @WebViewExposed
    public static void checkPermission(String permission, WebViewCallback callback) {
        if (ClientProperties.getApplicationContext() == null) {
            callback.error(DeviceError.APPLICATION_CONTEXT_NULL, new Object[0]);
            return;
        }
        try {
            Context context = ClientProperties.getApplicationContext();
            int granted = context.getPackageManager().checkPermission(permission, context.getPackageName());
            callback.invoke(Integer.valueOf(granted));
        } catch (Exception e) {
            callback.error(PermissionsError.ERROR_CHECKING_PERMISSION, e.getMessage());
        }
    }

    @WebViewExposed
    @TargetApi(23)
    public static void requestPermissions(JSONArray permissions, Integer requestCode, WebViewCallback callback) {
        if (AdUnit.getAdUnitActivity() == null) {
            callback.error(AdUnitError.ADUNIT_NULL, new Object[0]);
        } else if (permissions == null || permissions.length() < 1) {
            callback.error(PermissionsError.NO_REQUESTED_PERMISSIONS, new Object[0]);
        } else {
            try {
                List<String> permissionsList = new ArrayList();
                for (int i = 0; i < permissions.length(); i++) {
                    permissionsList.add(permissions.getString(i));
                }
                AdUnit.getAdUnitActivity().requestPermissions((String[]) permissionsList.toArray(new String[permissionsList.size()]), requestCode.intValue());
                callback.invoke(new Object[0]);
            } catch (Exception e) {
                callback.error(PermissionsError.ERROR_REQUESTING_PERMISSIONS, e.getMessage());
            }
        }
    }
}
