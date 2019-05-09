// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.widget.TextView;
import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzbv;
import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.graphics.Bitmap$Config;
import java.util.concurrent.Executor;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import com.google.android.gms.common.internal.Asserts;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.content.Context;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.support.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import android.widget.FrameLayout;

@zzadh
@ParametersAreNonnullByDefault
public final class zzapi extends FrameLayout implements zzapf
{
    private final zzapw zzcxm;
    private final FrameLayout zzcxn;
    private final zznx zzcxo;
    private final zzapy zzcxp;
    private final long zzcxq;
    @Nullable
    private zzapg zzcxr;
    private boolean zzcxs;
    private boolean zzcxt;
    private boolean zzcxu;
    private boolean zzcxv;
    private long zzcxw;
    private long zzcxx;
    private String zzcxy;
    private Bitmap zzcxz;
    private ImageView zzcya;
    private boolean zzcyb;
    
    public zzapi(final Context context, final zzapw zzcxm, final int n, final boolean b, final zznx zzcxo, final zzapv zzapv) {
        super(context);
        this.zzcxm = zzcxm;
        this.zzcxo = zzcxo;
        this.addView((View)(this.zzcxn = new FrameLayout(context)), (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
        Asserts.checkNotNull((Object)zzcxm.zzbi());
        this.zzcxr = zzcxm.zzbi().zzwz.zza(context, zzcxm, n, b, zzcxo, zzapv);
        if (this.zzcxr != null) {
            this.zzcxn.addView((View)this.zzcxr, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1, 17));
            if (zzkb.zzik().zzd(zznk.zzavg)) {
                this.zztd();
            }
        }
        this.zzcya = new ImageView(context);
        this.zzcxq = (long)zzkb.zzik().zzd(zznk.zzavk);
        this.zzcxv = (boolean)zzkb.zzik().zzd(zznk.zzavi);
        if (this.zzcxo != null) {
            final zznx zzcxo2 = this.zzcxo;
            String s;
            if (this.zzcxv) {
                s = "1";
            }
            else {
                s = "0";
            }
            zzcxo2.zze("spinner_used", s);
        }
        this.zzcxp = new zzapy(this);
        if (this.zzcxr != null) {
            this.zzcxr.zza(this);
        }
        if (this.zzcxr == null) {
            this.zzg("AdVideoUnderlay Error", "Allocating player failed.");
        }
    }
    
    public static void zza(final zzapw zzapw) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "no_video_view");
        zzapw.zza("onVideoEvent", hashMap);
    }
    
    public static void zza(final zzapw zzapw, final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "decoderProps");
        hashMap.put("error", s);
        zzapw.zza("onVideoEvent", hashMap);
    }
    
    public static void zza(final zzapw zzapw, final Map<String, List<Map<String, Object>>> map) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", "decoderProps");
        hashMap.put("mimeTypes", (String)map);
        zzapw.zza("onVideoEvent", hashMap);
    }
    
    private final void zza(String s, final String... array) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("event", s);
        final int length = array.length;
        int i = 0;
        s = null;
        while (i < length) {
            final String s2 = array[i];
            if (s == null) {
                s = s2;
            }
            else {
                hashMap.put(s, s2);
                s = null;
            }
            ++i;
        }
        this.zzcxm.zza("onVideoEvent", hashMap);
    }
    
    private final boolean zztf() {
        return this.zzcya.getParent() != null;
    }
    
    private final void zztg() {
        if (this.zzcxm.zzto() != null && this.zzcxt && !this.zzcxu) {
            this.zzcxm.zzto().getWindow().clearFlags(128);
            this.zzcxt = false;
        }
    }
    
    public final void destroy() {
        this.zzcxp.pause();
        if (this.zzcxr != null) {
            this.zzcxr.stop();
        }
        this.zztg();
    }
    
    public final void finalize() throws Throwable {
        try {
            this.zzcxp.pause();
            if (this.zzcxr != null) {
                final zzapg zzcxr = this.zzcxr;
                final Executor zzcvy = zzaoe.zzcvy;
                zzcxr.getClass();
                zzcvy.execute(zzapj.zza(zzcxr));
            }
        }
        finally {
            super.finalize();
        }
    }
    
    public final void onPaused() {
        this.zza("pause", new String[0]);
        this.zztg();
        this.zzcxs = false;
    }
    
    public final void onWindowVisibilityChanged(final int n) {
        boolean b;
        if (n == 0) {
            this.zzcxp.resume();
            b = true;
        }
        else {
            this.zzcxp.pause();
            this.zzcxx = this.zzcxw;
            b = false;
        }
        zzakk.zzcrm.post((Runnable)new zzapm(this, b));
    }
    
    public final void pause() {
        if (this.zzcxr == null) {
            return;
        }
        this.zzcxr.pause();
    }
    
    public final void play() {
        if (this.zzcxr == null) {
            return;
        }
        this.zzcxr.play();
    }
    
    public final void seekTo(final int n) {
        if (this.zzcxr == null) {
            return;
        }
        this.zzcxr.seekTo(n);
    }
    
    public final void setVolume(final float volume) {
        if (this.zzcxr == null) {
            return;
        }
        final zzapg zzcxr = this.zzcxr;
        zzcxr.zzcxl.setVolume(volume);
        zzcxr.zzst();
    }
    
    public final void zza(final float n, final float n2) {
        if (this.zzcxr != null) {
            this.zzcxr.zza(n, n2);
        }
    }
    
    public final void zzd(final int n, final int n2, final int n3, final int n4) {
        if (n3 == 0 || n4 == 0) {
            return;
        }
        final FrameLayout$LayoutParams layoutParams = new FrameLayout$LayoutParams(n3, n4);
        layoutParams.setMargins(n, n2, 0, 0);
        this.zzcxn.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        this.requestLayout();
    }
    
    public final void zzdn(final String zzcxy) {
        this.zzcxy = zzcxy;
    }
    
    public final void zzf(int max, int max2) {
        if (this.zzcxv) {
            max = Math.max(max / (int)zzkb.zzik().zzd(zznk.zzavj), 1);
            max2 = Math.max(max2 / (int)zzkb.zzik().zzd(zznk.zzavj), 1);
            if (this.zzcxz == null || this.zzcxz.getWidth() != max || this.zzcxz.getHeight() != max2) {
                this.zzcxz = Bitmap.createBitmap(max, max2, Bitmap$Config.ARGB_8888);
                this.zzcyb = false;
            }
        }
    }
    
    @TargetApi(14)
    public final void zzf(final MotionEvent motionEvent) {
        if (this.zzcxr == null) {
            return;
        }
        this.zzcxr.dispatchTouchEvent(motionEvent);
    }
    
    public final void zzg(final String s, @Nullable final String s2) {
        this.zza("error", "what", s, "extra", s2);
    }
    
    public final void zzsu() {
        this.zzcxp.resume();
        zzakk.zzcrm.post((Runnable)new zzapk(this));
    }
    
    public final void zzsv() {
        if (this.zzcxr != null && this.zzcxx == 0L) {
            this.zza("canplaythrough", "duration", String.valueOf(this.zzcxr.getDuration() / 1000.0f), "videoWidth", String.valueOf(this.zzcxr.getVideoWidth()), "videoHeight", String.valueOf(this.zzcxr.getVideoHeight()));
        }
    }
    
    public final void zzsw() {
        if (this.zzcxm.zzto() != null && !this.zzcxt && !(this.zzcxu = ((this.zzcxm.zzto().getWindow().getAttributes().flags & 0x80) != 0x0))) {
            this.zzcxm.zzto().getWindow().addFlags(128);
            this.zzcxt = true;
        }
        this.zzcxs = true;
    }
    
    public final void zzsx() {
        this.zza("ended", new String[0]);
        this.zztg();
    }
    
    public final void zzsy() {
        if (this.zzcyb && this.zzcxz != null && !this.zztf()) {
            this.zzcya.setImageBitmap(this.zzcxz);
            this.zzcya.invalidate();
            this.zzcxn.addView((View)this.zzcya, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -1));
            this.zzcxn.bringChildToFront((View)this.zzcya);
        }
        this.zzcxp.pause();
        this.zzcxx = this.zzcxw;
        zzakk.zzcrm.post((Runnable)new zzapl(this));
    }
    
    public final void zzsz() {
        if (this.zzcxs && this.zztf()) {
            this.zzcxn.removeView((View)this.zzcya);
        }
        if (this.zzcxz != null) {
            final long elapsedRealtime = zzbv.zzer().elapsedRealtime();
            if (this.zzcxr.getBitmap(this.zzcxz) != null) {
                this.zzcyb = true;
            }
            final long n = zzbv.zzer().elapsedRealtime() - elapsedRealtime;
            if (zzakb.zzqp()) {
                zzakb.v(new StringBuilder(46).append("Spinner frame grab took ").append(n).append("ms").toString());
            }
            if (n > this.zzcxq) {
                zzakb.zzdk("Spinner frame grab crossed jank threshold! Suspending spinner.");
                this.zzcxv = false;
                this.zzcxz = null;
                if (this.zzcxo != null) {
                    this.zzcxo.zze("spinner_jank", Long.toString(n));
                }
            }
        }
    }
    
    public final void zzta() {
        if (this.zzcxr == null) {
            return;
        }
        if (!TextUtils.isEmpty((CharSequence)this.zzcxy)) {
            this.zzcxr.setVideoPath(this.zzcxy);
            return;
        }
        this.zza("no_src", new String[0]);
    }
    
    public final void zztb() {
        if (this.zzcxr == null) {
            return;
        }
        final zzapg zzcxr = this.zzcxr;
        zzcxr.zzcxl.setMuted(true);
        zzcxr.zzst();
    }
    
    public final void zztc() {
        if (this.zzcxr == null) {
            return;
        }
        final zzapg zzcxr = this.zzcxr;
        zzcxr.zzcxl.setMuted(false);
        zzcxr.zzst();
    }
    
    @TargetApi(14)
    public final void zztd() {
        if (this.zzcxr == null) {
            return;
        }
        final TextView textView = new TextView(this.zzcxr.getContext());
        final String value = String.valueOf(this.zzcxr.zzsp());
        String concat;
        if (value.length() != 0) {
            concat = "AdMob - ".concat(value);
        }
        else {
            concat = new String("AdMob - ");
        }
        textView.setText((CharSequence)concat);
        textView.setTextColor(-65536);
        textView.setBackgroundColor(-256);
        this.zzcxn.addView((View)textView, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 17));
        this.zzcxn.bringChildToFront((View)textView);
    }
    
    final void zzte() {
        if (this.zzcxr != null) {
            final long zzcxw = this.zzcxr.getCurrentPosition();
            if (this.zzcxw != zzcxw && zzcxw > 0L) {
                this.zza("timeupdate", "time", String.valueOf(zzcxw / 1000.0f));
                this.zzcxw = zzcxw;
            }
        }
    }
}
