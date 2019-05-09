// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import android.app.PendingIntent;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.PublishCallback;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.internal.nearby.zzgw;
import com.google.android.gms.nearby.messages.MessageListener;
import android.content.Intent;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.nearby.messages.Messages;

public final class zzbi implements Messages
{
    public static final Api$AbstractClientBuilder<zzah, MessagesOptions> CLIENT_BUILDER;
    public static final Api$ClientKey<zzah> CLIENT_KEY;
    public static final zzbi zzij;
    
    static {
        zzij = new zzbi();
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzbj();
    }
    
    private zzbi() {
    }
    
    @Override
    public final PendingResult<Status> getPermissionStatus(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbr(this, googleApiClient));
    }
    
    @Override
    public final void handleIntent(final Intent intent, final MessageListener messageListener) {
        zzgw.zza(intent, messageListener);
    }
    
    @Override
    public final PendingResult<Status> publish(final GoogleApiClient googleApiClient, final Message message) {
        return this.publish(googleApiClient, message, PublishOptions.DEFAULT);
    }
    
    @Override
    public final PendingResult<Status> publish(final GoogleApiClient googleApiClient, final Message message, final PublishOptions publishOptions) {
        final zzbt zzbt = null;
        Preconditions.checkNotNull((Object)message);
        Preconditions.checkNotNull((Object)publishOptions);
        ListenerHolder registerListener;
        if (publishOptions.getCallback() == null) {
            registerListener = null;
        }
        else {
            registerListener = googleApiClient.registerListener((Object)publishOptions.getCallback());
        }
        zzbt zzbt2;
        if (registerListener == null) {
            zzbt2 = zzbt;
        }
        else {
            zzbt2 = new zzbt((ListenerHolder<PublishCallback>)registerListener);
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbl(this, googleApiClient, message, zzbt2, publishOptions));
    }
    
    @Override
    public final PendingResult<Status> registerStatusCallback(final GoogleApiClient googleApiClient, final StatusCallback statusCallback) {
        Preconditions.checkNotNull((Object)statusCallback);
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbs(this, googleApiClient, googleApiClient.registerListener((Object)statusCallback)));
    }
    
    @Override
    public final PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        return this.subscribe(googleApiClient, pendingIntent, SubscribeOptions.DEFAULT);
    }
    
    @Override
    public final PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent, final SubscribeOptions subscribeOptions) {
        final zzbw zzbw = null;
        Preconditions.checkNotNull((Object)pendingIntent);
        Preconditions.checkNotNull((Object)subscribeOptions);
        ListenerHolder registerListener;
        if (subscribeOptions.getCallback() == null) {
            registerListener = null;
        }
        else {
            registerListener = googleApiClient.registerListener((Object)subscribeOptions.getCallback());
        }
        zzbw zzbw2;
        if (registerListener == null) {
            zzbw2 = zzbw;
        }
        else {
            zzbw2 = new zzbw((ListenerHolder<SubscribeCallback>)registerListener);
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbo(this, googleApiClient, pendingIntent, zzbw2, subscribeOptions));
    }
    
    @Override
    public final PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final MessageListener messageListener) {
        return this.subscribe(googleApiClient, messageListener, SubscribeOptions.DEFAULT);
    }
    
    @Override
    public final PendingResult<Status> subscribe(final GoogleApiClient googleApiClient, final MessageListener messageListener, final SubscribeOptions subscribeOptions) {
        final zzbw zzbw = null;
        Preconditions.checkNotNull((Object)messageListener);
        Preconditions.checkNotNull((Object)subscribeOptions);
        Preconditions.checkArgument(subscribeOptions.getStrategy().zzae() == 0, (Object)"Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
        final ListenerHolder registerListener = googleApiClient.registerListener((Object)messageListener);
        ListenerHolder registerListener2;
        if (subscribeOptions.getCallback() == null) {
            registerListener2 = null;
        }
        else {
            registerListener2 = googleApiClient.registerListener((Object)subscribeOptions.getCallback());
        }
        zzbw zzbw2;
        if (registerListener2 == null) {
            zzbw2 = zzbw;
        }
        else {
            zzbw2 = new zzbw((ListenerHolder<SubscribeCallback>)registerListener2);
        }
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbn(this, googleApiClient, registerListener, zzbw2, subscribeOptions));
    }
    
    @Override
    public final PendingResult<Status> unpublish(final GoogleApiClient googleApiClient, final Message message) {
        Preconditions.checkNotNull((Object)message);
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbm(this, googleApiClient, message));
    }
    
    @Override
    public final PendingResult<Status> unregisterStatusCallback(final GoogleApiClient googleApiClient, final StatusCallback statusCallback) {
        Preconditions.checkNotNull((Object)statusCallback);
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbk(this, googleApiClient, googleApiClient.registerListener((Object)statusCallback)));
    }
    
    @Override
    public final PendingResult<Status> unsubscribe(final GoogleApiClient googleApiClient, final PendingIntent pendingIntent) {
        Preconditions.checkNotNull((Object)pendingIntent);
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbq(this, googleApiClient, pendingIntent));
    }
    
    @Override
    public final PendingResult<Status> unsubscribe(final GoogleApiClient googleApiClient, final MessageListener messageListener) {
        Preconditions.checkNotNull((Object)messageListener);
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzbp(this, googleApiClient, googleApiClient.registerListener((Object)messageListener)));
    }
}
