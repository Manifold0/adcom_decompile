// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.utils;

import android.net.Uri;
import java.util.List;
import android.content.Context;
import java.io.IOException;
import java.io.BufferedOutputStream;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import android.os.Build$VERSION;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import android.text.TextUtils;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HttpURLConnectionBuilder
{
    public static final String DEFAULT_CHARSET = "UTF-8";
    private static final int DEFAULT_TIMEOUT = 120000;
    private final Map<String, String> mHeaders;
    private SimpleMultipartEntity mMultipartEntity;
    private String mRequestBody;
    private String mRequestMethod;
    private int mTimeout;
    private final String mUrlString;
    
    public HttpURLConnectionBuilder(final String mUrlString) {
        this.mTimeout = 120000;
        this.mUrlString = mUrlString;
        (this.mHeaders = new HashMap<String, String>()).put("User-Agent", "HockeySDK/Android 4.1.2");
    }
    
    private static String getFormString(final Map<String, String> map, final String s) throws UnsupportedEncodingException {
        final ArrayList<String> list = new ArrayList<String>();
        for (final String s2 : map.keySet()) {
            list.add(URLEncoder.encode(s2, s) + "=" + URLEncoder.encode(map.get(s2), s));
        }
        return TextUtils.join((CharSequence)"&", (Iterable)list);
    }
    
    public HttpURLConnection build() throws IOException {
        final HttpURLConnection httpURLConnection = (HttpURLConnection)new URL(this.mUrlString).openConnection();
        httpURLConnection.setConnectTimeout(this.mTimeout);
        httpURLConnection.setReadTimeout(this.mTimeout);
        if (Build$VERSION.SDK_INT <= 9) {
            httpURLConnection.setRequestProperty("Connection", "close");
        }
        if (!TextUtils.isEmpty((CharSequence)this.mRequestMethod)) {
            httpURLConnection.setRequestMethod(this.mRequestMethod);
            if (!TextUtils.isEmpty((CharSequence)this.mRequestBody) || this.mRequestMethod.equalsIgnoreCase("POST") || this.mRequestMethod.equalsIgnoreCase("PUT")) {
                httpURLConnection.setDoOutput(true);
            }
        }
        for (final String s : this.mHeaders.keySet()) {
            httpURLConnection.setRequestProperty(s, this.mHeaders.get(s));
        }
        if (!TextUtils.isEmpty((CharSequence)this.mRequestBody)) {
            final BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(httpURLConnection.getOutputStream(), "UTF-8"));
            bufferedWriter.write(this.mRequestBody);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        if (this.mMultipartEntity != null) {
            httpURLConnection.setRequestProperty("Content-Length", String.valueOf(this.mMultipartEntity.getContentLength()));
            final BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
            bufferedOutputStream.write(this.mMultipartEntity.getOutputStream().toByteArray());
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        }
        return httpURLConnection;
    }
    
    public HttpURLConnectionBuilder setBasicAuthorization(final String s, final String s2) {
        this.setHeader("Authorization", "Basic " + Base64.encodeToString((s + ":" + s2).getBytes(), 2));
        return this;
    }
    
    public HttpURLConnectionBuilder setHeader(final String s, final String s2) {
        this.mHeaders.put(s, s2);
        return this;
    }
    
    public HttpURLConnectionBuilder setRequestBody(final String mRequestBody) {
        this.mRequestBody = mRequestBody;
        return this;
    }
    
    public HttpURLConnectionBuilder setRequestMethod(final String mRequestMethod) {
        this.mRequestMethod = mRequestMethod;
        return this;
    }
    
    public HttpURLConnectionBuilder setTimeout(final int mTimeout) {
        if (mTimeout < 0) {
            throw new IllegalArgumentException("Timeout has to be positive.");
        }
        this.mTimeout = mTimeout;
        return this;
    }
    
    public HttpURLConnectionBuilder writeFormFields(final Map<String, String> map) {
        try {
            final String formString = getFormString(map, "UTF-8");
            this.setHeader("Content-Type", "application/x-www-form-urlencoded");
            this.setRequestBody(formString);
            return this;
        }
        catch (UnsupportedEncodingException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public HttpURLConnectionBuilder writeMultipartData(final Map<String, String> map, final Context context, final List<Uri> list) {
        try {
            (this.mMultipartEntity = new SimpleMultipartEntity()).writeFirstBoundaryIfNeeds();
            for (final String s : map.keySet()) {
                this.mMultipartEntity.addPart(s, map.get(s));
            }
        }
        catch (IOException ex) {
            throw new RuntimeException(ex);
        }
        for (int i = 0; i < list.size(); ++i) {
            final Uri uri = list.get(i);
            this.mMultipartEntity.addPart("attachment" + i, uri.getLastPathSegment(), context.getContentResolver().openInputStream(uri), i == list.size() - 1);
        }
        this.mMultipartEntity.writeLastBoundaryIfNeeds();
        this.setHeader("Content-Type", "multipart/form-data; boundary=" + this.mMultipartEntity.getBoundary());
        return this;
    }
}
