package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.ironsource.sdk.constants.Constants.ParametersKeys;

@Class(creator = "CustomPropertyCreator")
@Reserved({1})
public final class zzc extends AbstractSafeParcelable {
    public static final Creator<zzc> CREATOR = new zzd();
    @Nullable
    @Field(id = 3)
    final String value;
    @Field(id = 2)
    final CustomPropertyKey zzio;

    @Constructor
    public zzc(@Param(id = 2) CustomPropertyKey customPropertyKey, @Nullable @Param(id = 3) String str) {
        Preconditions.checkNotNull(customPropertyKey, ParametersKeys.KEY);
        this.zzio = customPropertyKey;
        this.value = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || obj.getClass() != getClass()) {
            return false;
        }
        zzc zzc = (zzc) obj;
        return Objects.equal(this.zzio, zzc.zzio) && Objects.equal(this.value, zzc.value);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzio, this.value});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, this.zzio, i, false);
        SafeParcelWriter.writeString(parcel, 3, this.value, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
