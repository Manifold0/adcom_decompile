// 
// Decompiled by Procyon v0.5.34
// 

package com.kongregate.android.internal.sdk.unity;

import com.kongregate.android.api.KongregateEventBundleListener;
import android.net.Uri;
import com.kongregate.android.internal.util.i;
import java.util.Map;
import com.kongregate.android.api.AnalyticsServices;
import org.json.JSONObject;
import android.text.TextUtils;
import java.lang.reflect.InvocationTargetException;
import android.view.ViewGroup$LayoutParams;
import android.widget.RelativeLayout$LayoutParams;
import com.kongregate.android.internal.util.j;
import android.content.Context;
import android.view.ViewGroup;
import com.kongregate.o.c.d;
import com.kongregate.android.api.APIBootstrap;
import com.kongregate.android.internal.sdk.NativeAPI;
import android.util.TypedValue;
import android.app.Activity;
import com.kongregate.android.api.KongregateEventListener;
import java.lang.reflect.Method;
import android.widget.RelativeLayout;
import android.view.View;
import com.kongregate.android.api.KongregateAPI;

public class UnityWrapper
{
    private static volatile UnityWrapper sIntance;
    private final KongregateAPI mAPI;
    private final boolean mAutoReposition;
    private View mButton;
    private RelativeLayout mButtonLayout;
    private int mButtonSize;
    private int mButtonX;
    private int mButtonY;
    private String mEventListenerGameObject;
    private String mEventListenerMethodName;
    private boolean mNativeRendering;
    private boolean mReady;
    private Method mSendUnityMessageMethod;
    
    private UnityWrapper(final KongregateAPI mapi, final int n) {
        boolean mAutoReposition = false;
        this.mNativeRendering = false;
        this.mButtonX = 10;
        this.mButtonY = 10;
        this.mButtonSize = 48;
        this.mReady = false;
        if (n != 0) {
            mAutoReposition = true;
        }
        this.mAutoReposition = mAutoReposition;
        (this.mAPI = mapi).addEventListener(new KongregateEventListener() {
            @Override
            public void onEvent(final String s) {
                if ("KONG_API_EVENT_READY".equals(s)) {
                    UnityWrapper.this.mReady = true;
                }
                UnityWrapper.this.sendEventToUnity(s);
            }
        });
    }
    
    public static UnityWrapper getInstance() {
        if (UnityWrapper.sIntance == null) {
            throw new IllegalStateException("Must initialize wrapper before calling getInstance");
        }
        return UnityWrapper.sIntance;
    }
    
    private int getSize(final int n) {
        int n2 = n;
        if (this.mAutoReposition) {
            n2 = n;
            if (this.mButton != null) {
                n2 = (int)TypedValue.applyDimension(1, (float)n, this.mButton.getResources().getDisplayMetrics());
            }
        }
        return n2;
    }
    
    private Method getUnitySendMessage() {
        try {
            return Class.forName("com.unity3d.player.UnityPlayer").getMethod("UnitySendMessage", String.class, String.class, String.class);
        }
        catch (ClassNotFoundException ex) {
            log("could not find UnityPlayer class");
            return null;
        }
        catch (NoSuchMethodException ex2) {
            log("could not find method UnitySendMessage");
            return null;
        }
    }
    
    public static UnityWrapper initializeWrapper(final Activity activity, final long n, final String s, final String s2) {
        boolean b = false;
        try {
            final NativeAPI nativeAPI = (NativeAPI)APIBootstrap.initializeNativeAPI(activity, n, s, s2);
            if (nativeAPI.a("auto_reposition", Boolean.valueOf(false))) {
                b = true;
            }
            UnityWrapper.sIntance = new UnityWrapper(nativeAPI, b ? 1 : 0);
            log("initializing API with " + activity + " " + n);
            return UnityWrapper.sIntance;
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    private void layoutButton(final Activity activity) {
        final ViewGroup viewGroup = (ViewGroup)activity.getWindow().getDecorView().findViewById(16908290);
        if (this.mButton == null) {
            this.mButton = this.mAPI.mobile().getButton(activity.getApplicationContext());
            (this.mButtonLayout = new RelativeLayout((Context)activity)).addView(this.mButton);
        }
        else {
            final ViewGroup viewGroup2 = (ViewGroup)this.mButtonLayout.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView((View)this.mButtonLayout);
            }
        }
        this.resizeButton();
        this.mButtonLayout.setVisibility(0);
        viewGroup.addView((View)this.mButtonLayout);
        viewGroup.bringChildToFront((View)this.mButtonLayout);
    }
    
    private static void log(final String s) {
        j.a("UnityWrapper : " + s);
    }
    
    private void resizeButton() {
        if (this.mButton != null) {
            this.mButtonLayout.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(-1, -1));
            this.mButtonLayout.setPadding(this.getSize(this.mButtonX), this.getSize(this.mButtonY), 0, 0);
            this.mButton.setLayoutParams((ViewGroup$LayoutParams)new RelativeLayout$LayoutParams(this.getSize(this.mButtonSize), this.getSize(this.mButtonSize)));
        }
    }
    
    private void sendEventToUnity(final String s) {
        if (this.mSendUnityMessageMethod == null || this.mEventListenerGameObject == null || this.mEventListenerMethodName == null) {
            return;
        }
        try {
            log("Sending event to Unity: " + s);
            this.mSendUnityMessageMethod.invoke(null, this.mEventListenerGameObject, this.mEventListenerMethodName, s);
        }
        catch (IllegalAccessException ex) {
            log("Failed sending message: IllegalAccessException " + ex.getMessage());
        }
        catch (InvocationTargetException ex2) {
            log("Failed sending message: InvocationTargetException " + ex2.getMessage());
        }
    }
    
    private void setButtonPosition(final int mButtonX, final int mButtonY, final int mButtonSize) {
        this.mButtonX = mButtonX;
        this.mButtonY = mButtonY;
        this.mButtonSize = mButtonSize;
        d.b(new Runnable() {
            @Override
            public void run() {
                UnityWrapper.this.resizeButton();
            }
        });
    }
    
    public void KongregateAPIAnalyticsAddEvent(final String s, final String s2, final String s3) {
        try {
            this.mAPI.analytics().addEvent(s, s2, s3);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsAddFilterType(final String s) {
        log("adding filter type: " + s);
        this.mAPI.analytics().addFilterType(s);
    }
    
    public void KongregateAPIAnalyticsDeltaButtonListener(final String s, final String s2) {
        final Method unitySendMessage = this.getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            log("Failed to set Delta button listener: " + s + " : " + s2);
            return;
        }
        log("Setting Delta button listener to Unity method: " + s + "." + s2);
        this.mAPI.analytics().setDeltaCustomButtonListener(new com.kongregate.o.a.d.b() {
            @Override
            public void a(final String s, String string) {
                try {
                    string = s + " " + string;
                    log("Sending event to Unity: " + s);
                    unitySendMessage.invoke(null, s, s2, string);
                }
                catch (IllegalAccessException ex) {
                    log("Failed sending message: IllegalAccessException " + ex.getMessage());
                }
                catch (InvocationTargetException ex2) {
                    log("Failed sending message: InvocationTargetException " + ex2.getMessage());
                }
            }
            
            @Override
            public void b(final String s, String string) {
                try {
                    string = s + " " + string;
                    log("Sending event to Unity: " + s);
                    unitySendMessage.invoke(null, s, s2, string);
                }
                catch (IllegalAccessException ex) {
                    log("Failed sending message: IllegalAccessException " + ex.getMessage());
                }
                catch (InvocationTargetException ex2) {
                    log("Failed sending message: InvocationTargetException " + ex2.getMessage());
                }
            }
        });
    }
    
    public void KongregateAPIAnalyticsDeltaParameterListener(final String s, final String s2) {
        final Method unitySendMessage = this.getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            log("Failed to set Delta parameter listener: " + s + " : " + s2);
            return;
        }
        log("Setting Delta parameter listener to Unity method: " + s + "." + s2);
        this.mAPI.analytics().setDeltaCustomParamListener(new com.kongregate.o.a.d.c() {
            @Override
            public void a(final JSONObject jsonObject) {
                try {
                    final String string = jsonObject.toString();
                    log("Sending event to Unity: " + string);
                    unitySendMessage.invoke(null, s, s2, string);
                }
                catch (IllegalAccessException ex) {
                    log("Failed sending message: IllegalAccessException " + ex.getMessage());
                }
                catch (InvocationTargetException ex2) {
                    log("Failed sending message: InvocationTargetException " + ex2.getMessage());
                }
            }
        });
    }
    
    public void KongregateAPIAnalyticsFinishPromoAward(final String s) {
        try {
            this.mAPI.analytics().finishPromoAward(s);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsFinishPurchase(final String s, final String s2, final String s3, final String s4) {
        log("finish purchase: " + s);
        try {
            this.mAPI.analytics().finishPurchase(s, s2, s3, s4);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsGameUserUpdate(final String s) {
        try {
            this.mAPI.analytics().gameUserUpdate(s);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public boolean KongregateAPIAnalyticsGetAutoBoolProperty(final String s) {
        return this.mAPI.analytics().getAutoBoolProperty(s);
    }
    
    public double KongregateAPIAnalyticsGetAutoDoubleProperty(final String s) {
        return this.mAPI.analytics().getAutoDoubleProperty(s);
    }
    
    public int KongregateAPIAnalyticsGetAutoIntProperty(final String s) {
        return this.mAPI.analytics().getAutoIntProperty(s);
    }
    
    public long KongregateAPIAnalyticsGetAutoLongProperty(final String s) {
        return this.mAPI.analytics().getAutoLongProperty(s);
    }
    
    public String KongregateAPIAnalyticsGetAutoPropertiesJSON() {
        final String autoPropertiesJSON = this.mAPI.analytics().getAutoPropertiesJSON();
        if (autoPropertiesJSON != null) {
            return autoPropertiesJSON;
        }
        return "";
    }
    
    public String KongregateAPIAnalyticsGetAutoStringProperty(final String s) {
        return this.mAPI.analytics().getAutoStringProperty(s);
    }
    
    public String KongregateAPIAnalyticsGetAutoUTCProperty(final String s) {
        return this.mAPI.analytics().getAutoUTCProperty(s);
    }
    
    public String KongregateAPIAnalyticsGetInstallReferrer() {
        return this.mAPI.analytics().getInstallReferrer();
    }
    
    public String KongregateAPIAnalyticsGetKongProperties() {
        try {
            return this.mAPI.analytics().getKongPropertiesJSON();
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public boolean KongregateAPIAnalyticsGetResourceAsBool(final String s, final String s2, final boolean b) {
        try {
            return this.mAPI.analytics().getResourceAsBool(s, s2, b);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public float KongregateAPIAnalyticsGetResourceAsFloat(final String s, final String s2, float resourceAsFloat) {
        try {
            resourceAsFloat = this.mAPI.analytics().getResourceAsFloat(s, s2, resourceAsFloat);
            return resourceAsFloat;
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public int KongregateAPIAnalyticsGetResourceAsInt(final String s, final String s2, int resourceAsInt) {
        try {
            resourceAsInt = this.mAPI.analytics().getResourceAsInt(s, s2, resourceAsInt);
            return resourceAsInt;
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public String KongregateAPIAnalyticsGetResourceAsString(String resourceAsString, final String s, final String s2) {
        try {
            resourceAsString = this.mAPI.analytics().getResourceAsString(resourceAsString, s, s2);
            return resourceAsString;
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public String KongregateAPIAnalyticsGetResourceNames() {
        try {
            return this.mAPI.analytics().getResourceNamesAsJson();
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsSetAutoEventListener(final String s, final String s2) {
        final Method unitySendMessage = this.getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            log("Failed to set auto event listener: " + s + " : " + s2);
            return;
        }
        log("Setting auto event listener to Unity method: " + s + "." + s2);
        this.mAPI.analytics().setAutoEventListener((AnalyticsServices.AutoEventListener)new AnalyticsServices.AutoEventListener() {
            @Override
            public void onAutoEvent(final String s, final Map<String, Object> map) {
                while (true) {
                    while (true) {
                        try {
                            final String a = i.a(map, false);
                            if (s != null) {
                                final String string = s + " " + a;
                                log("Sending auto event callback to Unity: " + s);
                                unitySendMessage.invoke(null, s, s2, string);
                                return;
                            }
                        }
                        catch (IllegalAccessException ex) {
                            log("Failed sending message: IllegalAccessException " + ex.getMessage());
                            return;
                        }
                        catch (InvocationTargetException ex2) {
                            log("Failed sending message: InvocationTargetException " + ex2.getMessage());
                            return;
                        }
                        final String string = s;
                        continue;
                    }
                }
            }
        });
    }
    
    public void KongregateAPIAnalyticsStart(final Activity activity) {
        try {
            this.mAPI.analytics().start(activity);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsStartPurchase(final String s, final int n, final String s2) {
        log("start purchase: " + s);
        try {
            this.mAPI.analytics().startPurchase(s, s2);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsSwrveButtonListener(final String s, final String s2) {
        this.KongregateAPIAnalyticsDeltaButtonListener(s, s2);
    }
    
    public void KongregateAPIAnalyticsTrackPurchase(final String s, final String s2) {
        log("track purchase: " + s);
        try {
            this.mAPI.analytics().trackPurchase(s, s2);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public void KongregateAPIAnalyticsUpdateCommonProps(final String commonProperties) {
        try {
            this.mAPI.analytics().setCommonProperties(commonProperties);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
    
    public boolean KongregateAPIIsReady() {
        return this.mReady;
    }
    
    public void KongregateAPIMessageReceived(final String s) {
        log("message received " + s);
    }
    
    public void KongregateAPIMobileButtonHide(final Activity activity) {
        if (this.mNativeRendering) {
            log("hide button");
            d.b(new Runnable() {
                @Override
                public void run() {
                    if (UnityWrapper.this.mButtonLayout != null) {
                        UnityWrapper.this.mButtonLayout.setVisibility(4);
                    }
                }
            });
        }
    }
    
    public void KongregateAPIMobileButtonSetNativeRendering(final boolean mNativeRendering) {
        log("set native rendering to " + mNativeRendering);
        this.mNativeRendering = mNativeRendering;
    }
    
    public void KongregateAPIMobileButtonSetSize(final int n) {
        log("set button size to " + n);
        this.setButtonPosition(this.mButtonX, this.mButtonY, n);
    }
    
    public void KongregateAPIMobileButtonSetX(final int n) {
        log("set button x to " + n);
        this.setButtonPosition(n, this.mButtonY, this.mButtonSize);
    }
    
    public void KongregateAPIMobileButtonSetY(final int n) {
        log("set button y to " + n);
        this.setButtonPosition(this.mButtonX, n, this.mButtonSize);
    }
    
    public void KongregateAPIMobileButtonShow(final Activity activity) {
        if (this.mNativeRendering) {
            activity.runOnUiThread((Runnable)new Runnable() {
                @Override
                public void run() {
                    log("show button");
                    UnityWrapper.this.layoutButton(activity);
                }
            });
        }
    }
    
    public void KongregateAPIMobileCloseKongregateWindow(final Activity activity) {
        log("close Kongregate window");
        this.mAPI.mobile().closeKongregateWindow((Context)activity);
    }
    
    public String KongregateAPIMobileGetOpenURL(final Activity activity) {
        final Uri openURL = this.mAPI.mobile().getOpenURL();
        if (openURL != null) {
            return openURL.toString();
        }
        return null;
    }
    
    public void KongregateAPIMobileOpenKongregateWindow(final Activity activity, final String s, final String s2) {
        log("open Kongregate window: " + s + " : " + s2);
        this.mAPI.mobile().openKongregateWindow((Context)activity, s, s2);
    }
    
    public void KongregateAPIMobileTrigger(final Activity activity, final String s) {
        log("trigger " + s);
        this.mAPI.mobile().trigger(s);
    }
    
    public boolean KongregateAPIMtxHasItem(final String s) {
        return this.mAPI.mtx().hasItem(s);
    }
    
    public String KongregateAPIMtxReceiptVerificationStatus(final String s) {
        return this.mAPI.mtx().receiptVerificationStatus(s).toString();
    }
    
    public void KongregateAPIMtxRequestUserItemList() {
        this.mAPI.mtx().requestUserItemList();
    }
    
    public void KongregateAPIMtxVerifyGooglePurchase(final String s, final String s2) {
        this.mAPI.mtx().verifyTransaction(s, s2);
    }
    
    public void KongregateAPIOnPause(final Activity activity) {
        this.mAPI.onPause(activity);
    }
    
    public void KongregateAPIOnResume(final Activity activity) {
        this.mAPI.onResume(activity);
    }
    
    public String KongregateAPIServicesGetGameAuthToken() {
        log("getting game auth token");
        return this.mAPI.services().getGameAuthToken();
    }
    
    public int KongregateAPIServicesGetNotificationCount() {
        return this.mAPI.services().getNotificationCount();
    }
    
    public long KongregateAPIServicesGetUserId() {
        log("getting user id");
        return this.mAPI.services().getUserId();
    }
    
    public String KongregateAPIServicesGetUsername() {
        log("getting username");
        return this.mAPI.services().getUsername();
    }
    
    public boolean KongregateAPIServicesHasKongPlus() {
        return this.mAPI.services().hasKongPlus();
    }
    
    public boolean KongregateAPIServicesHasUnreadGuildMessages() {
        return this.mAPI.services().hasUnreadGuildMessages();
    }
    
    public boolean KongregateAPIServicesIsGuest() {
        log("checking for guest");
        return this.mAPI.services().isGuest();
    }
    
    public void KongregateAPIServicesSetCharacterToken(final String characterToken) {
        log("set character token");
        this.mAPI.services().setCharacterToken(characterToken);
    }
    
    public void KongregateAPISetEventBundleListener(final String s, final String s2) {
        final Method unitySendMessage = this.getUnitySendMessage();
        if (unitySendMessage == null || TextUtils.isEmpty((CharSequence)s) || TextUtils.isEmpty((CharSequence)s2)) {
            log("Failed to set event bundle listener: " + s + " : " + s2);
            return;
        }
        log("Setting event bundle listener to Unity method: " + s + "." + s2);
        this.mAPI.addEventBundleListener(new KongregateEventBundleListener() {
            @Override
            public void onKongregateEventBundle(final String s, final String s2) {
                String string = s;
                Label_0030: {
                    if (s2 == null) {
                        break Label_0030;
                    }
                    try {
                        string = s + " " + s2;
                        log("Sending event bundle to Unity: " + string);
                        unitySendMessage.invoke(null, s, s2, string);
                    }
                    catch (IllegalAccessException ex) {
                        log("Failed sending message: IllegalAccessException " + ex.getMessage());
                    }
                    catch (InvocationTargetException ex2) {
                        log("Failed sending message: InvocationTargetException " + ex2.getMessage());
                    }
                }
            }
        });
    }
    
    public void KongregateAPISetEventListener(final String mEventListenerGameObject, final String mEventListenerMethodName) {
        this.mSendUnityMessageMethod = this.getUnitySendMessage();
        if (this.mSendUnityMessageMethod != null) {
            this.mEventListenerGameObject = mEventListenerGameObject;
            this.mEventListenerMethodName = mEventListenerMethodName;
            log("set event listener " + mEventListenerGameObject + " " + mEventListenerMethodName);
            return;
        }
        log("error setting event listener: " + mEventListenerGameObject + " " + mEventListenerMethodName);
    }
    
    public void KongregateAPIStatsSubmit(final String s, final long n) {
        try {
            log("submitting stats " + s + " " + n);
            this.mAPI.stats().submit(s, n);
        }
        catch (RuntimeException ex) {
            d.a(ex);
            throw ex;
        }
    }
}
