// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.os.ResultReceiver;
import android.webkit.WebView;
import android.annotation.SuppressLint;

@SuppressLint({ "RestrictedApi" })
public class SDKDownloadClient
{
    private WebView adWebView;
    private ValidationCheck appMarketValidation;
    private ResultReceiver callBackReceiver;
    private InstallStatusCheck installStatusCheck;
    private String pkgName;
    private ResultReceiver sendingReceiver;
    
    public SDKDownloadClient(final String pkgName) {
        this.callBackReceiver = new ResultReceiver(new Handler(Looper.getMainLooper())) {
            @SuppressLint({ "SetTextI18n" })
            protected void onReceiveResult(final int n, final Bundle bundle) {
                if (n == 6) {
                    bundle.getString("PACKAGE_NAME");
                    final boolean boolean1 = bundle.getBoolean("RESULT");
                    final boolean boolean2 = bundle.getBoolean("IN_APP_PURCHASE", false);
                    if (SDKDownloadClient.this.installStatusCheck != null) {
                        SDKDownloadClient.this.installStatusCheck.isAppInstalled(boolean1, boolean2);
                    }
                }
                else if (n == 16) {
                    if (SDKDownloadClient.this.adWebView != null) {
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0})");
                    }
                }
                else if (n == 17) {
                    final float n2 = bundle.getInt("PROGRESS") / 100.0f;
                    if (SDKDownloadClient.this.adWebView != null) {
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":0,\"percentage\":" + n2 + "})");
                    }
                }
                else if (n == 18) {
                    if (SDKDownloadClient.this.adWebView != null) {
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":0})");
                    }
                }
                else if (n == 19) {
                    final boolean boolean3 = bundle.getBoolean("RESULT", true);
                    if (SDKDownloadClient.this.adWebView != null) {
                        if (!boolean3) {
                            SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":-1})");
                            return;
                        }
                        SDKDownloadClient.this.adWebView.loadUrl("javascript:window.vungle.mraidBridgeExt.getInstallationStatus({\"status\":1,\"percentage\":1})");
                    }
                }
                else if (n != 26) {
                    if (n == 36) {
                        bundle.getString("PACKAGE_NAME");
                        final boolean boolean4 = bundle.getBoolean("RESULT");
                        if (SDKDownloadClient.this.appMarketValidation != null) {
                            SDKDownloadClient.this.appMarketValidation.validateAppPresenceInMarket(boolean4);
                        }
                    }
                    else if (n != 56 && n == 66) {
                        return;
                    }
                }
            }
        };
        this.pkgName = pkgName;
    }
    
    private void requestDetailOpen() {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(61, bundle);
    }
    
    public void cancelDownloadRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(21, bundle);
    }
    
    public void cleanUp() {
        this.adWebView = null;
    }
    
    public ResultReceiver getCallBackReceiver() {
        return this.callBackReceiver;
    }
    
    public void installStatusRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(1, bundle);
    }
    
    public void sendADDisplayingNotify(final boolean b, final DirectDownloadAdapter.CONTRACT_TYPE contract_TYPE) {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        bundle.putString("ADTYPE", contract_TYPE.name());
        final ResultReceiver sendingReceiver = this.sendingReceiver;
        int n;
        if (b) {
            n = 71;
        }
        else {
            n = 72;
        }
        sendingReceiver.send(n, bundle);
    }
    
    public void sendDownloadRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(11, bundle);
    }
    
    public void sendOpenPackageRequest() {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", this.pkgName);
        this.sendingReceiver.send(51, bundle);
    }
    
    protected void sendValidation(final String s) {
        final Bundle bundle = new Bundle();
        bundle.putString("PACKAGE_NAME", s);
        this.sendingReceiver.send(31, bundle);
    }
    
    public void setAdWebView(final WebView adWebView) {
        this.adWebView = adWebView;
    }
    
    public void setAppMarketValidation(final ValidationCheck appMarketValidation) {
        this.appMarketValidation = appMarketValidation;
    }
    
    public void setInstallStatusCheck(final InstallStatusCheck installStatusCheck) {
        this.installStatusCheck = installStatusCheck;
    }
    
    public void setSendingReceiver(final ResultReceiver sendingReceiver) {
        this.sendingReceiver = sendingReceiver;
    }
    
    public interface InstallStatusCheck
    {
        void isAppInstalled(final boolean p0, final boolean p1);
    }
    
    public interface ValidationCheck
    {
        void validateAppPresenceInMarket(final boolean p0);
    }
}
