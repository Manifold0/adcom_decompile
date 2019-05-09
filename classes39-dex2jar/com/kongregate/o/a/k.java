// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.o.a;

import org.json.JSONObject;
import com.kongregate.android.internal.util.i;
import java.util.Iterator;
import java.util.List;
import android.os.RemoteException;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import com.kongregate.android.internal.util.j;
import android.content.Intent;
import android.os.IBinder;
import android.content.ComponentName;
import android.content.ServiceConnection;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.CountDownLatch;
import java.util.HashMap;
import android.content.Context;

public class k
{
    private Context a;
    private HashMap<String, a> b;
    
    public k(final Context a) {
        this.a = a;
        this.b = new HashMap<String, a>();
    }
    
    public a a(final String s) {
        if (this.b.containsKey(s)) {
            return this.b.get(s);
        }
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        final AtomicReference<g> atomicReference = new AtomicReference<g>(null);
        final ServiceConnection serviceConnection = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                atomicReference.set(g.a.a(binder));
                countDownLatch.countDown();
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                countDownLatch.countDown();
            }
        };
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        final List queryIntentServices = this.a.getPackageManager().queryIntentServices(intent, 0);
        Label_0145: {
            if (queryIntentServices == null || queryIntentServices.isEmpty()) {
                break Label_0145;
            }
            this.a.bindService(intent, (ServiceConnection)serviceConnection, 1);
            while (true) {
                try {
                    j.a("ProductInfoCache: wait for connection to InAppBillingService");
                    countDownLatch.await(2000L, TimeUnit.MILLISECONDS);
                    if (atomicReference.get() == null) {
                        j.c("ProductInfoCache: unable to connect to InAppBillingService");
                        return null;
                    }
                    break Label_0145;
                    j.c("ProdcutInfoCache: unable to bind to InAppBillingService");
                    return null;
                }
                catch (InterruptedException ex2) {
                    j.c("ProductInfoCache:interrupted waiting to connect to InAppBillingService");
                    continue;
                }
                break;
            }
        }
    Label_0240_Outer:
        while (true) {
            j.c("ProductInfoCache: connected to service");
            final ArrayList<String> list = new ArrayList<String>();
            list.add(s);
            final Bundle bundle = new Bundle();
            bundle.putStringArrayList("ITEM_ID_LIST", (ArrayList)list);
            while (true) {
                Bundle a;
                while (true) {
                    try {
                        a = atomicReference.get().a(3, this.a.getPackageName(), "inapp", bundle);
                        if (a == null || !a.containsKey("DETAILS_LIST")) {
                            j.c("ProductInfoCache: failed to get product details");
                            if (serviceConnection != null) {
                                this.a.unbindService((ServiceConnection)serviceConnection);
                                atomicReference.set(null);
                            }
                            return this.b.get(s);
                        }
                    }
                    catch (RemoteException ex) {
                        j.c("ProductInfoCache:  exception getting product details: ", (Throwable)ex);
                        a = null;
                        continue Label_0240_Outer;
                    }
                    break;
                }
                for (final String s2 : a.getStringArrayList("DETAILS_LIST")) {
                    j.c("ProductInfoCache: got detail for product: " + s2);
                    this.b.put(s, new a(s2));
                }
                continue;
            }
        }
    }
    
    public static class a
    {
        private String a;
        private long b;
        
        a(final String s) {
            final JSONObject c = i.c(s);
            if (c != null) {
                this.a = c.optString("price_currency_code");
                this.b = c.optLong("price_amount_micros");
            }
        }
        
        public String a() {
            return this.a;
        }
        
        public double b() {
            return this.b / 1000000.0;
        }
    }
}
