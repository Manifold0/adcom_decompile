package com.google.android.gms.drive.widget;

import android.content.Context;
import android.database.CursorIndexOutOfBoundsException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.common.internal.GmsLogger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBufferAdapter<T> extends BaseAdapter {
    private static final GmsLogger zzbx = new GmsLogger("DataBufferAdapter", "");
    private final int fieldId;
    private final int resource;
    private final Context zzgu;
    private int zzmj;
    private final List<DataBuffer<T>> zzmk;
    private final LayoutInflater zzml;
    private boolean zzmm;

    public DataBufferAdapter(Context context, int i) {
        this(context, i, 0, new ArrayList());
    }

    public DataBufferAdapter(Context context, int i, int i2) {
        this(context, i, i2, new ArrayList());
    }

    public DataBufferAdapter(Context context, int i, int i2, List<DataBuffer<T>> list) {
        this.zzmm = true;
        this.zzgu = context;
        this.zzmj = i;
        this.resource = i;
        this.fieldId = i2;
        this.zzmk = list;
        this.zzml = (LayoutInflater) context.getSystemService("layout_inflater");
    }

    public DataBufferAdapter(Context context, int i, int i2, DataBuffer<T>... dataBufferArr) {
        this(context, i, i2, Arrays.asList(dataBufferArr));
    }

    public DataBufferAdapter(Context context, int i, List<DataBuffer<T>> list) {
        this(context, i, 0, (List) list);
    }

    public DataBufferAdapter(Context context, int i, DataBuffer<T>... dataBufferArr) {
        this(context, i, 0, Arrays.asList(dataBufferArr));
    }

    private final View zza(int i, View view, ViewGroup viewGroup, int i2) {
        View inflate = view == null ? this.zzml.inflate(i2, viewGroup, false) : view;
        try {
            TextView textView = this.fieldId == 0 ? (TextView) inflate : (TextView) inflate.findViewById(this.fieldId);
            Object item = getItem(i);
            if (item instanceof CharSequence) {
                textView.setText((CharSequence) item);
            } else {
                textView.setText(item.toString());
            }
            return inflate;
        } catch (Throwable e) {
            zzbx.e("DataBufferAdapter", "You must supply a resource ID for a TextView", e);
            throw new IllegalStateException("DataBufferAdapter requires the resource ID to be a TextView", e);
        }
    }

    public void append(DataBuffer<T> dataBuffer) {
        this.zzmk.add(dataBuffer);
        if (this.zzmm) {
            notifyDataSetChanged();
        }
    }

    public void clear() {
        for (DataBuffer release : this.zzmk) {
            release.release();
        }
        this.zzmk.clear();
        if (this.zzmm) {
            notifyDataSetChanged();
        }
    }

    public Context getContext() {
        return this.zzgu;
    }

    public int getCount() {
        int i = 0;
        for (DataBuffer count : this.zzmk) {
            i = count.getCount() + i;
        }
        return i;
    }

    public View getDropDownView(int i, View view, ViewGroup viewGroup) {
        return zza(i, view, viewGroup, this.zzmj);
    }

    public T getItem(int i) throws CursorIndexOutOfBoundsException {
        int i2 = i;
        for (DataBuffer dataBuffer : this.zzmk) {
            int count = dataBuffer.getCount();
            if (count <= i2) {
                i2 -= count;
            } else {
                try {
                    return dataBuffer.get(i2);
                } catch (CursorIndexOutOfBoundsException e) {
                    throw new CursorIndexOutOfBoundsException(i, getCount());
                }
            }
        }
        throw new CursorIndexOutOfBoundsException(i, getCount());
    }

    public long getItemId(int i) {
        return (long) i;
    }

    public View getView(int i, View view, ViewGroup viewGroup) {
        return zza(i, view, viewGroup, this.resource);
    }

    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        this.zzmm = true;
    }

    public void setDropDownViewResource(int i) {
        this.zzmj = i;
    }

    public void setNotifyOnChange(boolean z) {
        this.zzmm = z;
    }
}
