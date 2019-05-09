// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Preconditions;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Class(creator = "ScopeCreator")
public final class Scope extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<Scope> CREATOR;
    @Field(getter = "getScopeUri", id = 2)
    private final String zzaq;
    @VersionField(id = 1)
    private final int zzg;
    
    static {
        CREATOR = (Parcelable$Creator)new zza();
    }
    
    @Constructor
    Scope(@Param(id = 1) final int zzg, @Param(id = 2) final String zzaq) {
        Preconditions.checkNotEmpty(zzaq, "scopeUri must not be null or empty");
        this.zzg = zzg;
        this.zzaq = zzaq;
    }
    
    public Scope(final String s) {
        this(1, s);
    }
    
    @Override
    public final boolean equals(final Object o) {
        return this == o || (o instanceof Scope && this.zzaq.equals(((Scope)o).zzaq));
    }
    
    @KeepForSdk
    public final String getScopeUri() {
        return this.zzaq;
    }
    
    @Override
    public final int hashCode() {
        return this.zzaq.hashCode();
    }
    
    @Override
    public final String toString() {
        return this.zzaq;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zzg);
        SafeParcelWriter.writeString(parcel, 2, this.getScopeUri(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
