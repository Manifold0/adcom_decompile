// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import org.json.JSONArray;
import java.util.Date;

public abstract class UpdateManagerListener
{
    public boolean canUpdateInMarket() {
        return false;
    }
    
    public Date getExpiryDate() {
        return null;
    }
    
    public Class<? extends UpdateActivity> getUpdateActivityClass() {
        return UpdateActivity.class;
    }
    
    public Class<? extends UpdateFragment> getUpdateFragmentClass() {
        return UpdateFragment.class;
    }
    
    public boolean onBuildExpired() {
        return true;
    }
    
    public void onCancel() {
    }
    
    public void onNoUpdateAvailable() {
    }
    
    public void onUpdateAvailable() {
    }
    
    public void onUpdateAvailable(final JSONArray jsonArray, final String s) {
        this.onUpdateAvailable();
    }
    
    public void onUpdatePermissionsNotGranted() {
    }
}
