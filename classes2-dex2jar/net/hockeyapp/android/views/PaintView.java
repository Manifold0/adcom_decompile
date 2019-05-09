// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.views;

import android.view.MotionEvent;
import java.util.Iterator;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.content.ContentResolver;
import java.io.IOException;
import net.hockeyapp.android.utils.HockeyLog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.graphics.Paint$Cap;
import android.graphics.Paint$Join;
import android.graphics.Paint$Style;
import android.net.Uri;
import android.content.Context;
import java.util.Stack;
import android.graphics.Path;
import android.graphics.Paint;
import android.annotation.SuppressLint;
import android.widget.ImageView;

@SuppressLint({ "ViewConstructor" })
public class PaintView extends ImageView
{
    private static final float TOUCH_TOLERANCE = 4.0f;
    private float mX;
    private float mY;
    private Paint paint;
    private Path path;
    private Stack<Path> paths;
    
    public PaintView(final Context context, final Uri uri, final int n, final int n2) {
        super(context);
        this.path = new Path();
        this.paths = new Stack<Path>();
        (this.paint = new Paint()).setAntiAlias(true);
        this.paint.setDither(true);
        this.paint.setColor(-65536);
        this.paint.setStyle(Paint$Style.STROKE);
        this.paint.setStrokeJoin(Paint$Join.ROUND);
        this.paint.setStrokeCap(Paint$Cap.ROUND);
        this.paint.setStrokeWidth(12.0f);
        new AsyncTask<Object, Void, Bitmap>() {
            protected Bitmap doInBackground(final Object... array) {
                final Context context = (Context)array[0];
                final Uri uri = (Uri)array[1];
                final Integer n = (Integer)array[2];
                final Integer n2 = (Integer)array[3];
                try {
                    return decodeSampledBitmapFromResource(context.getContentResolver(), uri, n, n2);
                }
                catch (IOException ex) {
                    HockeyLog.error("Could not load image into ImageView.", ex);
                    return null;
                }
            }
            
            protected void onPostExecute(final Bitmap imageBitmap) {
                if (imageBitmap == null) {
                    return;
                }
                PaintView.this.setImageBitmap(imageBitmap);
            }
            
            protected void onPreExecute() {
                PaintView.this.setAdjustViewBounds(true);
            }
        }.execute(new Object[] { context, uri, n, n2 });
    }
    
    private static int calculateInSampleSize(final BitmapFactory$Options bitmapFactory$Options, final int n, final int n2) {
        final int outHeight = bitmapFactory$Options.outHeight;
        final int outWidth = bitmapFactory$Options.outWidth;
        int n3 = 1;
        int n4 = 1;
        if (outHeight > n2 || outWidth > n) {
            final int n5 = outHeight / 2;
            final int n6 = outWidth / 2;
            while (true) {
                n3 = n4;
                if (n5 / n4 <= n2) {
                    break;
                }
                n3 = n4;
                if (n6 / n4 <= n) {
                    break;
                }
                n4 *= 2;
            }
        }
        return n3;
    }
    
    private static Bitmap decodeSampledBitmapFromResource(final ContentResolver contentResolver, final Uri uri, final int n, final int n2) throws IOException {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(contentResolver.openInputStream(uri), (Rect)null, bitmapFactory$Options);
        bitmapFactory$Options.inSampleSize = calculateInSampleSize(bitmapFactory$Options, n, n2);
        bitmapFactory$Options.inJustDecodeBounds = false;
        return BitmapFactory.decodeStream(contentResolver.openInputStream(uri), (Rect)null, bitmapFactory$Options);
    }
    
    public static int determineOrientation(final ContentResolver contentResolver, final Uri uri) {
        int n = 1;
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        try {
            BitmapFactory.decodeStream(contentResolver.openInputStream(uri), (Rect)null, bitmapFactory$Options);
            if (bitmapFactory$Options.outWidth / (float)bitmapFactory$Options.outHeight > 1.0f) {
                n = 0;
            }
            return n;
        }
        catch (IOException ex) {
            HockeyLog.error("Unable to determine necessary screen orientation.", ex);
            return 1;
        }
    }
    
    private void touchMove(final float mx, final float my) {
        final float abs = Math.abs(mx - this.mX);
        final float abs2 = Math.abs(my - this.mY);
        if (abs >= 4.0f || abs2 >= 4.0f) {
            this.path.quadTo(this.mX, this.mY, (this.mX + mx) / 2.0f, (this.mY + my) / 2.0f);
            this.mX = mx;
            this.mY = my;
        }
    }
    
    private void touchStart(final float mx, final float my) {
        this.path.reset();
        this.path.moveTo(mx, my);
        this.mX = mx;
        this.mY = my;
    }
    
    private void touchUp() {
        this.path.lineTo(this.mX, this.mY);
        this.paths.push(this.path);
        this.path = new Path();
    }
    
    public void clearImage() {
        this.paths.clear();
        this.invalidate();
    }
    
    public boolean isClear() {
        return this.paths.empty();
    }
    
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);
        final Iterator<Path> iterator = this.paths.iterator();
        while (iterator.hasNext()) {
            canvas.drawPath((Path)iterator.next(), this.paint);
        }
        canvas.drawPath(this.path, this.paint);
    }
    
    public boolean onTouchEvent(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 0: {
                this.touchStart(x, y);
                this.invalidate();
                break;
            }
            case 2: {
                this.touchMove(x, y);
                this.invalidate();
                break;
            }
            case 1: {
                this.touchUp();
                this.invalidate();
                break;
            }
        }
        return true;
    }
    
    public void undo() {
        if (!this.paths.empty()) {
            this.paths.pop();
            this.invalidate();
        }
    }
}
