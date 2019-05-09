// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import java.io.IOException;
import java.net.URL;
import net.hockeyapp.android.listeners.DownloadFileListener;
import android.content.Context;

public class GetFileSizeTask extends DownloadFileTask
{
    private long mSize;
    
    public GetFileSizeTask(final Context context, final String s, final DownloadFileListener downloadFileListener) {
        super(context, s, downloadFileListener);
    }
    
    @Override
    protected Long doInBackground(final Void... array) {
        try {
            return (long)this.createConnection(new URL(this.getURLString()), 6).getContentLength();
        }
        catch (IOException ex) {
            ex.printStackTrace();
            return 0L;
        }
    }
    
    public long getSize() {
        return this.mSize;
    }
    
    @Override
    protected void onPostExecute(final Long n) {
        this.mSize = n;
        if (this.mSize > 0L) {
            this.mNotifier.downloadSuccessful(this);
            return;
        }
        this.mNotifier.downloadFailed(this, false);
    }
    
    @Override
    protected void onProgressUpdate(final Integer... array) {
    }
}
