// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.iid;

import javax.annotation.Nullable;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public interface MessagingChannel
{
    @KeepForSdk
    Task<Void> ackMessage(final String p0);
    
    @KeepForSdk
    Task<Void> buildChannel(final String p0, @Nullable final String p1);
    
    @KeepForSdk
    Task<Void> deleteInstanceId(final String p0);
    
    @KeepForSdk
    Task<Void> deleteToken(final String p0, @Nullable final String p1, final String p2, final String p3);
    
    @KeepForSdk
    Task<String> getToken(final String p0, @Nullable final String p1, final String p2, final String p3);
    
    @KeepForSdk
    boolean isAvailable();
    
    @KeepForSdk
    boolean isChannelBuilt();
    
    @KeepForSdk
    Task<Void> subscribeToTopic(final String p0, final String p1, final String p2);
    
    @KeepForSdk
    Task<Void> unsubscribeFromTopic(final String p0, final String p1, final String p2);
}
