// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.util;

import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.ByteArrayOutputStream;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.database.CharArrayBuffer;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class DataUtils
{
    @KeepForSdk
    public static void copyStringToBuffer(final String s, final CharArrayBuffer charArrayBuffer) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            charArrayBuffer.sizeCopied = 0;
        }
        else if (charArrayBuffer.data == null || charArrayBuffer.data.length < s.length()) {
            charArrayBuffer.data = s.toCharArray();
        }
        else {
            s.getChars(0, s.length(), charArrayBuffer.data, 0);
        }
        charArrayBuffer.sizeCopied = s.length();
    }
    
    @KeepForSdk
    public static byte[] loadImageBytes(final Bitmap bitmap) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
    }
}
