// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.logging;

import java.util.Locale;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public class Logger
{
    private final String mTag;
    private final String zzei;
    private final GmsLogger zzew;
    private final int zzex;
    
    private Logger(final String mTag, final String zzei) {
        this.zzei = zzei;
        this.mTag = mTag;
        this.zzew = new GmsLogger(mTag);
        int zzex;
        for (zzex = 2; 7 >= zzex && !Log.isLoggable(this.mTag, zzex); ++zzex) {}
        this.zzex = zzex;
    }
    
    @KeepForSdk
    public Logger(final String s, final String... array) {
        String string;
        if (array.length == 0) {
            string = "";
        }
        else {
            final StringBuilder sb = new StringBuilder();
            sb.append('[');
            for (int length = array.length, i = 0; i < length; ++i) {
                final String s2 = array[i];
                if (sb.length() > 1) {
                    sb.append(",");
                }
                sb.append(s2);
            }
            sb.append(']').append(' ');
            string = sb.toString();
        }
        this(s, string);
    }
    
    private final String format(final String s, @Nullable final Object... array) {
        String format = s;
        if (array != null) {
            format = s;
            if (array.length > 0) {
                format = String.format(Locale.US, s, array);
            }
        }
        return this.zzei.concat(format);
    }
    
    @KeepForSdk
    public void d(final String s, @Nullable final Object... array) {
        if (this.isLoggable(3)) {
            Log.d(this.mTag, this.format(s, array));
        }
    }
    
    @KeepForSdk
    public void e(final String s, final Throwable t, @Nullable final Object... array) {
        Log.e(this.mTag, this.format(s, array), t);
    }
    
    @KeepForSdk
    public void e(final String s, @Nullable final Object... array) {
        Log.e(this.mTag, this.format(s, array));
    }
    
    @KeepForSdk
    public void i(final String s, @Nullable final Object... array) {
        Log.i(this.mTag, this.format(s, array));
    }
    
    @KeepForSdk
    public boolean isLoggable(final int n) {
        return this.zzex <= n;
    }
    
    @KeepForSdk
    public void v(final String s, @Nullable final Object... array) {
        if (this.isLoggable(2)) {
            Log.v(this.mTag, this.format(s, array));
        }
    }
    
    @KeepForSdk
    public void w(final String s, @Nullable final Object... array) {
        Log.w(this.mTag, this.format(s, array));
    }
    
    @KeepForSdk
    public void wtf(final String s, final Throwable t, @Nullable final Object... array) {
        Log.wtf(this.mTag, this.format(s, array), t);
    }
    
    @KeepForSdk
    public void wtf(final Throwable t) {
        Log.wtf(this.mTag, t);
    }
}
