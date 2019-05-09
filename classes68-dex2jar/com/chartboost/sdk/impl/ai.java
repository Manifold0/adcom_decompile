// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.content.Intent;
import android.net.NetworkInfo;
import com.chartboost.sdk.Libraries.CBLogging;
import android.telephony.TelephonyManager;
import android.net.ConnectivityManager;
import android.content.Context;
import java.util.Observable;

public class ai extends Observable
{
    private boolean a;
    private boolean b;
    private int c;
    private final a d;
    
    public ai() {
        this.a = true;
        this.b = false;
        this.c = -1;
        this.d = new a();
    }
    
    public static Integer d(final Context context) {
        try {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            int n;
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                n = 1;
            }
            else {
                n = 0;
            }
            if (n != 0) {
                final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
                if (telephonyManager != null) {
                    return telephonyManager.getNetworkType();
                }
            }
        }
        catch (SecurityException ex) {
            CBLogging.b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
        }
        return null;
    }
    
    public int a() {
        return this.c;
    }
    
    public void a(final Context context) {
        try {
            final NetworkInfo activeNetworkInfo = ((ConnectivityManager)context.getSystemService("connectivity")).getActiveNetworkInfo();
            if (activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting()) {
                this.a(true);
                if (activeNetworkInfo.getType() == 1) {
                    this.c = 1;
                    CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_WIFI");
                    return;
                }
                if (activeNetworkInfo.getType() == 0) {
                    this.c = 2;
                    CBLogging.a("CBReachability", "NETWORK TYPE: TYPE_MOBILE");
                }
                return;
            }
        }
        catch (SecurityException ex) {
            this.c = -1;
            CBLogging.b("CBReachability", "Chartboost SDK requires 'android.permission.ACCESS_NETWORK_STATE' permission set in your AndroidManifest.xml");
            return;
        }
        this.a(false);
        this.c = 0;
        CBLogging.a("CBReachability", "NETWORK TYPE: NO Network");
    }
    
    public void a(final boolean a) {
        this.a = a;
    }
    
    public Intent b(final Context context) {
        if (context != null && !this.b) {
            this.b(true);
            CBLogging.a("CBReachability", "Network broadcast successfully registered");
            return context.registerReceiver((BroadcastReceiver)this.d, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
        }
        return null;
    }
    
    public void b(final boolean b) {
        this.b = b;
    }
    
    public boolean b() {
        return this.a;
    }
    
    public void c(final Context context) {
        if (context != null && this.b) {
            context.unregisterReceiver((BroadcastReceiver)this.d);
            this.b(false);
            CBLogging.a("CBReachability", "Network broadcast successfully unregistered");
        }
    }
    
    @Override
    public void notifyObservers() {
        if (this.a) {
            this.setChanged();
            super.notifyObservers(this);
        }
    }
    
    private class a extends BroadcastReceiver
    {
        public a() {
        }
        
        public void onReceive(final Context context, final Intent intent) {
            ai.this.a(context);
            ai.this.notifyObservers();
        }
    }
}
