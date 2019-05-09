// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareMessengerOpenGraphMusicTemplateContent extends ShareContent<ShareMessengerOpenGraphMusicTemplateContent, Builder>
{
    public static final Parcelable$Creator<ShareMessengerOpenGraphMusicTemplateContent> CREATOR;
    private final ShareMessengerActionButton button;
    private final Uri url;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMessengerOpenGraphMusicTemplateContent>() {
            public ShareMessengerOpenGraphMusicTemplateContent createFromParcel(final Parcel parcel) {
                return new ShareMessengerOpenGraphMusicTemplateContent(parcel);
            }
            
            public ShareMessengerOpenGraphMusicTemplateContent[] newArray(final int n) {
                return new ShareMessengerOpenGraphMusicTemplateContent[n];
            }
        };
    }
    
    ShareMessengerOpenGraphMusicTemplateContent(final Parcel parcel) {
        super(parcel);
        this.url = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.button = (ShareMessengerActionButton)parcel.readParcelable(ShareMessengerActionButton.class.getClassLoader());
    }
    
    private ShareMessengerOpenGraphMusicTemplateContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.url = builder.url;
        this.button = builder.button;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    public ShareMessengerActionButton getButton() {
        return this.button;
    }
    
    public Uri getUrl() {
        return this.url;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.url, n);
        parcel.writeParcelable((Parcelable)this.button, n);
    }
    
    public static class Builder extends ShareContent.Builder<ShareMessengerOpenGraphMusicTemplateContent, Builder>
    {
        private ShareMessengerActionButton button;
        private Uri url;
        
        @Override
        public ShareMessengerOpenGraphMusicTemplateContent build() {
            return new ShareMessengerOpenGraphMusicTemplateContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareMessengerOpenGraphMusicTemplateContent shareMessengerOpenGraphMusicTemplateContent) {
            if (shareMessengerOpenGraphMusicTemplateContent == null) {
                return this;
            }
            return super.readFrom(shareMessengerOpenGraphMusicTemplateContent).setUrl(shareMessengerOpenGraphMusicTemplateContent.getUrl()).setButton(shareMessengerOpenGraphMusicTemplateContent.getButton());
        }
        
        public Builder setButton(final ShareMessengerActionButton button) {
            this.button = button;
            return this;
        }
        
        public Builder setUrl(final Uri url) {
            this.url = url;
            return this;
        }
    }
}
