// 
// Decompiled by Procyon v0.5.34
// 

package com.tonyodev.fetch.request;

import java.util.Iterator;
import android.support.annotation.NonNull;
import java.util.List;

public final class RequestInfo
{
    private final long downloadedBytes;
    private final int error;
    private final String filePath;
    private final long fileSize;
    private final List<Header> headers;
    private final long id;
    private final int priority;
    private final int progress;
    private final int status;
    private final String url;
    
    public RequestInfo(final long id, final int status, @NonNull final String url, @NonNull final String filePath, final int progress, final long downloadedBytes, final long fileSize, final int error, @NonNull final List<Header> headers, final int priority) {
        if (url == null) {
            throw new NullPointerException("Url cannot be null");
        }
        if (filePath == null) {
            throw new NullPointerException("FilePath cannot be null");
        }
        if (headers == null) {
            throw new NullPointerException("Headers cannot be null");
        }
        this.id = id;
        this.status = status;
        this.url = url;
        this.filePath = filePath;
        this.progress = progress;
        this.downloadedBytes = downloadedBytes;
        this.fileSize = fileSize;
        this.error = error;
        this.headers = headers;
        this.priority = priority;
    }
    
    public long getDownloadedBytes() {
        return this.downloadedBytes;
    }
    
    public int getError() {
        return this.error;
    }
    
    @NonNull
    public String getFilePath() {
        return this.filePath;
    }
    
    public long getFileSize() {
        return this.fileSize;
    }
    
    @NonNull
    public List<Header> getHeaders() {
        return this.headers;
    }
    
    public long getId() {
        return this.id;
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    public int getProgress() {
        return this.progress;
    }
    
    public int getStatus() {
        return this.status;
    }
    
    @NonNull
    public String getUrl() {
        return this.url;
    }
    
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        final Iterator<Header> iterator = this.headers.iterator();
        while (iterator.hasNext()) {
            sb.append(iterator.next().toString()).append(",");
        }
        if (this.headers.size() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return "{id:" + this.id + ",status:" + this.status + ",url:" + this.url + ",filePath:" + this.filePath + ",progress:" + this.progress + ",fileSize:" + this.fileSize + ",error:" + this.error + ",headers:{" + sb.toString() + "},priority:" + this.priority + "}";
    }
}
