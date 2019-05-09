// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.model;

public class ApplicationConfigurations
{
    private boolean mIsIntegration;
    private ApplicationLogger mLogger;
    private ServerSegmetData mSegmetData;
    
    public ApplicationConfigurations() {
        this.mLogger = new ApplicationLogger();
    }
    
    public ApplicationConfigurations(final ApplicationLogger mLogger, final ServerSegmetData mSegmetData, final boolean mIsIntegration) {
        this.mLogger = mLogger;
        this.mSegmetData = mSegmetData;
        this.mIsIntegration = mIsIntegration;
    }
    
    public boolean getIntegration() {
        return this.mIsIntegration;
    }
    
    public ApplicationLogger getLoggerConfigurations() {
        return this.mLogger;
    }
    
    public ServerSegmetData getSegmetData() {
        return this.mSegmetData;
    }
}
