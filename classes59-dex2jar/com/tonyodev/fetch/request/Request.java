// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.request;

import java.util.Iterator;
import java.util.ArrayList;
import java.util.List;
import android.support.annotation.Nullable;
import java.util.Date;
import android.os.Environment;
import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.support.annotation.NonNull;
import java.util.Map;

public final class Request
{
    private final String filePath;
    private final Map<String, String> headers;
    private int priority;
    private final String url;
    
    public Request(@NonNull final String s) {
        this(s, generateFileName(s));
    }
    
    public Request(@NonNull final String s, @NonNull final String s2) {
        this(s, generateDirectoryName(), s2);
    }
    
    public Request(@NonNull final String url, @NonNull final String s, @NonNull final String s2) {
        this.headers = (Map<String, String>)new ArrayMap();
        this.priority = 600;
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("Url cannot be null or empty");
        }
        if (s == null || s.isEmpty()) {
            throw new NullPointerException("directory path cannot be null or empty");
        }
        if (s2 == null || s2.isEmpty()) {
            throw new NullPointerException("File Name cannot be null or empty");
        }
        final String scheme = Uri.parse(url).getScheme();
        if (scheme == null || (!scheme.equals("http") && !scheme.equals("https"))) {
            throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + url);
        }
        this.url = url;
        this.filePath = cleanFilePath(generateFilePath(s, s2));
    }
    
    private static String cleanFilePath(final String s) {
        return s.replace("//", "/");
    }
    
    private static String generateDirectoryName() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    }
    
    private static String generateFileName(final String s) {
        if (s == null) {
            throw new NullPointerException("Url cannot be null");
        }
        return new Date().getTime() + "_" + Uri.parse(s).getLastPathSegment();
    }
    
    private static String generateFilePath(final String s, final String s2) {
        String string = s2;
        if (Uri.parse(s2).getPathSegments().size() == 1) {
            string = s + "/" + s2;
        }
        return string;
    }
    
    @NonNull
    public Request addHeader(@NonNull final Header header) {
        if (header == null) {
            throw new NullPointerException("Header cannot be null");
        }
        this.headers.put(header.getHeader(), header.getValue());
        return this;
    }
    
    @NonNull
    public Request addHeader(@NonNull final String s, @Nullable final String s2) {
        return this.addHeader(new Header(s, s2));
    }
    
    @NonNull
    public String getFilePath() {
        return this.filePath;
    }
    
    @NonNull
    public List<Header> getHeaders() {
        final ArrayList<Header> list = new ArrayList<Header>(this.headers.size());
        for (final String s : this.headers.keySet()) {
            list.add(new Header(s, this.headers.get(s)));
        }
        return list;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    @NonNull
    public String getUrl() {
        return this.url;
    }
    
    @NonNull
    public Request setPriority(final int n) {
        this.priority = 600;
        if (n == 601) {
            this.priority = 601;
        }
        return this;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Header> iterator = this.getHeaders().iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString()).append(",");
        }
        if (this.headers.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "{url:" + this.url + " ,filePath:" + this.filePath + ",headers:{" + sb.toString() + "},priority:" + this.priority + "}";
    }
}
