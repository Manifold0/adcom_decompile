// 
// Decompiled by Procyon v0.5.34
// 

package com.moat.analytics.mobile.vng;

import android.content.pm.PackageManager;
import android.os.AsyncTask;
import com.google.android.gms.ads.identifier.AdvertisingIdClient$Info;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import android.content.Context;
import android.support.annotation.FloatRange;
import android.media.AudioManager;

class s
{
    private static String a;
    
    @FloatRange(from = 0.0, to = 1.0)
    static double a() {
        try {
            return e() / (double)((AudioManager)((Context)com.moat.analytics.mobile.vng.a.a()).getSystemService("audio")).getStreamMaxVolume(3);
        }
        catch (Exception ex) {
            m.a(ex);
            return 0.0;
        }
    }
    
    static void a(final Context context) {
        try {
            AsyncTask.execute((Runnable)new Runnable() {
                @Override
                public void run() {
                    try {
                        final AdvertisingIdClient$Info advertisingIdInfo = AdvertisingIdClient.getAdvertisingIdInfo(context);
                        if (!advertisingIdInfo.isLimitAdTrackingEnabled()) {
                            s.a = advertisingIdInfo.getId();
                            p.a(3, "Util", this, "Retrieved Advertising ID = " + s.a);
                            return;
                        }
                        p.a(3, "Util", this, "User has limited ad tracking");
                    }
                    catch (Exception ex) {
                        m.a(ex);
                    }
                }
            });
        }
        catch (Exception ex) {
            m.a(ex);
        }
    }
    
    static String b() {
        return s.a;
    }
    
    static Context c() {
        return ((k)MoatAnalytics.getInstance()).e.get();
    }
    
    private static int e() {
        try {
            return ((AudioManager)((Context)com.moat.analytics.mobile.vng.a.a()).getSystemService("audio")).getStreamVolume(3);
        }
        catch (Exception ex) {
            m.a(ex);
            return 0;
        }
    }
    
    static class a
    {
        private boolean a;
        private String b;
        private String c;
        
        a() {
            this.a = false;
            this.b = "_unknown_";
            this.c = "_unknown_";
        }
        
        private void c() {
            try {
                final Context c = s.c();
                if (c != null) {
                    final PackageManager packageManager = c.getPackageManager();
                    this.c = c.getPackageName();
                    this.b = packageManager.getApplicationLabel(c.getApplicationInfo()).toString();
                    this.a = true;
                    return;
                }
                p.a(3, "Util", this, "Can't get app name, appContext is null.");
            }
            catch (Exception ex) {
                m.a(ex);
            }
        }
        
        String a() {
            if (this.a) {
                return this.b;
            }
            this.c();
            return this.b;
        }
        
        String b() {
            if (this.a) {
                return this.c;
            }
            this.c();
            return this.c;
        }
    }
}
