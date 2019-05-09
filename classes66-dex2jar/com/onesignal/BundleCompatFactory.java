// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.os.Build$VERSION;

class BundleCompatFactory
{
    static BundleCompat getInstance() {
        if (Build$VERSION.SDK_INT >= 26) {
            return new BundleCompatPersistableBundle();
        }
        return new BundleCompatBundle();
    }
}
