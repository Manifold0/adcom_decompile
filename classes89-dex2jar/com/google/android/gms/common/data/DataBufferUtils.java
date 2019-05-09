// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;
import java.util.ArrayList;
import com.google.android.gms.common.annotation.KeepForSdk;

public final class DataBufferUtils
{
    @KeepForSdk
    public static final String KEY_NEXT_PAGE_TOKEN = "next_page_token";
    @KeepForSdk
    public static final String KEY_PREV_PAGE_TOKEN = "prev_page_token";
    
    private DataBufferUtils() {
    }
    
    public static <T, E extends Freezable<T>> ArrayList<T> freezeAndClose(final DataBuffer<E> dataBuffer) {
        final ArrayList<Object> list = new ArrayList<Object>(dataBuffer.getCount());
        try {
            final Iterator<E> iterator = dataBuffer.iterator();
            while (iterator.hasNext()) {
                list.add(iterator.next().freeze());
            }
        }
        finally {
            dataBuffer.close();
        }
        dataBuffer.close();
        return;
    }
    
    public static boolean hasData(final DataBuffer<?> dataBuffer) {
        return dataBuffer != null && dataBuffer.getCount() > 0;
    }
    
    public static boolean hasNextPage(final DataBuffer<?> dataBuffer) {
        final Bundle metadata = dataBuffer.getMetadata();
        return metadata != null && metadata.getString("next_page_token") != null;
    }
    
    public static boolean hasPrevPage(final DataBuffer<?> dataBuffer) {
        final Bundle metadata = dataBuffer.getMetadata();
        return metadata != null && metadata.getString("prev_page_token") != null;
    }
}
