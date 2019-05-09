// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads.internal.protocol;

import android.text.TextUtils;

public class a
{
    private final AdErrorType a;
    private final String b;
    
    public a(final int n, final String s) {
        this(AdErrorType.adErrorTypeFromCode(n), s);
    }
    
    public a(final AdErrorType a, final String s) {
        String defaultErrorMessage = s;
        if (TextUtils.isEmpty((CharSequence)s)) {
            defaultErrorMessage = a.getDefaultErrorMessage();
        }
        this.a = a;
        this.b = defaultErrorMessage;
    }
    
    public static a a(final AdErrorType adErrorType, final String s) {
        return new a(adErrorType, s);
    }
    
    public static a a(final b b) {
        return new a(b.a(), b.b());
    }
    
    public AdErrorType a() {
        return this.a;
    }
    
    public String b() {
        return this.b;
    }
}
