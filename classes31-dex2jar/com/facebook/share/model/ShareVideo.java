// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareVideo extends ShareMedia
{
    public static final Parcelable$Creator<ShareVideo> CREATOR;
    private final Uri localUrl;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareVideo>() {
            public ShareVideo createFromParcel(final Parcel parcel) {
                return new ShareVideo(parcel);
            }
            
            public ShareVideo[] newArray(final int n) {
                return new ShareVideo[n];
            }
        };
    }
    
    ShareVideo(final Parcel parcel) {
        super(parcel);
        this.localUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
    }
    
    private ShareVideo(final Builder builder) {
        super((ShareMedia.Builder)builder);
        this.localUrl = builder.localUrl;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Uri getLocalUrl() {
        return this.localUrl;
    }
    
    @Override
    public Type getMediaType() {
        return Type.VIDEO;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable((Parcelable)this.localUrl, 0);
    }
    
    public static final class Builder extends ShareMedia.Builder<ShareVideo, Builder>
    {
        private Uri localUrl;
        
        @Override
        public ShareVideo build() {
            return new ShareVideo(this, null);
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((ShareVideo)parcel.readParcelable(ShareVideo.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final ShareVideo shareVideo) {
            if (shareVideo == null) {
                return this;
            }
            return super.readFrom(shareVideo).setLocalUrl(shareVideo.getLocalUrl());
        }
        
        public Builder setLocalUrl(@Nullable final Uri localUrl) {
            this.localUrl = localUrl;
            return this;
        }
    }
}
