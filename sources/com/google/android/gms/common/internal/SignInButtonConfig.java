package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Class;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.VersionField;

@Class(creator = "SignInButtonConfigCreator")
public class SignInButtonConfig extends AbstractSafeParcelable {
    public static final Creator<SignInButtonConfig> CREATOR = new zao();
    @VersionField(id = 1)
    private final int zalf;
    @Field(getter = "getScopes", id = 4)
    @Deprecated
    private final Scope[] zany;
    @Field(getter = "getButtonSize", id = 2)
    private final int zapd;
    @Field(getter = "getColorScheme", id = 3)
    private final int zape;

    @Constructor
    SignInButtonConfig(@Param(id = 1) int i, @Param(id = 2) int i2, @Param(id = 3) int i3, @Param(id = 4) Scope[] scopeArr) {
        this.zalf = i;
        this.zapd = i2;
        this.zape = i3;
        this.zany = scopeArr;
    }

    public SignInButtonConfig(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
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

    public void writeToParcel(Parcel parcel, int i) {
        int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.zalf);
        SafeParcelWriter.writeInt(parcel, 2, getButtonSize());
        SafeParcelWriter.writeInt(parcel, 3, getColorScheme());
        SafeParcelWriter.writeTypedArray(parcel, 4, getScopes(), i, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
