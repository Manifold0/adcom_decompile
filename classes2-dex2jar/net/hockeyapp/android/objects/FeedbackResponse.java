// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.io.Serializable;

public class FeedbackResponse implements Serializable
{
    private static final long serialVersionUID = -1093570359639034766L;
    private Feedback mFeedback;
    private String mStatus;
    private String mToken;
    
    public Feedback getFeedback() {
        return this.mFeedback;
    }
    
    public String getStatus() {
        return this.mStatus;
    }
    
    public String getToken() {
        return this.mToken;
    }
    
    public void setFeedback(final Feedback mFeedback) {
        this.mFeedback = mFeedback;
    }
    
    public void setStatus(final String mStatus) {
        this.mStatus = mStatus;
    }
    
    public void setToken(final String mToken) {
        this.mToken = mToken;
    }
}
