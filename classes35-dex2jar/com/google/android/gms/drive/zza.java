// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.util.Base64;
import com.google.android.gms.internal.drive.zzix;
import com.google.android.gms.internal.drive.zzhm;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ChangeSequenceNumberCreator")
@SafeParcelable$Reserved({ 1 })
public class zza extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zza> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final long zze;
    @SafeParcelable$Field(id = 3)
    private final long zzf;
    @SafeParcelable$Field(id = 4)
    private final long zzg;
    private volatile String zzh;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @SafeParcelable$Constructor
    public zza(@SafeParcelable$Param(id = 2) final long zze, @SafeParcelable$Param(id = 3) final long zzf, @SafeParcelable$Param(id = 4) final long zzg) {
        final boolean b = true;
        this.zzh = null;
        Preconditions.checkArgument(zze != -1L);
        Preconditions.checkArgument(zzf != -1L);
        Preconditions.checkArgument(zzg != -1L && b);
        this.zze = zze;
        this.zzf = zzf;
        this.zzg = zzg;
    }
    
    public boolean equals(final Object o) {
        if (o != null && o.getClass() == zza.class) {
            final zza zza = (zza)o;
            if (zza.zzf == this.zzf && zza.zzg == this.zzg && zza.zze == this.zze) {
                return true;
            }
        }
        return false;
    }
    
    public int hashCode() {
        final String value = String.valueOf(this.zze);
        final String value2 = String.valueOf(this.zzf);
        final String value3 = String.valueOf(this.zzg);
        return new StringBuilder(String.valueOf(value).length() + String.valueOf(value2).length() + String.valueOf(value3).length()).append(value).append(value2).append(value3).toString().hashCode();
    }
    
    public String toString() {
        if (this.zzh == null) {
            final zzhm zzhm = new zzhm();
            zzhm.versionCode = 1;
            zzhm.zze = this.zze;
            zzhm.zzf = this.zzf;
            zzhm.zzg = this.zzg;
            final String encodeToString = Base64.encodeToString(zzix.zza(zzhm), 10);
            final String value = String.valueOf("ChangeSequenceNumber:");
            final String value2 = String.valueOf(encodeToString);
            String concat;
            if (value2.length() != 0) {
                concat = value.concat(value2);
            }
            else {
                concat = new String(value);
            }
            this.zzh = concat;
        }
        return this.zzh;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeLong(parcel, 2, this.zze);
        SafeParcelWriter.writeLong(parcel, 3, this.zzf);
        SafeParcelWriter.writeLong(parcel, 4, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
