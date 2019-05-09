// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.content.Context;

class DelayedConsentInitializationParameters
{
    public String appId;
    public Context context;
    public String googleProjectNumber;
    public OneSignal.NotificationOpenedHandler openedHandler;
    public OneSignal.NotificationReceivedHandler receivedHandler;
    
    DelayedConsentInitializationParameters(final Context context, final String googleProjectNumber, final String appId, final OneSignal.NotificationOpenedHandler openedHandler, final OneSignal.NotificationReceivedHandler receivedHandler) {
        this.context = context;
        this.googleProjectNumber = googleProjectNumber;
        this.appId = appId;
        this.openedHandler = openedHandler;
        this.receivedHandler = receivedHandler;
    }
}
