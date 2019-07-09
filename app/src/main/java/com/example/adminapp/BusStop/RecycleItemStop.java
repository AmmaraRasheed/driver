package com.example.adminapp.BusStop;

public class RecycleItemStop {
    String stop,stopTitle;

    public RecycleItemStop(String stop, String stopTitle) {

        this.stop = stop;
        this.stopTitle = stopTitle;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getStopTitle() {
        return stopTitle;
    }

    public void setStopTitle(String stopTitle) {
        this.stopTitle = stopTitle;
    }
}
