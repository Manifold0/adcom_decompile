// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.android.gms.common.internal.Preconditions;
import com.google.firebase.inject.Provider;
import java.util.Iterator;
import java.util.Collections;
import java.util.Collection;
import com.google.firebase.events.Publisher;
import com.google.firebase.events.Subscriber;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Executor;
import java.util.Map;
import java.util.List;

public final class zzf extends zza
{
    private final List<Component<?>> zza;
    private final Map<Class<?>, zzj<?>> zzb;
    private final zzh zzc;
    
    public zzf(final Executor executor, final Iterable<ComponentRegistrar> iterable, final Component<?>... array) {
        this.zzb = new HashMap<Class<?>, zzj<?>>();
        this.zzc = new zzh(executor);
        final ArrayList<Object> list = new ArrayList<Object>();
        list.add(Component.of(this.zzc, zzh.class, Subscriber.class, Publisher.class));
        final Iterator<ComponentRegistrar> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            list.addAll(iterator.next().getComponents());
        }
        Collections.addAll(list, array);
        this.zza = (List<Component<?>>)Collections.unmodifiableList((List<?>)Component.Component$1.zza((List<Component<?>>)list));
        final Iterator<Component<?>> iterator2 = this.zza.iterator();
        while (iterator2.hasNext()) {
            this.zza((Component<Object>)iterator2.next());
        }
        this.zza();
    }
    
    private void zza() {
        for (final Component<?> component : this.zza) {
            for (final Dependency dependency : component.zzb()) {
                if (dependency.zzb() && !this.zzb.containsKey(dependency.zza())) {
                    throw new MissingDependencyException(String.format("Unsatisfied dependency for component %s: %s", component, dependency.zza()));
                }
            }
        }
    }
    
    private <T> void zza(final Component<T> component) {
        final zzj<Object> zzj = new zzj<Object>((ComponentFactory<Object>)component.zzc(), new zzl(component, this));
        final Iterator<Class<? super T>> iterator = component.zza().iterator();
        while (iterator.hasNext()) {
            this.zzb.put(iterator.next(), zzj);
        }
    }
    
    @Override
    public final <T> Provider<T> getProvider(final Class<T> clazz) {
        Preconditions.checkNotNull((Object)clazz, (Object)"Null interface requested.");
        return (Provider<T>)this.zzb.get(clazz);
    }
    
    public final void zza(final boolean b) {
        for (final Component<?> component : this.zza) {
            if (component.zze() || (component.zzf() && b)) {
                this.get(component.zza().iterator().next());
            }
        }
        this.zzc.zza();
    }
}
