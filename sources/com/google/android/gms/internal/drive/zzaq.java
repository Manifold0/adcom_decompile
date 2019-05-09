package com.google.android.gms.internal.drive;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi.MetadataBufferResult;
import com.google.android.gms.drive.MetadataBuffer;

public final class zzaq implements MetadataBufferResult {
    private final Status zzdw;
    private final MetadataBuffer zzdx;
    private final boolean zzdy;

    public zzaq(Status status, MetadataBuffer metadataBuffer, boolean z) {
        this.zzdw = status;
        this.zzdx = metadataBuffer;
        this.zzdy = z;
    }

    public final MetadataBuffer getMetadataBuffer() {
        return this.zzdx;
    }

    public final Status getStatus() {
        return this.zzdw;
    }

    public final void release() {
        if (this.zzdx != null) {
            this.zzdx.release();
        }
    }
}
