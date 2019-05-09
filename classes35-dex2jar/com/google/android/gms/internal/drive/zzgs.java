// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

public final class zzgs extends zzl
{
    private final BaseImplementation$ResultHolder<Status> zzdv;
    
    public zzgs(final BaseImplementation$ResultHolder<Status> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void onSuccess() throws RemoteException {
        this.zzdv.setResult((Object)Status.RESULT_SUCCESS);
    }
    
    @Override
    public final void zza(final Status result) throws RemoteException {
        this.zzdv.setResult((Object)result);
    }
}
