// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.google.android.gms.gcm.GoogleCloudMessaging;

class PushRegistratorGCM extends PushRegistratorAbstractGoogle
{
    @Override
    String getProviderName() {
        return "GCM";
    }
    
    @Override
    String getToken(final String s) throws Throwable {
        return GoogleCloudMessaging.getInstance(OneSignal.appContext).register(new String[] { s });
    }
}
