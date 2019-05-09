// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.net.Uri;
import android.graphics.Bitmap;
import android.os.Parcelable$Creator;

public final class SharePhoto extends ShareMedia
{
    public static final Parcelable$Creator<SharePhoto> CREATOR;
    private final Bitmap bitmap;
    private final String caption;
    private final Uri imageUrl;
    private final boolean userGenerated;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<SharePhoto>() {
            public SharePhoto createFromParcel(final Parcel parcel) {
                return new SharePhoto(parcel);
            }
            
            public SharePhoto[] newArray(final int n) {
                return new SharePhoto[n];
            }
        };
    }
    
    SharePhoto(final Parcel parcel) {
        super(parcel);
        this.bitmap = (Bitmap)parcel.readParcelable(Bitmap.class.getClassLoader());
        this.imageUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.userGenerated = (parcel.readByte() != 0);
        this.caption = parcel.readString();
    }
    
    private SharePhoto(final Builder builder) {
        super((ShareMedia.Builder)builder);
        this.bitmap = builder.bitmap;
        this.imageUrl = builder.imageUrl;
        this.userGenerated = builder.userGenerated;
        this.caption = builder.caption;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Bitmap getBitmap() {
        return this.bitmap;
    }
    
    public String getCaption() {
        return this.caption;
    }
    
    @Nullable
    public Uri getImageUrl() {
        return this.imageUrl;
    }
    
    @Override
    public Type getMediaType() {
        return Type.PHOTO;
    }
    
    public boolean getUserGenerated() {
        return this.userGenerated;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 0;
        super.writeToParcel(parcel, n);
        parcel.writeParcelable((Parcelable)this.bitmap, 0);
        parcel.writeParcelable((Parcelable)this.imageUrl, 0);
        n = n2;
        if (this.userGenerated) {
            n = 1;
        }
        parcel.writeByte((byte)n);
        parcel.writeString(this.caption);
    }
    
    public static final class Builder extends ShareMedia.Builder<SharePhoto, Builder>
    {
        private Bitmap bitmap;
        private String caption;
        private Uri imageUrl;
        private boolean userGenerated;
        
        static List<SharePhoto> readPhotoListFrom(final Parcel parcel) {
            final List<ShareMedia> list = ShareMedia.Builder.readListFrom(parcel);
            final ArrayList<SharePhoto> list2 = new ArrayList<SharePhoto>();
            for (final ShareMedia shareMedia : list) {
                if (shareMedia instanceof SharePhoto) {
                    list2.add((SharePhoto)shareMedia);
                }
            }
            return list2;
        }
        
        static void writePhotoListTo(final Parcel parcel, final int n, final List<SharePhoto> list) {
            final ShareMedia[] array = new ShareMedia[list.size()];
            for (int i = 0; i < list.size(); ++i) {
                array[i] = list.get(i);
            }
            parcel.writeParcelableArray((Parcelable[])array, n);
        }
        
        @Override
        public SharePhoto build() {
            return new SharePhoto(this, null);
        }
        
        Bitmap getBitmap() {
            return this.bitmap;
        }
        
        Uri getImageUrl() {
            return this.imageUrl;
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((SharePhoto)parcel.readParcelable(SharePhoto.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final SharePhoto sharePhoto) {
            if (sharePhoto == null) {
                return this;
            }
            return super.readFrom(sharePhoto).setBitmap(sharePhoto.getBitmap()).setImageUrl(sharePhoto.getImageUrl()).setUserGenerated(sharePhoto.getUserGenerated()).setCaption(sharePhoto.getCaption());
        }
        
        public Builder setBitmap(@Nullable final Bitmap bitmap) {
            this.bitmap = bitmap;
            return this;
        }
        
        public Builder setCaption(@Nullable final String caption) {
            this.caption = caption;
            return this;
        }
        
        public Builder setImageUrl(@Nullable final Uri imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public Builder setUserGenerated(final boolean userGenerated) {
            this.userGenerated = userGenerated;
            return this;
        }
    }
}
