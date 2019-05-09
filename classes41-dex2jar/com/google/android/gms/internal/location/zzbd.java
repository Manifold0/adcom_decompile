// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import java.util.Collections;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.ClientIdentity;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "LocationRequestInternalCreator")
@SafeParcelable$Reserved({ 1000, 2, 3, 4 })
public final class zzbd extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzbd> CREATOR;
    static final List<ClientIdentity> zzcd;
    @Nullable
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 10)
    private String moduleId;
    @Nullable
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 6)
    private String tag;
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 1)
    private LocationRequest zzdg;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_HIDE_FROM_APP_OPS", id = 7)
    private boolean zzdh;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_FORCE_COARSE_LOCATION", id = 8)
    private boolean zzdi;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_EXEMPT_FROM_THROTTLE", id = 9)
    private boolean zzdj;
    private boolean zzdk;
    @SafeParcelable$Field(defaultValueUnchecked = "LocationRequestInternal.DEFAULT_CLIENTS", id = 5)
    private List<ClientIdentity> zzm;
    
    static {
        zzcd = Collections.emptyList();
        CREATOR = (Parcelable$Creator)new zzbe();
    }
    
    @SafeParcelable$Constructor
    zzbd(@SafeParcelable$Param(id = 1) final LocationRequest zzdg, @SafeParcelable$Param(id = 5) final List<ClientIdentity> zzm, @Nullable @SafeParcelable$Param(id = 6) final String tag, @SafeParcelable$Param(id = 7) final boolean zzdh, @SafeParcelable$Param(id = 8) final boolean zzdi, @SafeParcelable$Param(id = 9) final boolean zzdj, @SafeParcelable$Param(id = 10) final String moduleId) {
        this.zzdk = true;
        this.zzdg = zzdg;
        this.zzm = zzm;
        this.tag = tag;
        this.zzdh = zzdh;
        this.zzdi = zzdi;
        this.zzdj = zzdj;
        this.moduleId = moduleId;
    }
    
    @Deprecated
    public static zzbd zza(final LocationRequest locationRequest) {
        return new zzbd(locationRequest, zzbd.zzcd, null, false, false, false, null);
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof zzbd) {
            final zzbd zzbd = (zzbd)o;
            if (Objects.equal((Object)this.zzdg, (Object)zzbd.zzdg) && Objects.equal((Object)this.zzm, (Object)zzbd.zzm) && Objects.equal((Object)this.tag, (Object)zzbd.tag) && this.zzdh == zzbd.zzdh && this.zzdi == zzbd.zzdi && this.zzdj == zzbd.zzdj && Objects.equal((Object)this.moduleId, (Object)zzbd.moduleId)) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return this.zzdg.hashCode();
    }
    
    public final String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.zzdg);
        if (this.tag != null) {
            sb.append(" tag=").append(this.tag);
        }
        if (this.moduleId != null) {
            sb.append(" moduleId=").append(this.moduleId);
        }
        sb.append(" hideAppOps=").append(this.zzdh);
        sb.append(" clients=").append(this.zzm);
        sb.append(" forceCoarseLocation=").append(this.zzdi);
        if (this.zzdj) {
            sb.append(" exemptFromBackgroundThrottle");
        }
        return sb.toString();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzdg, n, false);
        SafeParcelWriter.writeTypedList(parcel, 5, (List)this.zzm, false);
        SafeParcelWriter.writeString(parcel, 6, this.tag, false);
        SafeParcelWriter.writeBoolean(parcel, 7, this.zzdh);
        SafeParcelWriter.writeBoolean(parcel, 8, this.zzdi);
        SafeParcelWriter.writeBoolean(parcel, 9, this.zzdj);
        SafeParcelWriter.writeString(parcel, 10, this.moduleId, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
