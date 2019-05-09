// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.environment;

import java.util.List;
import java.util.Arrays;
import android.os.Build$VERSION;
import org.json.JSONObject;
import org.json.JSONArray;
import android.text.TextUtils;
import java.io.File;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.pm.PackageInfo;
import android.content.Context;
import android.app.Activity;

public class ApplicationContext
{
    public static int getAppOrientation(final Activity activity) {
        return activity.getRequestedOrientation();
    }
    
    static PackageInfo getAppPackageInfo(final Context context) throws PackageManager$NameNotFoundException {
        return context.getPackageManager().getPackageInfo(getPackageName(context), 0);
    }
    
    public static String getApplicationVersionName(final Context context) {
        try {
            return getAppPackageInfo(context).versionName;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public static String getDiskCacheDirPath(final Context context) {
        final String s = null;
        final File cacheDir = context.getCacheDir();
        String path = s;
        if (cacheDir != null) {
            path = cacheDir.getPath();
        }
        return path;
    }
    
    public static long getFirstInstallTime(final Context context) {
        try {
            return getAppPackageInfo(context).firstInstallTime;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }
    
    public static String getInstallerPackageName(final Context context) {
        String s = null;
        while (true) {
            try {
                final String installerPackageName = context.getPackageManager().getInstallerPackageName(context.getPackageName());
                s = installerPackageName;
                if (TextUtils.isEmpty((CharSequence)installerPackageName)) {
                    s = "";
                }
                return s;
            }
            catch (Throwable t) {
                final String installerPackageName = s;
                continue;
            }
            break;
        }
    }
    
    public static long getLastUpdateTime(final Context context) {
        try {
            return getAppPackageInfo(context).lastUpdateTime;
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return -1L;
        }
    }
    
    public static String getPackageName(final Context context) {
        return context.getPackageName();
    }
    
    public static JSONObject getPermissions(final Context context, final JSONArray jsonArray) {
        final JSONObject jsonObject = new JSONObject();
        if (Build$VERSION.SDK_INT >= 16) {
            PackageInfo packageInfo;
            String s;
            List<String> list;
            int n = 0;
            String string;
            int index;
            String s2;
            Label_0100_Outer:Label_0145_Outer:
            while (true) {
                while (true) {
                Label_0191:
                    while (true) {
                    Label_0184:
                        while (true) {
                            Label_0178: {
                                try {
                                    packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                                    if (jsonArray.length() == 0) {
                                        for (int i = 0; i < packageInfo.requestedPermissions.length; ++i) {
                                            if ((packageInfo.requestedPermissionsFlags[i] & 0x2) == 0x0) {
                                                break Label_0178;
                                            }
                                            s = "Granted";
                                            jsonObject.put(packageInfo.requestedPermissions[i], (Object)s);
                                        }
                                    }
                                    else {
                                        list = Arrays.asList(packageInfo.requestedPermissions);
                                        n = 0;
                                        if (n < jsonArray.length()) {
                                            string = jsonArray.getString(n);
                                            index = list.indexOf(string);
                                            if (index == -1) {
                                                jsonObject.put(string, (Object)"notFoundInManifest");
                                                break Label_0184;
                                            }
                                            if ((packageInfo.requestedPermissionsFlags[index] & 0x2) != 0x0) {
                                                s2 = "Granted";
                                                jsonObject.put(string, (Object)s2);
                                                break Label_0184;
                                            }
                                            break Label_0191;
                                        }
                                    }
                                }
                                catch (Exception ex) {
                                    ex.printStackTrace();
                                }
                                break;
                            }
                            s = "Rejected";
                            continue Label_0100_Outer;
                        }
                        ++n;
                        continue Label_0145_Outer;
                    }
                    s2 = "Rejected";
                    continue;
                }
            }
        }
        return jsonObject;
    }
    
    public static String getPublisherApplicationVersion(final Context context, final String s) {
        try {
            return context.getPackageManager().getPackageInfo(s, 0).versionName;
        }
        catch (Exception ex) {
            return "";
        }
    }
    
    public static boolean isPermissionGranted(final Context context, final String s) {
        return context.checkCallingOrSelfPermission(s) == 0;
    }
    
    public static boolean isValidPermission(final Context context, final String s) {
        final boolean b = false;
        final boolean b2 = false;
        boolean equals = false;
        boolean b3 = b2;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            boolean b4 = b;
            try {
                final PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096);
                b4 = b;
                b3 = b2;
                if (packageInfo.requestedPermissions != null) {
                    int n = 0;
                    while (true) {
                        b4 = equals;
                        b3 = equals;
                        if (n >= packageInfo.requestedPermissions.length || (b3 = equals)) {
                            break;
                        }
                        b4 = equals;
                        equals = s.equals(packageInfo.requestedPermissions[n]);
                        ++n;
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                b3 = b4;
            }
        }
        return b3;
    }
}
