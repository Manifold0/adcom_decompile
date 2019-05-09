// 
// Decompiled by Procyon v0.5.34
// 

package com.google.ads.mediation;

import java.util.Calendar;
import android.location.Location;
import java.util.Set;
import com.google.ads.AdRequest;
import java.util.Date;

@Deprecated
public class MediationAdRequest
{
    private final Date zzhl;
    private final AdRequest.Gender zzhm;
    private final Set<String> zzhn;
    private final boolean zzho;
    private final Location zzhp;
    
    public MediationAdRequest(final Date zzhl, final AdRequest.Gender zzhm, final Set<String> zzhn, final boolean zzho, final Location zzhp) {
        this.zzhl = zzhl;
        this.zzhm = zzhm;
        this.zzhn = zzhn;
        this.zzho = zzho;
        this.zzhp = zzhp;
    }
    
    public Integer getAgeInYears() {
        if (this.zzhl != null) {
            final Calendar instance = Calendar.getInstance();
            final Calendar instance2 = Calendar.getInstance();
            instance.setTime(this.zzhl);
            final Integer value = instance2.get(1) - instance.get(1);
            if (instance2.get(2) >= instance.get(2)) {
                Integer value2 = value;
                if (instance2.get(2) != instance.get(2)) {
                    return value2;
                }
                value2 = value;
                if (instance2.get(5) >= instance.get(5)) {
                    return value2;
                }
            }
            return value - 1;
        }
        return null;
    }
    
    public Date getBirthday() {
        return this.zzhl;
    }
    
    public AdRequest.Gender getGender() {
        return this.zzhm;
    }
    
    public Set<String> getKeywords() {
        return this.zzhn;
    }
    
    public Location getLocation() {
        return this.zzhp;
    }
    
    public boolean isTesting() {
        return this.zzho;
    }
}
