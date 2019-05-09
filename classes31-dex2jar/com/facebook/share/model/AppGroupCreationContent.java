// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.io.Serializable;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public final class AppGroupCreationContent implements ShareModel
{
    public static final Parcelable$Creator<AppGroupCreationContent> CREATOR;
    private final String description;
    private final String name;
    private AppGroupPrivacy privacy;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<AppGroupCreationContent>() {
            public AppGroupCreationContent createFromParcel(final Parcel parcel) {
                return new AppGroupCreationContent(parcel);
            }
            
            public AppGroupCreationContent[] newArray(final int n) {
                return new AppGroupCreationContent[n];
            }
        };
    }
    
    AppGroupCreationContent(final Parcel parcel) {
        this.name = parcel.readString();
        this.description = parcel.readString();
        this.privacy = (AppGroupPrivacy)parcel.readSerializable();
    }
    
    private AppGroupCreationContent(final Builder builder) {
        this.name = builder.name;
        this.description = builder.description;
        this.privacy = builder.privacy;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public AppGroupPrivacy getAppGroupPrivacy() {
        return this.privacy;
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.name);
        parcel.writeString(this.description);
        parcel.writeSerializable((Serializable)this.privacy);
    }
    
    public enum AppGroupPrivacy
    {
        Closed, 
        Open;
    }
    
    public static class Builder implements ShareModelBuilder<AppGroupCreationContent, Builder>
    {
        private String description;
        private String name;
        private AppGroupPrivacy privacy;
        
        @Override
        public AppGroupCreationContent build() {
            return new AppGroupCreationContent(this, null);
        }
        
        @Override
        public Builder readFrom(final AppGroupCreationContent appGroupCreationContent) {
            if (appGroupCreationContent == null) {
                return this;
            }
            return this.setName(appGroupCreationContent.getName()).setDescription(appGroupCreationContent.getDescription()).setAppGroupPrivacy(appGroupCreationContent.getAppGroupPrivacy());
        }
        
        public Builder setAppGroupPrivacy(final AppGroupPrivacy privacy) {
            this.privacy = privacy;
            return this;
        }
        
        public Builder setDescription(final String description) {
            this.description = description;
            return this;
        }
        
        public Builder setName(final String name) {
            this.name = name;
            return this;
        }
    }
}
