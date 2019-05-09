// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.AbstractList;

public class GraphRequestBatch extends AbstractList<GraphRequest>
{
    private static AtomicInteger idGenerator;
    private String batchApplicationId;
    private Handler callbackHandler;
    private List<Callback> callbacks;
    private final String id;
    private List<GraphRequest> requests;
    private int timeoutInMilliseconds;
    
    static {
        GraphRequestBatch.idGenerator = new AtomicInteger();
    }
    
    public GraphRequestBatch() {
        this.requests = new ArrayList<GraphRequest>();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(GraphRequestBatch.idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList<Callback>();
        this.requests = new ArrayList<GraphRequest>();
    }
    
    public GraphRequestBatch(final GraphRequestBatch graphRequestBatch) {
        this.requests = new ArrayList<GraphRequest>();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(GraphRequestBatch.idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList<Callback>();
        this.requests = new ArrayList<GraphRequest>(graphRequestBatch);
        this.callbackHandler = graphRequestBatch.callbackHandler;
        this.timeoutInMilliseconds = graphRequestBatch.timeoutInMilliseconds;
        this.callbacks = new ArrayList<Callback>(graphRequestBatch.callbacks);
    }
    
    public GraphRequestBatch(final Collection<GraphRequest> collection) {
        this.requests = new ArrayList<GraphRequest>();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(GraphRequestBatch.idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList<Callback>();
        this.requests = new ArrayList<GraphRequest>(collection);
    }
    
    public GraphRequestBatch(final GraphRequest... array) {
        this.requests = new ArrayList<GraphRequest>();
        this.timeoutInMilliseconds = 0;
        this.id = Integer.valueOf(GraphRequestBatch.idGenerator.incrementAndGet()).toString();
        this.callbacks = new ArrayList<Callback>();
        this.requests = Arrays.asList(array);
    }
    
    @Override
    public final void add(final int n, final GraphRequest graphRequest) {
        this.requests.add(n, graphRequest);
    }
    
    @Override
    public final boolean add(final GraphRequest graphRequest) {
        return this.requests.add(graphRequest);
    }
    
    public void addCallback(final Callback callback) {
        if (!this.callbacks.contains(callback)) {
            this.callbacks.add(callback);
        }
    }
    
    @Override
    public final void clear() {
        this.requests.clear();
    }
    
    public final List<GraphResponse> executeAndWait() {
        return this.executeAndWaitImpl();
    }
    
    List<GraphResponse> executeAndWaitImpl() {
        return GraphRequest.executeBatchAndWait(this);
    }
    
    public final GraphRequestAsyncTask executeAsync() {
        return this.executeAsyncImpl();
    }
    
    GraphRequestAsyncTask executeAsyncImpl() {
        return GraphRequest.executeBatchAsync(this);
    }
    
    @Override
    public final GraphRequest get(final int n) {
        return this.requests.get(n);
    }
    
    public final String getBatchApplicationId() {
        return this.batchApplicationId;
    }
    
    final Handler getCallbackHandler() {
        return this.callbackHandler;
    }
    
    final List<Callback> getCallbacks() {
        return this.callbacks;
    }
    
    final String getId() {
        return this.id;
    }
    
    final List<GraphRequest> getRequests() {
        return this.requests;
    }
    
    public int getTimeout() {
        return this.timeoutInMilliseconds;
    }
    
    @Override
    public final GraphRequest remove(final int n) {
        return this.requests.remove(n);
    }
    
    public void removeCallback(final Callback callback) {
        this.callbacks.remove(callback);
    }
    
    @Override
    public final GraphRequest set(final int n, final GraphRequest graphRequest) {
        return this.requests.set(n, graphRequest);
    }
    
    public final void setBatchApplicationId(final String batchApplicationId) {
        this.batchApplicationId = batchApplicationId;
    }
    
    final void setCallbackHandler(final Handler callbackHandler) {
        this.callbackHandler = callbackHandler;
    }
    
    public void setTimeout(final int timeoutInMilliseconds) {
        if (timeoutInMilliseconds < 0) {
            throw new IllegalArgumentException("Argument timeoutInMilliseconds must be >= 0.");
        }
        this.timeoutInMilliseconds = timeoutInMilliseconds;
    }
    
    @Override
    public final int size() {
        return this.requests.size();
    }
    
    public interface Callback
    {
        void onBatchCompleted(final GraphRequestBatch p0);
    }
    
    public interface OnProgressCallback extends Callback
    {
        void onBatchProgress(final GraphRequestBatch p0, final long p1, final long p2);
    }
}
