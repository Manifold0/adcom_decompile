// 
// Decompiled by Procyon v0.5.34
// 

package com.ironsource.mediationsdk.events;

import android.os.Handler;
import android.os.HandlerThread;
import com.ironsource.mediationsdk.logger.IronSourceLogger;
import com.ironsource.mediationsdk.IronSourceObject;
import java.util.Iterator;
import org.json.JSONException;
import com.ironsource.eventsmodule.EventsSender;
import com.ironsource.eventsmodule.IEventsSenderResultListener;
import android.text.TextUtils;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import org.json.JSONObject;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import java.util.Collections;
import java.util.Comparator;
import java.util.Collection;
import java.util.List;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.IronSourceSegment;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.eventsmodule.EventData;
import java.util.ArrayList;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import android.content.Context;
import com.ironsource.eventsmodule.IEventsManager;

public abstract class BaseEventsManager implements IEventsManager
{
    private static final String KEY_SESSION_DEPTH = "sessionDepth";
    final String DATABASE_NAME;
    final int DATABASE_VERSION;
    final int DEFAULT_BACKUP_THRESHOLD;
    final int DEFAULT_MAX_EVENTS_PER_BATCH;
    final int DEFAULT_MAX_NUMBER_OF_EVENTS;
    final String KEY_PLACEMENT;
    final String KEY_PROVIDER;
    private final String MEDIATION_ABT;
    int mAdUnitType;
    private int mBackupThreshold;
    private Context mContext;
    private DataBaseEventsStorage mDbStorage;
    private EventThread mEventThread;
    String mEventType;
    private AbstractEventsFormatter mFormatter;
    String mFormatterType;
    private boolean mHadTopPriorityEvent;
    private boolean mHasServerResponse;
    private boolean mIsEventsEnabled;
    private ArrayList<EventData> mLocalEvents;
    private IronSourceLoggerManager mLoggerManager;
    private int mMaxEventsPerBatch;
    private int mMaxNumberOfEvents;
    private int[] mOptOutEvents;
    private IronSourceSegment mSegment;
    private ServerSegmetData mServerSegmentData;
    private String mSessionId;
    private int mTotalEvents;
    
    public BaseEventsManager() {
        this.DEFAULT_BACKUP_THRESHOLD = 1;
        this.DEFAULT_MAX_NUMBER_OF_EVENTS = 100;
        this.DEFAULT_MAX_EVENTS_PER_BATCH = 5000;
        this.DATABASE_VERSION = 5;
        this.DATABASE_NAME = "supersonic_sdk.db";
        this.KEY_PROVIDER = "provider";
        this.KEY_PLACEMENT = "placement";
        this.MEDIATION_ABT = "abt";
        this.mHadTopPriorityEvent = false;
        this.mIsEventsEnabled = true;
        this.mMaxNumberOfEvents = 100;
        this.mMaxEventsPerBatch = 5000;
        this.mBackupThreshold = 1;
    }
    
    private void backupEventsToDb() {
        this.mDbStorage.saveEvents(this.mLocalEvents, this.mEventType);
        this.mLocalEvents.clear();
    }
    
    private ArrayList<EventData> initCombinedEventList(final ArrayList<EventData> list, final ArrayList<EventData> list2, final int n) {
        final ArrayList<Object> list3 = new ArrayList<Object>();
        list3.addAll(list);
        list3.addAll(list2);
        Collections.sort(list3, (Comparator<? super Object>)new Comparator<EventData>() {
            @Override
            public int compare(final EventData eventData, final EventData eventData2) {
                if (eventData.getTimeStamp() >= eventData2.getTimeStamp()) {
                    return 1;
                }
                return -1;
            }
        });
        if (list3.size() <= n) {
            return new ArrayList<EventData>((Collection<? extends EventData>)list3);
        }
        final ArrayList list4 = new ArrayList<EventData>(list3.subList(0, n));
        this.mDbStorage.saveEvents((List<EventData>)list3.subList(n, list3.size()), this.mEventType);
        return (ArrayList<EventData>)list4;
    }
    
    private void sendEvents() {
        this.mHadTopPriorityEvent = false;
        final ArrayList<EventData> initCombinedEventList = this.initCombinedEventList(this.mLocalEvents, this.mDbStorage.loadEvents(this.mEventType), this.mMaxEventsPerBatch);
        this.mLocalEvents.clear();
        this.mDbStorage.clearEvents(this.mEventType);
        this.mTotalEvents = 0;
        if (initCombinedEventList.size() <= 0) {
            return;
        }
        Object o = GeneralProperties.getProperties().toJSON();
        while (true) {
            try {
                this.updateSegmentsData((JSONObject)o);
                final String abt = IronSourceUtils.getAbt();
                if (!TextUtils.isEmpty((CharSequence)abt)) {
                    ((JSONObject)o).put("abt", (Object)abt);
                }
                o = this.mFormatter.format(initCombinedEventList, (JSONObject)o);
                new EventsSender(new IEventsSenderResultListener() {
                    @Override
                    public void onEventsSenderResult(final ArrayList<EventData> list, final boolean b) {
                        synchronized (this) {
                            BaseEventsManager.this.mEventThread.postTask(new Runnable() {
                                @Override
                                public void run() {
                                    if (b) {
                                        BaseEventsManager.this.mTotalEvents = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType).size() + BaseEventsManager.this.mLocalEvents.size();
                                    }
                                    else if (list != null) {
                                        BaseEventsManager.this.mDbStorage.saveEvents(list, BaseEventsManager.this.mEventType);
                                        BaseEventsManager.this.mTotalEvents = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType).size() + BaseEventsManager.this.mLocalEvents.size();
                                    }
                                }
                            });
                        }
                    }
                }).execute(new Object[] { o, this.mFormatter.getEventsServerUrl(), initCombinedEventList });
            }
            catch (JSONException ex) {
                ex.printStackTrace();
                continue;
            }
            break;
        }
    }
    
    private boolean shouldAddSessionDepth(final EventData eventData) {
        return eventData.getEventId() != 14 && eventData.getEventId() != 140 && eventData.getEventId() != 40 && eventData.getEventId() != 41;
    }
    
    private boolean shouldBackupEventsToDb(final ArrayList<EventData> list) {
        boolean b = false;
        if (list != null) {
            if (list.size() < this.mBackupThreshold) {
                return false;
            }
            b = true;
        }
        return b;
    }
    
    private boolean shouldEventBeLogged(final EventData eventData) {
        boolean b2;
        final boolean b = b2 = true;
        if (eventData != null) {
            b2 = b;
            if (this.mOptOutEvents != null) {
                b2 = b;
                if (this.mOptOutEvents.length > 0) {
                    final int eventId = eventData.getEventId();
                    int n = 0;
                    while (true) {
                        b2 = b;
                        if (n >= this.mOptOutEvents.length) {
                            break;
                        }
                        if (eventId == this.mOptOutEvents[n]) {
                            b2 = false;
                            break;
                        }
                        ++n;
                    }
                }
            }
        }
        return b2;
    }
    
    private boolean shouldSendEvents() {
        return (this.mTotalEvents >= this.mMaxNumberOfEvents || this.mHadTopPriorityEvent) && this.mHasServerResponse;
    }
    
    private void updateSegmentsData(final JSONObject jsonObject) {
        try {
            if (this.mSegment != null) {
                if (this.mSegment.getAge() > 0) {
                    jsonObject.put("age", this.mSegment.getAge());
                }
                if (!TextUtils.isEmpty((CharSequence)this.mSegment.getGender())) {
                    jsonObject.put("gen", (Object)this.mSegment.getGender());
                }
                if (this.mSegment.getLevel() > 0) {
                    jsonObject.put("lvl", this.mSegment.getLevel());
                }
                if (this.mSegment.getIsPaying() != null) {
                    jsonObject.put("pay", this.mSegment.getIsPaying().get());
                }
                if (this.mSegment.getIapt() > 0.0) {
                    jsonObject.put("iapt", this.mSegment.getIapt());
                }
                if (this.mSegment.getUcd() > 0L) {
                    jsonObject.put("ucd", this.mSegment.getUcd());
                }
            }
            if (this.mServerSegmentData != null) {
                final String segmentId = this.mServerSegmentData.getSegmentId();
                if (!TextUtils.isEmpty((CharSequence)segmentId)) {
                    jsonObject.put("segmentId", (Object)segmentId);
                }
                final JSONObject customSegments = this.mServerSegmentData.getCustomSegments();
                final Iterator keys = customSegments.keys();
                while (keys.hasNext()) {
                    final String s = keys.next();
                    jsonObject.put(s, customSegments.get(s));
                }
            }
        }
        catch (JSONException ex) {
            ex.printStackTrace();
        }
    }
    
    private void verifyCurrentFormatter(final String s) {
        if (this.mFormatter == null || !this.mFormatter.getFormatterType().equals(s)) {
            this.mFormatter = EventsFormatterFactory.getFormatter(s, this.mAdUnitType);
        }
    }
    
    protected abstract String getCurrentPlacement(final int p0);
    
    String getProviderNameForEvent(final EventData eventData) {
        try {
            return new JSONObject(eventData.getAdditionalData()).optString("provider", "");
        }
        catch (JSONException ex) {
            return "";
        }
    }
    
    protected abstract int getSessionDepth(final EventData p0);
    
    protected abstract boolean increaseSessionDepthIfNeeded(final EventData p0);
    
    void initState() {
        this.mLocalEvents = new ArrayList<EventData>();
        this.mTotalEvents = 0;
        this.mFormatter = EventsFormatterFactory.getFormatter(this.mFormatterType, this.mAdUnitType);
        (this.mEventThread = new EventThread(this.mEventType + "EventThread")).start();
        this.mEventThread.prepareHandler();
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mSessionId = IronSourceObject.getInstance().getSessionId();
    }
    
    protected abstract boolean isTopPriorityEvent(final EventData p0);
    
    @Override
    public void log(final EventData eventData) {
        synchronized (this) {
            this.mEventThread.postTask(new Runnable() {
                @Override
                public void run() {
                    if (eventData != null && BaseEventsManager.this.mIsEventsEnabled) {
                        eventData.addToAdditionalData("eventSessionId", BaseEventsManager.this.mSessionId);
                        if (eventData.getEventId() != 40 && eventData.getEventId() != 41) {
                            eventData.addToAdditionalData("connectionType", IronSourceUtils.getConnectionType(BaseEventsManager.this.mContext));
                        }
                        boolean topPriorityEvent = false;
                    Label_0263_Outer:
                        while (true) {
                            while (true) {
                            Label_0357:
                                while (true) {
                                    try {
                                        BaseEventsManager.this.mLoggerManager.log(IronSourceLogger.IronSourceTag.EVENT, ("{\"eventId\":" + eventData.getEventId() + ",\"timestamp\":" + eventData.getTimeStamp() + "," + eventData.getAdditionalData().substring(1)).replace(",", "\n"), 0);
                                        if (BaseEventsManager.this.shouldEventBeLogged(eventData)) {
                                            if (BaseEventsManager.this.shouldAddSessionDepth(eventData)) {
                                                int n = BaseEventsManager.this.getSessionDepth(eventData);
                                                if (BaseEventsManager.this.increaseSessionDepthIfNeeded(eventData)) {
                                                    n = BaseEventsManager.this.getSessionDepth(eventData);
                                                }
                                                eventData.addToAdditionalData("sessionDepth", n);
                                            }
                                            if (!BaseEventsManager.this.shouldExtractCurrentPlacement(eventData)) {
                                                break Label_0357;
                                            }
                                            BaseEventsManager.this.setCurrentPlacement(eventData);
                                            BaseEventsManager.this.mLocalEvents.add(eventData);
                                            BaseEventsManager.this.mTotalEvents++;
                                        }
                                        topPriorityEvent = BaseEventsManager.this.isTopPriorityEvent(eventData);
                                        if (!BaseEventsManager.this.mHadTopPriorityEvent && topPriorityEvent) {
                                            BaseEventsManager.this.mHadTopPriorityEvent = true;
                                        }
                                        if (BaseEventsManager.this.mDbStorage == null) {
                                            return;
                                        }
                                        if (BaseEventsManager.this.shouldSendEvents()) {
                                            BaseEventsManager.this.sendEvents();
                                            return;
                                        }
                                        break;
                                    }
                                    catch (Exception ex) {
                                        ex.printStackTrace();
                                        continue Label_0263_Outer;
                                    }
                                    break;
                                }
                                if (!TextUtils.isEmpty((CharSequence)BaseEventsManager.this.getCurrentPlacement(eventData.getEventId())) && BaseEventsManager.this.shouldIncludeCurrentPlacement(eventData)) {
                                    eventData.addToAdditionalData("placement", BaseEventsManager.this.getCurrentPlacement(eventData.getEventId()));
                                    continue;
                                }
                                continue;
                            }
                        }
                        if (BaseEventsManager.this.shouldBackupEventsToDb(BaseEventsManager.this.mLocalEvents) || topPriorityEvent) {
                            BaseEventsManager.this.backupEventsToDb();
                        }
                    }
                }
            });
        }
    }
    
    public void sendEventToUrl(final EventData eventData, final String s) {
        try {
            final ArrayList<EventData> list = new ArrayList<EventData>();
            list.add(eventData);
            new EventsSender().execute(new Object[] { this.mFormatter.format(list, GeneralProperties.getProperties().toJSON()), s, null });
        }
        catch (Exception ex) {}
    }
    
    @Override
    public void setBackupThreshold(final int mBackupThreshold) {
        if (mBackupThreshold > 0) {
            this.mBackupThreshold = mBackupThreshold;
        }
    }
    
    protected abstract void setCurrentPlacement(final EventData p0);
    
    @Override
    public void setEventsUrl(final String eventsServerUrl, final Context context) {
        if (!TextUtils.isEmpty((CharSequence)eventsServerUrl)) {
            if (this.mFormatter != null) {
                this.mFormatter.setEventsServerUrl(eventsServerUrl);
            }
            IronSourceUtils.saveDefaultEventsURL(context, this.mEventType, eventsServerUrl);
        }
    }
    
    @Override
    public void setFormatterType(final String mFormatterType, final Context context) {
        if (!TextUtils.isEmpty((CharSequence)mFormatterType)) {
            this.mFormatterType = mFormatterType;
            IronSourceUtils.saveDefaultEventsFormatterType(context, this.mEventType, mFormatterType);
            this.verifyCurrentFormatter(mFormatterType);
        }
    }
    
    public void setHasServerResponse(final boolean mHasServerResponse) {
        this.mHasServerResponse = mHasServerResponse;
    }
    
    @Override
    public void setIsEventsEnabled(final boolean mIsEventsEnabled) {
        this.mIsEventsEnabled = mIsEventsEnabled;
    }
    
    @Override
    public void setMaxEventsPerBatch(final int mMaxEventsPerBatch) {
        if (mMaxEventsPerBatch > 0) {
            this.mMaxEventsPerBatch = mMaxEventsPerBatch;
        }
    }
    
    @Override
    public void setMaxNumberOfEvents(final int mMaxNumberOfEvents) {
        if (mMaxNumberOfEvents > 0) {
            this.mMaxNumberOfEvents = mMaxNumberOfEvents;
        }
    }
    
    @Override
    public void setOptOutEvents(final int[] mOptOutEvents, final Context context) {
        this.mOptOutEvents = mOptOutEvents;
        IronSourceUtils.saveDefaultOptOutEvents(context, this.mEventType, mOptOutEvents);
    }
    
    public void setServerSegmentData(final ServerSegmetData mServerSegmentData) {
        synchronized (this) {
            this.mServerSegmentData = mServerSegmentData;
        }
    }
    
    protected abstract boolean shouldExtractCurrentPlacement(final EventData p0);
    
    protected abstract boolean shouldIncludeCurrentPlacement(final EventData p0);
    
    public void start(final Context mContext, final IronSourceSegment mSegment) {
        synchronized (this) {
            this.verifyCurrentFormatter(this.mFormatterType = IronSourceUtils.getDefaultEventsFormatterType(mContext, this.mEventType, this.mFormatterType));
            this.mFormatter.setEventsServerUrl(IronSourceUtils.getDefaultEventsURL(mContext, this.mEventType, null));
            this.mDbStorage = DataBaseEventsStorage.getInstance(mContext, "supersonic_sdk.db", 5);
            this.backupEventsToDb();
            this.mOptOutEvents = IronSourceUtils.getDefaultOptOutEvents(mContext, this.mEventType);
            this.mSegment = mSegment;
            this.mContext = mContext;
        }
    }
    
    public void triggerEventsSend() {
        this.sendEvents();
    }
    
    private class EventThread extends HandlerThread
    {
        private Handler mHandler;
        
        EventThread(final String s) {
            super(s);
        }
        
        void postTask(final Runnable runnable) {
            this.mHandler.post(runnable);
        }
        
        void prepareHandler() {
            this.mHandler = new Handler(this.getLooper());
        }
    }
}
