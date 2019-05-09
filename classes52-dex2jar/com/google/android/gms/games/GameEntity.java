// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.net.Uri;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@SafeParcelable$Class(creator = "GameEntityCreator")
@SafeParcelable$Reserved({ 1000 })
@RetainForClient
public final class GameEntity extends GamesDowngradeableSafeParcel implements Game
{
    public static final Parcelable$Creator<GameEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 5)
    private final String description;
    @SafeParcelable$Field(getter = "isRealTimeMultiplayerEnabled", id = 16)
    private final boolean zzaa;
    @SafeParcelable$Field(getter = "isTurnBasedMultiplayerEnabled", id = 17)
    private final boolean zzab;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 18)
    private final String zzac;
    @SafeParcelable$Field(getter = "getHiResImageUrl", id = 19)
    private final String zzad;
    @SafeParcelable$Field(getter = "getFeaturedImageUrl", id = 20)
    private final String zzae;
    @SafeParcelable$Field(getter = "isMuted", id = 21)
    private final boolean zzaf;
    @SafeParcelable$Field(getter = "isIdentitySharingConfirmed", id = 22)
    private final boolean zzag;
    @SafeParcelable$Field(getter = "areSnapshotsEnabled", id = 23)
    private final boolean zzah;
    @SafeParcelable$Field(getter = "getThemeColor", id = 24)
    private final String zzai;
    @SafeParcelable$Field(getter = "hasGamepadSupport", id = 25)
    private final boolean zzaj;
    @SafeParcelable$Field(getter = "getApplicationId", id = 1)
    private final String zzm;
    @SafeParcelable$Field(getter = "getDisplayName", id = 2)
    private final String zzn;
    @SafeParcelable$Field(getter = "getPrimaryCategory", id = 3)
    private final String zzo;
    @SafeParcelable$Field(getter = "getSecondaryCategory", id = 4)
    private final String zzp;
    @SafeParcelable$Field(getter = "getDeveloperName", id = 6)
    private final String zzq;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 7)
    private final Uri zzr;
    @SafeParcelable$Field(getter = "getHiResImageUri", id = 8)
    private final Uri zzs;
    @SafeParcelable$Field(getter = "getFeaturedImageUri", id = 9)
    private final Uri zzt;
    @SafeParcelable$Field(getter = "isPlayEnabledGame", id = 10)
    private final boolean zzu;
    @SafeParcelable$Field(getter = "isInstanceInstalled", id = 11)
    private final boolean zzv;
    @SafeParcelable$Field(getter = "getInstancePackageName", id = 12)
    private final String zzw;
    @SafeParcelable$Field(getter = "getGameplayAclStatus", id = 13)
    private final int zzx;
    @SafeParcelable$Field(getter = "getAchievementTotalCount", id = 14)
    private final int zzy;
    @SafeParcelable$Field(getter = "getLeaderboardCount", id = 15)
    private final int zzz;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public GameEntity(final Game game) {
        this.zzm = game.getApplicationId();
        this.zzo = game.getPrimaryCategory();
        this.zzp = game.getSecondaryCategory();
        this.description = game.getDescription();
        this.zzq = game.getDeveloperName();
        this.zzn = game.getDisplayName();
        this.zzr = game.getIconImageUri();
        this.zzac = game.getIconImageUrl();
        this.zzs = game.getHiResImageUri();
        this.zzad = game.getHiResImageUrl();
        this.zzt = game.getFeaturedImageUri();
        this.zzae = game.getFeaturedImageUrl();
        this.zzu = game.zza();
        this.zzv = game.zzc();
        this.zzw = game.zzd();
        this.zzx = 1;
        this.zzy = game.getAchievementTotalCount();
        this.zzz = game.getLeaderboardCount();
        this.zzaa = game.isRealTimeMultiplayerEnabled();
        this.zzab = game.isTurnBasedMultiplayerEnabled();
        this.zzaf = game.isMuted();
        this.zzag = game.zzb();
        this.zzah = game.areSnapshotsEnabled();
        this.zzai = game.getThemeColor();
        this.zzaj = game.hasGamepadSupport();
    }
    
    @SafeParcelable$Constructor
    GameEntity(@SafeParcelable$Param(id = 1) final String zzm, @SafeParcelable$Param(id = 2) final String zzn, @SafeParcelable$Param(id = 3) final String zzo, @SafeParcelable$Param(id = 4) final String zzp, @SafeParcelable$Param(id = 5) final String description, @SafeParcelable$Param(id = 6) final String zzq, @SafeParcelable$Param(id = 7) final Uri zzr, @SafeParcelable$Param(id = 8) final Uri zzs, @SafeParcelable$Param(id = 9) final Uri zzt, @SafeParcelable$Param(id = 10) final boolean zzu, @SafeParcelable$Param(id = 11) final boolean zzv, @SafeParcelable$Param(id = 12) final String zzw, @SafeParcelable$Param(id = 13) final int zzx, @SafeParcelable$Param(id = 14) final int zzy, @SafeParcelable$Param(id = 15) final int zzz, @SafeParcelable$Param(id = 16) final boolean zzaa, @SafeParcelable$Param(id = 17) final boolean zzab, @SafeParcelable$Param(id = 18) final String zzac, @SafeParcelable$Param(id = 19) final String zzad, @SafeParcelable$Param(id = 20) final String zzae, @SafeParcelable$Param(id = 21) final boolean zzaf, @SafeParcelable$Param(id = 22) final boolean zzag, @SafeParcelable$Param(id = 23) final boolean zzah, @SafeParcelable$Param(id = 24) final String zzai, @SafeParcelable$Param(id = 25) final boolean zzaj) {
        this.zzm = zzm;
        this.zzn = zzn;
        this.zzo = zzo;
        this.zzp = zzp;
        this.description = description;
        this.zzq = zzq;
        this.zzr = zzr;
        this.zzac = zzac;
        this.zzs = zzs;
        this.zzad = zzad;
        this.zzt = zzt;
        this.zzae = zzae;
        this.zzu = zzu;
        this.zzv = zzv;
        this.zzw = zzw;
        this.zzx = zzx;
        this.zzy = zzy;
        this.zzz = zzz;
        this.zzaa = zzaa;
        this.zzab = zzab;
        this.zzaf = zzaf;
        this.zzag = zzag;
        this.zzah = zzah;
        this.zzai = zzai;
        this.zzaj = zzaj;
    }
    
    static int zza(final Game game) {
        return Objects.hashCode(new Object[] { game.getApplicationId(), game.getDisplayName(), game.getPrimaryCategory(), game.getSecondaryCategory(), game.getDescription(), game.getDeveloperName(), game.getIconImageUri(), game.getHiResImageUri(), game.getFeaturedImageUri(), game.zza(), game.zzc(), game.zzd(), game.getAchievementTotalCount(), game.getLeaderboardCount(), game.isRealTimeMultiplayerEnabled(), game.isTurnBasedMultiplayerEnabled(), game.isMuted(), game.zzb(), game.areSnapshotsEnabled(), game.getThemeColor(), game.hasGamepadSupport() });
    }
    
    static boolean zza(final Game game, final Object o) {
        if (o instanceof Game) {
            if (game == o) {
                return true;
            }
            final Game game2 = (Game)o;
            if (Objects.equal((Object)game2.getApplicationId(), (Object)game.getApplicationId()) && Objects.equal((Object)game2.getDisplayName(), (Object)game.getDisplayName()) && Objects.equal((Object)game2.getPrimaryCategory(), (Object)game.getPrimaryCategory()) && Objects.equal((Object)game2.getSecondaryCategory(), (Object)game.getSecondaryCategory()) && Objects.equal((Object)game2.getDescription(), (Object)game.getDescription()) && Objects.equal((Object)game2.getDeveloperName(), (Object)game.getDeveloperName()) && Objects.equal((Object)game2.getIconImageUri(), (Object)game.getIconImageUri()) && Objects.equal((Object)game2.getHiResImageUri(), (Object)game.getHiResImageUri()) && Objects.equal((Object)game2.getFeaturedImageUri(), (Object)game.getFeaturedImageUri()) && Objects.equal((Object)game2.zza(), (Object)game.zza()) && Objects.equal((Object)game2.zzc(), (Object)game.zzc()) && Objects.equal((Object)game2.zzd(), (Object)game.zzd()) && Objects.equal((Object)game2.getAchievementTotalCount(), (Object)game.getAchievementTotalCount()) && Objects.equal((Object)game2.getLeaderboardCount(), (Object)game.getLeaderboardCount()) && Objects.equal((Object)game2.isRealTimeMultiplayerEnabled(), (Object)game.isRealTimeMultiplayerEnabled()) && Objects.equal((Object)game2.isTurnBasedMultiplayerEnabled(), (Object)game.isTurnBasedMultiplayerEnabled()) && Objects.equal((Object)game2.isMuted(), (Object)game.isMuted()) && Objects.equal((Object)game2.zzb(), (Object)game.zzb()) && Objects.equal((Object)game2.areSnapshotsEnabled(), (Object)game.areSnapshotsEnabled()) && Objects.equal((Object)game2.getThemeColor(), (Object)game.getThemeColor()) && Objects.equal((Object)game2.hasGamepadSupport(), (Object)game.hasGamepadSupport())) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ boolean zza(final String s) {
        return canUnparcelSafely(s);
    }
    
    static String zzb(final Game game) {
        return Objects.toStringHelper((Object)game).add("ApplicationId", (Object)game.getApplicationId()).add("DisplayName", (Object)game.getDisplayName()).add("PrimaryCategory", (Object)game.getPrimaryCategory()).add("SecondaryCategory", (Object)game.getSecondaryCategory()).add("Description", (Object)game.getDescription()).add("DeveloperName", (Object)game.getDeveloperName()).add("IconImageUri", (Object)game.getIconImageUri()).add("IconImageUrl", (Object)game.getIconImageUrl()).add("HiResImageUri", (Object)game.getHiResImageUri()).add("HiResImageUrl", (Object)game.getHiResImageUrl()).add("FeaturedImageUri", (Object)game.getFeaturedImageUri()).add("FeaturedImageUrl", (Object)game.getFeaturedImageUrl()).add("PlayEnabledGame", (Object)game.zza()).add("InstanceInstalled", (Object)game.zzc()).add("InstancePackageName", (Object)game.zzd()).add("AchievementTotalCount", (Object)game.getAchievementTotalCount()).add("LeaderboardCount", (Object)game.getLeaderboardCount()).add("RealTimeMultiplayerEnabled", (Object)game.isRealTimeMultiplayerEnabled()).add("TurnBasedMultiplayerEnabled", (Object)game.isTurnBasedMultiplayerEnabled()).add("AreSnapshotsEnabled", (Object)game.areSnapshotsEnabled()).add("ThemeColor", (Object)game.getThemeColor()).add("HasGamepadSupport", (Object)game.hasGamepadSupport()).toString();
    }
    
    static /* synthetic */ Integer zze() {
        return getUnparcelClientVersion();
    }
    
    @Override
    public final boolean areSnapshotsEnabled() {
        return this.zzah;
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Game freeze() {
        return this;
    }
    
    @Override
    public final int getAchievementTotalCount() {
        return this.zzy;
    }
    
    @Override
    public final String getApplicationId() {
        return this.zzm;
    }
    
    @Override
    public final String getDescription() {
        return this.description;
    }
    
    @Override
    public final void getDescription(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }
    
    @Override
    public final String getDeveloperName() {
        return this.zzq;
    }
    
    @Override
    public final void getDeveloperName(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzq, charArrayBuffer);
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
    public final Uri getFeaturedImageUri() {
        return this.zzt;
    }
    
    @Override
    public final String getFeaturedImageUrl() {
        return this.zzae;
    }
    
    @Override
    public final Uri getHiResImageUri() {
        return this.zzs;
    }
    
    @Override
    public final String getHiResImageUrl() {
        return this.zzad;
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
    public final int getLeaderboardCount() {
        return this.zzz;
    }
    
    @Override
    public final String getPrimaryCategory() {
        return this.zzo;
    }
    
    @Override
    public final String getSecondaryCategory() {
        return this.zzp;
    }
    
    @Override
    public final String getThemeColor() {
        return this.zzai;
    }
    
    @Override
    public final boolean hasGamepadSupport() {
        return this.zzaj;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final boolean isMuted() {
        return this.zzaf;
    }
    
    @Override
    public final boolean isRealTimeMultiplayerEnabled() {
        return this.zzaa;
    }
    
    @Override
    public final boolean isTurnBasedMultiplayerEnabled() {
        return this.zzab;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, int n) {
        final String s = null;
        final int n2 = 1;
        if (!this.shouldDowngrade()) {
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, this.getApplicationId(), false);
            SafeParcelWriter.writeString(parcel, 2, this.getDisplayName(), false);
            SafeParcelWriter.writeString(parcel, 3, this.getPrimaryCategory(), false);
            SafeParcelWriter.writeString(parcel, 4, this.getSecondaryCategory(), false);
            SafeParcelWriter.writeString(parcel, 5, this.getDescription(), false);
            SafeParcelWriter.writeString(parcel, 6, this.getDeveloperName(), false);
            SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.getIconImageUri(), n, false);
            SafeParcelWriter.writeParcelable(parcel, 8, (Parcelable)this.getHiResImageUri(), n, false);
            SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.getFeaturedImageUri(), n, false);
            SafeParcelWriter.writeBoolean(parcel, 10, this.zzu);
            SafeParcelWriter.writeBoolean(parcel, 11, this.zzv);
            SafeParcelWriter.writeString(parcel, 12, this.zzw, false);
            SafeParcelWriter.writeInt(parcel, 13, this.zzx);
            SafeParcelWriter.writeInt(parcel, 14, this.getAchievementTotalCount());
            SafeParcelWriter.writeInt(parcel, 15, this.getLeaderboardCount());
            SafeParcelWriter.writeBoolean(parcel, 16, this.isRealTimeMultiplayerEnabled());
            SafeParcelWriter.writeBoolean(parcel, 17, this.isTurnBasedMultiplayerEnabled());
            SafeParcelWriter.writeString(parcel, 18, this.getIconImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 19, this.getHiResImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 20, this.getFeaturedImageUrl(), false);
            SafeParcelWriter.writeBoolean(parcel, 21, this.zzaf);
            SafeParcelWriter.writeBoolean(parcel, 22, this.zzag);
            SafeParcelWriter.writeBoolean(parcel, 23, this.areSnapshotsEnabled());
            SafeParcelWriter.writeString(parcel, 24, this.getThemeColor(), false);
            SafeParcelWriter.writeBoolean(parcel, 25, this.hasGamepadSupport());
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            return;
        }
        parcel.writeString(this.zzm);
        parcel.writeString(this.zzn);
        parcel.writeString(this.zzo);
        parcel.writeString(this.zzp);
        parcel.writeString(this.description);
        parcel.writeString(this.zzq);
        String string;
        if (this.zzr == null) {
            string = null;
        }
        else {
            string = this.zzr.toString();
        }
        parcel.writeString(string);
        String string2;
        if (this.zzs == null) {
            string2 = null;
        }
        else {
            string2 = this.zzs.toString();
        }
        parcel.writeString(string2);
        String string3;
        if (this.zzt == null) {
            string3 = s;
        }
        else {
            string3 = this.zzt.toString();
        }
        parcel.writeString(string3);
        if (this.zzu) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.zzv) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        parcel.writeString(this.zzw);
        parcel.writeInt(this.zzx);
        parcel.writeInt(this.zzy);
        parcel.writeInt(this.zzz);
    }
    
    @Override
    public final boolean zza() {
        return this.zzu;
    }
    
    @Override
    public final boolean zzb() {
        return this.zzag;
    }
    
    @Override
    public final boolean zzc() {
        return this.zzv;
    }
    
    @Override
    public final String zzd() {
        return this.zzw;
    }
    
    static final class zza extends zzh
    {
        @Override
        public final GameEntity zzb(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(GameEntity.zze()) || GameEntity.zza(GameEntity.class.getCanonicalName())) {
                return super.zzb(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            final String string4 = parcel.readString();
            final String string5 = parcel.readString();
            final String string6 = parcel.readString();
            final String string7 = parcel.readString();
            Uri parse;
            if (string7 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string7);
            }
            final String string8 = parcel.readString();
            Uri parse2;
            if (string8 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string8);
            }
            final String string9 = parcel.readString();
            Uri parse3;
            if (string9 == null) {
                parse3 = null;
            }
            else {
                parse3 = Uri.parse(string9);
            }
            return new GameEntity(string, string2, string3, string4, string5, string6, parse, parse2, parse3, parcel.readInt() > 0, parcel.readInt() > 0, parcel.readString(), parcel.readInt(), parcel.readInt(), parcel.readInt(), false, false, null, null, null, false, false, false, null, false);
        }
    }
}
