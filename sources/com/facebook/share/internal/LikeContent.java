package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;

@Deprecated
public class LikeContent implements ShareModel {
    @Deprecated
    public static final Creator<LikeContent> CREATOR = new C03571();
    private final String objectId;
    private final String objectType;

    /* renamed from: com.facebook.share.internal.LikeContent$1 */
    static class C03571 implements Creator<LikeContent> {
        C03571() {
        }

        public LikeContent createFromParcel(Parcel in) {
            return new LikeContent(in);
        }

        public LikeContent[] newArray(int size) {
            return new LikeContent[size];
        }
    }

    @Deprecated
    public static class Builder implements ShareModelBuilder<LikeContent, Builder> {
        private String objectId;
        private String objectType;

        @Deprecated
        public Builder setObjectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        @Deprecated
        public Builder setObjectType(String objectType) {
            this.objectType = objectType;
            return this;
        }

        @Deprecated
        public LikeContent build() {
            return new LikeContent();
        }

        @Deprecated
        public Builder readFrom(LikeContent content) {
            if (content == null) {
                return this;
            }
            return setObjectId(content.getObjectId()).setObjectType(content.getObjectType());
        }
    }

    private LikeContent(Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }

    @Deprecated
    LikeContent(Parcel in) {
        this.objectId = in.readString();
        this.objectType = in.readString();
    }

    @Deprecated
    public String getObjectId() {
        return this.objectId;
    }

    @Deprecated
    public String getObjectType() {
        return this.objectType;
    }

    @Deprecated
    public int describeContents() {
        return 0;
    }

    @Deprecated
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(this.objectId);
        out.writeString(this.objectType);
    }
}
