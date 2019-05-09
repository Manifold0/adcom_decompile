package com.google.android.gms.games.multiplayer.turnbased;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse {
    private final InvitationBuffer zzpg;
    private final TurnBasedMatchBuffer zzph;
    private final TurnBasedMatchBuffer zzpi;
    private final TurnBasedMatchBuffer zzpj;

    public LoadMatchesResponse(Bundle bundle) {
        DataHolder zza = zza(bundle, 0);
        if (zza != null) {
            this.zzpg = new InvitationBuffer(zza);
        } else {
            this.zzpg = null;
        }
        zza = zza(bundle, 1);
        if (zza != null) {
            this.zzph = new TurnBasedMatchBuffer(zza);
        } else {
            this.zzph = null;
        }
        zza = zza(bundle, 2);
        if (zza != null) {
            this.zzpi = new TurnBasedMatchBuffer(zza);
        } else {
            this.zzpi = null;
        }
        zza = zza(bundle, 3);
        if (zza != null) {
            this.zzpj = new TurnBasedMatchBuffer(zza);
        } else {
            this.zzpj = null;
        }
    }

    private static DataHolder zza(Bundle bundle, int i) {
        String str;
        switch (i) {
            case 0:
                str = "TURN_STATUS_INVITED";
                break;
            case 1:
                str = "TURN_STATUS_MY_TURN";
                break;
            case 2:
                str = "TURN_STATUS_THEIR_TURN";
                break;
            case 3:
                str = "TURN_STATUS_COMPLETE";
                break;
            default:
                zzh.m1660e("MatchTurnStatus", "Unknown match turn status: " + i);
                str = "TURN_STATUS_UNKNOWN";
                break;
        }
        return !bundle.containsKey(str) ? null : (DataHolder) bundle.getParcelable(str);
    }

    @Deprecated
    public final void close() {
        release();
    }

    public final TurnBasedMatchBuffer getCompletedMatches() {
        return this.zzpj;
    }

    public final InvitationBuffer getInvitations() {
        return this.zzpg;
    }

    public final TurnBasedMatchBuffer getMyTurnMatches() {
        return this.zzph;
    }

    public final TurnBasedMatchBuffer getTheirTurnMatches() {
        return this.zzpi;
    }

    public final boolean hasData() {
        return (this.zzpg == null || this.zzpg.getCount() <= 0) ? (this.zzph == null || this.zzph.getCount() <= 0) ? (this.zzpi == null || this.zzpi.getCount() <= 0) ? this.zzpj != null && this.zzpj.getCount() > 0 : true : true : true;
    }

    public final void release() {
        if (this.zzpg != null) {
            this.zzpg.release();
        }
        if (this.zzph != null) {
            this.zzph.release();
        }
        if (this.zzpi != null) {
            this.zzpi.release();
        }
        if (this.zzpj != null) {
            this.zzpj.release();
        }
    }
}
