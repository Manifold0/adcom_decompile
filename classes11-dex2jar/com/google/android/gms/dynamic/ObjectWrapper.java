// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import java.lang.reflect.Field;
import android.os.IBinder;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ObjectWrapper<T> extends Stub
{
    private final T zzib;
    
    private ObjectWrapper(final T zzib) {
        this.zzib = zzib;
    }
    
    @KeepForSdk
    public static <T> T unwrap(final IObjectWrapper objectWrapper) {
        int n = 0;
        if (objectWrapper instanceof ObjectWrapper) {
            return (T)((ObjectWrapper)objectWrapper).zzib;
        }
        final IBinder binder = objectWrapper.asBinder();
        final Field[] declaredFields = binder.getClass().getDeclaredFields();
        Field field = null;
        for (int length = declaredFields.length, i = 0; i < length; ++i) {
            final Field field2 = declaredFields[i];
            if (!field2.isSynthetic()) {
                ++n;
                field = field2;
            }
        }
        if (n == 1) {
            if (!field.isAccessible()) {
                field.setAccessible(true);
                try {
                    return (T)field.get(binder);
                }
                catch (NullPointerException ex) {
                    throw new IllegalArgumentException("Binder object is null.", ex);
                }
                catch (IllegalAccessException ex2) {
                    throw new IllegalArgumentException("Could not access the field in remoteBinder.", ex2);
                }
            }
            throw new IllegalArgumentException("IObjectWrapper declared field not private!");
        }
        throw new IllegalArgumentException(new StringBuilder(64).append("Unexpected number of IObjectWrapper declared fields: ").append(declaredFields.length).toString());
    }
    
    @KeepForSdk
    public static <T> IObjectWrapper wrap(final T t) {
        return new ObjectWrapper<Object>(t);
    }
}
