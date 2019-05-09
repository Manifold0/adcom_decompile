// 
// Decompiled by Procyon v0.5.34
// 

package com.unity.purchasing.googleplay;

import android.util.Log;
import java.io.Serializable;
import java.util.List;
import com.android.vending.billing.IInAppBillingService$Stub;
import android.os.IBinder;
import android.content.ComponentName;
import java.util.concurrent.Executors;
import android.content.ServiceConnection;
import com.android.vending.billing.IInAppBillingService;
import android.content.Intent;
import java.util.concurrent.ExecutorService;
import android.content.Context;
import java.util.concurrent.ConcurrentLinkedQueue;

public class BillingServiceManager implements IBillingServiceManager
{
    private final ConcurrentLinkedQueue<BillingServiceProcessor> callbacks;
    private Context context;
    private ExecutorService executor;
    private Intent googlePlayBillingServiceIntent;
    private boolean mIsBound;
    private volatile IInAppBillingService mService;
    private volatile ServiceConnection mServiceConn;
    
    public BillingServiceManager(final Context context) {
        this.executor = Executors.newSingleThreadExecutor();
        this.callbacks = new ConcurrentLinkedQueue<BillingServiceProcessor>();
        this.mIsBound = false;
        this.context = context;
        if (context == null) {
            this.logDebug("Unable to create BillingService Instance, invalid context");
        }
        this.mServiceConn = (ServiceConnection)new ServiceConnection() {
            public void onServiceConnected(final ComponentName componentName, final IBinder binder) {
                BillingServiceManager.this.logDebug("Billing service connected.");
                BillingServiceManager.this.mIsBound = true;
                BillingServiceManager.this.executor.execute(new Runnable() {
                    @Override
                    public void run() {
                        BillingServiceManager.this.mService = IInAppBillingService$Stub.asInterface(binder);
                        BillingServiceManager.this.tryPumpCallbacks();
                    }
                });
            }
            
            public void onServiceDisconnected(final ComponentName componentName) {
                BillingServiceManager.this.logDebug("Billing service disconnected.");
                BillingServiceManager.this.executor.execute(new Runnable() {
                    @Override
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
                });
            }
        };
    }
    
    private Intent getGooglePlayServiceIntent() throws GooglePlayBillingUnAvailableException {
        final Intent intent = new Intent("com.android.vending.billing.InAppBillingService.BIND");
        intent.setPackage("com.android.vending");
        final List queryIntentServices = this.context.getPackageManager().queryIntentServices(intent, 0);
        if (queryIntentServices != null && queryIntentServices.size() == 1) {
            return intent;
        }
        final StringBuilder append = new StringBuilder().append("Expected to find a single Google Play billing service but found ");
        Serializable value;
        if (queryIntentServices == null) {
            value = "0";
        }
        else {
            value = queryIntentServices.size();
        }
        final String string = append.append(value).toString();
        this.logDebug(string);
        throw new GooglePlayBillingUnAvailableException(string);
    }
    
    private void logDebug(final String s) {
        Log.i("UnityIAP", s);
    }
    
    private void tryPumpCallbacks() {
        this.executor.execute(new Runnable() {
            @Override
            public void run() {
                if (BillingServiceManager.this.mService != null) {
                    while (BillingServiceManager.this.callbacks.size() > 0) {
                        BillingServiceManager.this.logDebug("invoking callback");
                        ((BillingServiceProcessor)BillingServiceManager.this.callbacks.remove()).workWith(BillingServiceManager.this.mService);
                    }
                }
                else {
                    BillingServiceManager.this.bindToGooglePlayService();
                }
            }
        });
    }
    
    public boolean billingAvailable() {
        return false;
    }
    
    void bindToGooglePlayService() {
        this.context.bindService(this.googlePlayBillingServiceIntent, this.mServiceConn, 1);
    }
    
    @Override
    public void dispose() {
        if (this.context != null && this.mIsBound) {
            this.context.unbindService(this.mServiceConn);
            this.mIsBound = false;
        }
    }
    
    @Override
    public void initialise() throws GooglePlayBillingUnAvailableException {
        if (this.googlePlayBillingServiceIntent == null) {
            this.googlePlayBillingServiceIntent = this.getGooglePlayServiceIntent();
        }
    }
    
    @Override
    public void workWith(final BillingServiceProcessor billingServiceProcessor) {
        this.callbacks.add(billingServiceProcessor);
        this.tryPumpCallbacks();
    }
}
