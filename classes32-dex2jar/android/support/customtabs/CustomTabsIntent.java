// 
// Decompiled by Procyon v0.5.34
// 

package android.support.customtabs;

import android.widget.RemoteViews;
import android.support.annotation.ColorInt;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.annotation.AnimRes;
import android.graphics.Bitmap;
import android.os.Parcelable;
import android.app.PendingIntent;
import android.os.IBinder;
import android.support.v4.app.BundleCompat;
import java.util.ArrayList;
import android.support.v4.content.ContextCompat;
import android.net.Uri;
import android.content.Context;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.content.Intent;

public final class CustomTabsIntent
{
    public static final String EXTRA_ACTION_BUTTON_BUNDLE = "android.support.customtabs.extra.ACTION_BUTTON_BUNDLE";
    public static final String EXTRA_CLOSE_BUTTON_ICON = "android.support.customtabs.extra.CLOSE_BUTTON_ICON";
    public static final String EXTRA_DEFAULT_SHARE_MENU_ITEM = "android.support.customtabs.extra.SHARE_MENU_ITEM";
    public static final String EXTRA_ENABLE_INSTANT_APPS = "android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS";
    public static final String EXTRA_ENABLE_URLBAR_HIDING = "android.support.customtabs.extra.ENABLE_URLBAR_HIDING";
    public static final String EXTRA_EXIT_ANIMATION_BUNDLE = "android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE";
    public static final String EXTRA_MENU_ITEMS = "android.support.customtabs.extra.MENU_ITEMS";
    public static final String EXTRA_REMOTEVIEWS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS";
    public static final String EXTRA_REMOTEVIEWS_CLICKED_ID = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_CLICKED_ID";
    public static final String EXTRA_REMOTEVIEWS_PENDINGINTENT = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT";
    public static final String EXTRA_REMOTEVIEWS_VIEW_IDS = "android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS";
    public static final String EXTRA_SECONDARY_TOOLBAR_COLOR = "android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR";
    public static final String EXTRA_SESSION = "android.support.customtabs.extra.SESSION";
    public static final String EXTRA_TINT_ACTION_BUTTON = "android.support.customtabs.extra.TINT_ACTION_BUTTON";
    public static final String EXTRA_TITLE_VISIBILITY_STATE = "android.support.customtabs.extra.TITLE_VISIBILITY";
    public static final String EXTRA_TOOLBAR_COLOR = "android.support.customtabs.extra.TOOLBAR_COLOR";
    public static final String EXTRA_TOOLBAR_ITEMS = "android.support.customtabs.extra.TOOLBAR_ITEMS";
    private static final String EXTRA_USER_OPT_OUT_FROM_CUSTOM_TABS = "android.support.customtabs.extra.user_opt_out";
    public static final String KEY_DESCRIPTION = "android.support.customtabs.customaction.DESCRIPTION";
    public static final String KEY_ICON = "android.support.customtabs.customaction.ICON";
    public static final String KEY_ID = "android.support.customtabs.customaction.ID";
    public static final String KEY_MENU_ITEM_TITLE = "android.support.customtabs.customaction.MENU_ITEM_TITLE";
    public static final String KEY_PENDING_INTENT = "android.support.customtabs.customaction.PENDING_INTENT";
    private static final int MAX_TOOLBAR_ITEMS = 5;
    public static final int NO_TITLE = 0;
    public static final int SHOW_PAGE_TITLE = 1;
    public static final int TOOLBAR_ACTION_BUTTON_ID = 0;
    @NonNull
    public final Intent intent;
    @Nullable
    public final Bundle startAnimationBundle;
    
    private CustomTabsIntent(final Intent intent, final Bundle startAnimationBundle) {
        this.intent = intent;
        this.startAnimationBundle = startAnimationBundle;
    }
    
    public static int getMaxToolbarItems() {
        return 5;
    }
    
    public static Intent setAlwaysUseBrowserUI(final Intent intent) {
        Intent intent2 = intent;
        if (intent == null) {
            intent2 = new Intent("android.intent.action.VIEW");
        }
        intent2.addFlags(268435456);
        intent2.putExtra("android.support.customtabs.extra.user_opt_out", true);
        return intent2;
    }
    
    public static boolean shouldAlwaysUseBrowserUI(final Intent intent) {
        boolean b = false;
        if (intent.getBooleanExtra("android.support.customtabs.extra.user_opt_out", false)) {
            b = b;
            if ((intent.getFlags() & 0x10000000) != 0x0) {
                b = true;
            }
        }
        return b;
    }
    
    public void launchUrl(final Context context, final Uri data) {
        this.intent.setData(data);
        ContextCompat.startActivity(context, this.intent, this.startAnimationBundle);
    }
    
    public static final class Builder
    {
        private ArrayList<Bundle> mActionButtons;
        private boolean mInstantAppsEnabled;
        private final Intent mIntent;
        private ArrayList<Bundle> mMenuItems;
        private Bundle mStartAnimationBundle;
        
        public Builder() {
            this(null);
        }
        
        public Builder(@Nullable final CustomTabsSession customTabsSession) {
            final IBinder binder = null;
            this.mIntent = new Intent("android.intent.action.VIEW");
            this.mMenuItems = null;
            this.mStartAnimationBundle = null;
            this.mActionButtons = null;
            this.mInstantAppsEnabled = true;
            if (customTabsSession != null) {
                this.mIntent.setPackage(customTabsSession.getComponentName().getPackageName());
            }
            final Bundle bundle = new Bundle();
            IBinder binder2;
            if (customTabsSession == null) {
                binder2 = binder;
            }
            else {
                binder2 = customTabsSession.getBinder();
            }
            BundleCompat.putBinder(bundle, "android.support.customtabs.extra.SESSION", binder2);
            this.mIntent.putExtras(bundle);
        }
        
        public Builder addDefaultShareMenuItem() {
            this.mIntent.putExtra("android.support.customtabs.extra.SHARE_MENU_ITEM", true);
            return this;
        }
        
        public Builder addMenuItem(@NonNull final String s, @NonNull final PendingIntent pendingIntent) {
            if (this.mMenuItems == null) {
                this.mMenuItems = new ArrayList<Bundle>();
            }
            final Bundle bundle = new Bundle();
            bundle.putString("android.support.customtabs.customaction.MENU_ITEM_TITLE", s);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", (Parcelable)pendingIntent);
            this.mMenuItems.add(bundle);
            return this;
        }
        
        @Deprecated
        public Builder addToolbarItem(final int n, @NonNull final Bitmap bitmap, @NonNull final String s, final PendingIntent pendingIntent) throws IllegalStateException {
            if (this.mActionButtons == null) {
                this.mActionButtons = new ArrayList<Bundle>();
            }
            if (this.mActionButtons.size() >= 5) {
                throw new IllegalStateException("Exceeded maximum toolbar item count of 5");
            }
            final Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", n);
            bundle.putParcelable("android.support.customtabs.customaction.ICON", (Parcelable)bitmap);
            bundle.putString("android.support.customtabs.customaction.DESCRIPTION", s);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", (Parcelable)pendingIntent);
            this.mActionButtons.add(bundle);
            return this;
        }
        
        public CustomTabsIntent build() {
            if (this.mMenuItems != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.MENU_ITEMS", (ArrayList)this.mMenuItems);
            }
            if (this.mActionButtons != null) {
                this.mIntent.putParcelableArrayListExtra("android.support.customtabs.extra.TOOLBAR_ITEMS", (ArrayList)this.mActionButtons);
            }
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_ENABLE_INSTANT_APPS", this.mInstantAppsEnabled);
            return new CustomTabsIntent(this.mIntent, this.mStartAnimationBundle, null);
        }
        
        public Builder enableUrlBarHiding() {
            this.mIntent.putExtra("android.support.customtabs.extra.ENABLE_URLBAR_HIDING", true);
            return this;
        }
        
        public Builder setActionButton(@NonNull final Bitmap bitmap, @NonNull final String s, @NonNull final PendingIntent pendingIntent) {
            return this.setActionButton(bitmap, s, pendingIntent, false);
        }
        
        public Builder setActionButton(@NonNull final Bitmap bitmap, @NonNull final String s, @NonNull final PendingIntent pendingIntent, final boolean b) {
            final Bundle bundle = new Bundle();
            bundle.putInt("android.support.customtabs.customaction.ID", 0);
            bundle.putParcelable("android.support.customtabs.customaction.ICON", (Parcelable)bitmap);
            bundle.putString("android.support.customtabs.customaction.DESCRIPTION", s);
            bundle.putParcelable("android.support.customtabs.customaction.PENDING_INTENT", (Parcelable)pendingIntent);
            this.mIntent.putExtra("android.support.customtabs.extra.ACTION_BUTTON_BUNDLE", bundle);
            this.mIntent.putExtra("android.support.customtabs.extra.TINT_ACTION_BUTTON", b);
            return this;
        }
        
        public Builder setCloseButtonIcon(@NonNull final Bitmap bitmap) {
            this.mIntent.putExtra("android.support.customtabs.extra.CLOSE_BUTTON_ICON", (Parcelable)bitmap);
            return this;
        }
        
        public Builder setExitAnimations(@NonNull final Context context, @AnimRes final int n, @AnimRes final int n2) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXIT_ANIMATION_BUNDLE", ActivityOptionsCompat.makeCustomAnimation(context, n, n2).toBundle());
            return this;
        }
        
        public Builder setInstantAppsEnabled(final boolean mInstantAppsEnabled) {
            this.mInstantAppsEnabled = mInstantAppsEnabled;
            return this;
        }
        
        public Builder setSecondaryToolbarColor(@ColorInt final int n) {
            this.mIntent.putExtra("android.support.customtabs.extra.SECONDARY_TOOLBAR_COLOR", n);
            return this;
        }
        
        public Builder setSecondaryToolbarViews(@NonNull final RemoteViews remoteViews, @Nullable final int[] array, @Nullable final PendingIntent pendingIntent) {
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS", (Parcelable)remoteViews);
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_VIEW_IDS", array);
            this.mIntent.putExtra("android.support.customtabs.extra.EXTRA_REMOTEVIEWS_PENDINGINTENT", (Parcelable)pendingIntent);
            return this;
        }
        
        public Builder setShowTitle(final boolean b) {
            final Intent mIntent = this.mIntent;
            int n;
            if (b) {
                n = 1;
            }
            else {
                n = 0;
            }
            mIntent.putExtra("android.support.customtabs.extra.TITLE_VISIBILITY", n);
            return this;
        }
        
        public Builder setStartAnimations(@NonNull final Context context, @AnimRes final int n, @AnimRes final int n2) {
            this.mStartAnimationBundle = ActivityOptionsCompat.makeCustomAnimation(context, n, n2).toBundle();
            return this;
        }
        
        public Builder setToolbarColor(@ColorInt final int n) {
            this.mIntent.putExtra("android.support.customtabs.extra.TOOLBAR_COLOR", n);
            return this;
        }
    }
}
