package com.google.android.gms.games.snapshot;

import android.database.CharArrayBuffer;
import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.common.util.DataUtils;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "SnapshotMetadataEntityCreator")
@Reserved({1000})
public final class SnapshotMetadataEntity extends zzd implements SnapshotMetadata {
    public static final Creator<SnapshotMetadataEntity> CREATOR = new zzf();
    @Field(getter = "getDescription", id = 8)
    private final String description;
    @Field(getter = "getDeviceName", id = 15)
    private final String deviceName;
    @Field(getter = "getTitle", id = 7)
    private final String zzcc;
    @Field(getter = "getSnapshotId", id = 3)
    private final String zzgo;
    @Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @Field(getter = "getCoverImageUri", id = 5)
    private final Uri zzqs;
    @Field(getter = "getOwner", id = 2)
    private final PlayerEntity zzqv;
    @Field(getter = "getCoverImageUrl", id = 6)
    private final String zzqw;
    @Field(getter = "getLastModifiedTimestamp", id = 9)
    private final long zzqx;
    @Field(getter = "getPlayedTime", id = 10)
    private final long zzqy;
    @Field(getter = "getCoverImageAspectRatio", id = 11)
    private final float zzqz;
    @Field(getter = "getUniqueName", id = 12)
    private final String zzra;
    @Field(getter = "hasChangePending", id = 13)
    private final boolean zzrb;
    @Field(getter = "getProgressValue", id = 14)
    private final long zzrc;

    @Constructor
    SnapshotMetadataEntity(@Param(id = 1) GameEntity gameEntity, @Param(id = 2) PlayerEntity playerEntity, @Param(id = 3) String str, @Param(id = 5) Uri uri, @Param(id = 6) String str2, @Param(id = 7) String str3, @Param(id = 8) String str4, @Param(id = 9) long j, @Param(id = 10) long j2, @Param(id = 11) float f, @Param(id = 12) String str5, @Param(id = 13) boolean z, @Param(id = 14) long j3, @Param(id = 15) String str6) {
        this.zzky = gameEntity;
        this.zzqv = playerEntity;
        this.zzgo = str;
        this.zzqs = uri;
        this.zzqw = str2;
        this.zzqz = f;
        this.zzcc = str3;
        this.description = str4;
        this.zzqx = j;
        this.zzqy = j2;
        this.zzra = str5;
        this.zzrb = z;
        this.zzrc = j3;
        this.deviceName = str6;
    }

    public SnapshotMetadataEntity(SnapshotMetadata snapshotMetadata) {
        this.zzky = new GameEntity(snapshotMetadata.getGame());
        this.zzqv = new PlayerEntity(snapshotMetadata.getOwner());
        this.zzgo = snapshotMetadata.getSnapshotId();
        this.zzqs = snapshotMetadata.getCoverImageUri();
        this.zzqw = snapshotMetadata.getCoverImageUrl();
        this.zzqz = snapshotMetadata.getCoverImageAspectRatio();
        this.zzcc = snapshotMetadata.getTitle();
        this.description = snapshotMetadata.getDescription();
        this.zzqx = snapshotMetadata.getLastModifiedTimestamp();
        this.zzqy = snapshotMetadata.getPlayedTime();
        this.zzra = snapshotMetadata.getUniqueName();
        this.zzrb = snapshotMetadata.hasChangePending();
        this.zzrc = snapshotMetadata.getProgressValue();
        this.deviceName = snapshotMetadata.getDeviceName();
    }

    static int zza(SnapshotMetadata snapshotMetadata) {
        return Objects.hashCode(new Object[]{snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio()), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getPlayedTime()), snapshotMetadata.getUniqueName(), Boolean.valueOf(snapshotMetadata.hasChangePending()), Long.valueOf(snapshotMetadata.getProgressValue()), snapshotMetadata.getDeviceName()});
    }

    static boolean zza(SnapshotMetadata snapshotMetadata, Object obj) {
        if (!(obj instanceof SnapshotMetadata)) {
            return false;
        }
        if (snapshotMetadata == obj) {
            return true;
        }
        SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata) obj;
        return Objects.equal(snapshotMetadata2.getGame(), snapshotMetadata.getGame()) && Objects.equal(snapshotMetadata2.getOwner(), snapshotMetadata.getOwner()) && Objects.equal(snapshotMetadata2.getSnapshotId(), snapshotMetadata.getSnapshotId()) && Objects.equal(snapshotMetadata2.getCoverImageUri(), snapshotMetadata.getCoverImageUri()) && Objects.equal(Float.valueOf(snapshotMetadata2.getCoverImageAspectRatio()), Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())) && Objects.equal(snapshotMetadata2.getTitle(), snapshotMetadata.getTitle()) && Objects.equal(snapshotMetadata2.getDescription(), snapshotMetadata.getDescription()) && Objects.equal(Long.valueOf(snapshotMetadata2.getLastModifiedTimestamp()), Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())) && Objects.equal(Long.valueOf(snapshotMetadata2.getPlayedTime()), Long.valueOf(snapshotMetadata.getPlayedTime())) && Objects.equal(snapshotMetadata2.getUniqueName(), snapshotMetadata.getUniqueName()) && Objects.equal(Boolean.valueOf(snapshotMetadata2.hasChangePending()), Boolean.valueOf(snapshotMetadata.hasChangePending())) && Objects.equal(Long.valueOf(snapshotMetadata2.getProgressValue()), Long.valueOf(snapshotMetadata.getProgressValue())) && Objects.equal(snapshotMetadata2.getDeviceName(), snapshotMetadata.getDeviceName());
    }

    static String zzb(SnapshotMetadata snapshotMetadata) {
        return Objects.toStringHelper(snapshotMetadata).add("Game", snapshotMetadata.getGame()).add("Owner", snapshotMetadata.getOwner()).add("SnapshotId", snapshotMetadata.getSnapshotId()).add("CoverImageUri", snapshotMetadata.getCoverImageUri()).add("CoverImageUrl", snapshotMetadata.getCoverImageUrl()).add("CoverImageAspectRatio", Float.valueOf(snapshotMetadata.getCoverImageAspectRatio())).add("Description", snapshotMetadata.getDescription()).add("LastModifiedTimestamp", Long.valueOf(snapshotMetadata.getLastModifiedTimestamp())).add("PlayedTime", Long.valueOf(snapshotMetadata.getPlayedTime())).add("UniqueName", snapshotMetadata.getUniqueName()).add("ChangePending", Boolean.valueOf(snapshotMetadata.hasChangePending())).add("ProgressValue", Long.valueOf(snapshotMetadata.getProgressValue())).add("DeviceName", snapshotMetadata.getDeviceName()).toString();
    }

    public final boolean equals(Object obj) {
        return zza(this, obj);
    }

    public final SnapshotMetadata freeze() {
        return this;
    }

    public final float getCoverImageAspectRatio() {
        return this.zzqz;
    }

    public final Uri getCoverImageUri() {
        return this.zzqs;
    }

    public final String getCoverImageUrl() {
        return this.zzqw;
    }

    public final String getDescription() {
        return this.description;
    }

    public final void getDescription(CharArrayBuffer charArrayBuffer) {
        DataUtils.copyStringToBuffer(this.description, charArrayBuffer);
    }

    public final String getDeviceName() {
        return this.deviceName;
    }

    public final Game getGame() {
        return this.zzky;
    }

    public final long getLastModifiedTimestamp() {
        return this.zzqx;
    }

    public final Player getOwner() {
        return this.zzqv;
    }

    public final long getPlayedTime() {
        return this.zzqy;
    }

    public final long getProgressValue() {
        return this.zzrc;
    }

    public final String getSnapshotId() {
        return this.zzgo;
    }

    public final String getTitle() {
        return this.zzcc;
    }

    public final String getUniqueName() {
        return this.zzra;
    }

    public final boolean hasChangePending() {
        return this.zzrb;
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

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getGame(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 2, getOwner(), i, false);
        SafeParcelWriter.writeString(parcel, 3, getSnapshotId(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, getCoverImageUri(), i, false);
        SafeParcelWriter.writeString(parcel, 6, getCoverImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcc, false);
        SafeParcelWriter.writeString(parcel, 8, getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 9, getLastModifiedTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, getPlayedTime());
        SafeParcelWriter.writeFloat(parcel, 11, getCoverImageAspectRatio());
        SafeParcelWriter.writeString(parcel, 12, getUniqueName(), false);
        SafeParcelWriter.writeBoolean(parcel, 13, hasChangePending());
        SafeParcelWriter.writeLong(parcel, 14, getProgressValue());
        SafeParcelWriter.writeString(parcel, 15, getDeviceName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
