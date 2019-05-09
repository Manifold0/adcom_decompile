// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import org.json.JSONObject;
import java.io.ObjectStreamField;
import java.io.Serializable;

public class AdjustAttribution implements Serializable
{
    private static final ObjectStreamField[] serialPersistentFields;
    private static final long serialVersionUID = 1L;
    public String adgroup;
    public String adid;
    public String campaign;
    public String clickLabel;
    public String creative;
    public String network;
    public String trackerName;
    public String trackerToken;
    
    static {
        serialPersistentFields = new ObjectStreamField[] { new ObjectStreamField("trackerToken", String.class), new ObjectStreamField("trackerName", String.class), new ObjectStreamField("network", String.class), new ObjectStreamField("campaign", String.class), new ObjectStreamField("adgroup", String.class), new ObjectStreamField("creative", String.class), new ObjectStreamField("clickLabel", String.class), new ObjectStreamField("adid", String.class) };
    }
    
    public static AdjustAttribution fromJson(final JSONObject jsonObject, final String adid) {
        if (jsonObject == null) {
            return null;
        }
        final AdjustAttribution adjustAttribution = new AdjustAttribution();
        adjustAttribution.trackerToken = jsonObject.optString("tracker_token", (String)null);
        adjustAttribution.trackerName = jsonObject.optString("tracker_name", (String)null);
        adjustAttribution.network = jsonObject.optString("network", (String)null);
        adjustAttribution.campaign = jsonObject.optString("campaign", (String)null);
        adjustAttribution.adgroup = jsonObject.optString("adgroup", (String)null);
        adjustAttribution.creative = jsonObject.optString("creative", (String)null);
        adjustAttribution.clickLabel = jsonObject.optString("click_label", (String)null);
        adjustAttribution.adid = adid;
        return adjustAttribution;
    }
    
    private void readObject(final ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
    }
    
    private void writeObject(final ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (o == null) {
                return false;
            }
            if (this.getClass() != o.getClass()) {
                return false;
            }
            final AdjustAttribution adjustAttribution = (AdjustAttribution)o;
            if (!Util.equalString(this.trackerToken, adjustAttribution.trackerToken)) {
                return false;
            }
            if (!Util.equalString(this.trackerName, adjustAttribution.trackerName)) {
                return false;
            }
            if (!Util.equalString(this.network, adjustAttribution.network)) {
                return false;
            }
            if (!Util.equalString(this.campaign, adjustAttribution.campaign)) {
                return false;
            }
            if (!Util.equalString(this.adgroup, adjustAttribution.adgroup)) {
                return false;
            }
            if (!Util.equalString(this.creative, adjustAttribution.creative)) {
                return false;
            }
            if (!Util.equalString(this.clickLabel, adjustAttribution.clickLabel)) {
                return false;
            }
            if (!Util.equalString(this.adid, adjustAttribution.adid)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (((((((Util.hashString(this.trackerToken) + 629) * 37 + Util.hashString(this.trackerName)) * 37 + Util.hashString(this.network)) * 37 + Util.hashString(this.campaign)) * 37 + Util.hashString(this.adgroup)) * 37 + Util.hashString(this.creative)) * 37 + Util.hashString(this.clickLabel)) * 37 + Util.hashString(this.adid);
    }
    
    @Override
    public String toString() {
        return Util.formatString("tt:%s tn:%s net:%s cam:%s adg:%s cre:%s cl:%s adid:%s", this.trackerToken, this.trackerName, this.network, this.campaign, this.adgroup, this.creative, this.clickLabel, this.adid);
    }
}
