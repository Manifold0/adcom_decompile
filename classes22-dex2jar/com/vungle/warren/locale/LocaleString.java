// 
// Decompiled by Procyon v0.5.34
// 

package com.vungle.warren.locale;

import java.util.Locale;

public class LocaleString
{
    public static final int ID_DOWNLOADING = 4;
    public static final int ID_DOWNLOAD_COMPLETE = 5;
    public static final int ID_PROGRESS = 3;
    public static final int ID_START_DOWNLOAD = 1;
    public static final int ID_WAITING_FOR_WIFI = 2;
    private static final String TEXT_DOWNLOADING_CN = "\u4e0b\u8f7d\u4e2d\u2026";
    private static final String TEXT_DOWNLOADING_DEFAULT = "Downloading\u2026";
    private static final String TEXT_DOWNLOAD_COMPLETE_CN = "\u4e0b\u8f7d\u5b8c\u6210";
    private static final String TEXT_DOWNLOAD_COMPLETE_DEFAULT = "Download complete.";
    private static final String TEXT_IN_PROGRESS_CN = "\u6b63\u5728\u4e0b\u8f7d\u2026";
    private static final String TEXT_IN_PROGRESS_DEFAULT = "Download in progress\u2026";
    private static final String TEXT_START_DOWNLOAD_CN = "\u5f00\u59cb\u4e0b\u8f7d\u2026";
    private static final String TEXT_START_DOWNLOAD_DEFAULT = "Download started\u2026";
    private static final String TEXT_WAITING_FOR_WIFI_CN = "\u7b49\u5f85Wi-Fi\u2026";
    private static final String TEXT_WAITING_FOR_WIFI_DEFAULT = "Waiting for Wi-Fi\u2026";
    
    public static String getLocaleText(final int n) {
        final boolean equals = Locale.getDefault().getLanguage().equals(new Locale("zh").getLanguage());
        switch (n) {
            default: {
                return "";
            }
            case 1: {
                if (equals) {
                    return "\u5f00\u59cb\u4e0b\u8f7d\u2026";
                }
                return "Download started\u2026";
            }
            case 2: {
                if (equals) {
                    return "\u7b49\u5f85Wi-Fi\u2026";
                }
                return "Waiting for Wi-Fi\u2026";
            }
            case 3: {
                if (equals) {
                    return "\u6b63\u5728\u4e0b\u8f7d\u2026";
                }
                return "Download in progress\u2026";
            }
            case 4: {
                if (equals) {
                    return "\u4e0b\u8f7d\u4e2d\u2026";
                }
                return "Downloading\u2026";
            }
            case 5: {
                if (equals) {
                    return "\u4e0b\u8f7d\u5b8c\u6210";
                }
                return "Download complete.";
            }
        }
    }
}
