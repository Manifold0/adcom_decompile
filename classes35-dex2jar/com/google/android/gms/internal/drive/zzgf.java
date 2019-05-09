// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.drive.DriveContents;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveFile;
import com.google.android.gms.drive.DriveApi;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzgf extends zzl
{
    private final BaseImplementation$ResultHolder<DriveApi.DriveContentsResult> zzdv;
    private final DriveFile.DownloadProgressListener zzia;
    
    zzgf(final BaseImplementation$ResultHolder<DriveApi.DriveContentsResult> zzdv, final DriveFile.DownloadProgressListener zzia) {
        this.zzdv = zzdv;
        this.zzia = zzia;
    }
    
    @Override
    public final void zza(final Status status) throws RemoteException {
        this.zzdv.setResult((Object)new zzal(status, null));
    }
    
    @Override
    public final void zza(final zzfb zzfb) throws RemoteException {
        Status result_SUCCESS;
        if (zzfb.zzhf) {
            result_SUCCESS = new Status(-1);
        }
        else {
            result_SUCCESS = Status.RESULT_SUCCESS;
        }
        this.zzdv.setResult((Object)new zzal(result_SUCCESS, new zzbi(zzfb.zzeq)));
    }
    
    @Override
    public final void zza(final zzff zzff) throws RemoteException {
        if (this.zzia != null) {
            this.zzia.onProgress(zzff.zzhi, zzff.zzhj);
        }
    }
}
