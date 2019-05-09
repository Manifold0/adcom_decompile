package com.google.android.gms.auth;

import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.internal.auth.zzay;
import com.google.android.gms.internal.auth.zzf;
import java.io.IOException;

final class zzi implements zzj<Boolean> {
    private final /* synthetic */ String zzt;

    zzi(String str) {
        this.zzt = str;
    }

    public final /* synthetic */ Object zzb(IBinder iBinder) throws RemoteException, IOException, GoogleAuthException {
        Bundle bundle = (Bundle) zzd.zza(zzf.zza(iBinder).zza(this.zzt));
        String string = bundle.getString("Error");
        Intent intent = (Intent) bundle.getParcelable("userRecoveryIntent");
        zzay zzc = zzay.zzc(string);
        if (zzay.SUCCESS.equals(zzc)) {
            return Boolean.valueOf(true);
        }
        if (zzay.zza(zzc)) {
            Object[] objArr = new Object[1];
            String valueOf = String.valueOf(zzc);
            objArr[0] = new StringBuilder(String.valueOf(valueOf).length() + 31).append("isUserRecoverableError status: ").append(valueOf).toString();
            zzd.zzn.w("GoogleAuthUtil", objArr);
            throw new UserRecoverableAuthException(string, intent);
        }
        throw new GoogleAuthException(string);
    }
}
