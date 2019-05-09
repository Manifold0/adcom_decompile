// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.eventsmodule;

import java.io.OutputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import android.os.AsyncTask;

public class EventsSender extends AsyncTask<Object, Void, Boolean>
{
    private final String APPLICATION_JSON;
    private final String CONTENT_TYPE_FIELD;
    private final String SERVER_REQUEST_ENCODING;
    private final String SERVER_REQUEST_METHOD;
    private final int SERVER_REQUEST_TIMEOUT;
    private ArrayList extraData;
    private IEventsSenderResultListener mResultListener;
    
    public EventsSender() {
        this.SERVER_REQUEST_TIMEOUT = 15000;
        this.SERVER_REQUEST_METHOD = "POST";
        this.SERVER_REQUEST_ENCODING = "UTF-8";
        this.CONTENT_TYPE_FIELD = "Content-Type";
        this.APPLICATION_JSON = "application/json";
    }
    
    public EventsSender(final IEventsSenderResultListener mResultListener) {
        this.SERVER_REQUEST_TIMEOUT = 15000;
        this.SERVER_REQUEST_METHOD = "POST";
        this.SERVER_REQUEST_ENCODING = "UTF-8";
        this.CONTENT_TYPE_FIELD = "Content-Type";
        this.APPLICATION_JSON = "application/json";
        this.mResultListener = mResultListener;
    }
    
    protected Boolean doInBackground(final Object... array) {
        try {
            final URL url = new URL((String)array[1]);
            this.extraData = (ArrayList)array[2];
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setReadTimeout(15000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            final OutputStream outputStream = httpURLConnection.getOutputStream();
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            bufferedWriter.write((String)array[0]);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            final int responseCode = httpURLConnection.getResponseCode();
            httpURLConnection.disconnect();
            return responseCode == 200;
        }
        catch (Exception ex) {
            return false;
        }
    }
    
    protected void onPostExecute(final Boolean b) {
        if (this.mResultListener != null) {
            this.mResultListener.onEventsSenderResult(this.extraData, b);
        }
    }
}
