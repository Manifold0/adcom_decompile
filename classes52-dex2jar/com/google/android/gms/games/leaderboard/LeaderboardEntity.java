// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import java.util.Collection;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.games.GameEntity;
import android.net.Uri;
import com.google.android.gms.games.Game;
import java.util.ArrayList;

public final class LeaderboardEntity implements Leaderboard
{
    private final String zzac;
    private final String zzmv;
    private final int zzmw;
    private final ArrayList<zzb> zzmx;
    private final Game zzmy;
    private final String zzn;
    private final Uri zzr;
    
    public LeaderboardEntity(final Leaderboard leaderboard) {
        this.zzmv = leaderboard.getLeaderboardId();
        this.zzn = leaderboard.getDisplayName();
        this.zzr = leaderboard.getIconImageUri();
        this.zzac = leaderboard.getIconImageUrl();
        this.zzmw = leaderboard.getScoreOrder();
        final Game game = leaderboard.getGame();
        Game zzmy;
        if (game == null) {
            zzmy = null;
        }
        else {
            zzmy = new GameEntity(game);
        }
        this.zzmy = zzmy;
        final ArrayList<LeaderboardVariant> variants = leaderboard.getVariants();
        final int size = variants.size();
        this.zzmx = new ArrayList<zzb>(size);
        for (int i = 0; i < size; ++i) {
            this.zzmx.add((zzb)variants.get(i).freeze());
        }
    }
    
    static int zza(final Leaderboard leaderboard) {
        return Objects.hashCode(new Object[] { leaderboard.getLeaderboardId(), leaderboard.getDisplayName(), leaderboard.getIconImageUri(), leaderboard.getScoreOrder(), leaderboard.getVariants() });
    }
    
    static boolean zza(final Leaderboard leaderboard, final Object o) {
        if (o instanceof Leaderboard) {
            if (leaderboard == o) {
                return true;
            }
            final Leaderboard leaderboard2 = (Leaderboard)o;
            if (Objects.equal((Object)leaderboard2.getLeaderboardId(), (Object)leaderboard.getLeaderboardId()) && Objects.equal((Object)leaderboard2.getDisplayName(), (Object)leaderboard.getDisplayName()) && Objects.equal((Object)leaderboard2.getIconImageUri(), (Object)leaderboard.getIconImageUri()) && Objects.equal((Object)leaderboard2.getScoreOrder(), (Object)leaderboard.getScoreOrder()) && Objects.equal((Object)leaderboard2.getVariants(), (Object)leaderboard.getVariants())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final Leaderboard leaderboard) {
        return Objects.toStringHelper((Object)leaderboard).add("LeaderboardId", (Object)leaderboard.getLeaderboardId()).add("DisplayName", (Object)leaderboard.getDisplayName()).add("IconImageUri", (Object)leaderboard.getIconImageUri()).add("IconImageUrl", (Object)leaderboard.getIconImageUrl()).add("ScoreOrder", (Object)leaderboard.getScoreOrder()).add("Variants", (Object)leaderboard.getVariants()).toString();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    @Override
    public final String getDisplayName() {
        return this.zzn;
    }
    
    @Override
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
    }
    
    @Override
    public final Game getGame() {
        return this.zzmy;
    }
    
    @Override
    public final Uri getIconImageUri() {
        return this.zzr;
    }
    
    @Override
    public final String getIconImageUrl() {
        return this.zzac;
    }
    
    @Override
    public final String getLeaderboardId() {
        return this.zzmv;
    }
    
    @Override
    public final int getScoreOrder() {
        return this.zzmw;
    }
    
    @Override
    public final ArrayList<LeaderboardVariant> getVariants() {
        return new ArrayList<LeaderboardVariant>(this.zzmx);
    }
    
    @Override
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final String toString() {
        return zzb(this);
    }
}
