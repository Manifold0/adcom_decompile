// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.View$MeasureSpec;
import java.io.IOException;
import android.view.Surface;
import android.graphics.SurfaceTexture;
import com.google.android.gms.ads.internal.zzbv;
import android.content.Context;
import android.os.Build$VERSION;
import java.util.HashMap;
import android.net.Uri;
import android.media.MediaPlayer;
import java.util.Map;
import android.annotation.TargetApi;
import android.view.TextureView$SurfaceTextureListener;
import android.media.MediaPlayer$OnVideoSizeChangedListener;
import android.media.MediaPlayer$OnPreparedListener;
import android.media.MediaPlayer$OnInfoListener;
import android.media.MediaPlayer$OnErrorListener;
import android.media.MediaPlayer$OnCompletionListener;
import android.media.MediaPlayer$OnBufferingUpdateListener;

@zzadh
@TargetApi(14)
public final class zzaov extends zzapg implements MediaPlayer$OnBufferingUpdateListener, MediaPlayer$OnCompletionListener, MediaPlayer$OnErrorListener, MediaPlayer$OnInfoListener, MediaPlayer$OnPreparedListener, MediaPlayer$OnVideoSizeChangedListener, TextureView$SurfaceTextureListener
{
    private static final Map<Integer, String> zzcwo;
    private final zzapx zzcwp;
    private final boolean zzcwq;
    private int zzcwr;
    private int zzcws;
    private MediaPlayer zzcwt;
    private Uri zzcwu;
    private int zzcwv;
    private int zzcww;
    private int zzcwx;
    private int zzcwy;
    private int zzcwz;
    private zzapu zzcxa;
    private boolean zzcxb;
    private int zzcxc;
    private zzapf zzcxd;
    
    static {
        zzcwo = new HashMap<Integer, String>();
        if (Build$VERSION.SDK_INT >= 17) {
            zzaov.zzcwo.put(-1004, "MEDIA_ERROR_IO");
            zzaov.zzcwo.put(-1007, "MEDIA_ERROR_MALFORMED");
            zzaov.zzcwo.put(-1010, "MEDIA_ERROR_UNSUPPORTED");
            zzaov.zzcwo.put(-110, "MEDIA_ERROR_TIMED_OUT");
            zzaov.zzcwo.put(3, "MEDIA_INFO_VIDEO_RENDERING_START");
        }
        zzaov.zzcwo.put(100, "MEDIA_ERROR_SERVER_DIED");
        zzaov.zzcwo.put(1, "MEDIA_ERROR_UNKNOWN");
        zzaov.zzcwo.put(1, "MEDIA_INFO_UNKNOWN");
        zzaov.zzcwo.put(700, "MEDIA_INFO_VIDEO_TRACK_LAGGING");
        zzaov.zzcwo.put(701, "MEDIA_INFO_BUFFERING_START");
        zzaov.zzcwo.put(702, "MEDIA_INFO_BUFFERING_END");
        zzaov.zzcwo.put(800, "MEDIA_INFO_BAD_INTERLEAVING");
        zzaov.zzcwo.put(801, "MEDIA_INFO_NOT_SEEKABLE");
        zzaov.zzcwo.put(802, "MEDIA_INFO_METADATA_UPDATE");
        if (Build$VERSION.SDK_INT >= 19) {
            zzaov.zzcwo.put(901, "MEDIA_INFO_UNSUPPORTED_SUBTITLE");
            zzaov.zzcwo.put(902, "MEDIA_INFO_SUBTITLE_TIMED_OUT");
        }
    }
    
    public zzaov(final Context context, final boolean zzcxb, final boolean zzcwq, final zzapv zzapv, final zzapx zzcwp) {
        super(context);
        this.zzcwr = 0;
        this.zzcws = 0;
        this.setSurfaceTextureListener((TextureView$SurfaceTextureListener)this);
        this.zzcwp = zzcwp;
        this.zzcxb = zzcxb;
        this.zzcwq = zzcwq;
        this.zzcwp.zzb(this);
    }
    
    private final void zza(final float n) {
        Label_0017: {
            if (this.zzcwt == null) {
                break Label_0017;
            }
            try {
                this.zzcwt.setVolume(n, n);
                return;
                zzakb.zzdk("AdMediaPlayerView setMediaPlayerVolume() called before onPrepared().");
            }
            catch (IllegalStateException ex) {}
        }
    }
    
    private final void zzag(final int zzcwr) {
        if (zzcwr == 3) {
            this.zzcwp.zztt();
            this.zzcxl.zztt();
        }
        else if (this.zzcwr == 3) {
            this.zzcwp.zztu();
            this.zzcxl.zztu();
        }
        this.zzcwr = zzcwr;
    }
    
    private final void zzag(final boolean b) {
        zzakb.v("AdMediaPlayerView release");
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
            this.zzcxa = null;
        }
        if (this.zzcwt != null) {
            this.zzcwt.reset();
            this.zzcwt.release();
            this.zzcwt = null;
            this.zzag(0);
            if (b) {
                this.zzcws = 0;
                this.zzcws = 0;
            }
        }
    }
    
    private final void zzsq() {
        zzakb.v("AdMediaPlayerView init MediaPlayer");
        Object surfaceTexture = this.getSurfaceTexture();
        if (this.zzcwu == null || surfaceTexture == null) {
            return;
        }
        this.zzag(false);
        try {
            zzbv.zzfb();
            (this.zzcwt = new MediaPlayer()).setOnBufferingUpdateListener((MediaPlayer$OnBufferingUpdateListener)this);
            this.zzcwt.setOnCompletionListener((MediaPlayer$OnCompletionListener)this);
            this.zzcwt.setOnErrorListener((MediaPlayer$OnErrorListener)this);
            this.zzcwt.setOnInfoListener((MediaPlayer$OnInfoListener)this);
            this.zzcwt.setOnPreparedListener((MediaPlayer$OnPreparedListener)this);
            this.zzcwt.setOnVideoSizeChangedListener((MediaPlayer$OnVideoSizeChangedListener)this);
            this.zzcwx = 0;
            if (!this.zzcxb) {
                goto Label_0286;
            }
            (this.zzcxa = new zzapu(this.getContext())).zza((SurfaceTexture)surfaceTexture, this.getWidth(), this.getHeight());
            this.zzcxa.start();
            final SurfaceTexture zztj = this.zzcxa.zztj();
            if (zztj != null) {
                surfaceTexture = zztj;
                this.zzcwt.setDataSource(this.getContext(), this.zzcwu);
                zzbv.zzfc();
                surfaceTexture = new Surface((SurfaceTexture)surfaceTexture);
                this.zzcwt.setSurface((Surface)surfaceTexture);
                this.zzcwt.setAudioStreamType(3);
                this.zzcwt.setScreenOnWhilePlaying(true);
                this.zzcwt.prepareAsync();
                this.zzag(1);
                return;
            }
            goto Label_0274;
        }
        catch (IOException ex) {}
        catch (IllegalStateException surfaceTexture) {
            goto Label_0220;
        }
        catch (IllegalArgumentException surfaceTexture) {
            goto Label_0220;
        }
    }
    
    private final void zzsr() {
        if (this.zzcwq && this.zzss() && this.zzcwt.getCurrentPosition() > 0 && this.zzcws != 3) {
            zzakb.v("AdMediaPlayerView nudging MediaPlayer");
            this.zza(0.0f);
            this.zzcwt.start();
            final int currentPosition = this.zzcwt.getCurrentPosition();
            final long currentTimeMillis = zzbv.zzer().currentTimeMillis();
            while (this.zzss() && this.zzcwt.getCurrentPosition() == currentPosition && zzbv.zzer().currentTimeMillis() - currentTimeMillis <= 250L) {}
            this.zzcwt.pause();
            this.zzst();
        }
    }
    
    private final boolean zzss() {
        return this.zzcwt != null && this.zzcwr != -1 && this.zzcwr != 0 && this.zzcwr != 1;
    }
    
    @Override
    public final int getCurrentPosition() {
        if (this.zzss()) {
            return this.zzcwt.getCurrentPosition();
        }
        return 0;
    }
    
    @Override
    public final int getDuration() {
        if (this.zzss()) {
            return this.zzcwt.getDuration();
        }
        return -1;
    }
    
    @Override
    public final int getVideoHeight() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoHeight();
        }
        return 0;
    }
    
    @Override
    public final int getVideoWidth() {
        if (this.zzcwt != null) {
            return this.zzcwt.getVideoWidth();
        }
        return 0;
    }
    
    public final void onBufferingUpdate(final MediaPlayer mediaPlayer, final int zzcwx) {
        this.zzcwx = zzcwx;
    }
    
    public final void onCompletion(final MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView completion");
        this.zzag(5);
        this.zzcws = 5;
        zzakk.zzcrm.post((Runnable)new zzaoy(this));
    }
    
    public final boolean onError(final MediaPlayer mediaPlayer, final int n, final int n2) {
        final String s = zzaov.zzcwo.get(n);
        final String s2 = zzaov.zzcwo.get(n2);
        zzakb.zzdk(new StringBuilder(String.valueOf(s).length() + 38 + String.valueOf(s2).length()).append("AdMediaPlayerView MediaPlayer error: ").append(s).append(":").append(s2).toString());
        this.zzag(-1);
        this.zzcws = -1;
        zzakk.zzcrm.post((Runnable)new zzaoz(this, s, s2));
        return true;
    }
    
    public final boolean onInfo(final MediaPlayer mediaPlayer, final int n, final int n2) {
        final String s = zzaov.zzcwo.get(n);
        final String s2 = zzaov.zzcwo.get(n2);
        zzakb.v(new StringBuilder(String.valueOf(s).length() + 37 + String.valueOf(s2).length()).append("AdMediaPlayerView MediaPlayer info: ").append(s).append(":").append(s2).toString());
        return true;
    }
    
    protected final void onMeasure(int size, int size2) {
        final int defaultSize = getDefaultSize(this.zzcwv, size);
        int defaultSize2;
        final int n = defaultSize2 = getDefaultSize(this.zzcww, size2);
        int zzcwy = defaultSize;
        if (this.zzcwv > 0) {
            defaultSize2 = n;
            zzcwy = defaultSize;
            if (this.zzcww > 0) {
                defaultSize2 = n;
                zzcwy = defaultSize;
                if (this.zzcxa == null) {
                    final int mode = View$MeasureSpec.getMode(size);
                    size = View$MeasureSpec.getSize(size);
                    final int mode2 = View$MeasureSpec.getMode(size2);
                    size2 = View$MeasureSpec.getSize(size2);
                    if (mode == 1073741824 && mode2 == 1073741824) {
                        if (this.zzcwv * size2 < this.zzcww * size) {
                            zzcwy = this.zzcwv * size2 / this.zzcww;
                            defaultSize2 = size2;
                        }
                        else if (this.zzcwv * size2 > this.zzcww * size) {
                            defaultSize2 = this.zzcww * size / this.zzcwv;
                            zzcwy = size;
                        }
                        else {
                            defaultSize2 = size2;
                            zzcwy = size;
                        }
                    }
                    else if (mode == 1073741824) {
                        defaultSize2 = this.zzcww * size / this.zzcwv;
                        if (mode2 == Integer.MIN_VALUE && defaultSize2 > size2) {
                            defaultSize2 = size2;
                            zzcwy = size;
                        }
                        else {
                            zzcwy = size;
                        }
                    }
                    else if (mode2 == 1073741824) {
                        final int n2 = this.zzcwv * size2 / this.zzcww;
                        defaultSize2 = size2;
                        zzcwy = n2;
                        if (mode == Integer.MIN_VALUE) {
                            defaultSize2 = size2;
                            if ((zzcwy = n2) > size) {
                                defaultSize2 = size2;
                                zzcwy = size;
                            }
                        }
                    }
                    else {
                        int zzcwv = this.zzcwv;
                        final int zzcww = this.zzcww;
                        if (mode2 == Integer.MIN_VALUE && zzcww > size2) {
                            zzcwv = this.zzcwv * size2 / this.zzcww;
                        }
                        else {
                            size2 = zzcww;
                        }
                        defaultSize2 = size2;
                        zzcwy = zzcwv;
                        if (mode == Integer.MIN_VALUE) {
                            defaultSize2 = size2;
                            if ((zzcwy = zzcwv) > size) {
                                defaultSize2 = this.zzcww * size / this.zzcwv;
                                zzcwy = size;
                            }
                        }
                    }
                }
            }
        }
        this.setMeasuredDimension(zzcwy, defaultSize2);
        if (this.zzcxa != null) {
            this.zzcxa.zzh(zzcwy, defaultSize2);
        }
        if (Build$VERSION.SDK_INT == 16) {
            if ((this.zzcwy > 0 && this.zzcwy != zzcwy) || (this.zzcwz > 0 && this.zzcwz != defaultSize2)) {
                this.zzsr();
            }
            this.zzcwy = zzcwy;
            this.zzcwz = defaultSize2;
        }
    }
    
    public final void onPrepared(final MediaPlayer mediaPlayer) {
        zzakb.v("AdMediaPlayerView prepared");
        this.zzag(2);
        this.zzcwp.zzsv();
        zzakk.zzcrm.post((Runnable)new zzaox(this));
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcxc != 0) {
            this.seekTo(this.zzcxc);
        }
        this.zzsr();
        zzakb.zzdj(new StringBuilder(62).append("AdMediaPlayerView stream dimensions: ").append(this.zzcwv).append(" x ").append(this.zzcww).toString());
        if (this.zzcws == 3) {
            this.play();
        }
        this.zzst();
    }
    
    public final void onSurfaceTextureAvailable(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        zzakb.v("AdMediaPlayerView surface created");
        this.zzsq();
        zzakk.zzcrm.post((Runnable)new zzapa(this));
    }
    
    public final boolean onSurfaceTextureDestroyed(final SurfaceTexture surfaceTexture) {
        zzakb.v("AdMediaPlayerView surface destroyed");
        if (this.zzcwt != null && this.zzcxc == 0) {
            this.zzcxc = this.zzcwt.getCurrentPosition();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzti();
        }
        zzakk.zzcrm.post((Runnable)new zzapc(this));
        this.zzag(true);
        return true;
    }
    
    public final void onSurfaceTextureSizeChanged(final SurfaceTexture surfaceTexture, final int n, final int n2) {
        boolean b = true;
        zzakb.v("AdMediaPlayerView surface changed");
        boolean b2;
        if (this.zzcws == 3) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        if (this.zzcwv != n || this.zzcww != n2) {
            b = false;
        }
        if (this.zzcwt != null && b2 && b) {
            if (this.zzcxc != 0) {
                this.seekTo(this.zzcxc);
            }
            this.play();
        }
        if (this.zzcxa != null) {
            this.zzcxa.zzh(n, n2);
        }
        zzakk.zzcrm.post((Runnable)new zzapb(this, n, n2));
    }
    
    public final void onSurfaceTextureUpdated(final SurfaceTexture surfaceTexture) {
        this.zzcwp.zzc(this);
        this.zzcxk.zza(surfaceTexture, this.zzcxd);
    }
    
    public final void onVideoSizeChanged(final MediaPlayer mediaPlayer, final int n, final int n2) {
        zzakb.v(new StringBuilder(57).append("AdMediaPlayerView size changed: ").append(n).append(" x ").append(n2).toString());
        this.zzcwv = mediaPlayer.getVideoWidth();
        this.zzcww = mediaPlayer.getVideoHeight();
        if (this.zzcwv != 0 && this.zzcww != 0) {
            this.requestLayout();
        }
    }
    
    protected final void onWindowVisibilityChanged(final int n) {
        zzakb.v(new StringBuilder(58).append("AdMediaPlayerView window visibility changed to ").append(n).toString());
        zzakk.zzcrm.post((Runnable)new zzaow(this, n));
        super.onWindowVisibilityChanged(n);
    }
    
    @Override
    public final void pause() {
        zzakb.v("AdMediaPlayerView pause");
        if (this.zzss() && this.zzcwt.isPlaying()) {
            this.zzcwt.pause();
            this.zzag(4);
            zzakk.zzcrm.post((Runnable)new zzape(this));
        }
        this.zzcws = 4;
    }
    
    @Override
    public final void play() {
        zzakb.v("AdMediaPlayerView play");
        if (this.zzss()) {
            this.zzcwt.start();
            this.zzag(3);
            this.zzcxk.zzsw();
            zzakk.zzcrm.post((Runnable)new zzapd(this));
        }
        this.zzcws = 3;
    }
    
    @Override
    public final void seekTo(final int zzcxc) {
        zzakb.v(new StringBuilder(34).append("AdMediaPlayerView seek ").append(zzcxc).toString());
        if (this.zzss()) {
            this.zzcwt.seekTo(zzcxc);
            this.zzcxc = 0;
            return;
        }
        this.zzcxc = zzcxc;
    }
    
    @Override
    public final void setVideoPath(final String s) {
        Uri zzcwu = Uri.parse(s);
        final zzhl zzd = zzhl.zzd(zzcwu);
        if (zzd != null) {
            zzcwu = Uri.parse(zzd.url);
        }
        this.zzcwu = zzcwu;
        this.zzcxc = 0;
        this.zzsq();
        this.requestLayout();
        this.invalidate();
    }
    
    @Override
    public final void stop() {
        zzakb.v("AdMediaPlayerView stop");
        if (this.zzcwt != null) {
            this.zzcwt.stop();
            this.zzcwt.release();
            this.zzcwt = null;
            this.zzag(0);
            this.zzcws = 0;
        }
        this.zzcwp.onStop();
    }
    
    public final String toString() {
        final String name = this.getClass().getName();
        final String hexString = Integer.toHexString(this.hashCode());
        return new StringBuilder(String.valueOf(name).length() + 1 + String.valueOf(hexString).length()).append(name).append("@").append(hexString).toString();
    }
    
    @Override
    public final void zza(final float n, final float n2) {
        if (this.zzcxa != null) {
            this.zzcxa.zzb(n, n2);
        }
    }
    
    @Override
    public final void zza(final zzapf zzcxd) {
        this.zzcxd = zzcxd;
    }
    
    @Override
    public final String zzsp() {
        String s;
        if (this.zzcxb) {
            s = " spherical";
        }
        else {
            s = "";
        }
        final String value = String.valueOf(s);
        if (value.length() != 0) {
            return "MediaPlayer".concat(value);
        }
        return new String("MediaPlayer");
    }
    
    @Override
    public final void zzst() {
        this.zza(this.zzcxl.getVolume());
    }
}
