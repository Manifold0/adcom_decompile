package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveResource.MetadataResult;
import com.google.android.gms.drive.Metadata;

final class zzdz implements MetadataResult {
    private final Status zzdw;
    private final Metadata zzgp;

    public zzdz(Status status, Metadata metadata) {
        this.zzdw = status;
        this.zzgp = metadata;
    }

    public final Metadata getMetadata() {
        return this.zzgp;
    }

    public final Status getStatus() {
        return this.zzdw;
    }
}
