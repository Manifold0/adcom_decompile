package com.onesignal;

import android.content.Context;
import com.onesignal.OneSignal.NotificationOpenedHandler;
import com.onesignal.OneSignal.NotificationReceivedHandler;

class DelayedConsentInitializationParameters {
    public String appId;
    public Context context;
    public String googleProjectNumber;
    public NotificationOpenedHandler openedHandler;
    public NotificationReceivedHandler receivedHandler;

    DelayedConsentInitializationParameters(Context delayContext, String delayGoogleProjectNumber, String delayAppId, NotificationOpenedHandler delayOpenedHandler, NotificationReceivedHandler delayReceivedHandler) {
        this.context = delayContext;
        this.googleProjectNumber = delayGoogleProjectNumber;
        this.appId = delayAppId;
        this.openedHandler = delayOpenedHandler;
        this.receivedHandler = delayReceivedHandler;
    }
}
