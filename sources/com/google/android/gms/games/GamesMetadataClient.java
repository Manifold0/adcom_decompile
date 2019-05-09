package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.PendingResultUtil.ResultConverter;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;
import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.internal.games.zzu;
import com.google.android.gms.tasks.Task;

public class GamesMetadataClient extends zzu {
    private static final ResultConverter<LoadGamesResult, Game> zzbf = new zzv();
    private static final zzq<LoadGamesResult> zzbg = new zzw();

    GamesMetadataClient(@NonNull Activity activity, @NonNull GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }

    GamesMetadataClient(@NonNull Context context, @NonNull GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }

    public Task<Game> getCurrentGame() {
        return doRead(new zzu(this));
    }

    public Task<AnnotatedData<Game>> loadGame() {
        return zzi.zza(Games.GamesMetadata.loadGame(asGoogleApiClient()), zzbf, zzbg);
    }
}
