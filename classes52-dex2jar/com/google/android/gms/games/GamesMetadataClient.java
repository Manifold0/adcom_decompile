// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.games.internal.zzi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.tasks.Task;
import android.content.Context;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.games.internal.zzq;
import com.google.android.gms.common.internal.PendingResultUtil$ResultConverter;
import com.google.android.gms.internal.games.zzu;

public class GamesMetadataClient extends zzu
{
    private static final PendingResultUtil$ResultConverter<GamesMetadata.LoadGamesResult, Game> zzbf;
    private static final zzq<GamesMetadata.LoadGamesResult> zzbg;
    
    static {
        zzbf = (PendingResultUtil$ResultConverter)new zzv();
        zzbg = new zzw();
    }
    
    GamesMetadataClient(@NonNull final Activity activity, @NonNull final Games.GamesOptions gamesOptions) {
        super(activity, gamesOptions);
    }
    
    GamesMetadataClient(@NonNull final Context context, @NonNull final Games.GamesOptions gamesOptions) {
        super(context, gamesOptions);
    }
    
    public Task<Game> getCurrentGame() {
        return (Task<Game>)this.doRead((TaskApiCall)new com.google.android.gms.games.zzu(this));
    }
    
    public Task<AnnotatedData<Game>> loadGame() {
        return zzi.zza(Games.GamesMetadata.loadGame(this.asGoogleApiClient()), GamesMetadataClient.zzbf, GamesMetadataClient.zzbg);
    }
}
