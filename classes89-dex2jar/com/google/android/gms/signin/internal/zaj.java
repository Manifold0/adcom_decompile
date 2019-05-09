// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.signin.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.app.PendingIntent;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.ConnectionResult;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SignInResponseCreator")
public final class zaj extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<zaj> CREATOR;
    @SafeParcelable$Field(getter = "getConnectionResult", id = 2)
    private final ConnectionResult zadi;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getResolveAccountResponse", id = 3)
    private final ResolveAccountResponse zase;
    
    static {
        CREATOR = (Parcelable$Creator)new zak();
    }
    
    public zaj(final int n) {
        this(new ConnectionResult(8, (PendingIntent)null), null);
    }
    
    @SafeParcelable$Constructor
    zaj(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final ConnectionResult zadi, @SafeParcelable$Param(id = 3) final ResolveAccountResponse zase) {
        this.zalf = zalf;
        this.zadi = zadi;
        this.zase = zase;
    }
    
    private zaj(final ConnectionResult connectionResult, final ResolveAccountResponse resolveAccountResponse) {
        this(1, connectionResult, null);
    }
    
    public final ConnectionResult getConnectionResult() {
        return this.zadi;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.zadi, n, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zase, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final ResolveAccountResponse zacx() {
        return this.zase;
    }
}
