// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.auth.api.credentials;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.support.annotation.Nullable;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@SafeParcelable$Class(creator = "HintRequestCreator")
public final class HintRequest extends AbstractSafeParcelable implements ReflectedParcelable
{
    public static final Parcelable$Creator<HintRequest> CREATOR;
    @SafeParcelable$Field(getter = "getAccountTypes", id = 4)
    private final String[] zzaa;
    @SafeParcelable$Field(getter = "isIdTokenRequested", id = 5)
    private final boolean zzad;
    @SafeParcelable$Field(getter = "getServerClientId", id = 6)
    private final String zzae;
    @SafeParcelable$Field(getter = "getIdTokenNonce", id = 7)
    private final String zzaf;
    @SafeParcelable$Field(getter = "getHintPickerConfig", id = 1)
    private final CredentialPickerConfig zzah;
    @SafeParcelable$Field(getter = "isEmailAddressIdentifierSupported", id = 2)
    private final boolean zzai;
    @SafeParcelable$Field(getter = "isPhoneNumberIdentifierSupported", id = 3)
    private final boolean zzaj;
    @SafeParcelable$Field(id = 1000)
    private final int zzu;
    
    static {
        CREATOR = (Parcelable$Creator)new zzj();
    }
    
    @SafeParcelable$Constructor
    HintRequest(@SafeParcelable$Param(id = 1000) final int zzu, @SafeParcelable$Param(id = 1) final CredentialPickerConfig credentialPickerConfig, @SafeParcelable$Param(id = 2) final boolean zzai, @SafeParcelable$Param(id = 3) final boolean zzaj, @SafeParcelable$Param(id = 4) final String[] array, @SafeParcelable$Param(id = 5) final boolean zzad, @SafeParcelable$Param(id = 6) final String zzae, @SafeParcelable$Param(id = 7) final String zzaf) {
        this.zzu = zzu;
        this.zzah = (CredentialPickerConfig)Preconditions.checkNotNull((Object)credentialPickerConfig);
        this.zzai = zzai;
        this.zzaj = zzaj;
        this.zzaa = (String[])Preconditions.checkNotNull((Object)array);
        if (this.zzu < 2) {
            this.zzad = true;
            this.zzae = null;
            this.zzaf = null;
            return;
        }
        this.zzad = zzad;
        this.zzae = zzae;
        this.zzaf = zzaf;
    }
    
    private HintRequest(final Builder builder) {
        this(2, builder.zzah, builder.zzai, builder.zzaj, builder.zzaa, builder.zzad, builder.zzae, builder.zzaf);
    }
    
    @NonNull
    public final String[] getAccountTypes() {
        return this.zzaa;
    }
    
    @NonNull
    public final CredentialPickerConfig getHintPickerConfig() {
        return this.zzah;
    }
    
    @Nullable
    public final String getIdTokenNonce() {
        return this.zzaf;
    }
    
    @Nullable
    public final String getServerClientId() {
        return this.zzae;
    }
    
    public final boolean isEmailAddressIdentifierSupported() {
        return this.zzai;
    }
    
    public final boolean isIdTokenRequested() {
        return this.zzad;
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeParcelable(parcel, 1, (Parcelable)this.getHintPickerConfig(), n, false);
        SafeParcelWriter.writeBoolean(parcel, 2, this.isEmailAddressIdentifierSupported());
        SafeParcelWriter.writeBoolean(parcel, 3, this.zzaj);
        SafeParcelWriter.writeStringArray(parcel, 4, this.getAccountTypes(), false);
        SafeParcelWriter.writeBoolean(parcel, 5, this.isIdTokenRequested());
        SafeParcelWriter.writeString(parcel, 6, this.getServerClientId(), false);
        SafeParcelWriter.writeString(parcel, 7, this.getIdTokenNonce(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzu);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public static final class Builder
    {
        private String[] zzaa;
        private boolean zzad;
        private String zzae;
        private String zzaf;
        private CredentialPickerConfig zzah;
        private boolean zzai;
        private boolean zzaj;
        
        public Builder() {
            this.zzah = new CredentialPickerConfig.Builder().build();
            this.zzad = false;
        }
        
        public final HintRequest build() {
            if (this.zzaa == null) {
                this.zzaa = new String[0];
            }
            if (!this.zzai && !this.zzaj && this.zzaa.length == 0) {
                throw new IllegalStateException("At least one authentication method must be specified");
            }
            return new HintRequest(this, null);
        }
        
        public final Builder setAccountTypes(final String... array) {
            String[] zzaa = array;
            if (array == null) {
                zzaa = new String[0];
            }
            this.zzaa = zzaa;
            return this;
        }
        
        public final Builder setEmailAddressIdentifierSupported(final boolean zzai) {
            this.zzai = zzai;
            return this;
        }
        
        public final Builder setHintPickerConfig(@NonNull final CredentialPickerConfig credentialPickerConfig) {
            this.zzah = (CredentialPickerConfig)Preconditions.checkNotNull((Object)credentialPickerConfig);
            return this;
        }
        
        public final Builder setIdTokenNonce(@Nullable final String zzaf) {
            this.zzaf = zzaf;
            return this;
        }
        
        public final Builder setIdTokenRequested(final boolean zzad) {
            this.zzad = zzad;
            return this;
        }
        
        public final Builder setPhoneNumberIdentifierSupported(final boolean zzaj) {
            this.zzaj = zzaj;
            return this;
        }
        
        public final Builder setServerClientId(@Nullable final String zzae) {
            this.zzae = zzae;
            return this;
        }
    }
}
