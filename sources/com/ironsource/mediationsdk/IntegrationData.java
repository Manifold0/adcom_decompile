package com.ironsource.mediationsdk;

import android.util.Pair;
import java.util.ArrayList;

public class IntegrationData {
    public String[] activities = null;
    public ArrayList<Pair<String, String>> externalLibs = null;
    public String name;
    public String sdkVersion;
    public String[] services = null;
    public boolean validateWriteExternalStorage = false;
    public String version;

    public IntegrationData(String name, String version) {
        this.name = name;
        this.version = version;
    }
}
