// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v7.widget;

import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat$CollectionItemInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import java.util.Arrays;
import android.view.View$MeasureSpec;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.ViewGroup$LayoutParams;
import android.util.Log;
import android.util.AttributeSet;
import android.content.Context;
import android.view.View;
import android.util.SparseIntArray;
import android.graphics.Rect;

public class GridLayoutManager extends LinearLayoutManager
{
    private static final boolean DEBUG = false;
    public static final int DEFAULT_SPAN_COUNT = -1;
    private static final String TAG = "GridLayoutManager";
    int[] mCachedBorders;
    final Rect mDecorInsets;
    boolean mPendingSpanCountChange;
    final SparseIntArray mPreLayoutSpanIndexCache;
    final SparseIntArray mPreLayoutSpanSizeCache;
    View[] mSet;
    int mSpanCount;
    SpanSizeLookup mSpanSizeLookup;
    
    public GridLayoutManager(final Context context, final int spanCount) {
        super(context);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final int spanCount, final int n, final boolean b) {
        super(context, n, b);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(spanCount);
    }
    
    public GridLayoutManager(final Context context, final AttributeSet set, final int n, final int n2) {
        super(context, set, n, n2);
        this.mPendingSpanCountChange = false;
        this.mSpanCount = -1;
        this.mPreLayoutSpanSizeCache = new SparseIntArray();
        this.mPreLayoutSpanIndexCache = new SparseIntArray();
        this.mSpanSizeLookup = (SpanSizeLookup)new DefaultSpanSizeLookup();
        this.mDecorInsets = new Rect();
        this.setSpanCount(RecyclerView.LayoutManager.getProperties(context, set, n, n2).spanCount);
    }
    
    private void assignSpans(final Recycler recycler, final State state, int i, int n, final boolean b) {
        int n3;
        if (b) {
            final int n2 = 0;
            n3 = i;
            n = 1;
            i = n2;
        }
        else {
            --i;
            n3 = -1;
            n = -1;
        }
        int mSpanIndex = 0;
        while (i != n3) {
            final View view = this.mSet[i];
            final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
            layoutParams.mSpanSize = this.getSpanSize(recycler, state, ((RecyclerView.LayoutManager)this).getPosition(view));
            layoutParams.mSpanIndex = mSpanIndex;
            mSpanIndex += layoutParams.mSpanSize;
            i += n;
        }
    }
    
    private void cachePreLayoutSpanMapping() {
        for (int childCount = ((RecyclerView.LayoutManager)this).getChildCount(), i = 0; i < childCount; ++i) {
            final LayoutParams layoutParams = (LayoutParams)((RecyclerView.LayoutManager)this).getChildAt(i).getLayoutParams();
            final int viewLayoutPosition = ((RecyclerView.LayoutParams)layoutParams).getViewLayoutPosition();
            this.mPreLayoutSpanSizeCache.put(viewLayoutPosition, layoutParams.getSpanSize());
            this.mPreLayoutSpanIndexCache.put(viewLayoutPosition, layoutParams.getSpanIndex());
        }
    }
    
    private void calculateItemBorders(final int n) {
        this.mCachedBorders = calculateItemBorders(this.mCachedBorders, this.mSpanCount, n);
    }
    
    static int[] calculateItemBorders(final int[] array, final int n, int n2) {
        int[] array2 = null;
        Label_0032: {
            if (array != null && array.length == n + 1) {
                array2 = array;
                if (array[array.length - 1] == n2) {
                    break Label_0032;
                }
            }
            array2 = new int[n + 1];
        }
        array2[0] = 0;
        final int n3 = n2 / n;
        final int n4 = n2 % n;
        int n5 = 0;
        n2 = 0;
        for (int i = 1; i <= n; ++i) {
            final int n6 = n3;
            final int n7 = n2 += n4;
            int n8 = n6;
            if (n7 > 0) {
                n2 = n7;
                n8 = n6;
                if (n - n7 < n4) {
                    n8 = n6 + 1;
                    n2 = n7 - n;
                }
            }
            n5 += n8;
            array2[i] = n5;
        }
        return array2;
    }
    
    private void clearPreLayoutSpanMappingCache() {
        this.mPreLayoutSpanSizeCache.clear();
        this.mPreLayoutSpanIndexCache.clear();
    }
    
    private void ensureAnchorIsInCorrectSpan(final Recycler recycler, final State state, final AnchorInfo anchorInfo, int n) {
        int n2 = 1;
        if (n != 1) {
            n2 = 0;
        }
        n = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
        if (n2 != 0) {
            while (n > 0 && anchorInfo.mPosition > 0) {
                --anchorInfo.mPosition;
                n = this.getSpanIndex(recycler, state, anchorInfo.mPosition);
            }
        }
        else {
            int itemCount;
            int i;
            int spanIndex;
            for (itemCount = state.getItemCount(), i = anchorInfo.mPosition; i < itemCount - 1; ++i, n = spanIndex) {
                spanIndex = this.getSpanIndex(recycler, state, i + 1);
                if (spanIndex <= n) {
                    break;
                }
            }
            anchorInfo.mPosition = i;
        }
    }
    
    private void ensureViewSet() {
        if (this.mSet == null || this.mSet.length != this.mSpanCount) {
            this.mSet = new View[this.mSpanCount];
        }
    }
    
    private int getSpanGroupIndex(final Recycler recycler, final State state, final int n) {
        if (!state.isPreLayout()) {
            return this.mSpanSizeLookup.getSpanGroupIndex(n, this.mSpanCount);
        }
        final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
        if (convertPreLayoutPositionToPostLayout == -1) {
            Log.w("GridLayoutManager", "Cannot find span size for pre layout position. " + n);
            return 0;
        }
        return this.mSpanSizeLookup.getSpanGroupIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
    }
    
    private int getSpanIndex(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getCachedSpanIndex(n, this.mSpanCount);
        }
        else if ((n2 = this.mPreLayoutSpanIndexCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 0;
            }
            return this.mSpanSizeLookup.getCachedSpanIndex(convertPreLayoutPositionToPostLayout, this.mSpanCount);
        }
        return n2;
    }
    
    private int getSpanSize(final Recycler recycler, final State state, final int n) {
        int n2;
        if (!state.isPreLayout()) {
            n2 = this.mSpanSizeLookup.getSpanSize(n);
        }
        else if ((n2 = this.mPreLayoutSpanSizeCache.get(n, -1)) == -1) {
            final int convertPreLayoutPositionToPostLayout = recycler.convertPreLayoutPositionToPostLayout(n);
            if (convertPreLayoutPositionToPostLayout == -1) {
                Log.w("GridLayoutManager", "Cannot find span size for pre layout position. It is not cached, not in the adapter. Pos:" + n);
                return 1;
            }
            return this.mSpanSizeLookup.getSpanSize(convertPreLayoutPositionToPostLayout);
        }
        return n2;
    }
    
    private void guessMeasurement(final float n, final int n2) {
        this.calculateItemBorders(Math.max(Math.round(this.mSpanCount * n), n2));
    }
    
    private void measureChild(final View view, int n, final boolean b) {
        final LayoutParams layoutParams = (LayoutParams)view.getLayoutParams();
        final Rect mDecorInsets = layoutParams.mDecorInsets;
        final int n2 = mDecorInsets.top + mDecorInsets.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
        final int n3 = mDecorInsets.left + mDecorInsets.right + layoutParams.leftMargin + layoutParams.rightMargin;
        final int spaceForSpanRange = this.getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
        int n4;
        if (this.mOrientation == 1) {
            n4 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n3, layoutParams.width, false);
            n = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), ((RecyclerView.LayoutManager)this).getHeightMode(), n2, layoutParams.height, true);
        }
        else {
            n = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, n, n2, layoutParams.height, false);
            n4 = RecyclerView.LayoutManager.getChildMeasureSpec(this.mOrientationHelper.getTotalSpace(), ((RecyclerView.LayoutManager)this).getWidthMode(), n3, layoutParams.width, true);
        }
        this.measureChildWithDecorationsAndMargin(view, n4, n, b);
    }
    
    private void measureChildWithDecorationsAndMargin(final View view, final int n, final int n2, final boolean b) {
        final RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams)view.getLayoutParams();
        boolean b2;
        if (b) {
            b2 = ((RecyclerView.LayoutManager)this).shouldReMeasureChild(view, n, n2, layoutParams);
        }
        else {
            b2 = ((RecyclerView.LayoutManager)this).shouldMeasureChild(view, n, n2, layoutParams);
        }
        if (b2) {
            view.measure(n, n2);
        }
    }
    
    private void updateMeasurements() {
        int n;
        if (this.getOrientation() == 1) {
            n = ((RecyclerView.LayoutManager)this).getWidth() - ((RecyclerView.LayoutManager)this).getPaddingRight() - ((RecyclerView.LayoutManager)this).getPaddingLeft();
        }
        else {
            n = ((RecyclerView.LayoutManager)this).getHeight() - ((RecyclerView.LayoutManager)this).getPaddingBottom() - ((RecyclerView.LayoutManager)this).getPaddingTop();
        }
        this.calculateItemBorders(n);
    }
    
    @Override
    public boolean checkLayoutParams(final RecyclerView.LayoutParams layoutParams) {
        return layoutParams instanceof LayoutParams;
    }
    
    @Override
    void collectPrefetchPositionsForLayoutState(final State state, final LayoutState layoutState, final LayoutPrefetchRegistry layoutPrefetchRegistry) {
        int mCurrentPosition;
        for (int mSpanCount = this.mSpanCount, n = 0; n < this.mSpanCount && layoutState.hasMore(state) && mSpanCount > 0; mSpanCount -= this.mSpanSizeLookup.getSpanSize(mCurrentPosition), layoutState.mCurrentPosition += layoutState.mItemDirection, ++n) {
            mCurrentPosition = layoutState.mCurrentPosition;
            layoutPrefetchRegistry.addPosition(mCurrentPosition, Math.max(0, layoutState.mScrollingOffset));
        }
    }
    
    @Override
    View findReferenceChild(final Recycler recycler, final State state, int i, final int n, final int n2) {
        this.ensureLayoutState();
        View view = null;
        View view2 = null;
        final int startAfterPadding = this.mOrientationHelper.getStartAfterPadding();
        final int endAfterPadding = this.mOrientationHelper.getEndAfterPadding();
        int n3;
        if (n > i) {
            n3 = 1;
        }
        else {
            n3 = -1;
        }
        while (i != n) {
            final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
            final int position = ((RecyclerView.LayoutManager)this).getPosition(child);
            View view3 = view;
            View view4 = view2;
            if (position >= 0) {
                view3 = view;
                view4 = view2;
                if (position < n2) {
                    if (this.getSpanIndex(recycler, state, position) != 0) {
                        view4 = view2;
                        view3 = view;
                    }
                    else if (((RecyclerView.LayoutParams)child.getLayoutParams()).isItemRemoved()) {
                        view3 = view;
                        view4 = view2;
                        if (view == null) {
                            view3 = child;
                            view4 = view2;
                        }
                    }
                    else {
                        if (this.mOrientationHelper.getDecoratedStart(child) < endAfterPadding) {
                            final View view5 = child;
                            if (this.mOrientationHelper.getDecoratedEnd(child) >= startAfterPadding) {
                                return view5;
                            }
                        }
                        view3 = view;
                        if ((view4 = view2) == null) {
                            view3 = view;
                            view4 = child;
                        }
                    }
                }
            }
            i += n3;
            view = view3;
            view2 = view4;
        }
        if (view2 == null) {
            view2 = view;
        }
        return view2;
    }
    
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        if (this.mOrientation == 0) {
            return new LayoutParams(-2, -1);
        }
        return new LayoutParams(-1, -2);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final Context context, final AttributeSet set) {
        return new LayoutParams(context, set);
    }
    
    @Override
    public RecyclerView.LayoutParams generateLayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
        if (viewGroup$LayoutParams instanceof ViewGroup$MarginLayoutParams) {
            return new LayoutParams((ViewGroup$MarginLayoutParams)viewGroup$LayoutParams);
        }
        return new LayoutParams(viewGroup$LayoutParams);
    }
    
    @Override
    public int getColumnCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 1) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }
    
    @Override
    public int getRowCountForAccessibility(final Recycler recycler, final State state) {
        if (this.mOrientation == 0) {
            return this.mSpanCount;
        }
        if (state.getItemCount() < 1) {
            return 0;
        }
        return this.getSpanGroupIndex(recycler, state, state.getItemCount() - 1) + 1;
    }
    
    int getSpaceForSpanRange(final int n, final int n2) {
        if (this.mOrientation == 1 && this.isLayoutRTL()) {
            return this.mCachedBorders[this.mSpanCount - n] - this.mCachedBorders[this.mSpanCount - n - n2];
        }
        return this.mCachedBorders[n + n2] - this.mCachedBorders[n];
    }
    
    public int getSpanCount() {
        return this.mSpanCount;
    }
    
    public SpanSizeLookup getSpanSizeLookup() {
        return this.mSpanSizeLookup;
    }
    
    @Override
    void layoutChunk(final Recycler recycler, final State state, final LayoutState layoutState, final LayoutChunkResult layoutChunkResult) {
        final int modeInOther = this.mOrientationHelper.getModeInOther();
        boolean b;
        if (modeInOther != 1073741824) {
            b = true;
        }
        else {
            b = false;
        }
        int n;
        if (((RecyclerView.LayoutManager)this).getChildCount() > 0) {
            n = this.mCachedBorders[this.mSpanCount];
        }
        else {
            n = 0;
        }
        if (b) {
            this.updateMeasurements();
        }
        final boolean b2 = layoutState.mItemDirection == 1;
        final int n2 = 0;
        final int n3 = 0;
        int mSpanCount = this.mSpanCount;
        int n4 = n2;
        int n5 = n3;
        if (!b2) {
            mSpanCount = this.getSpanIndex(recycler, state, layoutState.mCurrentPosition) + this.getSpanSize(recycler, state, layoutState.mCurrentPosition);
            n5 = n3;
            n4 = n2;
        }
        while (n4 < this.mSpanCount && layoutState.hasMore(state) && mSpanCount > 0) {
            final int mCurrentPosition = layoutState.mCurrentPosition;
            final int spanSize = this.getSpanSize(recycler, state, mCurrentPosition);
            if (spanSize > this.mSpanCount) {
                throw new IllegalArgumentException("Item at position " + mCurrentPosition + " requires " + spanSize + " spans but GridLayoutManager has only " + this.mSpanCount + " spans.");
            }
            mSpanCount -= spanSize;
            if (mSpanCount < 0) {
                break;
            }
            final View next = layoutState.next(recycler);
            if (next == null) {
                break;
            }
            n5 += spanSize;
            this.mSet[n4] = next;
            ++n4;
        }
        if (n4 == 0) {
            layoutChunkResult.mFinished = true;
            return;
        }
        int n6 = 0;
        float n7 = 0.0f;
        this.assignSpans(recycler, state, n4, n5, b2);
        int n8;
        float n10;
        for (int i = 0; i < n4; ++i, n6 = n8, n7 = n10) {
            final View view = this.mSet[i];
            if (layoutState.mScrapList == null) {
                if (b2) {
                    ((RecyclerView.LayoutManager)this).addView(view);
                }
                else {
                    ((RecyclerView.LayoutManager)this).addView(view, 0);
                }
            }
            else if (b2) {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view);
            }
            else {
                ((RecyclerView.LayoutManager)this).addDisappearingView(view, 0);
            }
            ((RecyclerView.LayoutManager)this).calculateItemDecorationsForChild(view, this.mDecorInsets);
            this.measureChild(view, modeInOther, false);
            final int decoratedMeasurement = this.mOrientationHelper.getDecoratedMeasurement(view);
            n8 = n6;
            if (decoratedMeasurement > n6) {
                n8 = decoratedMeasurement;
            }
            final float n9 = 1.0f * this.mOrientationHelper.getDecoratedMeasurementInOther(view) / ((LayoutParams)view.getLayoutParams()).mSpanSize;
            n10 = n7;
            if (n9 > n7) {
                n10 = n9;
            }
        }
        int mConsumed = n6;
        if (b) {
            this.guessMeasurement(n7, n);
            int n11 = 0;
            int n12 = 0;
            while (true) {
                mConsumed = n11;
                if (n12 >= n4) {
                    break;
                }
                final View view2 = this.mSet[n12];
                this.measureChild(view2, 1073741824, true);
                final int decoratedMeasurement2 = this.mOrientationHelper.getDecoratedMeasurement(view2);
                int n13;
                if (decoratedMeasurement2 > (n13 = n11)) {
                    n13 = decoratedMeasurement2;
                }
                ++n12;
                n11 = n13;
            }
        }
        for (int j = 0; j < n4; ++j) {
            final View view3 = this.mSet[j];
            if (this.mOrientationHelper.getDecoratedMeasurement(view3) != mConsumed) {
                final LayoutParams layoutParams = (LayoutParams)view3.getLayoutParams();
                final Rect mDecorInsets = layoutParams.mDecorInsets;
                final int n14 = mDecorInsets.top + mDecorInsets.bottom + layoutParams.topMargin + layoutParams.bottomMargin;
                final int n15 = mDecorInsets.left + mDecorInsets.right + layoutParams.leftMargin + layoutParams.rightMargin;
                final int spaceForSpanRange = this.getSpaceForSpanRange(layoutParams.mSpanIndex, layoutParams.mSpanSize);
                int n16;
                int n17;
                if (this.mOrientation == 1) {
                    n16 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n15, layoutParams.width, false);
                    n17 = View$MeasureSpec.makeMeasureSpec(mConsumed - n14, 1073741824);
                }
                else {
                    n16 = View$MeasureSpec.makeMeasureSpec(mConsumed - n15, 1073741824);
                    n17 = RecyclerView.LayoutManager.getChildMeasureSpec(spaceForSpanRange, 1073741824, n14, layoutParams.height, false);
                }
                this.measureChildWithDecorationsAndMargin(view3, n16, n17, true);
            }
        }
        layoutChunkResult.mConsumed = mConsumed;
        int mOffset = 0;
        final boolean b3 = false;
        int mOffset2 = 0;
        int mOffset3 = 0;
        int n18;
        if (this.mOrientation == 1) {
            if (layoutState.mLayoutDirection == -1) {
                mOffset3 = layoutState.mOffset;
                mOffset2 = mOffset3 - mConsumed;
                n18 = (b3 ? 1 : 0);
            }
            else {
                mOffset2 = layoutState.mOffset;
                mOffset3 = mOffset2 + mConsumed;
                n18 = (b3 ? 1 : 0);
            }
        }
        else if (layoutState.mLayoutDirection == -1) {
            final int mOffset4 = layoutState.mOffset;
            mOffset = mOffset4 - mConsumed;
            n18 = mOffset4;
        }
        else {
            mOffset = layoutState.mOffset;
            n18 = mOffset + mConsumed;
        }
        int k = 0;
        int n19 = n18;
        int n20 = mOffset2;
        while (k < n4) {
            final View view4 = this.mSet[k];
            final LayoutParams layoutParams2 = (LayoutParams)view4.getLayoutParams();
            int n21;
            if (this.mOrientation == 1) {
                if (this.isLayoutRTL()) {
                    n21 = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[this.mSpanCount - layoutParams2.mSpanIndex];
                    mOffset = n21 - this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
                else {
                    mOffset = ((RecyclerView.LayoutManager)this).getPaddingLeft() + this.mCachedBorders[layoutParams2.mSpanIndex];
                    n21 = mOffset + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                }
            }
            else {
                n20 = ((RecyclerView.LayoutManager)this).getPaddingTop() + this.mCachedBorders[layoutParams2.mSpanIndex];
                mOffset3 = n20 + this.mOrientationHelper.getDecoratedMeasurementInOther(view4);
                n21 = n19;
            }
            ((RecyclerView.LayoutManager)this).layoutDecoratedWithMargins(view4, mOffset, n20, n21, mOffset3);
            if (((RecyclerView.LayoutParams)layoutParams2).isItemRemoved() || ((RecyclerView.LayoutParams)layoutParams2).isItemChanged()) {
                layoutChunkResult.mIgnoreConsumed = true;
            }
            layoutChunkResult.mFocusable |= view4.hasFocusable();
            ++k;
            n19 = n21;
        }
        Arrays.fill(this.mSet, null);
    }
    
    @Override
    void onAnchorReady(final Recycler recycler, final State state, final AnchorInfo anchorInfo, final int n) {
        super.onAnchorReady(recycler, state, anchorInfo, n);
        this.updateMeasurements();
        if (state.getItemCount() > 0 && !state.isPreLayout()) {
            this.ensureAnchorIsInCorrectSpan(recycler, state, anchorInfo, n);
        }
        this.ensureViewSet();
    }
    
    @Override
    public View onFocusSearchFailed(View view, int n, final Recycler recycler, final State state) {
        final View containingItemView = ((RecyclerView.LayoutManager)this).findContainingItemView(view);
        View view2;
        if (containingItemView == null) {
            view2 = null;
        }
        else {
            final LayoutParams layoutParams = (LayoutParams)containingItemView.getLayoutParams();
            final int mSpanIndex = layoutParams.mSpanIndex;
            final int n2 = layoutParams.mSpanIndex + layoutParams.mSpanSize;
            if (super.onFocusSearchFailed(view, n, recycler, state) == null) {
                return null;
            }
            if (this.convertFocusDirectionToLayoutDirection(n) == 1 != this.mShouldReverseLayout) {
                n = 1;
            }
            else {
                n = 0;
            }
            int n3;
            int childCount;
            if (n != 0) {
                n = ((RecyclerView.LayoutManager)this).getChildCount() - 1;
                n3 = -1;
                childCount = -1;
            }
            else {
                n = 0;
                n3 = 1;
                childCount = ((RecyclerView.LayoutManager)this).getChildCount();
            }
            final boolean b = this.mOrientation == 1 && this.isLayoutRTL();
            View view3 = null;
            int n4 = -1;
            int n5 = 0;
            view = null;
            int n6 = -1;
            int n7 = 0;
            final int spanGroupIndex = this.getSpanGroupIndex(recycler, state, n);
            int mSpanIndex2;
            int n8;
            View view4;
            int mSpanIndex3;
            int n9;
            View view5;
            for (int i = n; i != childCount; i += n3, view3 = view5, n5 = n9, n4 = mSpanIndex3, view = view4, n7 = n8, n6 = mSpanIndex2) {
                n = this.getSpanGroupIndex(recycler, state, i);
                final View child = ((RecyclerView.LayoutManager)this).getChildAt(i);
                if (child == containingItemView) {
                    break;
                }
                if (child.hasFocusable() && n != spanGroupIndex) {
                    if (view3 != null) {
                        break;
                    }
                    mSpanIndex2 = n6;
                    n8 = n7;
                    view4 = view;
                    mSpanIndex3 = n4;
                    n9 = n5;
                    view5 = view3;
                }
                else {
                    final LayoutParams layoutParams2 = (LayoutParams)child.getLayoutParams();
                    final int mSpanIndex4 = layoutParams2.mSpanIndex;
                    final int n10 = layoutParams2.mSpanIndex + layoutParams2.mSpanSize;
                    if (child.hasFocusable() && mSpanIndex4 == mSpanIndex) {
                        view2 = child;
                        if (n10 == n2) {
                            return view2;
                        }
                    }
                    final int n11 = 0;
                    if ((child.hasFocusable() && view3 == null) || (!child.hasFocusable() && view == null)) {
                        n = 1;
                    }
                    else {
                        n = Math.max(mSpanIndex4, mSpanIndex);
                        final int n12 = Math.min(n10, n2) - n;
                        if (child.hasFocusable()) {
                            if (n12 > n5) {
                                n = 1;
                            }
                            else {
                                n = n11;
                                if (n12 == n5) {
                                    final boolean b2 = mSpanIndex4 > n4;
                                    n = n11;
                                    if (b == b2) {
                                        n = 1;
                                    }
                                }
                            }
                        }
                        else {
                            n = n11;
                            if (view3 == null) {
                                n = n11;
                                if (((RecyclerView.LayoutManager)this).isViewPartiallyVisible(child, false, true)) {
                                    if (n12 > n7) {
                                        n = 1;
                                    }
                                    else {
                                        n = n11;
                                        if (n12 == n7) {
                                            final boolean b3 = mSpanIndex4 > n6;
                                            n = n11;
                                            if (b == b3) {
                                                n = 1;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                    view5 = view3;
                    n9 = n5;
                    mSpanIndex3 = n4;
                    view4 = view;
                    n8 = n7;
                    mSpanIndex2 = n6;
                    if (n != 0) {
                        if (child.hasFocusable()) {
                            mSpanIndex3 = layoutParams2.mSpanIndex;
                            n9 = Math.min(n10, n2) - Math.max(mSpanIndex4, mSpanIndex);
                            view5 = child;
                            view4 = view;
                            n8 = n7;
                            mSpanIndex2 = n6;
                        }
                        else {
                            mSpanIndex2 = layoutParams2.mSpanIndex;
                            n8 = Math.min(n10, n2) - Math.max(mSpanIndex4, mSpanIndex);
                            view5 = view3;
                            n9 = n5;
                            mSpanIndex3 = n4;
                            view4 = child;
                        }
                    }
                }
            }
            if (view3 == null) {
                view3 = view;
            }
            return view3;
        }
        return view2;
    }
    
    @Override
    public void onInitializeAccessibilityNodeInfoForItem(final Recycler recycler, final State state, final View view, final AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
        final ViewGroup$LayoutParams layoutParams = view.getLayoutParams();
        if (!(layoutParams instanceof LayoutParams)) {
            super.onInitializeAccessibilityNodeInfoForItem(view, accessibilityNodeInfoCompat);
            return;
        }
        final LayoutParams layoutParams2 = (LayoutParams)layoutParams;
        final int spanGroupIndex = this.getSpanGroupIndex(recycler, state, ((RecyclerView.LayoutParams)layoutParams2).getViewLayoutPosition());
        if (this.mOrientation == 0) {
            accessibilityNodeInfoCompat.setCollectionItemInfo((Object)AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), spanGroupIndex, 1, this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
            return;
        }
        accessibilityNodeInfoCompat.setCollectionItemInfo((Object)AccessibilityNodeInfoCompat$CollectionItemInfoCompat.obtain(spanGroupIndex, 1, layoutParams2.getSpanIndex(), layoutParams2.getSpanSize(), this.mSpanCount > 1 && layoutParams2.getSpanSize() == this.mSpanCount, false));
    }
    
    @Override
    public void onItemsAdded(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsChanged(final RecyclerView recyclerView) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsMoved(final RecyclerView recyclerView, final int n, final int n2, final int n3) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsRemoved(final RecyclerView recyclerView, final int n, final int n2) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onItemsUpdated(final RecyclerView recyclerView, final int n, final int n2, final Object o) {
        this.mSpanSizeLookup.invalidateSpanIndexCache();
    }
    
    @Override
    public void onLayoutChildren(final Recycler recycler, final State state) {
        if (state.isPreLayout()) {
            this.cachePreLayoutSpanMapping();
        }
        super.onLayoutChildren(recycler, state);
        this.clearPreLayoutSpanMappingCache();
    }
    
    @Override
    public void onLayoutCompleted(final State state) {
        super.onLayoutCompleted(state);
        this.mPendingSpanCountChange = false;
    }
    
    @Override
    public int scrollHorizontallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollHorizontallyBy(n, recycler, state);
    }
    
    @Override
    public int scrollVerticallyBy(final int n, final Recycler recycler, final State state) {
        this.updateMeasurements();
        this.ensureViewSet();
        return super.scrollVerticallyBy(n, recycler, state);
    }
    
    @Override
    public void setMeasuredDimension(final Rect rect, int chooseSize, int chooseSize2) {
        if (this.mCachedBorders == null) {
            super.setMeasuredDimension(rect, chooseSize, chooseSize2);
        }
        final int n = ((RecyclerView.LayoutManager)this).getPaddingLeft() + ((RecyclerView.LayoutManager)this).getPaddingRight();
        final int n2 = ((RecyclerView.LayoutManager)this).getPaddingTop() + ((RecyclerView.LayoutManager)this).getPaddingBottom();
        if (this.mOrientation == 1) {
            final int chooseSize3 = RecyclerView.LayoutManager.chooseSize(chooseSize2, rect.height() + n2, ((RecyclerView.LayoutManager)this).getMinimumHeight());
            chooseSize2 = RecyclerView.LayoutManager.chooseSize(chooseSize, this.mCachedBorders[this.mCachedBorders.length - 1] + n, ((RecyclerView.LayoutManager)this).getMinimumWidth());
            chooseSize = chooseSize3;
        }
        else {
            final int chooseSize4 = RecyclerView.LayoutManager.chooseSize(chooseSize, rect.width() + n, ((RecyclerView.LayoutManager)this).getMinimumWidth());
            chooseSize = RecyclerView.LayoutManager.chooseSize(chooseSize2, this.mCachedBorders[this.mCachedBorders.length - 1] + n2, ((RecyclerView.LayoutManager)this).getMinimumHeight());
            chooseSize2 = chooseSize4;
        }
        ((RecyclerView.LayoutManager)this).setMeasuredDimension(chooseSize2, chooseSize);
    }
    
    public void setSpanCount(final int mSpanCount) {
        if (mSpanCount == this.mSpanCount) {
            return;
        }
        this.mPendingSpanCountChange = true;
        if (mSpanCount < 1) {
            throw new IllegalArgumentException("Span count should be at least 1. Provided " + mSpanCount);
        }
        this.mSpanCount = mSpanCount;
        this.mSpanSizeLookup.invalidateSpanIndexCache();
        ((RecyclerView.LayoutManager)this).requestLayout();
    }
    
    public void setSpanSizeLookup(final SpanSizeLookup mSpanSizeLookup) {
        this.mSpanSizeLookup = mSpanSizeLookup;
    }
    
    @Override
    public void setStackFromEnd(final boolean b) {
        if (b) {
            throw new UnsupportedOperationException("GridLayoutManager does not support stack from end. Consider using reverse layout");
        }
        super.setStackFromEnd(false);
    }
    
    @Override
    public boolean supportsPredictiveItemAnimations() {
        return this.mPendingSavedState == null && !this.mPendingSpanCountChange;
    }
    
    public static final class DefaultSpanSizeLookup extends SpanSizeLookup
    {
        @Override
        public int getSpanIndex(final int n, final int n2) {
            return n % n2;
        }
        
        @Override
        public int getSpanSize(final int n) {
            return 1;
        }
    }
    
    public static class LayoutParams extends RecyclerView.LayoutParams
    {
        public static final int INVALID_SPAN_ID = -1;
        int mSpanIndex;
        int mSpanSize;
        
        public LayoutParams(final int n, final int n2) {
            super(n, n2);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final Context context, final AttributeSet set) {
            super(context, set);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final RecyclerView.LayoutParams layoutParams) {
            super(layoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$LayoutParams viewGroup$LayoutParams) {
            super(viewGroup$LayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public LayoutParams(final ViewGroup$MarginLayoutParams viewGroup$MarginLayoutParams) {
            super(viewGroup$MarginLayoutParams);
            this.mSpanIndex = -1;
            this.mSpanSize = 0;
        }
        
        public int getSpanIndex() {
            return this.mSpanIndex;
        }
        
        public int getSpanSize() {
            return this.mSpanSize;
        }
    }
    
    public abstract static class SpanSizeLookup
    {
        private boolean mCacheSpanIndices;
        final SparseIntArray mSpanIndexCache;
        
        public SpanSizeLookup() {
            this.mSpanIndexCache = new SparseIntArray();
            this.mCacheSpanIndices = false;
        }
        
        int findReferenceIndexFromCache(int n) {
            int i = 0;
            int n2 = this.mSpanIndexCache.size() - 1;
            while (i <= n2) {
                final int n3 = i + n2 >>> 1;
                if (this.mSpanIndexCache.keyAt(n3) < n) {
                    i = n3 + 1;
                }
                else {
                    n2 = n3 - 1;
                }
            }
            n = i - 1;
            if (n >= 0 && n < this.mSpanIndexCache.size()) {
                return this.mSpanIndexCache.keyAt(n);
            }
            return -1;
        }
        
        int getCachedSpanIndex(final int n, int spanIndex) {
            int n2;
            if (!this.mCacheSpanIndices) {
                n2 = this.getSpanIndex(n, spanIndex);
            }
            else if ((n2 = this.mSpanIndexCache.get(n, -1)) == -1) {
                spanIndex = this.getSpanIndex(n, spanIndex);
                this.mSpanIndexCache.put(n, spanIndex);
                return spanIndex;
            }
            return n2;
        }
        
        public int getSpanGroupIndex(int n, final int n2) {
            int n3 = 0;
            int n4 = 0;
            final int spanSize = this.getSpanSize(n);
            int n6;
            for (int i = 0; i < n; ++i, n4 = n6) {
                final int spanSize2 = this.getSpanSize(i);
                final int n5 = n3 + spanSize2;
                if (n5 == n2) {
                    n3 = 0;
                    n6 = n4 + 1;
                }
                else {
                    n6 = n4;
                    if ((n3 = n5) > n2) {
                        n3 = spanSize2;
                        n6 = n4 + 1;
                    }
                }
            }
            n = n4;
            if (n3 + spanSize > n2) {
                n = n4 + 1;
            }
            return n;
        }
        
        public int getSpanIndex(int n, final int n2) {
            final int spanSize = this.getSpanSize(n);
            if (spanSize == n2) {
                n = 0;
            }
            else {
                final int n3 = 0;
                final int n4 = 0;
                int n5 = n3;
                int i = n4;
                if (this.mCacheSpanIndices) {
                    n5 = n3;
                    i = n4;
                    if (this.mSpanIndexCache.size() > 0) {
                        final int referenceIndexFromCache = this.findReferenceIndexFromCache(n);
                        n5 = n3;
                        i = n4;
                        if (referenceIndexFromCache >= 0) {
                            n5 = this.mSpanIndexCache.get(referenceIndexFromCache) + this.getSpanSize(referenceIndexFromCache);
                            i = referenceIndexFromCache + 1;
                        }
                    }
                }
                while (i < n) {
                    final int spanSize2 = this.getSpanSize(i);
                    final int n6 = n5 + spanSize2;
                    if (n6 == n2) {
                        n5 = 0;
                    }
                    else if ((n5 = n6) > n2) {
                        n5 = spanSize2;
                    }
                    ++i;
                }
                n = n5;
                if (n5 + spanSize > n2) {
                    return 0;
                }
            }
            return n;
        }
        
        public abstract int getSpanSize(final int p0);
        
        public void invalidateSpanIndexCache() {
            this.mSpanIndexCache.clear();
        }
        
        public boolean isSpanIndexCacheEnabled() {
            return this.mCacheSpanIndices;
        }
        
        public void setSpanIndexCacheEnabled(final boolean mCacheSpanIndices) {
            this.mCacheSpanIndices = mCacheSpanIndices;
        }
    }
}
