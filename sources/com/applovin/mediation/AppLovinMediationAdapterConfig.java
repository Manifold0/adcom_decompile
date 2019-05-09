package com.applovin.mediation;

public interface AppLovinMediationAdapterConfig {
    Boolean getBoolean(String str, Boolean bool);

    boolean getBoolean(String str);

    int getInt(String str, int i);

    long getLong(String str, long j);

    String getString(String str, String str2);
}
