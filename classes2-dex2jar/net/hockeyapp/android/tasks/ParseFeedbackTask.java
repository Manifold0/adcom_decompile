// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import java.io.Serializable;
import android.os.Bundle;
import android.os.Message;
import net.hockeyapp.android.utils.FeedbackParser;
import android.app.Notification;
import net.hockeyapp.android.utils.Util;
import android.app.PendingIntent;
import android.content.Intent;
import net.hockeyapp.android.FeedbackActivity;
import android.app.NotificationManager;
import net.hockeyapp.android.FeedbackManagerListener;
import android.content.SharedPreferences;
import net.hockeyapp.android.FeedbackManager;
import net.hockeyapp.android.objects.FeedbackMessage;
import java.util.ArrayList;
import android.os.Handler;
import android.content.Context;
import net.hockeyapp.android.objects.FeedbackResponse;
import android.os.AsyncTask;

public class ParseFeedbackTask extends AsyncTask<Void, Void, FeedbackResponse>
{
    public static final String BUNDLE_PARSE_FEEDBACK_RESPONSE = "parse_feedback_response";
    public static final String ID_LAST_MESSAGE_PROCESSED = "idLastMessageProcessed";
    public static final String ID_LAST_MESSAGE_SEND = "idLastMessageSend";
    public static final int NEW_ANSWER_NOTIFICATION_ID = 2;
    public static final String PREFERENCES_NAME = "net.hockeyapp.android.feedback";
    private Context mContext;
    private String mFeedbackResponse;
    private Handler mHandler;
    private String mRequestType;
    private String mUrlString;
    
    public ParseFeedbackTask(final Context mContext, final String mFeedbackResponse, final Handler mHandler, final String mRequestType) {
        this.mContext = mContext;
        this.mFeedbackResponse = mFeedbackResponse;
        this.mHandler = mHandler;
        this.mRequestType = mRequestType;
        this.mUrlString = null;
    }
    
    private void checkForNewAnswers(final ArrayList<FeedbackMessage> list) {
        final FeedbackMessage feedbackMessage = list.get(list.size() - 1);
        final int id = feedbackMessage.getId();
        final SharedPreferences sharedPreferences = this.mContext.getSharedPreferences("net.hockeyapp.android.feedback", 0);
        if (this.mRequestType.equals("send")) {
            sharedPreferences.edit().putInt("idLastMessageSend", id).putInt("idLastMessageProcessed", id).apply();
        }
        else if (this.mRequestType.equals("fetch")) {
            final int int1 = sharedPreferences.getInt("idLastMessageSend", -1);
            final int int2 = sharedPreferences.getInt("idLastMessageProcessed", -1);
            if (id != int1 && id != int2) {
                sharedPreferences.edit().putInt("idLastMessageProcessed", id).apply();
                boolean feedbackAnswered = false;
                final FeedbackManagerListener lastListener = FeedbackManager.getLastListener();
                if (lastListener != null) {
                    feedbackAnswered = lastListener.feedbackAnswered(feedbackMessage);
                }
                if (!feedbackAnswered) {
                    this.startNotification(this.mContext);
                }
            }
        }
    }
    
    private void startNotification(final Context context) {
        if (this.mUrlString != null) {
            final NotificationManager notificationManager = (NotificationManager)context.getSystemService("notification");
            final int identifier = context.getResources().getIdentifier("ic_menu_refresh", "drawable", "android");
            Class<? extends FeedbackActivity> feedbackActivityClass = null;
            if (FeedbackManager.getLastListener() != null) {
                feedbackActivityClass = FeedbackManager.getLastListener().getFeedbackActivityClass();
            }
            Class<? extends FeedbackActivity> clazz;
            if ((clazz = feedbackActivityClass) == null) {
                clazz = FeedbackActivity.class;
            }
            final Intent intent = new Intent();
            intent.setFlags(805306368);
            intent.setClass(context, (Class)clazz);
            intent.putExtra("url", this.mUrlString);
            final Notification notification = Util.createNotification(context, PendingIntent.getActivity(context, 0, intent, 1073741824), "HockeyApp Feedback", "A new answer to your feedback is available.", identifier);
            if (notification != null) {
                notificationManager.notify(2, notification);
            }
        }
    }
    
    protected FeedbackResponse doInBackground(final Void... array) {
        if (this.mContext != null && this.mFeedbackResponse != null) {
            final FeedbackResponse feedbackResponse = FeedbackParser.getInstance().parseFeedbackResponse(this.mFeedbackResponse);
            if (feedbackResponse != null && feedbackResponse.getFeedback() != null) {
                final ArrayList<FeedbackMessage> messages = feedbackResponse.getFeedback().getMessages();
                if (messages != null && !messages.isEmpty()) {
                    this.checkForNewAnswers(messages);
                }
            }
            return feedbackResponse;
        }
        return null;
    }
    
    protected void onPostExecute(final FeedbackResponse feedbackResponse) {
        if (feedbackResponse != null && this.mHandler != null) {
            final Message message = new Message();
            final Bundle data = new Bundle();
            data.putSerializable("parse_feedback_response", (Serializable)feedbackResponse);
            message.setData(data);
            this.mHandler.sendMessage(message);
        }
    }
    
    public void setUrlString(final String mUrlString) {
        this.mUrlString = mUrlString;
    }
}
