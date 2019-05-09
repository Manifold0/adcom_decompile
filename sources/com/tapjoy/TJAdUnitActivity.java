package com.tapjoy;

import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.ep;
import com.tapjoy.internal.et;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.gc;

public class TJAdUnitActivity extends Activity implements OnClickListener {
    /* renamed from: b */
    private static TJAdUnitActivity f6822b;
    /* renamed from: a */
    private final Handler f6823a = new Handler(Looper.getMainLooper());
    /* renamed from: c */
    private TJAdUnit f6824c;
    /* renamed from: d */
    private TJPlacementData f6825d;
    /* renamed from: e */
    private TJAdUnitSaveStateData f6826e = new TJAdUnitSaveStateData();
    /* renamed from: f */
    private RelativeLayout f6827f = null;
    /* renamed from: g */
    private TJCloseButton f6828g;
    /* renamed from: h */
    private ProgressBar f6829h;
    /* renamed from: i */
    private boolean f6830i = false;

    /* renamed from: com.tapjoy.TJAdUnitActivity$1 */
    class C27871 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ TJAdUnitActivity f6820a;

        C27871(TJAdUnitActivity tJAdUnitActivity) {
            this.f6820a = tJAdUnitActivity;
        }

        public final void run() {
            if (this.f6820a.f6824c.getCloseRequested()) {
                TapjoyLog.m7126d("TJAdUnitActivity", "Did not receive callback from content. Closing ad.");
                this.f6820a.finish();
            }
        }
    }

    /* renamed from: com.tapjoy.TJAdUnitActivity$2 */
    class C27882 implements DialogInterface.OnClickListener {
        /* renamed from: a */
        final /* synthetic */ TJAdUnitActivity f6821a;

        C27882(TJAdUnitActivity tJAdUnitActivity) {
            this.f6821a = tJAdUnitActivity;
        }

        public final void onClick(DialogInterface dialog, int which) {
            this.f6821a.handleClose();
            dialog.cancel();
        }
    }

    protected void onCreate(Bundle savedInstanceState) {
        TapjoyLog.m7126d("TJAdUnitActivity", "TJAdUnitActivity onCreate: " + savedInstanceState);
        super.onCreate(savedInstanceState);
        f6822b = this;
        if (savedInstanceState != null) {
            this.f6826e = (TJAdUnitSaveStateData) savedInstanceState.getSerializable("ad_unit_bundle");
            if (this.f6826e != null && this.f6826e.isVideoComplete) {
                TapjoyLog.m7126d("TJAdUnitActivity", "finishing TJAdUnitActivity");
                finish();
                return;
            }
        }
        Bundle extras = getIntent().getExtras();
        if (extras == null || extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) == null) {
            TapjoyLog.m7127e("TJAdUnitActivity", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Failed to launch AdUnit Activity"));
            finish();
            return;
        }
        this.f6825d = (TJPlacementData) extras.getSerializable(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
        if (this.f6825d.getContentViewId() != null) {
            TapjoyConnectCore.viewWillOpen(this.f6825d.getContentViewId(), 1);
        }
        if (TJPlacementManager.m7079a(this.f6825d.getKey()) != null) {
            this.f6824c = TJPlacementManager.m7079a(this.f6825d.getKey()).getAdUnit();
        } else {
            this.f6824c = new TJAdUnit();
            this.f6824c.setAdContentTracker(new ep(this.f6825d.getPlacementName(), this.f6825d.getPlacementType()));
        }
        if (!this.f6824c.hasCalledLoad()) {
            TapjoyLog.m7126d("TJAdUnitActivity", "No content loaded for ad unit -- loading now");
            this.f6824c.load(this.f6825d, false, this);
        }
        this.f6824c.setAdUnitActivity(this);
        if (VERSION.SDK_INT < 11) {
            setTheme(16973829);
        } else {
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            getWindow().setFlags(16777216, 16777216);
        }
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -1);
        this.f6827f = new RelativeLayout(this);
        this.f6827f.setLayoutParams(layoutParams);
        this.f6827f.setBackgroundColor(0);
        View backgroundWebView = this.f6824c.getBackgroundWebView();
        backgroundWebView.setLayoutParams(layoutParams);
        if (backgroundWebView.getParent() != null) {
            ((ViewGroup) backgroundWebView.getParent()).removeView(backgroundWebView);
        }
        View webView = this.f6824c.getWebView();
        webView.setLayoutParams(layoutParams);
        if (webView.getParent() != null) {
            ((ViewGroup) webView.getParent()).removeView(webView);
        }
        View videoView = this.f6824c.getVideoView();
        LayoutParams layoutParams2 = new RelativeLayout.LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        videoView.setLayoutParams(layoutParams2);
        if (videoView.getParent() != null) {
            ((ViewGroup) videoView.getParent()).removeView(videoView);
        }
        this.f6827f.addView(backgroundWebView);
        this.f6827f.addView(videoView);
        this.f6827f.addView(webView);
        this.f6829h = new ProgressBar(this, null, 16842874);
        if (this.f6825d.hasProgressSpinner()) {
            setProgressSpinnerVisibility(true);
        } else {
            setProgressSpinnerVisibility(false);
        }
        layoutParams2 = new RelativeLayout.LayoutParams(-2, -2);
        layoutParams2.addRule(13);
        this.f6829h.setLayoutParams(layoutParams2);
        this.f6827f.addView(this.f6829h);
        if (!this.f6824c.getWebView().isMraid()) {
            this.f6828g = new TJCloseButton(this);
            this.f6828g.setOnClickListener(this);
            this.f6827f.addView(this.f6828g);
        }
        setContentView(this.f6827f);
        this.f6824c.setVisible(true);
        TJCorePlacement a = TJPlacementManager.m7079a(this.f6825d.getKey());
        if (a != null) {
            TapjoyLog.m7129i(TJCorePlacement.f6912a, "Content shown for placement " + a.f6914c.getPlacementName());
            a.f6917f.m7689a();
            TJPlacement a2 = a.m7059a("SHOW");
            if (a2 != null && a2.getListener() != null) {
                a2.getListener().onContentShow(a2);
            }
        }
    }

    public void setCloseButtonVisibility(boolean visibility) {
        if (visibility) {
            this.f6828g.setVisibility(0);
        } else {
            this.f6828g.setVisibility(4);
        }
    }

    public void setCloseButtonClickable(boolean clickable) {
        this.f6828g.setClickableRequested(clickable);
    }

    public void setProgressSpinnerVisibility(boolean visibility) {
        if (visibility) {
            this.f6829h.setVisibility(0);
        } else {
            this.f6829h.setVisibility(4);
        }
    }

    public void onBackPressed() {
        handleClose();
    }

    public void handleClose() {
        handleClose(false);
    }

    public void handleClose(boolean shouldForceClose) {
        if (!this.f6824c.getCloseRequested()) {
            TapjoyLog.m7126d("TJAdUnitActivity", String.CLOSE_REQUESTED);
            this.f6824c.closeRequested(shouldForceClose);
            this.f6823a.postDelayed(new C27871(this), 1000);
        }
    }

    protected void onDestroy() {
        super.onDestroy();
        f6822b = null;
        TapjoyLog.m7126d("TJAdUnitActivity", "onDestroy");
        if (this.f6824c != null) {
            this.f6824c.destroy();
        }
        if (this.f6825d != null && this.f6825d.isBaseActivity()) {
            if (this.f6825d.getContentViewId() != null) {
                TapjoyConnectCore.viewDidClose(this.f6825d.getContentViewId());
            }
            TJCorePlacement a = TJPlacementManager.m7079a(this.f6825d.getKey());
            if (a != null) {
                TJPlacement a2 = a.m7059a("SHOW");
                if (a2 != null && a2.getListener() != null) {
                    TapjoyLog.m7129i(TJCorePlacement.f6912a, "Content dismissed for placement " + a.f6914c.getPlacementName());
                    et etVar = a.f6917f.f7707a;
                    if (etVar != null) {
                        etVar.f7676b.clear();
                    }
                    if (a2 != null && a2.f6958a != null) {
                        a2.f6958a.onContentDismiss(a2);
                    }
                }
            }
        }
    }

    protected void onResume() {
        TapjoyLog.m7126d("TJAdUnitActivity", "onResume");
        super.onResume();
        if (this.f6824c.isLockedOrientation()) {
            setRequestedOrientation(this.f6824c.getOrientation());
        }
        this.f6824c.resume(this.f6826e);
    }

    protected void onStart() {
        super.onStart();
        TapjoyLog.m7126d("TJAdUnitActivity", "onStart");
        if (gc.m7831a().f7862n) {
            this.f6830i = true;
            fq.m7775a(this);
        }
        if (!this.f6825d.isBaseActivity()) {
            setResult(-1, getIntent());
        }
    }

    protected void onPause() {
        super.onPause();
        TapjoyLog.m7126d("TJAdUnitActivity", "onPause");
        this.f6824c.pause();
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        TapjoyLog.m7126d("TJAdUnitActivity", "onSaveInstanceState");
        this.f6826e.seekTime = this.f6824c.getVideoSeekTime();
        this.f6826e.isVideoComplete = this.f6824c.isVideoComplete();
        this.f6826e.isVideoMuted = this.f6824c.isMuted();
        outState.putSerializable("ad_unit_bundle", this.f6826e);
    }

    protected void onStop() {
        if (this.f6830i) {
            this.f6830i = false;
            fq.m7780b(this);
        }
        super.onStop();
        TapjoyLog.m7126d("TJAdUnitActivity", "onStop");
    }

    public void showErrorDialog() {
        if (!isFinishing()) {
            new Builder(this).setMessage("An error occured. Please try again later.").setPositiveButton("OK", new C27882(this)).create().show();
        }
    }

    public void onClick(View v) {
        handleClose();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == TJAdUnitConstants.MRAID_REQUEST_CODE && data != null && data.hasExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA) && this.f6824c != null) {
            TJPlacementData tJPlacementData = (TJPlacementData) data.getSerializableExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA);
            this.f6824c.invokeBridgeCallback(tJPlacementData.getCallbackID(), Boolean.TRUE);
        }
    }

    /* renamed from: a */
    static void m7023a() {
        TJAdUnitActivity tJAdUnitActivity = f6822b;
        if (tJAdUnitActivity != null) {
            tJAdUnitActivity.handleClose(true);
        }
    }
}
