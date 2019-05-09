// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import com.facebook.GraphRequestAsyncTask;
import java.util.List;
import java.util.Collection;
import org.json.JSONArray;
import java.util.Arrays;
import java.util.Iterator;
import com.facebook.share.internal.ShareInternalUtility;
import org.json.JSONObject;
import com.facebook.FacebookGraphResponseException;
import com.facebook.GraphResponse;
import com.facebook.GraphRequest$Callback;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CountDownLatch;
import android.os.AsyncTask;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import com.facebook.FacebookServiceException;
import com.facebook.FacebookRequestError;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import com.facebook.FacebookDialogException;
import android.graphics.Bitmap;
import com.facebook.AccessToken;
import com.facebook.FacebookException;
import android.view.Display;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.net.Uri;
import android.os.AsyncTask$Status;
import android.view.KeyEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnCancelListener;
import com.facebook.common.R$string;
import com.facebook.FacebookOperationCanceledException;
import android.annotation.SuppressLint;
import android.view.MotionEvent;
import android.view.View$OnTouchListener;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import com.facebook.common.R$drawable;
import android.view.View;
import android.view.View$OnClickListener;
import java.util.Locale;
import com.facebook.FacebookSdk;
import android.os.Bundle;
import android.content.Context;
import com.facebook.common.R$style;
import android.view.WindowManager$LayoutParams;
import android.webkit.WebView;
import android.app.ProgressDialog;
import android.widget.ImageView;
import android.widget.FrameLayout;
import android.app.Dialog;

public class WebDialog extends Dialog
{
    private static final int API_EC_DIALOG_CANCEL = 4201;
    private static final int BACKGROUND_GRAY = -872415232;
    static final String CANCEL_URI = "fbconnect://cancel";
    private static final int DEFAULT_THEME;
    static final boolean DISABLE_SSL_CHECK_FOR_TESTING = false;
    private static final String DISPLAY_TOUCH = "touch";
    private static final String LOG_TAG = "FacebookSDK.WebDialog";
    private static final int MAX_PADDING_SCREEN_HEIGHT = 1280;
    private static final int MAX_PADDING_SCREEN_WIDTH = 800;
    private static final double MIN_SCALE_FACTOR = 0.5;
    private static final int NO_PADDING_SCREEN_HEIGHT = 800;
    private static final int NO_PADDING_SCREEN_WIDTH = 480;
    static final String REDIRECT_URI = "fbconnect://success";
    private static volatile int webDialogTheme;
    private FrameLayout contentFrameLayout;
    private ImageView crossImageView;
    private String expectedRedirectUrl;
    private boolean isDetached;
    private boolean isPageFinished;
    private boolean listenerCalled;
    private OnCompleteListener onCompleteListener;
    private ProgressDialog spinner;
    private UploadStagingResourcesTask uploadTask;
    private String url;
    private WebView webView;
    private WindowManager$LayoutParams windowParams;
    
    static {
        DEFAULT_THEME = R$style.com_facebook_activity_theme;
    }
    
    protected WebDialog(final Context context, final String s) {
        this(context, s, getWebDialogTheme());
    }
    
    private WebDialog(final Context context, final String url, final int n) {
        int webDialogTheme = n;
        if (n == 0) {
            webDialogTheme = getWebDialogTheme();
        }
        super(context, webDialogTheme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        this.url = url;
    }
    
    private WebDialog(final Context context, final String s, final Bundle bundle, final int n, final OnCompleteListener onCompleteListener) {
        int webDialogTheme = n;
        if (n == 0) {
            webDialogTheme = getWebDialogTheme();
        }
        super(context, webDialogTheme);
        this.expectedRedirectUrl = "fbconnect://success";
        this.listenerCalled = false;
        this.isDetached = false;
        this.isPageFinished = false;
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("redirect_uri", "fbconnect://success");
        bundle2.putString("display", "touch");
        bundle2.putString("client_id", FacebookSdk.getApplicationId());
        bundle2.putString("sdk", String.format(Locale.ROOT, "android-%s", FacebookSdk.getSdkVersion()));
        this.onCompleteListener = onCompleteListener;
        if (s.equals("share") && bundle2.containsKey("media")) {
            this.uploadTask = new UploadStagingResourcesTask(s, bundle2);
            return;
        }
        this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + "dialog/" + s, bundle2).toString();
    }
    
    private void createCrossImage() {
        (this.crossImageView = new ImageView(this.getContext())).setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                WebDialog.this.cancel();
            }
        });
        this.crossImageView.setImageDrawable(this.getContext().getResources().getDrawable(R$drawable.com_facebook_close));
        this.crossImageView.setVisibility(4);
    }
    
    private int getScaledSize(final int n, final float n2, final int n3, final int n4) {
        final int n5 = (int)(n / n2);
        double n6;
        if (n5 <= n3) {
            n6 = 1.0;
        }
        else if (n5 >= n4) {
            n6 = 0.5;
        }
        else {
            n6 = 0.5 + (n4 - n5) / (double)(n4 - n3) * 0.5;
        }
        return (int)(n * n6);
    }
    
    public static int getWebDialogTheme() {
        Validate.sdkInitialized();
        return WebDialog.webDialogTheme;
    }
    
    protected static void initDefaultTheme(final Context context) {
        if (context != null) {
            try {
                final ApplicationInfo applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 128);
                if (applicationInfo != null && applicationInfo.metaData != null && WebDialog.webDialogTheme == 0) {
                    setWebDialogTheme(applicationInfo.metaData.getInt("com.facebook.sdk.WebDialogTheme"));
                }
            }
            catch (PackageManager$NameNotFoundException ex) {}
        }
    }
    
    public static WebDialog newInstance(final Context context, final String s, final Bundle bundle, final int n, final OnCompleteListener onCompleteListener) {
        initDefaultTheme(context);
        return new WebDialog(context, s, bundle, n, onCompleteListener);
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    private void setUpWebView(final int n) {
        final LinearLayout linearLayout = new LinearLayout(this.getContext());
        (this.webView = new WebView(this.getContext()) {
            public void onWindowFocusChanged(final boolean b) {
                try {
                    super.onWindowFocusChanged(b);
                }
                catch (NullPointerException ex) {}
            }
        }).setVerticalScrollBarEnabled(false);
        this.webView.setHorizontalScrollBarEnabled(false);
        this.webView.setWebViewClient((WebViewClient)new DialogWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.loadUrl(this.url);
        this.webView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        this.webView.setVisibility(4);
        this.webView.getSettings().setSavePassword(false);
        this.webView.getSettings().setSaveFormData(false);
        this.webView.setFocusable(true);
        this.webView.setFocusableInTouchMode(true);
        this.webView.setOnTouchListener((View$OnTouchListener)new View$OnTouchListener() {
            public boolean onTouch(final View view, final MotionEvent motionEvent) {
                if (!view.hasFocus()) {
                    view.requestFocus();
                }
                return false;
            }
        });
        linearLayout.setPadding(n, n, n, n);
        linearLayout.addView((View)this.webView);
        linearLayout.setBackgroundColor(-872415232);
        this.contentFrameLayout.addView((View)linearLayout);
    }
    
    public static void setWebDialogTheme(int default_THEME) {
        if (default_THEME == 0) {
            default_THEME = WebDialog.DEFAULT_THEME;
        }
        WebDialog.webDialogTheme = default_THEME;
    }
    
    public void cancel() {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.sendErrorToListener((Throwable)new FacebookOperationCanceledException());
        }
    }
    
    public void dismiss() {
        if (this.webView != null) {
            this.webView.stopLoading();
        }
        if (!this.isDetached && this.spinner != null && this.spinner.isShowing()) {
            this.spinner.dismiss();
        }
        super.dismiss();
    }
    
    public OnCompleteListener getOnCompleteListener() {
        return this.onCompleteListener;
    }
    
    protected WebView getWebView() {
        return this.webView;
    }
    
    protected boolean isListenerCalled() {
        return this.listenerCalled;
    }
    
    protected boolean isPageFinished() {
        return this.isPageFinished;
    }
    
    public void onAttachedToWindow() {
        this.isDetached = false;
        if (Utility.mustFixWindowParamsForAutofill(this.getContext()) && this.windowParams != null && this.windowParams.token == null) {
            this.windowParams.token = this.getOwnerActivity().getWindow().getAttributes().token;
            Utility.logd("FacebookSDK.WebDialog", "Set token on onAttachedToWindow(): " + this.windowParams.token);
        }
        super.onAttachedToWindow();
    }
    
    protected void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        (this.spinner = new ProgressDialog(this.getContext())).requestWindowFeature(1);
        this.spinner.setMessage((CharSequence)this.getContext().getString(R$string.com_facebook_loading));
        this.spinner.setCanceledOnTouchOutside(false);
        this.spinner.setOnCancelListener((DialogInterface$OnCancelListener)new DialogInterface$OnCancelListener() {
            public void onCancel(final DialogInterface dialogInterface) {
                WebDialog.this.cancel();
            }
        });
        this.requestWindowFeature(1);
        this.contentFrameLayout = new FrameLayout(this.getContext());
        this.resize();
        this.getWindow().setGravity(17);
        this.getWindow().setSoftInputMode(16);
        this.createCrossImage();
        if (this.url != null) {
            this.setUpWebView(this.crossImageView.getDrawable().getIntrinsicWidth() / 2 + 1);
        }
        this.contentFrameLayout.addView((View)this.crossImageView, new ViewGroup$LayoutParams(-2, -2));
        this.setContentView((View)this.contentFrameLayout);
    }
    
    public void onDetachedFromWindow() {
        this.isDetached = true;
        super.onDetachedFromWindow();
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4) {
            this.cancel();
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    protected void onStart() {
        super.onStart();
        if (this.uploadTask != null && this.uploadTask.getStatus() == AsyncTask$Status.PENDING) {
            this.uploadTask.execute((Object[])new Void[0]);
            this.spinner.show();
            return;
        }
        this.resize();
    }
    
    protected void onStop() {
        if (this.uploadTask != null) {
            this.uploadTask.cancel(true);
            this.spinner.dismiss();
        }
        super.onStop();
    }
    
    public void onWindowAttributesChanged(final WindowManager$LayoutParams windowParams) {
        if (windowParams.token == null) {
            this.windowParams = windowParams;
        }
        super.onWindowAttributesChanged(windowParams);
    }
    
    protected Bundle parseResponseUri(final String s) {
        final Uri parse = Uri.parse(s);
        final Bundle urlQueryString = Utility.parseUrlQueryString(parse.getQuery());
        urlQueryString.putAll(Utility.parseUrlQueryString(parse.getFragment()));
        return urlQueryString;
    }
    
    public void resize() {
        final Display defaultDisplay = ((WindowManager)this.getContext().getSystemService("window")).getDefaultDisplay();
        final DisplayMetrics displayMetrics = new DisplayMetrics();
        defaultDisplay.getMetrics(displayMetrics);
        int n;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n = displayMetrics.widthPixels;
        }
        else {
            n = displayMetrics.heightPixels;
        }
        int n2;
        if (displayMetrics.widthPixels < displayMetrics.heightPixels) {
            n2 = displayMetrics.heightPixels;
        }
        else {
            n2 = displayMetrics.widthPixels;
        }
        this.getWindow().setLayout(Math.min(this.getScaledSize(n, displayMetrics.density, 480, 800), displayMetrics.widthPixels), Math.min(this.getScaledSize(n2, displayMetrics.density, 800, 1280), displayMetrics.heightPixels));
    }
    
    protected void sendErrorToListener(final Throwable t) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            FacebookException ex;
            if (t instanceof FacebookException) {
                ex = (FacebookException)t;
            }
            else {
                ex = new FacebookException(t);
            }
            this.onCompleteListener.onComplete(null, ex);
            this.dismiss();
        }
    }
    
    protected void sendSuccessToListener(final Bundle bundle) {
        if (this.onCompleteListener != null && !this.listenerCalled) {
            this.listenerCalled = true;
            this.onCompleteListener.onComplete(bundle, null);
            this.dismiss();
        }
    }
    
    protected void setExpectedRedirectUrl(final String expectedRedirectUrl) {
        this.expectedRedirectUrl = expectedRedirectUrl;
    }
    
    public void setOnCompleteListener(final OnCompleteListener onCompleteListener) {
        this.onCompleteListener = onCompleteListener;
    }
    
    public static class Builder
    {
        private AccessToken accessToken;
        private String action;
        private String applicationId;
        private Context context;
        private OnCompleteListener listener;
        private Bundle parameters;
        private int theme;
        
        public Builder(final Context context, final String s, final Bundle bundle) {
            if (!AccessToken.isCurrentAccessTokenActive()) {
                final String metadataApplicationId = Utility.getMetadataApplicationId(context);
                if (metadataApplicationId == null) {
                    throw new FacebookException("Attempted to create a builder without a valid access token or a valid default Application ID.");
                }
                this.applicationId = metadataApplicationId;
            }
            this.finishInit(context, s, bundle);
        }
        
        public Builder(final Context context, final String s, final String s2, final Bundle bundle) {
            String metadataApplicationId = s;
            if (s == null) {
                metadataApplicationId = Utility.getMetadataApplicationId(context);
            }
            Validate.notNullOrEmpty(metadataApplicationId, "applicationId");
            this.applicationId = metadataApplicationId;
            this.finishInit(context, s2, bundle);
        }
        
        private void finishInit(final Context context, final String action, final Bundle parameters) {
            this.context = context;
            this.action = action;
            if (parameters != null) {
                this.parameters = parameters;
                return;
            }
            this.parameters = new Bundle();
        }
        
        public WebDialog build() {
            if (this.accessToken != null) {
                this.parameters.putString("app_id", this.accessToken.getApplicationId());
                this.parameters.putString("access_token", this.accessToken.getToken());
            }
            else {
                this.parameters.putString("app_id", this.applicationId);
            }
            return WebDialog.newInstance(this.context, this.action, this.parameters, this.theme, this.listener);
        }
        
        public String getApplicationId() {
            return this.applicationId;
        }
        
        public Context getContext() {
            return this.context;
        }
        
        public OnCompleteListener getListener() {
            return this.listener;
        }
        
        public Bundle getParameters() {
            return this.parameters;
        }
        
        public int getTheme() {
            return this.theme;
        }
        
        public Builder setOnCompleteListener(final OnCompleteListener listener) {
            this.listener = listener;
            return this;
        }
        
        public Builder setTheme(final int theme) {
            this.theme = theme;
            return this;
        }
    }
    
    private class DialogWebViewClient extends WebViewClient
    {
        public void onPageFinished(final WebView webView, final String s) {
            super.onPageFinished(webView, s);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.dismiss();
            }
            WebDialog.this.contentFrameLayout.setBackgroundColor(0);
            WebDialog.this.webView.setVisibility(0);
            WebDialog.this.crossImageView.setVisibility(0);
            WebDialog.this.isPageFinished = true;
        }
        
        public void onPageStarted(final WebView webView, final String s, final Bitmap bitmap) {
            Utility.logd("FacebookSDK.WebDialog", "Webview loading URL: " + s);
            super.onPageStarted(webView, s, bitmap);
            if (!WebDialog.this.isDetached) {
                WebDialog.this.spinner.show();
            }
        }
        
        public void onReceivedError(final WebView webView, final int n, final String s, final String s2) {
            super.onReceivedError(webView, n, s, s2);
            WebDialog.this.sendErrorToListener((Throwable)new FacebookDialogException(s, n, s2));
        }
        
        public void onReceivedSslError(final WebView webView, final SslErrorHandler sslErrorHandler, final SslError sslError) {
            super.onReceivedSslError(webView, sslErrorHandler, sslError);
            sslErrorHandler.cancel();
            WebDialog.this.sendErrorToListener((Throwable)new FacebookDialogException(null, -11, null));
        }
        
        public boolean shouldOverrideUrlLoading(final WebView webView, String s) {
            Utility.logd("FacebookSDK.WebDialog", "Redirect URL: " + s);
            if (s.startsWith(WebDialog.this.expectedRedirectUrl)) {
                final Bundle responseUri = WebDialog.this.parseResponseUri(s);
                if ((s = responseUri.getString("error")) == null) {
                    s = responseUri.getString("error_type");
                }
                String s2;
                if ((s2 = responseUri.getString("error_msg")) == null) {
                    s2 = responseUri.getString("error_message");
                }
                String string;
                if ((string = s2) == null) {
                    string = responseUri.getString("error_description");
                }
                final String string2 = responseUri.getString("error_code");
                int int1 = -1;
            Label_0133:
                while (true) {
                    if (Utility.isNullOrEmpty(string2)) {
                        break Label_0133;
                    }
                    while (true) {
                        try {
                            int1 = Integer.parseInt(string2);
                            if (Utility.isNullOrEmpty(s) && Utility.isNullOrEmpty(string) && int1 == -1) {
                                WebDialog.this.sendSuccessToListener(responseUri);
                                return true;
                            }
                        }
                        catch (NumberFormatException ex) {
                            int1 = -1;
                            continue Label_0133;
                        }
                        if (s != null && (s.equals("access_denied") || s.equals("OAuthAccessDeniedException"))) {
                            WebDialog.this.cancel();
                            return true;
                        }
                        if (int1 == 4201) {
                            WebDialog.this.cancel();
                            return true;
                        }
                        WebDialog.this.sendErrorToListener((Throwable)new FacebookServiceException(new FacebookRequestError(int1, s, string), string));
                        return true;
                    }
                    break;
                }
            }
            else {
                if (s.startsWith("fbconnect://cancel")) {
                    WebDialog.this.cancel();
                    return true;
                }
                if (s.contains("touch")) {
                    return false;
                }
                try {
                    WebDialog.this.getContext().startActivity(new Intent("android.intent.action.VIEW", Uri.parse(s)));
                    return true;
                }
                catch (ActivityNotFoundException ex2) {
                    return false;
                }
            }
        }
    }
    
    public interface OnCompleteListener
    {
        void onComplete(final Bundle p0, final FacebookException p1);
    }
    
    private class UploadStagingResourcesTask extends AsyncTask<Void, Void, String[]>
    {
        private String action;
        private Exception[] exceptions;
        private Bundle parameters;
        
        UploadStagingResourcesTask(final String action, final Bundle parameters) {
            this.action = action;
            this.parameters = parameters;
        }
        
        protected String[] doInBackground(Void... array) {
            final String[] stringArray = this.parameters.getStringArray("media");
            final String[] array2 = new String[stringArray.length];
            this.exceptions = new Exception[stringArray.length];
            final CountDownLatch countDownLatch = new CountDownLatch(stringArray.length);
            array = (Void[])(Object)new ConcurrentLinkedQueue();
            final AccessToken currentAccessToken = AccessToken.getCurrentAccessToken();
            int n = 0;
            while (true) {
                Label_0194: {
                    Label_0130: {
                        try {
                            if (n >= stringArray.length) {
                                break Label_0194;
                            }
                            if (!this.isCancelled()) {
                                break Label_0130;
                            }
                            final Iterator<AsyncTask> iterator = ((ConcurrentLinkedQueue<AsyncTask>)(Object)array).iterator();
                            while (iterator.hasNext()) {
                                iterator.next().cancel(true);
                            }
                        }
                        catch (Exception ex) {
                            final Iterator<AsyncTask> iterator2 = ((ConcurrentLinkedQueue<AsyncTask>)(Object)array).iterator();
                            while (iterator2.hasNext()) {
                                iterator2.next().cancel(true);
                            }
                            return null;
                        }
                        return null;
                    }
                    final Uri parse = Uri.parse(stringArray[n]);
                    if (Utility.isWebUri(parse)) {
                        array2[n] = parse.toString();
                        countDownLatch.countDown();
                    }
                    else {
                        ((ConcurrentLinkedQueue<GraphRequestAsyncTask>)(Object)array).add(ShareInternalUtility.newUploadStagingResourceWithImageRequest(currentAccessToken, parse, (GraphRequest$Callback)new GraphRequest$Callback() {
                            public void onCompleted(final GraphResponse graphResponse) {
                                while (true) {
                                    Label_0055: {
                                        try {
                                            final FacebookRequestError error = graphResponse.getError();
                                            if (error != null) {
                                                String errorMessage;
                                                if ((errorMessage = error.getErrorMessage()) == null) {
                                                    errorMessage = "Error staging photo.";
                                                }
                                                throw new FacebookGraphResponseException(graphResponse, errorMessage);
                                            }
                                            break Label_0055;
                                        }
                                        catch (Exception ex) {
                                            UploadStagingResourcesTask.this.exceptions[n] = ex;
                                        }
                                        countDownLatch.countDown();
                                        return;
                                    }
                                    final JSONObject jsonObject = graphResponse.getJSONObject();
                                    if (jsonObject == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    final String optString = jsonObject.optString("uri");
                                    if (optString == null) {
                                        throw new FacebookException("Error staging photo.");
                                    }
                                    array2[n] = optString;
                                    continue;
                                }
                            }
                        }).executeAsync());
                    }
                    ++n;
                    continue;
                }
                countDownLatch.await();
                return array2;
            }
        }
        
        protected void onPostExecute(final String[] array) {
            WebDialog.this.spinner.dismiss();
            final Exception[] exceptions = this.exceptions;
            for (int length = exceptions.length, i = 0; i < length; ++i) {
                final Exception ex = exceptions[i];
                if (ex != null) {
                    WebDialog.this.sendErrorToListener(ex);
                    return;
                }
            }
            if (array == null) {
                WebDialog.this.sendErrorToListener((Throwable)new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            final List<String> list = Arrays.asList(array);
            if (list.contains(null)) {
                WebDialog.this.sendErrorToListener((Throwable)new FacebookException("Failed to stage photos for web dialog"));
                return;
            }
            Utility.putJSONValueInBundle(this.parameters, "media", (Object)new JSONArray((Collection)list));
            WebDialog.this.url = Utility.buildUri(ServerProtocol.getDialogAuthority(), FacebookSdk.getGraphApiVersion() + "/" + "dialog/" + this.action, this.parameters).toString();
            WebDialog.this.setUpWebView(WebDialog.this.crossImageView.getDrawable().getIntrinsicWidth() / 2 + 1);
        }
    }
}
