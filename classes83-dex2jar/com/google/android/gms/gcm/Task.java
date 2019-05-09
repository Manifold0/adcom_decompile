// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.support.annotation.RequiresPermission;
import android.support.annotation.CallSuper;
import com.google.android.gms.common.internal.zzbq;
import java.util.Iterator;
import android.util.Log;
import android.os.Parcel;
import android.os.Bundle;
import com.google.android.gms.common.internal.ReflectedParcelable;

public class Task implements ReflectedParcelable
{
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1L;
    private final Bundle mExtras;
    private final String mTag;
    private final String zziai;
    private final boolean zziaj;
    private final boolean zziak;
    private final int zzial;
    private final boolean zziam;
    private final boolean zzian;
    private final zzi zziao;
    
    @Deprecated
    Task(final Parcel parcel) {
        final boolean b = true;
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zziai = parcel.readString();
        this.mTag = parcel.readString();
        this.zziaj = (parcel.readInt() == 1);
        this.zziak = (parcel.readInt() == 1 && b);
        this.zzial = 2;
        this.zziam = false;
        this.zzian = false;
        this.zziao = zzi.zziad;
        this.mExtras = null;
    }
    
    Task(final Builder builder) {
        this.zziai = builder.gcmTaskService;
        this.mTag = builder.tag;
        this.zziaj = builder.updateCurrent;
        this.zziak = builder.isPersisted;
        this.zzial = builder.requiredNetworkState;
        this.zziam = builder.requiresCharging;
        this.zzian = false;
        this.mExtras = builder.extras;
        zzi zziao;
        if (builder.zziap != null) {
            zziao = builder.zziap;
        }
        else {
            zziao = zzi.zziad;
        }
        this.zziao = zziao;
    }
    
    public static void zzv(final Bundle bundle) {
        if (bundle != null) {
            final Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            final int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > 10240) {
                throw new IllegalArgumentException(new StringBuilder(String.valueOf("Extras exceeding maximum size(10240 bytes): ").length() + 11).append("Extras exceeding maximum size(10240 bytes): ").append(dataSize).toString());
            }
            final Iterator<String> iterator = (Iterator<String>)bundle.keySet().iterator();
            while (iterator.hasNext()) {
                final Object value = bundle.get((String)iterator.next());
                int n;
                if (value instanceof Integer || value instanceof Long || value instanceof Double || value instanceof String || value instanceof Boolean) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n == 0) {
                    if (!(value instanceof Bundle)) {
                        throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                    }
                    zzv((Bundle)value);
                }
            }
        }
    }
    
    public int describeContents() {
        return 0;
    }
    
    public Bundle getExtras() {
        return this.mExtras;
    }
    
    public int getRequiredNetwork() {
        return this.zzial;
    }
    
    public boolean getRequiresCharging() {
        return this.zziam;
    }
    
    public String getServiceName() {
        return this.zziai;
    }
    
    public String getTag() {
        return this.mTag;
    }
    
    public boolean isPersisted() {
        return this.zziak;
    }
    
    public boolean isUpdateCurrent() {
        return this.zziaj;
    }
    
    public void toBundle(final Bundle bundle) {
        bundle.putString("tag", this.mTag);
        bundle.putBoolean("update_current", this.zziaj);
        bundle.putBoolean("persisted", this.zziak);
        bundle.putString("service", this.zziai);
        bundle.putInt("requiredNetwork", this.zzial);
        bundle.putBoolean("requiresCharging", this.zziam);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", this.zziao.zzu(new Bundle()));
        bundle.putBundle("extras", this.mExtras);
    }
    
    public void writeToParcel(final Parcel parcel, int n) {
        final int n2 = 1;
        parcel.writeString(this.zziai);
        parcel.writeString(this.mTag);
        if (this.zziaj) {
            n = 1;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
        if (this.zziak) {
            n = n2;
        }
        else {
            n = 0;
        }
        parcel.writeInt(n);
    }
    
    public abstract static class Builder
    {
        protected Bundle extras;
        protected String gcmTaskService;
        protected boolean isPersisted;
        protected int requiredNetworkState;
        protected boolean requiresCharging;
        protected String tag;
        protected boolean updateCurrent;
        protected zzi zziap;
        
        public Builder() {
            this.zziap = zzi.zziad;
        }
        
        public abstract Task build();
        
        @CallSuper
        protected void checkConditions() {
            zzbq.checkArgument(this.gcmTaskService != null, (Object)"Must provide an endpoint for this task by calling setService(ComponentName).");
            GcmNetworkManager.zzhp(this.tag);
            final zzi zziap = this.zziap;
            if (zziap != null) {
                final int zzaul = zziap.zzaul();
                if (zzaul != 1 && zzaul != 0) {
                    throw new IllegalArgumentException(new StringBuilder(45).append("Must provide a valid RetryPolicy: ").append(zzaul).toString());
                }
                final int zzaum = zziap.zzaum();
                final int zzaun = zziap.zzaun();
                if (zzaul == 0 && zzaum < 0) {
                    throw new IllegalArgumentException(new StringBuilder(52).append("InitialBackoffSeconds can't be negative: ").append(zzaum).toString());
                }
                if (zzaul == 1 && zzaum < 10) {
                    throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                }
                if (zzaun < zzaum) {
                    throw new IllegalArgumentException(new StringBuilder(77).append("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: ").append(zziap.zzaun()).toString());
                }
            }
            if (this.isPersisted) {
                Task.zzv(this.extras);
            }
        }
        
        public abstract Builder setExtras(final Bundle p0);
        
        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public abstract Builder setPersisted(final boolean p0);
        
        public abstract Builder setRequiredNetwork(final int p0);
        
        public abstract Builder setRequiresCharging(final boolean p0);
        
        public abstract Builder setService(final Class<? extends GcmTaskService> p0);
        
        public abstract Builder setTag(final String p0);
        
        public abstract Builder setUpdateCurrent(final boolean p0);
    }
}
