// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.annotation.KeepForSdk;
import android.util.Log;
import android.support.annotation.NonNull;

public abstract class ResultCallbacks<R extends Result> implements ResultCallback<R>
{
    public abstract void onFailure(@NonNull final Status p0);
    
    @KeepForSdk
    @Override
    public final void onResult(@NonNull final R r) {
        final Status status = r.getStatus();
        if (status.isSuccess()) {
            this.onSuccess(r);
        }
        else {
            this.onFailure(status);
            if (r instanceof Releasable) {
                try {
                    ((Releasable)r).release();
                }
                catch (RuntimeException ex) {
                    final String value = String.valueOf(r);
                    Log.w("ResultCallbacks", new StringBuilder(String.valueOf(value).length() + 18).append("Unable to release ").append(value).toString(), (Throwable)ex);
                }
            }
        }
    }
    
    public abstract void onSuccess(@NonNull final R p0);
}
