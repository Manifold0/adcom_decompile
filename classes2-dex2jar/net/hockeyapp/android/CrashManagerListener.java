// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

public abstract class CrashManagerListener
{
    public String getContact() {
        return null;
    }
    
    public String getDescription() {
        return null;
    }
    
    public int getMaxRetryAttempts() {
        return 1;
    }
    
    public String getUserID() {
        return null;
    }
    
    public boolean ignoreDefaultHandler() {
        return false;
    }
    
    public boolean includeDeviceData() {
        return true;
    }
    
    public boolean includeDeviceIdentifier() {
        return true;
    }
    
    public boolean includeThreadDetails() {
        return true;
    }
    
    public void onConfirmedCrashesFound() {
    }
    
    public boolean onCrashesFound() {
        return false;
    }
    
    public void onCrashesNotSent() {
    }
    
    public void onCrashesSent() {
    }
    
    public boolean onHandleAlertView() {
        return false;
    }
    
    public void onNewCrashesFound() {
    }
    
    public void onUserDeniedCrashes() {
    }
    
    public boolean shouldAutoUploadCrashes() {
        return false;
    }
}
