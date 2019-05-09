// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.support.annotation.Nullable;
import android.os.Parcel;

public abstract class ShareMessengerActionButton implements ShareModel
{
    private final String title;
    
    ShareMessengerActionButton(final Parcel parcel) {
        this.title = parcel.readString();
    }
    
    protected ShareMessengerActionButton(final Builder builder) {
        this.title = builder.title;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.title);
    }
    
    public abstract static class Builder<M extends ShareMessengerActionButton, B extends Builder> implements ShareModelBuilder<M, B>
    {
        private String title;
        
        @Override
        public B readFrom(final M m) {
            if (m == null) {
                return (B)this;
            }
            return this.setTitle(m.getTitle());
        }
        
        public B setTitle(@Nullable final String title) {
            this.title = title;
            return (B)this;
        }
    }
}
