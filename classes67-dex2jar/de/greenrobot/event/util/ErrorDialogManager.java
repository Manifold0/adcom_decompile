// 
// Decompiled by Procyon v0.5.34
// 

package de.greenrobot.event.util;

import android.support.v4.app.FragmentActivity;
import android.app.DialogFragment;
import android.app.FragmentManager;
import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build$VERSION;
import android.util.Log;
import de.greenrobot.event.EventBus;
import android.os.Bundle;
import android.app.Activity;

public class ErrorDialogManager
{
    public static final String KEY_EVENT_TYPE_ON_CLOSE = "de.greenrobot.eventbus.errordialog.event_type_on_close";
    public static final String KEY_FINISH_AFTER_DIALOG = "de.greenrobot.eventbus.errordialog.finish_after_dialog";
    public static final String KEY_ICON_ID = "de.greenrobot.eventbus.errordialog.icon_id";
    public static final String KEY_MESSAGE = "de.greenrobot.eventbus.errordialog.message";
    public static final String KEY_TITLE = "de.greenrobot.eventbus.errordialog.title";
    protected static final String TAG_ERROR_DIALOG = "de.greenrobot.eventbus.error_dialog";
    protected static final String TAG_ERROR_DIALOG_MANAGER = "de.greenrobot.eventbus.error_dialog_manager";
    public static ErrorDialogFragmentFactory<?> factory;
    
    public static void attachTo(final Activity activity) {
        attachTo(activity, false, null);
    }
    
    public static void attachTo(final Activity activity, final Object o, final boolean b, final Bundle bundle) {
        if (ErrorDialogManager.factory == null) {
            throw new RuntimeException("You must set the static factory field to configure error dialogs for your app.");
        }
        if (isSupportActivity(activity)) {
            SupportManagerFragment.attachTo(activity, o, b, bundle);
            return;
        }
        HoneycombManagerFragment.attachTo(activity, o, b, bundle);
    }
    
    public static void attachTo(final Activity activity, final boolean b) {
        attachTo(activity, b, null);
    }
    
    public static void attachTo(final Activity activity, final boolean b, final Bundle bundle) {
        attachTo(activity, activity.getClass(), b, bundle);
    }
    
    protected static void checkLogException(final ThrowableFailureEvent throwableFailureEvent) {
        if (ErrorDialogManager.factory.config.logExceptions) {
            String s;
            if ((s = ErrorDialogManager.factory.config.tagForLoggingExceptions) == null) {
                s = EventBus.TAG;
            }
            Log.i(s, "Error dialog manager received exception", throwableFailureEvent.throwable);
        }
    }
    
    private static boolean isInExecutionScope(final Object o, final ThrowableFailureEvent throwableFailureEvent) {
        if (o != null && throwableFailureEvent instanceof HasExecutionScope) {
            final Object executionScope = throwableFailureEvent.getExecutionScope();
            if (executionScope != null && !executionScope.equals(o)) {
                return false;
            }
        }
        return true;
    }
    
    private static boolean isSupportActivity(final Activity activity) {
        boolean b = false;
        Class<? super Activity> clazz = activity.getClass().getSuperclass();
        while (clazz != null) {
            final String name = clazz.getName();
            if (name.equals("android.support.v4.app.FragmentActivity")) {
                b = true;
            }
            else {
                if (name.startsWith("com.actionbarsherlock.app") && (name.endsWith(".SherlockActivity") || name.endsWith(".SherlockListActivity") || name.endsWith(".SherlockPreferenceActivity"))) {
                    throw new RuntimeException("Please use SherlockFragmentActivity. Illegal activity: " + name);
                }
                if (!name.equals("android.app.Activity")) {
                    clazz = clazz.getSuperclass();
                    continue;
                }
                if (Build$VERSION.SDK_INT < 11) {
                    throw new RuntimeException("Illegal activity without fragment support. Either use Android 3.0+ or android.support.v4.app.FragmentActivity.");
                }
            }
            return b;
        }
        throw new RuntimeException("Illegal activity type: " + activity.getClass());
    }
    
    @TargetApi(11)
    public static class HoneycombManagerFragment extends Fragment
    {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;
        
        public static void attachTo(final Activity activity, final Object executionScope, final boolean finishAfterDialog, final Bundle argumentsForErrorDialog) {
            final FragmentManager fragmentManager = activity.getFragmentManager();
            HoneycombManagerFragment honeycombManagerFragment;
            if ((honeycombManagerFragment = (HoneycombManagerFragment)fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager")) == null) {
                honeycombManagerFragment = new HoneycombManagerFragment();
                fragmentManager.beginTransaction().add((Fragment)honeycombManagerFragment, "de.greenrobot.eventbus.error_dialog_manager").commit();
                fragmentManager.executePendingTransactions();
            }
            honeycombManagerFragment.finishAfterDialog = finishAfterDialog;
            honeycombManagerFragment.argumentsForErrorDialog = argumentsForErrorDialog;
            honeycombManagerFragment.executionScope = executionScope;
        }
        
        public void onEventMainThread(final ThrowableFailureEvent throwableFailureEvent) {
            if (isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                ErrorDialogManager.checkLogException(throwableFailureEvent);
                final FragmentManager fragmentManager = this.getFragmentManager();
                fragmentManager.executePendingTransactions();
                final DialogFragment dialogFragment = (DialogFragment)fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                final DialogFragment dialogFragment2 = (DialogFragment)ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, "de.greenrobot.eventbus.error_dialog");
                }
            }
        }
        
        public void onPause() {
            this.eventBus.unregister(this);
            super.onPause();
        }
        
        public void onResume() {
            super.onResume();
            (this.eventBus = ErrorDialogManager.factory.config.getEventBus()).register(this);
        }
    }
    
    public static class SupportManagerFragment extends android.support.v4.app.Fragment
    {
        protected Bundle argumentsForErrorDialog;
        private EventBus eventBus;
        private Object executionScope;
        protected boolean finishAfterDialog;
        private boolean skipRegisterOnNextResume;
        
        public static void attachTo(final Activity activity, final Object executionScope, final boolean finishAfterDialog, final Bundle argumentsForErrorDialog) {
            final android.support.v4.app.FragmentManager supportFragmentManager = ((FragmentActivity)activity).getSupportFragmentManager();
            SupportManagerFragment supportManagerFragment;
            if ((supportManagerFragment = (SupportManagerFragment)supportFragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog_manager")) == null) {
                supportManagerFragment = new SupportManagerFragment();
                supportFragmentManager.beginTransaction().add((android.support.v4.app.Fragment)supportManagerFragment, "de.greenrobot.eventbus.error_dialog_manager").commit();
                supportFragmentManager.executePendingTransactions();
            }
            supportManagerFragment.finishAfterDialog = finishAfterDialog;
            supportManagerFragment.argumentsForErrorDialog = argumentsForErrorDialog;
            supportManagerFragment.executionScope = executionScope;
        }
        
        public void onCreate(final Bundle bundle) {
            super.onCreate(bundle);
            (this.eventBus = ErrorDialogManager.factory.config.getEventBus()).register(this);
            this.skipRegisterOnNextResume = true;
        }
        
        public void onEventMainThread(final ThrowableFailureEvent throwableFailureEvent) {
            if (isInExecutionScope(this.executionScope, throwableFailureEvent)) {
                ErrorDialogManager.checkLogException(throwableFailureEvent);
                final android.support.v4.app.FragmentManager fragmentManager = this.getFragmentManager();
                fragmentManager.executePendingTransactions();
                final android.support.v4.app.DialogFragment dialogFragment = (android.support.v4.app.DialogFragment)fragmentManager.findFragmentByTag("de.greenrobot.eventbus.error_dialog");
                if (dialogFragment != null) {
                    dialogFragment.dismiss();
                }
                final android.support.v4.app.DialogFragment dialogFragment2 = (android.support.v4.app.DialogFragment)ErrorDialogManager.factory.prepareErrorFragment(throwableFailureEvent, this.finishAfterDialog, this.argumentsForErrorDialog);
                if (dialogFragment2 != null) {
                    dialogFragment2.show(fragmentManager, "de.greenrobot.eventbus.error_dialog");
                }
            }
        }
        
        public void onPause() {
            this.eventBus.unregister(this);
            super.onPause();
        }
        
        public void onResume() {
            super.onResume();
            if (this.skipRegisterOnNextResume) {
                this.skipRegisterOnNextResume = false;
                return;
            }
            (this.eventBus = ErrorDialogManager.factory.config.getEventBus()).register(this);
        }
    }
}
