// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.games.internal.zzd;

@SafeParcelable$Class(creator = "SnapshotEntityCreator")
@SafeParcelable$Reserved({ 1000 })
public final class SnapshotEntity extends zzd implements Snapshot
{
    public static final Parcelable$Creator<SnapshotEntity> CREATOR;
    @SafeParcelable$Field(getter = "getMetadata", id = 1)
    private final SnapshotMetadataEntity zzqn;
    @SafeParcelable$Field(getter = "getSnapshotContents", id = 3)
    private final zza zzqo;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    public SnapshotEntity(@SafeParcelable$Param(id = 1) final SnapshotMetadata snapshotMetadata, @SafeParcelable$Param(id = 3) final zza zzqo) {
        this.zzqn = new SnapshotMetadataEntity(snapshotMetadata);
        this.zzqo = zzqo;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof Snapshot) {
            if (this != o) {
                final Snapshot snapshot = (Snapshot)o;
                if (!Objects.equal((Object)snapshot.getMetadata(), (Object)this.getMetadata()) || !Objects.equal((Object)snapshot.getSnapshotContents(), (Object)this.getSnapshotContents())) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public final Snapshot freeze() {
        return this;
    }
    
    @Override
    public final SnapshotMetadata getMetadata() {
        return this.zzqn;
    }
    
    @Override
    public final SnapshotContents getSnapshotContents() {
        if (this.zzqo.isClosed()) {
            return null;
        }
        return this.zzqo;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.getMetadata(), this.getSnapshotContents() });
    }
    
    public final boolean isDataValid() {
        return true;
    }
    
    public final String toString() {
        return Objects.toStringHelper((Object)this).add("Metadata", (Object)this.getMetadata()).add("HasContents", (Object)(this.getSnapshotContents() != null)).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getMetadata(), n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getSnapshotContents(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
