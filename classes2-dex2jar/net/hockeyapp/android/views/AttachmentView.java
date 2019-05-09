// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.views;

import net.hockeyapp.android.utils.ImageUtils;
import android.widget.ImageButton;
import android.text.TextUtils$TruncateAt;
import net.hockeyapp.android.R$color;
import android.widget.LinearLayout;
import android.os.Build$VERSION;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.view.View;
import android.view.View$OnClickListener;
import android.widget.ImageView$ScaleType;
import android.graphics.Color;
import android.view.ViewGroup$LayoutParams;
import android.widget.FrameLayout$LayoutParams;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import net.hockeyapp.android.R$string;
import java.io.File;
import net.hockeyapp.android.Constants;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.TextView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.content.Context;
import android.net.Uri;
import net.hockeyapp.android.objects.FeedbackAttachment;
import android.annotation.SuppressLint;
import android.widget.FrameLayout;

@SuppressLint({ "ViewConstructor" })
public class AttachmentView extends FrameLayout
{
    private static final int IMAGES_PER_ROW_LANDSCAPE = 2;
    private static final int IMAGES_PER_ROW_PORTRAIT = 3;
    private final FeedbackAttachment mAttachment;
    private final Uri mAttachmentUri;
    private final Context mContext;
    private final String mFilename;
    private int mGap;
    private ImageView mImageView;
    private int mMaxHeightLandscape;
    private int mMaxHeightPortrait;
    private int mOrientation;
    private final ViewGroup mParent;
    private TextView mTextView;
    private int mWidthLandscape;
    private int mWidthPortrait;
    
    public AttachmentView(final Context mContext, final ViewGroup mParent, final Uri mAttachmentUri, final boolean b) {
        super(mContext);
        this.mContext = mContext;
        this.mParent = mParent;
        this.mAttachment = null;
        this.mAttachmentUri = mAttachmentUri;
        this.mFilename = mAttachmentUri.getLastPathSegment();
        this.calculateDimensions(20);
        this.initializeView(mContext, b);
        this.mTextView.setText((CharSequence)this.mFilename);
        new AsyncTask<Void, Void, Bitmap>() {
            protected Bitmap doInBackground(final Void... array) {
                return AttachmentView.this.loadImageThumbnail();
            }
            
            protected void onPostExecute(final Bitmap bitmap) {
                if (bitmap != null) {
                    AttachmentView.this.configureViewForThumbnail(bitmap, false);
                    return;
                }
                AttachmentView.this.configureViewForPlaceholder(false);
            }
        }.execute((Object[])new Void[0]);
    }
    
    public AttachmentView(final Context mContext, final ViewGroup mParent, final FeedbackAttachment mAttachment, final boolean b) {
        super(mContext);
        this.mContext = mContext;
        this.mParent = mParent;
        this.mAttachment = mAttachment;
        this.mAttachmentUri = Uri.fromFile(new File(Constants.getHockeyAppStorageDir(), mAttachment.getCacheId()));
        this.mFilename = mAttachment.getFilename();
        this.calculateDimensions(30);
        this.initializeView(mContext, b);
        this.mOrientation = 0;
        this.mTextView.setText(R$string.hockeyapp_feedback_attachment_loading);
        this.configureViewForPlaceholder(false);
    }
    
    private void calculateDimensions(int round) {
        final DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        this.mGap = Math.round(TypedValue.applyDimension(1, 10.0f, displayMetrics));
        round = Math.round(TypedValue.applyDimension(1, (float)round, displayMetrics));
        final int widthPixels = displayMetrics.widthPixels;
        final int mGap = this.mGap;
        final int mGap2 = this.mGap;
        this.mWidthPortrait = (widthPixels - round * 2 - mGap * 2) / 3;
        this.mWidthLandscape = (widthPixels - round * 2 - mGap2) / 2;
        this.mMaxHeightPortrait = this.mWidthPortrait * 2;
        this.mMaxHeightLandscape = this.mWidthLandscape;
    }
    
    private void configureViewForPlaceholder(final boolean b) {
        this.mTextView.setMaxWidth(this.mWidthPortrait);
        this.mTextView.setMinWidth(this.mWidthPortrait);
        this.mImageView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        this.mImageView.setAdjustViewBounds(false);
        this.mImageView.setBackgroundColor(Color.parseColor("#eeeeee"));
        this.mImageView.setMinimumHeight((int)(this.mWidthPortrait * 1.2f));
        this.mImageView.setMinimumWidth(this.mWidthPortrait);
        this.mImageView.setScaleType(ImageView$ScaleType.FIT_CENTER);
        this.mImageView.setImageDrawable(this.getSystemIcon("ic_menu_attachment"));
        this.mImageView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (!b) {
                    return;
                }
                final Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setDataAndType(AttachmentView.this.mAttachmentUri, "*/*");
                AttachmentView.this.mContext.startActivity(intent);
            }
        });
    }
    
    private void configureViewForThumbnail(final Bitmap imageBitmap, final boolean b) {
        int n;
        if (this.mOrientation == 1) {
            n = this.mWidthLandscape;
        }
        else {
            n = this.mWidthPortrait;
        }
        int maxHeight;
        if (this.mOrientation == 1) {
            maxHeight = this.mMaxHeightLandscape;
        }
        else {
            maxHeight = this.mMaxHeightPortrait;
        }
        this.mTextView.setMaxWidth(n);
        this.mTextView.setMinWidth(n);
        this.mImageView.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2));
        this.mImageView.setAdjustViewBounds(true);
        this.mImageView.setMinimumWidth(n);
        this.mImageView.setMaxWidth(n);
        this.mImageView.setMaxHeight(maxHeight);
        this.mImageView.setScaleType(ImageView$ScaleType.CENTER_INSIDE);
        this.mImageView.setImageBitmap(imageBitmap);
        this.mImageView.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
            public void onClick(final View view) {
                if (!b) {
                    return;
                }
                final Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setDataAndType(AttachmentView.this.mAttachmentUri, "image/*");
                AttachmentView.this.mContext.startActivity(intent);
            }
        });
    }
    
    private Drawable getSystemIcon(final String s) {
        if (Build$VERSION.SDK_INT >= 21) {
            return this.getResources().getDrawable(this.getResources().getIdentifier(s, "drawable", "android"), this.mContext.getTheme());
        }
        return this.getResources().getDrawable(this.getResources().getIdentifier(s, "drawable", "android"));
    }
    
    private void initializeView(final Context context, final boolean b) {
        this.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 80));
        this.setPadding(0, this.mGap, 0, 0);
        this.mImageView = new ImageView(context);
        final LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -2, 80));
        linearLayout.setGravity(8388611);
        linearLayout.setOrientation(1);
        linearLayout.setBackgroundColor(Color.parseColor("#80262626"));
        (this.mTextView = new TextView(context)).setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -2, 17));
        this.mTextView.setGravity(17);
        this.mTextView.setTextColor(context.getResources().getColor(R$color.hockeyapp_text_white));
        this.mTextView.setSingleLine();
        this.mTextView.setEllipsize(TextUtils$TruncateAt.MIDDLE);
        if (b) {
            final ImageButton imageButton = new ImageButton(context);
            imageButton.setLayoutParams((ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-1, -2, 80));
            imageButton.setAdjustViewBounds(true);
            imageButton.setImageDrawable(this.getSystemIcon("ic_menu_delete"));
            imageButton.setBackgroundResource(0);
            imageButton.setOnClickListener((View$OnClickListener)new View$OnClickListener() {
                public void onClick(final View view) {
                    AttachmentView.this.remove();
                }
            });
            linearLayout.addView((View)imageButton);
        }
        linearLayout.addView((View)this.mTextView);
        this.addView((View)this.mImageView);
        this.addView((View)linearLayout);
    }
    
    private Bitmap loadImageThumbnail() {
        try {
            this.mOrientation = ImageUtils.determineOrientation(this.mContext, this.mAttachmentUri);
            int n;
            if (this.mOrientation == 1) {
                n = this.mWidthLandscape;
            }
            else {
                n = this.mWidthPortrait;
            }
            int n2;
            if (this.mOrientation == 1) {
                n2 = this.mMaxHeightLandscape;
            }
            else {
                n2 = this.mMaxHeightPortrait;
            }
            return ImageUtils.decodeSampledBitmap(this.mContext, this.mAttachmentUri, n, n2);
        }
        catch (Throwable t) {
            return null;
        }
    }
    
    public FeedbackAttachment getAttachment() {
        return this.mAttachment;
    }
    
    public Uri getAttachmentUri() {
        return this.mAttachmentUri;
    }
    
    public int getEffectiveMaxHeight() {
        if (this.mOrientation == 1) {
            return this.mMaxHeightLandscape;
        }
        return this.mMaxHeightPortrait;
    }
    
    public int getGap() {
        return this.mGap;
    }
    
    public int getMaxHeightLandscape() {
        return this.mMaxHeightLandscape;
    }
    
    public int getMaxHeightPortrait() {
        return this.mMaxHeightPortrait;
    }
    
    public int getWidthLandscape() {
        return this.mWidthLandscape;
    }
    
    public int getWidthPortrait() {
        return this.mWidthPortrait;
    }
    
    public void remove() {
        this.mParent.removeView((View)this);
    }
    
    public void setImage(final Bitmap bitmap, final int mOrientation) {
        this.mTextView.setText((CharSequence)this.mFilename);
        this.mOrientation = mOrientation;
        if (bitmap == null) {
            this.configureViewForPlaceholder(true);
            return;
        }
        this.configureViewForThumbnail(bitmap, true);
    }
    
    public void signalImageLoadingError() {
        this.mTextView.setText(R$string.hockeyapp_feedback_attachment_error);
    }
}
