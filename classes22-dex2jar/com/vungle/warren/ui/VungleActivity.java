// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import android.util.DisplayMetrics;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.app.AlertDialog$Builder;
import android.view.ContextThemeWrapper;
import android.content.DialogInterface$OnClickListener;
import android.view.TouchDelegate;
import android.graphics.Rect;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.graphics.Bitmap;
import android.media.AudioManager;
import android.view.View$OnClickListener;
import android.net.Uri;
import android.os.PersistableBundle;
import android.media.MediaPlayer$OnCompletionListener;
import android.content.res.Resources;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.Vungle;
import com.vungle.warren.utility.ViewUtility;
import android.util.TypedValue;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.RelativeLayout;
import android.support.annotation.Nullable;
import android.content.res.Configuration;
import android.annotation.SuppressLint;
import android.util.Log;
import java.util.Locale;
import android.os.Build$VERSION;
import android.os.Bundle;
import android.content.IntentFilter;
import android.content.Intent;
import android.content.Context;
import android.webkit.WebView;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import android.widget.VideoView;
import android.widget.ProgressBar;
import com.vungle.warren.AdvertisementPresenterFactory;
import android.media.MediaPlayer;
import android.os.Handler;
import android.app.AlertDialog;
import android.widget.ImageView;
import android.content.BroadcastReceiver;
import com.vungle.warren.presenter.AdvertisementPresenter;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.app.Activity;

public class VungleActivity extends Activity implements AdView, MediaPlayer$OnPreparedListener, MediaPlayer$OnErrorListener
{
    protected static final double NINE_BY_SIXTEEN_ASPECT_RATIO = 0.5625;
    public static final String PLACEMENT_EXTRA = "placement";
    private static final String TAG = "VungleActivity";
    private static AdvertisementPresenter.EventListener bus;
    private BroadcastReceiver broadcastReciever;
    private ImageView closeButton;
    private ImageView ctaOverlay;
    private AlertDialog dialog;
    private Handler handler;
    private MediaPlayer mediaPlayer;
    private ImageView muteButton;
    private boolean muted;
    private boolean pendingPause;
    private String placementId;
    private AdvertisementPresenter presenter;
    private AdvertisementPresenterFactory presenterFactory;
    private ImageView privacyOverlay;
    private ProgressBar progressBar;
    private boolean released;
    private Runnable reportProgress;
    private int videoPosition;
    private VideoView videoView;
    private ReactiveVideoTracker viewabilityTracker;
    private WebView webView;
    
    public VungleActivity() {
        this.handler = new Handler();
        this.videoPosition = 0;
        this.muted = false;
        this.released = false;
    }
    
    private void connectBroadcastReceiver() {
        this.registerReceiver(this.broadcastReciever = new BroadcastReceiver() {
            public void onReceive(final Context context, final Intent intent) {
                final String stringExtra = intent.getStringExtra("command");
                switch (stringExtra) {
                    default: {
                        throw new IllegalArgumentException("No such command " + stringExtra);
                    }
                    case "closeFlex": {
                        if (VungleActivity.this.presenter.handleExit(intent.getStringExtra("placement"))) {
                            VungleActivity.this.close();
                        }
                    }
                }
            }
        }, new IntentFilter("AdvertisementBus"));
    }
    
    private void pauseAd() {
        if (this.webView != null) {
            this.webView.onPause();
        }
        if (!this.released && this.videoView.isPlaying()) {
            this.videoView.pause();
            this.videoPosition = this.videoView.getCurrentPosition();
        }
        this.presenter.stop(this.isChangingConfigurations(), this.isFinishing());
    }
    
    private void prepare(final Bundle bundle) {
        this.videoView.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
        this.webView.setWebViewClient(this.presenter.getWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface((Object)new JavascriptBridge((JavascriptBridge.ActionHandler)this.presenter), "Android");
        if (Build$VERSION.SDK_INT >= 17 && Build$VERSION.SDK_INT <= 19) {
            this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.videoView.setVisibility(8);
        this.webView.setVisibility(8);
    }
    
    private void resumeAd() {
        this.setImmersiveMode();
        if (this.webView != null) {
            this.webView.onResume();
        }
        if (this.dialog != null && this.dialog.isShowing()) {
            return;
        }
        if (!this.videoView.isPlaying() && this.mediaPlayer != null) {
            this.videoView.requestFocus();
            this.videoView.seekTo(this.videoPosition);
            this.videoView.start();
            return;
        }
        this.presenter.play();
    }
    
    public static void setEventListener(final AdvertisementPresenter.EventListener bus) {
        VungleActivity.bus = bus;
    }
    
    private void setImmersiveMode() {
        this.getWindow().getDecorView().setSystemUiVisibility(5894);
    }
    
    private void setupPlayerProgressBar() {
        this.reportProgress = new Runnable() {
            float duration = -2.0f;
            
            @Override
            public void run() {
                if (VungleActivity.this.mediaPlayer == null) {
                    return;
                }
                try {
                    final int currentPosition = VungleActivity.this.mediaPlayer.getCurrentPosition();
                    if (this.duration == -2.0f) {
                        this.duration = (float)VungleActivity.this.mediaPlayer.getDuration();
                    }
                    VungleActivity.this.presenter.reportAction("video_viewed", String.format(Locale.ENGLISH, "%d", currentPosition));
                    VungleActivity.this.presenter.onProgressUpdate((int)(currentPosition / this.duration * 100.0f));
                    VungleActivity.this.progressBar.setMax((int)this.duration);
                    VungleActivity.this.progressBar.setProgress(currentPosition);
                    VungleActivity.this.handler.postDelayed((Runnable)this, 1000L);
                }
                catch (IllegalStateException ex) {
                    Log.v("VungleActivity", "IllegalStateException while reporting progress indicates activity was killed via SIGKILL.");
                }
            }
        };
        this.handler.post(this.reportProgress);
    }
    
    protected boolean canRotate() {
        return true;
    }
    
    public void close() {
        this.runOnUiThread((Runnable)new Runnable() {
            @Override
            public void run() {
                if (VungleActivity.this.videoView != null && VungleActivity.this.videoView.isPlaying()) {
                    VungleActivity.this.presenter.stopViewabilityTracker();
                }
                VungleActivity.this.webView.removeJavascriptInterface("Android");
                VungleActivity.this.webView.loadUrl("about:blank");
            }
        });
        this.finish();
    }
    
    public String getWebsiteUrl() {
        return this.webView.getUrl();
    }
    
    @SuppressLint({ "ResourceType" })
    public void onBackPressed() {
        if (this.presenter.handleExit(null)) {
            this.close();
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        super.onConfigurationChanged(configuration);
        if (configuration.orientation == 2) {
            Log.d("VungleActivity", "landscape");
        }
        else if (configuration.orientation == 1) {
            Log.d("VungleActivity", "portrait");
        }
        this.presenter.notifyPropertiesChanged();
    }
    
    @SuppressLint({ "SetJavaScriptEnabled" })
    protected void onCreate(@Nullable final Bundle bundle) {
        super.onCreate(bundle);
        this.requestWindowFeature(1);
        this.getWindow().setFlags(16777216, 16777216);
        final Resources resources = this.getResources();
        final RelativeLayout relativeLayout = new RelativeLayout((Context)this);
        final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-1, -1);
        relativeLayout.setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.videoView = new VideoView((Context)this);
        final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-1, -1);
        layoutParams.addRule(13);
        this.videoView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        relativeLayout.addView((View)this.videoView, (ViewGroup$LayoutParams)layoutParams);
        (this.webView = new WebView((Context)this)).setLayoutParams((ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        relativeLayout.addView((View)this.webView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        this.progressBar = new ProgressBar((Context)this, (AttributeSet)null, 16842872);
        final RelativeLayout$LayoutParams layoutParams2 = new RelativeLayout$LayoutParams(-1, (int)TypedValue.applyDimension(1, 4.0f, resources.getDisplayMetrics()));
        layoutParams2.addRule(12);
        this.progressBar.setLayoutParams((ViewGroup$LayoutParams)layoutParams2);
        this.progressBar.setMax(100);
        this.progressBar.setIndeterminate(false);
        this.progressBar.setVisibility(4);
        relativeLayout.addView((View)this.progressBar);
        final int n = (int)TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        final RelativeLayout$LayoutParams layoutParams3 = new RelativeLayout$LayoutParams(n, n);
        final int n2 = (int)TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        layoutParams3.setMargins(n2, n2, n2, n2);
        (this.muteButton = new ImageView((Context)this)).setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.unMute, (Context)this));
        this.muteButton.setLayoutParams((ViewGroup$LayoutParams)layoutParams3);
        this.muteButton.setVisibility(8);
        relativeLayout.addView((View)this.muteButton);
        final RelativeLayout$LayoutParams layoutParams4 = new RelativeLayout$LayoutParams(n, n);
        layoutParams4.setMargins(n2, n2, n2, n2);
        (this.closeButton = new ImageView((Context)this)).setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.close, (Context)this));
        layoutParams4.addRule(11);
        this.closeButton.setLayoutParams((ViewGroup$LayoutParams)layoutParams4);
        this.closeButton.setVisibility(8);
        relativeLayout.addView((View)this.closeButton);
        final RelativeLayout$LayoutParams layoutParams5 = new RelativeLayout$LayoutParams(n, n);
        layoutParams5.addRule(12);
        layoutParams5.addRule(11);
        layoutParams5.setMargins(n2, n2, n2, n2);
        (this.ctaOverlay = new ImageView((Context)this)).setLayoutParams((ViewGroup$LayoutParams)layoutParams5);
        this.ctaOverlay.setVisibility(8);
        relativeLayout.addView((View)this.ctaOverlay);
        final RelativeLayout$LayoutParams layoutParams6 = new RelativeLayout$LayoutParams(n, n);
        layoutParams6.addRule(12);
        layoutParams6.addRule(9);
        layoutParams6.setMargins(n2, n2, n2, n2);
        (this.privacyOverlay = new ImageView((Context)this)).setLayoutParams((ViewGroup$LayoutParams)layoutParams6);
        this.privacyOverlay.setVisibility(8);
        relativeLayout.addView((View)this.privacyOverlay);
        this.setContentView((View)relativeLayout, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
        if (!Vungle.isInitialized() || VungleActivity.bus == null) {
            this.finish();
            return;
        }
        String stringExtra;
        if (this.getIntent().hasExtra("userID")) {
            stringExtra = this.getIntent().getStringExtra("userID");
        }
        else {
            stringExtra = null;
        }
        this.placementId = this.getIntent().getStringExtra("placement");
        this.presenterFactory = new AdvertisementPresenterFactory();
        this.presenter = this.presenterFactory.getAdPresenter(this.placementId, bundle, stringExtra);
        if (this.presenter == null) {
            if (VungleActivity.bus != null) {
                VungleActivity.bus.onError(new VungleException(10));
            }
            this.finish();
            return;
        }
        this.presenter.setEventListener(VungleActivity.bus);
        this.presenter.attach(this);
        this.presenter.prepare(bundle);
        this.prepare(bundle);
    }
    
    protected void onDestroy() {
        if (this.presenter != null) {
            this.presenter.stop(this.isChangingConfigurations(), true);
        }
        this.handler.removeCallbacksAndMessages((Object)null);
        while (true) {
            if (this.videoView == null || !this.videoView.isPlaying()) {
                break Label_0053;
            }
            try {
                this.videoView.stopPlayback();
                super.onDestroy();
            }
            catch (Throwable t) {
                Log.w("VungleActivity", "error on stopping player " + t);
                continue;
            }
            break;
        }
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        switch (n) {
            default: {
                sb.append("UNKNOWN");
                break;
            }
            case 100: {
                sb.append("MEDIA_ERROR_SERVER_DIED");
                break;
            }
            case 1: {
                sb.append("MEDIA_ERROR_UNKNOWN");
                break;
            }
        }
        sb.append(":");
        switch (n2) {
            default: {
                sb.append("MEDIA_ERROR_SYSTEM");
                break;
            }
            case -1004: {
                sb.append("MEDIA_ERROR_IO");
                break;
            }
            case -1007: {
                sb.append("MEDIA_ERROR_MALFORMED");
                break;
            }
            case 200: {
                sb.append("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
                break;
            }
            case -1010: {
                sb.append("MEDIA_ERROR_UNSUPPORTED");
                break;
            }
            case -110: {
                sb.append("MEDIA_ERROR_TIMED_OUT");
                break;
            }
        }
        this.presenter.reportError(sb.toString());
        return true;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.mediaPlayer = mediaPlayer;
        this.muteButton.setVisibility(0);
        if (this.muted) {
            mediaPlayer.setVolume(0.0f, 0.0f);
        }
        mediaPlayer.seekTo(this.videoPosition);
        mediaPlayer.start();
        this.presenter.initializeViewabilityTracker(mediaPlayer.getDuration(), this.videoView);
        mediaPlayer.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayer$OnCompletionListener() {
            public void onCompletion(final MediaPlayer mediaPlayer) {
                Log.d("VungleActivity", "mediaplayer onCompletion");
                if (VungleActivity.this.reportProgress != null) {
                    VungleActivity.this.handler.removeCallbacks(VungleActivity.this.reportProgress);
                }
                VungleActivity.this.presenter.onProgressUpdate(100);
                VungleActivity.this.runOnUiThread((Runnable)new Runnable() {
                    @Override
                    public void run() {
                        VungleActivity.this.muteButton.setEnabled(false);
                        VungleActivity.this.presenter.stopViewabilityTracker();
                    }
                });
            }
        });
        mediaPlayer.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayer$OnErrorListener() {
            public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
                switch (n) {
                    default: {
                        return true;
                    }
                }
            }
        });
        this.presenter.reportAction("videoLength", String.format(Locale.ENGLISH, "%d", mediaPlayer.getDuration()));
        this.setupPlayerProgressBar();
        if (this.pendingPause || (this.dialog != null && this.dialog.isShowing())) {
            this.pendingPause = false;
            this.videoView.pause();
        }
    }
    
    protected void onRestart() {
        super.onRestart();
        if (this.videoView != null) {
            this.videoView.seekTo(this.videoPosition);
        }
        this.setupPlayerProgressBar();
    }
    
    protected void onRestoreInstanceState(final Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        Log.d("VungleActivity", "onRestoreInstanceState(" + bundle + ")");
        this.presenter.restoreFromSave(bundle);
        if (bundle != null) {
            this.videoPosition = bundle.getInt("videoPosition", 0);
        }
    }
    
    public void onRestoreInstanceState(final Bundle bundle, final PersistableBundle persistableBundle) {
        super.onRestoreInstanceState(bundle, persistableBundle);
        if (bundle != null) {
            this.videoPosition = bundle.getInt("videoPosition");
        }
    }
    
    protected void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        Log.d("VungleActivity", "onSaveInstanceState");
        this.presenter.generateSaveState(bundle);
        this.presenterFactory.saveState(bundle);
        if (this.videoView != null) {
            bundle.putInt("videoPosition", this.videoPosition);
        }
    }
    
    protected void onStart() {
        this.connectBroadcastReceiver();
        super.onStart();
    }
    
    protected void onStop() {
        if (this.reportProgress != null) {
            this.handler.removeCallbacks(this.reportProgress);
        }
        this.unregisterReceiver(this.broadcastReciever);
        super.onStop();
    }
    
    public void onWindowFocusChanged(final boolean b) {
        super.onWindowFocusChanged(b);
        if (b) {
            this.resumeAd();
            return;
        }
        this.pauseAd();
    }
    
    public void open(final String s) {
        Log.v("VungleActivity", "Opening " + s);
        try {
            final Intent uri = Intent.parseUri(s, 0);
            uri.setFlags(268435456);
            this.startActivity(uri);
        }
        catch (Exception ex) {
            Log.e("VungleActivity", "Unable to start activity " + ex.getLocalizedMessage());
        }
    }
    
    public void playVideo(final Uri videoURI, final boolean muted) {
        this.videoView.setVisibility(0);
        this.videoView.setOnErrorListener((MediaPlayer$OnErrorListener)this);
        this.videoView.setVideoURI(videoURI);
        this.muted = muted;
        if (this.muted) {
            this.presenter.reportAction("mute", "true");
        }
        final Bitmap bitmap = ViewUtility.getBitmap(ViewUtility.Asset.mute, (Context)this);
        final Bitmap bitmap2 = ViewUtility.getBitmap(ViewUtility.Asset.unMute, (Context)this);
        this.privacyOverlay.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.privacy, (Context)this));
        this.privacyOverlay.setVisibility(0);
        this.privacyOverlay.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                VungleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://vungle.com/privacy/")));
            }
        });
        final ImageView muteButton = this.muteButton;
        Bitmap imageBitmap;
        if (this.muted) {
            imageBitmap = bitmap;
        }
        else {
            imageBitmap = bitmap2;
        }
        muteButton.setImageBitmap(imageBitmap);
        this.muteButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            final /* synthetic */ AudioManager val$audioManager = (AudioManager)VungleActivity.this.getSystemService("audio");
            
            public void onClick(final View view) {
                if (VungleActivity.this.mediaPlayer != null) {
                    if (VungleActivity.this.muted) {
                        final float n = this.val$audioManager.getStreamVolume(3) / (float)this.val$audioManager.getStreamMaxVolume(3);
                        VungleActivity.this.mediaPlayer.setVolume(n, n);
                        VungleActivity.this.muted = false;
                        VungleActivity.this.presenter.reportAction("unmute", "false");
                    }
                    else {
                        VungleActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
                        VungleActivity.this.muted = true;
                        VungleActivity.this.presenter.reportAction("mute", "true");
                    }
                    final ImageView access$300 = VungleActivity.this.muteButton;
                    Bitmap imageBitmap;
                    if (VungleActivity.this.muted) {
                        imageBitmap = bitmap;
                    }
                    else {
                        imageBitmap = bitmap2;
                    }
                    access$300.setImageBitmap(imageBitmap);
                }
            }
        });
        this.progressBar.setVisibility(0);
        this.progressBar.setMax(this.videoView.getDuration());
        this.closeButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                VungleActivity.this.onBackPressed();
            }
        });
    }
    
    public void setOrientation(final int requestedOrientation) {
        Log.d("VungleActivity", " requested orientation " + requestedOrientation);
        if (this.canRotate()) {
            this.setRequestedOrientation(requestedOrientation);
        }
    }
    
    public void setVisibility(final boolean b) {
    }
    
    public void showCTAOverlay(final boolean b, final boolean enabled, final int n) {
        if (b) {
            this.videoView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    VungleActivity.this.ctaOverlay.setVisibility(0);
                    VungleActivity.this.ctaOverlay.setImageBitmap(ViewUtility.getBitmap(ViewUtility.Asset.cta, (Context)VungleActivity.this));
                    VungleActivity.this.ctaOverlay.setEnabled(true);
                }
            });
        }
        else {
            this.ctaOverlay.setVisibility(0);
            final ImageView ctaOverlay = this.ctaOverlay;
            ViewUtility.Asset asset;
            if (enabled) {
                asset = ViewUtility.Asset.cta;
            }
            else {
                asset = ViewUtility.Asset.ctaDisabled;
            }
            ctaOverlay.setImageBitmap(ViewUtility.getBitmap(asset, (Context)this));
            this.ctaOverlay.setEnabled(enabled);
        }
        if (n == 100) {
            this.videoView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    VungleActivity.this.presenter.reportAction("cta", "");
                    ((JavascriptBridge.ActionHandler)VungleActivity.this.presenter).handleAction("download");
                }
            });
        }
        else {
            this.ctaOverlay.getViewTreeObserver().addOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)new ViewTreeObserver$OnPreDrawListener() {
                final /* synthetic */ View val$parent = (View)VungleActivity.this.ctaOverlay.getParent();
                
                public boolean onPreDraw() {
                    VungleActivity.this.ctaOverlay.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver$OnPreDrawListener)this);
                    this.val$parent.post((Runnable)new Runnable() {
                        final /* synthetic */ int val$finalHeight = VungleActivity.this.ctaOverlay.getMeasuredHeight();
                        final /* synthetic */ int val$finalWidth = VungleActivity.this.ctaOverlay.getMeasuredWidth();
                        
                        @Override
                        public void run() {
                            final Rect rect = new Rect();
                            VungleActivity.this.ctaOverlay.getHitRect(rect);
                            rect.top -= this.val$finalHeight / 2 * n;
                            rect.left -= this.val$finalWidth / 2 * n;
                            rect.bottom += this.val$finalHeight / 2 * n;
                            rect.right += this.val$finalWidth / 2 * n;
                            ViewTreeObserver$OnPreDrawListener.this.val$parent.setTouchDelegate(new TouchDelegate(rect, (View)VungleActivity.this.ctaOverlay));
                        }
                    });
                    return true;
                }
            });
        }
        this.ctaOverlay.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                VungleActivity.this.presenter.reportAction("cta", "");
                ((JavascriptBridge.ActionHandler)VungleActivity.this.presenter).handleAction("download");
            }
        });
    }
    
    public void showCloseButton() {
        this.closeButton.setVisibility(0);
    }
    
    public void showDialog(final String title, final String message, final String s, final String s2, final DialogInterface$OnClickListener dialogInterface$OnClickListener) {
        final AlertDialog$Builder alertDialog$Builder = new AlertDialog$Builder((Context)new ContextThemeWrapper((Context)this, this.getApplicationInfo().theme));
        if (!TextUtils.isEmpty((CharSequence)title)) {
            alertDialog$Builder.setTitle((CharSequence)title);
        }
        if (!TextUtils.isEmpty((CharSequence)message)) {
            alertDialog$Builder.setMessage((CharSequence)message);
        }
        alertDialog$Builder.setPositiveButton((CharSequence)s, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                if (dialogInterface$OnClickListener != null) {
                    dialogInterface$OnClickListener.onClick(dialogInterface, n);
                }
            }
        });
        alertDialog$Builder.setNegativeButton((CharSequence)s2, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
            public void onClick(final DialogInterface dialogInterface, final int n) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                if (dialogInterface$OnClickListener != null) {
                    dialogInterface$OnClickListener.onClick(dialogInterface, n);
                }
            }
        });
        alertDialog$Builder.setCancelable(false);
        this.dialog = alertDialog$Builder.create();
        if (this.videoView.isPlaying() && this.mediaPlayer != null) {
            this.videoView.pause();
        }
        else {
            this.pendingPause = true;
        }
        this.videoView.pause();
        this.videoPosition = this.videoView.getCurrentPosition();
        this.dialog.show();
    }
    
    public void showWebsite(final String s) {
        Log.d("VungleActivity", "loadJs: " + s);
        this.webView.loadUrl(s);
        this.webView.setVisibility(0);
        if (this.videoView != null) {
            this.videoView.stopPlayback();
            this.handler.removeCallbacks(this.reportProgress);
            this.mediaPlayer = null;
        }
        this.videoView.setVisibility(4);
        this.progressBar.setVisibility(8);
        this.closeButton.setVisibility(8);
        this.muteButton.setVisibility(8);
        this.ctaOverlay.setVisibility(8);
    }
    
    public void updateWindow(final boolean b) {
        if (b) {
            final DisplayMetrics displayMetrics = new DisplayMetrics();
            this.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            final int heightPixels = displayMetrics.heightPixels;
            final int widthPixels = displayMetrics.widthPixels;
            final int orientation = this.getResources().getConfiguration().orientation;
            Object layoutParams = null;
            if (orientation == 1) {
                this.getWindow().setGravity(83);
                this.getWindow().setLayout(widthPixels, (int)Math.round(widthPixels * 0.5625));
                layoutParams = new RelativeLayout$LayoutParams(widthPixels, (int)(widthPixels * 0.5625));
            }
            else if (orientation == 2) {
                this.getWindow().setLayout((int)Math.round(heightPixels * 0.5625), heightPixels);
                this.getWindow().setGravity(85);
                layoutParams = new RelativeLayout$LayoutParams((int)Math.round(heightPixels * 0.5625), heightPixels);
                ((RelativeLayout$LayoutParams)layoutParams).addRule(11, -1);
            }
            this.webView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            this.getWindow().addFlags(288);
            return;
        }
        this.getWindow().setFlags(1024, 1024);
        this.getWindow().getDecorView().setBackgroundColor(-16777216);
    }
}
