// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.webkit.WebChromeClient$CustomViewCallback;
import com.google.android.gms.common.util.PlatformVersion;
import android.webkit.PermissionRequest;
import com.google.android.gms.ads.internal.zzbv;
import android.webkit.GeolocationPermissions$Callback;
import android.webkit.WebStorage$QuotaUpdater;
import android.webkit.WebView$WebViewTransport;
import android.os.Message;
import android.webkit.ConsoleMessage;
import com.google.android.gms.ads.internal.overlay.zzd;
import com.google.android.gms.ads.internal.zzx;
import android.view.WindowManager$BadTokenException;
import android.content.DialogInterface$OnCancelListener;
import android.content.DialogInterface$OnClickListener;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.LinearLayout;
import android.app.AlertDialog$Builder;
import android.webkit.JsPromptResult;
import android.webkit.JsResult;
import android.content.Context;
import android.webkit.WebView;
import android.annotation.TargetApi;
import android.webkit.WebChromeClient;

@zzadh
@TargetApi(11)
public final class zzaqo extends WebChromeClient
{
    private final zzaqw zzbnd;
    
    public zzaqo(final zzaqw zzbnd) {
        this.zzbnd = zzbnd;
    }
    
    private static Context zza(final WebView webView) {
        Object o;
        if (!(webView instanceof zzaqw)) {
            o = webView.getContext();
        }
        else {
            final zzaqw zzaqw = (zzaqw)webView;
            if ((o = zzaqw.zzto()) == null) {
                return zzaqw.getContext();
            }
        }
        return (Context)o;
    }
    
    private final boolean zza(final Context context, final String s, final String title, final String s2, final String text, final JsResult jsResult, final JsPromptResult jsPromptResult, final boolean b) {
        AlertDialog$Builder alertDialog$Builder;
        try {
            if (this.zzbnd != null && this.zzbnd.zzuf() != null && this.zzbnd.zzuf().zzut() != null) {
                final zzx zzut = this.zzbnd.zzuf().zzut();
                if (zzut != null && !zzut.zzcy()) {
                    zzut.zzs(new StringBuilder(String.valueOf(s).length() + 11 + String.valueOf(s2).length()).append("window.").append(s).append("('").append(s2).append("')").toString());
                    return false;
                }
            }
            alertDialog$Builder = new AlertDialog$Builder(context);
            alertDialog$Builder.setTitle((CharSequence)title);
            if (b) {
                final LinearLayout view = new LinearLayout(context);
                view.setOrientation(1);
                final TextView textView = new TextView(context);
                textView.setText((CharSequence)s2);
                final EditText editText = new EditText(context);
                editText.setText((CharSequence)text);
                view.addView((View)textView);
                view.addView((View)editText);
                alertDialog$Builder.setView((View)view).setPositiveButton(17039370, (DialogInterface$OnClickListener)new zzaqu(jsPromptResult, editText)).setNegativeButton(17039360, (DialogInterface$OnClickListener)new zzaqt(jsPromptResult)).setOnCancelListener((DialogInterface$OnCancelListener)new zzaqs(jsPromptResult)).create().show();
                return true;
            }
        }
        catch (WindowManager$BadTokenException ex) {
            zzakb.zzc("Fail to display Dialog.", (Throwable)ex);
            return true;
        }
        alertDialog$Builder.setMessage((CharSequence)s2).setPositiveButton(17039370, (DialogInterface$OnClickListener)new zzaqr(jsResult)).setNegativeButton(17039360, (DialogInterface$OnClickListener)new zzaqq(jsResult)).setOnCancelListener((DialogInterface$OnCancelListener)new zzaqp(jsResult)).create().show();
        return true;
    }
    
    public final void onCloseWindow(final WebView webView) {
        if (!(webView instanceof zzaqw)) {
            zzakb.zzdk("Tried to close a WebView that wasn't an AdWebView.");
            return;
        }
        final zzd zzub = ((zzaqw)webView).zzub();
        if (zzub == null) {
            zzakb.zzdk("Tried to close an AdWebView not associated with an overlay.");
            return;
        }
        zzub.close();
    }
    
    public final boolean onConsoleMessage(final ConsoleMessage consoleMessage) {
        final String message = consoleMessage.message();
        final String sourceId = consoleMessage.sourceId();
        final String string = new StringBuilder(String.valueOf(message).length() + 19 + String.valueOf(sourceId).length()).append("JS: ").append(message).append(" (").append(sourceId).append(":").append(consoleMessage.lineNumber()).append(")").toString();
        if (string.contains("Application Cache")) {
            return super.onConsoleMessage(consoleMessage);
        }
        switch (zzaqv.zzdbn[consoleMessage.messageLevel().ordinal()]) {
            default: {
                zzakb.zzdj(string);
                break;
            }
            case 1: {
                zzakb.e(string);
                break;
            }
            case 2: {
                zzakb.zzdk(string);
                break;
            }
            case 3:
            case 4: {
                zzakb.zzdj(string);
                break;
            }
            case 5: {
                zzakb.zzck(string);
                break;
            }
        }
        return super.onConsoleMessage(consoleMessage);
    }
    
    public final boolean onCreateWindow(WebView webView, final boolean b, final boolean b2, final Message message) {
        final WebView$WebViewTransport webView$WebViewTransport = (WebView$WebViewTransport)message.obj;
        webView = new WebView(webView.getContext());
        if (this.zzbnd.zzug() != null) {
            webView.setWebViewClient(this.zzbnd.zzug());
        }
        webView$WebViewTransport.setWebView(webView);
        message.sendToTarget();
        return true;
    }
    
    public final void onExceededDatabaseQuota(final String s, final String s2, final long n, long n2, long min, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        final long n3 = 5242880L - min;
        if (n3 <= 0L) {
            webStorage$QuotaUpdater.updateQuota(n);
            return;
        }
        if (n == 0L) {
            if (n2 > n3 || n2 > 1048576L) {
                n2 = 0L;
            }
        }
        else {
            if (n2 == 0L) {
                min = Math.min(Math.min(131072L, n3) + n, 1048576L);
            }
            else {
                min = n;
                if (n2 <= Math.min(1048576L - n, n3)) {
                    min = n + n2;
                }
            }
            n2 = min;
        }
        webStorage$QuotaUpdater.updateQuota(n2);
    }
    
    public final void onGeolocationPermissionsShowPrompt(final String s, final GeolocationPermissions$Callback geolocationPermissions$Callback) {
        if (geolocationPermissions$Callback != null) {
            zzbv.zzek();
            boolean b = false;
            Label_0050: {
                if (!zzakk.zzl(this.zzbnd.getContext(), "android.permission.ACCESS_FINE_LOCATION")) {
                    zzbv.zzek();
                    if (!zzakk.zzl(this.zzbnd.getContext(), "android.permission.ACCESS_COARSE_LOCATION")) {
                        b = false;
                        break Label_0050;
                    }
                }
                b = true;
            }
            geolocationPermissions$Callback.invoke(s, b, true);
        }
    }
    
    public final void onHideCustomView() {
        final zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            zzakb.zzdk("Could not get ad overlay when hiding custom view.");
            return;
        }
        zzub.zznh();
    }
    
    public final boolean onJsAlert(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(zza(webView), "alert", s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsBeforeUnload(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(zza(webView), "onBeforeUnload", s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsConfirm(final WebView webView, final String s, final String s2, final JsResult jsResult) {
        return this.zza(zza(webView), "confirm", s, s2, null, jsResult, null, false);
    }
    
    public final boolean onJsPrompt(final WebView webView, final String s, final String s2, final String s3, final JsPromptResult jsPromptResult) {
        return this.zza(zza(webView), "prompt", s, s2, s3, null, jsPromptResult, true);
    }
    
    @TargetApi(21)
    public final void onPermissionRequest(final PermissionRequest permissionRequest) {
        if (!PlatformVersion.isAtLeastLollipop() || (boolean)zzkb.zzik().zzd(zznk.zzayb)) {
            super.onPermissionRequest(permissionRequest);
            return;
        }
        if (this.zzbnd == null || this.zzbnd.zzuf() == null || this.zzbnd.zzuf().zzvf() == null) {
            super.onPermissionRequest(permissionRequest);
            return;
        }
        final String[] zzb = this.zzbnd.zzuf().zzvf().zzb(permissionRequest.getResources());
        if (zzb.length > 0) {
            permissionRequest.grant(zzb);
            return;
        }
        permissionRequest.deny();
    }
    
    public final void onReachedMaxAppCacheSize(long n, final long n2, final WebStorage$QuotaUpdater webStorage$QuotaUpdater) {
        n += 131072L;
        if (5242880L - n2 < n) {
            webStorage$QuotaUpdater.updateQuota(0L);
            return;
        }
        webStorage$QuotaUpdater.updateQuota(n);
    }
    
    @Deprecated
    public final void onShowCustomView(final View view, final int requestedOrientation, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        final zzd zzub = this.zzbnd.zzub();
        if (zzub == null) {
            zzakb.zzdk("Could not get ad overlay when showing custom view.");
            webChromeClient$CustomViewCallback.onCustomViewHidden();
            return;
        }
        zzub.zza(view, webChromeClient$CustomViewCallback);
        zzub.setRequestedOrientation(requestedOrientation);
    }
    
    public final void onShowCustomView(final View view, final WebChromeClient$CustomViewCallback webChromeClient$CustomViewCallback) {
        this.onShowCustomView(view, -1, webChromeClient$CustomViewCallback);
    }
}
