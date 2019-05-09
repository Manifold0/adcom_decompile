// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import java.io.File;
import java.io.IOException;

public interface Designer
{
    void clearCache();
    
    void deleteAssets(final String p0) throws IOException;
    
    File getAssetDirectory(final String p0) throws IllegalStateException;
    
    File getCacheDirectory() throws IllegalStateException;
    
    boolean hasAssetsFor(final String p0, final int p1) throws IllegalStateException;
}
