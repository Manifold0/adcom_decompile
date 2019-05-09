// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.api.internal;

import java.util.Iterator;
import com.google.android.gms.common.internal.Preconditions;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.Map;
import java.util.Collections;
import java.util.WeakHashMap;
import java.util.Set;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class ListenerHolders
{
    private final Set<ListenerHolder<?>> zajo;
    
    public ListenerHolders() {
        this.zajo = Collections.newSetFromMap(new WeakHashMap<ListenerHolder<?>, Boolean>());
    }
    
    @KeepForSdk
    public static <L> ListenerHolder<L> createListenerHolder(@NonNull final L l, @NonNull final Looper looper, @NonNull final String s) {
        Preconditions.checkNotNull((Object)l, (Object)"Listener must not be null");
        Preconditions.checkNotNull((Object)looper, (Object)"Looper must not be null");
        Preconditions.checkNotNull((Object)s, (Object)"Listener type must not be null");
        return new ListenerHolder<L>(looper, l, s);
    }
    
    @KeepForSdk
    public static <L> ListenerHolder.ListenerKey<L> createListenerKey(@NonNull final L l, @NonNull final String s) {
        Preconditions.checkNotNull((Object)l, (Object)"Listener must not be null");
        Preconditions.checkNotNull((Object)s, (Object)"Listener type must not be null");
        Preconditions.checkNotEmpty(s, (Object)"Listener type must not be empty");
        return (ListenerHolder.ListenerKey<L>)new ListenerHolder.ListenerKey(l, s);
    }
    
    public final void release() {
        final Iterator<ListenerHolder<?>> iterator = this.zajo.iterator();
        while (iterator.hasNext()) {
            iterator.next().clear();
        }
        this.zajo.clear();
    }
    
    public final <L> ListenerHolder<L> zaa(@NonNull final L l, @NonNull final Looper looper, @NonNull final String s) {
        final ListenerHolder<Object> listenerHolder = createListenerHolder((Object)l, looper, s);
        this.zajo.add(listenerHolder);
        return (ListenerHolder<L>)listenerHolder;
    }
}
