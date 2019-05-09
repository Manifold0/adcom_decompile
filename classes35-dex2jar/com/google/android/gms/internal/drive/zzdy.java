// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzdy extends zzl
{
    private final BaseImplementation$ResultHolder<DriveResource.MetadataResult> zzdv;
    
    public zzdy(final BaseImplementation$ResultHolder<DriveResource.MetadataResult> zzdv) {
        this.zzdv = zzdv;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzdz(status, null));
    }
    
    @Override
    public final void zza(final zzfs zzfs) throws RemoteException {
        this.zzdv.setResult((Object)new zzdz(Status.RESULT_SUCCESS, new zzaa(zzfs.zzdl)));
    }
}
