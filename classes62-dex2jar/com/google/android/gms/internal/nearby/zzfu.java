// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Arrays;
import com.google.android.gms.common.internal.Objects;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import android.os.IInterface;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.support.annotation.Nullable;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Reserved;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SendPayloadParamsCreator")
@SafeParcelable$Reserved({ 1000 })
public final class zzfu extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zzfu> CREATOR;
    @Nullable
    @SafeParcelable$Field(getter = "getResultListenerAsBinder", id = 1, type = "android.os.IBinder")
    private zzdz zzar;
    @Nullable
    @SafeParcelable$Field(getter = "getPayload", id = 3)
    private zzfh zzdk;
    @SafeParcelable$Field(getter = "getRemoteEndpointIds", id = 2)
    private String[] zzee;
    @SafeParcelable$Field(getter = "getSendReliably", id = 4)
    private boolean zzef;
    
    static {
        CREATOR = (Parcelable$Creator)new zzfx();
    }
    
    private zzfu() {
    }
    
    @SafeParcelable$Constructor
    zzfu(@Nullable @SafeParcelable$Param(id = 1) final IBinder binder, @SafeParcelable$Param(id = 2) final String[] array, @Nullable @SafeParcelable$Param(id = 3) final zzfh zzfh, @SafeParcelable$Param(id = 4) final boolean b) {
        zzdz zzdz;
        if (binder == null) {
            zzdz = null;
        }
        else {
            final IInterface queryLocalInterface = binder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.IResultListener");
            if (queryLocalInterface instanceof zzdz) {
                zzdz = (zzdz)queryLocalInterface;
            }
            else {
                zzdz = new zzeb(binder);
            }
        }
        this(zzdz, array, zzfh, b);
    }
    
    private zzfu(@Nullable final zzdz zzar, final String[] zzee, @Nullable final zzfh zzdk, final boolean zzef) {
        this.zzar = zzar;
        this.zzee = zzee;
        this.zzdk = zzdk;
        this.zzef = zzef;
    }
    
    public final boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof zzfu)) {
                return false;
            }
            final zzfu zzfu = (zzfu)o;
            if (!Objects.equal((Object)this.zzar, (Object)zzfu.zzar) || !Arrays.equals(this.zzee, zzfu.zzee) || !Objects.equal((Object)this.zzdk, (Object)zzfu.zzdk) || !Objects.equal((Object)this.zzef, (Object)zzfu.zzef)) {
                return false;
            }
        }
        return true;
    }
    
    public final int hashCode() {
        return Objects.hashCode(new Object[] { this.zzar, Arrays.hashCode(this.zzee), this.zzdk, this.zzef });
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        IBinder binder;
        if (this.zzar == null) {
            binder = null;
        }
        else {
            binder = this.zzar.asBinder();
        }
        SafeParcelWriter.writeIBinder(parcel, 1, binder, false);
        SafeParcelWriter.writeStringArray(parcel, 2, this.zzee, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzdk, n, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.zzef);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
