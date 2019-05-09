// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.Collection;
import java.util.HashSet;
import java.util.Arrays;
import javax.annotation.Nullable;
import java.util.Set;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.accounts.Account;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.IBinder;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "AuthAccountRequestCreator")
public class AuthAccountRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<AuthAccountRequest> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @Deprecated
    @SafeParcelable$Field(id = 2)
    private final IBinder zanx;
    @SafeParcelable$Field(id = 3)
    private final Scope[] zany;
    @SafeParcelable$Field(id = 4)
    private Integer zanz;
    @SafeParcelable$Field(id = 5)
    private Integer zaoa;
    @SafeParcelable$Field(id = 6, type = "android.accounts.Account")
    private Account zax;
    
    static {
        CREATOR = (Parcelable$Creator)new zaa();
    }
    
    @SafeParcelable$Constructor
    AuthAccountRequest(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final IBinder zanx, @SafeParcelable$Param(id = 3) final Scope[] zany, @SafeParcelable$Param(id = 4) final Integer zanz, @SafeParcelable$Param(id = 5) final Integer zaoa, @SafeParcelable$Param(id = 6) final Account zax) {
        this.zalf = zalf;
        this.zanx = zanx;
        this.zany = zany;
        this.zanz = zanz;
        this.zaoa = zaoa;
        this.zax = zax;
    }
    
    public AuthAccountRequest(final Account account, final Set<Scope> set) {
        this(3, null, set.toArray(new Scope[set.size()]), null, null, (Account)Preconditions.checkNotNull((Object)account));
    }
    
    @Deprecated
    public AuthAccountRequest(final IAccountAccessor accountAccessor, final Set<Scope> set) {
        this(3, accountAccessor.asBinder(), set.toArray(new Scope[set.size()]), null, null, null);
    }
    
    public Account getAccount() {
        Account zax = null;
        if (this.zax != null) {
            zax = this.zax;
        }
        else if (this.zanx != null) {
            return AccountAccessor.getAccountBinderSafe(IAccountAccessor$Stub.asInterface(this.zanx));
        }
        return zax;
    }
    
    @Nullable
    public Integer getOauthPolicy() {
        return this.zanz;
    }
    
    @Nullable
    public Integer getPolicyAction() {
        return this.zaoa;
    }
    
    public Set<Scope> getScopes() {
        return new HashSet<Scope>(Arrays.asList(this.zany));
    }
    
    public AuthAccountRequest setOauthPolicy(@Nullable final Integer zanz) {
        this.zanz = zanz;
        return this;
    }
    
    public AuthAccountRequest setPolicyAction(@Nullable final Integer zaoa) {
        this.zaoa = zaoa;
        return this;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeIBinder(parcel, 2, this.zanx, false);
        SafeParcelWriter.writeTypedArray(parcel, 3, (Parcelable[])this.zany, n, false);
        SafeParcelWriter.writeIntegerObject(parcel, 4, this.zanz, false);
        SafeParcelWriter.writeIntegerObject(parcel, 5, this.zaoa, false);
        SafeParcelWriter.writeParcelable(parcel, 6, (Parcelable)this.zax, n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
