package com.tapjoy;

import com.tapjoy.internal.ct;
import java.io.Serializable;

public class TJPlacementData implements Serializable {
    /* renamed from: a */
    private String f6963a;
    /* renamed from: b */
    private String f6964b;
    /* renamed from: c */
    private String f6965c;
    /* renamed from: d */
    private String f6966d;
    /* renamed from: e */
    private String f6967e;
    /* renamed from: f */
    private int f6968f;
    /* renamed from: g */
    private String f6969g;
    /* renamed from: h */
    private String f6970h;
    /* renamed from: i */
    private int f6971i;
    /* renamed from: j */
    private boolean f6972j;
    /* renamed from: k */
    private String f6973k;
    /* renamed from: l */
    private boolean f6974l;
    /* renamed from: m */
    private String f6975m;
    /* renamed from: n */
    private String f6976n;
    /* renamed from: o */
    private boolean f6977o = true;
    /* renamed from: p */
    private boolean f6978p = false;

    public TJPlacementData(String key, String url) {
        setKey(key);
        updateUrl(url);
        setPlacementType(TapjoyConstants.TJC_APP_PLACEMENT);
    }

    public TJPlacementData(String baseUrl, String httpResponse, String callbackID) {
        setBaseURL(baseUrl);
        setHttpResponse(httpResponse);
        this.f6975m = callbackID;
        this.f6977o = false;
        setPlacementType(TapjoyConstants.TJC_APP_PLACEMENT);
    }

    public void resetPlacementRequestData() {
        setHttpResponse(null);
        setHttpStatusCode(0);
        setRedirectURL(null);
        setHasProgressSpinner(false);
        setPrerenderingRequested(false);
        setPreloadDisabled(false);
        setContentViewId(null);
    }

    public String getCallbackID() {
        return this.f6975m;
    }

    public boolean isBaseActivity() {
        return this.f6977o;
    }

    public void setKey(String key) {
        this.f6963a = key;
    }

    public void setBaseURL(String baseURL) {
        this.f6965c = baseURL;
    }

    public void setMediationURL(String mediationURL) {
        this.f6966d = mediationURL;
    }

    public void setHttpResponse(String httpResponse) {
        this.f6967e = httpResponse;
    }

    public void setHttpStatusCode(int httpStatusCode) {
        this.f6968f = httpStatusCode;
    }

    public void setPlacementName(String placementName) {
        this.f6969g = placementName;
    }

    public void setPlacementType(String placementType) {
        this.f6970h = placementType;
    }

    public void setViewType(int viewType) {
        this.f6971i = viewType;
    }

    public void setRedirectURL(String redirectURL) {
        this.f6973k = redirectURL;
    }

    public void setHasProgressSpinner(boolean hasProgressSpinner) {
        this.f6972j = hasProgressSpinner;
    }

    public void setContentViewId(String contentViewId) {
        this.f6976n = contentViewId;
    }

    public String getUrl() {
        return this.f6964b;
    }

    public String getKey() {
        return this.f6963a;
    }

    public String getBaseURL() {
        return this.f6965c;
    }

    public String getMediationURL() {
        return this.f6966d;
    }

    public String getHttpResponse() {
        return this.f6967e;
    }

    public int getHttpStatusCode() {
        return this.f6968f;
    }

    public String getPlacementName() {
        return this.f6969g;
    }

    public String getPlacementType() {
        return this.f6970h;
    }

    public int getViewType() {
        return this.f6971i;
    }

    public String getRedirectURL() {
        return this.f6973k;
    }

    public String getContentViewId() {
        return this.f6976n;
    }

    public boolean hasProgressSpinner() {
        return this.f6972j;
    }

    public void setPreloadDisabled(boolean value) {
        this.f6978p = value;
    }

    public boolean isPreloadDisabled() {
        return this.f6978p;
    }

    public boolean isPrerenderingRequested() {
        return this.f6974l;
    }

    public void setPrerenderingRequested(boolean prerenderingRequested) {
        this.f6974l = prerenderingRequested;
    }

    public void updateUrl(String url) {
        this.f6964b = url;
        if (!ct.m7339c(url)) {
            setBaseURL(url.substring(0, url.indexOf(47, url.indexOf("//") + 3)));
        }
    }
}
