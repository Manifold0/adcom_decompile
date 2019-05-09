package com.google.android.gms.drive.query.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Locale;

@Class(creator = "FieldWithSortOrderCreator")
@Reserved({1000})
public final class zzf extends AbstractSafeParcelable {
    public static final Creator<zzf> CREATOR = new zzg();
    @Field(id = 1)
    private final String fieldName;
    @Field(id = 2)
    private final boolean zzlm;

    @Constructor
    public zzf(@Param(id = 1) String str, @Param(id = 2) boolean z) {
        this.fieldName = str;
        this.zzlm = z;
    }

    public final String toString() {
        Locale locale = Locale.US;
        String str = "FieldWithSortOrder[%s %s]";
        Object[] objArr = new Object[2];
        objArr[0] = this.fieldName;
        objArr[1] = this.zzlm ? "ASC" : "DESC";
        return String.format(locale, str, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.fieldName, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
