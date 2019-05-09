package com.facebook.ads;

import java.io.Serializable;

public class RewardData implements Serializable {
    public static final long serialVersionUID = 1;
    /* renamed from: a */
    private String f3819a;
    /* renamed from: b */
    private String f3820b;

    public RewardData(String str, String str2) {
        this.f3819a = str;
        this.f3820b = str2;
    }

    public String getCurrency() {
        return this.f3820b;
    }

    public String getUserID() {
        return this.f3819a;
    }
}
