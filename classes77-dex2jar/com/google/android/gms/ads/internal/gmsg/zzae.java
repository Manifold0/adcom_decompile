// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.ads.internal.gmsg;

import java.util.Iterator;
import android.app.ActivityManager$RunningAppProcessInfo;
import com.google.android.gms.internal.ads.zzakk;
import com.google.android.gms.internal.ads.zznk;
import com.google.android.gms.internal.ads.zzkb;
import android.app.Activity;
import android.text.TextUtils;
import android.app.ActivityManager;
import java.util.Map;
import android.net.Uri;
import java.util.List;
import android.content.pm.PackageManager;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Collection;
import java.util.ArrayList;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import com.google.android.gms.internal.ads.zzci;
import android.view.View;
import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
public final class zzae
{
    private final Context mContext;
    private final View mView;
    private final zzci zzbne;
    
    public zzae(final Context mContext, final zzci zzbne, final View mView) {
        this.mContext = mContext;
        this.zzbne = zzbne;
        this.mView = mView;
    }
    
    private static Intent zza(Intent intent, final ResolveInfo resolveInfo) {
        intent = new Intent(intent);
        intent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name);
        return intent;
    }
    
    @VisibleForTesting
    private final ResolveInfo zza(Intent resolveActivity, final ArrayList<ResolveInfo> t) {
        while (true) {
            while (true) {
                Label_0133: {
                    while (true) {
                        try {
                            final PackageManager packageManager = this.mContext.getPackageManager();
                            if (packageManager == null) {
                                return null;
                            }
                            final List queryIntentActivities = packageManager.queryIntentActivities(resolveActivity, 65536);
                            resolveActivity = (Intent)packageManager.resolveActivity(resolveActivity, 65536);
                            if (queryIntentActivities != null && resolveActivity != null) {
                                Block_7: {
                                    for (int i = 0; i < queryIntentActivities.size(); ++i) {
                                        final ResolveInfo resolveInfo = queryIntentActivities.get(i);
                                        if (resolveActivity != null && ((ResolveInfo)resolveActivity).activityInfo.name.equals(resolveInfo.activityInfo.name)) {
                                            break Block_7;
                                        }
                                    }
                                    break Label_0133;
                                }
                                try {
                                    ((ArrayList)t).addAll(queryIntentActivities);
                                    return (ResolveInfo)resolveActivity;
                                }
                                catch (Throwable t2) {}
                                zzbv.zzeo().zza(t, "OpenSystemBrowserHandler.getDefaultBrowserResolverForIntent");
                                return (ResolveInfo)resolveActivity;
                            }
                        }
                        catch (Throwable t) {
                            resolveActivity = null;
                            continue;
                        }
                        break;
                    }
                }
                resolveActivity = null;
                continue;
            }
        }
    }
    
    @VisibleForTesting
    private final ResolveInfo zzb(final Intent intent) {
        return this.zza(intent, new ArrayList<ResolveInfo>());
    }
    
    private static Intent zze(final Uri data) {
        if (data == null) {
            return null;
        }
        final Intent intent = new Intent("android.intent.action.VIEW");
        intent.addFlags(268435456);
        intent.setData(data);
        intent.setAction("android.intent.action.VIEW");
        return intent;
    }
    
    @VisibleForTesting
    public final Intent zzi(final Map<String, String> map) {
        final Uri uri = null;
        final Intent intent = null;
        final ActivityManager activityManager = (ActivityManager)this.mContext.getSystemService("activity");
        final String s = map.get("u");
        Intent zza;
        if (TextUtils.isEmpty((CharSequence)s)) {
            zza = intent;
        }
        else {
            final Uri parse = Uri.parse(zzad.zza(this.mContext, this.zzbne, s, this.mView, null));
            final boolean boolean1 = Boolean.parseBoolean(map.get("use_first_package"));
            final boolean boolean2 = Boolean.parseBoolean(map.get("use_running_process"));
            int n;
            if (Boolean.parseBoolean(map.get("use_custom_tabs")) || (boolean)zzkb.zzik().zzd(zznk.zzbdz)) {
                n = 1;
            }
            else {
                n = 0;
            }
            Uri uri2;
            if ("http".equalsIgnoreCase(parse.getScheme())) {
                uri2 = parse.buildUpon().scheme("https").build();
            }
            else {
                uri2 = uri;
                if ("https".equalsIgnoreCase(parse.getScheme())) {
                    uri2 = parse.buildUpon().scheme("http").build();
                }
            }
            final ArrayList list = new ArrayList<ResolveInfo>();
            final Intent zze = zze(parse);
            final Intent zze2 = zze(uri2);
            if (n != 0) {
                zzbv.zzek();
                zzakk.zzb(this.mContext, zze);
                zzbv.zzek();
                zzakk.zzb(this.mContext, zze2);
            }
            final ResolveInfo zza2 = this.zza(zze, list);
            if (zza2 != null) {
                return zza(zze, zza2);
            }
            if (zze2 != null) {
                final ResolveInfo zzb = this.zzb(zze2);
                if (zzb != null && this.zzb(zza = zza(zze, zzb)) != null) {
                    return zza;
                }
            }
            if (list.size() == 0) {
                return zze;
            }
            if (boolean2 && activityManager != null) {
                final List runningAppProcesses = activityManager.getRunningAppProcesses();
                if (runningAppProcesses != null) {
                    final ArrayList<ResolveInfo> list2 = (ArrayList<ResolveInfo>)list;
                    for (int size = list2.size(), i = 0; i < size; ++i) {
                        final ResolveInfo resolveInfo = list2.get(i);
                        final Iterator<ActivityManager$RunningAppProcessInfo> iterator = runningAppProcesses.iterator();
                        while (iterator.hasNext()) {
                            if (iterator.next().processName.equals(resolveInfo.activityInfo.packageName)) {
                                return zza(zze, resolveInfo);
                            }
                        }
                    }
                }
            }
            if (boolean1) {
                return zza(zze, list.get(0));
            }
            return zze;
        }
        return zza;
    }
}
