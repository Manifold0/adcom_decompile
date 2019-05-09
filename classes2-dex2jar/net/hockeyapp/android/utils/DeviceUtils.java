// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import net.hockeyapp.android.Constants;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.Context;

public class DeviceUtils
{
    private DeviceUtils() {
    }
    
    public static DeviceUtils getInstance() {
        return DeviceUtilsHolder.INSTANCE;
    }
    
    public String getAppName(final Context context) {
        if (context == null) {
            return "";
        }
        try {
            final PackageManager packageManager = context.getPackageManager();
            if (packageManager == null) {
                return "";
            }
            return packageManager.getApplicationLabel(packageManager.getApplicationInfo(context.getPackageName(), 0)).toString();
        }
        catch (PackageManager$NameNotFoundException ex) {
            ex.printStackTrace();
            return "";
        }
    }
    
    public int getCurrentVersionCode(final Context context) {
        return Integer.parseInt(Constants.APP_VERSION);
    }
    
    private static class DeviceUtilsHolder
    {
        public static final DeviceUtils INSTANCE;
        
        static {
            INSTANCE = new DeviceUtils(null);
        }
    }
}
