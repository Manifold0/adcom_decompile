// 
// Decompiled by Procyon v0.5.34
// 

package android.support.v7.widget.helper;

import android.support.v4.animation.AnimatorUpdateListenerCompat;
import android.support.v4.animation.AnimatorCompatHelper;
import android.support.v4.animation.AnimatorListenerCompat;
import android.view.GestureDetector$SimpleOnGestureListener;
import android.view.animation.Interpolator;
import android.util.Log;
import android.view.ViewParent;
import android.support.v4.animation.ValueAnimatorCompat;
import android.graphics.Canvas;
import android.content.res.Resources;
import android.support.v7.recyclerview.R$dimen;
import android.support.annotation.Nullable;
import android.view.ViewConfiguration;
import android.view.GestureDetector$OnGestureListener;
import android.support.v4.view.VelocityTrackerCompat;
import android.os.Build$VERSION;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import android.view.VelocityTracker;
import android.graphics.Rect;
import android.view.View;
import android.support.v4.view.GestureDetectorCompat;
import java.util.List;
import android.support.v7.widget.RecyclerView;

public class ItemTouchHelper extends ItemDecoration implements OnChildAttachStateChangeListener
{
    static final int ACTION_MODE_DRAG_MASK = 16711680;
    private static final int ACTION_MODE_IDLE_MASK = 255;
    static final int ACTION_MODE_SWIPE_MASK = 65280;
    public static final int ACTION_STATE_DRAG = 2;
    public static final int ACTION_STATE_IDLE = 0;
    public static final int ACTION_STATE_SWIPE = 1;
    static final int ACTIVE_POINTER_ID_NONE = -1;
    public static final int ANIMATION_TYPE_DRAG = 8;
    public static final int ANIMATION_TYPE_SWIPE_CANCEL = 4;
    public static final int ANIMATION_TYPE_SWIPE_SUCCESS = 2;
    static final boolean DEBUG = false;
    static final int DIRECTION_FLAG_COUNT = 8;
    public static final int DOWN = 2;
    public static final int END = 32;
    public static final int LEFT = 4;
    private static final int PIXELS_PER_SECOND = 1000;
    public static final int RIGHT = 8;
    public static final int START = 16;
    static final String TAG = "ItemTouchHelper";
    public static final int UP = 1;
    int mActionState;
    int mActivePointerId;
    Callback mCallback;
    private ChildDrawingOrderCallback mChildDrawingOrderCallback;
    private List<Integer> mDistances;
    private long mDragScrollStartTimeInMs;
    float mDx;
    float mDy;
    GestureDetectorCompat mGestureDetector;
    float mInitialTouchX;
    float mInitialTouchY;
    float mMaxSwipeVelocity;
    private final OnItemTouchListener mOnItemTouchListener;
    View mOverdrawChild;
    int mOverdrawChildPosition;
    final List<View> mPendingCleanup;
    List<RecoverAnimation> mRecoverAnimations;
    RecyclerView mRecyclerView;
    final Runnable mScrollRunnable;
    ViewHolder mSelected;
    int mSelectedFlags;
    float mSelectedStartX;
    float mSelectedStartY;
    private int mSlop;
    private List<ViewHolder> mSwapTargets;
    float mSwipeEscapeVelocity;
    private final float[] mTmpPosition;
    private Rect mTmpRect;
    VelocityTracker mVelocityTracker;
    
    public ItemTouchHelper(final Callback mCallback) {
        this.mPendingCleanup = new ArrayList<View>();
        this.mTmpPosition = new float[2];
        this.mSelected = null;
        this.mActivePointerId = -1;
        this.mActionState = 0;
        this.mRecoverAnimations = new ArrayList<RecoverAnimation>();
        this.mScrollRunnable = new Runnable() {
            @Override
            public void run() {
                if (ItemTouchHelper.this.mSelected != null && ItemTouchHelper.this.scrollIfNecessary()) {
                    if (ItemTouchHelper.this.mSelected != null) {
                        ItemTouchHelper.this.moveIfNecessary(ItemTouchHelper.this.mSelected);
                    }
                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                    ViewCompat.postOnAnimation((View)ItemTouchHelper.this.mRecyclerView, (Runnable)this);
                }
            }
        };
        this.mChildDrawingOrderCallback = null;
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.mOnItemTouchListener = new OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
                final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                if (actionMasked == 0) {
                    ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(0);
                    ItemTouchHelper.this.mInitialTouchX = motionEvent.getX();
                    ItemTouchHelper.this.mInitialTouchY = motionEvent.getY();
                    ItemTouchHelper.this.obtainVelocityTracker();
                    if (ItemTouchHelper.this.mSelected == null) {
                        final RecoverAnimation animation = ItemTouchHelper.this.findAnimation(motionEvent);
                        if (animation != null) {
                            final ItemTouchHelper this$0 = ItemTouchHelper.this;
                            this$0.mInitialTouchX -= animation.mX;
                            final ItemTouchHelper this$2 = ItemTouchHelper.this;
                            this$2.mInitialTouchY -= animation.mY;
                            ItemTouchHelper.this.endRecoverAnimation(animation.mViewHolder, true);
                            if (ItemTouchHelper.this.mPendingCleanup.remove(animation.mViewHolder.itemView)) {
                                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, animation.mViewHolder);
                            }
                            ItemTouchHelper.this.select(animation.mViewHolder, animation.mActionState);
                            ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, 0);
                        }
                    }
                }
                else if (actionMasked == 3 || actionMasked == 1) {
                    ItemTouchHelper.this.mActivePointerId = -1;
                    ItemTouchHelper.this.select(null, 0);
                }
                else if (ItemTouchHelper.this.mActivePointerId != -1) {
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if (pointerIndex >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, pointerIndex);
                    }
                }
                if (ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
                }
                return ItemTouchHelper.this.mSelected != null;
            }
            
            @Override
            public void onRequestDisallowInterceptTouchEvent(final boolean b) {
                if (!b) {
                    return;
                }
                ItemTouchHelper.this.select(null, 0);
            }
            
            @Override
            public void onTouchEvent(final RecyclerView recyclerView, final MotionEvent motionEvent) {
                int n = 0;
                ItemTouchHelper.this.mGestureDetector.onTouchEvent(motionEvent);
                if (ItemTouchHelper.this.mVelocityTracker != null) {
                    ItemTouchHelper.this.mVelocityTracker.addMovement(motionEvent);
                }
                if (ItemTouchHelper.this.mActivePointerId != -1) {
                    final int actionMasked = MotionEventCompat.getActionMasked(motionEvent);
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    if (pointerIndex >= 0) {
                        ItemTouchHelper.this.checkSelectForSwipe(actionMasked, motionEvent, pointerIndex);
                    }
                    final ViewHolder mSelected = ItemTouchHelper.this.mSelected;
                    if (mSelected != null) {
                        switch (actionMasked) {
                            default: {}
                            case 3: {
                                if (ItemTouchHelper.this.mVelocityTracker != null) {
                                    ItemTouchHelper.this.mVelocityTracker.clear();
                                }
                            }
                            case 1: {
                                ItemTouchHelper.this.select(null, 0);
                                ItemTouchHelper.this.mActivePointerId = -1;
                            }
                            case 2: {
                                if (pointerIndex >= 0) {
                                    ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, pointerIndex);
                                    ItemTouchHelper.this.moveIfNecessary(mSelected);
                                    ItemTouchHelper.this.mRecyclerView.removeCallbacks(ItemTouchHelper.this.mScrollRunnable);
                                    ItemTouchHelper.this.mScrollRunnable.run();
                                    ItemTouchHelper.this.mRecyclerView.invalidate();
                                    return;
                                }
                                break;
                            }
                            case 6: {
                                final int actionIndex = MotionEventCompat.getActionIndex(motionEvent);
                                if (motionEvent.getPointerId(actionIndex) == ItemTouchHelper.this.mActivePointerId) {
                                    if (actionIndex == 0) {
                                        n = 1;
                                    }
                                    ItemTouchHelper.this.mActivePointerId = motionEvent.getPointerId(n);
                                    ItemTouchHelper.this.updateDxDy(motionEvent, ItemTouchHelper.this.mSelectedFlags, actionIndex);
                                    return;
                                }
                                break;
                            }
                        }
                    }
                }
            }
        };
        this.mCallback = mCallback;
    }
    
    private void addChildDrawingOrderCallback() {
        if (Build$VERSION.SDK_INT >= 21) {
            return;
        }
        if (this.mChildDrawingOrderCallback == null) {
            this.mChildDrawingOrderCallback = new ChildDrawingOrderCallback() {
                @Override
                public int onGetChildDrawingOrder(final int n, final int n2) {
                    if (ItemTouchHelper.this.mOverdrawChild != null) {
                        int mOverdrawChildPosition;
                        if ((mOverdrawChildPosition = ItemTouchHelper.this.mOverdrawChildPosition) == -1) {
                            mOverdrawChildPosition = ItemTouchHelper.this.mRecyclerView.indexOfChild(ItemTouchHelper.this.mOverdrawChild);
                            ItemTouchHelper.this.mOverdrawChildPosition = mOverdrawChildPosition;
                        }
                        if (n2 == n - 1) {
                            return mOverdrawChildPosition;
                        }
                        if (n2 >= mOverdrawChildPosition) {
                            return n2 + 1;
                        }
                    }
                    return n2;
                }
            };
        }
        this.mRecyclerView.setChildDrawingOrderCallback(this.mChildDrawingOrderCallback);
    }
    
    private int checkHorizontalSwipe(final ViewHolder viewHolder, final int n) {
        if ((n & 0xC) != 0x0) {
            int n2;
            if (this.mDx > 0.0f) {
                n2 = 8;
            }
            else {
                n2 = 4;
            }
            if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                final float xVelocity = VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId);
                final float yVelocity = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
                int n3;
                if (xVelocity > 0.0f) {
                    n3 = 8;
                }
                else {
                    n3 = 4;
                }
                final float abs = Math.abs(xVelocity);
                if ((n3 & n) != 0x0 && n2 == n3 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(yVelocity)) {
                    return n3;
                }
            }
            final float n4 = (float)this.mRecyclerView.getWidth();
            final float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder);
            if ((n & n2) != 0x0 && Math.abs(this.mDx) > n4 * swipeThreshold) {
                return n2;
            }
        }
        return 0;
    }
    
    private int checkVerticalSwipe(final ViewHolder viewHolder, final int n) {
        if ((n & 0x3) != 0x0) {
            int n2;
            if (this.mDy > 0.0f) {
                n2 = 2;
            }
            else {
                n2 = 1;
            }
            if (this.mVelocityTracker != null && this.mActivePointerId > -1) {
                this.mVelocityTracker.computeCurrentVelocity(1000, this.mCallback.getSwipeVelocityThreshold(this.mMaxSwipeVelocity));
                final float xVelocity = VelocityTrackerCompat.getXVelocity(this.mVelocityTracker, this.mActivePointerId);
                final float yVelocity = VelocityTrackerCompat.getYVelocity(this.mVelocityTracker, this.mActivePointerId);
                int n3;
                if (yVelocity > 0.0f) {
                    n3 = 2;
                }
                else {
                    n3 = 1;
                }
                final float abs = Math.abs(yVelocity);
                if ((n3 & n) != 0x0 && n3 == n2 && abs >= this.mCallback.getSwipeEscapeVelocity(this.mSwipeEscapeVelocity) && abs > Math.abs(xVelocity)) {
                    return n3;
                }
            }
            final float n4 = (float)this.mRecyclerView.getHeight();
            final float swipeThreshold = this.mCallback.getSwipeThreshold(viewHolder);
            if ((n & n2) != 0x0 && Math.abs(this.mDy) > n4 * swipeThreshold) {
                return n2;
            }
        }
        return 0;
    }
    
    private void destroyCallbacks() {
        this.mRecyclerView.removeItemDecoration((RecyclerView.ItemDecoration)this);
        this.mRecyclerView.removeOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.removeOnChildAttachStateChangeListener((RecyclerView.OnChildAttachStateChangeListener)this);
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; --i) {
            this.mCallback.clearView(this.mRecyclerView, this.mRecoverAnimations.get(0).mViewHolder);
        }
        this.mRecoverAnimations.clear();
        this.mOverdrawChild = null;
        this.mOverdrawChildPosition = -1;
        this.releaseVelocityTracker();
    }
    
    private List<ViewHolder> findSwapTargets(final ViewHolder viewHolder) {
        if (this.mSwapTargets == null) {
            this.mSwapTargets = new ArrayList<ViewHolder>();
            this.mDistances = new ArrayList<Integer>();
        }
        else {
            this.mSwapTargets.clear();
            this.mDistances.clear();
        }
        final int boundingBoxMargin = this.mCallback.getBoundingBoxMargin();
        final int n = Math.round(this.mSelectedStartX + this.mDx) - boundingBoxMargin;
        final int n2 = Math.round(this.mSelectedStartY + this.mDy) - boundingBoxMargin;
        final int n3 = viewHolder.itemView.getWidth() + n + boundingBoxMargin * 2;
        final int n4 = viewHolder.itemView.getHeight() + n2 + boundingBoxMargin * 2;
        final int n5 = (n + n3) / 2;
        final int n6 = (n2 + n4) / 2;
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        for (int childCount = layoutManager.getChildCount(), i = 0; i < childCount; ++i) {
            final View child = layoutManager.getChildAt(i);
            if (child != viewHolder.itemView && child.getBottom() >= n2 && child.getTop() <= n4 && child.getRight() >= n && child.getLeft() <= n3) {
                final RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(child);
                if (this.mCallback.canDropOver(this.mRecyclerView, this.mSelected, childViewHolder)) {
                    final int abs = Math.abs(n5 - (child.getLeft() + child.getRight()) / 2);
                    final int abs2 = Math.abs(n6 - (child.getTop() + child.getBottom()) / 2);
                    final int n7 = abs * abs + abs2 * abs2;
                    int n8 = 0;
                    for (int size = this.mSwapTargets.size(), n9 = 0; n9 < size && n7 > this.mDistances.get(n9); ++n9) {
                        ++n8;
                    }
                    this.mSwapTargets.add(n8, childViewHolder);
                    this.mDistances.add(n8, n7);
                }
            }
        }
        return this.mSwapTargets;
    }
    
    private ViewHolder findSwipedView(final MotionEvent motionEvent) {
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (this.mActivePointerId != -1) {
            final int pointerIndex = motionEvent.findPointerIndex(this.mActivePointerId);
            final float x = motionEvent.getX(pointerIndex);
            final float mInitialTouchX = this.mInitialTouchX;
            final float y = motionEvent.getY(pointerIndex);
            final float mInitialTouchY = this.mInitialTouchY;
            final float abs = Math.abs(x - mInitialTouchX);
            final float abs2 = Math.abs(y - mInitialTouchY);
            if ((abs >= this.mSlop || abs2 >= this.mSlop) && (abs <= abs2 || !layoutManager.canScrollHorizontally()) && (abs2 <= abs || !layoutManager.canScrollVertically())) {
                final View childView = this.findChildView(motionEvent);
                if (childView != null) {
                    return this.mRecyclerView.getChildViewHolder(childView);
                }
            }
        }
        return null;
    }
    
    private void getSelectedDxDy(final float[] array) {
        if ((this.mSelectedFlags & 0xC) != 0x0) {
            array[0] = this.mSelectedStartX + this.mDx - this.mSelected.itemView.getLeft();
        }
        else {
            array[0] = ViewCompat.getTranslationX(this.mSelected.itemView);
        }
        if ((this.mSelectedFlags & 0x3) != 0x0) {
            array[1] = this.mSelectedStartY + this.mDy - this.mSelected.itemView.getTop();
            return;
        }
        array[1] = ViewCompat.getTranslationY(this.mSelected.itemView);
    }
    
    private static boolean hitTest(final View view, final float n, final float n2, final float n3, final float n4) {
        return n >= n3 && n <= view.getWidth() + n3 && n2 >= n4 && n2 <= view.getHeight() + n4;
    }
    
    private void initGestureDetector() {
        if (this.mGestureDetector != null) {
            return;
        }
        this.mGestureDetector = new GestureDetectorCompat(this.mRecyclerView.getContext(), (GestureDetector$OnGestureListener)new ItemTouchHelperGestureListener());
    }
    
    private void releaseVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
            this.mVelocityTracker = null;
        }
    }
    
    private void setupCallbacks() {
        this.mSlop = ViewConfiguration.get(this.mRecyclerView.getContext()).getScaledTouchSlop();
        this.mRecyclerView.addItemDecoration((RecyclerView.ItemDecoration)this);
        this.mRecyclerView.addOnItemTouchListener(this.mOnItemTouchListener);
        this.mRecyclerView.addOnChildAttachStateChangeListener((RecyclerView.OnChildAttachStateChangeListener)this);
        this.initGestureDetector();
    }
    
    private int swipeIfNecessary(final ViewHolder viewHolder) {
        int n;
        if (this.mActionState == 2) {
            n = 0;
        }
        else {
            final int movementFlags = this.mCallback.getMovementFlags(this.mRecyclerView, viewHolder);
            final int n2 = (this.mCallback.convertToAbsoluteDirection(movementFlags, ViewCompat.getLayoutDirection((View)this.mRecyclerView)) & 0xFF00) >> 8;
            if (n2 == 0) {
                return 0;
            }
            final int n3 = (movementFlags & 0xFF00) >> 8;
            if (Math.abs(this.mDx) > Math.abs(this.mDy)) {
                final int checkHorizontalSwipe = this.checkHorizontalSwipe(viewHolder, n2);
                if (checkHorizontalSwipe > 0) {
                    n = checkHorizontalSwipe;
                    if ((n3 & checkHorizontalSwipe) == 0x0) {
                        return Callback.convertToRelativeDirection(checkHorizontalSwipe, ViewCompat.getLayoutDirection((View)this.mRecyclerView));
                    }
                    return n;
                }
                else if ((n = this.checkVerticalSwipe(viewHolder, n2)) > 0) {
                    return n;
                }
            }
            else {
                if ((n = this.checkVerticalSwipe(viewHolder, n2)) > 0) {
                    return n;
                }
                final int checkHorizontalSwipe2 = this.checkHorizontalSwipe(viewHolder, n2);
                if (checkHorizontalSwipe2 > 0) {
                    n = checkHorizontalSwipe2;
                    if ((n3 & checkHorizontalSwipe2) == 0x0) {
                        return Callback.convertToRelativeDirection(checkHorizontalSwipe2, ViewCompat.getLayoutDirection((View)this.mRecyclerView));
                    }
                    return n;
                }
            }
            return 0;
        }
        return n;
    }
    
    public void attachToRecyclerView(@Nullable final RecyclerView mRecyclerView) {
        if (this.mRecyclerView != mRecyclerView) {
            if (this.mRecyclerView != null) {
                this.destroyCallbacks();
            }
            this.mRecyclerView = mRecyclerView;
            if (this.mRecyclerView != null) {
                final Resources resources = mRecyclerView.getResources();
                this.mSwipeEscapeVelocity = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_velocity);
                this.mMaxSwipeVelocity = resources.getDimension(R$dimen.item_touch_helper_swipe_escape_max_velocity);
                this.setupCallbacks();
            }
        }
    }
    
    boolean checkSelectForSwipe(int n, final MotionEvent motionEvent, final int n2) {
        if (this.mSelected != null || n != 2 || this.mActionState == 2 || !this.mCallback.isItemViewSwipeEnabled()) {
            return false;
        }
        if (this.mRecyclerView.getScrollState() == 1) {
            return false;
        }
        final ViewHolder swipedView = this.findSwipedView(motionEvent);
        if (swipedView == null) {
            return false;
        }
        n = (0xFF00 & this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, swipedView)) >> 8;
        if (n == 0) {
            return false;
        }
        final float x = motionEvent.getX(n2);
        final float y = motionEvent.getY(n2);
        final float n3 = x - this.mInitialTouchX;
        final float n4 = y - this.mInitialTouchY;
        final float abs = Math.abs(n3);
        final float abs2 = Math.abs(n4);
        if (abs < this.mSlop && abs2 < this.mSlop) {
            return false;
        }
        if (abs > abs2) {
            if (n3 < 0.0f && (n & 0x4) == 0x0) {
                return false;
            }
            if (n3 > 0.0f && (n & 0x8) == 0x0) {
                return false;
            }
        }
        else {
            if (n4 < 0.0f && (n & 0x1) == 0x0) {
                return false;
            }
            if (n4 > 0.0f && (n & 0x2) == 0x0) {
                return false;
            }
        }
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.mActivePointerId = motionEvent.getPointerId(0);
        this.select(swipedView, 1);
        return true;
    }
    
    int endRecoverAnimation(final ViewHolder viewHolder, final boolean b) {
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; --i) {
            final RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
            if (recoverAnimation.mViewHolder == viewHolder) {
                recoverAnimation.mOverridden |= b;
                if (!recoverAnimation.mEnded) {
                    recoverAnimation.cancel();
                }
                this.mRecoverAnimations.remove(i);
                return recoverAnimation.mAnimationType;
            }
        }
        return 0;
    }
    
    RecoverAnimation findAnimation(final MotionEvent motionEvent) {
        if (!this.mRecoverAnimations.isEmpty()) {
            final View childView = this.findChildView(motionEvent);
            for (int i = this.mRecoverAnimations.size() - 1; i >= 0; --i) {
                final RecoverAnimation recoverAnimation;
                if ((recoverAnimation = this.mRecoverAnimations.get(i)).mViewHolder.itemView == childView) {
                    return recoverAnimation;
                }
            }
            return null;
        }
        return null;
    }
    
    View findChildView(final MotionEvent motionEvent) {
        final float x = motionEvent.getX();
        final float y = motionEvent.getY();
        if (this.mSelected != null) {
            final View itemView = this.mSelected.itemView;
            if (hitTest(itemView, x, y, this.mSelectedStartX + this.mDx, this.mSelectedStartY + this.mDy)) {
                return itemView;
            }
        }
        for (int i = this.mRecoverAnimations.size() - 1; i >= 0; --i) {
            final RecoverAnimation recoverAnimation = this.mRecoverAnimations.get(i);
            final View itemView2 = recoverAnimation.mViewHolder.itemView;
            if (hitTest(itemView2, x, y, recoverAnimation.mX, recoverAnimation.mY)) {
                return itemView2;
            }
        }
        return this.mRecyclerView.findChildViewUnder(x, y);
    }
    
    @Override
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final State state) {
        rect.setEmpty();
    }
    
    boolean hasRunningRecoverAnim() {
        for (int size = this.mRecoverAnimations.size(), i = 0; i < size; ++i) {
            if (!this.mRecoverAnimations.get(i).mEnded) {
                return true;
            }
        }
        return false;
    }
    
    void moveIfNecessary(final ViewHolder viewHolder) {
        if (!this.mRecyclerView.isLayoutRequested() && this.mActionState == 2) {
            final float moveThreshold = this.mCallback.getMoveThreshold(viewHolder);
            final int n = (int)(this.mSelectedStartX + this.mDx);
            final int n2 = (int)(this.mSelectedStartY + this.mDy);
            if (Math.abs(n2 - viewHolder.itemView.getTop()) >= viewHolder.itemView.getHeight() * moveThreshold || Math.abs(n - viewHolder.itemView.getLeft()) >= viewHolder.itemView.getWidth() * moveThreshold) {
                final List<ViewHolder> swapTargets = this.findSwapTargets(viewHolder);
                if (swapTargets.size() != 0) {
                    final ViewHolder chooseDropTarget = this.mCallback.chooseDropTarget(viewHolder, swapTargets, n, n2);
                    if (chooseDropTarget == null) {
                        this.mSwapTargets.clear();
                        this.mDistances.clear();
                        return;
                    }
                    final int adapterPosition = chooseDropTarget.getAdapterPosition();
                    final int adapterPosition2 = viewHolder.getAdapterPosition();
                    if (this.mCallback.onMove(this.mRecyclerView, viewHolder, chooseDropTarget)) {
                        this.mCallback.onMoved(this.mRecyclerView, viewHolder, adapterPosition2, chooseDropTarget, adapterPosition, n, n2);
                    }
                }
            }
        }
    }
    
    void obtainVelocityTracker() {
        if (this.mVelocityTracker != null) {
            this.mVelocityTracker.recycle();
        }
        this.mVelocityTracker = VelocityTracker.obtain();
    }
    
    @Override
    public void onChildViewAttachedToWindow(final View view) {
    }
    
    @Override
    public void onChildViewDetachedFromWindow(final View view) {
        this.removeChildDrawingOrderCallbackIfNecessary(view);
        final RecyclerView.ViewHolder childViewHolder = this.mRecyclerView.getChildViewHolder(view);
        if (childViewHolder != null) {
            if (this.mSelected != null && childViewHolder == this.mSelected) {
                this.select(null, 0);
                return;
            }
            this.endRecoverAnimation(childViewHolder, false);
            if (this.mPendingCleanup.remove(childViewHolder.itemView)) {
                this.mCallback.clearView(this.mRecyclerView, childViewHolder);
            }
        }
    }
    
    @Override
    public void onDraw(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        this.mOverdrawChildPosition = -1;
        float n = 0.0f;
        float n2 = 0.0f;
        if (this.mSelected != null) {
            this.getSelectedDxDy(this.mTmpPosition);
            n = this.mTmpPosition[0];
            n2 = this.mTmpPosition[1];
        }
        this.mCallback.onDraw(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, n, n2);
    }
    
    @Override
    public void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final State state) {
        float n = 0.0f;
        float n2 = 0.0f;
        if (this.mSelected != null) {
            this.getSelectedDxDy(this.mTmpPosition);
            n = this.mTmpPosition[0];
            n2 = this.mTmpPosition[1];
        }
        this.mCallback.onDrawOver(canvas, recyclerView, this.mSelected, this.mRecoverAnimations, this.mActionState, n, n2);
    }
    
    void postDispatchSwipe(final RecoverAnimation recoverAnimation, final int n) {
        this.mRecyclerView.post((Runnable)new Runnable() {
            @Override
            public void run() {
                if (ItemTouchHelper.this.mRecyclerView != null && ItemTouchHelper.this.mRecyclerView.isAttachedToWindow() && !recoverAnimation.mOverridden && recoverAnimation.mViewHolder.getAdapterPosition() != -1) {
                    final RecyclerView.ItemAnimator itemAnimator = ItemTouchHelper.this.mRecyclerView.getItemAnimator();
                    if ((itemAnimator != null && itemAnimator.isRunning(null)) || ItemTouchHelper.this.hasRunningRecoverAnim()) {
                        ItemTouchHelper.this.mRecyclerView.post((Runnable)this);
                        return;
                    }
                    ItemTouchHelper.this.mCallback.onSwiped(recoverAnimation.mViewHolder, n);
                }
            }
        });
    }
    
    void removeChildDrawingOrderCallbackIfNecessary(final View view) {
        if (view == this.mOverdrawChild) {
            this.mOverdrawChild = null;
            if (this.mChildDrawingOrderCallback != null) {
                this.mRecyclerView.setChildDrawingOrderCallback(null);
            }
        }
    }
    
    boolean scrollIfNecessary() {
        if (this.mSelected == null) {
            this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
            return false;
        }
        final long currentTimeMillis = System.currentTimeMillis();
        long n;
        if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
            n = 0L;
        }
        else {
            n = currentTimeMillis - this.mDragScrollStartTimeInMs;
        }
        final RecyclerView.LayoutManager layoutManager = this.mRecyclerView.getLayoutManager();
        if (this.mTmpRect == null) {
            this.mTmpRect = new Rect();
        }
        final int n2 = 0;
        final int n3 = 0;
        layoutManager.calculateItemDecorationsForChild(this.mSelected.itemView, this.mTmpRect);
        int n4 = n2;
        if (layoutManager.canScrollHorizontally()) {
            final int n5 = (int)(this.mSelectedStartX + this.mDx);
            n4 = n5 - this.mTmpRect.left - this.mRecyclerView.getPaddingLeft();
            if (this.mDx >= 0.0f || n4 >= 0) {
                n4 = n2;
                if (this.mDx > 0.0f) {
                    final int n6 = this.mSelected.itemView.getWidth() + n5 + this.mTmpRect.right - (this.mRecyclerView.getWidth() - this.mRecyclerView.getPaddingRight());
                    n4 = n2;
                    if (n6 > 0) {
                        n4 = n6;
                    }
                }
            }
        }
        int n7 = n3;
        if (layoutManager.canScrollVertically()) {
            final int n8 = (int)(this.mSelectedStartY + this.mDy);
            n7 = n8 - this.mTmpRect.top - this.mRecyclerView.getPaddingTop();
            if (this.mDy >= 0.0f || n7 >= 0) {
                n7 = n3;
                if (this.mDy > 0.0f) {
                    final int n9 = this.mSelected.itemView.getHeight() + n8 + this.mTmpRect.bottom - (this.mRecyclerView.getHeight() - this.mRecyclerView.getPaddingBottom());
                    n7 = n3;
                    if (n9 > 0) {
                        n7 = n9;
                    }
                }
            }
        }
        int interpolateOutOfBoundsScroll;
        if ((interpolateOutOfBoundsScroll = n4) != 0) {
            interpolateOutOfBoundsScroll = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getWidth(), n4, this.mRecyclerView.getWidth(), n);
        }
        int interpolateOutOfBoundsScroll2;
        if ((interpolateOutOfBoundsScroll2 = n7) != 0) {
            interpolateOutOfBoundsScroll2 = this.mCallback.interpolateOutOfBoundsScroll(this.mRecyclerView, this.mSelected.itemView.getHeight(), n7, this.mRecyclerView.getHeight(), n);
        }
        if (interpolateOutOfBoundsScroll != 0 || interpolateOutOfBoundsScroll2 != 0) {
            if (this.mDragScrollStartTimeInMs == Long.MIN_VALUE) {
                this.mDragScrollStartTimeInMs = currentTimeMillis;
            }
            this.mRecyclerView.scrollBy(interpolateOutOfBoundsScroll, interpolateOutOfBoundsScroll2);
            return true;
        }
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        return false;
    }
    
    void select(final ViewHolder mSelected, final int mActionState) {
        if (mSelected == this.mSelected && mActionState == this.mActionState) {
            return;
        }
        this.mDragScrollStartTimeInMs = Long.MIN_VALUE;
        final int mActionState2 = this.mActionState;
        this.endRecoverAnimation(mSelected, true);
        if ((this.mActionState = mActionState) == 2) {
            this.mOverdrawChild = mSelected.itemView;
            this.addChildDrawingOrderCallback();
        }
        int n = 0;
        final int n2 = 0;
        if (this.mSelected != null) {
            final ViewHolder mSelected2 = this.mSelected;
            if (mSelected2.itemView.getParent() != null) {
                int swipeIfNecessary;
                if (mActionState2 == 2) {
                    swipeIfNecessary = 0;
                }
                else {
                    swipeIfNecessary = this.swipeIfNecessary(mSelected2);
                }
                this.releaseVelocityTracker();
                float n3 = 0.0f;
                float n4 = 0.0f;
                switch (swipeIfNecessary) {
                    default: {
                        n3 = 0.0f;
                        n4 = 0.0f;
                        break;
                    }
                    case 4:
                    case 8:
                    case 16:
                    case 32: {
                        n4 = 0.0f;
                        n3 = Math.signum(this.mDx) * this.mRecyclerView.getWidth();
                        break;
                    }
                    case 1:
                    case 2: {
                        n3 = 0.0f;
                        n4 = Math.signum(this.mDy) * this.mRecyclerView.getHeight();
                        break;
                    }
                }
                int n5;
                if (mActionState2 == 2) {
                    n5 = 8;
                }
                else if (swipeIfNecessary > 0) {
                    n5 = 2;
                }
                else {
                    n5 = 4;
                }
                this.getSelectedDxDy(this.mTmpPosition);
                final float n6 = this.mTmpPosition[0];
                final float n7 = this.mTmpPosition[1];
                final RecoverAnimation recoverAnimation = new RecoverAnimation(mSelected2, n5, mActionState2, n6, n7, n3, n4) {
                    @Override
                    public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
                        super.onAnimationEnd(valueAnimatorCompat);
                        if (!this.mOverridden) {
                            if (swipeIfNecessary <= 0) {
                                ItemTouchHelper.this.mCallback.clearView(ItemTouchHelper.this.mRecyclerView, mSelected2);
                            }
                            else {
                                ItemTouchHelper.this.mPendingCleanup.add(mSelected2.itemView);
                                this.mIsPendingCleanup = true;
                                if (swipeIfNecessary > 0) {
                                    ItemTouchHelper.this.postDispatchSwipe((RecoverAnimation)this, swipeIfNecessary);
                                }
                            }
                            if (ItemTouchHelper.this.mOverdrawChild == mSelected2.itemView) {
                                ItemTouchHelper.this.removeChildDrawingOrderCallbackIfNecessary(mSelected2.itemView);
                            }
                        }
                    }
                };
                ((RecoverAnimation)recoverAnimation).setDuration(this.mCallback.getAnimationDuration(this.mRecyclerView, n5, n3 - n6, n4 - n7));
                this.mRecoverAnimations.add((RecoverAnimation)recoverAnimation);
                ((RecoverAnimation)recoverAnimation).start();
                n = 1;
            }
            else {
                this.removeChildDrawingOrderCallbackIfNecessary(mSelected2.itemView);
                this.mCallback.clearView(this.mRecyclerView, mSelected2);
                n = n2;
            }
            this.mSelected = null;
        }
        if (mSelected != null) {
            this.mSelectedFlags = (this.mCallback.getAbsoluteMovementFlags(this.mRecyclerView, mSelected) & (1 << mActionState * 8 + 8) - 1) >> this.mActionState * 8;
            this.mSelectedStartX = (float)mSelected.itemView.getLeft();
            this.mSelectedStartY = (float)mSelected.itemView.getTop();
            this.mSelected = mSelected;
            if (mActionState == 2) {
                this.mSelected.itemView.performHapticFeedback(0);
            }
        }
        final ViewParent parent = this.mRecyclerView.getParent();
        if (parent != null) {
            parent.requestDisallowInterceptTouchEvent(this.mSelected != null);
        }
        if (n == 0) {
            this.mRecyclerView.getLayoutManager().requestSimpleAnimationsInNextLayout();
        }
        this.mCallback.onSelectedChanged(this.mSelected, this.mActionState);
        this.mRecyclerView.invalidate();
    }
    
    public void startDrag(final ViewHolder viewHolder) {
        if (!this.mCallback.hasDragFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start drag has been called but swiping is not enabled");
            return;
        }
        if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start drag has been called with a view holder which is not a child of the RecyclerView which is controlled by this ItemTouchHelper.");
            return;
        }
        this.obtainVelocityTracker();
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.select(viewHolder, 2);
    }
    
    public void startSwipe(final ViewHolder viewHolder) {
        if (!this.mCallback.hasSwipeFlag(this.mRecyclerView, viewHolder)) {
            Log.e("ItemTouchHelper", "Start swipe has been called but dragging is not enabled");
            return;
        }
        if (viewHolder.itemView.getParent() != this.mRecyclerView) {
            Log.e("ItemTouchHelper", "Start swipe has been called with a view holder which is not a child of the RecyclerView controlled by this ItemTouchHelper.");
            return;
        }
        this.obtainVelocityTracker();
        this.mDy = 0.0f;
        this.mDx = 0.0f;
        this.select(viewHolder, 1);
    }
    
    void updateDxDy(final MotionEvent motionEvent, final int n, final int n2) {
        final float x = motionEvent.getX(n2);
        final float y = motionEvent.getY(n2);
        this.mDx = x - this.mInitialTouchX;
        this.mDy = y - this.mInitialTouchY;
        if ((n & 0x4) == 0x0) {
            this.mDx = Math.max(0.0f, this.mDx);
        }
        if ((n & 0x8) == 0x0) {
            this.mDx = Math.min(0.0f, this.mDx);
        }
        if ((n & 0x1) == 0x0) {
            this.mDy = Math.max(0.0f, this.mDy);
        }
        if ((n & 0x2) == 0x0) {
            this.mDy = Math.min(0.0f, this.mDy);
        }
    }
    
    public abstract static class Callback
    {
        private static final int ABS_HORIZONTAL_DIR_FLAGS = 789516;
        public static final int DEFAULT_DRAG_ANIMATION_DURATION = 200;
        public static final int DEFAULT_SWIPE_ANIMATION_DURATION = 250;
        private static final long DRAG_SCROLL_ACCELERATION_LIMIT_TIME_MS = 2000L;
        static final int RELATIVE_DIR_FLAGS = 3158064;
        private static final Interpolator sDragScrollInterpolator;
        private static final Interpolator sDragViewScrollCapInterpolator;
        private static final ItemTouchUIUtil sUICallback;
        private int mCachedMaxScrollSpeed;
        
        static {
            sDragScrollInterpolator = (Interpolator)new Interpolator() {
                public float getInterpolation(final float n) {
                    return n * n * n * n * n;
                }
            };
            sDragViewScrollCapInterpolator = (Interpolator)new Interpolator() {
                public float getInterpolation(float n) {
                    --n;
                    return n * n * n * n * n + 1.0f;
                }
            };
            if (Build$VERSION.SDK_INT >= 21) {
                sUICallback = new ItemTouchUIUtilImpl.Lollipop();
                return;
            }
            if (Build$VERSION.SDK_INT >= 11) {
                sUICallback = new ItemTouchUIUtilImpl.Honeycomb();
                return;
            }
            sUICallback = new ItemTouchUIUtilImpl.Gingerbread();
        }
        
        public Callback() {
            this.mCachedMaxScrollSpeed = -1;
        }
        
        public static int convertToRelativeDirection(int n, final int n2) {
            final int n3 = n & 0xC0C0C;
            if (n3 == 0) {
                return n;
            }
            n &= ~n3;
            if (n2 == 0) {
                return n | n3 << 2;
            }
            return n | (n3 << 1 & 0xFFF3F3F3) | (n3 << 1 & 0xC0C0C) << 2;
        }
        
        public static ItemTouchUIUtil getDefaultUIUtil() {
            return Callback.sUICallback;
        }
        
        private int getMaxDragScroll(final RecyclerView recyclerView) {
            if (this.mCachedMaxScrollSpeed == -1) {
                this.mCachedMaxScrollSpeed = recyclerView.getResources().getDimensionPixelSize(R$dimen.item_touch_helper_max_drag_scroll_per_frame);
            }
            return this.mCachedMaxScrollSpeed;
        }
        
        public static int makeFlag(final int n, final int n2) {
            return n2 << n * 8;
        }
        
        public static int makeMovementFlags(final int n, final int n2) {
            return makeFlag(0, n2 | n) | makeFlag(1, n2) | makeFlag(2, n);
        }
        
        public boolean canDropOver(final RecyclerView recyclerView, final ViewHolder viewHolder, final ViewHolder viewHolder2) {
            return true;
        }
        
        public ViewHolder chooseDropTarget(final ViewHolder viewHolder, final List<ViewHolder> list, final int n, final int n2) {
            final int width = viewHolder.itemView.getWidth();
            final int height = viewHolder.itemView.getHeight();
            ViewHolder viewHolder2 = null;
            int n3 = -1;
            final int n4 = n - viewHolder.itemView.getLeft();
            final int n5 = n2 - viewHolder.itemView.getTop();
            for (int size = list.size(), i = 0; i < size; ++i) {
                final ViewHolder viewHolder3 = list.get(i);
                ViewHolder viewHolder4 = viewHolder2;
                int n6 = n3;
                if (n4 > 0) {
                    final int n7 = viewHolder3.itemView.getRight() - (n + width);
                    viewHolder4 = viewHolder2;
                    n6 = n3;
                    if (n7 < 0) {
                        viewHolder4 = viewHolder2;
                        n6 = n3;
                        if (viewHolder3.itemView.getRight() > viewHolder.itemView.getRight()) {
                            final int abs = Math.abs(n7);
                            viewHolder4 = viewHolder2;
                            if (abs > (n6 = n3)) {
                                n6 = abs;
                                viewHolder4 = viewHolder3;
                            }
                        }
                    }
                }
                ViewHolder viewHolder5 = viewHolder4;
                int n8 = n6;
                if (n4 < 0) {
                    final int n9 = viewHolder3.itemView.getLeft() - n;
                    viewHolder5 = viewHolder4;
                    n8 = n6;
                    if (n9 > 0) {
                        viewHolder5 = viewHolder4;
                        n8 = n6;
                        if (viewHolder3.itemView.getLeft() < viewHolder.itemView.getLeft()) {
                            final int abs2 = Math.abs(n9);
                            viewHolder5 = viewHolder4;
                            if (abs2 > (n8 = n6)) {
                                n8 = abs2;
                                viewHolder5 = viewHolder3;
                            }
                        }
                    }
                }
                ViewHolder viewHolder6 = viewHolder5;
                int n10 = n8;
                if (n5 < 0) {
                    final int n11 = viewHolder3.itemView.getTop() - n2;
                    viewHolder6 = viewHolder5;
                    n10 = n8;
                    if (n11 > 0) {
                        viewHolder6 = viewHolder5;
                        n10 = n8;
                        if (viewHolder3.itemView.getTop() < viewHolder.itemView.getTop()) {
                            final int abs3 = Math.abs(n11);
                            viewHolder6 = viewHolder5;
                            if (abs3 > (n10 = n8)) {
                                n10 = abs3;
                                viewHolder6 = viewHolder3;
                            }
                        }
                    }
                }
                viewHolder2 = viewHolder6;
                n3 = n10;
                if (n5 > 0) {
                    final int n12 = viewHolder3.itemView.getBottom() - (n2 + height);
                    viewHolder2 = viewHolder6;
                    n3 = n10;
                    if (n12 < 0) {
                        viewHolder2 = viewHolder6;
                        n3 = n10;
                        if (viewHolder3.itemView.getBottom() > viewHolder.itemView.getBottom()) {
                            final int abs4 = Math.abs(n12);
                            viewHolder2 = viewHolder6;
                            if (abs4 > (n3 = n10)) {
                                n3 = abs4;
                                viewHolder2 = viewHolder3;
                            }
                        }
                    }
                }
            }
            return viewHolder2;
        }
        
        public void clearView(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            Callback.sUICallback.clearView(viewHolder.itemView);
        }
        
        public int convertToAbsoluteDirection(int n, final int n2) {
            final int n3 = n & 0x303030;
            if (n3 == 0) {
                return n;
            }
            n &= ~n3;
            if (n2 == 0) {
                return n | n3 >> 2;
            }
            return n | (n3 >> 1 & 0xFFCFCFCF) | (n3 >> 1 & 0x303030) >> 2;
        }
        
        final int getAbsoluteMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.convertToAbsoluteDirection(this.getMovementFlags(recyclerView, viewHolder), ViewCompat.getLayoutDirection((View)recyclerView));
        }
        
        public long getAnimationDuration(final RecyclerView recyclerView, final int n, final float n2, final float n3) {
            final RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
            if (itemAnimator == null) {
                if (n == 8) {
                    return 200L;
                }
                return 250L;
            }
            else {
                if (n == 8) {
                    return itemAnimator.getMoveDuration();
                }
                return itemAnimator.getRemoveDuration();
            }
        }
        
        public int getBoundingBoxMargin() {
            return 0;
        }
        
        public float getMoveThreshold(final ViewHolder viewHolder) {
            return 0.5f;
        }
        
        public abstract int getMovementFlags(final RecyclerView p0, final ViewHolder p1);
        
        public float getSwipeEscapeVelocity(final float n) {
            return n;
        }
        
        public float getSwipeThreshold(final ViewHolder viewHolder) {
            return 0.5f;
        }
        
        public float getSwipeVelocityThreshold(final float n) {
            return n;
        }
        
        boolean hasDragFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return (0xFF0000 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
        }
        
        boolean hasSwipeFlag(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return (0xFF00 & this.getAbsoluteMovementFlags(recyclerView, viewHolder)) != 0x0;
        }
        
        public int interpolateOutOfBoundsScroll(final RecyclerView recyclerView, int n, final int n2, int maxDragScroll, final long n3) {
            maxDragScroll = this.getMaxDragScroll(recyclerView);
            n = (int)((int)Math.signum((float)n2) * maxDragScroll * Callback.sDragViewScrollCapInterpolator.getInterpolation(Math.min(1.0f, 1.0f * Math.abs(n2) / n)));
            float n4;
            if (n3 > 2000L) {
                n4 = 1.0f;
            }
            else {
                n4 = n3 / 2000.0f;
            }
            n *= (int)Callback.sDragScrollInterpolator.getInterpolation(n4);
            if (n != 0) {
                return n;
            }
            if (n2 > 0) {
                return 1;
            }
            return -1;
        }
        
        public boolean isItemViewSwipeEnabled() {
            return true;
        }
        
        public boolean isLongPressDragEnabled() {
            return true;
        }
        
        public void onChildDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
            Callback.sUICallback.onDraw(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
        }
        
        public void onChildDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final float n, final float n2, final int n3, final boolean b) {
            Callback.sUICallback.onDrawOver(canvas, recyclerView, viewHolder.itemView, n, n2, n3, b);
        }
        
        void onDraw(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, final int n, final float n2, final float n3) {
            for (int size = list.size(), i = 0; i < size; ++i) {
                final RecoverAnimation recoverAnimation = list.get(i);
                recoverAnimation.update();
                final int save = canvas.save();
                this.onChildDraw(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                final int save2 = canvas.save();
                this.onChildDraw(canvas, recyclerView, viewHolder, n2, n3, n, true);
                canvas.restoreToCount(save2);
            }
        }
        
        void onDrawOver(final Canvas canvas, final RecyclerView recyclerView, final ViewHolder viewHolder, final List<RecoverAnimation> list, int i, final float n, final float n2) {
            final int size = list.size();
            for (int j = 0; j < size; ++j) {
                final RecoverAnimation recoverAnimation = list.get(j);
                final int save = canvas.save();
                this.onChildDrawOver(canvas, recyclerView, recoverAnimation.mViewHolder, recoverAnimation.mX, recoverAnimation.mY, recoverAnimation.mActionState, false);
                canvas.restoreToCount(save);
            }
            if (viewHolder != null) {
                final int save2 = canvas.save();
                this.onChildDrawOver(canvas, recyclerView, viewHolder, n, n2, i, true);
                canvas.restoreToCount(save2);
            }
            boolean b = false;
            RecoverAnimation recoverAnimation2;
            for (i = size - 1; i >= 0; --i) {
                recoverAnimation2 = list.get(i);
                if (recoverAnimation2.mEnded && !recoverAnimation2.mIsPendingCleanup) {
                    list.remove(i);
                }
                else if (!recoverAnimation2.mEnded) {
                    b = true;
                }
            }
            if (b) {
                recyclerView.invalidate();
            }
        }
        
        public abstract boolean onMove(final RecyclerView p0, final ViewHolder p1, final ViewHolder p2);
        
        public void onMoved(final RecyclerView recyclerView, final ViewHolder viewHolder, final int n, final ViewHolder viewHolder2, final int n2, final int n3, final int n4) {
            final RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof ViewDropHandler) {
                ((ViewDropHandler)layoutManager).prepareForDrop(viewHolder.itemView, viewHolder2.itemView, n3, n4);
            }
            else {
                if (layoutManager.canScrollHorizontally()) {
                    if (layoutManager.getDecoratedLeft(viewHolder2.itemView) <= recyclerView.getPaddingLeft()) {
                        recyclerView.scrollToPosition(n2);
                    }
                    if (layoutManager.getDecoratedRight(viewHolder2.itemView) >= recyclerView.getWidth() - recyclerView.getPaddingRight()) {
                        recyclerView.scrollToPosition(n2);
                    }
                }
                if (layoutManager.canScrollVertically()) {
                    if (layoutManager.getDecoratedTop(viewHolder2.itemView) <= recyclerView.getPaddingTop()) {
                        recyclerView.scrollToPosition(n2);
                    }
                    if (layoutManager.getDecoratedBottom(viewHolder2.itemView) >= recyclerView.getHeight() - recyclerView.getPaddingBottom()) {
                        recyclerView.scrollToPosition(n2);
                    }
                }
            }
        }
        
        public void onSelectedChanged(final ViewHolder viewHolder, final int n) {
            if (viewHolder != null) {
                Callback.sUICallback.onSelected(viewHolder.itemView);
            }
        }
        
        public abstract void onSwiped(final ViewHolder p0, final int p1);
    }
    
    private class ItemTouchHelperGestureListener extends GestureDetector$SimpleOnGestureListener
    {
        ItemTouchHelperGestureListener() {
        }
        
        public boolean onDown(final MotionEvent motionEvent) {
            return true;
        }
        
        public void onLongPress(final MotionEvent motionEvent) {
            final View childView = ItemTouchHelper.this.findChildView(motionEvent);
            if (childView != null) {
                final RecyclerView.ViewHolder childViewHolder = ItemTouchHelper.this.mRecyclerView.getChildViewHolder(childView);
                if (childViewHolder != null && ItemTouchHelper.this.mCallback.hasDragFlag(ItemTouchHelper.this.mRecyclerView, childViewHolder) && motionEvent.getPointerId(0) == ItemTouchHelper.this.mActivePointerId) {
                    final int pointerIndex = motionEvent.findPointerIndex(ItemTouchHelper.this.mActivePointerId);
                    final float x = motionEvent.getX(pointerIndex);
                    final float y = motionEvent.getY(pointerIndex);
                    ItemTouchHelper.this.mInitialTouchX = x;
                    ItemTouchHelper.this.mInitialTouchY = y;
                    final ItemTouchHelper this$0 = ItemTouchHelper.this;
                    ItemTouchHelper.this.mDy = 0.0f;
                    this$0.mDx = 0.0f;
                    if (ItemTouchHelper.this.mCallback.isLongPressDragEnabled()) {
                        ItemTouchHelper.this.select(childViewHolder, 2);
                    }
                }
            }
        }
    }
    
    private class RecoverAnimation implements AnimatorListenerCompat
    {
        final int mActionState;
        final int mAnimationType;
        boolean mEnded;
        private float mFraction;
        public boolean mIsPendingCleanup;
        boolean mOverridden;
        final float mStartDx;
        final float mStartDy;
        final float mTargetX;
        final float mTargetY;
        private final ValueAnimatorCompat mValueAnimator;
        final ViewHolder mViewHolder;
        float mX;
        float mY;
        
        public RecoverAnimation(final ViewHolder mViewHolder, final int mAnimationType, final int mActionState, final float mStartDx, final float mStartDy, final float mTargetX, final float mTargetY) {
            this.mOverridden = false;
            this.mEnded = false;
            this.mActionState = mActionState;
            this.mAnimationType = mAnimationType;
            this.mViewHolder = mViewHolder;
            this.mStartDx = mStartDx;
            this.mStartDy = mStartDy;
            this.mTargetX = mTargetX;
            this.mTargetY = mTargetY;
            (this.mValueAnimator = AnimatorCompatHelper.emptyValueAnimator()).addUpdateListener((AnimatorUpdateListenerCompat)new AnimatorUpdateListenerCompat() {
                public void onAnimationUpdate(final ValueAnimatorCompat valueAnimatorCompat) {
                    RecoverAnimation.this.setFraction(valueAnimatorCompat.getAnimatedFraction());
                }
            });
            this.mValueAnimator.setTarget(mViewHolder.itemView);
            this.mValueAnimator.addListener((AnimatorListenerCompat)this);
            this.setFraction(0.0f);
        }
        
        public void cancel() {
            this.mValueAnimator.cancel();
        }
        
        public void onAnimationCancel(final ValueAnimatorCompat valueAnimatorCompat) {
            this.setFraction(1.0f);
        }
        
        public void onAnimationEnd(final ValueAnimatorCompat valueAnimatorCompat) {
            if (!this.mEnded) {
                this.mViewHolder.setIsRecyclable(true);
            }
            this.mEnded = true;
        }
        
        public void onAnimationRepeat(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        public void onAnimationStart(final ValueAnimatorCompat valueAnimatorCompat) {
        }
        
        public void setDuration(final long duration) {
            this.mValueAnimator.setDuration(duration);
        }
        
        public void setFraction(final float mFraction) {
            this.mFraction = mFraction;
        }
        
        public void start() {
            this.mViewHolder.setIsRecyclable(false);
            this.mValueAnimator.start();
        }
        
        public void update() {
            if (this.mStartDx == this.mTargetX) {
                this.mX = ViewCompat.getTranslationX(this.mViewHolder.itemView);
            }
            else {
                this.mX = this.mStartDx + this.mFraction * (this.mTargetX - this.mStartDx);
            }
            if (this.mStartDy == this.mTargetY) {
                this.mY = ViewCompat.getTranslationY(this.mViewHolder.itemView);
                return;
            }
            this.mY = this.mStartDy + this.mFraction * (this.mTargetY - this.mStartDy);
        }
    }
    
    public abstract static class SimpleCallback extends Callback
    {
        private int mDefaultDragDirs;
        private int mDefaultSwipeDirs;
        
        public SimpleCallback(final int mDefaultDragDirs, final int mDefaultSwipeDirs) {
            this.mDefaultSwipeDirs = mDefaultSwipeDirs;
            this.mDefaultDragDirs = mDefaultDragDirs;
        }
        
        public int getDragDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.mDefaultDragDirs;
        }
        
        @Override
        public int getMovementFlags(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return Callback.makeMovementFlags(this.getDragDirs(recyclerView, viewHolder), this.getSwipeDirs(recyclerView, viewHolder));
        }
        
        public int getSwipeDirs(final RecyclerView recyclerView, final ViewHolder viewHolder) {
            return this.mDefaultSwipeDirs;
        }
        
        public void setDefaultDragDirs(final int mDefaultDragDirs) {
            this.mDefaultDragDirs = mDefaultDragDirs;
        }
        
        public void setDefaultSwipeDirs(final int mDefaultSwipeDirs) {
            this.mDefaultSwipeDirs = mDefaultSwipeDirs;
        }
    }
    
    public interface ViewDropHandler
    {
        void prepareForDrop(final View p0, final View p1, final int p2, final int p3);
    }
}
