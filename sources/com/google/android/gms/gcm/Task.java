package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.support.annotation.CallSuper;
import android.support.annotation.RequiresPermission;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;

public class Task implements ReflectedParcelable {
    public static final int EXTRAS_LIMIT_BYTES = 10240;
    public static final int NETWORK_STATE_ANY = 2;
    public static final int NETWORK_STATE_CONNECTED = 0;
    public static final int NETWORK_STATE_UNMETERED = 1;
    protected static final long UNINITIALIZED = -1;
    private final Bundle mExtras;
    private final String mTag;
    private final String zziai;
    private final boolean zziaj;
    private final boolean zziak;
    private final int zzial;
    private final boolean zziam;
    private final boolean zzian;
    private final zzi zziao;

    public static abstract class Builder {
        protected Bundle extras;
        protected String gcmTaskService;
        protected boolean isPersisted;
        protected int requiredNetworkState;
        protected boolean requiresCharging;
        protected String tag;
        protected boolean updateCurrent;
        protected zzi zziap = zzi.zziad;

        public abstract Task build();

        @CallSuper
        protected void checkConditions() {
            zzbq.checkArgument(this.gcmTaskService != null, "Must provide an endpoint for this task by calling setService(ComponentName).");
            GcmNetworkManager.zzhp(this.tag);
            zzi zzi = this.zziap;
            if (zzi != null) {
                int zzaul = zzi.zzaul();
                if (zzaul == 1 || zzaul == 0) {
                    int zzaum = zzi.zzaum();
                    int zzaun = zzi.zzaun();
                    if (zzaul == 0 && zzaum < 0) {
                        throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + zzaum);
                    } else if (zzaul == 1 && zzaum < 10) {
                        throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
                    } else if (zzaun < zzaum) {
                        throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + zzi.zzaun());
                    }
                }
                throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + zzaul);
            }
            if (this.isPersisted) {
                Task.zzv(this.extras);
            }
        }

        public abstract Builder setExtras(Bundle bundle);

        @RequiresPermission("android.permission.RECEIVE_BOOT_COMPLETED")
        public abstract Builder setPersisted(boolean z);

        public abstract Builder setRequiredNetwork(int i);

        public abstract Builder setRequiresCharging(boolean z);

        public abstract Builder setService(Class<? extends GcmTaskService> cls);

        public abstract Builder setTag(String str);

        public abstract Builder setUpdateCurrent(boolean z);
    }

    @Deprecated
    Task(Parcel parcel) {
        boolean z = true;
        Log.e("Task", "Constructing a Task object using a parcel.");
        this.zziai = parcel.readString();
        this.mTag = parcel.readString();
        this.zziaj = parcel.readInt() == 1;
        if (parcel.readInt() != 1) {
            z = false;
        }
        this.zziak = z;
        this.zzial = 2;
        this.zziam = false;
        this.zzian = false;
        this.zziao = zzi.zziad;
        this.mExtras = null;
    }

    Task(Builder builder) {
        this.zziai = builder.gcmTaskService;
        this.mTag = builder.tag;
        this.zziaj = builder.updateCurrent;
        this.zziak = builder.isPersisted;
        this.zzial = builder.requiredNetworkState;
        this.zziam = builder.requiresCharging;
        this.zzian = false;
        this.mExtras = builder.extras;
        this.zziao = builder.zziap != null ? builder.zziap : zzi.zziad;
    }

    public static void zzv(Bundle bundle) {
        if (bundle != null) {
            Parcel obtain = Parcel.obtain();
            bundle.writeToParcel(obtain, 0);
            int dataSize = obtain.dataSize();
            obtain.recycle();
            if (dataSize > EXTRAS_LIMIT_BYTES) {
                String str = "Extras exceeding maximum size(10240 bytes): ";
                throw new IllegalArgumentException(new StringBuilder(String.valueOf(str).length() + 11).append(str).append(dataSize).toString());
            }
            for (String str2 : bundle.keySet()) {
                Object obj = bundle.get(str2);
                dataSize = ((obj instanceof Integer) || (obj instanceof Long) || (obj instanceof Double) || (obj instanceof String) || (obj instanceof Boolean)) ? 1 : 0;
                if (dataSize == 0) {
                    if (obj instanceof Bundle) {
                        zzv((Bundle) obj);
                    } else {
                        throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, Boolean, and nested Bundles with the same restrictions.");
                    }
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

    public void toBundle(Bundle bundle) {
        bundle.putString("tag", this.mTag);
        bundle.putBoolean("update_current", this.zziaj);
        bundle.putBoolean("persisted", this.zziak);
        bundle.putString(NotificationCompat.CATEGORY_SERVICE, this.zziai);
        bundle.putInt("requiredNetwork", this.zzial);
        bundle.putBoolean("requiresCharging", this.zziam);
        bundle.putBoolean("requiresIdle", false);
        bundle.putBundle("retryStrategy", this.zziao.zzu(new Bundle()));
        bundle.putBundle("extras", this.mExtras);
    }

    public void writeToParcel(Parcel parcel, int i) {
        int i2 = 1;
        parcel.writeString(this.zziai);
        parcel.writeString(this.mTag);
        parcel.writeInt(this.zziaj ? 1 : 0);
        if (!this.zziak) {
            i2 = 0;
        }
        parcel.writeInt(i2);
    }
}
