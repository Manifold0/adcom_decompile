// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import android.support.annotation.NonNull;
import java.util.Iterator;
import java.util.Collection;
import java.util.ArrayList;
import android.os.Parcelable;
import java.util.Collections;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationSettingsRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationSettingsRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<LocationSettingsRequest> CREATOR;
    @SafeParcelable$Field(getter = "getLocationRequests", id = 1)
    private final List<LocationRequest> zzbg;
    @SafeParcelable$Field(defaultValue = "false", getter = "alwaysShow", id = 2)
    private final boolean zzbh;
    @SafeParcelable$Field(getter = "needBle", id = 3)
    private final boolean zzbi;
    @SafeParcelable$Field(getter = "getConfiguration", id = 5)
    private zzae zzbj;
    
    static {
        CREATOR = (Parcelable$Creator)new zzag();
    }
    
    @SafeParcelable$Constructor
    LocationSettingsRequest(@SafeParcelable$Param(id = 1) final List<LocationRequest> zzbg, @SafeParcelable$Param(id = 2) final boolean zzbh, @SafeParcelable$Param(id = 3) final boolean zzbi, @SafeParcelable$Param(id = 5) final zzae zzbj) {
        this.zzbg = zzbg;
        this.zzbh = zzbh;
        this.zzbi = zzbi;
        this.zzbj = zzbj;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)Collections.unmodifiableList((List<?>)this.zzbg), false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.zzbh);
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzbi);
        SafeParcelWriter.writeParcelable(parcel, 5, (Parcelable)this.zzbj, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private boolean zzbh;
        private boolean zzbi;
        private zzae zzbj;
        private final ArrayList<LocationRequest> zzbk;
        
        public Builder() {
            this.zzbk = new ArrayList<LocationRequest>();
            this.zzbh = false;
            this.zzbi = false;
            this.zzbj = null;
        }
        
        public final Builder addAllLocationRequests(final Collection<LocationRequest> collection) {
            for (final LocationRequest locationRequest : collection) {
                if (locationRequest != null) {
                    this.zzbk.add(locationRequest);
                }
            }
            return this;
        }
        
        public final Builder addLocationRequest(@NonNull final LocationRequest locationRequest) {
            if (locationRequest != null) {
                this.zzbk.add(locationRequest);
            }
            return this;
        }
        
        public final LocationSettingsRequest build() {
            return new LocationSettingsRequest(this.zzbk, this.zzbh, this.zzbi, null);
        }
        
        public final Builder setAlwaysShow(final boolean zzbh) {
            this.zzbh = zzbh;
            return this;
        }
        
        public final Builder setNeedBle(final boolean zzbi) {
            this.zzbi = zzbi;
            return this;
        }
    }
}
