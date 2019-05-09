// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.app.AlertDialog$Builder;
import com.tapjoy.internal.fq;
import com.tapjoy.internal.gc;
import java.io.Serializable;
import com.tapjoy.internal.et;
import android.widget.VideoView;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.BasicWebView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Build$VERSION;
import android.content.Context;
import com.tapjoy.internal.ep;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.os.Looper;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.view.View$OnClickListener;
import android.app.Activity;

public class TJAdUnitActivity extends Activity implements View$OnClickListener
{
    private static TJAdUnitActivity b;
    private final Handler a;
    private TJAdUnit c;
    private TJPlacementData d;
    private TJAdUnitSaveStateData e;
    private RelativeLayout f;
    private TJCloseButton g;
    private ProgressBar h;
    private boolean i;
    
    public TJAdUnitActivity() {
        this.a = new Handler(Looper.getMainLooper());
        this.e = new TJAdUnitSaveStateData();
        this.f = null;
        this.i = false;
    }
    
    static void a() {
        final TJAdUnitActivity b = TJAdUnitActivity.b;
        if (b != null) {
            b.handleClose(true);
        }
    }
    
    public void handleClose() {
        this.handleClose(false);
    }
    
    public void handleClose(final boolean b) {
        if (!this.c.getCloseRequested()) {
            TapjoyLog.d("TJAdUnitActivity", "closeRequested");
            this.c.closeRequested(b);
            this.a.postDelayed((Runnable)new Runnable() {
                @Override
                public final void run() {
                    if (TJAdUnitActivity.this.c.getCloseRequested()) {
                        TapjoyLog.d("TJAdUnitActivity", "Did not receive callback from content. Closing ad.");
                        TJAdUnitActivity.this.finish();
                    }
                }
            }, 1000L);
        }
    }
    
    protected void onActivityResult(final int n, final int n2, final Intent intent) {
        if (n == 327 && intent != null && intent.hasExtra("placement_data") && this.c != null) {
            this.c.invokeBridgeCallback(((TJPlacementData)intent.getSerializableExtra("placement_data")).getCallbackID(), Boolean.TRUE);
        }
    }
    
    public void onBackPressed() {
        this.handleClose();
    }
    
    public void onClick(final View view) {
        this.handleClose();
    }
    
    protected void onCreate(Bundle extras) {
        TapjoyLog.d("TJAdUnitActivity", "TJAdUnitActivity onCreate: " + extras);
        super.onCreate(extras);
        TJAdUnitActivity.b = this;
        Label_0076: {
            if (extras == null) {
                break Label_0076;
            }
            this.e = (TJAdUnitSaveStateData)extras.getSerializable("ad_unit_bundle");
            if (this.e == null || !this.e.isVideoComplete) {
                break Label_0076;
            }
            TapjoyLog.d("TJAdUnitActivity", "finishing TJAdUnitActivity");
            this.finish();
            return;
        }
        extras = this.getIntent().getExtras();
        if (extras == null || extras.getSerializable("placement_data") == null) {
            TapjoyLog.e("TJAdUnitActivity", new TapjoyErrorMessage(TapjoyErrorMessage.ErrorType.SDK_ERROR, "Failed to launch AdUnit Activity"));
            this.finish();
            return;
        }
        this.d = (TJPlacementData)extras.getSerializable("placement_data");
        if (this.d.getContentViewId() != null) {
            TapjoyConnectCore.viewWillOpen(this.d.getContentViewId(), 1);
        }
        if (TJPlacementManager.a(this.d.getKey()) != null) {
            this.c = TJPlacementManager.a(this.d.getKey()).getAdUnit();
        }
        else {
            (this.c = new TJAdUnit()).setAdContentTracker(new ep(this.d.getPlacementName(), this.d.getPlacementType()));
        }
        if (!this.c.hasCalledLoad()) {
            TapjoyLog.d("TJAdUnitActivity", "No content loaded for ad unit -- loading now");
            this.c.load(this.d, false, (Context)this);
        }
        this.c.setAdUnitActivity(this);
        if (Build$VERSION.SDK_INT < 11) {
            this.setTheme(16973829);
        }
        else {
            this.requestWindowFeature(1);
            this.getWindow().setFlags(1024, 1024);
            this.getWindow().setFlags(16777216, 16777216);
        }
        this.getWindow().setBackgroundDrawable((Drawable)new ColorDrawable(0));
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
        (this.f = new RelativeLayout((Context)this)).setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.f.setBackgroundColor(0);
        final BasicWebView backgroundWebView = this.c.getBackgroundWebView();
        backgroundWebView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        if (backgroundWebView.getParent() != null) {
            ((ViewGroup)backgroundWebView.getParent()).removeView((View)backgroundWebView);
        }
        final MraidView webView = this.c.getWebView();
        webView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        if (webView.getParent() != null) {
            ((ViewGroup)webView.getParent()).removeView((View)webView);
        }
        final VideoView videoView = this.c.getVideoView();
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams2.addRule(13);
        videoView.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        if (videoView.getParent() != null) {
            ((ViewGroup)videoView.getParent()).removeView((View)videoView);
        }
        this.f.addView((View)backgroundWebView);
        this.f.addView((View)videoView);
        this.f.addView((View)webView);
        this.h = new ProgressBar((Context)this, (AttributeSet)null, 16842874);
        if (this.d.hasProgressSpinner()) {
            this.setProgressSpinnerVisibility(true);
        }
        else {
            this.setProgressSpinnerVisibility(false);
        }
        final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(-2, -2);
        layoutParams3.addRule(13);
        this.h.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        this.f.addView((View)this.h);
        if (!this.c.getWebView().isMraid()) {
            (this.g = new TJCloseButton((Context)this)).setOnClickListener((View$OnClickListener)this);
            this.f.addView((View)this.g);
        }
        this.setContentView((View)this.f);
        this.c.setVisible(true);
        final TJCorePlacement a = TJPlacementManager.a(this.d.getKey());
        if (a == null) {
            return;
        }
        TapjoyLog.i(TJCorePlacement.a, "Content shown for placement " + a.c.getPlacementName());
        a.f.a();
        final TJPlacement a2 = a.a("SHOW");
        if (a2 != null && a2.getListener() != null) {
            a2.getListener().onContentShow(a2);
        }
    }
    
    protected void onDestroy() {
        super.onDestroy();
        TJAdUnitActivity.b = null;
        TapjoyLog.d("TJAdUnitActivity", "onDestroy");
        if (this.c != null) {
            this.c.destroy();
        }
        if (this.d != null && this.d.isBaseActivity()) {
            if (this.d.getContentViewId() != null) {
                TapjoyConnectCore.viewDidClose(this.d.getContentViewId());
            }
            final TJCorePlacement a = TJPlacementManager.a(this.d.getKey());
            if (a != null) {
                final TJPlacement a2 = a.a("SHOW");
                if (a2 != null && a2.getListener() != null) {
                    TapjoyLog.i(TJCorePlacement.a, "Content dismissed for placement " + a.c.getPlacementName());
                    final et a3 = a.f.a;
                    if (a3 != null) {
                        a3.b.clear();
                    }
                    if (a2 != null && a2.a != null) {
                        a2.a.onContentDismiss(a2);
                    }
                }
            }
        }
    }
    
    protected void onPause() {
        super.onPause();
        TapjoyLog.d("TJAdUnitActivity", "onPause");
        this.c.pause();
    }
    
    protected void onResume() {
        TapjoyLog.d("TJAdUnitActivity", "onResume");
        super.onResume();
        if (this.c.isLockedOrientation()) {
            this.setRequestedOrientation(this.c.getOrientation());
        }
        this.c.resume(this.e);
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        TapjoyLog.d("TJAdUnitActivity", "onSaveInstanceState");
        this.e.seekTime = this.c.getVideoSeekTime();
        this.e.isVideoComplete = this.c.isVideoComplete();
        this.e.isVideoMuted = this.c.isMuted();
        bundle.putSerializable("ad_unit_bundle", (Serializable)this.e);
    }
    
    protected void onStart() {
        super.onStart();
        TapjoyLog.d("TJAdUnitActivity", "onStart");
        if (gc.a().n) {
            this.i = true;
            fq.a(this);
        }
        if (!this.d.isBaseActivity()) {
            this.setResult(-1, this.getIntent());
        }
    }
    
    protected void onStop() {
        if (this.i) {
            this.i = false;
            fq.b(this);
        }
        super.onStop();
        TapjoyLog.d("TJAdUnitActivity", "onStop");
    }
    
    public void setCloseButtonClickable(final boolean clickableRequested) {
        this.g.setClickableRequested(clickableRequested);
    }
    
    public void setCloseButtonVisibility(final boolean b) {
        if (b) {
            this.g.setVisibility(0);
            return;
        }
        this.g.setVisibility(4);
    }
    
    public void setProgressSpinnerVisibility(final boolean b) {
        if (b) {
            this.h.setVisibility(0);
            return;
        }
        this.h.setVisibility(4);
    }
    
    public void showErrorDialog() {
        if (!this.isFinishing()) {
            new AlertDialog$Builder((Context)this).setMessage((CharSequence)"An error occured. Please try again later.").setPositiveButton((CharSequence)"OK", (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public final void onClick(final DialogInterface dialogInterface, final int n) {
                    TJAdUnitActivity.this.handleClose();
                    dialogInterface.cancel();
                }
            }).create().show();
        }
    }
}
