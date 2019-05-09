// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.signin.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.auth.api.signin.GoogleSignInOptionsExtension;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Bundle;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "GoogleSignInOptionsExtensionCreator")
public class GoogleSignInOptionsExtensionParcelable extends AbstractSafeParcelable
{
    public static final Parcelable$Creator<GoogleSignInOptionsExtensionParcelable> CREATOR;
    @SafeParcelable$Field(getter = "getBundle", id = 3)
    private Bundle mBundle;
    @SafeParcelable$Field(getter = "getType", id = 2)
    private int mType;
    @SafeParcelable$VersionField(id = 1)
    private final int versionCode;
    
    static {
        CREATOR = (Parcelable$Creator)new zaa();
    }
    
    @SafeParcelable$Constructor
    GoogleSignInOptionsExtensionParcelable(@SafeParcelable$Param(id = 1) final int versionCode, @SafeParcelable$Param(id = 2) final int mType, @SafeParcelable$Param(id = 3) final Bundle mBundle) {
        this.versionCode = versionCode;
        this.mType = mType;
        this.mBundle = mBundle;
    }
    
    public GoogleSignInOptionsExtensionParcelable(final GoogleSignInOptionsExtension googleSignInOptionsExtension) {
        this(1, googleSignInOptionsExtension.getExtensionType(), googleSignInOptionsExtension.toBundle());
    }
    
    @KeepForSdk
    public int getType() {
        return this.mType;
    }
    
    public void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.versionCode);
        SafeParcelWriter.writeInt(parcel, 2, this.getType());
        SafeParcelWriter.writeBundle(parcel, 3, this.mBundle, false);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
}
