// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import java.util.HashMap;
import java.util.Map;
import android.os.Handler;
import java.io.OutputStream;

class ProgressNoopOutputStream extends OutputStream implements RequestOutputStream
{
    private int batchMax;
    private final Handler callbackHandler;
    private GraphRequest currentRequest;
    private RequestProgress currentRequestProgress;
    private final Map<GraphRequest, RequestProgress> progressMap;
    
    ProgressNoopOutputStream(final Handler callbackHandler) {
        this.progressMap = new HashMap<GraphRequest, RequestProgress>();
        this.callbackHandler = callbackHandler;
    }
    
    void addProgress(final long n) {
        if (this.currentRequestProgress == null) {
            this.currentRequestProgress = new RequestProgress(this.callbackHandler, this.currentRequest);
            this.progressMap.put(this.currentRequest, this.currentRequestProgress);
        }
        this.currentRequestProgress.addToMax(n);
        this.batchMax += (int)n;
    }
    
    int getMaxProgress() {
        return this.batchMax;
    }
    
    Map<GraphRequest, RequestProgress> getProgressMap() {
        return this.progressMap;
    }
    
    @Override
    public void setCurrentRequest(final GraphRequest currentRequest) {
        this.currentRequest = currentRequest;
        RequestProgress currentRequestProgress;
        if (currentRequest != null) {
            currentRequestProgress = this.progressMap.get(currentRequest);
        }
        else {
            currentRequestProgress = null;
        }
        this.currentRequestProgress = currentRequestProgress;
    }
    
    @Override
    public void write(final int n) {
        this.addProgress(1L);
    }
    
    @Override
    public void write(final byte[] array) {
        this.addProgress(array.length);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        this.addProgress(n2);
    }
}
