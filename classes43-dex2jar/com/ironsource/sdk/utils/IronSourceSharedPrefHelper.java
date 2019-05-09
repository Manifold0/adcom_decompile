// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.sdk.utils;

import com.ironsource.sdk.data.SSABCParameters;
import android.text.TextUtils;
import java.util.Collection;
import com.ironsource.sdk.data.SSAObj;
import java.util.ArrayList;
import java.util.List;
import com.ironsource.sdk.data.SSAEnums;
import android.content.SharedPreferences$Editor;
import org.json.JSONException;
import org.json.JSONArray;
import org.json.JSONObject;
import com.ironsource.sdk.data.SSASession;
import android.content.Context;
import android.content.SharedPreferences;

public class IronSourceSharedPrefHelper
{
    private static final String APPLICATION_KEY_IS = "application_key_is";
    private static final String APPLICATION_KEY_OW = "application_key_ow";
    private static final String APPLICATION_KEY_RV = "application_key_rv";
    private static final String BACK_BUTTON_STATE = "back_button_state";
    private static final String IS_REPORTED = "is_reported";
    private static final String REGISTER_SESSIONS = "register_sessions";
    private static final String SEARCH_KEYS = "search_keys";
    private static final String SESSIONS = "sessions";
    private static final String SSA_RV_PARAMETER_CONNECTION_RETRIES = "ssa_rv_parameter_connection_retries";
    private static final String SSA_SDK_DOWNLOAD_URL = "ssa_sdk_download_url";
    private static final String SSA_SDK_LOAD_URL = "ssa_sdk_load_url";
    private static final String SUPERSONIC_SHARED_PREF = "supersonic_shared_preferen";
    private static final String UNIQUE_ID_IS = "unique_id_is";
    private static final String UNIQUE_ID_OW = "unique_id_ow";
    private static final String UNIQUE_ID_RV = "unique_id_rv";
    private static final String USER_ID_IS = "user_id_is";
    private static final String USER_ID_OW = "user_id_ow";
    private static final String USER_ID_RV = "user_id_rv";
    private static final String VERSION = "version";
    private static IronSourceSharedPrefHelper mInstance;
    private SharedPreferences mSharedPreferences;
    
    private IronSourceSharedPrefHelper(final Context context) {
        this.mSharedPreferences = context.getSharedPreferences("supersonic_shared_preferen", 0);
    }
    
    private boolean getShouldRegisterSessions() {
        return this.mSharedPreferences.getBoolean("register_sessions", true);
    }
    
    public static IronSourceSharedPrefHelper getSupersonicPrefHelper() {
        synchronized (IronSourceSharedPrefHelper.class) {
            return IronSourceSharedPrefHelper.mInstance;
        }
    }
    
    public static IronSourceSharedPrefHelper getSupersonicPrefHelper(final Context context) {
        synchronized (IronSourceSharedPrefHelper.class) {
            if (IronSourceSharedPrefHelper.mInstance == null) {
                IronSourceSharedPrefHelper.mInstance = new IronSourceSharedPrefHelper(context);
            }
            return IronSourceSharedPrefHelper.mInstance;
        }
    }
    
    public void addSession(final SSASession ssaSession) {
        if (!this.getShouldRegisterSessions()) {
            return;
        }
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("sessionStartTime", ssaSession.getSessionStartTime());
                jsonObject.put("sessionEndTime", ssaSession.getSessionEndTime());
                jsonObject.put("sessionType", (Object)ssaSession.getSessionType());
                jsonObject.put("connectivity", (Object)ssaSession.getConnectivity());
                JSONArray sessions;
                if ((sessions = this.getSessions()) == null) {
                    sessions = new JSONArray();
                }
                sessions.put((Object)jsonObject);
                final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
                edit.putString("sessions", sessions.toString());
                edit.commit();
            }
            catch (JSONException ex) {
                continue;
            }
            break;
        }
    }
    
    public void deleteSessions() {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString("sessions", (String)null);
        edit.commit();
    }
    
    public String getApplicationKey(final SSAEnums.ProductType productType) {
        switch (productType) {
            default: {
                return "EMPTY_APPLICATION_KEY";
            }
            case RewardedVideo: {
                return this.mSharedPreferences.getString("application_key_rv", (String)null);
            }
            case OfferWall: {
                return this.mSharedPreferences.getString("application_key_ow", (String)null);
            }
            case Interstitial: {
                return this.mSharedPreferences.getString("application_key_is", (String)null);
            }
        }
    }
    
    public SSAEnums.BackButtonState getBackButtonState() {
        final int int1 = Integer.parseInt(this.mSharedPreferences.getString("back_button_state", "2"));
        if (int1 == 0) {
            return SSAEnums.BackButtonState.None;
        }
        if (int1 == 1) {
            return SSAEnums.BackButtonState.Device;
        }
        if (int1 == 2) {
            return SSAEnums.BackButtonState.Controller;
        }
        return SSAEnums.BackButtonState.Controller;
    }
    
    public String getCampaignLastUpdate(final String s) {
        return this.mSharedPreferences.getString(s, (String)null);
    }
    
    public String getConnectionRetries() {
        return this.mSharedPreferences.getString("ssa_rv_parameter_connection_retries", "3");
    }
    
    public String getCurrentSDKVersion() {
        return this.mSharedPreferences.getString("version", "UN_VERSIONED");
    }
    
    public boolean getReportAppStarted() {
        return this.mSharedPreferences.getBoolean("is_reported", false);
    }
    
    public String getSDKDownloadUrl() {
        return this.mSharedPreferences.getString("ssa_sdk_download_url", (String)null);
    }
    
    public List<String> getSearchKeys() {
        final String string = this.mSharedPreferences.getString("search_keys", (String)null);
        final ArrayList<String> list = new ArrayList<String>();
        if (string == null) {
            return list;
        }
        final SSAObj ssaObj = new SSAObj(string);
        if (!ssaObj.containsKey("searchKeys")) {
            return list;
        }
        final JSONArray jsonArray = (JSONArray)ssaObj.get("searchKeys");
        try {
            list.addAll(ssaObj.toList(jsonArray));
            return list;
        }
        catch (JSONException ex) {
            ex.printStackTrace();
            return list;
        }
    }
    
    public JSONArray getSessions() {
        final String string = this.mSharedPreferences.getString("sessions", (String)null);
        if (string == null) {
            return new JSONArray();
        }
        try {
            return new JSONArray(string);
        }
        catch (JSONException ex) {
            return new JSONArray();
        }
    }
    
    public String getUniqueId(final SSAEnums.ProductType productType) {
        switch (productType) {
            default: {
                return "EMPTY_UNIQUE_ID";
            }
            case RewardedVideo: {
                return this.mSharedPreferences.getString("unique_id_rv", (String)null);
            }
            case OfferWall: {
                return this.mSharedPreferences.getString("unique_id_ow", (String)null);
            }
            case Interstitial: {
                return this.mSharedPreferences.getString("unique_id_is", (String)null);
            }
        }
    }
    
    public String getUniqueId(final String s) {
        String string = "EMPTY_UNIQUE_ID";
        if (s.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            string = this.mSharedPreferences.getString("unique_id_rv", (String)null);
        }
        else {
            if (s.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
                return this.mSharedPreferences.getString("unique_id_ow", (String)null);
            }
            if (s.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
                return this.mSharedPreferences.getString("unique_id_is", (String)null);
            }
        }
        return string;
    }
    
    public String getUserData(String string) {
        string = this.mSharedPreferences.getString(string, (String)null);
        if (string != null) {
            return string;
        }
        return "{}";
    }
    
    public void setApplicationKey(final String s, final SSAEnums.ProductType productType) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        switch (productType) {
            case RewardedVideo: {
                edit.putString("application_key_rv", s);
                break;
            }
            case OfferWall: {
                edit.putString("application_key_ow", s);
                break;
            }
            case Interstitial: {
                edit.putString("application_key_is", s);
                break;
            }
        }
        edit.commit();
    }
    
    public void setBackButtonState(final String s) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString("back_button_state", s);
        edit.commit();
    }
    
    public void setCampaignLastUpdate(final String s, final String s2) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString(s, s2);
        edit.commit();
    }
    
    public void setCurrentSDKVersion(final String s) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString("version", s);
        edit.commit();
    }
    
    public boolean setLatestCompeltionsTime(final String s, final String s2, final String s3) {
        final boolean b = false;
        final String string = this.mSharedPreferences.getString("ssaUserData", (String)null);
        boolean commit = b;
        if (TextUtils.isEmpty((CharSequence)string)) {
            return commit;
        }
        try {
            final JSONObject jsonObject = new JSONObject(string);
            commit = b;
            if (!jsonObject.isNull(s2)) {
                final JSONObject jsonObject2 = jsonObject.getJSONObject(s2);
                commit = b;
                if (!jsonObject2.isNull(s3)) {
                    jsonObject2.getJSONObject(s3).put("timestamp", (Object)s);
                    final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
                    edit.putString("ssaUserData", jsonObject.toString());
                    commit = edit.commit();
                }
            }
            return commit;
        }
        catch (JSONException ex) {
            new IronSourceAsyncHttpRequestTask().execute((Object[])new String[] { "https://www.supersonicads.com/mobile/sdk5/log?method=" + ex.getStackTrace()[0].getMethodName() });
            return false;
        }
    }
    
    public void setReportAppStarted(final boolean b) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putBoolean("is_reported", b);
        edit.apply();
    }
    
    public void setSSABCParameters(final SSABCParameters ssabcParameters) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString("ssa_rv_parameter_connection_retries", ssabcParameters.getConnectionRetries());
        edit.commit();
    }
    
    public void setSearchKeys(final String s) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString("search_keys", s);
        edit.commit();
    }
    
    public void setShouldRegisterSessions(final boolean b) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putBoolean("register_sessions", b);
        edit.commit();
    }
    
    public boolean setUniqueId(final String s, final String s2) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        if (s2.equalsIgnoreCase(SSAEnums.ProductType.RewardedVideo.toString())) {
            edit.putString("unique_id_rv", s);
        }
        else if (s2.equalsIgnoreCase(SSAEnums.ProductType.OfferWall.toString())) {
            edit.putString("unique_id_ow", s);
        }
        else if (s2.equalsIgnoreCase(SSAEnums.ProductType.Interstitial.toString())) {
            edit.putString("unique_id_is", s);
        }
        return edit.commit();
    }
    
    public boolean setUserData(final String s, final String s2) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        edit.putString(s, s2);
        return edit.commit();
    }
    
    public void setUserID(final String s, final SSAEnums.ProductType productType) {
        final SharedPreferences$Editor edit = this.mSharedPreferences.edit();
        switch (productType) {
            case RewardedVideo: {
                edit.putString("user_id_rv", s);
                break;
            }
            case OfferWall: {
                edit.putString("user_id_ow", s);
                break;
            }
            case Interstitial: {
                edit.putString("user_id_is", s);
                break;
            }
        }
        edit.commit();
    }
}
