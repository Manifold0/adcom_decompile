// 
// Decompiled by Procyon v0.5.34
// 

package dagger.internal;

import java.util.Iterator;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Collection;
import javax.inject.Provider;
import java.util.List;
import java.util.Set;

public final class SetFactory<T> implements Factory<Set<T>>
{
    private static final Factory<Set<Object>> EMPTY_FACTORY;
    private final List<Provider<Collection<T>>> collectionProviders;
    private final List<Provider<T>> individualProviders;
    
    static {
        EMPTY_FACTORY = new Factory<Set<Object>>() {
            public Set<Object> get() {
                return Collections.emptySet();
            }
        };
    }
    
    private SetFactory(final List<Provider<T>> individualProviders, final List<Provider<Collection<T>>> collectionProviders) {
        this.individualProviders = individualProviders;
        this.collectionProviders = collectionProviders;
    }
    
    public static <T> Builder<T> builder(final int n, final int n2) {
        return new Builder<T>(n, n2);
    }
    
    public static <T> Factory<Set<T>> empty() {
        return (Factory<Set<T>>)SetFactory.EMPTY_FACTORY;
    }
    
    public Set<T> get() {
        int size = this.individualProviders.size();
        final ArrayList<Collection<T>> list = new ArrayList<Collection<T>>(this.collectionProviders.size());
        for (int i = 0; i < this.collectionProviders.size(); ++i) {
            final Collection collection = (Collection)this.collectionProviders.get(i).get();
            size += collection.size();
            list.add(collection);
        }
        final HashSet<Object> hashSetWithExpectedSize = DaggerCollections.newHashSetWithExpectedSize(size);
        for (int j = 0; j < this.individualProviders.size(); ++j) {
            hashSetWithExpectedSize.add(Preconditions.checkNotNull(this.individualProviders.get(j).get()));
        }
        for (int k = 0; k < list.size(); ++k) {
            final Iterator<T> iterator = list.get(k).iterator();
            while (iterator.hasNext()) {
                hashSetWithExpectedSize.add(Preconditions.checkNotNull((Object)iterator.next()));
            }
        }
        return Collections.unmodifiableSet((Set<? extends T>)hashSetWithExpectedSize);
    }
    
    public static final class Builder<T>
    {
        private final List<Provider<Collection<T>>> collectionProviders;
        private final List<Provider<T>> individualProviders;
        
        private Builder(final int n, final int n2) {
            this.individualProviders = DaggerCollections.presizedList(n);
            this.collectionProviders = DaggerCollections.presizedList(n2);
        }
        
        public Builder<T> addCollectionProvider(final Provider<? extends Collection<? extends T>> provider) {
            assert provider != null : "Codegen error? Null provider";
            this.collectionProviders.add((Provider<Collection<T>>)provider);
            return this;
        }
        
        public Builder<T> addProvider(final Provider<? extends T> provider) {
            assert provider != null : "Codegen error? Null provider";
            this.individualProviders.add((Provider<T>)provider);
            return this;
        }
        
        public SetFactory<T> build() {
            assert !DaggerCollections.hasDuplicates(this.individualProviders) : "Codegen error?  Duplicates in the provider list";
            assert !DaggerCollections.hasDuplicates(this.collectionProviders) : "Codegen error?  Duplicates in the provider list";
            return new SetFactory<T>(this.individualProviders, this.collectionProviders, null);
        }
    }
}
