// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.support.annotation.RequiresPermission;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class OneoffTask extends Task
{
    public static final Parcelable$Creator<OneoffTask> CREATOR;
    private final long zzhzx;
    private final long zzhzy;
    
    static {
        CREATOR = (Parcelable$Creator)new zzf();
    }
    
    @Deprecated
    private OneoffTask(final Parcel parcel) {
        super(parcel);
        this.zzhzx = parcel.readLong();
        this.zzhzy = parcel.readLong();
    }
    
    private OneoffTask(final Builder builder) {
        super((Task.Builder)builder);
        this.zzhzx = builder.zzhzz;
        this.zzhzy = builder.zziaa;
    }
    
    public long getWindowEnd() {
        return this.zzhzy;
    }
    
    public long getWindowStart() {
        return this.zzhzx;
    }
    
    @Override
    public void toBundle(final Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("window_start", this.zzhzx);
        bundle.putLong("window_end", this.zzhzy);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return new StringBuilder(String.valueOf(string).length() + 64).append(string).append(" windowStart=").append(this.getWindowStart()).append(" windowEnd=").append(this.getWindowEnd()).toString();
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeLong(this.zzhzx);
        parcel.writeLong(this.zzhzy);
    }
    
    public static class Builder extends Task.Builder
    {
        private long zzhzz;
        private long zziaa;
        
        public Builder() {
            this.zzhzz = -1L;
            this.zziaa = -1L;
            this.isPersisted = false;
        }
        
        public OneoffTask build() {
            ((Task.Builder)this).checkConditions();
            return new OneoffTask(this, null);
        }
        
        @Override
        protected void checkConditions() {
            super.checkConditions();
            if (this.zzhzz == -1L || this.zziaa == -1L) {
                throw new IllegalArgumentException("Must specify an execution window using setExecutionWindow.");
            }
            if (this.zzhzz >= this.zziaa) {
                throw new IllegalArgumentException("Window start must be shorter than window end.");
            }
        }
        
        public Builder setExecutionWindow(final long zzhzz, final long zziaa) {
            this.zzhzz = zzhzz;
            this.zziaa = zziaa;
            return this;
        }
        
        public Builder setExtras(final Bundle extras) {
            this.extras = extras;
            return this;
        }
        
        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public Builder setPersisted(final boolean isPersisted) {
            this.isPersisted = isPersisted;
            return this;
        }
        
        public Builder setRequiredNetwork(final int requiredNetworkState) {
            this.requiredNetworkState = requiredNetworkState;
            return this;
        }
        
        public Builder setRequiresCharging(final boolean requiresCharging) {
            this.requiresCharging = requiresCharging;
            return this;
        }
        
        public Builder setService(final Class<? extends GcmTaskService> clazz) {
            this.gcmTaskService = clazz.getName();
            return this;
        }
        
        public Builder setTag(final String tag) {
            this.tag = tag;
            return this;
        }
        
        public Builder setUpdateCurrent(final boolean updateCurrent) {
            this.updateCurrent = updateCurrent;
            return this;
        }
    }
}
