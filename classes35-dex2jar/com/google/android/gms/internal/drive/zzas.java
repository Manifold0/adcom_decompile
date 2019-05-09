// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzas extends zzl
{
    private final BaseImplementation$ResultHolder<DriveApi.MetadataBufferResult> zzdv;
    
    zzas(final BaseImplementation$ResultHolder<DriveApi.MetadataBufferResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzaq(status, null, false));
    }
    
    @Override
    public final void zza(final zzfn zzfn) throws RemoteException {
        this.zzdv.setResult((Object)new zzaq(Status.RESULT_SUCCESS, new MetadataBuffer(zzfn.zzhs), zzfn.zzdy));
    }
}
