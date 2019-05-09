// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.drive.widget;

import android.database.CursorIndexOutOfBoundsException;
import java.util.Iterator;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.View;
import java.util.Arrays;
import java.util.ArrayList;
import android.view.LayoutInflater;
import com.google.android.gms.common.data.DataBuffer;
import java.util.List;
import android.content.Context;
import com.google.android.gms.common.internal.GmsLogger;
import android.widget.BaseAdapter;

public class DataBufferAdapter<T> extends BaseAdapter
{
    private static final GmsLogger zzbx;
    private final int fieldId;
    private final int resource;
    private final Context zzgu;
    private int zzmj;
    private final List<DataBuffer<T>> zzmk;
    private final LayoutInflater zzml;
    private boolean zzmm;
    
    static {
        zzbx = new GmsLogger("DataBufferAdapter", "");
    }
    
    public DataBufferAdapter(final Context context, final int n) {
        this(context, n, 0, (List)new ArrayList());
    }
    
    public DataBufferAdapter(final Context context, final int n, final int n2) {
        this(context, n, n2, (List)new ArrayList());
    }
    
    public DataBufferAdapter(final Context zzgu, final int n, final int fieldId, final List<DataBuffer<T>> zzmk) {
        this.zzmm = true;
        this.zzgu = zzgu;
        this.zzmj = n;
        this.resource = n;
        this.fieldId = fieldId;
        this.zzmk = zzmk;
        this.zzml = (LayoutInflater)zzgu.getSystemService("layout_inflater");
    }
    
    public DataBufferAdapter(final Context context, final int n, final int n2, final DataBuffer<T>... array) {
        this(context, n, n2, Arrays.asList(array));
    }
    
    public DataBufferAdapter(final Context context, final int n, final List<DataBuffer<T>> list) {
        this(context, n, 0, list);
    }
    
    public DataBufferAdapter(final Context context, final int n, final DataBuffer<T>... array) {
        this(context, n, 0, Arrays.asList(array));
    }
    
    private final View zza(final int n, View inflate, final ViewGroup viewGroup, final int n2) {
        TextView textView = null;
        CharSequence item = null;
        Label_0054: {
            if (inflate != null) {
                break Label_0054;
            }
            inflate = this.zzml.inflate(n2, viewGroup, false);
            try {
                while (true) {
                    if (this.fieldId == 0) {
                        textView = (TextView)inflate;
                    }
                    else {
                        textView = (TextView)inflate.findViewById(this.fieldId);
                    }
                    item = this.getItem(n);
                    if (item instanceof CharSequence) {
                        textView.setText((CharSequence)item);
                        return inflate;
                    }
                    break Label_0054;
                    continue;
                }
            }
            catch (ClassCastException ex) {
                DataBufferAdapter.zzbx.e("DataBufferAdapter", "You must supply a resource ID for a TextView", (Throwable)ex);
                throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", ex);
            }
        }
        textView.setText((CharSequence)item.toString());
        return inflate;
    }
    
    public void append(final DataBuffer<T> dataBuffer) {
        this.zzmk.add(dataBuffer);
        if (this.zzmm) {
            this.notifyDataSetChanged();
        }
    }
    
    public void clear() {
        final Iterator<DataBuffer<T>> iterator = this.zzmk.iterator();
        while (iterator.hasNext()) {
            iterator.next().release();
        }
        this.zzmk.clear();
        if (this.zzmm) {
            this.notifyDataSetChanged();
        }
    }
    
    public Context getContext() {
        return this.zzgu;
    }
    
    public int getCount() {
        final Iterator<DataBuffer<T>> iterator = this.zzmk.iterator();
        int n = 0;
        while (iterator.hasNext()) {
            n += iterator.next().getCount();
        }
        return n;
    }
    
    public View getDropDownView(final int n, final View view, final ViewGroup viewGroup) {
        return this.zza(n, view, viewGroup, this.zzmj);
    }
    
    public T getItem(final int n) throws CursorIndexOutOfBoundsException {
        final Iterator<DataBuffer<T>> iterator = this.zzmk.iterator();
        int n2 = n;
        while (iterator.hasNext()) {
            final DataBuffer<T> dataBuffer = iterator.next();
            final int count = dataBuffer.getCount();
            if (count > n2) {
                try {
                    return (T)dataBuffer.get(n2);
                }
                catch (CursorIndexOutOfBoundsException ex) {
                    throw new CursorIndexOutOfBoundsException(n, this.getCount());
                }
                break;
            }
            n2 -= count;
        }
        throw new CursorIndexOutOfBoundsException(n, this.getCount());
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int n, final View view, final ViewGroup viewGroup) {
        return this.zza(n, view, viewGroup, this.resource);
    }
    
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.zzmm = true;
    }
    
    public void setDropDownViewResource(final int zzmj) {
        this.zzmj = zzmj;
    }
    
    public void setNotifyOnChange(final boolean zzmm) {
        this.zzmm = zzmm;
    }
}
