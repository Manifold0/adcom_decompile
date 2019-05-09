// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.data;

public class SSAFile extends SSAObj
{
    private String FILE;
    private String LAST_UPDATE_TIME;
    private String PATH;
    private String mErrMsg;
    private String mFile;
    private String mLastUpdateTime;
    private String mPath;
    
    public SSAFile(final String s) {
        super(s);
        this.FILE = "file";
        this.PATH = "path";
        this.LAST_UPDATE_TIME = "lastUpdateTime";
        if (this.containsKey(this.FILE)) {
            this.setFile(this.getString(this.FILE));
        }
        if (this.containsKey(this.PATH)) {
            this.setPath(this.getString(this.PATH));
        }
        if (this.containsKey(this.LAST_UPDATE_TIME)) {
            this.setLastUpdateTime(this.getString(this.LAST_UPDATE_TIME));
        }
    }
    
    public SSAFile(final String file, final String path) {
        this.FILE = "file";
        this.PATH = "path";
        this.LAST_UPDATE_TIME = "lastUpdateTime";
        this.setFile(file);
        this.setPath(path);
    }
    
    private void setFile(final String mFile) {
        this.mFile = mFile;
    }
    
    private void setPath(final String mPath) {
        this.mPath = mPath;
    }
    
    public String getErrMsg() {
        return this.mErrMsg;
    }
    
    public String getFile() {
        return this.mFile;
    }
    
    public String getLastUpdateTime() {
        return this.mLastUpdateTime;
    }
    
    public String getPath() {
        return this.mPath;
    }
    
    public void setErrMsg(final String mErrMsg) {
        this.mErrMsg = mErrMsg;
    }
    
    public void setLastUpdateTime(final String mLastUpdateTime) {
        this.mLastUpdateTime = mLastUpdateTime;
    }
}
