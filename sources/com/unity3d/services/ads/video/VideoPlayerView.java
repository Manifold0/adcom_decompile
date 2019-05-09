package com.unity3d.services.ads.video;

import android.content.Context;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnInfoListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.os.Build.VERSION;
import android.widget.VideoView;
import com.tapjoy.TJAdUnitConstants;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.webview.WebViewApp;
import com.unity3d.services.core.webview.WebViewEventCategory;
import java.util.Timer;
import java.util.TimerTask;

public class VideoPlayerView extends VideoView {
    private boolean _infoListenerEnabled = true;
    private MediaPlayer _mediaPlayer = null;
    private Timer _prepareTimer;
    private int _progressEventInterval = TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL;
    private Timer _videoTimer;
    private String _videoUrl;
    private Float _volume = null;

    /* renamed from: com.unity3d.services.ads.video.VideoPlayerView$1 */
    class C15861 extends TimerTask {
        C15861() {
        }

        public void run() {
            boolean isPlaying = false;
            try {
                isPlaying = VideoPlayerView.this.isPlaying();
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PROGRESS, Integer.valueOf(VideoPlayerView.this.getCurrentPosition()));
            } catch (IllegalStateException e) {
                DeviceLog.exception("Exception while sending current position to webapp", e);
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.ILLEGAL_STATE, VideoPlayerEvent.PROGRESS, VideoPlayerView.this._videoUrl, Boolean.valueOf(isPlaying));
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.video.VideoPlayerView$2 */
    class C15872 extends TimerTask {
        C15872() {
        }

        public void run() {
            if (!VideoPlayerView.this.isPlaying()) {
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_TIMEOUT, VideoPlayerView.this._videoUrl);
                DeviceLog.error("Video player prepare timeout: " + VideoPlayerView.this._videoUrl);
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.video.VideoPlayerView$4 */
    class C15894 implements OnErrorListener {
        C15894() {
        }

        public boolean onError(MediaPlayer mp, int what, int extra) {
            VideoPlayerView.this.stopPrepareTimer();
            if (mp != null) {
                VideoPlayerView.this._mediaPlayer = mp;
            }
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.GENERIC_ERROR, VideoPlayerView.this._videoUrl, Integer.valueOf(what), Integer.valueOf(extra));
            VideoPlayerView.this.stopVideoProgressTimer();
            return true;
        }
    }

    /* renamed from: com.unity3d.services.ads.video.VideoPlayerView$5 */
    class C15905 implements OnCompletionListener {
        C15905() {
        }

        public void onCompletion(MediaPlayer mp) {
            if (mp != null) {
                VideoPlayerView.this._mediaPlayer = mp;
            }
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.COMPLETED, VideoPlayerView.this._videoUrl);
            VideoPlayerView.this.stopVideoProgressTimer();
        }
    }

    /* renamed from: com.unity3d.services.ads.video.VideoPlayerView$6 */
    class C15916 implements OnInfoListener {
        C15916() {
        }

        public boolean onInfo(MediaPlayer mp, int what, int extra) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, VideoPlayerView.this._videoUrl, Integer.valueOf(what), Integer.valueOf(extra));
            return true;
        }
    }

    public VideoPlayerView(Context context) {
        super(context);
    }

    private void startVideoProgressTimer() {
        this._videoTimer = new Timer();
        this._videoTimer.scheduleAtFixedRate(new C15861(), (long) this._progressEventInterval, (long) this._progressEventInterval);
    }

    public void stopVideoProgressTimer() {
        if (this._videoTimer != null) {
            this._videoTimer.cancel();
            this._videoTimer.purge();
            this._videoTimer = null;
        }
    }

    private void startPrepareTimer(long delay) {
        this._prepareTimer = new Timer();
        this._prepareTimer.schedule(new C15872(), delay);
    }

    public void stopPrepareTimer() {
        if (this._prepareTimer != null) {
            this._prepareTimer.cancel();
            this._prepareTimer.purge();
            this._prepareTimer = null;
        }
    }

    public boolean prepare(String url, final float initialVolume, int timeout) {
        DeviceLog.entered();
        this._videoUrl = url;
        setOnPreparedListener(new OnPreparedListener() {
            public void onPrepared(MediaPlayer mp) {
                VideoPlayerView.this.stopPrepareTimer();
                if (mp != null) {
                    VideoPlayerView.this._mediaPlayer = mp;
                }
                VideoPlayerView.this.setVolume(Float.valueOf(initialVolume));
                WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARED, VideoPlayerView.this._videoUrl, Integer.valueOf(mp.getDuration()), Integer.valueOf(mp.getVideoWidth()), Integer.valueOf(mp.getVideoHeight()));
            }
        });
        setOnErrorListener(new C15894());
        setInfoListenerEnabled(this._infoListenerEnabled);
        if (timeout > 0) {
            startPrepareTimer((long) timeout);
        }
        try {
            setVideoPath(this._videoUrl);
            return true;
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PREPARE_ERROR, this._videoUrl);
            DeviceLog.exception("Error preparing video: " + this._videoUrl, e);
            return false;
        }
    }

    public void play() {
        DeviceLog.entered();
        setOnCompletionListener(new C15905());
        start();
        stopVideoProgressTimer();
        startVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PLAY, this._videoUrl);
    }

    public void setInfoListenerEnabled(boolean enabled) {
        this._infoListenerEnabled = enabled;
        if (VERSION.SDK_INT <= 16) {
            return;
        }
        if (this._infoListenerEnabled) {
            setOnInfoListener(new C15916());
        } else {
            setOnInfoListener(null);
        }
    }

    public void pause() {
        try {
            super.pause();
            stopVideoProgressTimer();
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.PAUSE_ERROR, this._videoUrl);
            DeviceLog.exception("Error pausing video", e);
        }
    }

    public void seekTo(int msec) {
        try {
            super.seekTo(msec);
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO, this._videoUrl);
        } catch (Exception e) {
            WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.SEEKTO_ERROR, this._videoUrl);
            DeviceLog.exception("Error seeking video", e);
        }
    }

    public void stop() {
        stopPlayback();
        stopVideoProgressTimer();
        WebViewApp.getCurrentApp().sendEvent(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.STOP, this._videoUrl);
    }

    public float getVolume() {
        return this._volume.floatValue();
    }

    public void setVolume(Float volume) {
        try {
            this._mediaPlayer.setVolume(volume.floatValue(), volume.floatValue());
            this._volume = volume;
        } catch (Exception e) {
            DeviceLog.exception("MediaPlayer generic error", e);
        }
    }

    public void setProgressEventInterval(int ms) {
        this._progressEventInterval = ms;
        if (this._videoTimer != null) {
            stopVideoProgressTimer();
            startVideoProgressTimer();
        }
    }

    public int getProgressEventInterval() {
        return this._progressEventInterval;
    }
}
