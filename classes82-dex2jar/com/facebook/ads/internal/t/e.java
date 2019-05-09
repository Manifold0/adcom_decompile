// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.t;

import android.view.MotionEvent;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import java.util.HashMap;
import java.util.Map;
import com.facebook.ads.internal.adapters.AdAdapter;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.adapters.q;
import android.graphics.drawable.Drawable;
import java.util.Collection;
import com.facebook.ads.internal.view.aa;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.facebook.ads.internal.protocol.AdErrorType;
import java.util.Iterator;
import android.view.View$OnLongClickListener;
import android.view.View$OnClickListener;
import android.net.Uri;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.UUID;
import com.facebook.ads.internal.view.ab;
import com.facebook.ads.internal.w.b.w;
import com.facebook.ads.internal.x.a;
import android.view.View$OnTouchListener;
import java.util.List;
import com.facebook.ads.NativeAdLayout;
import com.facebook.ads.internal.b.g;
import android.content.Context;
import com.facebook.ads.internal.adapters.i;
import com.facebook.ads.internal.adapters.p;
import android.support.annotation.Nullable;
import com.facebook.ads.internal.view.c.c;
import com.facebook.ads.internal.h.b;
import java.lang.ref.WeakReference;
import android.view.View;
import java.util.WeakHashMap;

public class e
{
    private static final String b;
    private static WeakHashMap<View, WeakReference<e>> c;
    private static com.facebook.ads.internal.h.b h;
    private k A;
    private boolean B;
    private boolean C;
    private boolean D;
    private boolean E;
    @Nullable
    private com.facebook.ads.internal.view.c.c F;
    private d G;
    private p.a H;
    private String I;
    private View J;
    @Nullable
    protected i a;
    private final Context d;
    private final String e;
    private final String f;
    private final com.facebook.ads.internal.h.b g;
    @Nullable
    private h i;
    private final c j;
    private g k;
    private volatile boolean l;
    @Nullable
    private com.facebook.ads.internal.m.d m;
    private com.facebook.ads.internal.protocol.e n;
    @Nullable
    private View o;
    @Nullable
    private NativeAdLayout p;
    @Nullable
    private f q;
    private final List<View> r;
    private View$OnTouchListener s;
    private com.facebook.ads.internal.x.a t;
    private com.facebook.ads.internal.x.a.a u;
    private WeakReference<com.facebook.ads.internal.x.a.a> v;
    private final w w;
    @Nullable
    private p x;
    private a y;
    private ab z;
    
    static {
        b = e.class.getSimpleName();
        e.c = new WeakHashMap<View, WeakReference<e>>();
    }
    
    public e(final Context context, final i a, final com.facebook.ads.internal.m.d m, final c c) {
        this(context, null, c);
        this.a = a;
        this.m = m;
        this.l = true;
        this.J = new View(context);
    }
    
    public e(final Context d, final String e, final c j) {
        this.f = UUID.randomUUID().toString();
        this.n = com.facebook.ads.internal.protocol.e.j;
        this.r = new ArrayList<View>();
        this.w = new w();
        this.C = false;
        this.D = false;
        this.G = com.facebook.ads.internal.t.d.b;
        this.H = com.facebook.ads.internal.adapters.p.a.a;
        this.d = d;
        this.e = e;
        this.j = j;
        if (com.facebook.ads.internal.t.e.h != null) {
            this.g = com.facebook.ads.internal.t.e.h;
        }
        else {
            this.g = new com.facebook.ads.internal.h.b(d);
        }
        this.J = new View(d);
    }
    
    public e(final e e) {
        this(e.d, null, e.j);
        this.m = e.m;
        this.a = e.a;
        this.l = true;
        this.J = new View(this.d);
    }
    
    private boolean A() {
        return this.a != null && this.a.B();
    }
    
    private void B() {
        if (!TextUtils.isEmpty((CharSequence)this.p())) {
            com.facebook.ads.internal.w.e.g.a(new com.facebook.ads.internal.w.e.g(), this.d, Uri.parse(this.p()), this.v());
        }
    }
    
    private void C() {
        for (final View view : this.r) {
            view.setOnClickListener((View$OnClickListener)null);
            view.setOnTouchListener((View$OnTouchListener)null);
            view.setOnLongClickListener((View$OnLongClickListener)null);
        }
        this.r.clear();
    }
    
    private void a(@Nullable final i i, final boolean b) {
        if (i == null) {
            return;
        }
        if (this.G.equals(com.facebook.ads.internal.t.d.b)) {
            if (i.l() != null) {
                this.g.a(i.l().a(), i.l().c(), i.l().b());
            }
            if (!this.n.equals(com.facebook.ads.internal.protocol.e.k)) {
                if (i.m() != null) {
                    this.g.a(i.m().a(), i.m().c(), i.m().b());
                }
                if (i.x() != null) {
                    for (final e e : i.x()) {
                        if (e.j() != null) {
                            this.g.a(e.j().a(), e.j().c(), e.j().b());
                        }
                    }
                }
                if (!TextUtils.isEmpty((CharSequence)i.t())) {
                    this.g.a(i.t());
                }
            }
        }
        this.g.a(new com.facebook.ads.internal.h.a() {
            @Override
            public void a() {
                com.facebook.ads.internal.t.e.this.a = i;
                if (com.facebook.ads.internal.t.e.this.i != null) {
                    if (com.facebook.ads.internal.t.e.this.G.equals(com.facebook.ads.internal.t.d.b) && !com.facebook.ads.internal.t.e.this.A()) {
                        com.facebook.ads.internal.t.e.this.i.a();
                    }
                    if (b) {
                        com.facebook.ads.internal.t.e.this.i.b();
                    }
                }
            }
            
            @Override
            public void b() {
                if (com.facebook.ads.internal.t.e.this.a != null) {
                    com.facebook.ads.internal.t.e.this.a.c();
                    com.facebook.ads.internal.t.e.this.a = null;
                }
                if (com.facebook.ads.internal.t.e.this.i != null) {
                    com.facebook.ads.internal.t.e.this.i.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.CACHE_FAILURE_ERROR, "Failed to download a media."));
                }
            }
        });
    }
    
    public static void a(final com.facebook.ads.internal.t.g g, final ImageView imageView) {
        if (g != null && imageView != null) {
            new com.facebook.ads.internal.view.c.d(imageView).a(g.c(), g.b()).a(g.a());
        }
    }
    
    private void a(final List<View> list, final View view) {
        if (this.j == null || !this.j.a(view)) {
            if (!(view instanceof ViewGroup)) {
                list.add(view);
                return;
            }
            final ViewGroup viewGroup = (ViewGroup)view;
            for (int i = 0; i < viewGroup.getChildCount(); ++i) {
                this.a(list, viewGroup.getChildAt(i));
            }
        }
    }
    
    private void b(final View o, final f q, final List<View> list) {
        final int n = 0;
        if (o == null) {
            throw new IllegalArgumentException("Must provide a View");
        }
        if (list == null || list.size() == 0) {
            throw new IllegalArgumentException("Invalid set of clickable views");
        }
        if (!this.f()) {
            Log.e(com.facebook.ads.internal.t.e.b, "Ad not loaded");
        }
        else {
            if (this.p != null) {
                this.p.clearAdReportingLayout();
            }
            if (q == null) {
                if (this.n == com.facebook.ads.internal.protocol.e.j) {
                    if (this.i != null) {
                        this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_MEDIAVIEW_IN_NATIVEAD, "MediaView is missing."));
                    }
                    if (AdInternalSettings.isDebugBuild()) {
                        Log.e(com.facebook.ads.internal.t.e.b, "MediaView is missing.");
                    }
                }
                else {
                    if (this.i != null) {
                        this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.NO_ICONVIEW_IN_NATIVEBANNERAD, "AdIconView is missing."));
                    }
                    if (AdInternalSettings.isDebugBuild()) {
                        Log.e(com.facebook.ads.internal.t.e.b, "AdIconView is missing.");
                    }
                }
            }
            else if (q.getAdContentsView() == null) {
                if (this.i != null) {
                    this.i.a(new com.facebook.ads.internal.protocol.a(AdErrorType.UNSUPPORTED_AD_ASSET_NATIVEAD, "ad media type is not supported."));
                }
            }
            else {
                if (this.o != null) {
                    Log.w(com.facebook.ads.internal.t.e.b, "Native Ad was already registered with a View. Auto unregistering and proceeding.");
                    this.z();
                }
                if (com.facebook.ads.internal.t.e.c.containsKey(o) && com.facebook.ads.internal.t.e.c.get(o).get() != null) {
                    Log.w(com.facebook.ads.internal.t.e.b, "View already registered with a NativeAd. Auto unregistering and proceeding.");
                    com.facebook.ads.internal.t.e.c.get(o).get().z();
                }
                this.y = new a();
                this.o = o;
                this.q = q;
                if (o instanceof ViewGroup) {
                    this.z = new ab(o.getContext(), new aa() {
                        @Override
                        public void a(final int n) {
                            if (com.facebook.ads.internal.t.e.this.a != null) {
                                com.facebook.ads.internal.t.e.this.a.a(n);
                            }
                        }
                    });
                    ((ViewGroup)o).addView((View)this.z);
                }
                final ArrayList<View> list2 = new ArrayList<View>(list);
                if (this.J != null) {
                    list2.add(this.J);
                }
                for (final View view : list2) {
                    this.r.add(view);
                    view.setOnClickListener((View$OnClickListener)this.y);
                    view.setOnTouchListener((View$OnTouchListener)this.y);
                    if (com.facebook.ads.internal.r.a.b(view.getContext())) {
                        view.setOnLongClickListener((View$OnLongClickListener)this.y);
                    }
                }
                this.a.a(o, list2);
                int n2;
                if (this.m != null) {
                    n2 = this.m.f();
                }
                else if (this.k != null && this.k.b() != null) {
                    n2 = this.k.b().f();
                }
                else {
                    n2 = 1;
                }
                this.u = new com.facebook.ads.internal.x.a.a() {
                    @Override
                    public void a() {
                        if (!e.this.w.b()) {
                            e.this.w.a();
                            e.this.t.c();
                            if (e.this.v != null && e.this.v.get() != null) {
                                ((a.a)e.this.v.get()).a();
                            }
                            if (e.this.x != null && e.this.o != null && e.this.q != null) {
                                e.this.x.a(e.this.o);
                                e.this.x.a(e.this.q);
                                e.this.x.a(e.this.A);
                                e.this.x.a(e.this.B);
                                e.this.x.b(e.this.C);
                                e.this.x.d(e.this.D);
                                e.this.x.c(e.o(e.this));
                                e.this.x.a(e.this.H);
                                e.this.x.e(e.this.E);
                                e.this.x.a(com.facebook.ads.internal.view.a.d.a(e.this.p));
                                e.this.x.a(e.this.I);
                                e.this.x.a();
                            }
                        }
                    }
                };
                View view2;
                if (q != null) {
                    view2 = q.getAdContentsView();
                }
                else {
                    view2 = this.o;
                }
                int n3;
                if (this.m != null) {
                    n3 = this.m.g();
                }
                else if (this.k != null && this.k.b() != null) {
                    n3 = this.k.b().g();
                }
                else {
                    n3 = 0;
                }
                this.t = new com.facebook.ads.internal.x.a(view2, n2, n3, true, this.u);
                final com.facebook.ads.internal.x.a t = this.t;
                int n4;
                if (this.m != null) {
                    n4 = this.m.h();
                }
                else if (this.a != null) {
                    n4 = this.a.j();
                }
                else {
                    n4 = n;
                    if (this.k != null) {
                        n4 = n;
                        if (this.k.b() != null) {
                            n4 = this.k.b().h();
                        }
                    }
                }
                t.a(n4);
                final com.facebook.ads.internal.x.a t2 = this.t;
                int n5;
                if (this.m != null) {
                    n5 = this.m.i();
                }
                else if (this.a != null) {
                    n5 = this.a.k();
                }
                else if (this.k != null && this.k.b() != null) {
                    n5 = this.k.b().i();
                }
                else {
                    n5 = 1000;
                }
                t2.b(n5);
                (this.x = new p(this.d, new b(), this.t, this.a)).a(list2);
                com.facebook.ads.internal.t.e.c.put(o, new WeakReference<e>(this));
                if (com.facebook.ads.internal.r.a.b(this.d)) {
                    (this.F = new com.facebook.ads.internal.view.c.c()).a(this.e);
                    this.F.b(this.d.getPackageName());
                    this.F.a(this.t);
                    if (this.a.z() > 0) {
                        this.F.a(this.a.z(), this.a.y());
                    }
                    if (this.m != null) {
                        this.F.a(this.m.a());
                    }
                    else if (this.k != null && this.k.b() != null) {
                        this.F.a(this.k.b().a());
                    }
                    this.o.getOverlay().add((Drawable)this.F);
                }
            }
        }
    }
    
    static /* synthetic */ boolean o(final e e) {
        return e.t() == l.b;
    }
    
    @Nullable
    public i a() {
        return this.a;
    }
    
    @Nullable
    public String a(final String s) {
        if (!this.f()) {
            return null;
        }
        return this.a.a(s);
    }
    
    public void a(final View$OnTouchListener s) {
        this.s = s;
    }
    
    public void a(final View view, final f f) {
        final ArrayList<View> list = new ArrayList<View>();
        this.a(list, view);
        this.b(view, f, list);
    }
    
    public void a(final View view, final f f, final List<View> list) {
        this.b(view, f, list);
    }
    
    public void a(@Nullable final NativeAdLayout p) {
        this.p = p;
    }
    
    public void a(final q q) {
        if (this.a == null) {
            return;
        }
        this.a.a(q);
    }
    
    public void a(final com.facebook.ads.internal.protocol.e n) {
        this.n = n;
    }
    
    public void a(final d g, final String s) {
        if (this.l) {
            throw new IllegalStateException("loadAd cannot be called more than once");
        }
        this.l = true;
        this.G = g;
        if (g.equals(com.facebook.ads.internal.t.d.a)) {
            this.H = com.facebook.ads.internal.adapters.p.a.b;
        }
        final String e = this.e;
        final com.facebook.ads.internal.protocol.e n = this.n;
        AdPlacementType adPlacementType;
        if (this.n == com.facebook.ads.internal.protocol.e.j) {
            adPlacementType = AdPlacementType.NATIVE;
        }
        else {
            adPlacementType = AdPlacementType.NATIVE_BANNER;
        }
        final com.facebook.ads.internal.b.a a = new com.facebook.ads.internal.b.a(e, n, adPlacementType, null, 1);
        a.a(g);
        a.a(this.I);
        (this.k = new g(this.d, a)).a(new com.facebook.ads.internal.adapters.a() {
            @Override
            public void a() {
                if (com.facebook.ads.internal.t.e.this.i != null) {
                    com.facebook.ads.internal.t.e.this.i.c();
                }
            }
            
            @Override
            public void a(final AdAdapter adAdapter) {
                if (com.facebook.ads.internal.t.e.this.k != null) {
                    com.facebook.ads.internal.t.e.this.k.e();
                }
            }
            
            @Override
            public void a(final i i) {
                com.facebook.ads.internal.t.e.this.a(i, true);
                if (com.facebook.ads.internal.t.e.this.i != null && i.x() != null) {
                    final q q = new q() {
                        @Override
                        public void a(final i i) {
                        }
                        
                        @Override
                        public void a(final i i, final com.facebook.ads.internal.protocol.a a) {
                        }
                        
                        @Override
                        public void b(final i i) {
                        }
                        
                        @Override
                        public void c(final i i) {
                            if (com.facebook.ads.internal.t.e.this.i != null) {
                                com.facebook.ads.internal.t.e.this.i.c();
                            }
                        }
                    };
                    final Iterator<e> iterator = i.x().iterator();
                    while (iterator.hasNext()) {
                        iterator.next().a(q);
                    }
                }
            }
            
            @Override
            public void a(final com.facebook.ads.internal.protocol.a a) {
                if (com.facebook.ads.internal.t.e.this.i != null) {
                    com.facebook.ads.internal.t.e.this.i.a(a);
                }
            }
            
            @Override
            public void b() {
                throw new IllegalStateException("Native ads manager their own impressions.");
            }
        });
        this.k.b(s);
    }
    
    public void a(final h i) {
        this.i = i;
    }
    
    public void a(final k a) {
        this.A = a;
    }
    
    public void a(final com.facebook.ads.internal.x.a.a a) {
        this.v = new WeakReference<com.facebook.ads.internal.x.a.a>(a);
    }
    
    public void a(final boolean b) {
        this.B = b;
    }
    
    public void a(final boolean b, final boolean b2) {
        if (b) {
            if (this.G.equals(com.facebook.ads.internal.t.d.a) && !this.A() && this.i != null) {
                this.i.a();
            }
            if (this.t != null) {
                this.t.a();
            }
        }
        else {
            if (this.t != null) {
                this.t.c();
            }
            if (this.i != null && b2) {
                this.i.a(com.facebook.ads.internal.protocol.a.a(AdErrorType.BROKEN_MEDIA_ERROR, "Failed to load Media."));
            }
        }
    }
    
    public void b(final String i) {
        this.I = i;
    }
    
    public void b(final boolean e) {
        this.E = e;
    }
    
    public boolean b() {
        return this.k == null || this.k.g();
    }
    
    public void c() {
        if (this.G.equals(com.facebook.ads.internal.t.d.a)) {
            this.H = com.facebook.ads.internal.adapters.p.a.c;
        }
        this.G = com.facebook.ads.internal.t.d.b;
        this.a(this.a, false);
    }
    
    public void c(final boolean c) {
        this.C = c;
    }
    
    public void d() {
        if (this.k != null) {
            this.k.a(true);
            this.k = null;
        }
    }
    
    public void d(final boolean d) {
        this.D = d;
    }
    
    public String e() {
        return this.e;
    }
    
    public boolean f() {
        return this.a != null && this.a.A();
    }
    
    public boolean g() {
        return this.f() && this.a.e();
    }
    
    public boolean h() {
        return this.a != null && this.a.f();
    }
    
    @Nullable
    public com.facebook.ads.internal.t.g i() {
        if (!this.f()) {
            return null;
        }
        return this.a.l();
    }
    
    @Nullable
    public com.facebook.ads.internal.t.g j() {
        if (!this.f()) {
            return null;
        }
        return this.a.m();
    }
    
    @Nullable
    public j k() {
        if (!this.f()) {
            return null;
        }
        return this.a.n();
    }
    
    @Nullable
    public String l() {
        if (!this.f()) {
            return null;
        }
        return this.a.o();
    }
    
    @Nullable
    public com.facebook.ads.internal.t.i m() {
        if (!this.f()) {
            return null;
        }
        return this.a.p();
    }
    
    @Nullable
    public String n() {
        if (!this.f()) {
            return null;
        }
        return this.f;
    }
    
    @Nullable
    public com.facebook.ads.internal.t.g o() {
        if (!this.f()) {
            return null;
        }
        return this.a.q();
    }
    
    @Nullable
    public String p() {
        if (!this.f()) {
            return null;
        }
        return this.a.r();
    }
    
    @Nullable
    public String q() {
        if (!this.f()) {
            return null;
        }
        return this.a.s();
    }
    
    @Nullable
    public String r() {
        if (!this.f() || TextUtils.isEmpty((CharSequence)this.a.t())) {
            return null;
        }
        return this.g.c(this.a.t());
    }
    
    @Nullable
    public String s() {
        if (!this.f()) {
            return null;
        }
        return this.a.u();
    }
    
    @Nullable
    public l t() {
        if (!this.f()) {
            return com.facebook.ads.internal.t.l.a;
        }
        return this.a.v();
    }
    
    @Nullable
    public List<e> u() {
        if (!this.f()) {
            return null;
        }
        return this.a.x();
    }
    
    @Nullable
    public String v() {
        if (!this.f()) {
            return null;
        }
        return this.a.getClientToken();
    }
    
    public void w() {
        this.J.performClick();
    }
    
    public k x() {
        return this.A;
    }
    
    public void y() {
        if (!com.facebook.ads.internal.f.a.a(this.d, false)) {
            this.B();
            return;
        }
        final com.facebook.ads.internal.view.a.c a = com.facebook.ads.internal.view.a.d.a(this.d, com.facebook.ads.internal.s.d.a(this.d), this.v(), this.p);
        if (a == null) {
            this.B();
            return;
        }
        this.p.setAdReportingLayout(a);
        a.a();
    }
    
    public void z() {
        if (this.o == null || this.q == null) {
            return;
        }
        if (!com.facebook.ads.internal.t.e.c.containsKey(this.o) || com.facebook.ads.internal.t.e.c.get(this.o).get() != this) {
            throw new IllegalStateException("View not registered with this NativeAd");
        }
        if (this.o instanceof ViewGroup && this.z != null) {
            ((ViewGroup)this.o).removeView((View)this.z);
            this.z = null;
        }
        if (this.a != null) {
            this.a.c();
        }
        if (this.F != null && com.facebook.ads.internal.r.a.b(this.d)) {
            this.F.b();
            this.o.getOverlay().remove((Drawable)this.F);
        }
        com.facebook.ads.internal.t.e.c.remove(this.o);
        this.C();
        this.o = null;
        this.q = null;
        if (this.t != null) {
            this.t.c();
            this.t = null;
        }
        this.x = null;
    }
    
    private class a implements View$OnClickListener, View$OnLongClickListener, View$OnTouchListener
    {
        private Map a() {
            final HashMap<String, String> hashMap = new HashMap<String, String>();
            hashMap.put("touch", com.facebook.ads.internal.w.b.k.a(com.facebook.ads.internal.t.e.this.w.e()));
            if (com.facebook.ads.internal.t.e.this.A != null) {
                hashMap.put("nti", String.valueOf(com.facebook.ads.internal.t.e.this.A.c()));
            }
            if (com.facebook.ads.internal.t.e.this.B) {
                hashMap.put("nhs", String.valueOf(com.facebook.ads.internal.t.e.this.B));
            }
            com.facebook.ads.internal.t.e.this.t.a(hashMap);
            return hashMap;
        }
        
        private void a(final Map<String, String> map) {
            if (com.facebook.ads.internal.t.e.this.a != null) {
                com.facebook.ads.internal.t.e.this.a.e(map);
            }
        }
        
        public void onClick(final View view) {
            if (!com.facebook.ads.internal.t.e.this.w.d()) {
                Log.e("FBAudienceNetworkLog", "No touch data recorded, please ensure touch events reach the ad View by returning false if you intercept the event.");
            }
            final int f = com.facebook.ads.internal.r.a.F(com.facebook.ads.internal.t.e.this.d);
            if (f >= 0 && com.facebook.ads.internal.t.e.this.w.c() < f) {
                if (com.facebook.ads.internal.t.e.this.w.b()) {
                    Log.e("FBAudienceNetworkLog", "Clicks happened too fast.");
                    return;
                }
                Log.e("FBAudienceNetworkLog", "Ad cannot be clicked before it is viewed.");
            }
            else if (com.facebook.ads.internal.t.e.this.w.a(com.facebook.ads.internal.t.e.this.d)) {
                if (com.facebook.ads.internal.t.e.this.a != null) {
                    com.facebook.ads.internal.t.e.this.a.d(this.a());
                }
            }
            else {
                if (com.facebook.ads.internal.r.a.e(com.facebook.ads.internal.t.e.this.d)) {
                    if (com.facebook.ads.internal.t.e.this.a != null) {
                        com.facebook.ads.internal.t.e.this.a.c(this.a());
                    }
                    com.facebook.ads.internal.w.b.g.a((DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            final Map a = com.facebook.ads.internal.t.e.a.this.a();
                            a.put("is_two_step", "true");
                            com.facebook.ads.internal.t.e.a.this.a(a);
                        }
                    }, (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                        public void onClick(final DialogInterface dialogInterface, final int n) {
                            if (com.facebook.ads.internal.t.e.this.a != null) {
                                com.facebook.ads.internal.t.e.this.a.b(com.facebook.ads.internal.t.e.a.this.a());
                            }
                        }
                    }, (Context)com.facebook.ads.internal.w.a.b.a());
                    return;
                }
                this.a(this.a());
            }
        }
        
        public boolean onLongClick(final View view) {
            boolean b = false;
            if (com.facebook.ads.internal.t.e.this.o == null || com.facebook.ads.internal.t.e.this.F == null) {
                return false;
            }
            com.facebook.ads.internal.t.e.this.F.setBounds(0, 0, com.facebook.ads.internal.t.e.this.o.getWidth(), com.facebook.ads.internal.t.e.this.o.getHeight());
            final com.facebook.ads.internal.view.c.c u = com.facebook.ads.internal.t.e.this.F;
            if (!com.facebook.ads.internal.t.e.this.F.a()) {
                b = true;
            }
            u.a(b);
            return true;
        }
        
        public boolean onTouch(final View view, final MotionEvent motionEvent) {
            com.facebook.ads.internal.t.e.this.w.a(motionEvent, com.facebook.ads.internal.t.e.this.o, view);
            return com.facebook.ads.internal.t.e.this.s != null && com.facebook.ads.internal.t.e.this.s.onTouch(view, motionEvent);
        }
    }
    
    private class b extends com.facebook.ads.internal.adapters.c
    {
        @Override
        public void a() {
            if (com.facebook.ads.internal.t.e.this.i != null) {
                com.facebook.ads.internal.t.e.this.i.d();
            }
        }
        
        @Override
        public void b() {
        }
    }
    
    public interface c
    {
        boolean a(final View p0);
    }
}
