// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@zzadh
@SafeParcelable$Class(creator = "SearchAdRequestParcelCreator")
@SafeParcelable$Reserved({ 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 })
public final class zzmq extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzmq> CREATOR;
    @SafeParcelable$Field(id = 15)
    public final String zzatn;
    
    static {
        CREATOR = (Parcelable$Creator)new zzmr();
    }
    
    public zzmq(final SearchAdRequest searchAdRequest) {
        this.zzatn = searchAdRequest.getQuery();
    }
    
    @SafeParcelable$Constructor
    zzmq(@SafeParcelable$Param(id = 15) final String zzatn) {
        this.zzatn = zzatn;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 15, this.zzatn, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
