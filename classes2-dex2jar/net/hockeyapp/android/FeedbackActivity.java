// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.os.Message;
import java.lang.ref.WeakReference;
import android.app.AlertDialog;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import android.app.Dialog;
import android.view.ContextMenu$ContextMenuInfo;
import android.view.ContextMenu;
import android.app.NotificationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.content.ActivityNotFoundException;
import net.hockeyapp.android.utils.HockeyLog;
import android.os.Parcelable;
import android.view.View;
import net.hockeyapp.android.views.AttachmentView;
import android.view.ViewGroup;
import android.os.AsyncTask;
import net.hockeyapp.android.utils.AsyncTaskUtils;
import net.hockeyapp.android.views.AttachmentListView;
import net.hockeyapp.android.objects.FeedbackUserDataElement;
import android.text.TextUtils;
import android.widget.Toast;
import net.hockeyapp.android.utils.Util;
import android.annotation.SuppressLint;
import java.util.Iterator;
import java.text.ParseException;
import android.widget.ListAdapter;
import java.util.Collections;
import java.text.SimpleDateFormat;
import android.view.inputmethod.InputMethodManager;
import net.hockeyapp.android.utils.PrefsUtil;
import android.content.Intent;
import net.hockeyapp.android.objects.FeedbackResponse;
import android.widget.LinearLayout;
import net.hockeyapp.android.tasks.SendFeedbackTask;
import net.hockeyapp.android.tasks.ParseFeedbackTask;
import android.widget.ListView;
import net.hockeyapp.android.adapters.MessagesAdapter;
import android.widget.TextView;
import android.net.Uri;
import java.util.List;
import android.widget.ScrollView;
import net.hockeyapp.android.objects.FeedbackMessage;
import java.util.ArrayList;
import android.os.Handler;
import net.hockeyapp.android.objects.ErrorObject;
import android.widget.EditText;
import android.content.Context;
import android.widget.Button;
import android.view.View$OnClickListener;
import android.app.Activity;

public class FeedbackActivity extends Activity implements View$OnClickListener
{
    private static final int ATTACH_FILE = 2;
    private static final int ATTACH_PICTURE = 1;
    private static final int DIALOG_ERROR_ID = 0;
    public static final String EXTRA_FORCE_NEW_THREAD = "forceNewThread";
    public static final String EXTRA_INITIAL_ATTACHMENTS = "initialAttachments";
    public static final String EXTRA_INITIAL_USER_EMAIL = "initialUserEmail";
    public static final String EXTRA_INITIAL_USER_NAME = "initialUserName";
    public static final String EXTRA_URL = "url";
    private static final int MAX_ATTACHMENTS_PER_MSG = 3;
    private static final int PAINT_IMAGE = 3;
    private String initialUserEmail;
    private String initialUserName;
    private Button mAddAttachmentButton;
    private Button mAddResponseButton;
    private Context mContext;
    private EditText mEmailInput;
    private ErrorObject mError;
    private Handler mFeedbackHandler;
    private ArrayList<FeedbackMessage> mFeedbackMessages;
    private ScrollView mFeedbackScrollview;
    private boolean mFeedbackViewInitialized;
    private boolean mForceNewThread;
    private boolean mInSendFeedback;
    private List<Uri> mInitialAttachments;
    private TextView mLastUpdatedTextView;
    private MessagesAdapter mMessagesAdapter;
    private ListView mMessagesListView;
    private EditText mNameInput;
    private Handler mParseFeedbackHandler;
    private ParseFeedbackTask mParseFeedbackTask;
    private Button mRefreshButton;
    private Button mSendFeedbackButton;
    private SendFeedbackTask mSendFeedbackTask;
    private EditText mSubjectInput;
    private EditText mTextInput;
    private String mToken;
    private String mUrl;
    private LinearLayout mWrapperLayoutFeedbackAndMessages;
    
    private boolean addAttachment(final int n) {
        if (n == 2) {
            final Intent intent = new Intent();
            intent.setType("*/*");
            intent.setAction("android.intent.action.GET_CONTENT");
            this.startActivityForResult(Intent.createChooser(intent, (CharSequence)this.getString(R$string.hockeyapp_feedback_select_file)), 2);
            return true;
        }
        if (n == 1) {
            final Intent intent2 = new Intent();
            intent2.setType("image/*");
            intent2.setAction("android.intent.action.GET_CONTENT");
            this.startActivityForResult(Intent.createChooser(intent2, (CharSequence)this.getString(R$string.hockeyapp_feedback_select_picture)), 1);
            return true;
        }
        return false;
    }
    
    private void configureAppropriateView() {
        if (!this.mForceNewThread || this.mInSendFeedback) {
            this.mToken = PrefsUtil.getInstance().getFeedbackTokenFromPrefs((Context)this);
        }
        if (this.mToken == null || this.mInSendFeedback) {
            this.configureFeedbackView(false);
            return;
        }
        this.configureFeedbackView(true);
        this.sendFetchFeedback(this.mUrl, null, null, null, null, null, this.mToken, this.mFeedbackHandler, true);
    }
    
    private void createParseFeedbackTask(final String s, final String s2) {
        this.mParseFeedbackTask = new ParseFeedbackTask((Context)this, s, this.mParseFeedbackHandler, s2);
    }
    
    private void hideKeyboard() {
        if (this.mTextInput != null) {
            ((InputMethodManager)this.getSystemService("input_method")).hideSoftInputFromWindow(this.mTextInput.getWindowToken(), 0);
        }
    }
    
    private void initFeedbackHandler() {
        this.mFeedbackHandler = new FeedbackHandler(this);
    }
    
    private void initParseFeedbackHandler() {
        this.mParseFeedbackHandler = new ParseFeedbackHandler(this);
    }
    
    @SuppressLint({ "SimpleDateFormat" })
    private void loadFeedbackMessages(final FeedbackResponse feedbackResponse) {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                FeedbackActivity.this.configureFeedbackView(true);
                final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
                final SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("d MMM h:mm a");
                if (feedbackResponse == null || feedbackResponse.getFeedback() == null || feedbackResponse.getFeedback().getMessages() == null || feedbackResponse.getFeedback().getMessages().size() <= 0) {
                    return;
                }
            Label_0209_Outer:
                while (true) {
                    FeedbackActivity.this.mFeedbackMessages = feedbackResponse.getFeedback().getMessages();
                    Collections.reverse(FeedbackActivity.this.mFeedbackMessages);
                    while (true) {
                        while (true) {
                            try {
                                FeedbackActivity.this.mLastUpdatedTextView.setText((CharSequence)String.format(FeedbackActivity.this.getString(R$string.hockeyapp_feedback_last_updated_text), simpleDateFormat2.format(simpleDateFormat.parse(FeedbackActivity.this.mFeedbackMessages.get(0).getCreatedAt()))));
                                FeedbackActivity.this.mLastUpdatedTextView.setVisibility(0);
                                if (FeedbackActivity.this.mMessagesAdapter == null) {
                                    FeedbackActivity.this.mMessagesAdapter = new MessagesAdapter(FeedbackActivity.this.mContext, FeedbackActivity.this.mFeedbackMessages);
                                    FeedbackActivity.this.mMessagesListView.setAdapter((ListAdapter)FeedbackActivity.this.mMessagesAdapter);
                                    return;
                                }
                            }
                            catch (ParseException ex) {
                                ex.printStackTrace();
                                continue Label_0209_Outer;
                            }
                            break;
                        }
                        FeedbackActivity.this.mMessagesAdapter.clear();
                        final Iterator<FeedbackMessage> iterator = FeedbackActivity.this.mFeedbackMessages.iterator();
                        while (iterator.hasNext()) {
                            FeedbackActivity.this.mMessagesAdapter.add(iterator.next());
                        }
                        FeedbackActivity.this.mMessagesAdapter.notifyDataSetChanged();
                        continue;
                    }
                }
            }
        });
    }
    
    private void resetFeedbackView() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                PrefsUtil.getInstance().saveFeedbackTokenToPrefs((Context)FeedbackActivity.this, null);
                FeedbackActivity.this.getSharedPreferences("net.hockeyapp.android.feedback", 0).edit().remove("idLastMessageSend").remove("idLastMessageProcessed").apply();
                FeedbackActivity.this.configureFeedbackView(false);
            }
        });
    }
    
    private void sendFeedback() {
        if (!Util.isConnectedToNetwork((Context)this)) {
            Toast.makeText((Context)this, R$string.hockeyapp_error_no_network_message, 1).show();
            return;
        }
        this.enableDisableSendFeedbackButton(false);
        this.hideKeyboard();
        String feedbackTokenFromPrefs;
        if (this.mForceNewThread && !this.mInSendFeedback) {
            feedbackTokenFromPrefs = null;
        }
        else {
            feedbackTokenFromPrefs = PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.mContext);
        }
        final String trim = this.mNameInput.getText().toString().trim();
        final String trim2 = this.mEmailInput.getText().toString().trim();
        final String trim3 = this.mSubjectInput.getText().toString().trim();
        final String trim4 = this.mTextInput.getText().toString().trim();
        if (TextUtils.isEmpty((CharSequence)trim3)) {
            this.mSubjectInput.setVisibility(0);
            this.setError(this.mSubjectInput, R$string.hockeyapp_feedback_validate_subject_error);
            return;
        }
        if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty((CharSequence)trim)) {
            this.setError(this.mNameInput, R$string.hockeyapp_feedback_validate_name_error);
            return;
        }
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED && TextUtils.isEmpty((CharSequence)trim2)) {
            this.setError(this.mEmailInput, R$string.hockeyapp_feedback_validate_email_empty);
            return;
        }
        if (TextUtils.isEmpty((CharSequence)trim4)) {
            this.setError(this.mTextInput, R$string.hockeyapp_feedback_validate_text_error);
            return;
        }
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.REQUIRED && !Util.isValidEmail(trim2)) {
            this.setError(this.mEmailInput, R$string.hockeyapp_feedback_validate_email_error);
            return;
        }
        PrefsUtil.getInstance().saveNameEmailSubjectToPrefs(this.mContext, trim, trim2, trim3);
        this.sendFetchFeedback(this.mUrl, trim, trim2, trim3, trim4, ((AttachmentListView)this.findViewById(R$id.wrapper_attachments)).getAttachments(), feedbackTokenFromPrefs, this.mFeedbackHandler, false);
    }
    
    private void sendFetchFeedback(final String s, final String s2, final String s3, final String s4, final String s5, final List<Uri> list, final String s6, final Handler handler, final boolean b) {
        AsyncTaskUtils.execute(this.mSendFeedbackTask = new SendFeedbackTask(this.mContext, s, s2, s3, s4, s5, list, s6, handler, b));
    }
    
    private void setError(final EditText editText, final int n) {
        editText.setError((CharSequence)this.getString(n));
        this.enableDisableSendFeedbackButton(true);
    }
    
    private void startParseFeedbackTask(final String s, final String s2) {
        this.createParseFeedbackTask(s, s2);
        AsyncTaskUtils.execute(this.mParseFeedbackTask);
    }
    
    protected void configureFeedbackView(final boolean b) {
        this.mFeedbackScrollview = (ScrollView)this.findViewById(R$id.wrapper_feedback_scroll);
        this.mWrapperLayoutFeedbackAndMessages = (LinearLayout)this.findViewById(R$id.wrapper_messages);
        this.mMessagesListView = (ListView)this.findViewById(R$id.list_feedback_messages);
        if (b) {
            this.mWrapperLayoutFeedbackAndMessages.setVisibility(0);
            this.mFeedbackScrollview.setVisibility(8);
            (this.mLastUpdatedTextView = (TextView)this.findViewById(R$id.label_last_updated)).setVisibility(4);
            (this.mAddResponseButton = (Button)this.findViewById(R$id.button_add_response)).setOnClickListener((View$OnClickListener)this);
            (this.mRefreshButton = (Button)this.findViewById(R$id.button_refresh)).setOnClickListener((View$OnClickListener)this);
            return;
        }
        this.mWrapperLayoutFeedbackAndMessages.setVisibility(8);
        this.mFeedbackScrollview.setVisibility(0);
        this.mNameInput = (EditText)this.findViewById(R$id.input_name);
        this.mEmailInput = (EditText)this.findViewById(R$id.input_email);
        this.mSubjectInput = (EditText)this.findViewById(R$id.input_subject);
        this.mTextInput = (EditText)this.findViewById(R$id.input_message);
        if (!this.mFeedbackViewInitialized) {
            final String nameEmailFromPrefs = PrefsUtil.getInstance().getNameEmailFromPrefs(this.mContext);
            if (nameEmailFromPrefs != null) {
                final String[] split = nameEmailFromPrefs.split("\\|");
                if (split != null && split.length >= 2) {
                    this.mNameInput.setText((CharSequence)split[0]);
                    this.mEmailInput.setText((CharSequence)split[1]);
                    if (!this.mForceNewThread && split.length >= 3) {
                        this.mSubjectInput.setText((CharSequence)split[2]);
                        this.mTextInput.requestFocus();
                    }
                    else {
                        this.mSubjectInput.requestFocus();
                    }
                }
            }
            else {
                this.mNameInput.setText((CharSequence)this.initialUserName);
                this.mEmailInput.setText((CharSequence)this.initialUserEmail);
                this.mSubjectInput.setText((CharSequence)"");
                if (TextUtils.isEmpty((CharSequence)this.initialUserName)) {
                    this.mNameInput.requestFocus();
                }
                else if (TextUtils.isEmpty((CharSequence)this.initialUserEmail)) {
                    this.mEmailInput.requestFocus();
                }
                else {
                    this.mSubjectInput.requestFocus();
                }
            }
            this.mFeedbackViewInitialized = true;
        }
        final EditText mNameInput = this.mNameInput;
        int visibility;
        if (FeedbackManager.getRequireUserName() == FeedbackUserDataElement.DONT_SHOW) {
            visibility = 8;
        }
        else {
            visibility = 0;
        }
        mNameInput.setVisibility(visibility);
        final EditText mEmailInput = this.mEmailInput;
        int visibility2;
        if (FeedbackManager.getRequireUserEmail() == FeedbackUserDataElement.DONT_SHOW) {
            visibility2 = 8;
        }
        else {
            visibility2 = 0;
        }
        mEmailInput.setVisibility(visibility2);
        this.mTextInput.setText((CharSequence)"");
        if ((!this.mForceNewThread || this.mInSendFeedback) && PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.mContext) != null) {
            this.mSubjectInput.setVisibility(8);
        }
        else {
            this.mSubjectInput.setVisibility(0);
        }
        final ViewGroup viewGroup = (ViewGroup)this.findViewById(R$id.wrapper_attachments);
        viewGroup.removeAllViews();
        if (this.mInitialAttachments != null) {
            final Iterator<Uri> iterator = this.mInitialAttachments.iterator();
            while (iterator.hasNext()) {
                viewGroup.addView((View)new AttachmentView((Context)this, viewGroup, iterator.next(), true));
            }
        }
        (this.mAddAttachmentButton = (Button)this.findViewById(R$id.button_attachment)).setOnClickListener((View$OnClickListener)this);
        this.registerForContextMenu((View)this.mAddAttachmentButton);
        (this.mSendFeedbackButton = (Button)this.findViewById(R$id.button_send)).setOnClickListener((View$OnClickListener)this);
    }
    
    public void enableDisableSendFeedbackButton(final boolean enabled) {
        if (this.mSendFeedbackButton != null) {
            this.mSendFeedbackButton.setEnabled(enabled);
        }
    }
    
    @SuppressLint({ "InflateParams" })
    public View getLayoutView() {
        return this.getLayoutInflater().inflate(R$layout.hockeyapp_activity_feedback, (ViewGroup)null);
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n2 == -1) {
            if (n == 2) {
                final Uri data = intent.getData();
                if (data != null) {
                    final ViewGroup viewGroup = (ViewGroup)this.findViewById(R$id.wrapper_attachments);
                    viewGroup.addView((View)new AttachmentView((Context)this, viewGroup, data, true));
                }
            }
            else {
                if (n == 1) {
                    final Uri data2 = intent.getData();
                    if (data2 == null) {
                        return;
                    }
                    try {
                        final Intent intent2 = new Intent((Context)this, (Class)PaintActivity.class);
                        intent2.putExtra("imageUri", (Parcelable)data2);
                        this.startActivityForResult(intent2, 3);
                        return;
                    }
                    catch (ActivityNotFoundException ex) {
                        HockeyLog.error("HockeyApp", "Paint activity not declared!", (Throwable)ex);
                        return;
                    }
                }
                if (n == 3) {
                    final Uri uri = (Uri)intent.getParcelableExtra("imageUri");
                    if (uri != null) {
                        final ViewGroup viewGroup2 = (ViewGroup)this.findViewById(R$id.wrapper_attachments);
                        viewGroup2.addView((View)new AttachmentView((Context)this, viewGroup2, uri, true));
                    }
                }
            }
        }
    }
    
    public void onClick(final View view) {
        final int id = view.getId();
        if (id == R$id.button_send) {
            this.sendFeedback();
        }
        else if (id == R$id.button_attachment) {
            if (((ViewGroup)this.findViewById(R$id.wrapper_attachments)).getChildCount() >= 3) {
                Toast.makeText((Context)this, (CharSequence)String.valueOf(3), 0).show();
                return;
            }
            this.openContextMenu(view);
        }
        else {
            if (id == R$id.button_add_response) {
                this.mInSendFeedback = true;
                this.configureFeedbackView(false);
                return;
            }
            if (id == R$id.button_refresh) {
                this.sendFetchFeedback(this.mUrl, null, null, null, null, null, PrefsUtil.getInstance().getFeedbackTokenFromPrefs(this.mContext), this.mFeedbackHandler, true);
            }
        }
    }
    
    public boolean onContextItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onContextItemSelected(menuItem);
            }
            case 1:
            case 2: {
                return this.addAttachment(menuItem.getItemId());
            }
        }
    }
    
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.setContentView(this.getLayoutView());
        this.setTitle((CharSequence)this.getString(R$string.hockeyapp_feedback_title));
        this.mContext = (Context)this;
        final Bundle extras = this.getIntent().getExtras();
        if (extras != null) {
            this.mUrl = extras.getString("url");
            this.mForceNewThread = extras.getBoolean("forceNewThread");
            this.initialUserName = extras.getString("initialUserName");
            this.initialUserEmail = extras.getString("initialUserEmail");
            final Parcelable[] parcelableArray = extras.getParcelableArray("initialAttachments");
            if (parcelableArray != null) {
                this.mInitialAttachments = new ArrayList<Uri>();
                for (int length = parcelableArray.length, i = 0; i < length; ++i) {
                    this.mInitialAttachments.add((Uri)parcelableArray[i]);
                }
            }
        }
        if (bundle != null) {
            this.mFeedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
            this.mInSendFeedback = bundle.getBoolean("inSendFeedback");
        }
        else {
            this.mInSendFeedback = false;
            this.mFeedbackViewInitialized = false;
        }
        ((NotificationManager)this.getSystemService("notification")).cancel(2);
        this.initFeedbackHandler();
        this.initParseFeedbackHandler();
        this.configureAppropriateView();
    }
    
    public void onCreateContextMenu(final ContextMenu contextMenu, final View view, final ContextMenu$ContextMenuInfo contextMenu$ContextMenuInfo) {
        super.onCreateContextMenu(contextMenu, view, contextMenu$ContextMenuInfo);
        contextMenu.add(0, 2, 0, (CharSequence)this.getString(R$string.hockeyapp_feedback_attach_file));
        contextMenu.add(0, 1, 0, (CharSequence)this.getString(R$string.hockeyapp_feedback_attach_picture));
    }
    
    protected Dialog onCreateDialog(final int n) {
        switch (n) {
            default: {
                return null;
            }
            case 0: {
                return (Dialog)new AlertDialog$Builder((Context)this).setMessage((CharSequence)this.getString(R$string.hockeyapp_dialog_error_message)).setCancelable(false).setTitle((CharSequence)this.getString(R$string.hockeyapp_dialog_error_title)).setIcon(17301543).setPositiveButton((CharSequence)this.getString(R$string.hockeyapp_dialog_positive_button), (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                    public void onClick(final DialogInterface dialogInterface, final int n) {
                        FeedbackActivity.this.mError = null;
                        dialogInterface.cancel();
                    }
                }).create();
            }
        }
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            if (this.mInSendFeedback) {
                this.mInSendFeedback = false;
                this.configureAppropriateView();
            }
            else {
                this.finish();
            }
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    protected void onPrepareDialog(final int n, final Dialog dialog) {
        switch (n) {
            default: {}
            case 0: {
                final AlertDialog alertDialog = (AlertDialog)dialog;
                if (this.mError != null) {
                    alertDialog.setMessage((CharSequence)this.mError.getMessage());
                    return;
                }
                alertDialog.setMessage((CharSequence)this.getString(R$string.hockeyapp_feedback_generic_error));
            }
        }
    }
    
    protected void onRestoreInstanceState(final Bundle bundle) {
        if (bundle != null) {
            final ViewGroup viewGroup = (ViewGroup)this.findViewById(R$id.wrapper_attachments);
            for (final Uri uri : bundle.getParcelableArrayList("attachments")) {
                if (!this.mInitialAttachments.contains(uri)) {
                    viewGroup.addView((View)new AttachmentView((Context)this, viewGroup, uri, true));
                }
            }
            this.mFeedbackViewInitialized = bundle.getBoolean("feedbackViewInitialized");
        }
        super.onRestoreInstanceState(bundle);
    }
    
    public Object onRetainNonConfigurationInstance() {
        if (this.mSendFeedbackTask != null) {
            this.mSendFeedbackTask.detach();
        }
        return this.mSendFeedbackTask;
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        bundle.putParcelableArrayList("attachments", (ArrayList)((AttachmentListView)this.findViewById(R$id.wrapper_attachments)).getAttachments());
        bundle.putBoolean("feedbackViewInitialized", this.mFeedbackViewInitialized);
        bundle.putBoolean("inSendFeedback", this.mInSendFeedback);
        super.onSaveInstanceState(bundle);
    }
    
    protected void onSendFeedbackResult(final boolean b) {
    }
    
    protected void onStop() {
        super.onStop();
        if (this.mSendFeedbackTask != null) {
            this.mSendFeedbackTask.detach();
        }
    }
    
    private static class FeedbackHandler extends Handler
    {
        private final WeakReference<FeedbackActivity> mWeakFeedbackActivity;
        
        public FeedbackHandler(final FeedbackActivity feedbackActivity) {
            this.mWeakFeedbackActivity = new WeakReference<FeedbackActivity>(feedbackActivity);
        }
        
        public void handleMessage(final Message message) {
            boolean b = false;
            final ErrorObject errorObject = new ErrorObject();
            final FeedbackActivity feedbackActivity = this.mWeakFeedbackActivity.get();
            if (feedbackActivity == null) {
                return;
            }
            if (message != null && message.getData() != null) {
                final Bundle data = message.getData();
                final String string = data.getString("feedback_response");
                final String string2 = data.getString("feedback_status");
                final String string3 = data.getString("request_type");
                if ("send".equals(string3) && (string == null || Integer.parseInt(string2) != 201)) {
                    errorObject.setMessage(feedbackActivity.getString(R$string.hockeyapp_feedback_send_generic_error));
                }
                else if ("fetch".equals(string3) && string2 != null && (Integer.parseInt(string2) == 404 || Integer.parseInt(string2) == 422)) {
                    feedbackActivity.resetFeedbackView();
                    b = true;
                }
                else if (string != null) {
                    feedbackActivity.startParseFeedbackTask(string, string3);
                    b = true;
                }
                else {
                    errorObject.setMessage(feedbackActivity.getString(R$string.hockeyapp_feedback_send_network_error));
                }
            }
            else {
                errorObject.setMessage(feedbackActivity.getString(R$string.hockeyapp_feedback_send_generic_error));
            }
            feedbackActivity.mError = errorObject;
            if (!b) {
                feedbackActivity.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        feedbackActivity.enableDisableSendFeedbackButton(true);
                        feedbackActivity.showDialog(0);
                    }
                });
            }
            feedbackActivity.onSendFeedbackResult(b);
        }
    }
    
    private static class ParseFeedbackHandler extends Handler
    {
        private final WeakReference<FeedbackActivity> mWeakFeedbackActivity;
        
        public ParseFeedbackHandler(final FeedbackActivity feedbackActivity) {
            this.mWeakFeedbackActivity = new WeakReference<FeedbackActivity>(feedbackActivity);
        }
        
        public void handleMessage(final Message message) {
            final int n = 0;
            final FeedbackActivity feedbackActivity = this.mWeakFeedbackActivity.get();
            if (feedbackActivity == null) {
                return;
            }
            feedbackActivity.mError = new ErrorObject();
            int n2 = n;
            if (message != null) {
                n2 = n;
                if (message.getData() != null) {
                    final FeedbackResponse feedbackResponse = (FeedbackResponse)message.getData().getSerializable("parse_feedback_response");
                    n2 = n;
                    if (feedbackResponse != null) {
                        if (feedbackResponse.getStatus().equalsIgnoreCase("success")) {
                            n2 = 1;
                            if (feedbackResponse.getToken() != null) {
                                PrefsUtil.getInstance().saveFeedbackTokenToPrefs((Context)feedbackActivity, feedbackResponse.getToken());
                                feedbackActivity.loadFeedbackMessages(feedbackResponse);
                                feedbackActivity.mInSendFeedback = false;
                                n2 = n2;
                            }
                        }
                        else {
                            n2 = 0;
                        }
                    }
                }
            }
            if (n2 == 0) {
                feedbackActivity.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        feedbackActivity.showDialog(0);
                    }
                });
            }
            feedbackActivity.enableDisableSendFeedbackButton(true);
        }
    }
}
