// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.util.Log;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareLinkContent extends ShareContent<ShareLinkContent, Builder>
{
    public static final Parcelable$Creator<ShareLinkContent> CREATOR;
    @Deprecated
    private final String contentDescription;
    @Deprecated
    private final String contentTitle;
    @Deprecated
    private final Uri imageUrl;
    private final String quote;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareLinkContent>() {
            public ShareLinkContent createFromParcel(final Parcel parcel) {
                return new ShareLinkContent(parcel);
            }
            
            public ShareLinkContent[] newArray(final int n) {
                return new ShareLinkContent[n];
            }
        };
    }
    
    ShareLinkContent(final Parcel parcel) {
        super(parcel);
        this.contentDescription = parcel.readString();
        this.contentTitle = parcel.readString();
        this.imageUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.quote = parcel.readString();
    }
    
    private ShareLinkContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.contentDescription = builder.contentDescription;
        this.contentTitle = builder.contentTitle;
        this.imageUrl = builder.imageUrl;
        this.quote = builder.quote;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Deprecated
    public String getContentDescription() {
        return this.contentDescription;
    }
    
    @Deprecated
    @Nullable
    public String getContentTitle() {
        return this.contentTitle;
    }
    
    @Deprecated
    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }
    
    @Nullable
    public String getQuote() {
        return this.quote;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeString(this.contentDescription);
        parcel.writeString(this.contentTitle);
        parcel.writeParcelable((Parcelable)this.imageUrl, 0);
        parcel.writeString(this.quote);
    }
    
    public static final class Builder extends ShareContent.Builder<ShareLinkContent, Builder>
    {
        static final String TAG;
        @Deprecated
        private String contentDescription;
        @Deprecated
        private String contentTitle;
        @Deprecated
        private Uri imageUrl;
        private String quote;
        
        static {
            TAG = Builder.class.getSimpleName();
        }
        
        @Override
        public ShareLinkContent build() {
            return new ShareLinkContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareLinkContent shareLinkContent) {
            if (shareLinkContent == null) {
                return this;
            }
            return super.readFrom(shareLinkContent).setContentDescription(shareLinkContent.getContentDescription()).setImageUrl(shareLinkContent.getImageUrl()).setContentTitle(shareLinkContent.getContentTitle()).setQuote(shareLinkContent.getQuote());
        }
        
        @Deprecated
        public Builder setContentDescription(@Nullable final String s) {
            Log.w(Builder.TAG, "This method does nothing. ContentDescription is deprecated in Graph API 2.9.");
            return this;
        }
        
        @Deprecated
        public Builder setContentTitle(@Nullable final String s) {
            Log.w(Builder.TAG, "This method does nothing. ContentTitle is deprecated in Graph API 2.9.");
            return this;
        }
        
        @Deprecated
        public Builder setImageUrl(@Nullable final Uri uri) {
            Log.w(Builder.TAG, "This method does nothing. ImageUrl is deprecated in Graph API 2.9.");
            return this;
        }
        
        public Builder setQuote(@Nullable final String quote) {
            this.quote = quote;
            return this;
        }
    }
}
