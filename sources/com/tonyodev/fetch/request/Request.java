package com.tonyodev.fetch.request;

import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.adjust.sdk.Constants;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class Request {
    private final String filePath;
    private final Map<String, String> headers;
    private int priority;
    private final String url;

    public Request(@NonNull String url) {
        this(url, generateFileName(url));
    }

    public Request(@NonNull String url, @NonNull String fileName) {
        this(url, generateDirectoryName(), fileName);
    }

    public Request(@NonNull String url, @NonNull String dir, @NonNull String fileName) {
        this.headers = new ArrayMap();
        this.priority = 600;
        if (url == null || url.isEmpty()) {
            throw new NullPointerException("Url cannot be null or empty");
        } else if (dir == null || dir.isEmpty()) {
            throw new NullPointerException("directory path cannot be null or empty");
        } else if (fileName == null || fileName.isEmpty()) {
            throw new NullPointerException("File Name cannot be null or empty");
        } else {
            String scheme = Uri.parse(url).getScheme();
            if (scheme == null || !(scheme.equals("http") || scheme.equals(Constants.SCHEME))) {
                throw new IllegalArgumentException("Can only download HTTP/HTTPS URIs: " + url);
            }
            this.url = url;
            this.filePath = cleanFilePath(generateFilePath(dir, fileName));
        }
    }

    @NonNull
    public Request addHeader(@NonNull String header, @Nullable String value) {
        return addHeader(new Header(header, value));
    }

    @NonNull
    public Request addHeader(@NonNull Header header) {
        if (header == null) {
            throw new NullPointerException("Header cannot be null");
        }
        this.headers.put(header.getHeader(), header.getValue());
        return this;
    }

    @NonNull
    public Request setPriority(int priority) {
        this.priority = 600;
        if (priority == 601) {
            this.priority = 601;
        }
        return this;
    }

    @NonNull
    public String getUrl() {
        return this.url;
    }

    @NonNull
    public String getFilePath() {
        return this.filePath;
    }

    @NonNull
    public List<Header> getHeaders() {
        List<Header> headerList = new ArrayList(this.headers.size());
        for (String key : this.headers.keySet()) {
            headerList.add(new Header(key, (String) this.headers.get(key)));
        }
        return headerList;
    }

    public int getPriority() {
        return this.priority;
    }

    public String toString() {
        StringBuilder headerBuilder = new StringBuilder();
        for (Header header : getHeaders()) {
            headerBuilder.append(header.toString()).append(",");
        }
        if (this.headers.size() > 0) {
            headerBuilder.deleteCharAt(headerBuilder.length() - 1);
        }
        return "{url:" + this.url + " ,filePath:" + this.filePath + ",headers:{" + headerBuilder.toString() + "},priority:" + this.priority + "}";
    }

    private static String generateFileName(String url) {
        if (url != null) {
            return new Date().getTime() + "_" + Uri.parse(url).getLastPathSegment();
        }
        throw new NullPointerException("Url cannot be null");
    }

    private static String generateDirectoryName() {
        return Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
    }

    private static String generateFilePath(String parentDir, String fileName) {
        if (Uri.parse(fileName).getPathSegments().size() == 1) {
            return parentDir + "/" + fileName;
        }
        return fileName;
    }

    private static String cleanFilePath(String string) {
        return string.replace("//", "/");
    }
}
