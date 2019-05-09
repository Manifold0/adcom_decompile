// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.server.HttpFunctions;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONArray;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import org.json.JSONObject;
import java.util.ArrayList;

class LogsSender implements Runnable
{
    private final String AUTHO_PASSWORD;
    private final String AUTHO_USERNAME;
    private final String LOG_URL;
    private ArrayList<ServerLogEntry> mLogs;
    
    public LogsSender(final ArrayList<ServerLogEntry> mLogs) {
        this.LOG_URL = "https://mobilelogs.supersonic.com";
        this.AUTHO_USERNAME = "mobilelogs";
        this.AUTHO_PASSWORD = "k@r@puz";
        this.mLogs = mLogs;
    }
    
    private JSONObject getJSONToSend() {
        final JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray;
        try {
            jsonObject.put("general_properties", (Object)GeneralProperties.getProperties().toJSON());
            jsonArray = new JSONArray();
            final Iterator<ServerLogEntry> iterator = this.mLogs.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next().toJSON());
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return jsonObject;
        }
        jsonObject.put("log_data", (Object)jsonArray);
        return jsonObject;
    }
    
    private void sendLogs(final JSONObject jsonObject) {
        HttpFunctions.getStringFromPostWithAutho("https://mobilelogs.supersonic.com", jsonObject.toString(), "mobilelogs", "k@r@puz");
    }
    
    @Override
    public void run() {
    }
}
