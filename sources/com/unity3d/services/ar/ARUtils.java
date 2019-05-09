package com.unity3d.services.ar;

import android.annotation.TargetApi;
import android.content.Context;
import com.google.ar.core.ArCoreApk;
import com.google.ar.core.ArCoreApk.Availability;
import com.google.ar.core.Config;
import com.google.ar.core.Config.LightEstimationMode;
import com.google.ar.core.Config.PlaneFindingMode;
import com.google.ar.core.Config.UpdateMode;
import com.google.ar.core.Session;
import com.google.ar.core.exceptions.FatalException;
import com.google.ar.core.exceptions.UnavailableException;
import com.unity3d.services.core.log.DeviceLog;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ARUtils {
    public static final int AR_CHECK_NOT_SUPPORTED = 0;
    public static final int AR_CHECK_SUPPORTED = 1;
    public static final int AR_CHECK_TRANSIENT = 2;
    private static LightEstimationMode[] lightEstimationModes;
    private static PlaneFindingMode[] planeFindingModes;
    private static UpdateMode[] updateModes;

    public static int isSupported(Context context) {
        if (!ARCheck.isFrameworkPresent()) {
            return 0;
        }
        if (planeFindingModes == null) {
            planeFindingModes = PlaneFindingMode.values();
            lightEstimationModes = LightEstimationMode.values();
            updateModes = UpdateMode.values();
        }
        Availability availability = ArCoreApk.getInstance().checkAvailability(context);
        if (availability.isTransient()) {
            return 2;
        }
        if (availability != Availability.SUPPORTED_INSTALLED) {
            return 0;
        }
        try {
            Session session = new Session(context);
        } catch (FatalException e) {
            return 0;
        } catch (UnavailableException e2) {
            return 0;
        } catch (SecurityException e3) {
        }
        return 1;
    }

    public static Config createConfiguration(JSONObject properties, Session session) {
        int i = 0;
        Config config = new Config(session);
        if (properties.has("lightEstimationMode")) {
            try {
                String lightEstimationMode = properties.getString("lightEstimationMode");
                for (LightEstimationMode lem : lightEstimationModes) {
                    if (lightEstimationMode.equalsIgnoreCase(lem.name())) {
                        config.setLightEstimationMode(lem);
                        break;
                    }
                }
            } catch (JSONException e) {
                DeviceLog.warning("lightEstimationEnabled is not a string.");
            }
        }
        if (properties.has("planeFindingMode")) {
            try {
                String planeFindingMode = properties.getString("planeFindingMode");
                for (PlaneFindingMode pfm : planeFindingModes) {
                    if (planeFindingMode.equalsIgnoreCase(pfm.name())) {
                        config.setPlaneFindingMode(pfm);
                        break;
                    }
                }
            } catch (JSONException e2) {
                DeviceLog.warning("planeFindingMode is not a string.");
            }
        }
        if (properties.has("updateMode")) {
            try {
                String updateMode = properties.getString("updateMode");
                UpdateMode[] updateModeArr = updateModes;
                int length = updateModeArr.length;
                while (i < length) {
                    UpdateMode um = updateModeArr[i];
                    if (updateMode.equalsIgnoreCase(um.name())) {
                        config.setUpdateMode(um);
                        break;
                    }
                    i++;
                }
            } catch (JSONException e3) {
                DeviceLog.warning("updateMode is not a string.");
            }
        }
        return config;
    }

    @TargetApi(19)
    public static JSONObject getConfigEnums() {
        int i = 0;
        JSONObject enums = new JSONObject();
        try {
            ArrayList<String> values = new ArrayList();
            for (LightEstimationMode lem : LightEstimationMode.values()) {
                values.add(lem.name());
            }
            enums.put("lightEstimationMode", new JSONArray(values.toArray()));
            values.clear();
            for (PlaneFindingMode pfm : PlaneFindingMode.values()) {
                values.add(pfm.name());
            }
            enums.put("planeFindingMode", new JSONArray(values.toArray()));
            values.clear();
            UpdateMode[] values2 = UpdateMode.values();
            int length = values2.length;
            while (i < length) {
                values.add(values2[i].name());
                i++;
            }
            enums.put("updateMode", new JSONArray(values.toArray()));
        } catch (JSONException e) {
        }
        return enums;
    }
}
