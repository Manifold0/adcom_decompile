// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.places;

import com.facebook.internal.Utility;
import com.facebook.places.model.PlaceSearchRequestParams;
import com.facebook.places.model.PlaceInfoRequestParams;
import com.facebook.places.internal.LocationPackageManager;
import com.facebook.places.internal.LocationPackageRequestParams;
import com.facebook.HttpMethod;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.places.model.CurrentPlaceFeedbackRequestParams;
import java.util.Iterator;
import java.util.List;
import android.location.Location;
import java.util.Set;
import com.facebook.places.internal.BluetoothScanResult;
import org.json.JSONException;
import com.facebook.places.internal.WifiScanResult;
import org.json.JSONArray;
import java.util.Locale;
import org.json.JSONObject;
import android.text.TextUtils;
import com.facebook.FacebookException;
import android.os.Bundle;
import com.facebook.places.internal.LocationPackage;
import com.facebook.places.model.CurrentPlaceRequestParams;
import com.facebook.places.internal.ScannerException;

public class PlaceManager
{
    private static final String CURRENT_PLACE_FEEDBACK = "current_place/feedback";
    private static final String CURRENT_PLACE_RESULTS = "current_place/results";
    private static final String PARAM_ACCESS_POINTS = "access_points";
    private static final String PARAM_ACCURACY = "accuracy";
    private static final String PARAM_ALTITUDE = "altitude";
    private static final String PARAM_BLUETOOTH = "bluetooth";
    private static final String PARAM_CATEGORIES = "categories";
    private static final String PARAM_CENTER = "center";
    private static final String PARAM_COORDINATES = "coordinates";
    private static final String PARAM_CURRENT_CONNECTION = "current_connection";
    private static final String PARAM_DISTANCE = "distance";
    private static final String PARAM_ENABLED = "enabled";
    private static final String PARAM_FIELDS = "fields";
    private static final String PARAM_FREQUENCY = "frequency";
    private static final String PARAM_HEADING = "heading";
    private static final String PARAM_LATITUDE = "latitude";
    private static final String PARAM_LIMIT = "limit";
    private static final String PARAM_LONGITUDE = "longitude";
    private static final String PARAM_MAC_ADDRESS = "mac_address";
    private static final String PARAM_MIN_CONFIDENCE_LEVEL = "min_confidence_level";
    private static final String PARAM_PAYLOAD = "payload";
    private static final String PARAM_PLACE_ID = "place_id";
    private static final String PARAM_Q = "q";
    private static final String PARAM_RSSI = "rssi";
    private static final String PARAM_SCANS = "scans";
    private static final String PARAM_SIGNAL_STRENGTH = "signal_strength";
    private static final String PARAM_SPEED = "speed";
    private static final String PARAM_SSID = "ssid";
    private static final String PARAM_SUMMARY = "summary";
    private static final String PARAM_TRACKING = "tracking";
    private static final String PARAM_TYPE = "type";
    private static final String PARAM_WAS_HERE = "was_here";
    private static final String PARAM_WIFI = "wifi";
    private static final String SEARCH = "search";
    
    private PlaceManager() {
    }
    
    private static Bundle getCurrentPlaceParameters(final CurrentPlaceRequestParams currentPlaceRequestParams, final LocationPackage locationPackage) throws FacebookException {
        if (currentPlaceRequestParams == null) {
            throw new FacebookException("Request and location must be specified.");
        }
        LocationPackage locationPackage2;
        if ((locationPackage2 = locationPackage) == null) {
            locationPackage2 = new LocationPackage();
        }
        if (locationPackage2.location == null) {
            locationPackage2.location = currentPlaceRequestParams.getLocation();
        }
        if (locationPackage2.location == null) {
            throw new FacebookException("A location must be specified");
        }
        Bundle bundle = null;
        JSONObject jsonObject2 = null;
        Label_0435: {
            JSONArray jsonArray;
            try {
                bundle = new Bundle(6);
                bundle.putString("summary", "tracking");
                final int limit = currentPlaceRequestParams.getLimit();
                if (limit > 0) {
                    bundle.putInt("limit", limit);
                }
                final Set<String> fields = currentPlaceRequestParams.getFields();
                if (fields != null && !fields.isEmpty()) {
                    bundle.putString("fields", TextUtils.join((CharSequence)",", (Iterable)fields));
                }
                final Location location = locationPackage2.location;
                final JSONObject jsonObject = new JSONObject();
                jsonObject.put("latitude", location.getLatitude());
                jsonObject.put("longitude", location.getLongitude());
                if (location.hasAccuracy()) {
                    jsonObject.put("accuracy", (double)location.getAccuracy());
                }
                if (location.hasAltitude()) {
                    jsonObject.put("altitude", location.getAltitude());
                }
                if (location.hasBearing()) {
                    jsonObject.put("heading", (double)location.getBearing());
                }
                if (location.hasSpeed()) {
                    jsonObject.put("speed", (double)location.getSpeed());
                }
                bundle.putString("coordinates", jsonObject.toString());
                final CurrentPlaceRequestParams.ConfidenceLevel minConfidenceLevel = currentPlaceRequestParams.getMinConfidenceLevel();
                if (minConfidenceLevel == CurrentPlaceRequestParams.ConfidenceLevel.LOW || minConfidenceLevel == CurrentPlaceRequestParams.ConfidenceLevel.MEDIUM || minConfidenceLevel == CurrentPlaceRequestParams.ConfidenceLevel.HIGH) {
                    bundle.putString("min_confidence_level", minConfidenceLevel.toString().toLowerCase(Locale.US));
                }
                if (locationPackage2 == null) {
                    return bundle;
                }
                jsonObject2 = new JSONObject();
                jsonObject2.put("enabled", locationPackage2.isWifiScanningEnabled);
                final WifiScanResult connectedWifi = locationPackage2.connectedWifi;
                if (connectedWifi != null) {
                    jsonObject2.put("current_connection", (Object)getWifiScanJson(connectedWifi));
                }
                final List<WifiScanResult> ambientWifi = locationPackage2.ambientWifi;
                if (ambientWifi == null) {
                    break Label_0435;
                }
                jsonArray = new JSONArray();
                final Iterator<WifiScanResult> iterator = ambientWifi.iterator();
                while (iterator.hasNext()) {
                    jsonArray.put((Object)getWifiScanJson(iterator.next()));
                }
            }
            catch (JSONException ex) {
                throw new FacebookException((Throwable)ex);
            }
            jsonObject2.put("access_points", (Object)jsonArray);
        }
        bundle.putString("wifi", jsonObject2.toString());
        final JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("enabled", locationPackage2.isBluetoothScanningEnabled);
        final List<BluetoothScanResult> ambientBluetoothLe = locationPackage2.ambientBluetoothLe;
        if (ambientBluetoothLe != null) {
            final JSONArray jsonArray2 = new JSONArray();
            for (final BluetoothScanResult bluetoothScanResult : ambientBluetoothLe) {
                final JSONObject jsonObject4 = new JSONObject();
                jsonObject4.put("payload", (Object)bluetoothScanResult.payload);
                jsonObject4.put("rssi", bluetoothScanResult.rssi);
                jsonArray2.put((Object)jsonObject4);
            }
            jsonObject3.put("scans", (Object)jsonArray2);
        }
        bundle.putString("bluetooth", jsonObject3.toString());
        return bundle;
    }
    
    private static LocationError getLocationError(final ScannerException.Type type) {
        if (type == ScannerException.Type.PERMISSION_DENIED) {
            return LocationError.LOCATION_PERMISSION_DENIED;
        }
        if (type == ScannerException.Type.DISABLED) {
            return LocationError.LOCATION_SERVICES_DISABLED;
        }
        if (type == ScannerException.Type.TIMEOUT) {
            return LocationError.LOCATION_TIMEOUT;
        }
        return LocationError.UNKNOWN_ERROR;
    }
    
    private static JSONObject getWifiScanJson(final WifiScanResult wifiScanResult) throws JSONException {
        final JSONObject jsonObject = new JSONObject();
        jsonObject.put("mac_address", (Object)wifiScanResult.bssid);
        jsonObject.put("ssid", (Object)wifiScanResult.ssid);
        jsonObject.put("signal_strength", wifiScanResult.rssi);
        jsonObject.put("frequency", wifiScanResult.frequency);
        return jsonObject;
    }
    
    public static GraphRequest newCurrentPlaceFeedbackRequest(final CurrentPlaceFeedbackRequestParams currentPlaceFeedbackRequestParams) {
        final String placeId = currentPlaceFeedbackRequestParams.getPlaceId();
        final String tracking = currentPlaceFeedbackRequestParams.getTracking();
        final Boolean wasHere = currentPlaceFeedbackRequestParams.wasHere();
        if (tracking == null || placeId == null || wasHere == null) {
            throw new FacebookException("tracking, placeId and wasHere must be specified.");
        }
        final Bundle bundle = new Bundle(3);
        bundle.putString("tracking", tracking);
        bundle.putString("place_id", placeId);
        bundle.putBoolean("was_here", (boolean)wasHere);
        return new GraphRequest(AccessToken.getCurrentAccessToken(), "current_place/feedback", bundle, HttpMethod.POST);
    }
    
    public static void newCurrentPlaceRequest(final CurrentPlaceRequestParams currentPlaceRequestParams, final OnRequestReadyCallback onRequestReadyCallback) {
        final Location location = currentPlaceRequestParams.getLocation();
        final CurrentPlaceRequestParams.ScanMode scanMode = currentPlaceRequestParams.getScanMode();
        final LocationPackageRequestParams.Builder builder = new LocationPackageRequestParams.Builder();
        builder.setLocationScanEnabled(location == null);
        if (scanMode != null && scanMode == CurrentPlaceRequestParams.ScanMode.LOW_LATENCY) {
            builder.setWifiActiveScanAllowed(false);
        }
        LocationPackageManager.requestLocationPackage(builder.build(), (LocationPackageManager.Listener)new LocationPackageManager.Listener() {
            @Override
            public void onLocationPackage(final LocationPackage locationPackage) {
                if (locationPackage.locationError != null) {
                    onRequestReadyCallback.onLocationError(getLocationError(locationPackage.locationError));
                    return;
                }
                onRequestReadyCallback.onRequestReady(new GraphRequest(AccessToken.getCurrentAccessToken(), "current_place/results", getCurrentPlaceParameters(currentPlaceRequestParams, locationPackage), HttpMethod.GET));
            }
        });
    }
    
    public static GraphRequest newPlaceInfoRequest(final PlaceInfoRequestParams placeInfoRequestParams) {
        final String placeId = placeInfoRequestParams.getPlaceId();
        if (placeId == null) {
            throw new FacebookException("placeId must be specified.");
        }
        final Bundle bundle = new Bundle(1);
        final Set<String> fields = placeInfoRequestParams.getFields();
        if (fields != null && !fields.isEmpty()) {
            bundle.putString("fields", TextUtils.join((CharSequence)",", (Iterable)fields));
        }
        return new GraphRequest(AccessToken.getCurrentAccessToken(), placeId, bundle, HttpMethod.GET);
    }
    
    public static void newPlaceSearchRequest(final PlaceSearchRequestParams placeSearchRequestParams, final OnRequestReadyCallback onRequestReadyCallback) {
        final LocationPackageRequestParams.Builder builder = new LocationPackageRequestParams.Builder();
        builder.setWifiScanEnabled(false);
        builder.setBluetoothScanEnabled(false);
        LocationPackageManager.requestLocationPackage(builder.build(), (LocationPackageManager.Listener)new LocationPackageManager.Listener() {
            @Override
            public void onLocationPackage(final LocationPackage locationPackage) {
                if (locationPackage.locationError == null) {
                    onRequestReadyCallback.onRequestReady(PlaceManager.newPlaceSearchRequestForLocation(placeSearchRequestParams, locationPackage.location));
                    return;
                }
                onRequestReadyCallback.onLocationError(getLocationError(locationPackage.locationError));
            }
        });
    }
    
    public static GraphRequest newPlaceSearchRequestForLocation(final PlaceSearchRequestParams placeSearchRequestParams, final Location location) {
        final String searchText = placeSearchRequestParams.getSearchText();
        if (location == null && searchText == null) {
            throw new FacebookException("Either location or searchText must be specified.");
        }
        final int limit = placeSearchRequestParams.getLimit();
        final Set<String> fields = placeSearchRequestParams.getFields();
        final Set<String> categories = placeSearchRequestParams.getCategories();
        final Bundle bundle = new Bundle(7);
        bundle.putString("type", "place");
        if (location != null) {
            bundle.putString("center", String.format(Locale.US, "%f,%f", location.getLatitude(), location.getLongitude()));
            final int distance = placeSearchRequestParams.getDistance();
            if (distance > 0) {
                bundle.putInt("distance", distance);
            }
        }
        if (limit > 0) {
            bundle.putInt("limit", limit);
        }
        if (!Utility.isNullOrEmpty(searchText)) {
            bundle.putString("q", searchText);
        }
        if (categories != null && !categories.isEmpty()) {
            final JSONArray jsonArray = new JSONArray();
            final Iterator<String> iterator = categories.iterator();
            while (iterator.hasNext()) {
                jsonArray.put((Object)iterator.next());
            }
            bundle.putString("categories", jsonArray.toString());
        }
        if (fields != null && !fields.isEmpty()) {
            bundle.putString("fields", TextUtils.join((CharSequence)",", (Iterable)fields));
        }
        return new GraphRequest(AccessToken.getCurrentAccessToken(), "search", bundle, HttpMethod.GET);
    }
    
    public enum LocationError
    {
        LOCATION_PERMISSION_DENIED, 
        LOCATION_SERVICES_DISABLED, 
        LOCATION_TIMEOUT, 
        UNKNOWN_ERROR;
    }
    
    public interface OnRequestReadyCallback
    {
        void onLocationError(final LocationError p0);
        
        void onRequestReady(final GraphRequest p0);
    }
}
