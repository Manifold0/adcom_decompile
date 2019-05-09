// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal.shortcutbadger.util;

import java.io.IOException;
import java.io.Closeable;
import android.database.Cursor;

public class CloseHelper
{
    public static void close(final Cursor cursor) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
    }
    
    public static void closeQuietly(final Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        }
        catch (IOException ex) {}
    }
}
