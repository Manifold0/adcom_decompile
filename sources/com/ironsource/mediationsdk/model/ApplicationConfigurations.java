package com.ironsource.mediationsdk.model;

public class ApplicationConfigurations {
    private boolean mIsIntegration;
    private ApplicationLogger mLogger;
    private ServerSegmetData mSegmetData;

    public ApplicationConfigurations() {
        this.mLogger = new ApplicationLogger();
    }

    public ApplicationConfigurations(ApplicationLogger logger, ServerSegmetData data, boolean isIntegration) {
        this.mLogger = logger;
        this.mSegmetData = data;
        this.mIsIntegration = isIntegration;
    }

    public ApplicationLogger getLoggerConfigurations() {
        return this.mLogger;
    }

    public ServerSegmetData getSegmetData() {
        return this.mSegmetData;
    }

    public boolean getIntegration() {
        return this.mIsIntegration;
    }
}
