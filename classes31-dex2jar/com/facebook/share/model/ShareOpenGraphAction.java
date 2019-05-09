// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ShareOpenGraphAction extends ShareOpenGraphValueContainer<ShareOpenGraphAction, Builder>
{
    public static final Parcelable$Creator<ShareOpenGraphAction> CREATOR;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareOpenGraphAction>() {
            public ShareOpenGraphAction createFromParcel(final Parcel parcel) {
                return new ShareOpenGraphAction(parcel);
            }
            
            public ShareOpenGraphAction[] newArray(final int n) {
                return new ShareOpenGraphAction[n];
            }
        };
    }
    
    ShareOpenGraphAction(final Parcel parcel) {
        super(parcel);
    }
    
    private ShareOpenGraphAction(final Builder builder) {
        super((ShareOpenGraphValueContainer.Builder)builder);
    }
    
    @Nullable
    public String getActionType() {
        return this.getString("og:type");
    }
    
    public static final class Builder extends ShareOpenGraphValueContainer.Builder<ShareOpenGraphAction, Builder>
    {
        private static final String ACTION_TYPE_KEY = "og:type";
        
        @Override
        public ShareOpenGraphAction build() {
            return new ShareOpenGraphAction(this, null);
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((ShareOpenGraphAction)parcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final ShareOpenGraphAction shareOpenGraphAction) {
            if (shareOpenGraphAction == null) {
                return this;
            }
            return super.readFrom(shareOpenGraphAction).setActionType(shareOpenGraphAction.getActionType());
        }
        
        public Builder setActionType(final String s) {
            this.putString("og:type", s);
            return this;
        }
    }
}
