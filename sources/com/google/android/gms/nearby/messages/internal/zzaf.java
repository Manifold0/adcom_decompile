package com.google.android.gms.nearby.messages.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;
import com.google.android.gms.nearby.messages.Message;

@Class(creator = "MessageWrapperCreator")
public final class zzaf extends AbstractSafeParcelable {
    public static final Creator<zzaf> CREATOR = new zzag();
    @VersionField(id = 1000)
    private final int versionCode;
    @Field(id = 1)
    private final Message zzhk;

    @Constructor
    zzaf(@Param(id = 1000) int i, @Param(id = 1) Message message) {
        this.versionCode = i;
        this.zzhk = (Message) Preconditions.checkNotNull(message);
    }

    public static final zzaf zza(Message message) {
        return new zzaf(1, message);
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzaf)) {
            return false;
        }
        return Objects.equal(this.zzhk, ((zzaf) obj).zzhk);
    }

    public final int hashCode() {
        return Objects.hashCode(new Object[]{this.zzhk});
    }

    public final String toString() {
        String message = this.zzhk.toString();
        return new StringBuilder(String.valueOf(message).length() + 24).append("MessageWrapper{message=").append(message).append("}").toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, this.zzhk, i, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
