// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.player;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Semaphore;
import android.os.Looper;
import android.app.Activity;
import android.content.Context;

class c
{
    protected o a;
    protected f b;
    protected Context c;
    protected String d;
    protected String e;
    
    c(final String e, final f b) {
        this.a = null;
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = "";
        this.e = e;
        this.b = b;
    }
    
    protected void reportError(final String s) {
        if (this.b != null) {
            this.b.reportError(this.e + " Error [" + this.d + "]", s);
            return;
        }
        g.Log(6, this.e + " Error [" + this.d + "]: " + s);
    }
    
    protected void runOnUiThread(final Runnable runnable) {
        if (this.c instanceof Activity) {
            ((Activity)this.c).runOnUiThread(runnable);
            return;
        }
        g.Log(5, "Not running " + this.e + " from an Activity; Ignoring execution request...");
    }
    
    protected boolean runOnUiThreadWithSync(final Runnable runnable) {
        boolean b = false;
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
            return true;
        }
        while (true) {
            final Semaphore semaphore = new Semaphore(0);
            this.runOnUiThread(new Runnable() {
                @Override
                public final void run() {
                    try {
                        runnable.run();
                    }
                    catch (Exception ex) {
                        com.unity3d.player.c.this.reportError("Exception unloading Google VR on UI Thread. " + ex.getLocalizedMessage());
                    }
                    finally {
                        semaphore.release();
                    }
                }
            });
            try {
                if (!semaphore.tryAcquire(4L, TimeUnit.SECONDS)) {
                    this.reportError("Timeout waiting for vr state change!");
                    return b;
                }
            }
            catch (InterruptedException ex) {
                this.reportError("Interrupted while trying to acquire sync lock. " + ex.getLocalizedMessage());
                return b;
            }
            b = true;
            return b;
        }
    }
}
