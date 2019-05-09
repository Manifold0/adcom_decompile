// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import java.util.Collection;
import com.google.android.gms.common.internal.Preconditions;
import java.util.HashSet;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class Component<T>
{
    private final Set<Class<? super T>> zza;
    private final Set<Dependency> zzb;
    private final int zzc;
    private final ComponentFactory<T> zzd;
    private final Set<Class<?>> zze;
    
    private Component(final Set<Class<? super T>> set, final Set<Dependency> set2, final int zzc, final ComponentFactory<T> zzd, final Set<Class<?>> set3) {
        this.zza = Collections.unmodifiableSet((Set<? extends Class<? super T>>)set);
        this.zzb = Collections.unmodifiableSet((Set<? extends Dependency>)set2);
        this.zzc = zzc;
        this.zzd = zzd;
        this.zze = Collections.unmodifiableSet((Set<? extends Class<?>>)set3);
    }
    
    @KeepForSdk
    public static <T> Builder<T> builder(final Class<T> clazz) {
        return new Builder<T>(clazz, new Class[0], (byte)0);
    }
    
    @KeepForSdk
    public static <T> Builder<T> builder(final Class<T> clazz, final Class<? super T>... array) {
        return new Builder<T>(clazz, array, (byte)0);
    }
    
    @Deprecated
    @KeepForSdk
    public static <T> Component<T> of(final Class<T> clazz, final T t) {
        return builder(clazz).factory(zzb.zza(t)).build();
    }
    
    @SafeVarargs
    @KeepForSdk
    public static <T> Component<T> of(final T t, final Class<T> clazz, final Class<? super T>... array) {
        return builder(clazz, array).factory(zzc.zza(t)).build();
    }
    
    @Override
    public final String toString() {
        return "Component<" + Arrays.toString(this.zza.toArray()) + ">{" + this.zzc + ", deps=" + Arrays.toString(this.zzb.toArray()) + "}";
    }
    
    public final Set<Class<? super T>> zza() {
        return this.zza;
    }
    
    public final Set<Dependency> zzb() {
        return this.zzb;
    }
    
    public final ComponentFactory<T> zzc() {
        return this.zzd;
    }
    
    public final Set<Class<?>> zzd() {
        return this.zze;
    }
    
    public final boolean zze() {
        return this.zzc == 1;
    }
    
    public final boolean zzf() {
        return this.zzc == 2;
    }
    
    @KeepForSdk
    public static class Builder<T>
    {
        private final Set<Class<? super T>> zza;
        private final Set<Dependency> zzb;
        private int zzc;
        private ComponentFactory<T> zzd;
        private Set<Class<?>> zze;
        
        private Builder(final Class<T> clazz, final Class<? super T>... array) {
            int i = 0;
            this.zza = new HashSet<Class<? super T>>();
            this.zzb = new HashSet<Dependency>();
            this.zzc = 0;
            this.zze = new HashSet<Class<?>>();
            Preconditions.checkNotNull((Object)clazz, (Object)"Null interface");
            this.zza.add(clazz);
            while (i < array.length) {
                Preconditions.checkNotNull((Object)array[i], (Object)"Null interface");
                ++i;
            }
            Collections.addAll(this.zza, array);
        }
        
        private Builder<T> zza(final int zzc) {
            Preconditions.checkState(this.zzc == 0, (Object)"Instantiation type has already been set.");
            this.zzc = zzc;
            return this;
        }
        
        @KeepForSdk
        public Builder<T> add(final Dependency dependency) {
            Preconditions.checkNotNull((Object)dependency, (Object)"Null dependency");
            Preconditions.checkArgument(!this.zza.contains(dependency.zza()), (Object)"Components are not allowed to depend on interfaces they themselves provide.");
            this.zzb.add(dependency);
            return this;
        }
        
        @KeepForSdk
        public Builder<T> alwaysEager() {
            return this.zza(1);
        }
        
        @KeepForSdk
        public Component<T> build() {
            Preconditions.checkState(this.zzd != null, (Object)"Missing required property: factory.");
            return new Component<T>(new HashSet(this.zza), new HashSet(this.zzb), this.zzc, this.zzd, this.zze, (byte)0);
        }
        
        @KeepForSdk
        public Builder<T> eagerInDefaultApp() {
            return this.zza(2);
        }
        
        @KeepForSdk
        public Builder<T> factory(final ComponentFactory<T> componentFactory) {
            this.zzd = (ComponentFactory<T>)Preconditions.checkNotNull((Object)componentFactory, (Object)"Null factory");
            return this;
        }
        
        @KeepForSdk
        public Builder<T> publishes(final Class<?> clazz) {
            this.zze.add(clazz);
            return this;
        }
    }
}
