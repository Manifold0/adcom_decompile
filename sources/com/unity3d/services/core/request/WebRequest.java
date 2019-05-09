package com.unity3d.services.core.request;

import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.unity3d.services.core.log.DeviceLog;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;

public class WebRequest {
    private String _body;
    private boolean _canceled;
    private int _connectTimeout;
    private long _contentLength;
    private Map<String, List<String>> _headers;
    private IWebRequestProgressListener _progressListener;
    private int _readTimeout;
    private String _requestType;
    private int _responseCode;
    private Map<String, List<String>> _responseHeaders;
    private URL _url;

    public enum RequestType {
        POST,
        GET,
        HEAD
    }

    public WebRequest(String url, String requestType, Map<String, List<String>> headers) throws MalformedURLException {
        this(url, requestType, headers, 30000, 30000);
    }

    public WebRequest(String url, String requestType, Map<String, List<String>> headers, int connectTimeout, int readTimeout) throws MalformedURLException {
        this._requestType = RequestType.GET.name();
        this._responseCode = -1;
        this._contentLength = -1;
        this._canceled = false;
        this._url = new URL(url);
        this._requestType = requestType;
        this._headers = headers;
        this._connectTimeout = connectTimeout;
        this._readTimeout = readTimeout;
    }

    public void cancel() {
        this._canceled = true;
    }

    public boolean isCanceled() {
        return this._canceled;
    }

    public URL getUrl() {
        return this._url;
    }

    public String getRequestType() {
        return this._requestType;
    }

    public String getBody() {
        return this._body;
    }

    public void setBody(String body) {
        this._body = body;
    }

    public String getQuery() {
        if (this._url != null) {
            return this._url.getQuery();
        }
        return null;
    }

    public Map<String, List<String>> getResponseHeaders() {
        return this._responseHeaders;
    }

    public Map<String, List<String>> getHeaders() {
        return this._headers;
    }

    public int getResponseCode() {
        return this._responseCode;
    }

    public long getContentLength() {
        return this._contentLength;
    }

    public int getConnectTimeout() {
        return this._connectTimeout;
    }

    public void setConnectTimeout(int timeout) {
        this._connectTimeout = timeout;
    }

    public int getReadTimeout() {
        return this._readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this._readTimeout = readTimeout;
    }

    public void setProgressListener(IWebRequestProgressListener listener) {
        this._progressListener = listener;
    }

    public long makeStreamRequest(OutputStream outputStream) throws NetworkIOException, IOException, IllegalStateException {
        Exception e;
        IOException e2;
        Throwable th;
        HttpURLConnection connection = getHttpUrlConnectionWithHeaders();
        connection.setDoInput(true);
        if (getRequestType().equals(RequestType.POST.name())) {
            connection.setDoOutput(true);
            PrintWriter printWriter = null;
            try {
                PrintWriter pout = new PrintWriter(new OutputStreamWriter(connection.getOutputStream(), "UTF-8"), true);
                try {
                    if (getBody() == null) {
                        pout.print(getQuery());
                    } else {
                        pout.print(getBody());
                    }
                    pout.flush();
                    if (pout != null) {
                        try {
                            pout.close();
                        } catch (Exception e3) {
                            DeviceLog.exception("Error closing writer", e3);
                            throw e3;
                        }
                    }
                } catch (IOException e4) {
                    e2 = e4;
                    printWriter = pout;
                    try {
                        DeviceLog.exception("Error while writing POST params", e2);
                        throw new NetworkIOException("Error writing POST params: " + e2.getMessage());
                    } catch (Throwable th2) {
                        th = th2;
                        if (printWriter != null) {
                            try {
                                printWriter.close();
                            } catch (Exception e32) {
                                DeviceLog.exception("Error closing writer", e32);
                                throw e32;
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                    printWriter = pout;
                    if (printWriter != null) {
                        printWriter.close();
                    }
                    throw th;
                }
            } catch (IOException e5) {
                e2 = e5;
                DeviceLog.exception("Error while writing POST params", e2);
                throw new NetworkIOException("Error writing POST params: " + e2.getMessage());
            }
        }
        try {
            InputStream input;
            this._responseCode = connection.getResponseCode();
            this._contentLength = (long) connection.getContentLength();
            if (connection.getHeaderFields() != null) {
                this._responseHeaders = connection.getHeaderFields();
            }
            try {
                input = connection.getInputStream();
            } catch (IOException e22) {
                input = connection.getErrorStream();
                if (input == null) {
                    throw new NetworkIOException("Can't open error stream: " + e22.getMessage());
                }
            }
            if (this._progressListener != null) {
                this._progressListener.onRequestStart(getUrl().toString(), this._contentLength, this._responseCode, this._responseHeaders);
            }
            BufferedInputStream binput = new BufferedInputStream(input);
            int bytesRead = 0;
            long total = 0;
            byte[] readTarget = new byte[4096];
            while (!isCanceled() && bytesRead != -1) {
                try {
                    bytesRead = binput.read(readTarget);
                    if (bytesRead > 0) {
                        outputStream.write(readTarget, 0, bytesRead);
                        total += (long) bytesRead;
                        if (this._progressListener != null) {
                            this._progressListener.onRequestProgress(getUrl().toString(), total, this._contentLength);
                        }
                    }
                } catch (IOException e222) {
                    throw new NetworkIOException("Network exception: " + e222.getMessage());
                }
            }
            connection.disconnect();
            outputStream.flush();
            return total;
        } catch (IOException e6) {
            e32 = e6;
            throw new NetworkIOException("Response code: " + e32.getMessage());
        } catch (RuntimeException e7) {
            e32 = e7;
            throw new NetworkIOException("Response code: " + e32.getMessage());
        }
    }

    public String makeRequest() throws NetworkIOException, IOException, IllegalStateException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        makeStreamRequest(baos);
        return baos.toString("UTF-8");
    }

    private HttpURLConnection getHttpUrlConnectionWithHeaders() throws NetworkIOException {
        HttpURLConnection connection;
        if (getUrl().toString().startsWith("https://")) {
            try {
                connection = (HttpsURLConnection) getUrl().openConnection();
            } catch (IOException e) {
                throw new NetworkIOException("Open HTTPS connection: " + e.getMessage());
            }
        }
        try {
            connection = (HttpURLConnection) getUrl().openConnection();
        } catch (IOException e2) {
            throw new NetworkIOException("Open HTTP connection: " + e2.getMessage());
        }
        connection.setInstanceFollowRedirects(false);
        connection.setConnectTimeout(getConnectTimeout());
        connection.setReadTimeout(getReadTimeout());
        try {
            connection.setRequestMethod(getRequestType());
            if (getHeaders() != null && getHeaders().size() > 0) {
                for (String k : getHeaders().keySet()) {
                    for (String value : (List) getHeaders().get(k)) {
                        DeviceLog.debug("Setting header: " + k + RequestParameters.EQUAL + value);
                        connection.setRequestProperty(k, value);
                    }
                }
            }
            return connection;
        } catch (ProtocolException e3) {
            throw new NetworkIOException("Set Request Method: " + getRequestType() + ", " + e3.getMessage());
        }
    }
}
