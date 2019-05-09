// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.leaderboard;

import java.io.Serializable;
import com.google.android.gms.common.internal.Objects$ToStringHelper;
import com.google.android.gms.internal.games.zzei;
import com.google.android.gms.common.internal.Objects;

public final class zzb implements LeaderboardVariant
{
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
    
    public zzb(final LeaderboardVariant leaderboardVariant) {
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
    
    static int zza(final LeaderboardVariant leaderboardVariant) {
        return Objects.hashCode(new Object[] { leaderboardVariant.getTimeSpan(), leaderboardVariant.getCollection(), leaderboardVariant.hasPlayerInfo(), leaderboardVariant.getRawPlayerScore(), leaderboardVariant.getDisplayPlayerScore(), leaderboardVariant.getPlayerRank(), leaderboardVariant.getDisplayPlayerRank(), leaderboardVariant.getNumScores(), leaderboardVariant.zzcd(), leaderboardVariant.zzcf(), leaderboardVariant.zzce() });
    }
    
    static boolean zza(final LeaderboardVariant leaderboardVariant, final Object o) {
        if (o instanceof LeaderboardVariant) {
            if (leaderboardVariant == o) {
                return true;
            }
            final LeaderboardVariant leaderboardVariant2 = (LeaderboardVariant)o;
            if (Objects.equal((Object)leaderboardVariant2.getTimeSpan(), (Object)leaderboardVariant.getTimeSpan()) && Objects.equal((Object)leaderboardVariant2.getCollection(), (Object)leaderboardVariant.getCollection()) && Objects.equal((Object)leaderboardVariant2.hasPlayerInfo(), (Object)leaderboardVariant.hasPlayerInfo()) && Objects.equal((Object)leaderboardVariant2.getRawPlayerScore(), (Object)leaderboardVariant.getRawPlayerScore()) && Objects.equal((Object)leaderboardVariant2.getDisplayPlayerScore(), (Object)leaderboardVariant.getDisplayPlayerScore()) && Objects.equal((Object)leaderboardVariant2.getPlayerRank(), (Object)leaderboardVariant.getPlayerRank()) && Objects.equal((Object)leaderboardVariant2.getDisplayPlayerRank(), (Object)leaderboardVariant.getDisplayPlayerRank()) && Objects.equal((Object)leaderboardVariant2.getNumScores(), (Object)leaderboardVariant.getNumScores()) && Objects.equal((Object)leaderboardVariant2.zzcd(), (Object)leaderboardVariant.zzcd()) && Objects.equal((Object)leaderboardVariant2.zzcf(), (Object)leaderboardVariant.zzcf()) && Objects.equal((Object)leaderboardVariant2.zzce(), (Object)leaderboardVariant.zzce())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final LeaderboardVariant leaderboardVariant) {
        final Objects$ToStringHelper add = Objects.toStringHelper((Object)leaderboardVariant).add("TimeSpan", (Object)zzei.zzn(leaderboardVariant.getTimeSpan()));
        final int collection = leaderboardVariant.getCollection();
        String s = null;
        switch (collection) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(43).append("Unknown leaderboard collection: ").append(collection).toString());
            }
            case -1: {
                s = "UNKNOWN";
                break;
            }
            case 0: {
                s = "PUBLIC";
                break;
            }
            case 1: {
                s = "SOCIAL";
                break;
            }
            case 2: {
                s = "SOCIAL_1P";
                break;
            }
        }
        final Objects$ToStringHelper add2 = add.add("Collection", (Object)s);
        Serializable value;
        if (leaderboardVariant.hasPlayerInfo()) {
            value = leaderboardVariant.getRawPlayerScore();
        }
        else {
            value = "none";
        }
        final Objects$ToStringHelper add3 = add2.add("RawPlayerScore", (Object)value);
        String displayPlayerScore;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerScore = leaderboardVariant.getDisplayPlayerScore();
        }
        else {
            displayPlayerScore = "none";
        }
        final Objects$ToStringHelper add4 = add3.add("DisplayPlayerScore", (Object)displayPlayerScore);
        Serializable value2;
        if (leaderboardVariant.hasPlayerInfo()) {
            value2 = leaderboardVariant.getPlayerRank();
        }
        else {
            value2 = "none";
        }
        final Objects$ToStringHelper add5 = add4.add("PlayerRank", (Object)value2);
        String displayPlayerRank;
        if (leaderboardVariant.hasPlayerInfo()) {
            displayPlayerRank = leaderboardVariant.getDisplayPlayerRank();
        }
        else {
            displayPlayerRank = "none";
        }
        return add5.add("DisplayPlayerRank", (Object)displayPlayerRank).add("NumScores", (Object)leaderboardVariant.getNumScores()).add("TopPageNextToken", (Object)leaderboardVariant.zzcd()).add("WindowPageNextToken", (Object)leaderboardVariant.zzcf()).add("WindowPagePrevToken", (Object)leaderboardVariant.zzce()).toString();
    }
    
    @Override
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    @Override
    public final int getCollection() {
        return this.zzno;
    }
    
    @Override
    public final String getDisplayPlayerRank() {
        return this.zznt;
    }
    
    @Override
    public final String getDisplayPlayerScore() {
        return this.zznr;
    }
    
    @Override
    public final long getNumScores() {
        return this.zznv;
    }
    
    @Override
    public final long getPlayerRank() {
        return this.zzns;
    }
    
    @Override
    public final String getPlayerScoreTag() {
        return this.zznu;
    }
    
    @Override
    public final long getRawPlayerScore() {
        return this.zznq;
    }
    
    @Override
    public final int getTimeSpan() {
        return this.zznn;
    }
    
    @Override
    public final boolean hasPlayerInfo() {
        return this.zznp;
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
    
    @Override
    public final String zzcd() {
        return this.zznw;
    }
    
    @Override
    public final String zzce() {
        return this.zznx;
    }
    
    @Override
    public final String zzcf() {
        return this.zzny;
    }
}
