// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.app.PendingIntent;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.ConnectionResult;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ResolveAccountResponseCreator")
public class ResolveAccountResponse extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountResponse> CREATOR;
    @SafeParcelable$Field(getter = "getConnectionResult", id = 3)
    private ConnectionResult zadi;
    @SafeParcelable$Field(getter = "getSaveDefaultAccount", id = 4)
    private boolean zagg;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(id = 2)
    private IBinder zanx;
    @SafeParcelable$Field(getter = "isFromCrossClientAuth", id = 5)
    private boolean zapc;
    
    static {
        CREATOR = (Parcelable$Creator)new zan();
    }
    
    public ResolveAccountResponse(final int n) {
        this(new ConnectionResult(n, (PendingIntent)null));
    }
    
    @SafeParcelable$Constructor
    ResolveAccountResponse(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final IBinder zanx, @SafeParcelable$Param(id = 3) final ConnectionResult zadi, @SafeParcelable$Param(id = 4) final boolean zagg, @SafeParcelable$Param(id = 5) final boolean zapc) {
        this.zalf = zalf;
        this.zanx = zanx;
        this.zadi = zadi;
        this.zagg = zagg;
        this.zapc = zapc;
    }
    
    public ResolveAccountResponse(final ConnectionResult connectionResult) {
        this(1, null, connectionResult, false, false);
    }
    
    public boolean equals(final Object o) {
        if (this != o) {
            if (!(o instanceof ResolveAccountResponse)) {
                return false;
            }
            final ResolveAccountResponse resolveAccountResponse = (ResolveAccountResponse)o;
            if (!this.zadi.equals((Object)resolveAccountResponse.zadi) || !this.getAccountAccessor().equals(resolveAccountResponse.getAccountAccessor())) {
                return false;
            }
        }
        return true;
    }
    
    public IAccountAccessor getAccountAccessor() {
        return IAccountAccessor$Stub.asInterface(this.zanx);
    }
    
    public ConnectionResult getConnectionResult() {
        return this.zadi;
    }
    
    public boolean getSaveDefaultAccount() {
        return this.zagg;
    }
    
    public boolean isFromCrossClientAuth() {
        return this.zapc;
    }
    
    public ResolveAccountResponse setAccountAccessor(final IAccountAccessor accountAccessor) {
        IBinder binder;
        if (accountAccessor == null) {
            binder = null;
        }
        else {
            binder = accountAccessor.asBinder();
        }
        this.zanx = binder;
        return this;
    }
    
    public ResolveAccountResponse setIsFromCrossClientAuth(final boolean zapc) {
        this.zapc = zapc;
        return this;
    }
    
    public ResolveAccountResponse setSaveDefaultAccount(final boolean zagg) {
        this.zagg = zagg;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zanx, false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.getConnectionResult(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 4, this.getSaveDefaultAccount());
        SafeParcelWriter.writeBoolean(parcel, 5, this.isFromCrossClientAuth());
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
