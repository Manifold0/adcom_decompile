// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.api.activities;

import android.content.IntentFilter;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.internal.sdk.o;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.android.internal.sdk.NativeAPI;
import android.os.Bundle;
import android.content.res.Configuration;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.text.Html;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.kongregate.android.internal.sdk.l;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.ImageView;
import com.kongregate.android.internal.util.g;
import org.json.JSONException;
import org.json.JSONObject;
import com.kongregate.android.internal.util.j;
import android.content.Intent;
import android.content.Context;
import com.kongregate.android.internal.browser.b;
import android.view.ViewGroup;
import com.kongregate.android.internal.browser.MobileApiWebView;
import android.content.BroadcastReceiver;
import com.kongregate.o.g.a;
import android.app.AlertDialog;
import java.util.HashMap;
import android.app.Activity;

public class KongregatePanelActivity extends Activity
{
    public static final String ALERT_TYPE_GDPR_ADULT = "gdpr_adult";
    public static final String ALERT_TYPE_GDPR_UNDERAGE = "gdpr_underage";
    public static final String INTENT_EXTRA_ALERT = "alert";
    public static final String INTENT_EXTRA_TARGET = "target";
    public static final String INTENT_EXTRA_TARGET_ID = "target_id";
    public static final HashMap<String, String[]> PANEL_TRANSITION_MAP;
    private AlertDialog mAlertDialog;
    private String mAlertDialogId;
    private a mConnectionManager;
    private boolean mHasHistory;
    private BroadcastReceiver mLocalBroadcastReceiver;
    private boolean mStarted;
    private String[] mTransitionOverride;
    private MobileApiWebView mWebView;
    private ViewGroup mWebViewHolder;
    private b mWebViewManager;
    
    static {
        (PANEL_TRANSITION_MAP = new HashMap<String, String[]>()).put("slideFromLeft", new String[] { "slide_in_left", "slide_out_left" });
        KongregatePanelActivity.PANEL_TRANSITION_MAP.put("slideFromRight", new String[] { "slide_in_right", "slide_out_right" });
    }
    
    public KongregatePanelActivity() {
        this.mHasHistory = false;
        this.mTransitionOverride = null;
        this.mAlertDialogId = null;
        this.mAlertDialog = null;
        this.mLocalBroadcastReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, final Intent intent) {
                if (KongregatePanelActivity.this.mWebView == null) {
                    j.c("Web View has already been destroyed. Ignoring: " + intent.getAction());
                }
                else {
                    Label_0083: {
                        if (!"com.kongregate.android.internal.sdk.ExpiredSesssion".equals(intent.getAction())) {
                            break Label_0083;
                        }
                        context = (Context)new JSONObject();
                        while (true) {
                            try {
                                ((JSONObject)context).put("username", (Object)intent.getStringExtra("username"));
                                KongregatePanelActivity.this.mWebView.a("invalidSession", (JSONObject)context);
                                return;
                                Label_0135: {
                                    KongregatePanelActivity.this.mWebView.m();
                                }
                                // iftrue(Label_0035:, !"com.kongregate.android.internal.sdk.LoadUser".equals((Object)intent.getAction()))
                                return;
                                Label_0115: {
                                    KongregatePanelActivity.this.finish();
                                }
                                // iftrue(Label_0135:, !"com.kongregate.android.internal.sdk.ClosePanel".equals((Object)intent.getAction()))
                                return;
                                // iftrue(Label_0115:, !"com.kongregate.android.internal.sdk.KongregateStatsSync".equals((Object)intent.getAction()))
                                KongregatePanelActivity.this.mWebView.a("KongEventStatsSync", new JSONObject());
                                return;
                            }
                            catch (JSONException ex) {
                                continue;
                            }
                            break;
                        }
                    }
                }
                Label_0035:;
            }
        };
    }
    
    private void checkToolbarAssetOverride(final String s, final String s2) {
        final int a = g.a(s2, "drawable", (Context)this);
        if (a != 0) {
            j.a("Found toolbar asset override: " + s + " : " + s2);
            final View viewById = this.findViewById(g.a(s, "id", (Context)this));
            if (viewById == null) {
                j.c("Unable to find custom drawable or view resource. Skipping custom background.");
                return;
            }
            if (!"kongregate_custom_toolbar_logo".equals(s2)) {
                viewById.setBackgroundResource(a);
                return;
            }
            ((ImageView)viewById).setImageResource(a);
        }
    }
    
    private void destroy() {
        if (this.mWebView != null && this.mWebViewHolder != null) {
            j.b("Destroying KongregatePanelActivity");
            this.mWebViewHolder.removeView((View)this.mWebView);
            this.mWebView.g();
            this.mWebView = null;
            this.mWebViewHolder = null;
            LocalBroadcastManager.getInstance((Context)this).sendBroadcast(l.a("KONG_API_EVENT_CLOSED_KONGREGATE"));
        }
        if (this.mAlertDialog != null && this.mAlertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
        }
    }
    
    private void dismissAlertDialog(final String s) {
        final Intent intent = new Intent("com.kongregate.android.internal.sdk.AlertDismissEvent");
        intent.putExtra("com.kongregate.android.internal.sdk.AlertDismissButton", s);
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(intent);
        this.finish();
    }
    
    private void resetAlertDialog() {
        if (this.mAlertDialog != null && this.mAlertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
        }
    }
    
    private void showGDPRAdultAlert() {
        this.resetAlertDialog();
        (this.mAlertDialog = new AlertDialog$Builder((Context)this).setTitle((CharSequence)"IMPORTANT INFORMATION").setCancelable(false).setPositiveButton((CharSequence)"ACCEPT", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                KongregatePanelActivity.this.dismissAlertDialog("gdpr_policy_accepted");
            }
        }).setMessage((CharSequence)Html.fromHtml("<html>By choosing \"<b>ACCEPT</b>,\" I acknowledge that I have read and agree to the <a href=\"https://www.kongregate.com/user-agreement\">User Agreement</a></html>, <a href=\"https://www.kongregate.com/privacy\">Privacy Policy</a> and <a href=\"https://www.kongregate.com/cookie-policy\">Cookie Policy.</html>")).create()).show();
        final View viewById = this.mAlertDialog.findViewById(16908299);
        if (viewById instanceof TextView) {
            ((TextView)viewById).setMovementMethod(LinkMovementMethod.getInstance());
            return;
        }
        j.c("ShowGDPRAlert: failed to find message view to make links clickable");
    }
    
    private void showGDPRUnderageAlert() {
        this.resetAlertDialog();
        (this.mAlertDialog = new AlertDialog$Builder((Context)this).setTitle((CharSequence)"IMPORTANT INFORMATION").setCancelable(false).setPositiveButton((CharSequence)"VIEW POLICY", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                KongregatePanelActivity.this.dismissAlertDialog("gdpr_view_policy");
            }
        }).setMessage((CharSequence)Html.fromHtml("Your parent or guardian must accept our privacy policy to continue.")).create()).show();
    }
    
    public void finish() {
        super.finish();
        if (this.mTransitionOverride != null) {
            this.overridePendingTransition(this.getResources().getIdentifier(this.mTransitionOverride[0], "anim", this.getPackageName()), this.getResources().getIdentifier(this.mTransitionOverride[1], "anim", this.getPackageName()));
        }
    }
    
    public void hideProgressSpinner() {
        final View viewById = this.findViewById(this.getResources().getIdentifier("kongregate_progress_spinner", "id", this.getPackageName()));
        if (viewById != null) {
            viewById.setVisibility(8);
            this.mWebViewHolder.setVisibility(0);
        }
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        j.a("onActivityResult(" + n + "," + n2 + "," + intent);
        this.mWebView.getFacebookHelper().a(this, n, n2, intent);
    }
    
    public void onBackPressed() {
        if (this.mHasHistory && this.mWebView.j()) {
            this.mWebView.a("back", new JSONObject());
            return;
        }
        super.onBackPressed();
    }
    
    public void onCloseButtonClicked(final View view) {
        this.finish();
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        j.a("Panel config changed: " + configuration);
    }
    
    protected void onCreate(final Bundle bundle) {
        if (!NativeAPI.g().c()) {
            j.c("Kong panel is not enabled, closing");
            this.finish();
        }
        else {
            j.a("Panel onCreate");
            super.onCreate(bundle);
            this.mAlertDialogId = this.getIntent().getStringExtra("alert");
            if (this.mAlertDialogId != null) {
                j.a("Show Alert Dialog: " + this.mAlertDialogId);
                return;
            }
            while (true) {
                this.requestWindowFeature(1);
                while (true) {
                    String stringExtra = null;
                    Label_0267: {
                        while (true) {
                            try {
                                stringExtra = this.getIntent().getStringExtra("orientationOverride");
                                if (StringUtils.c((CharSequence)stringExtra)) {
                                    this.setRequestedOrientation(4);
                                }
                                else {
                                    if (!"landscape".equals(stringExtra)) {
                                        break Label_0267;
                                    }
                                    this.setRequestedOrientation(0);
                                }
                                j.a("screen orientation option: " + stringExtra + " requested: " + this.getRequestedOrientation());
                                final String stringExtra2 = this.getIntent().getStringExtra("overrideTransition");
                                if (KongregatePanelActivity.PANEL_TRANSITION_MAP.containsKey(stringExtra2)) {
                                    this.mTransitionOverride = KongregatePanelActivity.PANEL_TRANSITION_MAP.get(stringExtra2);
                                }
                                this.setContentView(g.a("kongregate_browser", "layout", (Context)this));
                                this.getWindow().setLayout(-1, -1);
                                this.mStarted = false;
                                this.mWebView = (MobileApiWebView)this.findViewById(g.a("kongregate_webview", "id", (Context)this));
                                if (!this.verifyAPIReady()) {
                                    this.finish();
                                    return;
                                }
                                break;
                            }
                            catch (Exception ex) {
                                j.c("Failed to set request orientation: ", ex);
                                continue;
                            }
                            break;
                        }
                    }
                    if ("landscapeSensor".equals(stringExtra)) {
                        this.setRequestedOrientation(6);
                        continue;
                    }
                    if ("portrait".equals(stringExtra)) {
                        this.setRequestedOrientation(1);
                        continue;
                    }
                    if ("portraitSensor".equals(stringExtra)) {
                        this.setRequestedOrientation(7);
                        continue;
                    }
                    j.a("Unrecognized orientation option: " + stringExtra + ", using value from manifest");
                    continue;
                }
            }
            this.checkToolbarAssetOverride("kongregate_logo", "kongregate_custom_toolbar_logo");
            this.checkToolbarAssetOverride("kongregate_toolbar", "kongregate_custom_toolbar_background");
            this.mWebView = this.mWebViewManager.a(false);
            this.mWebViewHolder = (ViewGroup)this.findViewById(this.getResources().getIdentifier("kongregate_webview_holder", "id", this.getPackageName()));
            this.mWebView.a(this);
            this.mWebViewHolder.addView((View)this.mWebView);
            this.updateWebViewCookies();
            if (!this.getIntent().getBooleanExtra("showSystemUi", false)) {
                com.kongregate.android.internal.util.a.a(this, this.getIntent().getBooleanExtra("allowImmersiveMode", true));
            }
        }
    }
    
    protected void onDestroy() {
        j.a("Panel onDestroy");
        super.onDestroy();
        this.destroy();
    }
    
    public void onMenuButtonClicked(final View view) {
        this.mWebView.a("toggle_menu", new JSONObject());
    }
    
    protected void onPause() {
        j.a("Panel onPause, finishing: " + this.isFinishing());
        ((o)APIBootstrap.getInstance().analytics()).f();
        super.onPause();
        ((NativeAPI)APIBootstrap.getInstance()).b(this);
        LocalBroadcastManager.getInstance((Context)this).unregisterReceiver(this.mLocalBroadcastReceiver);
        if (this.isFinishing()) {
            this.destroy();
        }
    }
    
    public void onReloadButtonClicked(final View view) {
        this.showProgressSpinner();
        this.mWebView.a(false);
    }
    
    protected void onResume() {
        super.onResume();
        ((o)APIBootstrap.getInstance().analytics()).e();
        ((NativeAPI)APIBootstrap.getInstance()).c(this);
        final IntentFilter intentFilter = new IntentFilter("com.kongregate.android.internal.sdk.KongregateStatsSync");
        intentFilter.addAction("com.kongregate.android.internal.sdk.ExpiredSesssion");
        intentFilter.addAction("com.kongregate.android.internal.sdk.ClosePanel");
        intentFilter.addAction("com.kongregate.android.internal.sdk.LoadUser");
        LocalBroadcastManager.getInstance((Context)this).registerReceiver(this.mLocalBroadcastReceiver, intentFilter);
        if (this.mWebView != null) {
            this.mWebView.setPaused(false);
        }
    }
    
    protected void onStart() {
        super.onStart();
        if ("gdpr_adult".equals(this.mAlertDialogId)) {
            this.showGDPRAdultAlert();
        }
        else {
            if ("gdpr_underage".equals(this.mAlertDialogId)) {
                this.showGDPRUnderageAlert();
                return;
            }
            if (this.verifyAPIReady() && !this.mStarted) {
                this.mStarted = true;
                this.updateWebViewCookies();
            }
        }
    }
    
    public void onStop() {
        super.onStop();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (b && !this.getIntent().getBooleanExtra("showSystemUi", false)) {
            com.kongregate.android.internal.util.a.a(this, this.getIntent().getBooleanExtra("allowImmersiveMode", true));
        }
    }
    
    public void setHasHistory(final boolean mHasHistory) {
        this.mHasHistory = mHasHistory;
    }
    
    public void showProgressSpinner() {
        final View viewById = this.findViewById(g.a("kongregate_progress_spinner", "id", (Context)this));
        if (viewById != null) {
            viewById.setVisibility(0);
            this.mWebViewHolder.setVisibility(8);
        }
    }
    
    protected void updateWebViewCookies() {
        if (this.mConnectionManager != null) {
            j.a("updating web cookies");
            this.mConnectionManager.i();
        }
    }
    
    protected boolean verifyAPIReady() {
        this.mConnectionManager = a.b();
        if (this.mConnectionManager == null) {
            j.d("HttpConnectionManager is null, closing panel since API is not initialized");
            this.finish();
            return false;
        }
        this.mWebViewManager = b.a();
        if (this.mWebViewManager == null) {
            j.d("WebViewManager is not ready, closing panel since API is not initialized");
            this.finish();
            return false;
        }
        return true;
    }
}
