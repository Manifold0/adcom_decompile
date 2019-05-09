// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.GoogleApiAvailabilityLight;
import com.google.android.gms.common.Feature;
import android.accounts.Account;
import android.os.Bundle;
import com.google.android.gms.common.api.Scope;
import android.os.IBinder;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Class(creator = "GetServiceRequestCreator")
@Reserved({ 9 })
public class GetServiceRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GetServiceRequest> CREATOR;
    @VersionField(id = 1)
    private final int version;
    @Field(id = 2)
    private final int zzdg;
    @Field(id = 3)
    private int zzdh;
    @Field(id = 5)
    IBinder zzdi;
    @Field(id = 6)
    Scope[] zzdj;
    @Field(id = 7)
    Bundle zzdk;
    @Field(id = 8)
    Account zzdl;
    @Field(id = 10)
    Feature[] zzdm;
    @Field(id = 11)
    Feature[] zzdn;
    @Field(id = 12)
    private boolean zzdo;
    @Field(id = 4)
    String zzy;
    
    static {
        CREATOR = (Parcelable$Creator)new zzd();
    }
    
    public GetServiceRequest(final int zzdg) {
        this.version = 4;
        this.zzdh = GoogleApiAvailabilityLight.GOOGLE_PLAY_SERVICES_VERSION_CODE;
        this.zzdg = zzdg;
        this.zzdo = true;
    }
    
    @Constructor
    GetServiceRequest(@Param(id = 1) final int version, @Param(id = 2) final int zzdg, @Param(id = 3) final int zzdh, @Param(id = 4) final String zzy, @Param(id = 5) final IBinder zzdi, @Param(id = 6) final Scope[] zzdj, @Param(id = 7) final Bundle zzdk, @Param(id = 8) final Account zzdl, @Param(id = 10) final Feature[] zzdm, @Param(id = 11) final Feature[] zzdn, @Param(id = 12) final boolean zzdo) {
        this.version = version;
        this.zzdg = zzdg;
        this.zzdh = zzdh;
        if ("com.google.android.gms".equals(zzy)) {
            this.zzy = "com.google.android.gms";
        }
        else {
            this.zzy = zzy;
        }
        if (version < 2) {
            Account accountBinderSafe = null;
            if (zzdi != null) {
                accountBinderSafe = AccountAccessor.getAccountBinderSafe(IAccountAccessor.Stub.asInterface(zzdi));
            }
            this.zzdl = accountBinderSafe;
        }
        else {
            this.zzdi = zzdi;
            this.zzdl = zzdl;
        }
        this.zzdj = zzdj;
        this.zzdk = zzdk;
        this.zzdm = zzdm;
        this.zzdn = zzdn;
        this.zzdo = zzdo;
    }
    
    @KeepForSdk
    public Bundle getExtraArgs() {
        return this.zzdk;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.version);
        SafeParcelWriter.writeInt(parcel, 2, this.zzdg);
        SafeParcelWriter.writeInt(parcel, 3, this.zzdh);
        SafeParcelWriter.writeString(parcel, 4, this.zzy, false);
        SafeParcelWriter.writeIBinder(parcel, 5, this.zzdi, false);
        SafeParcelWriter.writeTypedArray(parcel, 6, this.zzdj, n, false);
        SafeParcelWriter.writeBundle(parcel, 7, this.zzdk, false);
        SafeParcelWriter.writeParcelable(parcel, 8, (Parcelable)this.zzdl, n, false);
        SafeParcelWriter.writeTypedArray(parcel, 10, this.zzdm, n, false);
        SafeParcelWriter.writeTypedArray(parcel, 11, this.zzdn, n, false);
        SafeParcelWriter.writeBoolean(parcel, 12, this.zzdo);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
