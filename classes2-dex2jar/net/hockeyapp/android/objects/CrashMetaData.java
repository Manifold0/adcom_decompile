// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.objects;

public class CrashMetaData
{
    private String mUserDescription;
    private String mUserEmail;
    private String mUserID;
    
    public String getUserDescription() {
        return this.mUserDescription;
    }
    
    public String getUserEmail() {
        return this.mUserEmail;
    }
    
    public String getUserID() {
        return this.mUserID;
    }
    
    public void setUserDescription(final String mUserDescription) {
        this.mUserDescription = mUserDescription;
    }
    
    public void setUserEmail(final String mUserEmail) {
        this.mUserEmail = mUserEmail;
    }
    
    public void setUserID(final String mUserID) {
        this.mUserID = mUserID;
    }
    
    @Override
    public String toString() {
        return "\n" + CrashMetaData.class.getSimpleName() + "\nuserDescription " + this.mUserDescription + "\nuserEmail       " + this.mUserEmail + "\nuserID          " + this.mUserID;
    }
}
