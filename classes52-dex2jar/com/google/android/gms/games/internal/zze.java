// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.internal;

import com.google.android.gms.common.api.internal.DataHolderResult;
import com.google.android.gms.internal.games.zzej;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.internal.games.zzem;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.quest.Quest;
import java.util.List;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.common.api.internal.DataHolderNotifier;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import java.util.Iterator;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.stats.PlayerStatsBuffer;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.common.api.internal.ListenerHolder$Notifier;
import java.util.ArrayList;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.Player;
import android.support.annotation.Nullable;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.stats.Stats;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.video.Videos;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.quest.Quests;
import com.google.android.gms.games.event.Events;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.achievement.Achievements;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.Snapshots;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.Leaderboards;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer;
import com.google.android.gms.games.Players;
import com.google.android.gms.games.request.Requests;
import com.google.android.gms.games.multiplayer.Invitations;
import com.google.android.gms.games.GamesMetadata;
import android.view.View;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import android.graphics.Bitmap;
import android.content.Intent;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collection;
import java.util.HashSet;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.BaseGmsClient$SignOutCallbacks;
import com.google.android.gms.common.ConnectionResult;
import android.support.annotation.NonNull;
import com.google.android.gms.signin.internal.SignInClientImpl;
import android.os.Parcelable;
import com.google.android.gms.common.internal.BinderWrapper;
import android.os.IInterface;
import android.os.IBinder;
import com.google.android.gms.common.internal.BaseGmsClient$ConnectionProgressReportCallbacks;
import com.google.android.gms.games.multiplayer.realtime.zzb;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import android.os.RemoteException;
import android.app.Activity;
import com.google.android.gms.common.api.GoogleApiClient$OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient$ConnectionCallbacks;
import com.google.android.gms.common.internal.ClientSettings;
import android.os.Looper;
import android.content.Context;
import android.os.Bundle;
import com.google.android.gms.games.Games;
import android.os.Binder;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.internal.games.zzel;
import com.google.android.gms.common.internal.GmsClient;

public class zze extends GmsClient<com.google.android.gms.games.internal.zzy>
{
    private zzel zzfp;
    private final String zzfq;
    private PlayerEntity zzfr;
    private GameEntity zzfs;
    private final com.google.android.gms.games.internal.zzac zzft;
    private boolean zzfu;
    private final Binder zzfv;
    private final long zzfw;
    private final Games.GamesOptions zzfx;
    private boolean zzfy;
    private Bundle zzfz;
    
    public zze(final Context context, final Looper looper, final ClientSettings clientSettings, final Games.GamesOptions zzfx, final GoogleApiClient$ConnectionCallbacks googleApiClient$ConnectionCallbacks, final GoogleApiClient$OnConnectionFailedListener googleApiClient$OnConnectionFailedListener) {
        super(context, looper, 1, clientSettings, googleApiClient$ConnectionCallbacks, googleApiClient$OnConnectionFailedListener);
        this.zzfp = new com.google.android.gms.games.internal.zzf(this);
        this.zzfu = false;
        this.zzfy = false;
        this.zzfq = clientSettings.getRealClientPackageName();
        this.zzfv = new Binder();
        this.zzft = com.google.android.gms.games.internal.zzac.zza(this, clientSettings.getGravityForPopups());
        this.zzfw = this.hashCode();
        this.zzfx = zzfx;
        if (!this.zzfx.zzaz && (clientSettings.getViewForPopups() != null || context instanceof Activity)) {
            this.zza(clientSettings.getViewForPopups());
        }
    }
    
    private static void zza(final RemoteException ex) {
        com.google.android.gms.games.internal.zzh.w("GamesClientImpl", "service died", (Throwable)ex);
    }
    
    private static <R> void zza(final BaseImplementation$ResultHolder<R> baseImplementation$ResultHolder, final SecurityException ex) {
        if (baseImplementation$ResultHolder != null) {
            baseImplementation$ResultHolder.setFailedResult(GamesClientStatusCodes.zza(4));
        }
    }
    
    static /* synthetic */ void zza(final zze zze, final RemoteException ex) {
        zza(ex);
    }
    
    static /* synthetic */ void zza(final zze zze, final SecurityException ex) {
        zza(ex);
    }
    
    private static void zza(final SecurityException ex) {
        com.google.android.gms.games.internal.zzh.e("GamesClientImpl", "Is player signed out?", ex);
    }
    
    private static Room zzba(final DataHolder dataHolder) {
        final com.google.android.gms.games.multiplayer.realtime.zzb zzb = new com.google.android.gms.games.multiplayer.realtime.zzb(dataHolder);
        Room room = null;
        try {
            if (zzb.getCount() > 0) {
                room = (Room)((Room)zzb.get(0)).freeze();
            }
            return room;
        }
        finally {
            zzb.release();
        }
    }
    
    public void connect(final BaseGmsClient$ConnectionProgressReportCallbacks baseGmsClient$ConnectionProgressReportCallbacks) {
        this.zzfr = null;
        this.zzfs = null;
        super.connect(baseGmsClient$ConnectionProgressReportCallbacks);
    }
    
    public void disconnect() {
        this.zzfu = false;
        while (true) {
            if (!this.isConnected()) {
                break Label_0043;
            }
            try {
                final com.google.android.gms.games.internal.zzy zzy = (com.google.android.gms.games.internal.zzy)this.getService();
                zzy.zzbd();
                this.zzfp.flush();
                zzy.zza(this.zzfw);
                super.disconnect();
            }
            catch (RemoteException ex) {
                com.google.android.gms.games.internal.zzh.w("GamesClientImpl", "Failed to notify client disconnect.");
                continue;
            }
            break;
        }
    }
    
    public Bundle getConnectionHint() {
        try {
            final Bundle connectionHint = ((com.google.android.gms.games.internal.zzy)this.getService()).getConnectionHint();
            if (connectionHint != null) {
                connectionHint.setClassLoader(zze.class.getClassLoader());
                this.zzfz = connectionHint;
            }
            return connectionHint;
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    protected Bundle getGetServiceRequestExtraArgs() {
        final String string = this.getContext().getResources().getConfiguration().locale.toString();
        final Bundle zzf = this.zzfx.zzf();
        zzf.putString("com.google.android.gms.games.key.gamePackageName", this.zzfq);
        zzf.putString("com.google.android.gms.games.key.desiredLocale", string);
        zzf.putParcelable("com.google.android.gms.games.key.popupWindowToken", (Parcelable)new BinderWrapper(this.zzft.zzjd.zzjb));
        zzf.putInt("com.google.android.gms.games.key.API_VERSION", 6);
        zzf.putBundle("com.google.android.gms.games.key.signInOptions", SignInClientImpl.createBundleFromClientSettings(this.getClientSettings()));
        return zzf;
    }
    
    public int getMinApkVersion() {
        return 12451000;
    }
    
    protected String getServiceDescriptor() {
        return "com.google.android.gms.games.internal.IGamesService";
    }
    
    protected String getStartServiceAction() {
        return "com.google.android.gms.games.service.START";
    }
    
    public void onConnectionFailed(final ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzfu = false;
    }
    
    protected void onPostInitHandler(final int n, final IBinder binder, final Bundle bundle, final int n2) {
        if (n == 0 && bundle != null) {
            bundle.setClassLoader(zze.class.getClassLoader());
            this.zzfu = bundle.getBoolean("show_welcome_popup");
            this.zzfy = this.zzfu;
            this.zzfr = (PlayerEntity)bundle.getParcelable("com.google.android.gms.games.current_player");
            this.zzfs = (GameEntity)bundle.getParcelable("com.google.android.gms.games.current_game");
        }
        super.onPostInitHandler(n, binder, bundle, n2);
    }
    
    public void onUserSignOut(@NonNull final BaseGmsClient$SignOutCallbacks baseGmsClient$SignOutCallbacks) {
        try {
            this.zzb((BaseImplementation$ResultHolder<Status>)new com.google.android.gms.games.internal.zzg(this, baseGmsClient$SignOutCallbacks));
        }
        catch (RemoteException ex) {
            baseGmsClient$SignOutCallbacks.onSignOutComplete();
        }
    }
    
    public boolean requiresSignIn() {
        return true;
    }
    
    protected Set<Scope> validateScopes(final Set<Scope> set) {
        final HashSet<Scope> set2 = new HashSet<Scope>(set);
        final boolean contains = set.contains(Games.SCOPE_GAMES);
        final boolean contains2 = set.contains(Games.SCOPE_GAMES_LITE);
        if (set.contains(Games.zzal)) {
            Preconditions.checkState(!contains, "Cannot have both %s and %s!", new Object[] { "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty" });
        }
        else {
            Preconditions.checkState(contains || contains2, "Games APIs requires %s function.", new Object[] { "https://www.googleapis.com/auth/games_lite" });
            if (contains2 && contains) {
                set2.remove(Games.SCOPE_GAMES_LITE);
                return set2;
            }
        }
        return set2;
    }
    
    public final int zza(final ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder, final byte[] array, final String s, final String s2) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbv(listenerHolder), array, s, s2);
    }
    
    public final int zza(final byte[] array, final String s) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(array, s, null);
    }
    
    public final int zza(final byte[] array, final String s, final String[] array2) {
        Preconditions.checkNotNull((Object)array2, (Object)"Participant IDs must not be null");
        try {
            Preconditions.checkNotNull((Object)array2, (Object)"Participant IDs must not be null");
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(array, s, array2);
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final Intent zza(final int n, final int n2, final boolean b) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zza(n, n2, b);
    }
    
    public final Intent zza(final int n, final byte[] array, final int n2, final Bitmap bitmap, final String s) {
        try {
            final Intent zza = ((com.google.android.gms.games.internal.zzy)this.getService()).zza(n, array, n2, s);
            Preconditions.checkNotNull((Object)bitmap, (Object)"Must provide a non null icon");
            zza.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", (Parcelable)bitmap);
            return zza;
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zza(final PlayerEntity playerEntity) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zza(playerEntity);
    }
    
    public final Intent zza(final Room room, final int n) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zza((RoomEntity)room.freeze(), n);
    }
    
    public final Intent zza(final String s, final int n, final int n2) {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(s, n, n2);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zza(final String s, final boolean b, final boolean b2, final int n) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zza(s, b, b2, n);
    }
    
    public final Intent zza(final int[] array) {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zza(array);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final String zza(final boolean b) throws RemoteException {
        if (this.zzfr != null) {
            return this.zzfr.getPlayerId();
        }
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzbf();
    }
    
    public final void zza(final IBinder binder, final Bundle bundle) {
        if (!this.isConnected()) {
            return;
        }
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(binder, bundle);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zza(final View view) {
        this.zzft.zzb(view);
    }
    
    public final void zza(final BaseImplementation$ResultHolder<GamesMetadata.LoadGamesResult> baseImplementation$ResultHolder) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzx(baseImplementation$ResultHolder));
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Invitations.LoadInvitationsResult> baseImplementation$ResultHolder, final int n) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzae(baseImplementation$ResultHolder), n);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Requests.LoadRequestsResult> baseImplementation$ResultHolder, final int n, final int n2, final int n3) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbz(baseImplementation$ResultHolder), n, n2, n3);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Players.LoadPlayersResult> baseImplementation$ResultHolder, final int n, final boolean b, final boolean b2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbn(baseImplementation$ResultHolder), n, b, b2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> baseImplementation$ResultHolder, final int n, final int[] array) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzct(baseImplementation$ResultHolder), n, array);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult> baseImplementation$ResultHolder, final LeaderboardScoreBuffer leaderboardScoreBuffer, final int n, final int n2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzah(baseImplementation$ResultHolder), leaderboardScoreBuffer.zzcb().zzcc(), n, n2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> baseImplementation$ResultHolder, final TurnBasedMatchConfig turnBasedMatchConfig) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzco(baseImplementation$ResultHolder), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.zzci(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Snapshots.CommitSnapshotResult> baseImplementation$ResultHolder, final Snapshot snapshot, final SnapshotMetadataChange snapshotMetadataChange) throws RemoteException {
        final SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Label_0108: {
            if (snapshotContents.isClosed()) {
                break Label_0108;
            }
            boolean b = true;
            while (true) {
                Preconditions.checkState(b, (Object)"Snapshot already closed");
                final BitmapTeleporter zzcm = snapshotMetadataChange.zzcm();
                if (zzcm != null) {
                    zzcm.setTempDir(this.getContext().getCacheDir());
                }
                final Contents zzcl = snapshotContents.zzcl();
                snapshotContents.close();
                try {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzch(baseImplementation$ResultHolder), snapshot.getMetadata().getSnapshotId(), (zze)snapshotMetadataChange, zzcl);
                    return;
                    b = false;
                    continue;
                }
                catch (SecurityException ex) {
                    zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
                }
                break;
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        Label_0041: {
            if (baseImplementation$ResultHolder != null) {
                break Label_0041;
            }
            com.google.android.gms.games.internal.zzu zzu = null;
            try {
                while (true) {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(zzu, s, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
                    return;
                    zzu = new zze(baseImplementation$ResultHolder);
                    continue;
                }
            }
            catch (SecurityException ex) {
                zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> baseImplementation$ResultHolder, final String s, final int n) throws RemoteException {
        Label_0044: {
            if (baseImplementation$ResultHolder != null) {
                break Label_0044;
            }
            com.google.android.gms.games.internal.zzu zzu = null;
            try {
                while (true) {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(zzu, s, n, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
                    return;
                    zzu = new zze(baseImplementation$ResultHolder);
                    continue;
                }
            }
            catch (SecurityException ex) {
                zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult> baseImplementation$ResultHolder, final String s, final int n, final int n2, final int n3, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzah(baseImplementation$ResultHolder), s, n, n2, n3, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Players.LoadPlayersResult> baseImplementation$ResultHolder, final String s, final int n, final boolean b, final boolean b2) throws RemoteException {
        switch (s) {
            default: {
                final String value = String.valueOf(s);
                String concat;
                if (value.length() != 0) {
                    concat = "Invalid player collection: ".concat(value);
                }
                else {
                    concat = new String("Invalid player collection: ");
                }
                throw new IllegalArgumentException(concat);
            }
            case "played_with": {
                try {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbn(baseImplementation$ResultHolder), s, n, b, b2);
                    return;
                }
                catch (SecurityException ex) {
                    zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
                    return;
                }
                break;
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Leaderboards.SubmitScoreResult> baseImplementation$ResultHolder, final String s, final long n, final String s2) throws RemoteException {
        Label_0026: {
            if (baseImplementation$ResultHolder != null) {
                break Label_0026;
            }
            com.google.android.gms.games.internal.zzu zzu = null;
            try {
                while (true) {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(zzu, s, n, s2);
                    return;
                    zzu = new zzcl(baseImplementation$ResultHolder);
                    continue;
                }
            }
            catch (SecurityException ex) {
                zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> baseImplementation$ResultHolder, final String s, final String s2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcp(baseImplementation$ResultHolder), s, s2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Leaderboards.LoadPlayerScoreResult> baseImplementation$ResultHolder, final String s, final String s2, final int n, final int n2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbl(baseImplementation$ResultHolder), null, s2, n, n2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Snapshots.OpenSnapshotResult> baseImplementation$ResultHolder, final String s, final String s2, final SnapshotMetadataChange snapshotMetadataChange, final SnapshotContents snapshotContents) throws RemoteException {
        Label_0093: {
            if (snapshotContents.isClosed()) {
                break Label_0093;
            }
            boolean b = true;
            while (true) {
                Preconditions.checkState(b, (Object)"SnapshotContents already closed");
                final BitmapTeleporter zzcm = snapshotMetadataChange.zzcm();
                if (zzcm != null) {
                    zzcm.setTempDir(this.getContext().getCacheDir());
                }
                final Contents zzcl = snapshotContents.zzcl();
                snapshotContents.close();
                try {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcj(baseImplementation$ResultHolder), s, s2, (zze)snapshotMetadataChange, zzcl);
                    return;
                    b = false;
                    continue;
                }
                catch (SecurityException ex) {
                    zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
                }
                break;
            }
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Players.LoadPlayersResult> baseImplementation$ResultHolder, final String s, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzbn(baseImplementation$ResultHolder), s, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Snapshots.OpenSnapshotResult> baseImplementation$ResultHolder, final String s, final boolean b, final int n) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcj(baseImplementation$ResultHolder), s, b, n);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> baseImplementation$ResultHolder, final String s, final byte[] array, final String s2, final ParticipantResult[] array2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcs(baseImplementation$ResultHolder), s, array, s2, array2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> baseImplementation$ResultHolder, final String s, final byte[] array, final ParticipantResult[] array2) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcs(baseImplementation$ResultHolder), s, array, array2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Players.LoadPlayersResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(new zzbn(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Events.LoadEventsResult> baseImplementation$ResultHolder, final boolean b, final String... array) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzu(baseImplementation$ResultHolder), b, array);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Quests.LoadQuestsResult> baseImplementation$ResultHolder, final int[] array, final int n, final boolean b) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbt(baseImplementation$ResultHolder), array, n, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final BaseImplementation$ResultHolder<Requests.UpdateRequestsResult> baseImplementation$ResultHolder, final String[] array) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzca(baseImplementation$ResultHolder), array);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zza(final ListenerHolder<OnInvitationReceivedListener> listenerHolder) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzab(listenerHolder), this.zzfw);
    }
    
    public final void zza(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, final ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, final RoomConfig roomConfig) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcc(listenerHolder, listenerHolder2, listenerHolder3), (IBinder)this.zzfv, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), false, this.zzfw);
    }
    
    public final void zza(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final String s) {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcc(listenerHolder), s);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zza(final Snapshot snapshot) throws RemoteException {
        final SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Preconditions.checkState(!snapshotContents.isClosed(), (Object)"Snapshot already closed");
        final Contents zzcl = snapshotContents.zzcl();
        snapshotContents.close();
        ((com.google.android.gms.games.internal.zzy)this.getService()).zza(zzcl);
    }
    
    public final void zza(final String s, final int n) {
        this.zzfp.zza(s, n);
    }
    
    public final void zza(final String s, final BaseImplementation$ResultHolder<Games.GetServerAuthCodeResult> baseImplementation$ResultHolder) throws RemoteException {
        Preconditions.checkNotEmpty(s, (Object)"Please provide a valid serverClientId");
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(s, new zzy(baseImplementation$ResultHolder));
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzaa() throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(this.zzfw);
    }
    
    public final void zzab() {
        try {
            this.zzaa();
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzac() throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(this.zzfw);
    }
    
    public final void zzad() {
        try {
            this.zzac();
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzae() {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zze(this.zzfw);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzaf() {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(this.zzfw);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final Intent zzag() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzag();
    }
    
    public final Intent zzah() {
        try {
            return this.zzag();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzai() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzai();
    }
    
    public final Intent zzaj() {
        try {
            return this.zzai();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final int zzak() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzak();
    }
    
    public final int zzal() {
        try {
            return this.zzak();
        }
        catch (RemoteException ex) {
            zza(ex);
            return 4368;
        }
    }
    
    public final String zzam() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzam();
    }
    
    public final String zzan() {
        try {
            return this.zzam();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final int zzao() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzao();
    }
    
    public final int zzap() {
        try {
            return this.zzao();
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final Intent zzaq() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzaq();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final int zzar() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzar();
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final int zzas() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzas();
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final int zzat() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzat();
    }
    
    public final int zzau() {
        try {
            return this.zzat();
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final int zzav() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzav();
    }
    
    public final int zzaw() {
        try {
            return this.zzav();
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final Intent zzax() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzbi();
    }
    
    public final Intent zzay() {
        try {
            return this.zzax();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final boolean zzaz() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzaz();
    }
    
    public final int zzb(final ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> listenerHolder, final byte[] array, final String s, final String s2) {
        try {
            return this.zza(listenerHolder, array, s, s2);
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final int zzb(final byte[] array, final String s) {
        try {
            return this.zza(array, s);
        }
        catch (RemoteException ex) {
            zza(ex);
            return -1;
        }
    }
    
    public final Intent zzb(final int n, final int n2, final boolean b) {
        try {
            return this.zza(n, n2, b);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzb(final PlayerEntity playerEntity) {
        try {
            return this.zza(playerEntity);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzb(final Room room, final int n) {
        try {
            return this.zza(room, n);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzb(final String s, final boolean b, final boolean b2, final int n) {
        try {
            return this.zza(s, b, b2, n);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final String zzb(final boolean b) {
        try {
            return this.zza(true);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcg(baseImplementation$ResultHolder));
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Videos.CaptureAvailableResult> baseImplementation$ResultHolder, final int n) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzh(baseImplementation$ResultHolder), n);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        Label_0041: {
            if (baseImplementation$ResultHolder != null) {
                break Label_0041;
            }
            com.google.android.gms.games.internal.zzu zzu = null;
            try {
                while (true) {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(zzu, s, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
                    return;
                    zzu = new zze(baseImplementation$ResultHolder);
                    continue;
                }
            }
            catch (SecurityException ex) {
                zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
            }
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> baseImplementation$ResultHolder, final String s, final int n) throws RemoteException {
        Label_0044: {
            if (baseImplementation$ResultHolder != null) {
                break Label_0044;
            }
            com.google.android.gms.games.internal.zzu zzu = null;
            try {
                while (true) {
                    ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(zzu, s, n, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
                    return;
                    zzu = new zze(baseImplementation$ResultHolder);
                    continue;
                }
            }
            catch (SecurityException ex) {
                zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
            }
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult> baseImplementation$ResultHolder, final String s, final int n, final int n2, final int n3, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzah(baseImplementation$ResultHolder), s, n, n2, n3, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Quests.ClaimMilestoneResult> baseImplementation$ResultHolder, final String s, final String s2) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzbr(baseImplementation$ResultHolder, s2), s, s2);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Leaderboards.LeaderboardMetadataResult> baseImplementation$ResultHolder, final String s, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzai(baseImplementation$ResultHolder), s, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Leaderboards.LeaderboardMetadataResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzai(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Quests.LoadQuestsResult> baseImplementation$ResultHolder, final boolean b, final String[] array) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzbt(baseImplementation$ResultHolder), array, b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final BaseImplementation$ResultHolder<Requests.UpdateRequestsResult> baseImplementation$ResultHolder, final String[] array) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzca(baseImplementation$ResultHolder), array);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzb(final ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
        try {
            this.zza(listenerHolder);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzb(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, final ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, final RoomConfig roomConfig) {
        try {
            this.zza(listenerHolder, listenerHolder2, listenerHolder3, roomConfig);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzb(final Snapshot snapshot) {
        try {
            this.zza(snapshot);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzb(final String s) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzf(s);
    }
    
    public final void zzb(final String s, final int n) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(s, n);
    }
    
    public final boolean zzba() {
        try {
            return this.zzaz();
        }
        catch (RemoteException ex) {
            zza(ex);
            return false;
        }
    }
    
    public final void zzbb() throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzf(this.zzfw);
    }
    
    public final void zzbc() {
        try {
            this.zzbb();
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzbd() {
        if (!this.isConnected()) {
            return;
        }
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzbd();
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final Intent zzc(final int n, final int n2, final boolean b) throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(n, n2, b);
    }
    
    public final void zzc(final BaseImplementation$ResultHolder<Videos.CaptureCapabilitiesResult> baseImplementation$ResultHolder) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(new zzj(baseImplementation$ResultHolder));
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzc(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzco(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzc(final BaseImplementation$ResultHolder<Achievements.LoadAchievementsResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzf(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzc(final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzb(new zzaz(listenerHolder), this.zzfw);
    }
    
    public final void zzc(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, final ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, final RoomConfig roomConfig) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zza(new zzcc(listenerHolder, listenerHolder2, listenerHolder3), (IBinder)this.zzfv, roomConfig.getInvitationId(), false, this.zzfw);
    }
    
    public final void zzc(final String s) {
        try {
            this.zzb(s);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzc(final String s, final int n) {
        try {
            this.zzb(s, n);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final Intent zzd(final int n, final int n2, final boolean b) {
        try {
            return this.zzc(n, n2, b);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzd(final String s) {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(s);
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final void zzd(final BaseImplementation$ResultHolder<Videos.CaptureStateResult> baseImplementation$ResultHolder) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(new zzn(baseImplementation$ResultHolder));
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzd(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(new zzco(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzd(final BaseImplementation$ResultHolder<Events.LoadEventsResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zze(new zzu(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzd(final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
        try {
            this.zzc(listenerHolder);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzd(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, final ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, final RoomConfig roomConfig) {
        try {
            this.zzc(listenerHolder, listenerHolder2, listenerHolder3, roomConfig);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzd(final String s, final int n) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(s, n);
    }
    
    public final void zze(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zze(new zzcp(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zze(final BaseImplementation$ResultHolder<Stats.LoadPlayerStatsResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzf(new zzbm(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zze(final ListenerHolder<QuestUpdateListener> listenerHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(new zzbs(listenerHolder), this.zzfw);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zze(final String s) {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zza(s, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zze(final String s, final int n) {
        try {
            this.zzd(s, n);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzf(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.CancelMatchResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(new zzcn(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzf(final BaseImplementation$ResultHolder<Snapshots.LoadSnapshotsResult> baseImplementation$ResultHolder, final boolean b) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzd(new zzck(baseImplementation$ResultHolder), b);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzf(final ListenerHolder<OnRequestReceivedListener> listenerHolder) {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzc(new zzbw(listenerHolder), this.zzfw);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzg(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzf(new zzcq(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzg(final ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zze(new zzl(listenerHolder), this.zzfw);
    }
    
    public final void zzh(final BaseImplementation$ResultHolder<Quests.AcceptQuestResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        this.zzfp.flush();
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzh(new zzbp(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzh(final ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) {
        try {
            this.zzg(listenerHolder);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    public final void zzi(final BaseImplementation$ResultHolder<Snapshots.DeleteSnapshotResult> baseImplementation$ResultHolder, final String s) throws RemoteException {
        try {
            ((com.google.android.gms.games.internal.zzy)this.getService()).zzg(new zzci(baseImplementation$ResultHolder), s);
        }
        catch (SecurityException ex) {
            zza((com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder<Object>)baseImplementation$ResultHolder, ex);
        }
    }
    
    public final void zzj(final int gravity) {
        this.zzft.zzjd.gravity = gravity;
    }
    
    public final void zzk(final int n) throws RemoteException {
        ((com.google.android.gms.games.internal.zzy)this.getService()).zzk(n);
    }
    
    public final void zzl(final int n) {
        try {
            this.zzk(n);
        }
        catch (RemoteException ex) {
            zza(ex);
        }
    }
    
    @Nullable
    public final Bundle zzo() {
        Bundle bundle;
        if ((bundle = this.getConnectionHint()) == null) {
            bundle = this.zzfz;
        }
        this.zzfz = null;
        return bundle;
    }
    
    public final String zzp() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzp();
    }
    
    public final String zzq() {
        try {
            return this.zzp();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Player zzr() throws RemoteException {
        this.checkConnected();
        synchronized (this) {
            Label_0064: {
                if (this.zzfr != null) {
                    break Label_0064;
                }
                final PlayerBuffer playerBuffer = new PlayerBuffer(((com.google.android.gms.games.internal.zzy)this.getService()).zzbg());
                try {
                    if (playerBuffer.getCount() > 0) {
                        this.zzfr = (PlayerEntity)((Player)playerBuffer.get(0)).freeze();
                    }
                    playerBuffer.release();
                    return this.zzfr;
                }
                finally {
                    playerBuffer.release();
                }
            }
        }
    }
    
    public final Player zzs() {
        try {
            return this.zzr();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Game zzt() throws RemoteException {
        this.checkConnected();
        synchronized (this) {
            Label_0064: {
                if (this.zzfs != null) {
                    break Label_0064;
                }
                final GameBuffer gameBuffer = new GameBuffer(((com.google.android.gms.games.internal.zzy)this.getService()).zzbh());
                try {
                    if (gameBuffer.getCount() > 0) {
                        this.zzfs = (GameEntity)((Game)gameBuffer.get(0)).freeze();
                    }
                    gameBuffer.release();
                    return this.zzfs;
                }
                finally {
                    gameBuffer.release();
                }
            }
        }
    }
    
    public final Game zzu() {
        try {
            return this.zzt();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzv() throws RemoteException {
        return ((com.google.android.gms.games.internal.zzy)this.getService()).zzv();
    }
    
    public final Intent zzw() {
        try {
            return this.zzv();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzx() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzx();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzy() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzy();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    public final Intent zzz() {
        try {
            return ((com.google.android.gms.games.internal.zzy)this.getService()).zzz();
        }
        catch (RemoteException ex) {
            zza(ex);
            return null;
        }
    }
    
    private abstract static class zza extends zzc
    {
        private final ArrayList<String> zzgc;
        
        zza(final DataHolder dataHolder, final String[] array) {
            super(dataHolder);
            this.zzgc = new ArrayList<String>();
            for (int i = 0; i < array.length; ++i) {
                this.zzgc.add(array[i]);
            }
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            this.zza(roomStatusUpdateListener, room, this.zzgc);
        }
        
        protected abstract void zza(final RoomStatusUpdateListener p0, final Room p1, final ArrayList<String> p2);
    }
    
    private static final class zzaa extends zzcr implements InitiateMatchResult
    {
        zzaa(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class zzab extends zza
    {
        private final ListenerHolder<OnInvitationReceivedListener> zzgj;
        
        zzab(final ListenerHolder<OnInvitationReceivedListener> zzgj) {
            this.zzgj = zzgj;
        }
        
        @Override
        public final void onInvitationRemoved(final String s) {
            this.zzgj.notifyListener((ListenerHolder$Notifier)new zzad(s));
        }
        
        @Override
        public final void zzl(final DataHolder dataHolder) {
            final InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            Invitation invitation = null;
            try {
                if (invitationBuffer.getCount() > 0) {
                    invitation = (Invitation)((Invitation)invitationBuffer.get(0)).freeze();
                }
                invitationBuffer.release();
                if (invitation != null) {
                    this.zzgj.notifyListener((ListenerHolder$Notifier)new zzac(invitation));
                }
            }
            finally {
                invitationBuffer.release();
            }
        }
    }
    
    private static final class zzac implements ListenerHolder$Notifier<OnInvitationReceivedListener>
    {
        private final Invitation zzgq;
        
        zzac(final Invitation zzgq) {
            this.zzgq = zzgq;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzad implements ListenerHolder$Notifier<OnInvitationReceivedListener>
    {
        private final String zzgr;
        
        zzad(final String zzgr) {
            this.zzgr = zzgr;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzae extends zza
    {
        private final BaseImplementation$ResultHolder<Invitations.LoadInvitationsResult> zzge;
        
        zzae(final BaseImplementation$ResultHolder<Invitations.LoadInvitationsResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Invitations.LoadInvitationsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzk(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzao(dataHolder));
        }
    }
    
    private static final class zzaf extends zzb
    {
        public zzaf(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onJoinedRoom(n, room);
        }
    }
    
    private static final class zzag extends zzw implements LeaderboardMetadataResult
    {
        private final LeaderboardBuffer zzgs;
        
        zzag(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzgs = new LeaderboardBuffer(dataHolder);
        }
        
        @Override
        public final LeaderboardBuffer getLeaderboards() {
            return this.zzgs;
        }
    }
    
    private static final class zzah extends zza
    {
        private final BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult> zzge;
        
        zzah(final BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Leaderboards.LoadScoresResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final DataHolder dataHolder, final DataHolder dataHolder2) {
            this.zzge.setResult((Object)new zzaw(dataHolder, dataHolder2));
        }
    }
    
    private static final class zzai extends zza
    {
        private final BaseImplementation$ResultHolder<Leaderboards.LeaderboardMetadataResult> zzge;
        
        zzai(final BaseImplementation$ResultHolder<Leaderboards.LeaderboardMetadataResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Leaderboards.LeaderboardMetadataResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzc(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzag(dataHolder));
        }
    }
    
    private static final class zzaj extends zzcr implements LeaveMatchResult
    {
        zzaj(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class zzak implements ListenerHolder$Notifier<RoomUpdateListener>
    {
        private final int statusCode;
        private final String zzgt;
        
        zzak(final int statusCode, final String zzgt) {
            this.statusCode = statusCode;
            this.zzgt = zzgt;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzal extends zzw implements LoadAchievementsResult
    {
        private final AchievementBuffer zzgu;
        
        zzal(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzgu = new AchievementBuffer(dataHolder);
        }
        
        @Override
        public final AchievementBuffer getAchievements() {
            return this.zzgu;
        }
    }
    
    private static final class zzam extends zzw implements LoadEventsResult
    {
        private final EventBuffer zzgv;
        
        zzam(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzgv = new EventBuffer(dataHolder);
        }
        
        @Override
        public final EventBuffer getEvents() {
            return this.zzgv;
        }
    }
    
    private static final class zzan extends zzw implements LoadGamesResult
    {
        private final GameBuffer zzgw;
        
        zzan(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzgw = new GameBuffer(dataHolder);
        }
        
        @Override
        public final GameBuffer getGames() {
            return this.zzgw;
        }
    }
    
    private static final class zzao extends zzw implements LoadInvitationsResult
    {
        private final InvitationBuffer zzgx;
        
        zzao(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzgx = new InvitationBuffer(dataHolder);
        }
        
        @Override
        public final InvitationBuffer getInvitations() {
            return this.zzgx;
        }
    }
    
    private static final class zzap extends zzcr implements LoadMatchResult
    {
        zzap(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class zzaq implements LoadMatchesResult
    {
        private final Status zzgf;
        private final LoadMatchesResponse zzgy;
        
        zzaq(final Status zzgf, final Bundle bundle) {
            this.zzgf = zzgf;
            this.zzgy = new LoadMatchesResponse(bundle);
        }
        
        @Override
        public final LoadMatchesResponse getMatches() {
            return this.zzgy;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
        
        public final void release() {
            this.zzgy.release();
        }
    }
    
    private static final class zzar extends zzw implements LoadPlayerScoreResult
    {
        private final LeaderboardScoreEntity zzgz;
        
        zzar(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new LeaderboardScoreBuffer(dataHolder);
            try {
                if (((LeaderboardScoreBuffer)dataHolder).getCount() > 0) {
                    this.zzgz = (LeaderboardScoreEntity)((LeaderboardScore)((LeaderboardScoreBuffer)dataHolder).get(0)).freeze();
                }
                else {
                    this.zzgz = null;
                }
            }
            finally {
                ((LeaderboardScoreBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final LeaderboardScore getScore() {
            return this.zzgz;
        }
    }
    
    private static final class zzas extends zzw implements LoadPlayerStatsResult
    {
        private final PlayerStats zzha;
        
        zzas(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new PlayerStatsBuffer(dataHolder);
            try {
                if (((PlayerStatsBuffer)dataHolder).getCount() > 0) {
                    this.zzha = new com.google.android.gms.games.stats.zza((PlayerStats)((PlayerStatsBuffer)dataHolder).get(0));
                }
                else {
                    this.zzha = null;
                }
            }
            finally {
                ((PlayerStatsBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final PlayerStats getPlayerStats() {
            return this.zzha;
        }
    }
    
    private static final class zzat extends zzw implements LoadPlayersResult
    {
        private final PlayerBuffer zzhb;
        
        zzat(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzhb = new PlayerBuffer(dataHolder);
        }
        
        @Override
        public final PlayerBuffer getPlayers() {
            return this.zzhb;
        }
    }
    
    private static final class zzau extends zzw implements LoadQuestsResult
    {
        private final DataHolder zzhc;
        
        zzau(final DataHolder zzhc) {
            super(zzhc);
            this.zzhc = zzhc;
        }
        
        @Override
        public final QuestBuffer getQuests() {
            return new QuestBuffer(this.zzhc);
        }
    }
    
    private static final class zzav implements LoadRequestsResult
    {
        private final Status zzgf;
        private final Bundle zzhd;
        
        zzav(final Status zzgf, final Bundle zzhd) {
            this.zzgf = zzgf;
            this.zzhd = zzhd;
        }
        
        @Override
        public final GameRequestBuffer getRequests(final int n) {
            String s = null;
            switch (n) {
                default: {
                    com.google.android.gms.games.internal.zzh.e("RequestType", new StringBuilder(33).append("Unknown request type: ").append(n).toString());
                    s = "UNKNOWN_TYPE";
                    break;
                }
                case 1: {
                    s = "GIFT";
                    break;
                }
                case 2: {
                    s = "WISH";
                    break;
                }
            }
            if (!this.zzhd.containsKey(s)) {
                return null;
            }
            return new GameRequestBuffer((DataHolder)this.zzhd.get(s));
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
        
        public final void release() {
            final Iterator<String> iterator = this.zzhd.keySet().iterator();
            while (iterator.hasNext()) {
                final DataHolder dataHolder = (DataHolder)this.zzhd.getParcelable((String)iterator.next());
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }
    
    private static final class zzaw extends zzw implements LoadScoresResult
    {
        private final LeaderboardEntity zzhe;
        private final LeaderboardScoreBuffer zzhf;
        
        zzaw(DataHolder dataHolder, final DataHolder dataHolder2) {
            super(dataHolder2);
            dataHolder = (DataHolder)new LeaderboardBuffer(dataHolder);
            try {
                if (((LeaderboardBuffer)dataHolder).getCount() > 0) {
                    this.zzhe = (LeaderboardEntity)((Leaderboard)((LeaderboardBuffer)dataHolder).get(0)).freeze();
                }
                else {
                    this.zzhe = null;
                }
                ((LeaderboardBuffer)dataHolder).release();
                this.zzhf = new LeaderboardScoreBuffer(dataHolder2);
            }
            finally {
                ((LeaderboardBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final Leaderboard getLeaderboard() {
            return this.zzhe;
        }
        
        @Override
        public final LeaderboardScoreBuffer getScores() {
            return this.zzhf;
        }
    }
    
    private static final class zzax extends zzw implements LoadSnapshotsResult
    {
        zzax(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        @Override
        public final SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.mDataHolder);
        }
    }
    
    private static final class zzay implements ListenerHolder$Notifier<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final String zzhg;
        
        zzay(final String zzhg) {
            this.zzhg = zzhg;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzaz extends zza
    {
        private final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> zzgj;
        
        zzaz(final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> zzgj) {
            this.zzgj = zzgj;
        }
        
        @Override
        public final void onTurnBasedMatchRemoved(final String s) {
            this.zzgj.notifyListener((ListenerHolder$Notifier)new zzay(s));
        }
        
        @Override
        public final void zzr(final DataHolder dataHolder) {
            final TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = (TurnBasedMatch)((TurnBasedMatch)turnBasedMatchBuffer.get(0)).freeze();
                }
                turnBasedMatchBuffer.release();
                if (turnBasedMatch != null) {
                    this.zzgj.notifyListener((ListenerHolder$Notifier)new zzba(turnBasedMatch));
                }
            }
            finally {
                turnBasedMatchBuffer.release();
            }
        }
    }
    
    private abstract static class zzb extends DataHolderNotifier<RoomUpdateListener>
    {
        zzb(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        protected abstract void zza(final RoomUpdateListener p0, final Room p1, final int p2);
    }
    
    private static final class zzba implements ListenerHolder$Notifier<OnTurnBasedMatchUpdateReceivedListener>
    {
        private final TurnBasedMatch match;
        
        zzba(final TurnBasedMatch match) {
            this.match = match;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbb implements ListenerHolder$Notifier<RealTimeMessageReceivedListener>
    {
        private final RealTimeMessage zzhh;
        
        zzbb(final RealTimeMessage zzhh) {
            this.zzhh = zzhh;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbc extends zzw implements OpenSnapshotResult
    {
        private final String zzei;
        private final Snapshot zzhi;
        private final Snapshot zzhj;
        private final SnapshotContents zzhk;
        
        zzbc(final DataHolder dataHolder, final Contents contents) {
            this(dataHolder, null, contents, null, null);
        }
        
        zzbc(final DataHolder dataHolder, final String zzei, final Contents contents, final Contents contents2, final Contents contents3) {
        Label_0036_Outer:
            while (true) {
                boolean b = true;
                super(dataHolder);
                final SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
                while (true) {
                    Label_0141: {
                        while (true) {
                            Label_0135: {
                                try {
                                    if (snapshotMetadataBuffer.getCount() == 0) {
                                        this.zzhi = null;
                                        this.zzhj = null;
                                    }
                                    else {
                                        if (snapshotMetadataBuffer.getCount() != 1) {
                                            break Label_0141;
                                        }
                                        if (dataHolder.getStatusCode() == 4004) {
                                            break Label_0135;
                                        }
                                        Asserts.checkState(b);
                                        this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata)snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                                        this.zzhj = null;
                                    }
                                    snapshotMetadataBuffer.release();
                                    this.zzei = zzei;
                                    this.zzhk = new com.google.android.gms.games.snapshot.zza(contents3);
                                    return;
                                }
                                finally {
                                    snapshotMetadataBuffer.release();
                                }
                            }
                            b = false;
                            continue Label_0036_Outer;
                        }
                    }
                    this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata)snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                    this.zzhj = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata)snapshotMetadataBuffer.get(1)), new com.google.android.gms.games.snapshot.zza(contents2));
                    continue;
                }
            }
        }
        
        @Override
        public final String getConflictId() {
            return this.zzei;
        }
        
        @Override
        public final Snapshot getConflictingSnapshot() {
            return this.zzhj;
        }
        
        @Override
        public final SnapshotContents getResolutionSnapshotContents() {
            return this.zzhk;
        }
        
        @Override
        public final Snapshot getSnapshot() {
            return this.zzhi;
        }
    }
    
    private static final class zzbd implements ListenerHolder$Notifier<RoomStatusUpdateListener>
    {
        private final String zzhl;
        
        zzbd(final String zzhl) {
            this.zzhl = zzhl;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbe implements ListenerHolder$Notifier<RoomStatusUpdateListener>
    {
        private final String zzhl;
        
        zzbe(final String zzhl) {
            this.zzhl = zzhl;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbf extends zza
    {
        zzbf(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersConnected(room, list);
        }
    }
    
    private static final class zzbg extends zza
    {
        zzbg(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerDeclined(room, list);
        }
    }
    
    private static final class zzbh extends zza
    {
        zzbh(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeersDisconnected(room, list);
        }
    }
    
    private static final class zzbi extends zza
    {
        zzbi(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, list);
        }
    }
    
    private static final class zzbj extends zza
    {
        zzbj(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerJoined(room, list);
        }
    }
    
    private static final class zzbk extends zza
    {
        zzbk(final DataHolder dataHolder, final String[] array) {
            super(dataHolder, array);
        }
        
        @Override
        protected final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room, final ArrayList<String> list) {
            roomStatusUpdateListener.onPeerLeft(room, list);
        }
    }
    
    private static final class zzbl extends zza
    {
        private final BaseImplementation$ResultHolder<Leaderboards.LoadPlayerScoreResult> zzge;
        
        zzbl(final BaseImplementation$ResultHolder<Leaderboards.LoadPlayerScoreResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Leaderboards.LoadPlayerScoreResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzac(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzar(dataHolder));
        }
    }
    
    private static final class zzbm extends zza
    {
        private final BaseImplementation$ResultHolder<Stats.LoadPlayerStatsResult> zzge;
        
        public zzbm(final BaseImplementation$ResultHolder<Stats.LoadPlayerStatsResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Stats.LoadPlayerStatsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzap(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzas(dataHolder));
        }
    }
    
    private static final class zzbn extends zza
    {
        private final BaseImplementation$ResultHolder<Players.LoadPlayersResult> zzge;
        
        zzbn(final BaseImplementation$ResultHolder<Players.LoadPlayersResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Players.LoadPlayersResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zze(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzat(dataHolder));
        }
        
        @Override
        public final void zzf(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzat(dataHolder));
        }
    }
    
    private static final class zzbo extends zzb
    {
        private final com.google.android.gms.games.internal.zzac zzft;
        
        public zzbo(final com.google.android.gms.games.internal.zzac zzft) {
            this.zzft = zzft;
        }
        
        @Override
        public final com.google.android.gms.games.internal.zzaa zzn() {
            return new com.google.android.gms.games.internal.zzaa(this.zzft.zzjd);
        }
    }
    
    private static final class zzbp extends zza
    {
        private final BaseImplementation$ResultHolder<Quests.AcceptQuestResult> zzhm;
        
        public zzbp(final BaseImplementation$ResultHolder<Quests.AcceptQuestResult> baseImplementation$ResultHolder) {
            this.zzhm = (BaseImplementation$ResultHolder<Quests.AcceptQuestResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzaj(final DataHolder dataHolder) {
            this.zzhm.setResult((Object)new zzd(dataHolder));
        }
    }
    
    private static final class zzbq implements ListenerHolder$Notifier<QuestUpdateListener>
    {
        private final Quest zzgd;
        
        zzbq(final Quest zzgd) {
            this.zzgd = zzgd;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbr extends zza
    {
        private final BaseImplementation$ResultHolder<Quests.ClaimMilestoneResult> zzhn;
        private final String zzho;
        
        public zzbr(final BaseImplementation$ResultHolder<Quests.ClaimMilestoneResult> baseImplementation$ResultHolder, final String s) {
            this.zzhn = (BaseImplementation$ResultHolder<Quests.ClaimMilestoneResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
            this.zzho = (String)Preconditions.checkNotNull((Object)s, (Object)"MilestoneId must not be null");
        }
        
        @Override
        public final void zzai(final DataHolder dataHolder) {
            this.zzhn.setResult((Object)new zzp(dataHolder, this.zzho));
        }
    }
    
    private static final class zzbs extends zza
    {
        private final ListenerHolder<QuestUpdateListener> zzgj;
        
        zzbs(final ListenerHolder<QuestUpdateListener> zzgj) {
            this.zzgj = zzgj;
        }
        
        private static Quest zzbc(final DataHolder dataHolder) {
            final QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            Quest quest = null;
            try {
                if (questBuffer.getCount() > 0) {
                    quest = (Quest)((Quest)questBuffer.get(0)).freeze();
                }
                return quest;
            }
            finally {
                questBuffer.release();
            }
        }
        
        @Override
        public final void zzak(final DataHolder dataHolder) {
            final Quest zzbc = zzbc(dataHolder);
            if (zzbc != null) {
                this.zzgj.notifyListener((ListenerHolder$Notifier)new zzbq(zzbc));
            }
        }
    }
    
    private static final class zzbt extends zza
    {
        private final BaseImplementation$ResultHolder<Quests.LoadQuestsResult> zzhp;
        
        public zzbt(final BaseImplementation$ResultHolder<Quests.LoadQuestsResult> baseImplementation$ResultHolder) {
            this.zzhp = (BaseImplementation$ResultHolder<Quests.LoadQuestsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzam(final DataHolder dataHolder) {
            this.zzhp.setResult((Object)new zzau(dataHolder));
        }
    }
    
    private static final class zzbu implements ListenerHolder$Notifier<RealTimeMultiplayer.ReliableMessageSentCallback>
    {
        private final int statusCode;
        private final int token;
        private final String zzhq;
        
        zzbu(final int statusCode, final int token, final String zzhq) {
            this.statusCode = statusCode;
            this.token = token;
            this.zzhq = zzhq;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbv extends zza
    {
        private final ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> zzhr;
        
        public zzbv(final ListenerHolder<RealTimeMultiplayer.ReliableMessageSentCallback> zzhr) {
            this.zzhr = zzhr;
        }
        
        @Override
        public final void zza(final int n, final int n2, final String s) {
            if (this.zzhr != null) {
                this.zzhr.notifyListener((ListenerHolder$Notifier)new zzbu(n, n2, s));
            }
        }
    }
    
    private static final class zzbw extends zza
    {
        private final ListenerHolder<OnRequestReceivedListener> zzgj;
        
        zzbw(final ListenerHolder<OnRequestReceivedListener> zzgj) {
            this.zzgj = zzgj;
        }
        
        @Override
        public final void onRequestRemoved(final String s) {
            this.zzgj.notifyListener((ListenerHolder$Notifier)new zzby(s));
        }
        
        @Override
        public final void zzm(final DataHolder dataHolder) {
            final GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            GameRequest gameRequest = null;
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    gameRequest = (GameRequest)((GameRequest)gameRequestBuffer.get(0)).freeze();
                }
                gameRequestBuffer.release();
                if (gameRequest != null) {
                    this.zzgj.notifyListener((ListenerHolder$Notifier)new zzbx(gameRequest));
                }
            }
            finally {
                gameRequestBuffer.release();
            }
        }
    }
    
    private static final class zzbx implements ListenerHolder$Notifier<OnRequestReceivedListener>
    {
        private final GameRequest zzhs;
        
        zzbx(final GameRequest zzhs) {
            this.zzhs = zzhs;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzby implements ListenerHolder$Notifier<OnRequestReceivedListener>
    {
        private final String zzht;
        
        zzby(final String zzht) {
            this.zzht = zzht;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzbz extends zza
    {
        private final BaseImplementation$ResultHolder<Requests.LoadRequestsResult> zzhu;
        
        public zzbz(final BaseImplementation$ResultHolder<Requests.LoadRequestsResult> baseImplementation$ResultHolder) {
            this.zzhu = (BaseImplementation$ResultHolder<Requests.LoadRequestsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzb(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.zzhu.setResult((Object)new zzav(GamesStatusCodes.zza(n), bundle));
        }
    }
    
    private abstract static class zzc extends DataHolderNotifier<RoomStatusUpdateListener>
    {
        zzc(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        protected abstract void zza(final RoomStatusUpdateListener p0, final Room p1);
    }
    
    private static final class zzca extends zza
    {
        private final BaseImplementation$ResultHolder<Requests.UpdateRequestsResult> zzhv;
        
        public zzca(final BaseImplementation$ResultHolder<Requests.UpdateRequestsResult> baseImplementation$ResultHolder) {
            this.zzhv = (BaseImplementation$ResultHolder<Requests.UpdateRequestsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzad(final DataHolder dataHolder) {
            this.zzhv.setResult((Object)new zzcw(dataHolder));
        }
    }
    
    private static final class zzcb extends zzc
    {
        zzcb(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }
    
    private static final class zzcc extends zza
    {
        private final ListenerHolder<? extends RoomUpdateListener> zzhw;
        private final ListenerHolder<? extends RoomStatusUpdateListener> zzhx;
        private final ListenerHolder<? extends RealTimeMessageReceivedListener> zzhy;
        
        public zzcc(final ListenerHolder<? extends RoomUpdateListener> listenerHolder) {
            this.zzhw = (ListenerHolder<? extends RoomUpdateListener>)Preconditions.checkNotNull((Object)listenerHolder, (Object)"Callbacks must not be null");
            this.zzhx = null;
            this.zzhy = null;
        }
        
        public zzcc(final ListenerHolder<? extends RoomUpdateListener> listenerHolder, final ListenerHolder<? extends RoomStatusUpdateListener> zzhx, final ListenerHolder<? extends RealTimeMessageReceivedListener> zzhy) {
            this.zzhw = (ListenerHolder<? extends RoomUpdateListener>)Preconditions.checkNotNull((Object)listenerHolder, (Object)"Callbacks must not be null");
            this.zzhx = zzhx;
            this.zzhy = zzhy;
        }
        
        @Override
        public final void onLeftRoom(final int n, final String s) {
            this.zzhw.notifyListener((ListenerHolder$Notifier)new zzak(n, s));
        }
        
        @Override
        public final void onP2PConnected(final String s) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbd(s));
            }
        }
        
        @Override
        public final void onP2PDisconnected(final String s) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbe(s));
            }
        }
        
        @Override
        public final void onRealTimeMessageReceived(final RealTimeMessage realTimeMessage) {
            if (this.zzhy != null) {
                this.zzhy.notifyListener((ListenerHolder$Notifier)new zzbb(realTimeMessage));
            }
        }
        
        @Override
        public final void zza(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbi(dataHolder, array));
            }
        }
        
        @Override
        public final void zzb(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbj(dataHolder, array));
            }
        }
        
        @Override
        public final void zzc(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbk(dataHolder, array));
            }
        }
        
        @Override
        public final void zzd(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbg(dataHolder, array));
            }
        }
        
        @Override
        public final void zze(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbf(dataHolder, array));
            }
        }
        
        @Override
        public final void zzf(final DataHolder dataHolder, final String[] array) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzbh(dataHolder, array));
            }
        }
        
        @Override
        public final void zzs(final DataHolder dataHolder) {
            this.zzhw.notifyListener((ListenerHolder$Notifier)new zzcf(dataHolder));
        }
        
        @Override
        public final void zzt(final DataHolder dataHolder) {
            this.zzhw.notifyListener((ListenerHolder$Notifier)new zzaf(dataHolder));
        }
        
        @Override
        public final void zzu(final DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzce(dataHolder));
            }
        }
        
        @Override
        public final void zzv(final DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzcb(dataHolder));
            }
        }
        
        @Override
        public final void zzw(final DataHolder dataHolder) {
            this.zzhw.notifyListener((ListenerHolder$Notifier)new zzcd(dataHolder));
        }
        
        @Override
        public final void zzx(final DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzr(dataHolder));
            }
        }
        
        @Override
        public final void zzy(final DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener((ListenerHolder$Notifier)new zzt(dataHolder));
            }
        }
    }
    
    private static final class zzcd extends zzb
    {
        zzcd(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomConnected(n, room);
        }
    }
    
    private static final class zzce extends zzc
    {
        zzce(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }
    
    private static final class zzcf extends zzb
    {
        public zzcf(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomUpdateListener roomUpdateListener, final Room room, final int n) {
            roomUpdateListener.onRoomCreated(n, room);
        }
    }
    
    private static final class zzcg extends zza
    {
        private final BaseImplementation$ResultHolder<Status> zzge;
        
        public zzcg(final BaseImplementation$ResultHolder<Status> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Status>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void onSignOutComplete() {
            this.zzge.setResult((Object)GamesStatusCodes.zza(0));
        }
    }
    
    private static final class zzch extends zza
    {
        private final BaseImplementation$ResultHolder<Snapshots.CommitSnapshotResult> zzhz;
        
        public zzch(final BaseImplementation$ResultHolder<Snapshots.CommitSnapshotResult> baseImplementation$ResultHolder) {
            this.zzhz = (BaseImplementation$ResultHolder<Snapshots.CommitSnapshotResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzah(final DataHolder dataHolder) {
            this.zzhz.setResult((Object)new zzq(dataHolder));
        }
    }
    
    static final class zzci extends zza
    {
        private final BaseImplementation$ResultHolder<Snapshots.DeleteSnapshotResult> zzge;
        
        public zzci(final BaseImplementation$ResultHolder<Snapshots.DeleteSnapshotResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Snapshots.DeleteSnapshotResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzd(final int n, final String s) {
            this.zzge.setResult((Object)new zzs(n, s));
        }
    }
    
    private static final class zzcj extends zza
    {
        private final BaseImplementation$ResultHolder<Snapshots.OpenSnapshotResult> zzia;
        
        public zzcj(final BaseImplementation$ResultHolder<Snapshots.OpenSnapshotResult> baseImplementation$ResultHolder) {
            this.zzia = (BaseImplementation$ResultHolder<Snapshots.OpenSnapshotResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final DataHolder dataHolder, final Contents contents) {
            this.zzia.setResult((Object)new zzbc(dataHolder, contents));
        }
        
        @Override
        public final void zza(final DataHolder dataHolder, final String s, final Contents contents, final Contents contents2, final Contents contents3) {
            this.zzia.setResult((Object)new zzbc(dataHolder, s, contents, contents2, contents3));
        }
    }
    
    private static final class zzck extends zza
    {
        private final BaseImplementation$ResultHolder<Snapshots.LoadSnapshotsResult> zzib;
        
        public zzck(final BaseImplementation$ResultHolder<Snapshots.LoadSnapshotsResult> baseImplementation$ResultHolder) {
            this.zzib = (BaseImplementation$ResultHolder<Snapshots.LoadSnapshotsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzag(final DataHolder dataHolder) {
            this.zzib.setResult((Object)new zzax(dataHolder));
        }
    }
    
    private static final class zzcl extends zza
    {
        private final BaseImplementation$ResultHolder<Leaderboards.SubmitScoreResult> zzge;
        
        public zzcl(final BaseImplementation$ResultHolder<Leaderboards.SubmitScoreResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Leaderboards.SubmitScoreResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzd(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzcm(dataHolder));
        }
    }
    
    private static final class zzcm extends zzw implements SubmitScoreResult
    {
        private final ScoreSubmissionData zzic;
        
        public zzcm(final DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.zzic = new ScoreSubmissionData(dataHolder);
            }
            finally {
                dataHolder.close();
            }
        }
        
        @Override
        public final ScoreSubmissionData getScoreData() {
            return this.zzic;
        }
    }
    
    private static final class zzcn extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.CancelMatchResult> zzid;
        
        public zzcn(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.CancelMatchResult> baseImplementation$ResultHolder) {
            this.zzid = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.CancelMatchResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzc(final int n, final String s) {
            this.zzid.setResult((Object)new zzg(GamesStatusCodes.zza(n), s));
        }
    }
    
    private static final class zzco extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> zzie;
        
        public zzco(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult> baseImplementation$ResultHolder) {
            this.zzie = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.InitiateMatchResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzo(final DataHolder dataHolder) {
            this.zzie.setResult((Object)new zzaa(dataHolder));
        }
    }
    
    private static final class zzcp extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> zzif;
        
        public zzcp(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LeaveMatchResult> baseImplementation$ResultHolder) {
            this.zzif = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.LeaveMatchResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzq(final DataHolder dataHolder) {
            this.zzif.setResult((Object)new zzaj(dataHolder));
        }
    }
    
    private static final class zzcq extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchResult> zzig;
        
        public zzcq(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchResult> baseImplementation$ResultHolder) {
            this.zzig = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzn(final DataHolder dataHolder) {
            this.zzig.setResult((Object)new zzap(dataHolder));
        }
    }
    
    private static class zzcr extends zzw
    {
        private final TurnBasedMatch match;
        
        zzcr(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new TurnBasedMatchBuffer(dataHolder);
            try {
                if (((TurnBasedMatchBuffer)dataHolder).getCount() > 0) {
                    this.match = (TurnBasedMatch)((TurnBasedMatch)((TurnBasedMatchBuffer)dataHolder).get(0)).freeze();
                }
                else {
                    this.match = null;
                }
            }
            finally {
                ((TurnBasedMatchBuffer)dataHolder).release();
            }
        }
        
        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }
    
    private static final class zzcs extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> zzih;
        
        public zzcs(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.UpdateMatchResult> baseImplementation$ResultHolder) {
            this.zzih = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.UpdateMatchResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzp(final DataHolder dataHolder) {
            this.zzih.setResult((Object)new zzcv(dataHolder));
        }
    }
    
    private static final class zzct extends zza
    {
        private final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> zzii;
        
        public zzct(final BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchesResult> baseImplementation$ResultHolder) {
            this.zzii = (BaseImplementation$ResultHolder<TurnBasedMultiplayer.LoadMatchesResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final int n, final Bundle bundle) {
            bundle.setClassLoader(this.getClass().getClassLoader());
            this.zzii.setResult((Object)new zzaq(GamesStatusCodes.zza(n), bundle));
        }
    }
    
    private static final class zzcu implements UpdateAchievementResult
    {
        private final String zzfa;
        private final Status zzgf;
        
        zzcu(final int n, final String zzfa) {
            this.zzgf = GamesStatusCodes.zza(n);
            this.zzfa = zzfa;
        }
        
        @Override
        public final String getAchievementId() {
            return this.zzfa;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
    
    private static final class zzcv extends zzcr implements UpdateMatchResult
    {
        zzcv(final DataHolder dataHolder) {
            super(dataHolder);
        }
    }
    
    private static final class zzcw extends zzw implements UpdateRequestsResult
    {
        private final zzem zzij;
        
        zzcw(final DataHolder dataHolder) {
            super(dataHolder);
            this.zzij = zzem.zzbd(dataHolder);
        }
        
        @Override
        public final Set<String> getRequestIds() {
            return this.zzij.getRequestIds();
        }
        
        @Override
        public final int getRequestOutcome(final String s) {
            return this.zzij.getRequestOutcome(s);
        }
    }
    
    private static final class zzd extends zzw implements AcceptQuestResult
    {
        private final Quest zzgd;
        
        zzd(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new QuestBuffer(dataHolder);
            try {
                if (((QuestBuffer)dataHolder).getCount() > 0) {
                    this.zzgd = new QuestEntity((Quest)((QuestBuffer)dataHolder).get(0));
                }
                else {
                    this.zzgd = null;
                }
            }
            finally {
                ((QuestBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final Quest getQuest() {
            return this.zzgd;
        }
    }
    
    private static final class zze extends zza
    {
        private final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> zzge;
        
        zze(final BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Achievements.UpdateAchievementResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzb(final int n, final String s) {
            this.zzge.setResult((Object)new zzcu(n, s));
        }
    }
    
    private static final class zzf extends zza
    {
        private final BaseImplementation$ResultHolder<Achievements.LoadAchievementsResult> zzge;
        
        zzf(final BaseImplementation$ResultHolder<Achievements.LoadAchievementsResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Achievements.LoadAchievementsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzal(dataHolder));
        }
    }
    
    private static final class zzg implements CancelMatchResult
    {
        private final Status zzgf;
        private final String zzgg;
        
        zzg(final Status zzgf, final String zzgg) {
            this.zzgf = zzgf;
            this.zzgg = zzgg;
        }
        
        @Override
        public final String getMatchId() {
            return this.zzgg;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
    
    private static final class zzh extends zza
    {
        private final BaseImplementation$ResultHolder<Videos.CaptureAvailableResult> zzge;
        
        zzh(final BaseImplementation$ResultHolder<Videos.CaptureAvailableResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Videos.CaptureAvailableResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final int n, final boolean b) {
            this.zzge.setResult((Object)new zzi(new Status(n), b));
        }
    }
    
    private static final class zzi implements CaptureAvailableResult
    {
        private final Status zzgf;
        private final boolean zzgh;
        
        zzi(final Status zzgf, final boolean zzgh) {
            this.zzgf = zzgf;
            this.zzgh = zzgh;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
        
        @Override
        public final boolean isAvailable() {
            return this.zzgh;
        }
    }
    
    private static final class zzj extends zza
    {
        private final BaseImplementation$ResultHolder<Videos.CaptureCapabilitiesResult> zzge;
        
        zzj(final BaseImplementation$ResultHolder<Videos.CaptureCapabilitiesResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Videos.CaptureCapabilitiesResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final int n, final VideoCapabilities videoCapabilities) {
            this.zzge.setResult((Object)new zzk(new Status(n), videoCapabilities));
        }
    }
    
    private static final class zzk implements CaptureCapabilitiesResult
    {
        private final Status zzgf;
        private final VideoCapabilities zzgi;
        
        zzk(final Status zzgf, final VideoCapabilities zzgi) {
            this.zzgf = zzgf;
            this.zzgi = zzgi;
        }
        
        @Override
        public final VideoCapabilities getCapabilities() {
            return this.zzgi;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
    
    private static final class zzl extends zza
    {
        private final ListenerHolder<Videos.CaptureOverlayStateListener> zzgj;
        
        zzl(final ListenerHolder<Videos.CaptureOverlayStateListener> listenerHolder) {
            this.zzgj = (ListenerHolder<Videos.CaptureOverlayStateListener>)Preconditions.checkNotNull((Object)listenerHolder, (Object)"Callback must not be null");
        }
        
        @Override
        public final void onCaptureOverlayStateChanged(final int n) {
            this.zzgj.notifyListener((ListenerHolder$Notifier)new zzm(n));
        }
    }
    
    private static final class zzm implements ListenerHolder$Notifier<Videos.CaptureOverlayStateListener>
    {
        private final int zzgk;
        
        zzm(final int zzgk) {
            this.zzgk = zzgk;
        }
        
        public final void onNotifyListenerFailed() {
        }
    }
    
    private static final class zzn extends zza
    {
        private final BaseImplementation$ResultHolder<Videos.CaptureStateResult> zzge;
        
        public zzn(final BaseImplementation$ResultHolder<Videos.CaptureStateResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Videos.CaptureStateResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzg(final int n, final Bundle bundle) {
            this.zzge.setResult((Object)new zzo(new Status(n), CaptureState.zzb(bundle)));
        }
    }
    
    private static final class zzo implements CaptureStateResult
    {
        private final Status zzgf;
        private final CaptureState zzgl;
        
        zzo(final Status zzgf, final CaptureState zzgl) {
            this.zzgf = zzgf;
            this.zzgl = zzgl;
        }
        
        @Override
        public final CaptureState getCaptureState() {
            return this.zzgl;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
    
    private static final class zzp extends zzw implements ClaimMilestoneResult
    {
        private final Quest zzgd;
        private final Milestone zzgm;
        
        zzp(DataHolder dataHolder, final String s) {
            int i = 0;
            super(dataHolder);
            dataHolder = (DataHolder)new QuestBuffer(dataHolder);
            try {
                if (((QuestBuffer)dataHolder).getCount() > 0) {
                    this.zzgd = new QuestEntity((Quest)((QuestBuffer)dataHolder).get(0));
                    for (List<Milestone> zzcj = this.zzgd.zzcj(); i < zzcj.size(); ++i) {
                        if (zzcj.get(i).getMilestoneId().equals(s)) {
                            this.zzgm = zzcj.get(i);
                            return;
                        }
                    }
                    this.zzgm = null;
                }
                else {
                    this.zzgm = null;
                    this.zzgd = null;
                }
            }
            finally {
                ((QuestBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final Milestone getMilestone() {
            return this.zzgm;
        }
        
        @Override
        public final Quest getQuest() {
            return this.zzgd;
        }
    }
    
    private static final class zzq extends zzw implements CommitSnapshotResult
    {
        private final SnapshotMetadata zzgn;
        
        zzq(DataHolder dataHolder) {
            super(dataHolder);
            dataHolder = (DataHolder)new SnapshotMetadataBuffer(dataHolder);
            try {
                if (((SnapshotMetadataBuffer)dataHolder).getCount() > 0) {
                    this.zzgn = new SnapshotMetadataEntity((SnapshotMetadata)((SnapshotMetadataBuffer)dataHolder).get(0));
                }
                else {
                    this.zzgn = null;
                }
            }
            finally {
                ((SnapshotMetadataBuffer)dataHolder).release();
            }
        }
        
        @Override
        public final SnapshotMetadata getSnapshotMetadata() {
            return this.zzgn;
        }
    }
    
    private static final class zzr extends zzc
    {
        zzr(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }
    
    private static final class zzs implements DeleteSnapshotResult
    {
        private final Status zzgf;
        private final String zzgo;
        
        zzs(final int n, final String zzgo) {
            this.zzgf = GamesStatusCodes.zza(n);
            this.zzgo = zzgo;
        }
        
        @Override
        public final String getSnapshotId() {
            return this.zzgo;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
    
    private static final class zzt extends zzc
    {
        zzt(final DataHolder dataHolder) {
            super(dataHolder);
        }
        
        public final void zza(final RoomStatusUpdateListener roomStatusUpdateListener, final Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }
    
    private static final class zzu extends zza
    {
        private final BaseImplementation$ResultHolder<Events.LoadEventsResult> zzge;
        
        zzu(final BaseImplementation$ResultHolder<Events.LoadEventsResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Events.LoadEventsResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzb(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzam(dataHolder));
        }
    }
    
    private final class zzv extends zzej
    {
        public zzv() {
            super(zze.this.getContext().getMainLooper(), 1000);
        }
        
        @Override
        protected final void zzf(final String s, final int n) {
            try {
                if (zze.this.isConnected()) {
                    ((com.google.android.gms.games.internal.zzy)zze.this.getService()).zza(s, n);
                    return;
                }
                com.google.android.gms.games.internal.zzh.e("GamesClientImpl", new StringBuilder(String.valueOf(s).length() + 89).append("Unable to increment event ").append(s).append(" by ").append(n).append(" because the games client is no longer connected").toString());
            }
            catch (RemoteException ex) {
                zze.zza(zze.this, ex);
            }
            catch (SecurityException ex2) {
                zze.zza(zze.this, ex2);
            }
        }
    }
    
    private static class zzw extends DataHolderResult
    {
        protected zzw(final DataHolder dataHolder) {
            super(dataHolder, GamesStatusCodes.zza(dataHolder.getStatusCode()));
        }
    }
    
    private static final class zzx extends zza
    {
        private final BaseImplementation$ResultHolder<GamesMetadata.LoadGamesResult> zzge;
        
        zzx(final BaseImplementation$ResultHolder<GamesMetadata.LoadGamesResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<GamesMetadata.LoadGamesResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zzg(final DataHolder dataHolder) {
            this.zzge.setResult((Object)new zzan(dataHolder));
        }
    }
    
    private static final class zzy extends zza
    {
        private final BaseImplementation$ResultHolder<Games.GetServerAuthCodeResult> zzge;
        
        public zzy(final BaseImplementation$ResultHolder<Games.GetServerAuthCodeResult> baseImplementation$ResultHolder) {
            this.zzge = (BaseImplementation$ResultHolder<Games.GetServerAuthCodeResult>)Preconditions.checkNotNull((Object)baseImplementation$ResultHolder, (Object)"Holder must not be null");
        }
        
        @Override
        public final void zza(final int n, final String s) {
            this.zzge.setResult((Object)new zzz(GamesStatusCodes.zza(n), s));
        }
    }
    
    private static final class zzz implements GetServerAuthCodeResult
    {
        private final Status zzgf;
        private final String zzgp;
        
        zzz(final Status zzgf, final String zzgp) {
            this.zzgf = zzgf;
            this.zzgp = zzgp;
        }
        
        @Override
        public final String getCode() {
            return this.zzgp;
        }
        
        public final Status getStatus() {
            return this.zzgf;
        }
    }
}
