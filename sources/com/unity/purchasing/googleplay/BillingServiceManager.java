package com.unity.purchasing.googleplay;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;
import com.android.vending.billing.IInAppBillingService;
import com.android.vending.billing.IInAppBillingService.Stub;
import com.facebook.appevents.AppEventsConstants;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BillingServiceManager implements IBillingServiceManager {
    private final ConcurrentLinkedQueue<BillingServiceProcessor> callbacks = new ConcurrentLinkedQueue();
    private Context context;
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Intent googlePlayBillingServiceIntent;
    private boolean mIsBound = false;
    private volatile IInAppBillingService mService;
    private volatile ServiceConnection mServiceConn;

    /* renamed from: com.unity.purchasing.googleplay.BillingServiceManager$2 */
    class C27002 implements Runnable {
        C27002() {
        }

        public void run() {
            if (BillingServiceManager.this.mService != null) {
                while (BillingServiceManager.this.callbacks.size() > 0) {
                    BillingServiceManager.this.logDebug("invoking callback");
                    ((BillingServiceProcessor) BillingServiceManager.this.callbacks.remove()).workWith(BillingServiceManager.this.mService);
                }
                return;
            }
            BillingServiceManager.this.bindToGooglePlayService();
        }
    }

    public BillingServiceManager(final Context context) {
        this.context = context;
        if (context == null) {
            logDebug("Unable to create BillingService Instance, invalid context");
        }
        this.mServiceConn = new ServiceConnection() {

            /* renamed from: com.unity.purchasing.googleplay.BillingServiceManager$1$1 */
            class C26971 implements Runnable {
                C26971() {
                }

                public void run() {
                    if (context != null && BillingServiceManager.this.mIsBound) {
                        context.unbindService(BillingServiceManager.this.mServiceConn);
                        BillingServiceManager.this.mIsBound = false;
                    }
                    BillingServiceManager.this.mService = null;
                    if (BillingServiceManager.this.callbacks.size() == 0) {
                        BillingServiceManager.this.logDebug("Releasing billing service.");
                        return;
                    }
                    BillingServiceManager.this.logDebug("Rebinding billing service.");
                    BillingServiceManager.this.bindToGooglePlayService();
                }
            }

            public void onServiceDisconnected(ComponentName name) {
                BillingServiceManager.this.logDebug("Billing service disconnected.");
                BillingServiceManager.this.executor.execute(new C26971());
            }

            public void onServiceConnected(ComponentName name, final IBinder service) {
                BillingServiceManager.this.logDebug("Billing service connected.");
                BillingServiceManager.this.mIsBound = true;
                BillingServiceManager.this.executor.execute(new Runnable() {
                    public void run() {
                        BillingServiceManager.this.mService = Stub.asInterface(service);
                        BillingServiceManager.this.tryPumpCallbacks();
                    }
                });
            }
        };
    }

    public void initialise() throws GooglePlayBillingUnAvailableException {
        if (this.googlePlayBillingServiceIntent == null) {
            this.googlePlayBillingServiceIntent = getGooglePlayServiceIntent();
        }
    }

    private Intent getGooglePlayServiceIntent() throws GooglePlayBillingUnAvailableException {
        Intent serviceIntent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        serviceIntent.setPackage("com.android.vending");
        List<ResolveInfo> list = this.context.getPackageManager().queryIntentServices(serviceIntent, 0);
        if (list != null && list.size() == 1) {
            return serviceIntent;
        }
        String message = "Expected to find a single Google Play billing service but found " + (list == null ? AppEventsConstants.EVENT_PARAM_VALUE_NO : Integer.valueOf(list.size()));
        logDebug(message);
        throw new GooglePlayBillingUnAvailableException(message);
    }

    void bindToGooglePlayService() {
        this.context.bindService(this.googlePlayBillingServiceIntent, this.mServiceConn, 1);
    }

    public boolean billingAvailable() {
        return false;
    }

    public void workWith(BillingServiceProcessor processor) {
        this.callbacks.add(processor);
        tryPumpCallbacks();
    }

    public void dispose() {
        if (this.context != null && this.mIsBound) {
            this.context.unbindService(this.mServiceConn);
            this.mIsBound = false;
        }
    }

    private void tryPumpCallbacks() {
        this.executor.execute(new C27002());
    }

    private void logDebug(String msg) {
        Log.i("UnityIAP", msg);
    }
}
