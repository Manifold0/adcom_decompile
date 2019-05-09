// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

import java.util.ArrayList;
import java.io.Serializable;

public class Feedback implements Serializable
{
    private static final long serialVersionUID = 2590172806951065320L;
    private String mCreatedAt;
    private String mEmail;
    private int mId;
    private ArrayList<FeedbackMessage> mMessages;
    private String mName;
    
    public String getCreatedAt() {
        return this.mCreatedAt;
    }
    
    public String getEmail() {
        return this.mEmail;
    }
    
    public int getId() {
        return this.mId;
    }
    
    public ArrayList<FeedbackMessage> getMessages() {
        return this.mMessages;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public void setCreatedAt(final String mCreatedAt) {
        this.mCreatedAt = mCreatedAt;
    }
    
    public void setEmail(final String mEmail) {
        this.mEmail = mEmail;
    }
    
    public void setId(final int mId) {
        this.mId = mId;
    }
    
    public void setMessages(final ArrayList<FeedbackMessage> mMessages) {
        this.mMessages = mMessages;
    }
    
    public void setName(final String mName) {
        this.mName = mName;
    }
}
