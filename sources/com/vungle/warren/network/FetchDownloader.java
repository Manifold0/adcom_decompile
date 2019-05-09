package com.vungle.warren.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.util.Pair;
import com.tonyodev.fetch.Fetch;
import com.tonyodev.fetch.Fetch.Settings;
import com.tonyodev.fetch.listener.FetchListener;
import com.tonyodev.fetch.request.Request;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.network.Downloader.Listener;
import com.vungle.warren.utility.FileUtility;
import com.vungle.warren.utility.UnzipUtility;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

public class FetchDownloader implements Downloader, FetchListener {
    private static final String DOWNLOADS_FOLDER = "downloads_vungle";
    private static final String TAG = FetchDownloader.class.getSimpleName();
    private Context context;
    private int downloadCount = 0;
    protected Fetch fetch;
    protected HashMap<Long, Pair<Listener, File>> operations;

    public FetchDownloader(Context context) {
        this.fetch = Fetch.getInstance(context);
        this.fetch.addFetchListener(this);
        this.fetch.removeAll();
        this.operations = new HashMap();
        this.context = context;
        new Settings(context).enableLogging(true).apply();
    }

    public boolean download(@NonNull String url, @NonNull File destination, @NonNull Listener listener) throws IOException, IllegalArgumentException, IllegalStateException {
        if (destination.exists()) {
            try {
                if (!destination.delete()) {
                    throw new IOException("Failed to delete file at " + destination.getAbsolutePath());
                }
            } catch (SecurityException secErr) {
                throw new IOException(secErr);
            }
        }
        if (!this.fetch.isValid()) {
            if (this.context != null) {
                this.fetch = Fetch.getInstance(this.context);
                this.fetch.addFetchListener(this);
            } else {
                throw new IllegalStateException("Context is null, application is no longer running");
            }
        }
        String path = downloadFolder().getPath();
        StringBuilder append = new StringBuilder().append(destination.getName()).append(" (");
        int i = this.downloadCount;
        this.downloadCount = i + 1;
        this.operations.put(Long.valueOf(this.fetch.enqueue(new Request(url, path, append.append(i).append(")").toString()))), new Pair(listener, destination));
        return true;
    }

    private File downloadFolder() {
        return new File(this.context.getCacheDir().getPath() + File.separator + DOWNLOADS_FOLDER);
    }

    public void onUpdate(long id, int status, int progress, long downloadedBytes, long fileSize, int error) {
        Log.d("FetchDownloader", String.format(Locale.ENGLISH, "%s: %d%% completed, %d/%d , error: %d", new Object[]{Long.valueOf(id), Integer.valueOf(progress), Long.valueOf(downloadedBytes), Long.valueOf(fileSize), Integer.valueOf(error)}));
        if (this.operations.containsKey(Long.valueOf(id))) {
            Listener processor = ((Pair) this.operations.get(Long.valueOf(id))).first;
            File dest = ((Pair) this.operations.get(Long.valueOf(id))).second;
            if (processor != null && dest != null) {
                processor.onProgress(progress);
                if (error != -1) {
                    Log.e(TAG, "error: " + error);
                    processor.onError(new IOException("Error downloading !!!"));
                }
                if (progress == 100) {
                    File downloaded = this.fetch.getDownloadedFile(id);
                    if (downloaded == null) {
                        processor.onError(new IOException("Downloaded file not found!"));
                        return;
                    }
                    File parent = dest.getParentFile();
                    if (!parent.exists()) {
                        parent.mkdir();
                    }
                    if (downloaded.renameTo(dest)) {
                        if (dest.getName().equals(Advertisement.KEY_POSTROLL)) {
                            try {
                                UnzipUtility.unzip(dest.getPath(), new File(dest.getParent() + File.separator + Advertisement.POSTROLL_UNZIP).getPath());
                            } catch (IOException e) {
                                Log.e(TAG, "Error on unzipping assets", e);
                            }
                            try {
                                FileUtility.delete(dest);
                            } catch (IOException e2) {
                                Log.e(TAG, "Error on deleting zip assets archive", e2);
                            }
                        }
                        processor.onComplete(dest.getParentFile());
                        this.operations.remove(Long.valueOf(id));
                    } else {
                        processor.onError(new IOException("Error processing file to destination directory!"));
                    }
                    if (this.operations.isEmpty()) {
                        this.fetch.release();
                    }
                }
            }
        }
    }

    public void pause() {
    }

    public void resume() {
    }
}
