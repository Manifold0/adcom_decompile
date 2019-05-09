package com.kongregate.android.api.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.api.MobileServices;
import com.kongregate.android.internal.browser.C0451a;
import com.kongregate.android.internal.browser.C0462b;
import com.kongregate.android.internal.browser.MobileApiWebView;
import com.kongregate.android.internal.sdk.C0507l;
import com.kongregate.android.internal.sdk.C0525o;
import com.kongregate.android.internal.sdk.NativeAPI;
import com.kongregate.android.internal.util.C0542a;
import com.kongregate.android.internal.util.C0558g;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.android.internal.util.StringUtils;
import com.kongregate.p000o.p002g.C0640a;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class KongregatePanelActivity extends Activity {
    public static final String ALERT_TYPE_GDPR_ADULT = "gdpr_adult";
    public static final String ALERT_TYPE_GDPR_UNDERAGE = "gdpr_underage";
    public static final String INTENT_EXTRA_ALERT = "alert";
    public static final String INTENT_EXTRA_TARGET = "target";
    public static final String INTENT_EXTRA_TARGET_ID = "target_id";
    public static final HashMap<String, String[]> PANEL_TRANSITION_MAP = new HashMap();
    private AlertDialog mAlertDialog = null;
    private String mAlertDialogId = null;
    private C0640a mConnectionManager;
    private boolean mHasHistory = false;
    private BroadcastReceiver mLocalBroadcastReceiver = new C04361();
    private boolean mStarted;
    private String[] mTransitionOverride = null;
    private MobileApiWebView mWebView;
    private ViewGroup mWebViewHolder;
    private C0462b mWebViewManager;

    /* renamed from: com.kongregate.android.api.activities.KongregatePanelActivity$1 */
    class C04361 extends BroadcastReceiver {
        C04361() {
        }

        public void onReceive(Context context, Intent intent) {
            if (KongregatePanelActivity.this.mWebView == null) {
                C0562j.m759c("Web View has already been destroyed. Ignoring: " + intent.getAction());
            } else if (C0507l.f559i.equals(intent.getAction())) {
                JSONObject jSONObject = new JSONObject();
                try {
                    jSONObject.put("username", intent.getStringExtra("username"));
                } catch (JSONException e) {
                }
                KongregatePanelActivity.this.mWebView.m252a("invalidSession", jSONObject);
            } else if (C0507l.f558h.equals(intent.getAction())) {
                KongregatePanelActivity.this.mWebView.m252a("KongEventStatsSync", new JSONObject());
            } else if (C0507l.f560j.equals(intent.getAction())) {
                KongregatePanelActivity.this.finish();
            } else if (C0507l.f561k.equals(intent.getAction())) {
                KongregatePanelActivity.this.mWebView.m261m();
            }
        }
    }

    /* renamed from: com.kongregate.android.api.activities.KongregatePanelActivity$2 */
    class C04372 implements OnClickListener {
        C04372() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            KongregatePanelActivity.this.dismissAlertDialog(C0507l.f556f);
        }
    }

    /* renamed from: com.kongregate.android.api.activities.KongregatePanelActivity$3 */
    class C04383 implements OnClickListener {
        C04383() {
        }

        public void onClick(DialogInterface dialogInterface, int i) {
            KongregatePanelActivity.this.dismissAlertDialog(C0507l.f557g);
        }
    }

    static {
        PANEL_TRANSITION_MAP.put(MobileServices.PANEL_TRANSITION_SLIDE_FROM_LEFT, new String[]{"slide_in_left", "slide_out_left"});
        PANEL_TRANSITION_MAP.put(MobileServices.PANEL_TRANSITION_SLIDE_FROM_RIGHT, new String[]{"slide_in_right", "slide_out_right"});
    }

    protected void onCreate(Bundle bundle) {
        if (NativeAPI.m351g().mo1253c()) {
            C0562j.m753a("Panel onCreate");
            super.onCreate(bundle);
            this.mAlertDialogId = getIntent().getStringExtra(INTENT_EXTRA_ALERT);
            if (this.mAlertDialogId != null) {
                C0562j.m753a("Show Alert Dialog: " + this.mAlertDialogId);
                return;
            }
            requestWindowFeature(1);
            try {
                CharSequence stringExtra = getIntent().getStringExtra(C0451a.f317g);
                if (StringUtils.m587c(stringExtra)) {
                    setRequestedOrientation(4);
                } else if ("landscape".equals(stringExtra)) {
                    setRequestedOrientation(0);
                } else if (KongregateAPI.ORIENTATION_LANDSCAPE_SENSOR.equals(stringExtra)) {
                    setRequestedOrientation(6);
                } else if ("portrait".equals(stringExtra)) {
                    setRequestedOrientation(1);
                } else if (KongregateAPI.ORIENTATION_PORTRAIT_SENSOR.equals(stringExtra)) {
                    setRequestedOrientation(7);
                } else {
                    C0562j.m753a("Unrecognized orientation option: " + stringExtra + ", using value from manifest");
                }
                C0562j.m753a("screen orientation option: " + stringExtra + " requested: " + getRequestedOrientation());
            } catch (Throwable e) {
                C0562j.m760c("Failed to set request orientation: ", e);
            }
            String stringExtra2 = getIntent().getStringExtra(C0451a.f318h);
            if (PANEL_TRANSITION_MAP.containsKey(stringExtra2)) {
                this.mTransitionOverride = (String[]) PANEL_TRANSITION_MAP.get(stringExtra2);
            }
            setContentView(C0558g.m664a("kongregate_browser", "layout", (Context) this));
            getWindow().setLayout(-1, -1);
            this.mStarted = false;
            this.mWebView = (MobileApiWebView) findViewById(C0558g.m664a("kongregate_webview", "id", (Context) this));
            if (verifyAPIReady()) {
                checkToolbarAssetOverride("kongregate_logo", "kongregate_custom_toolbar_logo");
                checkToolbarAssetOverride("kongregate_toolbar", "kongregate_custom_toolbar_background");
                this.mWebView = this.mWebViewManager.m285a(false);
                this.mWebViewHolder = (ViewGroup) findViewById(getResources().getIdentifier("kongregate_webview_holder", "id", getPackageName()));
                this.mWebView.m251a(this);
                this.mWebViewHolder.addView(this.mWebView);
                updateWebViewCookies();
                if (!getIntent().getBooleanExtra(C0451a.f312b, false)) {
                    C0542a.m602a((Activity) this, getIntent().getBooleanExtra(C0451a.f313c, true));
                    return;
                }
                return;
            }
            finish();
            return;
        }
        C0562j.m759c("Kong panel is not enabled, closing");
        finish();
    }

    private void dismissAlertDialog(String str) {
        Intent intent = new Intent(C0507l.f554d);
        intent.putExtra(C0507l.f555e, str);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
        finish();
    }

    private void resetAlertDialog() {
        if (this.mAlertDialog != null && this.mAlertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
        }
    }

    private void showGDPRAdultAlert() {
        resetAlertDialog();
        this.mAlertDialog = new Builder(this).setTitle("IMPORTANT INFORMATION").setCancelable(false).setPositiveButton("ACCEPT", new C04372()).setMessage(Html.fromHtml("<html>By choosing \"<b>ACCEPT</b>,\" I acknowledge that I have read and agree to the <a href=\"https://www.kongregate.com/user-agreement\">User Agreement</a></html>, <a href=\"https://www.kongregate.com/privacy\">Privacy Policy</a> and <a href=\"https://www.kongregate.com/cookie-policy\">Cookie Policy.</html>")).create();
        this.mAlertDialog.show();
        View findViewById = this.mAlertDialog.findViewById(16908299);
        if (findViewById instanceof TextView) {
            ((TextView) findViewById).setMovementMethod(LinkMovementMethod.getInstance());
        } else {
            C0562j.m759c("ShowGDPRAlert: failed to find message view to make links clickable");
        }
    }

    private void showGDPRUnderageAlert() {
        resetAlertDialog();
        this.mAlertDialog = new Builder(this).setTitle("IMPORTANT INFORMATION").setCancelable(false).setPositiveButton("VIEW POLICY", new C04383()).setMessage(Html.fromHtml("Your parent or guardian must accept our privacy policy to continue.")).create();
        this.mAlertDialog.show();
    }

    private void checkToolbarAssetOverride(String str, String str2) {
        int a = C0558g.m664a(str2, "drawable", (Context) this);
        if (a != 0) {
            C0562j.m753a("Found toolbar asset override: " + str + " : " + str2);
            View findViewById = findViewById(C0558g.m664a(str, "id", (Context) this));
            if (findViewById == null) {
                C0562j.m759c("Unable to find custom drawable or view resource. Skipping custom background.");
            } else if ("kongregate_custom_toolbar_logo".equals(str2)) {
                ((ImageView) findViewById).setImageResource(a);
            } else {
                findViewById.setBackgroundResource(a);
            }
        }
    }

    protected void onStart() {
        super.onStart();
        if (ALERT_TYPE_GDPR_ADULT.equals(this.mAlertDialogId)) {
            showGDPRAdultAlert();
        } else if (ALERT_TYPE_GDPR_UNDERAGE.equals(this.mAlertDialogId)) {
            showGDPRUnderageAlert();
        } else if (verifyAPIReady() && !this.mStarted) {
            this.mStarted = true;
            updateWebViewCookies();
        }
    }

    protected boolean verifyAPIReady() {
        this.mConnectionManager = C0640a.m1055b();
        if (this.mConnectionManager == null) {
            C0562j.m761d("HttpConnectionManager is null, closing panel since API is not initialized");
            finish();
            return false;
        }
        this.mWebViewManager = C0462b.m266a();
        if (this.mWebViewManager != null) {
            return true;
        }
        C0562j.m761d("WebViewManager is not ready, closing panel since API is not initialized");
        finish();
        return false;
    }

    protected void updateWebViewCookies() {
        if (this.mConnectionManager != null) {
            C0562j.m753a("updating web cookies");
            this.mConnectionManager.m1080i();
        }
    }

    public void finish() {
        super.finish();
        if (this.mTransitionOverride != null) {
            overridePendingTransition(getResources().getIdentifier(this.mTransitionOverride[0], "anim", getPackageName()), getResources().getIdentifier(this.mTransitionOverride[1], "anim", getPackageName()));
        }
    }

    protected void onPause() {
        C0562j.m753a("Panel onPause, finishing: " + isFinishing());
        ((C0525o) APIBootstrap.getInstance().analytics()).m533f();
        super.onPause();
        ((NativeAPI) APIBootstrap.getInstance()).m382b((Activity) this);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(this.mLocalBroadcastReceiver);
        if (isFinishing()) {
            destroy();
        }
    }

    private void destroy() {
        if (!(this.mWebView == null || this.mWebViewHolder == null)) {
            C0562j.m756b("Destroying KongregatePanelActivity");
            this.mWebViewHolder.removeView(this.mWebView);
            this.mWebView.mo1129g();
            this.mWebView = null;
            this.mWebViewHolder = null;
            LocalBroadcastManager.getInstance(this).sendBroadcast(C0507l.m456a(KongregateEvent.CLOSED_KONGREGATE));
        }
        if (this.mAlertDialog != null && this.mAlertDialog.isShowing()) {
            this.mAlertDialog.dismiss();
        }
    }

    protected void onResume() {
        super.onResume();
        ((C0525o) APIBootstrap.getInstance().analytics()).m532e();
        ((NativeAPI) APIBootstrap.getInstance()).m387c((Activity) this);
        IntentFilter intentFilter = new IntentFilter(C0507l.f558h);
        intentFilter.addAction(C0507l.f559i);
        intentFilter.addAction(C0507l.f560j);
        intentFilter.addAction(C0507l.f561k);
        LocalBroadcastManager.getInstance(this).registerReceiver(this.mLocalBroadcastReceiver, intentFilter);
        if (this.mWebView != null) {
            this.mWebView.setPaused(false);
        }
    }

    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z && !getIntent().getBooleanExtra(C0451a.f312b, false)) {
            C0542a.m602a((Activity) this, getIntent().getBooleanExtra(C0451a.f313c, true));
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        C0562j.m753a("Panel config changed: " + configuration);
    }

    public void onStop() {
        super.onStop();
    }

    protected void onDestroy() {
        C0562j.m753a("Panel onDestroy");
        super.onDestroy();
        destroy();
    }

    protected void onActivityResult(int i, int i2, Intent intent) {
        C0562j.m753a("onActivityResult(" + i + "," + i2 + "," + intent);
        this.mWebView.getFacebookHelper().m659a(this, i, i2, intent);
    }

    public void onCloseButtonClicked(View view) {
        finish();
    }

    public void onReloadButtonClicked(View view) {
        showProgressSpinner();
        this.mWebView.m253a(false);
    }

    public void hideProgressSpinner() {
        View findViewById = findViewById(getResources().getIdentifier("kongregate_progress_spinner", "id", getPackageName()));
        if (findViewById != null) {
            findViewById.setVisibility(8);
            this.mWebViewHolder.setVisibility(0);
        }
    }

    public void showProgressSpinner() {
        View findViewById = findViewById(C0558g.m664a("kongregate_progress_spinner", "id", (Context) this));
        if (findViewById != null) {
            findViewById.setVisibility(0);
            this.mWebViewHolder.setVisibility(8);
        }
    }

    public void onMenuButtonClicked(View view) {
        this.mWebView.m252a("toggle_menu", new JSONObject());
    }

    public void setHasHistory(boolean z) {
        this.mHasHistory = z;
    }

    public void onBackPressed() {
        if (this.mHasHistory && this.mWebView.m258j()) {
            this.mWebView.m252a("back", new JSONObject());
        } else {
            super.onBackPressed();
        }
    }
}
