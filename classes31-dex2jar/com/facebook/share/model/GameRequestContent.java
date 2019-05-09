// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.model;

import java.util.Arrays;
import java.io.Serializable;
import android.text.TextUtils;
import android.os.Parcel;
import java.util.List;
import android.os.Parcelable$Creator;

public final class GameRequestContent implements ShareModel
{
    public static final Parcelable$Creator<GameRequestContent> CREATOR;
    private final ActionType actionType;
    private final String data;
    private final Filters filters;
    private final String message;
    private final String objectId;
    private final List<String> recipients;
    private final List<String> suggestions;
    private final String title;
    
    static {
        CREATOR = (Parcelable$Creator)new Parcelable$Creator<GameRequestContent>() {
            public GameRequestContent createFromParcel(final Parcel parcel) {
                return new GameRequestContent(parcel);
            }
            
            public GameRequestContent[] newArray(final int n) {
                return new GameRequestContent[n];
            }
        };
    }
    
    GameRequestContent(final Parcel parcel) {
        this.message = parcel.readString();
        this.recipients = (List<String>)parcel.createStringArrayList();
        this.title = parcel.readString();
        this.data = parcel.readString();
        this.actionType = (ActionType)parcel.readSerializable();
        this.objectId = parcel.readString();
        this.filters = (Filters)parcel.readSerializable();
        parcel.readStringList((List)(this.suggestions = (List<String>)parcel.createStringArrayList()));
    }
    
    private GameRequestContent(final Builder builder) {
        this.message = builder.message;
        this.recipients = builder.recipients;
        this.title = builder.title;
        this.data = builder.data;
        this.actionType = builder.actionType;
        this.objectId = builder.objectId;
        this.filters = builder.filters;
        this.suggestions = builder.suggestions;
    }
    
    public int describeContents() {
        return 0;
    }
    
    public ActionType getActionType() {
        return this.actionType;
    }
    
    public String getData() {
        return this.data;
    }
    
    public Filters getFilters() {
        return this.filters;
    }
    
    public String getMessage() {
        return this.message;
    }
    
    public String getObjectId() {
        return this.objectId;
    }
    
    public List<String> getRecipients() {
        return this.recipients;
    }
    
    public List<String> getSuggestions() {
        return this.suggestions;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getTo() {
        if (this.getRecipients() != null) {
            return TextUtils.join((CharSequence)",", (Iterable)this.getRecipients());
        }
        return null;
    }
    
    public void writeToParcel(final Parcel parcel, final int n) {
        parcel.writeString(this.message);
        parcel.writeStringList((List)this.recipients);
        parcel.writeString(this.title);
        parcel.writeString(this.data);
        parcel.writeSerializable((Serializable)this.actionType);
        parcel.writeString(this.objectId);
        parcel.writeSerializable((Serializable)this.filters);
        parcel.writeStringList((List)this.suggestions);
    }
    
    public enum ActionType
    {
        ASKFOR, 
        SEND, 
        TURN;
    }
    
    public static class Builder implements ShareModelBuilder<GameRequestContent, Builder>
    {
        private ActionType actionType;
        private String data;
        private Filters filters;
        private String message;
        private String objectId;
        private List<String> recipients;
        private List<String> suggestions;
        private String title;
        
        @Override
        public GameRequestContent build() {
            return new GameRequestContent(this, null);
        }
        
        Builder readFrom(final Parcel parcel) {
            return this.readFrom((GameRequestContent)parcel.readParcelable(GameRequestContent.class.getClassLoader()));
        }
        
        @Override
        public Builder readFrom(final GameRequestContent gameRequestContent) {
            if (gameRequestContent == null) {
                return this;
            }
            return this.setMessage(gameRequestContent.getMessage()).setRecipients(gameRequestContent.getRecipients()).setTitle(gameRequestContent.getTitle()).setData(gameRequestContent.getData()).setActionType(gameRequestContent.getActionType()).setObjectId(gameRequestContent.getObjectId()).setFilters(gameRequestContent.getFilters()).setSuggestions(gameRequestContent.getSuggestions());
        }
        
        public Builder setActionType(final ActionType actionType) {
            this.actionType = actionType;
            return this;
        }
        
        public Builder setData(final String data) {
            this.data = data;
            return this;
        }
        
        public Builder setFilters(final Filters filters) {
            this.filters = filters;
            return this;
        }
        
        public Builder setMessage(final String message) {
            this.message = message;
            return this;
        }
        
        public Builder setObjectId(final String objectId) {
            this.objectId = objectId;
            return this;
        }
        
        public Builder setRecipients(final List<String> recipients) {
            this.recipients = recipients;
            return this;
        }
        
        public Builder setSuggestions(final List<String> suggestions) {
            this.suggestions = suggestions;
            return this;
        }
        
        public Builder setTitle(final String title) {
            this.title = title;
            return this;
        }
        
        public Builder setTo(final String s) {
            if (s != null) {
                this.recipients = Arrays.asList(s.split(","));
            }
            return this;
        }
    }
    
    public enum Filters
    {
        APP_NON_USERS, 
        APP_USERS;
    }
}
