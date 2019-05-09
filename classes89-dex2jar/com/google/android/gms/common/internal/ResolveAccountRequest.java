// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.accounts.Account;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "ResolveAccountRequestCreator")
public class ResolveAccountRequest extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<ResolveAccountRequest> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @SafeParcelable$Field(getter = "getSessionId", id = 3)
    private final int zapa;
    @SafeParcelable$Field(getter = "getSignInAccountHint", id = 4)
    private final GoogleSignInAccount zapb;
    @SafeParcelable$Field(getter = "getAccount", id = 2)
    private final Account zax;
    
    static {
        CREATOR = (Parcelable$Creator)new zam();
    }
    
    @SafeParcelable$Constructor
    ResolveAccountRequest(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final Account zax, @SafeParcelable$Param(id = 3) final int zapa, @SafeParcelable$Param(id = 4) final GoogleSignInAccount zapb) {
        this.zalf = zalf;
        this.zax = zax;
        this.zapa = zapa;
        this.zapb = zapb;
    }
    
    public ResolveAccountRequest(final Account account, final int n, final GoogleSignInAccount googleSignInAccount) {
        this(2, account, n, googleSignInAccount);
    }
    
    public Account getAccount() {
        return this.zax;
    }
    
    public int getSessionId() {
        return this.zapa;
    }
    
    @Nullable
    public GoogleSignInAccount getSignInAccountHint() {
        return this.zapb;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeParcelable(parcel, 2, (Parcelable)this.getAccount(), n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.getSessionId());
        SafeParcelWriter.writeParcelable(parcel, 4, (Parcelable)this.getSignInAccountHint(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
