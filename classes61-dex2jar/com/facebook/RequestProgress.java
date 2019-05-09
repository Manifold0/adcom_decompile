// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.os.Handler;

class RequestProgress
{
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final GraphRequest request;
    private final long threshold;
    
    RequestProgress(final Handler callbackHandler, final GraphRequest request) {
        this.request = request;
        this.callbackHandler = callbackHandler;
        this.threshold = FacebookSdk.getOnProgressThreshold();
    }
    
    void addProgress(final long n) {
        this.progress += n;
        if (this.progress >= this.lastReportedProgress + this.threshold || this.progress >= this.maxProgress) {
            this.reportProgress();
        }
    }
    
    void addToMax(final long n) {
        this.maxProgress += n;
    }
    
    long getMaxProgress() {
        return this.maxProgress;
    }
    
    long getProgress() {
        return this.progress;
    }
    
    void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            final GraphRequest.Callback callback = this.request.getCallback();
            if (this.maxProgress > 0L && callback instanceof GraphRequest.OnProgressCallback) {
                final long progress = this.progress;
                final long maxProgress = this.maxProgress;
                final GraphRequest.OnProgressCallback onProgressCallback = (GraphRequest.OnProgressCallback)callback;
                if (this.callbackHandler == null) {
                    onProgressCallback.onProgress(progress, maxProgress);
                }
                else {
                    this.callbackHandler.post((Runnable)new Runnable() {
                        @Override
                        public void run() {
                            onProgressCallback.onProgress(progress, maxProgress);
                        }
                    });
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }
}
