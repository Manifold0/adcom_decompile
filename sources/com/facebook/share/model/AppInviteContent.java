package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

@Deprecated
public final class AppInviteContent implements ShareModel {
    @Deprecated
    public static final Creator<AppInviteContent> CREATOR = new C09061();
    private final String applinkUrl;
    private final Destination destination;
    private final String previewImageUrl;
    private final String promoCode;
    private final String promoText;

    /* renamed from: com.facebook.share.model.AppInviteContent$1 */
    static class C09061 implements Creator<AppInviteContent> {
        C09061() {
        }

        public AppInviteContent createFromParcel(Parcel in) {
            return new AppInviteContent(in);
        }

        public AppInviteContent[] newArray(int size) {
            return new AppInviteContent[size];
        }
    }

    @Deprecated
    public static class Builder implements ShareModelBuilder<AppInviteContent, Builder> {
        private String applinkUrl;
        private Destination destination;
        private String previewImageUrl;
        private String promoCode;
        private String promoText;

        @Deprecated
        public enum Destination {
            FACEBOOK("facebook"),
            MESSENGER("messenger");
            
            private final String name;

            private Destination(String s) {
                this.name = s;
            }

            public boolean equalsName(String otherName) {
                return otherName == null ? false : this.name.equals(otherName);
            }

            public String toString() {
                return this.name;
            }
        }

        @Deprecated
        public Builder setApplinkUrl(String applinkUrl) {
            this.applinkUrl = applinkUrl;
            return this;
        }

        @Deprecated
        public Builder setPreviewImageUrl(String previewImageUrl) {
            this.previewImageUrl = previewImageUrl;
            return this;
        }

        @Deprecated
        public Builder setPromotionDetails(String promotionText, String promotionCode) {
            if (TextUtils.isEmpty(promotionText)) {
                if (!TextUtils.isEmpty(promotionCode)) {
                    throw new IllegalArgumentException("promotionCode cannot be specified without a valid promotionText");
                }
            } else if (promotionText.length() > 80) {
                throw new IllegalArgumentException("Invalid promotion text, promotionText needs to be between1 and 80 characters long");
            } else if (!isAlphanumericWithSpaces(promotionText)) {
                throw new IllegalArgumentException("Invalid promotion text, promotionText can only contain alphanumericcharacters and spaces.");
            } else if (!TextUtils.isEmpty(promotionCode)) {
                if (promotionCode.length() > 10) {
                    throw new IllegalArgumentException("Invalid promotion code, promotionCode can be between1 and 10 characters long");
                } else if (!isAlphanumericWithSpaces(promotionCode)) {
                    throw new IllegalArgumentException("Invalid promotion code, promotionCode can only contain alphanumeric characters and spaces.");
                }
            }
            this.promoCode = promotionCode;
            this.promoText = promotionText;
            return this;
        }

        @Deprecated
        public Builder setDestination(Destination destination) {
            this.destination = destination;
            return this;
        }

        @Deprecated
        public AppInviteContent build() {
            return new AppInviteContent();
        }

        @Deprecated
        public Builder readFrom(AppInviteContent content) {
            if (content == null) {
                return this;
            }
            return setApplinkUrl(content.getApplinkUrl()).setPreviewImageUrl(content.getPreviewImageUrl()).setPromotionDetails(content.getPromotionText(), content.getPromotionCode()).setDestination(content.getDestination());
        }

        private boolean isAlphanumericWithSpaces(String str) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (!Character.isDigit(c) && !Character.isLetter(c) && !Character.isSpaceChar(c)) {
                    return false;
                }
            }
            return true;
        }
    }

    private AppInviteContent(Builder builder) {
        this.applinkUrl = builder.applinkUrl;
        this.previewImageUrl = builder.previewImageUrl;
        this.promoCode = builder.promoCode;
        this.promoText = builder.promoText;
        this.destination = builder.destination;
    }

    @Deprecated
    AppInviteContent(Parcel in) {
        this.applinkUrl = in.readString();
        this.previewImageUrl = in.readString();
        this.promoText = in.readString();
        this.promoCode = in.readString();
        String destinationString = in.readString();
        if (destinationString.length() > 0) {
            this.destination = Destination.valueOf(destinationString);
        } else {
            this.destination = Destination.FACEBOOK;
        }
    }

    @Deprecated
    public String getApplinkUrl() {
        return this.applinkUrl;
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
    public Destination getDestination() {
        if (this.destination != null) {
            return this.destination;
        }
        return Destination.FACEBOOK;
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.applinkUrl);
        out.writeString(this.previewImageUrl);
        out.writeString(this.promoText);
        out.writeString(this.promoCode);
        out.writeString(this.destination.toString());
    }
}
