package com.adjust.sdk.plugin;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class MacAddressUtil {
    public static String getMacAddress(Context context) {
        String rawAddress = getRawMacAddress(context);
        if (rawAddress == null) {
            return null;
        }
        return removeSpaceString(rawAddress.toUpperCase(Locale.US));
    }

    private static String getRawMacAddress(Context context) {
        String wlanAddress = loadAddress("wlan0");
        if (wlanAddress != null) {
            return wlanAddress;
        }
        String ethAddress = loadAddress("eth0");
        if (ethAddress != null) {
            return ethAddress;
        }
        try {
            String wifiAddress = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress();
            if (wifiAddress != null) {
                return wifiAddress;
            }
        } catch (Exception e) {
        }
        return null;
    }

    private static String loadAddress(String interfaceName) {
        try {
            String filePath = "/sys/class/net/" + interfaceName + "/address";
            StringBuilder fileData = new StringBuilder(1000);
            BufferedReader reader = new BufferedReader(new FileReader(filePath), 1024);
            char[] buf = new char[1024];
            while (true) {
                int numRead = reader.read(buf);
                if (numRead != -1) {
                    fileData.append(String.valueOf(buf, 0, numRead));
                } else {
                    reader.close();
                    return fileData.toString();
                }
            }
        } catch (IOException e) {
            return null;
        }
    }

    private static String removeSpaceString(String inputString) {
        if (inputString == null) {
            return null;
        }
        String outputString = inputString.replaceAll("\\s", "");
        if (TextUtils.isEmpty(outputString)) {
            return null;
        }
        return outputString;
    }
}
