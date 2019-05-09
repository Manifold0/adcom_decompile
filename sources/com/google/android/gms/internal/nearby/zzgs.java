package com.google.android.gms.internal.nearby;

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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "NearbyDeviceCreator")
public final class zzgs extends AbstractSafeParcelable {
    public static final Creator<zzgs> CREATOR = new zzgt();
    private static final String zzgu = null;
    public static final zzgs zzgv = new zzgs("", null);
    @VersionField(id = 1000)
    private final int zzex;
    @Field(getter = "getHandle", id = 3)
    private final String zzgw;
    @Nullable
    @Field(getter = "getBluetoothAddress", id = 6)
    private final String zzgx;

    @Constructor
    zzgs(@Param(id = 1000) int i, @Nullable @Param(id = 3) String str, @Nullable @Param(id = 6) String str2) {
        this.zzex = ((Integer) Preconditions.checkNotNull(Integer.valueOf(i))).intValue();
        if (str == null) {
            str = "";
        }
        this.zzgw = str;
        this.zzgx = str2;
    }

    private zzgs(String str, String str2) {
        this(1, str, null);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzgs)) {
            return false;
        }
        zzgs zzgs = (zzgs) obj;
        return Objects.equal(this.zzgw, zzgs.zzgw) && Objects.equal(this.zzgx, zzgs.zzgx);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzgw, this.zzgx});
    }

    public final String toString() {
        String str = this.zzgw;
        String str2 = this.zzgx;
        return new StringBuilder((String.valueOf(str).length() + 40) + String.valueOf(str2).length()).append("NearbyDevice{handle=").append(str).append(", bluetoothAddress=").append(str2).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 3, this.zzgw, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzgx, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
