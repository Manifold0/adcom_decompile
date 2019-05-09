// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.adapters;

import android.util.AttributeSet;
import net.hockeyapp.android.views.FeedbackMessageView;
import android.view.ViewGroup;
import android.view.View;
import net.hockeyapp.android.objects.FeedbackMessage;
import java.util.ArrayList;
import android.content.Context;
import android.widget.BaseAdapter;

public class MessagesAdapter extends BaseAdapter
{
    private Context mContext;
    private ArrayList<FeedbackMessage> mMessagesList;
    
    public MessagesAdapter(final Context mContext, final ArrayList<FeedbackMessage> mMessagesList) {
        this.mContext = mContext;
        this.mMessagesList = mMessagesList;
    }
    
    public void add(final FeedbackMessage feedbackMessage) {
        if (feedbackMessage != null && this.mMessagesList != null) {
            this.mMessagesList.add(feedbackMessage);
        }
    }
    
    public void clear() {
        if (this.mMessagesList != null) {
            this.mMessagesList.clear();
        }
    }
    
    public int getCount() {
        return this.mMessagesList.size();
    }
    
    public Object getItem(final int n) {
        return this.mMessagesList.get(n);
    }
    
    public long getItemId(final int n) {
        return n;
    }
    
    public View getView(final int index, final View view, final ViewGroup viewGroup) {
        final FeedbackMessage feedbackMessage = this.mMessagesList.get(index);
        FeedbackMessageView feedbackMessageView;
        if (view == null) {
            feedbackMessageView = new FeedbackMessageView(this.mContext, null);
        }
        else {
            feedbackMessageView = (FeedbackMessageView)view;
        }
        if (feedbackMessage != null) {
            feedbackMessageView.setFeedbackMessage(feedbackMessage);
        }
        feedbackMessageView.setIndex(index);
        return (View)feedbackMessageView;
    }
}
