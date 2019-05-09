// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

public enum zzbes
{
    zzeaa(zzbex.zzeay, 1), 
    zzeab(zzbex.zzeax, 5), 
    zzeac(zzbex.zzeaw, 0), 
    zzead(zzbex.zzeaw, 0), 
    zzeae(zzbex.zzeav, 0), 
    zzeaf(zzbex.zzeaw, 1), 
    zzeag(zzbex.zzeav, 5), 
    zzeah(zzbex.zzeaz, 0), 
    zzeai("STRING", 8, zzbex.zzeba, 2) {
        zzbet(final String s, final int n, final zzbex zzbex, final int n2) {
        }
    }, 
    zzeaj("GROUP", 9, zzbex.zzebd, 3) {
        zzbeu(final String s, final int n, final zzbex zzbex, final int n2) {
        }
    }, 
    zzeak("MESSAGE", 10, zzbex.zzebd, 2) {
        zzbev(final String s, final int n, final zzbex zzbex, final int n2) {
        }
    }, 
    zzeal("BYTES", 11, zzbex.zzebb, 2) {
        zzbew(final String s, final int n, final zzbex zzbex, final int n2) {
        }
    }, 
    zzeam(zzbex.zzeav, 0), 
    zzean(zzbex.zzebc, 0), 
    zzeao(zzbex.zzeav, 5), 
    zzeap(zzbex.zzeaw, 1), 
    zzeaq(zzbex.zzeav, 0), 
    zzear(zzbex.zzeaw, 0);
    
    private final zzbex zzeas;
    private final int zzeat;
    
    private zzbes(final zzbex zzeas, final int zzeat) {
        this.zzeas = zzeas;
        this.zzeat = zzeat;
    }
    
    public final zzbex zzagl() {
        return this.zzeas;
    }
    
    public final int zzagm() {
        return this.zzeat;
    }
}
