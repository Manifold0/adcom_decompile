// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.tasks;

import net.hockeyapp.android.R$string;
import android.os.Bundle;
import android.os.Message;
import java.util.Map;
import java.net.HttpURLConnection;
import java.io.IOException;
import net.hockeyapp.android.utils.HttpURLConnectionBuilder;
import net.hockeyapp.android.utils.Util;
import net.hockeyapp.android.utils.HockeyLog;
import java.io.File;
import net.hockeyapp.android.Constants;
import android.app.ProgressDialog;
import android.os.Handler;
import android.content.Context;
import android.net.Uri;
import java.util.List;
import java.util.HashMap;

public class SendFeedbackTask extends ConnectionTask<Void, Void, HashMap<String, String>>
{
    public static final String BUNDLE_FEEDBACK_RESPONSE = "feedback_response";
    public static final String BUNDLE_FEEDBACK_STATUS = "feedback_status";
    public static final String BUNDLE_REQUEST_TYPE = "request_type";
    private static final String FILE_TAG = "HockeyApp";
    private static final String TAG = "SendFeedbackTask";
    private List<Uri> mAttachmentUris;
    private Context mContext;
    private String mEmail;
    private Handler mHandler;
    private boolean mIsFetchMessages;
    private int mLastMessageId;
    private String mName;
    private ProgressDialog mProgressDialog;
    private boolean mShowProgressDialog;
    private String mSubject;
    private String mText;
    private String mToken;
    private String mUrlString;
    
    public SendFeedbackTask(final Context mContext, final String mUrlString, final String mName, final String mEmail, final String mSubject, final String mText, final List<Uri> mAttachmentUris, final String mToken, final Handler mHandler, final boolean mIsFetchMessages) {
        this.mContext = mContext;
        this.mUrlString = mUrlString;
        this.mName = mName;
        this.mEmail = mEmail;
        this.mSubject = mSubject;
        this.mText = mText;
        this.mAttachmentUris = mAttachmentUris;
        this.mToken = mToken;
        this.mHandler = mHandler;
        this.mIsFetchMessages = mIsFetchMessages;
        this.mShowProgressDialog = true;
        this.mLastMessageId = -1;
        if (mContext != null) {
            Constants.loadFromContext(mContext);
        }
    }
    
    private void clearTemporaryFolder(final HashMap<String, String> hashMap) {
        final String s = hashMap.get("status");
        if (s != null && s.startsWith("2") && this.mContext != null) {
            final File file = new File(this.mContext.getCacheDir(), "HockeyApp");
            if (file != null && file.exists()) {
                final File[] listFiles = file.listFiles();
                for (int length = listFiles.length, i = 0; i < length; ++i) {
                    final File file2 = listFiles[i];
                    if (file2 != null && !(boolean)file2.delete()) {
                        HockeyLog.debug("SendFeedbackTask", "Error deleting file from temporary folder");
                    }
                }
            }
        }
    }
    
    private HashMap<String, String> doGet() {
        final StringBuilder sb = new StringBuilder();
        sb.append(this.mUrlString + Util.encodeParam(this.mToken));
        if (this.mLastMessageId != -1) {
            sb.append("?last_message_id=" + this.mLastMessageId);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        HttpURLConnection httpURLConnection = null;
        HttpURLConnection build = null;
        try {
            final HttpURLConnection httpURLConnection2 = httpURLConnection = (build = new HttpURLConnectionBuilder(sb.toString()).build());
            hashMap.put("type", "fetch");
            build = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            httpURLConnection2.connect();
            build = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            hashMap.put("status", String.valueOf(httpURLConnection2.getResponseCode()));
            build = httpURLConnection2;
            httpURLConnection = httpURLConnection2;
            hashMap.put("response", ConnectionTask.getStringFromConnection(httpURLConnection2));
            return hashMap;
        }
        catch (IOException ex) {
            httpURLConnection = build;
            ex.printStackTrace();
            return hashMap;
        }
        finally {
            if (httpURLConnection != null) {
                httpURLConnection.disconnect();
            }
        }
    }
    
    private HashMap<String, String> doPostPut() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("type", "send");
        final HttpURLConnection httpURLConnection = null;
        HttpURLConnection build;
        final HttpURLConnection httpURLConnection2 = build = null;
        HttpURLConnection httpURLConnection3 = httpURLConnection;
        try {
            final HashMap<String, String> hashMap2 = new HashMap<String, String>();
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("name", this.mName);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("email", this.mEmail);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("subject", this.mSubject);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("text", this.mText);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_identifier", Constants.APP_PACKAGE);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_short_version", Constants.APP_VERSION_NAME);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_version", Constants.APP_VERSION);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("os_version", Constants.ANDROID_VERSION);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("oem", Constants.PHONE_MANUFACTURER);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("model", Constants.PHONE_MODEL);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("sdk_version", "4.1.2");
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            if (this.mToken != null) {
                build = httpURLConnection2;
                httpURLConnection3 = httpURLConnection;
                this.mUrlString = this.mUrlString + this.mToken + "/";
            }
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            final HttpURLConnectionBuilder httpURLConnectionBuilder = new HttpURLConnectionBuilder(this.mUrlString);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            String requestMethod;
            if (this.mToken != null) {
                requestMethod = "PUT";
            }
            else {
                requestMethod = "POST";
            }
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            final HttpURLConnection httpURLConnection4 = httpURLConnection3 = (build = httpURLConnectionBuilder.setRequestMethod(requestMethod).writeFormFields(hashMap2).build());
            httpURLConnection4.connect();
            build = httpURLConnection4;
            httpURLConnection3 = httpURLConnection4;
            hashMap.put("status", String.valueOf(httpURLConnection4.getResponseCode()));
            build = httpURLConnection4;
            httpURLConnection3 = httpURLConnection4;
            hashMap.put("response", ConnectionTask.getStringFromConnection(httpURLConnection4));
            return hashMap;
        }
        catch (IOException ex) {
            httpURLConnection3 = build;
            ex.printStackTrace();
            return hashMap;
        }
        finally {
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
        }
    }
    
    private HashMap<String, String> doPostPutWithAttachments() {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("type", "send");
        final HttpURLConnection httpURLConnection = null;
        HttpURLConnection build;
        final HttpURLConnection httpURLConnection2 = build = null;
        HttpURLConnection httpURLConnection3 = httpURLConnection;
        try {
            final HashMap<String, String> hashMap2 = new HashMap<String, String>();
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("name", this.mName);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("email", this.mEmail);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("subject", this.mSubject);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("text", this.mText);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_identifier", Constants.APP_PACKAGE);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_short_version", Constants.APP_VERSION_NAME);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("bundle_version", Constants.APP_VERSION);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("os_version", Constants.ANDROID_VERSION);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("oem", Constants.PHONE_MANUFACTURER);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("model", Constants.PHONE_MODEL);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            hashMap2.put("sdk_version", "4.1.2");
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            if (this.mToken != null) {
                build = httpURLConnection2;
                httpURLConnection3 = httpURLConnection;
                this.mUrlString = this.mUrlString + this.mToken + "/";
            }
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            final HttpURLConnectionBuilder httpURLConnectionBuilder = new HttpURLConnectionBuilder(this.mUrlString);
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            String requestMethod;
            if (this.mToken != null) {
                requestMethod = "PUT";
            }
            else {
                requestMethod = "POST";
            }
            build = httpURLConnection2;
            httpURLConnection3 = httpURLConnection;
            final HttpURLConnection httpURLConnection4 = httpURLConnection3 = (build = httpURLConnectionBuilder.setRequestMethod(requestMethod).writeMultipartData(hashMap2, this.mContext, this.mAttachmentUris).build());
            httpURLConnection4.connect();
            build = httpURLConnection4;
            httpURLConnection3 = httpURLConnection4;
            hashMap.put("status", String.valueOf(httpURLConnection4.getResponseCode()));
            build = httpURLConnection4;
            httpURLConnection3 = httpURLConnection4;
            hashMap.put("response", ConnectionTask.getStringFromConnection(httpURLConnection4));
            return hashMap;
        }
        catch (IOException ex) {
            httpURLConnection3 = build;
            ex.printStackTrace();
            return hashMap;
        }
        finally {
            if (httpURLConnection3 != null) {
                httpURLConnection3.disconnect();
            }
        }
    }
    
    public void attach(final Context mContext) {
        this.mContext = mContext;
    }
    
    public void detach() {
        this.mContext = null;
        this.mProgressDialog = null;
    }
    
    protected HashMap<String, String> doInBackground(final Void... array) {
        HashMap<String, String> doGet;
        if (this.mIsFetchMessages && this.mToken != null) {
            doGet = this.doGet();
        }
        else {
            if (this.mIsFetchMessages) {
                return null;
            }
            if (this.mAttachmentUris.isEmpty()) {
                return this.doPostPut();
            }
            final HashMap<String, String> doPostPutWithAttachments = this.doPostPutWithAttachments();
            if ((doGet = doPostPutWithAttachments) != null) {
                this.clearTemporaryFolder(doPostPutWithAttachments);
                return doPostPutWithAttachments;
            }
        }
        return doGet;
    }
    
    protected void onPostExecute(final HashMap<String, String> hashMap) {
    Label_0014:
        while (true) {
            if (this.mProgressDialog == null) {
                break Label_0014;
            }
            while (true) {
                while (true) {
                    Bundle data = null;
                    Label_0109: {
                        try {
                            this.mProgressDialog.dismiss();
                            if (this.mHandler != null) {
                                final Message message = new Message();
                                data = new Bundle();
                                if (hashMap == null) {
                                    break Label_0109;
                                }
                                data.putString("request_type", (String)hashMap.get("type"));
                                data.putString("feedback_response", (String)hashMap.get("response"));
                                data.putString("feedback_status", (String)hashMap.get("status"));
                                message.setData(data);
                                this.mHandler.sendMessage(message);
                            }
                            return;
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                            continue Label_0014;
                        }
                    }
                    data.putString("request_type", "unknown");
                    continue;
                }
            }
            break;
        }
    }
    
    protected void onPreExecute() {
        String s = this.mContext.getString(R$string.hockeyapp_feedback_sending_feedback_text);
        if (this.mIsFetchMessages) {
            s = this.mContext.getString(R$string.hockeyapp_feedback_fetching_feedback_text);
        }
        if ((this.mProgressDialog == null || !this.mProgressDialog.isShowing()) && this.mShowProgressDialog) {
            this.mProgressDialog = ProgressDialog.show(this.mContext, (CharSequence)"", (CharSequence)s, true, false);
        }
    }
    
    public void setLastMessageId(final int mLastMessageId) {
        this.mLastMessageId = mLastMessageId;
    }
    
    public void setShowProgressDialog(final boolean mShowProgressDialog) {
        this.mShowProgressDialog = mShowProgressDialog;
    }
}
