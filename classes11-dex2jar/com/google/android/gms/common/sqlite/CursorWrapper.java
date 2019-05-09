// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.sqlite;

import android.database.CursorWindow;
import android.database.Cursor;
import android.database.AbstractWindowedCursor;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.database.CrossProcessCursor;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor
{
    private AbstractWindowedCursor zzez;
    
    @KeepForSdk
    public CursorWrapper(Cursor wrappedCursor) {
        super(wrappedCursor);
        for (int n = 0; n < 10 && wrappedCursor instanceof android.database.CursorWrapper; wrappedCursor = ((android.database.CursorWrapper)wrappedCursor).getWrappedCursor(), ++n) {}
        if (!(wrappedCursor instanceof AbstractWindowedCursor)) {
            final String value = String.valueOf(wrappedCursor.getClass().getName());
            String concat;
            if (value.length() != 0) {
                concat = "Unknown type: ".concat(value);
            }
            else {
                concat = new String("Unknown type: ");
            }
            throw new IllegalArgumentException(concat);
        }
        this.zzez = (AbstractWindowedCursor)wrappedCursor;
    }
    
    @KeepForSdk
    public void fillWindow(final int n, final CursorWindow cursorWindow) {
        this.zzez.fillWindow(n, cursorWindow);
    }
    
    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }
    
    public boolean onMove(final int n, final int n2) {
        return this.zzez.onMove(n, n2);
    }
    
    @KeepForSdk
    public void setWindow(final CursorWindow window) {
        this.zzez.setWindow(window);
    }
}
