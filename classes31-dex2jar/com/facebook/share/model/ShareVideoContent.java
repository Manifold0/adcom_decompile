// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ShareVideoContent extends ShareContent<ShareVideoContent, Builder> implements ShareModel
{
    public static final Parcelable$Creator<ShareVideoContent> CREATOR;
    private final String contentDescription;
    private final String contentTitle;
    private final SharePhoto previewPhoto;
    private final ShareVideo video;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareVideoContent>() {
            public ShareVideoContent createFromParcel(final Parcel parcel) {
                return new ShareVideoContent(parcel);
            }
            
            public ShareVideoContent[] newArray(final int n) {
                return new ShareVideoContent[n];
            }
        };
    }
    
    ShareVideoContent(final Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        final SharePhoto.Builder from = new SharePhoto.Builder().readFrom(parcel);
        if (from.getImageUrl() != null || from.getBitmap() != null) {
            this.previewPhoto = from.build();
        }
        else {
            this.previewPhoto = null;
        }
        this.video = new ShareVideo.Builder().readFrom(parcel).build();
    }
    
    private ShareVideoContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.contentDescription = builder.contentDescription;
        this.contentTitle = builder.contentTitle;
        this.previewPhoto = builder.previewPhoto;
        this.video = builder.video;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public String getContentDescription() {
        return this.contentDescription;
    }
    
    @Nullable
    public String getContentTitle() {
        return this.contentTitle;
    }
    
    @Nullable
    public SharePhoto getPreviewPhoto() {
        return this.previewPhoto;
    }
    
    @Nullable
    public ShareVideo getVideo() {
        return this.video;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable((Parcelable)this.previewPhoto, 0);
        parcel.writeParcelable((Parcelable)this.video, 0);
    }
    
    public static final class Builder extends ShareContent.Builder<ShareVideoContent, Builder>
    {
        private String contentDescription;
        private String contentTitle;
        private SharePhoto previewPhoto;
        private ShareVideo video;
        
        @Override
        public ShareVideoContent build() {
            return new ShareVideoContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareVideoContent shareVideoContent) {
            if (shareVideoContent == null) {
                return this;
            }
            return super.readFrom(shareVideoContent).setContentDescription(shareVideoContent.getContentDescription()).setContentTitle(shareVideoContent.getContentTitle()).setPreviewPhoto(shareVideoContent.getPreviewPhoto()).setVideo(shareVideoContent.getVideo());
        }
        
        public Builder setContentDescription(@Nullable final String contentDescription) {
            this.contentDescription = contentDescription;
            return this;
        }
        
        public Builder setContentTitle(@Nullable final String contentTitle) {
            this.contentTitle = contentTitle;
            return this;
        }
        
        public Builder setPreviewPhoto(@Nullable SharePhoto build) {
            if (build == null) {
                build = null;
            }
            else {
                build = new SharePhoto.Builder().readFrom(build).build();
            }
            this.previewPhoto = build;
            return this;
        }
        
        public Builder setVideo(@Nullable final ShareVideo shareVideo) {
            if (shareVideo == null) {
                return this;
            }
            this.video = new ShareVideo.Builder().readFrom(shareVideo).build();
            return this;
        }
    }
}
