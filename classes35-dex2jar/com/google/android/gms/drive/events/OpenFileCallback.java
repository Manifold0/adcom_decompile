// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.events;

import android.support.annotation.NonNull;
import com.google.android.gms.drive.DriveContents;

public abstract class OpenFileCallback
{
    public abstract void onContents(@NonNull final DriveContents p0);
    
    public abstract void onError(@NonNull final Exception p0);
    
    public abstract void onProgress(final long p0, final long p1);
}
