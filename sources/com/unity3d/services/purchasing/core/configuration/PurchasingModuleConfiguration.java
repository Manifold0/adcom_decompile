package com.unity3d.services.purchasing.core.configuration;

import com.unity3d.services.core.configuration.Configuration;
import com.unity3d.services.core.configuration.IModuleConfiguration;
import com.unity3d.services.purchasing.core.api.CustomPurchasing;

public class PurchasingModuleConfiguration implements IModuleConfiguration {
    private static final Class[] WEB_APP_API_CLASS_LIST = new Class[]{CustomPurchasing.class};

    public Class[] getWebAppApiClassList() {
        return WEB_APP_API_CLASS_LIST;
    }

    public boolean resetState(Configuration configuration) {
        return true;
    }

    public boolean initModuleState(Configuration configuration) {
        return true;
    }

    public boolean initErrorState(Configuration configuration, String state, String message) {
        return true;
    }

    public boolean initCompleteState(Configuration configuration) {
        return true;
    }
}
