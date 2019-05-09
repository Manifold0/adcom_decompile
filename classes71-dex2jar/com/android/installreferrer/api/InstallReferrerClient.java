// 
// Decompiled by Procyon v0.5.34
// 

package com.android.installreferrer.api;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.os.RemoteException;
import android.support.annotation.UiThread;
import android.support.annotation.NonNull;
import android.content.Context;

public abstract class InstallReferrerClient
{
    @UiThread
    public static Builder newBuilder(@NonNull final Context context) {
        return new Builder(context);
    }
    
    @UiThread
    public abstract void endConnection();
    
    @UiThread
    public abstract ReferrerDetails getInstallReferrer() throws RemoteException;
    
    @UiThread
    public abstract boolean isReady();
    
    @UiThread
    public abstract void startConnection(@NonNull final InstallReferrerStateListener p0);
    
    public static final class Builder
    {
        private final Context mContext;
        
        private Builder(final Context mContext) {
            this.mContext = mContext;
        }
        
        @UiThread
        public InstallReferrerClient build() {
            if (this.mContext == null) {
                throw new IllegalArgumentException("Please provide a valid Context.");
            }
            return new InstallReferrerClientImpl(this.mContext);
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface InstallReferrerResponse {
        public static final int DEVELOPER_ERROR = 3;
        public static final int FEATURE_NOT_SUPPORTED = 2;
        public static final int OK = 0;
        public static final int SERVICE_DISCONNECTED = -1;
        public static final int SERVICE_UNAVAILABLE = 1;
    }
}
