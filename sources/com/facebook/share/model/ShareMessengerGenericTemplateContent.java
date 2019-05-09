package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerGenericTemplateContent extends ShareContent<ShareMessengerGenericTemplateContent, Builder> {
    public static final Creator<ShareMessengerGenericTemplateContent> CREATOR = new C03811();
    private final ShareMessengerGenericTemplateElement genericTemplateElement;
    private final ImageAspectRatio imageAspectRatio;
    private final boolean isSharable;

    /* renamed from: com.facebook.share.model.ShareMessengerGenericTemplateContent$1 */
    static class C03811 implements Creator<ShareMessengerGenericTemplateContent> {
        C03811() {
        }

        public ShareMessengerGenericTemplateContent createFromParcel(Parcel source) {
            return new ShareMessengerGenericTemplateContent(source);
        }

        public ShareMessengerGenericTemplateContent[] newArray(int size) {
            return new ShareMessengerGenericTemplateContent[size];
        }
    }

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<ShareMessengerGenericTemplateContent, Builder> {
        private ShareMessengerGenericTemplateElement genericTemplateElement;
        private ImageAspectRatio imageAspectRatio;
        private boolean isSharable;

        public Builder setIsSharable(boolean isSharable) {
            this.isSharable = isSharable;
            return this;
        }

        public Builder setImageAspectRatio(ImageAspectRatio imageAspectRatio) {
            this.imageAspectRatio = imageAspectRatio;
            return this;
        }

        public Builder setGenericTemplateElement(ShareMessengerGenericTemplateElement genericTemplateElement) {
            this.genericTemplateElement = genericTemplateElement;
            return this;
        }

        public ShareMessengerGenericTemplateContent build() {
            return new ShareMessengerGenericTemplateContent(this);
        }

        public Builder readFrom(ShareMessengerGenericTemplateContent model) {
            if (model == null) {
                return this;
            }
            return ((Builder) super.readFrom((ShareContent) model)).setIsSharable(model.getIsSharable()).setImageAspectRatio(model.getImageAspectRatio()).setGenericTemplateElement(model.getGenericTemplateElement());
        }
    }

    public enum ImageAspectRatio {
        HORIZONTAL,
        SQUARE
    }

    protected ShareMessengerGenericTemplateContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.isSharable = builder.isSharable;
        this.imageAspectRatio = builder.imageAspectRatio;
        this.genericTemplateElement = builder.genericTemplateElement;
    }

    ShareMessengerGenericTemplateContent(Parcel in) {
        super(in);
        this.isSharable = in.readByte() != (byte) 0;
        this.imageAspectRatio = (ImageAspectRatio) in.readSerializable();
        this.genericTemplateElement = (ShareMessengerGenericTemplateElement) in.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader());
    }

    public boolean getIsSharable() {
        return this.isSharable;
    }

    public ImageAspectRatio getImageAspectRatio() {
        return this.imageAspectRatio;
    }

    public ShareMessengerGenericTemplateElement getGenericTemplateElement() {
        return this.genericTemplateElement;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel out, int flags) {
        super.writeToParcel(out, flags);
        out.writeByte((byte) (this.isSharable ? 1 : 0));
        out.writeSerializable(this.imageAspectRatio);
        out.writeParcelable(this.genericTemplateElement, flags);
    }
}
