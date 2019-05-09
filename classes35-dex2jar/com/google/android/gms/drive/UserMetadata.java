// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "UserMetadataCreator")
@SafeParcelable$Reserved({ 1 })
public class UserMetadata extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<UserMetadata> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final String zzbm;
    @Nullable
    @SafeParcelable$Field(id = 3)
    private final String zzbn;
    @Nullable
    @SafeParcelable$Field(id = 4)
    private final String zzbo;
    @SafeParcelable$Field(id = 5)
    private final boolean zzbp;
    @Nullable
    @SafeParcelable$Field(id = 6)
    private final String zzbq;
    
    static {
        CREATOR = (Parcelable$Creator)new zzt();
    }
    
    @SafeParcelable$Constructor
    public UserMetadata(@SafeParcelable$Param(id = 2) final String zzbm, @Nullable @SafeParcelable$Param(id = 3) final String zzbn, @Nullable @SafeParcelable$Param(id = 4) final String zzbo, @SafeParcelable$Param(id = 5) final boolean zzbp, @Nullable @SafeParcelable$Param(id = 6) final String zzbq) {
        this.zzbm = zzbm;
        this.zzbn = zzbn;
        this.zzbo = zzbo;
        this.zzbp = zzbp;
        this.zzbq = zzbq;
    }
    
    public String toString() {
        return String.format("Permission ID: '%s', Display Name: '%s', Picture URL: '%s', Authenticated User: %b, Email: '%s'", this.zzbm, this.zzbn, this.zzbo, this.zzbp, this.zzbq);
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 2, this.zzbm, false);
        SafeParcelWriter.writeString(parcel, 3, this.zzbn, false);
        SafeParcelWriter.writeString(parcel, 4, this.zzbo, false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.zzbp);
        SafeParcelWriter.writeString(parcel, 6, this.zzbq, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
