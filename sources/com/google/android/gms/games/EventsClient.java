package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events.LoadEventsResult;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class EventsClient extends zzu {
    private static final ResultConverter<LoadEventsResult, EventBuffer> zzj = new zzg();

    EventsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    EventsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public void increment(@NonNull String str, @IntRange(from = 0) int i) {
        doWrite(new zzf(this, str, i));
    }

    public Task<AnnotatedData<EventBuffer>> load(boolean z) {
        return zzi.zzb(Games.Events.load(asGoogleApiClient(), z), zzj);
    }

    public Task<AnnotatedData<EventBuffer>> loadByIds(boolean z, @NonNull String... strArr) {
        return zzi.zzb(Games.Events.loadByIds(asGoogleApiClient(), z, strArr), zzj);
    }
}
