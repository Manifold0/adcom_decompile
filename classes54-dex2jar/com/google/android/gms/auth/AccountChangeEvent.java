// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AccountChangeEventCreator")
public class AccountChangeEvent extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AccountChangeEvent> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zze;
    @SafeParcelable$Field(id = 2)
    private final long zzf;
    @SafeParcelable$Field(id = 3)
    private final String zzg;
    @SafeParcelable$Field(id = 4)
    private final int zzh;
    @SafeParcelable$Field(id = 5)
    private final int zzi;
    @SafeParcelable$Field(id = 6)
    private final String zzj;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @SafeParcelable$Constructor
    AccountChangeEvent(@SafeParcelable$Param(id = 1) final int zze, @SafeParcelable$Param(id = 2) final long zzf, @SafeParcelable$Param(id = 3) final String s, @SafeParcelable$Param(id = 4) final int zzh, @SafeParcelable$Param(id = 5) final int zzi, @SafeParcelable$Param(id = 6) final String zzj) {
        this.zze = zze;
        this.zzf = zzf;
        this.zzg = (String)Preconditions.checkNotNull((Object)s);
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
    }
    
    public AccountChangeEvent(final long zzf, final String s, final int zzh, final int zzi, final String zzj) {
        this.zze = 1;
        this.zzf = zzf;
        this.zzg = (String)Preconditions.checkNotNull((Object)s);
        this.zzh = zzh;
        this.zzi = zzi;
        this.zzj = zzj;
    }
    
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AccountChangeEvent)) {
                return false;
            }
            final AccountChangeEvent accountChangeEvent = (AccountChangeEvent)o;
            if (this.zze != accountChangeEvent.zze || this.zzf != accountChangeEvent.zzf || !Objects.equal((Object)this.zzg, (Object)accountChangeEvent.zzg) || this.zzh != accountChangeEvent.zzh || this.zzi != accountChangeEvent.zzi || !Objects.equal((Object)this.zzj, (Object)accountChangeEvent.zzj)) {
                return false;
            }
        }
        return true;
    }
    
    public String getAccountName() {
        return this.zzg;
    }
    
    public String getChangeData() {
        return this.zzj;
    }
    
    public int getChangeType() {
        return this.zzh;
    }
    
    public int getEventIndex() {
        return this.zzi;
    }
    
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj });
    }
    
    public String toString() {
        String s = "UNKNOWN";
        switch (this.zzh) {
            case 1: {
                s = "ADDED";
                break;
            }
            case 2: {
                s = "REMOVED";
                break;
            }
            case 4: {
                s = "RENAMED_TO";
                break;
            }
            case 3: {
                s = "RENAMED_FROM";
                break;
            }
        }
        final String zzg = this.zzg;
        final String zzj = this.zzj;
        return new StringBuilder(String.valueOf(zzg).length() + 91 + String.valueOf(s).length() + String.valueOf(zzj).length()).append("AccountChangeEvent {accountName = ").append(zzg).append(", changeType = ").append(s).append(", changeData = ").append(zzj).append(", eventIndex = ").append(this.zzi).append("}").toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zze);
        SafeParcelWriter.writeLong(parcel, 2, this.zzf);
        SafeParcelWriter.writeString(parcel, 3, this.zzg, false);
        SafeParcelWriter.writeInt(parcel, 4, this.zzh);
        SafeParcelWriter.writeInt(parcel, 5, this.zzi);
        SafeParcelWriter.writeString(parcel, 6, this.zzj, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
