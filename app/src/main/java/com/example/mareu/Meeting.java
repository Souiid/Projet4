package com.example.mareu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Meeting implements Serializable {
    String topic;
    Date date;
    String place;
    List<String> participants;

    public Meeting(String topic, Date date, String place, List<String> participants) {
        this.topic = topic;
        this.date = date;
        this.place = place;
        this.participants = participants;
    }

}
