// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Set;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;

public class CameraEffectArguments implements ShareModel
{
    public static final Parcelable$Creator<CameraEffectArguments> CREATOR;
    private final Bundle params;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<CameraEffectArguments>() {
            public CameraEffectArguments createFromParcel(final Parcel parcel) {
                return new CameraEffectArguments(parcel);
            }
            
            public CameraEffectArguments[] newArray(final int n) {
                return new CameraEffectArguments[n];
            }
        };
    }
    
    CameraEffectArguments(final Parcel parcel) {
        this.params = parcel.readBundle(this.getClass().getClassLoader());
    }
    
    private CameraEffectArguments(final Builder builder) {
        this.params = builder.params;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Object get(final String s) {
        return this.params.get(s);
    }
    
    @Nullable
    public String getString(final String s) {
        return this.params.getString(s);
    }
    
    @Nullable
    public String[] getStringArray(final String s) {
        return this.params.getStringArray(s);
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.params.keySet();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.params);
    }
    
    public static class Builder implements ShareModelBuilder<CameraEffectArguments, Builder>
    {
        private Bundle params;
        
        public Builder() {
            this.params = new Bundle();
        }
        
        @Override
        public CameraEffectArguments build() {
            return new CameraEffectArguments(this, null);
        }
        
        public Builder putArgument(final String s, final String s2) {
            this.params.putString(s, s2);
            return this;
        }
        
        public Builder putArgument(final String s, final String[] array) {
            this.params.putStringArray(s, array);
            return this;
        }
        
        public Builder readFrom(final Parcel parcel) {
            return this.readFrom((CameraEffectArguments)parcel.readParcelable(CameraEffectArguments.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final CameraEffectArguments cameraEffectArguments) {
            if (cameraEffectArguments != null) {
                this.params.putAll(cameraEffectArguments.params);
            }
            return this;
        }
    }
}
