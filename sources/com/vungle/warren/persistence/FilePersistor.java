package com.vungle.warren.persistence;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.vungle.warren.persistence.Persistor.MigrationCallback;
import com.vungle.warren.persistence.Persistor.Transformation;
import com.vungle.warren.utility.FileUtility;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class FilePersistor implements Persistor {
    private static final String TAG = FilePersistor.class.getSimpleName();
    static final String VUNGLE = "vungle";
    private static final String V_PREFIX = "V";
    private Integer currentVersion;
    private File filesDir;

    private interface TransformationReader {
        @NonNull
        byte[] readBytes(@NonNull File file);
    }

    /* renamed from: com.vungle.warren.persistence.FilePersistor$3 */
    class C01433 implements TransformationReader {
        C01433() {
        }

        public byte[] readBytes(@NonNull File file) {
            byte[] bytes = null;
            try {
                bytes = FileUtility.extractBytes(file);
            } catch (IOException e) {
                Log.e(FilePersistor.TAG, "Failed restore " + file.getName() + " " + e.getLocalizedMessage());
            }
            if (bytes == null || bytes.length <= 1) {
                return new byte[0];
            }
            return Arrays.copyOfRange(bytes, 1, bytes.length);
        }
    }

    static class Version implements Memorable {
        public static final String ID = "Data";
        private final String reason;
        private final long timestamp;
        private final int versionInt;

        public Version(int versionInt, @NonNull String reason, long timestamp) {
            this.versionInt = versionInt;
            this.reason = reason;
            this.timestamp = timestamp;
        }

        public Version(byte[] bytes) {
            ByteBuffer buffy = ByteBuffer.wrap(bytes);
            this.versionInt = buffy.getInt();
            this.reason = MemoryUtils.extractString(buffy);
            this.timestamp = buffy.getLong();
        }

        public byte[] toByteArray() {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            try {
                out.write(MemoryUtils.toBytes(this.versionInt));
                MemoryUtils.write(this.reason, out);
                MemoryUtils.write(Long.valueOf(this.timestamp), out);
                return out.toByteArray();
            } catch (IOException e) {
                Log.e("Version#toByteArray()", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }

        @NonNull
        public String getId() {
            return ID;
        }

        public int getVersion() {
            return this.versionInt;
        }
    }

    public FilePersistor(File filesDir) {
        this.filesDir = new File(filesDir, VUNGLE);
    }

    public File getStorageDirectory() throws IllegalStateException {
        return makeWorkingDir();
    }

    @NonNull
    private File makeWorkingDir() {
        checkInitialized();
        File vungleDir = new File(this.filesDir, V_PREFIX + this.currentVersion);
        if (!vungleDir.exists()) {
            vungleDir.mkdirs();
        }
        return vungleDir;
    }

    private void checkInitialized() {
        if (this.filesDir == null || this.currentVersion == null) {
            throw new IllegalStateException("Working dir is null");
        }
    }

    public void init(int currentVersion, MigrationCallback migrationCallback) {
        this.currentVersion = Integer.valueOf(currentVersion);
        checkInitialized();
        Version version = (Version) find(Version.ID, Version.class);
        if (version == null || version.getVersion() != currentVersion) {
            int oldVersion = version == null ? 0 : version.getVersion();
            if (oldVersion > currentVersion) {
                Log.e(TAG, "downgrade is not supported performing destructive migration, old version = " + oldVersion + " current = " + currentVersion);
                migrationCallback.onDowngrade(oldVersion, currentVersion);
            } else {
                migrationCallback.onUpgrade(oldVersion, currentVersion);
            }
            save(new Version(currentVersion, "upgrade/new", System.currentTimeMillis()));
            scanAndDeleteNonRelevant();
            return;
        }
        scanAndDeleteNonRelevant();
    }

    private void scanAndDeleteNonRelevant() {
        final File dir = makeWorkingDir();
        File[] filesToDelete = this.filesDir.listFiles(new FileFilter() {
            public boolean accept(File file) {
                return !dir.getName().equals(file.getName());
            }
        });
        if (filesToDelete == null) {
            Log.d(TAG, "nothing was found for deletion during scanning");
            return;
        }
        for (File file : filesToDelete) {
            try {
                FileUtility.delete(file);
            } catch (IOException e) {
                Log.d(TAG, "error deletion during scanning " + e.getLocalizedMessage());
            }
        }
    }

    public boolean save(Memorable m) {
        FileNotFoundException e;
        Throwable th;
        IOException e2;
        Log.d(TAG, " Saving " + m);
        File dir = getStorageDirectory();
        if (dir == null || !dir.isDirectory()) {
            return false;
        }
        FileOutputStream fos = null;
        try {
            File out = new File(getStorageDirectory(), m.getId() + "." + m.getClass().getSimpleName());
            if (out.exists() && !out.delete()) {
                throw new IOException("Failed to delete previous version of memorable file!");
            } else if (out.createNewFile()) {
                FileOutputStream fos2 = new FileOutputStream(out);
                try {
                    fos2.write(m.toByteArray());
                    FileUtility.closeQuietly(fos2);
                    return true;
                } catch (FileNotFoundException e3) {
                    e = e3;
                    fos = fos2;
                    try {
                        e.printStackTrace();
                        FileUtility.closeQuietly(fos);
                        return false;
                    } catch (Throwable th2) {
                        th = th2;
                        FileUtility.closeQuietly(fos);
                        throw th;
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    fos = fos2;
                    e2.printStackTrace();
                    FileUtility.closeQuietly(fos);
                    return false;
                } catch (Throwable th3) {
                    th = th3;
                    fos = fos2;
                    FileUtility.closeQuietly(fos);
                    throw th;
                }
            } else {
                throw new IOException("Failed to create file for memorable!");
            }
        } catch (FileNotFoundException e5) {
            e = e5;
            e.printStackTrace();
            FileUtility.closeQuietly(fos);
            return false;
        } catch (IOException e6) {
            e2 = e6;
            e2.printStackTrace();
            FileUtility.closeQuietly(fos);
            return false;
        }
    }

    public boolean save(@NonNull List<Memorable> memorables) {
        if (memorables == null) {
            return false;
        }
        boolean ret = true;
        for (Memorable memorable : memorables) {
            ret &= save(memorable);
        }
        return ret;
    }

    @NonNull
    public <T extends Memorable> List<T> extractAll(@NonNull Class<T> clazz) {
        return extractAll(clazz, null);
    }

    @NonNull
    private <T extends Memorable> List<T> extractAll(@NonNull final Class<T> clazz, @Nullable final FilenameFilter fileFilter) {
        File dir = getStorageDirectory();
        if (dir == null || !dir.isDirectory() || !dir.exists()) {
            return Collections.emptyList();
        }
        File[] data = dir.listFiles(new FilenameFilter() {
            public boolean accept(File file, String s) {
                if ((fileFilter == null ? true : fileFilter.accept(file, s)) && s.endsWith(clazz.getSimpleName())) {
                    return true;
                }
                return false;
            }
        });
        if (data == null || data.length == 0) {
            return Collections.emptyList();
        }
        List<T> items = new ArrayList();
        for (File file : data) {
            if (file != null && file.exists()) {
                try {
                    T model = extractFrom(file, clazz);
                    if (model != null) {
                        items.add(model);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return items;
    }

    @Nullable
    public <T extends Memorable> T extractFrom(@NonNull File file, @NonNull Class<T> clazz) throws IOException {
        Exception e;
        try {
            if (FileUtility.extractBytes(file).length == 0) {
                return null;
            }
            return (Memorable) clazz.getDeclaredConstructor(new Class[]{byte[].class}).newInstance(new Object[]{bytes});
        } catch (FileNotFoundException e2) {
            e = e2;
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e3) {
            e = e3;
            e.printStackTrace();
            return null;
        } catch (InstantiationException e4) {
            e = e4;
            e.printStackTrace();
            return null;
        } catch (InvocationTargetException e5) {
            e = e5;
            e.printStackTrace();
            return null;
        } catch (NoSuchMethodException e6) {
            e = e6;
            e.printStackTrace();
            return null;
        }
    }

    public <T extends Memorable> T find(@NonNull String key, @NonNull Class<T> clazz) {
        T t = null;
        if (!key.contains(".")) {
            key = key + "." + clazz.getSimpleName();
        }
        File f = new File(getStorageDirectory() + "/" + key);
        if (f.exists()) {
            try {
                t = extractFrom(f, clazz);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return t;
    }

    @NonNull
    public <T extends Memorable> List<T> findAll(@NonNull Set<String> keys, Class<T> clazz) {
        if (keys == null || keys.isEmpty()) {
            return Collections.emptyList();
        }
        List<T> items = new ArrayList();
        for (String key : keys) {
            T item = find(key, clazz);
            if (item != null) {
                items.add(item);
            }
        }
        return items;
    }

    public boolean delete(Memorable m) {
        Log.d(TAG, " Delete " + m);
        File f = new File(getStorageDirectory() + File.separator + m.getId() + "." + m.getClass().getSimpleName());
        return f.exists() && f.delete();
    }

    public void clearCache() {
        File dir = this.filesDir;
        if (dir.exists()) {
            try {
                FileUtility.delete(dir);
            } catch (IOException e) {
                Log.e(TAG, "Failed to delete cached files. Reason: " + e.getLocalizedMessage());
            }
        }
        makeWorkingDir();
    }

    public <T extends Memorable> void migrateData(int oldVersion, int newVersion, @NonNull final Class<T> clazz, @NonNull Transformation<T> transformation) {
        if (oldVersion < 1) {
            TransformationReader reader = new C01433();
            File[] files = this.filesDir.listFiles(new FilenameFilter() {
                public boolean accept(File file, String s) {
                    return s.endsWith(clazz.getSimpleName());
                }
            });
            if (files == null) {
                Log.e(TAG, "Cannot read files during migration for " + clazz.getSimpleName());
                return;
            }
            for (File modelFile : files) {
                try {
                    byte[] data = reader.readBytes(modelFile);
                    FileUtility.delete(modelFile);
                    if (data.length != 0) {
                        try {
                            Memorable obj = (Memorable) transformation.transform(oldVersion, newVersion, data);
                            if (obj != null) {
                                save(obj);
                            }
                        } catch (Throwable th) {
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
