// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DeviceMetaDataCreator")
public class DeviceMetaData extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<DeviceMetaData> CREATOR;
    @SafeParcelable$Field(getter = "isLockScreenSolved", id = 2)
    private boolean zzbs;
    @SafeParcelable$Field(getter = "getMinAgeOfLockScreen", id = 3)
    private long zzbt;
    @SafeParcelable$Field(getter = "isChallengeAllowed", id = 4)
    private final boolean zzbu;
    @SafeParcelable$VersionField(id = 1)
    private final int zzv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzv();
    }
    
    @SafeParcelable$Constructor
    DeviceMetaData(@SafeParcelable$Param(id = 1) final int zzv, @SafeParcelable$Param(id = 2) final boolean zzbs, @SafeParcelable$Param(id = 3) final long zzbt, @SafeParcelable$Param(id = 4) final boolean zzbu) {
        this.zzv = zzv;
        this.zzbs = zzbs;
        this.zzbt = zzbt;
        this.zzbu = zzbu;
    }
    
    public long getMinAgeOfLockScreen() {
        return this.zzbt;
    }
    
    public boolean isChallengeAllowed() {
        return this.zzbu;
    }
    
    public boolean isLockScreenSolved() {
        return this.zzbs;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzv);
        SafeParcelWriter.writeBoolean(parcel, 2, this.isLockScreenSolved());
        SafeParcelWriter.writeLong(parcel, 3, this.getMinAgeOfLockScreen());
        SafeParcelWriter.writeBoolean(parcel, 4, this.isChallengeAllowed());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
