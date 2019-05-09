// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.os.Looper;
import java.io.InputStream;
import java.io.IOException;
import android.graphics.BitmapFactory;
import java.net.URLConnection;
import java.io.Closeable;
import android.net.Uri;
import com.facebook.FacebookException;
import android.graphics.Bitmap;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;
import android.os.Handler;

public class ImageDownloader
{
    private static final int CACHE_READ_QUEUE_MAX_CONCURRENT = 2;
    private static final int DOWNLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static WorkQueue cacheReadQueue;
    private static WorkQueue downloadQueue;
    private static Handler handler;
    private static final Map<RequestKey, DownloaderContext> pendingRequests;
    
    static {
        ImageDownloader.downloadQueue = new WorkQueue(8);
        ImageDownloader.cacheReadQueue = new WorkQueue(2);
        pendingRequests = new HashMap<RequestKey, DownloaderContext>();
    }
    
    public static boolean cancelRequest(final ImageRequest imageRequest) {
        boolean b = false;
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                b = true;
                if (downloaderContext.workItem.cancel()) {
                    ImageDownloader.pendingRequests.remove(requestKey);
                }
                else {
                    downloaderContext.isCancelled = true;
                }
            }
            return b;
        }
    }
    
    public static void clearCache(final Context context) {
        ImageResponseCache.clearCache(context);
        UrlRedirectCache.clearCache();
    }
    
    private static void download(final RequestKey requestKey, Context decodeStream) {
    Label_0144_Outer:
        while (true) {
            URLConnection urlConnection = null;
            URLConnection urlConnection2 = null;
            final InputStream inputStream = null;
            final Closeable closeable = null;
            final Closeable closeable2 = null;
            final Exception ex = null;
            final IOException ex2 = null;
            final IOException ex3 = null;
            int n = 1;
            int n3;
            int n2 = n3 = 1;
            Closeable closeable3 = closeable2;
            Closeable closeable4 = closeable;
        Label_0718_Outer:
            while (true) {
                Label_0635: {
                    try {
                        final HttpURLConnection httpURLConnection = (HttpURLConnection)(urlConnection2 = new URL(requestKey.uri.toString()).openConnection());
                        n3 = n2;
                        closeable3 = closeable2;
                        urlConnection = httpURLConnection;
                        closeable4 = closeable;
                        httpURLConnection.setInstanceFollowRedirects(false);
                        urlConnection2 = httpURLConnection;
                        n3 = n2;
                        closeable3 = closeable2;
                        urlConnection = httpURLConnection;
                        closeable4 = closeable;
                        switch (httpURLConnection.getResponseCode()) {
                            case 301:
                            case 302: {
                                break Label_0635;
                            }
                            case 200: {
                                break Label_0635;
                            }
                            default: {
                                break Label_0635;
                            }
                        }
                    Label_0254_Outer:
                        while (true) {
                            urlConnection2 = httpURLConnection;
                            n3 = n2;
                            final InputStream inputStream2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            final Object o = new InputStreamReader(inputStream2);
                            urlConnection2 = httpURLConnection;
                            n3 = n2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            final char[] array = new char[128];
                            while (true) {
                                urlConnection2 = httpURLConnection;
                                n3 = n2;
                                closeable3 = inputStream2;
                                urlConnection = httpURLConnection;
                                closeable4 = inputStream2;
                                final int read = ((InputStreamReader)o).read(array, 0, array.length);
                                urlConnection2 = httpURLConnection;
                                n3 = n2;
                                closeable3 = inputStream2;
                                urlConnection = httpURLConnection;
                                closeable4 = inputStream2;
                                ((StringBuilder)decodeStream).append(array, 0, read);
                                continue Label_0144_Outer;
                            }
                            urlConnection2 = httpURLConnection;
                            n3 = n2;
                            closeable3 = closeable2;
                            urlConnection = httpURLConnection;
                            closeable4 = closeable;
                            inputStream2 = httpURLConnection.getErrorStream();
                            urlConnection2 = httpURLConnection;
                            n3 = n2;
                            closeable3 = inputStream2;
                            urlConnection = httpURLConnection;
                            closeable4 = inputStream2;
                            decodeStream = (IOException)new StringBuilder();
                            continue Label_0254_Outer;
                        }
                    }
                    // iftrue(Label_0695:, read <= 0)
                    // iftrue(Label_0758:, inputStream2 == null)
                    catch (IOException decodeStream) {
                        Utility.closeQuietly(closeable3);
                        Utility.disconnectQuietly(urlConnection2);
                        Object o = decodeStream;
                        decodeStream = ex3;
                        // iftrue(Label_0620:, removePendingRequest == null)
                        // iftrue(Label_0620:, Utility.isNullOrEmpty(headerField))
                        final HttpURLConnection httpURLConnection;
                        InputStream inputStream2 = null;
                        String headerField;
                        Uri parse;
                        DownloaderContext removePendingRequest;
                        int n4 = 0;
                        Label_0620_Outer:Block_9_Outer:
                        while (true) {
                            while (true) {
                                Block_8: {
                                Label_0620:
                                    while (true) {
                                        while (true) {
                                            if (n3 != 0) {
                                                issueResponse(requestKey, (Exception)o, (Bitmap)decodeStream, false);
                                            }
                                            return;
                                            urlConnection = httpURLConnection;
                                            closeable4 = inputStream2;
                                            o = new FacebookException(((StringBuilder)decodeStream).toString());
                                            decodeStream = ex2;
                                            n2 = n;
                                            break Label_0620;
                                            while (true) {
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                parse = Uri.parse(headerField);
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                UrlRedirectCache.cacheUriRedirect(requestKey.uri, parse);
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                removePendingRequest = removePendingRequest(requestKey);
                                                decodeStream = ex2;
                                                o = ex;
                                                n2 = n4;
                                                inputStream2 = inputStream;
                                                break Block_8;
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                enqueueCacheRead(removePendingRequest.request, new RequestKey(parse, requestKey.tag), false);
                                                inputStream2 = inputStream;
                                                n2 = n4;
                                                o = ex;
                                                decodeStream = ex2;
                                                break Label_0620;
                                                n4 = 0;
                                                n = 0;
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                headerField = httpURLConnection.getHeaderField("location");
                                                decodeStream = ex2;
                                                o = ex;
                                                n2 = n4;
                                                inputStream2 = inputStream;
                                                urlConnection = httpURLConnection;
                                                closeable4 = closeable;
                                                continue Block_9_Outer;
                                            }
                                            Utility.closeQuietly(inputStream2);
                                            Utility.disconnectQuietly(httpURLConnection);
                                            n3 = n2;
                                            continue Label_0718_Outer;
                                        }
                                        urlConnection = httpURLConnection;
                                        closeable4 = closeable;
                                        inputStream2 = ImageResponseCache.interceptAndCacheImageStream((Context)decodeStream, httpURLConnection);
                                        urlConnection = httpURLConnection;
                                        closeable4 = inputStream2;
                                        decodeStream = (IOException)BitmapFactory.decodeStream(inputStream2);
                                        o = ex;
                                        n2 = n;
                                        continue Label_0620;
                                    }
                                    Label_0695: {
                                        urlConnection = httpURLConnection;
                                    }
                                    closeable4 = inputStream2;
                                    Utility.closeQuietly((Closeable)o);
                                    continue Label_0620_Outer;
                                }
                                decodeStream = ex2;
                                o = ex;
                                n2 = n4;
                                inputStream2 = inputStream;
                                urlConnection = httpURLConnection;
                                closeable4 = closeable;
                                continue;
                            }
                            Label_0758: {
                                urlConnection = httpURLConnection;
                            }
                            closeable4 = inputStream2;
                            ((StringBuilder)decodeStream).append("Unexpected error while downloading an image.");
                            continue Label_0620_Outer;
                        }
                    }
                    // iftrue(Label_0620:, removePendingRequest.isCancelled)
                    finally {
                        Utility.closeQuietly(closeable4);
                        Utility.disconnectQuietly(urlConnection);
                    }
                }
                continue;
            }
        }
    }
    
    public static void downloadAsync(final ImageRequest request) {
        if (request == null) {
            return;
        }
        while (true) {
            final RequestKey requestKey = new RequestKey(request.getImageUri(), request.getCallerTag());
            synchronized (ImageDownloader.pendingRequests) {
                final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
                if (downloaderContext != null) {
                    downloaderContext.request = request;
                    downloaderContext.isCancelled = false;
                    downloaderContext.workItem.moveToFront();
                    return;
                }
            }
            final ImageRequest imageRequest;
            enqueueCacheRead(imageRequest, requestKey, imageRequest.isCachedRedirectAllowed());
        }
    }
    
    private static void enqueueCacheRead(final ImageRequest imageRequest, final RequestKey requestKey, final boolean b) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.cacheReadQueue, new CacheReadWorkItem(imageRequest.getContext(), requestKey, b));
    }
    
    private static void enqueueDownload(final ImageRequest imageRequest, final RequestKey requestKey) {
        enqueueRequest(imageRequest, requestKey, ImageDownloader.downloadQueue, new DownloadImageWorkItem(imageRequest.getContext(), requestKey));
    }
    
    private static void enqueueRequest(final ImageRequest request, final RequestKey requestKey, final WorkQueue workQueue, final Runnable runnable) {
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = new DownloaderContext();
            downloaderContext.request = request;
            ImageDownloader.pendingRequests.put(requestKey, downloaderContext);
            downloaderContext.workItem = workQueue.addActiveWorkItem(runnable);
        }
    }
    
    private static Handler getHandler() {
        synchronized (ImageDownloader.class) {
            if (ImageDownloader.handler == null) {
                ImageDownloader.handler = new Handler(Looper.getMainLooper());
            }
            return ImageDownloader.handler;
        }
    }
    
    private static void issueResponse(final RequestKey requestKey, final Exception ex, final Bitmap bitmap, final boolean b) {
        final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
        if (removePendingRequest != null && !removePendingRequest.isCancelled) {
            final ImageRequest request = removePendingRequest.request;
            final ImageRequest.Callback callback = request.getCallback();
            if (callback != null) {
                getHandler().post((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        callback.onCompleted(new ImageResponse(request, ex, b, bitmap));
                    }
                });
            }
        }
    }
    
    public static void prioritizeRequest(final ImageRequest imageRequest) {
        final RequestKey requestKey = new RequestKey(imageRequest.getImageUri(), imageRequest.getCallerTag());
        synchronized (ImageDownloader.pendingRequests) {
            final DownloaderContext downloaderContext = ImageDownloader.pendingRequests.get(requestKey);
            if (downloaderContext != null) {
                downloaderContext.workItem.moveToFront();
            }
        }
    }
    
    private static void readFromCache(final RequestKey requestKey, final Context context, final boolean b) {
        final InputStream inputStream = null;
        final boolean b2 = false;
        InputStream inputStream2 = inputStream;
        boolean b3 = b2;
        if (b) {
            final Uri redirectedUri = UrlRedirectCache.getRedirectedUri(requestKey.uri);
            inputStream2 = inputStream;
            b3 = b2;
            if (redirectedUri != null) {
                inputStream2 = ImageResponseCache.getCachedImageStream(redirectedUri, context);
                b3 = (inputStream2 != null);
            }
        }
        if (!b3) {
            inputStream2 = ImageResponseCache.getCachedImageStream(requestKey.uri, context);
        }
        if (inputStream2 != null) {
            final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream2);
            Utility.closeQuietly(inputStream2);
            issueResponse(requestKey, null, decodeStream, b3);
        }
        else {
            final DownloaderContext removePendingRequest = removePendingRequest(requestKey);
            if (removePendingRequest != null && !removePendingRequest.isCancelled) {
                enqueueDownload(removePendingRequest.request, requestKey);
            }
        }
    }
    
    private static DownloaderContext removePendingRequest(final RequestKey requestKey) {
        synchronized (ImageDownloader.pendingRequests) {
            return ImageDownloader.pendingRequests.remove(requestKey);
        }
    }
    
    private static class CacheReadWorkItem implements Runnable
    {
        private boolean allowCachedRedirects;
        private Context context;
        private RequestKey key;
        
        CacheReadWorkItem(final Context context, final RequestKey key, final boolean allowCachedRedirects) {
            this.context = context;
            this.key = key;
            this.allowCachedRedirects = allowCachedRedirects;
        }
        
        @Override
        public void run() {
            readFromCache(this.key, this.context, this.allowCachedRedirects);
        }
    }
    
    private static class DownloadImageWorkItem implements Runnable
    {
        private Context context;
        private RequestKey key;
        
        DownloadImageWorkItem(final Context context, final RequestKey key) {
            this.context = context;
            this.key = key;
        }
        
        @Override
        public void run() {
            download(this.key, this.context);
        }
    }
    
    private static class DownloaderContext
    {
        boolean isCancelled;
        ImageRequest request;
        WorkQueue.WorkItem workItem;
    }
    
    private static class RequestKey
    {
        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        Object tag;
        Uri uri;
        
        RequestKey(final Uri uri, final Object tag) {
            this.uri = uri;
            this.tag = tag;
        }
        
        @Override
        public boolean equals(final Object o) {
            boolean b = false;
            if (o != null) {
                b = b;
                if (o instanceof RequestKey) {
                    final RequestKey requestKey = (RequestKey)o;
                    if (requestKey.uri != this.uri || requestKey.tag != this.tag) {
                        return false;
                    }
                    b = true;
                }
            }
            return b;
        }
        
        @Override
        public int hashCode() {
            return (this.uri.hashCode() + 1073) * 37 + this.tag.hashCode();
        }
    }
}
