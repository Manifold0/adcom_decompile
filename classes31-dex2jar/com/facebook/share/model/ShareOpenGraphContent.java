// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class ShareOpenGraphContent extends ShareContent<ShareOpenGraphContent, Builder>
{
    public static final Parcelable$Creator<ShareOpenGraphContent> CREATOR;
    private final ShareOpenGraphAction action;
    private final String previewPropertyName;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<ShareOpenGraphContent>() {
            public ShareOpenGraphContent createFromParcel(final Parcel parcel) {
                return new ShareOpenGraphContent(parcel);
            }
            
            public ShareOpenGraphContent[] newArray(final int n) {
                return new ShareOpenGraphContent[n];
            }
        };
    }
    
    ShareOpenGraphContent(final Parcel parcel) {
        super(parcel);
        this.action = new ShareOpenGraphAction.Builder().readFrom(parcel).build();
        this.previewPropertyName = parcel.readString();
    }
    
    private ShareOpenGraphContent(final Builder builder) {
        super((ShareContent.Builder)builder);
        this.action = builder.action;
        this.previewPropertyName = builder.previewPropertyName;
    }
    
    @Override
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public ShareOpenGraphAction getAction() {
        return this.action;
    }
    
    @Nullable
    public String getPreviewPropertyName() {
        return this.previewPropertyName;
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeParcelable((Parcelable)this.action, 0);
        parcel.writeString(this.previewPropertyName);
    }
    
    public static final class Builder extends ShareContent.Builder<ShareOpenGraphContent, Builder>
    {
        private ShareOpenGraphAction action;
        private String previewPropertyName;
        
        @Override
        public ShareOpenGraphContent build() {
            return new ShareOpenGraphContent(this, null);
        }
        
        @Override
        public Builder readFrom(final ShareOpenGraphContent shareOpenGraphContent) {
            if (shareOpenGraphContent == null) {
                return this;
            }
            return super.readFrom(shareOpenGraphContent).setAction(shareOpenGraphContent.getAction()).setPreviewPropertyName(shareOpenGraphContent.getPreviewPropertyName());
        }
        
        public Builder setAction(@Nullable ShareOpenGraphAction build) {
            if (build == null) {
                build = null;
            }
            else {
                build = new ShareOpenGraphAction.Builder().readFrom(build).build();
            }
            this.action = build;
            return this;
        }
        
        public Builder setPreviewPropertyName(@Nullable final String previewPropertyName) {
            this.previewPropertyName = previewPropertyName;
            return this;
        }
    }
}
