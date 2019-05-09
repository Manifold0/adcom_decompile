// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.app.Activity;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Intent;
import android.content.Context;
import android.view.View$OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.widget.LinearLayout;
import com.google.android.gms.common.internal.ConnectionErrorMessages;
import com.google.android.gms.common.GoogleApiAvailability;
import android.widget.FrameLayout;
import java.util.LinkedList;
import android.os.Bundle;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public abstract class DeferredLifecycleHelper<T extends LifecycleDelegate>
{
    private T zarf;
    private Bundle zarg;
    private LinkedList<zaa> zarh;
    private final OnDelegateCreatedListener<T> zari;
    
    @KeepForSdk
    public DeferredLifecycleHelper() {
        this.zari = (OnDelegateCreatedListener<T>)new com.google.android.gms.dynamic.zaa(this);
    }
    
    @KeepForSdk
    public static void showGooglePlayUnavailableMessage(final FrameLayout frameLayout) {
        final GoogleApiAvailability instance = GoogleApiAvailability.getInstance();
        final Context context = frameLayout.getContext();
        final int googlePlayServicesAvailable = instance.isGooglePlayServicesAvailable(context);
        final String errorMessage = ConnectionErrorMessages.getErrorMessage(context, googlePlayServicesAvailable);
        final String errorDialogButtonMessage = ConnectionErrorMessages.getErrorDialogButtonMessage(context, googlePlayServicesAvailable);
        final LinearLayout linearLayout = new LinearLayout(frameLayout.getContext());
        linearLayout.setOrientation(1);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        frameLayout.addView((View)linearLayout);
        final TextView textView = new TextView(frameLayout.getContext());
        textView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        textView.setText((CharSequence)errorMessage);
        linearLayout.addView((View)textView);
        final Intent errorResolutionIntent = instance.getErrorResolutionIntent(context, googlePlayServicesAvailable, null);
        if (errorResolutionIntent != null) {
            final Button button = new Button(context);
            button.setId(16908313);
            button.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
            button.setText((CharSequence)errorDialogButtonMessage);
            linearLayout.addView((View)button);
            button.setOnClickListener((View$OnClickListener)new zae(context, errorResolutionIntent));
        }
    }
    
    private final void zaa(final Bundle bundle, final zaa zaa) {
        if (this.zarf != null) {
            zaa.zaa(this.zarf);
            return;
        }
        if (this.zarh == null) {
            this.zarh = new LinkedList<zaa>();
        }
        this.zarh.add(zaa);
        if (bundle != null) {
            if (this.zarg == null) {
                this.zarg = (Bundle)bundle.clone();
            }
            else {
                this.zarg.putAll(bundle);
            }
        }
        this.createDelegate(this.zari);
    }
    
    private final void zal(final int n) {
        while (!this.zarh.isEmpty() && this.zarh.getLast().getState() >= n) {
            this.zarh.removeLast();
        }
    }
    
    @KeepForSdk
    protected abstract void createDelegate(final OnDelegateCreatedListener<T> p0);
    
    @KeepForSdk
    public T getDelegate() {
        return this.zarf;
    }
    
    @KeepForSdk
    protected void handleGooglePlayUnavailable(final FrameLayout frameLayout) {
        showGooglePlayUnavailableMessage(frameLayout);
    }
    
    @KeepForSdk
    public void onCreate(final Bundle bundle) {
        this.zaa(bundle, (zaa)new zac(this, bundle));
    }
    
    @KeepForSdk
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final FrameLayout frameLayout = new FrameLayout(layoutInflater.getContext());
        this.zaa(bundle, (zaa)new zad(this, frameLayout, layoutInflater, viewGroup, bundle));
        if (this.zarf == null) {
            this.handleGooglePlayUnavailable(frameLayout);
        }
        return (View)frameLayout;
    }
    
    @KeepForSdk
    public void onDestroy() {
        if (this.zarf != null) {
            this.zarf.onDestroy();
            return;
        }
        this.zal(1);
    }
    
    @KeepForSdk
    public void onDestroyView() {
        if (this.zarf != null) {
            this.zarf.onDestroyView();
            return;
        }
        this.zal(2);
    }
    
    @KeepForSdk
    public void onInflate(final Activity activity, final Bundle bundle, final Bundle bundle2) {
        this.zaa(bundle2, (zaa)new zab(this, activity, bundle, bundle2));
    }
    
    @KeepForSdk
    public void onLowMemory() {
        if (this.zarf != null) {
            this.zarf.onLowMemory();
        }
    }
    
    @KeepForSdk
    public void onPause() {
        if (this.zarf != null) {
            this.zarf.onPause();
            return;
        }
        this.zal(5);
    }
    
    @KeepForSdk
    public void onResume() {
        this.zaa(null, (zaa)new zag(this));
    }
    
    @KeepForSdk
    public void onSaveInstanceState(final Bundle bundle) {
        if (this.zarf != null) {
            this.zarf.onSaveInstanceState(bundle);
        }
        else if (this.zarg != null) {
            bundle.putAll(this.zarg);
        }
    }
    
    @KeepForSdk
    public void onStart() {
        this.zaa(null, (zaa)new zaf(this));
    }
    
    @KeepForSdk
    public void onStop() {
        if (this.zarf != null) {
            this.zarf.onStop();
            return;
        }
        this.zal(4);
    }
    
    private interface zaa
    {
        int getState();
        
        void zaa(final LifecycleDelegate p0);
    }
}
