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

@Class(creator = "AppIdentifierCreator")
@Reserved({1000})
@Deprecated
public final class AppIdentifier extends AbstractSafeParcelable {
    public static final Creator<AppIdentifier> CREATOR = new zzc();
    @Field(getter = "getIdentifier", id = 1)
    private final String zzo;

    @Constructor
    public AppIdentifier(@Param(id = 1) String str) {
        this.zzo = Preconditions.checkNotEmpty(str, "Missing application identifier value");
    }

    public final String getIdentifier() {
        return this.zzo;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, getIdentifier(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
