// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.views;

import net.hockeyapp.android.R$color;
import java.util.Iterator;
import java.text.ParseException;
import android.view.View;
import net.hockeyapp.android.tasks.AttachmentDownloader;
import net.hockeyapp.android.objects.FeedbackAttachment;
import net.hockeyapp.android.R$id;
import android.view.ViewGroup;
import net.hockeyapp.android.R$layout;
import android.view.LayoutInflater;
import android.util.AttributeSet;
import net.hockeyapp.android.objects.FeedbackMessage;
import android.content.Context;
import android.widget.TextView;
import android.annotation.SuppressLint;
import java.text.SimpleDateFormat;
import android.widget.LinearLayout;

public class FeedbackMessageView extends LinearLayout
{
    @SuppressLint({ "SimpleDateFormat" })
    private static final SimpleDateFormat DATE_FORMAT_IN;
    @SuppressLint({ "SimpleDateFormat" })
    private static final SimpleDateFormat DATE_FORMAT_OUT;
    private AttachmentListView mAttachmentListView;
    private TextView mAuthorTextView;
    private final Context mContext;
    private TextView mDateTextView;
    private FeedbackMessage mFeedbackMessage;
    private TextView mMessageTextView;
    @Deprecated
    private boolean ownMessage;
    
    static {
        DATE_FORMAT_IN = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        DATE_FORMAT_OUT = new SimpleDateFormat("d MMM h:mm a");
    }
    
    public FeedbackMessageView(final Context mContext, final AttributeSet set) {
        super(mContext, set);
        this.mContext = mContext;
        LayoutInflater.from(mContext).inflate(R$layout.hockeyapp_view_feedback_message, (ViewGroup)this);
        this.mAuthorTextView = (TextView)this.findViewById(R$id.label_author);
        this.mDateTextView = (TextView)this.findViewById(R$id.label_date);
        this.mMessageTextView = (TextView)this.findViewById(R$id.label_text);
        this.mAttachmentListView = (AttachmentListView)this.findViewById(R$id.list_attachments);
    }
    
    public void setFeedbackMessage(final FeedbackMessage mFeedbackMessage) {
        this.mFeedbackMessage = mFeedbackMessage;
        while (true) {
            try {
                this.mDateTextView.setText((CharSequence)FeedbackMessageView.DATE_FORMAT_OUT.format(FeedbackMessageView.DATE_FORMAT_IN.parse(this.mFeedbackMessage.getCreatedAt())));
                this.mAuthorTextView.setText((CharSequence)this.mFeedbackMessage.getName());
                this.mMessageTextView.setText((CharSequence)this.mFeedbackMessage.getText());
                this.mAttachmentListView.removeAllViews();
                for (final FeedbackAttachment feedbackAttachment : this.mFeedbackMessage.getFeedbackAttachments()) {
                    final AttachmentView attachmentView = new AttachmentView(this.mContext, this.mAttachmentListView, feedbackAttachment, false);
                    AttachmentDownloader.getInstance().download(feedbackAttachment, attachmentView);
                    this.mAttachmentListView.addView((View)attachmentView);
                }
            }
            catch (ParseException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    public void setIndex(final int n) {
        if (n % 2 == 0) {
            this.setBackgroundColor(this.getResources().getColor(R$color.hockeyapp_background_light));
            this.mAuthorTextView.setTextColor(this.getResources().getColor(R$color.hockeyapp_text_white));
            this.mDateTextView.setTextColor(this.getResources().getColor(R$color.hockeyapp_text_white));
        }
        else {
            this.setBackgroundColor(this.getResources().getColor(R$color.hockeyapp_background_white));
            this.mAuthorTextView.setTextColor(this.getResources().getColor(R$color.hockeyapp_text_light));
            this.mDateTextView.setTextColor(this.getResources().getColor(R$color.hockeyapp_text_light));
        }
        this.mMessageTextView.setTextColor(this.getResources().getColor(R$color.hockeyapp_text_black));
    }
}
