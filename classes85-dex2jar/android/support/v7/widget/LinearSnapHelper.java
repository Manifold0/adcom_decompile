// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v7.widget;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.view.View;
import android.support.annotation.Nullable;

public class LinearSnapHelper extends SnapHelper
{
    private static final float INVALID_DISTANCE = 1.0f;
    @Nullable
    private OrientationHelper mHorizontalHelper;
    @Nullable
    private OrientationHelper mVerticalHelper;
    
    private float computeDistancePerChild(final LayoutManager layoutManager, final OrientationHelper orientationHelper) {
        View view = null;
        View view2 = null;
        int n = Integer.MAX_VALUE;
        int n2 = Integer.MIN_VALUE;
        final int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return 1.0f;
        }
        View view3;
        int n3;
        int n4;
        for (int i = 0; i < childCount; ++i, n2 = n4, n = n3, view = view3) {
            final View child = layoutManager.getChildAt(i);
            final int position = layoutManager.getPosition(child);
            if (position == -1) {
                view3 = view;
                n3 = n;
                n4 = n2;
            }
            else {
                int n5;
                if (position < (n5 = n)) {
                    n5 = position;
                    view = child;
                }
                n4 = n2;
                n3 = n5;
                view3 = view;
                if (position > n2) {
                    n4 = position;
                    view2 = child;
                    n3 = n5;
                    view3 = view;
                }
            }
        }
        if (view == null || view2 == null) {
            return 1.0f;
        }
        final int n6 = Math.max(orientationHelper.getDecoratedEnd(view), orientationHelper.getDecoratedEnd(view2)) - Math.min(orientationHelper.getDecoratedStart(view), orientationHelper.getDecoratedStart(view2));
        if (n6 == 0) {
            return 1.0f;
        }
        return 1.0f * n6 / (n2 - n + 1);
    }
    
    private int distanceToCenter(@NonNull final LayoutManager layoutManager, @NonNull final View view, final OrientationHelper orientationHelper) {
        final int decoratedStart = orientationHelper.getDecoratedStart(view);
        final int n = orientationHelper.getDecoratedMeasurement(view) / 2;
        int n2;
        if (layoutManager.getClipToPadding()) {
            n2 = orientationHelper.getStartAfterPadding() + orientationHelper.getTotalSpace() / 2;
        }
        else {
            n2 = orientationHelper.getEnd() / 2;
        }
        return decoratedStart + n - n2;
    }
    
    private int estimateNextPositionDiffForFling(final LayoutManager layoutManager, final OrientationHelper orientationHelper, int n, final int n2) {
        final int[] calculateScrollDistance = this.calculateScrollDistance(n, n2);
        final float computeDistancePerChild = this.computeDistancePerChild(layoutManager, orientationHelper);
        if (computeDistancePerChild <= 0.0f) {
            return 0;
        }
        if (Math.abs(calculateScrollDistance[0]) > Math.abs(calculateScrollDistance[1])) {
            n = calculateScrollDistance[0];
        }
        else {
            n = calculateScrollDistance[1];
        }
        if (n > 0) {
            return (int)Math.floor(n / computeDistancePerChild);
        }
        return (int)Math.ceil(n / computeDistancePerChild);
    }
    
    @Nullable
    private View findCenterView(final LayoutManager layoutManager, final OrientationHelper orientationHelper) {
        final int childCount = layoutManager.getChildCount();
        View view;
        if (childCount == 0) {
            view = null;
        }
        else {
            View view2 = null;
            int n;
            if (layoutManager.getClipToPadding()) {
                n = orientationHelper.getStartAfterPadding() + orientationHelper.getTotalSpace() / 2;
            }
            else {
                n = orientationHelper.getEnd() / 2;
            }
            int n2 = Integer.MAX_VALUE;
            int n3 = 0;
            while (true) {
                view = view2;
                if (n3 >= childCount) {
                    break;
                }
                final View child = layoutManager.getChildAt(n3);
                final int abs = Math.abs(orientationHelper.getDecoratedStart(child) + orientationHelper.getDecoratedMeasurement(child) / 2 - n);
                int n4;
                if (abs < (n4 = n2)) {
                    n4 = abs;
                    view2 = child;
                }
                ++n3;
                n2 = n4;
            }
        }
        return view;
    }
    
    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull final LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null || this.mHorizontalHelper.mLayoutManager != layoutManager) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }
        return this.mHorizontalHelper;
    }
    
    @NonNull
    private OrientationHelper getVerticalHelper(@NonNull final LayoutManager layoutManager) {
        if (this.mVerticalHelper == null || this.mVerticalHelper.mLayoutManager != layoutManager) {
            this.mVerticalHelper = OrientationHelper.createVerticalHelper(layoutManager);
        }
        return this.mVerticalHelper;
    }
    
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull final LayoutManager layoutManager, @NonNull final View view) {
        final int[] array = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            array[0] = this.distanceToCenter(layoutManager, view, this.getHorizontalHelper(layoutManager));
        }
        else {
            array[0] = 0;
        }
        if (layoutManager.canScrollVertically()) {
            array[1] = this.distanceToCenter(layoutManager, view, this.getVerticalHelper(layoutManager));
            return array;
        }
        array[1] = 0;
        return array;
    }
    
    @Override
    public View findSnapView(final LayoutManager layoutManager) {
        if (layoutManager.canScrollVertically()) {
            return this.findCenterView(layoutManager, this.getVerticalHelper(layoutManager));
        }
        if (layoutManager.canScrollHorizontally()) {
            return this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager));
        }
        return null;
    }
    
    @Override
    public int findTargetSnapPosition(final LayoutManager layoutManager, int estimateNextPositionDiffForFling, int estimateNextPositionDiffForFling2) {
        final int n = -1;
        int n2;
        if (!(layoutManager instanceof ScrollVectorProvider)) {
            n2 = n;
        }
        else {
            final int itemCount = layoutManager.getItemCount();
            n2 = n;
            if (itemCount != 0) {
                final View snapView = this.findSnapView(layoutManager);
                n2 = n;
                if (snapView != null) {
                    final int position = layoutManager.getPosition(snapView);
                    n2 = n;
                    if (position != -1) {
                        final PointF computeScrollVectorForPosition = ((ScrollVectorProvider)layoutManager).computeScrollVectorForPosition(itemCount - 1);
                        n2 = n;
                        if (computeScrollVectorForPosition != null) {
                            if (layoutManager.canScrollHorizontally()) {
                                final int n3 = estimateNextPositionDiffForFling = this.estimateNextPositionDiffForFling(layoutManager, this.getHorizontalHelper(layoutManager), estimateNextPositionDiffForFling, 0);
                                if (computeScrollVectorForPosition.x < 0.0f) {
                                    estimateNextPositionDiffForFling = -n3;
                                }
                            }
                            else {
                                estimateNextPositionDiffForFling = 0;
                            }
                            if (layoutManager.canScrollVertically()) {
                                final int n4 = estimateNextPositionDiffForFling2 = this.estimateNextPositionDiffForFling(layoutManager, this.getVerticalHelper(layoutManager), 0, estimateNextPositionDiffForFling2);
                                if (computeScrollVectorForPosition.y < 0.0f) {
                                    estimateNextPositionDiffForFling2 = -n4;
                                }
                            }
                            else {
                                estimateNextPositionDiffForFling2 = 0;
                            }
                            if (layoutManager.canScrollVertically()) {
                                estimateNextPositionDiffForFling = estimateNextPositionDiffForFling2;
                            }
                            n2 = n;
                            if (estimateNextPositionDiffForFling != 0) {
                                estimateNextPositionDiffForFling2 = position + estimateNextPositionDiffForFling;
                                if ((estimateNextPositionDiffForFling = estimateNextPositionDiffForFling2) < 0) {
                                    estimateNextPositionDiffForFling = 0;
                                }
                                if ((n2 = estimateNextPositionDiffForFling) >= itemCount) {
                                    return itemCount - 1;
                                }
                            }
                        }
                    }
                }
            }
        }
        return n2;
    }
}
