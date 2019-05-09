package com.vungle.warren;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.os.ResultReceiver;
import android.webkit.WebView;
import com.vungle.warren.DirectDownloadAdapter.CONTRACT_TYPE;

@SuppressLint({"RestrictedApi"})
public class SDKDownloadClient {
    private WebView adWebView;
    private ValidationCheck appMarketValidation;
    private ResultReceiver callBackReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) {
        @SuppressLint({"SetTextI18n"})
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            String packageName;
            if (resultCode == 6) {
                packageName = resultData.getString("PACKAGE_NAME");
                boolean isInstalled = resultData.getBoolean(DirectDownloadAdapter.RESULT);
                boolean isInAppPurchase = resultData.getBoolean(DirectDownloadAdapter.IN_APP_PURCHASE, false);
                if (SDKDownloadClient.this.installStatusCheck != null) {
                    SDKDownloadClient.this.installStatusCheck.isAppInstalled(isInstalled, isInAppPurchase);
                }
            } else if (resultCode == 16) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0})");
                }
            } else if (resultCode == 17) {
                float progressPercent = ((float) resultData.getInt(DirectDownloadAdapter.PROGRESS)) / 100.0f;
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0,\"percentage\":" + progressPercent + "})");
                }
            } else if (resultCode == 18) {
                if (SDKDownloadClient.this.adWebView != null) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":0})");
                }
            } else if (resultCode == 19) {
                boolean downloadInstallSuccess = resultData.getBoolean(DirectDownloadAdapter.RESULT, true);
                if (SDKDownloadClient.this.adWebView == null) {
                    return;
                }
                if (downloadInstallSuccess) {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":1})");
                } else {
                    SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":-1})");
                }
            } else if (resultCode == 26) {
            } else {
                if (resultCode == 36) {
                    packageName = resultData.getString("PACKAGE_NAME");
                    boolean isValid = resultData.getBoolean(DirectDownloadAdapter.RESULT);
                    if (SDKDownloadClient.this.appMarketValidation != null) {
                        SDKDownloadClient.this.appMarketValidation.validateAppPresenceInMarket(isValid);
                    }
                } else if (resultCode != 56 && resultCode == 66) {
                }
            }
        }
    };
    private InstallStatusCheck installStatusCheck;
    private String pkgName;
    private ResultReceiver sendingReceiver;

    public interface ValidationCheck {
        void validateAppPresenceInMarket(boolean z);
    }

    public interface InstallStatusCheck {
        void isAppInstalled(boolean z, boolean z2);
    }

    public SDKDownloadClient(String pkgName) {
        this.pkgName = pkgName;
    }

    public void setSendingReceiver(ResultReceiver sendingReceiver) {
        this.sendingReceiver = sendingReceiver;
    }

    public void setAdWebView(WebView adWebView) {
        this.adWebView = adWebView;
    }

    public void sendDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(11, bundle);
    }

    public void sendOpenPackageRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(51, bundle);
    }

    public void cancelDownloadRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(21, bundle);
    }

    public void installStatusRequest() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(1, bundle);
    }

    public ResultReceiver getCallBackReceiver() {
        return this.callBackReceiver;
    }

    public void sendADDisplayingNotify(boolean isOver80, CONTRACT_TYPE type) {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        bundle.putString(DirectDownloadAdapter.ADTYPE, type.name());
        this.sendingReceiver.send(isOver80 ? 71 : 72, bundle);
    }

    protected void sendValidation(String pkgName) {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", pkgName);
        this.sendingReceiver.send(31, bundle);
    }

    private void requestDetailOpen() {
        Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(61, bundle);
    }

    public void setAppMarketValidation(ValidationCheck appMarketValidation) {
        this.appMarketValidation = appMarketValidation;
    }

    public void setInstallStatusCheck(InstallStatusCheck installStatusCheck) {
        this.installStatusCheck = installStatusCheck;
    }

    public void cleanUp() {
        this.adWebView = null;
    }
}
