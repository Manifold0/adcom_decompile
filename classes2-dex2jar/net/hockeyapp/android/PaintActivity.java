// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android;

import android.view.MenuItem;
import android.app.AlertDialog$Builder;
import android.content.DialogInterface;
import android.content.DialogInterface$OnClickListener;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.Toast;
import android.view.View;
import android.view.ViewGroup$LayoutParams;
import android.widget.LinearLayout$LayoutParams;
import android.widget.LinearLayout;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.content.Intent;
import java.io.IOException;
import net.hockeyapp.android.utils.HockeyLog;
import java.io.OutputStream;
import android.graphics.Bitmap$CompressFormat;
import java.io.FileOutputStream;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.database.Cursor;
import java.io.File;
import android.net.Uri;
import net.hockeyapp.android.views.PaintView;
import android.app.Activity;

public class PaintActivity extends Activity
{
    public static final String EXTRA_IMAGE_URI = "imageUri";
    private static final int MENU_CLEAR_ID = 3;
    private static final int MENU_SAVE_ID = 1;
    private static final int MENU_UNDO_ID = 2;
    private String mImageName;
    private PaintView mPaintView;
    
    private String determineFilename(final Uri uri, final String s) {
        final String s2 = null;
        final String s3 = null;
        final Cursor query = this.getApplicationContext().getContentResolver().query(uri, new String[] { "_data" }, (String)null, (String[])null, (String)null);
        String string = s2;
        while (true) {
            if (query != null) {
                string = s3;
                try {
                    if (query.moveToFirst()) {
                        string = query.getString(0);
                    }
                    query.close();
                    if (string == null) {
                        return s;
                    }
                }
                finally {
                    query.close();
                }
                final String s4;
                return new File(s4).getName();
            }
            continue;
        }
    }
    
    private void makeResult() {
        final File file = new File(this.getCacheDir(), "HockeyApp");
        file.mkdir();
        File file2 = new File(file, this.mImageName + ".jpg");
        for (int n = 1; file2.exists(); file2 = new File(file, this.mImageName + "_" + n + ".jpg"), ++n) {}
        this.mPaintView.setDrawingCacheEnabled(true);
        new AsyncTask<File, Void, Void>() {
            final /* synthetic */ Bitmap val$bitmap = PaintActivity.this.mPaintView.getDrawingCache();
            
            protected Void doInBackground(final File... array) {
                try {
                    final FileOutputStream fileOutputStream = new FileOutputStream(array[0]);
                    this.val$bitmap.compress(Bitmap$CompressFormat.JPEG, 100, (OutputStream)fileOutputStream);
                    fileOutputStream.close();
                    return null;
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                    HockeyLog.error("Could not save image.", ex);
                    return null;
                }
            }
        }.execute((Object[])new File[] { file2 });
        final Intent intent = new Intent();
        intent.putExtra("imageUri", (Parcelable)Uri.fromFile(file2));
        if (this.getParent() == null) {
            this.setResult(-1, intent);
        }
        else {
            this.getParent().setResult(-1, intent);
        }
        this.finish();
    }
    
    public void onCreate(Bundle extras) {
        super.onCreate(extras);
        extras = this.getIntent().getExtras();
        if (extras == null || extras.getParcelable("imageUri") == null) {
            HockeyLog.error("Can't set up PaintActivity as image extra was not provided!");
            return;
        }
        final Uri uri = (Uri)extras.getParcelable("imageUri");
        this.mImageName = this.determineFilename(uri, uri.getLastPathSegment());
        final int widthPixels = this.getResources().getDisplayMetrics().widthPixels;
        final int heightPixels = this.getResources().getDisplayMetrics().heightPixels;
        int n;
        if (widthPixels > heightPixels) {
            n = 0;
        }
        else {
            n = 1;
        }
        final int determineOrientation = PaintView.determineOrientation(this.getContentResolver(), uri);
        this.setRequestedOrientation(determineOrientation);
        if (n != determineOrientation) {
            HockeyLog.debug("Image loading skipped because activity will be destroyed for orientation change.");
            return;
        }
        this.mPaintView = new PaintView((Context)this, uri, widthPixels, heightPixels);
        final LinearLayout contentView = new LinearLayout((Context)this);
        contentView.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
        contentView.setGravity(17);
        contentView.setOrientation(1);
        final LinearLayout linearLayout = new LinearLayout((Context)this);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new LinearLayout$LayoutParams(-1, -1));
        linearLayout.setGravity(17);
        linearLayout.setOrientation(0);
        contentView.addView((View)linearLayout);
        linearLayout.addView((View)this.mPaintView);
        this.setContentView((View)contentView);
        Toast.makeText((Context)this, (CharSequence)this.getString(R$string.hockeyapp_paint_indicator_toast), 1).show();
    }
    
    public boolean onCreateOptionsMenu(final Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, 1, 0, (CharSequence)this.getString(R$string.hockeyapp_paint_menu_save));
        menu.add(0, 2, 0, (CharSequence)this.getString(R$string.hockeyapp_paint_menu_undo));
        menu.add(0, 3, 0, (CharSequence)this.getString(R$string.hockeyapp_paint_menu_clear));
        return true;
    }
    
    public boolean onKeyDown(final int n, final KeyEvent keyEvent) {
        if (n == 4 && !this.mPaintView.isClear()) {
            final DialogInterface$OnClickListener dialogInterface$OnClickListener = (DialogInterface$OnClickListener)new DialogInterface$OnClickListener() {
                public void onClick(final DialogInterface dialogInterface, final int n) {
                    switch (n) {
                        default: {}
                        case -1: {
                            PaintActivity.this.makeResult();
                        }
                        case -2: {
                            PaintActivity.this.finish();
                        }
                    }
                }
            };
            new AlertDialog$Builder((Context)this).setMessage(R$string.hockeyapp_paint_dialog_message).setPositiveButton(R$string.hockeyapp_paint_dialog_positive_button, (DialogInterface$OnClickListener)dialogInterface$OnClickListener).setNegativeButton(R$string.hockeyapp_paint_dialog_negative_button, (DialogInterface$OnClickListener)dialogInterface$OnClickListener).setNeutralButton(R$string.hockeyapp_paint_dialog_neutral_button, (DialogInterface$OnClickListener)dialogInterface$OnClickListener).show();
            return true;
        }
        return super.onKeyDown(n, keyEvent);
    }
    
    public boolean onOptionsItemSelected(final MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            default: {
                return super.onOptionsItemSelected(menuItem);
            }
            case 1: {
                this.makeResult();
                return true;
            }
            case 2: {
                this.mPaintView.undo();
                return true;
            }
            case 3: {
                this.mPaintView.clearImage();
                return true;
            }
        }
    }
    
    public boolean onPrepareOptionsMenu(final Menu menu) {
        super.onPrepareOptionsMenu(menu);
        return true;
    }
}
