package com.adjust.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.SocketTimeoutException;

public class RequestHandler implements IRequestHandler {
    private ILogger logger = AdjustFactory.getLogger();
    private WeakReference<IPackageHandler> packageHandlerWeakRef;
    private CustomScheduledExecutor scheduledExecutor = new CustomScheduledExecutor("RequestHandler", false);

    public RequestHandler(IPackageHandler packageHandler) {
        init(packageHandler);
    }

    public void init(IPackageHandler packageHandler) {
        this.packageHandlerWeakRef = new WeakReference(packageHandler);
    }

    public void sendPackage(final ActivityPackage activityPackage, final int queueSize) {
        this.scheduledExecutor.submit(new Runnable() {
            public void run() {
                RequestHandler.this.sendI(activityPackage, queueSize);
            }
        });
    }

    public void teardown() {
        this.logger.verbose("RequestHandler teardown", new Object[0]);
        if (this.scheduledExecutor != null) {
            try {
                this.scheduledExecutor.shutdownNow();
            } catch (SecurityException e) {
            }
        }
        if (this.packageHandlerWeakRef != null) {
            this.packageHandlerWeakRef.clear();
        }
        this.scheduledExecutor = null;
        this.packageHandlerWeakRef = null;
        this.logger = null;
    }

    private void sendI(ActivityPackage activityPackage, int queueSize) {
        try {
            ResponseData responseData = UtilNetworking.createPOSTHttpsURLConnection(Constants.BASE_URL + activityPackage.getPath(), activityPackage, queueSize);
            IPackageHandler packageHandler = (IPackageHandler) this.packageHandlerWeakRef.get();
            if (packageHandler != null) {
                if (responseData.jsonResponse == null) {
                    packageHandler.closeFirstPackage(responseData, activityPackage);
                } else {
                    packageHandler.sendNextPackage(responseData);
                }
            }
        } catch (UnsupportedEncodingException e) {
            sendNextPackageI(activityPackage, "Failed to encode parameters", e);
        } catch (SocketTimeoutException e2) {
            closePackageI(activityPackage, "Request timed out", e2);
        } catch (IOException e3) {
            closePackageI(activityPackage, "Request failed", e3);
        } catch (Throwable e4) {
            sendNextPackageI(activityPackage, "Runtime exception", e4);
        }
    }

    private void closePackageI(ActivityPackage activityPackage, String message, Throwable throwable) {
        String packageMessage = activityPackage.getFailureMessage();
        String reasonString = Util.getReasonString(message, throwable);
        String finalMessage = Util.formatString("%s. (%s) Will retry later", packageMessage, reasonString);
        this.logger.error(finalMessage, new Object[0]);
        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;
        IPackageHandler packageHandler = (IPackageHandler) this.packageHandlerWeakRef.get();
        if (packageHandler != null) {
            packageHandler.closeFirstPackage(responseData, activityPackage);
        }
    }

    private void sendNextPackageI(ActivityPackage activityPackage, String message, Throwable throwable) {
        String failureMessage = activityPackage.getFailureMessage();
        String reasonString = Util.getReasonString(message, throwable);
        String finalMessage = Util.formatString("%s. (%s)", failureMessage, reasonString);
        this.logger.error(finalMessage, new Object[0]);
        ResponseData responseData = ResponseData.buildResponseData(activityPackage);
        responseData.message = finalMessage;
        IPackageHandler packageHandler = (IPackageHandler) this.packageHandlerWeakRef.get();
        if (packageHandler != null) {
            packageHandler.sendNextPackage(responseData);
        }
    }
}
