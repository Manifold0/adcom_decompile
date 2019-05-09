package com.facebook.ads;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.ads.internal.adapters.C1918g;
import com.facebook.ads.internal.adapters.C1922h;
import com.facebook.ads.internal.adapters.p030b.C1874f;
import com.facebook.ads.internal.adapters.p030b.C1879k;
import com.facebook.ads.internal.adapters.p030b.C1887q;
import com.facebook.ads.internal.ipc.RemoteANActivity;
import com.facebook.ads.internal.p021o.C2058a;
import com.facebook.ads.internal.p021o.C2059b;
import com.facebook.ads.internal.p021o.C2061d;
import com.facebook.ads.internal.p024h.C2011b;
import com.facebook.ads.internal.p025w.p026b.C2567c;
import com.facebook.ads.internal.p025w.p026b.C2600x;
import com.facebook.ads.internal.p025w.p044h.C2625a;
import com.facebook.ads.internal.p025w.p044h.C2626b;
import com.facebook.ads.internal.p050r.C2078a;
import com.facebook.ads.internal.p051s.C2085c;
import com.facebook.ads.internal.p051s.C2087d;
import com.facebook.ads.internal.settings.AdInternalSettings;
import com.facebook.ads.internal.settings.C2094a.C2093a;
import com.facebook.ads.internal.view.C1921a;
import com.facebook.ads.internal.view.C1921a.C1789a;
import com.facebook.ads.internal.view.C2240b;
import com.facebook.ads.internal.view.C2352f;
import com.facebook.ads.internal.view.C2352f.C2335a;
import com.facebook.ads.internal.view.C2372h;
import com.facebook.ads.internal.view.C2513l;
import com.facebook.ads.internal.view.C2516m;
import com.facebook.ads.internal.view.C2526n;
import com.facebook.ads.internal.view.C2537r;
import com.facebook.ads.internal.view.C2547s;
import com.facebook.ads.internal.view.C2562z;
import com.facebook.ads.internal.view.p019c.C2251c;
import com.facebook.ads.internal.view.p022i.C2394a;
import com.facebook.ads.internal.view.p022i.p023b.aa;
import com.facebook.ads.internal.view.p055a.C2192b;
import com.facebook.ads.internal.view.p055a.C2195c;
import com.facebook.ads.internal.view.p055a.C2196d;
import com.facebook.ads.internal.view.p061e.p062a.C2324e;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class AudienceNetworkActivity extends Activity {
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
    /* renamed from: a */
    private final List<BackButtonInterceptor> f3704a = new ArrayList();
    /* renamed from: b */
    private RelativeLayout f3705b;
    /* renamed from: c */
    private int f3706c = -1;
    /* renamed from: d */
    private String f3707d;
    /* renamed from: e */
    private C2093a f3708e;
    /* renamed from: f */
    private long f3709f;
    /* renamed from: g */
    private long f3710g;
    /* renamed from: h */
    private int f3711h;
    /* renamed from: i */
    private C1921a f3712i;
    /* renamed from: j */
    private C2195c f3713j;
    /* renamed from: k */
    private C2251c f3714k;

    public interface BackButtonInterceptor {
        boolean interceptBackButton();
    }

    /* renamed from: com.facebook.ads.AudienceNetworkActivity$a */
    private static class C1790a implements C1789a {
        /* renamed from: a */
        final WeakReference<AudienceNetworkActivity> f3699a;

        private C1790a(AudienceNetworkActivity audienceNetworkActivity) {
            this.f3699a = new WeakReference(audienceNetworkActivity);
        }

        /* renamed from: a */
        public void mo5333a(View view) {
            if (this.f3699a.get() != null) {
                ((AudienceNetworkActivity) this.f3699a.get()).f3705b.addView(view);
            }
        }

        /* renamed from: a */
        public void mo5334a(View view, int i) {
            if (this.f3699a.get() != null) {
                ((AudienceNetworkActivity) this.f3699a.get()).f3705b.addView(view, i);
            }
        }

        /* renamed from: a */
        public void mo5335a(String str) {
            if (this.f3699a.get() != null) {
                ((AudienceNetworkActivity) this.f3699a.get()).m3988a(str);
            }
        }

        /* renamed from: a */
        public void mo5336a(String str, C2061d c2061d) {
            if (this.f3699a.get() != null) {
                AudienceNetworkActivity.m3985a((AudienceNetworkActivity) this.f3699a.get(), str, c2061d);
            }
        }

        /* renamed from: a */
        public void mo5337a(String str, boolean z, @Nullable C2192b c2192b) {
            if (this.f3699a.get() != null) {
                AudienceNetworkActivity.m3986a((AudienceNetworkActivity) this.f3699a.get(), str, z, c2192b);
            }
        }
    }

    /* renamed from: com.facebook.ads.AudienceNetworkActivity$b */
    private static class C1791b {
        /* renamed from: a */
        private final AudienceNetworkActivity f3700a;
        /* renamed from: b */
        private final Intent f3701b;
        /* renamed from: c */
        private final C2085c f3702c;

        private C1791b(AudienceNetworkActivity audienceNetworkActivity, Intent intent, C2085c c2085c) {
            this.f3700a = audienceNetworkActivity;
            this.f3701b = intent;
            this.f3702c = c2085c;
        }

        /* renamed from: a */
        static /* synthetic */ C1921a m3967a(C1791b c1791b, RelativeLayout relativeLayout) {
            C1921a c2562z = new C2562z(c1791b.f3700a, c1791b.f3702c, new C1790a());
            c2562z.m6610a((View) relativeLayout);
            c2562z.m6607a(c1791b.f3701b.getIntExtra("video_time_polling_interval", 200));
            return c2562z;
        }

        /* renamed from: a */
        private boolean m3968a() {
            return this.f3701b.getBooleanExtra(AudienceNetworkActivity.USE_CACHE, false);
        }

        /* renamed from: b */
        private C1879k m3969b() {
            return (C1879k) this.f3701b.getSerializableExtra("ad_data_bundle");
        }

        /* renamed from: f */
        static /* synthetic */ C1921a m3974f(C1791b c1791b) {
            C1921a a = C1918g.m4455a(c1791b.f3701b.getStringExtra(AudienceNetworkActivity.AUDIENCE_NETWORK_UNIQUE_ID_EXTRA));
            if (a == null) {
                return null;
            }
            a.setListener(new C1790a());
            return a;
        }

        /* renamed from: g */
        static /* synthetic */ C1921a m3975g(C1791b c1791b) {
            return new C2526n(c1791b.f3700a, c1791b.f3702c, c1791b.m3969b(), c1791b.m3968a() ? new C2011b(c1791b.f3700a) : null, new C1790a());
        }

        /* renamed from: i */
        static /* synthetic */ C1921a m3977i(C1791b c1791b) {
            return new C2324e(c1791b.f3700a, c1791b.f3702c, c1791b.m3968a() ? new C2011b(c1791b.f3700a) : null, new C1790a());
        }
    }

    /* renamed from: com.facebook.ads.AudienceNetworkActivity$c */
    private class C1792c implements OnLongClickListener {
        /* renamed from: a */
        final /* synthetic */ AudienceNetworkActivity f3703a;

        private C1792c(AudienceNetworkActivity audienceNetworkActivity) {
            this.f3703a = audienceNetworkActivity;
        }

        public boolean onLongClick(View view) {
            boolean z = false;
            if (!(this.f3703a.f3714k == null || this.f3703a.f3705b == null)) {
                this.f3703a.f3714k.setBounds(0, 0, this.f3703a.f3705b.getWidth(), this.f3703a.f3705b.getHeight());
                C2251c a = this.f3703a.f3714k;
                if (!this.f3703a.f3714k.m5768a()) {
                    z = true;
                }
                a.m5767a(z);
            }
            return true;
        }
    }

    /* renamed from: com.facebook.ads.AudienceNetworkActivity$d */
    private static class C1793d extends C1790a {
        private C1793d(AudienceNetworkActivity audienceNetworkActivity) {
            super();
        }

        /* renamed from: a */
        public void mo5335a(String str) {
            if (this.a.get() != null) {
                ((AudienceNetworkActivity) this.a.get()).m3988a(str);
                String a = aa.REWARDED_VIDEO_END_ACTIVITY.m6205a();
                String a2 = aa.REWARDED_VIDEO_ERROR.m6205a();
                if (str.equals(a) || str.equals(a2)) {
                    ((AudienceNetworkActivity) this.a.get()).finish();
                }
            }
        }

        /* renamed from: a */
        public void mo5336a(String str, C2061d c2061d) {
            super.mo5336a(str, c2061d);
            if (this.a.get() != null) {
                AudienceNetworkActivity audienceNetworkActivity = (AudienceNetworkActivity) this.a.get();
                if (str.equals(aa.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD.m6205a())) {
                    Intent intent = new Intent();
                    intent.putExtra("rewardedVideoAdDataBundle", ((C2335a) c2061d).m6017a());
                    C1921a a = new C2547s(new C1791b(intent, C2087d.m5183a((Context) audienceNetworkActivity)).f3700a, new C1791b(intent, C2087d.m5183a((Context) audienceNetworkActivity)).f3702c, new C2394a(new C1791b(intent, C2087d.m5183a((Context) audienceNetworkActivity)).f3700a), new C1793d(), (C1887q) new C1791b(intent, C2087d.m5183a((Context) audienceNetworkActivity)).f3701b.getSerializableExtra("rewardedVideoAdDataBundle"));
                    if (audienceNetworkActivity.f3713j != null) {
                        audienceNetworkActivity.f3713j.m5666b();
                    }
                    audienceNetworkActivity.f3713j = null;
                    C2600x.m6683a((ViewGroup) a);
                    audienceNetworkActivity.f3712i = a;
                    a.mo5403a(audienceNetworkActivity.getIntent(), null, audienceNetworkActivity);
                }
            }
        }
    }

    /* renamed from: a */
    static /* synthetic */ void m3985a(AudienceNetworkActivity audienceNetworkActivity, String str, C2061d c2061d) {
        Intent intent = new Intent(str + ":" + audienceNetworkActivity.f3707d);
        intent.putExtra("event", c2061d);
        LocalBroadcastManager.getInstance(audienceNetworkActivity).sendBroadcast(intent);
    }

    /* renamed from: a */
    static /* synthetic */ void m3986a(AudienceNetworkActivity audienceNetworkActivity, String str, boolean z, C2192b c2192b) {
        if (audienceNetworkActivity.f3713j == null) {
            audienceNetworkActivity.f3713j = C2196d.m5673a(audienceNetworkActivity.getApplicationContext(), C2087d.m5183a((Context) audienceNetworkActivity), str, audienceNetworkActivity.f3712i, new C1790a());
            audienceNetworkActivity.f3713j.setLayoutParams(new LayoutParams(-1, -1));
        }
        audienceNetworkActivity.f3713j.m5665a(z);
        audienceNetworkActivity.f3713j.setAdReportingFlowListener(c2192b);
        C2600x.m6689b(audienceNetworkActivity.f3713j);
        C2600x.m6683a(audienceNetworkActivity.f3705b);
        audienceNetworkActivity.f3705b.addView(audienceNetworkActivity.f3713j);
        audienceNetworkActivity.f3713j.m5663a();
    }

    /* renamed from: a */
    private void m3987a(Exception exception) {
        finish();
        C2625a.m6741b(this, "an_activity", C2626b.aa, exception);
    }

    /* renamed from: a */
    private void m3988a(String str) {
        if ("com.facebook.ads.adreporting.FINISH_AD_REPORTING_FLOW".equals(str)) {
            finish();
            return;
        }
        LocalBroadcastManager.getInstance(this).sendBroadcast(new Intent(str + ":" + this.f3707d));
    }

    /* renamed from: a */
    private boolean m3989a() {
        return this.f3708e == C2093a.REWARDED_VIDEO || this.f3708e == C2093a.REWARDED_PLAYABLE || this.f3708e == C2093a.REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD;
    }

    public static Class getAdActivity() {
        return AdInternalSettings.f4779d ? RemoteANActivity.class : AudienceNetworkActivity.class;
    }

    public void addBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.f3704a.add(backButtonInterceptor);
    }

    public void finish() {
        if (!isFinishing()) {
            if (m3989a()) {
                m3988a(aa.REWARDED_VIDEO_CLOSED.m6205a());
            } else {
                m3988a("com.facebook.ads.interstitial.dismissed");
            }
            super.finish();
        }
    }

    public void onBackPressed() {
        try {
            long currentTimeMillis = System.currentTimeMillis();
            this.f3710g += currentTimeMillis - this.f3709f;
            this.f3709f = currentTimeMillis;
            if (this.f3710g > ((long) this.f3711h)) {
                Object obj = null;
                for (BackButtonInterceptor interceptBackButton : this.f3704a) {
                    obj = interceptBackButton.interceptBackButton() ? 1 : obj;
                }
                if (obj == null) {
                    super.onBackPressed();
                }
            }
        } catch (Exception e) {
            m3987a(e);
        }
    }

    public void onConfigurationChanged(Configuration configuration) {
        try {
            if (this.f3712i instanceof C1922h) {
                ((C1922h) this.f3712i).m4479a(configuration);
            } else if (this.f3712i instanceof C2547s) {
                ((C2547s) this.f3712i).onConfigurationChanged(configuration);
            }
        } catch (Exception e) {
            m3987a(e);
        }
        super.onConfigurationChanged(configuration);
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        try {
            C1921a a;
            C2567c.m6620a();
            requestWindowFeature(1);
            getWindow().setFlags(1024, 1024);
            this.f3705b = new RelativeLayout(this);
            C2600x.m6680a(this.f3705b, (int) ViewCompat.MEASURED_STATE_MASK);
            setContentView(this.f3705b, new LayoutParams(-1, -1));
            Intent intent = getIntent();
            if (bundle != null) {
                this.f3706c = bundle.getInt(PREDEFINED_ORIENTATION_KEY, -1);
                this.f3707d = bundle.getString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
                this.f3708e = (C2093a) bundle.getSerializable(VIEW_TYPE);
            } else {
                this.f3706c = intent.getIntExtra(PREDEFINED_ORIENTATION_KEY, -1);
                this.f3707d = intent.getStringExtra(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA);
                this.f3708e = (C2093a) intent.getSerializableExtra(VIEW_TYPE);
                this.f3711h = intent.getIntExtra(SKIP_DELAY_SECONDS_KEY, 0) * 1000;
            }
            C1791b c1791b = new C1791b(getIntent(), C2087d.m5183a((Context) this));
            if (this.f3708e != null) {
                switch (this.f3708e) {
                    case FULL_SCREEN_VIDEO:
                        a = C1791b.m3967a(c1791b, this.f3705b);
                        break;
                    case REWARDED_VIDEO:
                        a = new C2547s(c1791b.f3700a, c1791b.f3702c, new C2394a(c1791b.f3700a), new C1793d(), (C1887q) c1791b.f3701b.getSerializableExtra("rewardedVideoAdDataBundle"));
                        break;
                    case REWARDED_PLAYABLE:
                        a = new C2537r(c1791b.f3700a, c1791b.f3702c, new C1793d(), (C1887q) c1791b.f3701b.getSerializableExtra("rewardedVideoAdDataBundle"));
                        break;
                    case REWARDED_VIDEO_CHOOSE_YOUR_OWN_AD:
                        a = new C2352f(c1791b.f3700a, (C1874f) c1791b.f3701b.getSerializableExtra("rewardedVideoAdDataBundle"), c1791b.f3702c, new C1793d());
                        break;
                    case INTERSTITIAL_WEB_VIEW:
                        a = new C2372h(c1791b.f3700a, c1791b.f3702c, new C1790a());
                        break;
                    case BROWSER:
                        a = new C2240b(c1791b.f3700a, c1791b.f3702c, new C1790a());
                        break;
                    case INTERSTITIAL_OLD_NATIVE_VIDEO:
                        a = C1791b.m3974f(c1791b);
                        break;
                    case INTERSTITIAL_NATIVE_VIDEO:
                        a = C1791b.m3975g(c1791b);
                        break;
                    case INTERSTITIAL_NATIVE_IMAGE:
                        a = new C2513l(c1791b.f3700a, c1791b.m3969b(), c1791b.f3702c, new C1790a());
                        break;
                    case INTERSTITIAL_NATIVE_CAROUSEL:
                        a = C1791b.m3977i(c1791b);
                        break;
                    case INTERSTITIAL_NATIVE_PLAYABLE:
                        a = new C2516m(c1791b.f3700a, c1791b.f3702c, c1791b.m3969b(), new C1790a());
                        break;
                    default:
                        a = null;
                        break;
                }
            }
            a = null;
            this.f3712i = a;
            if (this.f3712i == null) {
                C2059b.m5023a(C2058a.m5020a(null, "Unable to infer viewType from intent or savedInstanceState"));
                m3988a("com.facebook.ads.interstitial.error");
                finish();
                return;
            }
            this.f3712i.mo5403a(intent, bundle, this);
            m3988a("com.facebook.ads.interstitial.displayed");
            this.f3709f = System.currentTimeMillis();
            Object obj = this.f3708e == C2093a.INTERSTITIAL_WEB_VIEW ? 1 : null;
            if (C2078a.m5091b(this) && this.f3708e != C2093a.BROWSER) {
                this.f3714k = new C2251c();
                this.f3714k.m5766a(intent.getStringExtra("placementId"));
                this.f3714k.m5770b(getPackageName());
                long longExtra = intent.getLongExtra(REQUEST_TIME, 0);
                if (longExtra != 0) {
                    this.f3714k.m5764a(longExtra);
                }
                View textView = new TextView(this);
                textView.setText("Debug");
                textView.setTextColor(-1);
                C2600x.m6680a(textView, Color.argb(160, 0, 0, 0));
                textView.setPadding(5, 5, 5, 5);
                ViewGroup.LayoutParams layoutParams = new LayoutParams(-2, -2);
                layoutParams.addRule(12, -1);
                layoutParams.addRule(11, -1);
                textView.setLayoutParams(layoutParams);
                OnLongClickListener c1792c = new C1792c();
                textView.setOnLongClickListener(c1792c);
                if (obj != null) {
                    this.f3705b.addView(textView);
                } else {
                    this.f3705b.setOnLongClickListener(c1792c);
                }
                this.f3705b.getOverlay().add(this.f3714k);
            }
        } catch (Exception e) {
            m3987a(e);
        }
    }

    protected void onDestroy() {
        try {
            if (m3989a()) {
                m3988a(aa.REWARDED_VIDEO_ACTIVITY_DESTROYED.m6205a());
            } else {
                m3988a("com.facebook.ads.interstitial.activity_destroyed");
            }
            if (this.f3705b != null) {
                this.f3705b.removeAllViews();
            }
            if (this.f3712i != null) {
                C1918g.m4457a(this.f3712i);
                this.f3712i.onDestroy();
                this.f3712i = null;
            }
            if (this.f3714k != null && C2078a.m5091b(this)) {
                this.f3714k.m5769b();
            }
            if (this.f3713j != null) {
                this.f3713j.m5666b();
            }
        } catch (Exception e) {
            m3987a(e);
        }
        super.onDestroy();
    }

    public void onPause() {
        try {
            this.f3710g += System.currentTimeMillis() - this.f3709f;
            if (this.f3712i != null) {
                this.f3712i.a_(false);
            }
        } catch (Exception e) {
            m3987a(e);
        }
        super.onPause();
    }

    public void onResume() {
        super.onResume();
        try {
            this.f3709f = System.currentTimeMillis();
            if (this.f3712i != null) {
                this.f3712i.mo5406b(false);
            }
        } catch (Exception e) {
            m3987a(e);
        }
    }

    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            if (this.f3712i != null) {
                this.f3712i.mo5404a(bundle);
            }
            bundle.putInt(PREDEFINED_ORIENTATION_KEY, this.f3706c);
            bundle.putString(AUDIENCE_NETWORK_UNIQUE_ID_EXTRA, this.f3707d);
            bundle.putSerializable(VIEW_TYPE, this.f3708e);
        } catch (Exception e) {
            m3987a(e);
        }
    }

    public void onStart() {
        super.onStart();
        try {
            if (this.f3706c != -1) {
                try {
                    setRequestedOrientation(this.f3706c);
                } catch (IllegalStateException e) {
                }
            }
        } catch (Exception e2) {
            m3987a(e2);
        }
    }

    public void removeBackButtonInterceptor(BackButtonInterceptor backButtonInterceptor) {
        this.f3704a.remove(backButtonInterceptor);
    }
}
