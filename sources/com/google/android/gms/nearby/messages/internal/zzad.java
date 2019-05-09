package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "MessageTypeCreator")
public final class zzad extends AbstractSafeParcelable {
    public static final Creator<zzad> CREATOR = new zzae();
    @Nullable
    @Field(id = 1)
    private final String namespace;
    @Nullable
    @Field(id = 2)
    private final String type;
    @VersionField(id = 1000)
    private final int versionCode;

    @Constructor
    zzad(@Param(id = 1000) int i, @Nullable @Param(id = 1) String str, @Nullable @Param(id = 2) String str2) {
        this.versionCode = i;
        this.namespace = str;
        this.type = str2;
    }

    public zzad(@Nullable String str, @Nullable String str2) {
        this(1, str, str2);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzad) || hashCode() != obj.hashCode()) {
            return false;
        }
        zzad zzad = (zzad) obj;
        return Objects.equal(this.namespace, zzad.namespace) && Objects.equal(this.type, zzad.type);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.namespace, this.type});
    }

    public final String toString() {
        String str = this.namespace;
        String str2 = this.type;
        return new StringBuilder((String.valueOf(str).length() + 17) + String.valueOf(str2).length()).append("namespace=").append(str).append(", type=").append(str2).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.namespace, false);
        SafeParcelWriter.writeString(parcel, 2, this.type, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
