package com.unity3d.player;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.pm.PackageInfo;
import android.content.pm.PackageItemInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.unity3d.player.h */
public final class C0244h implements C0242e {
    /* renamed from: a */
    private static boolean m153a(PackageItemInfo packageItemInfo) {
        try {
            return packageItemInfo.metaData.getBoolean("unityplayer.SkipPermissionsDialog");
        } catch (Exception e) {
            return false;
        }
    }

    /* renamed from: a */
    public final void mo694a(Activity activity, Runnable runnable) {
        if (activity != null) {
            PackageManager packageManager = activity.getPackageManager();
            try {
                PackageItemInfo activityInfo = packageManager.getActivityInfo(activity.getComponentName(), 128);
                PackageItemInfo applicationInfo = packageManager.getApplicationInfo(activity.getPackageName(), 128);
                if (C0244h.m153a(activityInfo) || C0244h.m153a(applicationInfo)) {
                    runnable.run();
                    return;
                }
            } catch (Exception e) {
            }
            try {
                PackageInfo packageInfo = packageManager.getPackageInfo(activity.getPackageName(), 4096);
                if (packageInfo.requestedPermissions == null) {
                    packageInfo.requestedPermissions = new String[0];
                }
                List linkedList = new LinkedList();
                for (String str : packageInfo.requestedPermissions) {
                    try {
                        if (!((packageManager.getPermissionInfo(str, 128).protectionLevel & 1) == 0 || activity.checkCallingOrSelfPermission(str) == 0)) {
                            linkedList.add(str);
                        }
                    } catch (NameNotFoundException e2) {
                        C0243g.Log(5, "Failed to get permission info for " + str + ", manifest likely missing custom permission declaration");
                        C0243g.Log(5, "Permission " + str + " ignored");
                    }
                }
                if (linkedList.isEmpty()) {
                    runnable.run();
                    return;
                }
                Fragment c0245i = new C0245i(runnable);
                Bundle bundle = new Bundle();
                bundle.putStringArray("PermissionNames", (String[]) linkedList.toArray(new String[0]));
                c0245i.setArguments(bundle);
                FragmentTransaction beginTransaction = activity.getFragmentManager().beginTransaction();
                beginTransaction.add(0, c0245i);
                beginTransaction.commit();
            } catch (Exception e3) {
                C0243g.Log(6, "Unable to query for permission: " + e3.getMessage());
            }
        }
    }
}
