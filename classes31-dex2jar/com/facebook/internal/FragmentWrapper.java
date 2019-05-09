// 
// Decompiled by Procyon v0.5.34
// 

package com.facebook.internal;

import android.content.Intent;
import android.app.Activity;
import android.app.Fragment;

public class FragmentWrapper
{
    private Fragment nativeFragment;
    private android.support.v4.app.Fragment supportFragment;
    
    public FragmentWrapper(final Fragment nativeFragment) {
        Validate.notNull((Object)nativeFragment, "fragment");
        this.nativeFragment = nativeFragment;
    }
    
    public FragmentWrapper(final android.support.v4.app.Fragment supportFragment) {
        Validate.notNull((Object)supportFragment, "fragment");
        this.supportFragment = supportFragment;
    }
    
    public final Activity getActivity() {
        if (this.supportFragment != null) {
            return (Activity)this.supportFragment.getActivity();
        }
        return this.nativeFragment.getActivity();
    }
    
    public Fragment getNativeFragment() {
        return this.nativeFragment;
    }
    
    public android.support.v4.app.Fragment getSupportFragment() {
        return this.supportFragment;
    }
    
    public void startActivityForResult(final Intent intent, final int n) {
        if (this.supportFragment != null) {
            this.supportFragment.startActivityForResult(intent, n);
            return;
        }
        this.nativeFragment.startActivityForResult(intent, n);
    }
}
