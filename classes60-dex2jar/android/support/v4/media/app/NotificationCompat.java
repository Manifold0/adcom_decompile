// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.media.app;

import android.support.mediacompat.R$integer;
import android.support.annotation.RequiresApi;
import android.media.session.MediaSession$Token;
import android.os.IBinder;
import android.os.Parcelable;
import android.os.Bundle;
import android.os.Parcel;
import android.support.v4.app.BundleCompat;
import android.app.Notification;
import android.support.v4.app.NotificationCompat$Action;
import android.support.v4.app.NotificationCompat$Builder;
import android.support.v4.media.session.MediaSessionCompat;
import android.app.PendingIntent;
import android.support.v4.app.NotificationCompat$Style;
import android.support.mediacompat.R$layout;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.app.Notification$Style;
import android.app.Notification$MediaStyle;
import android.app.Notification$DecoratedMediaCustomViewStyle;
import android.os.Build$VERSION;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.mediacompat.R$id;
import android.support.mediacompat.R$color;
import android.widget.RemoteViews;

public class NotificationCompat
{
    private NotificationCompat() {
    }
    
    public static class DecoratedMediaCustomViewStyle extends MediaStyle
    {
        private void setBackgroundColor(final RemoteViews remoteViews) {
            int n;
            if (this.mBuilder.getColor() != 0) {
                n = this.mBuilder.getColor();
            }
            else {
                n = this.mBuilder.mContext.getResources().getColor(R$color.notification_material_background_media_default_color);
            }
            remoteViews.setInt(R$id.status_bar_latest_event_content, "setBackgroundColor", n);
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        @Override
        public void apply(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build$VERSION.SDK_INT >= 24) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle((Notification$Style)((MediaStyle)this).fillInMediaStyle((Notification$MediaStyle)new Notification$DecoratedMediaCustomViewStyle()));
                return;
            }
            super.apply(notificationBuilderWithBuilderAccessor);
        }
        
        @Override
        int getBigContentViewLayoutResource(final int n) {
            if (n <= 3) {
                return R$layout.notification_template_big_media_narrow_custom;
            }
            return R$layout.notification_template_big_media_custom;
        }
        
        @Override
        int getContentViewLayoutResource() {
            if (this.mBuilder.getContentView() != null) {
                return R$layout.notification_template_media_custom;
            }
            return super.getContentViewLayoutResource();
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        @Override
        public RemoteViews makeBigContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews = null;
            if (Build$VERSION.SDK_INT < 24) {
                RemoteViews remoteViews2;
                if (this.mBuilder.getBigContentView() != null) {
                    remoteViews2 = this.mBuilder.getBigContentView();
                }
                else {
                    remoteViews2 = this.mBuilder.getContentView();
                }
                if (remoteViews2 != null) {
                    final RemoteViews generateBigContentView = ((MediaStyle)this).generateBigContentView();
                    this.buildIntoRemoteViews(generateBigContentView, remoteViews2);
                    remoteViews = generateBigContentView;
                    if (Build$VERSION.SDK_INT >= 21) {
                        this.setBackgroundColor(generateBigContentView);
                        return generateBigContentView;
                    }
                }
            }
            return remoteViews;
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        @Override
        public RemoteViews makeContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build$VERSION.SDK_INT >= 24) {
                return null;
            }
            boolean b;
            if (this.mBuilder.getContentView() != null) {
                b = true;
            }
            else {
                b = false;
            }
            if (Build$VERSION.SDK_INT >= 21) {
                int n;
                if (b || this.mBuilder.getBigContentView() != null) {
                    n = 1;
                }
                else {
                    n = 0;
                }
                if (n != 0) {
                    final RemoteViews generateContentView = ((MediaStyle)this).generateContentView();
                    if (b) {
                        this.buildIntoRemoteViews(generateContentView, this.mBuilder.getContentView());
                    }
                    this.setBackgroundColor(generateContentView);
                    return generateContentView;
                }
            }
            else {
                final RemoteViews generateContentView2 = ((MediaStyle)this).generateContentView();
                if (b) {
                    this.buildIntoRemoteViews(generateContentView2, this.mBuilder.getContentView());
                    return generateContentView2;
                }
            }
            return null;
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public RemoteViews makeHeadsUpContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            RemoteViews remoteViews = null;
            if (Build$VERSION.SDK_INT < 24) {
                RemoteViews remoteViews2;
                if (this.mBuilder.getHeadsUpContentView() != null) {
                    remoteViews2 = this.mBuilder.getHeadsUpContentView();
                }
                else {
                    remoteViews2 = this.mBuilder.getContentView();
                }
                if (remoteViews2 != null) {
                    final RemoteViews generateBigContentView = ((MediaStyle)this).generateBigContentView();
                    this.buildIntoRemoteViews(generateBigContentView, remoteViews2);
                    remoteViews = generateBigContentView;
                    if (Build$VERSION.SDK_INT >= 21) {
                        this.setBackgroundColor(generateBigContentView);
                        return generateBigContentView;
                    }
                }
            }
            return remoteViews;
        }
    }
    
    public static class MediaStyle extends NotificationCompat$Style
    {
        private static final int MAX_MEDIA_BUTTONS = 5;
        private static final int MAX_MEDIA_BUTTONS_IN_COMPACT = 3;
        int[] mActionsToShowInCompact;
        PendingIntent mCancelButtonIntent;
        boolean mShowCancelButton;
        MediaSessionCompat.Token mToken;
        
        public MediaStyle() {
            this.mActionsToShowInCompact = null;
        }
        
        public MediaStyle(final NotificationCompat$Builder builder) {
            this.mActionsToShowInCompact = null;
            this.setBuilder(builder);
        }
        
        private RemoteViews generateMediaActionButton(final NotificationCompat$Action notificationCompat$Action) {
            int n;
            if (notificationCompat$Action.getActionIntent() == null) {
                n = 1;
            }
            else {
                n = 0;
            }
            final RemoteViews remoteViews = new RemoteViews(this.mBuilder.mContext.getPackageName(), R$layout.notification_media_action);
            remoteViews.setImageViewResource(R$id.action0, notificationCompat$Action.getIcon());
            if (n == 0) {
                remoteViews.setOnClickPendingIntent(R$id.action0, notificationCompat$Action.getActionIntent());
            }
            if (Build$VERSION.SDK_INT >= 15) {
                remoteViews.setContentDescription(R$id.action0, notificationCompat$Action.getTitle());
            }
            return remoteViews;
        }
        
        public static MediaSessionCompat.Token getMediaSession(final Notification notification) {
            final Bundle extras = android.support.v4.app.NotificationCompat.getExtras(notification);
            if (extras != null) {
                if (Build$VERSION.SDK_INT >= 21) {
                    final Parcelable parcelable = extras.getParcelable("android.mediaSession");
                    if (parcelable != null) {
                        return MediaSessionCompat.Token.fromToken(parcelable);
                    }
                }
                else {
                    final IBinder binder = BundleCompat.getBinder(extras, "android.mediaSession");
                    if (binder != null) {
                        final Parcel obtain = Parcel.obtain();
                        obtain.writeStrongBinder(binder);
                        obtain.setDataPosition(0);
                        final MediaSessionCompat.Token token = (MediaSessionCompat.Token)MediaSessionCompat.Token.CREATOR.createFromParcel(obtain);
                        obtain.recycle();
                        return token;
                    }
                }
            }
            return null;
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public void apply(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build$VERSION.SDK_INT >= 21) {
                notificationBuilderWithBuilderAccessor.getBuilder().setStyle((Notification$Style)this.fillInMediaStyle(new Notification$MediaStyle()));
            }
            else if (this.mShowCancelButton) {
                notificationBuilderWithBuilderAccessor.getBuilder().setOngoing(true);
            }
        }
        
        @RequiresApi(21)
        Notification$MediaStyle fillInMediaStyle(final Notification$MediaStyle notification$MediaStyle) {
            if (this.mActionsToShowInCompact != null) {
                notification$MediaStyle.setShowActionsInCompactView(this.mActionsToShowInCompact);
            }
            if (this.mToken != null) {
                notification$MediaStyle.setMediaSession((MediaSession$Token)this.mToken.getToken());
            }
            return notification$MediaStyle;
        }
        
        RemoteViews generateBigContentView() {
            final int min = Math.min(this.mBuilder.mActions.size(), 5);
            final RemoteViews applyStandardTemplate = this.applyStandardTemplate(false, this.getBigContentViewLayoutResource(min), false);
            applyStandardTemplate.removeAllViews(R$id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; ++i) {
                    applyStandardTemplate.addView(R$id.media_actions, this.generateMediaActionButton((NotificationCompat$Action)this.mBuilder.mActions.get(i)));
                }
            }
            if (this.mShowCancelButton) {
                applyStandardTemplate.setViewVisibility(R$id.cancel_action, 0);
                applyStandardTemplate.setInt(R$id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R$integer.cancel_button_image_alpha));
                applyStandardTemplate.setOnClickPendingIntent(R$id.cancel_action, this.mCancelButtonIntent);
                return applyStandardTemplate;
            }
            applyStandardTemplate.setViewVisibility(R$id.cancel_action, 8);
            return applyStandardTemplate;
        }
        
        RemoteViews generateContentView() {
            final RemoteViews applyStandardTemplate = this.applyStandardTemplate(false, this.getContentViewLayoutResource(), true);
            final int size = this.mBuilder.mActions.size();
            int min;
            if (this.mActionsToShowInCompact == null) {
                min = 0;
            }
            else {
                min = Math.min(this.mActionsToShowInCompact.length, 3);
            }
            applyStandardTemplate.removeAllViews(R$id.media_actions);
            if (min > 0) {
                for (int i = 0; i < min; ++i) {
                    if (i >= size) {
                        throw new IllegalArgumentException(String.format("setShowActionsInCompactView: action %d out of bounds (max %d)", i, size - 1));
                    }
                    applyStandardTemplate.addView(R$id.media_actions, this.generateMediaActionButton((NotificationCompat$Action)this.mBuilder.mActions.get(this.mActionsToShowInCompact[i])));
                }
            }
            if (this.mShowCancelButton) {
                applyStandardTemplate.setViewVisibility(R$id.end_padder, 8);
                applyStandardTemplate.setViewVisibility(R$id.cancel_action, 0);
                applyStandardTemplate.setOnClickPendingIntent(R$id.cancel_action, this.mCancelButtonIntent);
                applyStandardTemplate.setInt(R$id.cancel_action, "setAlpha", this.mBuilder.mContext.getResources().getInteger(R$integer.cancel_button_image_alpha));
                return applyStandardTemplate;
            }
            applyStandardTemplate.setViewVisibility(R$id.end_padder, 0);
            applyStandardTemplate.setViewVisibility(R$id.cancel_action, 8);
            return applyStandardTemplate;
        }
        
        int getBigContentViewLayoutResource(final int n) {
            if (n <= 3) {
                return R$layout.notification_template_big_media_narrow;
            }
            return R$layout.notification_template_big_media;
        }
        
        int getContentViewLayoutResource() {
            return R$layout.notification_template_media;
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public RemoteViews makeBigContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build$VERSION.SDK_INT >= 21) {
                return null;
            }
            return this.generateBigContentView();
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public RemoteViews makeContentView(final NotificationBuilderWithBuilderAccessor notificationBuilderWithBuilderAccessor) {
            if (Build$VERSION.SDK_INT >= 21) {
                return null;
            }
            return this.generateContentView();
        }
        
        public MediaStyle setCancelButtonIntent(final PendingIntent mCancelButtonIntent) {
            this.mCancelButtonIntent = mCancelButtonIntent;
            return this;
        }
        
        public MediaStyle setMediaSession(final MediaSessionCompat.Token mToken) {
            this.mToken = mToken;
            return this;
        }
        
        public MediaStyle setShowActionsInCompactView(final int... mActionsToShowInCompact) {
            this.mActionsToShowInCompact = mActionsToShowInCompact;
            return this;
        }
        
        public MediaStyle setShowCancelButton(final boolean mShowCancelButton) {
            if (Build$VERSION.SDK_INT < 21) {
                this.mShowCancelButton = mShowCancelButton;
            }
            return this;
        }
    }
}
