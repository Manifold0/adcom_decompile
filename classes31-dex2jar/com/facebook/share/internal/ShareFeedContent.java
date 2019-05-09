// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.ShareModelBuilder;
import com.facebook.share.model.ShareModel;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.facebook.share.model.ShareContent;

public class ShareFeedContent extends ShareContent<ShareFeedContent, Builder>
{
    public static final Parcelable$Creator<ShareFeedContent> CREATOR;
    private final String link;
    private final String linkCaption;
    private final String linkDescription;
    private final String linkName;
    private final String mediaSource;
    private final String picture;
    private final String toId;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareFeedContent>() {
            public ShareFeedContent createFromParcel(final Parcel parcel) {
                return new ShareFeedContent(parcel);
            }
            
            public ShareFeedContent[] newArray(final int n) {
                return new ShareFeedContent[n];
            }
        };
    }
    
    ShareFeedContent(final Parcel parcel) {
        super(parcel);
        this.toId = parcel.readString();
        this.link = parcel.readString();
        this.linkName = parcel.readString();
        this.linkCaption = parcel.readString();
        this.linkDescription = parcel.readString();
        this.picture = parcel.readString();
        this.mediaSource = parcel.readString();
    }
    
    private ShareFeedContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.toId = builder.toId;
        this.link = builder.link;
        this.linkName = builder.linkName;
        this.linkCaption = builder.linkCaption;
        this.linkDescription = builder.linkDescription;
        this.picture = builder.picture;
        this.mediaSource = builder.mediaSource;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public String getLink() {
        return this.link;
    }
    
    public String getLinkCaption() {
        return this.linkCaption;
    }
    
    public String getLinkDescription() {
        return this.linkDescription;
    }
    
    public String getLinkName() {
        return this.linkName;
    }
    
    public String getMediaSource() {
        return this.mediaSource;
    }
    
    public String getPicture() {
        return this.picture;
    }
    
    public String getToId() {
        return this.toId;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.toId);
        parcel.writeString(this.link);
        parcel.writeString(this.linkName);
        parcel.writeString(this.linkCaption);
        parcel.writeString(this.linkDescription);
        parcel.writeString(this.picture);
        parcel.writeString(this.mediaSource);
    }
    
    public static final class Builder extends ShareContent.Builder<ShareFeedContent, Builder>
    {
        private String link;
        private String linkCaption;
        private String linkDescription;
        private String linkName;
        private String mediaSource;
        private String picture;
        private String toId;
        
        @Override
        public ShareFeedContent build() {
            return new ShareFeedContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareFeedContent shareFeedContent) {
            if (shareFeedContent == null) {
                return this;
            }
            return super.readFrom(shareFeedContent).setToId(shareFeedContent.getToId()).setLink(shareFeedContent.getLink()).setLinkName(shareFeedContent.getLinkName()).setLinkCaption(shareFeedContent.getLinkCaption()).setLinkDescription(shareFeedContent.getLinkDescription()).setPicture(shareFeedContent.getPicture()).setMediaSource(shareFeedContent.getMediaSource());
        }
        
        public Builder setLink(final String link) {
            this.link = link;
            return this;
        }
        
        public Builder setLinkCaption(final String linkCaption) {
            this.linkCaption = linkCaption;
            return this;
        }
        
        public Builder setLinkDescription(final String linkDescription) {
            this.linkDescription = linkDescription;
            return this;
        }
        
        public Builder setLinkName(final String linkName) {
            this.linkName = linkName;
            return this;
        }
        
        public Builder setMediaSource(final String mediaSource) {
            this.mediaSource = mediaSource;
            return this;
        }
        
        public Builder setPicture(final String picture) {
            this.picture = picture;
            return this;
        }
        
        public Builder setToId(final String toId) {
            this.toId = toId;
            return this;
        }
    }
}
