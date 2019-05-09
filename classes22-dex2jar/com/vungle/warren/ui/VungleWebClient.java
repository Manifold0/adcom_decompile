// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.ui;

import java.util.Iterator;
import java.util.Locale;
import com.vungle.warren.SDKDownloadClient;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.util.Log;
import android.os.Build$VERSION;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.vungle.warren.model.Placement;
import android.webkit.WebView;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.model.Advertisement;
import android.webkit.WebViewClient;

public class VungleWebClient extends WebViewClient
{
    private MRAIDDelegate MRAIDDelegate;
    private Advertisement advertisement;
    private boolean collectConsent;
    private DirectDownloadAdapter directDownloadAdapter;
    private String gdprAccept;
    private String gdprBody;
    private String gdprDeny;
    private String gdprTitle;
    private Boolean isViewable;
    private WebView loadedWebView;
    private Placement placement;
    private boolean ready;
    
    public VungleWebClient(final Advertisement advertisement, final Placement placement) {
        this.ready = false;
        this.advertisement = advertisement;
        this.placement = placement;
    }
    
    public VungleWebClient(final Advertisement advertisement, final Placement placement, final DirectDownloadAdapter directDownloadAdapter) {
        this.ready = false;
        this.advertisement = advertisement;
        this.placement = placement;
        this.directDownloadAdapter = directDownloadAdapter;
    }
    
    public void notifyPropertiesChange(final boolean b) {
        if (this.loadedWebView != null) {
            final JsonObject jsonObject = new JsonObject();
            final JsonObject jsonObject2 = new JsonObject();
            jsonObject2.addProperty("width", (Number)this.loadedWebView.getWidth());
            jsonObject2.addProperty("height", (Number)this.loadedWebView.getHeight());
            final JsonObject jsonObject3 = new JsonObject();
            jsonObject3.addProperty("x", (Number)0);
            jsonObject3.addProperty("y", (Number)0);
            jsonObject3.addProperty("width", (Number)this.loadedWebView.getWidth());
            jsonObject3.addProperty("height", (Number)this.loadedWebView.getHeight());
            final JsonObject jsonObject4 = new JsonObject();
            jsonObject4.addProperty("sms", Boolean.valueOf(false));
            jsonObject4.addProperty("tel", Boolean.valueOf(false));
            jsonObject4.addProperty("calendar", Boolean.valueOf(false));
            jsonObject4.addProperty("storePicture", Boolean.valueOf(false));
            jsonObject4.addProperty("inlineVideo", Boolean.valueOf(false));
            jsonObject.add("maxSize", (JsonElement)jsonObject2);
            jsonObject.add("screenSize", (JsonElement)jsonObject2);
            jsonObject.add("defaultPosition", (JsonElement)jsonObject3);
            jsonObject.add("currentPosition", (JsonElement)jsonObject3);
            jsonObject.add("supports", (JsonElement)jsonObject4);
            jsonObject.addProperty("placementType", this.advertisement.getTemplateType());
            if (this.isViewable != null) {
                jsonObject.addProperty("isViewable", this.isViewable);
            }
            jsonObject.addProperty("os", "android");
            jsonObject.addProperty("osVersion", Integer.toString(Build$VERSION.SDK_INT));
            jsonObject.addProperty("incentivized", Boolean.valueOf(this.placement.isIncentivized()));
            jsonObject.addProperty("enableBackImmediately", Boolean.valueOf(this.advertisement.getShowCloseDelay(this.placement.isIncentivized()) == 0));
            jsonObject.addProperty("version", "1.0");
            if (this.collectConsent) {
                jsonObject.addProperty("consentRequired", Boolean.valueOf(true));
                jsonObject.addProperty("consentTitleText", this.gdprTitle);
                jsonObject.addProperty("consentBodyText", this.gdprBody);
                jsonObject.addProperty("consentAcceptButtonText", this.gdprAccept);
                jsonObject.addProperty("consentDenyButtonText", this.gdprDeny);
            }
            else {
                jsonObject.addProperty("consentRequired", Boolean.valueOf(false));
            }
            Log.d("VungleWebClient", "loadJsjavascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + b + ")");
            this.loadedWebView.loadUrl("javascript:window.vungle.mraidBridge.notifyPropertiesChange(" + jsonObject + "," + b + ")");
        }
    }
    
    public void onPageFinished(final WebView loadedWebView, final String s) {
        super.onPageFinished(loadedWebView, s);
        switch (this.advertisement.getAdType()) {
            default: {
                throw new IllegalArgumentException("Unknown Client Type!");
            }
            case 0: {
                loadedWebView.loadUrl("javascript:vungleInit({\"privacyPolicyEnabled\": \"true\"})");
                loadedWebView.loadUrl("javascript:function actionClicked(action){Android.performAction(action);};");
            }
            case 1: {
                (this.loadedWebView = loadedWebView).setVisibility(0);
                this.notifyPropertiesChange(true);
            }
        }
    }
    
    public void setAdVisibility(final boolean b) {
        if (this.loadedWebView != null) {
            this.isViewable = b;
            this.notifyPropertiesChange(false);
        }
    }
    
    public void setConsentStatus(final boolean collectConsent, @Nullable final String gdprTitle, @Nullable final String gdprBody, @Nullable final String gdprAccept, @Nullable final String gdprDeny) {
        this.collectConsent = collectConsent;
        this.gdprTitle = gdprTitle;
        this.gdprBody = gdprBody;
        this.gdprAccept = gdprAccept;
        this.gdprDeny = gdprDeny;
    }
    
    public void setMRAIDDelegate(final MRAIDDelegate mraidDelegate) {
        this.MRAIDDelegate = mraidDelegate;
    }
    
    public void setReady(final boolean ready) {
        this.ready = ready;
    }
    
    public boolean shouldOverrideUrlLoading(final WebView webView, final String s) {
        Log.d("Vungle", "MRAID Command " + s);
        final Uri parse = Uri.parse(s);
        if (parse.getScheme().equals("mraid")) {
            final String host = parse.getHost();
            if (host.equals("propertiesChangeCompleted") && !this.ready) {
                final JsonObject mraidArgs = this.advertisement.getMRAIDArgs();
                if (this.directDownloadAdapter != null) {
                    this.directDownloadAdapter.getSdkDownloadClient().setInstallStatusCheck((SDKDownloadClient.InstallStatusCheck)new SDKDownloadClient.InstallStatusCheck() {
                        @Override
                        public void isAppInstalled(final boolean b, final boolean b2) {
                            mraidArgs.addProperty("isDirectDownload", Boolean.valueOf(true));
                            mraidArgs.addProperty("isDisplayIAP", Boolean.valueOf(b2));
                            mraidArgs.addProperty("isInstalled", Boolean.valueOf(b));
                            mraidArgs.addProperty("locale", Locale.getDefault().toString());
                            mraidArgs.addProperty("language", Locale.getDefault().getLanguage());
                            webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mraidArgs + ")");
                        }
                    });
                    this.directDownloadAdapter.getSdkDownloadClient().installStatusRequest();
                }
                else {
                    webView.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mraidArgs + ")");
                }
                this.ready = true;
            }
            else if (this.MRAIDDelegate != null) {
                final JsonObject jsonObject = new JsonObject();
                for (final String s2 : parse.getQueryParameterNames()) {
                    jsonObject.addProperty(s2, parse.getQueryParameter(s2));
                }
                if (this.MRAIDDelegate.processCommand(host, jsonObject)) {
                    webView.loadUrl("javascript:window.vungle.mraidBridge.notifyCommandComplete()");
                    return true;
                }
            }
            return true;
        }
        return false;
    }
    
    public interface MRAIDDelegate
    {
        boolean processCommand(final String p0, final JsonObject p1);
    }
}
