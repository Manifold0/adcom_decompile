// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.amazon.device.messaging.ADMMessageReceiver;
import android.os.Bundle;
import android.content.Context;
import android.content.Intent;
import com.amazon.device.messaging.ADMMessageHandlerBase;

public class ADMMessageHandler extends ADMMessageHandlerBase
{
    public ADMMessageHandler() {
        super("ADMMessageHandler");
    }
    
    protected void onMessage(final Intent intent) {
        final Context applicationContext = this.getApplicationContext();
        final Bundle extras = intent.getExtras();
        if (NotificationBundleProcessor.processBundleFromReceiver(applicationContext, extras).processed()) {
            return;
        }
        final NotificationGenerationJob notificationGenerationJob = new NotificationGenerationJob(applicationContext);
        notificationGenerationJob.jsonPayload = NotificationBundleProcessor.bundleAsJSONObject(extras);
        NotificationBundleProcessor.ProcessJobForDisplay(notificationGenerationJob);
    }
    
    protected void onRegistered(final String s) {
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "ADM registration ID: " + s);
        PushRegistratorADM.fireCallback(s);
    }
    
    protected void onRegistrationError(final String s) {
        OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "ADM:onRegistrationError: " + s);
        if ("INVALID_SENDER".equals(s)) {
            OneSignal.Log(OneSignal.LOG_LEVEL.ERROR, "Please double check that you have a matching package name (NOTE: Case Sensitive), api_key.txt, and the apk was signed with the same Keystore and Alias.");
        }
        PushRegistratorADM.fireCallback(null);
    }
    
    protected void onUnregistered(final String s) {
        OneSignal.Log(OneSignal.LOG_LEVEL.INFO, "ADM:onUnregistered: " + s);
    }
    
    public static class Receiver extends ADMMessageReceiver
    {
        public Receiver() {
            super((Class)ADMMessageHandler.class);
        }
    }
}
