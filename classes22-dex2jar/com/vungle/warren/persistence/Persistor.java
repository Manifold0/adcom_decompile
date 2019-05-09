// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.persistence;

import java.util.Set;
import android.support.annotation.Nullable;
import java.io.IOException;
import java.io.File;
import android.support.annotation.NonNull;
import java.util.List;

public interface Persistor
{
    void clearCache();
    
    boolean delete(final Memorable p0);
    
    @NonNull
     <T extends Memorable> List<T> extractAll(final Class<T> p0);
    
    @Nullable
     <T extends Memorable> T extractFrom(final File p0, final Class<T> p1) throws IOException;
    
    @Nullable
     <T extends Memorable> T find(final String p0, final Class<T> p1);
    
    @NonNull
     <T extends Memorable> List<T> findAll(@NonNull final Set<String> p0, @NonNull final Class<T> p1);
    
    File getStorageDirectory() throws IllegalStateException;
    
    void init(final int p0, final MigrationCallback p1);
    
     <T extends Memorable> void migrateData(final int p0, final int p1, @NonNull final Class<T> p2, @NonNull final Transformation<T> p3);
    
    boolean save(final Memorable p0);
    
    boolean save(final List<Memorable> p0);
    
    public interface MigrationCallback
    {
        void onDowngrade(final int p0, final int p1);
        
        void onUpgrade(final int p0, final int p1);
    }
    
    public interface Transformation<T>
    {
        @Nullable
        T transform(final int p0, final int p1, @NonNull final byte[] p2);
    }
}
