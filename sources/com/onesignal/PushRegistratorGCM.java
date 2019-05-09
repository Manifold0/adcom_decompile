package com.onesignal;

import com.google.android.gms.gcm.GoogleCloudMessaging;

class PushRegistratorGCM extends PushRegistratorAbstractGoogle {
    PushRegistratorGCM() {
    }

    String getProviderName() {
        return "GCM";
    }

    String getToken(String senderId) throws Throwable {
        return GoogleCloudMessaging.getInstance(OneSignal.appContext).register(new String[]{senderId});
    }
}
