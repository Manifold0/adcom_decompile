// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk;

import android.util.Pair;
import java.util.ArrayList;

public class IntegrationData
{
    public String[] activities;
    public ArrayList<Pair<String, String>> externalLibs;
    public String name;
    public String sdkVersion;
    public String[] services;
    public boolean validateWriteExternalStorage;
    public String version;
    
    public IntegrationData(final String name, final String version) {
        this.name = name;
        this.version = version;
        this.activities = null;
        this.externalLibs = null;
        this.services = null;
        this.validateWriteExternalStorage = false;
    }
}
