// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ShareMessengerGenericTemplateContent extends ShareContent<ShareMessengerGenericTemplateContent, Builder>
{
    public static final Parcelable$Creator<ShareMessengerGenericTemplateContent> CREATOR;
    private final ShareMessengerGenericTemplateElement genericTemplateElement;
    private final ImageAspectRatio imageAspectRatio;
    private final boolean isSharable;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMessengerGenericTemplateContent>() {
            public ShareMessengerGenericTemplateContent createFromParcel(final Parcel parcel) {
                return new ShareMessengerGenericTemplateContent(parcel);
            }
            
            public ShareMessengerGenericTemplateContent[] newArray(final int n) {
                return new ShareMessengerGenericTemplateContent[n];
            }
        };
    }
    
    ShareMessengerGenericTemplateContent(final Parcel parcel) {
        super(parcel);
        this.isSharable = (parcel.readByte() != 0);
        this.imageAspectRatio = (ImageAspectRatio)parcel.readSerializable();
        this.genericTemplateElement = (ShareMessengerGenericTemplateElement)parcel.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader());
    }
    
    protected ShareMessengerGenericTemplateContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.isSharable = builder.isSharable;
        this.imageAspectRatio = builder.imageAspectRatio;
        this.genericTemplateElement = builder.genericTemplateElement;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public ShareMessengerGenericTemplateElement getGenericTemplateElement() {
        return this.genericTemplateElement;
    }
    
    public ImageAspectRatio getImageAspectRatio() {
        return this.imageAspectRatio;
    }
    
    public boolean getIsSharable() {
        return this.isSharable;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        boolean b;
        if (this.isSharable) {
            b = true;
        }
        else {
            b = false;
        }
        parcel.writeByte((byte)(byte)(b ? 1 : 0));
        parcel.writeSerializable((Serializable)this.imageAspectRatio);
        parcel.writeParcelable((Parcelable)this.genericTemplateElement, n);
    }
    
    public static class Builder extends ShareContent.Builder<ShareMessengerGenericTemplateContent, Builder>
    {
        private ShareMessengerGenericTemplateElement genericTemplateElement;
        private ImageAspectRatio imageAspectRatio;
        private boolean isSharable;
        
        @Override
        public ShareMessengerGenericTemplateContent build() {
            return new ShareMessengerGenericTemplateContent(this);
        }
        
        @Override
        public Builder readFrom(final ShareMessengerGenericTemplateContent shareMessengerGenericTemplateContent) {
            if (shareMessengerGenericTemplateContent == null) {
                return this;
            }
            return super.readFrom(shareMessengerGenericTemplateContent).setIsSharable(shareMessengerGenericTemplateContent.getIsSharable()).setImageAspectRatio(shareMessengerGenericTemplateContent.getImageAspectRatio()).setGenericTemplateElement(shareMessengerGenericTemplateContent.getGenericTemplateElement());
        }
        
        public Builder setGenericTemplateElement(final ShareMessengerGenericTemplateElement genericTemplateElement) {
            this.genericTemplateElement = genericTemplateElement;
            return this;
        }
        
        public Builder setImageAspectRatio(final ImageAspectRatio imageAspectRatio) {
            this.imageAspectRatio = imageAspectRatio;
            return this;
        }
        
        public Builder setIsSharable(final boolean isSharable) {
            this.isSharable = isSharable;
            return this;
        }
    }
    
    public enum ImageAspectRatio
    {
        HORIZONTAL, 
        SQUARE;
    }
}
