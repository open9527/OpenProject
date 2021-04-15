package com.android.open9527.image.pkg;

/**
 * @author open_9527
 * Create at 2021/2/1
 **/
public class LocationChangeEvent {

    private int startPosition = 0;
    private int currentPosition = 0;

    public LocationChangeEvent(int startPosition, int currentPosition) {
        this.startPosition = startPosition;
        this.currentPosition = currentPosition;
    }

    public int getStartPosition() {
        return startPosition;
    }

    public void setStartPosition(int startPosition) {
        this.startPosition = startPosition;
    }

    public int getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(int currentPosition) {
        this.currentPosition = currentPosition;
    }

    @Override
    public String toString() {
        return "LocationChangeEvent{" +
                "startPosition=" + startPosition +
                ", currentPosition=" + currentPosition +
                '}';
    }
}
