package com.kongregate.p000o.p003a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.C0566n;
import com.unity.purchasing.googleplay.IabHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TimeZone;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.a.c */
public class C0580c {
    /* renamed from: a */
    static final double[] f800a = new double[]{0.99d, 1.99d, 2.99d, 3.99d, 4.99d, 5.99d, 6.99d, 7.99d, 8.99d, 9.99d, 10.99d, 11.99d, 12.99d, 13.99d, 14.99d, 15.99d, 16.99d, 17.99d, 18.99d, 19.99d, 20.99d, 21.99d, 22.99d, 23.99d, 24.99d, 25.99d, 26.99d, 27.99d, 28.99d, 29.99d, 30.99d, 31.99d, 32.99d, 33.99d, 34.99d, 35.99d, 36.99d, 37.99d, 38.99d, 39.99d, 40.99d, 41.99d, 42.99d, 43.99d, 44.99d, 45.99d, 46.99d, 47.99d, 48.99d, 49.99d, 54.99d, 59.99d, 64.99d, 69.99d, 74.99d, 79.99d, 84.99d, 89.99d, 94.99d, 99.99d, 109.99d, 119.99d, 124.99d, 129.99d, 139.99d, 149.99d, 159.99d, 169.99d, 174.99d, 179.99d, 189.99d, 199.99d, 209.99d, 219.99d, 229.99d, 239.99d, 249.99d, 299.99d, 349.99d, 399.99d, 449.99d, 499.99d, 599.99d, 699.99d, 799.99d, 899.99d, 999.99d};

    /* renamed from: a */
    public static String m841a(String str) {
        if (str == null) {
            C0562j.m759c("Unable to parse null product ID");
            return "";
        }
        int lastIndexOf = str.lastIndexOf(".");
        if (lastIndexOf < 0) {
            return str;
        }
        if (lastIndexOf + 1 < str.length()) {
            return str.substring(lastIndexOf + 1);
        }
        C0562j.m759c("Unable to parse invalid product ID: " + str);
        return "";
    }

    /* renamed from: b */
    public static double m842b(String str) {
        String a = C0580c.m841a(str);
        if (a == null || a.length() < 4 || !a.startsWith("t")) {
            C0562j.m759c("unable to parse item price: " + str);
            return 0.0d;
        }
        try {
            int parseInt = Integer.parseInt(a.substring(1, 3));
            if (parseInt > 0 && parseInt <= f800a.length) {
                return f800a[parseInt - 1];
            }
            C0562j.m759c("parseItemPrice - invalid pricing tier: " + str);
            return 0.0d;
        } catch (NumberFormatException e) {
            C0562j.m759c("parseItemPrice - invalid pricing tier: " + str);
            return 0.0d;
        }
    }

    /* renamed from: a */
    public static double m837a(final Context context, final String str) {
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference(Double.valueOf(0.0d));
        ServiceConnection c05791 = new ServiceConnection() {
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                String packageName = context.getPackageName();
                Bundle bundle = new Bundle();
                ArrayList arrayList = new ArrayList();
                arrayList.add(str);
                bundle.putStringArrayList(IabHelper.GET_SKU_DETAILS_ITEM_LIST, arrayList);
                try {
                    IInterface iInterface = (IInterface) Class.forName("com.android.vending.billing.IInAppBillingService$Stub").getMethod("asInterface", new Class[]{IBinder.class}).invoke(null, new Object[]{iBinder});
                    Bundle bundle2 = (Bundle) iInterface.getClass().getMethod("getSkuDetails", new Class[]{Integer.TYPE, String.class, String.class, Bundle.class}).invoke(iInterface, new Object[]{Integer.valueOf(3), packageName, "inapp", bundle});
                    if (bundle2 == null) {
                        C0562j.m759c("getItemPriceFromBillingService - unable to retrieve sku details");
                    }
                    Iterator it = bundle2.getStringArrayList(IabHelper.RESPONSE_GET_SKU_DETAILS_LIST).iterator();
                    while (it.hasNext()) {
                        String str = (String) it.next();
                        C0562j.m756b("Got sku details: " + str);
                        JSONObject c = C0561i.m749c(str);
                        if (c == null || !"USD".equals(c.optString("price_currency_code"))) {
                            C0562j.m759c("SKU details are not in USD. ignoring result.-");
                        } else {
                            atomicReference.set(Double.valueOf(((double) c.optLong("price_amount_micros", 0)) / 1000000.0d));
                        }
                    }
                } catch (Throwable e) {
                    C0562j.m760c("getItemPriceFromBillingService - Exception using reflection to access billing service: ", e);
                } finally {
                    countDownLatch.countDown();
                }
            }

            public void onServiceDisconnected(ComponentName componentName) {
                C0562j.m759c("getItemPriceFromBillingService - disconnected from service");
                countDownLatch.countDown();
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        if (context.getPackageManager().queryIntentServices(intent, 0).isEmpty()) {
            C0562j.m759c("getItemPriceFromBillingService - failed to bind to billing service");
        } else {
            context.bindService(intent, c05791, 1);
        }
        try {
            countDownLatch.await(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            C0562j.m759c("getItemPriceFromBillingService - latch interupted");
        }
        if (c05791 != null) {
            context.unbindService(c05791);
        }
        return ((Double) atomicReference.get()).doubleValue();
    }

    /* renamed from: a */
    public static int m838a(long j) {
        return C0580c.m839a(j, (long) TimeZone.getDefault().getOffset(j));
    }

    /* renamed from: a */
    public static int m839a(long j, long j2) {
        return (int) ((j + j2) / 86400000);
    }

    /* renamed from: b */
    public static int m843b(long j) {
        return C0580c.m838a(System.currentTimeMillis()) - C0580c.m838a(j);
    }

    /* renamed from: b */
    public static int m844b(long j, long j2) {
        return C0580c.m838a(System.currentTimeMillis()) - C0580c.m839a(j, j2);
    }

    /* renamed from: a */
    public static String m840a(Context context) {
        Object a = C0566n.m779a("android.support.v4.app.NotificationManagerCompat", "from", new Class[]{Context.class}, context);
        if (a != null) {
            Object a2 = C0566n.m781a(true, "android.support.v4.app.NotificationManagerCompat", "areNotificationsEnabled", a, new Class[0], new Object[0]);
            if (a2 instanceof Boolean) {
                return ((Boolean) a2).booleanValue() ? "true" : "false";
            }
        }
        return "unknown";
    }
}
