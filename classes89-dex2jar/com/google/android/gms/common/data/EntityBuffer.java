// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.ArrayList;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T>
{
    private boolean zame;
    private ArrayList<Integer> zamf;
    
    @KeepForSdk
    protected EntityBuffer(final DataHolder dataHolder) {
        super(dataHolder);
        this.zame = false;
    }
    
    private final void zacb() {
        while (true) {
            Label_0199: {
                while (true) {
                    int windowIndex;
                    final String string;
                    synchronized (this) {
                        if (this.zame) {
                            break;
                        }
                        final int count = this.mDataHolder.getCount();
                        this.zamf = new ArrayList<Integer>();
                        if (count <= 0) {
                            break Label_0199;
                        }
                        this.zamf.add(0);
                        final String primaryDataMarkerColumn = this.getPrimaryDataMarkerColumn();
                        windowIndex = this.mDataHolder.getWindowIndex(0);
                        this.mDataHolder.getString(primaryDataMarkerColumn, 0, windowIndex);
                        windowIndex = 1;
                        if (windowIndex >= count) {
                            break Label_0199;
                        }
                        final int windowIndex2 = this.mDataHolder.getWindowIndex(windowIndex);
                        string = this.mDataHolder.getString(primaryDataMarkerColumn, windowIndex, windowIndex2);
                        if (string == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78).append("Missing value for markerColumn: ").append(primaryDataMarkerColumn).append(", at row: ").append(windowIndex).append(", for window: ").append(windowIndex2).toString());
                        }
                    }
                    final Throwable t;
                    if (!string.equals(t)) {
                        this.zamf.add(windowIndex);
                    }
                    ++windowIndex;
                    continue;
                }
            }
            this.zame = true;
            break;
        }
    }
    // monitorexit(this)
    
    private final int zah(final int n) {
        if (n < 0 || n >= this.zamf.size()) {
            throw new IllegalArgumentException(new StringBuilder(53).append("Position ").append(n).append(" is out of bounds for this buffer").toString());
        }
        return this.zamf.get(n);
    }
    
    @KeepForSdk
    @Override
    public final T get(int zah) {
        this.zacb();
        final int zah2 = this.zah(zah);
        int n;
        if (zah < 0 || zah == this.zamf.size()) {
            n = 0;
        }
        else {
            int n2;
            if (zah == this.zamf.size() - 1) {
                n2 = this.mDataHolder.getCount() - this.zamf.get(zah);
            }
            else {
                n2 = this.zamf.get(zah + 1) - this.zamf.get(zah);
            }
            n = n2;
            if (n2 == 1) {
                zah = this.zah(zah);
                final int windowIndex = this.mDataHolder.getWindowIndex(zah);
                final String childDataMarkerColumn = this.getChildDataMarkerColumn();
                n = n2;
                if (childDataMarkerColumn != null) {
                    n = n2;
                    if (this.mDataHolder.getString(childDataMarkerColumn, zah, windowIndex) == null) {
                        n = 0;
                    }
                }
            }
        }
        return this.getEntry(zah2, n);
    }
    
    @KeepForSdk
    protected String getChildDataMarkerColumn() {
        return null;
    }
    
    @KeepForSdk
    @Override
    public int getCount() {
        this.zacb();
        return this.zamf.size();
    }
    
    @KeepForSdk
    protected abstract T getEntry(final int p0, final int p1);
    
    @KeepForSdk
    protected abstract String getPrimaryDataMarkerColumn();
}
