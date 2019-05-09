// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OnFetchThumbnailResponseCreator")
@SafeParcelable$Reserved({ 1 })
public final class zzfl extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfl> CREATOR;
    @SafeParcelable$Field(id = 2)
    private final ParcelFileDescriptor zzhr;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfm();
    }
    
    @SafeParcelable$Constructor
    public zzfl(@SafeParcelable$Param(id = 2) final ParcelFileDescriptor zzhr) {
        this.zzhr = zzhr;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzhr, n | 0x1, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
