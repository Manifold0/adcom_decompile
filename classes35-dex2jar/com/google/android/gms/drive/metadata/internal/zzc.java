// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CustomPropertyCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzc extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzc> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 3)
    final String value;
    @SafeParcelable$Field(id = 2)
    final CustomPropertyKey zzio;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    @SafeParcelable$Constructor
    public zzc(@SafeParcelable$Param(id = 2) final CustomPropertyKey zzio, @Nullable @SafeParcelable$Param(id = 3) final String value) {
        Preconditions.checkNotNull((Object)zzio, (Object)"key");
        this.zzio = zzio;
        this.value = value;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            }
            final zzc zzc = (zzc)o;
            if (!Objects.equal((Object)this.zzio, (Object)zzc.zzio) || !Objects.equal((Object)this.value, (Object)zzc.value)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzio, this.value });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzio, n, false);
        SafeParcelWriter.writeString(parcel, 3, this.value, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
