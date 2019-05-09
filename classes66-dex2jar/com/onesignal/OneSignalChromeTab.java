// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.customtabs.CustomTabsSession;
import java.util.List;
import android.net.Uri;
import android.os.Bundle;
import android.support.customtabs.CustomTabsCallback;
import android.content.ComponentName;
import android.support.customtabs.CustomTabsServiceConnection;
import android.support.customtabs.CustomTabsClient;
import java.security.SecureRandom;
import android.content.Context;

class OneSignalChromeTab
{
    private static boolean opened;
    
    static void setup(final Context context, String s, String s2, final String s3) {
        if (OneSignalChromeTab.opened || OneSignal.mEnterp || s2 == null) {
            return;
        }
        try {
            Class.forName("android.support.customtabs.CustomTabsServiceConnection");
            s2 = (s = "?app_id=" + s + "&user_id=" + s2);
            if (s3 != null) {
                s = s2 + "&ad_id=" + s3;
            }
            OneSignalChromeTab.opened = CustomTabsClient.bindCustomTabsService(context, "com.android.chrome", (CustomTabsServiceConnection)new OneSignalCustomTabsServiceConnection(context, s + "&cbs_id=" + new SecureRandom().nextInt(Integer.MAX_VALUE)));
        }
        catch (ClassNotFoundException ex) {}
    }
    
    private static class OneSignalCustomTabsServiceConnection extends CustomTabsServiceConnection
    {
        private Context mContext;
        private String mParams;
        
        OneSignalCustomTabsServiceConnection(final Context mContext, final String mParams) {
            this.mContext = mContext;
            this.mParams = mParams;
        }
        
        public void onCustomTabsServiceConnected(final ComponentName componentName, final CustomTabsClient customTabsClient) {
            if (customTabsClient != null) {
                customTabsClient.warmup(0L);
                final CustomTabsSession session = customTabsClient.newSession((CustomTabsCallback)new CustomTabsCallback() {
                    public void extraCallback(final String s, final Bundle bundle) {
                        super.extraCallback(s, bundle);
                    }
                    
                    public void onNavigationEvent(final int n, final Bundle bundle) {
                        super.onNavigationEvent(n, bundle);
                    }
                });
                if (session != null) {
                    session.mayLaunchUrl(Uri.parse("https://onesignal.com/android_frame.html" + this.mParams), (Bundle)null, (List)null);
                }
            }
        }
        
        public void onServiceDisconnected(final ComponentName componentName) {
        }
    }
}
