// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import com.google.android.gms.common.api.PendingResult;
import android.support.annotation.Nullable;
import com.google.android.gms.common.api.GoogleApiClient;

public interface DriveFile extends DriveResource
{
    public static final int MODE_READ_ONLY = 268435456;
    public static final int MODE_READ_WRITE = 805306368;
    public static final int MODE_WRITE_ONLY = 536870912;
    
    @Deprecated
    PendingResult<DriveApi.DriveContentsResult> open(final GoogleApiClient p0, final int p1, @Nullable final DownloadProgressListener p2);
    
    @Deprecated
    public interface DownloadProgressListener
    {
        void onProgress(final long p0, final long p1);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    public @interface OpenMode {
    }
}
