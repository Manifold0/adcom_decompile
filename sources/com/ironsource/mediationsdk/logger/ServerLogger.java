package com.ironsource.mediationsdk.logger;

import android.util.Log;
import com.ironsource.mediationsdk.logger.IronSourceLogger.IronSourceTag;
import com.ironsource.mediationsdk.utils.IronSourceUtils;
import com.ironsource.sdk.constants.Constants.RequestParameters;
import com.tapjoy.TJAdUnitConstants;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ServerLogger extends IronSourceLogger {
    public static final String NAME = "server";
    private final int SERVER_LOGS_SIZE_LIMIT = 1000;
    private ArrayList<ServerLogEntry> mLogs = new ArrayList();

    private class SendingCalc {
        private int DEFAULT_DEBUG_LEVEL = 3;
        private int DEFAULT_SIZE = 1;
        private int DEFAULT_TIME = 1;

        public SendingCalc() {
            initDefaults();
        }

        private void initDefaults() {
        }

        public void notifyEvent(int event) {
            if (calc(event)) {
                ServerLogger.this.send();
            }
        }

        private boolean calc(int event) {
            if (error(event) || size() || time()) {
                return true;
            }
            return false;
        }

        private boolean time() {
            return false;
        }

        private boolean error(int event) {
            return event == 3;
        }

        private boolean size() {
            return false;
        }
    }

    public ServerLogger() {
        super(NAME);
    }

    public ServerLogger(int debugLevel) {
        super(NAME, debugLevel);
    }

    private synchronized void addLogEntry(ServerLogEntry entry) {
        this.mLogs.add(entry);
        if (shouldSendLogs()) {
            send();
        } else if (this.mLogs.size() > 1000) {
            try {
                ArrayList<ServerLogEntry> newerLog = new ArrayList();
                for (int i = TJAdUnitConstants.DEFAULT_VOLUME_CHECK_INTERVAL; i < this.mLogs.size(); i++) {
                    newerLog.add(this.mLogs.get(i));
                }
                this.mLogs = newerLog;
            } catch (Exception e) {
                this.mLogs = new ArrayList();
            }
        }
    }

    private boolean shouldSendLogs() {
        return ((ServerLogEntry) this.mLogs.get(this.mLogs.size() + -1)).getLogLevel() == 3;
    }

    private void send() {
        IronSourceUtils.createAndStartWorker(new LogsSender(this.mLogs), "LogsSender");
        this.mLogs = new ArrayList();
    }

    private String getTimestamp() {
        return new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date());
    }

    public synchronized void log(IronSourceTag tag, String message, int logLevel) {
        addLogEntry(new ServerLogEntry(tag, getTimestamp(), message, logLevel));
    }

    public synchronized void logException(IronSourceTag tag, String message, Throwable e) {
        StringBuilder logMessage = new StringBuilder(message);
        if (e != null) {
            logMessage.append(":stacktrace[");
            logMessage.append(Log.getStackTraceString(e)).append(RequestParameters.RIGHT_BRACKETS);
        }
        addLogEntry(new ServerLogEntry(tag, getTimestamp(), logMessage.toString(), 3));
    }
}
