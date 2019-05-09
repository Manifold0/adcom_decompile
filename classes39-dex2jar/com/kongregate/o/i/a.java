// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.i;

import android.net.NetworkInfo;
import java.util.Collections;
import java.util.HashMap;
import java.io.IOException;
import com.kongregate.android.internal.util.j;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Map;
import com.kongregate.android.internal.util.StringUtils;
import java.util.LinkedList;
import java.util.Enumeration;
import java.net.SocketException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import android.telephony.TelephonyManager;
import android.content.Context;
import android.net.ConnectivityManager;

public class a
{
    private ConnectivityManager a;
    private final Context b;
    
    public a(final Context b) {
        this.b = b;
    }
    
    public static String a(final Context context) {
        final TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService("phone");
        if (telephonyManager != null) {
            return telephonyManager.getNetworkOperatorName();
        }
        return null;
    }
    
    public static String c() {
        try {
            final Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                final Enumeration<InetAddress> inetAddresses = networkInterfaces.nextElement().getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    final InetAddress inetAddress = inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
            return null;
        }
        catch (SocketException ex) {
            ex.printStackTrace();
        }
        return null;
    }
    
    public static String d() {
        final Map<String, String> f = f();
        final LinkedList<String> list = new LinkedList<String>();
        final String e = e();
        if (!StringUtils.c((CharSequence)e)) {
            list.add(e);
        }
        list.add("wlan0");
        list.add("tiwlan0");
        list.add("eth0");
        list.add("wlan1");
        list.add("tiwlan1");
        list.add("eth1");
        for (final String s : list) {
            if (f.containsKey(s)) {
                return f.get(s);
            }
        }
        return null;
    }
    
    private static String e() {
        try {
            return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop wifi.interface").getInputStream())).readLine();
        }
        catch (IOException ex) {
            j.d("Error getting wifi interface name", ex);
            return null;
        }
    }
    
    private static Map<String, String> f() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        try {
            for (final NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                final byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    final StringBuilder sb = new StringBuilder();
                    for (int length = hardwareAddress.length, i = 0; i < length; ++i) {
                        sb.append(String.format("%02X:", hardwareAddress[i]));
                    }
                    if (sb.length() > 0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    hashMap.put(networkInterface.getName(), sb.toString());
                }
            }
        }
        catch (Exception ex) {
            j.d("Error while getting MAC address", ex);
        }
        return hashMap;
    }
    
    public boolean a() {
        final NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }
    
    public boolean b() {
        Label_0023: {
            if (this.a != null) {
                break Label_0023;
            }
            while (true) {
                try {
                    this.a = (ConnectivityManager)this.b.getSystemService("connectivity");
                    final NetworkInfo activeNetworkInfo = this.a.getActiveNetworkInfo();
                    if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
                        return true;
                    }
                }
                catch (IllegalArgumentException ex) {
                    j.c("Problem retrieving connectivity service, assuming connected: " + ex);
                    this.a = null;
                    return true;
                }
                return false;
            }
        }
    }
}
