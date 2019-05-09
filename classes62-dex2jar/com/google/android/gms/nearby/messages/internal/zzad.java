// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.messages.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "MessageTypeCreator")
public final class zzad extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzad> CREATOR;
    @Nullable
    @SafeParcelable$Field(id = 1)
    private final String namespace;
    @Nullable
    @SafeParcelable$Field(id = 2)
    private final String type;
    @SafeParcelable$VersionField(id = 1000)
    private final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zzae();
    }
    
    @SafeParcelable$Constructor
    zzad(@SafeParcelable$Param(id = 1000) final int versionCode, @Nullable @SafeParcelable$Param(id = 1) final String namespace, @Nullable @SafeParcelable$Param(id = 2) final String type) {
        this.versionCode = versionCode;
        this.namespace = namespace;
        this.type = type;
    }
    
    public zzad(@Nullable final String s, @Nullable final String s2) {
        this(1, s, s2);
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzad) || this.hashCode() != o.hashCode()) {
                return false;
            }
            final zzad zzad = (zzad)o;
            if (!Objects.equal((Object)this.namespace, (Object)zzad.namespace) || !Objects.equal((Object)this.type, (Object)zzad.type)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.namespace, this.type });
    }
    
    public final String toString() {
        final String namespace = this.namespace;
        final String type = this.type;
        return new StringBuilder(String.valueOf(namespace).length() + 17 + String.valueOf(type).length()).append("namespace=").append(namespace).append(", type=").append(type).toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.namespace, false);
        SafeParcelWriter.writeString(parcel, 2, this.type, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.versionCode);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
