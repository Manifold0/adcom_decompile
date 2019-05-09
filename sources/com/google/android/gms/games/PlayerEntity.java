package com.google.android.gms.games;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;
import com.google.android.gms.games.internal.player.zzb;

@RetainForClient
@Class(creator = "PlayerEntityCreator")
@Reserved({1000})
public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player {
    public static final Creator<PlayerEntity> CREATOR = new zza();
    @Field(getter = "getName", id = 21)
    private final String name;
    @Field(getter = "getIconImageUrl", id = 8)
    private final String zzac;
    @Field(getter = "getHiResImageUrl", id = 9)
    private final String zzad;
    @Field(getter = "getPlayerId", id = 1)
    private String zzby;
    @Field(getter = "getRetrievedTimestamp", id = 5)
    private final long zzbz;
    @Field(getter = "isInCircles", id = 6)
    private final int zzca;
    @Field(getter = "getLastPlayedWithTimestamp", id = 7)
    private final long zzcb;
    @Field(getter = "getTitle", id = 14)
    private final String zzcc;
    @Field(getter = "getMostRecentGameInfo", id = 15)
    private final zzb zzcd;
    @Field(getter = "getLevelInfo", id = 16)
    private final PlayerLevelInfo zzce;
    @Field(getter = "isProfileVisible", id = 18)
    private final boolean zzcf;
    @Field(getter = "hasDebugAccess", id = 19)
    private final boolean zzcg;
    @Field(getter = "getGamerTag", id = 20)
    private final String zzch;
    @Field(getter = "getBannerImageLandscapeUri", id = 22)
    private final Uri zzci;
    @Field(getter = "getBannerImageLandscapeUrl", id = 23)
    private final String zzcj;
    @Field(getter = "getBannerImagePortraitUri", id = 24)
    private final Uri zzck;
    @Field(getter = "getBannerImagePortraitUrl", id = 25)
    private final String zzcl;
    @Field(getter = "getGamerFriendStatus", id = 26)
    private final int zzcm;
    @Field(getter = "getGamerFriendUpdateTimestamp", id = 27)
    private final long zzcn;
    @Field(getter = "isMuted", id = 28)
    private final boolean zzco;
    @Field(getter = "getDisplayName", id = 2)
    private String zzn;
    @Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;
    @Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;

    static final class zza extends zzap {
        zza() {
        }

        public final /* synthetic */ Object createFromParcel(Parcel parcel) {
            return zzc(parcel);
        }

        public final PlayerEntity zzc(Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(PlayerEntity.getUnparcelClientVersion()) || PlayerEntity.canUnparcelSafely(PlayerEntity.class.getCanonicalName())) {
                return super.zzc(parcel);
            }
            String readString = parcel.readString();
            String readString2 = parcel.readString();
            String readString3 = parcel.readString();
            String readString4 = parcel.readString();
            return new PlayerEntity(readString, readString2, readString3 == null ? null : Uri.parse(readString3), readString4 == null ? null : Uri.parse(readString4), parcel.readLong(), -1, -1, null, null, null, null, null, true, false, parcel.readString(), parcel.readString(), null, null, null, null, -1, -1, false);
        }
    }

    public PlayerEntity(Player player) {
        this(player, true);
    }

    private PlayerEntity(Player player, boolean z) {
        this.zzby = player.getPlayerId();
        this.zzn = player.getDisplayName();
        this.zzr = player.getIconImageUri();
        this.zzac = player.getIconImageUrl();
        this.zzs = player.getHiResImageUri();
        this.zzad = player.getHiResImageUrl();
        this.zzbz = player.getRetrievedTimestamp();
        this.zzca = player.zzi();
        this.zzcb = player.getLastPlayedWithTimestamp();
        this.zzcc = player.getTitle();
        this.zzcf = player.zzj();
        com.google.android.gms.games.internal.player.zza zzk = player.zzk();
        this.zzcd = zzk == null ? null : new zzb(zzk);
        this.zzce = player.getLevelInfo();
        this.zzcg = player.zzh();
        this.zzch = player.zzg();
        this.name = player.getName();
        this.zzci = player.getBannerImageLandscapeUri();
        this.zzcj = player.getBannerImageLandscapeUrl();
        this.zzck = player.getBannerImagePortraitUri();
        this.zzcl = player.getBannerImagePortraitUrl();
        this.zzcm = player.zzl();
        this.zzcn = player.zzm();
        this.zzco = player.isMuted();
        Asserts.checkNotNull(this.zzby);
        Asserts.checkNotNull(this.zzn);
        Asserts.checkState(this.zzbz > 0);
    }

    @Constructor
    PlayerEntity(@Param(id = 1) String str, @Param(id = 2) String str2, @Param(id = 3) Uri uri, @Param(id = 4) Uri uri2, @Param(id = 5) long j, @Param(id = 6) int i, @Param(id = 7) long j2, @Param(id = 8) String str3, @Param(id = 9) String str4, @Param(id = 14) String str5, @Param(id = 15) zzb zzb, @Param(id = 16) PlayerLevelInfo playerLevelInfo, @Param(id = 18) boolean z, @Param(id = 19) boolean z2, @Param(id = 20) String str6, @Param(id = 21) String str7, @Param(id = 22) Uri uri3, @Param(id = 23) String str8, @Param(id = 24) Uri uri4, @Param(id = 25) String str9, @Param(id = 26) int i2, @Param(id = 27) long j3, @Param(id = 28) boolean z3) {
        this.zzby = str;
        this.zzn = str2;
        this.zzr = uri;
        this.zzac = str3;
        this.zzs = uri2;
        this.zzad = str4;
        this.zzbz = j;
        this.zzca = i;
        this.zzcb = j2;
        this.zzcc = str5;
        this.zzcf = z;
        this.zzcd = zzb;
        this.zzce = playerLevelInfo;
        this.zzcg = z2;
        this.zzch = str6;
        this.name = str7;
        this.zzci = uri3;
        this.zzcj = str8;
        this.zzck = uri4;
        this.zzcl = str9;
        this.zzcm = i2;
        this.zzcn = j3;
        this.zzco = z3;
    }

    static int zza(Player player) {
        return Objects.hashCode(new Object[]{player.getPlayerId(), player.getDisplayName(), Boolean.valueOf(player.zzh()), player.getIconImageUri(), player.getHiResImageUri(), Long.valueOf(player.getRetrievedTimestamp()), player.getTitle(), player.getLevelInfo(), player.zzg(), player.getName(), player.getBannerImageLandscapeUri(), player.getBannerImagePortraitUri(), Integer.valueOf(player.zzl()), Long.valueOf(player.zzm()), Boolean.valueOf(player.isMuted())});
    }

    static boolean zza(Player player, Object obj) {
        if (!(obj instanceof Player)) {
            return false;
        }
        if (player == obj) {
            return true;
        }
        Player player2 = (Player) obj;
        return Objects.equal(player2.getPlayerId(), player.getPlayerId()) && Objects.equal(player2.getDisplayName(), player.getDisplayName()) && Objects.equal(Boolean.valueOf(player2.zzh()), Boolean.valueOf(player.zzh())) && Objects.equal(player2.getIconImageUri(), player.getIconImageUri()) && Objects.equal(player2.getHiResImageUri(), player.getHiResImageUri()) && Objects.equal(Long.valueOf(player2.getRetrievedTimestamp()), Long.valueOf(player.getRetrievedTimestamp())) && Objects.equal(player2.getTitle(), player.getTitle()) && Objects.equal(player2.getLevelInfo(), player.getLevelInfo()) && Objects.equal(player2.zzg(), player.zzg()) && Objects.equal(player2.getName(), player.getName()) && Objects.equal(player2.getBannerImageLandscapeUri(), player.getBannerImageLandscapeUri()) && Objects.equal(player2.getBannerImagePortraitUri(), player.getBannerImagePortraitUri()) && Objects.equal(Integer.valueOf(player2.zzl()), Integer.valueOf(player.zzl())) && Objects.equal(Long.valueOf(player2.zzm()), Long.valueOf(player.zzm())) && Objects.equal(Boolean.valueOf(player2.isMuted()), Boolean.valueOf(player.isMuted()));
    }

    static String zzb(Player player) {
        return Objects.toStringHelper(player).add("PlayerId", player.getPlayerId()).add("DisplayName", player.getDisplayName()).add("HasDebugAccess", Boolean.valueOf(player.zzh())).add("IconImageUri", player.getIconImageUri()).add("IconImageUrl", player.getIconImageUrl()).add("HiResImageUri", player.getHiResImageUri()).add("HiResImageUrl", player.getHiResImageUrl()).add("RetrievedTimestamp", Long.valueOf(player.getRetrievedTimestamp())).add("Title", player.getTitle()).add("LevelInfo", player.getLevelInfo()).add("GamerTag", player.zzg()).add("Name", player.getName()).add("BannerImageLandscapeUri", player.getBannerImageLandscapeUri()).add("BannerImageLandscapeUrl", player.getBannerImageLandscapeUrl()).add("BannerImagePortraitUri", player.getBannerImagePortraitUri()).add("BannerImagePortraitUrl", player.getBannerImagePortraitUrl()).add("GamerFriendStatus", Integer.valueOf(player.zzl())).add("GamerFriendUpdateTimestamp", Long.valueOf(player.zzm())).add("IsMuted", Boolean.valueOf(player.isMuted())).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final Player freeze() {
        return this;
    }

    public final Uri getBannerImageLandscapeUri() {
        return this.zzci;
    }

    public final String getBannerImageLandscapeUrl() {
        return this.zzcj;
    }

    public final Uri getBannerImagePortraitUri() {
        return this.zzck;
    }

    public final String getBannerImagePortraitUrl() {
        return this.zzcl;
    }

    public final String getDisplayName() {
        return this.zzn;
    }

    public final void getDisplayName(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzn, charArrayBuffer);
    }

    public final Uri getHiResImageUri() {
        return this.zzs;
    }

    public final String getHiResImageUrl() {
        return this.zzad;
    }

    public final Uri getIconImageUri() {
        return this.zzr;
    }

    public final String getIconImageUrl() {
        return this.zzac;
    }

    public final long getLastPlayedWithTimestamp() {
        return this.zzcb;
    }

    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }

    public final String getName() {
        return this.name;
    }

    public final String getPlayerId() {
        return this.zzby;
    }

    public final long getRetrievedTimestamp() {
        return this.zzbz;
    }

    public final String getTitle() {
        return this.zzcc;
    }

    public final void getTitle(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzcc, charArrayBuffer);
    }

    public final boolean hasHiResImage() {
        return getHiResImageUri() != null;
    }

    public final boolean hasIconImage() {
        return getIconImageUri() != null;
    }

    public final int hashCode() {
        return zza((Player) this);
    }

    public final boolean isDataValid() {
        return true;
    }

    public final boolean isMuted() {
        return this.zzco;
    }

    public final String toString() {
        return zzb(this);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        String str = null;
        if (shouldDowngrade()) {
            parcel.writeString(this.zzby);
            parcel.writeString(this.zzn);
            parcel.writeString(this.zzr == null ? null : this.zzr.toString());
            if (this.zzs != null) {
                str = this.zzs.toString();
            }
            parcel.writeString(str);
            parcel.writeLong(this.zzbz);
            return;
        }
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getPlayerId(), false);
        SafeParcelWriter.writeString(parcel, 2, getDisplayName(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, getIconImageUri(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 4, getHiResImageUri(), i, false);
        SafeParcelWriter.writeLong(parcel, 5, getRetrievedTimestamp());
        SafeParcelWriter.writeInt(parcel, 6, this.zzca);
        SafeParcelWriter.writeLong(parcel, 7, getLastPlayedWithTimestamp());
        SafeParcelWriter.writeString(parcel, 8, getIconImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 9, getHiResImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 14, getTitle(), false);
        SafeParcelWriter.writeParcelable(parcel, 15, this.zzcd, i, false);
        SafeParcelWriter.writeParcelable(parcel, 16, getLevelInfo(), i, false);
        SafeParcelWriter.writeBoolean(parcel, 18, this.zzcf);
        SafeParcelWriter.writeBoolean(parcel, 19, this.zzcg);
        SafeParcelWriter.writeString(parcel, 20, this.zzch, false);
        SafeParcelWriter.writeString(parcel, 21, this.name, false);
        SafeParcelWriter.writeParcelable(parcel, 22, getBannerImageLandscapeUri(), i, false);
        SafeParcelWriter.writeString(parcel, 23, getBannerImageLandscapeUrl(), false);
        SafeParcelWriter.writeParcelable(parcel, 24, getBannerImagePortraitUri(), i, false);
        SafeParcelWriter.writeString(parcel, 25, getBannerImagePortraitUrl(), false);
        SafeParcelWriter.writeInt(parcel, 26, this.zzcm);
        SafeParcelWriter.writeLong(parcel, 27, this.zzcn);
        SafeParcelWriter.writeBoolean(parcel, 28, this.zzco);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    public final String zzg() {
        return this.zzch;
    }

    public final boolean zzh() {
        return this.zzcg;
    }

    public final int zzi() {
        return this.zzca;
    }

    public final boolean zzj() {
        return this.zzcf;
    }

    public final com.google.android.gms.games.internal.player.zza zzk() {
        return this.zzcd;
    }

    public final int zzl() {
        return this.zzcm;
    }

    public final long zzm() {
        return this.zzcn;
    }
}
