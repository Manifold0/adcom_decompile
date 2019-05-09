package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;
import java.util.Arrays;

@Class(creator = "ParcelablePayloadCreator")
@Reserved({1000})
public final class zzfh extends AbstractSafeParcelable {
    public static final Creator<zzfh> CREATOR = new zzfk();
    @Field(getter = "getId", id = 1)
    private long id;
    @Field(getter = "getType", id = 2)
    private int type;
    @Nullable
    @Field(getter = "getDataPfd", id = 4)
    private ParcelFileDescriptor zzdv;
    @Nullable
    @Field(getter = "getJavaFilePath", id = 5)
    private String zzdw;
    @Field(defaultValue = "-1", getter = "getJavaFileSize", id = 6)
    private long zzdx;
    @Nullable
    @Field(getter = "getStatusPfd", id = 7)
    private ParcelFileDescriptor zzdy;
    @Nullable
    @Field(getter = "getBytes", id = 3)
    private byte[] zzy;

    private zzfh() {
        this.zzdx = -1;
    }

    @Constructor
    zzfh(@Param(id = 1) long j, @Param(id = 2) int i, @Nullable @Param(id = 3) byte[] bArr, @Nullable @Param(id = 4) ParcelFileDescriptor parcelFileDescriptor, @Nullable @Param(id = 5) String str, @Param(id = 6) long j2, @Nullable @Param(id = 7) ParcelFileDescriptor parcelFileDescriptor2) {
        this.zzdx = -1;
        this.id = j;
        this.type = i;
        this.zzy = bArr;
        this.zzdv = parcelFileDescriptor;
        this.zzdw = str;
        this.zzdx = j2;
        this.zzdy = parcelFileDescriptor2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzfh)) {
            return false;
        }
        zzfh zzfh = (zzfh) obj;
        return Objects.equal(Long.valueOf(this.id), Long.valueOf(zzfh.id)) && Objects.equal(Integer.valueOf(this.type), Integer.valueOf(zzfh.type)) && Arrays.equals(this.zzy, zzfh.zzy) && Objects.equal(this.zzdv, zzfh.zzdv) && Objects.equal(this.zzdw, zzfh.zzdw) && Objects.equal(Long.valueOf(this.zzdx), Long.valueOf(zzfh.zzdx)) && Objects.equal(this.zzdy, zzfh.zzdy);
    }

    @Nullable
    public final byte[] getBytes() {
        return this.zzy;
    }

    public final long getId() {
        return this.id;
    }

    public final int getType() {
        return this.type;
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{Long.valueOf(this.id), Integer.valueOf(this.type), Integer.valueOf(Arrays.hashCode(this.zzy)), this.zzdv, this.zzdw, Long.valueOf(this.zzdx), this.zzdy});
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 1, this.id);
        SafeParcelWriter.writeInt(parcel, 2, this.type);
        SafeParcelWriter.writeByteArray(parcel, 3, this.zzy, false);
        SafeParcelWriter.writeParcelable(parcel, 4, this.zzdv, i, false);
        SafeParcelWriter.writeString(parcel, 5, this.zzdw, false);
        SafeParcelWriter.writeLong(parcel, 6, this.zzdx);
        SafeParcelWriter.writeParcelable(parcel, 7, this.zzdy, i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }

    @Nullable
    public final ParcelFileDescriptor zzo() {
        return this.zzdv;
    }

    @Nullable
    public final String zzp() {
        return this.zzdw;
    }

    public final long zzq() {
        return this.zzdx;
    }
}
