package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerOpenGraphMusicTemplateContent extends ShareContent<ShareMessengerOpenGraphMusicTemplateContent, Builder> {
    public static final Creator<ShareMessengerOpenGraphMusicTemplateContent> CREATOR = new C03841();
    private final ShareMessengerActionButton button;
    private final Uri url;

    /* renamed from: com.facebook.share.model.ShareMessengerOpenGraphMusicTemplateContent$1 */
    static class C03841 implements Creator<ShareMessengerOpenGraphMusicTemplateContent> {
        C03841() {
        }

        public ShareMessengerOpenGraphMusicTemplateContent createFromParcel(Parcel in) {
            return new ShareMessengerOpenGraphMusicTemplateContent(in);
        }

        public ShareMessengerOpenGraphMusicTemplateContent[] newArray(int size) {
            return new ShareMessengerOpenGraphMusicTemplateContent[size];
        }
    }

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<ShareMessengerOpenGraphMusicTemplateContent, Builder> {
        private ShareMessengerActionButton button;
        private Uri url;

        public Builder setUrl(Uri url) {
            this.url = url;
            return this;
        }

        public Builder setButton(ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }

        public Builder readFrom(ShareMessengerOpenGraphMusicTemplateContent content) {
            if (content == null) {
                return this;
            }
            return ((Builder) super.readFrom((ShareContent) content)).setUrl(content.getUrl()).setButton(content.getButton());
        }

        public ShareMessengerOpenGraphMusicTemplateContent build() {
            return new ShareMessengerOpenGraphMusicTemplateContent();
        }
    }

    private ShareMessengerOpenGraphMusicTemplateContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.url = builder.url;
        this.button = builder.button;
    }

    ShareMessengerOpenGraphMusicTemplateContent(Parcel in) {
        super(in);
        this.url = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton) in.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public Uri getUrl() {
        return this.url;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.url, flags);
        dest.writeParcelable(this.button, flags);
    }
}
