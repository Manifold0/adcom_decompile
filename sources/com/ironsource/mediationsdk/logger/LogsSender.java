package com.ironsource.mediationsdk.logger;

import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.server.HttpFunctions;
import java.util.ArrayList;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

class LogsSender implements Runnable {
    private final String AUTHO_PASSWORD = "k@r@puz";
    private final String AUTHO_USERNAME = "mobilelogs";
    private final String LOG_URL = "https://mobilelogs.supersonic.com";
    private ArrayList<ServerLogEntry> mLogs;

    public LogsSender(ArrayList<ServerLogEntry> logs) {
        this.mLogs = logs;
    }

    private JSONObject getJSONToSend() {
        JSONObject logContent = new JSONObject();
        try {
            logContent.put("general_properties", GeneralProperties.getProperties().toJSON());
            JSONArray logData = new JSONArray();
            Iterator it = this.mLogs.iterator();
            while (it.hasNext()) {
                logData.put(((ServerLogEntry) it.next()).toJSON());
            }
            logContent.put("log_data", logData);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return logContent;
    }

    private void sendLogs(JSONObject logContent) {
        HttpFunctions.getStringFromPostWithAutho("https://mobilelogs.supersonic.com", logContent.toString(), "mobilelogs", "k@r@puz");
    }

    public void run() {
    }
}
