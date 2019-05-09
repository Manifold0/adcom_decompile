// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.games;

import android.content.Context;
import com.google.android.gms.common.api.Api$ApiOptions;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi$Settings;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.Games;
import com.google.android.gms.common.api.GoogleApi;

public class zzu extends GoogleApi<Games.GamesOptions>
{
    protected zzu(@NonNull final Activity activity, @Nullable final Games.GamesOptions gamesOptions) {
        super(activity, (Api)Games.API, (Api$ApiOptions)gamesOptions, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
    
    protected zzu(@NonNull final Context context, @Nullable final Games.GamesOptions gamesOptions) {
        super(context, (Api)Games.API, (Api$ApiOptions)gamesOptions, GoogleApi$Settings.DEFAULT_SETTINGS);
    }
}
