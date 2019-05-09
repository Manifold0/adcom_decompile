// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import com.google.android.gms.common.api.GoogleApiClient;

class GoogleApiClientCompatProxy
{
    private final GoogleApiClient googleApiClient;
    private final Class googleApiClientListenerClass;
    
    GoogleApiClientCompatProxy(final GoogleApiClient googleApiClient) {
        this.googleApiClient = googleApiClient;
        this.googleApiClientListenerClass = googleApiClient.getClass();
    }
    
    void connect() {
        try {
            this.googleApiClientListenerClass.getMethod("connect", (Class[])new Class[0]).invoke(this.googleApiClient, new Object[0]);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    void disconnect() {
        try {
            this.googleApiClientListenerClass.getMethod("disconnect", (Class[])new Class[0]).invoke(this.googleApiClient, new Object[0]);
        }
        catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    GoogleApiClient realInstance() {
        return this.googleApiClient;
    }
}
