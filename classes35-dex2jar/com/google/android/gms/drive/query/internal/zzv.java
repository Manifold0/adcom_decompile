// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.drive.query.Filter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;

@SafeParcelable$Class(creator = "NotFilterCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzv extends zza
{
    public static final Parcelable$Creator<zzv> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final FilterHolder zzlz;
    
    static {
        CREATOR = (Parcelable$Creator)new zzw();
    }
    
    public zzv(final Filter filter) {
        this(new FilterHolder(filter));
    }
    
    @SafeParcelable$Constructor
    zzv(@SafeParcelable$Param(id = 1) final FilterHolder zzlz) {
        this.zzlz = zzlz;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzlz, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final <T> T zza(final zzj<T> zzj) {
        return zzj.zza(this.zzlz.getFilter().zza(zzj));
    }
}
