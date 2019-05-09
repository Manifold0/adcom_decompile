// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import java.util.Iterator;
import com.google.android.gms.internal.drive.zzaa;
import com.google.android.gms.internal.drive.zzhp;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.zzf;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class MetadataBuffer extends AbstractDataBuffer<Metadata>
{
    private zza zzas;
    
    public MetadataBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        dataHolder.getMetadata().setClassLoader(MetadataBuffer.class.getClassLoader());
    }
    
    public final Metadata get(final int n) {
        final zza zzas = this.zzas;
        if (zzas != null) {
            final zza zzas2 = zzas;
            if (zzas.row == n) {
                return zzas2;
            }
        }
        final zza zzas2 = new zza(this.mDataHolder, n);
        this.zzas = zzas2;
        return zzas2;
    }
    
    @Deprecated
    public final String getNextPageToken() {
        return null;
    }
    
    public final void release() {
        if (this.mDataHolder != null) {
            zzf.zza(this.mDataHolder);
        }
        super.release();
    }
    
    static final class zza extends Metadata
    {
        private final int row;
        private final DataHolder zzat;
        private final int zzau;
        
        public zza(final DataHolder zzat, final int row) {
            this.zzat = zzat;
            this.row = row;
            this.zzau = zzat.getWindowIndex(row);
        }
        
        public final boolean isDataValid() {
            return !this.zzat.isClosed();
        }
        
        @Override
        public final <T> T zza(final MetadataField<T> metadataField) {
            return metadataField.zza(this.zzat, this.row, this.zzau);
        }
    }
}
