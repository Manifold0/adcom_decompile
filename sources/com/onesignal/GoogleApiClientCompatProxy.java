package com.onesignal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.tapjoy.TapjoyConstants;

class GoogleApiClientCompatProxy {
    private final GoogleApiClient googleApiClient;
    private final Class googleApiClientListenerClass;

    GoogleApiClientCompatProxy(GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.googleApiClientListenerClass = googleApiClient.getClass();
    }

    void connect() {
        try {
            this.googleApiClientListenerClass.getMethod(TapjoyConstants.TJC_SDK_TYPE_CONNECT, new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    void disconnect() {
        try {
            this.googleApiClientListenerClass.getMethod("disconnect", new Class[0]).invoke(this.googleApiClient, new Object[0]);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    GoogleApiClient realInstance() {
        return this.googleApiClient;
    }
}
