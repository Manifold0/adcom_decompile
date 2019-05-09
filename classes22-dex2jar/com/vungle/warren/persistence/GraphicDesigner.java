// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import java.io.IOException;
import android.util.Log;
import com.vungle.warren.utility.FileUtility;
import android.support.annotation.NonNull;
import java.io.File;

public class GraphicDesigner implements Designer
{
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG;
    private File cacheDir;
    
    static {
        TAG = GraphicDesigner.class.getSimpleName();
    }
    
    public GraphicDesigner(@NonNull final File cacheDir) {
        this.cacheDir = cacheDir;
        FileUtility.printDirectoryTree(this.getCacheDirectory());
    }
    
    @Override
    public void clearCache() {
        if (this.cacheDir != null) {
            final File file = new File(this.cacheDir.getPath() + File.separator + "vungle");
            while (true) {
                if (!file.exists()) {
                    break Label_0058;
                }
                try {
                    FileUtility.delete(file);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                }
                catch (IOException ex) {
                    Log.e(GraphicDesigner.TAG, "Failed to delete cached files. Reason: " + ex.getLocalizedMessage());
                    continue;
                }
                break;
            }
        }
    }
    
    @Override
    public void deleteAssets(final String s) throws IOException, IllegalStateException {
        final File[] listFiles = this.getCacheDirectory().listFiles();
        if (listFiles != null) {
            for (int length = listFiles.length, i = 0; i < length; ++i) {
                final File file = listFiles[i];
                if (file.isDirectory() && file.getName().equals(s)) {
                    FileUtility.delete(file);
                }
            }
        }
    }
    
    @Override
    public File getAssetDirectory(final String s) throws IllegalStateException {
        final File file = new File(this.getCacheDirectory().getPath() + File.separator + s);
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
    
    @Override
    public File getCacheDirectory() throws IllegalStateException {
        if (this.cacheDir == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        final File file = new File(this.cacheDir.getPath() + File.separator + "vungle");
        if (!file.exists()) {
            file.mkdir();
        }
        return file;
    }
    
    @Override
    public boolean hasAssetsFor(final String s, final int n) throws IllegalStateException {
        final File[] listFiles = this.getCacheDirectory().listFiles();
        if (listFiles != null) {
            final int length = listFiles.length;
            int i = 0;
            while (i < length) {
                final File file = listFiles[i];
                if (file.isDirectory() && file.getName().equals(s)) {
                    if (file.list().length >= n) {
                        return true;
                    }
                    break;
                }
                else {
                    ++i;
                }
            }
        }
        return false;
    }
}
