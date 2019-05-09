// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.query.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.drive.query.Filter;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "FilterHolderCreator")
@SafeParcelable$Reserved({ 1000 })
public class FilterHolder extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<FilterHolder> CREATOR;
    private final Filter zzba;
    @SafeParcelable$Field(id = 1)
    private final zzb<?> zzln;
    @SafeParcelable$Field(id = 2)
    private final zzd zzlo;
    @SafeParcelable$Field(id = 3)
    private final zzr zzlp;
    @SafeParcelable$Field(id = 4)
    private final zzv zzlq;
    @SafeParcelable$Field(id = 5)
    private final zzp<?> zzlr;
    @SafeParcelable$Field(id = 6)
    private final zzt zzls;
    @SafeParcelable$Field(id = 7)
    private final zzn zzlt;
    @SafeParcelable$Field(id = 8)
    private final zzl zzlu;
    @SafeParcelable$Field(id = 9)
    private final zzz zzlv;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    public FilterHolder(final Filter zzba) {
        Preconditions.checkNotNull((Object)zzba, (Object)"Null filter.");
        zzb<?> zzln;
        if (zzba instanceof zzb) {
            zzln = (zzb<?>)zzba;
        }
        else {
            zzln = null;
        }
        this.zzln = zzln;
        zzd zzlo;
        if (zzba instanceof zzd) {
            zzlo = (zzd)zzba;
        }
        else {
            zzlo = null;
        }
        this.zzlo = zzlo;
        zzr zzlp;
        if (zzba instanceof zzr) {
            zzlp = (zzr)zzba;
        }
        else {
            zzlp = null;
        }
        this.zzlp = zzlp;
        zzv zzlq;
        if (zzba instanceof zzv) {
            zzlq = (zzv)zzba;
        }
        else {
            zzlq = null;
        }
        this.zzlq = zzlq;
        zzp<?> zzlr;
        if (zzba instanceof zzp) {
            zzlr = (zzp<?>)zzba;
        }
        else {
            zzlr = null;
        }
        this.zzlr = zzlr;
        zzt zzls;
        if (zzba instanceof zzt) {
            zzls = (zzt)zzba;
        }
        else {
            zzls = null;
        }
        this.zzls = zzls;
        zzn zzlt;
        if (zzba instanceof zzn) {
            zzlt = (zzn)zzba;
        }
        else {
            zzlt = null;
        }
        this.zzlt = zzlt;
        zzl zzlu;
        if (zzba instanceof zzl) {
            zzlu = (zzl)zzba;
        }
        else {
            zzlu = null;
        }
        this.zzlu = zzlu;
        zzz zzlv;
        if (zzba instanceof zzz) {
            zzlv = (zzz)zzba;
        }
        else {
            zzlv = null;
        }
        this.zzlv = zzlv;
        if (this.zzln == null && this.zzlo == null && this.zzlp == null && this.zzlq == null && this.zzlr == null && this.zzls == null && this.zzlt == null && this.zzlu == null && this.zzlv == null) {
            throw new IllegalArgumentException("Invalid filter type.");
        }
        this.zzba = zzba;
    }
    
    @SafeParcelable$Constructor
    FilterHolder(@SafeParcelable$Param(id = 1) final zzb<?> zzln, @SafeParcelable$Param(id = 2) final zzd zzlo, @SafeParcelable$Param(id = 3) final zzr zzlp, @SafeParcelable$Param(id = 4) final zzv zzlq, @SafeParcelable$Param(id = 5) final zzp<?> zzlr, @SafeParcelable$Param(id = 6) final zzt zzls, @SafeParcelable$Param(id = 7) final zzn<?> zzlt, @SafeParcelable$Param(id = 8) final zzl zzlu, @SafeParcelable$Param(id = 9) final zzz zzlv) {
        this.zzln = zzln;
        this.zzlo = zzlo;
        this.zzlp = zzlp;
        this.zzlq = zzlq;
        this.zzlr = zzlr;
        this.zzls = zzls;
        this.zzlt = zzlt;
        this.zzlu = zzlu;
        this.zzlv = zzlv;
        if (this.zzln != null) {
            this.zzba = this.zzln;
            return;
        }
        if (this.zzlo != null) {
            this.zzba = this.zzlo;
            return;
        }
        if (this.zzlp != null) {
            this.zzba = this.zzlp;
            return;
        }
        if (this.zzlq != null) {
            this.zzba = this.zzlq;
            return;
        }
        if (this.zzlr != null) {
            this.zzba = this.zzlr;
            return;
        }
        if (this.zzls != null) {
            this.zzba = this.zzls;
            return;
        }
        if (this.zzlt != null) {
            this.zzba = this.zzlt;
            return;
        }
        if (this.zzlu != null) {
            this.zzba = this.zzlu;
            return;
        }
        if (this.zzlv != null) {
            this.zzba = this.zzlv;
            return;
        }
        throw new IllegalArgumentException("At least one filter must be set.");
    }
    
    public final Filter getFilter() {
        return this.zzba;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzln, n, false);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zzlo, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzlp, n, false);
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.zzlq, n, false);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzlr, n, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zzls, n, false);
        SafeParcelWriter.writeParcelable(parcel, 7, (Parcelable)this.zzlt, n, false);
        SafeParcelWriter.writeParcelable(parcel, 8, (Parcelable)this.zzlu, n, false);
        SafeParcelWriter.writeParcelable(parcel, 9, (Parcelable)this.zzlv, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
