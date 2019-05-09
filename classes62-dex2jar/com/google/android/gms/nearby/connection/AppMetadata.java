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
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@Deprecated
@SafeParcelable$Class(creator = "AppMetadataCreator")
@SafeParcelable$Reserved({ 1000 })
public final class AppMetadata extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AppMetadata> CREATOR;
    @SafeParcelable$Field(getter = "getAppIdentifiers", id = 1)
    private final List<AppIdentifier> zzp;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    @SafeParcelable$Constructor
    public AppMetadata(@SafeParcelable$Param(id = 1) final List<AppIdentifier> list) {
        this.zzp = (List<AppIdentifier>)Preconditions.checkNotNull((Object)list, (Object)"Must specify application identifiers");
        Preconditions.checkNotZero(list.size(), (Object)"Application identifiers cannot be empty");
    }
    
    public final List<AppIdentifier> getAppIdentifiers() {
        return this.zzp;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.getAppIdentifiers(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
