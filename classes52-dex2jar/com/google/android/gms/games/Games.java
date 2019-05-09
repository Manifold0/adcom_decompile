// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import com.google.android.gms.common.api.Api$Client;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import com.google.android.gms.common.api.Result;
import android.os.Bundle;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;
import com.google.android.gms.common.api.Api$ApiOptions$HasGoogleSignInAccountOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.api.Api$AnyClientKey;
import com.google.android.gms.common.api.Status;
import android.view.View;
import android.content.Intent;
import com.google.android.gms.common.api.internal.BaseImplementation$ApiMethodImpl;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import android.content.Context;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import android.support.annotation.NonNull;
import android.app.Activity;
import com.google.android.gms.internal.games.zzcw;
import com.google.android.gms.internal.games.zzdy;
import com.google.android.gms.internal.games.zzcx;
import com.google.android.gms.internal.games.zzci;
import com.google.android.gms.internal.games.zzca;
import com.google.android.gms.internal.games.zzbo;
import com.google.android.gms.internal.games.zzbd;
import com.google.android.gms.internal.games.zzbe;
import com.google.android.gms.internal.games.zzbc;
import com.google.android.gms.internal.games.zzbz;
import com.google.android.gms.internal.games.zzdb;
import com.google.android.gms.internal.games.zzai;
import com.google.android.gms.internal.games.zzam;
import com.google.android.gms.internal.games.zzv;
import com.google.android.gms.internal.games.zzt;
import com.google.android.gms.internal.games.zzf;
import com.google.android.gms.internal.games.zzad;
import com.google.android.gms.internal.games.zzep;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.common.api.Api$AbstractClientBuilder;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
@VisibleForTesting
public final class Games
{
    @Deprecated
    public static final Api<GamesOptions> API;
    @Deprecated
    public static final Achievements Achievements;
    private static final Api$AbstractClientBuilder<zze, GamesOptions> CLIENT_BUILDER;
    static final Api$ClientKey<zze> CLIENT_KEY;
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final String EXTRA_STATUS = "status";
    @Deprecated
    public static final Events Events;
    @Deprecated
    public static final GamesMetadata GamesMetadata;
    @Deprecated
    public static final Invitations Invitations;
    @Deprecated
    public static final Leaderboards Leaderboards;
    @Deprecated
    public static final Notifications Notifications;
    @Deprecated
    public static final Players Players;
    @Deprecated
    public static final Quests Quests;
    @Deprecated
    public static final RealTimeMultiplayer RealTimeMultiplayer;
    @Deprecated
    public static final Requests Requests;
    public static final Scope SCOPE_GAMES;
    public static final Scope SCOPE_GAMES_LITE;
    @Deprecated
    public static final Snapshots Snapshots;
    @Deprecated
    public static final Stats Stats;
    @Deprecated
    public static final TurnBasedMultiplayer TurnBasedMultiplayer;
    @Deprecated
    public static final Videos Videos;
    private static final Api$AbstractClientBuilder<zze, GamesOptions> zzak;
    public static final Scope zzal;
    private static final Api<GamesOptions> zzam;
    private static final com.google.android.gms.internal.games.zze zzan;
    private static final Multiplayer zzao;
    private static final zzep zzap;
    
    static {
        CLIENT_KEY = new Api$ClientKey();
        CLIENT_BUILDER = new zzi();
        zzak = new zzj();
        SCOPE_GAMES = new Scope("https://www.googleapis.com/auth/games");
        SCOPE_GAMES_LITE = new Scope("https://www.googleapis.com/auth/games_lite");
        API = new Api("Games.API", (Api$AbstractClientBuilder)Games.CLIENT_BUILDER, (Api$ClientKey)Games.CLIENT_KEY);
        zzal = new Scope("https://www.googleapis.com/auth/games.firstparty");
        zzam = new Api("Games.API_1P", (Api$AbstractClientBuilder)Games.zzak, (Api$ClientKey)Games.CLIENT_KEY);
        GamesMetadata = new zzad();
        Achievements = new zzf();
        zzan = (com.google.android.gms.internal.games.zze)new zzt();
        Events = new zzv();
        Leaderboards = new zzam();
        Invitations = new zzai();
        TurnBasedMultiplayer = new zzdb();
        RealTimeMultiplayer = new zzbz();
        zzao = new zzbc();
        Players = new zzbe();
        Notifications = new zzbd();
        Quests = new zzbo();
        Requests = new zzca();
        Snapshots = new zzci();
        Stats = new zzcx();
        Videos = new zzdy();
        zzap = (zzep)new zzcw();
    }
    
    private Games() {
    }
    
    public static AchievementsClient getAchievementsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new AchievementsClient(activity, zza(googleSignInAccount));
    }
    
    public static AchievementsClient getAchievementsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new AchievementsClient(context, zza(googleSignInAccount));
    }
    
    @Deprecated
    public static String getAppId(final GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzan();
    }
    
    @Deprecated
    @RequiresPermission("android.permission.GET_ACCOUNTS")
    public static String getCurrentAccountName(final GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzq();
    }
    
    public static EventsClient getEventsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new EventsClient(activity, zza(googleSignInAccount));
    }
    
    public static EventsClient getEventsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new EventsClient(context, zza(googleSignInAccount));
    }
    
    public static GamesClient getGamesClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new GamesClient(activity, zza(googleSignInAccount));
    }
    
    public static GamesClient getGamesClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new GamesClient(context, zza(googleSignInAccount));
    }
    
    public static GamesMetadataClient getGamesMetadataClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new GamesMetadataClient(activity, zza(googleSignInAccount));
    }
    
    public static GamesMetadataClient getGamesMetadataClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new GamesMetadataClient(context, zza(googleSignInAccount));
    }
    
    @Deprecated
    @KeepForSdk
    public static PendingResult<GetServerAuthCodeResult> getGamesServerAuthCode(final GoogleApiClient googleApiClient, final String s) {
        Preconditions.checkNotEmpty(s, (Object)"Please provide a valid serverClientId");
        return (PendingResult<GetServerAuthCodeResult>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzk(googleApiClient, s));
    }
    
    public static InvitationsClient getInvitationsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new InvitationsClient(activity, zza(googleSignInAccount));
    }
    
    public static InvitationsClient getInvitationsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new InvitationsClient(context, zza(googleSignInAccount));
    }
    
    public static LeaderboardsClient getLeaderboardsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new LeaderboardsClient(activity, zza(googleSignInAccount));
    }
    
    public static LeaderboardsClient getLeaderboardsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new LeaderboardsClient(context, zza(googleSignInAccount));
    }
    
    public static NotificationsClient getNotificationsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new NotificationsClient(activity, zza(googleSignInAccount));
    }
    
    public static NotificationsClient getNotificationsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new NotificationsClient(context, zza(googleSignInAccount));
    }
    
    public static PlayerStatsClient getPlayerStatsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new PlayerStatsClient(activity, zza(googleSignInAccount));
    }
    
    public static PlayerStatsClient getPlayerStatsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new PlayerStatsClient(context, zza(googleSignInAccount));
    }
    
    public static PlayersClient getPlayersClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new PlayersClient(activity, zza(googleSignInAccount));
    }
    
    public static PlayersClient getPlayersClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new PlayersClient(context, zza(googleSignInAccount));
    }
    
    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(activity, zza(googleSignInAccount));
    }
    
    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(context, zza(googleSignInAccount));
    }
    
    @Deprecated
    public static int getSdkVariant(final GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzal();
    }
    
    @Deprecated
    public static Intent getSettingsIntent(final GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaj();
    }
    
    public static SnapshotsClient getSnapshotsClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new SnapshotsClient(activity, zza(googleSignInAccount));
    }
    
    public static SnapshotsClient getSnapshotsClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new SnapshotsClient(context, zza(googleSignInAccount));
    }
    
    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(activity, zza(googleSignInAccount));
    }
    
    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(context, zza(googleSignInAccount));
    }
    
    public static VideosClient getVideosClient(@NonNull final Activity activity, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new VideosClient(activity, zza(googleSignInAccount));
    }
    
    public static VideosClient getVideosClient(@NonNull final Context context, @NonNull final GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull((Object)googleSignInAccount, (Object)"GoogleSignInAccount must not be null");
        return new VideosClient(context, zza(googleSignInAccount));
    }
    
    @Deprecated
    public static void setGravityForPopups(final GoogleApiClient googleApiClient, final int n) {
        final zze zza = zza(googleApiClient, false);
        if (zza != null) {
            zza.zzj(n);
        }
    }
    
    @Deprecated
    public static void setViewForPopups(final GoogleApiClient googleApiClient, final View view) {
        Preconditions.checkNotNull((Object)view);
        final zze zza = zza(googleApiClient, false);
        if (zza != null) {
            zza.zza(view);
        }
    }
    
    @Deprecated
    public static PendingResult<Status> signOut(final GoogleApiClient googleApiClient) {
        return (PendingResult<Status>)googleApiClient.execute((BaseImplementation$ApiMethodImpl)new zzl(googleApiClient));
    }
    
    private static GamesOptions zza(@NonNull final GoogleSignInAccount zzbb) {
        final Builder builder = new Builder(null, null);
        builder.zzbb = zzbb;
        return builder.setSdkVariant(1052947).build();
    }
    
    public static zze zza(final GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true);
    }
    
    public static zze zza(final GoogleApiClient googleApiClient, final boolean b) {
        Preconditions.checkArgument(googleApiClient != null, (Object)"GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), (Object)"GoogleApiClient must be connected.");
        return zzb(googleApiClient, b);
    }
    
    public static zze zzb(final GoogleApiClient googleApiClient, final boolean b) {
        Preconditions.checkState(googleApiClient.hasApi((Api)Games.API), (Object)"GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        final boolean hasConnectedApi = googleApiClient.hasConnectedApi((Api)Games.API);
        if (b && !hasConnectedApi) {
            throw new IllegalStateException("GoogleApiClient has an optional Games.API and is not connected to Games. Use GoogleApiClient.hasConnectedApi(Games.API) to guard this call.");
        }
        if (hasConnectedApi) {
            return (zze)googleApiClient.getClient((Api$AnyClientKey)Games.CLIENT_KEY);
        }
        return null;
    }
    
    @Deprecated
    public static final class GamesOptions implements GoogleSignInOptionsExtension, Api$ApiOptions$HasGoogleSignInAccountOptions, Api$ApiOptions$Optional
    {
        public final boolean zzar;
        public final boolean zzas;
        public final int zzat;
        public final boolean zzau;
        public final int zzav;
        public final String zzaw;
        public final ArrayList<String> zzax;
        public final boolean zzay;
        public final boolean zzaz;
        public final boolean zzba;
        public final GoogleSignInAccount zzbb;
        
        private GamesOptions(final boolean zzar, final boolean zzas, final int zzat, final boolean zzau, final int zzav, final String zzaw, final ArrayList<String> zzax, final boolean zzay, final boolean zzaz, final boolean zzba, final GoogleSignInAccount zzbb) {
            this.zzar = zzar;
            this.zzas = zzas;
            this.zzat = zzat;
            this.zzau = zzau;
            this.zzav = zzav;
            this.zzaw = zzaw;
            this.zzax = zzax;
            this.zzay = zzay;
            this.zzaz = zzaz;
            this.zzba = zzba;
            this.zzbb = zzbb;
        }
        
        public static Builder builder() {
            return new Builder((zzi)null);
        }
        
        @Override
        public final boolean equals(final Object o) {
            if (o != this) {
                if (!(o instanceof GamesOptions)) {
                    return false;
                }
                final GamesOptions gamesOptions = (GamesOptions)o;
                if (this.zzar == gamesOptions.zzar && this.zzas == gamesOptions.zzas && this.zzat == gamesOptions.zzat && this.zzau == gamesOptions.zzau && this.zzav == gamesOptions.zzav) {
                    if (this.zzaw == null) {
                        if (gamesOptions.zzaw != null) {
                            return false;
                        }
                    }
                    else if (!this.zzaw.equals(gamesOptions.zzaw)) {
                        return false;
                    }
                    if (this.zzax.equals(gamesOptions.zzax) && this.zzay == gamesOptions.zzay && this.zzaz == gamesOptions.zzaz && this.zzba == gamesOptions.zzba) {
                        if (this.zzbb == null) {
                            if (gamesOptions.zzbb == null) {
                                return true;
                            }
                        }
                        else if (this.zzbb.equals((Object)gamesOptions.zzbb)) {
                            return true;
                        }
                    }
                }
                return false;
            }
            return true;
        }
        
        public final int getExtensionType() {
            return 1;
        }
        
        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzbb;
        }
        
        public final List<Scope> getImpliedScopes() {
            if (this.zzay) {
                return Collections.singletonList(Games.SCOPE_GAMES);
            }
            return Collections.singletonList(Games.SCOPE_GAMES_LITE);
        }
        
        @Override
        public final int hashCode() {
            int n = 1;
            int hashCode = 0;
            int n2;
            if (this.zzar) {
                n2 = 1;
            }
            else {
                n2 = 0;
            }
            int n3;
            if (this.zzas) {
                n3 = 1;
            }
            else {
                n3 = 0;
            }
            final int zzat = this.zzat;
            int n4;
            if (this.zzau) {
                n4 = 1;
            }
            else {
                n4 = 0;
            }
            final int zzav = this.zzav;
            int hashCode2;
            if (this.zzaw == null) {
                hashCode2 = 0;
            }
            else {
                hashCode2 = this.zzaw.hashCode();
            }
            final int hashCode3 = this.zzax.hashCode();
            int n5;
            if (this.zzay) {
                n5 = 1;
            }
            else {
                n5 = 0;
            }
            int n6;
            if (this.zzaz) {
                n6 = 1;
            }
            else {
                n6 = 0;
            }
            if (!this.zzba) {
                n = 0;
            }
            if (this.zzbb != null) {
                hashCode = this.zzbb.hashCode();
            }
            return ((n6 + (n5 + ((hashCode2 + ((n4 + ((n3 + (n2 + 527) * 31) * 31 + zzat) * 31) * 31 + zzav) * 31) * 31 + hashCode3) * 31) * 31) * 31 + n) * 31 + hashCode;
        }
        
        public final Bundle toBundle() {
            return this.zzf();
        }
        
        public final Bundle zzf() {
            final Bundle bundle = new Bundle();
            bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.zzar);
            bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.zzas);
            bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.zzat);
            bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.zzau);
            bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.zzav);
            bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.zzaw);
            bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", (ArrayList)this.zzax);
            bundle.putBoolean("com.google.android.gms.games.key.requireGooglePlus", this.zzay);
            bundle.putBoolean("com.google.android.gms.games.key.unauthenticated", this.zzaz);
            bundle.putBoolean("com.google.android.gms.games.key.skipWelcomePopup", this.zzba);
            return bundle;
        }
        
        @Deprecated
        public static final class Builder
        {
            private boolean zzar;
            private boolean zzas;
            private int zzat;
            private boolean zzau;
            private int zzav;
            private String zzaw;
            private ArrayList<String> zzax;
            private boolean zzay;
            private boolean zzaz;
            private boolean zzba;
            GoogleSignInAccount zzbb;
            
            private Builder() {
                this.zzar = false;
                this.zzas = true;
                this.zzat = 17;
                this.zzau = false;
                this.zzav = 4368;
                this.zzaw = null;
                this.zzax = new ArrayList<String>();
                this.zzay = false;
                this.zzaz = false;
                this.zzba = false;
                this.zzbb = null;
            }
            
            private Builder(final GamesOptions gamesOptions) {
                this.zzar = false;
                this.zzas = true;
                this.zzat = 17;
                this.zzau = false;
                this.zzav = 4368;
                this.zzaw = null;
                this.zzax = new ArrayList<String>();
                this.zzay = false;
                this.zzaz = false;
                this.zzba = false;
                this.zzbb = null;
                if (gamesOptions != null) {
                    this.zzar = gamesOptions.zzar;
                    this.zzas = gamesOptions.zzas;
                    this.zzat = gamesOptions.zzat;
                    this.zzau = gamesOptions.zzau;
                    this.zzav = gamesOptions.zzav;
                    this.zzaw = gamesOptions.zzaw;
                    this.zzax = gamesOptions.zzax;
                    this.zzay = gamesOptions.zzay;
                    this.zzaz = gamesOptions.zzaz;
                    this.zzba = gamesOptions.zzba;
                    this.zzbb = gamesOptions.zzbb;
                }
            }
            
            public final GamesOptions build() {
                return new GamesOptions(this.zzar, this.zzas, this.zzat, this.zzau, this.zzav, this.zzaw, this.zzax, this.zzay, this.zzaz, this.zzba, this.zzbb, null);
            }
            
            public final Builder setSdkVariant(final int zzav) {
                this.zzav = zzav;
                return this;
            }
            
            public final Builder setShowConnectingPopup(final boolean zzas) {
                this.zzas = zzas;
                this.zzat = 17;
                return this;
            }
            
            public final Builder setShowConnectingPopup(final boolean zzas, final int zzat) {
                this.zzas = zzas;
                this.zzat = zzat;
                return this;
            }
        }
    }
    
    @Deprecated
    @KeepForSdk
    public interface GetServerAuthCodeResult extends Result
    {
        @KeepForSdk
        String getCode();
    }
    
    public abstract static class zza<R extends Result> extends BaseImplementation$ApiMethodImpl<R, zze>
    {
        public zza(final GoogleApiClient googleApiClient) {
            super((Api$AnyClientKey)Games.CLIENT_KEY, googleApiClient);
        }
    }
    
    private static class zzb extends Api$AbstractClientBuilder<zze, GamesOptions>
    {
        public int getPriority() {
            return 1;
        }
    }
    
    private abstract static class zzc extends zza<GetServerAuthCodeResult>
    {
        private zzc(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
    }
    
    private abstract static class zzd extends zza<Status>
    {
        private zzd(final GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }
    }
}
