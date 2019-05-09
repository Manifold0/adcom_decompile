package com.ironsource.environment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class ApplicationContext {
    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    public static int getAppOrientation(Activity a) {
        return a.getRequestedOrientation();
    }

    public static String getDiskCacheDirPath(Context context) {
        File internalFile = context.getCacheDir();
        if (internalFile != null) {
            return internalFile.getPath();
        }
        return null;
    }

    public static boolean isValidPermission(Context context, String permissionToCheck) {
        boolean isPermissionValid = false;
        String permission = "";
        if (!TextUtils.isEmpty(permissionToCheck)) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                if (packageInfo.requestedPermissions != null) {
                    for (int i = 0; i < packageInfo.requestedPermissions.length && !isPermissionValid; i++) {
                        isPermissionValid = permissionToCheck.equals(packageInfo.requestedPermissions[i]);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return isPermissionValid;
    }

    public static boolean isPermissionGranted(Context context, String permission) {
        return context.checkCallingOrSelfPermission(permission) == 0;
    }

    public static JSONObject getPermissions(Context context, JSONArray permissionsFromController) {
        String GRANTED = "Granted";
        String REJECTED = "Rejected";
        String NOT_FOUND = "notFoundInManifest";
        JSONObject allPermissions = new JSONObject();
        if (VERSION.SDK_INT >= 16) {
            try {
                PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                int index;
                String permissionStatus;
                if (permissionsFromController.length() == 0) {
                    for (index = 0; index < packageInfo.requestedPermissions.length; index++) {
                        if ((packageInfo.requestedPermissionsFlags[index] & 2) != 0) {
                            permissionStatus = GRANTED;
                        } else {
                            permissionStatus = REJECTED;
                        }
                        allPermissions.put(packageInfo.requestedPermissions[index], permissionStatus);
                    }
                } else {
                    List<String> permissionsList = Arrays.asList(packageInfo.requestedPermissions);
                    for (index = 0; index < permissionsFromController.length(); index++) {
                        String permission = permissionsFromController.getString(index);
                        int indexOfPermission = permissionsList.indexOf(permission);
                        if (indexOfPermission != -1) {
                            if ((packageInfo.requestedPermissionsFlags[indexOfPermission] & 2) != 0) {
                                permissionStatus = GRANTED;
                            } else {
                                permissionStatus = REJECTED;
                            }
                            allPermissions.put(permission, permissionStatus);
                        } else {
                            allPermissions.put(permission, NOT_FOUND);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return allPermissions;
    }

    static PackageInfo getAppPackageInfo(Context context) throws NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getPackageName(context), 0);
    }

    public static long getFirstInstallTime(Context context) {
        try {
            return getAppPackageInfo(context).firstInstallTime;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static long getLastUpdateTime(Context context) {
        try {
            return getAppPackageInfo(context).lastUpdateTime;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static String getApplicationVersionName(Context context) {
        try {
            return getAppPackageInfo(context).versionName;
        } catch (NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static String getPublisherApplicationVersion(Context context, String packageName) {
        String result = "";
        try {
            return context.getPackageManager().getPackageInfo(packageName, 0).versionName;
        } catch (Exception e) {
            return result;
        }
    }

    public static String getInstallerPackageName(Context context) {
        String installer = null;
        try {
            installer = context.getPackageManager().getInstallerPackageName(context.getPackageName());
        } catch (Throwable th) {
        }
        return TextUtils.isEmpty(installer) ? "" : installer;
    }
}
