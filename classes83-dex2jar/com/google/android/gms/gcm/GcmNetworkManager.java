// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.content.ComponentName;
import java.util.Iterator;
import java.util.Collections;
import android.os.UserManager;
import android.os.Build$VERSION;
import com.google.android.gms.common.internal.zzbq;
import android.text.TextUtils;
import android.os.Process;
import android.os.Parcelable;
import com.google.android.gms.iid.zzl;
import android.util.Log;
import android.content.pm.ResolveInfo;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.Intent;
import android.support.v4.util.ArrayMap;
import java.util.Map;
import android.app.PendingIntent;
import android.content.Context;

public class GcmNetworkManager
{
    public static final int RESULT_FAILURE = 2;
    public static final int RESULT_RESCHEDULE = 1;
    public static final int RESULT_SUCCESS = 0;
    private static GcmNetworkManager zzhyy;
    private Context mContext;
    private final PendingIntent zzebp;
    private Boolean zzhyz;
    private final Map<String, Map<String, Boolean>> zzhza;
    
    private GcmNetworkManager(final Context mContext) {
        this.zzhza = (Map<String, Map<String, Boolean>>)new ArrayMap();
        this.mContext = mContext;
        this.zzebp = PendingIntent.getBroadcast(this.mContext, 0, new Intent().setPackage("com.google.example.invalidpackage"), 0);
    }
    
    public static GcmNetworkManager getInstance(final Context context) {
        synchronized (GcmNetworkManager.class) {
            if (GcmNetworkManager.zzhyy == null) {
                GcmNetworkManager.zzhyy = new GcmNetworkManager(context.getApplicationContext());
            }
            return GcmNetworkManager.zzhyy;
        }
    }
    
    private static List<ResolveInfo> zza(final PackageManager packageManager, final Intent intent, int n) {
        List<ResolveInfo> queryIntentServices = null;
        n = 0;
        List<ResolveInfo> list;
        while (true) {
            list = queryIntentServices;
            if (n >= 2) {
                break;
            }
            queryIntentServices = (List<ResolveInfo>)packageManager.queryIntentServices(intent, 0);
            if (queryIntentServices != null && !queryIntentServices.isEmpty()) {
                list = queryIntentServices;
                if (n > 0) {
                    Log.i("GcmNetworkManager", new StringBuilder(46).append("validation query succeeded on try #2").toString());
                    list = queryIntentServices;
                    break;
                }
                break;
            }
            else {
                ++n;
            }
        }
        return list;
    }
    
    private final Intent zzaug() {
        final String zzdm = zzl.zzdm(this.mContext);
        int zzdi = -1;
        if (zzdm != null) {
            zzdi = GoogleCloudMessaging.zzdi(this.mContext);
        }
        if (zzdm == null || zzdi < 5000000) {
            Log.e("GcmNetworkManager", new StringBuilder(91).append("Google Play Services is not available, dropping GcmNetworkManager request. code=").append(zzdi).toString());
            return null;
        }
        final Intent intent = new Intent("com.google.android.gms.gcm.ACTION_SCHEDULE");
        intent.setPackage(zzdm);
        intent.putExtra("app", (Parcelable)this.zzebp);
        intent.putExtra("source", 4);
        intent.putExtra("source_version", 11720000);
        return intent;
    }
    
    private final boolean zzauh() {
        synchronized (this) {
            if (this.zzhyz == null) {
                this.zzhyz = (this.mContext.checkPermission("android.permission.INTERACT_ACROSS_USERS", Process.myPid(), Process.myUid()) == 0);
            }
            return this.zzhyz;
        }
    }
    
    static void zzhp(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Must provide a valid tag.");
        }
        if (100 < s.length()) {
            throw new IllegalArgumentException("Tag is larger than max permissible tag length (100)");
        }
    }
    
    private final boolean zzhq(final String s) {
        zzbq.checkNotNull((Object)s, (Object)"GcmTaskService must not be null.");
        final PackageManager packageManager = this.mContext.getPackageManager();
        List<ResolveInfo> list;
        if (packageManager == null) {
            list = null;
        }
        else {
            boolean b;
            if (Build$VERSION.SDK_INT < 17) {
                b = true;
            }
            else {
                b = false;
            }
            boolean b2;
            if (Build$VERSION.SDK_INT < 24) {
                b2 = true;
            }
            else {
                b2 = false;
            }
            int n;
            if (b || (b2 && !this.zzauh())) {
                n = 1;
            }
            else {
                final UserManager userManager = (UserManager)this.mContext.getSystemService("user");
                if (userManager == null || userManager.isUserRunning(Process.myUserHandle())) {
                    n = 1;
                }
                else {
                    n = 0;
                }
            }
            if (n == 0) {
                String s2;
                if (s == null) {
                    s2 = "unknown service";
                }
                else {
                    s2 = s;
                }
                Log.w("GcmNetworkManager", new StringBuilder(String.valueOf(s2).length() + 51).append("Dropping request for ").append(s2).append(" because user is shutting down").toString());
                list = null;
            }
            else {
                Intent intent;
                if (s != null) {
                    intent = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY").setClassName(this.mContext, s);
                }
                else {
                    intent = new Intent("com.google.android.gms.gcm.ACTION_TASK_READY").setPackage(this.mContext.getPackageName());
                }
                if ((list = zza(packageManager, intent, 0)) == null) {
                    list = Collections.emptyList();
                }
            }
        }
        if (list == null) {
            return false;
        }
        final Iterator<ResolveInfo> iterator = list.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().serviceInfo.name.equals(s)) {
                return true;
            }
        }
        throw new IllegalArgumentException(new StringBuilder(String.valueOf(s).length() + 118).append("The GcmTaskService class you provided ").append(s).append(" does not seem to support receiving com.google.android.gms.gcm.ACTION_TASK_READY").toString());
    }
    
    @WorkerThread
    public void cancelAllTasks(final Class<? extends GcmTaskService> clazz) {
        final ComponentName componentName = new ComponentName(this.mContext, (Class)clazz);
        if (this.zzhq(componentName.getClassName())) {
            final Intent zzaug = this.zzaug();
            if (zzaug != null) {
                zzaug.putExtra("scheduler_action", "CANCEL_ALL");
                zzaug.putExtra("component", (Parcelable)componentName);
                this.mContext.sendBroadcast(zzaug);
            }
        }
    }
    
    @WorkerThread
    public void cancelTask(final String s, final Class<? extends GcmTaskService> clazz) {
        final ComponentName componentName = new ComponentName(this.mContext, (Class)clazz);
        zzhp(s);
        if (this.zzhq(componentName.getClassName())) {
            final Intent zzaug = this.zzaug();
            if (zzaug != null) {
                zzaug.putExtra("scheduler_action", "CANCEL_TASK");
                zzaug.putExtra("tag", s);
                zzaug.putExtra("component", (Parcelable)componentName);
                this.mContext.sendBroadcast(zzaug);
            }
        }
    }
    
    @WorkerThread
    public void schedule(final Task task) {
        synchronized (this) {
            if (this.zzhq(task.getServiceName())) {
                final Intent zzaug = this.zzaug();
                if (zzaug != null) {
                    final Bundle extras = zzaug.getExtras();
                    extras.putString("scheduler_action", "SCHEDULE_TASK");
                    task.toBundle(extras);
                    zzaug.putExtras(extras);
                    this.mContext.sendBroadcast(zzaug);
                    final Map<String, Boolean> map = this.zzhza.get(task.getServiceName());
                    if (map != null && map.containsKey(task.getTag())) {
                        map.put(task.getTag(), true);
                    }
                }
            }
        }
    }
    
    final boolean zzaa(final String s, final String s2) {
        synchronized (this) {
            Object o;
            if ((o = this.zzhza.get(s2)) == null) {
                o = new ArrayMap();
                this.zzhza.put(s2, (Map<String, Boolean>)o);
            }
            return ((Map<String, Boolean>)o).put(s, Boolean.valueOf(false)) == null;
        }
    }
    
    final void zzab(final String s, final String s2) {
        synchronized (this) {
            final Map<String, Boolean> map = this.zzhza.get(s2);
            if (map != null) {
                int n;
                if (map.remove(s) != null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n != 0 && map.isEmpty()) {
                    this.zzhza.remove(s2);
                }
            }
        }
    }
    
    final boolean zzac(final String s, final String s2) {
        synchronized (this) {
            final Map<String, Boolean> map = this.zzhza.get(s2);
            boolean b2;
            if (map != null) {
                final Boolean b = map.get(s);
                b2 = (b != null && b);
            }
            else {
                b2 = false;
            }
            return b2;
        }
    }
    
    final boolean zzhr(final String s) {
        synchronized (this) {
            return this.zzhza.containsKey(s);
        }
    }
}
