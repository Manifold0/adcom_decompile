// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.games.internal.zzr;

final class zzad implements zzr
{
    @Override
    public final boolean zza(@NonNull final Status status) {
        return status.isSuccess() || status.getStatusCode() == 5;
    }
}
