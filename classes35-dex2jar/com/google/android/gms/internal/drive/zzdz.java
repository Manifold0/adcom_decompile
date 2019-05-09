// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource;

final class zzdz implements MetadataResult
{
    private final Status zzdw;
    private final Metadata zzgp;
    
    public zzdz(final Status zzdw, final Metadata zzgp) {
        this.zzdw = zzdw;
        this.zzgp = zzgp;
    }
    
    @Override
    public final Metadata getMetadata() {
        return this.zzgp;
    }
    
    public final Status getStatus() {
        return this.zzdw;
    }
}
