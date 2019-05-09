package com.google.android.gms.games.leaderboard;

import android.util.SparseArray;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.internal.games.zzei;

public final class ScoreSubmissionData {
    private static final String[] zzmt = new String[]{"leaderboardId", "playerId", "timeSpan", "hasResult", "rawScore", "formattedScore", "newBest", "scoreTag"};
    private int statusCode;
    private String zzby;
    private String zzmv;
    private SparseArray<Result> zznz = new SparseArray();

    public static final class Result {
        public final String formattedScore;
        public final boolean newBest;
        public final long rawScore;
        public final String scoreTag;

        public Result(long j, String str, String str2, boolean z) {
            this.rawScore = j;
            this.formattedScore = str;
            this.scoreTag = str2;
            this.newBest = z;
        }

        public final String toString() {
            return Objects.toStringHelper(this).add("RawScore", Long.valueOf(this.rawScore)).add("FormattedScore", this.formattedScore).add("ScoreTag", this.scoreTag).add("NewBest", Boolean.valueOf(this.newBest)).toString();
        }
    }

    public ScoreSubmissionData(DataHolder dataHolder) {
        this.statusCode = dataHolder.getStatusCode();
        int count = dataHolder.getCount();
        Preconditions.checkArgument(count == 3);
        for (int i = 0; i < count; i++) {
            int windowIndex = dataHolder.getWindowIndex(i);
            if (i == 0) {
                this.zzmv = dataHolder.getString("leaderboardId", i, windowIndex);
                this.zzby = dataHolder.getString("playerId", i, windowIndex);
            }
            if (dataHolder.getBoolean("hasResult", i, windowIndex)) {
                this.zznz.put(dataHolder.getInteger("timeSpan", i, windowIndex), new Result(dataHolder.getLong("rawScore", i, windowIndex), dataHolder.getString("formattedScore", i, windowIndex), dataHolder.getString("scoreTag", i, windowIndex), dataHolder.getBoolean("newBest", i, windowIndex)));
            }
        }
    }

    public final String getLeaderboardId() {
        return this.zzmv;
    }

    public final String getPlayerId() {
        return this.zzby;
    }

    public final Result getScoreResult(int i) {
        return (Result) this.zznz.get(i);
    }

    public final String toString() {
        ToStringHelper add = Objects.toStringHelper(this).add("PlayerId", this.zzby).add("StatusCode", Integer.valueOf(this.statusCode));
        for (int i = 0; i < 3; i++) {
            Result result = (Result) this.zznz.get(i);
            add.add("TimesSpan", zzei.zzn(i));
            add.add("Result", result == null ? "null" : result.toString());
        }
        return add.toString();
    }
}
