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
import com.google.android.gms.games.internal.player.zza;
import com.google.android.gms.common.internal.Asserts;
import android.net.Uri;
import com.google.android.gms.games.internal.player.zzb;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.util.RetainForClient;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.GamesDowngradeableSafeParcel;

@SafeParcelable$Class(creator = "PlayerEntityCreator")
@SafeParcelable$Reserved({ 1000 })
@RetainForClient
public final class PlayerEntity extends GamesDowngradeableSafeParcel implements Player
{
    public static final Parcelable$Creator<PlayerEntity> CREATOR;
    @SafeParcelable$Field(getter = "getName", id = 21)
    private final String name;
    @SafeParcelable$Field(getter = "getIconImageUrl", id = 8)
    private final String zzac;
    @SafeParcelable$Field(getter = "getHiResImageUrl", id = 9)
    private final String zzad;
    @SafeParcelable$Field(getter = "getPlayerId", id = 1)
    private String zzby;
    @SafeParcelable$Field(getter = "getRetrievedTimestamp", id = 5)
    private final long zzbz;
    @SafeParcelable$Field(getter = "isInCircles", id = 6)
    private final int zzca;
    @SafeParcelable$Field(getter = "getLastPlayedWithTimestamp", id = 7)
    private final long zzcb;
    @SafeParcelable$Field(getter = "getTitle", id = 14)
    private final String zzcc;
    @SafeParcelable$Field(getter = "getMostRecentGameInfo", id = 15)
    private final zzb zzcd;
    @SafeParcelable$Field(getter = "getLevelInfo", id = 16)
    private final PlayerLevelInfo zzce;
    @SafeParcelable$Field(getter = "isProfileVisible", id = 18)
    private final boolean zzcf;
    @SafeParcelable$Field(getter = "hasDebugAccess", id = 19)
    private final boolean zzcg;
    @SafeParcelable$Field(getter = "getGamerTag", id = 20)
    private final String zzch;
    @SafeParcelable$Field(getter = "getBannerImageLandscapeUri", id = 22)
    private final Uri zzci;
    @SafeParcelable$Field(getter = "getBannerImageLandscapeUrl", id = 23)
    private final String zzcj;
    @SafeParcelable$Field(getter = "getBannerImagePortraitUri", id = 24)
    private final Uri zzck;
    @SafeParcelable$Field(getter = "getBannerImagePortraitUrl", id = 25)
    private final String zzcl;
    @SafeParcelable$Field(getter = "getGamerFriendStatus", id = 26)
    private final int zzcm;
    @SafeParcelable$Field(getter = "getGamerFriendUpdateTimestamp", id = 27)
    private final long zzcn;
    @SafeParcelable$Field(getter = "isMuted", id = 28)
    private final boolean zzco;
    @SafeParcelable$Field(getter = "getDisplayName", id = 2)
    private String zzn;
    @SafeParcelable$Field(getter = "getIconImageUri", id = 3)
    private final Uri zzr;
    @SafeParcelable$Field(getter = "getHiResImageUri", id = 4)
    private final Uri zzs;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    public PlayerEntity(final Player player) {
        this(player, true);
    }
    
    private PlayerEntity(final Player player, final boolean b) {
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
        final com.google.android.gms.games.internal.player.zza zzk = player.zzk();
        zzb zzcd;
        if (zzk == null) {
            zzcd = null;
        }
        else {
            zzcd = new zzb(zzk);
        }
        this.zzcd = zzcd;
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
        Asserts.checkNotNull((Object)this.zzby);
        Asserts.checkNotNull((Object)this.zzn);
        Asserts.checkState(this.zzbz > 0L);
    }
    
    @SafeParcelable$Constructor
    PlayerEntity(@SafeParcelable$Param(id = 1) final String zzby, @SafeParcelable$Param(id = 2) final String zzn, @SafeParcelable$Param(id = 3) final Uri zzr, @SafeParcelable$Param(id = 4) final Uri zzs, @SafeParcelable$Param(id = 5) final long zzbz, @SafeParcelable$Param(id = 6) final int zzca, @SafeParcelable$Param(id = 7) final long zzcb, @SafeParcelable$Param(id = 8) final String zzac, @SafeParcelable$Param(id = 9) final String zzad, @SafeParcelable$Param(id = 14) final String zzcc, @SafeParcelable$Param(id = 15) final zzb zzcd, @SafeParcelable$Param(id = 16) final PlayerLevelInfo zzce, @SafeParcelable$Param(id = 18) final boolean zzcf, @SafeParcelable$Param(id = 19) final boolean zzcg, @SafeParcelable$Param(id = 20) final String zzch, @SafeParcelable$Param(id = 21) final String name, @SafeParcelable$Param(id = 22) final Uri zzci, @SafeParcelable$Param(id = 23) final String zzcj, @SafeParcelable$Param(id = 24) final Uri zzck, @SafeParcelable$Param(id = 25) final String zzcl, @SafeParcelable$Param(id = 26) final int zzcm, @SafeParcelable$Param(id = 27) final long zzcn, @SafeParcelable$Param(id = 28) final boolean zzco) {
        this.zzby = zzby;
        this.zzn = zzn;
        this.zzr = zzr;
        this.zzac = zzac;
        this.zzs = zzs;
        this.zzad = zzad;
        this.zzbz = zzbz;
        this.zzca = zzca;
        this.zzcb = zzcb;
        this.zzcc = zzcc;
        this.zzcf = zzcf;
        this.zzcd = zzcd;
        this.zzce = zzce;
        this.zzcg = zzcg;
        this.zzch = zzch;
        this.name = name;
        this.zzci = zzci;
        this.zzcj = zzcj;
        this.zzck = zzck;
        this.zzcl = zzcl;
        this.zzcm = zzcm;
        this.zzcn = zzcn;
        this.zzco = zzco;
    }
    
    static int zza(final Player player) {
        return Objects.hashCode(new Object[] { player.getPlayerId(), player.getDisplayName(), player.zzh(), player.getIconImageUri(), player.getHiResImageUri(), player.getRetrievedTimestamp(), player.getTitle(), player.getLevelInfo(), player.zzg(), player.getName(), player.getBannerImageLandscapeUri(), player.getBannerImagePortraitUri(), player.zzl(), player.zzm(), player.isMuted() });
    }
    
    static boolean zza(final Player player, final Object o) {
        if (o instanceof Player) {
            if (player == o) {
                return true;
            }
            final Player player2 = (Player)o;
            if (Objects.equal((Object)player2.getPlayerId(), (Object)player.getPlayerId()) && Objects.equal((Object)player2.getDisplayName(), (Object)player.getDisplayName()) && Objects.equal((Object)player2.zzh(), (Object)player.zzh()) && Objects.equal((Object)player2.getIconImageUri(), (Object)player.getIconImageUri()) && Objects.equal((Object)player2.getHiResImageUri(), (Object)player.getHiResImageUri()) && Objects.equal((Object)player2.getRetrievedTimestamp(), (Object)player.getRetrievedTimestamp()) && Objects.equal((Object)player2.getTitle(), (Object)player.getTitle()) && Objects.equal((Object)player2.getLevelInfo(), (Object)player.getLevelInfo()) && Objects.equal((Object)player2.zzg(), (Object)player.zzg()) && Objects.equal((Object)player2.getName(), (Object)player.getName()) && Objects.equal((Object)player2.getBannerImageLandscapeUri(), (Object)player.getBannerImageLandscapeUri()) && Objects.equal((Object)player2.getBannerImagePortraitUri(), (Object)player.getBannerImagePortraitUri()) && Objects.equal((Object)player2.zzl(), (Object)player.zzl()) && Objects.equal((Object)player2.zzm(), (Object)player.zzm()) && Objects.equal((Object)player2.isMuted(), (Object)player.isMuted())) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ boolean zza(final String s) {
        return canUnparcelSafely(s);
    }
    
    static String zzb(final Player player) {
        return Objects.toStringHelper((Object)player).add("PlayerId", (Object)player.getPlayerId()).add("DisplayName", (Object)player.getDisplayName()).add("HasDebugAccess", (Object)player.zzh()).add("IconImageUri", (Object)player.getIconImageUri()).add("IconImageUrl", (Object)player.getIconImageUrl()).add("HiResImageUri", (Object)player.getHiResImageUri()).add("HiResImageUrl", (Object)player.getHiResImageUrl()).add("RetrievedTimestamp", (Object)player.getRetrievedTimestamp()).add("Title", (Object)player.getTitle()).add("LevelInfo", (Object)player.getLevelInfo()).add("GamerTag", (Object)player.zzg()).add("Name", (Object)player.getName()).add("BannerImageLandscapeUri", (Object)player.getBannerImageLandscapeUri()).add("BannerImageLandscapeUrl", (Object)player.getBannerImageLandscapeUrl()).add("BannerImagePortraitUri", (Object)player.getBannerImagePortraitUri()).add("BannerImagePortraitUrl", (Object)player.getBannerImagePortraitUrl()).add("GamerFriendStatus", (Object)player.zzl()).add("GamerFriendUpdateTimestamp", (Object)player.zzm()).add("IsMuted", (Object)player.isMuted()).toString();
    }
    
    static /* synthetic */ Integer zze() {
        return getUnparcelClientVersion();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final Player freeze() {
        return this;
    }
    
    @Override
    public final Uri getBannerImageLandscapeUri() {
        return this.zzci;
    }
    
    @Override
    public final String getBannerImageLandscapeUrl() {
        return this.zzcj;
    }
    
    @Override
    public final Uri getBannerImagePortraitUri() {
        return this.zzck;
    }
    
    @Override
    public final String getBannerImagePortraitUrl() {
        return this.zzcl;
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
    public final long getLastPlayedWithTimestamp() {
        return this.zzcb;
    }
    
    @Override
    public final PlayerLevelInfo getLevelInfo() {
        return this.zzce;
    }
    
    @Override
    public final String getName() {
        return this.name;
    }
    
    @Override
    public final String getPlayerId() {
        return this.zzby;
    }
    
    @Override
    public final long getRetrievedTimestamp() {
        return this.zzbz;
    }
    
    @Override
    public final String getTitle() {
        return this.zzcc;
    }
    
    @Override
    public final void getTitle(final CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.zzcc, charArrayBuffer);
    }
    
    @Override
    public final boolean hasHiResImage() {
        return this.getHiResImageUri() != null;
    }
    
    @Override
    public final boolean hasIconImage() {
        return this.getIconImageUri() != null;
    }
    
    public final int hashCode() {
        return zza(this);
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    @Override
    public final boolean isMuted() {
        return this.zzco;
    }
    
    public final String toString() {
        return zzb(this);
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final String s = null;
        if (!this.shouldDowngrade()) {
            final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
            SafeParcelWriter.writeString(parcel, 1, this.getPlayerId(), false);
            SafeParcelWriter.writeString(parcel, 2, this.getDisplayName(), false);
            SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getIconImageUri(), n, false);
            SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getHiResImageUri(), n, false);
            SafeParcelWriter.writeLong(parcel, 5, this.getRetrievedTimestamp());
            SafeParcelWriter.writeInt(parcel, 6, this.zzca);
            SafeParcelWriter.writeLong(parcel, 7, this.getLastPlayedWithTimestamp());
            SafeParcelWriter.writeString(parcel, 8, this.getIconImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 9, this.getHiResImageUrl(), false);
            SafeParcelWriter.writeString(parcel, 14, this.getTitle(), false);
            SafeParcelWriter.writeParcelable(parcel, 15, (Parcelable)this.zzcd, n, false);
            SafeParcelWriter.writeParcelable(parcel, 16, (Parcelable)this.getLevelInfo(), n, false);
            SafeParcelWriter.writeBoolean(parcel, 18, this.zzcf);
            SafeParcelWriter.writeBoolean(parcel, 19, this.zzcg);
            SafeParcelWriter.writeString(parcel, 20, this.zzch, false);
            SafeParcelWriter.writeString(parcel, 21, this.name, false);
            SafeParcelWriter.writeParcelable(parcel, 22, (Parcelable)this.getBannerImageLandscapeUri(), n, false);
            SafeParcelWriter.writeString(parcel, 23, this.getBannerImageLandscapeUrl(), false);
            SafeParcelWriter.writeParcelable(parcel, 24, (Parcelable)this.getBannerImagePortraitUri(), n, false);
            SafeParcelWriter.writeString(parcel, 25, this.getBannerImagePortraitUrl(), false);
            SafeParcelWriter.writeInt(parcel, 26, this.zzcm);
            SafeParcelWriter.writeLong(parcel, 27, this.zzcn);
            SafeParcelWriter.writeBoolean(parcel, 28, this.zzco);
            SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
            return;
        }
        parcel.writeString(this.zzby);
        parcel.writeString(this.zzn);
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
            string2 = s;
        }
        else {
            string2 = this.zzs.toString();
        }
        parcel.writeString(string2);
        parcel.writeLong(this.zzbz);
    }
    
    @Override
    public final String zzg() {
        return this.zzch;
    }
    
    @Override
    public final boolean zzh() {
        return this.zzcg;
    }
    
    @Override
    public final int zzi() {
        return this.zzca;
    }
    
    @Override
    public final boolean zzj() {
        return this.zzcf;
    }
    
    @Override
    public final com.google.android.gms.games.internal.player.zza zzk() {
        return this.zzcd;
    }
    
    @Override
    public final int zzl() {
        return this.zzcm;
    }
    
    @Override
    public final long zzm() {
        return this.zzcn;
    }
    
    static final class zza extends zzap
    {
        @Override
        public final PlayerEntity zzc(final Parcel parcel) {
            if (GamesDowngradeableSafeParcel.zzb(PlayerEntity.zze()) || PlayerEntity.zza(PlayerEntity.class.getCanonicalName())) {
                return super.zzc(parcel);
            }
            final String string = parcel.readString();
            final String string2 = parcel.readString();
            final String string3 = parcel.readString();
            final String string4 = parcel.readString();
            Uri parse;
            if (string3 == null) {
                parse = null;
            }
            else {
                parse = Uri.parse(string3);
            }
            Uri parse2;
            if (string4 == null) {
                parse2 = null;
            }
            else {
                parse2 = Uri.parse(string4);
            }
            return new PlayerEntity(string, string2, parse, parse2, parcel.readLong(), -1, -1L, null, null, null, null, null, true, false, parcel.readString(), parcel.readString(), null, null, null, null, -1, -1L, false);
        }
    }
}
