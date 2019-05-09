// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.views;

import net.hockeyapp.android.utils.HockeyLog;
import android.view.View$MeasureSpec;
import android.view.View;
import android.net.Uri;
import java.util.ArrayList;
import android.view.ViewGroup$LayoutParams;
import android.util.AttributeSet;
import android.content.Context;
import android.view.ViewGroup;

public class AttachmentListView extends ViewGroup
{
    private static final String TAG = "AttachmentListView";
    private int mLineHeight;
    
    public AttachmentListView(final Context context) {
        super(context);
    }
    
    public AttachmentListView(final Context context, final AttributeSet set) {
        super(context, set);
    }
    
    protected boolean checkLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        return viewGroup$LayoutParams instanceof ViewGroup$LayoutParams;
    }
    
    protected ViewGroup$LayoutParams generateDefaultLayoutParams() {
        return new ViewGroup$LayoutParams(1, 1);
    }
    
    public ArrayList<Uri> getAttachments() {
        final ArrayList<Uri> list = new ArrayList<Uri>();
        for (int i = 0; i < this.getChildCount(); ++i) {
            list.add(((AttachmentView)this.getChildAt(i)).getAttachmentUri());
        }
        return list;
    }
    
    protected void onLayout(final boolean b, final int n, int paddingTop, final int n2, int paddingLeft) {
        final int childCount = this.getChildCount();
        paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        int n3;
        int n4;
        for (int i = 0; i < childCount; ++i, paddingLeft = n3, paddingTop = n4) {
            final View child = this.getChildAt(i);
            n3 = paddingLeft;
            n4 = paddingTop;
            if (child.getVisibility() != 8) {
                child.invalidate();
                final int measuredWidth = child.getMeasuredWidth();
                final int measuredHeight = child.getMeasuredHeight();
                final ViewGroup$LayoutParams layoutParams = child.getLayoutParams();
                int paddingLeft2 = paddingLeft;
                n4 = paddingTop;
                if (paddingLeft + measuredWidth > n2 - n) {
                    paddingLeft2 = this.getPaddingLeft();
                    n4 = paddingTop + this.mLineHeight;
                }
                child.layout(paddingLeft2, n4, paddingLeft2 + measuredWidth, n4 + measuredHeight);
                n3 = paddingLeft2 + (layoutParams.width + measuredWidth + ((AttachmentView)child).getGap());
            }
        }
    }
    
    protected void onMeasure(int paddingTop, final int n) {
        if (View$MeasureSpec.getMode(paddingTop) == 0) {
            HockeyLog.debug("AttachmentListView", "Width is unspecified");
        }
        final int size = View$MeasureSpec.getSize(paddingTop);
        final int childCount = this.getChildCount();
        int n2 = 0;
        int mLineHeight = 0;
        int paddingLeft = this.getPaddingLeft();
        paddingTop = this.getPaddingTop();
        int n3;
        int max;
        int n4;
        int n5;
        for (int i = 0; i < childCount; ++i, n2 = n3, mLineHeight = max, paddingLeft = n4, paddingTop = n5) {
            final View child = this.getChildAt(i);
            final AttachmentView attachmentView = (AttachmentView)child;
            n3 = attachmentView.getEffectiveMaxHeight() + attachmentView.getPaddingTop();
            max = mLineHeight;
            n4 = paddingLeft;
            n5 = paddingTop;
            if (child.getVisibility() != 8) {
                final ViewGroup$LayoutParams layoutParams = child.getLayoutParams();
                child.measure(View$MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE), View$MeasureSpec.makeMeasureSpec(n3, Integer.MIN_VALUE));
                final int measuredWidth = child.getMeasuredWidth();
                max = Math.max(mLineHeight, child.getMeasuredHeight() + layoutParams.height);
                int paddingLeft2 = paddingLeft;
                int n6 = paddingTop;
                if (paddingLeft + measuredWidth > size) {
                    paddingLeft2 = this.getPaddingLeft();
                    n6 = paddingTop + max;
                }
                n4 = paddingLeft2 + (layoutParams.width + measuredWidth);
                n5 = n6;
            }
        }
        this.mLineHeight = mLineHeight;
        int n7;
        if (View$MeasureSpec.getMode(n) == 0) {
            n7 = paddingTop + mLineHeight + this.getPaddingBottom();
        }
        else {
            n7 = n2;
            if (View$MeasureSpec.getMode(n) == Integer.MIN_VALUE && paddingTop + mLineHeight + this.getPaddingBottom() < (n7 = n2)) {
                n7 = paddingTop + mLineHeight + this.getPaddingBottom();
            }
        }
        this.setMeasuredDimension(size, n7);
    }
}
