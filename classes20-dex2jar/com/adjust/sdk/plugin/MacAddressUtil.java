// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk.plugin;

import android.text.TextUtils;
import java.io.IOException;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import android.net.wifi.WifiManager;
import java.util.Locale;
import android.content.Context;

public class MacAddressUtil
{
    public static String getMacAddress(final Context context) {
        final String rawMacAddress = getRawMacAddress(context);
        if (rawMacAddress == null) {
            return null;
        }
        return removeSpaceString(rawMacAddress.toUpperCase(Locale.US));
    }
    
    private static String getRawMacAddress(final Context context) {
        final String loadAddress = loadAddress("wlan0");
        if (loadAddress != null) {
            return loadAddress;
        }
        final String loadAddress2 = loadAddress("eth0");
        if (loadAddress2 != null) {
            return loadAddress2;
        }
        try {
            final String macAddress = ((WifiManager)context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (macAddress != null) {
                return macAddress;
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private static String loadAddress(String string) {
        try {
            final String string2 = "/sys/class/net/" + string + "/address";
            final StringBuilder sb = new StringBuilder(1000);
            final BufferedReader bufferedReader = new BufferedReader(new FileReader(string2), 1024);
            final char[] array = new char[1024];
            while (true) {
                final int read = bufferedReader.read(array);
                if (read == -1) {
                    break;
                }
                sb.append(String.valueOf(array, 0, read));
            }
            bufferedReader.close();
            string = sb.toString();
            return string;
        }
        catch (IOException ex) {
            return null;
        }
    }
    
    private static String removeSpaceString(String replaceAll) {
        if (replaceAll == null) {
            replaceAll = null;
        }
        else if (TextUtils.isEmpty((CharSequence)(replaceAll = replaceAll.replaceAll("\\s", "")))) {
            return null;
        }
        return replaceAll;
    }
}
