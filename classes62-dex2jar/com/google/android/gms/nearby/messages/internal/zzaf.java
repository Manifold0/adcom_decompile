// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.nearby.messages.Message;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "MessageWrapperCreator")
public final class zzaf extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzaf> CREATOR;
    @SafeParcelable$VersionField(id = 1000)
    private final int versionCode;
    @SafeParcelable$Field(id = 1)
    private final Message zzhk;
    
    static {
        CREATOR = (Parcelable$Creator)new zzag();
    }
    
    @SafeParcelable$Constructor
    zzaf(@SafeParcelable$Param(id = 1000) final int versionCode, @SafeParcelable$Param(id = 1) final Message message) {
        this.versionCode = versionCode;
        this.zzhk = (Message)Preconditions.checkNotNull((Object)message);
    }
    
    public static final zzaf zza(final Message message) {
        return new zzaf(1, message);
    }
    
    public final boolean equals(final Object o) {
        return this == o || (o instanceof zzaf && Objects.equal((Object)this.zzhk, (Object)((zzaf)o).zzhk));
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzhk });
    }
    
    public final String toString() {
        final String string = this.zzhk.toString();
        return new StringBuilder(String.valueOf(string).length() + 24).append("MessageWrapper{message=").append(string).append("}").toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzhk, n, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
