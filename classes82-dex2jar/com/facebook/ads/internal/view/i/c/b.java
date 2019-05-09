// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view.i.c;

import com.facebook.ads.internal.o.f;
import com.facebook.ads.internal.view.i.a;
import android.os.Handler;
import android.os.Looper;
import com.facebook.ads.internal.view.i.b.k;
import com.facebook.ads.internal.view.i.b.i;
import android.media.AudioManager;
import android.content.Context;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.d;
import android.media.AudioManager$OnAudioFocusChangeListener;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.view.i.a.c;

public class b extends c
{
    private WeakReference<AudioManager$OnAudioFocusChangeListener> a;
    private final d b;
    private final j c;
    private final l d;
    
    public b(final Context context) {
        super(context);
        this.a = null;
        this.b = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                final AudioManager audioManager = (AudioManager)com.facebook.ads.internal.view.i.c.b.this.getContext().getApplicationContext().getSystemService("audio");
                AudioManager$OnAudioFocusChangeListener audioManager$OnAudioFocusChangeListener;
                if (com.facebook.ads.internal.view.i.c.b.this.a == null) {
                    audioManager$OnAudioFocusChangeListener = null;
                }
                else {
                    audioManager$OnAudioFocusChangeListener = (AudioManager$OnAudioFocusChangeListener)com.facebook.ads.internal.view.i.c.b.this.a.get();
                }
                audioManager.abandonAudioFocus(audioManager$OnAudioFocusChangeListener);
            }
        };
        this.c = new j() {
            @Override
            public void a(final i i) {
                final AudioManager audioManager = (AudioManager)com.facebook.ads.internal.view.i.c.b.this.getContext().getApplicationContext().getSystemService("audio");
                AudioManager$OnAudioFocusChangeListener audioManager$OnAudioFocusChangeListener;
                if (com.facebook.ads.internal.view.i.c.b.this.a == null) {
                    audioManager$OnAudioFocusChangeListener = null;
                }
                else {
                    audioManager$OnAudioFocusChangeListener = (AudioManager$OnAudioFocusChangeListener)com.facebook.ads.internal.view.i.c.b.this.a.get();
                }
                audioManager.abandonAudioFocus(audioManager$OnAudioFocusChangeListener);
            }
        };
        this.d = new l() {
            @Override
            public void a(final k k) {
                if (com.facebook.ads.internal.view.i.c.b.this.a == null || com.facebook.ads.internal.view.i.c.b.this.a.get() == null) {
                    com.facebook.ads.internal.view.i.c.b.this.a = (WeakReference<AudioManager$OnAudioFocusChangeListener>)new WeakReference((T)new AudioManager$OnAudioFocusChangeListener() {
                        public void onAudioFocusChange(final int n) {
                            new Handler(Looper.getMainLooper()).post((Runnable)new Runnable() {
                                @Override
                                public void run() {
                                    if (com.facebook.ads.internal.view.i.a.c.this.getVideoView() != null && n <= 0) {
                                        com.facebook.ads.internal.view.i.a.c.this.getVideoView().a(false);
                                    }
                                }
                            });
                        }
                    });
                }
                ((AudioManager)com.facebook.ads.internal.view.i.c.b.this.getContext().getApplicationContext().getSystemService("audio")).requestAudioFocus((AudioManager$OnAudioFocusChangeListener)com.facebook.ads.internal.view.i.c.b.this.a.get(), 3, 1);
            }
        };
    }
    
    @Override
    protected void a() {
        super.a();
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().a(this.d, this.b, this.c);
        }
    }
    
    @Override
    protected void b() {
        if (this.getVideoView() != null) {
            this.getVideoView().getEventBus().b(this.c, this.b, this.d);
        }
        super.b();
    }
    
    protected void onDetachedFromWindow() {
        final AudioManager audioManager = (AudioManager)this.getContext().getApplicationContext().getSystemService("audio");
        AudioManager$OnAudioFocusChangeListener audioManager$OnAudioFocusChangeListener;
        if (this.a == null) {
            audioManager$OnAudioFocusChangeListener = null;
        }
        else {
            audioManager$OnAudioFocusChangeListener = this.a.get();
        }
        audioManager.abandonAudioFocus(audioManager$OnAudioFocusChangeListener);
        super.onDetachedFromWindow();
    }
}
