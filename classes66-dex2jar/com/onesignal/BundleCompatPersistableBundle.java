// 
// Decompiled by Procyon v0.5.34
// 

package com.onesignal;

import android.support.annotation.RequiresApi;
import android.os.PersistableBundle;

@RequiresApi(api = 22)
class BundleCompatPersistableBundle implements BundleCompat<PersistableBundle>
{
    private PersistableBundle mBundle;
    
    BundleCompatPersistableBundle() {
        this.mBundle = new PersistableBundle();
    }
    
    BundleCompatPersistableBundle(final PersistableBundle mBundle) {
        this.mBundle = mBundle;
    }
    
    @Override
    public boolean containsKey(final String s) {
        return this.mBundle.containsKey(s);
    }
    
    @Override
    public boolean getBoolean(final String s) {
        return this.mBundle.getBoolean(s);
    }
    
    @Override
    public boolean getBoolean(final String s, final boolean b) {
        return this.mBundle.getBoolean(s, b);
    }
    
    @Override
    public PersistableBundle getBundle() {
        return this.mBundle;
    }
    
    @Override
    public Integer getInt(final String s) {
        return this.mBundle.getInt(s);
    }
    
    @Override
    public Long getLong(final String s) {
        return this.mBundle.getLong(s);
    }
    
    @Override
    public String getString(final String s) {
        return this.mBundle.getString(s);
    }
    
    @Override
    public void putBoolean(final String s, final Boolean b) {
        this.mBundle.putBoolean(s, (boolean)b);
    }
    
    @Override
    public void putInt(final String s, final Integer n) {
        this.mBundle.putInt(s, (int)n);
    }
    
    @Override
    public void putLong(final String s, final Long n) {
        this.mBundle.putLong(s, (long)n);
    }
    
    @Override
    public void putString(final String s, final String s2) {
        this.mBundle.putString(s, s2);
    }
}