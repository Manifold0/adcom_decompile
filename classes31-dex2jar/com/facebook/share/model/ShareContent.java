// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import java.util.Collections;
import java.util.ArrayList;
import android.os.Parcel;
import java.util.List;
import android.net.Uri;

public abstract class ShareContent<P extends ShareContent, E extends Builder> implements ShareModel
{
    private final Uri contentUrl;
    private final ShareHashtag hashtag;
    private final String pageId;
    private final List<String> peopleIds;
    private final String placeId;
    private final String ref;
    
    protected ShareContent(final Parcel parcel) {
        this.contentUrl = (Uri)parcel.readParcelable(Uri.class.getClassLoader());
        this.peopleIds = this.readUnmodifiableStringList(parcel);
        this.placeId = parcel.readString();
        this.pageId = parcel.readString();
        this.ref = parcel.readString();
        this.hashtag = new ShareHashtag.Builder().readFrom(parcel).build();
    }
    
    protected ShareContent(final Builder builder) {
        this.contentUrl = builder.contentUrl;
        this.peopleIds = builder.peopleIds;
        this.placeId = builder.placeId;
        this.pageId = builder.pageId;
        this.ref = builder.ref;
        this.hashtag = builder.hashtag;
    }
    
    private List<String> readUnmodifiableStringList(final Parcel parcel) {
        final ArrayList list = new ArrayList<String>();
        parcel.readStringList((List)list);
        if (list.size() == 0) {
            return null;
        }
        return Collections.unmodifiableList((List<? extends String>)list);
    }
    
    public int describeContents() {
        return 0;
    }
    
    @Nullable
    public Uri getContentUrl() {
        return this.contentUrl;
    }
    
    @Nullable
    public String getPageId() {
        return this.pageId;
    }
    
    @Nullable
    public List<String> getPeopleIds() {
        return this.peopleIds;
    }
    
    @Nullable
    public String getPlaceId() {
        return this.placeId;
    }
    
    @Nullable
    public String getRef() {
        return this.ref;
    }
    
    @Nullable
    public ShareHashtag getShareHashtag() {
        return this.hashtag;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeParcelable((Parcelable)this.contentUrl, 0);
        parcel.writeStringList((List)this.peopleIds);
        parcel.writeString(this.placeId);
        parcel.writeString(this.pageId);
        parcel.writeString(this.ref);
        parcel.writeParcelable((Parcelable)this.hashtag, 0);
    }
    
    public abstract static class Builder<P extends ShareContent, E extends Builder> implements ShareModelBuilder<P, E>
    {
        private Uri contentUrl;
        private ShareHashtag hashtag;
        private String pageId;
        private List<String> peopleIds;
        private String placeId;
        private String ref;
        
        @Override
        public E readFrom(final P p) {
            if (p == null) {
                return (E)this;
            }
            return (E)this.setContentUrl(p.getContentUrl()).setPeopleIds(p.getPeopleIds()).setPlaceId(p.getPlaceId()).setPageId(p.getPageId()).setRef(p.getRef());
        }
        
        public E setContentUrl(@Nullable final Uri contentUrl) {
            this.contentUrl = contentUrl;
            return (E)this;
        }
        
        public E setPageId(@Nullable final String pageId) {
            this.pageId = pageId;
            return (E)this;
        }
        
        public E setPeopleIds(@Nullable final List<String> list) {
            List<String> unmodifiableList;
            if (list == null) {
                unmodifiableList = null;
            }
            else {
                unmodifiableList = Collections.unmodifiableList((List<? extends String>)list);
            }
            this.peopleIds = unmodifiableList;
            return (E)this;
        }
        
        public E setPlaceId(@Nullable final String placeId) {
            this.placeId = placeId;
            return (E)this;
        }
        
        public E setRef(@Nullable final String ref) {
            this.ref = ref;
            return (E)this;
        }
        
        public E setShareHashtag(@Nullable final ShareHashtag hashtag) {
            this.hashtag = hashtag;
            return (E)this;
        }
    }
}
