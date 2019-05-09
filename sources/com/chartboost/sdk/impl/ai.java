package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.chartboost.sdk.Libraries.CBLogging;
import com.facebook.places.model.PlaceFields;
import java.util.Observable;

public class ai extends Observable {
    /* renamed from: a */
    private boolean f3004a = true;
    /* renamed from: b */
    private boolean f3005b = false;
    /* renamed from: c */
    private int f3006c = -1;
    /* renamed from: d */
    private final C1418a f3007d = new C1418a(this);

    /* renamed from: com.chartboost.sdk.impl.ai$a */
    private class C1418a extends BroadcastReceiver {
        /* renamed from: a */
        final /* synthetic */ ai f3003a;

        public C1418a(ai aiVar) {
            this.f3003a = aiVar;
        }

        public void onReceive(Context context, Intent intent) {
            this.f3003a.m3374a(context);
            this.f3003a.notifyObservers();
        }
    }

    /* renamed from: a */
    public int m3373a() {
        return this.f3006c;
    }

    /* renamed from: a */
    public void m3374a(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) {
                m3375a(false);
                this.f3006c = 0;
                CBLogging.m3097a("CBReachability", "NETWORK TYPE: NO Network");
                return;
            }
            m3375a(true);
            if (activeNetworkInfo.getType() == 1) {
                this.f3006c = 1;
                CBLogging.m3097a("CBReachability", "NETWORK TYPE: TYPE_WIFI");
            } else if (activeNetworkInfo.getType() == 0) {
                this.f3006c = 2;
                CBLogging.m3097a("CBReachability", "NETWORK TYPE: TYPE_MOBILE");
            }
        } catch (SecurityException e) {
            this.f3006c = -1;
            CBLogging.m3099b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
    }

    public void notifyObservers() {
        if (this.f3004a) {
            setChanged();
            super.notifyObservers(this);
        }
    }

    /* renamed from: a */
    public void m3375a(boolean z) {
        this.f3004a = z;
    }

    /* renamed from: b */
    public boolean m3378b() {
        return this.f3004a;
    }

    /* renamed from: b */
    public Intent m3376b(Context context) {
        if (context == null || this.f3005b) {
            return null;
        }
        m3377b(true);
        CBLogging.m3097a("CBReachability", "Network broadcast successfully registered");
        return context.registerReceiver(this.f3007d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
    }

    /* renamed from: c */
    public void m3379c(Context context) {
        if (context != null && this.f3005b) {
            context.unregisterReceiver(this.f3007d);
            m3377b(false);
            CBLogging.m3097a("CBReachability", "Network broadcast successfully unregistered");
        }
    }

    /* renamed from: b */
    public void m3377b(boolean z) {
        this.f3005b = z;
    }

    /* renamed from: d */
    public static Integer m3372d(Context context) {
        try {
            NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
            Object obj = (activeNetworkInfo == null || !activeNetworkInfo.isConnectedOrConnecting()) ? null : 1;
            if (obj != null) {
                TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
                if (telephonyManager != null) {
                    return Integer.valueOf(telephonyManager.getNetworkType());
                }
            }
        } catch (SecurityException e) {
            CBLogging.m3099b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
        return null;
    }
}
