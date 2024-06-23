package com.example.mareu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * This class represent a meeting.
 * Contains information like topic, date, room and participants
 */
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

    public Date getDate() {
        return date;
    }

    public String getPlace() {
        return place;
    }

    public String getTopic() {
        return topic;
    }

}
