// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.media.MediaCodecInfo$CodecCapabilities;
import android.media.MediaCodecInfo$CodecProfileLevel;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Arrays;
import android.media.MediaCodecList;
import android.os.Build$VERSION;
import android.annotation.TargetApi;
import android.util.Range;
import java.util.HashMap;
import android.media.MediaCodecInfo;
import java.util.List;
import java.util.Map;

@zzadh
public final class zzams
{
    private static Map<String, List<Map<String, Object>>> zzcug;
    private static List<MediaCodecInfo> zzcuh;
    private static final Object zzcui;
    
    static {
        zzams.zzcug = new HashMap<String, List<Map<String, Object>>>();
        zzcui = new Object();
    }
    
    @TargetApi(21)
    private static Integer[] zza(final Range<Integer> range) {
        return new Integer[] { (Integer)range.getLower(), (Integer)range.getUpper() };
    }
    
    @TargetApi(16)
    public static List<Map<String, Object>> zzdd(final String s) {
        // monitorenter(zzams.zzcui)
        try {
            if (zzams.zzcug.containsKey(s)) {
                return zzams.zzcug.get(s);
            }
            try {
                synchronized (zzams.zzcui) {
                    if (zzams.zzcuh == null) {
                        if (Build$VERSION.SDK_INT < 21) {
                            goto Label_0322;
                        }
                        zzams.zzcuh = Arrays.asList(new MediaCodecList(0).getCodecInfos());
                    }
                    // monitorexit(zzams.zzcui)
                    final ArrayList list = new ArrayList();
                    Block_10: {
                        for (final Object capabilitiesForType : zzams.zzcuh) {
                            if (!((MediaCodecInfo)capabilitiesForType).isEncoder() && Arrays.asList(((MediaCodecInfo)capabilitiesForType).getSupportedTypes()).contains(s)) {
                                break Block_10;
                            }
                        }
                        goto Label_0556;
                    }
                    Object capabilitiesForType = null;
                    new HashMap<String, String>().put("codecName", ((MediaCodecInfo)capabilitiesForType).getName());
                    capabilitiesForType = ((MediaCodecInfo)capabilitiesForType).getCapabilitiesForType(s);
                    final ArrayList<Integer[]> list2 = new ArrayList<Integer[]>();
                    final MediaCodecInfo$CodecProfileLevel[] profileLevels = ((MediaCodecInfo$CodecCapabilities)capabilitiesForType).profileLevels;
                    for (int length = profileLevels.length, i = 0; i < length; ++i) {
                        final MediaCodecInfo$CodecProfileLevel mediaCodecInfo$CodecProfileLevel = profileLevels[i];
                        list2.add(new Integer[] { mediaCodecInfo$CodecProfileLevel.profile, mediaCodecInfo$CodecProfileLevel.level });
                    }
                }
            }
            catch (RuntimeException ex) {}
            catch (LinkageError list) {}
        }
        finally {}
    }
}
