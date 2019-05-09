package com.unity3d.ads.metadata;

import android.content.Context;

public class MediationMetaData extends MetaData {
    public static final String KEY_MISSED_IMPRESSION_ORDINAL = "missedImpressionOrdinal";
    public static final String KEY_NAME = "name";
    public static final String KEY_ORDINAL = "ordinal";
    public static final String KEY_VERSION = "version";

    public MediationMetaData(Context context) {
        super(context);
        setCategory("mediation");
    }

    public void setOrdinal(int ordinal) {
        set(KEY_ORDINAL, Integer.valueOf(ordinal));
    }

    public void setMissedImpressionOrdinal(int ordinal) {
        set(KEY_MISSED_IMPRESSION_ORDINAL, Integer.valueOf(ordinal));
    }

    public void setName(String mediationNetworkName) {
        set("name", mediationNetworkName);
    }

    public void setVersion(String mediationSdkVersion) {
        set("version", mediationSdkVersion);
    }
}
