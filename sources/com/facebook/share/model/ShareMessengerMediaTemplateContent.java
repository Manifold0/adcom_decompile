package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class ShareMessengerMediaTemplateContent extends ShareContent<ShareMessengerMediaTemplateContent, Builder> {
    public static final Creator<ShareMessengerMediaTemplateContent> CREATOR = new C03831();
    private final String attachmentId;
    private final ShareMessengerActionButton button;
    private final MediaType mediaType;
    private final Uri mediaUrl;

    /* renamed from: com.facebook.share.model.ShareMessengerMediaTemplateContent$1 */
    static class C03831 implements Creator<ShareMessengerMediaTemplateContent> {
        C03831() {
        }

        public ShareMessengerMediaTemplateContent createFromParcel(Parcel in) {
            return new ShareMessengerMediaTemplateContent(in);
        }

        public ShareMessengerMediaTemplateContent[] newArray(int size) {
            return new ShareMessengerMediaTemplateContent[size];
        }
    }

    public static class Builder extends com.facebook.share.model.ShareContent.Builder<ShareMessengerMediaTemplateContent, Builder> {
        private String attachmentId;
        private ShareMessengerActionButton button;
        private MediaType mediaType;
        private Uri mediaUrl;

        public Builder setMediaType(MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }

        public Builder setAttachmentId(String attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }

        public Builder setMediaUrl(Uri mediaUrl) {
            this.mediaUrl = mediaUrl;
            return this;
        }

        public Builder setButton(ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }

        public Builder readFrom(ShareMessengerMediaTemplateContent content) {
            if (content == null) {
                return this;
            }
            return ((Builder) super.readFrom((ShareContent) content)).setMediaType(content.getMediaType()).setAttachmentId(content.getAttachmentId()).setMediaUrl(content.getMediaUrl()).setButton(content.getButton());
        }

        public ShareMessengerMediaTemplateContent build() {
            return new ShareMessengerMediaTemplateContent();
        }
    }

    public enum MediaType {
        IMAGE,
        VIDEO
    }

    private ShareMessengerMediaTemplateContent(Builder builder) {
        super((com.facebook.share.model.ShareContent.Builder) builder);
        this.mediaType = builder.mediaType;
        this.attachmentId = builder.attachmentId;
        this.mediaUrl = builder.mediaUrl;
        this.button = builder.button;
    }

    ShareMessengerMediaTemplateContent(Parcel in) {
        super(in);
        this.mediaType = (MediaType) in.readSerializable();
        this.attachmentId = in.readString();
        this.mediaUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton) in.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }

    public MediaType getMediaType() {
        return this.mediaType;
    }

    public String getAttachmentId() {
        return this.attachmentId;
    }

    public Uri getMediaUrl() {
        return this.mediaUrl;
    }

    public ShareMessengerActionButton getButton() {
        return this.button;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeSerializable(this.mediaType);
        dest.writeString(this.attachmentId);
        dest.writeParcelable(this.mediaUrl, flags);
        dest.writeParcelable(this.button, flags);
    }
}
