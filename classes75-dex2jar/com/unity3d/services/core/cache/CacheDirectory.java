// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.core.cache;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import com.unity3d.services.core.log.DeviceLog;
import android.os.Environment;
import android.os.Build$VERSION;
import android.content.Context;
import java.io.File;

public class CacheDirectory
{
    private static final String TEST_FILE_NAME = "UnityAdsTest.txt";
    private String _cacheDirName;
    private File _cacheDirectory;
    private boolean _initialized;
    private CacheDirectoryType _type;
    
    public CacheDirectory(final String cacheDirName) {
        this._initialized = false;
        this._cacheDirectory = null;
        this._type = null;
        this._cacheDirName = cacheDirName;
    }
    
    public File createCacheDirectory(File file, final String s) {
        if (file == null) {
            file = null;
        }
        else {
            final File file2 = new File(file, s);
            file2.mkdirs();
            file = file2;
            if (!file2.isDirectory()) {
                return null;
            }
        }
        return file;
    }
    
    public File getCacheDirectory(final Context context) {
        if (this._initialized) {
            return this._cacheDirectory;
        }
        this._initialized = true;
        Label_0118: {
            if (Build$VERSION.SDK_INT > 18) {
                if ("mounted".equals(Environment.getExternalStorageState())) {
                    File cacheDirectory = null;
                    while (true) {
                        try {
                            cacheDirectory = this.createCacheDirectory(context.getExternalCacheDir(), this._cacheDirName);
                            if (this.testCacheDirectory(cacheDirectory)) {
                                this._cacheDirectory = cacheDirectory;
                                this._type = CacheDirectoryType.EXTERNAL;
                                DeviceLog.debug("Unity Ads is using external cache directory: " + cacheDirectory.getAbsolutePath());
                                return this._cacheDirectory;
                            }
                            break Label_0118;
                        }
                        catch (Exception ex) {
                            DeviceLog.exception("Creating external cache directory failed", ex);
                            continue;
                        }
                        break;
                    }
                }
                DeviceLog.debug("External media not mounted");
            }
        }
        final File filesDir = context.getFilesDir();
        if (this.testCacheDirectory(filesDir)) {
            this._cacheDirectory = filesDir;
            this._type = CacheDirectoryType.INTERNAL;
            DeviceLog.debug("Unity Ads is using internal cache directory: " + filesDir.getAbsolutePath());
            return this._cacheDirectory;
        }
        DeviceLog.error("Unity Ads failed to initialize cache directory");
        return null;
    }
    
    public CacheDirectoryType getType() {
        return this._type;
    }
    
    public boolean testCacheDirectory(final File file) {
        if (file == null || !file.isDirectory()) {
            return false;
        }
        byte[] array;
        int read;
        try {
            final byte[] bytes = "test".getBytes("UTF-8");
            array = new byte[bytes.length];
            final File file2 = new File(file, "UnityAdsTest.txt");
            final FileOutputStream fileOutputStream = new FileOutputStream(file2);
            fileOutputStream.write(bytes);
            fileOutputStream.flush();
            fileOutputStream.close();
            final FileInputStream fileInputStream = new FileInputStream(file2);
            read = fileInputStream.read(array, 0, array.length);
            fileInputStream.close();
            if (!file2.delete()) {
                DeviceLog.debug("Failed to delete testfile " + file2.getAbsoluteFile());
                return false;
            }
        }
        catch (Exception ex) {
            DeviceLog.debug("Unity Ads exception while testing cache directory " + file.getAbsolutePath() + ": " + ex.getMessage());
            return false;
        }
        if (read != array.length) {
            DeviceLog.debug("Read buffer size mismatch");
            return false;
        }
        if (new String(array, "UTF-8").equals("test")) {
            return true;
        }
        DeviceLog.debug("Read buffer content mismatch");
        return false;
    }
}
