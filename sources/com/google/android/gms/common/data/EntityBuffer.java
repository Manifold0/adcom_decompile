package com.google.android.gms.common.data;

import com.google.android.gms.common.annotation.KeepForSdk;
import java.util.ArrayList;

@KeepForSdk
public abstract class EntityBuffer<T> extends AbstractDataBuffer<T> {
    private boolean zame = false;
    private ArrayList<Integer> zamf;

    @KeepForSdk
    protected EntityBuffer(DataHolder dataHolder) {
        super(dataHolder);
    }

    @KeepForSdk
    protected abstract T getEntry(int i, int i2);

    @KeepForSdk
    protected abstract String getPrimaryDataMarkerColumn();

    @KeepForSdk
    public final T get(int i) {
        int i2;
        zacb();
        int zah = zah(i);
        if (i < 0 || i == this.zamf.size()) {
            i2 = 0;
        } else {
            if (i == this.zamf.size() - 1) {
                i2 = this.mDataHolder.getCount() - ((Integer) this.zamf.get(i)).intValue();
            } else {
                i2 = ((Integer) this.zamf.get(i + 1)).intValue() - ((Integer) this.zamf.get(i)).intValue();
            }
            if (i2 == 1) {
                int zah2 = zah(i);
                int windowIndex = this.mDataHolder.getWindowIndex(zah2);
                String childDataMarkerColumn = getChildDataMarkerColumn();
                if (childDataMarkerColumn != null && this.mDataHolder.getString(childDataMarkerColumn, zah2, windowIndex) == null) {
                    i2 = 0;
                }
            }
        }
        return getEntry(zah, i2);
    }

    @KeepForSdk
    public int getCount() {
        zacb();
        return this.zamf.size();
    }

    private final void zacb() {
        synchronized (this) {
            if (!this.zame) {
                int count = this.mDataHolder.getCount();
                this.zamf = new ArrayList();
                if (count > 0) {
                    this.zamf.add(Integer.valueOf(0));
                    String primaryDataMarkerColumn = getPrimaryDataMarkerColumn();
                    String string = this.mDataHolder.getString(primaryDataMarkerColumn, 0, this.mDataHolder.getWindowIndex(0));
                    int i = 1;
                    while (i < count) {
                        int windowIndex = this.mDataHolder.getWindowIndex(i);
                        String string2 = this.mDataHolder.getString(primaryDataMarkerColumn, i, windowIndex);
                        if (string2 == null) {
                            throw new NullPointerException(new StringBuilder(String.valueOf(primaryDataMarkerColumn).length() + 78).append("Missing value for markerColumn: ").append(primaryDataMarkerColumn).append(", at row: ").append(i).append(", for window: ").append(windowIndex).toString());
                        }
                        if (string2.equals(string)) {
                            string2 = string;
                        } else {
                            this.zamf.add(Integer.valueOf(i));
                        }
                        i++;
                        string = string2;
                    }
                }
                this.zame = true;
            }
        }
    }

    private final int zah(int i) {
        if (i >= 0 && i < this.zamf.size()) {
            return ((Integer) this.zamf.get(i)).intValue();
        }
        throw new IllegalArgumentException("Position " + i + " is out of bounds for this buffer");
    }

    @KeepForSdk
    protected String getChildDataMarkerColumn() {
        return null;
    }
}
