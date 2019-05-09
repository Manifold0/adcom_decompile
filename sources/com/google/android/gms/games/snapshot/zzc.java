package com.google.android.gms.games.snapshot;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelReader;

public final class zzc implements Creator<SnapshotEntity> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int validateObjectHeader = SafeParcelReader.validateObjectHeader(parcel);
        zza zza = null;
        SnapshotMetadata snapshotMetadata = null;
        while (parcel.dataPosition() < validateObjectHeader) {
            int readHeader = SafeParcelReader.readHeader(parcel);
            switch (SafeParcelReader.getFieldId(readHeader)) {
                case 1:
                    snapshotMetadata = (SnapshotMetadataEntity) SafeParcelReader.createParcelable(parcel, readHeader, SnapshotMetadataEntity.CREATOR);
                    break;
                case 3:
                    zza = (zza) SafeParcelReader.createParcelable(parcel, readHeader, zza.CREATOR);
                    break;
                default:
                    SafeParcelReader.skipUnknownField(parcel, readHeader);
                    break;
            }
        }
        SafeParcelReader.ensureAtEnd(parcel, validateObjectHeader);
        return new SnapshotEntity(snapshotMetadata, zza);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new SnapshotEntity[i];
    }
}
