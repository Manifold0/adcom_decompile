package com.vungle.warren;

import com.vungle.warren.DownloadStrategy.VerificationCallback;
import com.vungle.warren.SDKDownloadClient.ValidationCheck;

public class DirectDownloadStrategy implements DownloadStrategy {
    private PublisherDirectDownload directDownload;

    public DirectDownloadStrategy(PublisherDirectDownload directDownload) {
        this.directDownload = directDownload;
    }

    public void isApplicationAvailable(String marketId, final VerificationCallback downloadVerificationResult) {
        DirectDownloadAdapter directDownloadAdapter = new DirectDownloadAdapter(this.directDownload, marketId);
        directDownloadAdapter.getSdkDownloadClient().setAppMarketValidation(new ValidationCheck() {
            public void validateAppPresenceInMarket(boolean isAppInMarket) {
                downloadVerificationResult.onResult(isAppInMarket);
            }
        });
        directDownloadAdapter.getSdkDownloadClient().sendValidation(marketId);
    }
}
