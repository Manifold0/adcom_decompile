// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import java.util.Set;
import java.util.Date;
import java.util.Collection;
import java.util.HashSet;
import com.google.ads.mediation.MediationAdRequest;
import com.google.ads.AdRequest;

@zzadh
public final class zzzc
{
    public static int zza(final AdRequest.ErrorCode errorCode) {
        switch (zzzd.zzbvg[errorCode.ordinal()]) {
            default: {
                return 0;
            }
            case 2: {
                return 1;
            }
            case 3: {
                return 2;
            }
            case 4: {
                return 3;
            }
        }
    }
    
    public static MediationAdRequest zza(final zzjj zzjj, final boolean b) {
        HashSet<String> set;
        if (zzjj.zzapy != null) {
            set = new HashSet<String>(zzjj.zzapy);
        }
        else {
            set = null;
        }
        final Date date = new Date(zzjj.zzapw);
        AdRequest.Gender gender = null;
        switch (zzjj.zzapx) {
            default: {
                gender = AdRequest.Gender.UNKNOWN;
                break;
            }
            case 2: {
                gender = AdRequest.Gender.FEMALE;
                break;
            }
            case 1: {
                gender = AdRequest.Gender.MALE;
                break;
            }
        }
        return new MediationAdRequest(date, gender, set, b, zzjj.zzaqe);
    }
}
