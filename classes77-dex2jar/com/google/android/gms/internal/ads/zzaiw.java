// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Iterator;
import com.google.android.gms.ads.internal.zzbv;
import java.util.ArrayList;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.HashMap;
import android.content.Context;
import java.util.List;
import java.util.Map;
import android.annotation.TargetApi;
import javax.annotation.ParametersAreNonnullByDefault;

@zzadh
@ParametersAreNonnullByDefault
@TargetApi(21)
final class zzaiw
{
    private static final Map<String, String> zzcnl;
    private final List<String> zzcnm;
    private final zzaii zzcnn;
    private final Context zzrt;
    
    static {
        final HashMap<String, String> zzcnl2 = new HashMap<String, String>();
        if (PlatformVersion.isAtLeastLollipop()) {
            zzcnl2.put("android.webkit.resource.AUDIO_CAPTURE", "android.permission.RECORD_AUDIO");
            zzcnl2.put("android.webkit.resource.VIDEO_CAPTURE", "android.permission.CAMERA");
        }
        zzcnl = zzcnl2;
    }
    
    zzaiw(final Context zzrt, final List<String> zzcnm, final zzaii zzcnn) {
        this.zzrt = zzrt;
        this.zzcnm = zzcnm;
        this.zzcnn = zzcnn;
    }
    
    final List<String> zzc(final String[] array) {
        final ArrayList<String> list = new ArrayList<String>();
        final int length = array.length;
        int i = 0;
    Label_0071_Outer:
        while (i < length) {
            final String s = array[i];
            while (true) {
                for (final String s2 : this.zzcnm) {
                    int n;
                    if (s2.equals(s)) {
                        n = 1;
                    }
                    else {
                        final String value = String.valueOf("android.webkit.resource.");
                        final String value2 = String.valueOf(s2);
                        String concat;
                        if (value2.length() != 0) {
                            concat = value.concat(value2);
                        }
                        else {
                            concat = new String(value);
                        }
                        if (!concat.equals(s)) {
                            continue Label_0071_Outer;
                        }
                        n = 1;
                    }
                    if (n != 0) {
                        int n2 = 0;
                        Label_0117: {
                            if (zzaiw.zzcnl.containsKey(s)) {
                                zzbv.zzek();
                                if (!zzakk.zzl(this.zzrt, zzaiw.zzcnl.get(s))) {
                                    n2 = 0;
                                    break Label_0117;
                                }
                            }
                            n2 = 1;
                        }
                        if (n2 != 0) {
                            list.add(s);
                        }
                        else {
                            this.zzcnn.zzch(s);
                        }
                    }
                    else {
                        this.zzcnn.zzcg(s);
                    }
                    ++i;
                    continue Label_0071_Outer;
                }
                int n = 0;
                continue;
            }
        }
        return list;
    }
}
