// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Class(creator = "FeatureCreator")
public class Feature extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<Feature> CREATOR;
    @Field(getter = "getName", id = 1)
    private final String name;
    @Deprecated
    @Field(getter = "getOldVersion", id = 2)
    private final int zzk;
    @Field(defaultValue = "-1", getter = "getVersion", id = 3)
    private final long zzl;
    
    static {
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @Constructor
    public Feature(@Param(id = 1) final String name, @Param(id = 2) final int zzk, @Param(id = 3) final long zzl) {
        this.name = name;
        this.zzk = zzk;
        this.zzl = zzl;
    }
    
    @KeepForSdk
    public Feature(final String name, final long zzl) {
        this.name = name;
        this.zzl = zzl;
        this.zzk = -1;
    }
    
    @Override
    public boolean equals(@Nullable final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof Feature) {
            final Feature feature = (Feature)o;
            if (this.getName() == null || !this.getName().equals(feature.getName())) {
                b2 = b;
                if (this.getName() != null) {
                    return b2;
                }
                b2 = b;
                if (feature.getName() != null) {
                    return b2;
                }
            }
            b2 = b;
            if (this.getVersion() == feature.getVersion()) {
                b2 = true;
            }
        }
        return b2;
    }
    
    @KeepForSdk
    public String getName() {
        return this.name;
    }
    
    @KeepForSdk
    public long getVersion() {
        if (this.zzl == -1L) {
            return this.zzk;
        }
        return this.zzl;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(this.getName(), this.getVersion());
    }
    
    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", this.getName()).add("version", this.getVersion()).toString();
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeString(parcel, 1, this.getName(), false);
        SafeParcelWriter.writeInt(parcel, 2, this.zzk);
        SafeParcelWriter.writeLong(parcel, 3, this.getVersion());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
