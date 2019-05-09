// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.io.FileOutputStream;
import java.io.Closeable;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;
import java.lang.reflect.InvocationTargetException;
import java.io.FileNotFoundException;
import com.vungle.warren.utility.FileUtility;
import android.util.Log;
import java.io.FileFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import android.support.annotation.Nullable;
import java.io.FilenameFilter;
import android.support.annotation.NonNull;
import java.io.File;

public class FilePersistor implements Persistor
{
    private static final String TAG;
    static final String VUNGLE = "vungle";
    private static final String V_PREFIX = "V";
    private Integer currentVersion;
    private File filesDir;
    
    static {
        TAG = FilePersistor.class.getSimpleName();
    }
    
    public FilePersistor(final File file) {
        this.filesDir = new File(file, "vungle");
    }
    
    private void checkInitialized() {
        if (this.filesDir == null || this.currentVersion == null) {
            throw new IllegalStateException("Working dir is null");
        }
    }
    
    @NonNull
    private <T extends Memorable> List<T> extractAll(@NonNull final Class<T> clazz, @Nullable final FilenameFilter filenameFilter) {
        final File storageDirectory = this.getStorageDirectory();
        List<T> emptyList;
        if (storageDirectory == null || !storageDirectory.isDirectory() || !storageDirectory.exists()) {
            emptyList = Collections.emptyList();
        }
        else {
            final File[] listFiles = storageDirectory.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return (filenameFilter == null || filenameFilter.accept(file, s)) && s.endsWith(clazz.getSimpleName());
                }
            });
            if (listFiles == null || listFiles.length == 0) {
                return Collections.emptyList();
            }
            final ArrayList<T> list = new ArrayList<T>();
            final int length = listFiles.length;
            int n = 0;
            while (true) {
                emptyList = list;
                if (n >= length) {
                    break;
                }
                final File file = listFiles[n];
                while (true) {
                    if (file == null || !file.exists()) {
                        break Label_0126;
                    }
                    try {
                        final Memorable from = this.extractFrom(file, clazz);
                        if (from != null) {
                            list.add((T)from);
                        }
                        ++n;
                    }
                    catch (IOException ex) {
                        ex.printStackTrace();
                        continue;
                    }
                    break;
                }
            }
        }
        return emptyList;
    }
    
    @NonNull
    private File makeWorkingDir() {
        this.checkInitialized();
        final File file = new File(this.filesDir, "V" + this.currentVersion);
        if (!file.exists()) {
            file.mkdirs();
        }
        return file;
    }
    
    private void scanAndDeleteNonRelevant() {
        final File[] listFiles = this.filesDir.listFiles(new FileFilter() {
            final /* synthetic */ File val$dir = FilePersistor.this.makeWorkingDir();
            
            @Override
            public boolean accept(final File file) {
                return !this.val$dir.getName().equals(file.getName());
            }
        });
        if (listFiles == null) {
            Log.d(FilePersistor.TAG, "nothing was found for deletion during scanning");
        }
        else {
            final int length = listFiles.length;
            int i = 0;
            while (i < length) {
                final File file = listFiles[i];
                while (true) {
                    try {
                        FileUtility.delete(file);
                        ++i;
                    }
                    catch (IOException ex) {
                        Log.d(FilePersistor.TAG, "error deletion during scanning " + ex.getLocalizedMessage());
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    @Override
    public void clearCache() {
        final File filesDir = this.filesDir;
        while (true) {
            if (!filesDir.exists()) {
                break Label_0016;
            }
            try {
                FileUtility.delete(filesDir);
                this.makeWorkingDir();
            }
            catch (IOException ex) {
                Log.e(FilePersistor.TAG, "Failed to delete cached files. Reason: " + ex.getLocalizedMessage());
                continue;
            }
            break;
        }
    }
    
    @Override
    public boolean delete(final Memorable memorable) {
        Log.d(FilePersistor.TAG, " Delete " + memorable);
        final File file = new File(this.getStorageDirectory() + File.separator + memorable.getId() + "." + memorable.getClass().getSimpleName());
        return file.exists() && file.delete();
    }
    
    @NonNull
    @Override
    public <T extends Memorable> List<T> extractAll(@NonNull final Class<T> clazz) {
        return this.extractAll(clazz, null);
    }
    
    @Nullable
    @Override
    public <T extends Memorable> T extractFrom(@NonNull File bytes, @NonNull final Class<T> clazz) throws IOException {
        try {
            bytes = (InstantiationException)(Object)FileUtility.extractBytes((File)bytes);
            if (bytes.length == 0) {
                return null;
            }
            bytes = (InstantiationException)clazz.getDeclaredConstructor(byte[].class).newInstance(bytes);
            return (T)bytes;
        }
        catch (FileNotFoundException ex) {}
        catch (InstantiationException bytes) {
            goto Label_0043;
        }
        catch (IllegalAccessException bytes) {
            goto Label_0043;
        }
        catch (NoSuchMethodException bytes) {
            goto Label_0043;
        }
        catch (InvocationTargetException bytes) {
            goto Label_0043;
        }
    }
    
    @Override
    public <T extends Memorable> T find(@NonNull final String s, @NonNull final Class<T> clazz) {
        final Memorable memorable = null;
        String string = s;
        if (!s.contains(".")) {
            string = s + "." + clazz.getSimpleName();
        }
        final File file = new File(this.getStorageDirectory() + "/" + string);
        Memorable from = memorable;
        if (!file.exists()) {
            return (T)from;
        }
        try {
            from = this.extractFrom(file, clazz);
            return (T)from;
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    @NonNull
    @Override
    public <T extends Memorable> List<T> findAll(@NonNull final Set<String> set, final Class<T> clazz) {
        List<T> emptyList;
        if (set == null || set.isEmpty()) {
            emptyList = Collections.emptyList();
        }
        else {
            final ArrayList<T> list = new ArrayList<T>();
            final Iterator<String> iterator = set.iterator();
            while (true) {
                emptyList = list;
                if (!iterator.hasNext()) {
                    break;
                }
                final Memorable find = this.find(iterator.next(), clazz);
                if (find == null) {
                    continue;
                }
                list.add((T)find);
            }
        }
        return emptyList;
    }
    
    @Override
    public File getStorageDirectory() throws IllegalStateException {
        return this.makeWorkingDir();
    }
    
    @Override
    public void init(final int n, final MigrationCallback migrationCallback) {
        this.currentVersion = n;
        this.checkInitialized();
        final Version version = this.find("Data", Version.class);
        if (version != null && version.getVersion() == n) {
            this.scanAndDeleteNonRelevant();
            return;
        }
        int version2;
        if (version == null) {
            version2 = 0;
        }
        else {
            version2 = version.getVersion();
        }
        if (version2 > n) {
            Log.e(FilePersistor.TAG, "downgrade is not supported performing destructive migration, old version = " + version2 + " current = " + n);
            migrationCallback.onDowngrade(version2, n);
        }
        else {
            migrationCallback.onUpgrade(version2, n);
        }
        this.save(new Version(n, "upgrade/new", System.currentTimeMillis()));
        this.scanAndDeleteNonRelevant();
    }
    
    @Override
    public <T extends Memorable> void migrateData(final int n, final int n2, @NonNull final Class<T> clazz, @NonNull final Transformation<T> transformation) {
        if (n < 1) {
            final TransformationReader transformationReader = new TransformationReader() {
                @Override
                public byte[] readBytes(@NonNull File bytes) {
                    final Object o = null;
                    while (true) {
                        try {
                            bytes = FileUtility.extractBytes((File)bytes);
                            if (bytes == null || bytes.length <= 1) {
                                return new byte[0];
                            }
                        }
                        catch (IOException ex) {
                            Log.e(FilePersistor.TAG, "Failed restore " + ((File)bytes).getName() + " " + ex.getLocalizedMessage());
                            bytes = o;
                            continue;
                        }
                        break;
                    }
                    return Arrays.copyOfRange((byte[])bytes, 1, bytes.length);
                }
            };
            final File[] listFiles = this.filesDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.endsWith(clazz.getSimpleName());
                }
            });
            if (listFiles == null) {
                Log.e(FilePersistor.TAG, "Cannot read files during migration for " + clazz.getSimpleName());
            }
            else {
                final int length = listFiles.length;
                int i = 0;
                while (i < length) {
                    final File file = listFiles[i];
                    while (true) {
                        try {
                            final byte[] bytes = ((TransformationReader)transformationReader).readBytes(file);
                            FileUtility.delete(file);
                            if (bytes.length != 0) {
                                try {
                                    final Memorable memorable = transformation.transform(n, n2, bytes);
                                    if (memorable != null) {
                                        this.save(memorable);
                                    }
                                }
                                catch (Throwable t) {}
                            }
                            ++i;
                        }
                        catch (IOException ex) {
                            ex.printStackTrace();
                            continue;
                        }
                        break;
                    }
                }
            }
        }
    }
    
    @Override
    public boolean save(final Memorable memorable) {
        Log.d(FilePersistor.TAG, " Saving " + memorable);
        final File storageDirectory = this.getStorageDirectory();
        if (storageDirectory == null || !storageDirectory.isDirectory()) {
            return false;
        }
        final Closeable closeable = null;
        FileOutputStream fileOutputStream = null;
        final Closeable closeable2 = null;
        Object o = fileOutputStream;
        Object o2 = null;
        Label_0151: {
            try {
                final File file = new File(this.getStorageDirectory(), memorable.getId() + "." + memorable.getClass().getSimpleName());
                o = fileOutputStream;
                if (!file.exists()) {
                    break Label_0151;
                }
                o = fileOutputStream;
                if (!file.delete()) {
                    o = fileOutputStream;
                    throw new IOException("Failed to delete previous version of memorable file!");
                }
                break Label_0151;
            }
            catch (FileNotFoundException fileOutputStream) {
                o2 = (o = closeable2);
                ((Throwable)fileOutputStream).printStackTrace();
                FileUtility.closeQuietly((Closeable)o2);
                return false;
                while (true) {
                    o = fileOutputStream;
                    throw new IOException("Failed to create file for memorable!");
                    o = fileOutputStream;
                    continue;
                }
            }
            // iftrue(Label_0190:, file.createNewFile())
            catch (IOException fileOutputStream) {
                o2 = (o = closeable);
                ((Throwable)fileOutputStream).printStackTrace();
                FileUtility.closeQuietly((Closeable)o2);
                return false;
                Label_0190: {
                    o = fileOutputStream;
                }
                final File file;
                final FileOutputStream fileOutputStream2;
                fileOutputStream = (fileOutputStream2 = new FileOutputStream(file));
                final Object o3 = o2;
                final byte[] array = ((Memorable)o3).toByteArray();
                fileOutputStream2.write(array);
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                FileUtility.closeQuietly(fileOutputStream3);
                return true;
            }
            finally {
                final Closeable closeable3;
                o2 = closeable3;
            }
        }
        while (true) {
            try {
                final FileOutputStream fileOutputStream2 = fileOutputStream;
                final Object o3 = o2;
                final byte[] array = ((Memorable)o3).toByteArray();
                fileOutputStream2.write(array);
                final FileOutputStream fileOutputStream3 = fileOutputStream;
                FileUtility.closeQuietly(fileOutputStream3);
                return true;
                FileUtility.closeQuietly((Closeable)o);
                throw o2;
            }
            catch (IOException ex) {}
            catch (FileNotFoundException ex2) {}
            finally {
                o = fileOutputStream;
                continue;
            }
            break;
        }
    }
    
    @Override
    public boolean save(@NonNull final List<Memorable> list) {
        int n;
        if (list == null) {
            n = 0;
        }
        else {
            int n2 = 1;
            final Iterator<Memorable> iterator = list.iterator();
            while (true) {
                n = n2;
                if (!iterator.hasNext()) {
                    break;
                }
                n2 &= (this.save(iterator.next()) ? 1 : 0);
            }
        }
        return n != 0;
    }
    
    private interface TransformationReader
    {
        @NonNull
        byte[] readBytes(@NonNull final File p0);
    }
    
    static class Version implements Memorable
    {
        public static final String ID = "Data";
        private final String reason;
        private final long timestamp;
        private final int versionInt;
        
        public Version(final int versionInt, @NonNull final String reason, final long timestamp) {
            this.versionInt = versionInt;
            this.reason = reason;
            this.timestamp = timestamp;
        }
        
        public Version(final byte[] array) {
            final ByteBuffer wrap = ByteBuffer.wrap(array);
            this.versionInt = wrap.getInt();
            this.reason = MemoryUtils.extractString(wrap);
            this.timestamp = wrap.getLong();
        }
        
        @NonNull
        @Override
        public String getId() {
            return "Data";
        }
        
        public int getVersion() {
            return this.versionInt;
        }
        
        @Override
        public byte[] toByteArray() {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                byteArrayOutputStream.write(MemoryUtils.toBytes(this.versionInt));
                MemoryUtils.write(this.reason, byteArrayOutputStream);
                MemoryUtils.write(this.timestamp, byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            catch (IOException ex) {
                Log.e("Version#toByteArray()", "Failed to write " + this + " to a byte array");
                return new byte[0];
            }
        }
    }
}
