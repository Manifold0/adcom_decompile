// 
// Decompiled by Procyon v0.5.34
// 

package com.chartboost.sdk.impl;

import com.chartboost.sdk.i;
import org.json.JSONObject;
import com.chartboost.sdk.Model.CBError;

public class g implements a
{
    private final e a;
    private final String b;
    
    public g(final e a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    @Override
    public void a(final aj aj, final CBError cbError) {
        if (this.a.f.h) {
            synchronized (this.a) {
                this.a.b(this.b);
            }
        }
    }
    
    @Override
    public void a(final aj aj, final JSONObject jsonObject) {
        if (this.a.f.h || com.chartboost.sdk.i.t) {
            synchronized (this.a) {
                this.a.b(this.b);
            }
        }
    }
}
