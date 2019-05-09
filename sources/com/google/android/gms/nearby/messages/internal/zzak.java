package com.google.android.gms.nearby.messages.internal;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolders;
import com.google.android.gms.common.internal.ClientSettings.Builder;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.nearby.zzgw;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.nearby.messages.MessageListener;
import com.google.android.gms.nearby.messages.MessagesClient;
import com.google.android.gms.nearby.messages.MessagesOptions;
import com.google.android.gms.nearby.messages.PublishOptions;
import com.google.android.gms.nearby.messages.StatusCallback;
import com.google.android.gms.nearby.messages.SubscribeOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzak extends MessagesClient {
    private static final AbstractClientBuilder<zzah, MessagesOptions> CLIENT_BUILDER = new zzau();
    private static final ClientKey<zzah> CLIENT_KEY = new ClientKey();
    private static final Api<MessagesOptions> MESSAGES_API = new Api("Nearby.MESSAGES_API", CLIENT_BUILDER, CLIENT_KEY);
    private final int zzhf;

    public zzak(Activity activity, @Nullable MessagesOptions messagesOptions) {
        super(activity, MESSAGES_API, messagesOptions, Settings.DEFAULT_SETTINGS);
        this.zzhf = 1;
        activity.getApplication().registerActivityLifecycleCallbacks(new zzbc(activity, this, null));
    }

    public zzak(Context context, @Nullable MessagesOptions messagesOptions) {
        super(context, MESSAGES_API, messagesOptions, Settings.DEFAULT_SETTINGS);
        this.zzhf = zzah.zzb(context);
    }

    private final <T> ListenerHolder<ResultHolder<Status>> zza(TaskCompletionSource<T> taskCompletionSource) {
        return registerListener(new zzax(this, taskCompletionSource), Status.class.getName());
    }

    private final <T> Task<Void> zza(ListenerHolder<T> listenerHolder, zzbd zzbd, zzbd zzbd2) {
        return doRegisterEventListener(new zzaz(this, listenerHolder, zzbd), new zzba(this, listenerHolder.getListenerKey(), zzbd2));
    }

    private final Task<Void> zza(zzbd zzbd) {
        return doWrite(new zzbb(this, zzbd));
    }

    private final <T> Task<Void> zza(T t) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        doUnregisterEventListener(ListenerHolders.createListenerKey(t, t.getClass().getName())).addOnCompleteListener(new zzay(this, taskCompletionSource));
        return taskCompletionSource.getTask();
    }

    private final <T> ListenerHolder<T> zzb(T t) {
        return t == null ? null : registerListener(t, t.getClass().getName());
    }

    private final void zzf(int i) {
        zza(new zzat(1));
    }

    protected final Builder createClientSettingsBuilder() {
        Builder createClientSettingsBuilder = super.createClientSettingsBuilder();
        if (getApiOptions() != null) {
            getApiOptions();
        }
        return createClientSettingsBuilder;
    }

    public final void handleIntent(Intent intent, MessageListener messageListener) {
        zzgw.zza(intent, messageListener);
    }

    public final Task<Void> publish(Message message) {
        return publish(message, PublishOptions.DEFAULT);
    }

    public final Task<Void> publish(Message message, PublishOptions publishOptions) {
        Preconditions.checkNotNull(message);
        Preconditions.checkNotNull(publishOptions);
        ListenerHolder zzb = zzb(message);
        return zza(zzb, new zzal(this, message, new zzav(this, zzb(publishOptions.getCallback()), zzb), publishOptions), new zzam(message));
    }

    public final Task<Void> registerStatusCallback(StatusCallback statusCallback) {
        Preconditions.checkNotNull(statusCallback);
        ListenerHolder zzb = zzb(statusCallback);
        return zza(zzb, new zzar(zzb), new zzas(zzb));
    }

    public final Task<Void> subscribe(PendingIntent pendingIntent) {
        return subscribe(pendingIntent, SubscribeOptions.DEFAULT);
    }

    public final Task<Void> subscribe(PendingIntent pendingIntent, SubscribeOptions subscribeOptions) {
        Preconditions.checkNotNull(pendingIntent);
        Preconditions.checkNotNull(subscribeOptions);
        ListenerHolder zzb = zzb(subscribeOptions.getCallback());
        return zza(new zzap(this, pendingIntent, zzb == null ? null : new zzbg(zzb), subscribeOptions));
    }

    public final Task<Void> subscribe(MessageListener messageListener) {
        return subscribe(messageListener, SubscribeOptions.DEFAULT);
    }

    public final Task<Void> subscribe(MessageListener messageListener, SubscribeOptions subscribeOptions) {
        Preconditions.checkNotNull(messageListener);
        Preconditions.checkNotNull(subscribeOptions);
        Preconditions.checkArgument(subscribeOptions.getStrategy().zzae() == 0, "Strategy.setBackgroundScanMode() is only supported by background subscribe (the version which takes a PendingIntent).");
        ListenerHolder zzb = zzb(messageListener);
        return zza(zzb, new zzan(this, zzb, new zzaw(this, zzb(subscribeOptions.getCallback()), zzb), subscribeOptions), new zzao(zzb));
    }

    public final Task<Void> unpublish(Message message) {
        Preconditions.checkNotNull(message);
        return zza((Object) message);
    }

    public final Task<Void> unregisterStatusCallback(StatusCallback statusCallback) {
        Preconditions.checkNotNull(statusCallback);
        return zza((Object) statusCallback);
    }

    public final Task<Void> unsubscribe(PendingIntent pendingIntent) {
        Preconditions.checkNotNull(pendingIntent);
        return zza(new zzaq(pendingIntent));
    }

    public final Task<Void> unsubscribe(MessageListener messageListener) {
        Preconditions.checkNotNull(messageListener);
        return zza((Object) messageListener);
    }

    final /* synthetic */ void zza(PendingIntent pendingIntent, zzbg zzbg, SubscribeOptions subscribeOptions, zzah zzah, ListenerHolder listenerHolder) throws RemoteException {
        zzah.zza(listenerHolder, pendingIntent, (zzab) zzbg, subscribeOptions, this.zzhf);
    }

    final /* synthetic */ void zza(ListenerHolder listenerHolder, zzbg zzbg, SubscribeOptions subscribeOptions, zzah zzah, ListenerHolder listenerHolder2) throws RemoteException {
        zzah.zza(listenerHolder2, listenerHolder, zzbg, subscribeOptions, null, this.zzhf);
    }

    final /* synthetic */ void zza(Message message, zzbe zzbe, PublishOptions publishOptions, zzah zzah, ListenerHolder listenerHolder) throws RemoteException {
        zzah.zza(listenerHolder, zzaf.zza(message), (zzv) zzbe, publishOptions, this.zzhf);
    }
}
