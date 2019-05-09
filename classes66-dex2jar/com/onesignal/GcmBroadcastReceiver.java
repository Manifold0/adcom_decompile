// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.annotation.TargetApi;
import android.os.PersistableBundle;
import android.app.job.JobInfo$Builder;
import android.content.ComponentName;
import java.security.SecureRandom;
import android.app.job.JobScheduler;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver
{
    private static final String GCM_RECEIVE_ACTION = "com.google.android.c2dm.intent.RECEIVE";
    private static final String GCM_TYPE = "gcm";
    private static final String MESSAGE_TYPE_EXTRA_KEY = "message_type";
    
    private static boolean isGcmMessage(final Intent intent) {
        boolean b = false;
        if ("com.google.android.c2dm.intent.RECEIVE".equals(intent.getAction())) {
            final String stringExtra = intent.getStringExtra("message_type");
            if (stringExtra != null) {
                b = b;
                if (!"gcm".equals(stringExtra)) {
                    return b;
                }
            }
            b = true;
        }
        return b;
    }
    
    private static NotificationBundleProcessor.ProcessedBundleResult processOrderBroadcast(final Context context, final Intent intent, final Bundle bundle) {
        Object processBundleFromReceiver;
        if (!isGcmMessage(intent)) {
            processBundleFromReceiver = null;
        }
        else {
            final NotificationBundleProcessor.ProcessedBundleResult processedBundleResult = (NotificationBundleProcessor.ProcessedBundleResult)(processBundleFromReceiver = NotificationBundleProcessor.processBundleFromReceiver(context, bundle));
            if (!processedBundleResult.processed()) {
                startGCMService(context, bundle);
                return processedBundleResult;
            }
        }
        return (NotificationBundleProcessor.ProcessedBundleResult)processBundleFromReceiver;
    }
    
    private void setAbort() {
        if (this.isOrderedBroadcast()) {
            this.abortBroadcast();
            this.setResultCode(-1);
        }
    }
    
    private static BundleCompat setCompatBundleForServer(final Bundle bundle, final BundleCompat bundleCompat) {
        bundleCompat.putString("json_payload", NotificationBundleProcessor.bundleAsJSONObject(bundle).toString());
        bundleCompat.putLong("timestamp", System.currentTimeMillis() / 1000L);
        return bundleCompat;
    }
    
    private void setSuccessfulResultCode() {
        if (this.isOrderedBroadcast()) {
            this.setResultCode(-1);
        }
    }
    
    private static void startGCMService(final Context context, final Bundle bundle) {
        if (!NotificationBundleProcessor.hasRemoteResource(bundle)) {
            NotificationBundleProcessor.ProcessFromGCMIntentService(context, setCompatBundleForServer(bundle, BundleCompatFactory.getInstance()), null);
            return;
        }
        boolean b;
        if (Integer.parseInt(bundle.getString("pri", "0")) > 9) {
            b = true;
        }
        else {
            b = false;
        }
        if (!b && Build$VERSION.SDK_INT >= 26) {
            startGCMServiceWithJobScheduler(context, bundle);
            return;
        }
        try {
            startGCMServiceWithWakefulService(context, bundle);
        }
        catch (IllegalStateException ex) {
            if (Build$VERSION.SDK_INT >= 21) {
                startGCMServiceWithJobScheduler(context, bundle);
                return;
            }
            throw ex;
        }
    }
    
    @TargetApi(21)
    private static void startGCMServiceWithJobScheduler(final Context context, final Bundle bundle) {
        ((JobScheduler)context.getSystemService("jobscheduler")).schedule(new JobInfo$Builder(new SecureRandom().nextInt(), new ComponentName(context.getPackageName(), GcmIntentJobService.class.getName())).setOverrideDeadline(0L).setExtras((PersistableBundle)setCompatBundleForServer(bundle, BundleCompatFactory.getInstance()).getBundle()).build());
    }
    
    private static void startGCMServiceWithWakefulService(final Context context, final Bundle bundle) {
        startWakefulService(context, new Intent().replaceExtras((Bundle)setCompatBundleForServer(bundle, new BundleCompatBundle()).getBundle()).setComponent(new ComponentName(context.getPackageName(), GcmIntentService.class.getName())));
    }
    
    public void onReceive(final Context appContext, final Intent intent) {
        final Bundle extras = intent.getExtras();
        if (extras == null || "google.com/iid".equals(extras.getString("from"))) {
            return;
        }
        OneSignal.setAppContext(appContext);
        final NotificationBundleProcessor.ProcessedBundleResult processOrderBroadcast = processOrderBroadcast(appContext, intent, extras);
        if (processOrderBroadcast == null) {
            this.setSuccessfulResultCode();
            return;
        }
        if (processOrderBroadcast.isDup || processOrderBroadcast.hasExtenderService) {
            this.setAbort();
            return;
        }
        if (processOrderBroadcast.isOneSignalPayload && OneSignal.getFilterOtherGCMReceivers(appContext)) {
            this.setAbort();
            return;
        }
        this.setSuccessfulResultCode();
    }
}
