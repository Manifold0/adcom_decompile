// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;

public class ShareHashtag implements ShareModel
{
    public static final Parcelable$Creator<ShareHashtag> CREATOR;
    private final String hashtag;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareHashtag>() {
            public ShareHashtag createFromParcel(final Parcel parcel) {
                return new ShareHashtag(parcel);
            }
            
            public ShareHashtag[] newArray(final int n) {
                return new ShareHashtag[n];
            }
        };
    }
    
    ShareHashtag(final Parcel parcel) {
        this.hashtag = parcel.readString();
    }
    
    private ShareHashtag(final Builder builder) {
        this.hashtag = builder.hashtag;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getHashtag() {
        return this.hashtag;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.hashtag);
    }
    
    public static class Builder implements ShareModelBuilder<ShareHashtag, Builder>
    {
        private String hashtag;
        
        @Override
        public ShareHashtag build() {
            return new ShareHashtag(this, null);
        }
        
        public String getHashtag() {
            return this.hashtag;
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((ShareHashtag)parcel.readParcelable(ShareHashtag.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final ShareHashtag shareHashtag) {
            if (shareHashtag == null) {
                return this;
            }
            return this.setHashtag(shareHashtag.getHashtag());
        }
        
        public Builder setHashtag(final String hashtag) {
            this.hashtag = hashtag;
            return this;
        }
    }
}
