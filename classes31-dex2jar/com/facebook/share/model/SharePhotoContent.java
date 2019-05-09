// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Iterator;
import java.util.ArrayList;
import android.support.annotation.Nullable;
import java.util.Collections;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;

public final class SharePhotoContent extends ShareContent<SharePhotoContent, Builder>
{
    public static final Parcelable$Creator<SharePhotoContent> CREATOR;
    private final List<SharePhoto> photos;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SharePhotoContent>() {
            public SharePhotoContent createFromParcel(final Parcel parcel) {
                return new SharePhotoContent(parcel);
            }
            
            public SharePhotoContent[] newArray(final int n) {
                return new SharePhotoContent[n];
            }
        };
    }
    
    SharePhotoContent(final Parcel parcel) {
        super(parcel);
        this.photos = Collections.unmodifiableList((List<? extends SharePhoto>)SharePhoto.Builder.readPhotoListFrom(parcel));
    }
    
    private SharePhotoContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.photos = Collections.unmodifiableList((List<? extends SharePhoto>)builder.photos);
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public List<SharePhoto> getPhotos() {
        return this.photos;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        SharePhoto.Builder.writePhotoListTo(parcel, n, this.photos);
    }
    
    public static class Builder extends ShareContent.Builder<SharePhotoContent, Builder>
    {
        private final List<SharePhoto> photos;
        
        public Builder() {
            this.photos = new ArrayList<SharePhoto>();
        }
        
        public Builder addPhoto(@Nullable final SharePhoto sharePhoto) {
            if (sharePhoto != null) {
                this.photos.add(new SharePhoto.Builder().readFrom(sharePhoto).build());
            }
            return this;
        }
        
        public Builder addPhotos(@Nullable final List<SharePhoto> list) {
            if (list != null) {
                final Iterator<SharePhoto> iterator = list.iterator();
                while (iterator.hasNext()) {
                    this.addPhoto(iterator.next());
                }
            }
            return this;
        }
        
        @Override
        public SharePhotoContent build() {
            return new SharePhotoContent(this, null);
        }
        
        @Override
        public Builder readFrom(final SharePhotoContent sharePhotoContent) {
            if (sharePhotoContent == null) {
                return this;
            }
            return super.readFrom(sharePhotoContent).addPhotos(sharePhotoContent.getPhotos());
        }
        
        public Builder setPhotos(@Nullable final List<SharePhoto> list) {
            this.photos.clear();
            this.addPhotos(list);
            return this;
        }
    }
}
