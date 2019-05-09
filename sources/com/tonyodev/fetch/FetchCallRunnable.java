package com.tonyodev.fetch;

import android.os.Handler;
import android.os.Looper;
import com.tonyodev.fetch.callback.FetchCall;
import com.tonyodev.fetch.exception.DownloadInterruptedException;
import com.tonyodev.fetch.request.Header;
import com.tonyodev.fetch.request.Request;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

final class FetchCallRunnable implements Runnable {
    private BufferedReader bufferedReader;
    private final Callback callback;
    private final FetchCall<String> fetchCall;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private HttpURLConnection httpURLConnection;
    private InputStream input;
    private volatile boolean interrupted = false;
    private final Request request;
    private String response;

    interface Callback {
        void onDone(Request request);
    }

    /* renamed from: com.tonyodev.fetch.FetchCallRunnable$1 */
    class C10841 implements Runnable {
        C10841() {
        }

        public void run() {
            FetchCallRunnable.this.fetchCall.onSuccess(FetchCallRunnable.this.response, FetchCallRunnable.this.request);
        }
    }

    FetchCallRunnable(Request request, FetchCall<String> fetchCall, Callback callback) {
        if (request == null) {
            throw new NullPointerException("Request Cannot be null");
        } else if (fetchCall == null) {
            throw new NullPointerException("FetchCall cannot be null");
        } else if (callback == null) {
            throw new NullPointerException("Callback cannot be null");
        } else {
            this.request = request;
            this.fetchCall = fetchCall;
            this.callback = callback;
        }
    }

    public void run() {
        try {
            setHttpConnectionPrefs();
            this.httpURLConnection.connect();
            int responseCode = this.httpURLConnection.getResponseCode();
            if (responseCode != 200) {
                throw new IllegalStateException("SSRV:" + responseCode);
            } else if (isInterrupted()) {
                throw new DownloadInterruptedException("DIE", -118);
            } else {
                this.input = this.httpURLConnection.getInputStream();
                this.response = inputToString();
                if (!isInterrupted()) {
                    this.handler.post(new C10841());
                }
                release();
                this.callback.onDone(this.request);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            final int error = ErrorUtils.getCode(exception.getMessage());
            if (!isInterrupted()) {
                this.handler.post(new Runnable() {
                    public void run() {
                        FetchCallRunnable.this.fetchCall.onError(error, FetchCallRunnable.this.request);
                    }
                });
            }
            release();
            this.callback.onDone(this.request);
        } catch (Throwable th) {
            release();
            this.callback.onDone(this.request);
        }
    }

    private void setHttpConnectionPrefs() throws IOException {
        this.httpURLConnection = (HttpURLConnection) new URL(this.request.getUrl()).openConnection();
        this.httpURLConnection.setRequestMethod("GET");
        this.httpURLConnection.setReadTimeout(15000);
        this.httpURLConnection.setConnectTimeout(10000);
        this.httpURLConnection.setUseCaches(true);
        this.httpURLConnection.setDefaultUseCaches(true);
        this.httpURLConnection.setInstanceFollowRedirects(true);
        this.httpURLConnection.setDoInput(true);
        for (Header header : this.request.getHeaders()) {
            this.httpURLConnection.addRequestProperty(header.getHeader(), header.getValue());
        }
    }

    private String inputToString() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        this.bufferedReader = new BufferedReader(new InputStreamReader(this.input));
        while (true) {
            String line = this.bufferedReader.readLine();
            if (line != null && !isInterrupted()) {
                stringBuilder.append(line);
            }
        }
        if (isInterrupted()) {
            return null;
        }
        return stringBuilder.toString();
    }

    private void release() {
        try {
            if (this.input != null) {
                this.input.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            if (this.bufferedReader != null) {
                this.bufferedReader.close();
            }
        } catch (IOException e2) {
            e2.printStackTrace();
        }
        if (this.httpURLConnection != null) {
            this.httpURLConnection.disconnect();
        }
    }

    private boolean isInterrupted() {
        return this.interrupted;
    }

    synchronized void interrupt() {
        this.interrupted = true;
    }

    public Request getRequest() {
        return this.request;
    }
}
