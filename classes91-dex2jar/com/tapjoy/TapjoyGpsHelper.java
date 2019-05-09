// 
// Decompiled by Procyon v0.5.34
// 

package com.tapjoy;

import android.content.Context;

public class TapjoyGpsHelper
{
    private Context a;
    private String b;
    private boolean c;
    private int d;
    private int e;
    private boolean f;
    private Boolean g;
    private Boolean h;
    
    public TapjoyGpsHelper(final Context a) {
        this.d = 0;
        this.e = 0;
        this.a = a;
    }
    
    public void checkGooglePlayIntegration() {
        if (!this.isGooglePlayServicesAvailable()) {
            throw new TapjoyIntegrationException("Tapjoy SDK is disabled because Google Play Services was not found. For more information about including the Google Play services client library visit http://developer.android.com/google/play-services/setup.html or http://tech.tapjoy.com/product-overview/sdk-change-log/tapjoy-and-identifiers");
        }
        if (!this.isGooglePlayManifestConfigured()) {
            throw new TapjoyIntegrationException("Failed to load manifest.xml meta-data, 'com.google.android.gms.version' not found. For more information about including the Google Play services client library visit http://developer.android.com/google/play-services/setup.html or http://tech.tapjoy.com/product-overview/sdk-change-log/tapjoy-and-identifiers");
        }
    }
    
    public String getAdvertisingId() {
        return this.b;
    }
    
    public int getDeviceGooglePlayServicesVersion() {
        return this.d;
    }
    
    public int getPackagedGooglePlayServicesVersion() {
        return this.e;
    }
    
    public boolean isAdIdAvailable() {
        return this.f;
    }
    
    public boolean isAdTrackingEnabled() {
        return this.c;
    }
    
    public boolean isGooglePlayManifestConfigured() {
        Label_0047: {
            if (this.h != null) {
                break Label_0047;
            }
            try {
                this.e = this.a.getPackageManager().getApplicationInfo(this.a.getPackageName(), 128).metaData.getInt("com.google.android.gms.version");
                this.h = true;
                return this.h;
            }
            catch (Exception ex) {
                this.h = false;
                return this.h;
            }
        }
    }
    
    public boolean isGooglePlayServicesAvailable() {
        Label_0028: {
            if (this.g != null) {
                break Label_0028;
            }
            try {
                this.a.getClassLoader().loadClass("com.google.android.gms.ads.identifier.AdvertisingIdClient");
                this.g = true;
                return this.g;
            }
            catch (Exception ex) {
                this.g = false;
                return this.g;
            }
            catch (Error error) {
                this.g = false;
                return this.g;
            }
        }
    }
    
    public void loadAdvertisingId() {
        TapjoyLog.i("TapjoyGpsHelper", "Looking for Google Play Services...");
        if (this.isGooglePlayServicesAvailable() && this.isGooglePlayManifestConfigured()) {
            TapjoyLog.i("TapjoyGpsHelper", "Packaged Google Play Services found, fetching advertisingID...");
            TapjoyLog.i("TapjoyGpsHelper", "Packaged Google Play Services version: " + this.e);
            final TapjoyAdIdClient tapjoyAdIdClient = new TapjoyAdIdClient(this.a);
            this.f = tapjoyAdIdClient.setupAdIdInfo();
            while (true) {
                try {
                    this.d = this.a.getPackageManager().getPackageInfo("com.google.android.gms", 0).versionCode;
                    TapjoyLog.i("TapjoyGpsHelper", "Device's Google Play Services version: " + this.d);
                    if (this.f) {
                        this.c = tapjoyAdIdClient.isAdTrackingEnabled();
                        this.b = tapjoyAdIdClient.getAdvertisingId();
                        TapjoyLog.i("TapjoyGpsHelper", "Found advertising ID: " + this.b);
                        TapjoyLog.i("TapjoyGpsHelper", "Is ad tracking enabled: " + Boolean.toString(this.c));
                        return;
                    }
                }
                catch (Exception ex) {
                    TapjoyLog.i("TapjoyGpsHelper", "Error getting device's Google Play Services version");
                    continue;
                }
                break;
            }
            TapjoyLog.i("TapjoyGpsHelper", "Error getting advertisingID from Google Play Services");
            return;
        }
        TapjoyLog.i("TapjoyGpsHelper", "Google Play Services not found");
    }
}
