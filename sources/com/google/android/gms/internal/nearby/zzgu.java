package com.google.android.gms.internal.nearby;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.nearby.messages.internal.zzg;
import com.google.android.gms.nearby.messages.internal.zzl;
import java.util.UUID;

@Class(creator = "NearbyDeviceFilterCreator")
public final class zzgu extends AbstractSafeParcelable {
    public static final Creator<zzgu> CREATOR = new zzgv();
    @VersionField(id = 1000)
    private final int zzex;
    @Field(id = 1)
    private final int zzgy;
    @Field(id = 2)
    private final byte[] zzgz;
    @Field(id = 3)
    private final boolean zzha;

    @Constructor
    zzgu(@Param(id = 1000) int i, @Param(id = 1) int i2, @Param(id = 2) byte[] bArr, @Param(id = 3) boolean z) {
        this.zzex = i;
        this.zzgy = i2;
        this.zzgz = bArr;
        this.zzha = z;
    }

    private zzgu(int i, byte[] bArr) {
        this(1, i, bArr, false);
    }

    public static zzgu zza(UUID uuid, @Nullable Short sh, @Nullable Short sh2) {
        return new zzgu(3, new zzl(uuid, sh, sh2).getBytes());
    }

    public static zzgu zzb(String str, @Nullable String str2) {
        Object obj;
        String valueOf = String.valueOf(str);
        if (str2 == null) {
            obj = "";
        }
        String valueOf2 = String.valueOf(obj);
        return new zzgu(2, new zzg(valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf)).getBytes());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzgy);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzha);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
