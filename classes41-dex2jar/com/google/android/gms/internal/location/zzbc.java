// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.location;

import android.os.RemoteException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;

final class zzbc extends zzar
{
    private BaseImplementation$ResultHolder<LocationSettingsResult> zzdf;
    
    public zzbc(final BaseImplementation$ResultHolder<LocationSettingsResult> zzdf) {
        Preconditions.checkArgument(zzdf != null, (Object)"listener can't be null.");
        this.zzdf = zzdf;
    }
    
    @Override
    public final void zza(final LocationSettingsResult result) throws RemoteException {
        this.zzdf.setResult((Object)result);
        this.zzdf = null;
    }
}
