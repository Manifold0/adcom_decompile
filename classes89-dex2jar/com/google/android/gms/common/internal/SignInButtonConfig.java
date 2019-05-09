// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<SignInButtonConfig> CREATOR;
    @SafeParcelable$VersionField(id = 1)
    private final int zalf;
    @Deprecated
    @SafeParcelable$Field(getter = "getScopes", id = 4)
    private final Scope[] zany;
    @SafeParcelable$Field(getter = "getButtonSize", id = 2)
    private final int zapd;
    @SafeParcelable$Field(getter = "getColorScheme", id = 3)
    private final int zape;
    
    static {
        CREATOR = (Parcelable$Creator)new zao();
    }
    
    @SafeParcelable$Constructor
    SignInButtonConfig(@SafeParcelable$Param(id = 1) final int zalf, @SafeParcelable$Param(id = 2) final int zapd, @SafeParcelable$Param(id = 3) final int zape, @SafeParcelable$Param(id = 4) final Scope[] zany) {
        this.zalf = zalf;
        this.zapd = zapd;
        this.zape = zape;
        this.zany = zany;
    }
    
    public SignInButtonConfig(final int n, final int n2, final Scope[] array) {
        this(1, n, n2, null);
    }
    
    public int getButtonSize() {
        return this.zapd;
    }
    
    public int getColorScheme() {
        return this.zape;
    }
    
    @Deprecated
    public Scope[] getScopes() {
        return this.zany;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeInt(parcel, 2, this.getButtonSize());
        SafeParcelWriter.writeInt(parcel, 3, this.getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel, 4, (Parcelable[])this.getScopes(), n, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
