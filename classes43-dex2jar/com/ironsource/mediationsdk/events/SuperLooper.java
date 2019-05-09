// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import com.ironsource.mediationsdk.logger.ThreadExceptionHandler;
import android.os.HandlerThread;
import android.os.Handler;

public class SuperLooper extends Thread
{
    private static SuperLooper mInstance;
    private SupersonicSdkThread mSdkThread;
    
    private SuperLooper() {
        (this.mSdkThread = new SupersonicSdkThread(this.getClass().getSimpleName())).start();
        this.mSdkThread.prepareHandler();
    }
    
    public static SuperLooper getLooper() {
        synchronized (SuperLooper.class) {
            if (SuperLooper.mInstance == null) {
                SuperLooper.mInstance = new SuperLooper();
            }
            return SuperLooper.mInstance;
        }
    }
    
    public void post(final Runnable runnable) {
        synchronized (this) {
            if (this.mSdkThread != null) {
                final Handler callbackHandler = this.mSdkThread.getCallbackHandler();
                if (callbackHandler != null) {
                    callbackHandler.post(runnable);
                }
            }
        }
    }
    
    private class SupersonicSdkThread extends HandlerThread
    {
        private Handler mHandler;
        
        SupersonicSdkThread(final String s) {
            super(s);
            this.setUncaughtExceptionHandler((UncaughtExceptionHandler)new ThreadExceptionHandler());
        }
        
        Handler getCallbackHandler() {
            return this.mHandler;
        }
        
        void prepareHandler() {
            this.mHandler = new Handler(this.getLooper());
        }
    }
}
