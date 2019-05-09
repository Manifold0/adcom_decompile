// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import android.content.IntentSender$SendIntentException;
import android.util.Log;
import com.google.android.gms.common.internal.Preconditions;
import android.support.annotation.NonNull;
import android.app.Activity;

public abstract class ResolvingResultCallbacks<R extends Result> extends ResultCallbacks<R>
{
    private final Activity mActivity;
    private final int zzao;
    
    protected ResolvingResultCallbacks(@NonNull final Activity activity, final int zzao) {
        this.mActivity = Preconditions.checkNotNull(activity, "Activity must not be null");
        this.zzao = zzao;
    }
    
    @KeepForSdk
    @Override
    public final void onFailure(@NonNull final Status status) {
        if (status.hasResolution()) {
            try {
                status.startResolutionForResult(this.mActivity, this.zzao);
                return;
            }
            catch (IntentSender$SendIntentException ex) {
                Log.e("ResolvingResultCallback", "Failed to start resolution", (Throwable)ex);
                this.onUnresolvableFailure(new Status(8));
                return;
            }
        }
        this.onUnresolvableFailure(status);
    }
    
    @Override
    public abstract void onSuccess(@NonNull final R p0);
    
    public abstract void onUnresolvableFailure(@NonNull final Status p0);
}
