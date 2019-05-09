// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import android.bluetooth.BluetoothDevice;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnEndpointFoundParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzer extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzer> CREATOR;
    @SafeParcelable$Field(getter = "getEndpointId", id = 1)
    private String zzca;
    @SafeParcelable$Field(getter = "getEndpointName", id = 3)
    private String zzq;
    @SafeParcelable$Field(getter = "getServiceId", id = 2)
    private String zzu;
    @Nullable
    @SafeParcelable$Field(getter = "getBluetoothDevice", id = 4)
    private BluetoothDevice zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzes();
    }
    
    private zzer() {
    }
    
    @SafeParcelable$Constructor
    zzer(@SafeParcelable$Param(id = 1) final String zzca, @SafeParcelable$Param(id = 2) final String zzu, @SafeParcelable$Param(id = 3) final String zzq, @Nullable @SafeParcelable$Param(id = 4) final BluetoothDevice zzv) {
        this.zzca = zzca;
        this.zzu = zzu;
        this.zzq = zzq;
        this.zzv = zzv;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzer)) {
                return false;
            }
            final zzer zzer = (zzer)o;
            if (!Objects.equal((Object)this.zzca, (Object)zzer.zzca) || !Objects.equal((Object)this.zzu, (Object)zzer.zzu) || !Objects.equal((Object)this.zzq, (Object)zzer.zzq) || !Objects.equal((Object)this.zzv, (Object)zzer.zzv)) {
                return false;
            }
        }
        return true;
    }
    
    public final String getEndpointName() {
        return this.zzq;
    }
    
    public final String getServiceId() {
        return this.zzu;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzca, this.zzu, this.zzq, this.zzv });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.zzca, false);
        SafeParcelWriter.writeString(parcel, 2, this.zzu, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzq, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzv, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zze() {
        return this.zzca;
    }
    
    @Nullable
    public final BluetoothDevice zzk() {
        return this.zzv;
    }
}
