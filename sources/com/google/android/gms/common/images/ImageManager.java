package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Asserts;
import com.google.android.gms.common.internal.Constants;
import com.google.android.gms.internal.base.zak;
import com.google.android.gms.internal.base.zap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager {
    private static final Object zamh = new Object();
    private static HashSet<Uri> zami = new HashSet();
    private static ImageManager zamj;
    private final Context mContext;
    private final Handler mHandler = new zap(Looper.getMainLooper());
    private final ExecutorService zamk = Executors.newFixedThreadPool(4);
    private final zaa zaml = null;
    private final zak zamm = new zak();
    private final Map<zaa, ImageReceiver> zamn = new HashMap();
    private final Map<Uri, ImageReceiver> zamo = new HashMap();
    private final Map<Uri, Long> zamp = new HashMap();

    @KeepName
    private final class ImageReceiver extends ResultReceiver {
        private final Uri mUri;
        private final ArrayList<zaa> zamq = new ArrayList();
        private final /* synthetic */ ImageManager zamr;

        ImageReceiver(ImageManager imageManager, Uri uri) {
            this.zamr = imageManager;
            super(new zap(Looper.getMainLooper()));
            this.mUri = uri;
        }

        public final void zab(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zamq.add(zaa);
        }

        public final void zac(zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zamq.remove(zaa);
        }

        public final void zace() {
            Intent intent = new Intent(Constants.ACTION_LOAD_IMAGE);
            intent.putExtra(Constants.EXTRA_URI, this.mUri);
            intent.putExtra(Constants.EXTRA_RESULT_RECEIVER, this);
            intent.putExtra(Constants.EXTRA_PRIORITY, 3);
            this.zamr.mContext.sendBroadcast(intent);
        }

        public final void onReceiveResult(int i, Bundle bundle) {
            this.zamr.zamk.execute(new zab(this.zamr, this.mUri, (ParcelFileDescriptor) bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
    }

    public interface OnImageLoadedListener {
        void onImageLoaded(Uri uri, Drawable drawable, boolean z);
    }

    private static final class zaa extends LruCache<zab, Bitmap> {
        protected final /* synthetic */ int sizeOf(Object obj, Object obj2) {
            Bitmap bitmap = (Bitmap) obj2;
            return bitmap.getHeight() * bitmap.getRowBytes();
        }

        protected final /* synthetic */ void entryRemoved(boolean z, Object obj, Object obj2, Object obj3) {
            super.entryRemoved(z, (zab) obj, (Bitmap) obj2, (Bitmap) obj3);
        }
    }

    private final class zab implements Runnable {
        private final Uri mUri;
        private final /* synthetic */ ImageManager zamr;
        private final ParcelFileDescriptor zams;

        public zab(ImageManager imageManager, Uri uri, ParcelFileDescriptor parcelFileDescriptor) {
            this.zamr = imageManager;
            this.mUri = uri;
            this.zams = parcelFileDescriptor;
        }

        public final void run() {
            Asserts.checkNotMainThread("LoadBitmapFromDiskRunnable can't be executed in the main thread");
            boolean z = false;
            Bitmap bitmap = null;
            if (this.zams != null) {
                try {
                    bitmap = BitmapFactory.decodeFileDescriptor(this.zams.getFileDescriptor());
                } catch (Throwable e) {
                    String valueOf = String.valueOf(this.mUri);
                    Log.e("ImageManager", new StringBuilder(String.valueOf(valueOf).length() + 34).append("OOM while loading bitmap for uri: ").append(valueOf).toString(), e);
                    z = true;
                }
                try {
                    this.zams.close();
                } catch (Throwable e2) {
                    Log.e("ImageManager", "closed failed", e2);
                }
            }
            CountDownLatch countDownLatch = new CountDownLatch(1);
            this.zamr.mHandler.post(new zad(this.zamr, this.mUri, bitmap, z, countDownLatch));
            try {
                countDownLatch.await();
            } catch (InterruptedException e3) {
                String valueOf2 = String.valueOf(this.mUri);
                Log.w("ImageManager", new StringBuilder(String.valueOf(valueOf2).length() + 32).append("Latch interrupted while posting ").append(valueOf2).toString());
            }
        }
    }

    private final class zac implements Runnable {
        private final /* synthetic */ ImageManager zamr;
        private final zaa zamt;

        public zac(ImageManager imageManager, zaa zaa) {
            this.zamr = imageManager;
            this.zamt = zaa;
        }

        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            ImageReceiver imageReceiver = (ImageReceiver) this.zamr.zamn.get(this.zamt);
            if (imageReceiver != null) {
                this.zamr.zamn.remove(this.zamt);
                imageReceiver.zac(this.zamt);
            }
            zab zab = this.zamt.zamv;
            if (zab.uri == null) {
                this.zamt.zaa(this.zamr.mContext, this.zamr.zamm, true);
                return;
            }
            Bitmap zaa = this.zamr.zaa(zab);
            if (zaa != null) {
                this.zamt.zaa(this.zamr.mContext, zaa, true);
                return;
            }
            Long l = (Long) this.zamr.zamp.get(zab.uri);
            if (l != null) {
                if (SystemClock.elapsedRealtime() - l.longValue() < 3600000) {
                    this.zamt.zaa(this.zamr.mContext, this.zamr.zamm, true);
                    return;
                }
                this.zamr.zamp.remove(zab.uri);
            }
            this.zamt.zaa(this.zamr.mContext, this.zamr.zamm);
            imageReceiver = (ImageReceiver) this.zamr.zamo.get(zab.uri);
            if (imageReceiver == null) {
                imageReceiver = new ImageReceiver(this.zamr, zab.uri);
                this.zamr.zamo.put(zab.uri, imageReceiver);
            }
            imageReceiver.zab(this.zamt);
            if (!(this.zamt instanceof zad)) {
                this.zamr.zamn.put(this.zamt, imageReceiver);
            }
            synchronized (ImageManager.zamh) {
                if (!ImageManager.zami.contains(zab.uri)) {
                    ImageManager.zami.add(zab.uri);
                    imageReceiver.zace();
                }
            }
        }
    }

    private final class zad implements Runnable {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zadr;
        private final /* synthetic */ ImageManager zamr;
        private boolean zamu;

        public zad(ImageManager imageManager, Uri uri, Bitmap bitmap, boolean z, CountDownLatch countDownLatch) {
            this.zamr = imageManager;
            this.mUri = uri;
            this.mBitmap = bitmap;
            this.zamu = z;
            this.zadr = countDownLatch;
        }

        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            boolean z = this.mBitmap != null;
            if (this.zamr.zaml != null) {
                if (this.zamu) {
                    this.zamr.zaml.evictAll();
                    System.gc();
                    this.zamu = false;
                    this.zamr.mHandler.post(this);
                    return;
                } else if (z) {
                    this.zamr.zaml.put(new zab(this.mUri), this.mBitmap);
                }
            }
            ImageReceiver imageReceiver = (ImageReceiver) this.zamr.zamo.remove(this.mUri);
            if (imageReceiver != null) {
                ArrayList zaa = imageReceiver.zamq;
                int size = zaa.size();
                for (int i = 0; i < size; i++) {
                    zaa zaa2 = (zaa) zaa.get(i);
                    if (z) {
                        zaa2.zaa(this.zamr.mContext, this.mBitmap, false);
                    } else {
                        this.zamr.zamp.put(this.mUri, Long.valueOf(SystemClock.elapsedRealtime()));
                        zaa2.zaa(this.zamr.mContext, this.zamr.zamm, false);
                    }
                    if (!(zaa2 instanceof zad)) {
                        this.zamr.zamn.remove(zaa2);
                    }
                }
            }
            this.zadr.countDown();
            synchronized (ImageManager.zamh) {
                ImageManager.zami.remove(this.mUri);
            }
        }
    }

    public static ImageManager create(Context context) {
        if (zamj == null) {
            zamj = new ImageManager(context, false);
        }
        return zamj;
    }

    private ImageManager(Context context, boolean z) {
        this.mContext = context.getApplicationContext();
    }

    public final void loadImage(ImageView imageView, Uri uri) {
        zaa(new zac(imageView, uri));
    }

    public final void loadImage(ImageView imageView, int i) {
        zaa(new zac(imageView, i));
    }

    public final void loadImage(ImageView imageView, Uri uri, int i) {
        zaa zac = new zac(imageView, uri);
        zac.zamx = i;
        zaa(zac);
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri) {
        zaa(new zad(onImageLoadedListener, uri));
    }

    public final void loadImage(OnImageLoadedListener onImageLoadedListener, Uri uri, int i) {
        zaa zad = new zad(onImageLoadedListener, uri);
        zad.zamx = i;
        zaa(zad);
    }

    private final void zaa(zaa zaa) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zac(this, zaa).run();
    }

    private final Bitmap zaa(zab zab) {
        if (this.zaml == null) {
            return null;
        }
        return (Bitmap) this.zaml.get(zab);
    }
}
