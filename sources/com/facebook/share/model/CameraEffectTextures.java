package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.facebook.internal.Utility;
import java.util.Set;

public class CameraEffectTextures implements ShareModel {
    public static final Creator<CameraEffectTextures> CREATOR = new C03751();
    private final Bundle textures;

    /* renamed from: com.facebook.share.model.CameraEffectTextures$1 */
    static class C03751 implements Creator<CameraEffectTextures> {
        C03751() {
        }

        public CameraEffectTextures createFromParcel(Parcel in) {
            return new CameraEffectTextures(in);
        }

        public CameraEffectTextures[] newArray(int size) {
            return new CameraEffectTextures[size];
        }
    }

    public static class Builder implements ShareModelBuilder<CameraEffectTextures, Builder> {
        private Bundle textures = new Bundle();

        public Builder putTexture(String key, Bitmap texture) {
            return putParcelableTexture(key, texture);
        }

        public Builder putTexture(String key, Uri textureUrl) {
            return putParcelableTexture(key, textureUrl);
        }

        private Builder putParcelableTexture(String key, Parcelable parcelableTexture) {
            if (!(Utility.isNullOrEmpty(key) || parcelableTexture == null)) {
                this.textures.putParcelable(key, parcelableTexture);
            }
            return this;
        }

        public Builder readFrom(CameraEffectTextures model) {
            if (model != null) {
                this.textures.putAll(model.textures);
            }
            return this;
        }

        public Builder readFrom(Parcel parcel) {
            return readFrom((CameraEffectTextures) parcel.readParcelable(CameraEffectTextures.class.getClassLoader()));
        }

        public CameraEffectTextures build() {
            return new CameraEffectTextures();
        }
    }

    private CameraEffectTextures(Builder builder) {
        this.textures = builder.textures;
    }

    CameraEffectTextures(Parcel in) {
        this.textures = in.readBundle(getClass().getClassLoader());
    }

    @Nullable
    public Bitmap getTextureBitmap(String key) {
        Object value = this.textures.get(key);
        if (value instanceof Bitmap) {
            return (Bitmap) value;
        }
        return null;
    }

    @Nullable
    public Uri getTextureUri(String key) {
        Object value = this.textures.get(key);
        if (value instanceof Uri) {
            return (Uri) value;
        }
        return null;
    }

    @Nullable
    public Object get(String key) {
        return this.textures.get(key);
    }

    public Set<String> keySet() {
        return this.textures.keySet();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        out.writeBundle(this.textures);
    }
}
