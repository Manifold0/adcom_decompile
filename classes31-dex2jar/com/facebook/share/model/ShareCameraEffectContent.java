// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ShareCameraEffectContent extends ShareContent<ShareCameraEffectContent, Builder>
{
    public static final Parcelable$Creator<ShareCameraEffectContent> CREATOR;
    private CameraEffectArguments arguments;
    private String effectId;
    private CameraEffectTextures textures;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareCameraEffectContent>() {
            public ShareCameraEffectContent createFromParcel(final Parcel parcel) {
                return new ShareCameraEffectContent(parcel);
            }
            
            public ShareCameraEffectContent[] newArray(final int n) {
                return new ShareCameraEffectContent[n];
            }
        };
    }
    
    ShareCameraEffectContent(final Parcel parcel) {
        super(parcel);
        this.effectId = parcel.readString();
        this.arguments = new CameraEffectArguments.Builder().readFrom(parcel).build();
        this.textures = new CameraEffectTextures.Builder().readFrom(parcel).build();
    }
    
    private ShareCameraEffectContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.effectId = builder.effectId;
        this.arguments = builder.arguments;
        this.textures = builder.textures;
    }
    
    public CameraEffectArguments getArguments() {
        return this.arguments;
    }
    
    public String getEffectId() {
        return this.effectId;
    }
    
    public CameraEffectTextures getTextures() {
        return this.textures;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.effectId);
        parcel.writeParcelable((Parcelable)this.arguments, 0);
        parcel.writeParcelable((Parcelable)this.textures, 0);
    }
    
    public static final class Builder extends ShareContent.Builder<ShareCameraEffectContent, Builder>
    {
        private CameraEffectArguments arguments;
        private String effectId;
        private CameraEffectTextures textures;
        
        @Override
        public ShareCameraEffectContent build() {
            return new ShareCameraEffectContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareCameraEffectContent shareCameraEffectContent) {
            if (shareCameraEffectContent == null) {
                return this;
            }
            return super.readFrom(shareCameraEffectContent).setEffectId(this.effectId).setArguments(this.arguments);
        }
        
        public Builder setArguments(final CameraEffectArguments arguments) {
            this.arguments = arguments;
            return this;
        }
        
        public Builder setEffectId(final String effectId) {
            this.effectId = effectId;
            return this;
        }
        
        public Builder setTextures(final CameraEffectTextures textures) {
            this.textures = textures;
            return this;
        }
    }
}
