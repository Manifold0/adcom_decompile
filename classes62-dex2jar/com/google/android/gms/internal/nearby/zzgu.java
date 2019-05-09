// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.nearby.messages.internal.zzg;
import com.google.android.gms.nearby.messages.internal.zzl;
import android.support.annotation.Nullable;
import java.util.UUID;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "NearbyDeviceFilterCreator")
public final class zzgu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzgu> CREATOR;
    @SafeParcelable$VersionField(id = 1000)
    private final int zzex;
    @SafeParcelable$Field(id = 1)
    private final int zzgy;
    @SafeParcelable$Field(id = 2)
    private final byte[] zzgz;
    @SafeParcelable$Field(id = 3)
    private final boolean zzha;
    
    static {
        CREATOR = (Parcelable$Creator)new zzgv();
    }
    
    @SafeParcelable$Constructor
    zzgu(@SafeParcelable$Param(id = 1000) final int zzex, @SafeParcelable$Param(id = 1) final int zzgy, @SafeParcelable$Param(id = 2) final byte[] zzgz, @SafeParcelable$Param(id = 3) final boolean zzha) {
        this.zzex = zzex;
        this.zzgy = zzgy;
        this.zzgz = zzgz;
        this.zzha = zzha;
    }
    
    private zzgu(final int n, final byte[] array) {
        this(1, n, array, false);
    }
    
    public static zzgu zza(final UUID uuid, @Nullable final Short n, @Nullable final Short n2) {
        return new zzgu(3, new zzl(uuid, n, n2).getBytes());
    }
    
    public static zzgu zzb(String s, @Nullable final String s2) {
        final String value = String.valueOf(s);
        s = s2;
        if (s2 == null) {
            s = "";
        }
        s = String.valueOf(s);
        if (s.length() != 0) {
            s = value.concat(s);
        }
        else {
            s = new String(value);
        }
        return new zzgu(2, new zzg(s).getBytes());
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzgy);
        SafeParcelWriter.writeByteArray(parcel, 2, this.zzgz, false);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzha);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzex);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
