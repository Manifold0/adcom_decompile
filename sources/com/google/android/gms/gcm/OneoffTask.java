package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.RequiresPermission;

public class OneoffTask extends Task {
    public static final Creator<OneoffTask> CREATOR = new zzf();
    private final long zzhzx;
    private final long zzhzy;

    public static class Builder extends com.google.android.gms.gcm.Task.Builder {
        private long zzhzz;
        private long zziaa;

        public Builder() {
            this.zzhzz = -1;
            this.zziaa = -1;
            this.isPersisted = false;
        }

        public OneoffTask build() {
            checkConditions();
            return new OneoffTask();
        }

        protected void checkConditions() {
            super.checkConditions();
            if (this.zzhzz == -1 || this.zziaa == -1) {
                throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
            } else if (this.zzhzz >= this.zziaa) {
                throw new IllegalArgumentException("Window start must be shorter than window end.");
            }
        }

        public Builder setExecutionWindow(long j, long j2) {
            this.zzhzz = j;
            this.zziaa = j2;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = bundle;
            return this;
        }

        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public Builder setPersisted(boolean z) {
            this.isPersisted = z;
            return this;
        }

        public Builder setRequiredNetwork(int i) {
            this.requiredNetworkState = i;
            return this;
        }

        public Builder setRequiresCharging(boolean z) {
            this.requiresCharging = z;
            return this;
        }

        public Builder setService(Class<? extends GcmTaskService> cls) {
            this.gcmTaskService = cls.getName();
            return this;
        }

        public Builder setTag(String str) {
            this.tag = str;
            return this;
        }

        public Builder setUpdateCurrent(boolean z) {
            this.updateCurrent = z;
            return this;
        }
    }

    @Deprecated
    private OneoffTask(Parcel parcel) {
        super(parcel);
        this.zzhzx = parcel.readLong();
        this.zzhzy = parcel.readLong();
    }

    private OneoffTask(Builder builder) {
        super((com.google.android.gms.gcm.Task.Builder) builder);
        this.zzhzx = builder.zzhzz;
        this.zzhzy = builder.zziaa;
    }

    public long getWindowEnd() {
        return this.zzhzy;
    }

    public long getWindowStart() {
        return this.zzhzx;
    }

    public void toBundle(Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzhzx);
        bundle.putLong("window_end", this.zzhzy);
    }

    public String toString() {
        String obj = super.toString();
        long windowStart = getWindowStart();
        return new StringBuilder(String.valueOf(obj).length() + 64).append(obj).append(" windowStart=").append(windowStart).append(" windowEnd=").append(getWindowEnd()).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeLong(this.zzhzx);
        parcel.writeLong(this.zzhzy);
    }
}
