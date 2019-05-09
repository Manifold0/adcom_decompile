package com.google.android.gms.nearby.connection;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.List;

@Class(creator = "AppMetadataCreator")
@Reserved({1000})
@Deprecated
public final class AppMetadata extends AbstractSafeParcelable {
    public static final Creator<AppMetadata> CREATOR = new zzd();
    @Field(getter = "getAppIdentifiers", id = 1)
    private final List<AppIdentifier> zzp;

    @Constructor
    public AppMetadata(@Param(id = 1) List<AppIdentifier> list) {
        this.zzp = (List) Preconditions.checkNotNull(list, "Must specify application identifiers");
        Preconditions.checkNotZero(list.size(), "Application identifiers cannot be empty");
    }

    public final List<AppIdentifier> getAppIdentifiers() {
        return this.zzp;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, getAppIdentifiers(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
