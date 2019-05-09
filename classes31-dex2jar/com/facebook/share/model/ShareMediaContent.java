// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Iterator;
import java.util.ArrayList;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.Arrays;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;

public final class ShareMediaContent extends ShareContent<ShareMediaContent, Builder>
{
    public static final Parcelable$Creator<ShareMediaContent> CREATOR;
    private final List<ShareMedia> media;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMediaContent>() {
            public ShareMediaContent createFromParcel(final Parcel parcel) {
                return new ShareMediaContent(parcel);
            }
            
            public ShareMediaContent[] newArray(final int n) {
                return new ShareMediaContent[n];
            }
        };
    }
    
    ShareMediaContent(final Parcel parcel) {
        super(parcel);
        this.media = Arrays.asList((ShareMedia[])parcel.readParcelableArray(ShareMedia.class.getClassLoader()));
    }
    
    private ShareMediaContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.media = Collections.unmodifiableList((List<? extends ShareMedia>)builder.media);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public List<ShareMedia> getMedia() {
        return this.media;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelableArray((Parcelable[])this.media.toArray(), n);
    }
    
    public static class Builder extends ShareContent.Builder<ShareMediaContent, Builder>
    {
        private final List<ShareMedia> media;
        
        public Builder() {
            this.media = new ArrayList<ShareMedia>();
        }
        
        public Builder addMedia(@Nullable final List<ShareMedia> list) {
            if (list != null) {
                final Iterator<ShareMedia> iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.addMedium(iterator.next());
                }
            }
            return this;
        }
        
        public Builder addMedium(@Nullable final ShareMedia shareMedia) {
            if (shareMedia != null) {
                ShareMedia shareMedia2;
                if (shareMedia instanceof SharePhoto) {
                    shareMedia2 = new SharePhoto.Builder().readFrom((SharePhoto)shareMedia).build();
                }
                else {
                    if (!(shareMedia instanceof ShareVideo)) {
                        throw new IllegalArgumentException("medium must be either a SharePhoto or ShareVideo");
                    }
                    shareMedia2 = new ShareVideo.Builder().readFrom((ShareVideo)shareMedia).build();
                }
                this.media.add(shareMedia2);
            }
            return this;
        }
        
        @Override
        public ShareMediaContent build() {
            return new ShareMediaContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareMediaContent shareMediaContent) {
            if (shareMediaContent == null) {
                return this;
            }
            return super.readFrom(shareMediaContent).addMedia(shareMediaContent.getMedia());
        }
        
        public Builder setMedia(@Nullable final List<ShareMedia> list) {
            this.media.clear();
            this.addMedia(list);
            return this;
        }
    }
}
