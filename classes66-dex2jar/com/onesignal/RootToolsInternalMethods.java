// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import java.io.File;

class RootToolsInternalMethods
{
    static boolean isRooted() {
        final String[] array = { "/sbin/", "/system/bin/", "/system/xbin/", "/data/local/xbin/", "/data/local/bin/", "/system/sd/xbin/", "/system/bin/failsafe/", "/data/local/" };
        for (int length = array.length, i = 0; i < length; ++i) {
            if (new File(array[i] + "su").exists()) {
                return true;
            }
        }
        return false;
    }
}
