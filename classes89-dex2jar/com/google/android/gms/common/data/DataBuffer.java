// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.os.Bundle;
import com.google.android.gms.common.api.Releasable;

public interface DataBuffer<T> extends Releasable, Iterable<T>
{
    @Deprecated
    void close();
    
    T get(final int p0);
    
    int getCount();
    
    @KeepForSdk
    Bundle getMetadata();
    
    @Deprecated
    boolean isClosed();
    
    Iterator<T> iterator();
    
    void release();
    
    Iterator<T> singleRefIterator();
}
