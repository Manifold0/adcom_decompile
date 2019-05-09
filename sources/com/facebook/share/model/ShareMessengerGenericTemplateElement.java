package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerGenericTemplateElement implements ShareModel {
    public static final Creator<ShareMessengerGenericTemplateElement> CREATOR = new C03821();
    private final ShareMessengerActionButton button;
    private final ShareMessengerActionButton defaultAction;
    private final Uri imageUrl;
    private final String subtitle;
    private final String title;

    /* renamed from: com.facebook.share.model.ShareMessengerGenericTemplateElement$1 */
    static class C03821 implements Creator<ShareMessengerGenericTemplateElement> {
        C03821() {
        }

        public ShareMessengerGenericTemplateElement createFromParcel(Parcel in) {
            return new ShareMessengerGenericTemplateElement(in);
        }

        public ShareMessengerGenericTemplateElement[] newArray(int size) {
            return new ShareMessengerGenericTemplateElement[size];
        }
    }

    public static class Builder implements ShareModelBuilder<ShareMessengerGenericTemplateElement, Builder> {
        private ShareMessengerActionButton button;
        private ShareMessengerActionButton defaultAction;
        private Uri imageUrl;
        private String subtitle;
        private String title;

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setSubtitle(String subtitle) {
            this.subtitle = subtitle;
            return this;
        }

        public Builder setImageUrl(Uri imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Builder setDefaultAction(ShareMessengerActionButton defaultAction) {
            this.defaultAction = defaultAction;
            return this;
        }

        public Builder setButton(ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }

        public ShareMessengerGenericTemplateElement build() {
            return new ShareMessengerGenericTemplateElement();
        }

        public Builder readFrom(ShareMessengerGenericTemplateElement model) {
            if (model == null) {
                return this;
            }
            return setTitle(model.title).setSubtitle(model.subtitle).setImageUrl(model.imageUrl).setDefaultAction(model.defaultAction).setButton(model.button);
        }

        Builder readFrom(Parcel parcel) {
            return readFrom((ShareMessengerGenericTemplateElement) parcel.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader()));
        }
    }

    private ShareMessengerGenericTemplateElement(Builder builder) {
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.imageUrl = builder.imageUrl;
        this.defaultAction = builder.defaultAction;
        this.button = builder.button;
    }

    ShareMessengerGenericTemplateElement(Parcel in) {
        this.title = in.readString();
        this.subtitle = in.readString();
        this.imageUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.defaultAction = (ShareMessengerActionButton) in.readParcelable(ShareMessengerActionButton.class.getClassLoader());
        this.button = (ShareMessengerActionButton) in.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public String getTitle() {
        return this.title;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public Uri getImageUrl() {
        return this.imageUrl;
    }

    public ShareMessengerActionButton getDefaultAction() {
        return this.defaultAction;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.subtitle);
        dest.writeParcelable(this.imageUrl, flags);
        dest.writeParcelable(this.defaultAction, flags);
        dest.writeParcelable(this.button, flags);
    }
}
