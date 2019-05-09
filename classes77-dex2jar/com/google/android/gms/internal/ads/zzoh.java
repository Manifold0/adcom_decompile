// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.content.ServiceConnection;
import android.app.Activity;
import android.support.customtabs.CustomTabsCallback;
import android.os.Bundle;
import java.util.List;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.Intent;
import android.net.Uri;
import android.content.Context;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsClient;
import android.support.annotation.Nullable;
import android.support.customtabs.CustomTabsSession;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
public final class zzoh implements zzbfy
{
    @Nullable
    private CustomTabsSession zzbgw;
    @Nullable
    private CustomTabsClient zzbgx;
    @Nullable
    private CustomTabsServiceConnection zzbgy;
    @Nullable
    private zzoi zzbgz;
    
    public static boolean zzh(final Context context) {
        final PackageManager packageManager = context.getPackageManager();
        if (packageManager != null) {
            final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://www.example.com"));
            final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            final List queryIntentActivities = packageManager.queryIntentActivities(intent, 65536);
            if (queryIntentActivities != null && resolveActivity != null) {
                for (int i = 0; i < queryIntentActivities.size(); ++i) {
                    if (resolveActivity.activityInfo.name.equals(queryIntentActivities.get(i).activityInfo.name)) {
                        return resolveActivity.activityInfo.packageName.equals(zzbfw.zzbn(context));
                    }
                }
            }
        }
        return false;
    }
    
    public final boolean mayLaunchUrl(final Uri uri, final Bundle bundle, final List<Bundle> list) {
        if (this.zzbgx != null) {
            if (this.zzbgx == null) {
                this.zzbgw = null;
            }
            else if (this.zzbgw == null) {
                this.zzbgw = this.zzbgx.newSession((CustomTabsCallback)null);
            }
            final CustomTabsSession zzbgw = this.zzbgw;
            if (zzbgw != null) {
                return zzbgw.mayLaunchUrl(uri, (Bundle)null, (List)null);
            }
        }
        return false;
    }
    
    @Override
    public final void zza(final CustomTabsClient zzbgx) {
        (this.zzbgx = zzbgx).warmup(0L);
        if (this.zzbgz != null) {
            this.zzbgz.zzjp();
        }
    }
    
    public final void zza(final zzoi zzbgz) {
        this.zzbgz = zzbgz;
    }
    
    public final void zzc(final Activity activity) {
        if (this.zzbgy == null) {
            return;
        }
        activity.unbindService((ServiceConnection)this.zzbgy);
        this.zzbgx = null;
        this.zzbgw = null;
        this.zzbgy = null;
    }
    
    public final void zzd(final Activity activity) {
        if (this.zzbgx == null) {
            final String zzbn = zzbfw.zzbn((Context)activity);
            if (zzbn != null) {
                CustomTabsClient.bindCustomTabsService((Context)activity, zzbn, this.zzbgy = new zzbfx(this));
            }
        }
    }
    
    @Override
    public final void zzjo() {
        this.zzbgx = null;
        this.zzbgw = null;
        if (this.zzbgz != null) {
            this.zzbgz.zzjq();
        }
    }
}
