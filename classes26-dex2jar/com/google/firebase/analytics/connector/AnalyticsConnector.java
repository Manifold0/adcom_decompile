// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.analytics.connector;

import java.util.Set;
import java.util.Map;
import android.support.annotation.WorkerThread;
import java.util.List;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.Size;
import android.support.annotation.NonNull;

public interface AnalyticsConnector
{
    @KeepForSdk
    void clearConditionalUserProperty(@NonNull @Size(max = 24L, min = 1L) final String p0, @Nullable final String p1, @Nullable final Bundle p2);
    
    @WorkerThread
    @KeepForSdk
    List<ConditionalUserProperty> getConditionalUserProperties(@NonNull final String p0, @Nullable @Size(max = 23L, min = 1L) final String p1);
    
    @WorkerThread
    @KeepForSdk
    int getMaxUserProperties(@NonNull @Size(min = 1L) final String p0);
    
    @WorkerThread
    @KeepForSdk
    Map<String, Object> getUserProperties(final boolean p0);
    
    @KeepForSdk
    void logEvent(@NonNull final String p0, @NonNull final String p1, final Bundle p2);
    
    @KeepForSdk
    AnalyticsConnectorHandle registerAnalyticsConnectorListener(final String p0, final AnalyticsConnectorListener p1);
    
    @KeepForSdk
    void setConditionalUserProperty(@NonNull final ConditionalUserProperty p0);
    
    @KeepForSdk
    void setUserProperty(@NonNull final String p0, @NonNull final String p1, final Object p2);
    
    @KeepForSdk
    public interface AnalyticsConnectorHandle
    {
        @KeepForSdk
        void registerEventNames(final Set<String> p0);
        
        @KeepForSdk
        void unregister();
        
        @KeepForSdk
        void unregisterEventNames();
    }
    
    @KeepForSdk
    public interface AnalyticsConnectorListener
    {
        @KeepForSdk
        void onMessageTriggered(final int p0, @Nullable final Bundle p1);
    }
    
    @KeepForSdk
    public static class ConditionalUserProperty
    {
        @KeepForSdk
        public boolean active;
        @KeepForSdk
        public long creationTimestamp;
        @KeepForSdk
        public String expiredEventName;
        @KeepForSdk
        public Bundle expiredEventParams;
        @KeepForSdk
        public String name;
        @KeepForSdk
        public String origin;
        @KeepForSdk
        public long timeToLive;
        @KeepForSdk
        public String timedOutEventName;
        @KeepForSdk
        public Bundle timedOutEventParams;
        @KeepForSdk
        public String triggerEventName;
        @KeepForSdk
        public long triggerTimeout;
        @KeepForSdk
        public String triggeredEventName;
        @KeepForSdk
        public Bundle triggeredEventParams;
        @KeepForSdk
        public long triggeredTimestamp;
        @KeepForSdk
        public Object value;
    }
}
