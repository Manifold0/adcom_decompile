// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import com.facebook.internal.Utility;
import android.os.Parcelable;
import java.util.Set;
import android.net.Uri;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Bundle;
import android.os.Parcelable$Creator;

public class CameraEffectTextures implements ShareModel
{
    public static final Parcelable$Creator<CameraEffectTextures> CREATOR;
    private final Bundle textures;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<CameraEffectTextures>() {
            public CameraEffectTextures createFromParcel(final Parcel parcel) {
                return new CameraEffectTextures(parcel);
            }
            
            public CameraEffectTextures[] newArray(final int n) {
                return new CameraEffectTextures[n];
            }
        };
    }
    
    CameraEffectTextures(final Parcel parcel) {
        this.textures = parcel.readBundle(this.getClass().getClassLoader());
    }
    
    private CameraEffectTextures(final Builder builder) {
        this.textures = builder.textures;
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Object get(final String s) {
        return this.textures.get(s);
    }
    
    @Nullable
    public Bitmap getTextureBitmap(final String s) {
        final Object value = this.textures.get(s);
        if (value instanceof Bitmap) {
            return (Bitmap)value;
        }
        return null;
    }
    
    @Nullable
    public Uri getTextureUri(final String s) {
        final Object value = this.textures.get(s);
        if (value instanceof Uri) {
            return (Uri)value;
        }
        return null;
    }
    
    public Set<String> keySet() {
        return (Set<String>)this.textures.keySet();
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.textures);
    }
    
    public static class Builder implements ShareModelBuilder<CameraEffectTextures, Builder>
    {
        private Bundle textures;
        
        public Builder() {
            this.textures = new Bundle();
        }
        
        private Builder putParcelableTexture(final String s, final Parcelable parcelable) {
            if (!Utility.isNullOrEmpty(s) && parcelable != null) {
                this.textures.putParcelable(s, parcelable);
            }
            return this;
        }
        
        @Override
        public CameraEffectTextures build() {
            return new CameraEffectTextures(this, null);
        }
        
        public Builder putTexture(final String s, final Bitmap bitmap) {
            return this.putParcelableTexture(s, (Parcelable)bitmap);
        }
        
        public Builder putTexture(final String s, final Uri uri) {
            return this.putParcelableTexture(s, (Parcelable)uri);
        }
        
        public Builder readFrom(final Parcel parcel) {
            return this.readFrom((CameraEffectTextures)parcel.readParcelable(CameraEffectTextures.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final CameraEffectTextures cameraEffectTextures) {
            if (cameraEffectTextures != null) {
                this.textures.putAll(cameraEffectTextures.textures);
            }
            return this;
        }
    }
}
