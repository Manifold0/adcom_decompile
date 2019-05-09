// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.internal.TaskApiCall;
import android.support.annotation.IntRange;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class EventsClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<Events.LoadEventsResult, EventBuffer> zzj;
    
    static {
        zzj = (PendingResultUtil$ResultConverter)new zzg();
    }
    
    EventsClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    EventsClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public void increment(@NonNull final String s, @IntRange(from = 0L) final int n) {
        this.doWrite((TaskApiCall)new zzf(this, s, n));
    }
    
    public Task<AnnotatedData<EventBuffer>> load(final boolean b) {
        return zzi.zzb(Games.Events.load(this.asGoogleApiClient(), b), EventsClient.zzj);
    }
    
    public Task<AnnotatedData<EventBuffer>> loadByIds(final boolean b, @NonNull final String... array) {
        return zzi.zzb(Games.Events.loadByIds(this.asGoogleApiClient(), b, array), EventsClient.zzj);
    }
}
