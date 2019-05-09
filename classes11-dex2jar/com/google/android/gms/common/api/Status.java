// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import android.content.IntentSender$SendIntentException;
import android.content.Intent;
import android.app.Activity;
import com.google.android.gms.common.internal.Objects;
import android.support.annotation.Nullable;
import android.app.PendingIntent;
import com.google.android.gms.common.util.VisibleForTesting;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@Class(creator = "StatusCreator")
public final class Status extends AbstractSafeParcelable implements Result, ReflectedParcelable
{
    public static final Parcelable$Creator<Status> CREATOR;
    @KeepForSdk
    public static final Status RESULT_CANCELED;
    @KeepForSdk
    public static final Status RESULT_DEAD_CLIENT;
    @KeepForSdk
    public static final Status RESULT_INTERNAL_ERROR;
    @KeepForSdk
    public static final Status RESULT_INTERRUPTED;
    @KeepForSdk
    @VisibleForTesting
    public static final Status RESULT_SUCCESS;
    @KeepForSdk
    public static final Status RESULT_TIMEOUT;
    private static final Status zzar;
    @VersionField(id = 1000)
    private final int zzg;
    @Field(getter = "getStatusCode", id = 1)
    private final int zzh;
    @Nullable
    @Field(getter = "getPendingIntent", id = 3)
    private final PendingIntent zzi;
    @Nullable
    @Field(getter = "getStatusMessage", id = 2)
    private final String zzj;
    
    static {
        RESULT_SUCCESS = new Status(0);
        RESULT_INTERRUPTED = new Status(14);
        RESULT_INTERNAL_ERROR = new Status(8);
        RESULT_TIMEOUT = new Status(15);
        RESULT_CANCELED = new Status(16);
        zzar = new Status(17);
        RESULT_DEAD_CLIENT = new Status(18);
        CREATOR = (Parcelable$Creator)new zzb();
    }
    
    @KeepForSdk
    public Status(final int n) {
        this(n, null);
    }
    
    @KeepForSdk
    @Constructor
    Status(@Param(id = 1000) final int zzg, @Param(id = 1) final int zzh, @Nullable @Param(id = 2) final String zzj, @Nullable @Param(id = 3) final PendingIntent zzi) {
        this.zzg = zzg;
        this.zzh = zzh;
        this.zzj = zzj;
        this.zzi = zzi;
    }
    
    @KeepForSdk
    public Status(final int n, @Nullable final String s) {
        this(1, n, s, null);
    }
    
    @KeepForSdk
    public Status(final int n, @Nullable final String s, @Nullable final PendingIntent pendingIntent) {
        this(1, n, s, pendingIntent);
    }
    
    @Override
    public final boolean equals(final Object o) {
        if (o instanceof Status) {
            final Status status = (Status)o;
            if (this.zzg == status.zzg && this.zzh == status.zzh && Objects.equal(this.zzj, status.zzj) && Objects.equal(this.zzi, status.zzi)) {
                return true;
            }
        }
        return false;
    }
    
    public final PendingIntent getResolution() {
        return this.zzi;
    }
    
    @KeepForSdk
    @Override
    public final Status getStatus() {
        return this;
    }
    
    public final int getStatusCode() {
        return this.zzh;
    }
    
    @Nullable
    public final String getStatusMessage() {
        return this.zzj;
    }
    
    @VisibleForTesting
    public final boolean hasResolution() {
        return this.zzi != null;
    }
    
    @Override
    public final int hashCode() {
        return Objects.hashCode(this.zzg, this.zzh, this.zzj, this.zzi);
    }
    
    public final boolean isCanceled() {
        return this.zzh == 16;
    }
    
    public final boolean isInterrupted() {
        return this.zzh == 14;
    }
    
    public final boolean isSuccess() {
        return this.zzh <= 0;
    }
    
    public final void startResolutionForResult(final Activity activity, final int n) throws IntentSender$SendIntentException {
        if (!this.hasResolution()) {
            return;
        }
        activity.startIntentSenderForResult(this.zzi.getIntentSender(), n, (Intent)null, 0, 0, 0);
    }
    
    @Override
    public final String toString() {
        return Objects.toStringHelper(this).add("statusCode", this.zzg()).add("resolution", this.zzi).toString();
    }
    
    @KeepForSdk
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeInt(parcel, 1, this.getStatusCode());
        SafeParcelWriter.writeString(parcel, 2, this.getStatusMessage(), false);
        SafeParcelWriter.writeParcelable(parcel, 3, (Parcelable)this.zzi, n, false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zzg);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
    }
    
    public final String zzg() {
        if (this.zzj != null) {
            return this.zzj;
        }
        return CommonStatusCodes.getStatusCodeString(this.zzh);
    }
}
