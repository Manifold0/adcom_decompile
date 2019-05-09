package com.google.android.gms.games;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresPermission;
import android.view.View;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.AbstractClientBuilder;
import com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.Api.Client;
import com.google.android.gms.common.api.Api.ClientKey;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ApiMethodImpl;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.internal.zze;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.multiplayer.Multiplayer;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.internal.games.zzad;
import com.google.android.gms.internal.games.zzai;
import com.google.android.gms.internal.games.zzam;
import com.google.android.gms.internal.games.zzbc;
import com.google.android.gms.internal.games.zzbd;
import com.google.android.gms.internal.games.zzbe;
import com.google.android.gms.internal.games.zzbo;
import com.google.android.gms.internal.games.zzbz;
import com.google.android.gms.internal.games.zzca;
import com.google.android.gms.internal.games.zzci;
import com.google.android.gms.internal.games.zzcw;
import com.google.android.gms.internal.games.zzcx;
import com.google.android.gms.internal.games.zzdb;
import com.google.android.gms.internal.games.zzdy;
import com.google.android.gms.internal.games.zzep;
import com.google.android.gms.internal.games.zzf;
import com.google.android.gms.internal.games.zzt;
import com.google.android.gms.internal.games.zzv;
import com.ironsource.mediationsdk.logger.IronSourceError;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@KeepForSdk
@VisibleForTesting
public final class Games {
    @Deprecated
    public static final Api<GamesOptions> API = new Api("Games.API", CLIENT_BUILDER, CLIENT_KEY);
    @Deprecated
    public static final Achievements Achievements = new zzf();
    private static final AbstractClientBuilder<zze, GamesOptions> CLIENT_BUILDER = new zzi();
    static final ClientKey<zze> CLIENT_KEY = new ClientKey();
    public static final String EXTRA_PLAYER_IDS = "players";
    public static final String EXTRA_STATUS = "status";
    @Deprecated
    public static final Events Events = new zzv();
    @Deprecated
    public static final GamesMetadata GamesMetadata = new zzad();
    @Deprecated
    public static final Invitations Invitations = new zzai();
    @Deprecated
    public static final Leaderboards Leaderboards = new zzam();
    @Deprecated
    public static final Notifications Notifications = new zzbd();
    @Deprecated
    public static final Players Players = new zzbe();
    @Deprecated
    public static final Quests Quests = new zzbo();
    @Deprecated
    public static final RealTimeMultiplayer RealTimeMultiplayer = new zzbz();
    @Deprecated
    public static final Requests Requests = new zzca();
    public static final Scope SCOPE_GAMES = new Scope(Scopes.GAMES);
    public static final Scope SCOPE_GAMES_LITE = new Scope(Scopes.GAMES_LITE);
    @Deprecated
    public static final Snapshots Snapshots = new zzci();
    @Deprecated
    public static final Stats Stats = new zzcx();
    @Deprecated
    public static final TurnBasedMultiplayer TurnBasedMultiplayer = new zzdb();
    @Deprecated
    public static final Videos Videos = new zzdy();
    private static final AbstractClientBuilder<zze, GamesOptions> zzak = new zzj();
    public static final Scope zzal = new Scope(FirstPartyScopes.GAMES_1P);
    private static final Api<GamesOptions> zzam = new Api("Games.API_1P", zzak, CLIENT_KEY);
    private static final com.google.android.gms.internal.games.zze zzan = new zzt();
    private static final Multiplayer zzao = new zzbc();
    private static final zzep zzap = new zzcw();

    @Deprecated
    public static final class GamesOptions implements GoogleSignInOptionsExtension, HasGoogleSignInAccountOptions, Optional {
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

        @Deprecated
        public static final class Builder {
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
                this.zzax = new ArrayList();
                this.zzay = false;
                this.zzaz = false;
                this.zzba = false;
                this.zzbb = null;
            }

            private Builder(GamesOptions gamesOptions) {
                this.zzar = false;
                this.zzas = true;
                this.zzat = 17;
                this.zzau = false;
                this.zzav = 4368;
                this.zzaw = null;
                this.zzax = new ArrayList();
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
                return new GamesOptions(this.zzar, this.zzas, this.zzat, this.zzau, this.zzav, this.zzaw, this.zzax, this.zzay, this.zzaz, this.zzba, this.zzbb);
            }

            public final Builder setSdkVariant(int i) {
                this.zzav = i;
                return this;
            }

            public final Builder setShowConnectingPopup(boolean z) {
                this.zzas = z;
                this.zzat = 17;
                return this;
            }

            public final Builder setShowConnectingPopup(boolean z, int i) {
                this.zzas = z;
                this.zzat = i;
                return this;
            }
        }

        private GamesOptions(boolean z, boolean z2, int i, boolean z3, int i2, String str, ArrayList<String> arrayList, boolean z4, boolean z5, boolean z6, GoogleSignInAccount googleSignInAccount) {
            this.zzar = z;
            this.zzas = z2;
            this.zzat = i;
            this.zzau = z3;
            this.zzav = i2;
            this.zzaw = str;
            this.zzax = arrayList;
            this.zzay = z4;
            this.zzaz = z5;
            this.zzba = z6;
            this.zzbb = googleSignInAccount;
        }

        public static Builder builder() {
            return new Builder();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final boolean equals(java.lang.Object r5) {
            /*
            r4 = this;
            r0 = 1;
            r1 = 0;
            if (r5 != r4) goto L_0x0005;
        L_0x0004:
            return r0;
        L_0x0005:
            r2 = r5 instanceof com.google.android.gms.games.Games.GamesOptions;
            if (r2 != 0) goto L_0x000b;
        L_0x0009:
            r0 = r1;
            goto L_0x0004;
        L_0x000b:
            r5 = (com.google.android.gms.games.Games.GamesOptions) r5;
            r2 = r4.zzar;
            r3 = r5.zzar;
            if (r2 != r3) goto L_0x0057;
        L_0x0013:
            r2 = r4.zzas;
            r3 = r5.zzas;
            if (r2 != r3) goto L_0x0057;
        L_0x0019:
            r2 = r4.zzat;
            r3 = r5.zzat;
            if (r2 != r3) goto L_0x0057;
        L_0x001f:
            r2 = r4.zzau;
            r3 = r5.zzau;
            if (r2 != r3) goto L_0x0057;
        L_0x0025:
            r2 = r4.zzav;
            r3 = r5.zzav;
            if (r2 != r3) goto L_0x0057;
        L_0x002b:
            r2 = r4.zzaw;
            if (r2 != 0) goto L_0x0059;
        L_0x002f:
            r2 = r5.zzaw;
            if (r2 != 0) goto L_0x0057;
        L_0x0033:
            r2 = r4.zzax;
            r3 = r5.zzax;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0057;
        L_0x003d:
            r2 = r4.zzay;
            r3 = r5.zzay;
            if (r2 != r3) goto L_0x0057;
        L_0x0043:
            r2 = r4.zzaz;
            r3 = r5.zzaz;
            if (r2 != r3) goto L_0x0057;
        L_0x0049:
            r2 = r4.zzba;
            r3 = r5.zzba;
            if (r2 != r3) goto L_0x0057;
        L_0x004f:
            r2 = r4.zzbb;
            if (r2 != 0) goto L_0x0064;
        L_0x0053:
            r2 = r5.zzbb;
            if (r2 == 0) goto L_0x0004;
        L_0x0057:
            r0 = r1;
            goto L_0x0004;
        L_0x0059:
            r2 = r4.zzaw;
            r3 = r5.zzaw;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0057;
        L_0x0063:
            goto L_0x0033;
        L_0x0064:
            r2 = r4.zzbb;
            r3 = r5.zzbb;
            r2 = r2.equals(r3);
            if (r2 == 0) goto L_0x0057;
        L_0x006e:
            goto L_0x0004;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.games.Games.GamesOptions.equals(java.lang.Object):boolean");
        }

        public final int getExtensionType() {
            return 1;
        }

        public final GoogleSignInAccount getGoogleSignInAccount() {
            return this.zzbb;
        }

        public final List<Scope> getImpliedScopes() {
            return this.zzay ? Collections.singletonList(Games.SCOPE_GAMES) : Collections.singletonList(Games.SCOPE_GAMES_LITE);
        }

        public final int hashCode() {
            int i = 1;
            int i2 = 0;
            int hashCode = ((this.zzaz ? 1 : 0) + (((this.zzay ? 1 : 0) + (((((this.zzaw == null ? 0 : this.zzaw.hashCode()) + (((((this.zzau ? 1 : 0) + (((((this.zzas ? 1 : 0) + (((this.zzar ? 1 : 0) + IronSourceError.ERROR_NON_EXISTENT_INSTANCE) * 31)) * 31) + this.zzat) * 31)) * 31) + this.zzav) * 31)) * 31) + this.zzax.hashCode()) * 31)) * 31)) * 31;
            if (!this.zzba) {
                i = 0;
            }
            hashCode = (hashCode + i) * 31;
            if (this.zzbb != null) {
                i2 = this.zzbb.hashCode();
            }
            return hashCode + i2;
        }

        public final Bundle toBundle() {
            return zzf();
        }

        public final Bundle zzf() {
            Bundle bundle = new Bundle();
            bundle.putBoolean("com.google.android.gms.games.key.isHeadless", this.zzar);
            bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", this.zzas);
            bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", this.zzat);
            bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", this.zzau);
            bundle.putInt("com.google.android.gms.games.key.sdkVariant", this.zzav);
            bundle.putString("com.google.android.gms.games.key.forceResolveAccountKey", this.zzaw);
            bundle.putStringArrayList("com.google.android.gms.games.key.proxyApis", this.zzax);
            bundle.putBoolean("com.google.android.gms.games.key.requireGooglePlus", this.zzay);
            bundle.putBoolean("com.google.android.gms.games.key.unauthenticated", this.zzaz);
            bundle.putBoolean("com.google.android.gms.games.key.skipWelcomePopup", this.zzba);
            return bundle;
        }
    }

    @KeepForSdk
    @Deprecated
    public interface GetServerAuthCodeResult extends Result {
        @KeepForSdk
        String getCode();
    }

    public static abstract class zza<R extends Result> extends ApiMethodImpl<R, zze> {
        public zza(GoogleApiClient googleApiClient) {
            super(Games.CLIENT_KEY, googleApiClient);
        }
    }

    private static abstract class zzb extends AbstractClientBuilder<zze, GamesOptions> {
        private zzb() {
        }

        public /* synthetic */ Client buildClient(Context context, Looper looper, ClientSettings clientSettings, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            GamesOptions gamesOptions = (GamesOptions) obj;
            return new zze(context, looper, clientSettings, gamesOptions == null ? new Builder().build() : gamesOptions, connectionCallbacks, onConnectionFailedListener);
        }

        public int getPriority() {
            return 1;
        }
    }

    private static abstract class zzc extends zza<GetServerAuthCodeResult> {
        private zzc(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return new zzm(this, status);
        }
    }

    private static abstract class zzd extends zza<Status> {
        private zzd(GoogleApiClient googleApiClient) {
            super(googleApiClient);
        }

        public /* synthetic */ Result createFailedResult(Status status) {
            return status;
        }
    }

    private Games() {
    }

    public static AchievementsClient getAchievementsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(activity, zza(googleSignInAccount));
    }

    public static AchievementsClient getAchievementsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new AchievementsClient(context, zza(googleSignInAccount));
    }

    @Deprecated
    public static String getAppId(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzan();
    }

    @RequiresPermission("android.permission.GET_ACCOUNTS")
    @Deprecated
    public static String getCurrentAccountName(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzq();
    }

    public static EventsClient getEventsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(activity, zza(googleSignInAccount));
    }

    public static EventsClient getEventsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new EventsClient(context, zza(googleSignInAccount));
    }

    public static GamesClient getGamesClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(activity, zza(googleSignInAccount));
    }

    public static GamesClient getGamesClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesClient(context, zza(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(activity, zza(googleSignInAccount));
    }

    public static GamesMetadataClient getGamesMetadataClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new GamesMetadataClient(context, zza(googleSignInAccount));
    }

    @KeepForSdk
    @Deprecated
    public static PendingResult<GetServerAuthCodeResult> getGamesServerAuthCode(GoogleApiClient googleApiClient, String str) {
        Preconditions.checkNotEmpty(str, "Please provide a valid serverClientId");
        return googleApiClient.execute(new zzk(googleApiClient, str));
    }

    public static InvitationsClient getInvitationsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(activity, zza(googleSignInAccount));
    }

    public static InvitationsClient getInvitationsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new InvitationsClient(context, zza(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(activity, zza(googleSignInAccount));
    }

    public static LeaderboardsClient getLeaderboardsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new LeaderboardsClient(context, zza(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(activity, zza(googleSignInAccount));
    }

    public static NotificationsClient getNotificationsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new NotificationsClient(context, zza(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(activity, zza(googleSignInAccount));
    }

    public static PlayerStatsClient getPlayerStatsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayerStatsClient(context, zza(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(activity, zza(googleSignInAccount));
    }

    public static PlayersClient getPlayersClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new PlayersClient(context, zza(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(activity, zza(googleSignInAccount));
    }

    public static RealTimeMultiplayerClient getRealTimeMultiplayerClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new RealTimeMultiplayerClient(context, zza(googleSignInAccount));
    }

    @Deprecated
    public static int getSdkVariant(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzal();
    }

    @Deprecated
    public static Intent getSettingsIntent(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true).zzaj();
    }

    public static SnapshotsClient getSnapshotsClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(activity, zza(googleSignInAccount));
    }

    public static SnapshotsClient getSnapshotsClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new SnapshotsClient(context, zza(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(activity, zza(googleSignInAccount));
    }

    public static TurnBasedMultiplayerClient getTurnBasedMultiplayerClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new TurnBasedMultiplayerClient(context, zza(googleSignInAccount));
    }

    public static VideosClient getVideosClient(@NonNull Activity activity, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(activity, zza(googleSignInAccount));
    }

    public static VideosClient getVideosClient(@NonNull Context context, @NonNull GoogleSignInAccount googleSignInAccount) {
        Preconditions.checkNotNull(googleSignInAccount, "GoogleSignInAccount must not be null");
        return new VideosClient(context, zza(googleSignInAccount));
    }

    @Deprecated
    public static void setGravityForPopups(GoogleApiClient googleApiClient, int i) {
        zze zza = zza(googleApiClient, false);
        if (zza != null) {
            zza.zzj(i);
        }
    }

    @Deprecated
    public static void setViewForPopups(GoogleApiClient googleApiClient, View view) {
        Preconditions.checkNotNull(view);
        zze zza = zza(googleApiClient, false);
        if (zza != null) {
            zza.zza(view);
        }
    }

    @Deprecated
    public static PendingResult<Status> signOut(GoogleApiClient googleApiClient) {
        return googleApiClient.execute(new zzl(googleApiClient));
    }

    private static GamesOptions zza(@NonNull GoogleSignInAccount googleSignInAccount) {
        Builder builder = new Builder();
        builder.zzbb = googleSignInAccount;
        return builder.setSdkVariant(1052947).build();
    }

    public static zze zza(GoogleApiClient googleApiClient) {
        return zza(googleApiClient, true);
    }

    public static zze zza(GoogleApiClient googleApiClient, boolean z) {
        Preconditions.checkArgument(googleApiClient != null, "GoogleApiClient parameter is required.");
        Preconditions.checkState(googleApiClient.isConnected(), "GoogleApiClient must be connected.");
        return zzb(googleApiClient, z);
    }

    public static zze zzb(GoogleApiClient googleApiClient, boolean z) {
        Preconditions.checkState(googleApiClient.hasApi(API), "GoogleApiClient is not configured to use the Games Api. Pass Games.API into GoogleApiClient.Builder#addApi() to use this feature.");
        boolean hasConnectedApi = googleApiClient.hasConnectedApi(API);
        if (!z || hasConnectedApi) {
            return hasConnectedApi ? (zze) googleApiClient.getClient(CLIENT_KEY) : null;
        } else {
            throw new IllegalStateException("GoogleApiClient has an optional Games.API and is not connected to Games. Use GoogleApiClient.hasConnectedApi(Games.API) to guard this call.");
        }
    }
}
