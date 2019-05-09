// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.gcm;

import android.support.annotation.RequiresPermission;
import java.io.IOException;
import android.os.Bundle;
import android.content.Context;
import com.google.android.gms.iid.InstanceID;
import java.util.regex.Pattern;

public class GcmPubSub
{
    private static GcmPubSub zzhzd;
    private static final Pattern zzhzf;
    private InstanceID zzhze;
    
    static {
        zzhzf = Pattern.compile("/topics/[a-zA-Z0-9-_.~%]{1,900}");
    }
    
    private GcmPubSub(final Context context) {
        this.zzhze = InstanceID.getInstance(context);
    }
    
    public static GcmPubSub getInstance(final Context context) {
        synchronized (GcmPubSub.class) {
            if (GcmPubSub.zzhzd == null) {
                GcmPubSub.zzhzd = new GcmPubSub(context);
            }
            return GcmPubSub.zzhzd;
        }
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void subscribe(String s, final String s2, final Bundle bundle) throws IOException {
        if (s == null || s.isEmpty()) {
            s = String.valueOf(s);
            if (s.length() != 0) {
                s = "Invalid appInstanceToken: ".concat(s);
            }
            else {
                s = new String("Invalid appInstanceToken: ");
            }
            throw new IllegalArgumentException(s);
        }
        if (s2 == null || !GcmPubSub.zzhzf.matcher(s2).matches()) {
            s = String.valueOf(s2);
            if (s.length() != 0) {
                s = "Invalid topic name: ".concat(s);
            }
            else {
                s = new String("Invalid topic name: ");
            }
            throw new IllegalArgumentException(s);
        }
        Bundle bundle2;
        if ((bundle2 = bundle) == null) {
            bundle2 = new Bundle();
        }
        bundle2.putString("gcm.topic", s2);
        this.zzhze.getToken(s, s2, bundle2);
    }
    
    @RequiresPermission("com.google.android.c2dm.permission.RECEIVE")
    public void unsubscribe(final String s, final String s2) throws IOException {
        final Bundle bundle = new Bundle();
        bundle.putString("gcm.topic", s2);
        this.zzhze.zza(s, s2, bundle);
    }
}
