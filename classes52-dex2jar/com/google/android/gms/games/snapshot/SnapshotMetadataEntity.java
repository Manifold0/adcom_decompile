// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.Game;
import com.google.android.gms.common.util.DataUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.games.PlayerEntity;
import android.net.Uri;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "SnapshotMetadataEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class SnapshotMetadataEntity extends zzd implements SnapshotMetadata
{
    public static final Parcelable$Creator<SnapshotMetadataEntity> CREATOR;
    @SafeParcelable$Field(getter = "getDescription", id = 8)
    private final String description;
    @SafeParcelable$Field(getter = "getDeviceName", id = 15)
    private final String deviceName;
    @SafeParcelable$Field(getter = "getTitle", id = 7)
    private final String zzcc;
    @SafeParcelable$Field(getter = "getSnapshotId", id = 3)
    private final String zzgo;
    @SafeParcelable$Field(getter = "getGame", id = 1)
    private final GameEntity zzky;
    @SafeParcelable$Field(getter = "getCoverImageUri", id = 5)
    private final Uri zzqs;
    @SafeParcelable$Field(getter = "getOwner", id = 2)
    private final PlayerEntity zzqv;
    @SafeParcelable$Field(getter = "getCoverImageUrl", id = 6)
    private final String zzqw;
    @SafeParcelable$Field(getter = "getLastModifiedTimestamp", id = 9)
    private final long zzqx;
    @SafeParcelable$Field(getter = "getPlayedTime", id = 10)
    private final long zzqy;
    @SafeParcelable$Field(getter = "getCoverImageAspectRatio", id = 11)
    private final float zzqz;
    @SafeParcelable$Field(getter = "getUniqueName", id = 12)
    private final String zzra;
    @SafeParcelable$Field(getter = "hasChangePending", id = 13)
    private final boolean zzrb;
    @SafeParcelable$Field(getter = "getProgressValue", id = 14)
    private final long zzrc;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    @SafeParcelable$Constructor
    SnapshotMetadataEntity(@SafeParcelable$Param(id = 1) final GameEntity zzky, @SafeParcelable$Param(id = 2) final PlayerEntity zzqv, @SafeParcelable$Param(id = 3) final String zzgo, @SafeParcelable$Param(id = 5) final Uri zzqs, @SafeParcelable$Param(id = 6) final String zzqw, @SafeParcelable$Param(id = 7) final String zzcc, @SafeParcelable$Param(id = 8) final String description, @SafeParcelable$Param(id = 9) final long zzqx, @SafeParcelable$Param(id = 10) final long zzqy, @SafeParcelable$Param(id = 11) final float zzqz, @SafeParcelable$Param(id = 12) final String zzra, @SafeParcelable$Param(id = 13) final boolean zzrb, @SafeParcelable$Param(id = 14) final long zzrc, @SafeParcelable$Param(id = 15) final String deviceName) {
        this.zzky = zzky;
        this.zzqv = zzqv;
        this.zzgo = zzgo;
        this.zzqs = zzqs;
        this.zzqw = zzqw;
        this.zzqz = zzqz;
        this.zzcc = zzcc;
        this.description = description;
        this.zzqx = zzqx;
        this.zzqy = zzqy;
        this.zzra = zzra;
        this.zzrb = zzrb;
        this.zzrc = zzrc;
        this.deviceName = deviceName;
    }
    
    public SnapshotMetadataEntity(final SnapshotMetadata snapshotMetadata) {
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
    
    static int zza(final SnapshotMetadata snapshotMetadata) {
        return Objects.hashCode(new Object[] { snapshotMetadata.getGame(), snapshotMetadata.getOwner(), snapshotMetadata.getSnapshotId(), snapshotMetadata.getCoverImageUri(), snapshotMetadata.getCoverImageAspectRatio(), snapshotMetadata.getTitle(), snapshotMetadata.getDescription(), snapshotMetadata.getLastModifiedTimestamp(), snapshotMetadata.getPlayedTime(), snapshotMetadata.getUniqueName(), snapshotMetadata.hasChangePending(), snapshotMetadata.getProgressValue(), snapshotMetadata.getDeviceName() });
    }
    
    static boolean zza(final SnapshotMetadata snapshotMetadata, final Object o) {
        if (o instanceof SnapshotMetadata) {
            if (snapshotMetadata == o) {
                return true;
            }
            final SnapshotMetadata snapshotMetadata2 = (SnapshotMetadata)o;
            if (Objects.equal((Object)snapshotMetadata2.getGame(), (Object)snapshotMetadata.getGame()) && Objects.equal((Object)snapshotMetadata2.getOwner(), (Object)snapshotMetadata.getOwner()) && Objects.equal((Object)snapshotMetadata2.getSnapshotId(), (Object)snapshotMetadata.getSnapshotId()) && Objects.equal((Object)snapshotMetadata2.getCoverImageUri(), (Object)snapshotMetadata.getCoverImageUri()) && Objects.equal((Object)snapshotMetadata2.getCoverImageAspectRatio(), (Object)snapshotMetadata.getCoverImageAspectRatio()) && Objects.equal((Object)snapshotMetadata2.getTitle(), (Object)snapshotMetadata.getTitle()) && Objects.equal((Object)snapshotMetadata2.getDescription(), (Object)snapshotMetadata.getDescription()) && Objects.equal((Object)snapshotMetadata2.getLastModifiedTimestamp(), (Object)snapshotMetadata.getLastModifiedTimestamp()) && Objects.equal((Object)snapshotMetadata2.getPlayedTime(), (Object)snapshotMetadata.getPlayedTime()) && Objects.equal((Object)snapshotMetadata2.getUniqueName(), (Object)snapshotMetadata.getUniqueName()) && Objects.equal((Object)snapshotMetadata2.hasChangePending(), (Object)snapshotMetadata.hasChangePending()) && Objects.equal((Object)snapshotMetadata2.getProgressValue(), (Object)snapshotMetadata.getProgressValue()) && Objects.equal((Object)snapshotMetadata2.getDeviceName(), (Object)snapshotMetadata.getDeviceName())) {
                return true;
            }
        }
        return false;
    }
    
    static String zzb(final SnapshotMetadata snapshotMetadata) {
        return Objects.toStringHelper((Object)snapshotMetadata).add("Game", (Object)snapshotMetadata.getGame()).add("Owner", (Object)snapshotMetadata.getOwner()).add("SnapshotId", (Object)snapshotMetadata.getSnapshotId()).add("CoverImageUri", (Object)snapshotMetadata.getCoverImageUri()).add("CoverImageUrl", (Object)snapshotMetadata.getCoverImageUrl()).add("CoverImageAspectRatio", (Object)snapshotMetadata.getCoverImageAspectRatio()).add("Description", (Object)snapshotMetadata.getDescription()).add("LastModifiedTimestamp", (Object)snapshotMetadata.getLastModifiedTimestamp()).add("PlayedTime", (Object)snapshotMetadata.getPlayedTime()).add("UniqueName", (Object)snapshotMetadata.getUniqueName()).add("ChangePending", (Object)snapshotMetadata.hasChangePending()).add("ProgressValue", (Object)snapshotMetadata.getProgressValue()).add("DeviceName", (Object)snapshotMetadata.getDeviceName()).toString();
    }
    
    public final boolean equals(final Object o) {
        return zza(this, o);
    }
    
    public final SnapshotMetadata freeze() {
        return this;
    }
    
    @Override
    public final float getCoverImageAspectRatio() {
        return this.zzqz;
    }
    
    @Override
    public final Uri getCoverImageUri() {
        return this.zzqs;
    }
    
    @Override
    public final String getCoverImageUrl() {
        return this.zzqw;
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
    public final String getDeviceName() {
        return this.deviceName;
    }
    
    @Override
    public final Game getGame() {
        return this.zzky;
    }
    
    @Override
    public final long getLastModifiedTimestamp() {
        return this.zzqx;
    }
    
    @Override
    public final Player getOwner() {
        return this.zzqv;
    }
    
    @Override
    public final long getPlayedTime() {
        return this.zzqy;
    }
    
    @Override
    public final long getProgressValue() {
        return this.zzrc;
    }
    
    @Override
    public final String getSnapshotId() {
        return this.zzgo;
    }
    
    @Override
    public final String getTitle() {
        return this.zzcc;
    }
    
    @Override
    public final String getUniqueName() {
        return this.zzra;
    }
    
    @Override
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
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getGame(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.getOwner(), n, false);
        SafeParcelWriter.writeString(parcel, 3, this.getSnapshotId(), false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.getCoverImageUri(), n, false);
        SafeParcelWriter.writeString(parcel, 6, this.getCoverImageUrl(), false);
        SafeParcelWriter.writeString(parcel, 7, this.zzcc, false);
        SafeParcelWriter.writeString(parcel, 8, this.getDescription(), false);
        SafeParcelWriter.writeLong(parcel, 9, this.getLastModifiedTimestamp());
        SafeParcelWriter.writeLong(parcel, 10, this.getPlayedTime());
        SafeParcelWriter.writeFloat(parcel, 11, this.getCoverImageAspectRatio());
        SafeParcelWriter.writeString(parcel, 12, this.getUniqueName(), false);
        SafeParcelWriter.writeBoolean(parcel, 13, this.hasChangePending());
        SafeParcelWriter.writeLong(parcel, 14, this.getProgressValue());
        SafeParcelWriter.writeString(parcel, 15, this.getDeviceName(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
