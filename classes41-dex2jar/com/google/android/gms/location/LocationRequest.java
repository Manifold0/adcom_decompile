// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.location;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.os.SystemClock;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationRequestCreator")
@SafeParcelable$Reserved({ 1000 })
public final class LocationRequest extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<LocationRequest> CREATOR;
    public static final int PRIORITY_BALANCED_POWER_ACCURACY = 102;
    public static final int PRIORITY_HIGH_ACCURACY = 100;
    public static final int PRIORITY_LOW_POWER = 104;
    public static final int PRIORITY_NO_POWER = 105;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_PRIORITY", id = 1)
    private int priority;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPIRE_AT", id = 5)
    private long zzaf;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_INTERVAL", id = 2)
    private long zzaw;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_FASTEST_INTERVAL", id = 3)
    private long zzax;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_EXPLICIT_FASTEST_INTERVAL", id = 4)
    private boolean zzay;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_SMALLEST_DISPLACEMENT", id = 7)
    private float zzaz;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_MAX_WAIT_TIME", id = 8)
    private long zzba;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequest.DEFAULT_NUM_UPDATES", id = 6)
    private int zzx;
    
    static {
        CREATOR = (Parcelable$Creator)new zzab();
    }
    
    public LocationRequest() {
        this.priority = 102;
        this.zzaw = 3600000L;
        this.zzax = 600000L;
        this.zzay = false;
        this.zzaf = Long.MAX_VALUE;
        this.zzx = Integer.MAX_VALUE;
        this.zzaz = 0.0f;
        this.zzba = 0L;
    }
    
    @SafeParcelable$Constructor
    LocationRequest(@SafeParcelable$Param(id = 1) final int priority, @SafeParcelable$Param(id = 2) final long zzaw, @SafeParcelable$Param(id = 3) final long zzax, @SafeParcelable$Param(id = 4) final boolean zzay, @SafeParcelable$Param(id = 5) final long zzaf, @SafeParcelable$Param(id = 6) final int zzx, @SafeParcelable$Param(id = 7) final float zzaz, @SafeParcelable$Param(id = 8) final long zzba) {
        this.priority = priority;
        this.zzaw = zzaw;
        this.zzax = zzax;
        this.zzay = zzay;
        this.zzaf = zzaf;
        this.zzx = zzx;
        this.zzaz = zzaz;
        this.zzba = zzba;
    }
    
    @VisibleForTesting
    public static LocationRequest create() {
        return new LocationRequest();
    }
    
    private static void zza(final long n) {
        if (n < 0L) {
            throw new IllegalArgumentException(new StringBuilder(38).append("invalid interval: ").append(n).toString());
        }
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof LocationRequest)) {
                return false;
            }
            final LocationRequest locationRequest = (LocationRequest)o;
            if (this.priority != locationRequest.priority || this.zzaw != locationRequest.zzaw || this.zzax != locationRequest.zzax || this.zzay != locationRequest.zzay || this.zzaf != locationRequest.zzaf || this.zzx != locationRequest.zzx || this.zzaz != locationRequest.zzaz || this.getMaxWaitTime() != locationRequest.getMaxWaitTime()) {
                return false;
            }
        }
        return true;
    }
    
    public final long getExpirationTime() {
        return this.zzaf;
    }
    
    public final long getFastestInterval() {
        return this.zzax;
    }
    
    public final long getInterval() {
        return this.zzaw;
    }
    
    public final long getMaxWaitTime() {
        long n;
        if ((n = this.zzba) < this.zzaw) {
            n = this.zzaw;
        }
        return n;
    }
    
    public final int getNumUpdates() {
        return this.zzx;
    }
    
    public final int getPriority() {
        return this.priority;
    }
    
    public final float getSmallestDisplacement() {
        return this.zzaz;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.priority, this.zzaw, this.zzaz, this.zzba });
    }
    
    public final boolean isFastestIntervalExplicitlySet() {
        return this.zzay;
    }
    
    public final LocationRequest setExpirationDuration(final long n) {
        final long elapsedRealtime = SystemClock.elapsedRealtime();
        if (n > Long.MAX_VALUE - elapsedRealtime) {
            this.zzaf = Long.MAX_VALUE;
        }
        else {
            this.zzaf = elapsedRealtime + n;
        }
        if (this.zzaf < 0L) {
            this.zzaf = 0L;
        }
        return this;
    }
    
    @VisibleForTesting
    public final LocationRequest setExpirationTime(final long zzaf) {
        this.zzaf = zzaf;
        if (this.zzaf < 0L) {
            this.zzaf = 0L;
        }
        return this;
    }
    
    public final LocationRequest setFastestInterval(final long zzax) {
        zza(zzax);
        this.zzay = true;
        this.zzax = zzax;
        return this;
    }
    
    public final LocationRequest setInterval(final long zzaw) {
        zza(zzaw);
        this.zzaw = zzaw;
        if (!this.zzay) {
            this.zzax = (long)(this.zzaw / 6.0);
        }
        return this;
    }
    
    @VisibleForTesting
    public final LocationRequest setMaxWaitTime(final long zzba) {
        zza(zzba);
        this.zzba = zzba;
        return this;
    }
    
    @VisibleForTesting
    public final LocationRequest setNumUpdates(final int zzx) {
        if (zzx <= 0) {
            throw new IllegalArgumentException(new StringBuilder(31).append("invalid numUpdates: ").append(zzx).toString());
        }
        this.zzx = zzx;
        return this;
    }
    
    @VisibleForTesting
    public final LocationRequest setPriority(final int priority) {
        switch (priority) {
            default: {
                throw new IllegalArgumentException(new StringBuilder(28).append("invalid quality: ").append(priority).toString());
            }
            case 100:
            case 102:
            case 104:
            case 105: {
                this.priority = priority;
                return this;
            }
        }
    }
    
    @VisibleForTesting
    public final LocationRequest setSmallestDisplacement(final float zzaz) {
        if (zzaz < 0.0f) {
            throw new IllegalArgumentException(new StringBuilder(37).append("invalid displacement: ").append(zzaz).toString());
        }
        this.zzaz = zzaz;
        return this;
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        final StringBuilder append = sb.append("Request[");
        String s = null;
        switch (this.priority) {
            default: {
                s = "???";
                break;
            }
            case 100: {
                s = "PRIORITY_HIGH_ACCURACY";
                break;
            }
            case 102: {
                s = "PRIORITY_BALANCED_POWER_ACCURACY";
                break;
            }
            case 104: {
                s = "PRIORITY_LOW_POWER";
                break;
            }
            case 105: {
                s = "PRIORITY_NO_POWER";
                break;
            }
        }
        append.append(s);
        if (this.priority != 105) {
            sb.append(" requested=");
            sb.append(this.zzaw).append("ms");
        }
        sb.append(" fastest=");
        sb.append(this.zzax).append("ms");
        if (this.zzba > this.zzaw) {
            sb.append(" maxWait=");
            sb.append(this.zzba).append("ms");
        }
        if (this.zzaz > 0.0f) {
            sb.append(" smallestDisplacement=");
            sb.append(this.zzaz).append("m");
        }
        if (this.zzaf != Long.MAX_VALUE) {
            final long zzaf = this.zzaf;
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            sb.append(" expireIn=");
            sb.append(zzaf - elapsedRealtime).append("ms");
        }
        if (this.zzx != Integer.MAX_VALUE) {
            sb.append(" num=").append(this.zzx);
        }
        sb.append(']');
        return sb.toString();
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.priority);
        SafeParcelWriter.writeLong(parcel, 2, this.zzaw);
        SafeParcelWriter.writeLong(parcel, 3, this.zzax);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzay);
        SafeParcelWriter.writeLong(parcel, 5, this.zzaf);
        SafeParcelWriter.writeInt(parcel, 6, this.zzx);
        SafeParcelWriter.writeFloat(parcel, 7, this.zzaz);
        SafeParcelWriter.writeLong(parcel, 8, this.zzba);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
