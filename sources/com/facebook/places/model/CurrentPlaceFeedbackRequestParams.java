package com.facebook.places.model;

public class CurrentPlaceFeedbackRequestParams {
    private final String placeId;
    private final String tracking;
    private final Boolean wasHere;

    public static class Builder {
        private String placeId;
        private String tracking;
        private Boolean wasHere;

        public Builder setWasHere(boolean wasHere) {
            this.wasHere = Boolean.valueOf(wasHere);
            return this;
        }

        public Builder setPlaceId(String placeId) {
            this.placeId = placeId;
            return this;
        }

        public Builder setTracking(String tracking) {
            this.tracking = tracking;
            return this;
        }

        public CurrentPlaceFeedbackRequestParams build() {
            return new CurrentPlaceFeedbackRequestParams();
        }
    }

    private CurrentPlaceFeedbackRequestParams(Builder b) {
        this.tracking = b.tracking;
        this.placeId = b.placeId;
        this.wasHere = b.wasHere;
    }

    public String getTracking() {
        return this.tracking;
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public Boolean wasHere() {
        return this.wasHere;
    }
}
