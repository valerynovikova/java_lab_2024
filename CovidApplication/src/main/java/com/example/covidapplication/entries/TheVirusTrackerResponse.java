package com.example.covidapplication.entries;

import java.util.List;

public class TheVirusTrackerResponse {
    private List<TheVirusTrackerRecord> data;

    public List<TheVirusTrackerRecord> getData() {
        return data;
    }

    public void setData(List<TheVirusTrackerRecord> data) {
        this.data = data;
    }
}
