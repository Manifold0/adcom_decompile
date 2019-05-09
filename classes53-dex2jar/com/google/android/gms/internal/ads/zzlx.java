// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import android.location.Location;
import java.util.Date;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.HashMap;
import java.util.HashSet;
import android.os.Bundle;

public final class zzlx
{
    private int zzaqn;
    private int zzaqq;
    private String zzaqr;
    private String zzaqt;
    private final Bundle zzaqv;
    private String zzaqx;
    private boolean zzaqz;
    private final Bundle zzask;
    private final HashSet<String> zzasp;
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzasq;
    private final HashSet<String> zzasr;
    private final HashSet<String> zzass;
    private Date zzhl;
    private Location zzhp;
    private boolean zzvm;
    
    public zzlx() {
        this.zzasp = new HashSet<String>();
        this.zzask = new Bundle();
        this.zzasq = new HashMap<Class<? extends NetworkExtras>, NetworkExtras>();
        this.zzasr = new HashSet<String>();
        this.zzaqv = new Bundle();
        this.zzass = new HashSet<String>();
        this.zzaqn = -1;
        this.zzvm = false;
        this.zzaqq = -1;
    }
    
    public final void setManualImpressionsEnabled(final boolean zzvm) {
        this.zzvm = zzvm;
    }
    
    @Deprecated
    public final void zza(final NetworkExtras networkExtras) {
        if (networkExtras instanceof AdMobExtras) {
            this.zza(AdMobAdapter.class, ((AdMobExtras)networkExtras).getExtras());
            return;
        }
        this.zzasq.put(networkExtras.getClass(), networkExtras);
    }
    
    public final void zza(final Class<? extends MediationAdapter> clazz, final Bundle bundle) {
        this.zzask.putBundle(clazz.getName(), bundle);
    }
    
    public final void zza(final Date zzhl) {
        this.zzhl = zzhl;
    }
    
    public final void zzac(final String s) {
        this.zzasp.add(s);
    }
    
    public final void zzad(final String s) {
        this.zzasr.add(s);
    }
    
    public final void zzae(final String s) {
        this.zzasr.remove(s);
    }
    
    public final void zzaf(final String zzaqt) {
        this.zzaqt = zzaqt;
    }
    
    public final void zzag(final String zzaqr) {
        this.zzaqr = zzaqr;
    }
    
    public final void zzah(final String zzaqx) {
        this.zzaqx = zzaqx;
    }
    
    public final void zzai(final String s) {
        this.zzass.add(s);
    }
    
    public final void zzb(final Location zzhp) {
        this.zzhp = zzhp;
    }
    
    public final void zzb(final Class<? extends CustomEvent> clazz, final Bundle bundle) {
        if (this.zzask.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
            this.zzask.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
        }
        this.zzask.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(clazz.getName(), bundle);
    }
    
    public final void zzc(final String s, final String s2) {
        this.zzaqv.putString(s, s2);
    }
    
    public final void zzj(final boolean b) {
        int zzaqq;
        if (b) {
            zzaqq = 1;
        }
        else {
            zzaqq = 0;
        }
        this.zzaqq = zzaqq;
    }
    
    public final void zzk(final boolean zzaqz) {
        this.zzaqz = zzaqz;
    }
    
    public final void zzt(final int zzaqn) {
        this.zzaqn = zzaqn;
    }
}
