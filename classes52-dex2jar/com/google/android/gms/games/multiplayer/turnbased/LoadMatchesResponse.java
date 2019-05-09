// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.multiplayer.turnbased;

import com.google.android.gms.games.internal.zzh;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;
import com.google.android.gms.games.multiplayer.InvitationBuffer;

public final class LoadMatchesResponse
{
    private final InvitationBuffer zzpg;
    private final TurnBasedMatchBuffer zzph;
    private final TurnBasedMatchBuffer zzpi;
    private final TurnBasedMatchBuffer zzpj;
    
    public LoadMatchesResponse(final Bundle bundle) {
        final DataHolder zza = zza(bundle, 0);
        if (zza != null) {
            this.zzpg = new InvitationBuffer(zza);
        }
        else {
            this.zzpg = null;
        }
        final DataHolder zza2 = zza(bundle, 1);
        if (zza2 != null) {
            this.zzph = new TurnBasedMatchBuffer(zza2);
        }
        else {
            this.zzph = null;
        }
        final DataHolder zza3 = zza(bundle, 2);
        if (zza3 != null) {
            this.zzpi = new TurnBasedMatchBuffer(zza3);
        }
        else {
            this.zzpi = null;
        }
        final DataHolder zza4 = zza(bundle, 3);
        if (zza4 != null) {
            this.zzpj = new TurnBasedMatchBuffer(zza4);
            return;
        }
        this.zzpj = null;
    }
    
    private static DataHolder zza(final Bundle bundle, final int n) {
        String s = null;
        switch (n) {
            default: {
                zzh.e("MatchTurnStatus", new StringBuilder(38).append("Unknown match turn status: ").append(n).toString());
                s = "TURN_STATUS_UNKNOWN";
                break;
            }
            case 0: {
                s = "TURN_STATUS_INVITED";
                break;
            }
            case 1: {
                s = "TURN_STATUS_MY_TURN";
                break;
            }
            case 2: {
                s = "TURN_STATUS_THEIR_TURN";
                break;
            }
            case 3: {
                s = "TURN_STATUS_COMPLETE";
                break;
            }
        }
        if (!bundle.containsKey(s)) {
            return null;
        }
        return (DataHolder)bundle.getParcelable(s);
    }
    
    @Deprecated
    public final void close() {
        this.release();
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
        return (this.zzpg != null && this.zzpg.getCount() > 0) || (this.zzph != null && this.zzph.getCount() > 0) || (this.zzpi != null && this.zzpi.getCount() > 0) || (this.zzpj != null && this.zzpj.getCount() > 0);
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
