// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.data;

import java.util.Iterator;
import com.google.android.gms.common.internal.Asserts;
import android.content.ContentValues;
import java.util.HashMap;
import android.database.CharArrayBuffer;
import android.os.Parcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import android.os.Parcel;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import android.util.Log;
import android.database.CursorIndexOutOfBoundsException;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.sqlite.CursorWrapper;
import android.database.Cursor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Constructor;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Param;
import android.database.CursorWindow;
import android.os.Bundle;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Field;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$VersionField;
import android.os.Parcelable$Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable$Class;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.annotation.KeepForSdk;
import java.io.Closeable;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;

@KeepForSdk
@KeepName
@SafeParcelable$Class(creator = "DataHolderCreator", validate = true)
public final class DataHolder extends AbstractSafeParcelable implements Closeable
{
    @KeepForSdk
    public static final Parcelable$Creator<DataHolder> CREATOR;
    private static final Builder zaly;
    private boolean mClosed;
    @SafeParcelable$VersionField(id = 1000)
    private final int zalf;
    @SafeParcelable$Field(getter = "getColumns", id = 1)
    private final String[] zalq;
    private Bundle zalr;
    @SafeParcelable$Field(getter = "getWindows", id = 2)
    private final CursorWindow[] zals;
    @SafeParcelable$Field(getter = "getStatusCode", id = 3)
    private final int zalt;
    @SafeParcelable$Field(getter = "getMetadata", id = 4)
    private final Bundle zalu;
    private int[] zalv;
    private int zalw;
    private boolean zalx;
    
    static {
        CREATOR = (Parcelable$Creator)new zac();
        zaly = (Builder)new zab(new String[0], null);
    }
    
    @SafeParcelable$Constructor
    DataHolder(@SafeParcelable$Param(id = 1000) final int zalf, @SafeParcelable$Param(id = 1) final String[] zalq, @SafeParcelable$Param(id = 2) final CursorWindow[] zals, @SafeParcelable$Param(id = 3) final int zalt, @SafeParcelable$Param(id = 4) final Bundle zalu) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = zalf;
        this.zalq = zalq;
        this.zals = zals;
        this.zalt = zalt;
        this.zalu = zalu;
    }
    
    @KeepForSdk
    public DataHolder(final Cursor cursor, final int n, final Bundle bundle) {
        this(new CursorWrapper(cursor), n, bundle);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle) {
        this(builder.zalq, zaa(builder, -1), n, null);
    }
    
    private DataHolder(final Builder builder, final int n, final Bundle bundle, final int n2) {
        this(builder.zalq, zaa(builder, -1), n, bundle);
    }
    
    private DataHolder(final CursorWrapper cursorWrapper, final int n, final Bundle bundle) {
        this(cursorWrapper.getColumnNames(), zaa(cursorWrapper), n, bundle);
    }
    
    @KeepForSdk
    public DataHolder(final String[] array, final CursorWindow[] array2, final int zalt, final Bundle zalu) {
        this.mClosed = false;
        this.zalx = true;
        this.zalf = 1;
        this.zalq = (String[])Preconditions.checkNotNull((Object)array);
        this.zals = (CursorWindow[])Preconditions.checkNotNull((Object)array2);
        this.zalt = zalt;
        this.zalu = zalu;
        this.zaca();
    }
    
    @KeepForSdk
    public static Builder builder(final String[] array) {
        return new Builder(array, null, null);
    }
    
    @KeepForSdk
    public static DataHolder empty(final int n) {
        return new DataHolder(DataHolder.zaly, n, null);
    }
    
    private final void zaa(String s, final int n) {
        if (this.zalr == null || !this.zalr.containsKey(s)) {
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = "No such column: ".concat(s);
            }
            else {
                s = new String("No such column: ");
            }
            throw new IllegalArgumentException(s);
        }
        if (this.isClosed()) {
            throw new IllegalArgumentException("Buffer is closed.");
        }
        if (n < 0 || n >= this.zalw) {
            throw new CursorIndexOutOfBoundsException(n, this.zalw);
        }
    }
    
    private static CursorWindow[] zaa(final Builder builder, int i) {
        final int n = 0;
        if (builder.zalq.length == 0) {
            return new CursorWindow[0];
        }
        ArrayList<CursorWindow> list2 = null;
        while (true) {
            final int size2;
            Label_0088: {
                List<Map<K, String>> list = null;
                Label_0037: {
                    if (i < 0 || i >= builder.zalz.size()) {
                        list = (List<Map<K, String>>)builder.zalz;
                        break Label_0037;
                    }
                    CursorWindow cursorWindow = null;
                    boolean b = false;
                    Label_0220: {
                        break Label_0220;
                    Label_0406_Outer:
                        while (true) {
                            final CursorWindow cursorWindow2;
                            cursorWindow = cursorWindow2;
                            while (true) {
                            Label_0750:
                                while (true) {
                                    int n2 = 0;
                                    Label_0743: {
                                        try {
                                            if (!cursorWindow2.allocRow()) {
                                                Log.d("DataHolder", new StringBuilder(72).append("Allocating additional cursor window for large data set (row ").append(i).append(")").toString());
                                                final CursorWindow cursorWindow3 = new CursorWindow(false);
                                                cursorWindow3.setStartPosition(i);
                                                cursorWindow3.setNumColumns(builder.zalq.length);
                                                list2.add(cursorWindow3);
                                                cursorWindow = cursorWindow3;
                                                if (!cursorWindow3.allocRow()) {
                                                    Log.e("DataHolder", "Unable to allocate row to hold data.");
                                                    list2.remove(cursorWindow3);
                                                    return list2.toArray(new CursorWindow[list2.size()]);
                                                }
                                            }
                                            final Map<K, String> map = list.get(i);
                                            n2 = 0;
                                            b = true;
                                            if (n2 >= builder.zalq.length || !b) {
                                                break;
                                            }
                                            final String s = builder.zalq[n2];
                                            final String value = map.get(s);
                                            if (value == null) {
                                                b = cursorWindow.putNull(i, n2);
                                                break Label_0743;
                                            }
                                            if (value instanceof String) {
                                                b = cursorWindow.putString((String)value, i, n2);
                                                break Label_0743;
                                            }
                                            if (value instanceof Long) {
                                                b = cursorWindow.putLong((long)value, i, n2);
                                                break Label_0743;
                                            }
                                            if (value instanceof Integer) {
                                                b = cursorWindow.putLong((long)(int)value, i, n2);
                                                break Label_0743;
                                            }
                                            if (value instanceof Boolean) {
                                                if (value) {
                                                    final long n3 = 1L;
                                                    b = cursorWindow.putLong(n3, i, n2);
                                                    break Label_0743;
                                                }
                                                break Label_0750;
                                            }
                                            else {
                                                if (value instanceof byte[]) {
                                                    b = cursorWindow.putBlob((byte[])(Object)value, i, n2);
                                                    break Label_0743;
                                                }
                                                if (value instanceof Double) {
                                                    b = cursorWindow.putDouble((double)value, i, n2);
                                                    break Label_0743;
                                                }
                                                if (value instanceof Float) {
                                                    b = cursorWindow.putDouble((double)(float)value, i, n2);
                                                    break Label_0743;
                                                }
                                                final String value2 = String.valueOf(value);
                                                throw new IllegalArgumentException(new StringBuilder(String.valueOf(s).length() + 32 + String.valueOf(value2).length()).append("Unsupported object for column ").append(s).append(": ").append(value2).toString());
                                            }
                                            list = builder.zalz.subList(0, i);
                                            break Label_0037;
                                        }
                                        catch (RuntimeException ex) {
                                            int size;
                                            for (size = list2.size(), i = n; i < size; ++i) {
                                                list2.get(i).close();
                                            }
                                            throw ex;
                                        }
                                        break;
                                    }
                                    ++n2;
                                    continue Label_0406_Outer;
                                }
                                final long n3 = 0L;
                                continue;
                            }
                        }
                    }
                    int n5;
                    if (!b) {
                        final int n4;
                        if (n4 != 0) {
                            throw new zaa("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
                        }
                        Log.d("DataHolder", new StringBuilder(74).append("Couldn't populate window data for row ").append(i).append(" - allocating new window.").toString());
                        cursorWindow.freeLastRow();
                        cursorWindow = new CursorWindow(false);
                        cursorWindow.setStartPosition(i);
                        cursorWindow.setNumColumns(builder.zalq.length);
                        list2.add(cursorWindow);
                        n5 = i - 1;
                        i = 1;
                    }
                    else {
                        final int n6 = 0;
                        n5 = i;
                        i = n6;
                    }
                    final int n7 = i;
                    i = n5 + 1;
                    final CursorWindow cursorWindow2 = cursorWindow;
                    final int n4 = n7;
                    break Label_0088;
                }
                size2 = list.size();
                final CursorWindow cursorWindow2 = new CursorWindow(false);
                list2 = new ArrayList<CursorWindow>();
                list2.add(cursorWindow2);
                cursorWindow2.setNumColumns(builder.zalq.length);
                i = 0;
                final int n4 = 0;
            }
            if (i < size2) {
                continue;
            }
            break;
        }
        return list2.toArray(new CursorWindow[list2.size()]);
    }
    
    private static CursorWindow[] zaa(final CursorWrapper cursorWrapper) {
        ArrayList<CursorWindow> list;
        while (true) {
            list = new ArrayList<CursorWindow>();
            while (true) {
                Label_0162: {
                    try {
                        final int count = cursorWrapper.getCount();
                        final CursorWindow window = cursorWrapper.getWindow();
                        if (window == null || window.getStartPosition() != 0) {
                            break Label_0162;
                        }
                        window.acquireReference();
                        cursorWrapper.setWindow((CursorWindow)null);
                        list.add(window);
                        int numRows = window.getNumRows();
                        while (numRows < count && cursorWrapper.moveToPosition(numRows)) {
                            CursorWindow window2 = cursorWrapper.getWindow();
                            if (window2 != null) {
                                window2.acquireReference();
                                cursorWrapper.setWindow((CursorWindow)null);
                            }
                            else {
                                window2 = new CursorWindow(false);
                                window2.setStartPosition(numRows);
                                cursorWrapper.fillWindow(numRows, window2);
                            }
                            if (window2.getNumRows() == 0) {
                                break;
                            }
                            list.add(window2);
                            numRows = window2.getNumRows() + window2.getStartPosition();
                        }
                    }
                    finally {
                        cursorWrapper.close();
                    }
                    break;
                }
                int numRows = 0;
                continue;
            }
        }
        cursorWrapper.close();
        return list.toArray(new CursorWindow[list.size()]);
    }
    
    @KeepForSdk
    public final void close() {
        synchronized (this) {
            if (!this.mClosed) {
                this.mClosed = true;
                for (int i = 0; i < this.zals.length; ++i) {
                    this.zals[i].close();
                }
            }
        }
    }
    
    protected final void finalize() throws Throwable {
        try {
            if (this.zalx && this.zals.length > 0 && !this.isClosed()) {
                this.close();
                final String string = this.toString();
                Log.e("DataBuffer", new StringBuilder(String.valueOf(string).length() + 178).append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ").append(string).append(")").toString());
            }
        }
        finally {
            super.finalize();
        }
    }
    
    @KeepForSdk
    public final boolean getBoolean(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getLong(n, this.zalr.getInt(s)) == 1L;
    }
    
    @KeepForSdk
    public final byte[] getByteArray(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getBlob(n, this.zalr.getInt(s));
    }
    
    @KeepForSdk
    public final int getCount() {
        return this.zalw;
    }
    
    @KeepForSdk
    public final int getInteger(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getInt(n, this.zalr.getInt(s));
    }
    
    @KeepForSdk
    public final long getLong(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getLong(n, this.zalr.getInt(s));
    }
    
    @KeepForSdk
    public final Bundle getMetadata() {
        return this.zalu;
    }
    
    @KeepForSdk
    public final int getStatusCode() {
        return this.zalt;
    }
    
    @KeepForSdk
    public final String getString(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getString(n, this.zalr.getInt(s));
    }
    
    @KeepForSdk
    public final int getWindowIndex(int n) {
        int n2 = 0;
        Preconditions.checkState(n >= 0 && n < this.zalw);
        int n3;
        while (true) {
            n3 = n2;
            if (n2 >= this.zalv.length) {
                break;
            }
            if (n < this.zalv[n2]) {
                n3 = n2 - 1;
                break;
            }
            ++n2;
        }
        if ((n = n3) == this.zalv.length) {
            n = n3 - 1;
        }
        return n;
    }
    
    @KeepForSdk
    public final boolean hasColumn(final String s) {
        return this.zalr.containsKey(s);
    }
    
    @KeepForSdk
    public final boolean hasNull(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].isNull(n, this.zalr.getInt(s));
    }
    
    @KeepForSdk
    public final boolean isClosed() {
        synchronized (this) {
            return this.mClosed;
        }
    }
    
    public final void writeToParcel(final Parcel parcel, final int n) {
        final int beginObjectHeader = SafeParcelWriter.beginObjectHeader(parcel);
        SafeParcelWriter.writeStringArray(parcel, 1, this.zalq, false);
        SafeParcelWriter.writeTypedArray(parcel, 2, (Parcelable[])this.zals, n, false);
        SafeParcelWriter.writeInt(parcel, 3, this.getStatusCode());
        SafeParcelWriter.writeBundle(parcel, 4, this.getMetadata(), false);
        SafeParcelWriter.writeInt(parcel, 1000, this.zalf);
        SafeParcelWriter.finishObjectHeader(parcel, beginObjectHeader);
        if ((n & 0x1) != 0x0) {
            this.close();
        }
    }
    
    public final float zaa(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getFloat(n, this.zalr.getInt(s));
    }
    
    public final void zaa(final String s, final int n, final int n2, final CharArrayBuffer charArrayBuffer) {
        this.zaa(s, n);
        this.zals[n2].copyStringToBuffer(n, this.zalr.getInt(s), charArrayBuffer);
    }
    
    public final double zab(final String s, final int n, final int n2) {
        this.zaa(s, n);
        return this.zals[n2].getDouble(n, this.zalr.getInt(s));
    }
    
    public final void zaca() {
        final int n = 0;
        this.zalr = new Bundle();
        for (int i = 0; i < this.zalq.length; ++i) {
            this.zalr.putInt(this.zalq[i], i);
        }
        this.zalv = new int[this.zals.length];
        final int n2 = 0;
        int j = n;
        int zalw = n2;
        while (j < this.zals.length) {
            this.zalv[j] = zalw;
            zalw += this.zals[j].getNumRows() - (zalw - this.zals[j].getStartPosition());
            ++j;
        }
        this.zalw = zalw;
    }
    
    @KeepForSdk
    public static class Builder
    {
        private final String[] zalq;
        private final ArrayList<HashMap<String, Object>> zalz;
        private final String zama;
        private final HashMap<Object, Integer> zamb;
        private boolean zamc;
        private String zamd;
        
        private Builder(final String[] array, final String zama) {
            this.zalq = (String[])Preconditions.checkNotNull((Object)array);
            this.zalz = new ArrayList<HashMap<String, Object>>();
            this.zama = zama;
            this.zamb = new HashMap<Object, Integer>();
            this.zamc = false;
            this.zamd = null;
        }
        
        @KeepForSdk
        public DataHolder build(final int n) {
            return new DataHolder(this, n, null, null);
        }
        
        @KeepForSdk
        public DataHolder build(final int n, final Bundle bundle) {
            return new DataHolder(this, n, bundle, -1, null);
        }
        
        @KeepForSdk
        public Builder withRow(final ContentValues contentValues) {
            Asserts.checkNotNull((Object)contentValues);
            final HashMap<String, Object> hashMap = new HashMap<String, Object>(contentValues.size());
            for (final Map.Entry<String, V> entry : contentValues.valueSet()) {
                hashMap.put(entry.getKey(), entry.getValue());
            }
            return this.zaa(hashMap);
        }
        
        public Builder zaa(final HashMap<String, Object> hashMap) {
            Asserts.checkNotNull((Object)hashMap);
            int intValue;
            if (this.zama == null) {
                intValue = -1;
            }
            else {
                final Object value = hashMap.get(this.zama);
                if (value == null) {
                    intValue = -1;
                }
                else {
                    final Integer n = this.zamb.get(value);
                    if (n == null) {
                        this.zamb.put(value, this.zalz.size());
                        intValue = -1;
                    }
                    else {
                        intValue = n;
                    }
                }
            }
            if (intValue == -1) {
                this.zalz.add(hashMap);
            }
            else {
                this.zalz.remove(intValue);
                this.zalz.add(intValue, hashMap);
            }
            this.zamc = false;
            return this;
        }
    }
    
    public static final class zaa extends RuntimeException
    {
        public zaa(final String s) {
            super(s);
        }
    }
}
