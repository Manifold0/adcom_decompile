// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.AbstractDataBuffer;

public final class SnapshotMetadataBuffer extends AbstractDataBuffer<SnapshotMetadata>
{
    public SnapshotMetadataBuffer(final DataHolder dataHolder) {
        super(dataHolder);
    }
    
    public final SnapshotMetadata get(final int n) {
        return new SnapshotMetadataRef(this.mDataHolder, n);
    }
}
