package com.vungle.warren.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.TouchDelegate;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewTreeObserver.OnPreDrawListener;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.VideoView;
import com.google.android.gms.drive.DriveFile;
import com.ironsource.sdk.constants.Constants;
import com.moat.analytics.mobile.vng.ReactiveVideoTracker;
import com.tapjoy.TJAdUnitConstants.String;
import com.tonyodev.fetch.FetchConst;
import com.unity.purchasing.googleplay.IabHelper;
import com.vungle.warren.AdvertisementPresenterFactory;
import com.vungle.warren.Vungle;
import com.vungle.warren.error.VungleException;
import com.vungle.warren.presenter.AdvertisementPresenter;
import com.vungle.warren.presenter.AdvertisementPresenter.EventListener;
import com.vungle.warren.utility.ViewUtility;
import com.vungle.warren.utility.ViewUtility.Asset;
import java.util.Locale;

public class VungleActivity extends Activity implements AdView, OnPreparedListener, OnErrorListener {
    protected static final double NINE_BY_SIXTEEN_ASPECT_RATIO = 0.5625d;
    public static final String PLACEMENT_EXTRA = "placement";
    private static final String TAG = "VungleActivity";
    private static EventListener bus;
    private BroadcastReceiver broadcastReciever;
    private ImageView closeButton;
    private ImageView ctaOverlay;
    private AlertDialog dialog;
    private Handler handler = new Handler();
    private MediaPlayer mediaPlayer;
    private ImageView muteButton;
    private boolean muted = false;
    private boolean pendingPause;
    private String placementId;
    private AdvertisementPresenter presenter;
    private AdvertisementPresenterFactory presenterFactory;
    private ImageView privacyOverlay;
    private ProgressBar progressBar;
    private boolean released = false;
    private Runnable reportProgress;
    private int videoPosition = 0;
    private VideoView videoView;
    private ReactiveVideoTracker viewabilityTracker;
    private WebView webView;

    /* renamed from: com.vungle.warren.ui.VungleActivity$1 */
    class C01571 extends BroadcastReceiver {
        C01571() {
        }

        public void onReceive(Context context, Intent intent) {
            String command = intent.getStringExtra(String.COMMAND);
            Object obj = -1;
            switch (command.hashCode()) {
                case -482896367:
                    if (command.equals("closeFlex")) {
                        obj = null;
                        break;
                    }
                    break;
            }
            switch (obj) {
                case null:
                    if (VungleActivity.this.presenter.handleExit(intent.getStringExtra(VungleActivity.PLACEMENT_EXTRA))) {
                        VungleActivity.this.close();
                        return;
                    }
                    return;
                default:
                    throw new IllegalArgumentException("No such command " + command);
            }
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$2 */
    class C01592 implements OnCompletionListener {

        /* renamed from: com.vungle.warren.ui.VungleActivity$2$1 */
        class C01581 implements Runnable {
            C01581() {
            }

            public void run() {
                VungleActivity.this.muteButton.setEnabled(false);
                VungleActivity.this.presenter.stopViewabilityTracker();
            }
        }

        C01592() {
        }

        public void onCompletion(MediaPlayer mp) {
            Log.d(VungleActivity.TAG, "mediaplayer onCompletion");
            if (VungleActivity.this.reportProgress != null) {
                VungleActivity.this.handler.removeCallbacks(VungleActivity.this.reportProgress);
            }
            VungleActivity.this.presenter.onProgressUpdate(100);
            VungleActivity.this.runOnUiThread(new C01581());
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$3 */
    class C01603 implements OnErrorListener {
        C01603() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            switch (what) {
            }
            return true;
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$4 */
    class C01614 implements OnClickListener {
        C01614() {
        }

        public void onClick(View v) {
            VungleActivity.this.startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://vungle.com/privacy/")));
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$6 */
    class C01636 implements OnClickListener {
        C01636() {
        }

        public void onClick(View view) {
            VungleActivity.this.onBackPressed();
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$7 */
    class C01647 implements Runnable {
        C01647() {
        }

        public void run() {
            if (VungleActivity.this.videoView != null && VungleActivity.this.videoView.isPlaying()) {
                VungleActivity.this.presenter.stopViewabilityTracker();
            }
            VungleActivity.this.webView.removeJavascriptInterface(Constants.JAVASCRIPT_INTERFACE_NAME);
            VungleActivity.this.webView.loadUrl("about:blank");
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$8 */
    class C01658 implements OnClickListener {
        C01658() {
        }

        public void onClick(View v) {
            VungleActivity.this.ctaOverlay.setVisibility(0);
            VungleActivity.this.ctaOverlay.setImageBitmap(ViewUtility.getBitmap(Asset.cta, VungleActivity.this));
            VungleActivity.this.ctaOverlay.setEnabled(true);
        }
    }

    /* renamed from: com.vungle.warren.ui.VungleActivity$9 */
    class C01669 implements OnClickListener {
        C01669() {
        }

        public void onClick(View v) {
            VungleActivity.this.presenter.reportAction("cta", "");
            VungleActivity.this.presenter.handleAction("download");
        }
    }

    public static void setEventListener(EventListener listener) {
        bus = listener;
    }

    @SuppressLint({"SetJavaScriptEnabled"})
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(1);
        getWindow().setFlags(16777216, 16777216);
        Resources resources = getResources();
        RelativeLayout parentLayout = new RelativeLayout(this);
        LayoutParams matchParentLayoutParams = new LayoutParams(-1, -1);
        parentLayout.setLayoutParams(matchParentLayoutParams);
        this.videoView = new VideoView(this);
        ViewGroup.LayoutParams videoViewLayoutParams = new LayoutParams(-1, -1);
        videoViewLayoutParams.addRule(13);
        this.videoView.setLayoutParams(videoViewLayoutParams);
        parentLayout.addView(this.videoView, videoViewLayoutParams);
        this.webView = new WebView(this);
        this.webView.setLayoutParams(matchParentLayoutParams);
        parentLayout.addView(this.webView, matchParentLayoutParams);
        this.progressBar = new ProgressBar(this, null, 16842872);
        LayoutParams progressBarLayoutParams = new LayoutParams(-1, (int) TypedValue.applyDimension(1, 4.0f, resources.getDisplayMetrics()));
        progressBarLayoutParams.addRule(12);
        this.progressBar.setLayoutParams(progressBarLayoutParams);
        this.progressBar.setMax(100);
        this.progressBar.setIndeterminate(false);
        this.progressBar.setVisibility(4);
        parentLayout.addView(this.progressBar);
        int imageViewDimension = (int) TypedValue.applyDimension(1, 24.0f, resources.getDisplayMetrics());
        LayoutParams imageViewLayoutParams = new LayoutParams(imageViewDimension, imageViewDimension);
        int imageViewMargin = (int) TypedValue.applyDimension(1, 12.0f, resources.getDisplayMetrics());
        imageViewLayoutParams.setMargins(imageViewMargin, imageViewMargin, imageViewMargin, imageViewMargin);
        this.muteButton = new ImageView(this);
        this.muteButton.setImageBitmap(ViewUtility.getBitmap(Asset.unMute, this));
        this.muteButton.setLayoutParams(imageViewLayoutParams);
        this.muteButton.setVisibility(8);
        parentLayout.addView(this.muteButton);
        LayoutParams closeLayoutParams = new LayoutParams(imageViewDimension, imageViewDimension);
        closeLayoutParams.setMargins(imageViewMargin, imageViewMargin, imageViewMargin, imageViewMargin);
        this.closeButton = new ImageView(this);
        this.closeButton.setImageBitmap(ViewUtility.getBitmap(Asset.close, this));
        closeLayoutParams.addRule(11);
        this.closeButton.setLayoutParams(closeLayoutParams);
        this.closeButton.setVisibility(8);
        parentLayout.addView(this.closeButton);
        LayoutParams ctaOverlayLayoutParams = new LayoutParams(imageViewDimension, imageViewDimension);
        ctaOverlayLayoutParams.addRule(12);
        ctaOverlayLayoutParams.addRule(11);
        ctaOverlayLayoutParams.setMargins(imageViewMargin, imageViewMargin, imageViewMargin, imageViewMargin);
        this.ctaOverlay = new ImageView(this);
        this.ctaOverlay.setLayoutParams(ctaOverlayLayoutParams);
        this.ctaOverlay.setVisibility(8);
        parentLayout.addView(this.ctaOverlay);
        LayoutParams privacyLayoutParams = new LayoutParams(imageViewDimension, imageViewDimension);
        privacyLayoutParams.addRule(12);
        privacyLayoutParams.addRule(9);
        privacyLayoutParams.setMargins(imageViewMargin, imageViewMargin, imageViewMargin, imageViewMargin);
        this.privacyOverlay = new ImageView(this);
        this.privacyOverlay.setLayoutParams(privacyLayoutParams);
        this.privacyOverlay.setVisibility(8);
        parentLayout.addView(this.privacyOverlay);
        setContentView(parentLayout, matchParentLayoutParams);
        if (!Vungle.isInitialized() || bus == null) {
            finish();
            return;
        }
        String user = getIntent().hasExtra("userID") ? getIntent().getStringExtra("userID") : null;
        this.placementId = getIntent().getStringExtra(PLACEMENT_EXTRA);
        this.presenterFactory = new AdvertisementPresenterFactory();
        this.presenter = this.presenterFactory.getAdPresenter(this.placementId, savedInstanceState, user);
        if (this.presenter == null) {
            if (bus != null) {
                bus.onError(new VungleException(10));
            }
            finish();
            return;
        }
        this.presenter.setEventListener(bus);
        this.presenter.attach(this);
        this.presenter.prepare(savedInstanceState);
        prepare(savedInstanceState);
    }

    private void prepare(Bundle savedInstanceState) {
        this.videoView.setOnPreparedListener(this);
        this.webView.setWebViewClient(this.presenter.getWebViewClient());
        this.webView.getSettings().setJavaScriptEnabled(true);
        this.webView.addJavascriptInterface(new JavascriptBridge(this.presenter), Constants.JAVASCRIPT_INTERFACE_NAME);
        if (VERSION.SDK_INT >= 17 && VERSION.SDK_INT <= 19) {
            this.webView.getSettings().setMediaPlaybackRequiresUserGesture(false);
        }
        this.videoView.setVisibility(8);
        this.webView.setVisibility(8);
    }

    private void connectBroadcastReceiver() {
        this.broadcastReciever = new C01571();
        registerReceiver(this.broadcastReciever, new IntentFilter("AdvertisementBus"));
    }

    protected void onStart() {
        connectBroadcastReceiver();
        super.onStart();
    }

    protected void onRestart() {
        super.onRestart();
        if (this.videoView != null) {
            this.videoView.seekTo(this.videoPosition);
        }
        setupPlayerProgressBar();
    }

    protected void onStop() {
        if (this.reportProgress != null) {
            this.handler.removeCallbacks(this.reportProgress);
        }
        unregisterReceiver(this.broadcastReciever);
        super.onStop();
    }

    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            resumeAd();
        } else {
            pauseAd();
        }
    }

    private void pauseAd() {
        if (this.webView != null) {
            this.webView.onPause();
        }
        if (!this.released && this.videoView.isPlaying()) {
            this.videoView.pause();
            this.videoPosition = this.videoView.getCurrentPosition();
        }
        this.presenter.stop(isChangingConfigurations(), isFinishing());
    }

    private void resumeAd() {
        setImmersiveMode();
        if (this.webView != null) {
            this.webView.onResume();
        }
        if (this.dialog != null && this.dialog.isShowing()) {
            return;
        }
        if (this.videoView.isPlaying() || this.mediaPlayer == null) {
            this.presenter.play();
            return;
        }
        this.videoView.requestFocus();
        this.videoView.seekTo(this.videoPosition);
        this.videoView.start();
    }

    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == 2) {
            Log.d(TAG, "landscape");
        } else if (newConfig.orientation == 1) {
            Log.d(TAG, "portrait");
        }
        this.presenter.notifyPropertiesChanged();
    }

    @SuppressLint({"ResourceType"})
    public void onBackPressed() {
        if (this.presenter.handleExit(null)) {
            close();
        }
    }

    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        if (savedInstanceState != null) {
            this.videoPosition = savedInstanceState.getInt("videoPosition");
        }
    }

    public void onPrepared(MediaPlayer mp) {
        this.mediaPlayer = mp;
        this.muteButton.setVisibility(0);
        if (this.muted) {
            mp.setVolume(0.0f, 0.0f);
        }
        mp.seekTo(this.videoPosition);
        mp.start();
        this.presenter.initializeViewabilityTracker(mp.getDuration(), this.videoView);
        mp.setOnCompletionListener(new C01592());
        mp.setOnErrorListener(new C01603());
        this.presenter.reportAction(String.VIDEO_LENGTH, String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(mp.getDuration())}));
        setupPlayerProgressBar();
        if (this.pendingPause || (this.dialog != null && this.dialog.isShowing())) {
            this.pendingPause = false;
            this.videoView.pause();
        }
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState");
        this.presenter.generateSaveState(outState);
        this.presenterFactory.saveState(outState);
        if (this.videoView != null) {
            outState.putInt("videoPosition", this.videoPosition);
        }
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState(" + savedInstanceState + ")");
        this.presenter.restoreFromSave(savedInstanceState);
        if (savedInstanceState != null) {
            this.videoPosition = savedInstanceState.getInt("videoPosition", 0);
        }
    }

    public void updateWindow(boolean isFlexView) {
        if (isFlexView) {
            DisplayMetrics metrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(metrics);
            int heightPixels = metrics.heightPixels;
            int widthPixels = metrics.widthPixels;
            int orientation = getResources().getConfiguration().orientation;
            LayoutParams params = null;
            if (orientation == 1) {
                getWindow().setGravity(83);
                getWindow().setLayout(widthPixels, (int) Math.round(((double) widthPixels) * NINE_BY_SIXTEEN_ASPECT_RATIO));
                params = new LayoutParams(widthPixels, (int) (((double) widthPixels) * NINE_BY_SIXTEEN_ASPECT_RATIO));
            } else if (orientation == 2) {
                getWindow().setLayout((int) Math.round(((double) heightPixels) * NINE_BY_SIXTEEN_ASPECT_RATIO), heightPixels);
                getWindow().setGravity(85);
                params = new LayoutParams((int) Math.round(((double) heightPixels) * NINE_BY_SIXTEEN_ASPECT_RATIO), heightPixels);
                params.addRule(11, -1);
            }
            this.webView.setLayoutParams(params);
            getWindow().addFlags(288);
            return;
        }
        getWindow().setFlags(1024, 1024);
        getWindow().getDecorView().setBackgroundColor(ViewCompat.MEASURED_STATE_MASK);
    }

    public void setVisibility(boolean isVisible) {
    }

    public boolean onError(MediaPlayer mediaPlayer, int what, int extra) {
        StringBuilder description = new StringBuilder();
        switch (what) {
            case 1:
                description.append("MEDIA_ERROR_UNKNOWN");
                break;
            case 100:
                description.append("MEDIA_ERROR_SERVER_DIED");
                break;
            default:
                description.append("UNKNOWN");
                break;
        }
        description.append(":");
        switch (extra) {
            case IabHelper.IABHELPER_INVALID_CONSUMPTION /*-1010*/:
                description.append("MEDIA_ERROR_UNSUPPORTED");
                break;
            case IabHelper.IABHELPER_MISSING_TOKEN /*-1007*/:
                description.append("MEDIA_ERROR_MALFORMED");
                break;
            case IabHelper.IABHELPER_SEND_INTENT_FAILED /*-1004*/:
                description.append("MEDIA_ERROR_IO");
                break;
            case FetchConst.ERROR_SERVER_ERROR /*-110*/:
                description.append("MEDIA_ERROR_TIMED_OUT");
                break;
            case 200:
                description.append("MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK");
                break;
            default:
                description.append("MEDIA_ERROR_SYSTEM");
                break;
        }
        this.presenter.reportError(description.toString());
        return true;
    }

    public void setOrientation(int orientation) {
        Log.d(TAG, " requested orientation " + orientation);
        if (canRotate()) {
            setRequestedOrientation(orientation);
        }
    }

    protected boolean canRotate() {
        return true;
    }

    public void playVideo(Uri uri, boolean startMuted) {
        Bitmap bitmap;
        this.videoView.setVisibility(0);
        this.videoView.setOnErrorListener(this);
        this.videoView.setVideoURI(uri);
        this.muted = startMuted;
        if (this.muted) {
            this.presenter.reportAction("mute", "true");
        }
        final Bitmap muteBitmap = ViewUtility.getBitmap(Asset.mute, this);
        final Bitmap unMuteBitmap = ViewUtility.getBitmap(Asset.unMute, this);
        this.privacyOverlay.setImageBitmap(ViewUtility.getBitmap(Asset.privacy, this));
        this.privacyOverlay.setVisibility(0);
        this.privacyOverlay.setOnClickListener(new C01614());
        ImageView imageView = this.muteButton;
        if (this.muted) {
            bitmap = muteBitmap;
        } else {
            bitmap = unMuteBitmap;
        }
        imageView.setImageBitmap(bitmap);
        final AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.muteButton.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                if (VungleActivity.this.mediaPlayer != null) {
                    if (VungleActivity.this.muted) {
                        float currentVolume = ((float) audioManager.getStreamVolume(3)) / ((float) audioManager.getStreamMaxVolume(3));
                        VungleActivity.this.mediaPlayer.setVolume(currentVolume, currentVolume);
                        VungleActivity.this.muted = false;
                        VungleActivity.this.presenter.reportAction("unmute", "false");
                    } else {
                        VungleActivity.this.mediaPlayer.setVolume(0.0f, 0.0f);
                        VungleActivity.this.muted = true;
                        VungleActivity.this.presenter.reportAction("mute", "true");
                    }
                    VungleActivity.this.muteButton.setImageBitmap(VungleActivity.this.muted ? muteBitmap : unMuteBitmap);
                }
            }
        });
        this.progressBar.setVisibility(0);
        this.progressBar.setMax(this.videoView.getDuration());
        this.closeButton.setOnClickListener(new C01636());
    }

    public void showWebsite(String url) {
        Log.d(TAG, "loadJs: " + url);
        this.webView.loadUrl(url);
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

    public String getWebsiteUrl() {
        return this.webView.getUrl();
    }

    public void close() {
        runOnUiThread(new C01647());
        finish();
    }

    public void showCloseButton() {
        this.closeButton.setVisibility(0);
    }

    public void showCTAOverlay(boolean clickEnabled, boolean enabled, final int size) {
        if (clickEnabled) {
            this.videoView.setOnClickListener(new C01658());
        } else {
            this.ctaOverlay.setVisibility(0);
            this.ctaOverlay.setImageBitmap(ViewUtility.getBitmap(enabled ? Asset.cta : Asset.ctaDisabled, this));
            this.ctaOverlay.setEnabled(enabled);
        }
        if (size == 100) {
            this.videoView.setOnClickListener(new C01669());
        } else {
            final View parent = (View) this.ctaOverlay.getParent();
            this.ctaOverlay.getViewTreeObserver().addOnPreDrawListener(new OnPreDrawListener() {
                public boolean onPreDraw() {
                    VungleActivity.this.ctaOverlay.getViewTreeObserver().removeOnPreDrawListener(this);
                    final int finalHeight = VungleActivity.this.ctaOverlay.getMeasuredHeight();
                    final int finalWidth = VungleActivity.this.ctaOverlay.getMeasuredWidth();
                    parent.post(new Runnable() {
                        public void run() {
                            Rect rect = new Rect();
                            VungleActivity.this.ctaOverlay.getHitRect(rect);
                            rect.top -= (finalHeight / 2) * size;
                            rect.left -= (finalWidth / 2) * size;
                            rect.bottom += (finalHeight / 2) * size;
                            rect.right += (finalWidth / 2) * size;
                            parent.setTouchDelegate(new TouchDelegate(rect, VungleActivity.this.ctaOverlay));
                        }
                    });
                    return true;
                }
            });
        }
        this.ctaOverlay.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                VungleActivity.this.presenter.reportAction("cta", "");
                VungleActivity.this.presenter.handleAction("download");
            }
        });
    }

    public void open(String url) {
        Log.v(TAG, "Opening " + url);
        try {
            Intent i = Intent.parseUri(url, 0);
            i.setFlags(DriveFile.MODE_READ_ONLY);
            startActivity(i);
        } catch (Exception e) {
            Log.e(TAG, "Unable to start activity " + e.getLocalizedMessage());
        }
    }

    public void showDialog(String dialogTitle, String dialogBody, String dialogContinue, String dialogClose, final DialogInterface.OnClickListener responseListener) {
        Builder dialogBuilder = new Builder(new ContextThemeWrapper(this, getApplicationInfo().theme));
        if (!TextUtils.isEmpty(dialogTitle)) {
            dialogBuilder.setTitle(dialogTitle);
        }
        if (!TextUtils.isEmpty(dialogBody)) {
            dialogBuilder.setMessage(dialogBody);
        }
        dialogBuilder.setPositiveButton(dialogContinue, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                if (responseListener != null) {
                    responseListener.onClick(dialogInterface, i);
                }
            }
        });
        dialogBuilder.setNegativeButton(dialogClose, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                VungleActivity.this.pendingPause = false;
                VungleActivity.this.dialog.dismiss();
                if (responseListener != null) {
                    responseListener.onClick(dialogInterface, i);
                }
            }
        });
        dialogBuilder.setCancelable(false);
        this.dialog = dialogBuilder.create();
        if (!this.videoView.isPlaying() || this.mediaPlayer == null) {
            this.pendingPause = true;
        } else {
            this.videoView.pause();
        }
        this.videoView.pause();
        this.videoPosition = this.videoView.getCurrentPosition();
        this.dialog.show();
    }

    private void setupPlayerProgressBar() {
        this.reportProgress = new Runnable() {
            float duration = -2.0f;

            public void run() {
                if (VungleActivity.this.mediaPlayer != null) {
                    try {
                        int position = VungleActivity.this.mediaPlayer.getCurrentPosition();
                        if (this.duration == -2.0f) {
                            this.duration = (float) VungleActivity.this.mediaPlayer.getDuration();
                        }
                        VungleActivity.this.presenter.reportAction("video_viewed", String.format(Locale.ENGLISH, "%d", new Object[]{Integer.valueOf(position)}));
                        VungleActivity.this.presenter.onProgressUpdate((int) ((((float) position) / this.duration) * 100.0f));
                        VungleActivity.this.progressBar.setMax((int) this.duration);
                        VungleActivity.this.progressBar.setProgress(position);
                        VungleActivity.this.handler.postDelayed(this, 1000);
                    } catch (IllegalStateException e) {
                        Log.v(VungleActivity.TAG, "IllegalStateException while reporting progress indicates activity was killed via SIGKILL.");
                    }
                }
            }
        };
        this.handler.post(this.reportProgress);
    }

    private void setImmersiveMode() {
        getWindow().getDecorView().setSystemUiVisibility(5894);
    }

    protected void onDestroy() {
        if (this.presenter != null) {
            this.presenter.stop(isChangingConfigurations(), true);
        }
        this.handler.removeCallbacksAndMessages(null);
        if (this.videoView != null && this.videoView.isPlaying()) {
            try {
                this.videoView.stopPlayback();
            } catch (Throwable t) {
                Log.w(TAG, "error on stopping player " + t);
            }
        }
        super.onDestroy();
    }
}
