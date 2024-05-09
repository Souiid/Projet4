package com.example.mareu.service;

import com.example.mareu.Meeting;

import java.util.List;

public interface MeetingAPIService {

    List<Meeting> getMeetings();
    void deleteMeeting(Meeting meeting);
    void addMeeting(Meeting meeting);
}
