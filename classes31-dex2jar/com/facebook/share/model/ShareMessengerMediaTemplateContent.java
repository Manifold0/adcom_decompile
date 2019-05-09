// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import java.io.Serializable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareMessengerMediaTemplateContent extends ShareContent<ShareMessengerMediaTemplateContent, Builder>
{
    public static final Parcelable$Creator<ShareMessengerMediaTemplateContent> CREATOR;
    private final String attachmentId;
    private final ShareMessengerActionButton button;
    private final MediaType mediaType;
    private final Uri mediaUrl;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMessengerMediaTemplateContent>() {
            public ShareMessengerMediaTemplateContent createFromParcel(final Parcel parcel) {
                return new ShareMessengerMediaTemplateContent(parcel);
            }
            
            public ShareMessengerMediaTemplateContent[] newArray(final int n) {
                return new ShareMessengerMediaTemplateContent[n];
            }
        };
    }
    
    ShareMessengerMediaTemplateContent(final Parcel parcel) {
        super(parcel);
        this.mediaType = (MediaType)parcel.readSerializable();
        this.attachmentId = parcel.readString();
        this.mediaUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton)parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }
    
    private ShareMessengerMediaTemplateContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.mediaType = builder.mediaType;
        this.attachmentId = builder.attachmentId;
        this.mediaUrl = builder.mediaUrl;
        this.button = builder.button;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getAttachmentId() {
        return this.attachmentId;
    }
    
    public ShareMessengerActionButton getButton() {
        return this.button;
    }
    
    public MediaType getMediaType() {
        return this.mediaType;
    }
    
    public Uri getMediaUrl() {
        return this.mediaUrl;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeSerializable((Serializable)this.mediaType);
        parcel.writeString(this.attachmentId);
        parcel.writeParcelable((Parcelable)this.mediaUrl, n);
        parcel.writeParcelable((Parcelable)this.button, n);
    }
    
    public static class Builder extends ShareContent.Builder<ShareMessengerMediaTemplateContent, Builder>
    {
        private String attachmentId;
        private ShareMessengerActionButton button;
        private MediaType mediaType;
        private Uri mediaUrl;
        
        @Override
        public ShareMessengerMediaTemplateContent build() {
            return new ShareMessengerMediaTemplateContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareMessengerMediaTemplateContent shareMessengerMediaTemplateContent) {
            if (shareMessengerMediaTemplateContent == null) {
                return this;
            }
            return super.readFrom(shareMessengerMediaTemplateContent).setMediaType(shareMessengerMediaTemplateContent.getMediaType()).setAttachmentId(shareMessengerMediaTemplateContent.getAttachmentId()).setMediaUrl(shareMessengerMediaTemplateContent.getMediaUrl()).setButton(shareMessengerMediaTemplateContent.getButton());
        }
        
        public Builder setAttachmentId(final String attachmentId) {
            this.attachmentId = attachmentId;
            return this;
        }
        
        public Builder setButton(final ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }
        
        public Builder setMediaType(final MediaType mediaType) {
            this.mediaType = mediaType;
            return this;
        }
        
        public Builder setMediaUrl(final Uri mediaUrl) {
            this.mediaUrl = mediaUrl;
            return this;
        }
    }
    
    public enum MediaType
    {
        IMAGE, 
        VIDEO;
    }
}
