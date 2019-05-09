// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.dynamic;

import android.view.View;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import com.google.android.gms.common.annotation.KeepForSdk;

@KeepForSdk
public final class SupportFragmentWrapper extends Stub
{
    private Fragment zzie;
    
    private SupportFragmentWrapper(final Fragment zzie) {
        this.zzie = zzie;
    }
    
    @KeepForSdk
    public static SupportFragmentWrapper wrap(final Fragment fragment) {
        if (fragment != null) {
            return new SupportFragmentWrapper(fragment);
        }
        return null;
    }
    
    @Override
    public final Bundle getArguments() {
        return this.zzie.getArguments();
    }
    
    @Override
    public final int getId() {
        return this.zzie.getId();
    }
    
    @Override
    public final boolean getRetainInstance() {
        return this.zzie.getRetainInstance();
    }
    
    @Override
    public final String getTag() {
        return this.zzie.getTag();
    }
    
    @Override
    public final int getTargetRequestCode() {
        return this.zzie.getTargetRequestCode();
    }
    
    @Override
    public final boolean getUserVisibleHint() {
        return this.zzie.getUserVisibleHint();
    }
    
    @Override
    public final boolean isAdded() {
        return this.zzie.isAdded();
    }
    
    @Override
    public final boolean isDetached() {
        return this.zzie.isDetached();
    }
    
    @Override
    public final boolean isHidden() {
        return this.zzie.isHidden();
    }
    
    @Override
    public final boolean isInLayout() {
        return this.zzie.isInLayout();
    }
    
    @Override
    public final boolean isRemoving() {
        return this.zzie.isRemoving();
    }
    
    @Override
    public final boolean isResumed() {
        return this.zzie.isResumed();
    }
    
    @Override
    public final boolean isVisible() {
        return this.zzie.isVisible();
    }
    
    @Override
    public final void setHasOptionsMenu(final boolean hasOptionsMenu) {
        this.zzie.setHasOptionsMenu(hasOptionsMenu);
    }
    
    @Override
    public final void setMenuVisibility(final boolean menuVisibility) {
        this.zzie.setMenuVisibility(menuVisibility);
    }
    
    @Override
    public final void setRetainInstance(final boolean retainInstance) {
        this.zzie.setRetainInstance(retainInstance);
    }
    
    @Override
    public final void setUserVisibleHint(final boolean userVisibleHint) {
        this.zzie.setUserVisibleHint(userVisibleHint);
    }
    
    @Override
    public final void startActivity(final Intent intent) {
        this.zzie.startActivity(intent);
    }
    
    @Override
    public final void startActivityForResult(final Intent intent, final int n) {
        this.zzie.startActivityForResult(intent, n);
    }
    
    @Override
    public final void zza(final IObjectWrapper objectWrapper) {
        this.zzie.registerForContextMenu((View)ObjectWrapper.unwrap(objectWrapper));
    }
    
    @Override
    public final IObjectWrapper zzae() {
        return ObjectWrapper.wrap(this.zzie.getActivity());
    }
    
    @Override
    public final IFragmentWrapper zzaf() {
        return wrap(this.zzie.getParentFragment());
    }
    
    @Override
    public final IObjectWrapper zzag() {
        return ObjectWrapper.wrap(this.zzie.getResources());
    }
    
    @Override
    public final IFragmentWrapper zzah() {
        return wrap(this.zzie.getTargetFragment());
    }
    
    @Override
    public final IObjectWrapper zzai() {
        return ObjectWrapper.wrap(this.zzie.getView());
    }
    
    @Override
    public final void zzb(final IObjectWrapper objectWrapper) {
        this.zzie.unregisterForContextMenu((View)ObjectWrapper.unwrap(objectWrapper));
    }
}
