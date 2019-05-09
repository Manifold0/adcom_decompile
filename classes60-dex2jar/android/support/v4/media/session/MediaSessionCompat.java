// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v4.media.session;

import android.media.MediaMetadataEditor;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Annotation;
import android.os.Parcel;
import android.os.Parcelable$Creator;
import android.os.Parcelable;
import android.graphics.Bitmap;
import android.media.RemoteControlClient;
import android.media.AudioManager;
import android.os.IInterface;
import android.os.RemoteException;
import android.os.RemoteCallbackList;
import android.media.Rating;
import android.media.RemoteControlClient$OnMetadataUpdateListener;
import android.media.RemoteControlClient$MetadataEditor;
import android.media.RemoteControlClient$OnPlaybackPositionUpdateListener;
import android.os.IBinder;
import android.os.BadParcelableException;
import android.support.v4.app.BundleCompat;
import android.support.annotation.RequiresApi;
import android.os.Message;
import android.os.Looper;
import android.support.v4.media.RatingCompat;
import android.net.Uri;
import android.view.ViewConfiguration;
import android.view.KeyEvent;
import android.os.ResultReceiver;
import android.support.v4.media.MediaDescriptionCompat;
import java.lang.ref.WeakReference;
import java.util.List;
import android.support.v4.media.VolumeProviderCompat;
import android.os.Handler;
import java.util.Iterator;
import android.os.Bundle;
import android.support.annotation.RestrictTo$Scope;
import android.support.annotation.RestrictTo;
import android.os.SystemClock;
import android.support.v4.media.MediaMetadataCompat;
import android.util.TypedValue;
import android.content.Intent;
import android.util.Log;
import android.text.TextUtils;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.ArrayList;

public class MediaSessionCompat
{
    static final String ACTION_ARGUMENT_CAPTIONING_ENABLED = "android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED";
    static final String ACTION_ARGUMENT_EXTRAS = "android.support.v4.media.session.action.ARGUMENT_EXTRAS";
    static final String ACTION_ARGUMENT_MEDIA_ID = "android.support.v4.media.session.action.ARGUMENT_MEDIA_ID";
    static final String ACTION_ARGUMENT_QUERY = "android.support.v4.media.session.action.ARGUMENT_QUERY";
    static final String ACTION_ARGUMENT_RATING = "android.support.v4.media.session.action.ARGUMENT_RATING";
    static final String ACTION_ARGUMENT_REPEAT_MODE = "android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE";
    static final String ACTION_ARGUMENT_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED";
    static final String ACTION_ARGUMENT_URI = "android.support.v4.media.session.action.ARGUMENT_URI";
    public static final String ACTION_FLAG_AS_INAPPROPRIATE = "android.support.v4.media.session.action.FLAG_AS_INAPPROPRIATE";
    public static final String ACTION_FOLLOW = "android.support.v4.media.session.action.FOLLOW";
    static final String ACTION_PLAY_FROM_URI = "android.support.v4.media.session.action.PLAY_FROM_URI";
    static final String ACTION_PREPARE = "android.support.v4.media.session.action.PREPARE";
    static final String ACTION_PREPARE_FROM_MEDIA_ID = "android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID";
    static final String ACTION_PREPARE_FROM_SEARCH = "android.support.v4.media.session.action.PREPARE_FROM_SEARCH";
    static final String ACTION_PREPARE_FROM_URI = "android.support.v4.media.session.action.PREPARE_FROM_URI";
    static final String ACTION_SET_CAPTIONING_ENABLED = "android.support.v4.media.session.action.SET_CAPTIONING_ENABLED";
    static final String ACTION_SET_RATING = "android.support.v4.media.session.action.SET_RATING";
    static final String ACTION_SET_REPEAT_MODE = "android.support.v4.media.session.action.SET_REPEAT_MODE";
    static final String ACTION_SET_SHUFFLE_MODE = "android.support.v4.media.session.action.SET_SHUFFLE_MODE";
    static final String ACTION_SET_SHUFFLE_MODE_ENABLED = "android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED";
    public static final String ACTION_SKIP_AD = "android.support.v4.media.session.action.SKIP_AD";
    public static final String ACTION_UNFOLLOW = "android.support.v4.media.session.action.UNFOLLOW";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE";
    public static final String ARGUMENT_MEDIA_ATTRIBUTE_VALUE = "android.support.v4.media.session.ARGUMENT_MEDIA_ATTRIBUTE_VALUE";
    static final String EXTRA_BINDER = "android.support.v4.media.session.EXTRA_BINDER";
    public static final int FLAG_HANDLES_MEDIA_BUTTONS = 1;
    public static final int FLAG_HANDLES_QUEUE_COMMANDS = 4;
    public static final int FLAG_HANDLES_TRANSPORT_CONTROLS = 2;
    private static final int MAX_BITMAP_SIZE_IN_DP = 320;
    public static final int MEDIA_ATTRIBUTE_ALBUM = 1;
    public static final int MEDIA_ATTRIBUTE_ARTIST = 0;
    public static final int MEDIA_ATTRIBUTE_PLAYLIST = 2;
    static final String TAG = "MediaSessionCompat";
    static int sMaxBitmapSize;
    private final ArrayList<OnActiveChangeListener> mActiveListeners;
    private final MediaControllerCompat mController;
    private final MediaSessionImpl mImpl;
    
    private MediaSessionCompat(final Context context, final MediaSessionImpl mImpl) {
        this.mActiveListeners = new ArrayList<OnActiveChangeListener>();
        this.mImpl = mImpl;
        if (Build$VERSION.SDK_INT >= 21 && !MediaSessionCompatApi21.hasCallback(mImpl.getMediaSession())) {
            this.setCallback((Callback)new Callback() {});
        }
        this.mController = new MediaControllerCompat(context, this);
    }
    
    public MediaSessionCompat(final Context context, final String s) {
        this(context, s, null, null);
    }
    
    public MediaSessionCompat(final Context context, final String s, ComponentName mediaButtonReceiverComponent, final PendingIntent pendingIntent) {
        this.mActiveListeners = new ArrayList<OnActiveChangeListener>();
        if (context == null) {
            throw new IllegalArgumentException("context must not be null");
        }
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("tag must not be null or empty");
        }
        ComponentName component;
        if ((component = mediaButtonReceiverComponent) == null) {
            mediaButtonReceiverComponent = MediaButtonReceiver.getMediaButtonReceiverComponent(context);
            if ((component = mediaButtonReceiverComponent) == null) {
                Log.w("MediaSessionCompat", "Couldn't find a unique registered media button receiver in the given context.");
                component = mediaButtonReceiverComponent;
            }
        }
        PendingIntent broadcast = pendingIntent;
        if (component != null && (broadcast = pendingIntent) == null) {
            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
            intent.setComponent(component);
            broadcast = PendingIntent.getBroadcast(context, 0, intent, 0);
        }
        if (Build$VERSION.SDK_INT >= 21) {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplApi21(context, s);
            this.setCallback((Callback)new Callback() {});
            this.mImpl.setMediaButtonReceiver(broadcast);
        }
        else if (Build$VERSION.SDK_INT >= 19) {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplApi19(context, s, component, broadcast);
        }
        else if (Build$VERSION.SDK_INT >= 18) {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplApi18(context, s, component, broadcast);
        }
        else {
            this.mImpl = (MediaSessionImpl)new MediaSessionImplBase(context, s, component, broadcast);
        }
        this.mController = new MediaControllerCompat(context, this);
        if (MediaSessionCompat.sMaxBitmapSize == 0) {
            MediaSessionCompat.sMaxBitmapSize = (int)TypedValue.applyDimension(1, 320.0f, context.getResources().getDisplayMetrics());
        }
    }
    
    public static MediaSessionCompat fromMediaSession(final Context context, final Object o) {
        if (context != null && o != null && Build$VERSION.SDK_INT >= 21) {
            return new MediaSessionCompat(context, (MediaSessionImpl)new MediaSessionImplApi21(o));
        }
        return null;
    }
    
    private static PlaybackStateCompat getStateWithUpdatedPosition(final PlaybackStateCompat playbackStateCompat, final MediaMetadataCompat mediaMetadataCompat) {
        if (playbackStateCompat != null && playbackStateCompat.getPosition() != -1L && (playbackStateCompat.getState() == 3 || playbackStateCompat.getState() == 4 || playbackStateCompat.getState() == 5)) {
            final long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            if (lastPositionUpdateTime > 0L) {
                final long elapsedRealtime = SystemClock.elapsedRealtime();
                final long n = (long)(playbackStateCompat.getPlaybackSpeed() * (elapsedRealtime - lastPositionUpdateTime)) + playbackStateCompat.getPosition();
                long long1;
                final long n2 = long1 = -1L;
                if (mediaMetadataCompat != null) {
                    long1 = n2;
                    if (mediaMetadataCompat.containsKey("android.media.metadata.DURATION")) {
                        long1 = mediaMetadataCompat.getLong("android.media.metadata.DURATION");
                    }
                }
                if (long1 < 0L || n <= long1) {
                    long1 = n;
                    if (n < 0L) {
                        long1 = 0L;
                    }
                }
                return new PlaybackStateCompat.Builder(playbackStateCompat).setState(playbackStateCompat.getState(), long1, playbackStateCompat.getPlaybackSpeed(), elapsedRealtime).build();
            }
        }
        return playbackStateCompat;
    }
    
    public void addOnActiveChangeListener(final OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.add(onActiveChangeListener);
    }
    
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public String getCallingPackage() {
        return this.mImpl.getCallingPackage();
    }
    
    public MediaControllerCompat getController() {
        return this.mController;
    }
    
    public Object getMediaSession() {
        return this.mImpl.getMediaSession();
    }
    
    public Object getRemoteControlClient() {
        return this.mImpl.getRemoteControlClient();
    }
    
    public Token getSessionToken() {
        return this.mImpl.getSessionToken();
    }
    
    public boolean isActive() {
        return this.mImpl.isActive();
    }
    
    public void release() {
        this.mImpl.release();
    }
    
    public void removeOnActiveChangeListener(final OnActiveChangeListener onActiveChangeListener) {
        if (onActiveChangeListener == null) {
            throw new IllegalArgumentException("Listener may not be null");
        }
        this.mActiveListeners.remove(onActiveChangeListener);
    }
    
    public void sendSessionEvent(final String s, final Bundle bundle) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("event cannot be null or empty");
        }
        this.mImpl.sendSessionEvent(s, bundle);
    }
    
    public void setActive(final boolean active) {
        this.mImpl.setActive(active);
        final Iterator<OnActiveChangeListener> iterator = this.mActiveListeners.iterator();
        while (iterator.hasNext()) {
            iterator.next().onActiveChanged();
        }
    }
    
    public void setCallback(final Callback callback) {
        this.setCallback(callback, null);
    }
    
    public void setCallback(final Callback callback, Handler handler) {
        final MediaSessionImpl mImpl = this.mImpl;
        if (handler == null) {
            handler = new Handler();
        }
        mImpl.setCallback(callback, handler);
    }
    
    public void setCaptioningEnabled(final boolean captioningEnabled) {
        this.mImpl.setCaptioningEnabled(captioningEnabled);
    }
    
    public void setExtras(final Bundle extras) {
        this.mImpl.setExtras(extras);
    }
    
    public void setFlags(final int flags) {
        this.mImpl.setFlags(flags);
    }
    
    public void setMediaButtonReceiver(final PendingIntent mediaButtonReceiver) {
        this.mImpl.setMediaButtonReceiver(mediaButtonReceiver);
    }
    
    public void setMetadata(final MediaMetadataCompat metadata) {
        this.mImpl.setMetadata(metadata);
    }
    
    public void setPlaybackState(final PlaybackStateCompat playbackState) {
        this.mImpl.setPlaybackState(playbackState);
    }
    
    public void setPlaybackToLocal(final int playbackToLocal) {
        this.mImpl.setPlaybackToLocal(playbackToLocal);
    }
    
    public void setPlaybackToRemote(final VolumeProviderCompat playbackToRemote) {
        if (playbackToRemote == null) {
            throw new IllegalArgumentException("volumeProvider may not be null!");
        }
        this.mImpl.setPlaybackToRemote(playbackToRemote);
    }
    
    public void setQueue(final List<QueueItem> queue) {
        this.mImpl.setQueue(queue);
    }
    
    public void setQueueTitle(final CharSequence queueTitle) {
        this.mImpl.setQueueTitle(queueTitle);
    }
    
    public void setRatingType(final int ratingType) {
        this.mImpl.setRatingType(ratingType);
    }
    
    public void setRepeatMode(final int repeatMode) {
        this.mImpl.setRepeatMode(repeatMode);
    }
    
    public void setSessionActivity(final PendingIntent sessionActivity) {
        this.mImpl.setSessionActivity(sessionActivity);
    }
    
    public void setShuffleMode(final int shuffleMode) {
        this.mImpl.setShuffleMode(shuffleMode);
    }
    
    @Deprecated
    public void setShuffleModeEnabled(final boolean shuffleModeEnabled) {
        this.mImpl.setShuffleModeEnabled(shuffleModeEnabled);
    }
    
    public abstract static class Callback
    {
        private CallbackHandler mCallbackHandler;
        final Object mCallbackObj;
        private boolean mMediaPlayPauseKeyPending;
        private WeakReference<MediaSessionImpl> mSessionImpl;
        
        public Callback() {
            this.mCallbackHandler = null;
            if (Build$VERSION.SDK_INT >= 24) {
                this.mCallbackObj = MediaSessionCompatApi24.createCallback((MediaSessionCompatApi24.Callback)new StubApi24());
                return;
            }
            if (Build$VERSION.SDK_INT >= 23) {
                this.mCallbackObj = MediaSessionCompatApi23.createCallback((MediaSessionCompatApi23.Callback)new StubApi23());
                return;
            }
            if (Build$VERSION.SDK_INT >= 21) {
                this.mCallbackObj = MediaSessionCompatApi21.createCallback((MediaSessionCompatApi21.Callback)new StubApi21());
                return;
            }
            this.mCallbackObj = null;
        }
        
        private void handleMediaPlayPauseKeySingleTapIfPending() {
            boolean b = true;
            if (this.mMediaPlayPauseKeyPending) {
                this.mMediaPlayPauseKeyPending = false;
                this.mCallbackHandler.removeMessages(1);
                final MediaSessionImpl mediaSessionImpl = this.mSessionImpl.get();
                if (mediaSessionImpl != null) {
                    final PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                    long actions;
                    if (playbackState == null) {
                        actions = 0L;
                    }
                    else {
                        actions = playbackState.getActions();
                    }
                    boolean b2;
                    if (playbackState != null && playbackState.getState() == 3) {
                        b2 = true;
                    }
                    else {
                        b2 = false;
                    }
                    final boolean b3 = (0x204L & actions) != 0x0L;
                    if ((0x202L & actions) == 0x0L) {
                        b = false;
                    }
                    if (b2 && b) {
                        this.onPause();
                        return;
                    }
                    if (!b2 && b3) {
                        this.onPlay();
                    }
                }
            }
        }
        
        private void setSessionImpl(final MediaSessionImpl mediaSessionImpl, final Handler handler) {
            this.mSessionImpl = new WeakReference<MediaSessionImpl>(mediaSessionImpl);
            if (this.mCallbackHandler != null) {
                this.mCallbackHandler.removeCallbacksAndMessages((Object)null);
            }
            this.mCallbackHandler = new CallbackHandler(handler.getLooper());
        }
        
        public void onAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        }
        
        public void onAddQueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final int n) {
        }
        
        public void onCommand(final String s, final Bundle bundle, final ResultReceiver resultReceiver) {
        }
        
        public void onCustomAction(final String s, final Bundle bundle) {
        }
        
        public void onFastForward() {
        }
        
        public boolean onMediaButtonEvent(final Intent intent) {
            boolean b = true;
            final MediaSessionImpl mediaSessionImpl = this.mSessionImpl.get();
            if (mediaSessionImpl == null || this.mCallbackHandler == null) {
                b = false;
            }
            else {
                final KeyEvent keyEvent = (KeyEvent)intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
                if (keyEvent == null || keyEvent.getAction() != 0) {
                    return false;
                }
                switch (keyEvent.getKeyCode()) {
                    default: {
                        this.handleMediaPlayPauseKeySingleTapIfPending();
                        return false;
                    }
                    case 79:
                    case 85: {
                        if (keyEvent.getRepeatCount() > 0) {
                            this.handleMediaPlayPauseKeySingleTapIfPending();
                            return true;
                        }
                        if (!this.mMediaPlayPauseKeyPending) {
                            this.mMediaPlayPauseKeyPending = true;
                            this.mCallbackHandler.sendEmptyMessageDelayed(1, (long)ViewConfiguration.getDoubleTapTimeout());
                            return true;
                        }
                        this.mCallbackHandler.removeMessages(1);
                        this.mMediaPlayPauseKeyPending = false;
                        final PlaybackStateCompat playbackState = mediaSessionImpl.getPlaybackState();
                        long actions;
                        if (playbackState == null) {
                            actions = 0L;
                        }
                        else {
                            actions = playbackState.getActions();
                        }
                        if ((0x20L & actions) != 0x0L) {
                            this.onSkipToNext();
                            return true;
                        }
                        break;
                    }
                }
            }
            return b;
        }
        
        public void onPause() {
        }
        
        public void onPlay() {
        }
        
        public void onPlayFromMediaId(final String s, final Bundle bundle) {
        }
        
        public void onPlayFromSearch(final String s, final Bundle bundle) {
        }
        
        public void onPlayFromUri(final Uri uri, final Bundle bundle) {
        }
        
        public void onPrepare() {
        }
        
        public void onPrepareFromMediaId(final String s, final Bundle bundle) {
        }
        
        public void onPrepareFromSearch(final String s, final Bundle bundle) {
        }
        
        public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
        }
        
        public void onRemoveQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
        }
        
        @Deprecated
        public void onRemoveQueueItemAt(final int n) {
        }
        
        public void onRewind() {
        }
        
        public void onSeekTo(final long n) {
        }
        
        public void onSetCaptioningEnabled(final boolean b) {
        }
        
        public void onSetRating(final RatingCompat ratingCompat) {
        }
        
        public void onSetRating(final RatingCompat ratingCompat, final Bundle bundle) {
        }
        
        public void onSetRepeatMode(final int n) {
        }
        
        public void onSetShuffleMode(final int n) {
        }
        
        @Deprecated
        public void onSetShuffleModeEnabled(final boolean b) {
        }
        
        public void onSkipToNext() {
        }
        
        public void onSkipToPrevious() {
        }
        
        public void onSkipToQueueItem(final long n) {
        }
        
        public void onStop() {
        }
        
        private class CallbackHandler extends Handler
        {
            private static final int MSG_MEDIA_PLAY_PAUSE_KEY_DOUBLE_TAP_TIMEOUT = 1;
            
            CallbackHandler(final Looper looper) {
                super(looper);
            }
            
            public void handleMessage(final Message message) {
                if (message.what == 1) {
                    Callback.this.handleMediaPlayPauseKeySingleTapIfPending();
                }
            }
        }
        
        @RequiresApi(21)
        private class StubApi21 implements MediaSessionCompatApi21.Callback
        {
            StubApi21() {
            }
            
            @Override
            public void onCommand(final String s, Bundle bundle, final ResultReceiver resultReceiver) {
                final MediaSessionCompat.QueueItem queueItem = null;
                final IBinder binder = null;
                try {
                    if (s.equals("android.support.v4.media.session.command.GET_EXTRA_BINDER")) {
                        final MediaSessionImplApi21 mediaSessionImplApi21 = (MediaSessionImplApi21)MediaSessionCompat.Callback.this.mSessionImpl.get();
                        if (mediaSessionImplApi21 != null) {
                            bundle = new Bundle();
                            final IMediaSession extraBinder = mediaSessionImplApi21.getSessionToken().getExtraBinder();
                            IBinder binder2;
                            if (extraBinder == null) {
                                binder2 = binder;
                            }
                            else {
                                binder2 = extraBinder.asBinder();
                            }
                            BundleCompat.putBinder(bundle, "android.support.v4.media.session.EXTRA_BINDER", binder2);
                            resultReceiver.send(0, bundle);
                        }
                        return;
                    }
                    else if (s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM")) {
                        bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                        MediaSessionCompat.Callback.this.onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                        return;
                    }
                }
                catch (BadParcelableException ex) {
                    Log.e("MediaSessionCompat", "Could not unparcel the extra data.");
                    return;
                }
                if (s.equals("android.support.v4.media.session.command.ADD_QUEUE_ITEM_AT")) {
                    bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                    MediaSessionCompat.Callback.this.onAddQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"), bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM")) {
                    bundle.setClassLoader(MediaDescriptionCompat.class.getClassLoader());
                    MediaSessionCompat.Callback.this.onRemoveQueueItem((MediaDescriptionCompat)bundle.getParcelable("android.support.v4.media.session.command.ARGUMENT_MEDIA_DESCRIPTION"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.command.REMOVE_QUEUE_ITEM_AT")) {
                    final MediaSessionImplApi21 mediaSessionImplApi22 = (MediaSessionImplApi21)MediaSessionCompat.Callback.this.mSessionImpl.get();
                    if (mediaSessionImplApi22 != null && mediaSessionImplApi22.mQueue != null) {
                        final int int1 = bundle.getInt("android.support.v4.media.session.command.ARGUMENT_INDEX", -1);
                        Object o = queueItem;
                        if (int1 >= 0) {
                            o = queueItem;
                            if (int1 < mediaSessionImplApi22.mQueue.size()) {
                                o = mediaSessionImplApi22.mQueue.get(int1);
                            }
                        }
                        if (o != null) {
                            MediaSessionCompat.Callback.this.onRemoveQueueItem(((MediaSessionCompat.QueueItem)o).getDescription());
                        }
                    }
                }
                else {
                    MediaSessionCompat.Callback.this.onCommand(s, bundle, resultReceiver);
                }
            }
            
            @Override
            public void onCustomAction(String s, Bundle bundle) {
                if (s.equals("android.support.v4.media.session.action.PLAY_FROM_URI")) {
                    final Uri uri = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    bundle = (Bundle)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    MediaSessionCompat.Callback.this.onPlayFromUri(uri, bundle);
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.PREPARE")) {
                    MediaSessionCompat.Callback.this.onPrepare();
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_MEDIA_ID")) {
                    s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_MEDIA_ID");
                    bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    MediaSessionCompat.Callback.this.onPrepareFromMediaId(s, bundle);
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_SEARCH")) {
                    s = bundle.getString("android.support.v4.media.session.action.ARGUMENT_QUERY");
                    bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    MediaSessionCompat.Callback.this.onPrepareFromSearch(s, bundle);
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.PREPARE_FROM_URI")) {
                    final Uri uri2 = (Uri)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_URI");
                    bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    MediaSessionCompat.Callback.this.onPrepareFromUri(uri2, bundle);
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.SET_CAPTIONING_ENABLED")) {
                    MediaSessionCompat.Callback.this.onSetCaptioningEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_CAPTIONING_ENABLED"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.SET_REPEAT_MODE")) {
                    MediaSessionCompat.Callback.this.onSetRepeatMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_REPEAT_MODE"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE_ENABLED")) {
                    MediaSessionCompat.Callback.this.onSetShuffleModeEnabled(bundle.getBoolean("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE_ENABLED"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.SET_SHUFFLE_MODE")) {
                    MediaSessionCompat.Callback.this.onSetShuffleMode(bundle.getInt("android.support.v4.media.session.action.ARGUMENT_SHUFFLE_MODE"));
                    return;
                }
                if (s.equals("android.support.v4.media.session.action.SET_RATING")) {
                    bundle.setClassLoader(RatingCompat.class.getClassLoader());
                    final RatingCompat ratingCompat = (RatingCompat)bundle.getParcelable("android.support.v4.media.session.action.ARGUMENT_RATING");
                    bundle = bundle.getBundle("android.support.v4.media.session.action.ARGUMENT_EXTRAS");
                    MediaSessionCompat.Callback.this.onSetRating(ratingCompat, bundle);
                    return;
                }
                MediaSessionCompat.Callback.this.onCustomAction(s, bundle);
            }
            
            @Override
            public void onFastForward() {
                MediaSessionCompat.Callback.this.onFastForward();
            }
            
            @Override
            public boolean onMediaButtonEvent(final Intent intent) {
                return MediaSessionCompat.Callback.this.onMediaButtonEvent(intent);
            }
            
            @Override
            public void onPause() {
                MediaSessionCompat.Callback.this.onPause();
            }
            
            @Override
            public void onPlay() {
                MediaSessionCompat.Callback.this.onPlay();
            }
            
            @Override
            public void onPlayFromMediaId(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromMediaId(s, bundle);
            }
            
            @Override
            public void onPlayFromSearch(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromSearch(s, bundle);
            }
            
            @Override
            public void onRewind() {
                MediaSessionCompat.Callback.this.onRewind();
            }
            
            @Override
            public void onSeekTo(final long n) {
                MediaSessionCompat.Callback.this.onSeekTo(n);
            }
            
            @Override
            public void onSetRating(final Object o) {
                MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(o));
            }
            
            @Override
            public void onSetRating(final Object o, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onSetRating(RatingCompat.fromRating(o), bundle);
            }
            
            @Override
            public void onSkipToNext() {
                MediaSessionCompat.Callback.this.onSkipToNext();
            }
            
            @Override
            public void onSkipToPrevious() {
                MediaSessionCompat.Callback.this.onSkipToPrevious();
            }
            
            @Override
            public void onSkipToQueueItem(final long n) {
                MediaSessionCompat.Callback.this.onSkipToQueueItem(n);
            }
            
            @Override
            public void onStop() {
                MediaSessionCompat.Callback.this.onStop();
            }
        }
        
        @RequiresApi(23)
        private class StubApi23 extends StubApi21 implements MediaSessionCompatApi23.Callback
        {
            StubApi23() {
            }
            
            @Override
            public void onPlayFromUri(final Uri uri, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPlayFromUri(uri, bundle);
            }
        }
        
        @RequiresApi(24)
        private class StubApi24 extends StubApi23 implements MediaSessionCompatApi24.Callback
        {
            StubApi24() {
            }
            
            @Override
            public void onPrepare() {
                MediaSessionCompat.Callback.this.onPrepare();
            }
            
            @Override
            public void onPrepareFromMediaId(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromMediaId(s, bundle);
            }
            
            @Override
            public void onPrepareFromSearch(final String s, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromSearch(s, bundle);
            }
            
            @Override
            public void onPrepareFromUri(final Uri uri, final Bundle bundle) {
                MediaSessionCompat.Callback.this.onPrepareFromUri(uri, bundle);
            }
        }
    }
    
    interface MediaSessionImpl
    {
        String getCallingPackage();
        
        Object getMediaSession();
        
        PlaybackStateCompat getPlaybackState();
        
        Object getRemoteControlClient();
        
        Token getSessionToken();
        
        boolean isActive();
        
        void release();
        
        void sendSessionEvent(final String p0, final Bundle p1);
        
        void setActive(final boolean p0);
        
        void setCallback(final Callback p0, final Handler p1);
        
        void setCaptioningEnabled(final boolean p0);
        
        void setExtras(final Bundle p0);
        
        void setFlags(final int p0);
        
        void setMediaButtonReceiver(final PendingIntent p0);
        
        void setMetadata(final MediaMetadataCompat p0);
        
        void setPlaybackState(final PlaybackStateCompat p0);
        
        void setPlaybackToLocal(final int p0);
        
        void setPlaybackToRemote(final VolumeProviderCompat p0);
        
        void setQueue(final List<QueueItem> p0);
        
        void setQueueTitle(final CharSequence p0);
        
        void setRatingType(final int p0);
        
        void setRepeatMode(final int p0);
        
        void setSessionActivity(final PendingIntent p0);
        
        void setShuffleMode(final int p0);
        
        void setShuffleModeEnabled(final boolean p0);
    }
    
    @RequiresApi(18)
    static class MediaSessionImplApi18 extends MediaSessionImplBase
    {
        private static boolean sIsMbrPendingIntentSupported;
        
        static {
            MediaSessionImplApi18.sIsMbrPendingIntentSupported = true;
        }
        
        MediaSessionImplApi18(final Context context, final String s, final ComponentName componentName, final PendingIntent pendingIntent) {
            super(context, s, componentName, pendingIntent);
        }
        
        @Override
        int getRccTransportControlFlagsFromActions(final long n) {
            int rccTransportControlFlagsFromActions = super.getRccTransportControlFlagsFromActions(n);
            if ((0x100L & n) != 0x0L) {
                rccTransportControlFlagsFromActions |= 0x100;
            }
            return rccTransportControlFlagsFromActions;
        }
        
        @Override
        void registerMediaButtonEventReceiver(final PendingIntent pendingIntent, final ComponentName componentName) {
            while (true) {
                if (!MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                    break Label_0014;
                }
                try {
                    this.mAudioManager.registerMediaButtonEventReceiver(pendingIntent);
                    if (!MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                        super.registerMediaButtonEventReceiver(pendingIntent, componentName);
                    }
                }
                catch (NullPointerException ex) {
                    Log.w("MediaSessionCompat", "Unable to register media button event receiver with PendingIntent, falling back to ComponentName.");
                    MediaSessionImplApi18.sIsMbrPendingIntentSupported = false;
                    continue;
                }
                break;
            }
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.mRcc.setPlaybackPositionUpdateListener((RemoteControlClient$OnPlaybackPositionUpdateListener)null);
                return;
            }
            this.mRcc.setPlaybackPositionUpdateListener((RemoteControlClient$OnPlaybackPositionUpdateListener)new RemoteControlClient$OnPlaybackPositionUpdateListener() {
                public void onPlaybackPositionUpdate(final long n) {
                    ((MediaSessionImplBase)MediaSessionImplApi18.this).postToHandler(18, n);
                }
            });
        }
        
        @Override
        void setRccState(final PlaybackStateCompat playbackStateCompat) {
            final long position = playbackStateCompat.getPosition();
            final float playbackSpeed = playbackStateCompat.getPlaybackSpeed();
            final long lastPositionUpdateTime = playbackStateCompat.getLastPositionUpdateTime();
            final long elapsedRealtime = SystemClock.elapsedRealtime();
            long n = position;
            if (playbackStateCompat.getState() == 3) {
                n = position;
                if (position > 0L) {
                    long n2 = 0L;
                    if (lastPositionUpdateTime > 0L) {
                        final long n3 = n2 = elapsedRealtime - lastPositionUpdateTime;
                        if (playbackSpeed > 0.0f) {
                            n2 = n3;
                            if (playbackSpeed != 1.0f) {
                                n2 = (long)(n3 * playbackSpeed);
                            }
                        }
                    }
                    n = position + n2;
                }
            }
            this.mRcc.setPlaybackState(((MediaSessionImplBase)this).getRccStateFromState(playbackStateCompat.getState()), n, playbackSpeed);
        }
        
        @Override
        void unregisterMediaButtonEventReceiver(final PendingIntent pendingIntent, final ComponentName componentName) {
            if (MediaSessionImplApi18.sIsMbrPendingIntentSupported) {
                this.mAudioManager.unregisterMediaButtonEventReceiver(pendingIntent);
                return;
            }
            super.unregisterMediaButtonEventReceiver(pendingIntent, componentName);
        }
    }
    
    @RequiresApi(19)
    static class MediaSessionImplApi19 extends MediaSessionImplApi18
    {
        MediaSessionImplApi19(final Context context, final String s, final ComponentName componentName, final PendingIntent pendingIntent) {
            super(context, s, componentName, pendingIntent);
        }
        
        @Override
        RemoteControlClient$MetadataEditor buildRccMetadata(final Bundle bundle) {
            final RemoteControlClient$MetadataEditor buildRccMetadata = super.buildRccMetadata(bundle);
            long actions;
            if (this.mState == null) {
                actions = 0L;
            }
            else {
                actions = this.mState.getActions();
            }
            if ((0x80L & actions) != 0x0L) {
                buildRccMetadata.addEditableKey(268435457);
            }
            if (bundle != null) {
                if (bundle.containsKey("android.media.metadata.YEAR")) {
                    buildRccMetadata.putLong(8, bundle.getLong("android.media.metadata.YEAR"));
                }
                if (bundle.containsKey("android.media.metadata.RATING")) {
                    ((MediaMetadataEditor)buildRccMetadata).putObject(101, (Object)bundle.getParcelable("android.media.metadata.RATING"));
                }
                if (bundle.containsKey("android.media.metadata.USER_RATING")) {
                    ((MediaMetadataEditor)buildRccMetadata).putObject(268435457, (Object)bundle.getParcelable("android.media.metadata.USER_RATING"));
                    return buildRccMetadata;
                }
            }
            return buildRccMetadata;
        }
        
        @Override
        int getRccTransportControlFlagsFromActions(final long n) {
            int rccTransportControlFlagsFromActions = super.getRccTransportControlFlagsFromActions(n);
            if ((0x80L & n) != 0x0L) {
                rccTransportControlFlagsFromActions |= 0x200;
            }
            return rccTransportControlFlagsFromActions;
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
            super.setCallback(callback, handler);
            if (callback == null) {
                this.mRcc.setMetadataUpdateListener((RemoteControlClient$OnMetadataUpdateListener)null);
                return;
            }
            this.mRcc.setMetadataUpdateListener((RemoteControlClient$OnMetadataUpdateListener)new RemoteControlClient$OnMetadataUpdateListener() {
                public void onMetadataUpdate(final int n, final Object o) {
                    if (n == 268435457 && o instanceof Rating) {
                        ((MediaSessionImplBase)MediaSessionImplApi19.this).postToHandler(19, RatingCompat.fromRating(o));
                    }
                }
            });
        }
    }
    
    @RequiresApi(21)
    static class MediaSessionImplApi21 implements MediaSessionImpl
    {
        boolean mCaptioningEnabled;
        private boolean mDestroyed;
        private final RemoteCallbackList<IMediaControllerCallback> mExtraControllerCallbacks;
        private MediaMetadataCompat mMetadata;
        private PlaybackStateCompat mPlaybackState;
        private List<QueueItem> mQueue;
        int mRatingType;
        int mRepeatMode;
        private final Object mSessionObj;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        private final Token mToken;
        
        public MediaSessionImplApi21(final Context context, final String s) {
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
            this.mSessionObj = MediaSessionCompatApi21.createSession(context, s);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession());
        }
        
        public MediaSessionImplApi21(final Object o) {
            this.mDestroyed = false;
            this.mExtraControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
            this.mSessionObj = MediaSessionCompatApi21.verifySession(o);
            this.mToken = new Token(MediaSessionCompatApi21.getSessionToken(this.mSessionObj), new ExtraSession());
        }
        
        @Override
        public String getCallingPackage() {
            if (Build$VERSION.SDK_INT < 24) {
                return null;
            }
            return MediaSessionCompatApi24.getCallingPackage(this.mSessionObj);
        }
        
        @Override
        public Object getMediaSession() {
            return this.mSessionObj;
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            return this.mPlaybackState;
        }
        
        @Override
        public Object getRemoteControlClient() {
            return null;
        }
        
        @Override
        public Token getSessionToken() {
            return this.mToken;
        }
        
        @Override
        public boolean isActive() {
            return MediaSessionCompatApi21.isActive(this.mSessionObj);
        }
        
        @Override
        public void release() {
            this.mDestroyed = true;
            MediaSessionCompatApi21.release(this.mSessionObj);
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
            Label_0058: {
                if (Build$VERSION.SDK_INT >= 23) {
                    break Label_0058;
                }
                int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
            Label_0044_Outer:
                while (true) {
                    Label_0051: {
                        if (n < 0) {
                            break Label_0051;
                        }
                        final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                        while (true) {
                            try {
                                mediaControllerCallback.onEvent(s, bundle);
                                --n;
                                continue Label_0044_Outer;
                                this.mExtraControllerCallbacks.finishBroadcast();
                                MediaSessionCompatApi21.sendSessionEvent(this.mSessionObj, s, bundle);
                            }
                            catch (RemoteException ex) {
                                continue;
                            }
                            break;
                        }
                    }
                    break;
                }
            }
        }
        
        @Override
        public void setActive(final boolean b) {
            MediaSessionCompatApi21.setActive(this.mSessionObj, b);
        }
        
        @Override
        public void setCallback(final Callback callback, final Handler handler) {
            final Object mSessionObj = this.mSessionObj;
            Object mCallbackObj;
            if (callback == null) {
                mCallbackObj = null;
            }
            else {
                mCallbackObj = callback.mCallbackObj;
            }
            MediaSessionCompatApi21.setCallback(mSessionObj, mCallbackObj, handler);
            if (callback != null) {
                callback.setSessionImpl(this, handler);
            }
        }
        
        @Override
        public void setCaptioningEnabled(final boolean mCaptioningEnabled) {
            if (this.mCaptioningEnabled == mCaptioningEnabled) {
                return;
            }
            this.mCaptioningEnabled = mCaptioningEnabled;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0046_Outer:
            while (true) {
                Label_0053: {
                    if (n < 0) {
                        break Label_0053;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onCaptioningEnabledChanged(mCaptioningEnabled);
                            --n;
                            continue Label_0046_Outer;
                            this.mExtraControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
        
        @Override
        public void setExtras(final Bundle bundle) {
            MediaSessionCompatApi21.setExtras(this.mSessionObj, bundle);
        }
        
        @Override
        public void setFlags(final int n) {
            MediaSessionCompatApi21.setFlags(this.mSessionObj, n);
        }
        
        @Override
        public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setMediaButtonReceiver(this.mSessionObj, pendingIntent);
        }
        
        @Override
        public void setMetadata(final MediaMetadataCompat mMetadata) {
            this.mMetadata = mMetadata;
            final Object mSessionObj = this.mSessionObj;
            Object mediaMetadata;
            if (mMetadata == null) {
                mediaMetadata = null;
            }
            else {
                mediaMetadata = mMetadata.getMediaMetadata();
            }
            MediaSessionCompatApi21.setMetadata(mSessionObj, mediaMetadata);
        }
        
        @Override
        public void setPlaybackState(PlaybackStateCompat playbackState) {
            this.mPlaybackState = playbackState;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0038_Outer:
            while (true) {
                Label_0045: {
                    if (n < 0) {
                        break Label_0045;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onPlaybackStateChanged(playbackState);
                            --n;
                            continue Label_0038_Outer;
                            while (true) {
                                final Object mSessionObj;
                                MediaSessionCompatApi21.setPlaybackState(mSessionObj, playbackState);
                                return;
                                this.mExtraControllerCallbacks.finishBroadcast();
                                mSessionObj = this.mSessionObj;
                                Block_4: {
                                    break Block_4;
                                    Label_0069: {
                                        playbackState = (PlaybackStateCompat)playbackState.getPlaybackState();
                                    }
                                    continue;
                                }
                                playbackState = null;
                                continue;
                            }
                        }
                        // iftrue(Label_0069:, playbackState != null)
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
            MediaSessionCompatApi21.setPlaybackToLocal(this.mSessionObj, n);
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat volumeProviderCompat) {
            MediaSessionCompatApi21.setPlaybackToRemote(this.mSessionObj, volumeProviderCompat.getVolumeProvider());
        }
        
        @Override
        public void setQueue(final List<QueueItem> mQueue) {
            this.mQueue = mQueue;
            List<Object> list = null;
            if (mQueue != null) {
                final ArrayList<Object> list2 = new ArrayList<Object>();
                final Iterator<QueueItem> iterator = mQueue.iterator();
                while (true) {
                    list = list2;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    list2.add(iterator.next().getQueueItem());
                }
            }
            MediaSessionCompatApi21.setQueue(this.mSessionObj, list);
        }
        
        @Override
        public void setQueueTitle(final CharSequence charSequence) {
            MediaSessionCompatApi21.setQueueTitle(this.mSessionObj, charSequence);
        }
        
        @Override
        public void setRatingType(final int mRatingType) {
            if (Build$VERSION.SDK_INT < 22) {
                this.mRatingType = mRatingType;
                return;
            }
            MediaSessionCompatApi22.setRatingType(this.mSessionObj, mRatingType);
        }
        
        @Override
        public void setRepeatMode(final int mRepeatMode) {
            if (this.mRepeatMode == mRepeatMode) {
                return;
            }
            this.mRepeatMode = mRepeatMode;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0046_Outer:
            while (true) {
                Label_0053: {
                    if (n < 0) {
                        break Label_0053;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onRepeatModeChanged(mRepeatMode);
                            --n;
                            continue Label_0046_Outer;
                            this.mExtraControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
        
        @Override
        public void setSessionActivity(final PendingIntent pendingIntent) {
            MediaSessionCompatApi21.setSessionActivity(this.mSessionObj, pendingIntent);
        }
        
        @Override
        public void setShuffleMode(final int mShuffleMode) {
            if (this.mShuffleMode == mShuffleMode) {
                return;
            }
            this.mShuffleMode = mShuffleMode;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0046_Outer:
            while (true) {
                Label_0053: {
                    if (n < 0) {
                        break Label_0053;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onShuffleModeChanged(mShuffleMode);
                            --n;
                            continue Label_0046_Outer;
                            this.mExtraControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
        
        @Override
        public void setShuffleModeEnabled(final boolean mShuffleModeEnabled) {
            if (this.mShuffleModeEnabled == mShuffleModeEnabled) {
                return;
            }
            this.mShuffleModeEnabled = mShuffleModeEnabled;
            int n = this.mExtraControllerCallbacks.beginBroadcast() - 1;
        Label_0046_Outer:
            while (true) {
                Label_0053: {
                    if (n < 0) {
                        break Label_0053;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mExtraControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onShuffleModeChangedDeprecated(mShuffleModeEnabled);
                            --n;
                            continue Label_0046_Outer;
                            this.mExtraControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
                break;
            }
        }
        
        class ExtraSession extends Stub
        {
            public void addQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }
            
            public void addQueueItemAt(final MediaDescriptionCompat mediaDescriptionCompat, final int n) {
                throw new AssertionError();
            }
            
            public void adjustVolume(final int n, final int n2, final String s) {
                throw new AssertionError();
            }
            
            public void fastForward() throws RemoteException {
                throw new AssertionError();
            }
            
            public Bundle getExtras() {
                throw new AssertionError();
            }
            
            public long getFlags() {
                throw new AssertionError();
            }
            
            public PendingIntent getLaunchPendingIntent() {
                throw new AssertionError();
            }
            
            public MediaMetadataCompat getMetadata() {
                throw new AssertionError();
            }
            
            public String getPackageName() {
                throw new AssertionError();
            }
            
            public PlaybackStateCompat getPlaybackState() {
                return getStateWithUpdatedPosition(MediaSessionImplApi21.this.mPlaybackState, MediaSessionImplApi21.this.mMetadata);
            }
            
            public List<QueueItem> getQueue() {
                return null;
            }
            
            public CharSequence getQueueTitle() {
                throw new AssertionError();
            }
            
            public int getRatingType() {
                return MediaSessionImplApi21.this.mRatingType;
            }
            
            public int getRepeatMode() {
                return MediaSessionImplApi21.this.mRepeatMode;
            }
            
            public int getShuffleMode() {
                return MediaSessionImplApi21.this.mShuffleMode;
            }
            
            public String getTag() {
                throw new AssertionError();
            }
            
            public ParcelableVolumeInfo getVolumeAttributes() {
                throw new AssertionError();
            }
            
            public boolean isCaptioningEnabled() {
                return MediaSessionImplApi21.this.mCaptioningEnabled;
            }
            
            public boolean isShuffleModeEnabledDeprecated() {
                return MediaSessionImplApi21.this.mShuffleModeEnabled;
            }
            
            public boolean isTransportControlEnabled() {
                throw new AssertionError();
            }
            
            public void next() throws RemoteException {
                throw new AssertionError();
            }
            
            public void pause() throws RemoteException {
                throw new AssertionError();
            }
            
            public void play() throws RemoteException {
                throw new AssertionError();
            }
            
            public void playFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void playFromSearch(final String s, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void playFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void prepare() throws RemoteException {
                throw new AssertionError();
            }
            
            public void prepareFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void prepareFromSearch(final String s, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void prepareFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void previous() throws RemoteException {
                throw new AssertionError();
            }
            
            public void rate(final RatingCompat ratingCompat) throws RemoteException {
                throw new AssertionError();
            }
            
            public void rateWithExtras(final RatingCompat ratingCompat, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                if (!MediaSessionImplApi21.this.mDestroyed) {
                    MediaSessionImplApi21.this.mExtraControllerCallbacks.register((IInterface)mediaControllerCallback);
                }
            }
            
            public void removeQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
                throw new AssertionError();
            }
            
            public void removeQueueItemAt(final int n) {
                throw new AssertionError();
            }
            
            public void rewind() throws RemoteException {
                throw new AssertionError();
            }
            
            public void seekTo(final long n) throws RemoteException {
                throw new AssertionError();
            }
            
            public void sendCommand(final String s, final Bundle bundle, final ResultReceiverWrapper resultReceiverWrapper) {
                throw new AssertionError();
            }
            
            public void sendCustomAction(final String s, final Bundle bundle) throws RemoteException {
                throw new AssertionError();
            }
            
            public boolean sendMediaButton(final KeyEvent keyEvent) {
                throw new AssertionError();
            }
            
            public void setCaptioningEnabled(final boolean b) throws RemoteException {
                throw new AssertionError();
            }
            
            public void setRepeatMode(final int n) throws RemoteException {
                throw new AssertionError();
            }
            
            public void setShuffleMode(final int n) throws RemoteException {
                throw new AssertionError();
            }
            
            public void setShuffleModeEnabledDeprecated(final boolean b) throws RemoteException {
                throw new AssertionError();
            }
            
            public void setVolumeTo(final int n, final int n2, final String s) {
                throw new AssertionError();
            }
            
            public void skipToQueueItem(final long n) {
                throw new AssertionError();
            }
            
            public void stop() throws RemoteException {
                throw new AssertionError();
            }
            
            public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                MediaSessionImplApi21.this.mExtraControllerCallbacks.unregister((IInterface)mediaControllerCallback);
            }
        }
    }
    
    static class MediaSessionImplBase implements MediaSessionImpl
    {
        static final int RCC_PLAYSTATE_NONE = 0;
        final AudioManager mAudioManager;
        volatile Callback mCallback;
        boolean mCaptioningEnabled;
        private final Context mContext;
        final RemoteCallbackList<IMediaControllerCallback> mControllerCallbacks;
        boolean mDestroyed;
        Bundle mExtras;
        int mFlags;
        private MessageHandler mHandler;
        boolean mIsActive;
        private boolean mIsMbrRegistered;
        private boolean mIsRccRegistered;
        int mLocalStream;
        final Object mLock;
        private final ComponentName mMediaButtonReceiverComponentName;
        private final PendingIntent mMediaButtonReceiverIntent;
        MediaMetadataCompat mMetadata;
        final String mPackageName;
        List<QueueItem> mQueue;
        CharSequence mQueueTitle;
        int mRatingType;
        final RemoteControlClient mRcc;
        int mRepeatMode;
        PendingIntent mSessionActivity;
        int mShuffleMode;
        boolean mShuffleModeEnabled;
        PlaybackStateCompat mState;
        private final MediaSessionStub mStub;
        final String mTag;
        private final Token mToken;
        private VolumeProviderCompat.Callback mVolumeCallback;
        VolumeProviderCompat mVolumeProvider;
        int mVolumeType;
        
        public MediaSessionImplBase(final Context mContext, final String mTag, final ComponentName mMediaButtonReceiverComponentName, final PendingIntent mMediaButtonReceiverIntent) {
            this.mLock = new Object();
            this.mControllerCallbacks = (RemoteCallbackList<IMediaControllerCallback>)new RemoteCallbackList();
            this.mDestroyed = false;
            this.mIsActive = false;
            this.mIsMbrRegistered = false;
            this.mIsRccRegistered = false;
            this.mVolumeCallback = new VolumeProviderCompat.Callback() {
                @Override
                public void onVolumeChanged(final VolumeProviderCompat volumeProviderCompat) {
                    if (MediaSessionImplBase.this.mVolumeProvider != volumeProviderCompat) {
                        return;
                    }
                    MediaSessionImplBase.this.sendVolumeInfoChanged(new ParcelableVolumeInfo(MediaSessionImplBase.this.mVolumeType, MediaSessionImplBase.this.mLocalStream, volumeProviderCompat.getVolumeControl(), volumeProviderCompat.getMaxVolume(), volumeProviderCompat.getCurrentVolume()));
                }
            };
            if (mMediaButtonReceiverComponentName == null) {
                throw new IllegalArgumentException("MediaButtonReceiver component may not be null.");
            }
            this.mContext = mContext;
            this.mPackageName = mContext.getPackageName();
            this.mAudioManager = (AudioManager)mContext.getSystemService("audio");
            this.mTag = mTag;
            this.mMediaButtonReceiverComponentName = mMediaButtonReceiverComponentName;
            this.mMediaButtonReceiverIntent = mMediaButtonReceiverIntent;
            this.mStub = new MediaSessionStub();
            this.mToken = new Token(this.mStub);
            this.mRatingType = 0;
            this.mVolumeType = 1;
            this.mLocalStream = 3;
            this.mRcc = new RemoteControlClient(mMediaButtonReceiverIntent);
        }
        
        private void sendCaptioningEnabled(final boolean b) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onCaptioningEnabledChanged(b);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendEvent(final String s, final Bundle bundle) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0036_Outer:
            while (true) {
                Label_0043: {
                    if (n < 0) {
                        break Label_0043;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onEvent(s, bundle);
                            --n;
                            continue Label_0036_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendExtras(final Bundle bundle) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onExtrasChanged(bundle);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendMetadata(final MediaMetadataCompat mediaMetadataCompat) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onMetadataChanged(mediaMetadataCompat);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendQueue(final List<QueueItem> list) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onQueueChanged(list);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendQueueTitle(final CharSequence charSequence) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onQueueTitleChanged(charSequence);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendRepeatMode(final int n) {
            int n2 = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n2 < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n2);
                    while (true) {
                        try {
                            mediaControllerCallback.onRepeatModeChanged(n);
                            --n2;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendSessionDestroyed() {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0032_Outer:
            while (true) {
                Label_0039: {
                    if (n < 0) {
                        break Label_0039;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onSessionDestroyed();
                            --n;
                            continue Label_0032_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                            this.mControllerCallbacks.kill();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendShuffleMode(final int n) {
            int n2 = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n2 < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n2);
                    while (true) {
                        try {
                            mediaControllerCallback.onShuffleModeChanged(n);
                            --n2;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendShuffleModeEnabled(final boolean b) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onShuffleModeChangedDeprecated(b);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        private void sendState(final PlaybackStateCompat playbackStateCompat) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onPlaybackStateChanged(playbackStateCompat);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        void adjustVolume(final int n, final int n2) {
            if (this.mVolumeType == 2) {
                if (this.mVolumeProvider != null) {
                    this.mVolumeProvider.onAdjustVolume(n);
                }
                return;
            }
            this.mAudioManager.adjustStreamVolume(this.mLocalStream, n, n2);
        }
        
        RemoteControlClient$MetadataEditor buildRccMetadata(final Bundle bundle) {
            final RemoteControlClient$MetadataEditor editMetadata = this.mRcc.editMetadata(true);
            if (bundle != null) {
                if (bundle.containsKey("android.media.metadata.ART")) {
                    final Bitmap bitmap = (Bitmap)bundle.getParcelable("android.media.metadata.ART");
                    Bitmap copy;
                    if ((copy = bitmap) != null) {
                        copy = bitmap.copy(bitmap.getConfig(), false);
                    }
                    editMetadata.putBitmap(100, copy);
                }
                else if (bundle.containsKey("android.media.metadata.ALBUM_ART")) {
                    final Bitmap bitmap2 = (Bitmap)bundle.getParcelable("android.media.metadata.ALBUM_ART");
                    Bitmap copy2;
                    if ((copy2 = bitmap2) != null) {
                        copy2 = bitmap2.copy(bitmap2.getConfig(), false);
                    }
                    editMetadata.putBitmap(100, copy2);
                }
                if (bundle.containsKey("android.media.metadata.ALBUM")) {
                    editMetadata.putString(1, bundle.getString("android.media.metadata.ALBUM"));
                }
                if (bundle.containsKey("android.media.metadata.ALBUM_ARTIST")) {
                    editMetadata.putString(13, bundle.getString("android.media.metadata.ALBUM_ARTIST"));
                }
                if (bundle.containsKey("android.media.metadata.ARTIST")) {
                    editMetadata.putString(2, bundle.getString("android.media.metadata.ARTIST"));
                }
                if (bundle.containsKey("android.media.metadata.AUTHOR")) {
                    editMetadata.putString(3, bundle.getString("android.media.metadata.AUTHOR"));
                }
                if (bundle.containsKey("android.media.metadata.COMPILATION")) {
                    editMetadata.putString(15, bundle.getString("android.media.metadata.COMPILATION"));
                }
                if (bundle.containsKey("android.media.metadata.COMPOSER")) {
                    editMetadata.putString(4, bundle.getString("android.media.metadata.COMPOSER"));
                }
                if (bundle.containsKey("android.media.metadata.DATE")) {
                    editMetadata.putString(5, bundle.getString("android.media.metadata.DATE"));
                }
                if (bundle.containsKey("android.media.metadata.DISC_NUMBER")) {
                    editMetadata.putLong(14, bundle.getLong("android.media.metadata.DISC_NUMBER"));
                }
                if (bundle.containsKey("android.media.metadata.DURATION")) {
                    editMetadata.putLong(9, bundle.getLong("android.media.metadata.DURATION"));
                }
                if (bundle.containsKey("android.media.metadata.GENRE")) {
                    editMetadata.putString(6, bundle.getString("android.media.metadata.GENRE"));
                }
                if (bundle.containsKey("android.media.metadata.TITLE")) {
                    editMetadata.putString(7, bundle.getString("android.media.metadata.TITLE"));
                }
                if (bundle.containsKey("android.media.metadata.TRACK_NUMBER")) {
                    editMetadata.putLong(0, bundle.getLong("android.media.metadata.TRACK_NUMBER"));
                }
                if (bundle.containsKey("android.media.metadata.WRITER")) {
                    editMetadata.putString(11, bundle.getString("android.media.metadata.WRITER"));
                    return editMetadata;
                }
            }
            return editMetadata;
        }
        
        @Override
        public String getCallingPackage() {
            return null;
        }
        
        @Override
        public Object getMediaSession() {
            return null;
        }
        
        @Override
        public PlaybackStateCompat getPlaybackState() {
            synchronized (this.mLock) {
                return this.mState;
            }
        }
        
        int getRccStateFromState(final int n) {
            switch (n) {
                default: {
                    return -1;
                }
                case 6:
                case 8: {
                    return 8;
                }
                case 7: {
                    return 9;
                }
                case 4: {
                    return 4;
                }
                case 0: {
                    return 0;
                }
                case 2: {
                    return 2;
                }
                case 3: {
                    return 3;
                }
                case 5: {
                    return 5;
                }
                case 9: {
                    return 7;
                }
                case 10:
                case 11: {
                    return 6;
                }
                case 1: {
                    return 1;
                }
            }
        }
        
        int getRccTransportControlFlagsFromActions(final long n) {
            int n2 = 0;
            if ((0x1L & n) != 0x0L) {
                n2 = (0x0 | 0x20);
            }
            int n3 = n2;
            if ((0x2L & n) != 0x0L) {
                n3 = (n2 | 0x10);
            }
            int n4 = n3;
            if ((0x4L & n) != 0x0L) {
                n4 = (n3 | 0x4);
            }
            int n5 = n4;
            if ((0x8L & n) != 0x0L) {
                n5 = (n4 | 0x2);
            }
            int n6 = n5;
            if ((0x10L & n) != 0x0L) {
                n6 = (n5 | 0x1);
            }
            int n7 = n6;
            if ((0x20L & n) != 0x0L) {
                n7 = (n6 | 0x80);
            }
            int n8 = n7;
            if ((0x40L & n) != 0x0L) {
                n8 = (n7 | 0x40);
            }
            int n9 = n8;
            if ((0x200L & n) != 0x0L) {
                n9 = (n8 | 0x8);
            }
            return n9;
        }
        
        @Override
        public Object getRemoteControlClient() {
            return null;
        }
        
        @Override
        public Token getSessionToken() {
            return this.mToken;
        }
        
        @Override
        public boolean isActive() {
            return this.mIsActive;
        }
        
        void postToHandler(final int n) {
            this.postToHandler(n, null);
        }
        
        void postToHandler(final int n, final int n2) {
            this.postToHandler(n, null, n2);
        }
        
        void postToHandler(final int n, final Object o) {
            this.postToHandler(n, o, null);
        }
        
        void postToHandler(final int n, final Object o, final int n2) {
            synchronized (this.mLock) {
                if (this.mHandler != null) {
                    this.mHandler.post(n, o, n2);
                }
            }
        }
        
        void postToHandler(final int n, final Object o, final Bundle bundle) {
            synchronized (this.mLock) {
                if (this.mHandler != null) {
                    this.mHandler.post(n, o, bundle);
                }
            }
        }
        
        void registerMediaButtonEventReceiver(final PendingIntent pendingIntent, final ComponentName componentName) {
            this.mAudioManager.registerMediaButtonEventReceiver(componentName);
        }
        
        @Override
        public void release() {
            this.mIsActive = false;
            this.mDestroyed = true;
            this.update();
            this.sendSessionDestroyed();
        }
        
        @Override
        public void sendSessionEvent(final String s, final Bundle bundle) {
            this.sendEvent(s, bundle);
        }
        
        void sendVolumeInfoChanged(final ParcelableVolumeInfo parcelableVolumeInfo) {
            int n = this.mControllerCallbacks.beginBroadcast() - 1;
        Label_0033_Outer:
            while (true) {
                Label_0040: {
                    if (n < 0) {
                        break Label_0040;
                    }
                    final IMediaControllerCallback mediaControllerCallback = (IMediaControllerCallback)this.mControllerCallbacks.getBroadcastItem(n);
                    while (true) {
                        try {
                            mediaControllerCallback.onVolumeInfoChanged(parcelableVolumeInfo);
                            --n;
                            continue Label_0033_Outer;
                            this.mControllerCallbacks.finishBroadcast();
                        }
                        catch (RemoteException ex) {
                            continue;
                        }
                        break;
                    }
                }
            }
        }
        
        @Override
        public void setActive(final boolean mIsActive) {
            if (mIsActive != this.mIsActive) {
                this.mIsActive = mIsActive;
                if (this.update()) {
                    this.setMetadata(this.mMetadata);
                    this.setPlaybackState(this.mState);
                }
            }
        }
        
        @Override
        public void setCallback(final Callback mCallback, final Handler handler) {
            this.mCallback = mCallback;
            if (mCallback != null) {
                Handler handler2;
                if ((handler2 = handler) == null) {
                    handler2 = new Handler();
                }
                synchronized (this.mLock) {
                    if (this.mHandler != null) {
                        this.mHandler.removeCallbacksAndMessages((Object)null);
                    }
                    this.mHandler = new MessageHandler(handler2.getLooper());
                    this.mCallback.setSessionImpl(this, handler2);
                }
            }
        }
        
        @Override
        public void setCaptioningEnabled(final boolean mCaptioningEnabled) {
            if (this.mCaptioningEnabled != mCaptioningEnabled) {
                this.sendCaptioningEnabled(this.mCaptioningEnabled = mCaptioningEnabled);
            }
        }
        
        @Override
        public void setExtras(final Bundle mExtras) {
            this.sendExtras(this.mExtras = mExtras);
        }
        
        @Override
        public void setFlags(final int mFlags) {
            synchronized (this.mLock) {
                this.mFlags = mFlags;
                // monitorexit(this.mLock)
                this.update();
            }
        }
        
        @Override
        public void setMediaButtonReceiver(final PendingIntent pendingIntent) {
        }
        
        @Override
        public void setMetadata(final MediaMetadataCompat mediaMetadataCompat) {
            MediaMetadataCompat build = mediaMetadataCompat;
            if (mediaMetadataCompat != null) {
                build = new MediaMetadataCompat.Builder(mediaMetadataCompat, MediaSessionCompat.sMaxBitmapSize).build();
            }
            synchronized (this.mLock) {
                this.mMetadata = build;
                // monitorexit(this.mLock)
                this.sendMetadata(build);
                if (!this.mIsActive) {
                    return;
                }
            }
            final MediaMetadataCompat mediaMetadataCompat2;
            Bundle bundle;
            if (mediaMetadataCompat2 == null) {
                bundle = null;
            }
            else {
                bundle = mediaMetadataCompat2.getBundle();
            }
            this.buildRccMetadata(bundle).apply();
        }
        
        @Override
        public void setPlaybackState(final PlaybackStateCompat mState) {
            synchronized (this.mLock) {
                this.mState = mState;
                // monitorexit(this.mLock)
                this.sendState(mState);
                if (!this.mIsActive) {
                    return;
                }
            }
            final PlaybackStateCompat rccState;
            if (rccState == null) {
                this.mRcc.setPlaybackState(0);
                this.mRcc.setTransportControlFlags(0);
                return;
            }
            this.setRccState(rccState);
            this.mRcc.setTransportControlFlags(this.getRccTransportControlFlagsFromActions(rccState.getActions()));
        }
        
        @Override
        public void setPlaybackToLocal(final int n) {
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 1;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, 2, this.mAudioManager.getStreamMaxVolume(this.mLocalStream), this.mAudioManager.getStreamVolume(this.mLocalStream)));
        }
        
        @Override
        public void setPlaybackToRemote(final VolumeProviderCompat mVolumeProvider) {
            if (mVolumeProvider == null) {
                throw new IllegalArgumentException("volumeProvider may not be null");
            }
            if (this.mVolumeProvider != null) {
                this.mVolumeProvider.setCallback(null);
            }
            this.mVolumeType = 2;
            this.mVolumeProvider = mVolumeProvider;
            this.sendVolumeInfoChanged(new ParcelableVolumeInfo(this.mVolumeType, this.mLocalStream, this.mVolumeProvider.getVolumeControl(), this.mVolumeProvider.getMaxVolume(), this.mVolumeProvider.getCurrentVolume()));
            mVolumeProvider.setCallback(this.mVolumeCallback);
        }
        
        @Override
        public void setQueue(final List<QueueItem> mQueue) {
            this.sendQueue(this.mQueue = mQueue);
        }
        
        @Override
        public void setQueueTitle(final CharSequence mQueueTitle) {
            this.sendQueueTitle(this.mQueueTitle = mQueueTitle);
        }
        
        @Override
        public void setRatingType(final int mRatingType) {
            this.mRatingType = mRatingType;
        }
        
        void setRccState(final PlaybackStateCompat playbackStateCompat) {
            this.mRcc.setPlaybackState(this.getRccStateFromState(playbackStateCompat.getState()));
        }
        
        @Override
        public void setRepeatMode(final int mRepeatMode) {
            if (this.mRepeatMode != mRepeatMode) {
                this.sendRepeatMode(this.mRepeatMode = mRepeatMode);
            }
        }
        
        @Override
        public void setSessionActivity(final PendingIntent mSessionActivity) {
            synchronized (this.mLock) {
                this.mSessionActivity = mSessionActivity;
            }
        }
        
        @Override
        public void setShuffleMode(final int mShuffleMode) {
            if (this.mShuffleMode != mShuffleMode) {
                this.sendShuffleMode(this.mShuffleMode = mShuffleMode);
            }
        }
        
        @Override
        public void setShuffleModeEnabled(final boolean mShuffleModeEnabled) {
            if (this.mShuffleModeEnabled != mShuffleModeEnabled) {
                this.sendShuffleModeEnabled(this.mShuffleModeEnabled = mShuffleModeEnabled);
            }
        }
        
        void setVolumeTo(final int n, final int n2) {
            if (this.mVolumeType == 2) {
                if (this.mVolumeProvider != null) {
                    this.mVolumeProvider.onSetVolumeTo(n);
                }
                return;
            }
            this.mAudioManager.setStreamVolume(this.mLocalStream, n, n2);
        }
        
        void unregisterMediaButtonEventReceiver(final PendingIntent pendingIntent, final ComponentName componentName) {
            this.mAudioManager.unregisterMediaButtonEventReceiver(componentName);
        }
        
        boolean update() {
            final boolean b = false;
            boolean b2;
            if (this.mIsActive) {
                if (!this.mIsMbrRegistered && (this.mFlags & 0x1) != 0x0) {
                    this.registerMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = true;
                }
                else if (this.mIsMbrRegistered && (this.mFlags & 0x1) == 0x0) {
                    this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                if (!this.mIsRccRegistered && (this.mFlags & 0x2) != 0x0) {
                    this.mAudioManager.registerRemoteControlClient(this.mRcc);
                    this.mIsRccRegistered = true;
                    b2 = true;
                }
                else {
                    b2 = b;
                    if (this.mIsRccRegistered) {
                        b2 = b;
                        if ((this.mFlags & 0x2) == 0x0) {
                            this.mRcc.setPlaybackState(0);
                            this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                            return this.mIsRccRegistered = false;
                        }
                    }
                }
            }
            else {
                if (this.mIsMbrRegistered) {
                    this.unregisterMediaButtonEventReceiver(this.mMediaButtonReceiverIntent, this.mMediaButtonReceiverComponentName);
                    this.mIsMbrRegistered = false;
                }
                b2 = b;
                if (this.mIsRccRegistered) {
                    this.mRcc.setPlaybackState(0);
                    this.mAudioManager.unregisterRemoteControlClient(this.mRcc);
                    return this.mIsRccRegistered = false;
                }
            }
            return b2;
        }
        
        private static final class Command
        {
            public final String command;
            public final Bundle extras;
            public final ResultReceiver stub;
            
            public Command(final String command, final Bundle extras, final ResultReceiver stub) {
                this.command = command;
                this.extras = extras;
                this.stub = stub;
            }
        }
        
        class MediaSessionStub extends Stub
        {
            public void addQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
                MediaSessionImplBase.this.postToHandler(25, mediaDescriptionCompat);
            }
            
            public void addQueueItemAt(final MediaDescriptionCompat mediaDescriptionCompat, final int n) {
                MediaSessionImplBase.this.postToHandler(26, mediaDescriptionCompat, n);
            }
            
            public void adjustVolume(final int n, final int n2, final String s) {
                MediaSessionImplBase.this.adjustVolume(n, n2);
            }
            
            public void fastForward() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(16);
            }
            
            public Bundle getExtras() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mExtras;
                }
            }
            
            public long getFlags() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mFlags;
                }
            }
            
            public PendingIntent getLaunchPendingIntent() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mSessionActivity;
                }
            }
            
            public MediaMetadataCompat getMetadata() {
                return MediaSessionImplBase.this.mMetadata;
            }
            
            public String getPackageName() {
                return MediaSessionImplBase.this.mPackageName;
            }
            
            public PlaybackStateCompat getPlaybackState() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    final PlaybackStateCompat mState = MediaSessionImplBase.this.mState;
                    final MediaMetadataCompat mMetadata = MediaSessionImplBase.this.mMetadata;
                    // monitorexit(this.this$0.mLock)
                    return getStateWithUpdatedPosition(mState, mMetadata);
                }
            }
            
            public List<QueueItem> getQueue() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    return MediaSessionImplBase.this.mQueue;
                }
            }
            
            public CharSequence getQueueTitle() {
                return MediaSessionImplBase.this.mQueueTitle;
            }
            
            public int getRatingType() {
                return MediaSessionImplBase.this.mRatingType;
            }
            
            public int getRepeatMode() {
                return MediaSessionImplBase.this.mRepeatMode;
            }
            
            public int getShuffleMode() {
                return MediaSessionImplBase.this.mShuffleMode;
            }
            
            public String getTag() {
                return MediaSessionImplBase.this.mTag;
            }
            
            public ParcelableVolumeInfo getVolumeAttributes() {
                synchronized (MediaSessionImplBase.this.mLock) {
                    final int mVolumeType = MediaSessionImplBase.this.mVolumeType;
                    final int mLocalStream = MediaSessionImplBase.this.mLocalStream;
                    final VolumeProviderCompat mVolumeProvider = MediaSessionImplBase.this.mVolumeProvider;
                    int volumeControl;
                    int n;
                    int n2;
                    if (mVolumeType == 2) {
                        volumeControl = mVolumeProvider.getVolumeControl();
                        n = mVolumeProvider.getMaxVolume();
                        n2 = mVolumeProvider.getCurrentVolume();
                    }
                    else {
                        volumeControl = 2;
                        n = MediaSessionImplBase.this.mAudioManager.getStreamMaxVolume(mLocalStream);
                        n2 = MediaSessionImplBase.this.mAudioManager.getStreamVolume(mLocalStream);
                    }
                    // monitorexit(this.this$0.mLock)
                    return new ParcelableVolumeInfo(mVolumeType, mLocalStream, volumeControl, n, n2);
                }
            }
            
            public boolean isCaptioningEnabled() {
                return MediaSessionImplBase.this.mCaptioningEnabled;
            }
            
            public boolean isShuffleModeEnabledDeprecated() {
                return MediaSessionImplBase.this.mShuffleModeEnabled;
            }
            
            public boolean isTransportControlEnabled() {
                return (MediaSessionImplBase.this.mFlags & 0x2) != 0x0;
            }
            
            public void next() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(14);
            }
            
            public void pause() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(12);
            }
            
            public void play() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(7);
            }
            
            public void playFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(8, s, bundle);
            }
            
            public void playFromSearch(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(9, s, bundle);
            }
            
            public void playFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(10, uri, bundle);
            }
            
            public void prepare() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(3);
            }
            
            public void prepareFromMediaId(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(4, s, bundle);
            }
            
            public void prepareFromSearch(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(5, s, bundle);
            }
            
            public void prepareFromUri(final Uri uri, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(6, uri, bundle);
            }
            
            public void previous() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(15);
            }
            
            public void rate(final RatingCompat ratingCompat) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(19, ratingCompat);
            }
            
            public void rateWithExtras(final RatingCompat ratingCompat, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(31, ratingCompat, bundle);
            }
            
            public void registerCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                Label_0017: {
                    if (!MediaSessionImplBase.this.mDestroyed) {
                        break Label_0017;
                    }
                    try {
                        mediaControllerCallback.onSessionDestroyed();
                        return;
                        MediaSessionImplBase.this.mControllerCallbacks.register((IInterface)mediaControllerCallback);
                    }
                    catch (Exception ex) {}
                }
            }
            
            public void removeQueueItem(final MediaDescriptionCompat mediaDescriptionCompat) {
                MediaSessionImplBase.this.postToHandler(27, mediaDescriptionCompat);
            }
            
            public void removeQueueItemAt(final int n) {
                MediaSessionImplBase.this.postToHandler(28, n);
            }
            
            public void rewind() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(17);
            }
            
            public void seekTo(final long n) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(18, n);
            }
            
            public void sendCommand(final String s, final Bundle bundle, final ResultReceiverWrapper resultReceiverWrapper) {
                MediaSessionImplBase.this.postToHandler(1, new Command(s, bundle, resultReceiverWrapper.mResultReceiver));
            }
            
            public void sendCustomAction(final String s, final Bundle bundle) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(20, s, bundle);
            }
            
            public boolean sendMediaButton(final KeyEvent keyEvent) {
                final boolean b = (MediaSessionImplBase.this.mFlags & 0x1) != 0x0;
                if (b) {
                    MediaSessionImplBase.this.postToHandler(21, keyEvent);
                }
                return b;
            }
            
            public void setCaptioningEnabled(final boolean b) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(29, b);
            }
            
            public void setRepeatMode(final int n) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(23, n);
            }
            
            public void setShuffleMode(final int n) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(30, n);
            }
            
            public void setShuffleModeEnabledDeprecated(final boolean b) throws RemoteException {
                MediaSessionImplBase.this.postToHandler(24, b);
            }
            
            public void setVolumeTo(final int n, final int n2, final String s) {
                MediaSessionImplBase.this.setVolumeTo(n, n2);
            }
            
            public void skipToQueueItem(final long n) {
                MediaSessionImplBase.this.postToHandler(11, n);
            }
            
            public void stop() throws RemoteException {
                MediaSessionImplBase.this.postToHandler(13);
            }
            
            public void unregisterCallbackListener(final IMediaControllerCallback mediaControllerCallback) {
                MediaSessionImplBase.this.mControllerCallbacks.unregister((IInterface)mediaControllerCallback);
            }
        }
        
        class MessageHandler extends Handler
        {
            private static final int KEYCODE_MEDIA_PAUSE = 127;
            private static final int KEYCODE_MEDIA_PLAY = 126;
            private static final int MSG_ADD_QUEUE_ITEM = 25;
            private static final int MSG_ADD_QUEUE_ITEM_AT = 26;
            private static final int MSG_ADJUST_VOLUME = 2;
            private static final int MSG_COMMAND = 1;
            private static final int MSG_CUSTOM_ACTION = 20;
            private static final int MSG_FAST_FORWARD = 16;
            private static final int MSG_MEDIA_BUTTON = 21;
            private static final int MSG_NEXT = 14;
            private static final int MSG_PAUSE = 12;
            private static final int MSG_PLAY = 7;
            private static final int MSG_PLAY_MEDIA_ID = 8;
            private static final int MSG_PLAY_SEARCH = 9;
            private static final int MSG_PLAY_URI = 10;
            private static final int MSG_PREPARE = 3;
            private static final int MSG_PREPARE_MEDIA_ID = 4;
            private static final int MSG_PREPARE_SEARCH = 5;
            private static final int MSG_PREPARE_URI = 6;
            private static final int MSG_PREVIOUS = 15;
            private static final int MSG_RATE = 19;
            private static final int MSG_RATE_EXTRA = 31;
            private static final int MSG_REMOVE_QUEUE_ITEM = 27;
            private static final int MSG_REMOVE_QUEUE_ITEM_AT = 28;
            private static final int MSG_REWIND = 17;
            private static final int MSG_SEEK_TO = 18;
            private static final int MSG_SET_CAPTIONING_ENABLED = 29;
            private static final int MSG_SET_REPEAT_MODE = 23;
            private static final int MSG_SET_SHUFFLE_MODE = 30;
            private static final int MSG_SET_SHUFFLE_MODE_ENABLED = 24;
            private static final int MSG_SET_VOLUME = 22;
            private static final int MSG_SKIP_TO_ITEM = 11;
            private static final int MSG_STOP = 13;
            
            public MessageHandler(final Looper looper) {
                super(looper);
            }
            
            private void onMediaButtonEvent(final KeyEvent keyEvent, final Callback callback) {
                if (keyEvent != null && keyEvent.getAction() == 0) {
                    long actions;
                    if (MediaSessionImplBase.this.mState == null) {
                        actions = 0L;
                    }
                    else {
                        actions = MediaSessionImplBase.this.mState.getActions();
                    }
                    switch (keyEvent.getKeyCode()) {
                        default: {}
                        case 79:
                        case 85: {
                            Log.w("MediaSessionCompat", "KEYCODE_MEDIA_PLAY_PAUSE and KEYCODE_HEADSETHOOK are handled already");
                        }
                        case 126: {
                            if ((0x4L & actions) != 0x0L) {
                                callback.onPlay();
                                return;
                            }
                            break;
                        }
                        case 127: {
                            if ((0x2L & actions) != 0x0L) {
                                callback.onPause();
                                return;
                            }
                            break;
                        }
                        case 87: {
                            if ((0x20L & actions) != 0x0L) {
                                callback.onSkipToNext();
                                return;
                            }
                            break;
                        }
                        case 88: {
                            if ((0x10L & actions) != 0x0L) {
                                callback.onSkipToPrevious();
                                return;
                            }
                            break;
                        }
                        case 86: {
                            if ((0x1L & actions) != 0x0L) {
                                callback.onStop();
                                return;
                            }
                            break;
                        }
                        case 90: {
                            if ((0x40L & actions) != 0x0L) {
                                callback.onFastForward();
                                return;
                            }
                            break;
                        }
                        case 89: {
                            if ((0x8L & actions) != 0x0L) {
                                callback.onRewind();
                                return;
                            }
                            break;
                        }
                    }
                }
            }
            
            public void handleMessage(final Message message) {
                final Callback mCallback = MediaSessionImplBase.this.mCallback;
                if (mCallback != null) {
                    switch (message.what) {
                        default: {}
                        case 1: {
                            final Command command = (Command)message.obj;
                            mCallback.onCommand(command.command, command.extras, command.stub);
                        }
                        case 21: {
                            final KeyEvent keyEvent = (KeyEvent)message.obj;
                            final Intent intent = new Intent("android.intent.action.MEDIA_BUTTON");
                            intent.putExtra("android.intent.extra.KEY_EVENT", (Parcelable)keyEvent);
                            if (!mCallback.onMediaButtonEvent(intent)) {
                                this.onMediaButtonEvent(keyEvent, mCallback);
                                return;
                            }
                            break;
                        }
                        case 3: {
                            mCallback.onPrepare();
                        }
                        case 4: {
                            mCallback.onPrepareFromMediaId((String)message.obj, message.getData());
                        }
                        case 5: {
                            mCallback.onPrepareFromSearch((String)message.obj, message.getData());
                        }
                        case 6: {
                            mCallback.onPrepareFromUri((Uri)message.obj, message.getData());
                        }
                        case 7: {
                            mCallback.onPlay();
                        }
                        case 8: {
                            mCallback.onPlayFromMediaId((String)message.obj, message.getData());
                        }
                        case 9: {
                            mCallback.onPlayFromSearch((String)message.obj, message.getData());
                        }
                        case 10: {
                            mCallback.onPlayFromUri((Uri)message.obj, message.getData());
                        }
                        case 11: {
                            mCallback.onSkipToQueueItem((long)message.obj);
                        }
                        case 12: {
                            mCallback.onPause();
                        }
                        case 13: {
                            mCallback.onStop();
                        }
                        case 14: {
                            mCallback.onSkipToNext();
                        }
                        case 15: {
                            mCallback.onSkipToPrevious();
                        }
                        case 16: {
                            mCallback.onFastForward();
                        }
                        case 17: {
                            mCallback.onRewind();
                        }
                        case 18: {
                            mCallback.onSeekTo((long)message.obj);
                        }
                        case 19: {
                            mCallback.onSetRating((RatingCompat)message.obj);
                        }
                        case 31: {
                            mCallback.onSetRating((RatingCompat)message.obj, message.getData());
                        }
                        case 20: {
                            mCallback.onCustomAction((String)message.obj, message.getData());
                        }
                        case 25: {
                            mCallback.onAddQueueItem((MediaDescriptionCompat)message.obj);
                        }
                        case 26: {
                            mCallback.onAddQueueItem((MediaDescriptionCompat)message.obj, message.arg1);
                        }
                        case 27: {
                            mCallback.onRemoveQueueItem((MediaDescriptionCompat)message.obj);
                        }
                        case 28: {
                            if (MediaSessionImplBase.this.mQueue == null) {
                                break;
                            }
                            QueueItem queueItem;
                            if (message.arg1 >= 0 && message.arg1 < MediaSessionImplBase.this.mQueue.size()) {
                                queueItem = MediaSessionImplBase.this.mQueue.get(message.arg1);
                            }
                            else {
                                queueItem = null;
                            }
                            if (queueItem != null) {
                                mCallback.onRemoveQueueItem(queueItem.getDescription());
                                return;
                            }
                            break;
                        }
                        case 2: {
                            MediaSessionImplBase.this.adjustVolume(message.arg1, 0);
                        }
                        case 22: {
                            MediaSessionImplBase.this.setVolumeTo(message.arg1, 0);
                        }
                        case 29: {
                            mCallback.onSetCaptioningEnabled((boolean)message.obj);
                        }
                        case 23: {
                            mCallback.onSetRepeatMode(message.arg1);
                        }
                        case 24: {
                            mCallback.onSetShuffleModeEnabled((boolean)message.obj);
                        }
                        case 30: {
                            mCallback.onSetShuffleMode(message.arg1);
                        }
                    }
                }
            }
            
            public void post(final int n) {
                this.post(n, null);
            }
            
            public void post(final int n, final Object o) {
                this.obtainMessage(n, o).sendToTarget();
            }
            
            public void post(final int n, final Object o, final int n2) {
                this.obtainMessage(n, n2, 0, o).sendToTarget();
            }
            
            public void post(final int n, final Object o, final Bundle data) {
                final Message obtainMessage = this.obtainMessage(n, o);
                obtainMessage.setData(data);
                obtainMessage.sendToTarget();
            }
        }
    }
    
    public interface OnActiveChangeListener
    {
        void onActiveChanged();
    }
    
    public static final class QueueItem implements Parcelable
    {
        public static final Parcelable$Creator<QueueItem> CREATOR;
        public static final int UNKNOWN_ID = -1;
        private final MediaDescriptionCompat mDescription;
        private final long mId;
        private Object mItem;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<QueueItem>() {
                public QueueItem createFromParcel(final Parcel parcel) {
                    return new QueueItem(parcel);
                }
                
                public QueueItem[] newArray(final int n) {
                    return new QueueItem[n];
                }
            };
        }
        
        QueueItem(final Parcel parcel) {
            this.mDescription = (MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(parcel);
            this.mId = parcel.readLong();
        }
        
        public QueueItem(final MediaDescriptionCompat mediaDescriptionCompat, final long n) {
            this(null, mediaDescriptionCompat, n);
        }
        
        private QueueItem(final Object mItem, final MediaDescriptionCompat mDescription, final long mId) {
            if (mDescription == null) {
                throw new IllegalArgumentException("Description cannot be null.");
            }
            if (mId == -1L) {
                throw new IllegalArgumentException("Id cannot be QueueItem.UNKNOWN_ID");
            }
            this.mDescription = mDescription;
            this.mId = mId;
            this.mItem = mItem;
        }
        
        public static QueueItem fromQueueItem(final Object o) {
            if (o == null || Build$VERSION.SDK_INT < 21) {
                return null;
            }
            return new QueueItem(o, MediaDescriptionCompat.fromMediaDescription(MediaSessionCompatApi21.QueueItem.getDescription(o)), MediaSessionCompatApi21.QueueItem.getQueueId(o));
        }
        
        public static List<QueueItem> fromQueueItemList(final List<?> list) {
            List<QueueItem> list2;
            if (list == null || Build$VERSION.SDK_INT < 21) {
                list2 = null;
            }
            else {
                final ArrayList<QueueItem> list3 = new ArrayList<QueueItem>();
                final Iterator<?> iterator = list.iterator();
                while (true) {
                    list2 = list3;
                    if (!iterator.hasNext()) {
                        break;
                    }
                    list3.add(fromQueueItem(iterator.next()));
                }
            }
            return list2;
        }
        
        public int describeContents() {
            return 0;
        }
        
        public MediaDescriptionCompat getDescription() {
            return this.mDescription;
        }
        
        public long getQueueId() {
            return this.mId;
        }
        
        public Object getQueueItem() {
            if (this.mItem != null || Build$VERSION.SDK_INT < 21) {
                return this.mItem;
            }
            return this.mItem = MediaSessionCompatApi21.QueueItem.createItem(this.mDescription.getMediaDescription(), this.mId);
        }
        
        @Override
        public String toString() {
            return "MediaSession.QueueItem {Description=" + this.mDescription + ", Id=" + this.mId + " }";
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            this.mDescription.writeToParcel(parcel, n);
            parcel.writeLong(this.mId);
        }
    }
    
    static final class ResultReceiverWrapper implements Parcelable
    {
        public static final Parcelable$Creator<ResultReceiverWrapper> CREATOR;
        private ResultReceiver mResultReceiver;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<ResultReceiverWrapper>() {
                public ResultReceiverWrapper createFromParcel(final Parcel parcel) {
                    return new ResultReceiverWrapper(parcel);
                }
                
                public ResultReceiverWrapper[] newArray(final int n) {
                    return new ResultReceiverWrapper[n];
                }
            };
        }
        
        ResultReceiverWrapper(final Parcel parcel) {
            this.mResultReceiver = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(parcel);
        }
        
        public ResultReceiverWrapper(final ResultReceiver mResultReceiver) {
            this.mResultReceiver = mResultReceiver;
        }
        
        public int describeContents() {
            return 0;
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            this.mResultReceiver.writeToParcel(parcel, n);
        }
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
    public @interface SessionFlags {
    }
    
    public static final class Token implements Parcelable
    {
        public static final Parcelable$Creator<Token> CREATOR;
        private final IMediaSession mExtraBinder;
        private final Object mInner;
        
        static {
            CREATOR = (Parcelable$Creator)new Parcelable$Creator<Token>() {
                public Token createFromParcel(final Parcel parcel) {
                    Object o;
                    if (Build$VERSION.SDK_INT >= 21) {
                        o = parcel.readParcelable((ClassLoader)null);
                    }
                    else {
                        o = parcel.readStrongBinder();
                    }
                    return new Token(o);
                }
                
                public Token[] newArray(final int n) {
                    return new Token[n];
                }
            };
        }
        
        Token(final Object o) {
            this(o, null);
        }
        
        Token(final Object mInner, final IMediaSession mExtraBinder) {
            this.mInner = mInner;
            this.mExtraBinder = mExtraBinder;
        }
        
        public static Token fromToken(final Object o) {
            return fromToken(o, null);
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public static Token fromToken(final Object o, final IMediaSession mediaSession) {
            if (o != null && Build$VERSION.SDK_INT >= 21) {
                return new Token(MediaSessionCompatApi21.verifyToken(o), mediaSession);
            }
            return null;
        }
        
        public int describeContents() {
            return 0;
        }
        
        @Override
        public boolean equals(final Object o) {
            if (this != o) {
                if (!(o instanceof Token)) {
                    return false;
                }
                final Token token = (Token)o;
                if (this.mInner != null) {
                    return token.mInner != null && this.mInner.equals(token.mInner);
                }
                if (token.mInner != null) {
                    return false;
                }
            }
            return true;
        }
        
        @RestrictTo({ RestrictTo$Scope.LIBRARY_GROUP })
        public IMediaSession getExtraBinder() {
            return this.mExtraBinder;
        }
        
        public Object getToken() {
            return this.mInner;
        }
        
        @Override
        public int hashCode() {
            if (this.mInner == null) {
                return 0;
            }
            return this.mInner.hashCode();
        }
        
        public void writeToParcel(final Parcel parcel, final int n) {
            if (Build$VERSION.SDK_INT >= 21) {
                parcel.writeParcelable((Parcelable)this.mInner, n);
                return;
            }
            parcel.writeStrongBinder((IBinder)this.mInner);
        }
    }
}
