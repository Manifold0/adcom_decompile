package com.google.android.gms.internal.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApi.Settings;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.GamesOptions;

public class zzu extends GoogleApi<GamesOptions> {
    protected zzu(@NonNull Activity activity, @Nullable GamesOptions gamesOptions) {
        super(activity, Games.API, gamesOptions, Settings.DEFAULT_SETTINGS);
    }

    protected zzu(@NonNull Context context, @Nullable GamesOptions gamesOptions) {
        super(context, Games.API, gamesOptions, Settings.DEFAULT_SETTINGS);
    }
}
