// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.internal.ads;

import android.view.MotionEvent;
import java.lang.ref.WeakReference;
import java.util.Map;
import android.view.View$OnClickListener;
import android.view.View;
import android.os.Bundle;
import android.content.Context;

public interface zzoz
{
    void cancelUnconfirmedClick();
    
    Context getContext();
    
    void performClick(final Bundle p0);
    
    boolean recordImpression(final Bundle p0);
    
    void reportTouchEvent(final Bundle p0);
    
    void setClickConfirmingView(final View p0);
    
    View zza(final View$OnClickListener p0, final boolean p1);
    
    void zza(final View p0, final zzox p1);
    
    void zza(final View p0, final String p1, final Bundle p2, final Map<String, WeakReference<View>> p3, final View p4);
    
    void zza(final View p0, final Map<String, WeakReference<View>> p1);
    
    void zza(final View p0, final Map<String, WeakReference<View>> p1, final Bundle p2, final View p3);
    
    void zza(final zzro p0);
    
    void zzb(final View p0, final Map<String, WeakReference<View>> p1);
    
    void zzc(final View p0, final Map<String, WeakReference<View>> p1);
    
    void zzcr();
    
    void zzcs();
    
    void zzd(final MotionEvent p0);
    
    void zzj(final View p0);
    
    boolean zzkj();
    
    boolean zzkk();
    
    void zzkl();
    
    void zzkp();
    
    void zzkq();
    
    View zzkr();
    
    void zzl(final View p0);
}
