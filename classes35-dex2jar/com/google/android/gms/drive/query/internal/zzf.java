// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Locale;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "FieldWithSortOrderCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzf extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzf> CREATOR;
    @SafeParcelable$Field(id = 1)
    private final String fieldName;
    @SafeParcelable$Field(id = 2)
    private final boolean zzlm;
    
    static {
        CREATOR = (Parcelable$Creator)new zzg();
    }
    
    @SafeParcelable$Constructor
    public zzf(@SafeParcelable$Param(id = 1) final String fieldName, @SafeParcelable$Param(id = 2) final boolean zzlm) {
        this.fieldName = fieldName;
        this.zzlm = zzlm;
    }
    
    public final String toString() {
        final Locale us = Locale.US;
        final String fieldName = this.fieldName;
        String s;
        if (this.zzlm) {
            s = "ASC";
        }
        else {
            s = "DESC";
        }
        return String.format(us, "FieldWithSortOrder[%s %s]", fieldName, s);
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.fieldName, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzlm);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
