// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.response;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ShowFirstParty;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@ShowFirstParty
@SafeParcelable$Class(creator = "FieldMapPairCreator")
public final class zam extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zam> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    @SafeParcelable$Field(id = 2)
    final String zaqz;
    @SafeParcelable$Field(id = 3)
    final FastJsonResponse.Field<?, ?> zara;
    
    static {
        CREATOR = (Parcelable$Creator)new zaj();
    }
    
    @SafeParcelable$Constructor
    zam(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final String zaqz, @SafeParcelable$Param(id = 3) final FastJsonResponse.Field<?, ?> zara) {
        this.versionCode = versionCode;
        this.zaqz = zaqz;
        this.zara = zara;
    }
    
    zam(final String zaqz, final FastJsonResponse.Field<?, ?> zara) {
        this.versionCode = 1;
        this.zaqz = zaqz;
        this.zara = zara;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeString(parcel, 2, this.zaqz, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zara, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
