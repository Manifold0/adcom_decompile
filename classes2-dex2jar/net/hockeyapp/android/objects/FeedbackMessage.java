// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.util.List;
import java.io.Serializable;

public class FeedbackMessage implements Serializable
{
    private static final long serialVersionUID = -8773015828853994624L;
    private String mAppId;
    private String mCleanText;
    private String mCreatedAt;
    private String mDeviceModel;
    private String mDeviceOem;
    private String mDeviceOsVersion;
    private List<FeedbackAttachment> mFeedbackAttachments;
    private int mId;
    private String mName;
    private String mSubject;
    private String mText;
    private String mToken;
    private String mUserString;
    private int mVia;
    
    public String getAppId() {
        return this.mAppId;
    }
    
    public String getCleanText() {
        return this.mCleanText;
    }
    
    public String getCreatedAt() {
        return this.mCreatedAt;
    }
    
    public List<FeedbackAttachment> getFeedbackAttachments() {
        return this.mFeedbackAttachments;
    }
    
    public int getId() {
        return this.mId;
    }
    
    public String getModel() {
        return this.mDeviceModel;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public String getOem() {
        return this.mDeviceOem;
    }
    
    public String getOsVersion() {
        return this.mDeviceOsVersion;
    }
    
    @Deprecated
    public String getSubjec() {
        return this.mSubject;
    }
    
    public String getSubject() {
        return this.mSubject;
    }
    
    public String getText() {
        return this.mText;
    }
    
    public String getToken() {
        return this.mToken;
    }
    
    public String getUserString() {
        return this.mUserString;
    }
    
    public int getVia() {
        return this.mVia;
    }
    
    public void setAppId(final String mAppId) {
        this.mAppId = mAppId;
    }
    
    public void setCleanText(final String mCleanText) {
        this.mCleanText = mCleanText;
    }
    
    public void setCreatedAt(final String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }
    
    public void setFeedbackAttachments(final List<FeedbackAttachment> mFeedbackAttachments) {
        this.mFeedbackAttachments = mFeedbackAttachments;
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public void setModel(final String mDeviceModel) {
        this.mDeviceModel = mDeviceModel;
    }
    
    public void setName(final String mName) {
        this.mName = mName;
    }
    
    public void setOem(final String mDeviceOem) {
        this.mDeviceOem = mDeviceOem;
    }
    
    public void setOsVersion(final String mDeviceOsVersion) {
        this.mDeviceOsVersion = mDeviceOsVersion;
    }
    
    @Deprecated
    public void setSubjec(final String mSubject) {
        this.mSubject = mSubject;
    }
    
    public void setSubject(final String mSubject) {
        this.mSubject = mSubject;
    }
    
    public void setText(final String mText) {
        this.mText = mText;
    }
    
    public void setToken(final String mToken) {
        this.mToken = mToken;
    }
    
    public void setUserString(final String mUserString) {
        this.mUserString = mUserString;
    }
    
    public void setVia(final int mVia) {
        this.mVia = mVia;
    }
}
