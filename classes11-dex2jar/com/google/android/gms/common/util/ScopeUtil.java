// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.HashSet;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.api.Scope;
import java.util.Set;
import java.util.Collection;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class ScopeUtil
{
    private ScopeUtil() {
    }
    
    @KeepForSdk
    public static Set<Scope> fromScopeString(final Collection<String> collection) {
        Preconditions.checkNotNull(collection, "scopeStrings can't be null.");
        return fromScopeString((String[])collection.toArray(new String[collection.size()]));
    }
    
    @KeepForSdk
    public static Set<Scope> fromScopeString(final String... array) {
        Preconditions.checkNotNull(array, "scopeStrings can't be null.");
        final HashSet<Scope> set = new HashSet<Scope>(array.length);
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            if (!TextUtils.isEmpty((CharSequence)s)) {
                set.add(new Scope(s));
            }
        }
        return set;
    }
    
    @KeepForSdk
    public static String[] toScopeString(final Set<Scope> set) {
        Preconditions.checkNotNull(set, "scopes can't be null.");
        return toScopeString(set.toArray(new Scope[set.size()]));
    }
    
    @KeepForSdk
    public static String[] toScopeString(final Scope[] array) {
        Preconditions.checkNotNull(array, "scopes can't be null.");
        final String[] array2 = new String[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = array[i].getScopeUri();
        }
        return array2;
    }
}
