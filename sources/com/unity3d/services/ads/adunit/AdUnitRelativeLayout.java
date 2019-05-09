package com.unity3d.services.ads.adunit;

import android.annotation.TargetApi;
import android.content.Context;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import java.util.ArrayList;
import java.util.Iterator;

public class AdUnitRelativeLayout extends RelativeLayout {
    private int _maxEvents = 10000;
    private final ArrayList<AdUnitMotionEvent> _motionEvents = new ArrayList();
    private boolean _shouldCapture = false;

    public AdUnitRelativeLayout(Context context) {
        super(context);
    }

    @TargetApi(14)
    public boolean onInterceptTouchEvent(MotionEvent e) {
        super.onInterceptTouchEvent(e);
        if (this._shouldCapture && this._motionEvents.size() < this._maxEvents) {
            boolean isObscured = (e.getFlags() & 1) != 0;
            synchronized (this._motionEvents) {
                this._motionEvents.add(new AdUnitMotionEvent(e.getActionMasked(), isObscured, e.getToolType(0), e.getSource(), e.getDeviceId(), e.getX(0), e.getY(0), e.getEventTime(), e.getPressure(0), e.getSize(0)));
            }
        }
        return false;
    }

    public void startCapture(int maxEvents) {
        this._maxEvents = maxEvents;
        this._shouldCapture = true;
    }

    public void endCapture() {
        this._shouldCapture = false;
    }

    public void clearCapture() {
        synchronized (this._motionEvents) {
            this._motionEvents.clear();
        }
    }

    public int getMaxEventCount() {
        return this._maxEvents;
    }

    public int getCurrentEventCount() {
        int size;
        synchronized (this._motionEvents) {
            size = this._motionEvents.size();
        }
        return size;
    }

    public SparseArray<SparseArray<AdUnitMotionEvent>> getEvents(SparseArray<ArrayList<Integer>> requestedInfos) {
        SparseIntArray countTable = new SparseIntArray();
        SparseArray<SparseArray<AdUnitMotionEvent>> returnData = new SparseArray();
        synchronized (this._motionEvents) {
            Iterator it = this._motionEvents.iterator();
            while (it.hasNext()) {
                AdUnitMotionEvent currentEvent = (AdUnitMotionEvent) it.next();
                ArrayList<Integer> currentRequestedInfos = (ArrayList) requestedInfos.get(currentEvent.getAction());
                if (currentRequestedInfos != null) {
                    int currentRequestedInfoIndex = ((Integer) currentRequestedInfos.get(0)).intValue();
                    if (countTable.get(currentEvent.getAction(), 0) == currentRequestedInfoIndex) {
                        if (returnData.get(currentEvent.getAction()) == null) {
                            returnData.put(currentEvent.getAction(), new SparseArray());
                        }
                        ((SparseArray) returnData.get(currentEvent.getAction())).put(currentRequestedInfoIndex, currentEvent);
                        currentRequestedInfos.remove(0);
                    }
                    countTable.put(currentEvent.getAction(), countTable.get(currentEvent.getAction()) + 1);
                }
            }
        }
        return returnData;
    }

    public SparseIntArray getEventCount(ArrayList<Integer> eventTypes) {
        SparseIntArray returnArray = new SparseIntArray();
        synchronized (this._motionEvents) {
            Iterator it = this._motionEvents.iterator();
            while (it.hasNext()) {
                AdUnitMotionEvent currentEvent = (AdUnitMotionEvent) it.next();
                Iterator it2 = eventTypes.iterator();
                while (it2.hasNext()) {
                    Integer currentType = (Integer) it2.next();
                    if (currentEvent.getAction() == currentType.intValue()) {
                        returnArray.put(currentType.intValue(), returnArray.get(currentType.intValue(), 0) + 1);
                        break;
                    }
                }
            }
        }
        return returnArray;
    }
}
