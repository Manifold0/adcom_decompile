// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Bundle;

public abstract class ShareMedia implements ShareModel
{
    private final Bundle params;
    
    ShareMedia(final Parcel parcel) {
        this.params = parcel.readBundle();
    }
    
    protected ShareMedia(final Builder builder) {
        this.params = new Bundle(builder.params);
    }
    
    public int describeContents() {
        return 0;
    }
    
    public abstract Type getMediaType();
    
    @Deprecated
    public Bundle getParameters() {
        return new Bundle(this.params);
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeBundle(this.params);
    }
    
    public abstract static class Builder<M extends ShareMedia, B extends Builder> implements ShareModelBuilder<M, B>
    {
        private Bundle params;
        
        public Builder() {
            this.params = new Bundle();
        }
        
        static List<ShareMedia> readListFrom(final Parcel parcel) {
            final Parcelable[] parcelableArray = parcel.readParcelableArray(ShareMedia.class.getClassLoader());
            final ArrayList list = new ArrayList<ShareMedia>(parcelableArray.length);
            for (int length = parcelableArray.length, i = 0; i < length; ++i) {
                list.add((ShareMedia)parcelableArray[i]);
            }
            return (List<ShareMedia>)list;
        }
        
        static void writeListTo(final Parcel parcel, final int n, final List<ShareMedia> list) {
            parcel.writeParcelableArray((Parcelable[])list.toArray(), n);
        }
        
        @Override
        public B readFrom(final M m) {
            if (m == null) {
                return (B)this;
            }
            return this.setParameters(m.getParameters());
        }
        
        @Deprecated
        public B setParameter(final String s, final String s2) {
            this.params.putString(s, s2);
            return (B)this;
        }
        
        @Deprecated
        public B setParameters(final Bundle bundle) {
            this.params.putAll(bundle);
            return (B)this;
        }
    }
    
    public enum Type
    {
        PHOTO, 
        VIDEO;
    }
}
