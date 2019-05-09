// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.mediation.customevent.CustomEventExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEventAdapter;
import com.google.ads.mediation.AdUrlAdapter;
import com.google.ads.mediation.admob.AdMobAdapter;
import android.os.RemoteException;
import com.google.ads.mediation.MediationAdapter;
import com.google.ads.mediation.MediationServerParameters;
import com.google.android.gms.ads.mediation.NetworkExtras;
import java.util.Map;

@zzadh
public final class zzxm extends zzxo
{
    private static final zzzo zzbup;
    private Map<Class<? extends NetworkExtras>, NetworkExtras> zzbuo;
    
    static {
        zzbup = new zzzo();
    }
    
    private final <NetworkExtrasT extends com.google.ads.mediation.NetworkExtras, ServerParametersT extends MediationServerParameters> zzxq zzbo(final String s) throws RemoteException {
        try {
            final Class<?> forName = Class.forName(s, false, zzxm.class.getClassLoader());
            if (MediationAdapter.class.isAssignableFrom(forName)) {
                final MediationAdapter mediationAdapter = (MediationAdapter)forName.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
                return new zzyp<Object, Object>(mediationAdapter, (com.google.ads.mediation.NetworkExtras)this.zzbuo.get(mediationAdapter.getAdditionalParametersType()));
            }
            if (com.google.android.gms.ads.mediation.MediationAdapter.class.isAssignableFrom(forName)) {
                return new zzyk((com.google.android.gms.ads.mediation.MediationAdapter)forName.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]));
            }
            zzane.zzdk(new StringBuilder(String.valueOf(s).length() + 64).append("Could not instantiate mediation adapter: ").append(s).append(" (not a valid adapter).").toString());
            throw new RemoteException();
        }
        catch (Throwable t) {
            return this.zzbp(s);
        }
    }
    
    private final zzxq zzbp(final String s) throws RemoteException {
        Label_0102: {
            try {
                zzane.zzck("Reflection failed, retrying using direct instantiation");
                if ("com.google.ads.mediation.admob.AdMobAdapter".equals(s)) {
                    return new zzyk(new AdMobAdapter());
                }
                if ("com.google.ads.mediation.AdUrlAdapter".equals(s)) {
                    return new zzyk(new AdUrlAdapter());
                }
                break Label_0102;
            }
            catch (Throwable t) {
                zzane.zzc(new StringBuilder(String.valueOf(s).length() + 43).append("Could not instantiate mediation adapter: ").append(s).append(". ").toString(), t);
            }
            throw new RemoteException();
        }
        if ("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
            return new zzyk(new CustomEventAdapter());
        }
        if ("com.google.ads.mediation.customevent.CustomEventAdapter".equals(s)) {
            final com.google.ads.mediation.customevent.CustomEventAdapter customEventAdapter = new com.google.ads.mediation.customevent.CustomEventAdapter();
            return new zzyp<Object, Object>((MediationAdapter<com.google.ads.mediation.NetworkExtras, MediationServerParameters>)customEventAdapter, (com.google.ads.mediation.NetworkExtras)this.zzbuo.get(customEventAdapter.getAdditionalParametersType()));
        }
        throw new RemoteException();
    }
    
    public final zzxq zzbm(final String s) throws RemoteException {
        return this.zzbo(s);
    }
    
    public final boolean zzbn(final String s) throws RemoteException {
        try {
            return CustomEvent.class.isAssignableFrom(Class.forName(s, false, zzxm.class.getClassLoader()));
        }
        catch (Throwable t) {
            zzane.zzdk(new StringBuilder(String.valueOf(s).length() + 80).append("Could not load custom event implementation class: ").append(s).append(", assuming old implementation.").toString());
            return false;
        }
    }
    
    public final zzzj zzbq(final String s) throws RemoteException {
        return zzzo.zzbs(s);
    }
    
    public final void zzj(final Map<Class<? extends NetworkExtras>, NetworkExtras> zzbuo) {
        this.zzbuo = zzbuo;
    }
}
