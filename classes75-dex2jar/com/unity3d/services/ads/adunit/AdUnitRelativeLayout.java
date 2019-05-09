// 
// Decompiled by Procyon v0.5.34
// 

package com.unity3d.services.ads.adunit;

import android.annotation.TargetApi;
import android.view.MotionEvent;
import android.util.SparseArray;
import java.util.Iterator;
import android.util.SparseIntArray;
import android.content.Context;
import java.util.ArrayList;
import android.widget.RelativeLayout;

public class AdUnitRelativeLayout extends RelativeLayout
{
    private int _maxEvents;
    private final ArrayList<AdUnitMotionEvent> _motionEvents;
    private boolean _shouldCapture;
    
    public AdUnitRelativeLayout(final Context context) {
        super(context);
        this._motionEvents = new ArrayList<AdUnitMotionEvent>();
        this._maxEvents = 10000;
        this._shouldCapture = false;
    }
    
    public void clearCapture() {
        synchronized (this._motionEvents) {
            this._motionEvents.clear();
        }
    }
    
    public void endCapture() {
        this._shouldCapture = false;
    }
    
    public int getCurrentEventCount() {
        synchronized (this._motionEvents) {
            return this._motionEvents.size();
        }
    }
    
    public SparseIntArray getEventCount(final ArrayList<Integer> list) {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        synchronized (this._motionEvents) {
            for (final AdUnitMotionEvent adUnitMotionEvent : this._motionEvents) {
                for (final Integer n : list) {
                    if (adUnitMotionEvent.getAction() == n) {
                        sparseIntArray.put((int)n, sparseIntArray.get((int)n, 0) + 1);
                        break;
                    }
                }
            }
        }
        // monitorexit(list2)
        return sparseIntArray;
    }
    
    public SparseArray<SparseArray<AdUnitMotionEvent>> getEvents(final SparseArray<ArrayList<Integer>> sparseArray) {
        final SparseIntArray sparseIntArray = new SparseIntArray();
        final SparseArray sparseArray2 = new SparseArray();
        synchronized (this._motionEvents) {
            for (final AdUnitMotionEvent adUnitMotionEvent : this._motionEvents) {
                final ArrayList list = (ArrayList)sparseArray.get(adUnitMotionEvent.getAction());
                if (list != null) {
                    final int intValue = list.get(0);
                    if (sparseIntArray.get(adUnitMotionEvent.getAction(), 0) == intValue) {
                        if (sparseArray2.get(adUnitMotionEvent.getAction()) == null) {
                            sparseArray2.put(adUnitMotionEvent.getAction(), (Object)new SparseArray());
                        }
                        ((SparseArray)sparseArray2.get(adUnitMotionEvent.getAction())).put(intValue, (Object)adUnitMotionEvent);
                        list.remove(0);
                    }
                    sparseIntArray.put(adUnitMotionEvent.getAction(), sparseIntArray.get(adUnitMotionEvent.getAction()) + 1);
                }
            }
        }
        // monitorexit(list2)
        return (SparseArray<SparseArray<AdUnitMotionEvent>>)sparseArray2;
    }
    
    public int getMaxEventCount() {
        return this._maxEvents;
    }
    
    @TargetApi(14)
    public boolean onInterceptTouchEvent(final MotionEvent motionEvent) {
        super.onInterceptTouchEvent(motionEvent);
        if (this._shouldCapture && this._motionEvents.size() < this._maxEvents) {
            final boolean b = (motionEvent.getFlags() & 0x1) != 0x0;
            synchronized (this._motionEvents) {
                this._motionEvents.add(new AdUnitMotionEvent(motionEvent.getActionMasked(), b, motionEvent.getToolType(0), motionEvent.getSource(), motionEvent.getDeviceId(), motionEvent.getX(0), motionEvent.getY(0), motionEvent.getEventTime(), motionEvent.getPressure(0), motionEvent.getSize(0)));
            }
        }
        return false;
    }
    
    public void startCapture(final int maxEvents) {
        this._maxEvents = maxEvents;
        this._shouldCapture = true;
    }
}
