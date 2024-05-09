package com.example.mareu.service;

import com.example.mareu.Meeting;

import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyMeetingApiService implements  MeetingAPIService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }

}
