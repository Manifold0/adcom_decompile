// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.ads;

import java.io.Serializable;

public class RewardData implements Serializable
{
    public static final long serialVersionUID = 1L;
    private String a;
    private String b;
    
    public RewardData(final String a, final String b) {
        this.a = a;
        this.b = b;
    }
    
    public String getCurrency() {
        return this.b;
    }
    
    public String getUserID() {
        return this.a;
    }
}
