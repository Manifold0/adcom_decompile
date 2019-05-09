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
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import com.google.android.gms.location.zzj;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.common.internal.ClientIdentity;
import java.util.List;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "DeviceOrientationRequestInternalCreator")
public final class zzm extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzm> CREATOR;
    @VisibleForTesting
    static final List<ClientIdentity> zzcd;
    static final zzj zzce;
    @Nullable
    @SafeParcelable$Field(defaultValueUnchecked = "null", id = 3)
    private String tag;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_DEVICE_ORIENTATION_REQUEST", id = 1)
    private zzj zzcf;
    @SafeParcelable$Field(defaultValueUnchecked = "DeviceOrientationRequestInternal.DEFAULT_CLIENTS", id = 2)
    private List<ClientIdentity> zzm;
    
    static {
        zzcd = Collections.emptyList();
        zzce = new zzj();
        CREATOR = (Parcelable$Creator)new zzn();
    }
    
    @SafeParcelable$Constructor
    zzm(@SafeParcelable$Param(id = 1) final zzj zzcf, @SafeParcelable$Param(id = 2) final List<ClientIdentity> zzm, @SafeParcelable$Param(id = 3) final String tag) {
        this.zzcf = zzcf;
        this.zzm = zzm;
        this.tag = tag;
    }
    
    public final boolean equals(final Object o) {
        if (o instanceof zzm) {
            final zzm zzm = (zzm)o;
            if (Objects.equal((Object)this.zzcf, (Object)zzm.zzcf) && Objects.equal((Object)this.zzm, (Object)zzm.zzm) && Objects.equal((Object)this.tag, (Object)zzm.tag)) {
                return true;
            }
        }
        return false;
    }
    
    public final int hashCode() {
        return this.zzcf.hashCode();
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.zzcf, n, false);
        SafeParcelWriter.writeTypedList(parcel, 2, (List)this.zzm, false);
        SafeParcelWriter.writeString(parcel, 3, this.tag, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
