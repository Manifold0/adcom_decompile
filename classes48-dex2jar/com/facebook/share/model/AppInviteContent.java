// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.text.TextUtils;
import android.os.Parcel;
import android.os.Parcelable$Creator;

@Deprecated
public final class AppInviteContent implements ShareModel
{
    @Deprecated
    public static final Parcelable$Creator<AppInviteContent> CREATOR;
    private final String applinkUrl;
    private final Destination destination;
    private final String previewImageUrl;
    private final String promoCode;
    private final String promoText;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<AppInviteContent>() {
            public AppInviteContent createFromParcel(final Parcel parcel) {
                return new AppInviteContent(parcel);
            }
            
            public AppInviteContent[] newArray(final int n) {
                return new AppInviteContent[n];
            }
        };
    }
    
    @Deprecated
    AppInviteContent(final Parcel parcel) {
        this.applinkUrl = parcel.readString();
        this.previewImageUrl = parcel.readString();
        this.promoText = parcel.readString();
        this.promoCode = parcel.readString();
        final String string = parcel.readString();
        if (string.length() > 0) {
            this.destination = Destination.valueOf(string);
            return;
        }
        this.destination = Destination.FACEBOOK;
    }
    
    private AppInviteContent(final Builder builder) {
        this.applinkUrl = builder.applinkUrl;
        this.previewImageUrl = builder.previewImageUrl;
        this.promoCode = builder.promoCode;
        this.promoText = builder.promoText;
        this.destination = builder.destination;
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
    }
    
    @Deprecated
    public String getApplinkUrl() {
        return this.applinkUrl;
    }
    
    @Deprecated
    public Destination getDestination() {
        if (this.destination != null) {
            return this.destination;
        }
        return Destination.FACEBOOK;
    }
    
    @Deprecated
    public String getPreviewImageUrl() {
        return this.previewImageUrl;
    }
    
    @Deprecated
    public String getPromotionCode() {
        return this.promoCode;
    }
    
    @Deprecated
    public String getPromotionText() {
        return this.promoText;
    }
    
    @Deprecated
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.applinkUrl);
        parcel.writeString(this.previewImageUrl);
        parcel.writeString(this.promoText);
        parcel.writeString(this.promoCode);
        parcel.writeString(this.destination.toString());
    }
    
    @Deprecated
    public static class Builder implements ShareModelBuilder<AppInviteContent, Builder>
    {
        private String applinkUrl;
        private Destination destination;
        private String previewImageUrl;
        private String promoCode;
        private String promoText;
        
        private boolean isAlphanumericWithSpaces(final String s) {
            for (int i = 0; i < s.length(); ++i) {
                final char char1 = s.charAt(i);
                if (!Character.isDigit(char1) && !Character.isLetter(char1) && !Character.isSpaceChar(char1)) {
                    return false;
                }
            }
            return true;
        }
        
        @Deprecated
        public AppInviteContent build() {
            return new AppInviteContent(this, null);
        }
        
        @Deprecated
        public Builder readFrom(final AppInviteContent appInviteContent) {
            if (appInviteContent == null) {
                return this;
            }
            return this.setApplinkUrl(appInviteContent.getApplinkUrl()).setPreviewImageUrl(appInviteContent.getPreviewImageUrl()).setPromotionDetails(appInviteContent.getPromotionText(), appInviteContent.getPromotionCode()).setDestination(appInviteContent.getDestination());
        }
        
        @Deprecated
        public Builder setApplinkUrl(final String applinkUrl) {
            this.applinkUrl = applinkUrl;
            return this;
        }
        
        @Deprecated
        public Builder setDestination(final Destination destination) {
            this.destination = destination;
            return this;
        }
        
        @Deprecated
        public Builder setPreviewImageUrl(final String previewImageUrl) {
            this.previewImageUrl = previewImageUrl;
            return this;
        }
        
        @Deprecated
        public Builder setPromotionDetails(final String promoText, final String promoCode) {
            if (!TextUtils.isEmpty((CharSequence)promoText)) {
                if (promoText.length() > 80) {
                    throw new IllegalArgumentException("Invalid promotion text, promotionText needs to be between1 and 80 characters long");
                }
                if (!this.isAlphanumericWithSpaces(promoText)) {
                    throw new IllegalArgumentException("Invalid promotion text, promotionText can only contain alphanumericcharacters and spaces.");
                }
                if (!TextUtils.isEmpty((CharSequence)promoCode)) {
                    if (promoCode.length() > 10) {
                        throw new IllegalArgumentException("Invalid promotion code, promotionCode can be between1 and 10 characters long");
                    }
                    if (!this.isAlphanumericWithSpaces(promoCode)) {
                        throw new IllegalArgumentException("Invalid promotion code, promotionCode can only contain alphanumeric characters and spaces.");
                    }
                }
            }
            else if (!TextUtils.isEmpty((CharSequence)promoCode)) {
                throw new IllegalArgumentException("promotionCode cannot be specified without a valid promotionText");
            }
            this.promoCode = promoCode;
            this.promoText = promoText;
            return this;
        }
        
        @Deprecated
        public enum Destination
        {
            FACEBOOK("facebook"), 
            MESSENGER("messenger");
            
            private final String name;
            
            private Destination(final String name) {
                this.name = name;
            }
            
            public boolean equalsName(final String s) {
                return s != null && this.name.equals(s);
            }
            
            @Override
            public String toString() {
                return this.name;
            }
        }
    }
}
