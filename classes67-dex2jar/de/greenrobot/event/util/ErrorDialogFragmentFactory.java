// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

import android.annotation.TargetApi;
import android.app.Fragment;
import java.io.Serializable;
import android.os.Bundle;

public abstract class ErrorDialogFragmentFactory<T>
{
    protected final ErrorDialogConfig config;
    
    protected ErrorDialogFragmentFactory(final ErrorDialogConfig config) {
        this.config = config;
    }
    
    protected abstract T createErrorFragment(final ThrowableFailureEvent p0, final Bundle p1);
    
    protected String getMessageFor(final ThrowableFailureEvent throwableFailureEvent, final Bundle bundle) {
        return this.config.resources.getString(this.config.getMessageIdForThrowable(throwableFailureEvent.throwable));
    }
    
    protected String getTitleFor(final ThrowableFailureEvent throwableFailureEvent, final Bundle bundle) {
        return this.config.resources.getString(this.config.defaultTitleId);
    }
    
    protected T prepareErrorFragment(final ThrowableFailureEvent throwableFailureEvent, final boolean b, Bundle bundle) {
        if (throwableFailureEvent.isSuppressErrorUi()) {
            return null;
        }
        if (bundle != null) {
            bundle = (Bundle)bundle.clone();
        }
        else {
            bundle = new Bundle();
        }
        if (!bundle.containsKey("de.greenrobot.eventbus.errordialog.title")) {
            bundle.putString("de.greenrobot.eventbus.errordialog.title", this.getTitleFor(throwableFailureEvent, bundle));
        }
        if (!bundle.containsKey("de.greenrobot.eventbus.errordialog.message")) {
            bundle.putString("de.greenrobot.eventbus.errordialog.message", this.getMessageFor(throwableFailureEvent, bundle));
        }
        if (!bundle.containsKey("de.greenrobot.eventbus.errordialog.finish_after_dialog")) {
            bundle.putBoolean("de.greenrobot.eventbus.errordialog.finish_after_dialog", b);
        }
        if (!bundle.containsKey("de.greenrobot.eventbus.errordialog.event_type_on_close") && this.config.defaultEventTypeOnDialogClosed != null) {
            bundle.putSerializable("de.greenrobot.eventbus.errordialog.event_type_on_close", (Serializable)this.config.defaultEventTypeOnDialogClosed);
        }
        if (!bundle.containsKey("de.greenrobot.eventbus.errordialog.icon_id") && this.config.defaultDialogIconId != 0) {
            bundle.putInt("de.greenrobot.eventbus.errordialog.icon_id", this.config.defaultDialogIconId);
        }
        return this.createErrorFragment(throwableFailureEvent, bundle);
    }
    
    @TargetApi(11)
    public static class Honeycomb extends ErrorDialogFragmentFactory<Fragment>
    {
        public Honeycomb(final ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }
        
        @Override
        protected Fragment createErrorFragment(final ThrowableFailureEvent throwableFailureEvent, final Bundle arguments) {
            final ErrorDialogFragments.Honeycomb honeycomb = new ErrorDialogFragments.Honeycomb();
            honeycomb.setArguments(arguments);
            return (Fragment)honeycomb;
        }
    }
    
    public static class Support extends ErrorDialogFragmentFactory<android.support.v4.app.Fragment>
    {
        public Support(final ErrorDialogConfig errorDialogConfig) {
            super(errorDialogConfig);
        }
        
        @Override
        protected android.support.v4.app.Fragment createErrorFragment(final ThrowableFailureEvent throwableFailureEvent, final Bundle arguments) {
            final ErrorDialogFragments.Support support = new ErrorDialogFragments.Support();
            support.setArguments(arguments);
            return (android.support.v4.app.Fragment)support;
        }
    }
}
