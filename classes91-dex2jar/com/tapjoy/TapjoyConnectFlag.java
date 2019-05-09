// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import java.util.Hashtable;

public class TapjoyConnectFlag
{
    public static final Hashtable CONNECT_FLAG_DEFAULTS;
    public static final String DISABLE_ADVERTISING_ID_CHECK = "TJC_OPTION_DISABLE_ADVERTISING_ID_CHECK";
    public static final String DISABLE_ANDROID_ID_AS_ANALYTICS_ID = "TJC_OPTION_DISABLE_ANDROID_ID_AS_ANALYTICS_ID";
    public static final String DISABLE_AUTOMATIC_SESSION_TRACKING = "TJC_OPTION_DISABLE_AUTOMATIC_SESSION_TRACKING";
    public static final String DISABLE_PERSISTENT_IDS = "TJC_OPTION_DISABLE_PERSISTENT_IDS";
    public static final String ENABLE_LOGGING = "TJC_OPTION_ENABLE_LOGGING";
    public static final String[] FLAG_ARRAY;
    public static final String MEDIATION_CONFIGS = "TJC_OPTION_MEDIATION_CONFIGS";
    public static final String PLACEMENT_URL = "TJC_OPTION_PLACEMENT_SERVICE_URL";
    public static final String SERVICE_URL = "TJC_OPTION_SERVICE_URL";
    public static final String[] STORE_ARRAY;
    public static final String STORE_GFAN = "gfan";
    public static final String STORE_NAME = "TJC_OPTION_STORE_NAME";
    public static final String STORE_SKT = "skt";
    public static final String USER_ID = "TJC_OPTION_USER_ID";
    
    static {
        FLAG_ARRAY = new String[] { "TJC_OPTION_USER_ID", "TJC_OPTION_ENABLE_LOGGING", "TJC_OPTION_SERVICE_URL", "TJC_OPTION_PLACEMENT_SERVICE_URL", "TJC_OPTION_STORE_NAME" };
        STORE_ARRAY = new String[] { "gfan", "skt" };
        CONNECT_FLAG_DEFAULTS = new Hashtable() {
            {
                this.put("TJC_OPTION_SERVICE_URL", "https://ws.tapjoyads.com/");
                this.put("TJC_OPTION_PLACEMENT_SERVICE_URL", "https://placements.tapjoy.com/");
            }
        };
    }
}
