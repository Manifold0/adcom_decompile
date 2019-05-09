// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.games.snapshot;

import com.google.android.gms.drive.Contents;
import java.io.IOException;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable;

public interface SnapshotContents extends Parcelable
{
    void close();
    
    ParcelFileDescriptor getParcelFileDescriptor();
    
    boolean isClosed();
    
    boolean modifyBytes(final int p0, final byte[] p1, final int p2, final int p3);
    
    byte[] readFully() throws IOException;
    
    boolean writeBytes(final byte[] p0);
    
    Contents zzcl();
}
