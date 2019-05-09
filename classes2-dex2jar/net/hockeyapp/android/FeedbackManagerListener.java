// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import net.hockeyapp.android.objects.FeedbackMessage;

public abstract class FeedbackManagerListener
{
    public abstract boolean feedbackAnswered(final FeedbackMessage p0);
    
    public Class<? extends FeedbackActivity> getFeedbackActivityClass() {
        return FeedbackActivity.class;
    }
    
    public boolean shouldCreateNewFeedbackThread() {
        return false;
    }
}
