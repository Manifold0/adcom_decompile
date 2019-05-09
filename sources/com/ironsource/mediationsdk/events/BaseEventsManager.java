package com.ironsource.mediationsdk.events;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.text.TextUtils;
import com.ironsource.eventsmodule.DataBaseEventsStorage;
import com.ironsource.eventsmodule.EventData;
import com.ironsource.eventsmodule.EventsSender;
import com.ironsource.eventsmodule.IEventsManager;
import com.ironsource.eventsmodule.IEventsSenderResultListener;
import com.ironsource.mediationsdk.IronSourceObject;
import com.ironsource.mediationsdk.IronSourceSegment;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.logger.IronSourceLoggerManager;
import com.ironsource.mediationsdk.model.ServerSegmetData;
import com.ironsource.mediationsdk.sdk.GeneralProperties;
import com.ironsource.mediationsdk.utils.IronSourceConstants;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.ironsource.sdk.constants.LocationConst;
import com.ironsource.sdk.precache.DownloadManager;
import com.vungle.warren.ui.VungleActivity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class BaseEventsManager implements IEventsManager {
    private static final String KEY_SESSION_DEPTH = "sessionDepth";
    final String DATABASE_NAME = "supersonic_sdk.db";
    final int DATABASE_VERSION = 5;
    final int DEFAULT_BACKUP_THRESHOLD = 1;
    final int DEFAULT_MAX_EVENTS_PER_BATCH = DownloadManager.OPERATION_TIMEOUT;
    final int DEFAULT_MAX_NUMBER_OF_EVENTS = 100;
    final String KEY_PLACEMENT = VungleActivity.PLACEMENT_EXTRA;
    final String KEY_PROVIDER = LocationConst.PROVIDER;
    private final String MEDIATION_ABT = "abt";
    int mAdUnitType;
    private int mBackupThreshold = 1;
    private Context mContext;
    private DataBaseEventsStorage mDbStorage;
    private EventThread mEventThread;
    String mEventType;
    private AbstractEventsFormatter mFormatter;
    String mFormatterType;
    private boolean mHadTopPriorityEvent = false;
    private boolean mHasServerResponse;
    private boolean mIsEventsEnabled = true;
    private ArrayList<EventData> mLocalEvents;
    private IronSourceLoggerManager mLoggerManager;
    private int mMaxEventsPerBatch = DownloadManager.OPERATION_TIMEOUT;
    private int mMaxNumberOfEvents = 100;
    private int[] mOptOutEvents;
    private IronSourceSegment mSegment;
    private ServerSegmetData mServerSegmentData;
    private String mSessionId;
    private int mTotalEvents;

    /* renamed from: com.ironsource.mediationsdk.events.BaseEventsManager$2 */
    class C06932 implements IEventsSenderResultListener {
        C06932() {
        }

        public synchronized void onEventsSenderResult(final ArrayList<EventData> extraData, final boolean success) {
            BaseEventsManager.this.mEventThread.postTask(new Runnable() {
                public void run() {
                    if (success) {
                        BaseEventsManager.this.mTotalEvents = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType).size() + BaseEventsManager.this.mLocalEvents.size();
                    } else if (extraData != null) {
                        BaseEventsManager.this.mDbStorage.saveEvents(extraData, BaseEventsManager.this.mEventType);
                        BaseEventsManager.this.mTotalEvents = BaseEventsManager.this.mDbStorage.loadEvents(BaseEventsManager.this.mEventType).size() + BaseEventsManager.this.mLocalEvents.size();
                    }
                }
            });
        }
    }

    /* renamed from: com.ironsource.mediationsdk.events.BaseEventsManager$3 */
    class C06943 implements Comparator<EventData> {
        C06943() {
        }

        public int compare(EventData event1, EventData event2) {
            if (event1.getTimeStamp() >= event2.getTimeStamp()) {
                return 1;
            }
            return -1;
        }
    }

    private class EventThread extends HandlerThread {
        private Handler mHandler;

        EventThread(String name) {
            super(name);
        }

        void postTask(Runnable task) {
            this.mHandler.post(task);
        }

        void prepareHandler() {
            this.mHandler = new Handler(getLooper());
        }
    }

    protected abstract String getCurrentPlacement(int i);

    protected abstract int getSessionDepth(EventData eventData);

    protected abstract boolean increaseSessionDepthIfNeeded(EventData eventData);

    protected abstract boolean isTopPriorityEvent(EventData eventData);

    protected abstract void setCurrentPlacement(EventData eventData);

    protected abstract boolean shouldExtractCurrentPlacement(EventData eventData);

    protected abstract boolean shouldIncludeCurrentPlacement(EventData eventData);

    void initState() {
        this.mLocalEvents = new ArrayList();
        this.mTotalEvents = 0;
        this.mFormatter = EventsFormatterFactory.getFormatter(this.mFormatterType, this.mAdUnitType);
        this.mEventThread = new EventThread(this.mEventType + "EventThread");
        this.mEventThread.start();
        this.mEventThread.prepareHandler();
        this.mLoggerManager = IronSourceLoggerManager.getLogger();
        this.mSessionId = IronSourceObject.getInstance().getSessionId();
    }

    public synchronized void start(Context context, IronSourceSegment segment) {
        this.mFormatterType = IronSourceUtils.getDefaultEventsFormatterType(context, this.mEventType, this.mFormatterType);
        verifyCurrentFormatter(this.mFormatterType);
        this.mFormatter.setEventsServerUrl(IronSourceUtils.getDefaultEventsURL(context, this.mEventType, null));
        this.mDbStorage = DataBaseEventsStorage.getInstance(context, "supersonic_sdk.db", 5);
        backupEventsToDb();
        this.mOptOutEvents = IronSourceUtils.getDefaultOptOutEvents(context, this.mEventType);
        this.mSegment = segment;
        this.mContext = context;
    }

    public synchronized void setServerSegmentData(ServerSegmetData serverSegment) {
        this.mServerSegmentData = serverSegment;
    }

    public synchronized void log(final EventData event) {
        this.mEventThread.postTask(new Runnable() {
            public void run() {
                if (event != null && BaseEventsManager.this.mIsEventsEnabled) {
                    event.addToAdditionalData("eventSessionId", BaseEventsManager.this.mSessionId);
                    if (!(event.getEventId() == 40 || event.getEventId() == 41)) {
                        event.addToAdditionalData(RequestParameters.CONNECTION_TYPE, IronSourceUtils.getConnectionType(BaseEventsManager.this.mContext));
                    }
                    try {
                        BaseEventsManager.this.mLoggerManager.log(IronSourceTag.EVENT, ("{\"eventId\":" + event.getEventId() + ",\"timestamp\":" + event.getTimeStamp() + "," + event.getAdditionalData().substring(1)).replace(",", "\n"), 0);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (BaseEventsManager.this.shouldEventBeLogged(event)) {
                        if (BaseEventsManager.this.shouldAddSessionDepth(event)) {
                            int sessionDepth = BaseEventsManager.this.getSessionDepth(event);
                            if (BaseEventsManager.this.increaseSessionDepthIfNeeded(event)) {
                                sessionDepth = BaseEventsManager.this.getSessionDepth(event);
                            }
                            event.addToAdditionalData("sessionDepth", Integer.valueOf(sessionDepth));
                        }
                        if (BaseEventsManager.this.shouldExtractCurrentPlacement(event)) {
                            BaseEventsManager.this.setCurrentPlacement(event);
                        } else if (!TextUtils.isEmpty(BaseEventsManager.this.getCurrentPlacement(event.getEventId())) && BaseEventsManager.this.shouldIncludeCurrentPlacement(event)) {
                            event.addToAdditionalData(VungleActivity.PLACEMENT_EXTRA, BaseEventsManager.this.getCurrentPlacement(event.getEventId()));
                        }
                        BaseEventsManager.this.mLocalEvents.add(event);
                        BaseEventsManager.this.mTotalEvents = BaseEventsManager.this.mTotalEvents + 1;
                    }
                    boolean isTopPriority = BaseEventsManager.this.isTopPriorityEvent(event);
                    if (!BaseEventsManager.this.mHadTopPriorityEvent && isTopPriority) {
                        BaseEventsManager.this.mHadTopPriorityEvent = true;
                    }
                    if (BaseEventsManager.this.mDbStorage == null) {
                        return;
                    }
                    if (BaseEventsManager.this.shouldSendEvents()) {
                        BaseEventsManager.this.sendEvents();
                    } else if (BaseEventsManager.this.shouldBackupEventsToDb(BaseEventsManager.this.mLocalEvents) || isTopPriority) {
                        BaseEventsManager.this.backupEventsToDb();
                    }
                }
            }
        });
    }

    private void sendEvents() {
        this.mHadTopPriorityEvent = false;
        ArrayList<EventData> combinedEventList = initCombinedEventList(this.mLocalEvents, this.mDbStorage.loadEvents(this.mEventType), this.mMaxEventsPerBatch);
        this.mLocalEvents.clear();
        this.mDbStorage.clearEvents(this.mEventType);
        this.mTotalEvents = 0;
        if (combinedEventList.size() > 0) {
            JSONObject generalProperties = GeneralProperties.getProperties().toJSON();
            try {
                updateSegmentsData(generalProperties);
                String abt = IronSourceUtils.getAbt();
                if (!TextUtils.isEmpty(abt)) {
                    generalProperties.put("abt", abt);
                }
            } catch (JSONException ex) {
                ex.printStackTrace();
            }
            String dataToSend = this.mFormatter.format(combinedEventList, generalProperties);
            new EventsSender(new C06932()).execute(new Object[]{dataToSend, this.mFormatter.getEventsServerUrl(), combinedEventList});
        }
    }

    private ArrayList<EventData> initCombinedEventList(ArrayList<EventData> localEvents, ArrayList<EventData> storedEvents, int maxSize) {
        ArrayList<EventData> allEvents = new ArrayList();
        allEvents.addAll(localEvents);
        allEvents.addAll(storedEvents);
        Collections.sort(allEvents, new C06943());
        if (allEvents.size() <= maxSize) {
            return new ArrayList(allEvents);
        }
        ArrayList<EventData> result = new ArrayList(allEvents.subList(0, maxSize));
        this.mDbStorage.saveEvents(allEvents.subList(maxSize, allEvents.size()), this.mEventType);
        return result;
    }

    private void verifyCurrentFormatter(String formatterType) {
        if (this.mFormatter == null || !this.mFormatter.getFormatterType().equals(formatterType)) {
            this.mFormatter = EventsFormatterFactory.getFormatter(formatterType, this.mAdUnitType);
        }
    }

    public void setBackupThreshold(int backupThreshold) {
        if (backupThreshold > 0) {
            this.mBackupThreshold = backupThreshold;
        }
    }

    public void setMaxNumberOfEvents(int maxNumberOfEvents) {
        if (maxNumberOfEvents > 0) {
            this.mMaxNumberOfEvents = maxNumberOfEvents;
        }
    }

    public void setMaxEventsPerBatch(int maxEventsPerBatch) {
        if (maxEventsPerBatch > 0) {
            this.mMaxEventsPerBatch = maxEventsPerBatch;
        }
    }

    public void setOptOutEvents(int[] optOutEvents, Context context) {
        this.mOptOutEvents = optOutEvents;
        IronSourceUtils.saveDefaultOptOutEvents(context, this.mEventType, optOutEvents);
    }

    public void setEventsUrl(String eventsUrl, Context context) {
        if (!TextUtils.isEmpty(eventsUrl)) {
            if (this.mFormatter != null) {
                this.mFormatter.setEventsServerUrl(eventsUrl);
            }
            IronSourceUtils.saveDefaultEventsURL(context, this.mEventType, eventsUrl);
        }
    }

    public void setFormatterType(String formatterType, Context context) {
        if (!TextUtils.isEmpty(formatterType)) {
            this.mFormatterType = formatterType;
            IronSourceUtils.saveDefaultEventsFormatterType(context, this.mEventType, formatterType);
            verifyCurrentFormatter(formatterType);
        }
    }

    public void setIsEventsEnabled(boolean isEnabled) {
        this.mIsEventsEnabled = isEnabled;
    }

    private void backupEventsToDb() {
        this.mDbStorage.saveEvents(this.mLocalEvents, this.mEventType);
        this.mLocalEvents.clear();
    }

    private boolean shouldSendEvents() {
        return (this.mTotalEvents >= this.mMaxNumberOfEvents || this.mHadTopPriorityEvent) && this.mHasServerResponse;
    }

    private boolean shouldBackupEventsToDb(ArrayList<EventData> events) {
        if (events != null) {
            return events.size() >= this.mBackupThreshold;
        } else {
            return false;
        }
    }

    private boolean shouldEventBeLogged(EventData event) {
        if (event == null || this.mOptOutEvents == null || this.mOptOutEvents.length <= 0) {
            return true;
        }
        int eventId = event.getEventId();
        for (int i : this.mOptOutEvents) {
            if (eventId == i) {
                return false;
            }
        }
        return true;
    }

    public void setHasServerResponse(boolean hasResponse) {
        this.mHasServerResponse = hasResponse;
    }

    String getProviderNameForEvent(EventData event) {
        try {
            return new JSONObject(event.getAdditionalData()).optString(LocationConst.PROVIDER, "");
        } catch (JSONException e) {
            return "";
        }
    }

    public void triggerEventsSend() {
        sendEvents();
    }

    public void sendEventToUrl(EventData eventData, String url) {
        try {
            ArrayList<EventData> singleEventArray = new ArrayList();
            singleEventArray.add(eventData);
            String dataToSend = this.mFormatter.format(singleEventArray, GeneralProperties.getProperties().toJSON());
            new EventsSender().execute(new Object[]{dataToSend, url, null});
        } catch (Exception e) {
        }
    }

    private void updateSegmentsData(JSONObject object) {
        try {
            if (this.mSegment != null) {
                if (this.mSegment.getAge() > 0) {
                    object.put(IronSourceSegment.AGE, this.mSegment.getAge());
                }
                if (!TextUtils.isEmpty(this.mSegment.getGender())) {
                    object.put(IronSourceSegment.GENDER, this.mSegment.getGender());
                }
                if (this.mSegment.getLevel() > 0) {
                    object.put(IronSourceSegment.LEVEL, this.mSegment.getLevel());
                }
                if (this.mSegment.getIsPaying() != null) {
                    object.put(IronSourceSegment.PAYING, this.mSegment.getIsPaying().get());
                }
                if (this.mSegment.getIapt() > 0.0d) {
                    object.put(IronSourceSegment.IAPT, this.mSegment.getIapt());
                }
                if (this.mSegment.getUcd() > 0) {
                    object.put(IronSourceSegment.USER_CREATION_DATE, this.mSegment.getUcd());
                }
            }
            if (this.mServerSegmentData != null) {
                String id = this.mServerSegmentData.getSegmentId();
                if (!TextUtils.isEmpty(id)) {
                    object.put("segmentId", id);
                }
                JSONObject customs = this.mServerSegmentData.getCustomSegments();
                Iterator<String> iterator = customs.keys();
                while (iterator.hasNext()) {
                    String key = (String) iterator.next();
                    object.put(key, customs.get(key));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private boolean shouldAddSessionDepth(EventData event) {
        return (event.getEventId() == 14 || event.getEventId() == IronSourceConstants.USING_CACHE_FOR_INIT_EVENT || event.getEventId() == 40 || event.getEventId() == 41) ? false : true;
    }
}
