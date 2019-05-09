// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import android.net.Uri;
import com.google.android.gms.common.internal.Objects;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class DataBufferRef
{
    @KeepForSdk
    protected final DataHolder mDataHolder;
    @KeepForSdk
    protected int mDataRow;
    private int zaln;
    
    @KeepForSdk
    public DataBufferRef(final DataHolder dataHolder, final int n) {
        this.mDataHolder = (DataHolder)Preconditions.checkNotNull((Object)dataHolder);
        this.zag(n);
    }
    
    @KeepForSdk
    protected void copyToBuffer(final String s, final CharArrayBuffer charArrayBuffer) {
        this.mDataHolder.zaa(s, this.mDataRow, this.zaln, charArrayBuffer);
    }
    
    @Override
    public boolean equals(final Object o) {
        boolean b2;
        final boolean b = b2 = false;
        if (o instanceof DataBufferRef) {
            final DataBufferRef dataBufferRef = (DataBufferRef)o;
            b2 = b;
            if (Objects.equal((Object)dataBufferRef.mDataRow, (Object)this.mDataRow)) {
                b2 = b;
                if (Objects.equal((Object)dataBufferRef.zaln, (Object)this.zaln)) {
                    b2 = b;
                    if (dataBufferRef.mDataHolder == this.mDataHolder) {
                        b2 = true;
                    }
                }
            }
        }
        return b2;
    }
    
    @KeepForSdk
    protected boolean getBoolean(final String s) {
        return this.mDataHolder.getBoolean(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected byte[] getByteArray(final String s) {
        return this.mDataHolder.getByteArray(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected int getDataRow() {
        return this.mDataRow;
    }
    
    @KeepForSdk
    protected double getDouble(final String s) {
        return this.mDataHolder.zab(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected float getFloat(final String s) {
        return this.mDataHolder.zaa(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected int getInteger(final String s) {
        return this.mDataHolder.getInteger(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected long getLong(final String s) {
        return this.mDataHolder.getLong(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    protected String getString(final String s) {
        return this.mDataHolder.getString(s, this.mDataRow, this.zaln);
    }
    
    @KeepForSdk
    public boolean hasColumn(final String s) {
        return this.mDataHolder.hasColumn(s);
    }
    
    @KeepForSdk
    protected boolean hasNull(final String s) {
        return this.mDataHolder.hasNull(s, this.mDataRow, this.zaln);
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(new Object[] { this.mDataRow, this.zaln, this.mDataHolder });
    }
    
    @KeepForSdk
    public boolean isDataValid() {
        return !this.mDataHolder.isClosed();
    }
    
    @KeepForSdk
    protected Uri parseUri(String string) {
        string = this.mDataHolder.getString(string, this.mDataRow, this.zaln);
        if (string == null) {
            return null;
        }
        return Uri.parse(string);
    }
    
    protected final void zag(final int mDataRow) {
        Preconditions.checkState(mDataRow >= 0 && mDataRow < this.mDataHolder.getCount());
        this.mDataRow = mDataRow;
        this.zaln = this.mDataHolder.getWindowIndex(this.mDataRow);
    }
}
