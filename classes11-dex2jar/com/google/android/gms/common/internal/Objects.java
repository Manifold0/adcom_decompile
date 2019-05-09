// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import android.support.annotation.Nullable;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Objects
{
    private Objects() {
        throw new AssertionError((Object)"Uninstantiable");
    }
    
    @KeepForSdk
    public static boolean equal(@Nullable final Object o, @Nullable final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    @KeepForSdk
    public static int hashCode(final Object... array) {
        return Arrays.hashCode(array);
    }
    
    @KeepForSdk
    public static ToStringHelper toStringHelper(final Object o) {
        return new ToStringHelper(o, null);
    }
    
    @KeepForSdk
    public static final class ToStringHelper
    {
        private final List<String> zzer;
        private final Object zzes;
        
        private ToStringHelper(final Object o) {
            this.zzes = Preconditions.checkNotNull(o);
            this.zzer = new ArrayList<String>();
        }
        
        @KeepForSdk
        public final ToStringHelper add(String s, @Nullable final Object o) {
            final List<String> zzer = this.zzer;
            s = Preconditions.checkNotNull(s);
            final String value = String.valueOf(o);
            zzer.add(new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(value).length()).append(s).append("=").append(value).toString());
            return this;
        }
        
        @KeepForSdk
        @Override
        public final String toString() {
            final StringBuilder append = new StringBuilder(100).append(this.zzes.getClass().getSimpleName()).append('{');
            for (int size = this.zzer.size(), i = 0; i < size; ++i) {
                append.append(this.zzer.get(i));
                if (i < size - 1) {
                    append.append(", ");
                }
            }
            return append.append('}').toString();
        }
    }
}
