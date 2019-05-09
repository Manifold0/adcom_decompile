// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;
import android.annotation.SuppressLint;

@SuppressLint({ "NewApi" })
@KeepForSdk
public final class FragmentWrapper extends Stub
{
    private Fragment zzia;
    
    private FragmentWrapper(final Fragment zzia) {
        this.zzia = zzia;
    }
    
    @KeepForSdk
    public static FragmentWrapper wrap(final Fragment fragment) {
        if (fragment != null) {
            return new FragmentWrapper(fragment);
        }
        return null;
    }
    
    @Override
    public final Bundle getArguments() {
        return this.zzia.getArguments();
    }
    
    @Override
    public final int getId() {
        return this.zzia.getId();
    }
    
    @Override
    public final boolean getRetainInstance() {
        return this.zzia.getRetainInstance();
    }
    
    @Override
    public final String getTag() {
        return this.zzia.getTag();
    }
    
    @Override
    public final int getTargetRequestCode() {
        return this.zzia.getTargetRequestCode();
    }
    
    @Override
    public final boolean getUserVisibleHint() {
        return this.zzia.getUserVisibleHint();
    }
    
    @Override
    public final boolean isAdded() {
        return this.zzia.isAdded();
    }
    
    @Override
    public final boolean isDetached() {
        return this.zzia.isDetached();
    }
    
    @Override
    public final boolean isHidden() {
        return this.zzia.isHidden();
    }
    
    @Override
    public final boolean isInLayout() {
        return this.zzia.isInLayout();
    }
    
    @Override
    public final boolean isRemoving() {
        return this.zzia.isRemoving();
    }
    
    @Override
    public final boolean isResumed() {
        return this.zzia.isResumed();
    }
    
    @Override
    public final boolean isVisible() {
        return this.zzia.isVisible();
    }
    
    @Override
    public final void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.zzia.setHasOptionsMenu(hasOptionsMenu);
    }
    
    @Override
    public final void setMenuVisibility(final boolean menuVisibility) {
        this.zzia.setMenuVisibility(menuVisibility);
    }
    
    @Override
    public final void setRetainInstance(final boolean retainInstance) {
        this.zzia.setRetainInstance(retainInstance);
    }
    
    @Override
    public final void setUserVisibleHint(final boolean userVisibleHint) {
        this.zzia.setUserVisibleHint(userVisibleHint);
    }
    
    @Override
    public final void startActivity(final Intent intent) {
        this.zzia.startActivity(intent);
    }
    
    @Override
    public final void startActivityForResult(final Intent intent, final int n) {
        this.zzia.startActivityForResult(intent, n);
    }
    
    @Override
    public final void zza(final IObjectWrapper objectWrapper) {
        this.zzia.registerForContextMenu((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    @Override
    public final IObjectWrapper zzae() {
        return ObjectWrapper.wrap(this.zzia.getActivity());
    }
    
    @Override
    public final IFragmentWrapper zzaf() {
        return wrap(this.zzia.getParentFragment());
    }
    
    @Override
    public final IObjectWrapper zzag() {
        return ObjectWrapper.wrap(this.zzia.getResources());
    }
    
    @Override
    public final IFragmentWrapper zzah() {
        return wrap(this.zzia.getTargetFragment());
    }
    
    @Override
    public final IObjectWrapper zzai() {
        return ObjectWrapper.wrap(this.zzia.getView());
    }
    
    @Override
    public final void zzb(final IObjectWrapper objectWrapper) {
        this.zzia.unregisterForContextMenu((View)ObjectWrapper.unwrap(objectWrapper));
    }
}
