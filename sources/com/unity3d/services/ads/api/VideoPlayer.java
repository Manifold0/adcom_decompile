package com.unity3d.services.ads.api;

import android.os.Build.VERSION;
import com.unity3d.services.ads.video.VideoPlayerError;
import com.unity3d.services.ads.video.VideoPlayerEvent;
import com.unity3d.services.ads.video.VideoPlayerView;
import com.unity3d.services.core.log.DeviceLog;
import com.unity3d.services.core.misc.Utilities;
import com.unity3d.services.core.webview.WebViewEventCategory;
import com.unity3d.services.core.webview.bridge.WebViewCallback;
import com.unity3d.services.core.webview.bridge.WebViewExposed;

public class VideoPlayer {
    private static VideoPlayerView _videoPlayerView;

    /* renamed from: com.unity3d.services.ads.api.VideoPlayer$3 */
    static class C15743 implements Runnable {
        C15743() {
        }

        public void run() {
            if (VideoPlayer.getVideoPlayerView() != null) {
                VideoPlayer.getVideoPlayerView().play();
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.api.VideoPlayer$4 */
    static class C15754 implements Runnable {
        C15754() {
        }

        public void run() {
            if (VideoPlayer.getVideoPlayerView() != null) {
                VideoPlayer.getVideoPlayerView().pause();
            }
        }
    }

    /* renamed from: com.unity3d.services.ads.api.VideoPlayer$5 */
    static class C15765 implements Runnable {
        C15765() {
        }

        public void run() {
            if (VideoPlayer.getVideoPlayerView() != null) {
                VideoPlayer.getVideoPlayerView().stop();
            }
        }
    }

    public static void setVideoPlayerView(VideoPlayerView videoPlayerView) {
        _videoPlayerView = videoPlayerView;
    }

    public static VideoPlayerView getVideoPlayerView() {
        return _videoPlayerView;
    }

    @WebViewExposed
    public static void setProgressEventInterval(final Integer milliseconds, WebViewCallback callback) {
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().setProgressEventInterval(milliseconds.intValue());
                }
            }
        });
        if (getVideoPlayerView() != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getProgressEventInterval(WebViewCallback callback) {
        if (getVideoPlayerView() != null) {
            callback.invoke(Integer.valueOf(getVideoPlayerView().getProgressEventInterval()));
            return;
        }
        callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void prepare(String url, Double initialVolume, WebViewCallback callback) {
        prepare(url, initialVolume, Integer.valueOf(0), callback);
    }

    @WebViewExposed
    public static void prepare(final String url, final Double initialVolume, final Integer timeout, WebViewCallback callback) {
        DeviceLog.debug("Preparing video for playback: " + url);
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().prepare(url, initialVolume.floatValue(), timeout.intValue());
                }
            }
        });
        if (getVideoPlayerView() != null) {
            callback.invoke(url);
            return;
        }
        callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void play(WebViewCallback callback) {
        DeviceLog.debug("Starting playback of prepared video");
        Utilities.runOnUiThread(new C15743());
        if (getVideoPlayerView() != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void pause(WebViewCallback callback) {
        DeviceLog.debug("Pausing current video");
        Utilities.runOnUiThread(new C15754());
        if (getVideoPlayerView() != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void stop(WebViewCallback callback) {
        DeviceLog.debug("Stopping current video");
        Utilities.runOnUiThread(new C15765());
        if (getVideoPlayerView() != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void seekTo(final Integer time, WebViewCallback callback) {
        DeviceLog.debug("Seeking video to time: " + time);
        Utilities.runOnUiThread(new Runnable() {
            public void run() {
                if (VideoPlayer.getVideoPlayerView() != null) {
                    VideoPlayer.getVideoPlayerView().seekTo(time.intValue());
                }
            }
        });
        if (getVideoPlayerView() != null) {
            callback.invoke(new Object[0]);
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }

    @WebViewExposed
    public static void getCurrentPosition(WebViewCallback callback) {
        if (getVideoPlayerView() != null) {
            callback.invoke(Integer.valueOf(getVideoPlayerView().getCurrentPosition()));
            return;
        }
        callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void getVolume(WebViewCallback callback) {
        if (getVideoPlayerView() != null) {
            callback.invoke(Float.valueOf(getVideoPlayerView().getVolume()));
            return;
        }
        callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setVolume(Double volume, WebViewCallback callback) {
        DeviceLog.debug("Setting video volume: " + volume);
        if (getVideoPlayerView() != null) {
            getVideoPlayerView().setVolume(Float.valueOf(volume.floatValue()));
            callback.invoke(volume);
            return;
        }
        callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
    }

    @WebViewExposed
    public static void setInfoListenerEnabled(boolean enabled, WebViewCallback callback) {
        if (VERSION.SDK_INT <= 16) {
            callback.error(VideoPlayerError.API_LEVEL_ERROR, Integer.valueOf(VERSION.SDK_INT), Boolean.valueOf(enabled));
        } else if (getVideoPlayerView() != null) {
            getVideoPlayerView().setInfoListenerEnabled(enabled);
            callback.invoke(WebViewEventCategory.VIDEOPLAYER, VideoPlayerEvent.INFO, Boolean.valueOf(enabled));
        } else {
            callback.error(VideoPlayerError.VIDEOVIEW_NULL, new Object[0]);
        }
    }
}
