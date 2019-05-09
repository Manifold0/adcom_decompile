// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.tasks.TaskCompletionSource;

public final class zzhl extends zzhb<Void>
{
    public zzhl(final TaskCompletionSource<Void> taskCompletionSource) {
        super(taskCompletionSource);
    }
    
    @Override
    public final void onSuccess() throws RemoteException {
        TaskUtil.setResultOrApiException(Status.RESULT_SUCCESS, (TaskCompletionSource)this.zzap());
    }
}
