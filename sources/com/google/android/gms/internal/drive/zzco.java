package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.AnyClient;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.drive.DriveFolder;
import com.google.android.gms.tasks.TaskCompletionSource;

final class zzco extends TaskApiCall<zzaw, DriveFolder> {
    zzco(zzch zzch) {
    }

    protected final /* synthetic */ void doExecute(AnyClient anyClient, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzaw zzaw = (zzaw) anyClient;
        if (zzaw.zzae() == null) {
            taskCompletionSource.setException(new ApiException(new Status(10, "Drive#SCOPE_APPFOLDER must be requested")));
        } else {
            taskCompletionSource.setResult(new zzbs(zzaw.zzae()));
        }
    }
}
