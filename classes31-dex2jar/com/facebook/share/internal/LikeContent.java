// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.share.model.ShareModelBuilder;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import com.facebook.share.model.ShareModel;

@Deprecated
public class LikeContent implements ShareModel
{
    @Deprecated
    public static final Parcelable$Creator<LikeContent> CREATOR;
    private final String objectId;
    private final String objectType;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<LikeContent>() {
            public LikeContent createFromParcel(final Parcel parcel) {
                return new LikeContent(parcel);
            }
            
            public LikeContent[] newArray(final int n) {
                return new LikeContent[n];
            }
        };
    }
    
    @Deprecated
    LikeContent(final Parcel parcel) {
        this.objectId = parcel.readString();
        this.objectType = parcel.readString();
    }
    
    private LikeContent(final Builder builder) {
        this.objectId = builder.objectId;
        this.objectType = builder.objectType;
    }
    
    @Deprecated
    public int describeContents() {
        return 0;
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
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.objectId);
        parcel.writeString(this.objectType);
    }
    
    @Deprecated
    public static class Builder implements ShareModelBuilder<LikeContent, Builder>
    {
        private String objectId;
        private String objectType;
        
        @Deprecated
        @Override
        public LikeContent build() {
            return new LikeContent(this, null);
        }
        
        @Deprecated
        @Override
        public Builder readFrom(final LikeContent likeContent) {
            if (likeContent == null) {
                return this;
            }
            return this.setObjectId(likeContent.getObjectId()).setObjectType(likeContent.getObjectType());
        }
        
        @Deprecated
        public Builder setObjectId(final String objectId) {
            this.objectId = objectId;
            return this;
        }
        
        @Deprecated
        public Builder setObjectType(final String objectType) {
            this.objectType = objectType;
            return this;
        }
    }
}
