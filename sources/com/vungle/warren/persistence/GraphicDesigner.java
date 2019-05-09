package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.util.Log;
import com.vungle.warren.utility.FileUtility;
import java.io.File;
import java.io.IOException;

public class GraphicDesigner implements Designer {
    private static final String FOLDER_NAME = "vungle";
    private static final String TAG = GraphicDesigner.class.getSimpleName();
    private File cacheDir;

    public GraphicDesigner(@NonNull File cacheDir) {
        this.cacheDir = cacheDir;
        FileUtility.printDirectoryTree(getCacheDirectory());
    }

    public boolean hasAssetsFor(String identifier, int numOfAllDownloadedFiles) throws IllegalStateException {
        File[] files = getCacheDirectory().listFiles();
        if (files == null) {
            return false;
        }
        int length = files.length;
        int i = 0;
        while (i < length) {
            File f = files[i];
            if (!f.isDirectory() || !f.getName().equals(identifier)) {
                i++;
            } else if (f.list().length >= numOfAllDownloadedFiles) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public File getAssetDirectory(String identifier) throws IllegalStateException {
        File assetDir = new File(getCacheDirectory().getPath() + File.separator + identifier);
        if (!assetDir.exists()) {
            assetDir.mkdir();
        }
        return assetDir;
    }

    public void deleteAssets(String identifier) throws IOException, IllegalStateException {
        File[] files = getCacheDirectory().listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory() && f.getName().equals(identifier)) {
                    FileUtility.delete(f);
                }
            }
        }
    }

    public File getCacheDirectory() throws IllegalStateException {
        if (this.cacheDir == null) {
            throw new IllegalStateException("Context has expired, cannot continue.");
        }
        File dir = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
        if (!dir.exists()) {
            dir.mkdir();
        }
        return dir;
    }

    public void clearCache() {
        if (this.cacheDir != null) {
            File dir = new File(this.cacheDir.getPath() + File.separator + FOLDER_NAME);
            if (dir.exists()) {
                try {
                    FileUtility.delete(dir);
                } catch (IOException e) {
                    Log.e(TAG, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
                }
            }
            if (!dir.exists()) {
                dir.mkdir();
            }
        }
    }
}
