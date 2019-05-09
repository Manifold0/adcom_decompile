// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import java.util.Iterator;
import java.util.LinkedList;
import com.facebook.FacebookException;

public class CollectionMapper
{
    private CollectionMapper() {
    }
    
    public static <T> void iterate(final Collection<T> collection, final ValueMapper valueMapper, final OnMapperCompleteListener onMapperCompleteListener) {
        final Mutable mutable = new Mutable((T)false);
        final Mutable mutable2 = new Mutable((T)1);
        final OnMapperCompleteListener onMapperCompleteListener2 = new OnMapperCompleteListener() {
            @Override
            public void onComplete() {
                if (!(boolean)mutable.value) {
                    final Mutable val$pendingJobCount = mutable2;
                    final Integer value = (int)mutable2.value - 1;
                    val$pendingJobCount.value = (T)value;
                    if (value == 0) {
                        onMapperCompleteListener.onComplete();
                    }
                }
            }
            
            @Override
            public void onError(final FacebookException ex) {
                if (mutable.value) {
                    return;
                }
                mutable.value = (T)Boolean.valueOf(true);
                ((OnErrorListener)onMapperCompleteListener).onError(ex);
            }
        };
        final Iterator<T> keyIterator = collection.keyIterator();
        final LinkedList<T> list = new LinkedList<T>();
        while (keyIterator.hasNext()) {
            list.add(keyIterator.next());
        }
        for (final T next : list) {
            final Object value = collection.get(next);
            final OnMapValueCompleteListener onMapValueCompleteListener = new OnMapValueCompleteListener() {
                @Override
                public void onComplete(final Object o) {
                    collection.set(next, o, onMapperCompleteListener2);
                    ((OnMapperCompleteListener)onMapperCompleteListener2).onComplete();
                }
                
                @Override
                public void onError(final FacebookException ex) {
                    ((OnErrorListener)onMapperCompleteListener2).onError(ex);
                }
            };
            final Integer n = (Integer)mutable2.value;
            mutable2.value = (T)Integer.valueOf((int)mutable2.value + 1);
            valueMapper.mapValue(value, onMapValueCompleteListener);
        }
        ((OnMapperCompleteListener)onMapperCompleteListener2).onComplete();
    }
    
    public interface Collection<T>
    {
        Object get(final T p0);
        
        Iterator<T> keyIterator();
        
        void set(final T p0, final Object p1, final OnErrorListener p2);
    }
    
    public interface OnErrorListener
    {
        void onError(final FacebookException p0);
    }
    
    public interface OnMapValueCompleteListener extends OnErrorListener
    {
        void onComplete(final Object p0);
    }
    
    public interface OnMapperCompleteListener extends OnErrorListener
    {
        void onComplete();
    }
    
    public interface ValueMapper
    {
        void mapValue(final Object p0, final OnMapValueCompleteListener p1);
    }
}
