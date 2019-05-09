package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareMessengerURLActionButton extends ShareMessengerActionButton {
    public static final Creator<ShareMessengerURLActionButton> CREATOR = new C03851();
    private final Uri fallbackUrl;
    private final boolean isMessengerExtensionURL;
    private final boolean shouldHideWebviewShareButton;
    private final Uri url;
    private final WebviewHeightRatio webviewHeightRatio;

    /* renamed from: com.facebook.share.model.ShareMessengerURLActionButton$1 */
    static class C03851 implements Creator<ShareMessengerURLActionButton> {
        C03851() {
        }

        public ShareMessengerURLActionButton createFromParcel(Parcel in) {
            return new ShareMessengerURLActionButton(in);
        }

        public ShareMessengerURLActionButton[] newArray(int size) {
            return new ShareMessengerURLActionButton[size];
        }
    }

    public static final class Builder extends com.facebook.share.model.ShareMessengerActionButton.Builder<ShareMessengerURLActionButton, Builder> {
        private Uri fallbackUrl;
        private boolean isMessengerExtensionURL;
        private boolean shouldHideWebviewShareButton;
        private Uri url;
        private WebviewHeightRatio webviewHeightRatio;

        public Builder setUrl(@Nullable Uri url) {
            this.url = url;
            return this;
        }

        public Builder setIsMessengerExtensionURL(boolean isMessengerExtensionURL) {
            this.isMessengerExtensionURL = isMessengerExtensionURL;
            return this;
        }

        public Builder setFallbackUrl(@Nullable Uri fallbackUrl) {
            this.fallbackUrl = fallbackUrl;
            return this;
        }

        public Builder setWebviewHeightRatio(WebviewHeightRatio webviewHeightRatio) {
            this.webviewHeightRatio = webviewHeightRatio;
            return this;
        }

        public Builder setShouldHideWebviewShareButton(boolean shouldHideWebviewShareButton) {
            this.shouldHideWebviewShareButton = shouldHideWebviewShareButton;
            return this;
        }

        public Builder readFrom(ShareMessengerURLActionButton content) {
            if (content == null) {
                return this;
            }
            return setUrl(content.getUrl()).setIsMessengerExtensionURL(content.getIsMessengerExtensionURL()).setFallbackUrl(content.getFallbackUrl()).setWebviewHeightRatio(content.getWebviewHeightRatio()).setShouldHideWebviewShareButton(content.getShouldHideWebviewShareButton());
        }

        public ShareMessengerURLActionButton build() {
            return new ShareMessengerURLActionButton();
        }
    }

    public enum WebviewHeightRatio {
        WebviewHeightRatioFull,
        WebviewHeightRatioTall,
        WebviewHeightRatioCompact
    }

    private ShareMessengerURLActionButton(Builder builder) {
        super((com.facebook.share.model.ShareMessengerActionButton.Builder) builder);
        this.url = builder.url;
        this.isMessengerExtensionURL = builder.isMessengerExtensionURL;
        this.fallbackUrl = builder.fallbackUrl;
        this.webviewHeightRatio = builder.webviewHeightRatio;
        this.shouldHideWebviewShareButton = builder.shouldHideWebviewShareButton;
    }

    ShareMessengerURLActionButton(Parcel in) {
        boolean z;
        boolean z2 = true;
        super(in);
        this.url = (Uri) in.readParcelable(Uri.class.getClassLoader());
        if (in.readByte() != (byte) 0) {
            z = true;
        } else {
            z = false;
        }
        this.isMessengerExtensionURL = z;
        this.fallbackUrl = (Uri) in.readParcelable(Uri.class.getClassLoader());
        this.webviewHeightRatio = (WebviewHeightRatio) in.readSerializable();
        if (in.readByte() == (byte) 0) {
            z2 = false;
        }
        this.shouldHideWebviewShareButton = z2;
    }

    public Uri getUrl() {
        return this.url;
    }

    public boolean getIsMessengerExtensionURL() {
        return this.isMessengerExtensionURL;
    }

    @Nullable
    public Uri getFallbackUrl() {
        return this.fallbackUrl;
    }

    @Nullable
    public WebviewHeightRatio getWebviewHeightRatio() {
        return this.webviewHeightRatio;
    }

    public boolean getShouldHideWebviewShareButton() {
        return this.shouldHideWebviewShareButton;
    }
}
