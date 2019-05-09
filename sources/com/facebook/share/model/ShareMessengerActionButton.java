package com.facebook.share.model;

import android.os.Parcel;
import android.support.annotation.Nullable;

public abstract class ShareMessengerActionButton implements ShareModel {
    private final String title;

    public static abstract class Builder<M extends ShareMessengerActionButton, B extends Builder> implements ShareModelBuilder<M, B> {
        private String title;

        public B setTitle(@Nullable String title) {
            this.title = title;
            return this;
        }

        public B readFrom(M model) {
            return model == null ? this : setTitle(model.getTitle());
        }
    }

    protected ShareMessengerActionButton(Builder builder) {
        this.title = builder.title;
    }

    ShareMessengerActionButton(Parcel in) {
        this.title = in.readString();
    }

    public String getTitle() {
        return this.title;
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
    }
}
