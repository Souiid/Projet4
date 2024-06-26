package com.example.mareu;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * Meeting is a class that represents a meeting with a topic, date, place, and list of participants.
 * This class implements Serializable to allow meeting objects to be serialized.
 */
public class Meeting implements Serializable {
    String topic;
    Date date;
    String place;
    List<String> participants;



    /**
     * Constructs a new Meeting with the specified topic, date, place, and participants.
     *
     * @param topic The topic of the meeting.
     * @param date The date and time of the meeting.
     * @param place The place where the meeting will be held.
     * @param participants The list of participants in the meeting.
     */
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
