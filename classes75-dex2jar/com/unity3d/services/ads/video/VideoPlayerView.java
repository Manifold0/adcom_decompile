// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.video;

import android.media.MediaPlayer$OnInfoListener;
import android.os.Build$VERSION;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnCompletionListener;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.WebViewApp;
import java.util.TimerTask;
import android.content.Context;
import java.util.Timer;
import android.media.MediaPlayer;
import android.widget.VideoView;

public class VideoPlayerView extends VideoView
{
    private boolean _infoListenerEnabled;
    private MediaPlayer _mediaPlayer;
    private Timer _prepareTimer;
    private int _progressEventInterval;
    private Timer _videoTimer;
    private String _videoUrl;
    private Float _volume;
    
    public VideoPlayerView(final Context context) {
        super(context);
        this._progressEventInterval = 500;
        this._mediaPlayer = null;
        this._volume = null;
        this._infoListenerEnabled = true;
    }
    
    private void startPrepareTimer(final long n) {
        (this._prepareTimer = new Timer()).schedule(new TimerTask() {
            @Override
            public void run() {
                if (!VideoPlayerView.this.isPlaying()) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_TIMEOUT, VideoPlayerView.this._videoUrl);
                    DeviceLog.error("Video player prepare timeout: " + VideoPlayerView.this._videoUrl);
                }
            }
        }, n);
    }
    
    private void startVideoProgressTimer() {
        (this._videoTimer = new Timer()).scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                boolean playing = false;
                try {
                    playing = VideoPlayerView.this.isPlaying();
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PROGRESS, VideoPlayerView.this.getCurrentPosition());
                }
                catch (IllegalStateException ex) {
                    DeviceLog.exception("Exception while sending current position to webapp", ex);
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.ILLEGAL_STATE, VideoPlayerEvent.PROGRESS, VideoPlayerView.this._videoUrl, playing);
                }
            }
        }, this._progressEventInterval, this._progressEventInterval);
    }
    
    public int getProgressEventInterval() {
        return this._progressEventInterval;
    }
    
    public float getVolume() {
        return this._volume;
    }
    
    public void pause() {
        try {
            super.pause();
            this.stopVideoProgressTimer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE, this._videoUrl);
        }
        catch (Exception ex) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE_ERROR, this._videoUrl);
            DeviceLog.exception("Error pausing video", ex);
        }
    }
    
    public void play() {
        DeviceLog.entered();
        this.setOnCompletionListener((MediaPlayer$OnCompletionListener)new MediaPlayer$OnCompletionListener() {
            public void onCompletion(final MediaPlayer mediaPlayer) {
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.COMPLETED, VideoPlayerView.this._videoUrl);
                VideoPlayerView.this.stopVideoProgressTimer();
            }
        });
        this.start();
        this.stopVideoProgressTimer();
        this.startVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PLAY, this._videoUrl);
    }
    
    public boolean prepare(final String videoUrl, final float n, final int n2) {
        DeviceLog.entered();
        this._videoUrl = videoUrl;
        this.setOnPreparedListener((MediaPlayer$OnPreparedListener)new MediaPlayer$OnPreparedListener() {
            public void onPrepared(final MediaPlayer mediaPlayer) {
                VideoPlayerView.this.stopPrepareTimer();
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                VideoPlayerView.this.setVolume(n);
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARED, VideoPlayerView.this._videoUrl, mediaPlayer.getDuration(), mediaPlayer.getVideoWidth(), mediaPlayer.getVideoHeight());
            }
        });
        this.setOnErrorListener((MediaPlayer$OnErrorListener)new MediaPlayer$OnErrorListener() {
            public boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
                VideoPlayerView.this.stopPrepareTimer();
                if (mediaPlayer != null) {
                    VideoPlayerView.this._mediaPlayer = mediaPlayer;
                }
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.GENERIC_ERROR, VideoPlayerView.this._videoUrl, n, n2);
                VideoPlayerView.this.stopVideoProgressTimer();
                return true;
            }
        });
        this.setInfoListenerEnabled(this._infoListenerEnabled);
        if (n2 > 0) {
            this.startPrepareTimer(n2);
        }
        try {
            this.setVideoPath(this._videoUrl);
            return true;
        }
        catch (Exception ex) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_ERROR, this._videoUrl);
            DeviceLog.exception("Error preparing video: " + this._videoUrl, ex);
            return false;
        }
    }
    
    public void seekTo(final int n) {
        try {
            super.seekTo(n);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO, this._videoUrl);
        }
        catch (Exception ex) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO_ERROR, this._videoUrl);
            DeviceLog.exception("Error seeking video", ex);
        }
    }
    
    public void setInfoListenerEnabled(final boolean infoListenerEnabled) {
        this._infoListenerEnabled = infoListenerEnabled;
        if (Build$VERSION.SDK_INT > 16) {
            if (!this._infoListenerEnabled) {
                this.setOnInfoListener((MediaPlayer$OnInfoListener)null);
                return;
            }
            this.setOnInfoListener((MediaPlayer$OnInfoListener)new MediaPlayer$OnInfoListener() {
                public boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
                    WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, VideoPlayerView.this._videoUrl, n, n2);
                    return true;
                }
            });
        }
    }
    
    public void setProgressEventInterval(final int progressEventInterval) {
        this._progressEventInterval = progressEventInterval;
        if (this._videoTimer != null) {
            this.stopVideoProgressTimer();
            this.startVideoProgressTimer();
        }
    }
    
    public void setVolume(final Float volume) {
        try {
            this._mediaPlayer.setVolume((float)volume, (float)volume);
            this._volume = volume;
        }
        catch (Exception ex) {
            DeviceLog.exception("MediaPlayer generic error", ex);
        }
    }
    
    public void stop() {
        this.stopPlayback();
        this.stopVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.STOP, this._videoUrl);
    }
    
    public void stopPrepareTimer() {
        if (this._prepareTimer != null) {
            this._prepareTimer.cancel();
            this._prepareTimer.purge();
            this._prepareTimer = null;
        }
    }
    
    public void stopVideoProgressTimer() {
        if (this._videoTimer != null) {
            this._videoTimer.cancel();
            this._videoTimer.purge();
            this._videoTimer = null;
        }
    }
}
