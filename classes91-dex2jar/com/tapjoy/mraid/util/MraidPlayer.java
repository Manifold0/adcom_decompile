// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy.mraid.util;

import android.media.MediaPlayer;
import android.view.ViewGroup;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import android.widget.TextView;
import android.view.View;
import android.widget.MediaController;
import com.tapjoy.TapjoyLog;
import android.net.Uri;
import android.content.Context;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import com.tapjoy.mraid.listener.Player;
import android.media.AudioManager;
import com.tapjoy.mraid.controller.Abstract;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.widget.VideoView;

public class MraidPlayer extends VideoView implements MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnPreparedListener
{
    private static String h;
    private static String i;
    private Abstract.PlayerProperties a;
    private AudioManager b;
    private Player c;
    private int d;
    private String e;
    private RelativeLayout f;
    private ImageButton g;
    private boolean j;
    
    static {
        MraidPlayer.h = "Loading. Please Wait..";
        MraidPlayer.i = "MRAID Player";
    }
    
    public MraidPlayer(final Context context) {
        super(context);
        this.b = (AudioManager)this.getContext().getSystemService("audio");
    }
    
    private void a() {
        this.e = this.e.trim();
        this.e = Utils.convert(this.e);
        if (this.e == null && this.c != null) {
            this.b();
            this.c.onError();
        }
        else {
            this.setVideoURI(Uri.parse(this.e));
            TapjoyLog.d("player", Uri.parse(this.e).toString());
            if (this.a.showControl()) {
                final MediaController mediaController = new MediaController(this.getContext());
                this.setMediaController(mediaController);
                mediaController.setAnchorView((View)this);
            }
            this.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            this.setOnErrorListener((MediaPlayer$OnErrorListener)this);
            this.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
            if (!this.a.inline && !this.a.inline) {
                (this.f = new RelativeLayout(this.getContext())).setLayoutParams(this.getLayoutParams());
                final TextView textView = new TextView(this.getContext());
                textView.setText((CharSequence)MraidPlayer.h);
                textView.setTextColor(-1);
                final RelativeLayout$LayoutParams relativeLayout$LayoutParams = new RelativeLayout$LayoutParams(-2, -2);
                relativeLayout$LayoutParams.addRule(13);
                this.f.addView((View)textView, (ViewGroup$LayoutParams)relativeLayout$LayoutParams);
                ((ViewGroup)this.getParent()).addView((View)this.f);
            }
            if (this.a.isAutoPlay()) {
                this.start();
            }
        }
    }
    
    private void b() {
        final ViewGroup viewGroup = (ViewGroup)this.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
    }
    
    private void c() {
        if (this.f != null) {
            ((ViewGroup)this.getParent()).removeView((View)this.f);
        }
    }
    
    public ImageButton getCloseImageButton() {
        return this.g;
    }
    
    public void onCompletion(final MediaPlayer mediaPlayer) {
        if (this.a.doLoop()) {
            this.start();
        }
        else if (this.a.exitOnComplete() || this.a.inline) {
            this.releasePlayer();
        }
    }
    
    public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        TapjoyLog.i(MraidPlayer.i, "Player error : " + n);
        this.c();
        this.b();
        if (this.c != null) {
            this.c.onError();
        }
        return false;
    }
    
    public void onPrepared(final MediaPlayer mediaPlayer) {
        this.c();
        if (this.c != null) {
            this.c.onPrepared();
        }
    }
    
    public void playAudio() {
        this.a();
    }
    
    public void playVideo() {
        if (this.a.doMute()) {
            this.d = this.b.getStreamVolume(3);
            this.b.setStreamVolume(3, 0, 4);
        }
        this.a();
    }
    
    public void releasePlayer() {
        if (!this.j) {
            this.j = true;
            this.stopPlayback();
            this.b();
            if (this.a != null && this.a.doMute()) {
                this.b.setStreamVolume(3, this.d, 4);
            }
            if (this.c != null) {
                this.c.onComplete();
            }
        }
    }
    
    public void setListener(final Player c) {
        this.c = c;
    }
    
    public void setPlayData(final Abstract.PlayerProperties a, final String e) {
        this.j = false;
        this.a = a;
        this.e = e;
    }
}
