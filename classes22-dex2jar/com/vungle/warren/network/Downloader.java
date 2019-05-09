// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.network;

import java.io.IOException;
import java.io.File;
import android.support.annotation.NonNull;

public interface Downloader
{
    boolean download(@NonNull final String p0, @NonNull final File p1, @NonNull final Listener p2) throws IOException, IllegalArgumentException, IllegalStateException;
    
    void pause();
    
    void resume();
    
    public interface Listener
    {
        void onComplete(final File p0);
        
        void onError(final Throwable p0);
        
        void onProgress(final int p0);
    }
}
