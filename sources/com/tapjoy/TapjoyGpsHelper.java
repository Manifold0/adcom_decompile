package com.tapjoy;

import android.content.Context;

public class TapjoyGpsHelper {
    /* renamed from: a */
    private Context f7090a;
    /* renamed from: b */
    private String f7091b;
    /* renamed from: c */
    private boolean f7092c;
    /* renamed from: d */
    private int f7093d = 0;
    /* renamed from: e */
    private int f7094e = 0;
    /* renamed from: f */
    private boolean f7095f;
    /* renamed from: g */
    private Boolean f7096g;
    /* renamed from: h */
    private Boolean f7097h;

    public TapjoyGpsHelper(Context context) {
        this.f7090a = context;
    }

    public void loadAdvertisingId() {
        TapjoyLog.m7129i("TapjoyGpsHelper", "Looking for Google Play Services...");
        if (isGooglePlayServicesAvailable() && isGooglePlayManifestConfigured()) {
            TapjoyLog.m7129i("TapjoyGpsHelper", "Packaged Google Play Services found, fetching advertisingID...");
            TapjoyLog.m7129i("TapjoyGpsHelper", "Packaged Google Play Services version: " + this.f7094e);
            TapjoyAdIdClient tapjoyAdIdClient = new TapjoyAdIdClient(this.f7090a);
            this.f7095f = tapjoyAdIdClient.setupAdIdInfo();
            try {
                this.f7093d = this.f7090a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
                TapjoyLog.m7129i("TapjoyGpsHelper", "Device's Google Play Services version: " + this.f7093d);
            } catch (Exception e) {
                TapjoyLog.m7129i("TapjoyGpsHelper", "Error getting device's Google Play Services version");
            }
            if (this.f7095f) {
                this.f7092c = tapjoyAdIdClient.isAdTrackingEnabled();
                this.f7091b = tapjoyAdIdClient.getAdvertisingId();
                TapjoyLog.m7129i("TapjoyGpsHelper", "Found advertising ID: " + this.f7091b);
                TapjoyLog.m7129i("TapjoyGpsHelper", "Is ad tracking enabled: " + Boolean.toString(this.f7092c));
                return;
            }
            TapjoyLog.m7129i("TapjoyGpsHelper", "Error getting advertisingID from Google Play Services");
            return;
        }
        TapjoyLog.m7129i("TapjoyGpsHelper", "Google Play Services not found");
    }

    public void checkGooglePlayIntegration() {
        if (!isGooglePlayServicesAvailable()) {
            throw new TapjoyIntegrationException("Tapjoy SDK is disabled because Google Play Services was not found. For more information about including the Google Play services client library visit http://developer.android.com/google/play-services/setup.html or http://tech.tapjoy.com/product-overview/sdk-change-log/tapjoy-and-identifiers");
        } else if (!isGooglePlayManifestConfigured()) {
            throw new TapjoyIntegrationException("Failed to load manifest.xml meta-data, 'com.google.android.gms.version' not found. For more information about including the Google Play services client library visit http://developer.android.com/google/play-services/setup.html or http://tech.tapjoy.com/product-overview/sdk-change-log/tapjoy-and-identifiers");
        }
    }

    public boolean isGooglePlayServicesAvailable() {
        if (this.f7096g == null) {
            try {
                this.f7090a.getClassLoader().loadClass("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                this.f7096g = Boolean.valueOf(true);
            } catch (Exception e) {
                this.f7096g = Boolean.valueOf(false);
            } catch (Error e2) {
                this.f7096g = Boolean.valueOf(false);
            }
        }
        return this.f7096g.booleanValue();
    }

    public boolean isGooglePlayManifestConfigured() {
        if (this.f7097h == null) {
            try {
                this.f7094e = this.f7090a.getPackageManager().getApplicationInfo(this.f7090a.getPackageName(), 128).metaData.getInt("com.google.android.gms.version");
                this.f7097h = Boolean.valueOf(true);
            } catch (Exception e) {
                this.f7097h = Boolean.valueOf(false);
            }
        }
        return this.f7097h.booleanValue();
    }

    public String getAdvertisingId() {
        return this.f7091b;
    }

    public boolean isAdTrackingEnabled() {
        return this.f7092c;
    }

    public boolean isAdIdAvailable() {
        return this.f7095f;
    }

    public int getDeviceGooglePlayServicesVersion() {
        return this.f7093d;
    }

    public int getPackagedGooglePlayServicesVersion() {
        return this.f7094e;
    }
}
