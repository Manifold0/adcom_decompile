// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.app.FragmentTransaction;
import android.content.pm.PackageInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.app.Fragment;
import android.os.Bundle;
import android.content.pm.PackageManager$NameNotFoundException;
import java.util.LinkedList;
import android.app.Activity;
import android.content.pm.PackageItemInfo;

public final class h implements e
{
    private static boolean a(final PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public final void a(final Activity activity, final Runnable runnable) {
        int n = 0;
        if (activity == null) {
            return;
        }
        final PackageManager packageManager = activity.getPackageManager();
        try {
            final ActivityInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 128);
            final ApplicationInfo applicationInfo = packageManager.getApplicationInfo(activity.getPackageName(), 128);
            if (a((PackageItemInfo)activityInfo) || a((PackageItemInfo)applicationInfo)) {
                runnable.run();
                return;
            }
        }
        catch (Exception ex2) {}
        LinkedList<String> list;
        while (true) {
            while (true) {
                Label_0339: {
                    try {
                        final PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 4096);
                        if (packageInfo.requestedPermissions == null) {
                            packageInfo.requestedPermissions = new String[0];
                        }
                        list = new LinkedList<String>();
                        final String[] requestedPermissions = packageInfo.requestedPermissions;
                        final int length = requestedPermissions.length;
                        if (n < length) {
                            final String s = requestedPermissions[n];
                            try {
                                if ((packageManager.getPermissionInfo(s, 128).protectionLevel & 0x1) != 0x0) {
                                    if (activity.checkCallingOrSelfPermission(s) != 0) {
                                        list.add(s);
                                    }
                                }
                            }
                            catch (PackageManager$NameNotFoundException ex3) {
                                g.Log(5, "Failed to get permission info for " + s + ", manifest likely missing custom permission declaration");
                                g.Log(5, "Permission " + s + " ignored");
                            }
                            break Label_0339;
                        }
                    }
                    catch (Exception ex) {
                        g.Log(6, "Unable to query for permission: " + ex.getMessage());
                        return;
                    }
                    break;
                }
                ++n;
                continue;
            }
        }
        if (list.isEmpty()) {
            runnable.run();
            return;
        }
        final i i = new i(runnable);
        final Bundle arguments = new Bundle();
        arguments.putStringArray("PermissionNames", (String[])list.toArray(new String[0]));
        i.setArguments(arguments);
        final FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
        beginTransaction.add(0, (Fragment)i);
        beginTransaction.commit();
    }
}
