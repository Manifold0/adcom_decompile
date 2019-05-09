// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.common.data.Freezable;
import android.os.Parcelable;

public interface Snapshot extends Parcelable, Freezable<Snapshot>
{
    SnapshotMetadata getMetadata();
    
    SnapshotContents getSnapshotContents();
}
