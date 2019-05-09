// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.io.File;
import java.io.FilenameFilter;
import net.hockeyapp.android.Constants;
import java.io.Serializable;

public class FeedbackAttachment implements Serializable
{
    private static final long serialVersionUID = 5059651319640956830L;
    private String mCreatedAt;
    private String mFilename;
    private int mId;
    private int mMessageId;
    private String mUpdatedAt;
    private String mUrl;
    
    public String getCacheId() {
        return "" + this.mMessageId + this.mId;
    }
    
    public String getCreatedAt() {
        return this.mCreatedAt;
    }
    
    public String getFilename() {
        return this.mFilename;
    }
    
    public int getId() {
        return this.mId;
    }
    
    public int getMessageId() {
        return this.mMessageId;
    }
    
    public String getUpdatedAt() {
        return this.mUpdatedAt;
    }
    
    public String getUrl() {
        return this.mUrl;
    }
    
    public boolean isAvailableInCache() {
        final File hockeyAppStorageDir = Constants.getHockeyAppStorageDir();
        if (hockeyAppStorageDir.exists() && hockeyAppStorageDir.isDirectory()) {
            final File[] listFiles = hockeyAppStorageDir.listFiles(new FilenameFilter() {
                @Override
                public boolean accept(final File file, final String s) {
                    return s.equals(FeedbackAttachment.this.getCacheId());
                }
            });
            return listFiles != null && listFiles.length == 1;
        }
        return false;
    }
    
    public void setCreatedAt(final String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }
    
    public void setFilename(final String mFilename) {
        this.mFilename = mFilename;
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public void setMessageId(final int mMessageId) {
        this.mMessageId = mMessageId;
    }
    
    public void setUpdatedAt(final String mUpdatedAt) {
        this.mUpdatedAt = mUpdatedAt;
    }
    
    public void setUrl(final String mUrl) {
        this.mUrl = mUrl;
    }
    
    @Override
    public String toString() {
        return "\n" + FeedbackAttachment.class.getSimpleName() + "\nid         " + this.mId + "\nmessage id " + this.mMessageId + "\nfilename   " + this.mFilename + "\nurl        " + this.mUrl + "\ncreatedAt  " + this.mCreatedAt + "\nupdatedAt  " + this.mUpdatedAt;
    }
}
