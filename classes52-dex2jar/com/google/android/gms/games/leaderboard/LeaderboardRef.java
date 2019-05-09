// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import java.util.ArrayList;
import android.net.Uri;
import android.database.CharArrayBuffer;
import com.google.android.gms.games.GameRef;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.data.DataBufferRef;

public final class LeaderboardRef extends DataBufferRef implements Leaderboard
{
    private final Game zzmy;
    private final int zzmz;
    
    LeaderboardRef(final DataHolder dataHolder, final int n, final int zzmz) {
        super(dataHolder, n);
        this.zzmz = zzmz;
        this.zzmy = new GameRef(dataHolder, n);
    }
    
    public final boolean equals(final Object o) {
        return LeaderboardEntity.zza(this, o);
    }
    
    public final String getDisplayName() {
        return this.getString("name");
    }
    
    public final void getDisplayName(final CharArrayBuffer charArrayBuffer) {
        this.copyToBuffer("name", charArrayBuffer);
    }
    
    public final Game getGame() {
        return this.zzmy;
    }
    
    public final Uri getIconImageUri() {
        return this.parseUri("board_icon_image_uri");
    }
    
    public final String getIconImageUrl() {
        return this.getString("board_icon_image_url");
    }
    
    public final String getLeaderboardId() {
        return this.getString("external_leaderboard_id");
    }
    
    public final int getScoreOrder() {
        return this.getInteger("score_order");
    }
    
    public final ArrayList<LeaderboardVariant> getVariants() {
        final ArrayList<zzc> list = (ArrayList<zzc>)new ArrayList<LeaderboardVariant>(this.zzmz);
        for (int i = 0; i < this.zzmz; ++i) {
            list.add(new zzc(this.mDataHolder, this.mDataRow + i));
        }
        return (ArrayList<LeaderboardVariant>)list;
    }
    
    public final int hashCode() {
        return LeaderboardEntity.zza(this);
    }
    
    public final String toString() {
        return LeaderboardEntity.zzb(this);
    }
}
