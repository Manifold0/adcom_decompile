// 
// Decompiled by Procyon v0.5.34
// 

package com.google.android.gms.common.images;

import java.util.concurrent.CountDownLatch;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.graphics.drawable.Drawable;
import android.os.Parcelable;
import android.content.Intent;
import android.os.ParcelFileDescriptor;
import android.os.Bundle;
import java.util.ArrayList;
import com.google.android.gms.common.annotation.KeepName;
import android.os.ResultReceiver;
import android.widget.ImageView;
import com.google.android.gms.common.internal.Asserts;
import android.graphics.Bitmap;
import java.util.HashMap;
import java.util.concurrent.Executors;
import com.google.android.gms.internal.base.zap;
import android.os.Looper;
import java.util.Map;
import com.google.android.gms.internal.base.zak;
import java.util.concurrent.ExecutorService;
import android.os.Handler;
import android.content.Context;
import android.net.Uri;
import java.util.HashSet;

public final class ImageManager
{
    private static final Object zamh;
    private static HashSet<Uri> zami;
    private static ImageManager zamj;
    private final Context mContext;
    private final Handler mHandler;
    private final ExecutorService zamk;
    private final zaa zaml;
    private final zak zamm;
    private final Map<com.google.android.gms.common.images.zaa, ImageReceiver> zamn;
    private final Map<Uri, ImageReceiver> zamo;
    private final Map<Uri, Long> zamp;
    
    static {
        zamh = new Object();
        ImageManager.zami = new HashSet<Uri>();
    }
    
    private ImageManager(final Context context, final boolean b) {
        this.mContext = context.getApplicationContext();
        this.mHandler = new zap(Looper.getMainLooper());
        this.zamk = Executors.newFixedThreadPool(4);
        this.zaml = null;
        this.zamm = new zak();
        this.zamn = new HashMap<com.google.android.gms.common.images.zaa, ImageReceiver>();
        this.zamo = new HashMap<Uri, ImageReceiver>();
        this.zamp = new HashMap<Uri, Long>();
    }
    
    public static ImageManager create(final Context context) {
        if (ImageManager.zamj == null) {
            ImageManager.zamj = new ImageManager(context, false);
        }
        return ImageManager.zamj;
    }
    
    private final Bitmap zaa(final com.google.android.gms.common.images.zab zab) {
        if (this.zaml == null) {
            return null;
        }
        return (Bitmap)this.zaml.get((Object)zab);
    }
    
    private final void zaa(final com.google.android.gms.common.images.zaa zaa) {
        Asserts.checkMainThread("ImageManager.loadImage() must be called in the main thread");
        new zac(zaa).run();
    }
    
    public final void loadImage(final ImageView imageView, final int n) {
        this.zaa(new com.google.android.gms.common.images.zac(imageView, n));
    }
    
    public final void loadImage(final ImageView imageView, final Uri uri) {
        this.zaa(new com.google.android.gms.common.images.zac(imageView, uri));
    }
    
    public final void loadImage(final ImageView imageView, final Uri uri, final int zamx) {
        final com.google.android.gms.common.images.zac zac = new com.google.android.gms.common.images.zac(imageView, uri);
        zac.zamx = zamx;
        this.zaa(zac);
    }
    
    public final void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri) {
        this.zaa(new com.google.android.gms.common.images.zad(onImageLoadedListener, uri));
    }
    
    public final void loadImage(final OnImageLoadedListener onImageLoadedListener, final Uri uri, final int zamx) {
        final com.google.android.gms.common.images.zad zad = new com.google.android.gms.common.images.zad(onImageLoadedListener, uri);
        zad.zamx = zamx;
        this.zaa(zad);
    }
    
    @KeepName
    private final class ImageReceiver extends ResultReceiver
    {
        private final Uri mUri;
        private final ArrayList<com.google.android.gms.common.images.zaa> zamq;
        
        ImageReceiver(final Uri mUri) {
            super((Handler)new zap(Looper.getMainLooper()));
            this.mUri = mUri;
            this.zamq = new ArrayList<com.google.android.gms.common.images.zaa>();
        }
        
        public final void onReceiveResult(final int n, final Bundle bundle) {
            ImageManager.this.zamk.execute(new zab(this.mUri, (ParcelFileDescriptor)bundle.getParcelable("com.google.android.gms.extra.fileDescriptor")));
        }
        
        public final void zab(final com.google.android.gms.common.images.zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.addImageRequest() must be called in the main thread");
            this.zamq.add(zaa);
        }
        
        public final void zac(final com.google.android.gms.common.images.zaa zaa) {
            Asserts.checkMainThread("ImageReceiver.removeImageRequest() must be called in the main thread");
            this.zamq.remove(zaa);
        }
        
        public final void zace() {
            final Intent intent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
            intent.putExtra("com.google.android.gms.extras.uri", (Parcelable)this.mUri);
            intent.putExtra("com.google.android.gms.extras.resultReceiver", (Parcelable)this);
            intent.putExtra("com.google.android.gms.extras.priority", 3);
            ImageManager.this.mContext.sendBroadcast(intent);
        }
    }
    
    public interface OnImageLoadedListener
    {
        void onImageLoaded(final Uri p0, final Drawable p1, final boolean p2);
    }
    
    private static final class zaa extends LruCache<com.google.android.gms.common.images.zab, Bitmap>
    {
    }
    
    private final class zab implements Runnable
    {
        private final Uri mUri;
        private final ParcelFileDescriptor zams;
        
        public zab(final Uri mUri, final ParcelFileDescriptor zams) {
            this.mUri = mUri;
            this.zams = zams;
        }
        
        @Override
        public final void run() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     2: invokestatic    com/google/android/gms/common/internal/Asserts.checkNotMainThread:(Ljava/lang/String;)V
            //     5: iconst_0       
            //     6: istore_1       
            //     7: iconst_0       
            //     8: istore_2       
            //     9: aconst_null    
            //    10: astore_3       
            //    11: aconst_null    
            //    12: astore          4
            //    14: aload_0        
            //    15: getfield        com/google/android/gms/common/images/ImageManager$zab.zams:Landroid/os/ParcelFileDescriptor;
            //    18: ifnull          41
            //    21: aload_0        
            //    22: getfield        com/google/android/gms/common/images/ImageManager$zab.zams:Landroid/os/ParcelFileDescriptor;
            //    25: invokevirtual   android/os/ParcelFileDescriptor.getFileDescriptor:()Ljava/io/FileDescriptor;
            //    28: invokestatic    android/graphics/BitmapFactory.decodeFileDescriptor:(Ljava/io/FileDescriptor;)Landroid/graphics/Bitmap;
            //    31: astore_3       
            //    32: iload_2        
            //    33: istore_1       
            //    34: aload_0        
            //    35: getfield        com/google/android/gms/common/images/ImageManager$zab.zams:Landroid/os/ParcelFileDescriptor;
            //    38: invokevirtual   android/os/ParcelFileDescriptor.close:()V
            //    41: new             Ljava/util/concurrent/CountDownLatch;
            //    44: dup            
            //    45: iconst_1       
            //    46: invokespecial   java/util/concurrent/CountDownLatch.<init>:(I)V
            //    49: astore          4
            //    51: aload_0        
            //    52: getfield        com/google/android/gms/common/images/ImageManager$zab.zamr:Lcom/google/android/gms/common/images/ImageManager;
            //    55: invokestatic    com/google/android/gms/common/images/ImageManager.zag:(Lcom/google/android/gms/common/images/ImageManager;)Landroid/os/Handler;
            //    58: new             Lcom/google/android/gms/common/images/ImageManager$zad;
            //    61: dup            
            //    62: aload_0        
            //    63: getfield        com/google/android/gms/common/images/ImageManager$zab.zamr:Lcom/google/android/gms/common/images/ImageManager;
            //    66: aload_0        
            //    67: getfield        com/google/android/gms/common/images/ImageManager$zab.mUri:Landroid/net/Uri;
            //    70: aload_3        
            //    71: iload_1        
            //    72: aload           4
            //    74: invokespecial   com/google/android/gms/common/images/ImageManager$zad.<init>:(Lcom/google/android/gms/common/images/ImageManager;Landroid/net/Uri;Landroid/graphics/Bitmap;ZLjava/util/concurrent/CountDownLatch;)V
            //    77: invokevirtual   android/os/Handler.post:(Ljava/lang/Runnable;)Z
            //    80: pop            
            //    81: aload           4
            //    83: invokevirtual   java/util/concurrent/CountDownLatch.await:()V
            //    86: return         
            //    87: astore_3       
            //    88: aload_0        
            //    89: getfield        com/google/android/gms/common/images/ImageManager$zab.mUri:Landroid/net/Uri;
            //    92: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //    95: astore          5
            //    97: ldc             "ImageManager"
            //    99: new             Ljava/lang/StringBuilder;
            //   102: dup            
            //   103: aload           5
            //   105: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   108: invokevirtual   java/lang/String.length:()I
            //   111: bipush          34
            //   113: iadd           
            //   114: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //   117: ldc             "OOM while loading bitmap for uri: "
            //   119: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   122: aload           5
            //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   127: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   130: aload_3        
            //   131: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   134: pop            
            //   135: iconst_1       
            //   136: istore_1       
            //   137: aload           4
            //   139: astore_3       
            //   140: goto            34
            //   143: astore          4
            //   145: ldc             "ImageManager"
            //   147: ldc             "closed failed"
            //   149: aload           4
            //   151: invokestatic    android/util/Log.e:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
            //   154: pop            
            //   155: goto            41
            //   158: astore_3       
            //   159: aload_0        
            //   160: getfield        com/google/android/gms/common/images/ImageManager$zab.mUri:Landroid/net/Uri;
            //   163: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   166: astore_3       
            //   167: ldc             "ImageManager"
            //   169: new             Ljava/lang/StringBuilder;
            //   172: dup            
            //   173: aload_3        
            //   174: invokestatic    java/lang/String.valueOf:(Ljava/lang/Object;)Ljava/lang/String;
            //   177: invokevirtual   java/lang/String.length:()I
            //   180: bipush          32
            //   182: iadd           
            //   183: invokespecial   java/lang/StringBuilder.<init>:(I)V
            //   186: ldc             "Latch interrupted while posting "
            //   188: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   191: aload_3        
            //   192: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   195: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   198: invokestatic    android/util/Log.w:(Ljava/lang/String;Ljava/lang/String;)I
            //   201: pop            
            //   202: return         
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                            
            //  -----  -----  -----  -----  --------------------------------
            //  21     32     87     143    Ljava/lang/OutOfMemoryError;
            //  34     41     143    158    Ljava/io/IOException;
            //  81     86     158    203    Ljava/lang/InterruptedException;
            // 
            // The error that occurred was:
            // 
            // java.lang.IndexOutOfBoundsException: Index: 102, Size: 102
            //     at java.util.ArrayList.rangeCheck(ArrayList.java:653)
            //     at java.util.ArrayList.get(ArrayList.java:429)
            //     at com.strobel.decompiler.ast.AstBuilder.convertToAst(AstBuilder.java:3321)
            //     at com.strobel.decompiler.ast.AstBuilder.build(AstBuilder.java:113)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:211)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
            //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
            //     at com.strobel.decompiler.DecompilerDriver.decompileType(DecompilerDriver.java:330)
            //     at com.strobel.decompiler.DecompilerDriver.decompileJar(DecompilerDriver.java:251)
            //     at com.strobel.decompiler.DecompilerDriver.main(DecompilerDriver.java:126)
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    private final class zac implements Runnable
    {
        private final com.google.android.gms.common.images.zaa zamt;
        
        public zac(final com.google.android.gms.common.images.zaa zamt) {
            this.zamt = zamt;
        }
        
        @Override
        public final void run() {
            Asserts.checkMainThread("LoadImageRunnable must be executed on the main thread");
            final ImageReceiver imageReceiver = ImageManager.this.zamn.get(this.zamt);
            if (imageReceiver != null) {
                ImageManager.this.zamn.remove(this.zamt);
                imageReceiver.zac(this.zamt);
            }
            final com.google.android.gms.common.images.zab zamv = this.zamt.zamv;
            if (zamv.uri == null) {
                this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm, true);
                return;
            }
            final Bitmap zaa = ImageManager.this.zaa(zamv);
            if (zaa != null) {
                this.zamt.zaa(ImageManager.this.mContext, zaa, true);
                return;
            }
            final Long n = ImageManager.this.zamp.get(zamv.uri);
            if (n != null) {
                if (SystemClock.elapsedRealtime() - n < 3600000L) {
                    this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm, true);
                    return;
                }
                ImageManager.this.zamp.remove(zamv.uri);
            }
            this.zamt.zaa(ImageManager.this.mContext, ImageManager.this.zamm);
            ResultReceiver resultReceiver;
            if ((resultReceiver = ImageManager.this.zamo.get(zamv.uri)) == null) {
                resultReceiver = new ImageReceiver(zamv.uri);
                ImageManager.this.zamo.put(zamv.uri, resultReceiver);
            }
            ((ImageReceiver)resultReceiver).zab(this.zamt);
            if (!(this.zamt instanceof com.google.android.gms.common.images.zad)) {
                ImageManager.this.zamn.put(this.zamt, resultReceiver);
            }
            synchronized (ImageManager.zamh) {
                if (!ImageManager.zami.contains(zamv.uri)) {
                    ImageManager.zami.add(zamv.uri);
                    ((ImageReceiver)resultReceiver).zace();
                }
            }
        }
    }
    
    private final class zad implements Runnable
    {
        private final Bitmap mBitmap;
        private final Uri mUri;
        private final CountDownLatch zadr;
        private boolean zamu;
        
        public zad(final Uri mUri, final Bitmap mBitmap, final boolean zamu, final CountDownLatch zadr) {
            this.mUri = mUri;
            this.mBitmap = mBitmap;
            this.zamu = zamu;
            this.zadr = zadr;
        }
        
        @Override
        public final void run() {
            Asserts.checkMainThread("OnBitmapLoadedRunnable must be executed in the main thread");
            final boolean b = this.mBitmap != null;
            if (ImageManager.this.zaml != null) {
                if (this.zamu) {
                    ImageManager.this.zaml.evictAll();
                    System.gc();
                    this.zamu = false;
                    ImageManager.this.mHandler.post((Runnable)this);
                    return;
                }
                if (b) {
                    ImageManager.this.zaml.put((Object)new com.google.android.gms.common.images.zab(this.mUri), (Object)this.mBitmap);
                }
            }
            final ImageReceiver imageReceiver = ImageManager.this.zamo.remove(this.mUri);
            if (imageReceiver != null) {
                final ArrayList zaa = imageReceiver.zamq;
                for (int size = zaa.size(), i = 0; i < size; ++i) {
                    final com.google.android.gms.common.images.zaa zaa2 = zaa.get(i);
                    if (b) {
                        zaa2.zaa(ImageManager.this.mContext, this.mBitmap, false);
                    }
                    else {
                        ImageManager.this.zamp.put(this.mUri, SystemClock.elapsedRealtime());
                        zaa2.zaa(ImageManager.this.mContext, ImageManager.this.zamm, false);
                    }
                    if (!(zaa2 instanceof com.google.android.gms.common.images.zad)) {
                        ImageManager.this.zamn.remove(zaa2);
                    }
                }
            }
            this.zadr.countDown();
            synchronized (ImageManager.zamh) {
                ImageManager.zami.remove(this.mUri);
            }
        }
    }
}
