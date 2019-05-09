// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.os.Looper;
import android.os.Handler;
import android.os.RemoteException;
import android.os.Bundle;
import java.util.Iterator;
import android.content.pm.ResolveInfo;
import android.content.pm.PackageManager;
import java.util.Collection;
import android.net.Uri;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.List;
import android.content.ServiceConnection;
import android.text.TextUtils;
import android.content.Intent;
import android.content.Context;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.content.ComponentName;

public class CustomTabsClient
{
    private final ICustomTabsService mService;
    private final ComponentName mServiceComponentName;
    
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    CustomTabsClient(final ICustomTabsService mService, final ComponentName mServiceComponentName) {
        this.mService = mService;
        this.mServiceComponentName = mServiceComponentName;
    }
    
    public static boolean bindCustomTabsService(final Context context, final String package1, final CustomTabsServiceConnection customTabsServiceConnection) {
        final Intent intent = new Intent("android.support.customtabs.action.CustomTabsService");
        if (!TextUtils.isEmpty((CharSequence)package1)) {
            intent.setPackage(package1);
        }
        return context.bindService(intent, (ServiceConnection)customTabsServiceConnection, 33);
    }
    
    public static boolean connectAndInitialize(Context applicationContext, final String s) {
        if (s == null) {
            return false;
        }
        applicationContext = applicationContext.getApplicationContext();
        final CustomTabsServiceConnection customTabsServiceConnection = new CustomTabsServiceConnection() {
            @Override
            public final void onCustomTabsServiceConnected(final ComponentName componentName, final CustomTabsClient customTabsClient) {
                customTabsClient.warmup(0L);
                applicationContext.unbindService((ServiceConnection)this);
            }
            
            public final void onServiceDisconnected(final ComponentName componentName) {
            }
        };
        try {
            return bindCustomTabsService(applicationContext, s, customTabsServiceConnection);
        }
        catch (SecurityException ex) {
            return false;
        }
    }
    
    public static String getPackageName(final Context context, @Nullable final List<String> list) {
        return getPackageName(context, list, false);
    }
    
    public static String getPackageName(final Context context, @Nullable final List<String> list, final boolean b) {
        final PackageManager packageManager = context.getPackageManager();
        List<Object> list2;
        if (list == null) {
            list2 = (List<Object>)new ArrayList<String>();
        }
        else {
            list2 = (List<Object>)list;
        }
        final Intent intent = new Intent("android.intent.action.VIEW", Uri.parse("http://"));
        List<Object> list3 = list2;
        if (!b) {
            final ResolveInfo resolveActivity = packageManager.resolveActivity(intent, 0);
            list3 = list2;
            if (resolveActivity != null) {
                final String packageName = resolveActivity.activityInfo.packageName;
                list3 = new ArrayList<Object>(list2.size() + 1);
                list3.add(packageName);
                if (list != null) {
                    list3.addAll(list);
                }
            }
        }
        final Intent intent2 = new Intent("android.support.customtabs.action.CustomTabsService");
        for (final String package1 : list3) {
            intent2.setPackage(package1);
            if (packageManager.resolveService(intent2, 0) != null) {
                return package1;
            }
        }
        return null;
    }
    
    public Bundle extraCommand(final String s, final Bundle bundle) {
        try {
            return this.mService.extraCommand(s, bundle);
        }
        catch (RemoteException ex) {
            return null;
        }
    }
    
    public CustomTabsSession newSession(final CustomTabsCallback customTabsCallback) {
        final ICustomTabsCallback.Stub stub = new ICustomTabsCallback.Stub() {
            private Handler mHandler = new Handler(Looper.getMainLooper());
            
            public void extraCallback(final String s, final Bundle bundle) throws RemoteException {
                if (customTabsCallback == null) {
                    return;
                }
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        customTabsCallback.extraCallback(s, bundle);
                    }
                });
            }
            
            public void onMessageChannelReady(final Bundle bundle) throws RemoteException {
                if (customTabsCallback == null) {
                    return;
                }
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        customTabsCallback.onMessageChannelReady(bundle);
                    }
                });
            }
            
            public void onNavigationEvent(final int n, final Bundle bundle) {
                if (customTabsCallback == null) {
                    return;
                }
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        customTabsCallback.onNavigationEvent(n, bundle);
                    }
                });
            }
            
            public void onPostMessage(final String s, final Bundle bundle) throws RemoteException {
                if (customTabsCallback == null) {
                    return;
                }
                this.mHandler.post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        customTabsCallback.onPostMessage(s, bundle);
                    }
                });
            }
        };
        try {
            if (!this.mService.newSession(stub)) {
                return null;
            }
        }
        catch (RemoteException ex) {
            return null;
        }
        return new CustomTabsSession(this.mService, stub, this.mServiceComponentName);
    }
    
    public boolean warmup(final long n) {
        try {
            return this.mService.warmup(n);
        }
        catch (RemoteException ex) {
            return false;
        }
    }
}
