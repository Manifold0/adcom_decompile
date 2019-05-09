// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.internal;

import android.util.Log;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class GmsLogger
{
    private static final int zzef;
    private static final String zzeg;
    private final String zzeh;
    private final String zzei;
    
    static {
        zzef = 15;
        zzeg = null;
    }
    
    public GmsLogger(final String s) {
        this(s, null);
    }
    
    public GmsLogger(final String zzeh, final String zzei) {
        Preconditions.checkNotNull(zzeh, "log tag cannot be null");
        Preconditions.checkArgument(zzeh.length() <= 23, "tag \"%s\" is longer than the %d character maximum", zzeh, 23);
        this.zzeh = zzeh;
        if (zzei == null || zzei.length() <= 0) {
            this.zzei = null;
            return;
        }
        this.zzei = zzei;
    }
    
    private final String zza(String format, final Object... array) {
        format = String.format(format, array);
        if (this.zzei == null) {
            return format;
        }
        return this.zzei.concat(format);
    }
    
    private final String zzh(final String s) {
        if (this.zzei == null) {
            return s;
        }
        return this.zzei.concat(s);
    }
    
    @KeepForSdk
    public final boolean canLog(final int n) {
        return Log.isLoggable(this.zzeh, n);
    }
    
    @KeepForSdk
    public final boolean canLogPii() {
        return false;
    }
    
    @KeepForSdk
    public final void d(final String s, final String s2) {
        if (this.canLog(3)) {
            Log.d(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void d(final String s, final String s2, final Throwable t) {
        if (this.canLog(3)) {
            Log.d(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void e(final String s, final String s2) {
        if (this.canLog(6)) {
            Log.e(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void e(final String s, final String s2, final Throwable t) {
        if (this.canLog(6)) {
            Log.e(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void efmt(final String s, final String s2, final Object... array) {
        if (this.canLog(6)) {
            Log.e(s, this.zza(s2, array));
        }
    }
    
    @KeepForSdk
    public final void i(final String s, final String s2) {
        if (this.canLog(4)) {
            Log.i(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void i(final String s, final String s2, final Throwable t) {
        if (this.canLog(4)) {
            Log.i(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void pii(String s, final String s2) {
        if (this.canLogPii()) {
            s = String.valueOf(s);
            final String value = String.valueOf(" PII_LOG");
            if (value.length() != 0) {
                s = s.concat(value);
            }
            else {
                s = new String(s);
            }
            Log.i(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void pii(String s, final String s2, final Throwable t) {
        if (this.canLogPii()) {
            s = String.valueOf(s);
            final String value = String.valueOf(" PII_LOG");
            if (value.length() != 0) {
                s = s.concat(value);
            }
            else {
                s = new String(s);
            }
            Log.i(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void v(final String s, final String s2) {
        if (this.canLog(2)) {
            Log.v(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void v(final String s, final String s2, final Throwable t) {
        if (this.canLog(2)) {
            Log.v(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void w(final String s, final String s2) {
        if (this.canLog(5)) {
            Log.w(s, this.zzh(s2));
        }
    }
    
    @KeepForSdk
    public final void w(final String s, final String s2, final Throwable t) {
        if (this.canLog(5)) {
            Log.w(s, this.zzh(s2), t);
        }
    }
    
    @KeepForSdk
    public final void wfmt(final String s, final String s2, final Object... array) {
        if (this.canLog(5)) {
            Log.w(this.zzeh, this.zza(s2, array));
        }
    }
    
    @KeepForSdk
    public final void wtf(final String s, final String s2, final Throwable t) {
        if (this.canLog(7)) {
            Log.e(s, this.zzh(s2), t);
            Log.wtf(s, this.zzh(s2), t);
        }
    }
}
