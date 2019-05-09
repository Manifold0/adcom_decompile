package com.kongregate.android.internal.sdk.unity;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.api.AnalyticsServices.AutoEventListener;
import com.kongregate.android.api.KongregateAPI;
import com.kongregate.android.api.KongregateEvent;
import com.kongregate.android.api.KongregateEventBundleListener;
import com.kongregate.android.api.KongregateEventListener;
import com.kongregate.android.internal.sdk.NativeAPI;
import com.kongregate.android.internal.util.C0561i;
import com.kongregate.android.internal.util.C0562j;
import com.kongregate.p000o.p003a.C0582d.C0532b;
import com.kongregate.p000o.p003a.C0582d.C0534c;
import com.kongregate.p000o.p006c.C0626d;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONObject;

public class UnityWrapper {
    private static volatile UnityWrapper sIntance;
    private final KongregateAPI mAPI;
    private final boolean mAutoReposition;
    private View mButton;
    private RelativeLayout mButtonLayout;
    private int mButtonSize = 48;
    private int mButtonX = 10;
    private int mButtonY = 10;
    private String mEventListenerGameObject;
    private String mEventListenerMethodName;
    private boolean mNativeRendering = false;
    private boolean mReady = false;
    private Method mSendUnityMessageMethod;

    /* renamed from: com.kongregate.android.internal.sdk.unity.UnityWrapper$1 */
    class C05301 implements KongregateEventListener {
        /* renamed from: a */
        final /* synthetic */ UnityWrapper f673a;

        C05301(UnityWrapper unityWrapper) {
            this.f673a = unityWrapper;
        }

        public void onEvent(String str) {
            if (KongregateEvent.READY.equals(str)) {
                this.f673a.mReady = true;
            }
            this.f673a.sendEventToUnity(str);
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.unity.UnityWrapper$5 */
    class C05365 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityWrapper f686a;

        C05365(UnityWrapper unityWrapper) {
            this.f686a = unityWrapper;
        }

        public void run() {
            if (this.f686a.mButtonLayout != null) {
                this.f686a.mButtonLayout.setVisibility(4);
            }
        }
    }

    /* renamed from: com.kongregate.android.internal.sdk.unity.UnityWrapper$8 */
    class C05398 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ UnityWrapper f693a;

        C05398(UnityWrapper unityWrapper) {
            this.f693a = unityWrapper;
        }

        public void run() {
            this.f693a.resizeButton();
        }
    }

    public static UnityWrapper initializeWrapper(Activity activity, long j, String str, String str2) {
        int i = 0;
        try {
            NativeAPI nativeAPI = (NativeAPI) APIBootstrap.initializeNativeAPI(activity, j, str, str2);
            if (nativeAPI.m371a(KongregateAPI.KONGREGATE_OPTION_AUTO_REPOSITION, Boolean.valueOf(false)).booleanValue()) {
                i = 1;
            }
            sIntance = new UnityWrapper(nativeAPI, i);
            log("initializing API with " + activity + " " + j);
            return sIntance;
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public static UnityWrapper getInstance() {
        if (sIntance != null) {
            return sIntance;
        }
        throw new IllegalStateException("Must initialize wrapper before calling getInstance");
    }

    private UnityWrapper(KongregateAPI kongregateAPI, int i) {
        boolean z = false;
        if (i != 0) {
            z = true;
        }
        this.mAutoReposition = z;
        this.mAPI = kongregateAPI;
        this.mAPI.addEventListener(new C05301(this));
    }

    public String KongregateAPIAnalyticsGetKongProperties() {
        try {
            return this.mAPI.analytics().getKongPropertiesJSON();
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public String KongregateAPIAnalyticsGetResourceNames() {
        try {
            return this.mAPI.analytics().getResourceNamesAsJson();
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public String KongregateAPIAnalyticsGetResourceAsString(String str, String str2, String str3) {
        try {
            return this.mAPI.analytics().getResourceAsString(str, str2, str3);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public int KongregateAPIAnalyticsGetResourceAsInt(String str, String str2, int i) {
        try {
            return this.mAPI.analytics().getResourceAsInt(str, str2, i);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public boolean KongregateAPIAnalyticsGetResourceAsBool(String str, String str2, boolean z) {
        try {
            return this.mAPI.analytics().getResourceAsBool(str, str2, z);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public float KongregateAPIAnalyticsGetResourceAsFloat(String str, String str2, float f) {
        try {
            return this.mAPI.analytics().getResourceAsFloat(str, str2, f);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsFinishPromoAward(String str) {
        try {
            this.mAPI.analytics().finishPromoAward(str);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIStatsSubmit(String str, long j) {
        try {
            log("submitting stats " + str + " " + j);
            this.mAPI.stats().submit(str, j);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsAddEvent(String str, String str2, String str3) {
        try {
            this.mAPI.analytics().addEvent(str, str2, str3);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsUpdateCommonProps(String str) {
        try {
            this.mAPI.analytics().setCommonProperties(str);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsSetAutoEventListener(final String str, final String str2) {
        final Method unitySendMessage = getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            log("Failed to set auto event listener: " + str + " : " + str2);
            return;
        }
        log("Setting auto event listener to Unity method: " + str + "." + str2);
        this.mAPI.analytics().setAutoEventListener(new AutoEventListener(this) {
            /* renamed from: d */
            final /* synthetic */ UnityWrapper f677d;

            public void onAutoEvent(String str, Map<String, Object> map) {
                try {
                    String a = C0561i.m734a((Map) map, false);
                    if (str != null) {
                        a = str + " " + a;
                    } else {
                        a = str;
                    }
                    UnityWrapper.log("Sending auto event callback to Unity: " + str);
                    unitySendMessage.invoke(null, new Object[]{str, str2, a});
                } catch (IllegalAccessException e) {
                    UnityWrapper.log("Failed sending message: IllegalAccessException " + e.getMessage());
                } catch (InvocationTargetException e2) {
                    UnityWrapper.log("Failed sending message: InvocationTargetException " + e2.getMessage());
                }
            }
        });
    }

    public void KongregateAPIAnalyticsAddFilterType(String str) {
        log("adding filter type: " + str);
        this.mAPI.analytics().addFilterType(str);
    }

    public void KongregateAPIAnalyticsGameUserUpdate(String str) {
        try {
            this.mAPI.analytics().gameUserUpdate(str);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public String KongregateAPIAnalyticsGetInstallReferrer() {
        return this.mAPI.analytics().getInstallReferrer();
    }

    public String KongregateAPIAnalyticsGetAutoPropertiesJSON() {
        String autoPropertiesJSON = this.mAPI.analytics().getAutoPropertiesJSON();
        return autoPropertiesJSON != null ? autoPropertiesJSON : "";
    }

    public String KongregateAPIAnalyticsGetAutoStringProperty(String str) {
        return this.mAPI.analytics().getAutoStringProperty(str);
    }

    public String KongregateAPIAnalyticsGetAutoUTCProperty(String str) {
        return this.mAPI.analytics().getAutoUTCProperty(str);
    }

    public long KongregateAPIAnalyticsGetAutoLongProperty(String str) {
        return this.mAPI.analytics().getAutoLongProperty(str);
    }

    public int KongregateAPIAnalyticsGetAutoIntProperty(String str) {
        return this.mAPI.analytics().getAutoIntProperty(str);
    }

    public boolean KongregateAPIAnalyticsGetAutoBoolProperty(String str) {
        return this.mAPI.analytics().getAutoBoolProperty(str);
    }

    public double KongregateAPIAnalyticsGetAutoDoubleProperty(String str) {
        return this.mAPI.analytics().getAutoDoubleProperty(str);
    }

    public void KongregateAPIAnalyticsSwrveButtonListener(String str, String str2) {
        KongregateAPIAnalyticsDeltaButtonListener(str, str2);
    }

    public void KongregateAPIAnalyticsDeltaButtonListener(final String str, final String str2) {
        final Method unitySendMessage = getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            log("Failed to set Delta button listener: " + str + " : " + str2);
            return;
        }
        log("Setting Delta button listener to Unity method: " + str + "." + str2);
        this.mAPI.analytics().setDeltaCustomButtonListener(new C0532b(this) {
            /* renamed from: d */
            final /* synthetic */ UnityWrapper f681d;

            /* renamed from: a */
            public void mo1241a(String str, String str2) {
                try {
                    String str3 = str + " " + str2;
                    UnityWrapper.log("Sending event to Unity: " + str);
                    unitySendMessage.invoke(null, new Object[]{str, str2, str3});
                } catch (IllegalAccessException e) {
                    UnityWrapper.log("Failed sending message: IllegalAccessException " + e.getMessage());
                } catch (InvocationTargetException e2) {
                    UnityWrapper.log("Failed sending message: InvocationTargetException " + e2.getMessage());
                }
            }

            /* renamed from: b */
            public void mo1242b(String str, String str2) {
                try {
                    String str3 = str + " " + str2;
                    UnityWrapper.log("Sending event to Unity: " + str);
                    unitySendMessage.invoke(null, new Object[]{str, str2, str3});
                } catch (IllegalAccessException e) {
                    UnityWrapper.log("Failed sending message: IllegalAccessException " + e.getMessage());
                } catch (InvocationTargetException e2) {
                    UnityWrapper.log("Failed sending message: InvocationTargetException " + e2.getMessage());
                }
            }
        });
    }

    public void KongregateAPIAnalyticsDeltaParameterListener(final String str, final String str2) {
        final Method unitySendMessage = getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            log("Failed to set Delta parameter listener: " + str + " : " + str2);
            return;
        }
        log("Setting Delta parameter listener to Unity method: " + str + "." + str2);
        this.mAPI.analytics().setDeltaCustomParamListener(new C0534c(this) {
            /* renamed from: d */
            final /* synthetic */ UnityWrapper f685d;

            /* renamed from: a */
            public void mo1243a(JSONObject jSONObject) {
                try {
                    UnityWrapper.log("Sending event to Unity: " + jSONObject.toString());
                    unitySendMessage.invoke(null, new Object[]{str, str2, r0});
                } catch (IllegalAccessException e) {
                    UnityWrapper.log("Failed sending message: IllegalAccessException " + e.getMessage());
                } catch (InvocationTargetException e2) {
                    UnityWrapper.log("Failed sending message: InvocationTargetException " + e2.getMessage());
                }
            }
        });
    }

    private Method getUnitySendMessage() {
        Method method = null;
        try {
            method = Class.forName("com.unity3d.player.UnityPlayer").getMethod("UnitySendMessage", new Class[]{String.class, String.class, String.class});
        } catch (ClassNotFoundException e) {
            log("could not find UnityPlayer class");
        } catch (NoSuchMethodException e2) {
            log("could not find method UnitySendMessage");
        }
        return method;
    }

    public String KongregateAPIServicesGetUsername() {
        log("getting username");
        return this.mAPI.services().getUsername();
    }

    public long KongregateAPIServicesGetUserId() {
        log("getting user id");
        return this.mAPI.services().getUserId();
    }

    public String KongregateAPIServicesGetGameAuthToken() {
        log("getting game auth token");
        return this.mAPI.services().getGameAuthToken();
    }

    public boolean KongregateAPIServicesIsGuest() {
        log("checking for guest");
        return this.mAPI.services().isGuest();
    }

    public boolean KongregateAPIServicesHasKongPlus() {
        return this.mAPI.services().hasKongPlus();
    }

    public boolean KongregateAPIServicesHasUnreadGuildMessages() {
        return this.mAPI.services().hasUnreadGuildMessages();
    }

    public int KongregateAPIServicesGetNotificationCount() {
        return this.mAPI.services().getNotificationCount();
    }

    public void KongregateAPIServicesSetCharacterToken(String str) {
        log("set character token");
        this.mAPI.services().setCharacterToken(str);
    }

    public void KongregateAPIMobileButtonHide(Activity activity) {
        if (this.mNativeRendering) {
            log("hide button");
            C0626d.m968b(new C05365(this));
        }
    }

    public void KongregateAPIMobileButtonShow(final Activity activity) {
        if (this.mNativeRendering) {
            activity.runOnUiThread(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ UnityWrapper f688b;

                public void run() {
                    UnityWrapper.log("show button");
                    this.f688b.layoutButton(activity);
                }
            });
        }
    }

    public void KongregateAPIMobileButtonSetNativeRendering(boolean z) {
        log("set native rendering to " + z);
        this.mNativeRendering = z;
    }

    public void KongregateAPIMobileButtonSetX(int i) {
        log("set button x to " + i);
        setButtonPosition(i, this.mButtonY, this.mButtonSize);
    }

    public void KongregateAPIMobileButtonSetY(int i) {
        log("set button y to " + i);
        setButtonPosition(this.mButtonX, i, this.mButtonSize);
    }

    public void KongregateAPIMobileButtonSetSize(int i) {
        log("set button size to " + i);
        setButtonPosition(this.mButtonX, this.mButtonY, i);
    }

    public void KongregateAPIAnalyticsStartPurchase(String str, int i, String str2) {
        log("start purchase: " + str);
        try {
            this.mAPI.analytics().startPurchase(str, str2);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsFinishPurchase(String str, String str2, String str3, String str4) {
        log("finish purchase: " + str);
        try {
            this.mAPI.analytics().finishPurchase(str, str2, str3, str4);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsTrackPurchase(String str, String str2) {
        log("track purchase: " + str);
        try {
            this.mAPI.analytics().trackPurchase(str, str2);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIAnalyticsStart(Activity activity) {
        try {
            this.mAPI.analytics().start(activity);
        } catch (Throwable e) {
            C0626d.m966a(e);
            throw e;
        }
    }

    public void KongregateAPIMobileOpenKongregateWindow(Activity activity, String str, String str2) {
        log("open Kongregate window: " + str + " : " + str2);
        this.mAPI.mobile().openKongregateWindow(activity, str, str2);
    }

    public void KongregateAPIMobileCloseKongregateWindow(Activity activity) {
        log("close Kongregate window");
        this.mAPI.mobile().closeKongregateWindow(activity);
    }

    public void KongregateAPIMobileTrigger(Activity activity, String str) {
        log("trigger " + str);
        this.mAPI.mobile().trigger(str);
    }

    public void KongregateAPISetEventListener(String str, String str2) {
        this.mSendUnityMessageMethod = getUnitySendMessage();
        if (this.mSendUnityMessageMethod != null) {
            this.mEventListenerGameObject = str;
            this.mEventListenerMethodName = str2;
            log("set event listener " + str + " " + str2);
            return;
        }
        log("error setting event listener: " + str + " " + str2);
    }

    public void KongregateAPISetEventBundleListener(final String str, final String str2) {
        final Method unitySendMessage = getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            log("Failed to set event bundle listener: " + str + " : " + str2);
            return;
        }
        log("Setting event bundle listener to Unity method: " + str + "." + str2);
        this.mAPI.addEventBundleListener(new KongregateEventBundleListener(this) {
            /* renamed from: d */
            final /* synthetic */ UnityWrapper f692d;

            public void onKongregateEventBundle(String str, String str2) {
                if (str2 != null) {
                    try {
                        str = str + " " + str2;
                    } catch (IllegalAccessException e) {
                        UnityWrapper.log("Failed sending message: IllegalAccessException " + e.getMessage());
                        return;
                    } catch (InvocationTargetException e2) {
                        UnityWrapper.log("Failed sending message: InvocationTargetException " + e2.getMessage());
                        return;
                    }
                }
                UnityWrapper.log("Sending event bundle to Unity: " + str);
                unitySendMessage.invoke(null, new Object[]{str, str2, str});
            }
        });
    }

    public void KongregateAPIMessageReceived(String str) {
        log("message received " + str);
    }

    public boolean KongregateAPIIsReady() {
        return this.mReady;
    }

    public void KongregateAPIMtxRequestUserItemList() {
        this.mAPI.mtx().requestUserItemList();
    }

    public boolean KongregateAPIMtxHasItem(String str) {
        return this.mAPI.mtx().hasItem(str);
    }

    public void KongregateAPIMtxVerifyGooglePurchase(String str, String str2) {
        this.mAPI.mtx().verifyTransaction(str, str2);
    }

    public String KongregateAPIMtxReceiptVerificationStatus(String str) {
        return this.mAPI.mtx().receiptVerificationStatus(str).toString();
    }

    public void KongregateAPIOnPause(Activity activity) {
        this.mAPI.onPause(activity);
    }

    public void KongregateAPIOnResume(Activity activity) {
        this.mAPI.onResume(activity);
    }

    public String KongregateAPIMobileGetOpenURL(Activity activity) {
        Uri openURL = this.mAPI.mobile().getOpenURL();
        return openURL != null ? openURL.toString() : null;
    }

    private static void log(String str) {
        C0562j.m753a("UnityWrapper : " + str);
    }

    private void sendEventToUnity(String str) {
        if (this.mSendUnityMessageMethod != null && this.mEventListenerGameObject != null && this.mEventListenerMethodName != null) {
            try {
                log("Sending event to Unity: " + str);
                this.mSendUnityMessageMethod.invoke(null, new Object[]{this.mEventListenerGameObject, this.mEventListenerMethodName, str});
            } catch (IllegalAccessException e) {
                log("Failed sending message: IllegalAccessException " + e.getMessage());
            } catch (InvocationTargetException e2) {
                log("Failed sending message: InvocationTargetException " + e2.getMessage());
            }
        }
    }

    private void layoutButton(Activity activity) {
        ViewGroup viewGroup = (ViewGroup) activity.getWindow().getDecorView().findViewById(16908290);
        if (this.mButton == null) {
            this.mButton = this.mAPI.mobile().getButton(activity.getApplicationContext());
            this.mButtonLayout = new RelativeLayout(activity);
            this.mButtonLayout.addView(this.mButton);
        } else {
            ViewGroup viewGroup2 = (ViewGroup) this.mButtonLayout.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.mButtonLayout);
            }
        }
        resizeButton();
        this.mButtonLayout.setVisibility(0);
        viewGroup.addView(this.mButtonLayout);
        viewGroup.bringChildToFront(this.mButtonLayout);
    }

    private void resizeButton() {
        if (this.mButton != null) {
            this.mButtonLayout.setLayoutParams(new LayoutParams(-1, -1));
            this.mButtonLayout.setPadding(getSize(this.mButtonX), getSize(this.mButtonY), 0, 0);
            this.mButton.setLayoutParams(new LayoutParams(getSize(this.mButtonSize), getSize(this.mButtonSize)));
        }
    }

    private void setButtonPosition(int i, int i2, int i3) {
        this.mButtonX = i;
        this.mButtonY = i2;
        this.mButtonSize = i3;
        C0626d.m968b(new C05398(this));
    }

    private int getSize(int i) {
        if (!this.mAutoReposition || this.mButton == null) {
            return i;
        }
        return (int) TypedValue.applyDimension(1, (float) i, this.mButton.getResources().getDisplayMetrics());
    }
}
