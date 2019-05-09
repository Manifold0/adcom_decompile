// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.support.annotation.RequiresPermission;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable$Creator;

public class PeriodicTask extends Task
{
    public static final Parcelable$Creator<PeriodicTask> CREATOR;
    protected long mFlexInSeconds;
    protected long mIntervalInSeconds;
    
    static {
        CREATOR = (Parcelable$Creator)new zzh();
    }
    
    @Deprecated
    private PeriodicTask(final Parcel parcel) {
        super(parcel);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = parcel.readLong();
        this.mFlexInSeconds = Math.min(parcel.readLong(), this.mIntervalInSeconds);
    }
    
    private PeriodicTask(final Builder builder) {
        super((Task.Builder)builder);
        this.mIntervalInSeconds = -1L;
        this.mFlexInSeconds = -1L;
        this.mIntervalInSeconds = builder.zziab;
        this.mFlexInSeconds = Math.min(builder.zziac, this.mIntervalInSeconds);
    }
    
    public long getFlex() {
        return this.mFlexInSeconds;
    }
    
    public long getPeriod() {
        return this.mIntervalInSeconds;
    }
    
    @Override
    public void toBundle(final Bundle bundle) {
        super.toBundle(bundle);
        bundle.putLong("period", this.mIntervalInSeconds);
        bundle.putLong("period_flex", this.mFlexInSeconds);
    }
    
    @Override
    public String toString() {
        final String string = super.toString();
        return new StringBuilder(String.valueOf(string).length() + 54).append(string).append(" period=").append(this.getPeriod()).append(" flex=").append(this.getFlex()).toString();
    }
    
    @Override
    public void writeToParcel(final Parcel parcel, final int n) {
        super.writeToParcel(parcel, n);
        parcel.writeLong(this.mIntervalInSeconds);
        parcel.writeLong(this.mFlexInSeconds);
    }
    
    public static class Builder extends Task.Builder
    {
        private long zziab;
        private long zziac;
        
        public Builder() {
            this.zziab = -1L;
            this.zziac = -1L;
            this.isPersisted = true;
        }
        
        public PeriodicTask build() {
            ((Task.Builder)this).checkConditions();
            return new PeriodicTask(this, null);
        }
        
        @Override
        protected void checkConditions() {
            super.checkConditions();
            if (this.zziab == -1L) {
                throw new IllegalArgumentException("Must call setPeriod(long) to establish an execution interval for this periodic task.");
            }
            if (this.zziab <= 0L) {
                throw new IllegalArgumentException(new StringBuilder(66).append("Period set cannot be less than or equal to 0: ").append(this.zziab).toString());
            }
            if (this.zziac == -1L) {
                this.zziac = (long)(this.zziab * 0.1f);
            }
            else if (this.zziac > this.zziab) {
                this.zziac = this.zziab;
            }
        }
        
        public Builder setExtras(final Bundle extras) {
            this.extras = extras;
            return this;
        }
        
        public Builder setFlex(final long zziac) {
            this.zziac = zziac;
            return this;
        }
        
        public Builder setPeriod(final long zziab) {
            this.zziab = zziab;
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
