// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "OperatorCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzx extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzx> CREATOR;
    public static final zzx zzma;
    public static final zzx zzmb;
    public static final zzx zzmc;
    public static final zzx zzmd;
    public static final zzx zzme;
    public static final zzx zzmf;
    public static final zzx zzmg;
    private static final zzx zzmh;
    public static final zzx zzmi;
    @SafeParcelable$Field(id = 1)
    private final String tag;
    
    static {
        CREATOR = (Parcelable$Creator)new zzy();
        zzma = new zzx("=");
        zzmb = new zzx("<");
        zzmc = new zzx("<=");
        zzmd = new zzx(">");
        zzme = new zzx(">=");
        zzmf = new zzx("and");
        zzmg = new zzx("or");
        zzmh = new zzx("not");
        zzmi = new zzx("contains");
    }
    
    @SafeParcelable$Constructor
    zzx(@SafeParcelable$Param(id = 1) final String tag) {
        this.tag = tag;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (o == null || this.getClass() != o.getClass()) {
                return false;
            }
            final zzx zzx = (zzx)o;
            if (this.tag == null) {
                if (zzx.tag != null) {
                    return false;
                }
            }
            else if (!this.tag.equals(zzx.tag)) {
                return false;
            }
        }
        return true;
    }
    
    public final String getTag() {
        return this.tag;
    }
    
    public final int hashCode() {
        int hashCode;
        if (this.tag == null) {
            hashCode = 0;
        }
        else {
            hashCode = this.tag.hashCode();
        }
        return hashCode + 31;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
