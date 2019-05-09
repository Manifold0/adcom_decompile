// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.nearby.connection;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
@SafeParcelable$Class(creator = "AppIdentifierCreator")
@SafeParcelable$Reserved({ 1000 })
public final class AppIdentifier extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AppIdentifier> CREATOR;
    @SafeParcelable$Field(getter = "getIdentifier", id = 1)
    private final String zzo;
    
    static {
        CREATOR = (Parcelable$Creator)new zzc();
    }
    
    @SafeParcelable$Constructor
    public AppIdentifier(@SafeParcelable$Param(id = 1) final String s) {
        this.zzo = Preconditions.checkNotEmpty(s, (Object)"Missing application identifier value");
    }
    
    public final String getIdentifier() {
        return this.zzo;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getIdentifier(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
