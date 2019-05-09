// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.ArrayList;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ParentDriveIdSetCreator")
@SafeParcelable$Reserved({ 1 })
public class ParentDriveIdSet extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<ParentDriveIdSet> CREATOR;
    @SafeParcelable$Field(id = 2)
    final List<zzq> zzit;
    
    static {
        CREATOR = (Parcelable$Creator)new zzn();
    }
    
    public ParentDriveIdSet() {
        this(new ArrayList<zzq>());
    }
    
    @SafeParcelable$Constructor
    ParentDriveIdSet(@SafeParcelable$Param(id = 2) final List<zzq> zzit) {
        this.zzit = zzit;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzit, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
