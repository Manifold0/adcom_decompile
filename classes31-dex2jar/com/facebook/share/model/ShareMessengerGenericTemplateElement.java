// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareMessengerGenericTemplateElement implements ShareModel
{
    public static final Parcelable$Creator<ShareMessengerGenericTemplateElement> CREATOR;
    private final ShareMessengerActionButton button;
    private final ShareMessengerActionButton defaultAction;
    private final Uri imageUrl;
    private final String subtitle;
    private final String title;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMessengerGenericTemplateElement>() {
            public ShareMessengerGenericTemplateElement createFromParcel(final Parcel parcel) {
                return new ShareMessengerGenericTemplateElement(parcel);
            }
            
            public ShareMessengerGenericTemplateElement[] newArray(final int n) {
                return new ShareMessengerGenericTemplateElement[n];
            }
        };
    }
    
    ShareMessengerGenericTemplateElement(final Parcel parcel) {
        this.title = parcel.readString();
        this.subtitle = parcel.readString();
        this.imageUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.defaultAction = (ShareMessengerActionButton)parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
        this.button = (ShareMessengerActionButton)parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }
    
    private ShareMessengerGenericTemplateElement(final Builder builder) {
        this.title = builder.title;
        this.subtitle = builder.subtitle;
        this.imageUrl = builder.imageUrl;
        this.defaultAction = builder.defaultAction;
        this.button = builder.button;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public ShareMessengerActionButton getButton() {
        return this.button;
    }
    
    public ShareMessengerActionButton getDefaultAction() {
        return this.defaultAction;
    }
    
    public Uri getImageUrl() {
        return this.imageUrl;
    }
    
    public String getSubtitle() {
        return this.subtitle;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.title);
        parcel.writeString(this.subtitle);
        parcel.writeParcelable((Parcelable)this.imageUrl, n);
        parcel.writeParcelable((Parcelable)this.defaultAction, n);
        parcel.writeParcelable((Parcelable)this.button, n);
    }
    
    public static class Builder implements ShareModelBuilder<ShareMessengerGenericTemplateElement, Builder>
    {
        private ShareMessengerActionButton button;
        private ShareMessengerActionButton defaultAction;
        private Uri imageUrl;
        private String subtitle;
        private String title;
        
        @Override
        public ShareMessengerGenericTemplateElement build() {
            return new ShareMessengerGenericTemplateElement(this, null);
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((ShareMessengerGenericTemplateElement)parcel.readParcelable(ShareMessengerGenericTemplateElement.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final ShareMessengerGenericTemplateElement shareMessengerGenericTemplateElement) {
            if (shareMessengerGenericTemplateElement == null) {
                return this;
            }
            return this.setTitle(shareMessengerGenericTemplateElement.title).setSubtitle(shareMessengerGenericTemplateElement.subtitle).setImageUrl(shareMessengerGenericTemplateElement.imageUrl).setDefaultAction(shareMessengerGenericTemplateElement.defaultAction).setButton(shareMessengerGenericTemplateElement.button);
        }
        
        public Builder setButton(final ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }
        
        public Builder setDefaultAction(final ShareMessengerActionButton defaultAction) {
            this.defaultAction = defaultAction;
            return this;
        }
        
        public Builder setImageUrl(final Uri imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }
        
        public Builder setSubtitle(final String subtitle) {
            this.subtitle = subtitle;
            return this;
        }
        
        public Builder setTitle(final String title) {
            this.title = title;
            return this;
        }
    }
}
