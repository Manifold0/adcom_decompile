// 
// Decompiled by Procyon v0.5.34
// 

package com.google.firebase.components;

import com.google.firebase.inject.Provider;

abstract class zza implements ComponentContainer
{
    @Override
    public <T> T get(final Class<T> clazz) {
        final Provider<T> provider = this.getProvider(clazz);
        if (provider == null) {
            return null;
        }
        return provider.get();
    }
}
