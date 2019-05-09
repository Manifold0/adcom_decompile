package com.tapjoy.mraid.util;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageButton;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;
import com.tapjoy.TapjoyLog;
import com.tapjoy.mraid.controller.Abstract.PlayerProperties;
import com.tapjoy.mraid.listener.Player;

public class MraidPlayer extends VideoView implements OnCompletionListener, OnErrorListener, OnPreparedListener {
    /* renamed from: h */
    private static String f8299h = "Loading. Please Wait..";
    /* renamed from: i */
    private static String f8300i = "MRAID Player";
    /* renamed from: a */
    private PlayerProperties f8301a;
    /* renamed from: b */
    private AudioManager f8302b = ((AudioManager) getContext().getSystemService("audio"));
    /* renamed from: c */
    private Player f8303c;
    /* renamed from: d */
    private int f8304d;
    /* renamed from: e */
    private String f8305e;
    /* renamed from: f */
    private RelativeLayout f8306f;
    /* renamed from: g */
    private ImageButton f8307g;
    /* renamed from: j */
    private boolean f8308j;

    public MraidPlayer(Context context) {
        super(context);
    }

    public void setPlayData(PlayerProperties properties, String url) {
        this.f8308j = false;
        this.f8301a = properties;
        this.f8305e = url;
    }

    public void playAudio() {
        m8241a();
    }

    public ImageButton getCloseImageButton() {
        return this.f8307g;
    }

    /* renamed from: a */
    private void m8241a() {
        this.f8305e = this.f8305e.trim();
        this.f8305e = Utils.convert(this.f8305e);
        if (this.f8305e != null || this.f8303c == null) {
            setVideoURI(Uri.parse(this.f8305e));
            TapjoyLog.m7126d("player", Uri.parse(this.f8305e).toString());
            if (this.f8301a.showControl()) {
                MediaController mediaController = new MediaController(getContext());
                setMediaController(mediaController);
                mediaController.setAnchorView(this);
            }
            setOnCompletionListener(this);
            setOnErrorListener(this);
            setOnPreparedListener(this);
            if (!(this.f8301a.inline || this.f8301a.inline)) {
                this.f8306f = new RelativeLayout(getContext());
                this.f8306f.setLayoutParams(getLayoutParams());
                View textView = new TextView(getContext());
                textView.setText(f8299h);
                textView.setTextColor(-1);
                LayoutParams layoutParams = new RelativeLayout.LayoutParams(-2, -2);
                layoutParams.addRule(13);
                this.f8306f.addView(textView, layoutParams);
                ((ViewGroup) getParent()).addView(this.f8306f);
            }
            if (this.f8301a.isAutoPlay()) {
                start();
                return;
            }
            return;
        }
        m8242b();
        this.f8303c.onError();
    }

    public void playVideo() {
        if (this.f8301a.doMute()) {
            this.f8304d = this.f8302b.getStreamVolume(3);
            this.f8302b.setStreamVolume(3, 0, 4);
        }
        m8241a();
    }

    public void setListener(Player listener) {
        this.f8303c = listener;
    }

    public void onCompletion(MediaPlayer mp) {
        if (this.f8301a.doLoop()) {
            start();
        } else if (this.f8301a.exitOnComplete() || this.f8301a.inline) {
            releasePlayer();
        }
    }

    public boolean onError(MediaPlayer mp, int what, int extra) {
        TapjoyLog.m7129i(f8300i, "Player error : " + what);
        m8243c();
        m8242b();
        if (this.f8303c != null) {
            this.f8303c.onError();
        }
        return false;
    }

    public void onPrepared(MediaPlayer mp) {
        m8243c();
        if (this.f8303c != null) {
            this.f8303c.onPrepared();
        }
    }

    /* renamed from: b */
    private void m8242b() {
        ViewGroup viewGroup = (ViewGroup) getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }

    public void releasePlayer() {
        if (!this.f8308j) {
            this.f8308j = true;
            stopPlayback();
            m8242b();
            if (this.f8301a != null && this.f8301a.doMute()) {
                this.f8302b.setStreamVolume(3, this.f8304d, 4);
            }
            if (this.f8303c != null) {
                this.f8303c.onComplete();
            }
        }
    }

    /* renamed from: c */
    private void m8243c() {
        if (this.f8306f != null) {
            ((ViewGroup) getParent()).removeView(this.f8306f);
        }
    }
}
