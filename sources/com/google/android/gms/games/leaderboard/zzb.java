package com.google.android.gms.games.leaderboard;

import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Objects.ToStringHelper;
import com.google.android.gms.internal.games.zzei;
import com.ironsource.sdk.constants.Constants.ParametersKeys;

public final class zzb implements LeaderboardVariant {
    private final int zznn;
    private final int zzno;
    private final boolean zznp;
    private final long zznq;
    private final String zznr;
    private final long zzns;
    private final String zznt;
    private final String zznu;
    private final long zznv;
    private final String zznw;
    private final String zznx;
    private final String zzny;

    public zzb(LeaderboardVariant leaderboardVariant) {
        this.zznn = leaderboardVariant.getTimeSpan();
        this.zzno = leaderboardVariant.getCollection();
        this.zznp = leaderboardVariant.hasPlayerInfo();
        this.zznq = leaderboardVariant.getRawPlayerScore();
        this.zznr = leaderboardVariant.getDisplayPlayerScore();
        this.zzns = leaderboardVariant.getPlayerRank();
        this.zznt = leaderboardVariant.getDisplayPlayerRank();
        this.zznu = leaderboardVariant.getPlayerScoreTag();
        this.zznv = leaderboardVariant.getNumScores();
        this.zznw = leaderboardVariant.zzcd();
        this.zznx = leaderboardVariant.zzce();
        this.zzny = leaderboardVariant.zzcf();
    }

    static int zza(LeaderboardVariant leaderboardVariant) {
        return Objects.hashCode(new Object[]{Integer.valueOf(leaderboardVariant.getTimeSpan()), Integer.valueOf(leaderboardVariant.getCollection()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo()), Long.valueOf(leaderboardVariant.getRawPlayerScore()), leaderboardVariant.getDisplayPlayerScore(), Long.valueOf(leaderboardVariant.getPlayerRank()), leaderboardVariant.getDisplayPlayerRank(), Long.valueOf(leaderboardVariant.getNumScores()), leaderboardVariant.zzcd(), leaderboardVariant.zzcf(), leaderboardVariant.zzce()});
    }

    static boolean zza(LeaderboardVariant leaderboardVariant, Object obj) {
        if (!(obj instanceof LeaderboardVariant)) {
            return false;
        }
        if (leaderboardVariant == obj) {
            return true;
        }
        LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant) obj;
        return Objects.equal(Integer.valueOf(leaderboardVariant2.getTimeSpan()), Integer.valueOf(leaderboardVariant.getTimeSpan())) && Objects.equal(Integer.valueOf(leaderboardVariant2.getCollection()), Integer.valueOf(leaderboardVariant.getCollection())) && Objects.equal(Boolean.valueOf(leaderboardVariant2.hasPlayerInfo()), Boolean.valueOf(leaderboardVariant.hasPlayerInfo())) && Objects.equal(Long.valueOf(leaderboardVariant2.getRawPlayerScore()), Long.valueOf(leaderboardVariant.getRawPlayerScore())) && Objects.equal(leaderboardVariant2.getDisplayPlayerScore(), leaderboardVariant.getDisplayPlayerScore()) && Objects.equal(Long.valueOf(leaderboardVariant2.getPlayerRank()), Long.valueOf(leaderboardVariant.getPlayerRank())) && Objects.equal(leaderboardVariant2.getDisplayPlayerRank(), leaderboardVariant.getDisplayPlayerRank()) && Objects.equal(Long.valueOf(leaderboardVariant2.getNumScores()), Long.valueOf(leaderboardVariant.getNumScores())) && Objects.equal(leaderboardVariant2.zzcd(), leaderboardVariant.zzcd()) && Objects.equal(leaderboardVariant2.zzcf(), leaderboardVariant.zzcf()) && Objects.equal(leaderboardVariant2.zzce(), leaderboardVariant.zzce());
    }

    static String zzb(LeaderboardVariant leaderboardVariant) {
        Object obj;
        ToStringHelper add = Objects.toStringHelper(leaderboardVariant).add("TimeSpan", zzei.zzn(leaderboardVariant.getTimeSpan()));
        String str = "Collection";
        int collection = leaderboardVariant.getCollection();
        switch (collection) {
            case -1:
                obj = "UNKNOWN";
                break;
            case 0:
                obj = "PUBLIC";
                break;
            case 1:
                obj = "SOCIAL";
                break;
            case 2:
                obj = "SOCIAL_1P";
                break;
            default:
                throw new IllegalArgumentException("Unknown leaderboard collection: " + collection);
        }
        return add.add(str, obj).add("RawPlayerScore", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getRawPlayerScore()) : ParametersKeys.ORIENTATION_NONE).add("DisplayPlayerScore", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerScore() : ParametersKeys.ORIENTATION_NONE).add("PlayerRank", leaderboardVariant.hasPlayerInfo() ? Long.valueOf(leaderboardVariant.getPlayerRank()) : ParametersKeys.ORIENTATION_NONE).add("DisplayPlayerRank", leaderboardVariant.hasPlayerInfo() ? leaderboardVariant.getDisplayPlayerRank() : ParametersKeys.ORIENTATION_NONE).add("NumScores", Long.valueOf(leaderboardVariant.getNumScores())).add("TopPageNextToken", leaderboardVariant.zzcd()).add("WindowPageNextToken", leaderboardVariant.zzcf()).add("WindowPagePrevToken", leaderboardVariant.zzce()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final /* bridge */ /* synthetic */ Object freeze() {
        if (this != null) {
            return this;
        }
        throw null;
    }

    public final int getCollection() {
        return this.zzno;
    }

    public final String getDisplayPlayerRank() {
        return this.zznt;
    }

    public final String getDisplayPlayerScore() {
        return this.zznr;
    }

    public final long getNumScores() {
        return this.zznv;
    }

    public final long getPlayerRank() {
        return this.zzns;
    }

    public final String getPlayerScoreTag() {
        return this.zznu;
    }

    public final long getRawPlayerScore() {
        return this.zznq;
    }

    public final int getTimeSpan() {
        return this.zznn;
    }

    public final boolean hasPlayerInfo() {
        return this.zznp;
    }

    public final int hashCode() {
        return zza(this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return zzb(this);
    }

    public final String zzcd() {
        return this.zznw;
    }

    public final String zzce() {
        return this.zznx;
    }

    public final String zzcf() {
        return this.zzny;
    }
}
