package com.google.android.gms.nearby.messages;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.tasks.Task;

public abstract class MessagesClient extends GoogleApi<MessagesOptions> {
    protected MessagesClient(Activity activity, Api<MessagesOptions> api, @Nullable MessagesOptions messagesOptions, Settings settings) {
        super(activity, api, messagesOptions, settings);
    }

    protected MessagesClient(Context context, Api<MessagesOptions> api, @Nullable MessagesOptions messagesOptions, Settings settings) {
        super(context, api, messagesOptions, settings);
    }

    public abstract void handleIntent(@NonNull Intent intent, @NonNull MessageListener messageListener);

    public abstract Task<Void> publish(@NonNull Message message);

    public abstract Task<Void> publish(@NonNull Message message, @NonNull PublishOptions publishOptions);

    public abstract Task<Void> registerStatusCallback(@NonNull StatusCallback statusCallback);

    public abstract Task<Void> subscribe(@NonNull PendingIntent pendingIntent);

    public abstract Task<Void> subscribe(@NonNull PendingIntent pendingIntent, @NonNull SubscribeOptions subscribeOptions);

    public abstract Task<Void> subscribe(@NonNull MessageListener messageListener);

    public abstract Task<Void> subscribe(@NonNull MessageListener messageListener, @NonNull SubscribeOptions subscribeOptions);

    public abstract Task<Void> unpublish(@NonNull Message message);

    public abstract Task<Void> unregisterStatusCallback(@NonNull StatusCallback statusCallback);

    public abstract Task<Void> unsubscribe(@NonNull PendingIntent pendingIntent);

    public abstract Task<Void> unsubscribe(@NonNull MessageListener messageListener);
}
