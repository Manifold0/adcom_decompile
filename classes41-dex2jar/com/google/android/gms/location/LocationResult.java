// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.NonNull;
import java.util.Iterator;
import android.content.Intent;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.location.Location;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationResultCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationResult extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<LocationResult> CREATOR;
    static final List<Location> zzbb;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationResult.DEFAULT_LOCATIONS", getter = "getLocations", id = 1)
    private final List<Location> zzbc;
    
    static {
        zzbb = Collections.emptyList();
        CREATOR = (Parcelable$Creator)new zzac();
    }
    
    @SafeParcelable$Constructor
    LocationResult(@SafeParcelable$Param(id = 1) final List<Location> zzbc) {
        this.zzbc = zzbc;
    }
    
    public static LocationResult create(final List<Location> list) {
        List<Location> zzbb = list;
        if (list == null) {
            zzbb = LocationResult.zzbb;
        }
        return new LocationResult(zzbb);
    }
    
    public static LocationResult extractResult(final Intent intent) {
        if (!hasResult(intent)) {
            return null;
        }
        return (LocationResult)intent.getExtras().getParcelable("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }
    
    public static boolean hasResult(final Intent intent) {
        return intent != null && intent.hasExtra("com.google.android.gms.location.EXTRA_LOCATION_RESULT");
    }
    
    public final boolean equals(final Object o) {
        if (!(o instanceof LocationResult)) {
            return false;
        }
        final LocationResult locationResult = (LocationResult)o;
        if (locationResult.zzbc.size() != this.zzbc.size()) {
            return false;
        }
        final Iterator<Location> iterator = locationResult.zzbc.iterator();
        final Iterator<Location> iterator2 = this.zzbc.iterator();
        while (iterator.hasNext()) {
            if (iterator2.next().getTime() != iterator.next().getTime()) {
                return false;
            }
        }
        return true;
    }
    
    public final Location getLastLocation() {
        final int size = this.zzbc.size();
        if (size == 0) {
            return null;
        }
        return this.zzbc.get(size - 1);
    }
    
    @NonNull
    public final List<Location> getLocations() {
        return this.zzbc;
    }
    
    public final int hashCode() {
        final Iterator<Location> iterator = this.zzbc.iterator();
        int n = 17;
        while (iterator.hasNext()) {
            final long time = iterator.next().getTime();
            n = (int)(time ^ time >>> 32) + n * 31;
        }
        return n;
    }
    
    public final String toString() {
        final String value = String.valueOf(this.zzbc);
        return new StringBuilder(String.valueOf(value).length() + 27).append("LocationResult[locations: ").append(value).append("]").toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeTypedList(parcel, 1, (List)this.getLocations(), false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
