// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.view;

import android.content.ActivityNotFoundException;
import com.facebook.ads.InterstitialAdActivity;
import java.io.Serializable;
import android.content.Intent;
import com.facebook.ads.AudienceNetworkActivity;
import com.facebook.ads.internal.o.f;
import android.util.Log;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.protocol.AdErrorType;
import android.annotation.TargetApi;
import android.util.AttributeSet;
import com.facebook.ads.internal.view.i.b.i;
import com.facebook.ads.internal.view.i.b.k;
import java.util.UUID;
import android.content.Context;
import com.facebook.ads.NativeAd;
import android.net.Uri;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.i.b;
import com.facebook.ads.internal.s.c;
import com.facebook.ads.internal.adapters.v;
import com.facebook.ads.internal.view.i.b.d;
import com.facebook.ads.internal.view.i.b.j;
import com.facebook.ads.internal.view.i.b.l;
import com.facebook.ads.internal.view.i.a;

public class p extends a
{
    private static final String b;
    private final String c;
    private final l d;
    private final j e;
    private final d f;
    private final v g;
    private com.facebook.ads.internal.s.c h;
    @Nullable
    private com.facebook.ads.internal.view.i.b i;
    @Nullable
    private Uri j;
    @Nullable
    private String k;
    @Nullable
    private String l;
    @Nullable
    private String m;
    @Nullable
    private q n;
    @Nullable
    private NativeAd o;
    
    static {
        b = p.class.getSimpleName();
    }
    
    public p(final Context context) {
        super(context);
        this.c = UUID.randomUUID().toString();
        this.d = new l() {
            @Override
            public void a(final k k) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.c();
            }
        };
        this.e = new j() {
            @Override
            public void a(final i i) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.b();
            }
        };
        this.f = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.h();
            }
        };
        this.g = new v(this, context);
        this.t();
    }
    
    public p(final Context context, final AttributeSet set) {
        super(context, set);
        this.c = UUID.randomUUID().toString();
        this.d = new l() {
            @Override
            public void a(final k k) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.c();
            }
        };
        this.e = new j() {
            @Override
            public void a(final i i) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.b();
            }
        };
        this.f = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.h();
            }
        };
        this.g = new v(this, context);
        this.t();
    }
    
    public p(final Context context, final AttributeSet set, final int n) {
        super(context, set, n);
        this.c = UUID.randomUUID().toString();
        this.d = new l() {
            @Override
            public void a(final k k) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.c();
            }
        };
        this.e = new j() {
            @Override
            public void a(final i i) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.b();
            }
        };
        this.f = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.h();
            }
        };
        this.g = new v(this, context);
        this.t();
    }
    
    @TargetApi(21)
    public p(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.c = UUID.randomUUID().toString();
        this.d = new l() {
            @Override
            public void a(final k k) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.c();
            }
        };
        this.e = new j() {
            @Override
            public void a(final i i) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.b();
            }
        };
        this.f = new d() {
            @Override
            public void a(final com.facebook.ads.internal.view.i.b.c c) {
                if (p.this.n == null) {
                    return;
                }
                p.this.n.h();
            }
        };
        this.g = new v(this, context);
        this.t();
    }
    
    private void a(final String s) {
        com.facebook.ads.internal.w.h.a.b(this.getContext(), "parsing", com.facebook.ads.internal.w.h.b.M, new com.facebook.ads.internal.protocol.b(AdErrorType.PARSER_FAILURE, s));
        if (AdInternalSettings.isDebugBuild()) {
            Log.w(p.b, s);
        }
    }
    
    private void t() {
        this.getEventBus().a(this.d, this.e, this.f);
    }
    
    public void a() {
        final Context context = this.getContext();
        final Intent intent = new Intent(context, AudienceNetworkActivity.getAdActivity());
        Label_0047: {
            if (this.i != null) {
                break Label_0047;
            }
            this.a("Must setClientToken first");
            try {
                // iftrue(Label_0200:, this.k != null)
                // iftrue(Label_0070:, this.j != null || this.l != null)
            Block_4_Outer:
                while (true) {
                    this.a(false);
                    this.setVisibility(8);
                    context.startActivity(intent);
                    return;
                    Label_0070: {
                        intent.putExtra("useNativeCtaButton", this.m);
                    }
                    intent.putExtra("viewType", (Serializable)com.facebook.ads.internal.settings.a.a.g);
                    intent.putExtra("videoURL", this.j.toString());
                    String k = null;
                    Label_0115: {
                        Block_5: {
                            break Block_5;
                            Label_0200:
                            k = this.k;
                            break Label_0115;
                            while (true) {
                                this.a("Must setVideoURI or setVideoMPD first");
                                continue Block_4_Outer;
                                continue;
                            }
                        }
                        k = "";
                    }
                    intent.putExtra("clientToken", k);
                    intent.putExtra("videoMPD", this.l);
                    intent.putExtra("predefinedOrientationKey", 13);
                    intent.putExtra("videoSeekTime", this.getCurrentPositionInMillis());
                    intent.putExtra("uniqueId", this.c);
                    intent.putExtra("videoLogger", this.i.g());
                    intent.putExtra("video_time_polling_interval", this.getVideoProgressReportIntervalMs());
                    intent.addFlags(268435456);
                    continue;
                }
            }
            catch (ActivityNotFoundException ex3) {
                try {
                    intent.setClass(context, (Class)InterstitialAdActivity.class);
                    context.startActivity(intent);
                }
                catch (Exception ex) {
                    com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(ex, "Error occurred while loading fullscreen video activity."));
                }
            }
            catch (Exception ex2) {
                com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(ex2, "Error occurred while loading fullscreen video activity."));
            }
        }
    }
    
    public void b() {
        if (this.o != null) {
            this.o.onCtaBroadcast();
        }
    }
    
    @Nullable
    public q getListener() {
        return this.n;
    }
    
    public String getUniqueId() {
        return this.c;
    }
    
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.g.a();
    }
    
    @Override
    protected void onDetachedFromWindow() {
        this.g.b();
        super.onDetachedFromWindow();
    }
    
    public void setAdEventManager(final com.facebook.ads.internal.s.c h) {
        this.h = h;
    }
    
    public void setClientToken(@Nullable final String k) {
        if (this.i != null) {
            this.i.a();
        }
        com.facebook.ads.internal.view.i.b i;
        if ((this.k = k) != null) {
            i = new com.facebook.ads.internal.view.i.b(this.getContext(), this.h, this, k);
        }
        else {
            i = null;
        }
        this.i = i;
    }
    
    public void setEnableBackgroundVideo(final boolean backgroundPlaybackEnabled) {
        this.a.setBackgroundPlaybackEnabled(backgroundPlaybackEnabled);
    }
    
    public void setListener(@Nullable final q n) {
        this.n = n;
    }
    
    public void setNativeAd(@Nullable final NativeAd o) {
        this.o = o;
    }
    
    public void setVideoCTA(@Nullable final String m) {
        this.m = m;
    }
    
    @Override
    public void setVideoMPD(@Nullable final String l) {
        if (l != null && this.i == null) {
            this.a("Must setClientToken first");
            return;
        }
        super.setVideoMPD(this.l = l);
    }
    
    @Override
    public void setVideoURI(@Nullable final Uri j) {
        if (j != null && this.i == null) {
            this.a("Must setClientToken first");
            return;
        }
        super.setVideoURI(this.j = j);
    }
}
