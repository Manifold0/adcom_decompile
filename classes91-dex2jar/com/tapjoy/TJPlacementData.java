// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import com.tapjoy.internal.ct;
import java.io.Serializable;

public class TJPlacementData implements Serializable
{
    private String a;
    private String b;
    private String c;
    private String d;
    private String e;
    private int f;
    private String g;
    private String h;
    private int i;
    private boolean j;
    private String k;
    private boolean l;
    private String m;
    private String n;
    private boolean o;
    private boolean p;
    
    public TJPlacementData(final String key, final String s) {
        this.o = true;
        this.p = false;
        this.setKey(key);
        this.updateUrl(s);
        this.setPlacementType("app");
    }
    
    public TJPlacementData(final String baseURL, final String httpResponse, final String m) {
        this.o = true;
        this.p = false;
        this.setBaseURL(baseURL);
        this.setHttpResponse(httpResponse);
        this.m = m;
        this.o = false;
        this.setPlacementType("app");
    }
    
    public String getBaseURL() {
        return this.c;
    }
    
    public String getCallbackID() {
        return this.m;
    }
    
    public String getContentViewId() {
        return this.n;
    }
    
    public String getHttpResponse() {
        return this.e;
    }
    
    public int getHttpStatusCode() {
        return this.f;
    }
    
    public String getKey() {
        return this.a;
    }
    
    public String getMediationURL() {
        return this.d;
    }
    
    public String getPlacementName() {
        return this.g;
    }
    
    public String getPlacementType() {
        return this.h;
    }
    
    public String getRedirectURL() {
        return this.k;
    }
    
    public String getUrl() {
        return this.b;
    }
    
    public int getViewType() {
        return this.i;
    }
    
    public boolean hasProgressSpinner() {
        return this.j;
    }
    
    public boolean isBaseActivity() {
        return this.o;
    }
    
    public boolean isPreloadDisabled() {
        return this.p;
    }
    
    public boolean isPrerenderingRequested() {
        return this.l;
    }
    
    public void resetPlacementRequestData() {
        this.setHttpResponse(null);
        this.setHttpStatusCode(0);
        this.setRedirectURL(null);
        this.setHasProgressSpinner(false);
        this.setPrerenderingRequested(false);
        this.setPreloadDisabled(false);
        this.setContentViewId(null);
    }
    
    public void setBaseURL(final String c) {
        this.c = c;
    }
    
    public void setContentViewId(final String n) {
        this.n = n;
    }
    
    public void setHasProgressSpinner(final boolean j) {
        this.j = j;
    }
    
    public void setHttpResponse(final String e) {
        this.e = e;
    }
    
    public void setHttpStatusCode(final int f) {
        this.f = f;
    }
    
    public void setKey(final String a) {
        this.a = a;
    }
    
    public void setMediationURL(final String d) {
        this.d = d;
    }
    
    public void setPlacementName(final String g) {
        this.g = g;
    }
    
    public void setPlacementType(final String h) {
        this.h = h;
    }
    
    public void setPreloadDisabled(final boolean p) {
        this.p = p;
    }
    
    public void setPrerenderingRequested(final boolean l) {
        this.l = l;
    }
    
    public void setRedirectURL(final String k) {
        this.k = k;
    }
    
    public void setViewType(final int i) {
        this.i = i;
    }
    
    public void updateUrl(final String b) {
        this.b = b;
        if (!ct.c(b)) {
            this.setBaseURL(b.substring(0, b.indexOf(47, b.indexOf("//") + 3)));
        }
    }
}
