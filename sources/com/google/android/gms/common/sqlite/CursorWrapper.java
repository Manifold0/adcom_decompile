package com.google.android.gms.common.sqlite;

import android.database.AbstractWindowedCursor;
import android.database.CrossProcessCursor;
import android.database.Cursor;
import android.database.CursorWindow;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class CursorWrapper extends android.database.CursorWrapper implements CrossProcessCursor {
    private AbstractWindowedCursor zzez;

    @KeepForSdk
    public CursorWrapper(Cursor cursor) {
        super(cursor);
        int i = 0;
        Object obj = cursor;
        while (i < 10 && (obj instanceof android.database.CursorWrapper)) {
            i++;
            Cursor wrappedCursor = ((android.database.CursorWrapper) obj).getWrappedCursor();
        }
        if (obj instanceof AbstractWindowedCursor) {
            this.zzez = (AbstractWindowedCursor) obj;
            return;
        }
        String str = "Unknown type: ";
        String valueOf = String.valueOf(obj.getClass().getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    @KeepForSdk
    public CursorWindow getWindow() {
        return this.zzez.getWindow();
    }

    @KeepForSdk
    public void setWindow(CursorWindow cursorWindow) {
        this.zzez.setWindow(cursorWindow);
    }

    @KeepForSdk
    public void fillWindow(int i, CursorWindow cursorWindow) {
        this.zzez.fillWindow(i, cursorWindow);
    }

    public boolean onMove(int i, int i2) {
        return this.zzez.onMove(i, i2);
    }

    public /* synthetic */ Cursor getWrappedCursor() {
        return this.zzez;
    }
}
