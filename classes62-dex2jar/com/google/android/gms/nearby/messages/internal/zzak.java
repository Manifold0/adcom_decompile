// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.nearby.messages.SubscribeCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.internal.nearby.zzgw;
import android.content.Intent;
import com.google.android.gms.common.internal.ClientSettings$Builder;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.StatusCallback;
import android.app.PendingIntent;
import android.os.RemoteException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.tasks.TaskCompletionSource;
import android.content.Context;
import android.app.Application$ActivityLifecycleCallbacks;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.Nullable;
import android.app.Activity;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.nearby.messages.MessagesClient;

public final class zzak extends MessagesClient
{
    private static final Api$AbstractClientBuilder<zzah, MessagesOptions> CLIENT_BUILDER;
    private static final Api$ClientKey<zzah> CLIENT_KEY;
    private static final Api<MessagesOptions> MESSAGES_API;
    private final int zzhf;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzau();
        MESSAGES_API = new Api("Nearby.MESSAGES_API", (Api$AbstractClientBuilder)zzak.CLIENT_BUILDER, (Api$ClientKey)zzak.CLIENT_KEY);
    }
    
    public zzak(final Activity activity, @Nullable final MessagesOptions messagesOptions) {
        super(activity, zzak.MESSAGES_API, messagesOptions, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzhf = 1;
        activity.getApplication().registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)new zzbc(activity, this, null));
    }
    
    public zzak(final Context context, @Nullable final MessagesOptions messagesOptions) {
        super(context, zzak.MESSAGES_API, messagesOptions, GoogleApi$Settings.DEFAULT_SETTINGS);
        this.zzhf = zzah.zzb(context);
    }
    
    private final <T> ListenerHolder<BaseImplementation$ResultHolder<Status>> zza(final TaskCompletionSource<T> taskCompletionSource) {
        return (ListenerHolder<BaseImplementation$ResultHolder<Status>>)this.registerListener((Object)new zzax(this, taskCompletionSource), Status.class.getName());
    }
    
    private final <T> Task<Void> zza(final ListenerHolder<T> listenerHolder, final zzbd zzbd, final zzbd zzbd2) {
        return (Task<Void>)this.doRegisterEventListener((RegisterListenerMethod)new zzaz(this, listenerHolder, zzbd), (UnregisterListenerMethod)new zzba(this, listenerHolder.getListenerKey(), zzbd2));
    }
    
    private final Task<Void> zza(final zzbd zzbd) {
        return (Task<Void>)this.doWrite((TaskApiCall)new zzbb(this, zzbd));
    }
    
    private final <T> Task<Void> zza(final T t) {
        final TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.doUnregisterEventListener(ListenerHolders.createListenerKey((Object)t, t.getClass().getName())).addOnCompleteListener((OnCompleteListener)new zzay(this, taskCompletionSource));
        return (Task<Void>)taskCompletionSource.getTask();
    }
    
    private final <T> ListenerHolder<T> zzb(final T t) {
        if (t == null) {
            return null;
        }
        return (ListenerHolder<T>)this.registerListener((Object)t, t.getClass().getName());
    }
    
    private final void zzf(final int n) {
        this.zza((zzbd)new zzat(1));
    }
    
    protected final ClientSettings$Builder createClientSettingsBuilder() {
        final ClientSettings$Builder clientSettingsBuilder = super.createClientSettingsBuilder();
        if (this.getApiOptions() != null) {
            this.getApiOptions();
        }
        return clientSettingsBuilder;
    }
    
    @Override
    public final void handleIntent(final Intent intent, final MessageListener messageListener) {
        zzgw.zza(intent, messageListener);
    }
    
    @Override
    public final Task<Void> publish(final Message message) {
        return this.publish(message, PublishOptions.DEFAULT);
    }
    
    @Override
    public final Task<Void> publish(final Message message, final PublishOptions publishOptions) {
        Preconditions.checkNotNull((Object)message);
        Preconditions.checkNotNull((Object)publishOptions);
        final com.google.android.gms.common.api.internal.ListenerHolder<Message> zzb;
        return this.zza((com.google.android.gms.common.api.internal.ListenerHolder<Object>)zzb, new zzal(this, message, new zzav(this, this.zzb(publishOptions.getCallback()), zzb = this.zzb(message)), publishOptions), new zzam(message));
    }
    
    @Override
    public final Task<Void> registerStatusCallback(final StatusCallback statusCallback) {
        Preconditions.checkNotNull((Object)statusCallback);
        final com.google.android.gms.common.api.internal.ListenerHolder<StatusCallback> zzb = this.zzb(statusCallback);
        return this.zza((com.google.android.gms.common.api.internal.ListenerHolder<Object>)zzb, new zzar(zzb), new zzas(zzb));
    }
    
    @Override
    public final Task<Void> subscribe(final PendingIntent pendingIntent) {
        return this.subscribe(pendingIntent, SubscribeOptions.DEFAULT);
    }
    
    @Override
    public final Task<Void> subscribe(final PendingIntent pendingIntent, final SubscribeOptions subscribeOptions) {
        Preconditions.checkNotNull((Object)pendingIntent);
        Preconditions.checkNotNull((Object)subscribeOptions);
        final com.google.android.gms.common.api.internal.ListenerHolder<SubscribeCallback> zzb = this.zzb(subscribeOptions.getCallback());
        zzbg zzbg;
        if (zzb == null) {
            zzbg = null;
        }
        else {
            zzbg = new zzbg(zzb);
        }
        return this.zza((zzbd)new zzap(this, pendingIntent, zzbg, subscribeOptions));
    }
    
    @Override
    public final Task<Void> subscribe(final MessageListener messageListener) {
        return this.subscribe(messageListener, SubscribeOptions.DEFAULT);
    }
    
    @Override
    public final Task<Void> subscribe(final MessageListener messageListener, final SubscribeOptions subscribeOptions) {
        Preconditions.checkNotNull((Object)messageListener);
        Preconditions.checkNotNull((Object)subscribeOptions);
        Preconditions.checkArgument(subscribeOptions.getStrategy().zzae() == 0, (Object)"Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
        final com.google.android.gms.common.api.internal.ListenerHolder<MessageListener> zzb = this.zzb(messageListener);
        return this.zza((com.google.android.gms.common.api.internal.ListenerHolder<Object>)zzb, new zzan(this, zzb, new zzaw(this, this.zzb(subscribeOptions.getCallback()), zzb), subscribeOptions), new zzao(zzb));
    }
    
    @Override
    public final Task<Void> unpublish(final Message message) {
        Preconditions.checkNotNull((Object)message);
        return this.zza(message);
    }
    
    @Override
    public final Task<Void> unregisterStatusCallback(final StatusCallback statusCallback) {
        Preconditions.checkNotNull((Object)statusCallback);
        return this.zza(statusCallback);
    }
    
    @Override
    public final Task<Void> unsubscribe(final PendingIntent pendingIntent) {
        Preconditions.checkNotNull((Object)pendingIntent);
        return this.zza((zzbd)new zzaq(pendingIntent));
    }
    
    @Override
    public final Task<Void> unsubscribe(final MessageListener messageListener) {
        Preconditions.checkNotNull((Object)messageListener);
        return this.zza(messageListener);
    }
}
