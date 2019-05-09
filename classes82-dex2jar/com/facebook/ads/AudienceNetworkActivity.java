// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import com.facebook.ads.internal.view.m;
import com.facebook.ads.internal.view.e.a.e;
import com.facebook.ads.internal.view.l;
import com.facebook.ads.internal.view.n;
import com.facebook.ads.internal.adapters.b.f;
import com.facebook.ads.internal.view.r;
import com.facebook.ads.internal.adapters.b.k;
import com.facebook.ads.internal.view.z;
import com.facebook.ads.internal.adapters.b.q;
import android.support.annotation.Nullable;
import java.lang.ref.WeakReference;
import com.facebook.ads.internal.adapters.g;
import android.graphics.drawable.Drawable;
import android.view.View$OnLongClickListener;
import android.graphics.Color;
import android.widget.TextView;
import android.os.Bundle;
import com.facebook.ads.internal.view.s;
import com.facebook.ads.internal.adapters.h;
import android.content.res.Configuration;
import java.util.Iterator;
import com.facebook.ads.internal.view.i.b.aa;
import com.facebook.ads.internal.ipc.RemoteANActivity;
import com.facebook.ads.internal.settings.AdInternalSettings;
import android.view.ViewGroup;
import android.view.View;
import com.facebook.ads.internal.w.b.x;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.facebook.ads.internal.view.a.b;
import android.content.Context;
import android.support.v4.content.LocalBroadcastManager;
import java.io.Serializable;
import android.content.Intent;
import com.facebook.ads.internal.o.d;
import java.util.ArrayList;
import com.facebook.ads.internal.view.a.c;
import com.facebook.ads.internal.settings.a;
import android.widget.RelativeLayout;
import java.util.List;
import android.app.Activity;

public class AudienceNetworkActivity extends Activity
{
    @Deprecated
    public static final String AD_ICON_URL = "adIconUrl";
    @Deprecated
    public static final String AD_SUBTITLE = "adSubtitle";
    @Deprecated
    public static final String AD_TITLE = "adTitle";
    public static final String AUDIENCE_NETWORK_UNIQUE_ID_EXTRA = "uniqueId";
    public static final String AUTOPLAY = "autoplay";
    public static final String BROWSER_URL = "browserURL";
    public static final String CLIENT_TOKEN = "clientToken";
    @Deprecated
    public static final String CONTEXT_SWITCH_BEHAVIOR = "contextSwitchBehavior";
    @Deprecated
    public static final String END_CARD_ACTIVATION_COMMAND = "facebookRewardedVideoEndCardActivationCommand";
    @Deprecated
    public static final String END_CARD_MARKUP = "facebookRewardedVideoEndCardMarkup";
    public static final String HANDLER_TIME = "handlerTime";
    public static final String PLACEMENT_ID = "placementId";
    public static final String PREDEFINED_ORIENTATION_KEY = "predefinedOrientationKey";
    public static final String REQUEST_TIME = "requestTime";
    public static final String REWARD_SERVER_URL = "rewardServerURL";
    public static final String SKIP_DELAY_SECONDS_KEY = "skipAfterSeconds";
    public static final String USE_CACHE = "useCache";
    public static final String VIDEO_LOGGER = "videoLogger";
    public static final String VIDEO_MPD = "videoMPD";
    public static final String VIDEO_SEEK_TIME = "videoSeekTime";
    public static final String VIDEO_URL = "videoURL";
    public static final String VIEW_TYPE = "viewType";
    @Deprecated
    public static final String WEBVIEW_ENCODING = "utf-8";
    @Deprecated
    public static final String WEBVIEW_MIME_TYPE = "text/html";
    private final List<BackButtonInterceptor> a;
    private RelativeLayout b;
    private int c;
    private String d;
    private com.facebook.ads.internal.settings.a.a e;
    private long f;
    private long g;
    private int h;
    private com.facebook.ads.internal.view.a i;
    private com.facebook.ads.internal.view.a.c j;
    private com.facebook.ads.internal.view.c.c k;
    
    public AudienceNetworkActivity() {
        this.a = new ArrayList<BackButtonInterceptor>();
        this.c = -1;
    }
    
    static /* synthetic */ void a(final AudienceNetworkActivity audienceNetworkActivity, final String s, final com.facebook.ads.internal.o.d d) {
        final Intent intent = new Intent(s + ":" + audienceNetworkActivity.d);
        intent.putExtra("event", (Serializable)d);
        LocalBroadcastManager.getInstance((Context)audienceNetworkActivity).sendBroadcast(intent);
    }
    
    static /* synthetic */ void a(final AudienceNetworkActivity audienceNetworkActivity, final String s, final boolean b, final com.facebook.ads.internal.view.a.b adReportingFlowListener) {
        if (audienceNetworkActivity.j == null) {
            (audienceNetworkActivity.j = com.facebook.ads.internal.view.a.d.a(audienceNetworkActivity.getApplicationContext(), com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity), s, audienceNetworkActivity.i, new a(audienceNetworkActivity))).setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
        }
        audienceNetworkActivity.j.a(b);
        audienceNetworkActivity.j.setAdReportingFlowListener(adReportingFlowListener);
        x.b((View)audienceNetworkActivity.j);
        x.a((ViewGroup)audienceNetworkActivity.b);
        audienceNetworkActivity.b.addView((View)audienceNetworkActivity.j);
        audienceNetworkActivity.j.a();
    }
    
    private void a(final Exception ex) {
        this.finish();
        com.facebook.ads.internal.w.h.a.b((Context)this, "an_activity", com.facebook.ads.internal.w.h.b.aa, ex);
    }
    
    private void a(final String s) {
        if ("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW".equals(s)) {
            this.finish();
            return;
        }
        LocalBroadcastManager.getInstance((Context)this).sendBroadcast(new Intent(s + ":" + this.d));
    }
    
    private boolean a() {
        return this.e == com.facebook.ads.internal.settings.a.a.h || this.e == com.facebook.ads.internal.settings.a.a.i || this.e == com.facebook.ads.internal.settings.a.a.j;
    }
    
    public static Class getAdActivity() {
        if (AdInternalSettings.d) {
            return RemoteANActivity.class;
        }
        return AudienceNetworkActivity.class;
    }
    
    public void addBackButtonInterceptor(final BackButtonInterceptor backButtonInterceptor) {
        this.a.add(backButtonInterceptor);
    }
    
    public void finish() {
        if (this.isFinishing()) {
            return;
        }
        if (this.a()) {
            this.a(aa.g.a());
        }
        else {
            this.a("com.facebook.ads.interstitial.dismissed");
        }
        super.finish();
    }
    
    public void onBackPressed() {
        while (true) {
            while (true) {
                Label_0101: {
                    try {
                        final long currentTimeMillis = System.currentTimeMillis();
                        this.g += currentTimeMillis - this.f;
                        this.f = currentTimeMillis;
                        if (this.g > this.h) {
                            boolean b = false;
                            final Iterator<BackButtonInterceptor> iterator = this.a.iterator();
                            if (iterator.hasNext()) {
                                if (iterator.next().interceptBackButton()) {
                                    b = true;
                                    break Label_0101;
                                }
                                break Label_0101;
                            }
                            else if (!b) {
                                super.onBackPressed();
                            }
                        }
                        return;
                    }
                    catch (Exception ex) {
                        this.a(ex);
                        return;
                    }
                }
                continue;
            }
        }
    }
    
    public void onConfigurationChanged(final Configuration configuration) {
        while (true) {
            try {
                if (this.i instanceof h) {
                    ((h)this.i).a(configuration);
                }
                else if (this.i instanceof s) {
                    ((s)this.i).onConfigurationChanged(configuration);
                }
                super.onConfigurationChanged(configuration);
            }
            catch (Exception ex) {
                this.a(ex);
                continue;
            }
            break;
        }
    }
    
    public void onCreate(final Bundle bundle) {
        Intent intent;
        while (true) {
            super.onCreate(bundle);
            while (true) {
                b b;
                try {
                    com.facebook.ads.internal.w.b.c.a();
                    this.requestWindowFeature(1);
                    this.getWindow().setFlags(1024, 1024);
                    x.a((View)(this.b = new RelativeLayout((Context)this)), -16777216);
                    this.setContentView((View)this.b, (ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
                    intent = this.getIntent();
                    if (bundle != null) {
                        this.c = bundle.getInt("predefinedOrientationKey", -1);
                        this.d = bundle.getString("uniqueId");
                        this.e = (com.facebook.ads.internal.settings.a.a)bundle.getSerializable("viewType");
                    }
                    else {
                        this.c = intent.getIntExtra("predefinedOrientationKey", -1);
                        this.d = intent.getStringExtra("uniqueId");
                        this.e = (com.facebook.ads.internal.settings.a.a)intent.getSerializableExtra("viewType");
                        this.h = intent.getIntExtra("skipAfterSeconds", 0) * 1000;
                    }
                    b = new b(this, this.getIntent(), com.facebook.ads.internal.s.d.a((Context)this));
                    if (this.e == null) {
                        final com.facebook.ads.internal.view.a i = null;
                        this.i = i;
                        if (this.i == null) {
                            com.facebook.ads.internal.o.b.a(com.facebook.ads.internal.o.a.a(null, "Unable to infer viewType from intent or savedInstanceState"));
                            this.a("com.facebook.ads.interstitial.error");
                            this.finish();
                            return;
                        }
                        break;
                    }
                }
                catch (Exception ex) {
                    this.a(ex);
                    return;
                }
                switch (AudienceNetworkActivity$1.a[this.e.ordinal()]) {
                    case 1: {
                        final com.facebook.ads.internal.view.a i = AudienceNetworkActivity.b.a(b, this.b);
                        continue;
                    }
                    case 2: {
                        final com.facebook.ads.internal.view.a i = new s((Context)b.a, b.c, new com.facebook.ads.internal.view.i.a((Context)b.a), new d(b.a), (q)b.b.getSerializableExtra("rewardedVideoAdDataBundle"));
                        continue;
                    }
                    case 3: {
                        final com.facebook.ads.internal.view.a i = new r((Context)b.a, b.c, new d(b.a), (q)b.b.getSerializableExtra("rewardedVideoAdDataBundle"));
                        continue;
                    }
                    case 4: {
                        final com.facebook.ads.internal.view.a i = new com.facebook.ads.internal.view.f((Context)b.a, (f)b.b.getSerializableExtra("rewardedVideoAdDataBundle"), b.c, new d(b.a));
                        continue;
                    }
                    case 5: {
                        final com.facebook.ads.internal.view.a i = new com.facebook.ads.internal.view.h(b.a, b.c, new a(b.a));
                        continue;
                    }
                    case 6: {
                        final com.facebook.ads.internal.view.a i = new com.facebook.ads.internal.view.b(b.a, b.c, new a(b.a));
                        continue;
                    }
                    case 7: {
                        final com.facebook.ads.internal.view.a i = AudienceNetworkActivity.b.f(b);
                        continue;
                    }
                    case 8: {
                        final com.facebook.ads.internal.view.a i = AudienceNetworkActivity.b.g(b);
                        continue;
                    }
                    case 9: {
                        final com.facebook.ads.internal.view.a i = new l((Context)b.a, b.b(), b.c, new a(b.a));
                        continue;
                    }
                    case 10: {
                        final com.facebook.ads.internal.view.a i = AudienceNetworkActivity.b.i(b);
                        continue;
                    }
                    case 11: {
                        final com.facebook.ads.internal.view.a i = new m((Context)b.a, b.c, b.b(), new a(b.a));
                        continue;
                    }
                    default: {
                        final com.facebook.ads.internal.view.a i = null;
                        continue;
                    }
                }
                break;
            }
        }
        this.i.a(intent, bundle, this);
        this.a("com.facebook.ads.interstitial.displayed");
        this.f = System.currentTimeMillis();
        int n;
        if (this.e == com.facebook.ads.internal.settings.a.a.a) {
            n = 1;
        }
        else {
            n = 0;
        }
        if (com.facebook.ads.internal.r.a.b((Context)this) && this.e != com.facebook.ads.internal.settings.a.a.k) {
            (this.k = new com.facebook.ads.internal.view.c.c()).a(intent.getStringExtra("placementId"));
            this.k.b(this.getPackageName());
            final long longExtra = intent.getLongExtra("requestTime", 0L);
            if (longExtra != 0L) {
                this.k.a(longExtra);
            }
            final TextView textView = new TextView((Context)this);
            textView.setText((CharSequence)"Debug");
            textView.setTextColor(-1);
            x.a((View)textView, Color.argb(160, 0, 0, 0));
            textView.setPadding(5, 5, 5, 5);
            final RelativeLayout$LayoutParams layoutParams = new RelativeLayout$LayoutParams(-2, -2);
            layoutParams.addRule(12, -1);
            layoutParams.addRule(11, -1);
            textView.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
            final c c = new c();
            textView.setOnLongClickListener((View$OnLongClickListener)c);
            if (n != 0) {
                this.b.addView((View)textView);
            }
            else {
                this.b.setOnLongClickListener((View$OnLongClickListener)c);
            }
            this.b.getOverlay().add((Drawable)this.k);
        }
    }
    
    protected void onDestroy() {
        while (true) {
            try {
                if (this.a()) {
                    this.a(aa.j.a());
                }
                else {
                    this.a("com.facebook.ads.interstitial.activity_destroyed");
                }
                if (this.b != null) {
                    this.b.removeAllViews();
                }
                if (this.i != null) {
                    com.facebook.ads.internal.adapters.g.a(this.i);
                    this.i.onDestroy();
                    this.i = null;
                }
                if (this.k != null && com.facebook.ads.internal.r.a.b((Context)this)) {
                    this.k.b();
                }
                if (this.j != null) {
                    this.j.b();
                }
                super.onDestroy();
            }
            catch (Exception ex) {
                this.a(ex);
                continue;
            }
            break;
        }
    }
    
    public void onPause() {
        while (true) {
            try {
                this.g += System.currentTimeMillis() - this.f;
                if (this.i != null) {
                    this.i.a_(false);
                }
                super.onPause();
            }
            catch (Exception ex) {
                this.a(ex);
                continue;
            }
            break;
        }
    }
    
    public void onResume() {
        super.onResume();
        try {
            this.f = System.currentTimeMillis();
            if (this.i != null) {
                this.i.b(false);
            }
        }
        catch (Exception ex) {
            this.a(ex);
        }
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            if (this.i != null) {
                this.i.a(bundle);
            }
            bundle.putInt("predefinedOrientationKey", this.c);
            bundle.putString("uniqueId", this.d);
            bundle.putSerializable("viewType", (Serializable)this.e);
        }
        catch (Exception ex) {
            this.a(ex);
        }
    }
    
    public void onStart() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   android/app/Activity.onStart:()V
        //     4: aload_0        
        //     5: getfield        com/facebook/ads/AudienceNetworkActivity.c:I
        //     8: istore_1       
        //     9: iload_1        
        //    10: iconst_m1      
        //    11: if_icmpeq       22
        //    14: aload_0        
        //    15: aload_0        
        //    16: getfield        com/facebook/ads/AudienceNetworkActivity.c:I
        //    19: invokevirtual   com/facebook/ads/AudienceNetworkActivity.setRequestedOrientation:(I)V
        //    22: return         
        //    23: astore_2       
        //    24: aload_0        
        //    25: aload_2        
        //    26: invokespecial   com/facebook/ads/AudienceNetworkActivity.a:(Ljava/lang/Exception;)V
        //    29: return         
        //    30: astore_2       
        //    31: return         
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                             
        //  -----  -----  -----  -----  ---------------------------------
        //  4      9      23     30     Ljava/lang/Exception;
        //  14     22     30     32     Ljava/lang/IllegalStateException;
        //  14     22     23     30     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Expression is linked from several locations: Label_0022:
        //     at com.strobel.decompiler.ast.Error.expressionLinkedFromMultipleLocations(Error.java:27)
        //     at com.strobel.decompiler.ast.AstOptimizer.mergeDisparateObjectInitializations(AstOptimizer.java:2596)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:235)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
        //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
        //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public void removeBackButtonInterceptor(final BackButtonInterceptor backButtonInterceptor) {
        this.a.remove(backButtonInterceptor);
    }
    
    public interface BackButtonInterceptor
    {
        boolean interceptBackButton();
    }
    
    private static class a implements com.facebook.ads.internal.view.a.a
    {
        final WeakReference<AudienceNetworkActivity> a;
        
        private a(final AudienceNetworkActivity audienceNetworkActivity) {
            this.a = new WeakReference<AudienceNetworkActivity>(audienceNetworkActivity);
        }
        
        @Override
        public void a(final View view) {
            if (this.a.get() != null) {
                this.a.get().b.addView(view);
            }
        }
        
        @Override
        public void a(final View view, final int n) {
            if (this.a.get() != null) {
                this.a.get().b.addView(view, n);
            }
        }
        
        @Override
        public void a(final String s) {
            if (this.a.get() != null) {
                this.a.get().a(s);
            }
        }
        
        @Override
        public void a(final String s, final com.facebook.ads.internal.o.d d) {
            if (this.a.get() != null) {
                AudienceNetworkActivity.a(this.a.get(), s, d);
            }
        }
        
        @Override
        public void a(final String s, final boolean b, @Nullable final com.facebook.ads.internal.view.a.b b2) {
            if (this.a.get() != null) {
                AudienceNetworkActivity.a(this.a.get(), s, b, b2);
            }
        }
    }
    
    private static class b
    {
        private final AudienceNetworkActivity a;
        private final Intent b;
        private final com.facebook.ads.internal.s.c c;
        
        private b(final AudienceNetworkActivity a, final Intent b, final com.facebook.ads.internal.s.c c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }
        
        static /* synthetic */ com.facebook.ads.internal.view.a a(final b b, final RelativeLayout relativeLayout) {
            final z z = new z(b.a, b.c, new a(b.a));
            z.a((View)relativeLayout);
            z.a(b.b.getIntExtra("video_time_polling_interval", 200));
            return z;
        }
        
        private boolean a() {
            return this.b.getBooleanExtra("useCache", false);
        }
        
        private k b() {
            return (k)this.b.getSerializableExtra("ad_data_bundle");
        }
        
        static /* synthetic */ com.facebook.ads.internal.view.a f(final b b) {
            final com.facebook.ads.internal.view.a a = g.a(b.b.getStringExtra("uniqueId"));
            if (a == null) {
                return null;
            }
            a.setListener((com.facebook.ads.internal.view.a.a)new a(b.a));
            return a;
        }
        
        static /* synthetic */ com.facebook.ads.internal.view.a g(final b b) {
            final AudienceNetworkActivity a = b.a;
            final com.facebook.ads.internal.s.c c = b.c;
            final k b2 = b.b();
            com.facebook.ads.internal.h.b b3;
            if (b.a()) {
                b3 = new com.facebook.ads.internal.h.b((Context)b.a);
            }
            else {
                b3 = null;
            }
            return new n((Context)a, c, b2, b3, new a(b.a));
        }
        
        static /* synthetic */ com.facebook.ads.internal.view.a i(final b b) {
            final AudienceNetworkActivity a = b.a;
            final com.facebook.ads.internal.s.c c = b.c;
            com.facebook.ads.internal.h.b b2;
            if (b.a()) {
                b2 = new com.facebook.ads.internal.h.b((Context)b.a);
            }
            else {
                b2 = null;
            }
            return new e((Context)a, c, b2, new a(b.a));
        }
    }
    
    private class c implements View$OnLongClickListener
    {
        public boolean onLongClick(final View view) {
            boolean b = false;
            if (AudienceNetworkActivity.this.k != null && AudienceNetworkActivity.this.b != null) {
                AudienceNetworkActivity.this.k.setBounds(0, 0, AudienceNetworkActivity.this.b.getWidth(), AudienceNetworkActivity.this.b.getHeight());
                final com.facebook.ads.internal.view.c.c a = AudienceNetworkActivity.this.k;
                if (!AudienceNetworkActivity.this.k.a()) {
                    b = true;
                }
                a.a(b);
            }
            return true;
        }
    }
    
    private static class d extends a
    {
        private d(final AudienceNetworkActivity audienceNetworkActivity) {
            super(audienceNetworkActivity);
        }
        
        @Override
        public void a(final String s) {
            if (this.a.get() != null) {
                this.a.get().a(s);
                final String a = aa.c.a();
                final String a2 = aa.d.a();
                if (s.equals(a) || s.equals(a2)) {
                    this.a.get().finish();
                }
            }
        }
        
        @Override
        public void a(final String s, final com.facebook.ads.internal.o.d d) {
            super.a(s, d);
            if (this.a.get() != null) {
                final AudienceNetworkActivity audienceNetworkActivity = this.a.get();
                if (s.equals(aa.k.a())) {
                    final Intent intent = new Intent();
                    intent.putExtra("rewardedVideoAdDataBundle", (Serializable)((com.facebook.ads.internal.view.f.a)d).a());
                    final com.facebook.ads.internal.view.a a = new s((Context)new b(audienceNetworkActivity, intent, com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity)).a, new b(audienceNetworkActivity, intent, com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity)).c, new com.facebook.ads.internal.view.i.a((Context)new b(audienceNetworkActivity, intent, com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity)).a), new d(new b(audienceNetworkActivity, intent, com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity)).a), (q)new b(audienceNetworkActivity, intent, com.facebook.ads.internal.s.d.a((Context)audienceNetworkActivity)).b.getSerializableExtra("rewardedVideoAdDataBundle"));
                    if (audienceNetworkActivity.j != null) {
                        audienceNetworkActivity.j.b();
                    }
                    audienceNetworkActivity.j = null;
                    x.a((ViewGroup)a);
                    audienceNetworkActivity.i = a;
                    a.a(audienceNetworkActivity.getIntent(), null, audienceNetworkActivity);
                }
            }
        }
    }
}
