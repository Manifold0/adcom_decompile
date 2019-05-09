package com.tapjoy;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Looper;
import android.util.Pair;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewParent;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.RelativeLayout;
import com.facebook.ads.AudienceNetworkActivity;
import com.moat.analytics.mobile.tjy.MoatAdEvent;
import com.moat.analytics.mobile.tjy.MoatAdEventType;
import com.moat.analytics.mobile.tjy.MoatFactory;
import com.moat.analytics.mobile.tjy.ReactiveVideoTracker;
import com.moat.analytics.mobile.tjy.ReactiveVideoTrackerPlugin;
import com.tapjoy.TJAdUnitConstants.String;
import com.tapjoy.TapjoyErrorMessage.ErrorType;
import com.tapjoy.internal.ct;
import com.tapjoy.mraid.view.MraidView;
import com.tapjoy.mraid.view.MraidView.PLACEMENT_TYPE;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@SuppressLint({"SetJavaScriptEnabled"})
public class TJAdUnitJSBridge implements TJWebViewJSInterfaceListener {
    /* renamed from: a */
    final ConcurrentLinkedQueue f6873a;
    public boolean allowRedirect;
    /* renamed from: b */
    private TJWebViewJSInterface f6874b;
    /* renamed from: c */
    private TJAdUnitJSBridge f6875c;
    public boolean closeRequested;
    public boolean customClose;
    /* renamed from: d */
    private Context f6876d;
    public boolean didLaunchOtherActivity;
    /* renamed from: e */
    private TJAdUnitActivity f6877e;
    /* renamed from: f */
    private TJAdUnit f6878f;
    /* renamed from: g */
    private WebView f6879g;
    /* renamed from: h */
    private TJSplitWebView f6880h;
    /* renamed from: i */
    private ProgressDialog f6881i;
    /* renamed from: j */
    private View f6882j;
    /* renamed from: k */
    private boolean f6883k;
    /* renamed from: l */
    private ReactiveVideoTracker f6884l;
    /* renamed from: m */
    private HashMap f6885m;
    /* renamed from: n */
    private Handler f6886n;
    public String otherActivityCallbackID;
    public String splitWebViewCallbackID;

    public interface AdUnitAsyncTaskListner {
        void onComplete(boolean z);
    }

    @TargetApi(11)
    /* renamed from: com.tapjoy.TJAdUnitJSBridge$a */
    class C2799a extends AsyncTask {
        /* renamed from: a */
        WebView f6871a;
        /* renamed from: b */
        final /* synthetic */ TJAdUnitJSBridge f6872b;

        protected final /* bridge */ /* synthetic */ Object doInBackground(Object[] objArr) {
            return (Boolean[]) objArr;
        }

        protected final /* synthetic */ void onPostExecute(Object obj) {
            Boolean[] boolArr = (Boolean[]) obj;
            final boolean booleanValue = boolArr[0].booleanValue();
            final boolean booleanValue2 = boolArr[1].booleanValue();
            if (this.f6872b.f6876d instanceof Activity) {
                TapjoyUtil.runOnMainThread(new Runnable(this) {
                    /* renamed from: c */
                    final /* synthetic */ C2799a f6870c;

                    public final void run() {
                        if (booleanValue) {
                            this.f6870c.f6871a.setVisibility(0);
                            if (booleanValue2) {
                                if (this.f6870c.f6871a.getParent() instanceof RelativeLayout) {
                                    ((RelativeLayout) this.f6870c.f6871a.getParent()).getBackground().setAlpha(0);
                                    ((RelativeLayout) this.f6870c.f6871a.getParent()).setBackgroundColor(0);
                                }
                                if (VERSION.SDK_INT >= 11) {
                                    this.f6870c.f6871a.setLayerType(1, null);
                                    return;
                                }
                                return;
                            }
                            if (this.f6870c.f6871a.getParent() instanceof RelativeLayout) {
                                ((RelativeLayout) this.f6870c.f6871a.getParent()).getBackground().setAlpha(255);
                                ((RelativeLayout) this.f6870c.f6871a.getParent()).setBackgroundColor(-1);
                            }
                            if (VERSION.SDK_INT >= 11) {
                                this.f6870c.f6871a.setLayerType(0, null);
                                return;
                            }
                            return;
                        }
                        this.f6870c.f6871a.setVisibility(4);
                        if (this.f6870c.f6871a.getParent() instanceof RelativeLayout) {
                            ((RelativeLayout) this.f6870c.f6871a.getParent()).getBackground().setAlpha(0);
                            ((RelativeLayout) this.f6870c.f6871a.getParent()).setBackgroundColor(0);
                        }
                    }
                });
            } else {
                TapjoyLog.m7128e("TJAdUnitJSBridge", "Unable to present offerwall. No Activity context provided.");
            }
        }

        public C2799a(TJAdUnitJSBridge tJAdUnitJSBridge, WebView webView) {
            this.f6872b = tJAdUnitJSBridge;
            this.f6871a = webView;
        }
    }

    public TJAdUnitJSBridge(Context c, TJAdUnit tjAdUnit) {
        this(c, tjAdUnit.getWebView());
        this.f6878f = tjAdUnit;
    }

    public TJAdUnitJSBridge(Context c, WebView w) {
        this.f6882j = null;
        this.didLaunchOtherActivity = false;
        this.allowRedirect = true;
        this.otherActivityCallbackID = null;
        this.customClose = false;
        this.closeRequested = false;
        this.splitWebViewCallbackID = null;
        this.f6873a = new ConcurrentLinkedQueue();
        TapjoyLog.m7129i("TJAdUnitJSBridge", "creating AdUnit/JS Bridge");
        this.f6876d = c;
        this.f6879g = w;
        this.f6875c = this;
        if (this.f6879g == null) {
            TapjoyLog.m7127e("TJAdUnitJSBridge", new TapjoyErrorMessage(ErrorType.SDK_ERROR, "Cannot create AdUnitJSBridge -- webview is NULL"));
            return;
        }
        this.f6874b = new TJWebViewJSInterface(this.f6879g, this);
        this.f6879g.addJavascriptInterface(this.f6874b, TJAdUnitConstants.JAVASCRIPT_INTERFACE_ID);
        setEnabled(true);
    }

    public void onDispatchMethod(String methodName, JSONObject json) {
        String str = null;
        if (this.f6883k) {
            try {
                str = json.optString(String.CALLBACK_ID, null);
                JSONObject jSONObject = json.getJSONObject("data");
                Method method = TJAdUnitJSBridge.class.getMethod(methodName, new Class[]{JSONObject.class, String.class});
                TapjoyLog.m7126d("TJAdUnitJSBridge", "Dispatching method: " + method + " with data=" + jSONObject + "; callbackID=" + str);
                method.invoke(this.f6875c, new Object[]{jSONObject, str});
                return;
            } catch (Exception e) {
                e.printStackTrace();
                invokeJSCallback(str, Boolean.FALSE);
                return;
            }
        }
        TapjoyLog.m7126d("TJAdUnitJSBridge", "Bridge currently disabled. Adding " + methodName + " to message queue");
        this.f6873a.add(new Pair(methodName, json));
    }

    public void alert(JSONObject json, final String callbackID) {
        String string;
        CharSequence charSequence;
        JSONArray jSONArray;
        Exception e;
        JSONArray jSONArray2;
        Object obj;
        Context context;
        AlertDialog create;
        TapjoyLog.m7126d("TJAdUnitJSBridge", "alert_method: " + json);
        CharSequence charSequence2 = "";
        String str = "";
        JSONArray jSONArray3 = null;
        try {
            charSequence2 = json.getString("title");
            string = json.getString("message");
            try {
                charSequence = string;
                jSONArray = json.getJSONArray("buttons");
            } catch (Exception e2) {
                e = e2;
                invokeJSCallback(callbackID, Boolean.FALSE);
                e.printStackTrace();
                jSONArray2 = jSONArray3;
                obj = string;
                jSONArray = jSONArray2;
                context = this.f6877e;
                if (context != null) {
                    TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot alert -- TJAdUnitActivity is null");
                }
                create = new Builder(context).setTitle(charSequence2).setMessage(charSequence).create();
                if (jSONArray != null) {
                }
                invokeJSCallback(callbackID, Boolean.FALSE);
                return;
            }
        } catch (Exception e3) {
            Exception exception = e3;
            string = str;
            e = exception;
            invokeJSCallback(callbackID, Boolean.FALSE);
            e.printStackTrace();
            jSONArray2 = jSONArray3;
            obj = string;
            jSONArray = jSONArray2;
            context = this.f6877e;
            if (context != null) {
                create = new Builder(context).setTitle(charSequence2).setMessage(charSequence).create();
                if (jSONArray != null) {
                }
                invokeJSCallback(callbackID, Boolean.FALSE);
                return;
            }
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot alert -- TJAdUnitActivity is null");
        }
        context = this.f6877e;
        if (context != null) {
            create = new Builder(context).setTitle(charSequence2).setMessage(charSequence).create();
            if (jSONArray != null || jSONArray.length() == 0) {
                invokeJSCallback(callbackID, Boolean.FALSE);
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                int i2;
                switch (i) {
                    case 0:
                        i2 = -2;
                        break;
                    case 1:
                        i2 = -3;
                        break;
                    default:
                        i2 = -1;
                        break;
                }
                try {
                    arrayList.add(jSONArray.getString(i));
                } catch (Exception e4) {
                    e4.printStackTrace();
                }
                create.setButton(i2, (CharSequence) arrayList.get(i), new OnClickListener(this) {
                    /* renamed from: b */
                    final /* synthetic */ TJAdUnitJSBridge f6846b;

                    public final void onClick(DialogInterface dialog, int which) {
                        int i = 0;
                        switch (which) {
                            case -3:
                                i = 1;
                                break;
                            case -1:
                                i = 2;
                                break;
                        }
                        try {
                            this.f6846b.invokeJSCallback(callbackID, Integer.valueOf(i));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            create.setCancelable(false);
            create.setCanceledOnTouchOutside(false);
            create.show();
            return;
        }
        TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot alert -- TJAdUnitActivity is null");
    }

    public void checkAppInstalled(JSONObject json, String callbackID) {
        String str = "";
        try {
            str = json.getString(String.BUNDLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (str != null && str.length() > 0) {
            for (ApplicationInfo applicationInfo : this.f6876d.getPackageManager().getInstalledApplications(0)) {
                if (applicationInfo.packageName.equals(str)) {
                    invokeJSCallback(callbackID, Boolean.TRUE);
                    return;
                }
            }
        }
        invokeJSCallback(callbackID, Boolean.FALSE);
    }

    public void getInstalledAppData(JSONObject json, String callbackID) {
        PackageManager packageManager = this.f6876d.getPackageManager();
        List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(0);
        JSONArray jSONArray = new JSONArray();
        for (ApplicationInfo applicationInfo : installedApplications) {
            if ((applicationInfo.flags & 1) != 1) {
                Map hashMap = new HashMap();
                String str = applicationInfo.packageName;
                hashMap.put("packageName", str);
                hashMap.put("targetSdk", Integer.valueOf(applicationInfo.targetSdkVersion));
                try {
                    PackageInfo packageInfo = packageManager.getPackageInfo(str, 4096);
                    hashMap.put("installTime", new Date(packageInfo.firstInstallTime));
                    hashMap.put("updateTime", new Date(packageInfo.lastUpdateTime));
                    hashMap.put("versionName", packageInfo.versionName);
                    hashMap.put("versionCode", Integer.valueOf(packageInfo.versionCode));
                    jSONArray.put(new JSONObject(hashMap));
                } catch (Exception e) {
                }
            }
        }
        invokeJSCallback(callbackID, jSONArray);
    }

    public void closeRequested(Boolean shouldForceClose) {
        this.closeRequested = true;
        Map hashMap = new HashMap();
        hashMap.put("forceClose", shouldForceClose);
        invokeJSAdunitMethod(String.CLOSE_REQUESTED, hashMap);
    }

    public void getVolume(JSONObject json, String callbackID) {
        Map volumeArgs = getVolumeArgs();
        if (volumeArgs != null) {
            invokeJSCallback(callbackID, volumeArgs);
            return;
        }
        invokeJSCallback(callbackID, Boolean.valueOf(false));
    }

    public void onVolumeChanged() {
        invokeJSAdunitMethod(String.VOLUME_CHANGED, getVolumeArgs());
    }

    public HashMap getVolumeArgs() {
        if (this.f6878f == null) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "No ad unit provided");
            return null;
        }
        String volume = this.f6878f.getVolume();
        boolean isMuted = this.f6878f.isMuted();
        TapjoyLog.m7126d("TJAdUnitJSBridge", "getVolumeArgs: volume=" + volume + "; isMuted=" + isMuted);
        HashMap hashMap = new HashMap();
        hashMap.put(String.CURRENT_VOLUME, volume);
        hashMap.put(String.IS_MUTED, Boolean.valueOf(isMuted));
        return hashMap;
    }

    public void destroy() {
    }

    public void dismiss(JSONObject json, String callbackID) {
        TJAdUnitActivity tJAdUnitActivity = this.f6877e;
        if (tJAdUnitActivity != null) {
            invokeJSCallback(callbackID, Boolean.valueOf(true));
            tJAdUnitActivity.finish();
            return;
        }
        TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot dismiss -- TJAdUnitActivity is null");
        invokeJSCallback(callbackID, Boolean.valueOf(false));
    }

    public void display() {
        invokeJSAdunitMethod("display", new Object[0]);
    }

    public void displayRichMedia(final JSONObject json, String callbackID) {
        boolean z;
        String string;
        try {
            z = json.getBoolean(String.INLINE);
        } catch (Exception e) {
            z = false;
        }
        try {
            string = json.getString(String.HTML);
        } catch (Exception e2) {
            e2.printStackTrace();
            string = null;
        }
        if (string == null) {
            invokeJSCallback(callbackID, Boolean.FALSE);
        } else if (z) {
            ((Activity) this.f6876d).runOnUiThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6862b;

                public final void run() {
                    String string;
                    try {
                        string = json.getString(String.HTML);
                    } catch (Exception e) {
                        e.printStackTrace();
                        string = null;
                    }
                    if (!(this.f6862b.f6882j == null || this.f6862b.f6882j.getParent() == null)) {
                        ((ViewGroup) this.f6862b.f6882j.getParent()).removeView(this.f6862b.f6882j);
                    }
                    View mraidView = new MraidView(this.f6862b.f6876d);
                    this.f6862b.f6879g.getSettings().setJavaScriptEnabled(true);
                    mraidView.setPlacementType(PLACEMENT_TYPE.INLINE);
                    mraidView.setLayoutParams(new LayoutParams(640, 100));
                    mraidView.setInitialScale(100);
                    mraidView.setBackgroundColor(0);
                    mraidView.loadDataWithBaseURL(null, string, AudienceNetworkActivity.WEBVIEW_MIME_TYPE, AudienceNetworkActivity.WEBVIEW_ENCODING, null);
                    int width = ((WindowManager) ((Activity) this.f6862b.f6876d).getSystemService("window")).getDefaultDisplay().getWidth();
                    this.f6862b.f6882j = TapjoyUtil.scaleDisplayAd(mraidView, width);
                    ((Activity) this.f6862b.f6876d).addContentView(this.f6862b.f6882j, new LayoutParams(width, (int) (100.0d * (((double) width) / 640.0d))));
                }
            });
        } else {
            Serializable tJPlacementData = new TJPlacementData(TapjoyConnectCore.getHostURL(), string, callbackID);
            Context context = this.f6877e;
            if (context != null) {
                Intent intent = new Intent(context, TJAdUnitActivity.class);
                intent.putExtra(TJAdUnitConstants.EXTRA_TJ_PLACEMENT_DATA, tJPlacementData);
                context.startActivityForResult(intent, TJAdUnitConstants.MRAID_REQUEST_CODE);
            }
        }
    }

    public void displayStoreURL(JSONObject json, String callbackID) {
        displayURL(json, callbackID);
    }

    public void displayURL(JSONObject json, String callbackID) {
        try {
            String optString = json.optString("style");
            final String string = json.getString("url");
            final JSONObject optJSONObject = json.optJSONObject(String.SPLIT_VIEW_LAYOUT);
            final JSONArray optJSONArray = json.optJSONArray(String.SPLIT_VIEW_EXIT_HOSTS);
            if (String.STYLE_SPLIT.equals(optString)) {
                final String str = callbackID;
                TapjoyUtil.runOnMainThread(new Runnable(this) {
                    /* renamed from: e */
                    final /* synthetic */ TJAdUnitJSBridge f6867e;

                    public final void run() {
                        if (this.f6867e.f6879g != null) {
                            if (this.f6867e.f6880h == null) {
                                ViewParent parent = this.f6867e.f6879g.getParent();
                                if (parent instanceof ViewGroup) {
                                    ViewGroup viewGroup = (ViewGroup) parent;
                                    this.f6867e.f6880h = new TJSplitWebView(this.f6867e.f6876d, optJSONObject, optJSONArray, this.f6867e);
                                    viewGroup.addView(this.f6867e.f6880h, new RelativeLayout.LayoutParams(-1, -1));
                                }
                            } else {
                                this.f6867e.f6880h.setExitHosts(optJSONArray);
                                this.f6867e.f6880h.applyLayoutOption(optJSONObject);
                            }
                            if (this.f6867e.f6880h != null) {
                                this.f6867e.splitWebViewCallbackID = str;
                                this.f6867e.f6880h.loadUrl(string);
                                return;
                            }
                        }
                        this.f6867e.f6880h = null;
                        this.f6867e.splitWebViewCallbackID = null;
                        this.f6867e.invokeJSCallback(str, Boolean.FALSE);
                    }
                });
                return;
            }
            this.didLaunchOtherActivity = true;
            this.otherActivityCallbackID = callbackID;
            this.f6876d.startActivity(new Intent("android.intent.action.VIEW", Uri.parse(string)));
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.TRUE);
            e.printStackTrace();
        }
    }

    public void clearCache(JSONObject json, String callbackID) {
        if (TapjoyCache.getInstance() != null) {
            TapjoyCache.getInstance().clearTapjoyCache();
            invokeJSCallback(callbackID, Boolean.TRUE);
            return;
        }
        invokeJSCallback(callbackID, Boolean.FALSE);
    }

    public void setPrerenderLimit(JSONObject json, String callbackID) {
        try {
            TJPlacementManager.setPreRenderedPlacementLimit(json.getInt(String.TJC_PLACEMENT_PRE_RENDERED_LIMIT));
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (Exception e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to set Tapjoy placement pre-render limit. Invalid parameters.");
            invokeJSCallback(callbackID, Boolean.FALSE);
        }
    }

    public void setEventPreloadLimit(JSONObject json, String callbackID) {
        if (TapjoyCache.getInstance() != null) {
            try {
                TJPlacementManager.setCachedPlacementLimit(json.getInt(String.TJC_PLACEMENT_CACHE_LIMIT));
                invokeJSCallback(callbackID, Boolean.TRUE);
                return;
            } catch (Exception e) {
                TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to set Tapjoy cache's event preload limit. Invalid parameters.");
                invokeJSCallback(callbackID, Boolean.FALSE);
                return;
            }
        }
        invokeJSCallback(callbackID, Boolean.FALSE);
    }

    public void removeAssetFromCache(JSONObject json, String callbackID) {
        try {
            String assetURL = json.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(callbackID, Boolean.valueOf(TapjoyCache.getInstance().removeAssetFromCache(assetURL)));
                return;
            }
            invokeJSCallback(callbackID, Boolean.FALSE);
        } catch (Exception e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(callbackID, Boolean.FALSE);
        }
    }

    public void cacheAsset(JSONObject json, String callbackID) {
        String str = "";
        Long valueOf = Long.valueOf(0);
        try {
            String assetURL = json.getString("url");
            try {
                str = json.getString(TapjoyConstants.TJC_PLACEMENT_OFFER_ID);
            } catch (Exception e) {
            }
            try {
                valueOf = Long.valueOf(json.getLong(TapjoyConstants.TJC_TIME_TO_LIVE));
            } catch (Exception e2) {
            }
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(callbackID, TapjoyCache.getInstance().cacheAssetFromURL(assetURL, str, valueOf.longValue()));
                return;
            }
            invokeJSCallback(callbackID, Boolean.FALSE);
        } catch (Exception e3) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to cache video. Invalid parameters.");
            invokeJSCallback(callbackID, Boolean.FALSE);
        }
    }

    public void cachePathForURL(JSONObject json, String callbackID) {
        try {
            String url = json.getString("url");
            if (TapjoyCache.getInstance() != null) {
                invokeJSCallback(callbackID, TapjoyCache.getInstance().getPathOfCachedURL(url));
                return;
            }
            invokeJSCallback(callbackID, "");
        } catch (Exception e) {
            invokeJSCallback(callbackID, "");
        }
    }

    public void getCachedAssets(JSONObject json, String callbackID) {
        if (TapjoyCache.getInstance() != null) {
            invokeJSCallback(callbackID, TapjoyCache.getInstance().cachedAssetsToJSON());
            return;
        }
        invokeJSCallback(callbackID, "");
    }

    public void contentReady(JSONObject json, String callbackID) {
        if (this.f6878f != null) {
            this.f6878f.fireContentReady();
            invokeJSCallback(callbackID, Boolean.valueOf(true));
            return;
        }
        invokeJSCallback(callbackID, Boolean.valueOf(false));
    }

    public void setOrientation(JSONObject json, String callbackID) {
        if (this.f6878f == null) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            return;
        }
        try {
            String string = json.getString("orientation");
            int i = (string.equals("landscape") || string.equals(String.LANDSCAPE_LEFT)) ? 0 : string.equals(String.LANDSCAPE_RIGHT) ? 8 : 1;
            this.f6878f.setOrientation(i);
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void unsetOrientation(JSONObject json, String callbackID) {
        if (this.f6878f == null) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "No ad unit provided");
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            return;
        }
        try {
            this.f6878f.unsetOrientation();
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void setBackgroundColor(JSONObject json, final String callbackID) {
        try {
            String backgroundHexColor = json.getString(String.BACKGROUND_COLOR);
            if (this.f6878f != null) {
                this.f6878f.setBackgroundColor(backgroundHexColor, new AdUnitAsyncTaskListner(this) {
                    /* renamed from: b */
                    final /* synthetic */ TJAdUnitJSBridge f6833b;

                    public final void onComplete(boolean result) {
                        this.f6833b.invokeJSCallback(callbackID, Boolean.valueOf(result));
                    }
                });
                return;
            }
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to set background color. Invalid parameters.");
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void setBackgroundWebViewContent(JSONObject json, final String callbackID) {
        TapjoyLog.m7126d("TJAdUnitJSBridge", "setBackgroundWebViewContent");
        try {
            String string = json.getString(String.BACKGROUND_CONTENT);
            if (this.f6878f != null) {
                this.f6878f.setBackgroundContent(string, new AdUnitAsyncTaskListner(this) {
                    /* renamed from: b */
                    final /* synthetic */ TJAdUnitJSBridge f6835b;

                    public final void onComplete(boolean result) {
                        this.f6835b.invokeJSCallback(callbackID, Boolean.valueOf(result));
                    }
                });
                return;
            }
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to set background content. Invalid parameters.");
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void displayVideo(JSONObject json, final String callbackID) {
        try {
            String url = json.getString("url");
            if (url.length() <= 0 || url == "") {
                invokeJSCallback(callbackID, Boolean.FALSE);
                return;
            }
            this.f6878f.loadVideoUrl(url, new AdUnitAsyncTaskListner(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6837b;

                public final void onComplete(boolean result) {
                    this.f6837b.invokeJSCallback(callbackID, Boolean.valueOf(result));
                }
            });
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void playVideo(JSONObject json, String callbackID) {
        if (this.f6878f != null) {
            invokeJSCallback(callbackID, Boolean.valueOf(this.f6878f.playVideo()));
        }
    }

    public void pauseVideo(JSONObject json, String callbackID) {
        if (this.f6878f != null) {
            invokeJSCallback(callbackID, Boolean.valueOf(this.f6878f.pauseVideo()));
        }
    }

    public void clearVideo(JSONObject json, final String callbackID) {
        if (this.f6878f != null) {
            this.f6878f.clearVideo(new AdUnitAsyncTaskListner(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6839b;

                public final void onComplete(boolean result) {
                    this.f6839b.invokeJSCallback(callbackID, Boolean.valueOf(result));
                }
            });
        }
    }

    public void setVideoMute(JSONObject json, String callbackID) {
        try {
            this.f6878f.m7021a(json.getBoolean(String.ENABLED));
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (JSONException e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Failed to parse 'enabled' from json params.");
            invokeJSCallback(callbackID, Boolean.FALSE);
        }
    }

    public void log(JSONObject json, String callbackID) {
        try {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Logging message=" + json.getString("message"));
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void openApp(JSONObject json, String callbackID) {
        try {
            this.f6876d.startActivity(this.f6876d.getPackageManager().getLaunchIntentForPackage(json.getString(String.BUNDLE)));
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    @TargetApi(19)
    public void nativeEval(final JSONObject json, final String callbackID) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: c */
            final /* synthetic */ TJAdUnitJSBridge f6842c;

            public final void run() {
                try {
                    if (VERSION.SDK_INT >= 19) {
                        this.f6842c.f6879g.evaluateJavascript(json.getString(String.COMMAND), null);
                    } else {
                        this.f6842c.f6879g.loadUrl("javascript:" + json.getString(String.COMMAND));
                    }
                    this.f6842c.invokeJSCallback(callbackID, Boolean.TRUE);
                } catch (Exception e) {
                    this.f6842c.invokeJSCallback(callbackID, Boolean.FALSE);
                }
            }
        });
    }

    public void present(JSONObject json, String callbackID) {
        try {
            Boolean.valueOf(false);
            Boolean valueOf = Boolean.valueOf(false);
            Boolean valueOf2 = Boolean.valueOf(json.getString(String.VISIBLE));
            try {
                valueOf = Boolean.valueOf(json.getString("transparent"));
            } catch (Exception e) {
            }
            try {
                this.customClose = Boolean.valueOf(json.getString(String.CUSTOM_CLOSE)).booleanValue();
            } catch (Exception e2) {
            }
            new C2799a(this, this.f6879g).execute(new Boolean[]{valueOf2, valueOf});
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (Exception e3) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            e3.printStackTrace();
        }
    }

    public void triggerEvent(JSONObject json, String callbackID) {
        if (this.f6878f != null) {
            try {
                String string = json.getString("eventName");
                if (string.equals(String.VIDEO_START)) {
                    this.f6878f.fireOnVideoStart();
                } else if (string.equals(String.VIDEO_COMPLETE)) {
                    this.f6878f.fireOnVideoComplete();
                } else if (string.equals("error")) {
                    this.f6878f.fireOnVideoError("Error while trying to play video.");
                }
            } catch (Exception e) {
                TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to triggerEvent. No event name.");
            }
        }
    }

    public void invokeJSAdunitMethod(String methodName, Object... args) {
        this.f6874b.callback(new ArrayList(Arrays.asList(args)), methodName, null);
    }

    public void invokeJSAdunitMethod(String methodName, Map arguments) {
        this.f6874b.callback(arguments, methodName, null);
    }

    public void invokeJSCallback(String callbackID, Object... argArray) {
        if (ct.m7339c(callbackID)) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "invokeJSCallback -- no callbackID provided");
            return;
        }
        this.f6874b.callback(new ArrayList(Arrays.asList(argArray)), "", callbackID);
    }

    public void invokeJSCallback(String callbackID, Map arguments) {
        this.f6874b.callback(arguments, "", callbackID);
    }

    public void flushBacklogMessageQueue() {
        while (true) {
            Pair pair = (Pair) this.f6873a.poll();
            if (pair != null) {
                onDispatchMethod((String) pair.first, (JSONObject) pair.second);
            } else {
                return;
            }
        }
    }

    public void flushMessageQueue() {
        this.f6874b.flushMessageQueue();
    }

    public void setAllowRedirect(JSONObject json, String callbackID) {
        boolean z;
        try {
            z = json.getBoolean(String.ENABLED);
        } catch (Exception e) {
            z = true;
        }
        this.allowRedirect = z;
        invokeJSCallback(callbackID, Boolean.TRUE);
    }

    public void setAdUnitActivity(TJAdUnitActivity activity) {
        this.f6877e = activity;
    }

    public void setSpinnerVisible(JSONObject json, String callbackID) {
        try {
            boolean visible = json.getBoolean(String.VISIBLE);
            String title = json.optString("title");
            String message = json.optString("message");
            Context context = this.f6877e;
            if (context != null) {
                if (visible) {
                    this.f6881i = ProgressDialog.show(context, title, message);
                } else if (this.f6881i != null) {
                    this.f6881i.dismiss();
                }
                invokeJSCallback(callbackID, Boolean.TRUE);
                return;
            }
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot setSpinnerVisible -- TJAdUnitActivity is null");
            invokeJSCallback(callbackID, Boolean.FALSE);
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            e.printStackTrace();
        }
    }

    public void setCloseButtonVisible(JSONObject json, String callbackID) {
        try {
            final boolean z = json.getBoolean(String.VISIBLE);
            TapjoyUtil.runOnMainThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6844b;

                public final void run() {
                    TJAdUnitActivity e = this.f6844b.f6877e;
                    if (e != null) {
                        e.setCloseButtonVisibility(z);
                    } else {
                        TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot setCloseButtonVisible -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void setCloseButtonClickable(JSONObject json, String callbackID) {
        try {
            final boolean optBoolean = json.optBoolean(String.CLICKABLE);
            TapjoyUtil.runOnMainThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6848b;

                public final void run() {
                    TJAdUnitActivity e = this.f6848b.f6877e;
                    if (e != null) {
                        e.setCloseButtonClickable(optBoolean);
                    } else {
                        TapjoyLog.m7126d("TJAdUnitJSBridge", "Cannot setCloseButtonClickable -- TJAdUnitActivity is null");
                    }
                }
            });
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void shouldClose(JSONObject json, String callbackID) {
        TJAdUnitActivity tJAdUnitActivity = this.f6877e;
        try {
            Boolean.valueOf(false);
            if (Boolean.valueOf(json.getString(String.CLOSE)).booleanValue() && tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            invokeJSCallback(callbackID, Boolean.TRUE);
        } catch (Exception e) {
            invokeJSCallback(callbackID, Boolean.FALSE);
            if (tJAdUnitActivity != null) {
                tJAdUnitActivity.finish();
            }
            e.printStackTrace();
        }
        this.closeRequested = false;
    }

    public void setLoggingLevel(JSONObject json, String callbackID) {
        try {
            TapjoyAppSettings.getInstance().saveLoggingLevel(String.valueOf(json.getString(String.LOGGING_LEVEL)));
        } catch (Exception e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "setLoggingLevel exception " + e.getLocalizedMessage());
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void clearLoggingLevel(JSONObject json, String callbackID) {
        TapjoyAppSettings.getInstance().clearLoggingLevel();
    }

    public void attachVolumeListener(JSONObject json, String callbackID) {
        try {
            boolean z = json.getBoolean(String.ATTACH);
            int optInt = json.optInt(String.INTERVAL, TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL);
            if (optInt > 0) {
                this.f6878f.attachVolumeListener(z, optInt);
                invokeJSCallback(callbackID, Boolean.valueOf(true));
                return;
            }
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Invalid `interval` value passed to attachVolumeListener(): interval=" + optInt);
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (Exception e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "attachVolumeListener exception " + e.toString());
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            e.printStackTrace();
        }
    }

    public void initMoatVideoTracker(JSONObject json, String callbackID) {
        Activity activity = this.f6877e;
        if (activity == null) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Error from initMoatVideoTracker -- TJAdUnitActivity is null");
            invokeJSCallback(callbackID, Boolean.valueOf(false));
            return;
        }
        try {
            this.f6884l = (ReactiveVideoTracker) MoatFactory.create(activity).createCustomTracker(new ReactiveVideoTrackerPlugin(json.getString(String.PARTNER_CODE)));
            if (this.f6885m == null) {
                TapjoyLog.m7126d("TJAdUnitJSBridge", "Initializing MOAT tracking events map");
                this.f6885m = new HashMap();
                this.f6885m.put(String.VIDEO_FIRST_QUARTILE, MoatAdEventType.AD_EVT_FIRST_QUARTILE);
                this.f6885m.put(String.VIDEO_MIDPOINT, MoatAdEventType.AD_EVT_MID_POINT);
                this.f6885m.put(String.VIDEO_THIRD_QUARTILE, MoatAdEventType.AD_EVT_THIRD_QUARTILE);
                this.f6885m.put(String.VIDEO_COMPLETE, MoatAdEventType.AD_EVT_COMPLETE);
                this.f6885m.put("paused", MoatAdEventType.AD_EVT_PAUSED);
                this.f6885m.put("playing", MoatAdEventType.AD_EVT_PLAYING);
                this.f6885m.put(String.VIDEO_START, MoatAdEventType.AD_EVT_START);
                this.f6885m.put("stopped", MoatAdEventType.AD_EVT_STOPPED);
                this.f6885m.put(String.VIDEO_SKIPPED, MoatAdEventType.AD_EVT_SKIPPED);
                this.f6885m.put(String.VOLUME_CHANGED, MoatAdEventType.AD_EVT_VOLUME_CHANGE);
                this.f6885m.put(String.ENTER_FULL_SCREEN, MoatAdEventType.AD_EVT_ENTER_FULLSCREEN);
                this.f6885m.put(String.EXIT_FULL_SCREEN, MoatAdEventType.AD_EVT_EXIT_FULLSCREEN);
            }
            this.f6886n = new Handler(Looper.getMainLooper());
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "initMoatVideoTracker exception " + e.toString());
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void startMoatVideoTracker(JSONObject json, final String callbackID) {
        try {
            final Integer valueOf = Integer.valueOf(json.getInt(String.VIDEO_LENGTH));
            final Map hashMap = new HashMap();
            JSONObject jSONObject = json.getJSONObject(String.AD_IDS);
            if (jSONObject != null) {
                Iterator keys = jSONObject.keys();
                while (keys.hasNext()) {
                    String str = (String) keys.next();
                    hashMap.put(str, jSONObject.getString(str));
                }
            }
            this.f6886n.post(new Runnable(this) {
                /* renamed from: d */
                final /* synthetic */ TJAdUnitJSBridge f6852d;

                public final void run() {
                    boolean trackVideoAd;
                    if (this.f6852d.f6884l != null) {
                        trackVideoAd = this.f6852d.f6884l.trackVideoAd(hashMap, valueOf, this.f6852d.f6879g);
                    } else {
                        trackVideoAd = false;
                    }
                    this.f6852d.invokeJSCallback(callbackID, Boolean.valueOf(trackVideoAd));
                }
            });
        } catch (Exception e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "startMoatVideoTracker exception " + e.toString());
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void triggerMoatVideoEvent(JSONObject json, String callbackID) {
        try {
            Integer valueOf = Integer.valueOf(json.getInt(String.CURRENT_VIDEO_TIME));
            String string = json.getString("eventName");
            MoatAdEventType moatAdEventType = null;
            if (this.f6885m != null) {
                moatAdEventType = (MoatAdEventType) this.f6885m.get(string);
            }
            if (moatAdEventType == null) {
                TapjoyLog.m7126d("TJAdUnitJSBridge", "eventName:" + string + " has no matching MOAT event");
                invokeJSCallback(callbackID, Boolean.valueOf(false));
                return;
            }
            TapjoyLog.m7126d("TJAdUnitJSBridge", "Sending MOAT event: " + moatAdEventType);
            final MoatAdEvent moatAdEvent = new MoatAdEvent(moatAdEventType, valueOf);
            this.f6886n.post(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ TJAdUnitJSBridge f6854b;

                public final void run() {
                    if (this.f6854b.f6884l != null) {
                        this.f6854b.f6884l.dispatchEvent(moatAdEvent);
                    }
                }
            });
            invokeJSCallback(callbackID, Boolean.valueOf(true));
        } catch (Exception e) {
            TapjoyLog.m7126d("TJAdUnitJSBridge", "triggerMoatVideoEvent exception " + e.toString());
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        }
    }

    public void startUsageTrackingEvent(JSONObject json, String callbackID) {
        try {
            String string = json.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.m7126d("TJAdUnitJSBridge", "Empty name for startUsageTrackingEvent");
                invokeJSCallback(callbackID, Boolean.valueOf(false));
                return;
            }
            if (this.f6878f != null) {
                this.f6878f.startAdContentTracking(string, json);
                invokeJSCallback(callbackID, Boolean.valueOf(true));
                return;
            }
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (JSONException e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to startUsageTrackingEvent. Invalid parameters: " + e);
        }
    }

    public void endUsageTrackingEvent(JSONObject json, String callbackID) {
        try {
            String string = json.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.m7126d("TJAdUnitJSBridge", "Empty name for endUsageTrackingEvent");
                invokeJSCallback(callbackID, Boolean.valueOf(false));
                return;
            }
            if (this.f6878f != null) {
                this.f6878f.endAdContentTracking(string, json);
                invokeJSCallback(callbackID, Boolean.valueOf(true));
                return;
            }
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (JSONException e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to endUsageTrackingEvent. Invalid parameters: " + e);
        }
    }

    public void sendUsageTrackingEvent(JSONObject json, String callbackID) {
        try {
            String string = json.getString("name");
            if (string.isEmpty()) {
                TapjoyLog.m7126d("TJAdUnitJSBridge", "Empty name for sendUsageTrackingEvent");
                invokeJSCallback(callbackID, Boolean.valueOf(false));
                return;
            }
            if (this.f6878f != null) {
                this.f6878f.sendAdContentTracking(string, json);
                invokeJSCallback(callbackID, Boolean.valueOf(true));
                return;
            }
            invokeJSCallback(callbackID, Boolean.valueOf(false));
        } catch (JSONException e) {
            TapjoyLog.m7131w("TJAdUnitJSBridge", "Unable to sendUsageTrackingEvent. Invalid parameters: " + e);
        }
    }

    public void hasSplitView(JSONObject json, final String callbackID) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJAdUnitJSBridge f6856b;

            public final void run() {
                if (this.f6856b.f6880h != null) {
                    this.f6856b.invokeJSCallback(callbackID, Boolean.TRUE);
                    return;
                }
                this.f6856b.invokeJSCallback(callbackID, Boolean.FALSE);
            }
        });
    }

    public void dismissSplitView(JSONObject json, final String callbackID) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJAdUnitJSBridge f6858b;

            public final void run() {
                if (this.f6858b.f6880h != null) {
                    if (callbackID != null) {
                        this.f6858b.invokeJSCallback(callbackID, Boolean.TRUE);
                    }
                    if (this.f6858b.splitWebViewCallbackID != null) {
                        this.f6858b.invokeJSCallback(this.f6858b.splitWebViewCallbackID, Boolean.TRUE);
                        this.f6858b.splitWebViewCallbackID = null;
                    }
                    ((ViewGroup) this.f6858b.f6880h.getParent()).removeView(this.f6858b.f6880h);
                    this.f6858b.f6880h = null;
                } else if (callbackID != null) {
                    this.f6858b.invokeJSCallback(callbackID, Boolean.FALSE);
                }
            }
        });
    }

    public void getSplitViewURL(JSONObject json, final String callbackID) {
        TapjoyUtil.runOnMainThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ TJAdUnitJSBridge f6860b;

            public final void run() {
                if (this.f6860b.f6880h != null) {
                    this.f6860b.invokeJSCallback(callbackID, this.f6860b.f6880h.getLastUrl());
                    return;
                }
                this.f6860b.invokeJSCallback(callbackID, JSONObject.NULL);
            }
        });
    }

    public void setEnabled(boolean toggle) {
        this.f6883k = toggle;
        if (this.f6883k) {
            flushBacklogMessageQueue();
        }
    }

    public void onVideoReady(int videoDuration, int videoWidth, int videoHeight) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_READY_EVENT);
        hashMap.put(String.VIDEO_DURATION, Integer.valueOf(videoDuration));
        hashMap.put(String.VIDEO_WIDTH, Integer.valueOf(videoWidth));
        hashMap.put(String.VIDEO_HEIGHT, Integer.valueOf(videoHeight));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoStarted(int currentTime) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_START_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(currentTime));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoProgress(int currentTime) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_PROGRESS_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(currentTime));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoPaused(int currentTime) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_PAUSE_EVENT);
        hashMap.put(String.VIDEO_CURRENT_TIME, Integer.valueOf(currentTime));
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoCompletion() {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_COMPLETE_EVENT);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoInfo(String infoMessage) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_INFO_EVENT);
        hashMap.put(String.VIDEO_INFO, infoMessage);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }

    public void onVideoError(String errorMessage) {
        Map hashMap = new HashMap();
        hashMap.put(String.VIDEO_EVENT_NAME, String.VIDEO_ERROR_EVENT);
        hashMap.put("error", errorMessage);
        invokeJSAdunitMethod(String.VIDEO_EVENT, hashMap);
    }
}
