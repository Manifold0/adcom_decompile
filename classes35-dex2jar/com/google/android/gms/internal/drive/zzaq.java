// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.drive;

import com.google.android.gms.drive.MetadataBuffer;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.drive.DriveApi;

public final class zzaq implements MetadataBufferResult
{
    private final Status zzdw;
    private final MetadataBuffer zzdx;
    private final boolean zzdy;
    
    public zzaq(final Status zzdw, final MetadataBuffer zzdx, final boolean zzdy) {
        this.zzdw = zzdw;
        this.zzdx = zzdx;
        this.zzdy = zzdy;
    }
    
    @Override
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
