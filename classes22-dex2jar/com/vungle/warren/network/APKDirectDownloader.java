// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.network;

import java.util.Iterator;
import java.io.File;
import android.util.Pair;
import java.util.Map;
import com.tonyodev.fetch.Fetch$Settings;
import android.content.Context;

public class APKDirectDownloader extends FetchDownloader
{
    public APKDirectDownloader(final Context context) {
        super(context);
        new Fetch$Settings(context).setConcurrentDownloadsLimit(5).enableLogging(true).apply();
    }
    
    public boolean isDownloadTaskRunning() {
        return this.operations != null && !this.operations.isEmpty();
    }
    
    @Override
    public void pause() {
        if (this.operations != null) {
            final Iterator<Map.Entry<Long, Pair<Listener, File>>> iterator = this.operations.entrySet().iterator();
            while (iterator.hasNext()) {
                this.fetch.pause((long)iterator.next().getKey());
            }
        }
    }
    
    @Override
    public void resume() {
        if (this.operations != null) {
            final Iterator<Map.Entry<Long, Pair<Listener, File>>> iterator = this.operations.entrySet().iterator();
            while (iterator.hasNext()) {
                this.fetch.resume((long)iterator.next().getKey());
            }
        }
    }
}
