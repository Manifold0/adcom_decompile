package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.games.internal.zzd;

@Class(creator = "SnapshotEntityCreator")
@Reserved({1000})
public final class SnapshotEntity extends zzd implements Snapshot {
    public static final Creator<SnapshotEntity> CREATOR = new zzc();
    @Field(getter = "getMetadata", id = 1)
    private final SnapshotMetadataEntity zzqn;
    @Field(getter = "getSnapshotContents", id = 3)
    private final zza zzqo;

    @Constructor
    public SnapshotEntity(@Param(id = 1) SnapshotMetadata snapshotMetadata, @Param(id = 3) zza zza) {
        this.zzqn = new SnapshotMetadataEntity(snapshotMetadata);
        this.zzqo = zza;
    }

    public final boolean equals(Object obj) {
        if (obj instanceof Snapshot) {
            if (this == obj) {
                return true;
            }
            Snapshot snapshot = (Snapshot) obj;
            if (Objects.equal(snapshot.getMetadata(), getMetadata()) && Objects.equal(snapshot.getSnapshotContents(), getSnapshotContents())) {
                return true;
            }
        }
        return false;
    }

    public final Snapshot freeze() {
        return this;
    }

    public final SnapshotMetadata getMetadata() {
        return this.zzqn;
    }

    public final SnapshotContents getSnapshotContents() {
        return this.zzqo.isClosed() ? null : this.zzqo;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{getMetadata(), getSnapshotContents()});
    }

    public final boolean isDataValid() {
        return true;
    }

    public final String toString() {
        return Objects.toStringHelper(this).add("Metadata", getMetadata()).add("HasContents", Boolean.valueOf(getSnapshotContents() != null)).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, getMetadata(), i, false);
        SafeParcelWriter.writeParcelable(parcel, 3, getSnapshotContents(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
