package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public class ShareCameraEffectContent extends ShareContent<ShareCameraEffectContent, Builder> {
    public static final Creator<ShareCameraEffectContent> CREATOR = new C03771();
    private CameraEffectArguments arguments;
    private String effectId;
    private CameraEffectTextures textures;

    /* renamed from: com.facebook.share.model.ShareCameraEffectContent$1 */
    static class C03771 implements Creator<ShareCameraEffectContent> {
        C03771() {
        }

        public ShareCameraEffectContent createFromParcel(Parcel in) {
            return new ShareCameraEffectContent(in);
        }

        public ShareCameraEffectContent[] newArray(int size) {
            return new ShareCameraEffectContent[size];
        }
    }

    public static final class Builder extends com.facebook.share.model.ShareContent.Builder<ShareCameraEffectContent, Builder> {
        private CameraEffectArguments arguments;
        private String effectId;
        private CameraEffectTextures textures;

        public Builder setEffectId(String effectId) {
            this.effectId = effectId;
            return this;
        }

        public Builder setArguments(CameraEffectArguments arguments) {
            this.arguments = arguments;
            return this;
        }

        public Builder setTextures(CameraEffectTextures textures) {
            this.textures = textures;
            return this;
        }

        public ShareCameraEffectContent build() {
            return new ShareCameraEffectContent();
        }

        public Builder readFrom(ShareCameraEffectContent model) {
            if (model == null) {
                return this;
            }
            return ((Builder) super.readFrom((ShareContent) model)).setEffectId(this.effectId).setArguments(this.arguments);
        }
    }

    private ShareCameraEffectContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.effectId = builder.effectId;
        this.arguments = builder.arguments;
        this.textures = builder.textures;
    }

    ShareCameraEffectContent(Parcel in) {
        super(in);
        this.effectId = in.readString();
        this.arguments = new com.facebook.share.model.CameraEffectArguments.Builder().readFrom(in).build();
        this.textures = new com.facebook.share.model.CameraEffectTextures.Builder().readFrom(in).build();
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeString(this.effectId);
        out.writeParcelable(this.arguments, 0);
        out.writeParcelable(this.textures, 0);
    }

    public String getEffectId() {
        return this.effectId;
    }

    public CameraEffectArguments getArguments() {
        return this.arguments;
    }

    public CameraEffectTextures getTextures() {
        return this.textures;
    }
}
