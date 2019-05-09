// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "NearbyDeviceCreator")
public final class zzgs extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgs> CREATOR;
    private static final String zzgu;
    public static final zzgs zzgv;
    @SafeParcelable$VersionField(id = 1000)
    private final int zzex;
    @SafeParcelable$Field(getter = "getHandle", id = 3)
    private final String zzgw;
    @Nullable
    @SafeParcelable$Field(getter = "getBluetoothAddress", id = 6)
    private final String zzgx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgt();
        zzgu = null;
        zzgv = new zzgs("", null);
    }
    
    @SafeParcelable$Constructor
    zzgs(@SafeParcelable$Param(id = 1000) final int n, @Nullable @SafeParcelable$Param(id = 3) final String s, @Nullable @SafeParcelable$Param(id = 6) final String zzgx) {
        this.zzex = (int)Preconditions.checkNotNull((Object)n);
        String zzgw = s;
        if (s == null) {
            zzgw = "";
        }
        this.zzgw = zzgw;
        this.zzgx = zzgx;
    }
    
    private zzgs(final String s, final String s2) {
        this(1, s, null);
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzgs)) {
                return false;
            }
            final zzgs zzgs = (zzgs)o;
            if (!Objects.equal((Object)this.zzgw, (Object)zzgs.zzgw) || !Objects.equal((Object)this.zzgx, (Object)zzgs.zzgx)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzgw, this.zzgx });
    }
    
    public final String toString() {
        final String zzgw = this.zzgw;
        final String zzgx = this.zzgx;
        return new StringBuilder(String.valueOf(zzgw).length() + 40 + String.valueOf(zzgx).length()).append("NearbyDevice{handle=").append(zzgw).append(", bluetoothAddress=").append(zzgx).append("}").toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 3, this.zzgw, false);
        SafeParcelWriter.writeString(parcel, 6, this.zzgx, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
