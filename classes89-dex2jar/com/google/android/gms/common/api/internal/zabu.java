// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import com.google.android.gms.common.internal.ApiExceptionUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.ConnectionResult;
import java.util.concurrent.CancellationException;
import com.google.android.gms.tasks.Task;
import android.app.Activity;
import com.google.android.gms.tasks.TaskCompletionSource;

public class zabu extends zal
{
    private TaskCompletionSource<Void> zajp;
    
    private zabu(final LifecycleFragment lifecycleFragment) {
        super(lifecycleFragment);
        this.zajp = (TaskCompletionSource<Void>)new TaskCompletionSource();
        this.mLifecycleFragment.addCallback("GmsAvailabilityHelper", (LifecycleCallback)this);
    }
    
    public static zabu zac(final Activity activity) {
        final LifecycleFragment fragment = getFragment(activity);
        final zabu zabu = (zabu)fragment.getCallbackOrNull("GmsAvailabilityHelper", (Class)zabu.class);
        if (zabu != null) {
            if (zabu.zajp.getTask().isComplete()) {
                zabu.zajp = (TaskCompletionSource<Void>)new TaskCompletionSource();
            }
            return zabu;
        }
        return new zabu(fragment);
    }
    
    public final Task<Void> getTask() {
        return (Task<Void>)this.zajp.getTask();
    }
    
    public void onDestroy() {
        super.onDestroy();
        this.zajp.trySetException((Exception)new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }
    
    @Override
    protected final void zaa(final ConnectionResult connectionResult, final int n) {
        this.zajp.setException((Exception)ApiExceptionUtil.fromStatus(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution())));
    }
    
    @Override
    protected final void zao() {
        final int googlePlayServicesAvailable = this.zacd.isGooglePlayServicesAvailable((Context)this.mLifecycleFragment.getLifecycleActivity());
        if (googlePlayServicesAvailable == 0) {
            this.zajp.setResult((Object)null);
        }
        else if (!this.zajp.getTask().isComplete()) {
            this.zab(new ConnectionResult(googlePlayServicesAvailable, (PendingIntent)null), 0);
        }
    }
}
