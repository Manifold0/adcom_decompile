package com.kongregate.p000o.p003a;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p003a.C0585g.C0587a;
import com.tonyodev.fetch.FetchConst;
import com.unity.purchasing.googleplay.IabHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.json.JSONObject;

/* renamed from: com.kongregate.o.a.k */
public class C0597k {
    /* renamed from: a */
    private Context f860a;
    /* renamed from: b */
    private HashMap<String, C0596a> f861b = new HashMap();

    /* renamed from: com.kongregate.o.a.k$a */
    public static class C0596a {
        /* renamed from: a */
        private String f858a;
        /* renamed from: b */
        private long f859b;

        C0596a(String str) {
            JSONObject c = C0561i.m749c(str);
            if (c != null) {
                this.f858a = c.optString("price_currency_code");
                this.f859b = c.optLong("price_amount_micros");
            }
        }

        /* renamed from: a */
        public String m910a() {
            return this.f858a;
        }

        /* renamed from: b */
        public double m911b() {
            return ((double) this.f859b) / 1000000.0d;
        }
    }

    public C0597k(Context context) {
        this.f860a = context;
    }

    /* renamed from: a */
    public C0596a m912a(String str) {
        if (this.f861b.containsKey(str)) {
            return (C0596a) this.f861b.get(str);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference atomicReference = new AtomicReference(null);
        ServiceConnection c05951 = new ServiceConnection(this) {
            /* renamed from: c */
            final /* synthetic */ C0597k f857c;

            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                atomicReference.set(C0587a.m877a(iBinder));
                countDownLatch.countDown();
            }

            public void onServiceDisconnected(ComponentName componentName) {
                countDownLatch.countDown();
            }
        };
        Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        List queryIntentServices = this.f860a.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices == null || queryIntentServices.isEmpty()) {
            C0562j.m759c("ProdcutInfoCache: unable to bind to InAppBillingService");
            return null;
        }
        this.f860a.bindService(intent, c05951, 1);
        try {
            C0562j.m753a("ProductInfoCache: wait for connection to InAppBillingService");
            countDownLatch.await(FetchConst.DEFAULT_ON_UPDATE_INTERVAL, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            C0562j.m759c("ProductInfoCache:interrupted waiting to connect to InAppBillingService");
        }
        if (atomicReference.get() == null) {
            C0562j.m759c("ProductInfoCache: unable to connect to InAppBillingService");
            return null;
        }
        Bundle a;
        C0562j.m759c("ProductInfoCache: connected to service");
        ArrayList arrayList = new ArrayList();
        arrayList.add(str);
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(IabHelper.GET_SKU_DETAILS_ITEM_LIST, arrayList);
        try {
            a = ((C0585g) atomicReference.get()).mo1246a(3, this.f860a.getPackageName(), "inapp", bundle);
        } catch (Throwable e2) {
            C0562j.m760c("ProductInfoCache:  exception getting product details: ", e2);
            a = null;
        }
        if (a == null || !a.containsKey(IabHelper.RESPONSE_GET_SKU_DETAILS_LIST)) {
            C0562j.m759c("ProductInfoCache: failed to get product details");
        } else {
            Iterator it = a.getStringArrayList(IabHelper.RESPONSE_GET_SKU_DETAILS_LIST).iterator();
            while (it.hasNext()) {
                String str2 = (String) it.next();
                C0562j.m759c("ProductInfoCache: got detail for product: " + str2);
                this.f861b.put(str, new C0596a(str2));
            }
        }
        if (c05951 != null) {
            this.f860a.unbindService(c05951);
            atomicReference.set(null);
        }
        return (C0596a) this.f861b.get(str);
    }
}
