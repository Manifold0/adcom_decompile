package com.ironsource.eventsmodule;

import android.os.AsyncTask;
import com.kongregate.p000o.p002g.C0640a;
import java.io.BufferedWriter;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class EventsSender extends AsyncTask<Object, Void, Boolean> {
    private final String APPLICATION_JSON = C0640a.f1003a;
    private final String CONTENT_TYPE_FIELD = "Content-Type";
    private final String SERVER_REQUEST_ENCODING = "UTF-8";
    private final String SERVER_REQUEST_METHOD = "POST";
    private final int SERVER_REQUEST_TIMEOUT = 15000;
    private ArrayList extraData;
    private IEventsSenderResultListener mResultListener;

    public EventsSender(IEventsSenderResultListener resultListener) {
        this.mResultListener = resultListener;
    }

    protected Boolean doInBackground(Object... objects) {
        try {
            boolean z;
            URL requestURL = new URL((String) objects[1]);
            this.extraData = (ArrayList) objects[2];
            HttpURLConnection conn = (HttpURLConnection) requestURL.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", C0640a.f1003a);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write((String) objects[0]);
            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();
            conn.disconnect();
            if (responseCode == 200) {
                z = true;
            } else {
                z = false;
            }
            return Boolean.valueOf(z);
        } catch (Exception e) {
            return Boolean.valueOf(false);
        }
    }

    protected void onPostExecute(Boolean success) {
        if (this.mResultListener != null) {
            this.mResultListener.onEventsSenderResult(this.extraData, success.booleanValue());
        }
    }
}
