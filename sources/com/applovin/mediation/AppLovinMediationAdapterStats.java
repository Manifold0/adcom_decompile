package com.applovin.mediation;

public class AppLovinMediationAdapterStats {
    /* renamed from: a */
    private final String f2645a;
    /* renamed from: b */
    private final long f2646b;

    public AppLovinMediationAdapterStats(String str, long j) {
        if (str == null) {
            throw new IllegalArgumentException("No adapter name specified");
        }
        this.f2645a = str;
        this.f2646b = j;
    }

    public String getAdapterName() {
        return this.f2645a;
    }

    public long getLastAdLoadMillis() {
        return this.f2646b;
    }

    public String toString() {
        return "[Adapter Stats - <" + this.f2645a + " : loaded in " + this.f2646b + "milliseconds>]";
    }
}
