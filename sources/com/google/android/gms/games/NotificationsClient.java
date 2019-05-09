package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class NotificationsClient extends zzu {
    public static final int NOTIFICATION_TYPES_ALL = 19;
    public static final int NOTIFICATION_TYPES_MULTIPLAYER = 3;
    public static final int NOTIFICATION_TYPE_INVITATION = 1;
    public static final int NOTIFICATION_TYPE_LEVEL_UP = 16;
    public static final int NOTIFICATION_TYPE_MATCH_UPDATE = 2;

    NotificationsClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    NotificationsClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Void> clear(int i) {
        return doWrite(new zzao(this, i));
    }

    public Task<Void> clearAll() {
        return clear(19);
    }
}
