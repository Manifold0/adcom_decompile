// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import android.os.Build;

final class m implements UncaughtExceptionHandler
{
    private volatile UncaughtExceptionHandler a;
    
    final boolean a() {
        synchronized (this) {
            final Thread.UncaughtExceptionHandler defaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
            boolean b;
            if (defaultUncaughtExceptionHandler == this) {
                b = false;
            }
            else {
                this.a = defaultUncaughtExceptionHandler;
                Thread.setDefaultUncaughtExceptionHandler((Thread.UncaughtExceptionHandler)this);
                b = true;
            }
            return b;
        }
    }
    
    @Override
    public final void uncaughtException(final Thread thread, final Throwable t) {
        synchronized (this) {
            try {
                final Error error = new Error(String.format("FATAL EXCEPTION [%s]\n", thread.getName()) + String.format("Unity version     : %s\n", "2018.2.21f1") + String.format("Device model      : %s %s\n", Build.MANUFACTURER, Build.MODEL) + String.format("Device fingerprint: %s\n", Build.FINGERPRINT));
                error.setStackTrace(new StackTraceElement[0]);
                error.initCause(t);
                this.a.uncaughtException(thread, error);
            }
            catch (Throwable t2) {
                this.a.uncaughtException(thread, t);
            }
        }
    }
}
