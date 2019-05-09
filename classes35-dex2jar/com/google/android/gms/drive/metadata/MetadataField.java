// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.metadata;

import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.common.data.DataHolder;
import android.os.Bundle;

public interface MetadataField<T>
{
    String getName();
    
    T zza(final Bundle p0);
    
    T zza(final DataHolder p0, final int p1, final int p2);
    
    void zza(final DataHolder p0, final MetadataBundle p1, final int p2, final int p3);
    
    void zza(final T p0, final Bundle p1);
}
