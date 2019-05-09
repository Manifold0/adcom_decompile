package com.kongregate.p000o.p010i;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import com.facebook.places.model.PlaceFields;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

/* renamed from: com.kongregate.o.i.a */
public class C0657a {
    /* renamed from: a */
    private ConnectivityManager f1064a;
    /* renamed from: b */
    private final Context f1065b;

    public C0657a(Context context) {
        this.f1065b = context;
    }

    /* renamed from: a */
    public boolean m1143a() {
        NetworkInfo activeNetworkInfo = this.f1064a.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isAvailable();
    }

    /* renamed from: b */
    public boolean m1144b() {
        boolean z;
        if (this.f1064a == null) {
            try {
                this.f1064a = (ConnectivityManager) this.f1065b.getSystemService("connectivity");
            } catch (IllegalArgumentException e) {
                C0562j.m759c("Problem retrieving connectivity service, assuming connected: " + e);
                this.f1064a = null;
                return true;
            }
        }
        NetworkInfo activeNetworkInfo = this.f1064a.getActiveNetworkInfo();
        if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
            z = false;
        } else {
            z = true;
        }
        return z;
    }

    /* renamed from: c */
    public static String m1139c() {
        try {
            Enumeration networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                Enumeration inetAddresses = ((NetworkInterface) networkInterfaces.nextElement()).getInetAddresses();
                while (inetAddresses.hasMoreElements()) {
                    InetAddress inetAddress = (InetAddress) inetAddresses.nextElement();
                    if (!inetAddress.isLoopbackAddress() && (inetAddress instanceof Inet4Address)) {
                        return inetAddress.getHostAddress();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;
    }

    /* renamed from: d */
    public static String m1140d() {
        Map f = C0657a.m1142f();
        LinkedList linkedList = new LinkedList();
        CharSequence e = C0657a.m1141e();
        if (!StringUtils.m587c(e)) {
            linkedList.add(e);
        }
        linkedList.add("wlan0");
        linkedList.add("tiwlan0");
        linkedList.add("eth0");
        linkedList.add("wlan1");
        linkedList.add("tiwlan1");
        linkedList.add("eth1");
        Iterator it = linkedList.iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (f.containsKey(str)) {
                return (String) f.get(str);
            }
        }
        return null;
    }

    /* renamed from: e */
    private static String m1141e() {
        try {
            return new BufferedReader(new InputStreamReader(Runtime.getRuntime().exec("getprop wifi.interface").getInputStream())).readLine();
        } catch (Throwable e) {
            C0562j.m762d("Error getting wifi interface name", e);
            return null;
        }
    }

    /* renamed from: f */
    private static Map<String, String> m1142f() {
        Map hashMap = new HashMap();
        try {
            for (NetworkInterface networkInterface : Collections.list(NetworkInterface.getNetworkInterfaces())) {
                byte[] hardwareAddress = networkInterface.getHardwareAddress();
                if (hardwareAddress != null) {
                    StringBuilder stringBuilder = new StringBuilder();
                    int length = hardwareAddress.length;
                    for (int i = 0; i < length; i++) {
                        stringBuilder.append(String.format("%02X:", new Object[]{Byte.valueOf(hardwareAddress[i])}));
                    }
                    if (stringBuilder.length() > 0) {
                        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                    }
                    hashMap.put(networkInterface.getName(), stringBuilder.toString());
                }
            }
        } catch (Throwable e) {
            C0562j.m762d("Error while getting MAC address", e);
        }
        return hashMap;
    }

    /* renamed from: a */
    public static String m1138a(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(PlaceFields.PHONE);
        return telephonyManager != null ? telephonyManager.getNetworkOperatorName() : null;
    }
}
