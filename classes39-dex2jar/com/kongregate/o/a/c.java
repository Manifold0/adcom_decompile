// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import com.kongregate.android.internal.util.n;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;
import android.content.Intent;
import org.json.JSONObject;
import java.util.Iterator;
import com.kongregate.android.internal.util.i;
import com.kongregate.android.internal.util.j;
import android.os.IInterface;
import java.util.ArrayList;
import android.os.Bundle;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CountDownLatch;
import android.content.Context;

public class c
{
    static final double[] a;
    
    static {
        a = new double[] { 0.99, 1.99, 2.99, 3.99, 4.99, 5.99, 6.99, 7.99, 8.99, 9.99, 10.99, 11.99, 12.99, 13.99, 14.99, 15.99, 16.99, 17.99, 18.99, 19.99, 20.99, 21.99, 22.99, 23.99, 24.99, 25.99, 26.99, 27.99, 28.99, 29.99, 30.99, 31.99, 32.99, 33.99, 34.99, 35.99, 36.99, 37.99, 38.99, 39.99, 40.99, 41.99, 42.99, 43.99, 44.99, 45.99, 46.99, 47.99, 48.99, 49.99, 54.99, 59.99, 64.99, 69.99, 74.99, 79.99, 84.99, 89.99, 94.99, 99.99, 109.99, 119.99, 124.99, 129.99, 139.99, 149.99, 159.99, 169.99, 174.99, 179.99, 189.99, 199.99, 209.99, 219.99, 229.99, 239.99, 249.99, 299.99, 349.99, 399.99, 449.99, 499.99, 599.99, 699.99, 799.99, 899.99, 999.99 };
    }
    
    public static double a(final Context context, String s) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference<Double> atomicReference = new AtomicReference<Double>(0.0);
        s = (String)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                while (true) {
                    final String packageName = context.getPackageName();
                    final Bundle bundle = new Bundle();
                    final ArrayList<String> list = new ArrayList<String>();
                    list.add(s);
                    bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list);
                    while (true) {
                        try {
                            try {
                                final IInterface interface1 = (IInterface)Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", IBinder.class).invoke(null, binder);
                                final Bundle bundle2 = (Bundle)interface1.getClass().getMethod("getSkuDetails", Integer.TYPE, String.class, String.class, Bundle.class).invoke(interface1, 3, packageName, "inapp", bundle);
                                if (bundle2 == null) {
                                    j.c("getItemPriceFromBillingService - unable to retrieve sku details");
                                }
                                for (final String s : bundle2.getStringArrayList("DETAILS_LIST")) {
                                    j.b("Got sku details: " + s);
                                    final JSONObject c = i.c(s);
                                    if (c == null || !"USD".equals(c.optString("price_currency_code"))) {
                                        break;
                                    }
                                    atomicReference.set(c.optLong("price_amount_micros", 0L) / 1000000.0);
                                }
                            }
                            finally {
                                countDownLatch.countDown();
                            }
                        }
                        catch (Exception ex) {
                            j.c("getItemPriceFromBillingService - Exception using reflection to access billing service: ", ex);
                            countDownLatch.countDown();
                            return;
                        }
                        j.c("SKU details are not in USD. ignoring result.-");
                        continue;
                    }
                }
                countDownLatch.countDown();
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                j.c("getItemPriceFromBillingService - disconnected from service");
                countDownLatch.countDown();
            }
        };
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        Label_0108: {
            if (context.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
                break Label_0108;
            }
            context.bindService(intent, (ServiceConnection)s, 1);
            while (true) {
                try {
                    while (true) {
                        countDownLatch.await(1L, TimeUnit.SECONDS);
                        if (s != null) {
                            context.unbindService((ServiceConnection)s);
                        }
                        return atomicReference.get();
                        j.c("getItemPriceFromBillingService - failed to bind to billing service");
                        continue;
                    }
                }
                catch (InterruptedException ex) {
                    j.c("getItemPriceFromBillingService - latch interupted");
                    continue;
                }
                break;
            }
        }
    }
    
    public static int a(final long n) {
        return a(n, TimeZone.getDefault().getOffset(n));
    }
    
    public static int a(final long n, final long n2) {
        return (int)((n + n2) / 86400000L);
    }
    
    public static String a(final Context context) {
        final Object a = n.a("android.support.v4.app.NotificationManagerCompat", "from", new Class[] { Context.class }, context);
        if (a != null) {
            final Object a2 = n.a(true, "android.support.v4.app.NotificationManagerCompat", "areNotificationsEnabled", a, new Class[0], new Object[0]);
            if (a2 instanceof Boolean) {
                if (a2) {
                    return "true";
                }
                return "false";
            }
        }
        return "unknown";
    }
    
    public static String a(final String s) {
        String s2;
        if (s == null) {
            j.c("Unable to parse null product ID");
            s2 = "";
        }
        else {
            final int lastIndex = s.lastIndexOf(".");
            s2 = s;
            if (lastIndex >= 0) {
                if (lastIndex + 1 < s.length()) {
                    return s.substring(lastIndex + 1);
                }
                j.c("Unable to parse invalid product ID: " + s);
                return "";
            }
        }
        return s2;
    }
    
    public static double b(final String s) {
        final String a = a(s);
        if (a == null || a.length() < 4 || !a.startsWith("t")) {
            j.c("unable to parse item price: " + s);
            return 0.0;
        }
        try {
            final int int1 = Integer.parseInt(a.substring(1, 3));
            if (int1 > 0 && int1 <= c.a.length) {
                return c.a[int1 - 1];
            }
            j.c("parseItemPrice - invalid pricing tier: " + s);
            return 0.0;
        }
        catch (NumberFormatException ex) {
            j.c("parseItemPrice - invalid pricing tier: " + s);
            return 0.0;
        }
    }
    
    public static int b(final long n) {
        return a(System.currentTimeMillis()) - a(n);
    }
    
    public static int b(final long n, final long n2) {
        return a(System.currentTimeMillis()) - a(n, n2);
    }
}
