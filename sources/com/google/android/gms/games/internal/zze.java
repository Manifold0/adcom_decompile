package com.google.android.gms.games.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.FirstPartyScopes;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation.ResultHolder;
import com.google.android.gms.common.api.internal.DataHolderNotifier;
import com.google.android.gms.common.api.internal.DataHolderResult;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder.Notifier;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.BaseGmsClient.ConnectionProgressReportCallbacks;
import com.google.android.gms.common.internal.BaseGmsClient.SignOutCallbacks;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ClientSettings;
import com.google.android.gms.common.internal.GmsClient;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs.GamesExtraArgs;
import com.google.android.gms.drive.Contents;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Games;
import com.google.android.gms.games.Games.GamesOptions;
import com.google.android.gms.games.Games.GetServerAuthCodeResult;
import com.google.android.gms.games.GamesClientStatusCodes;
import com.google.android.gms.games.GamesMetadata.LoadGamesResult;
import com.google.android.gms.games.GamesStatusCodes;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.Players.LoadPlayersResult;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult;
import com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult;
import com.google.android.gms.games.event.EventBuffer;
import com.google.android.gms.games.event.Events.LoadEventsResult;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardEntity;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScoreEntity;
import com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult;
import com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult;
import com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult;
import com.google.android.gms.games.quest.Milestone;
import com.google.android.gms.games.quest.Quest;
import com.google.android.gms.games.quest.QuestBuffer;
import com.google.android.gms.games.quest.QuestEntity;
import com.google.android.gms.games.quest.QuestUpdateListener;
import com.google.android.gms.games.quest.Quests.AcceptQuestResult;
import com.google.android.gms.games.quest.Quests.ClaimMilestoneResult;
import com.google.android.gms.games.quest.Quests.LoadQuestsResult;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import com.google.android.gms.games.request.Requests.LoadRequestsResult;
import com.google.android.gms.games.request.Requests.UpdateRequestsResult;
import com.google.android.gms.games.snapshot.Snapshot;
import com.google.android.gms.games.snapshot.SnapshotContents;
import com.google.android.gms.games.snapshot.SnapshotEntity;
import com.google.android.gms.games.snapshot.SnapshotMetadata;
import com.google.android.gms.games.snapshot.SnapshotMetadataBuffer;
import com.google.android.gms.games.snapshot.SnapshotMetadataChange;
import com.google.android.gms.games.snapshot.SnapshotMetadataEntity;
import com.google.android.gms.games.snapshot.Snapshots.CommitSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.DeleteSnapshotResult;
import com.google.android.gms.games.snapshot.Snapshots.LoadSnapshotsResult;
import com.google.android.gms.games.snapshot.Snapshots.OpenSnapshotResult;
import com.google.android.gms.games.stats.PlayerStats;
import com.google.android.gms.games.stats.PlayerStatsBuffer;
import com.google.android.gms.games.stats.Stats.LoadPlayerStatsResult;
import com.google.android.gms.games.video.CaptureState;
import com.google.android.gms.games.video.VideoCapabilities;
import com.google.android.gms.games.video.Videos.CaptureAvailableResult;
import com.google.android.gms.games.video.Videos.CaptureCapabilitiesResult;
import com.google.android.gms.games.video.Videos.CaptureOverlayStateListener;
import com.google.android.gms.games.video.Videos.CaptureStateResult;
import com.google.android.gms.internal.games.zzej;
import com.google.android.gms.internal.games.zzel;
import com.google.android.gms.internal.games.zzem;
import com.google.android.gms.signin.internal.SignInClientImpl;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class zze extends GmsClient<zzy> {
    private zzel zzfp = new zzf(this);
    private final String zzfq;
    private PlayerEntity zzfr;
    private GameEntity zzfs;
    private final zzac zzft;
    private boolean zzfu = false;
    private final Binder zzfv;
    private final long zzfw;
    private final GamesOptions zzfx;
    private boolean zzfy = false;
    private Bundle zzfz;

    private static abstract class zzc extends DataHolderNotifier<RoomStatusUpdateListener> {
        zzc(DataHolder dataHolder) {
            super(dataHolder);
        }

        protected /* synthetic */ void notifyListener(Object obj, DataHolder dataHolder) {
            zza((RoomStatusUpdateListener) obj, zze.zzba(dataHolder));
        }

        protected abstract void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room);
    }

    private static abstract class zza extends zzc {
        private final ArrayList<String> zzgc = new ArrayList();

        zza(DataHolder dataHolder, String[] strArr) {
            super(dataHolder);
            for (Object add : strArr) {
                this.zzgc.add(add);
            }
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            zza(roomStatusUpdateListener, room, this.zzgc);
        }

        protected abstract void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList);
    }

    private static abstract class zzw extends DataHolderResult {
        protected zzw(DataHolder dataHolder) {
            super(dataHolder, GamesStatusCodes.zza(dataHolder.getStatusCode()));
        }
    }

    private static abstract class zzcr extends zzw {
        private final TurnBasedMatch match;

        zzcr(DataHolder dataHolder) {
            super(dataHolder);
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    this.match = (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze();
                } else {
                    this.match = null;
                }
                turnBasedMatchBuffer.release();
            } catch (Throwable th) {
                turnBasedMatchBuffer.release();
            }
        }

        public TurnBasedMatch getMatch() {
            return this.match;
        }
    }

    private static final class zzaa extends zzcr implements InitiateMatchResult {
        zzaa(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzab extends zza {
        private final ListenerHolder<OnInvitationReceivedListener> zzgj;

        zzab(ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        public final void onInvitationRemoved(String str) {
            this.zzgj.notifyListener(new zzad(str));
        }

        public final void zzl(DataHolder dataHolder) {
            InvitationBuffer invitationBuffer = new InvitationBuffer(dataHolder);
            Invitation invitation = null;
            try {
                if (invitationBuffer.getCount() > 0) {
                    invitation = (Invitation) ((Invitation) invitationBuffer.get(0)).freeze();
                }
                invitationBuffer.release();
                if (invitation != null) {
                    this.zzgj.notifyListener(new zzac(invitation));
                }
            } catch (Throwable th) {
                invitationBuffer.release();
            }
        }
    }

    private static final class zzac implements Notifier<OnInvitationReceivedListener> {
        private final Invitation zzgq;

        zzac(Invitation invitation) {
            this.zzgq = invitation;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnInvitationReceivedListener) obj).onInvitationReceived(this.zzgq);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzad implements Notifier<OnInvitationReceivedListener> {
        private final String zzgr;

        zzad(String str) {
            this.zzgr = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnInvitationReceivedListener) obj).onInvitationRemoved(this.zzgr);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzae extends zza {
        private final ResultHolder<LoadInvitationsResult> zzge;

        zzae(ResultHolder<LoadInvitationsResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzk(DataHolder dataHolder) {
            this.zzge.setResult(new zzao(dataHolder));
        }
    }

    private static abstract class zzb extends DataHolderNotifier<RoomUpdateListener> {
        zzb(DataHolder dataHolder) {
            super(dataHolder);
        }

        protected /* synthetic */ void notifyListener(Object obj, DataHolder dataHolder) {
            zza((RoomUpdateListener) obj, zze.zzba(dataHolder), dataHolder.getStatusCode());
        }

        protected abstract void zza(RoomUpdateListener roomUpdateListener, Room room, int i);
    }

    private static final class zzaf extends zzb {
        public zzaf(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onJoinedRoom(i, room);
        }
    }

    private static final class zzag extends zzw implements LeaderboardMetadataResult {
        private final LeaderboardBuffer zzgs;

        zzag(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgs = new LeaderboardBuffer(dataHolder);
        }

        public final LeaderboardBuffer getLeaderboards() {
            return this.zzgs;
        }
    }

    private static final class zzah extends zza {
        private final ResultHolder<LoadScoresResult> zzge;

        zzah(ResultHolder<LoadScoresResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(DataHolder dataHolder, DataHolder dataHolder2) {
            this.zzge.setResult(new zzaw(dataHolder, dataHolder2));
        }
    }

    private static final class zzai extends zza {
        private final ResultHolder<LeaderboardMetadataResult> zzge;

        zzai(ResultHolder<LeaderboardMetadataResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzc(DataHolder dataHolder) {
            this.zzge.setResult(new zzag(dataHolder));
        }
    }

    private static final class zzaj extends zzcr implements LeaveMatchResult {
        zzaj(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzak implements Notifier<RoomUpdateListener> {
        private final int statusCode;
        private final String zzgt;

        zzak(int i, String str) {
            this.statusCode = i;
            this.zzgt = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((RoomUpdateListener) obj).onLeftRoom(this.statusCode, this.zzgt);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzal extends zzw implements LoadAchievementsResult {
        private final AchievementBuffer zzgu;

        zzal(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgu = new AchievementBuffer(dataHolder);
        }

        public final AchievementBuffer getAchievements() {
            return this.zzgu;
        }
    }

    private static final class zzam extends zzw implements LoadEventsResult {
        private final EventBuffer zzgv;

        zzam(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgv = new EventBuffer(dataHolder);
        }

        public final EventBuffer getEvents() {
            return this.zzgv;
        }
    }

    private static final class zzan extends zzw implements LoadGamesResult {
        private final GameBuffer zzgw;

        zzan(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgw = new GameBuffer(dataHolder);
        }

        public final GameBuffer getGames() {
            return this.zzgw;
        }
    }

    private static final class zzao extends zzw implements LoadInvitationsResult {
        private final InvitationBuffer zzgx;

        zzao(DataHolder dataHolder) {
            super(dataHolder);
            this.zzgx = new InvitationBuffer(dataHolder);
        }

        public final InvitationBuffer getInvitations() {
            return this.zzgx;
        }
    }

    private static final class zzap extends zzcr implements LoadMatchResult {
        zzap(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzaq implements LoadMatchesResult {
        private final Status zzgf;
        private final LoadMatchesResponse zzgy;

        zzaq(Status status, Bundle bundle) {
            this.zzgf = status;
            this.zzgy = new LoadMatchesResponse(bundle);
        }

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

    private static final class zzar extends zzw implements LoadPlayerScoreResult {
        private final LeaderboardScoreEntity zzgz;

        zzar(DataHolder dataHolder) {
            super(dataHolder);
            LeaderboardScoreBuffer leaderboardScoreBuffer = new LeaderboardScoreBuffer(dataHolder);
            try {
                if (leaderboardScoreBuffer.getCount() > 0) {
                    this.zzgz = (LeaderboardScoreEntity) ((LeaderboardScore) leaderboardScoreBuffer.get(0)).freeze();
                } else {
                    this.zzgz = null;
                }
                leaderboardScoreBuffer.release();
            } catch (Throwable th) {
                leaderboardScoreBuffer.release();
            }
        }

        public final LeaderboardScore getScore() {
            return this.zzgz;
        }
    }

    private static final class zzas extends zzw implements LoadPlayerStatsResult {
        private final PlayerStats zzha;

        zzas(DataHolder dataHolder) {
            super(dataHolder);
            PlayerStatsBuffer playerStatsBuffer = new PlayerStatsBuffer(dataHolder);
            try {
                if (playerStatsBuffer.getCount() > 0) {
                    this.zzha = new com.google.android.gms.games.stats.zza((PlayerStats) playerStatsBuffer.get(0));
                } else {
                    this.zzha = null;
                }
                playerStatsBuffer.release();
            } catch (Throwable th) {
                playerStatsBuffer.release();
            }
        }

        public final PlayerStats getPlayerStats() {
            return this.zzha;
        }
    }

    private static final class zzat extends zzw implements LoadPlayersResult {
        private final PlayerBuffer zzhb;

        zzat(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhb = new PlayerBuffer(dataHolder);
        }

        public final PlayerBuffer getPlayers() {
            return this.zzhb;
        }
    }

    private static final class zzau extends zzw implements LoadQuestsResult {
        private final DataHolder zzhc;

        zzau(DataHolder dataHolder) {
            super(dataHolder);
            this.zzhc = dataHolder;
        }

        public final QuestBuffer getQuests() {
            return new QuestBuffer(this.zzhc);
        }
    }

    private static final class zzav implements LoadRequestsResult {
        private final Status zzgf;
        private final Bundle zzhd;

        zzav(Status status, Bundle bundle) {
            this.zzgf = status;
            this.zzhd = bundle;
        }

        public final GameRequestBuffer getRequests(int i) {
            String str;
            switch (i) {
                case 1:
                    str = "GIFT";
                    break;
                case 2:
                    str = "WISH";
                    break;
                default:
                    zzh.m1660e("RequestType", "Unknown request type: " + i);
                    str = "UNKNOWN_TYPE";
                    break;
            }
            return !this.zzhd.containsKey(str) ? null : new GameRequestBuffer((DataHolder) this.zzhd.get(str));
        }

        public final Status getStatus() {
            return this.zzgf;
        }

        public final void release() {
            for (String parcelable : this.zzhd.keySet()) {
                DataHolder dataHolder = (DataHolder) this.zzhd.getParcelable(parcelable);
                if (dataHolder != null) {
                    dataHolder.close();
                }
            }
        }
    }

    private static final class zzaw extends zzw implements LoadScoresResult {
        private final LeaderboardEntity zzhe;
        private final LeaderboardScoreBuffer zzhf;

        zzaw(DataHolder dataHolder, DataHolder dataHolder2) {
            super(dataHolder2);
            LeaderboardBuffer leaderboardBuffer = new LeaderboardBuffer(dataHolder);
            try {
                if (leaderboardBuffer.getCount() > 0) {
                    this.zzhe = (LeaderboardEntity) ((Leaderboard) leaderboardBuffer.get(0)).freeze();
                } else {
                    this.zzhe = null;
                }
                leaderboardBuffer.release();
                this.zzhf = new LeaderboardScoreBuffer(dataHolder2);
            } catch (Throwable th) {
                leaderboardBuffer.release();
            }
        }

        public final Leaderboard getLeaderboard() {
            return this.zzhe;
        }

        public final LeaderboardScoreBuffer getScores() {
            return this.zzhf;
        }
    }

    private static final class zzax extends zzw implements LoadSnapshotsResult {
        zzax(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final SnapshotMetadataBuffer getSnapshots() {
            return new SnapshotMetadataBuffer(this.mDataHolder);
        }
    }

    private static final class zzay implements Notifier<OnTurnBasedMatchUpdateReceivedListener> {
        private final String zzhg;

        zzay(String str) {
            this.zzhg = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnTurnBasedMatchUpdateReceivedListener) obj).onTurnBasedMatchRemoved(this.zzhg);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzaz extends zza {
        private final ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> zzgj;

        zzaz(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        public final void onTurnBasedMatchRemoved(String str) {
            this.zzgj.notifyListener(new zzay(str));
        }

        public final void zzr(DataHolder dataHolder) {
            TurnBasedMatchBuffer turnBasedMatchBuffer = new TurnBasedMatchBuffer(dataHolder);
            TurnBasedMatch turnBasedMatch = null;
            try {
                if (turnBasedMatchBuffer.getCount() > 0) {
                    turnBasedMatch = (TurnBasedMatch) ((TurnBasedMatch) turnBasedMatchBuffer.get(0)).freeze();
                }
                turnBasedMatchBuffer.release();
                if (turnBasedMatch != null) {
                    this.zzgj.notifyListener(new zzba(turnBasedMatch));
                }
            } catch (Throwable th) {
                turnBasedMatchBuffer.release();
            }
        }
    }

    private static final class zzba implements Notifier<OnTurnBasedMatchUpdateReceivedListener> {
        private final TurnBasedMatch match;

        zzba(TurnBasedMatch turnBasedMatch) {
            this.match = turnBasedMatch;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnTurnBasedMatchUpdateReceivedListener) obj).onTurnBasedMatchReceived(this.match);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbb implements Notifier<RealTimeMessageReceivedListener> {
        private final RealTimeMessage zzhh;

        zzbb(RealTimeMessage realTimeMessage) {
            this.zzhh = realTimeMessage;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((RealTimeMessageReceivedListener) obj).onRealTimeMessageReceived(this.zzhh);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbc extends zzw implements OpenSnapshotResult {
        private final String zzei;
        private final Snapshot zzhi;
        private final Snapshot zzhj;
        private final SnapshotContents zzhk;

        zzbc(DataHolder dataHolder, Contents contents) {
            this(dataHolder, null, contents, null, null);
        }

        zzbc(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            boolean z = true;
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() == 0) {
                    this.zzhi = null;
                    this.zzhj = null;
                } else if (snapshotMetadataBuffer.getCount() == 1) {
                    if (dataHolder.getStatusCode() == GamesStatusCodes.STATUS_SNAPSHOT_CONFLICT) {
                        z = false;
                    }
                    Asserts.checkState(z);
                    this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                    this.zzhj = null;
                } else {
                    this.zzhi = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0)), new com.google.android.gms.games.snapshot.zza(contents));
                    this.zzhj = new SnapshotEntity(new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(1)), new com.google.android.gms.games.snapshot.zza(contents2));
                }
                snapshotMetadataBuffer.release();
                this.zzei = str;
                this.zzhk = new com.google.android.gms.games.snapshot.zza(contents3);
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
            }
        }

        public final String getConflictId() {
            return this.zzei;
        }

        public final Snapshot getConflictingSnapshot() {
            return this.zzhj;
        }

        public final SnapshotContents getResolutionSnapshotContents() {
            return this.zzhk;
        }

        public final Snapshot getSnapshot() {
            return this.zzhi;
        }
    }

    private static final class zzbd implements Notifier<RoomStatusUpdateListener> {
        private final String zzhl;

        zzbd(String str) {
            this.zzhl = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((RoomStatusUpdateListener) obj).onP2PConnected(this.zzhl);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbe implements Notifier<RoomStatusUpdateListener> {
        private final String zzhl;

        zzbe(String str) {
            this.zzhl = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((RoomStatusUpdateListener) obj).onP2PDisconnected(this.zzhl);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbf extends zza {
        zzbf(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersConnected(room, arrayList);
        }
    }

    private static final class zzbg extends zza {
        zzbg(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerDeclined(room, arrayList);
        }
    }

    private static final class zzbh extends zza {
        zzbh(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeersDisconnected(room, arrayList);
        }
    }

    private static final class zzbi extends zza {
        zzbi(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerInvitedToRoom(room, arrayList);
        }
    }

    private static final class zzbj extends zza {
        zzbj(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerJoined(room, arrayList);
        }
    }

    private static final class zzbk extends zza {
        zzbk(DataHolder dataHolder, String[] strArr) {
            super(dataHolder, strArr);
        }

        protected final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room, ArrayList<String> arrayList) {
            roomStatusUpdateListener.onPeerLeft(room, arrayList);
        }
    }

    private static final class zzbl extends zza {
        private final ResultHolder<LoadPlayerScoreResult> zzge;

        zzbl(ResultHolder<LoadPlayerScoreResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzac(DataHolder dataHolder) {
            this.zzge.setResult(new zzar(dataHolder));
        }
    }

    private static final class zzbm extends zza {
        private final ResultHolder<LoadPlayerStatsResult> zzge;

        public zzbm(ResultHolder<LoadPlayerStatsResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzap(DataHolder dataHolder) {
            this.zzge.setResult(new zzas(dataHolder));
        }
    }

    private static final class zzbn extends zza {
        private final ResultHolder<LoadPlayersResult> zzge;

        zzbn(ResultHolder<LoadPlayersResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zze(DataHolder dataHolder) {
            this.zzge.setResult(new zzat(dataHolder));
        }

        public final void zzf(DataHolder dataHolder) {
            this.zzge.setResult(new zzat(dataHolder));
        }
    }

    private static final class zzbo extends zzb {
        private final zzac zzft;

        public zzbo(zzac zzac) {
            this.zzft = zzac;
        }

        public final zzaa zzn() {
            return new zzaa(this.zzft.zzjd);
        }
    }

    private static final class zzbp extends zza {
        private final ResultHolder<AcceptQuestResult> zzhm;

        public zzbp(ResultHolder<AcceptQuestResult> resultHolder) {
            this.zzhm = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzaj(DataHolder dataHolder) {
            this.zzhm.setResult(new zzd(dataHolder));
        }
    }

    private static final class zzbq implements Notifier<QuestUpdateListener> {
        private final Quest zzgd;

        zzbq(Quest quest) {
            this.zzgd = quest;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((QuestUpdateListener) obj).onQuestCompleted(this.zzgd);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbr extends zza {
        private final ResultHolder<ClaimMilestoneResult> zzhn;
        private final String zzho;

        public zzbr(ResultHolder<ClaimMilestoneResult> resultHolder, String str) {
            this.zzhn = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
            this.zzho = (String) Preconditions.checkNotNull(str, "MilestoneId must not be null");
        }

        public final void zzai(DataHolder dataHolder) {
            this.zzhn.setResult(new zzp(dataHolder, this.zzho));
        }
    }

    private static final class zzbs extends zza {
        private final ListenerHolder<QuestUpdateListener> zzgj;

        zzbs(ListenerHolder<QuestUpdateListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        private static Quest zzbc(DataHolder dataHolder) {
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            Quest quest = null;
            try {
                if (questBuffer.getCount() > 0) {
                    quest = (Quest) ((Quest) questBuffer.get(0)).freeze();
                }
                questBuffer.release();
                return quest;
            } catch (Throwable th) {
                questBuffer.release();
            }
        }

        public final void zzak(DataHolder dataHolder) {
            Quest zzbc = zzbc(dataHolder);
            if (zzbc != null) {
                this.zzgj.notifyListener(new zzbq(zzbc));
            }
        }
    }

    private static final class zzbt extends zza {
        private final ResultHolder<LoadQuestsResult> zzhp;

        public zzbt(ResultHolder<LoadQuestsResult> resultHolder) {
            this.zzhp = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzam(DataHolder dataHolder) {
            this.zzhp.setResult(new zzau(dataHolder));
        }
    }

    private static final class zzbu implements Notifier<ReliableMessageSentCallback> {
        private final int statusCode;
        private final int token;
        private final String zzhq;

        zzbu(int i, int i2, String str) {
            this.statusCode = i;
            this.token = i2;
            this.zzhq = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ReliableMessageSentCallback reliableMessageSentCallback = (ReliableMessageSentCallback) obj;
            if (reliableMessageSentCallback != null) {
                reliableMessageSentCallback.onRealTimeMessageSent(this.statusCode, this.token, this.zzhq);
            }
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbv extends zza {
        private final ListenerHolder<ReliableMessageSentCallback> zzhr;

        public zzbv(ListenerHolder<ReliableMessageSentCallback> listenerHolder) {
            this.zzhr = listenerHolder;
        }

        public final void zza(int i, int i2, String str) {
            if (this.zzhr != null) {
                this.zzhr.notifyListener(new zzbu(i, i2, str));
            }
        }
    }

    private static final class zzbw extends zza {
        private final ListenerHolder<OnRequestReceivedListener> zzgj;

        zzbw(ListenerHolder<OnRequestReceivedListener> listenerHolder) {
            this.zzgj = listenerHolder;
        }

        public final void onRequestRemoved(String str) {
            this.zzgj.notifyListener(new zzby(str));
        }

        public final void zzm(DataHolder dataHolder) {
            GameRequestBuffer gameRequestBuffer = new GameRequestBuffer(dataHolder);
            GameRequest gameRequest = null;
            try {
                if (gameRequestBuffer.getCount() > 0) {
                    gameRequest = (GameRequest) ((GameRequest) gameRequestBuffer.get(0)).freeze();
                }
                gameRequestBuffer.release();
                if (gameRequest != null) {
                    this.zzgj.notifyListener(new zzbx(gameRequest));
                }
            } catch (Throwable th) {
                gameRequestBuffer.release();
            }
        }
    }

    private static final class zzbx implements Notifier<OnRequestReceivedListener> {
        private final GameRequest zzhs;

        zzbx(GameRequest gameRequest) {
            this.zzhs = gameRequest;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnRequestReceivedListener) obj).onRequestReceived(this.zzhs);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzby implements Notifier<OnRequestReceivedListener> {
        private final String zzht;

        zzby(String str) {
            this.zzht = str;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((OnRequestReceivedListener) obj).onRequestRemoved(this.zzht);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzbz extends zza {
        private final ResultHolder<LoadRequestsResult> zzhu;

        public zzbz(ResultHolder<LoadRequestsResult> resultHolder) {
            this.zzhu = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzb(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzhu.setResult(new zzav(GamesStatusCodes.zza(i), bundle));
        }
    }

    private static final class zzca extends zza {
        private final ResultHolder<UpdateRequestsResult> zzhv;

        public zzca(ResultHolder<UpdateRequestsResult> resultHolder) {
            this.zzhv = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzad(DataHolder dataHolder) {
            this.zzhv.setResult(new zzcw(dataHolder));
        }
    }

    private static final class zzcb extends zzc {
        zzcb(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomAutoMatching(room);
        }
    }

    private static final class zzcc extends zza {
        private final ListenerHolder<? extends RoomUpdateListener> zzhw;
        private final ListenerHolder<? extends RoomStatusUpdateListener> zzhx;
        private final ListenerHolder<? extends RealTimeMessageReceivedListener> zzhy;

        public zzcc(ListenerHolder<? extends RoomUpdateListener> listenerHolder) {
            this.zzhw = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callbacks must not be null");
            this.zzhx = null;
            this.zzhy = null;
        }

        public zzcc(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3) {
            this.zzhw = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callbacks must not be null");
            this.zzhx = listenerHolder2;
            this.zzhy = listenerHolder3;
        }

        public final void onLeftRoom(int i, String str) {
            this.zzhw.notifyListener(new zzak(i, str));
        }

        public final void onP2PConnected(String str) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbd(str));
            }
        }

        public final void onP2PDisconnected(String str) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbe(str));
            }
        }

        public final void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {
            if (this.zzhy != null) {
                this.zzhy.notifyListener(new zzbb(realTimeMessage));
            }
        }

        public final void zza(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbi(dataHolder, strArr));
            }
        }

        public final void zzb(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbj(dataHolder, strArr));
            }
        }

        public final void zzc(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbk(dataHolder, strArr));
            }
        }

        public final void zzd(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbg(dataHolder, strArr));
            }
        }

        public final void zze(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbf(dataHolder, strArr));
            }
        }

        public final void zzf(DataHolder dataHolder, String[] strArr) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzbh(dataHolder, strArr));
            }
        }

        public final void zzs(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzcf(dataHolder));
        }

        public final void zzt(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzaf(dataHolder));
        }

        public final void zzu(DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzce(dataHolder));
            }
        }

        public final void zzv(DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzcb(dataHolder));
            }
        }

        public final void zzw(DataHolder dataHolder) {
            this.zzhw.notifyListener(new zzcd(dataHolder));
        }

        public final void zzx(DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzr(dataHolder));
            }
        }

        public final void zzy(DataHolder dataHolder) {
            if (this.zzhx != null) {
                this.zzhx.notifyListener(new zzt(dataHolder));
            }
        }
    }

    private static final class zzcd extends zzb {
        zzcd(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomConnected(i, room);
        }
    }

    private static final class zzce extends zzc {
        zzce(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onRoomConnecting(room);
        }
    }

    private static final class zzcf extends zzb {
        public zzcf(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomUpdateListener roomUpdateListener, Room room, int i) {
            roomUpdateListener.onRoomCreated(i, room);
        }
    }

    private static final class zzcg extends zza {
        private final ResultHolder<Status> zzge;

        public zzcg(ResultHolder<Status> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void onSignOutComplete() {
            this.zzge.setResult(GamesStatusCodes.zza(0));
        }
    }

    private static final class zzch extends zza {
        private final ResultHolder<CommitSnapshotResult> zzhz;

        public zzch(ResultHolder<CommitSnapshotResult> resultHolder) {
            this.zzhz = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzah(DataHolder dataHolder) {
            this.zzhz.setResult(new zzq(dataHolder));
        }
    }

    static final class zzci extends zza {
        private final ResultHolder<DeleteSnapshotResult> zzge;

        public zzci(ResultHolder<DeleteSnapshotResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzd(int i, String str) {
            this.zzge.setResult(new zzs(i, str));
        }
    }

    private static final class zzcj extends zza {
        private final ResultHolder<OpenSnapshotResult> zzia;

        public zzcj(ResultHolder<OpenSnapshotResult> resultHolder) {
            this.zzia = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(DataHolder dataHolder, Contents contents) {
            this.zzia.setResult(new zzbc(dataHolder, contents));
        }

        public final void zza(DataHolder dataHolder, String str, Contents contents, Contents contents2, Contents contents3) {
            this.zzia.setResult(new zzbc(dataHolder, str, contents, contents2, contents3));
        }
    }

    private static final class zzck extends zza {
        private final ResultHolder<LoadSnapshotsResult> zzib;

        public zzck(ResultHolder<LoadSnapshotsResult> resultHolder) {
            this.zzib = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzag(DataHolder dataHolder) {
            this.zzib.setResult(new zzax(dataHolder));
        }
    }

    private static final class zzcl extends zza {
        private final ResultHolder<SubmitScoreResult> zzge;

        public zzcl(ResultHolder<SubmitScoreResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzd(DataHolder dataHolder) {
            this.zzge.setResult(new zzcm(dataHolder));
        }
    }

    private static final class zzcm extends zzw implements SubmitScoreResult {
        private final ScoreSubmissionData zzic;

        public zzcm(DataHolder dataHolder) {
            super(dataHolder);
            try {
                this.zzic = new ScoreSubmissionData(dataHolder);
            } finally {
                dataHolder.close();
            }
        }

        public final ScoreSubmissionData getScoreData() {
            return this.zzic;
        }
    }

    private static final class zzcn extends zza {
        private final ResultHolder<CancelMatchResult> zzid;

        public zzcn(ResultHolder<CancelMatchResult> resultHolder) {
            this.zzid = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzc(int i, String str) {
            this.zzid.setResult(new zzg(GamesStatusCodes.zza(i), str));
        }
    }

    private static final class zzco extends zza {
        private final ResultHolder<InitiateMatchResult> zzie;

        public zzco(ResultHolder<InitiateMatchResult> resultHolder) {
            this.zzie = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzo(DataHolder dataHolder) {
            this.zzie.setResult(new zzaa(dataHolder));
        }
    }

    private static final class zzcp extends zza {
        private final ResultHolder<LeaveMatchResult> zzif;

        public zzcp(ResultHolder<LeaveMatchResult> resultHolder) {
            this.zzif = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzq(DataHolder dataHolder) {
            this.zzif.setResult(new zzaj(dataHolder));
        }
    }

    private static final class zzcq extends zza {
        private final ResultHolder<LoadMatchResult> zzig;

        public zzcq(ResultHolder<LoadMatchResult> resultHolder) {
            this.zzig = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzn(DataHolder dataHolder) {
            this.zzig.setResult(new zzap(dataHolder));
        }
    }

    private static final class zzcs extends zza {
        private final ResultHolder<UpdateMatchResult> zzih;

        public zzcs(ResultHolder<UpdateMatchResult> resultHolder) {
            this.zzih = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzp(DataHolder dataHolder) {
            this.zzih.setResult(new zzcv(dataHolder));
        }
    }

    private static final class zzct extends zza {
        private final ResultHolder<LoadMatchesResult> zzii;

        public zzct(ResultHolder<LoadMatchesResult> resultHolder) {
            this.zzii = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(int i, Bundle bundle) {
            bundle.setClassLoader(getClass().getClassLoader());
            this.zzii.setResult(new zzaq(GamesStatusCodes.zza(i), bundle));
        }
    }

    private static final class zzcu implements UpdateAchievementResult {
        private final String zzfa;
        private final Status zzgf;

        zzcu(int i, String str) {
            this.zzgf = GamesStatusCodes.zza(i);
            this.zzfa = str;
        }

        public final String getAchievementId() {
            return this.zzfa;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzcv extends zzcr implements UpdateMatchResult {
        zzcv(DataHolder dataHolder) {
            super(dataHolder);
        }
    }

    private static final class zzcw extends zzw implements UpdateRequestsResult {
        private final zzem zzij;

        zzcw(DataHolder dataHolder) {
            super(dataHolder);
            this.zzij = zzem.zzbd(dataHolder);
        }

        public final Set<String> getRequestIds() {
            return this.zzij.getRequestIds();
        }

        public final int getRequestOutcome(String str) {
            return this.zzij.getRequestOutcome(str);
        }
    }

    private static final class zzd extends zzw implements AcceptQuestResult {
        private final Quest zzgd;

        zzd(DataHolder dataHolder) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.zzgd = new QuestEntity((Quest) questBuffer.get(0));
                } else {
                    this.zzgd = null;
                }
                questBuffer.release();
            } catch (Throwable th) {
                questBuffer.release();
            }
        }

        public final Quest getQuest() {
            return this.zzgd;
        }
    }

    private static final class zze extends zza {
        private final ResultHolder<UpdateAchievementResult> zzge;

        zze(ResultHolder<UpdateAchievementResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzb(int i, String str) {
            this.zzge.setResult(new zzcu(i, str));
        }
    }

    private static final class zzf extends zza {
        private final ResultHolder<LoadAchievementsResult> zzge;

        zzf(ResultHolder<LoadAchievementsResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(DataHolder dataHolder) {
            this.zzge.setResult(new zzal(dataHolder));
        }
    }

    private static final class zzg implements CancelMatchResult {
        private final Status zzgf;
        private final String zzgg;

        zzg(Status status, String str) {
            this.zzgf = status;
            this.zzgg = str;
        }

        public final String getMatchId() {
            return this.zzgg;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzh extends zza {
        private final ResultHolder<CaptureAvailableResult> zzge;

        zzh(ResultHolder<CaptureAvailableResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(int i, boolean z) {
            this.zzge.setResult(new zzi(new Status(i), z));
        }
    }

    private static final class zzi implements CaptureAvailableResult {
        private final Status zzgf;
        private final boolean zzgh;

        zzi(Status status, boolean z) {
            this.zzgf = status;
            this.zzgh = z;
        }

        public final Status getStatus() {
            return this.zzgf;
        }

        public final boolean isAvailable() {
            return this.zzgh;
        }
    }

    private static final class zzj extends zza {
        private final ResultHolder<CaptureCapabilitiesResult> zzge;

        zzj(ResultHolder<CaptureCapabilitiesResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(int i, VideoCapabilities videoCapabilities) {
            this.zzge.setResult(new zzk(new Status(i), videoCapabilities));
        }
    }

    private static final class zzk implements CaptureCapabilitiesResult {
        private final Status zzgf;
        private final VideoCapabilities zzgi;

        zzk(Status status, VideoCapabilities videoCapabilities) {
            this.zzgf = status;
            this.zzgi = videoCapabilities;
        }

        public final VideoCapabilities getCapabilities() {
            return this.zzgi;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzl extends zza {
        private final ListenerHolder<CaptureOverlayStateListener> zzgj;

        zzl(ListenerHolder<CaptureOverlayStateListener> listenerHolder) {
            this.zzgj = (ListenerHolder) Preconditions.checkNotNull(listenerHolder, "Callback must not be null");
        }

        public final void onCaptureOverlayStateChanged(int i) {
            this.zzgj.notifyListener(new zzm(i));
        }
    }

    private static final class zzm implements Notifier<CaptureOverlayStateListener> {
        private final int zzgk;

        zzm(int i) {
            this.zzgk = i;
        }

        public final /* synthetic */ void notifyListener(Object obj) {
            ((CaptureOverlayStateListener) obj).onCaptureOverlayStateChanged(this.zzgk);
        }

        public final void onNotifyListenerFailed() {
        }
    }

    private static final class zzn extends zza {
        private final ResultHolder<CaptureStateResult> zzge;

        public zzn(ResultHolder<CaptureStateResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzg(int i, Bundle bundle) {
            this.zzge.setResult(new zzo(new Status(i), CaptureState.zzb(bundle)));
        }
    }

    private static final class zzo implements CaptureStateResult {
        private final Status zzgf;
        private final CaptureState zzgl;

        zzo(Status status, CaptureState captureState) {
            this.zzgf = status;
            this.zzgl = captureState;
        }

        public final CaptureState getCaptureState() {
            return this.zzgl;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzp extends zzw implements ClaimMilestoneResult {
        private final Quest zzgd;
        private final Milestone zzgm;

        zzp(DataHolder dataHolder, String str) {
            super(dataHolder);
            QuestBuffer questBuffer = new QuestBuffer(dataHolder);
            try {
                if (questBuffer.getCount() > 0) {
                    this.zzgd = new QuestEntity((Quest) questBuffer.get(0));
                    List zzcj = this.zzgd.zzcj();
                    int size = zzcj.size();
                    for (int i = 0; i < size; i++) {
                        if (((Milestone) zzcj.get(i)).getMilestoneId().equals(str)) {
                            this.zzgm = (Milestone) zzcj.get(i);
                            return;
                        }
                    }
                    this.zzgm = null;
                } else {
                    this.zzgm = null;
                    this.zzgd = null;
                }
                questBuffer.release();
            } finally {
                questBuffer.release();
            }
        }

        public final Milestone getMilestone() {
            return this.zzgm;
        }

        public final Quest getQuest() {
            return this.zzgd;
        }
    }

    private static final class zzq extends zzw implements CommitSnapshotResult {
        private final SnapshotMetadata zzgn;

        zzq(DataHolder dataHolder) {
            super(dataHolder);
            SnapshotMetadataBuffer snapshotMetadataBuffer = new SnapshotMetadataBuffer(dataHolder);
            try {
                if (snapshotMetadataBuffer.getCount() > 0) {
                    this.zzgn = new SnapshotMetadataEntity((SnapshotMetadata) snapshotMetadataBuffer.get(0));
                } else {
                    this.zzgn = null;
                }
                snapshotMetadataBuffer.release();
            } catch (Throwable th) {
                snapshotMetadataBuffer.release();
            }
        }

        public final SnapshotMetadata getSnapshotMetadata() {
            return this.zzgn;
        }
    }

    private static final class zzr extends zzc {
        zzr(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onConnectedToRoom(room);
        }
    }

    private static final class zzs implements DeleteSnapshotResult {
        private final Status zzgf;
        private final String zzgo;

        zzs(int i, String str) {
            this.zzgf = GamesStatusCodes.zza(i);
            this.zzgo = str;
        }

        public final String getSnapshotId() {
            return this.zzgo;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    private static final class zzt extends zzc {
        zzt(DataHolder dataHolder) {
            super(dataHolder);
        }

        public final void zza(RoomStatusUpdateListener roomStatusUpdateListener, Room room) {
            roomStatusUpdateListener.onDisconnectedFromRoom(room);
        }
    }

    private static final class zzu extends zza {
        private final ResultHolder<LoadEventsResult> zzge;

        zzu(ResultHolder<LoadEventsResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzb(DataHolder dataHolder) {
            this.zzge.setResult(new zzam(dataHolder));
        }
    }

    private class zzv extends zzej {
        private final /* synthetic */ zze zzga;

        public zzv(zze zze) {
            this.zzga = zze;
            super(zze.getContext().getMainLooper(), 1000);
        }

        protected final void zzf(String str, int i) {
            try {
                if (this.zzga.isConnected()) {
                    ((zzy) this.zzga.getService()).zza(str, i);
                } else {
                    zzh.m1660e("GamesClientImpl", new StringBuilder(String.valueOf(str).length() + 89).append("Unable to increment event ").append(str).append(" by ").append(i).append(" because the games client is no longer connected").toString());
                }
            } catch (RemoteException e) {
                zze.zza(e);
            } catch (SecurityException e2) {
                zze.zza(e2);
            }
        }
    }

    private static final class zzx extends zza {
        private final ResultHolder<LoadGamesResult> zzge;

        zzx(ResultHolder<LoadGamesResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zzg(DataHolder dataHolder) {
            this.zzge.setResult(new zzan(dataHolder));
        }
    }

    private static final class zzy extends zza {
        private final ResultHolder<GetServerAuthCodeResult> zzge;

        public zzy(ResultHolder<GetServerAuthCodeResult> resultHolder) {
            this.zzge = (ResultHolder) Preconditions.checkNotNull(resultHolder, "Holder must not be null");
        }

        public final void zza(int i, String str) {
            this.zzge.setResult(new zzz(GamesStatusCodes.zza(i), str));
        }
    }

    private static final class zzz implements GetServerAuthCodeResult {
        private final Status zzgf;
        private final String zzgp;

        zzz(Status status, String str) {
            this.zzgf = status;
            this.zzgp = str;
        }

        public final String getCode() {
            return this.zzgp;
        }

        public final Status getStatus() {
            return this.zzgf;
        }
    }

    public zze(Context context, Looper looper, ClientSettings clientSettings, GamesOptions gamesOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        super(context, looper, 1, clientSettings, connectionCallbacks, onConnectionFailedListener);
        this.zzfq = clientSettings.getRealClientPackageName();
        this.zzfv = new Binder();
        this.zzft = zzac.zza(this, clientSettings.getGravityForPopups());
        this.zzfw = (long) hashCode();
        this.zzfx = gamesOptions;
        if (!this.zzfx.zzaz) {
            if (clientSettings.getViewForPopups() != null || (context instanceof Activity)) {
                zza(clientSettings.getViewForPopups());
            }
        }
    }

    private static void zza(RemoteException remoteException) {
        zzh.m1664w("GamesClientImpl", "service died", remoteException);
    }

    private static <R> void zza(ResultHolder<R> resultHolder, SecurityException securityException) {
        if (resultHolder != null) {
            resultHolder.setFailedResult(GamesClientStatusCodes.zza(4));
        }
    }

    private static void zza(SecurityException securityException) {
        zzh.m1661e("GamesClientImpl", "Is player signed out?", securityException);
    }

    private static Room zzba(DataHolder dataHolder) {
        com.google.android.gms.games.multiplayer.realtime.zzb zzb = new com.google.android.gms.games.multiplayer.realtime.zzb(dataHolder);
        Room room = null;
        try {
            if (zzb.getCount() > 0) {
                room = (Room) ((Room) zzb.get(0)).freeze();
            }
            zzb.release();
            return room;
        } catch (Throwable th) {
            zzb.release();
        }
    }

    public void connect(ConnectionProgressReportCallbacks connectionProgressReportCallbacks) {
        this.zzfr = null;
        this.zzfs = null;
        super.connect(connectionProgressReportCallbacks);
    }

    protected /* synthetic */ IInterface createServiceInterface(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesService");
        return queryLocalInterface instanceof zzy ? (zzy) queryLocalInterface : new zzz(iBinder);
    }

    public void disconnect() {
        this.zzfu = false;
        if (isConnected()) {
            try {
                zzy zzy = (zzy) getService();
                zzy.zzbd();
                this.zzfp.flush();
                zzy.zza(this.zzfw);
            } catch (RemoteException e) {
                zzh.m1663w("GamesClientImpl", "Failed to notify client disconnect.");
            }
        }
        super.disconnect();
    }

    public Bundle getConnectionHint() {
        try {
            Bundle connectionHint = ((zzy) getService()).getConnectionHint();
            if (connectionHint == null) {
                return connectionHint;
            }
            connectionHint.setClassLoader(zze.class.getClassLoader());
            this.zzfz = connectionHint;
            return connectionHint;
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    protected Bundle getGetServiceRequestExtraArgs() {
        String locale = getContext().getResources().getConfiguration().locale.toString();
        Bundle zzf = this.zzfx.zzf();
        zzf.putString(GamesExtraArgs.GAME_PACKAGE_NAME, this.zzfq);
        zzf.putString(GamesExtraArgs.DESIRED_LOCALE, locale);
        zzf.putParcelable(GamesExtraArgs.WINDOW_TOKEN, new BinderWrapper(this.zzft.zzjd.zzjb));
        zzf.putInt("com.google.android.gms.games.key.API_VERSION", 6);
        zzf.putBundle(GamesExtraArgs.SIGNIN_OPTIONS, SignInClientImpl.createBundleFromClientSettings(getClientSettings()));
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

    public /* synthetic */ void onConnectedLocked(@NonNull IInterface iInterface) {
        zzy zzy = (zzy) iInterface;
        super.onConnectedLocked(zzy);
        if (this.zzfu) {
            this.zzft.zzbj();
            this.zzfu = false;
        }
        if (!this.zzfx.zzar && !this.zzfx.zzaz) {
            try {
                zzy.zza(new zzbo(this.zzft), this.zzfw);
            } catch (RemoteException e) {
                zza(e);
            }
        }
    }

    public void onConnectionFailed(ConnectionResult connectionResult) {
        super.onConnectionFailed(connectionResult);
        this.zzfu = false;
    }

    protected void onPostInitHandler(int i, IBinder iBinder, Bundle bundle, int i2) {
        if (i == 0 && bundle != null) {
            bundle.setClassLoader(zze.class.getClassLoader());
            this.zzfu = bundle.getBoolean("show_welcome_popup");
            this.zzfy = this.zzfu;
            this.zzfr = (PlayerEntity) bundle.getParcelable("com.google.android.gms.games.current_player");
            this.zzfs = (GameEntity) bundle.getParcelable("com.google.android.gms.games.current_game");
        }
        super.onPostInitHandler(i, iBinder, bundle, i2);
    }

    public void onUserSignOut(@NonNull SignOutCallbacks signOutCallbacks) {
        try {
            zzb(new zzg(this, signOutCallbacks));
        } catch (RemoteException e) {
            signOutCallbacks.onSignOutComplete();
        }
    }

    public boolean requiresSignIn() {
        return true;
    }

    protected Set<Scope> validateScopes(Set<Scope> set) {
        Set hashSet = new HashSet(set);
        boolean contains = set.contains(Games.SCOPE_GAMES);
        boolean contains2 = set.contains(Games.SCOPE_GAMES_LITE);
        if (set.contains(Games.zzal)) {
            Preconditions.checkState(!contains, "Cannot have both %s and %s!", new Object[]{Scopes.GAMES, FirstPartyScopes.GAMES_1P});
        } else {
            boolean z = contains || contains2;
            Preconditions.checkState(z, "Games APIs requires %s function.", new Object[]{Scopes.GAMES_LITE});
            if (contains2 && contains) {
                hashSet.remove(Games.SCOPE_GAMES_LITE);
            }
        }
        return hashSet;
    }

    public final int zza(ListenerHolder<ReliableMessageSentCallback> listenerHolder, byte[] bArr, String str, String str2) throws RemoteException {
        return ((zzy) getService()).zza(new zzbv(listenerHolder), bArr, str, str2);
    }

    public final int zza(byte[] bArr, String str) throws RemoteException {
        return ((zzy) getService()).zzb(bArr, str, null);
    }

    public final int zza(byte[] bArr, String str, String[] strArr) {
        Preconditions.checkNotNull(strArr, "Participant IDs must not be null");
        try {
            Preconditions.checkNotNull(strArr, "Participant IDs must not be null");
            return ((zzy) getService()).zzb(bArr, str, strArr);
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final Intent zza(int i, int i2, boolean z) throws RemoteException {
        return ((zzy) getService()).zza(i, i2, z);
    }

    public final Intent zza(int i, byte[] bArr, int i2, Bitmap bitmap, String str) {
        try {
            Intent zza = ((zzy) getService()).zza(i, bArr, i2, str);
            Preconditions.checkNotNull(bitmap, "Must provide a non null icon");
            zza.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
            return zza;
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zza(PlayerEntity playerEntity) throws RemoteException {
        return ((zzy) getService()).zza(playerEntity);
    }

    public final Intent zza(Room room, int i) throws RemoteException {
        return ((zzy) getService()).zza((RoomEntity) room.freeze(), i);
    }

    public final Intent zza(String str, int i, int i2) {
        try {
            return ((zzy) getService()).zzb(str, i, i2);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zza(String str, boolean z, boolean z2, int i) throws RemoteException {
        return ((zzy) getService()).zza(str, z, z2, i);
    }

    public final Intent zza(int[] iArr) {
        try {
            return ((zzy) getService()).zza(iArr);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final String zza(boolean z) throws RemoteException {
        return this.zzfr != null ? this.zzfr.getPlayerId() : ((zzy) getService()).zzbf();
    }

    public final void zza(IBinder iBinder, Bundle bundle) {
        if (isConnected()) {
            try {
                ((zzy) getService()).zza(iBinder, bundle);
            } catch (RemoteException e) {
                zza(e);
            }
        }
    }

    public final void zza(View view) {
        this.zzft.zzb(view);
    }

    public final void zza(ResultHolder<LoadGamesResult> resultHolder) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzx(resultHolder));
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadInvitationsResult> resultHolder, int i) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzae(resultHolder), i);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadRequestsResult> resultHolder, int i, int i2, int i3) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzbz(resultHolder), i, i2, i3);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadPlayersResult> resultHolder, int i, boolean z, boolean z2) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzbn(resultHolder), i, z, z2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadMatchesResult> resultHolder, int i, int[] iArr) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzct(resultHolder), i, iArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadScoresResult> resultHolder, LeaderboardScoreBuffer leaderboardScoreBuffer, int i, int i2) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzah(resultHolder), leaderboardScoreBuffer.zzcb().zzcc(), i, i2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<InitiateMatchResult> resultHolder, TurnBasedMatchConfig turnBasedMatchConfig) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzco(resultHolder), turnBasedMatchConfig.getVariant(), turnBasedMatchConfig.zzci(), turnBasedMatchConfig.getInvitedPlayerIds(), turnBasedMatchConfig.getAutoMatchCriteria());
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<CommitSnapshotResult> resultHolder, Snapshot snapshot, SnapshotMetadataChange snapshotMetadataChange) throws RemoteException {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Preconditions.checkState(!snapshotContents.isClosed(), "Snapshot already closed");
        BitmapTeleporter zzcm = snapshotMetadataChange.zzcm();
        if (zzcm != null) {
            zzcm.setTempDir(getContext().getCacheDir());
        }
        Contents zzcl = snapshotContents.zzcl();
        snapshotContents.close();
        try {
            ((zzy) getService()).zza(new zzch(resultHolder), snapshot.getMetadata().getSnapshotId(), (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, zzcl);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<UpdateAchievementResult> resultHolder, String str) throws RemoteException {
        if (resultHolder == null) {
            zzu zzu = null;
        } else {
            Object zze = new zze(resultHolder);
        }
        try {
            ((zzy) getService()).zza(zzu, str, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<UpdateAchievementResult> resultHolder, String str, int i) throws RemoteException {
        try {
            ((zzy) getService()).zza(resultHolder == null ? null : new zze(resultHolder), str, i, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadScoresResult> resultHolder, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzah(resultHolder), str, i, i2, i3, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadPlayersResult> resultHolder, String str, int i, boolean z, boolean z2) throws RemoteException {
        Object obj = -1;
        switch (str.hashCode()) {
            case 156408498:
                if (str.equals("played_with")) {
                    obj = null;
                    break;
                }
                break;
        }
        switch (obj) {
            case null:
                try {
                    ((zzy) getService()).zza(new zzbn(resultHolder), str, i, z, z2);
                    return;
                } catch (SecurityException e) {
                    zza((ResultHolder) resultHolder, e);
                    return;
                }
            default:
                String str2 = "Invalid player collection: ";
                String valueOf = String.valueOf(str);
                throw new IllegalArgumentException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
        }
    }

    public final void zza(ResultHolder<SubmitScoreResult> resultHolder, String str, long j, String str2) throws RemoteException {
        try {
            ((zzy) getService()).zza(resultHolder == null ? null : new zzcl(resultHolder), str, j, str2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LeaveMatchResult> resultHolder, String str, String str2) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzcp(resultHolder), str, str2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadPlayerScoreResult> resultHolder, String str, String str2, int i, int i2) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzbl(resultHolder), null, str2, i, i2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<OpenSnapshotResult> resultHolder, String str, String str2, SnapshotMetadataChange snapshotMetadataChange, SnapshotContents snapshotContents) throws RemoteException {
        Preconditions.checkState(!snapshotContents.isClosed(), "SnapshotContents already closed");
        BitmapTeleporter zzcm = snapshotMetadataChange.zzcm();
        if (zzcm != null) {
            zzcm.setTempDir(getContext().getCacheDir());
        }
        Contents zzcl = snapshotContents.zzcl();
        snapshotContents.close();
        try {
            ((zzy) getService()).zza(new zzcj(resultHolder), str, str2, (com.google.android.gms.games.snapshot.zze) snapshotMetadataChange, zzcl);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadPlayersResult> resultHolder, String str, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzbn(resultHolder), str, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<OpenSnapshotResult> resultHolder, String str, boolean z, int i) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzcj(resultHolder), str, z, i);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<UpdateMatchResult> resultHolder, String str, byte[] bArr, String str2, ParticipantResult[] participantResultArr) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzcs(resultHolder), str, bArr, str2, participantResultArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<UpdateMatchResult> resultHolder, String str, byte[] bArr, ParticipantResult[] participantResultArr) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzcs(resultHolder), str, bArr, participantResultArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadPlayersResult> resultHolder, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzc(new zzbn(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadEventsResult> resultHolder, boolean z, String... strArr) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zza(new zzu(resultHolder), z, strArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<LoadQuestsResult> resultHolder, int[] iArr, int i, boolean z) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zza(new zzbt(resultHolder), iArr, i, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ResultHolder<UpdateRequestsResult> resultHolder, String[] strArr) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzca(resultHolder), strArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zza(ListenerHolder<OnInvitationReceivedListener> listenerHolder) throws RemoteException {
        ((zzy) getService()).zza(new zzab(listenerHolder), this.zzfw);
    }

    public final void zza(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) throws RemoteException {
        ((zzy) getService()).zza(new zzcc(listenerHolder, listenerHolder2, listenerHolder3), this.zzfv, roomConfig.getVariant(), roomConfig.getInvitedPlayerIds(), roomConfig.getAutoMatchCriteria(), false, this.zzfw);
    }

    public final void zza(ListenerHolder<? extends RoomUpdateListener> listenerHolder, String str) {
        try {
            ((zzy) getService()).zza(new zzcc(listenerHolder), str);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zza(Snapshot snapshot) throws RemoteException {
        SnapshotContents snapshotContents = snapshot.getSnapshotContents();
        Preconditions.checkState(!snapshotContents.isClosed(), "Snapshot already closed");
        Contents zzcl = snapshotContents.zzcl();
        snapshotContents.close();
        ((zzy) getService()).zza(zzcl);
    }

    public final void zza(String str, int i) {
        this.zzfp.zza(str, i);
    }

    public final void zza(String str, ResultHolder<GetServerAuthCodeResult> resultHolder) throws RemoteException {
        Preconditions.checkNotEmpty(str, "Please provide a valid serverClientId");
        try {
            ((zzy) getService()).zza(str, new zzy(resultHolder));
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzaa() throws RemoteException {
        ((zzy) getService()).zzb(this.zzfw);
    }

    public final void zzab() {
        try {
            zzaa();
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzac() throws RemoteException {
        ((zzy) getService()).zzc(this.zzfw);
    }

    public final void zzad() {
        try {
            zzac();
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzae() {
        try {
            ((zzy) getService()).zze(this.zzfw);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzaf() {
        try {
            ((zzy) getService()).zzd(this.zzfw);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final Intent zzag() throws RemoteException {
        return ((zzy) getService()).zzag();
    }

    public final Intent zzah() {
        try {
            return zzag();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzai() throws RemoteException {
        return ((zzy) getService()).zzai();
    }

    public final Intent zzaj() {
        try {
            return zzai();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final int zzak() throws RemoteException {
        return ((zzy) getService()).zzak();
    }

    public final int zzal() {
        try {
            return zzak();
        } catch (RemoteException e) {
            zza(e);
            return 4368;
        }
    }

    public final String zzam() throws RemoteException {
        return ((zzy) getService()).zzam();
    }

    public final String zzan() {
        try {
            return zzam();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final int zzao() throws RemoteException {
        return ((zzy) getService()).zzao();
    }

    public final int zzap() {
        try {
            return zzao();
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final Intent zzaq() {
        try {
            return ((zzy) getService()).zzaq();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final int zzar() {
        try {
            return ((zzy) getService()).zzar();
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final int zzas() {
        try {
            return ((zzy) getService()).zzas();
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final int zzat() throws RemoteException {
        return ((zzy) getService()).zzat();
    }

    public final int zzau() {
        try {
            return zzat();
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final int zzav() throws RemoteException {
        return ((zzy) getService()).zzav();
    }

    public final int zzaw() {
        try {
            return zzav();
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final Intent zzax() throws RemoteException {
        return ((zzy) getService()).zzbi();
    }

    public final Intent zzay() {
        try {
            return zzax();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final boolean zzaz() throws RemoteException {
        return ((zzy) getService()).zzaz();
    }

    public final int zzb(ListenerHolder<ReliableMessageSentCallback> listenerHolder, byte[] bArr, String str, String str2) {
        try {
            return zza((ListenerHolder) listenerHolder, bArr, str, str2);
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final int zzb(byte[] bArr, String str) {
        try {
            return zza(bArr, str);
        } catch (RemoteException e) {
            zza(e);
            return -1;
        }
    }

    public final Intent zzb(int i, int i2, boolean z) {
        try {
            return zza(i, i2, z);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzb(PlayerEntity playerEntity) {
        try {
            return zza(playerEntity);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzb(Room room, int i) {
        try {
            return zza(room, i);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzb(String str, boolean z, boolean z2, int i) {
        try {
            return zza(str, z, z2, i);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final String zzb(boolean z) {
        try {
            return zza(true);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final void zzb(ResultHolder<Status> resultHolder) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zza(new zzcg(resultHolder));
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<CaptureAvailableResult> resultHolder, int i) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzh(resultHolder), i);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<UpdateAchievementResult> resultHolder, String str) throws RemoteException {
        if (resultHolder == null) {
            zzu zzu = null;
        } else {
            Object zze = new zze(resultHolder);
        }
        try {
            ((zzy) getService()).zzb(zzu, str, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<UpdateAchievementResult> resultHolder, String str, int i) throws RemoteException {
        try {
            ((zzy) getService()).zzb(resultHolder == null ? null : new zze(resultHolder), str, i, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<LoadScoresResult> resultHolder, String str, int i, int i2, int i3, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzah(resultHolder), str, i, i2, i3, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<ClaimMilestoneResult> resultHolder, String str, String str2) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zzb(new zzbr(resultHolder, str2), str, str2);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<LeaderboardMetadataResult> resultHolder, String str, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzai(resultHolder), str, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<LeaderboardMetadataResult> resultHolder, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzai(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<LoadQuestsResult> resultHolder, boolean z, String[] strArr) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zza(new zzbt(resultHolder), strArr, z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ResultHolder<UpdateRequestsResult> resultHolder, String[] strArr) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzca(resultHolder), strArr);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzb(ListenerHolder<OnInvitationReceivedListener> listenerHolder) {
        try {
            zza((ListenerHolder) listenerHolder);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzb(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        try {
            zza((ListenerHolder) listenerHolder, (ListenerHolder) listenerHolder2, (ListenerHolder) listenerHolder3, roomConfig);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzb(Snapshot snapshot) {
        try {
            zza(snapshot);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzb(String str) throws RemoteException {
        ((zzy) getService()).zzf(str);
    }

    public final void zzb(String str, int i) throws RemoteException {
        ((zzy) getService()).zzb(str, i);
    }

    public final boolean zzba() {
        try {
            return zzaz();
        } catch (RemoteException e) {
            zza(e);
            return false;
        }
    }

    public final void zzbb() throws RemoteException {
        ((zzy) getService()).zzf(this.zzfw);
    }

    public final void zzbc() {
        try {
            zzbb();
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzbd() {
        if (isConnected()) {
            try {
                ((zzy) getService()).zzbd();
            } catch (RemoteException e) {
                zza(e);
            }
        }
    }

    public final Intent zzc(int i, int i2, boolean z) throws RemoteException {
        return ((zzy) getService()).zzc(i, i2, z);
    }

    public final void zzc(ResultHolder<CaptureCapabilitiesResult> resultHolder) throws RemoteException {
        try {
            ((zzy) getService()).zzc(new zzj(resultHolder));
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzc(ResultHolder<InitiateMatchResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zzb(new zzco(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzc(ResultHolder<LoadAchievementsResult> resultHolder, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zza(new zzf(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzc(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) throws RemoteException {
        ((zzy) getService()).zzb(new zzaz(listenerHolder), this.zzfw);
    }

    public final void zzc(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) throws RemoteException {
        ((zzy) getService()).zza(new zzcc(listenerHolder, listenerHolder2, listenerHolder3), this.zzfv, roomConfig.getInvitationId(), false, this.zzfw);
    }

    public final void zzc(String str) {
        try {
            zzb(str);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzc(String str, int i) {
        try {
            zzb(str, i);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final Intent zzd(int i, int i2, boolean z) {
        try {
            return zzc(i, i2, z);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzd(String str) {
        try {
            return ((zzy) getService()).zzd(str);
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final void zzd(ResultHolder<CaptureStateResult> resultHolder) throws RemoteException {
        try {
            ((zzy) getService()).zzd(new zzn(resultHolder));
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzd(ResultHolder<InitiateMatchResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zzc(new zzco(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzd(ResultHolder<LoadEventsResult> resultHolder, boolean z) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zze(new zzu(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzd(ListenerHolder<OnTurnBasedMatchUpdateReceivedListener> listenerHolder) {
        try {
            zzc((ListenerHolder) listenerHolder);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzd(ListenerHolder<? extends RoomUpdateListener> listenerHolder, ListenerHolder<? extends RoomStatusUpdateListener> listenerHolder2, ListenerHolder<? extends RealTimeMessageReceivedListener> listenerHolder3, RoomConfig roomConfig) {
        try {
            zzc(listenerHolder, listenerHolder2, listenerHolder3, roomConfig);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzd(String str, int i) throws RemoteException {
        ((zzy) getService()).zzd(str, i);
    }

    public final void zze(ResultHolder<LeaveMatchResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zze(new zzcp(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zze(ResultHolder<LoadPlayerStatsResult> resultHolder, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzf(new zzbm(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zze(ListenerHolder<QuestUpdateListener> listenerHolder) {
        try {
            ((zzy) getService()).zzd(new zzbs(listenerHolder), this.zzfw);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zze(String str) {
        try {
            ((zzy) getService()).zza(str, this.zzft.zzjd.zzjb, this.zzft.zzjd.zzbk());
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zze(String str, int i) {
        try {
            zzd(str, i);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzf(ResultHolder<CancelMatchResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zzd(new zzcn(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzf(ResultHolder<LoadSnapshotsResult> resultHolder, boolean z) throws RemoteException {
        try {
            ((zzy) getService()).zzd(new zzck(resultHolder), z);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzf(ListenerHolder<OnRequestReceivedListener> listenerHolder) {
        try {
            ((zzy) getService()).zzc(new zzbw(listenerHolder), this.zzfw);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzg(ResultHolder<LoadMatchResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zzf(new zzcq(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzg(ListenerHolder<CaptureOverlayStateListener> listenerHolder) throws RemoteException {
        ((zzy) getService()).zze(new zzl(listenerHolder), this.zzfw);
    }

    public final void zzh(ResultHolder<AcceptQuestResult> resultHolder, String str) throws RemoteException {
        this.zzfp.flush();
        try {
            ((zzy) getService()).zzh(new zzbp(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzh(ListenerHolder<CaptureOverlayStateListener> listenerHolder) {
        try {
            zzg(listenerHolder);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    public final void zzi(ResultHolder<DeleteSnapshotResult> resultHolder, String str) throws RemoteException {
        try {
            ((zzy) getService()).zzg(new zzci(resultHolder), str);
        } catch (SecurityException e) {
            zza((ResultHolder) resultHolder, e);
        }
    }

    public final void zzj(int i) {
        this.zzft.zzjd.gravity = i;
    }

    public final void zzk(int i) throws RemoteException {
        ((zzy) getService()).zzk(i);
    }

    public final void zzl(int i) {
        try {
            zzk(i);
        } catch (RemoteException e) {
            zza(e);
        }
    }

    @Nullable
    public final Bundle zzo() {
        Bundle connectionHint = getConnectionHint();
        if (connectionHint == null) {
            connectionHint = this.zzfz;
        }
        this.zzfz = null;
        return connectionHint;
    }

    public final String zzp() throws RemoteException {
        return ((zzy) getService()).zzp();
    }

    public final String zzq() {
        try {
            return zzp();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Player zzr() throws RemoteException {
        checkConnected();
        synchronized (this) {
            if (this.zzfr == null) {
                PlayerBuffer playerBuffer = new PlayerBuffer(((zzy) getService()).zzbg());
                try {
                    if (playerBuffer.getCount() > 0) {
                        this.zzfr = (PlayerEntity) ((Player) playerBuffer.get(0)).freeze();
                    }
                    playerBuffer.release();
                } catch (Throwable th) {
                    playerBuffer.release();
                }
            }
        }
        return this.zzfr;
    }

    public final Player zzs() {
        try {
            return zzr();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Game zzt() throws RemoteException {
        checkConnected();
        synchronized (this) {
            if (this.zzfs == null) {
                GameBuffer gameBuffer = new GameBuffer(((zzy) getService()).zzbh());
                try {
                    if (gameBuffer.getCount() > 0) {
                        this.zzfs = (GameEntity) ((Game) gameBuffer.get(0)).freeze();
                    }
                    gameBuffer.release();
                } catch (Throwable th) {
                    gameBuffer.release();
                }
            }
        }
        return this.zzfs;
    }

    public final Game zzu() {
        try {
            return zzt();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzv() throws RemoteException {
        return ((zzy) getService()).zzv();
    }

    public final Intent zzw() {
        try {
            return zzv();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzx() {
        try {
            return ((zzy) getService()).zzx();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzy() {
        try {
            return ((zzy) getService()).zzy();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }

    public final Intent zzz() {
        try {
            return ((zzy) getService()).zzz();
        } catch (RemoteException e) {
            zza(e);
            return null;
        }
    }
}
