package com.adjust.sdk;

import android.content.Context;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

public class PackageHandler implements IPackageHandler {
    private static final String PACKAGE_QUEUE_FILENAME = "AdjustIoPackageQueue";
    private static final String PACKAGE_QUEUE_NAME = "Package queue";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy = AdjustFactory.getPackageHandlerBackoffStrategy();
    private Context context;
    private AtomicBoolean isSending;
    private ILogger logger = AdjustFactory.getLogger();
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private IRequestHandler requestHandler;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("PackageHandler", false);

    /* renamed from: com.adjust.sdk.PackageHandler$1 */
    class C00891 implements Runnable {
        C00891() {
        }

        public void run() {
            PackageHandler.this.initI();
        }
    }

    /* renamed from: com.adjust.sdk.PackageHandler$3 */
    class C00913 implements Runnable {
        C00913() {
        }

        public void run() {
            PackageHandler.this.sendFirstI();
        }
    }

    /* renamed from: com.adjust.sdk.PackageHandler$4 */
    class C00924 implements Runnable {
        C00924() {
        }

        public void run() {
            PackageHandler.this.sendNextI();
        }
    }

    /* renamed from: com.adjust.sdk.PackageHandler$5 */
    class C00935 implements Runnable {
        C00935() {
        }

        public void run() {
            PackageHandler.this.logger.verbose("Package handler can send", new Object[0]);
            PackageHandler.this.isSending.set(false);
            PackageHandler.this.sendFirstPackage();
        }
    }

    public void teardown(boolean deleteState) {
        this.logger.verbose("PackageHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.activityHandlerWeakRef != null) {
            this.activityHandlerWeakRef.clear();
        }
        if (this.requestHandler != null) {
            this.requestHandler.teardown();
        }
        if (this.packageQueue != null) {
            this.packageQueue.clear();
        }
        if (deleteState && this.context != null) {
            deletePackageQueue(this.context);
        }
        this.scheduledExecutor = null;
        this.requestHandler = null;
        this.activityHandlerWeakRef = null;
        this.packageQueue = null;
        this.isSending = null;
        this.context = null;
        this.logger = null;
        this.backoffStrategy = null;
    }

    public PackageHandler(IActivityHandler activityHandler, Context context, boolean startsSending) {
        init(activityHandler, context, startsSending);
        this.scheduledExecutor.submit(new C00891());
    }

    public void init(IActivityHandler activityHandler, Context context, boolean startsSending) {
        this.activityHandlerWeakRef = new WeakReference(activityHandler);
        this.context = context;
        this.paused = !startsSending;
    }

    public void addPackage(final ActivityPackage activityPackage) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.addI(activityPackage);
            }
        });
    }

    public void sendFirstPackage() {
        this.scheduledExecutor.submit(new C00913());
    }

    public void sendNextPackage(ResponseData responseData) {
        this.scheduledExecutor.submit(new C00924());
        IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
    }

    public void closeFirstPackage(ResponseData responseData, ActivityPackage activityPackage) {
        responseData.willRetry = true;
        IActivityHandler activityHandler = (IActivityHandler) this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
        Runnable runnable = new C00935();
        if (activityPackage == null) {
            runnable.run();
            return;
        }
        long waitTimeMilliSeconds = Util.getWaitingTime(activityPackage.increaseRetries(), this.backoffStrategy);
        String secondsString = Util.SecondsDisplayFormat.format(((double) waitTimeMilliSeconds) / 1000.0d);
        this.logger.verbose("Waiting for %s seconds before retrying the %d time", secondsString, Integer.valueOf(retries));
        this.scheduledExecutor.schedule(runnable, waitTimeMilliSeconds, TimeUnit.MILLISECONDS);
    }

    public void pauseSending() {
        this.paused = true;
    }

    public void resumeSending() {
        this.paused = false;
    }

    public void updatePackages(SessionParameters sessionParameters) {
        SessionParameters sessionParametersCopy;
        if (sessionParameters != null) {
            sessionParametersCopy = sessionParameters.deepCopy();
        } else {
            sessionParametersCopy = null;
        }
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                PackageHandler.this.updatePackagesI(sessionParametersCopy);
            }
        });
    }

    private void initI() {
        this.requestHandler = AdjustFactory.getRequestHandler(this);
        this.isSending = new AtomicBoolean();
        readPackageQueueI();
    }

    private void addI(ActivityPackage newPackage) {
        this.packageQueue.add(newPackage);
        this.logger.debug("Added package %d (%s)", Integer.valueOf(this.packageQueue.size()), newPackage);
        this.logger.verbose("%s", newPackage.getExtendedString());
        writePackageQueueI();
    }

    private void sendFirstI() {
        if (!this.packageQueue.isEmpty()) {
            if (this.paused) {
                this.logger.debug("Package handler is paused", new Object[0]);
            } else if (this.isSending.getAndSet(true)) {
                this.logger.verbose("Package handler is already sending", new Object[0]);
            } else {
                this.requestHandler.sendPackage((ActivityPackage) this.packageQueue.get(0), this.packageQueue.size() - 1);
            }
        }
    }

    private void sendNextI() {
        this.packageQueue.remove(0);
        writePackageQueueI();
        this.isSending.set(false);
        this.logger.verbose("Package handler can send", new Object[0]);
        sendFirstI();
    }

    public void updatePackagesI(SessionParameters sessionParameters) {
        if (sessionParameters != null) {
            this.logger.debug("Updating package handler queue", new Object[0]);
            this.logger.verbose("Session callback parameters: %s", sessionParameters.callbackParameters);
            this.logger.verbose("Session partner parameters: %s", sessionParameters.partnerParameters);
            for (ActivityPackage activityPackage : this.packageQueue) {
                Map<String, String> parameters = activityPackage.getParameters();
                PackageBuilder.addMapJson(parameters, Constants.CALLBACK_PARAMETERS, Util.mergeParameters(sessionParameters.callbackParameters, activityPackage.getCallbackParameters(), "Callback"));
                PackageBuilder.addMapJson(parameters, Constants.PARTNER_PARAMETERS, Util.mergeParameters(sessionParameters.partnerParameters, activityPackage.getPartnerParameters(), "Partner"));
            }
            writePackageQueueI();
        }
    }

    private void readPackageQueueI() {
        try {
            this.packageQueue = (List) Util.readObject(this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME, List.class);
        } catch (Exception e) {
            this.logger.error("Failed to read %s file (%s)", PACKAGE_QUEUE_NAME, e.getMessage());
            this.packageQueue = null;
        }
        if (this.packageQueue != null) {
            this.logger.debug("Package handler read %d packages", Integer.valueOf(this.packageQueue.size()));
            return;
        }
        this.packageQueue = new ArrayList();
    }

    private void writePackageQueueI() {
        Util.writeObject(this.packageQueue, this.context, PACKAGE_QUEUE_FILENAME, PACKAGE_QUEUE_NAME);
        this.logger.debug("Package handler wrote %d packages", Integer.valueOf(this.packageQueue.size()));
    }

    public static Boolean deletePackageQueue(Context context) {
        return Boolean.valueOf(context.deleteFile(PACKAGE_QUEUE_FILENAME));
    }
}
