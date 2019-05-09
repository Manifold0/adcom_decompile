// 
// Decompiled by Procyon v0.5.34
// 

package com.adjust.sdk;

import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import android.content.Context;
import java.lang.ref.WeakReference;

public class PackageHandler implements IPackageHandler
{
    private static final String PACKAGE_QUEUE_FILENAME = "AdjustIoPackageQueue";
    private static final String PACKAGE_QUEUE_NAME = "Package queue";
    private WeakReference<IActivityHandler> activityHandlerWeakRef;
    private BackoffStrategy backoffStrategy;
    private Context context;
    private AtomicBoolean isSending;
    private ILogger logger;
    private List<ActivityPackage> packageQueue;
    private boolean paused;
    private IRequestHandler requestHandler;
    private CustomScheduledExecutor scheduledExecutor;
    
    public PackageHandler(final IActivityHandler activityHandler, final Context context, final boolean b) {
        this.scheduledExecutor = new CustomScheduledExecutor("PackageHandler", false);
        this.logger = AdjustFactory.getLogger();
        this.backoffStrategy = AdjustFactory.getPackageHandlerBackoffStrategy();
        this.init(activityHandler, context, b);
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.initI();
            }
        });
    }
    
    private void addI(final ActivityPackage activityPackage) {
        this.packageQueue.add(activityPackage);
        this.logger.debug("Added package %d (%s)", this.packageQueue.size(), activityPackage);
        this.logger.verbose("%s", activityPackage.getExtendedString());
        this.writePackageQueueI();
    }
    
    public static Boolean deletePackageQueue(final Context context) {
        return context.deleteFile("AdjustIoPackageQueue");
    }
    
    private void initI() {
        this.requestHandler = AdjustFactory.getRequestHandler(this);
        this.isSending = new AtomicBoolean();
        this.readPackageQueueI();
    }
    
    private void readPackageQueueI() {
        while (true) {
            try {
                this.packageQueue = Util.readObject(this.context, "AdjustIoPackageQueue", "Package queue", (Class<List<ActivityPackage>>)List.class);
                if (this.packageQueue != null) {
                    this.logger.debug("Package handler read %d packages", this.packageQueue.size());
                    return;
                }
            }
            catch (Exception ex) {
                this.logger.error("Failed to read %s file (%s)", "Package queue", ex.getMessage());
                this.packageQueue = null;
                continue;
            }
            break;
        }
        this.packageQueue = new ArrayList<ActivityPackage>();
    }
    
    private void sendFirstI() {
        if (this.packageQueue.isEmpty()) {
            return;
        }
        if (this.paused) {
            this.logger.debug("Package handler is paused", new Object[0]);
            return;
        }
        if (this.isSending.getAndSet(true)) {
            this.logger.verbose("Package handler is already sending", new Object[0]);
            return;
        }
        this.requestHandler.sendPackage(this.packageQueue.get(0), this.packageQueue.size() - 1);
    }
    
    private void sendNextI() {
        this.packageQueue.remove(0);
        this.writePackageQueueI();
        this.isSending.set(false);
        this.logger.verbose("Package handler can send", new Object[0]);
        this.sendFirstI();
    }
    
    private void writePackageQueueI() {
        Util.writeObject(this.packageQueue, this.context, "AdjustIoPackageQueue", "Package queue");
        this.logger.debug("Package handler wrote %d packages", this.packageQueue.size());
    }
    
    @Override
    public void addPackage(final ActivityPackage activityPackage) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.addI(activityPackage);
            }
        });
    }
    
    @Override
    public void closeFirstPackage(final ResponseData responseData, final ActivityPackage activityPackage) {
        responseData.willRetry = true;
        final IActivityHandler activityHandler = this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.logger.verbose("Package handler can send", new Object[0]);
                PackageHandler.this.isSending.set(false);
                PackageHandler.this.sendFirstPackage();
            }
        };
        if (activityPackage == null) {
            runnable.run();
            return;
        }
        final int increaseRetries = activityPackage.increaseRetries();
        final long waitingTime = Util.getWaitingTime(increaseRetries, this.backoffStrategy);
        this.logger.verbose("Waiting for %s seconds before retrying the %d time", Util.SecondsDisplayFormat.format(waitingTime / 1000.0), increaseRetries);
        this.scheduledExecutor.schedule(runnable, waitingTime, TimeUnit.MILLISECONDS);
    }
    
    @Override
    public void init(final IActivityHandler activityHandler, final Context context, final boolean b) {
        this.activityHandlerWeakRef = new WeakReference<IActivityHandler>(activityHandler);
        this.context = context;
        this.paused = !b;
    }
    
    @Override
    public void pauseSending() {
        this.paused = true;
    }
    
    @Override
    public void resumeSending() {
        this.paused = false;
    }
    
    @Override
    public void sendFirstPackage() {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.sendFirstI();
            }
        });
    }
    
    @Override
    public void sendNextPackage(final ResponseData responseData) {
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.sendNextI();
            }
        });
        final IActivityHandler activityHandler = this.activityHandlerWeakRef.get();
        if (activityHandler != null) {
            activityHandler.finishedTrackingActivity(responseData);
        }
    }
    
    @Override
    public void teardown(final boolean b) {
        this.logger.verbose("PackageHandler teardown", new Object[0]);
        while (true) {
            if (this.scheduledExecutor == null) {
                break Label_0030;
            }
            try {
                this.scheduledExecutor.shutdownNow();
                if (this.activityHandlerWeakRef != null) {
                    this.activityHandlerWeakRef.clear();
                }
                if (this.requestHandler != null) {
                    this.requestHandler.teardown();
                }
                if (this.packageQueue != null) {
                    this.packageQueue.clear();
                }
                if (b && this.context != null) {
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
            catch (SecurityException ex) {
                continue;
            }
            break;
        }
    }
    
    @Override
    public void updatePackages(SessionParameters deepCopy) {
        if (deepCopy != null) {
            deepCopy = deepCopy.deepCopy();
        }
        else {
            deepCopy = null;
        }
        this.scheduledExecutor.submit(new Runnable() {
            @Override
            public void run() {
                PackageHandler.this.updatePackagesI(deepCopy);
            }
        });
    }
    
    public void updatePackagesI(final SessionParameters sessionParameters) {
        if (sessionParameters == null) {
            return;
        }
        this.logger.debug("Updating package handler queue", new Object[0]);
        this.logger.verbose("Session callback parameters: %s", sessionParameters.callbackParameters);
        this.logger.verbose("Session partner parameters: %s", sessionParameters.partnerParameters);
        for (final ActivityPackage activityPackage : this.packageQueue) {
            final Map<String, String> parameters = activityPackage.getParameters();
            PackageBuilder.addMapJson(parameters, "callback_params", Util.mergeParameters(sessionParameters.callbackParameters, activityPackage.getCallbackParameters(), "Callback"));
            PackageBuilder.addMapJson(parameters, "partner_params", Util.mergeParameters(sessionParameters.partnerParameters, activityPackage.getPartnerParameters(), "Partner"));
        }
        this.writePackageQueueI();
    }
}
