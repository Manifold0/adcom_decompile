// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.os.ParcelFileDescriptor;

final class zzsn extends zzaoj<ParcelFileDescriptor>
{
    private final /* synthetic */ zzsm zzbnn;
    
    zzsn(final zzsm zzbnn) {
        this.zzbnn = zzbnn;
    }
    
    @Override
    public final boolean cancel(final boolean b) {
        this.zzbnn.disconnect();
        return super.cancel(b);
    }
}
