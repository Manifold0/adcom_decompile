// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.nearby;

import com.google.android.gms.common.api.internal.ListenerHolders;
import java.util.Iterator;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.common.api.internal.UnregisterListenerMethod;
import com.google.android.gms.common.api.internal.RegisterListenerMethod;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.common.api.GoogleApi;
import java.util.HashSet;
import java.util.HashMap;
import com.google.android.gms.common.api.internal.ListenerHolder;
import com.google.android.gms.common.api.internal.ListenerHolder$ListenerKey;
import java.util.Set;
import java.util.Map;

public final class zzk
{
    private static zzk zzal;
    private final Map<String, Set<ListenerHolder$ListenerKey<?>>> zzam;
    private final Set<ListenerHolder$ListenerKey<?>> zzan;
    private final Map<String, ListenerHolder<String>> zzao;
    
    private zzk() {
        this.zzam = new HashMap<String, Set<ListenerHolder$ListenerKey<?>>>();
        this.zzan = new HashSet<ListenerHolder$ListenerKey<?>>();
        this.zzao = new HashMap<String, ListenerHolder<String>>();
    }
    
    public static zzk zza() {
        synchronized (zzk.class) {
            if (zzk.zzal == null) {
                zzk.zzal = new zzk();
            }
            return zzk.zzal;
        }
    }
    
    private final void zza(final String s, final ListenerHolder$ListenerKey<?> listenerHolder$ListenerKey) {
        Set<ListenerHolder$ListenerKey<?>> set;
        if ((set = this.zzam.get(s)) == null) {
            set = new HashSet<ListenerHolder$ListenerKey<?>>();
            this.zzam.put(s, set);
        }
        set.add(listenerHolder$ListenerKey);
    }
    
    public final <T> ListenerHolder<T> zza(final GoogleApi googleApi, final T t, final String s) {
        synchronized (this) {
            final ListenerHolder registerListener = googleApi.registerListener((Object)t, s);
            this.zza(s, (ListenerHolder$ListenerKey<?>)registerListener.getListenerKey());
            return (ListenerHolder<T>)registerListener;
        }
    }
    
    public final ListenerHolder<String> zza(final GoogleApi googleApi, final String s, final String s2) {
        synchronized (this) {
            ListenerHolder registerListener;
            if (this.zzao.containsKey(s) && this.zzao.get(s).hasListener()) {
                registerListener = this.zzao.get(s);
            }
            else {
                registerListener = googleApi.registerListener((Object)s, s2);
                this.zza(s2, (ListenerHolder$ListenerKey<?>)registerListener.getListenerKey());
                this.zzao.put(s, (ListenerHolder<String>)registerListener);
            }
            return (ListenerHolder<String>)registerListener;
        }
    }
    
    public final Task<Boolean> zza(final GoogleApi googleApi, final ListenerHolder$ListenerKey<?> listenerHolder$ListenerKey) {
        synchronized (this) {
            this.zzan.remove(listenerHolder$ListenerKey);
            return (Task<Boolean>)googleApi.doUnregisterEventListener((ListenerHolder$ListenerKey)listenerHolder$ListenerKey);
        }
    }
    
    public final Task<Void> zza(final GoogleApi googleApi, final RegisterListenerMethod registerListenerMethod, final UnregisterListenerMethod unregisterListenerMethod) {
        synchronized (this) {
            this.zzan.add((ListenerHolder$ListenerKey<?>)registerListenerMethod.getListenerKey());
            return (Task<Void>)googleApi.doRegisterEventListener(registerListenerMethod, unregisterListenerMethod).addOnFailureListener((OnFailureListener)new zzl(this, registerListenerMethod));
        }
    }
    
    public final void zza(final GoogleApi googleApi, final String s) {
        while (true) {
            synchronized (this) {
                final Set<ListenerHolder$ListenerKey<?>> set = this.zzam.get(s);
                if (set == null) {
                    return;
                }
                for (final ListenerHolder$ListenerKey<?> listenerHolder$ListenerKey : set) {
                    if (this.zzan.contains(listenerHolder$ListenerKey)) {
                        this.zza(googleApi, listenerHolder$ListenerKey);
                    }
                }
            }
            this.zzam.remove(s);
        }
    }
    
    public final <T> ListenerHolder$ListenerKey<T> zzb(final GoogleApi googleApi, final T t, final String s) {
        synchronized (this) {
            ListenerHolder$ListenerKey listenerHolder$ListenerKey;
            if (t instanceof String) {
                listenerHolder$ListenerKey = this.zza(googleApi, (String)t, s).getListenerKey();
            }
            else {
                listenerHolder$ListenerKey = ListenerHolders.createListenerKey((Object)t, s);
            }
            return (ListenerHolder$ListenerKey<T>)listenerHolder$ListenerKey;
        }
    }
}
