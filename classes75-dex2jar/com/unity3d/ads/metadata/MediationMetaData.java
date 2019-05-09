// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.ads.metadata;

import android.content.Context;

public class MediationMetaData extends MetaData
{
    public static final String KEY_MISSED_IMPRESSION_ORDINAL = "missedImpressionOrdinal";
    public static final String KEY_NAME = "name";
    public static final String KEY_ORDINAL = "ordinal";
    public static final String KEY_VERSION = "version";
    
    public MediationMetaData(final Context context) {
        super(context);
        this.setCategory("mediation");
    }
    
    public void setMissedImpressionOrdinal(final int n) {
        this.set("missedImpressionOrdinal", n);
    }
    
    public void setName(final String s) {
        this.set("name", s);
    }
    
    public void setOrdinal(final int n) {
        this.set("ordinal", n);
    }
    
    public void setVersion(final String s) {
        this.set("version", s);
    }
}
