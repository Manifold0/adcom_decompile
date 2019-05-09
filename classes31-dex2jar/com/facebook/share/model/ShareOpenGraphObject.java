// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ShareOpenGraphObject extends ShareOpenGraphValueContainer<ShareOpenGraphObject, Builder>
{
    public static final Parcelable$Creator<ShareOpenGraphObject> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareOpenGraphObject>() {
            public ShareOpenGraphObject createFromParcel(final Parcel parcel) {
                return new ShareOpenGraphObject(parcel);
            }
            
            public ShareOpenGraphObject[] newArray(final int n) {
                return new ShareOpenGraphObject[n];
            }
        };
    }
    
    ShareOpenGraphObject(final Parcel parcel) {
        super(parcel);
    }
    
    private ShareOpenGraphObject(final Builder builder) {
        super((ShareOpenGraphValueContainer.Builder)builder);
    }
    
    public static final class Builder extends ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder>
    {
        public Builder() {
            this.putBoolean("fbsdk:create_object", true);
        }
        
        @Override
        public ShareOpenGraphObject build() {
            return new ShareOpenGraphObject(this, null);
        }
        
        Builder readFrom(final Parcel parcel) {
            return ((ShareOpenGraphValueContainer.Builder<ShareOpenGraphObject, Builder>)this).readFrom((ShareOpenGraphObject)parcel.readParcelable(ShareOpenGraphObject.class.getClassLoader()));
        }
    }
}
