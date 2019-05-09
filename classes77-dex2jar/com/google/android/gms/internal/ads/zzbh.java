// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import android.util.Base64;
import android.text.TextUtils;
import java.util.HashMap;

public abstract class zzbh<K, V>
{
    private static final String TAG;
    
    static {
        TAG = zzbh.class.getSimpleName();
    }
    
    protected static <K, V> HashMap<K, V> zzk(final String s) {
        try {
            if (!TextUtils.isEmpty((CharSequence)s)) {
                return (HashMap<K, V>)new ObjectInputStream(new ByteArrayInputStream(Base64.decode(s.getBytes(), 0))).readObject();
            }
            goto Label_0048;
        }
        catch (ClassNotFoundException ex) {}
        catch (IOException ex2) {
            goto Label_0039;
        }
    }
    
    @Override
    public String toString() {
        try {
            final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            final ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(this.zzu());
            objectOutputStream.close();
            return Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0);
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    protected abstract void zzj(final String p0);
    
    protected abstract HashMap<K, V> zzu();
}
