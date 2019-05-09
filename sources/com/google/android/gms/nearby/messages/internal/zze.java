package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.nearby.messages.Distance;
import java.util.Locale;

@Class(creator = "DistanceImplCreator")
public final class zze extends AbstractSafeParcelable implements Distance {
    public static final Creator<zze> CREATOR = new zzf();
    @Field(id = 2)
    private final int accuracy;
    @VersionField(id = 1)
    private final int versionCode;
    @Field(id = 3)
    private final double zzhg;

    public zze(int i, double d) {
        this(1, 1, Double.NaN);
    }

    @Constructor
    zze(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) double d) {
        this.versionCode = i;
        this.accuracy = i2;
        this.zzhg = d;
    }

    public final int compareTo(@NonNull Distance distance) {
        return (Double.isNaN(getMeters()) && Double.isNaN(distance.getMeters())) ? 0 : Double.compare(getMeters(), distance.getMeters());
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zze)) {
            return false;
        }
        Distance distance = (zze) obj;
        return getAccuracy() == distance.getAccuracy() && compareTo(distance) == 0;
    }

    public final int getAccuracy() {
        return this.accuracy;
    }

    public final double getMeters() {
        return this.zzhg;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Integer.valueOf(getAccuracy()), Double.valueOf(getMeters())});
    }

    public final String toString() {
        String str;
        Locale locale = Locale.US;
        String str2 = "(%.1fm, %s)";
        Object[] objArr = new Object[2];
        objArr[0] = Double.valueOf(this.zzhg);
        switch (this.accuracy) {
            case 1:
                str = "LOW";
                break;
            default:
                str = "UNKNOWN";
                break;
        }
        objArr[1] = str;
        return String.format(locale, str2, objArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.accuracy);
        SafeParcelWriter.writeDouble(parcel, 3, this.zzhg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
