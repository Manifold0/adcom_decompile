package com.onesignal;

import android.content.ComponentName;
import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions.Builder;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;
import com.google.firebase.messaging.FirebaseMessaging;

class PushRegistratorFCM extends PushRegistratorAbstractGoogle {
    private static final String FCM_APP_NAME = "ONESIGNAL_SDK_FCM_APP_NAME";
    private FirebaseApp firebaseApp;

    PushRegistratorFCM() {
    }

    static void disableFirebaseInstanceIdService(Context context) {
        int componentState = 1;
        if (OSUtils.getResourceString(context, "gcm_defaultSenderId", null) == null) {
            componentState = 2;
        }
        try {
            context.getPackageManager().setComponentEnabledSetting(new ComponentName(context, FirebaseInstanceIdService.class), componentState, 1);
        } catch (NoClassDefFoundError e) {
        } catch (IllegalArgumentException e2) {
        }
    }

    String getProviderName() {
        return FirebaseMessaging.INSTANCE_ID_SCOPE;
    }

    String getToken(String senderId) throws Throwable {
        initFirebaseApp(senderId);
        return FirebaseInstanceId.getInstance(this.firebaseApp).getToken(senderId, FirebaseMessaging.INSTANCE_ID_SCOPE);
    }

    private void initFirebaseApp(String senderId) {
        if (this.firebaseApp == null) {
            this.firebaseApp = FirebaseApp.initializeApp(OneSignal.appContext, new Builder().setGcmSenderId(senderId).setApplicationId("OMIT_ID").setApiKey("OMIT_KEY").build(), FCM_APP_NAME);
        }
    }
}
