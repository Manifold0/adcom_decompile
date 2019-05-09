// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import net.hockeyapp.android.utils.HockeyLog;
import net.hockeyapp.android.utils.ImageUtils;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.IOException;
import android.os.Build$VERSION;
import java.net.HttpURLConnection;
import java.net.URLConnection;
import java.net.URL;
import net.hockeyapp.android.Constants;
import java.io.File;
import android.graphics.Bitmap;
import net.hockeyapp.android.views.AttachmentView;
import net.hockeyapp.android.objects.FeedbackAttachment;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import android.os.Message;
import android.os.Handler;
import java.util.LinkedList;
import java.util.Queue;

public class AttachmentDownloader
{
    private boolean downloadRunning;
    private Queue<DownloadJob> queue;
    
    private AttachmentDownloader() {
        this.queue = new LinkedList<DownloadJob>();
        this.downloadRunning = false;
    }
    
    private void downloadNext() {
        if (!this.downloadRunning) {
            final DownloadJob downloadJob = this.queue.peek();
            if (downloadJob != null) {
                final DownloadTask downloadTask = new DownloadTask(downloadJob, new Handler() {
                    public void handleMessage(final Message message) {
                        final DownloadJob downloadJob = AttachmentDownloader.this.queue.poll();
                        if (!downloadJob.isSuccess() && downloadJob.consumeRetry()) {
                            this.postDelayed((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    AttachmentDownloader.this.queue.add(downloadJob);
                                    AttachmentDownloader.this.downloadNext();
                                }
                            }, 3000L);
                        }
                        AttachmentDownloader.this.downloadRunning = false;
                        AttachmentDownloader.this.downloadNext();
                    }
                });
                this.downloadRunning = true;
                AsyncTaskUtils.execute(downloadTask);
            }
        }
    }
    
    public static AttachmentDownloader getInstance() {
        return AttachmentDownloaderHolder.INSTANCE;
    }
    
    public void download(final FeedbackAttachment feedbackAttachment, final AttachmentView attachmentView) {
        this.queue.add(new DownloadJob(feedbackAttachment, attachmentView));
        this.downloadNext();
    }
    
    private static class AttachmentDownloaderHolder
    {
        public static final AttachmentDownloader INSTANCE;
        
        static {
            INSTANCE = new AttachmentDownloader(null);
        }
    }
    
    private static class DownloadJob
    {
        private final AttachmentView attachmentView;
        private final FeedbackAttachment feedbackAttachment;
        private int remainingRetries;
        private boolean success;
        
        private DownloadJob(final FeedbackAttachment feedbackAttachment, final AttachmentView attachmentView) {
            this.feedbackAttachment = feedbackAttachment;
            this.attachmentView = attachmentView;
            this.success = false;
            this.remainingRetries = 2;
        }
        
        public boolean consumeRetry() {
            final int remainingRetries = this.remainingRetries - 1;
            this.remainingRetries = remainingRetries;
            return remainingRetries >= 0;
        }
        
        public AttachmentView getAttachmentView() {
            return this.attachmentView;
        }
        
        public FeedbackAttachment getFeedbackAttachment() {
            return this.feedbackAttachment;
        }
        
        public boolean hasRetry() {
            return this.remainingRetries > 0;
        }
        
        public boolean isSuccess() {
            return this.success;
        }
        
        public void setSuccess(final boolean success) {
            this.success = success;
        }
    }
    
    private static class DownloadTask extends AsyncTask<Void, Integer, Boolean>
    {
        private Bitmap bitmap;
        private int bitmapOrientation;
        private final DownloadJob downloadJob;
        private File dropFolder;
        private final Handler handler;
        
        public DownloadTask(final DownloadJob downloadJob, final Handler handler) {
            this.downloadJob = downloadJob;
            this.handler = handler;
            this.dropFolder = Constants.getHockeyAppStorageDir();
            this.bitmap = null;
            this.bitmapOrientation = 0;
        }
        
        private URLConnection createConnection(final URL url) throws IOException {
            final HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.addRequestProperty("User-Agent", "HockeySDK/Android 4.1.2");
            httpURLConnection.setInstanceFollowRedirects(true);
            if (Build$VERSION.SDK_INT <= 9) {
                httpURLConnection.setRequestProperty("connection", "close");
            }
            return httpURLConnection;
        }
        
        private boolean downloadAttachment(final String s, final String s2) {
            BufferedInputStream bufferedInputStream;
            FileOutputStream fileOutputStream;
            long n;
            try {
                final URLConnection connection = this.createConnection(new URL(s));
                connection.connect();
                final int contentLength = connection.getContentLength();
                final String headerField = connection.getHeaderField("Status");
                if (headerField != null && !headerField.startsWith("200")) {
                    return false;
                }
                final File file = new File(this.dropFolder, s2);
                bufferedInputStream = new BufferedInputStream(connection.getInputStream());
                fileOutputStream = new FileOutputStream(file);
                final byte[] array = new byte[1024];
                n = 0L;
                while (true) {
                    final int read = bufferedInputStream.read(array);
                    if (read == -1) {
                        break;
                    }
                    n += read;
                    this.publishProgress((Object[])new Integer[] { (int)(100L * n / contentLength) });
                    fileOutputStream.write(array, 0, read);
                }
            }
            catch (IOException ex) {
                ex.printStackTrace();
                return false;
            }
            fileOutputStream.flush();
            fileOutputStream.close();
            bufferedInputStream.close();
            return n > 0L;
        }
        
        private void loadImageThumbnail() {
            try {
                final String cacheId = this.downloadJob.getFeedbackAttachment().getCacheId();
                final AttachmentView attachmentView = this.downloadJob.getAttachmentView();
                this.bitmapOrientation = ImageUtils.determineOrientation(new File(this.dropFolder, cacheId));
                int n;
                if (this.bitmapOrientation == 1) {
                    n = attachmentView.getWidthLandscape();
                }
                else {
                    n = attachmentView.getWidthPortrait();
                }
                int n2;
                if (this.bitmapOrientation == 1) {
                    n2 = attachmentView.getMaxHeightLandscape();
                }
                else {
                    n2 = attachmentView.getMaxHeightPortrait();
                }
                this.bitmap = ImageUtils.decodeSampledBitmap(new File(this.dropFolder, cacheId), n, n2);
            }
            catch (IOException ex) {
                ex.printStackTrace();
                this.bitmap = null;
            }
        }
        
        protected Boolean doInBackground(final Void... array) {
            final FeedbackAttachment feedbackAttachment = this.downloadJob.getFeedbackAttachment();
            if (feedbackAttachment.isAvailableInCache()) {
                HockeyLog.error("Cached...");
                this.loadImageThumbnail();
                return true;
            }
            HockeyLog.error("Downloading...");
            final boolean downloadAttachment = this.downloadAttachment(feedbackAttachment.getUrl(), feedbackAttachment.getCacheId());
            if (downloadAttachment) {
                this.loadImageThumbnail();
            }
            return downloadAttachment;
        }
        
        protected void onPostExecute(final Boolean b) {
            final AttachmentView attachmentView = this.downloadJob.getAttachmentView();
            this.downloadJob.setSuccess(b);
            if (b) {
                attachmentView.setImage(this.bitmap, this.bitmapOrientation);
            }
            else if (!this.downloadJob.hasRetry()) {
                attachmentView.signalImageLoadingError();
            }
            this.handler.sendEmptyMessage(0);
        }
        
        protected void onPreExecute() {
        }
        
        protected void onProgressUpdate(final Integer... array) {
        }
    }
}
