// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.Collection;
import java.util.Iterator;
import com.google.android.gms.common.internal.Preconditions;
import java.util.Collections;
import java.util.Set;
import java.util.ArrayDeque;
import java.util.HashMap;
import com.google.firebase.events.Event;
import java.util.Queue;
import android.support.annotation.GuardedBy;
import java.util.concurrent.Executor;
import com.google.firebase.events.EventHandler;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;
import com.google.firebase.events.Subscriber;
import com.google.firebase.events.Publisher;

class zzh implements Publisher, Subscriber
{
    @GuardedBy("this")
    private final Map<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>> zza;
    @GuardedBy("this")
    private Queue<Event<?>> zzb;
    private final Executor zzc;
    
    zzh(final Executor zzc) {
        this.zza = new HashMap<Class<?>, ConcurrentHashMap<EventHandler<Object>, Executor>>();
        this.zzb = new ArrayDeque<Event<?>>();
        this.zzc = zzc;
    }
    
    private Set<Map.Entry<EventHandler<Object>, Executor>> zza(final Event<?> event) {
        synchronized (this) {
            final ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap = this.zza.get(event.getType());
            Object o;
            if (concurrentHashMap == null) {
                o = Collections.emptySet();
            }
            else {
                o = concurrentHashMap.entrySet();
            }
            return (Set<Map.Entry<EventHandler<Object>, Executor>>)o;
        }
    }
    
    @Override
    public void publish(final Event<?> event) {
        Preconditions.checkNotNull((Object)event);
        synchronized (this) {
            if (this.zzb != null) {
                this.zzb.add(event);
                return;
            }
            // monitorexit(this)
            for (final Map.Entry<EventHandler<Object>, Executor> entry : this.zza(event)) {
                entry.getValue().execute(zzi.zza((Map.Entry)entry, event));
            }
        }
    }
    
    @Override
    public <T> void subscribe(final Class<T> clazz, final EventHandler<? super T> eventHandler) {
        this.subscribe(clazz, this.zzc, eventHandler);
    }
    
    @Override
    public <T> void subscribe(final Class<T> clazz, final Executor executor, final EventHandler<? super T> eventHandler) {
        synchronized (this) {
            Preconditions.checkNotNull((Object)clazz);
            Preconditions.checkNotNull((Object)eventHandler);
            Preconditions.checkNotNull((Object)executor);
            if (!this.zza.containsKey(clazz)) {
                this.zza.put(clazz, new ConcurrentHashMap<EventHandler<Object>, Executor>());
            }
            this.zza.get(clazz).put((EventHandler<Object>)eventHandler, executor);
        }
    }
    
    @Override
    public <T> void unsubscribe(final Class<T> clazz, final EventHandler<? super T> eventHandler) {
        synchronized (this) {
            Preconditions.checkNotNull((Object)clazz);
            Preconditions.checkNotNull((Object)eventHandler);
            if (this.zza.containsKey(clazz)) {
                final ConcurrentHashMap<EventHandler<Object>, Executor> concurrentHashMap = this.zza.get(clazz);
                concurrentHashMap.remove(eventHandler);
                if (concurrentHashMap.isEmpty()) {
                    this.zza.remove(clazz);
                }
            }
        }
    }
    
    final void zza() {
        Collection<Event<?>> zzb = null;
        synchronized (this) {
            if (this.zzb != null) {
                zzb = this.zzb;
                this.zzb = null;
            }
            // monitorexit(this)
            if (zzb != null) {
                final Iterator<Event<?>> iterator = zzb.iterator();
                while (iterator.hasNext()) {
                    this.publish(iterator.next());
                }
            }
        }
    }
}
