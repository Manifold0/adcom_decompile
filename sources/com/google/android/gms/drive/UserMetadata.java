package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Reserved;

@Class(creator = "UserMetadataCreator")
@Reserved({1})
public class UserMetadata extends AbstractSafeParcelable implements ReflectedParcelable {
    public static final Creator<UserMetadata> CREATOR = new zzt();
    @Field(id = 2)
    private final String zzbm;
    @Nullable
    @Field(id = 3)
    private final String zzbn;
    @Nullable
    @Field(id = 4)
    private final String zzbo;
    @Field(id = 5)
    private final boolean zzbp;
    @Nullable
    @Field(id = 6)
    private final String zzbq;

    @Constructor
    public UserMetadata(@Param(id = 2) String str, @Nullable @Param(id = 3) String str2, @Nullable @Param(id = 4) String str3, @Param(id = 5) boolean z, @Nullable @Param(id = 6) String str4) {
        this.zzbm = str;
        this.zzbn = str2;
        this.zzbo = str3;
        this.zzbp = z;
        this.zzbq = str4;
    }

    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", new Object[]{this.zzbm, this.zzbn, this.zzbo, Boolean.valueOf(this.zzbp), this.zzbq});
    }

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbm, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbn, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbo, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbp);
        SafeParcelWriter.writeString(parcel, 6, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
