// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.FirebaseOptions$Builder;
import android.content.pm.PackageManager;
import android.content.ComponentName;
import com.google.firebase.iid.FirebaseInstanceIdService;
import android.content.Context;
import com.google.firebase.FirebaseApp;

class PushRegistratorFCM extends PushRegistratorAbstractGoogle
{
    private static final String FCM_APP_NAME = "ONESIGNAL_SDK_FCM_APP_NAME";
    private FirebaseApp firebaseApp;
    
    static void disableFirebaseInstanceIdService(final Context context) {
        int n = 1;
        if (OSUtils.getResourceString(context, "gcm_defaultSenderId", null) == null) {
            n = 2;
        }
        final PackageManager packageManager = context.getPackageManager();
        try {
            packageManager.setComponentEnabledSetting(new ComponentName(context, (Class)FirebaseInstanceIdService.class), n, 1);
        }
        catch (IllegalArgumentException ex) {}
        catch (NoClassDefFoundError noClassDefFoundError) {}
    }
    
    private void initFirebaseApp(final String gcmSenderId) {
        if (this.firebaseApp != null) {
            return;
        }
        this.firebaseApp = FirebaseApp.initializeApp(OneSignal.appContext, new FirebaseOptions$Builder().setGcmSenderId(gcmSenderId).setApplicationId("OMIT_ID").setApiKey("OMIT_KEY").build(), "ONESIGNAL_SDK_FCM_APP_NAME");
    }
    
    @Override
    String getProviderName() {
        return "FCM";
    }
    
    @Override
    String getToken(final String s) throws Throwable {
        this.initFirebaseApp(s);
        return FirebaseInstanceId.getInstance(this.firebaseApp).getToken(s, "FCM");
    }
}
