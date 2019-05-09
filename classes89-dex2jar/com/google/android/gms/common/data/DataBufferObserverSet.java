// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import java.util.HashSet;

public final class DataBufferObserverSet implements DataBufferObserver, Observable
{
    private HashSet<DataBufferObserver> zalm;
    
    public DataBufferObserverSet() {
        this.zalm = new HashSet<DataBufferObserver>();
    }
    
    @Override
    public final void addObserver(final DataBufferObserver dataBufferObserver) {
        this.zalm.add(dataBufferObserver);
    }
    
    public final void clear() {
        this.zalm.clear();
    }
    
    public final boolean hasObservers() {
        return !this.zalm.isEmpty();
    }
    
    @Override
    public final void onDataChanged() {
        final Iterator<DataBufferObserver> iterator = this.zalm.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataChanged();
        }
    }
    
    @Override
    public final void onDataRangeChanged(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zalm.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeChanged(n, n2);
        }
    }
    
    @Override
    public final void onDataRangeInserted(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zalm.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeInserted(n, n2);
        }
    }
    
    @Override
    public final void onDataRangeMoved(final int n, final int n2, final int n3) {
        final Iterator<DataBufferObserver> iterator = this.zalm.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeMoved(n, n2, n3);
        }
    }
    
    @Override
    public final void onDataRangeRemoved(final int n, final int n2) {
        final Iterator<DataBufferObserver> iterator = this.zalm.iterator();
        while (iterator.hasNext()) {
            iterator.next().onDataRangeRemoved(n, n2);
        }
    }
    
    @Override
    public final void removeObserver(final DataBufferObserver dataBufferObserver) {
        this.zalm.remove(dataBufferObserver);
    }
}
