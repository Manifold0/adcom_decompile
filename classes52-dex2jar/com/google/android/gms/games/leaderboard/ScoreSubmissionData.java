// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.internal.games.zzei;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.data.DataHolder;
import android.util.SparseArray;

public final class ScoreSubmissionData
{
    private static final String[] zzmt;
    private int statusCode;
    private String zzby;
    private String zzmv;
    private SparseArray<Result> zznz;
    
    static {
        zzmt = new String[] { "leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag" };
    }
    
    public ScoreSubmissionData(final DataHolder dataHolder) {
        this.statusCode = dataHolder.getStatusCode();
        this.zznz = (SparseArray<Result>)new SparseArray();
        final int count = dataHolder.getCount();
        Preconditions.checkArgument(count == 3);
        for (int i = 0; i < count; ++i) {
            final int windowIndex = dataHolder.getWindowIndex(i);
            if (i == 0) {
                this.zzmv = dataHolder.getString("leaderboardId", i, windowIndex);
                this.zzby = dataHolder.getString("playerId", i, windowIndex);
            }
            if (dataHolder.getBoolean("hasResult", i, windowIndex)) {
                this.zznz.put(dataHolder.getInteger("timeSpan", i, windowIndex), (Object)new Result(dataHolder.getLong("rawScore", i, windowIndex), dataHolder.getString("formattedScore", i, windowIndex), dataHolder.getString("scoreTag", i, windowIndex), dataHolder.getBoolean("newBest", i, windowIndex)));
            }
        }
    }
    
    public final String getLeaderboardId() {
        return this.zzmv;
    }
    
    public final String getPlayerId() {
        return this.zzby;
    }
    
    public final Result getScoreResult(final int n) {
        return (Result)this.zznz.get(n);
    }
    
    @Override
    public final String toString() {
        final Objects$ToStringHelper add = Objects.toStringHelper((Object)this).add("PlayerId", (Object)this.zzby).add("StatusCode", (Object)this.statusCode);
        for (int i = 0; i < 3; ++i) {
            final Result result = (Result)this.zznz.get(i);
            add.add("TimesSpan", (Object)zzei.zzn(i));
            String string;
            if (result == null) {
                string = "null";
            }
            else {
                string = result.toString();
            }
            add.add("Result", (Object)string);
        }
        return add.toString();
    }
    
    public static final class Result
    {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;
        
        public Result(final long rawScore, final String formattedScore, final String scoreTag, final boolean newBest) {
            this.rawScore = rawScore;
            this.formattedScore = formattedScore;
            this.scoreTag = scoreTag;
            this.newBest = newBest;
        }
        
        @Override
        public final String toString() {
            return Objects.toStringHelper((Object)this).add("RawScore", (Object)this.rawScore).add("FormattedScore", (Object)this.formattedScore).add("ScoreTag", (Object)this.scoreTag).add("NewBest", (Object)this.newBest).toString();
        }
    }
}
