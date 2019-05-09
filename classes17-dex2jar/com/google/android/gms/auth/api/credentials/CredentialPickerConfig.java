// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "CredentialPickerConfigCreator")
public final class CredentialPickerConfig extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<CredentialPickerConfig> CREATOR;
    @SafeParcelable$Field(getter = "shouldShowCancelButton", id = 2)
    private final boolean mShowCancelButton;
    @SafeParcelable$Field(id = 1000)
    private final int zzu;
    @SafeParcelable$Field(getter = "shouldShowAddAccountButton", id = 1)
    private final boolean zzv;
    @Deprecated
    @SafeParcelable$Field(getter = "isForNewAccount", id = 3)
    private final boolean zzw;
    @SafeParcelable$Field(getter = "getPromptInternalId", id = 4)
    private final int zzx;
    
    static {
        CREATOR = (Parcelable$Creator)new zze();
    }
    
    @SafeParcelable$Constructor
    CredentialPickerConfig(@SafeParcelable$Param(id = 1000) int n, @SafeParcelable$Param(id = 1) final boolean zzv, @SafeParcelable$Param(id = 2) final boolean mShowCancelButton, @SafeParcelable$Param(id = 3) final boolean zzw, @SafeParcelable$Param(id = 4) final int zzx) {
        final int n2 = 3;
        final boolean b = true;
        this.zzu = n;
        this.zzv = zzv;
        this.mShowCancelButton = mShowCancelButton;
        if (n < 2) {
            this.zzw = zzw;
            if (zzw) {
                n = n2;
            }
            else {
                n = 1;
            }
            this.zzx = n;
            return;
        }
        this.zzw = (zzx == 3 && b);
        this.zzx = zzx;
    }
    
    private CredentialPickerConfig(final Builder builder) {
        this(2, builder.zzv, builder.mShowCancelButton, false, builder.zzy);
    }
    
    @Deprecated
    public final boolean isForNewAccount() {
        return this.zzx == 3;
    }
    
    public final boolean shouldShowAddAccountButton() {
        return this.zzv;
    }
    
    public final boolean shouldShowCancelButton() {
        return this.mShowCancelButton;
    }
    
    public final void writeToParcel(final Parcel parcel, int beginObjectHeader) {
        beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeBoolean(parcel, 1, this.shouldShowAddAccountButton());
        SafeParcelWriter.writeBoolean(parcel, 2, this.shouldShowCancelButton());
        SafeParcelWriter.writeBoolean(parcel, 3, this.isForNewAccount());
        SafeParcelWriter.writeInt(parcel, 4, this.zzx);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static class Builder
    {
        private boolean mShowCancelButton;
        private boolean zzv;
        private int zzy;
        
        public Builder() {
            this.zzv = false;
            this.mShowCancelButton = true;
            this.zzy = 1;
        }
        
        public CredentialPickerConfig build() {
            return new CredentialPickerConfig(this, null);
        }
        
        @Deprecated
        public Builder setForNewAccount(final boolean b) {
            int zzy;
            if (b) {
                zzy = 3;
            }
            else {
                zzy = 1;
            }
            this.zzy = zzy;
            return this;
        }
        
        public Builder setPrompt(final int zzy) {
            this.zzy = zzy;
            return this;
        }
        
        public Builder setShowAddAccountButton(final boolean zzv) {
            this.zzv = zzv;
            return this;
        }
        
        public Builder setShowCancelButton(final boolean mShowCancelButton) {
            this.mShowCancelButton = mShowCancelButton;
            return this;
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface Prompt {
        public static final int CONTINUE = 1;
        public static final int SIGN_IN = 2;
        public static final int SIGN_UP = 3;
    }
}
