package com.vungle.warren.ui;

import android.net.Uri;
import android.os.Build.VERSION;
import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import com.google.gson.JsonObject;
import com.ironsource.sdk.constants.Constants.ParametersKeys;
import com.tapjoy.TapjoyConstants;
import com.vungle.warren.DirectDownloadAdapter;
import com.vungle.warren.SDKDownloadClient.InstallStatusCheck;
import com.vungle.warren.model.Advertisement;
import com.vungle.warren.model.Placement;
import java.util.Locale;

public class VungleWebClient extends WebViewClient {
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
    private boolean ready = false;

    public interface MRAIDDelegate {
        boolean processCommand(String str, JsonObject jsonObject);
    }

    public VungleWebClient(Advertisement advertisement, Placement placement, DirectDownloadAdapter directDownloadAdapter) {
        this.advertisement = advertisement;
        this.placement = placement;
        this.directDownloadAdapter = directDownloadAdapter;
    }

    public VungleWebClient(Advertisement advertisement, Placement placement) {
        this.advertisement = advertisement;
        this.placement = placement;
    }

    public void setConsentStatus(boolean collectedConsent, @Nullable String title, @Nullable String message, @Nullable String accept, @Nullable String deny) {
        this.collectConsent = collectedConsent;
        this.gdprTitle = title;
        this.gdprBody = message;
        this.gdprAccept = accept;
        this.gdprDeny = deny;
    }

    public void setMRAIDDelegate(MRAIDDelegate MRAIDDelegate) {
        this.MRAIDDelegate = MRAIDDelegate;
    }

    public boolean shouldOverrideUrlLoading(final WebView view, String url) {
        Log.d("Vungle", "MRAID Command " + url);
        Uri uri = Uri.parse(url);
        if (!uri.getScheme().equals("mraid")) {
            return false;
        }
        String command = uri.getHost();
        if (command.equals("propertiesChangeCompleted") && !this.ready) {
            final JsonObject mraidArgs = this.advertisement.getMRAIDArgs();
            if (this.directDownloadAdapter != null) {
                this.directDownloadAdapter.getSdkDownloadClient().setInstallStatusCheck(new InstallStatusCheck() {
                    public void isAppInstalled(boolean isInstalled, boolean isInAppPurchase) {
                        mraidArgs.addProperty("isDirectDownload", Boolean.valueOf(true));
                        mraidArgs.addProperty("isDisplayIAP", Boolean.valueOf(isInAppPurchase));
                        mraidArgs.addProperty("isInstalled", Boolean.valueOf(isInstalled));
                        mraidArgs.addProperty("locale", Locale.getDefault().toString());
                        mraidArgs.addProperty("language", Locale.getDefault().getLanguage());
                        view.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mraidArgs + ")");
                    }
                });
                this.directDownloadAdapter.getSdkDownloadClient().installStatusRequest();
            } else {
                view.loadUrl("javascript:window.vungle.mraidBridge.notifyReadyEvent(" + mraidArgs + ")");
            }
            this.ready = true;
            return true;
        } else if (this.MRAIDDelegate == null) {
            return true;
        } else {
            JsonObject args = new JsonObject();
            for (String param : uri.getQueryParameterNames()) {
                args.addProperty(param, uri.getQueryParameter(param));
            }
            if (!this.MRAIDDelegate.processCommand(command, args)) {
                return true;
            }
            view.loadUrl("javascript:window.vungle.mraidBridge.notifyCommandComplete()");
            return true;
        }
    }

    public void onPageFinished(WebView webView, String url) {
        super.onPageFinished(webView, url);
        switch (this.advertisement.getAdType()) {
            case 0:
                webView.loadUrl("javascript:vungleInit({\"privacyPolicyEnabled\": \"true\"})");
                webView.loadUrl("javascript:function actionClicked(action){Android.performAction(action);};");
                return;
            case 1:
                this.loadedWebView = webView;
                this.loadedWebView.setVisibility(0);
                notifyPropertiesChange(true);
                return;
            default:
                throw new IllegalArgumentException("Unknown Client Type!");
        }
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void notifyPropertiesChange(boolean skipCmdQueue) {
        if (this.loadedWebView != null) {
            boolean z;
            JsonObject screenJson = new JsonObject();
            JsonObject size = new JsonObject();
            size.addProperty("width", Integer.valueOf(this.loadedWebView.getWidth()));
            size.addProperty("height", Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject position = new JsonObject();
            position.addProperty("x", Integer.valueOf(0));
            position.addProperty("y", Integer.valueOf(0));
            position.addProperty("width", Integer.valueOf(this.loadedWebView.getWidth()));
            position.addProperty("height", Integer.valueOf(this.loadedWebView.getHeight()));
            JsonObject supports = new JsonObject();
            supports.addProperty("sms", Boolean.valueOf(false));
            supports.addProperty("tel", Boolean.valueOf(false));
            supports.addProperty("calendar", Boolean.valueOf(false));
            supports.addProperty("storePicture", Boolean.valueOf(false));
            supports.addProperty("inlineVideo", Boolean.valueOf(false));
            screenJson.add("maxSize", size);
            screenJson.add("screenSize", size);
            screenJson.add("defaultPosition", position);
            screenJson.add("currentPosition", position);
            screenJson.add("supports", supports);
            screenJson.addProperty("placementType", this.advertisement.getTemplateType());
            if (this.isViewable != null) {
                screenJson.addProperty(ParametersKeys.IS_VIEWABLE, this.isViewable);
            }
            screenJson.addProperty("os", TapjoyConstants.TJC_DEVICE_PLATFORM_TYPE);
            screenJson.addProperty("osVersion", Integer.toString(VERSION.SDK_INT));
            screenJson.addProperty("incentivized", Boolean.valueOf(this.placement.isIncentivized()));
            String str = "enableBackImmediately";
            if (this.advertisement.getShowCloseDelay(this.placement.isIncentivized()) == 0) {
                z = true;
            } else {
                z = false;
            }
            screenJson.addProperty(str, Boolean.valueOf(z));
            screenJson.addProperty("version", "1.0");
            if (this.collectConsent) {
                screenJson.addProperty("consentRequired", Boolean.valueOf(true));
                screenJson.addProperty("consentTitleText", this.gdprTitle);
                screenJson.addProperty("consentBodyText", this.gdprBody);
                screenJson.addProperty("consentAcceptButtonText", this.gdprAccept);
                screenJson.addProperty("consentDenyButtonText", this.gdprDeny);
            } else {
                screenJson.addProperty("consentRequired", Boolean.valueOf(false));
            }
            Log.d("VungleWebClient", "loadJsjavascript:window.vungle.mraidBridge.notifyPropertiesChange(" + screenJson + "," + skipCmdQueue + ")");
            this.loadedWebView.loadUrl("javascript:window.vungle.mraidBridge.notifyPropertiesChange(" + screenJson + "," + skipCmdQueue + ")");
        }
    }

    public void setAdVisibility(boolean isViewable) {
        if (this.loadedWebView != null) {
            this.isViewable = Boolean.valueOf(isViewable);
            notifyPropertiesChange(false);
        }
    }
}
