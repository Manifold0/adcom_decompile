package com.google.android.gms.gcm;

import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.Process;
import android.os.UserManager;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.facebook.share.internal.ShareConstants;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.GmsVersion;
import com.google.android.gms.iid.zzl;
import com.tapjoy.TapjoyConstants;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class GcmNetworkManager {
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS = 0;
    private static GcmNetworkManager zzhyy;
    private Context mContext;
    private final PendingIntent zzebp;
    private Boolean zzhyz;
    private final Map<String, Map<String, Boolean>> zzhza = new ArrayMap();

    private GcmNetworkManager(Context context) {
        this.mContext = context;
        this.zzebp = PendingIntent.getBroadcast(this.mContext, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);
    }

    public static GcmNetworkManager getInstance(Context context) {
        GcmNetworkManager gcmNetworkManager;
        synchronized (GcmNetworkManager.class) {
            if (zzhyy == null) {
                zzhyy = new GcmNetworkManager(context.getApplicationContext());
            }
            gcmNetworkManager = zzhyy;
        }
        return gcmNetworkManager;
    }

    private static List<ResolveInfo> zza(PackageManager packageManager, Intent intent, int i) {
        List<ResolveInfo> list = null;
        int i2 = 0;
        while (i2 < 2) {
            list = packageManager.queryIntentServices(intent, 0);
            if (list == null ? true : list.isEmpty()) {
                i2++;
            } else {
                if (i2 > 0) {
                    Log.i("GcmNetworkManager", "validation query succeeded on try #2");
                }
                return list;
            }
        }
        return list;
    }

    private final Intent zzaug() {
        String zzdm = zzl.zzdm(this.mContext);
        int i = -1;
        if (zzdm != null) {
            i = GoogleCloudMessaging.zzdi(this.mContext);
        }
        if (zzdm == null || i < GmsVersion.VERSION_LONGHORN) {
            Log.e("GcmNetworkManager", "Google Play Services is not available, dropping GcmNetworkManager request. code=" + i);
            return null;
        }
        Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage(zzdm);
        intent.putExtra(TapjoyConstants.TJC_APP_PLACEMENT, this.zzebp);
        intent.putExtra(ShareConstants.FEED_SOURCE_PARAM, 4);
        intent.putExtra("source_version", 11720000);
        return intent;
    }

    private final synchronized boolean zzauh() {
        if (this.zzhyz == null) {
            this.zzhyz = Boolean.valueOf(this.mContext.checkPermission("android.permission.INTERACT_ACROSS_USERS", Process.myPid(), Process.myUid()) == 0);
        }
        return this.zzhyz.booleanValue();
    }

    static void zzhp(String str) {
        if (TextUtils.isEmpty(str)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        } else if (100 < str.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }

    private final boolean zzhq(String str) {
        zzbq.checkNotNull(str, "GcmTaskService must not be null.");
        PackageManager packageManager = this.mContext.getPackageManager();
        List list;
        if (packageManager == null) {
            list = null;
        } else {
            boolean z = VERSION.SDK_INT < 17;
            boolean z2 = VERSION.SDK_INT < 24;
            if (z || (z2 && !zzauh())) {
                z = true;
            } else {
                UserManager userManager = (UserManager) this.mContext.getSystemService("user");
                z = userManager == null || userManager.isUserRunning(Process.myUserHandle());
            }
            if (z) {
                list = zza(packageManager, str != null ? new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setClassName(this.mContext, str) : new Intent(GcmTaskService.SERVICE_ACTION_EXECUTE_TASK).setPackage(this.mContext.getPackageName()), 0);
                if (list == null) {
                    list = Collections.emptyList();
                }
            } else {
                String str2 = "GcmNetworkManager";
                String str3 = str == null ? "unknown service" : str;
                Log.w(str2, new StringBuilder(String.valueOf(str3).length() + 51).append("Dropping request for ").append(str3).append(" because user is shutting down").toString());
                list = null;
            }
        }
        if (r0 == null) {
            return false;
        }
        for (ResolveInfo resolveInfo : r0) {
            if (resolveInfo.serviceInfo.name.equals(str)) {
                return true;
            }
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 118).append("The GcmTaskService class you provided ").append(str).append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY").toString());
    }

    @WorkerThread
    public void cancelAllTasks(Class<? extends GcmTaskService> cls) {
        Parcelable componentName = new ComponentName(this.mContext, cls);
        if (zzhq(componentName.getClassName())) {
            Intent zzaug = zzaug();
            if (zzaug != null) {
                zzaug.putExtra("scheduler_action", "CANCEL_ALL");
                zzaug.putExtra("component", componentName);
                this.mContext.sendBroadcast(zzaug);
            }
        }
    }

    @WorkerThread
    public void cancelTask(String str, Class<? extends GcmTaskService> cls) {
        Parcelable componentName = new ComponentName(this.mContext, cls);
        zzhp(str);
        if (zzhq(componentName.getClassName())) {
            Intent zzaug = zzaug();
            if (zzaug != null) {
                zzaug.putExtra("scheduler_action", "CANCEL_TASK");
                zzaug.putExtra("tag", str);
                zzaug.putExtra("component", componentName);
                this.mContext.sendBroadcast(zzaug);
            }
        }
    }

    @WorkerThread
    public synchronized void schedule(Task task) {
        if (zzhq(task.getServiceName())) {
            Intent zzaug = zzaug();
            if (zzaug != null) {
                Bundle extras = zzaug.getExtras();
                extras.putString("scheduler_action", "SCHEDULE_TASK");
                task.toBundle(extras);
                zzaug.putExtras(extras);
                this.mContext.sendBroadcast(zzaug);
                Map map = (Map) this.zzhza.get(task.getServiceName());
                if (map != null && map.containsKey(task.getTag())) {
                    map.put(task.getTag(), Boolean.valueOf(true));
                }
            }
        }
    }

    final synchronized boolean zzaa(String str, String str2) {
        Map map;
        map = (Map) this.zzhza.get(str2);
        if (map == null) {
            map = new ArrayMap();
            this.zzhza.put(str2, map);
        }
        return map.put(str, Boolean.valueOf(false)) == null;
    }

    final synchronized void zzab(String str, String str2) {
        Map map = (Map) this.zzhza.get(str2);
        if (map != null) {
            if ((map.remove(str) != null ? 1 : null) != null && map.isEmpty()) {
                this.zzhza.remove(str2);
            }
        }
    }

    final synchronized boolean zzac(String str, String str2) {
        boolean booleanValue;
        Map map = (Map) this.zzhza.get(str2);
        if (map != null) {
            Boolean bool = (Boolean) map.get(str);
            booleanValue = bool == null ? false : bool.booleanValue();
        } else {
            booleanValue = false;
        }
        return booleanValue;
    }

    final synchronized boolean zzhr(String str) {
        return this.zzhza.containsKey(str);
    }
}
