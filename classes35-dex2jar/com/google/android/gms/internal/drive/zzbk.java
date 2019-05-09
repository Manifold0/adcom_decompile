// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.BaseImplementation$ResultHolder;
import com.google.android.gms.common.api.Api$AnyClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.drive.zzn;
import com.google.android.gms.drive.MetadataChangeSet;

final class zzbk extends zzav
{
    private final /* synthetic */ zzbi zzet;
    private final /* synthetic */ MetadataChangeSet zzeu;
    private final /* synthetic */ zzn zzev;
    
    zzbk(final zzbi zzet, final GoogleApiClient googleApiClient, final MetadataChangeSet zzeu, final zzn zzev) {
        this.zzet = zzet;
        this.zzeu = zzeu;
        this.zzev = zzev;
        super(googleApiClient);
    }
}
