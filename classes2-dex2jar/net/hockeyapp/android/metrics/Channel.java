// 
// Decompiled by Procyon v0.5.34
// 

package net.hockeyapp.android.metrics;

import java.io.IOException;
import java.io.Writer;
import java.io.StringWriter;
import java.util.TimerTask;
import net.hockeyapp.android.utils.HockeyLog;
import java.util.Map;
import net.hockeyapp.android.utils.Util;
import java.util.Date;
import net.hockeyapp.android.metrics.model.TelemetryData;
import net.hockeyapp.android.metrics.model.Base;
import net.hockeyapp.android.metrics.model.Envelope;
import net.hockeyapp.android.metrics.model.Domain;
import net.hockeyapp.android.metrics.model.Data;
import java.util.LinkedList;
import java.util.Timer;
import java.util.List;

class Channel
{
    private static final Object LOCK;
    private static final String TAG = "HockeyApp-Metrics";
    protected static int mMaxBatchCount;
    protected static int mMaxBatchInterval;
    private final Persistence mPersistence;
    protected final List<String> mQueue;
    private SynchronizeChannelTask mSynchronizeTask;
    protected final TelemetryContext mTelemetryContext;
    private final Timer mTimer;
    
    static {
        LOCK = new Object();
        Channel.mMaxBatchCount = 50;
        Channel.mMaxBatchInterval = 15000;
    }
    
    public Channel(final TelemetryContext mTelemetryContext, final Persistence mPersistence) {
        this.mTelemetryContext = mTelemetryContext;
        this.mQueue = new LinkedList<String>();
        this.mPersistence = mPersistence;
        this.mTimer = new Timer("HockeyApp User Metrics Sender Queue", true);
    }
    
    protected Envelope createEnvelope(final Data<Domain> data) {
        final Envelope envelope = new Envelope();
        envelope.setData(data);
        final TelemetryData baseData = data.getBaseData();
        if (baseData instanceof TelemetryData) {
            envelope.setName(baseData.getEnvelopeName());
        }
        this.mTelemetryContext.updateScreenResolution();
        envelope.setTime(Util.dateToISO8601(new Date()));
        envelope.setIKey(this.mTelemetryContext.getInstrumentationKey());
        final Map<String, String> contextTags = this.mTelemetryContext.getContextTags();
        if (contextTags != null) {
            envelope.setTags(contextTags);
        }
        return envelope;
    }
    
    protected void enqueue(final String s) {
        if (s == null) {
            return;
        }
        while (true) {
            Label_0071: {
                synchronized (Channel.LOCK) {
                    if (!this.mQueue.add(s)) {
                        break Label_0071;
                    }
                    if (this.mQueue.size() >= Channel.mMaxBatchCount) {
                        this.synchronize();
                        return;
                    }
                }
                if (this.mQueue.size() == 1) {
                    this.scheduleSynchronizeTask();
                    return;
                }
                return;
            }
            HockeyLog.verbose("HockeyApp-Metrics", "Unable to add item to queue");
        }
    }
    
    public void enqueueData(final Base base) {
        if (base instanceof Data) {
            final Envelope envelope = null;
            while (true) {
                try {
                    final Envelope envelope2 = this.createEnvelope((Data<Domain>)base);
                    if (envelope2 != null) {
                        this.enqueue(this.serializeEnvelope(envelope2));
                        HockeyLog.debug("HockeyApp-Metrics", "enqueued telemetry: " + envelope2.getName());
                    }
                    return;
                }
                catch (ClassCastException ex) {
                    HockeyLog.debug("HockeyApp-Metrics", "Telemetry not enqueued, could not create envelope, must be of type ITelemetry");
                    final Envelope envelope2 = envelope;
                    continue;
                }
                break;
            }
        }
        HockeyLog.debug("HockeyApp-Metrics", "Telemetry not enqueued, must be of type ITelemetry");
    }
    
    protected void scheduleSynchronizeTask() {
        this.mSynchronizeTask = new SynchronizeChannelTask();
        this.mTimer.schedule(this.mSynchronizeTask, Channel.mMaxBatchInterval);
    }
    
    protected String serializeEnvelope(final Envelope envelope) {
        Label_0022: {
            if (envelope == null) {
                break Label_0022;
            }
            try {
                final StringWriter stringWriter = new StringWriter();
                envelope.serialize(stringWriter);
                return stringWriter.toString();
                HockeyLog.debug("HockeyApp-Metrics", "Envelope wasn't empty but failed to serialize anything, returning null");
                return null;
            }
            catch (IOException ex) {
                HockeyLog.debug("HockeyApp-Metrics", "Failed to save data with exception: " + ex.toString());
                return null;
            }
        }
    }
    
    protected void synchronize() {
        if (this.mSynchronizeTask != null) {
            this.mSynchronizeTask.cancel();
        }
        if (!this.mQueue.isEmpty()) {
            final String[] array = new String[this.mQueue.size()];
            this.mQueue.toArray(array);
            this.mQueue.clear();
            if (this.mPersistence != null) {
                this.mPersistence.persist(array);
            }
        }
    }
    
    private class SynchronizeChannelTask extends TimerTask
    {
        public SynchronizeChannelTask() {
        }
        
        @Override
        public void run() {
            Channel.this.synchronize();
        }
    }
}
