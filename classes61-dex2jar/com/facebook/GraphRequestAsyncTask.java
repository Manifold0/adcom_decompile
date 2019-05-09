// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook;

import android.os.Looper;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Log;
import java.util.Collection;
import java.net.HttpURLConnection;
import java.util.List;
import android.os.AsyncTask;

public class GraphRequestAsyncTask extends AsyncTask<Void, Void, List<GraphResponse>>
{
    private static final String TAG;
    private final HttpURLConnection connection;
    private Exception exception;
    private final GraphRequestBatch requests;
    
    static {
        TAG = GraphRequestAsyncTask.class.getCanonicalName();
    }
    
    public GraphRequestAsyncTask(final GraphRequestBatch graphRequestBatch) {
        this(null, graphRequestBatch);
    }
    
    public GraphRequestAsyncTask(final HttpURLConnection connection, final GraphRequestBatch requests) {
        this.requests = requests;
        this.connection = connection;
    }
    
    public GraphRequestAsyncTask(final HttpURLConnection httpURLConnection, final Collection<GraphRequest> collection) {
        this(httpURLConnection, new GraphRequestBatch(collection));
    }
    
    public GraphRequestAsyncTask(final HttpURLConnection httpURLConnection, final GraphRequest... array) {
        this(httpURLConnection, new GraphRequestBatch(array));
    }
    
    public GraphRequestAsyncTask(final Collection<GraphRequest> collection) {
        this(null, new GraphRequestBatch(collection));
    }
    
    public GraphRequestAsyncTask(final GraphRequest... array) {
        this(null, new GraphRequestBatch(array));
    }
    
    protected List<GraphResponse> doInBackground(final Void... array) {
        try {
            if (this.connection == null) {
                return this.requests.executeAndWait();
            }
            return GraphRequest.executeConnectionAndWait(this.connection, this.requests);
        }
        catch (Exception exception) {
            this.exception = exception;
            return null;
        }
    }
    
    protected final Exception getException() {
        return this.exception;
    }
    
    protected final GraphRequestBatch getRequests() {
        return this.requests;
    }
    
    protected void onPostExecute(final List<GraphResponse> list) {
        super.onPostExecute((Object)list);
        if (this.exception != null) {
            Log.d(GraphRequestAsyncTask.TAG, String.format("onPostExecute: exception encountered during request: %s", this.exception.getMessage()));
        }
    }
    
    protected void onPreExecute() {
        super.onPreExecute();
        if (FacebookSdk.isDebugEnabled()) {
            Log.d(GraphRequestAsyncTask.TAG, String.format("execute async task: %s", this));
        }
        if (this.requests.getCallbackHandler() == null) {
            Handler callbackHandler;
            if (Thread.currentThread() instanceof HandlerThread) {
                callbackHandler = new Handler();
            }
            else {
                callbackHandler = new Handler(Looper.getMainLooper());
            }
            this.requests.setCallbackHandler(callbackHandler);
        }
    }
    
    public String toString() {
        return "{RequestAsyncTask: " + " connection: " + this.connection + ", requests: " + this.requests + "}";
    }
}
