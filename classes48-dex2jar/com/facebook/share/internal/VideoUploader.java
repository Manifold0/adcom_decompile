// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.share.internal;

import com.facebook.FacebookRequestError;
import com.facebook.GraphResponse;
import com.facebook.FacebookGraphResponseException;
import com.facebook.GraphRequest$Callback;
import com.facebook.GraphRequest;
import com.facebook.HttpMethod;
import com.facebook.FacebookSdk;
import android.os.ParcelFileDescriptor$AutoCloseInputStream;
import android.os.ParcelFileDescriptor;
import java.io.File;
import android.text.TextUtils;
import java.util.Collection;
import com.facebook.internal.WorkQueue$WorkItem;
import android.net.Uri;
import java.io.InputStream;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import com.facebook.share.model.ShareVideo;
import com.facebook.internal.Validate;
import java.io.FileNotFoundException;
import com.facebook.share.Sharer$Result;
import com.facebook.share.model.ShareVideoContent;
import com.facebook.AccessToken;
import android.util.Log;
import java.util.Locale;
import com.facebook.FacebookCallback;
import java.io.Closeable;
import android.os.Looper;
import java.io.ByteArrayOutputStream;
import com.facebook.internal.Utility;
import java.util.Iterator;
import com.facebook.FacebookException;
import java.io.IOException;
import java.util.HashSet;
import com.facebook.internal.WorkQueue;
import java.util.Set;
import android.os.Handler;
import com.facebook.AccessTokenTracker;

public class VideoUploader
{
    private static final String ERROR_BAD_SERVER_RESPONSE = "Unexpected error in server response";
    private static final String ERROR_UPLOAD = "Video upload failed";
    private static final int MAX_RETRIES_PER_PHASE = 2;
    private static final String PARAM_DESCRIPTION = "description";
    private static final String PARAM_END_OFFSET = "end_offset";
    private static final String PARAM_FILE_SIZE = "file_size";
    private static final String PARAM_REF = "ref";
    private static final String PARAM_SESSION_ID = "upload_session_id";
    private static final String PARAM_START_OFFSET = "start_offset";
    private static final String PARAM_TITLE = "title";
    private static final String PARAM_UPLOAD_PHASE = "upload_phase";
    private static final String PARAM_VALUE_UPLOAD_FINISH_PHASE = "finish";
    private static final String PARAM_VALUE_UPLOAD_START_PHASE = "start";
    private static final String PARAM_VALUE_UPLOAD_TRANSFER_PHASE = "transfer";
    private static final String PARAM_VIDEO_FILE_CHUNK = "video_file_chunk";
    private static final String PARAM_VIDEO_ID = "video_id";
    private static final int RETRY_DELAY_BACK_OFF_FACTOR = 3;
    private static final int RETRY_DELAY_UNIT_MS = 5000;
    private static final String TAG = "VideoUploader";
    private static final int UPLOAD_QUEUE_MAX_CONCURRENT = 8;
    private static AccessTokenTracker accessTokenTracker;
    private static Handler handler;
    private static boolean initialized;
    private static Set<UploadContext> pendingUploads;
    private static WorkQueue uploadQueue;
    
    static {
        VideoUploader.uploadQueue = new WorkQueue(8);
        VideoUploader.pendingUploads = new HashSet<UploadContext>();
    }
    
    private static void cancelAllRequests() {
        synchronized (VideoUploader.class) {
            final Iterator<UploadContext> iterator = VideoUploader.pendingUploads.iterator();
            while (iterator.hasNext()) {
                iterator.next().isCanceled = true;
            }
        }
    }
    // monitorexit(VideoUploader.class)
    
    private static void enqueueRequest(final UploadContext uploadContext, final Runnable runnable) {
        synchronized (VideoUploader.class) {
            uploadContext.workItem = VideoUploader.uploadQueue.addActiveWorkItem(runnable);
        }
    }
    
    private static void enqueueUploadChunk(final UploadContext uploadContext, final String s, final String s2, final int n) {
        enqueueRequest(uploadContext, new TransferChunkWorkItem(uploadContext, s, s2, n));
    }
    
    private static void enqueueUploadFinish(final UploadContext uploadContext, final int n) {
        enqueueRequest(uploadContext, new FinishUploadWorkItem(uploadContext, n));
    }
    
    private static void enqueueUploadStart(final UploadContext uploadContext, final int n) {
        enqueueRequest(uploadContext, new StartUploadWorkItem(uploadContext, n));
    }
    
    private static byte[] getChunk(final UploadContext uploadContext, final String s, final String chunkStart) throws IOException {
        if (!Utility.areObjectsEqual((Object)s, (Object)uploadContext.chunkStart)) {
            logError(null, "Error reading video chunk. Expected chunk '%s'. Requested chunk '%s'.", uploadContext.chunkStart, s);
            return null;
        }
        int n = (int)(Long.parseLong(chunkStart) - Long.parseLong(s));
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        final byte[] array = new byte[Math.min(8192, n)];
        int n2;
        int read;
        do {
            read = uploadContext.videoStream.read(array);
            if (read != -1) {
                byteArrayOutputStream.write(array, 0, read);
                n2 = n - read;
                if (n2 != 0) {
                    continue;
                }
            }
            uploadContext.chunkStart = chunkStart;
            return byteArrayOutputStream.toByteArray();
        } while ((n = n2) >= 0);
        logError(null, "Error reading video chunk. Expected buffer length - '%d'. Actual - '%d'.", n2 + read, read);
        return null;
    }
    
    private static Handler getHandler() {
        synchronized (VideoUploader.class) {
            if (VideoUploader.handler == null) {
                VideoUploader.handler = new Handler(Looper.getMainLooper());
            }
            return VideoUploader.handler;
        }
    }
    
    private static void issueResponse(final UploadContext uploadContext, final FacebookException ex, final String s) {
        removePendingUpload(uploadContext);
        Utility.closeQuietly((Closeable)uploadContext.videoStream);
        if (uploadContext.callback != null) {
            if (ex != null) {
                ShareInternalUtility.invokeOnErrorCallback((FacebookCallback)uploadContext.callback, ex);
            }
            else {
                if (uploadContext.isCanceled) {
                    ShareInternalUtility.invokeOnCancelCallback((FacebookCallback)uploadContext.callback);
                    return;
                }
                ShareInternalUtility.invokeOnSuccessCallback((FacebookCallback)uploadContext.callback, s);
            }
        }
    }
    
    private static void logError(final Exception ex, final String s, final Object... array) {
        Log.e("VideoUploader", String.format(Locale.ROOT, s, array), (Throwable)ex);
    }
    
    private static void registerAccessTokenTracker() {
        VideoUploader.accessTokenTracker = new AccessTokenTracker() {
            protected void onCurrentAccessTokenChanged(final AccessToken accessToken, final AccessToken accessToken2) {
                if (accessToken != null && (accessToken2 == null || !Utility.areObjectsEqual((Object)accessToken2.getUserId(), (Object)accessToken.getUserId()))) {
                    cancelAllRequests();
                }
            }
        };
    }
    
    private static void removePendingUpload(final UploadContext uploadContext) {
        synchronized (VideoUploader.class) {
            VideoUploader.pendingUploads.remove(uploadContext);
        }
    }
    
    public static void uploadAsync(final ShareVideoContent shareVideoContent, final FacebookCallback<Sharer$Result> facebookCallback) throws FileNotFoundException {
        synchronized (VideoUploader.class) {
            uploadAsync(shareVideoContent, "me", facebookCallback);
        }
    }
    
    public static void uploadAsync(final ShareVideoContent shareVideoContent, final String s, final FacebookCallback<Sharer$Result> facebookCallback) throws FileNotFoundException {
        synchronized (VideoUploader.class) {
            if (!VideoUploader.initialized) {
                registerAccessTokenTracker();
                VideoUploader.initialized = true;
            }
            Validate.notNull((Object)shareVideoContent, "videoContent");
            Validate.notNull((Object)s, "graphNode");
            final ShareVideo video = shareVideoContent.getVideo();
            Validate.notNull((Object)video, "videoContent.video");
            Validate.notNull((Object)video.getLocalUrl(), "videoContent.video.localUrl");
            final UploadContext uploadContext = new UploadContext(shareVideoContent, s, (FacebookCallback)facebookCallback);
            uploadContext.initialize();
            VideoUploader.pendingUploads.add(uploadContext);
            enqueueUploadStart(uploadContext, 0);
        }
    }
    
    private static class FinishUploadWorkItem extends UploadWorkItemBase
    {
        static final Set<Integer> transientErrorCodes;
        
        static {
            transientErrorCodes = new HashSet<Integer>() {
                {
                    this.add(1363011);
                }
            };
        }
        
        public FinishUploadWorkItem(final UploadContext uploadContext, final int n) {
            super(uploadContext, n);
        }
        
        @Override
        protected void enqueueRetry(final int n) {
            enqueueUploadFinish(this.uploadContext, n);
        }
        
        public Bundle getParameters() {
            final Bundle bundle = new Bundle();
            if (this.uploadContext.params != null) {
                bundle.putAll(this.uploadContext.params);
            }
            bundle.putString("upload_phase", "finish");
            bundle.putString("upload_session_id", this.uploadContext.sessionId);
            Utility.putNonEmptyString(bundle, "title", this.uploadContext.title);
            Utility.putNonEmptyString(bundle, "description", this.uploadContext.description);
            Utility.putNonEmptyString(bundle, "ref", this.uploadContext.ref);
            return bundle;
        }
        
        @Override
        protected Set<Integer> getTransientErrorCodes() {
            return FinishUploadWorkItem.transientErrorCodes;
        }
        
        @Override
        protected void handleError(final FacebookException ex) {
            logError((Exception)ex, "Video '%s' failed to finish uploading", new Object[] { this.uploadContext.videoId });
            ((UploadWorkItemBase)this).endUploadWithFailure(ex);
        }
        
        @Override
        protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
            if (jsonObject.getBoolean("success")) {
                ((UploadWorkItemBase)this).issueResponseOnMainThread(null, this.uploadContext.videoId);
                return;
            }
            this.handleError(new FacebookException("Unexpected error in server response"));
        }
    }
    
    private static class StartUploadWorkItem extends UploadWorkItemBase
    {
        static final Set<Integer> transientErrorCodes;
        
        static {
            transientErrorCodes = new HashSet<Integer>() {
                {
                    this.add(6000);
                }
            };
        }
        
        public StartUploadWorkItem(final UploadContext uploadContext, final int n) {
            super(uploadContext, n);
        }
        
        @Override
        protected void enqueueRetry(final int n) {
            enqueueUploadStart(this.uploadContext, n);
        }
        
        public Bundle getParameters() {
            final Bundle bundle = new Bundle();
            bundle.putString("upload_phase", "start");
            bundle.putLong("file_size", this.uploadContext.videoSize);
            return bundle;
        }
        
        @Override
        protected Set<Integer> getTransientErrorCodes() {
            return StartUploadWorkItem.transientErrorCodes;
        }
        
        @Override
        protected void handleError(final FacebookException ex) {
            logError((Exception)ex, "Error starting video upload", new Object[0]);
            ((UploadWorkItemBase)this).endUploadWithFailure(ex);
        }
        
        @Override
        protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
            this.uploadContext.sessionId = jsonObject.getString("upload_session_id");
            this.uploadContext.videoId = jsonObject.getString("video_id");
            enqueueUploadChunk(this.uploadContext, jsonObject.getString("start_offset"), jsonObject.getString("end_offset"), 0);
        }
    }
    
    private static class TransferChunkWorkItem extends UploadWorkItemBase
    {
        static final Set<Integer> transientErrorCodes;
        private String chunkEnd;
        private String chunkStart;
        
        static {
            transientErrorCodes = new HashSet<Integer>() {
                {
                    this.add(1363019);
                    this.add(1363021);
                    this.add(1363030);
                    this.add(1363033);
                    this.add(1363041);
                }
            };
        }
        
        public TransferChunkWorkItem(final UploadContext uploadContext, final String chunkStart, final String chunkEnd, final int n) {
            super(uploadContext, n);
            this.chunkStart = chunkStart;
            this.chunkEnd = chunkEnd;
        }
        
        @Override
        protected void enqueueRetry(final int n) {
            enqueueUploadChunk(this.uploadContext, this.chunkStart, this.chunkEnd, n);
        }
        
        public Bundle getParameters() throws IOException {
            final Bundle bundle = new Bundle();
            bundle.putString("upload_phase", "transfer");
            bundle.putString("upload_session_id", this.uploadContext.sessionId);
            bundle.putString("start_offset", this.chunkStart);
            final byte[] access$600 = getChunk(this.uploadContext, this.chunkStart, this.chunkEnd);
            if (access$600 != null) {
                bundle.putByteArray("video_file_chunk", access$600);
                return bundle;
            }
            throw new FacebookException("Error reading video");
        }
        
        @Override
        protected Set<Integer> getTransientErrorCodes() {
            return TransferChunkWorkItem.transientErrorCodes;
        }
        
        @Override
        protected void handleError(final FacebookException ex) {
            logError((Exception)ex, "Error uploading video '%s'", new Object[] { this.uploadContext.videoId });
            ((UploadWorkItemBase)this).endUploadWithFailure(ex);
        }
        
        @Override
        protected void handleSuccess(final JSONObject jsonObject) throws JSONException {
            final String string = jsonObject.getString("start_offset");
            final String string2 = jsonObject.getString("end_offset");
            if (Utility.areObjectsEqual((Object)string, (Object)string2)) {
                enqueueUploadFinish(this.uploadContext, 0);
                return;
            }
            enqueueUploadChunk(this.uploadContext, string, string2, 0);
        }
    }
    
    private static class UploadContext
    {
        public final AccessToken accessToken;
        public final FacebookCallback<Sharer$Result> callback;
        public String chunkStart;
        public final String description;
        public final String graphNode;
        public boolean isCanceled;
        public Bundle params;
        public final String ref;
        public String sessionId;
        public final String title;
        public String videoId;
        public long videoSize;
        public InputStream videoStream;
        public final Uri videoUri;
        public WorkQueue$WorkItem workItem;
        
        private UploadContext(final ShareVideoContent shareVideoContent, final String graphNode, final FacebookCallback<Sharer$Result> callback) {
            this.chunkStart = "0";
            this.accessToken = AccessToken.getCurrentAccessToken();
            this.videoUri = shareVideoContent.getVideo().getLocalUrl();
            this.title = shareVideoContent.getContentTitle();
            this.description = shareVideoContent.getContentDescription();
            this.ref = shareVideoContent.getRef();
            this.graphNode = graphNode;
            this.callback = callback;
            this.params = shareVideoContent.getVideo().getParameters();
            if (!Utility.isNullOrEmpty((Collection)shareVideoContent.getPeopleIds())) {
                this.params.putString("tags", TextUtils.join((CharSequence)", ", (Iterable)shareVideoContent.getPeopleIds()));
            }
            if (!Utility.isNullOrEmpty(shareVideoContent.getPlaceId())) {
                this.params.putString("place", shareVideoContent.getPlaceId());
            }
            if (!Utility.isNullOrEmpty(shareVideoContent.getRef())) {
                this.params.putString("ref", shareVideoContent.getRef());
            }
        }
        
        private void initialize() throws FileNotFoundException {
            try {
                if (Utility.isFileUri(this.videoUri)) {
                    final ParcelFileDescriptor open = ParcelFileDescriptor.open(new File(this.videoUri.getPath()), 268435456);
                    this.videoSize = open.getStatSize();
                    this.videoStream = (InputStream)new ParcelFileDescriptor$AutoCloseInputStream(open);
                    return;
                }
                if (Utility.isContentUri(this.videoUri)) {
                    this.videoSize = Utility.getContentSize(this.videoUri);
                    this.videoStream = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(this.videoUri);
                    return;
                }
            }
            catch (FileNotFoundException ex) {
                Utility.closeQuietly((Closeable)this.videoStream);
                throw ex;
            }
            throw new FacebookException("Uri must be a content:// or file:// uri");
        }
    }
    
    private abstract static class UploadWorkItemBase implements Runnable
    {
        protected int completedRetries;
        protected UploadContext uploadContext;
        
        protected UploadWorkItemBase(final UploadContext uploadContext, final int completedRetries) {
            this.uploadContext = uploadContext;
            this.completedRetries = completedRetries;
        }
        
        private boolean attemptRetry(int n) {
            if (this.completedRetries < 2 && this.getTransientErrorCodes().contains(n)) {
                n = (int)Math.pow(3.0, this.completedRetries);
                getHandler().postDelayed((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        UploadWorkItemBase.this.enqueueRetry(UploadWorkItemBase.this.completedRetries + 1);
                    }
                }, (long)(n * 5000));
                return true;
            }
            return false;
        }
        
        protected void endUploadWithFailure(final FacebookException ex) {
            this.issueResponseOnMainThread(ex, null);
        }
        
        protected abstract void enqueueRetry(final int p0);
        
        protected void executeGraphRequestSynchronously(final Bundle bundle) {
            final GraphResponse executeAndWait = new GraphRequest(this.uploadContext.accessToken, String.format(Locale.ROOT, "%s/videos", this.uploadContext.graphNode), bundle, HttpMethod.POST, (GraphRequest$Callback)null).executeAndWait();
            if (executeAndWait == null) {
                this.handleError(new FacebookException("Unexpected error in server response"));
                return;
            }
            final FacebookRequestError error = executeAndWait.getError();
            final JSONObject jsonObject = executeAndWait.getJSONObject();
            if (error != null) {
                if (!this.attemptRetry(error.getSubErrorCode())) {
                    this.handleError((FacebookException)new FacebookGraphResponseException(executeAndWait, "Video upload failed"));
                }
                return;
            }
            if (jsonObject != null) {
                try {
                    this.handleSuccess(jsonObject);
                    return;
                }
                catch (JSONException ex) {
                    this.endUploadWithFailure(new FacebookException("Unexpected error in server response", (Throwable)ex));
                    return;
                }
            }
            this.handleError(new FacebookException("Unexpected error in server response"));
        }
        
        protected abstract Bundle getParameters() throws Exception;
        
        protected abstract Set<Integer> getTransientErrorCodes();
        
        protected abstract void handleError(final FacebookException p0);
        
        protected abstract void handleSuccess(final JSONObject p0) throws JSONException;
        
        protected void issueResponseOnMainThread(final FacebookException ex, final String s) {
            getHandler().post((Runnable)new Runnable() {
                @Override
                public void run() {
                    issueResponse(UploadWorkItemBase.this.uploadContext, ex, s);
                }
            });
        }
        
        @Override
        public void run() {
            if (!this.uploadContext.isCanceled) {
                try {
                    this.executeGraphRequestSynchronously(this.getParameters());
                    return;
                }
                catch (FacebookException ex) {
                    this.endUploadWithFailure(ex);
                    return;
                }
                catch (Exception ex2) {
                    this.endUploadWithFailure(new FacebookException("Video upload failed", (Throwable)ex2));
                    return;
                }
            }
            this.endUploadWithFailure(null);
        }
    }
}
