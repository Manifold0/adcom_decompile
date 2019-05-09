package com.vungle.warren.network;

import android.content.Context;
import com.tonyodev.fetch.Fetch.Settings;
import java.util.Map.Entry;

public class APKDirectDownloader extends FetchDownloader {
    public APKDirectDownloader(Context context) {
        super(context);
        new Settings(context).setConcurrentDownloadsLimit(5).enableLogging(true).apply();
    }

    public void pause() {
        if (this.operations != null) {
            for (Entry entry : this.operations.entrySet()) {
                this.fetch.pause(((Long) entry.getKey()).longValue());
            }
        }
    }

    public void resume() {
        if (this.operations != null) {
            for (Entry entry : this.operations.entrySet()) {
                this.fetch.resume(((Long) entry.getKey()).longValue());
            }
        }
    }

    public boolean isDownloadTaskRunning() {
        return (this.operations == null || this.operations.isEmpty()) ? false : true;
    }
}
