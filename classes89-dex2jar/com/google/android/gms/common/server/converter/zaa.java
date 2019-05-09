// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.server.converter;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ConverterWrapperCreator")
public final class zaa extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zaa> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getStringToIntConverter", id = 2)
    private final StringToIntConverter zapl;
    
    static {
        CREATOR = (Parcelable$Creator)new zab();
    }
    
    @SafeParcelable$Constructor
    zaa(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final StringToIntConverter zapl) {
        this.zalf = zalf;
        this.zapl = zapl;
    }
    
    private zaa(final StringToIntConverter zapl) {
        this.zalf = 1;
        this.zapl = zapl;
    }
    
    public static zaa zaa(final FastJsonResponse.FieldConverter<?, ?> fieldConverter) {
        if (fieldConverter instanceof StringToIntConverter) {
            return new zaa((StringToIntConverter)fieldConverter);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zapl, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final FastJsonResponse.FieldConverter<?, ?> zaci() {
        if (this.zapl != null) {
            return this.zapl;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
