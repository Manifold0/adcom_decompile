// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.support.annotation.Nullable;
import android.os.Parcel;
import android.net.Uri;
import android.os.Parcelable$Creator;

public final class ShareMessengerURLActionButton extends ShareMessengerActionButton
{
    public static final Parcelable$Creator<ShareMessengerURLActionButton> CREATOR;
    private final Uri fallbackUrl;
    private final boolean isMessengerExtensionURL;
    private final boolean shouldHideWebviewShareButton;
    private final Uri url;
    private final WebviewHeightRatio webviewHeightRatio;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareMessengerURLActionButton>() {
            public ShareMessengerURLActionButton createFromParcel(final Parcel parcel) {
                return new ShareMessengerURLActionButton(parcel);
            }
            
            public ShareMessengerURLActionButton[] newArray(final int n) {
                return new ShareMessengerURLActionButton[n];
            }
        };
    }
    
    ShareMessengerURLActionButton(final Parcel parcel) {
        final boolean b = true;
        super(parcel);
        this.url = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.isMessengerExtensionURL = (parcel.readByte() != 0);
        this.fallbackUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.webviewHeightRatio = (WebviewHeightRatio)parcel.readSerializable();
        this.shouldHideWebviewShareButton = (parcel.readByte() != 0 && b);
    }
    
    private ShareMessengerURLActionButton(final Builder builder) {
        super((ShareMessengerActionButton.Builder)builder);
        this.url = builder.url;
        this.isMessengerExtensionURL = builder.isMessengerExtensionURL;
        this.fallbackUrl = builder.fallbackUrl;
        this.webviewHeightRatio = builder.webviewHeightRatio;
        this.shouldHideWebviewShareButton = builder.shouldHideWebviewShareButton;
    }
    
    @Nullable
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }
    
    public boolean getIsMessengerExtensionURL() {
        return this.isMessengerExtensionURL;
    }
    
    public boolean getShouldHideWebviewShareButton() {
        return this.shouldHideWebviewShareButton;
    }
    
    public Uri getUrl() {
        return this.url;
    }
    
    @Nullable
    public WebviewHeightRatio getWebviewHeightRatio() {
        return this.webviewHeightRatio;
    }
    
    public static final class Builder extends ShareMessengerActionButton.Builder<ShareMessengerURLActionButton, Builder>
    {
        private Uri fallbackUrl;
        private boolean isMessengerExtensionURL;
        private boolean shouldHideWebviewShareButton;
        private Uri url;
        private WebviewHeightRatio webviewHeightRatio;
        
        @Override
        public ShareMessengerURLActionButton build() {
            return new ShareMessengerURLActionButton(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareMessengerURLActionButton shareMessengerURLActionButton) {
            if (shareMessengerURLActionButton == null) {
                return this;
            }
            return this.setUrl(shareMessengerURLActionButton.getUrl()).setIsMessengerExtensionURL(shareMessengerURLActionButton.getIsMessengerExtensionURL()).setFallbackUrl(shareMessengerURLActionButton.getFallbackUrl()).setWebviewHeightRatio(shareMessengerURLActionButton.getWebviewHeightRatio()).setShouldHideWebviewShareButton(shareMessengerURLActionButton.getShouldHideWebviewShareButton());
        }
        
        public Builder setFallbackUrl(@Nullable final Uri fallbackUrl) {
            this.fallbackUrl = fallbackUrl;
            return this;
        }
        
        public Builder setIsMessengerExtensionURL(final boolean isMessengerExtensionURL) {
            this.isMessengerExtensionURL = isMessengerExtensionURL;
            return this;
        }
        
        public Builder setShouldHideWebviewShareButton(final boolean shouldHideWebviewShareButton) {
            this.shouldHideWebviewShareButton = shouldHideWebviewShareButton;
            return this;
        }
        
        public Builder setUrl(@Nullable final Uri url) {
            this.url = url;
            return this;
        }
        
        public Builder setWebviewHeightRatio(final WebviewHeightRatio webviewHeightRatio) {
            this.webviewHeightRatio = webviewHeightRatio;
            return this;
        }
    }
    
    public enum WebviewHeightRatio
    {
        WebviewHeightRatioCompact, 
        WebviewHeightRatioFull, 
        WebviewHeightRatioTall;
    }
}
