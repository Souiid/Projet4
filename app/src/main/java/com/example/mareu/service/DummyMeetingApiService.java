package com.example.mareu.service;

import com.example.mareu.Meeting;

import java.util.List;

/**
 * Dummy mock for the Api
 */
/**
 * Dummy implementation of the MeetingAPIService interface.
 * This class provides a simple in-memory implementation of the service,
 * primarily used for testing and development purposes.
 */
public class DummyMeetingApiService implements MeetingAPIService {

    // The list of meetings managed by this service
    private final List<Meeting> meetings = DummyMeetingGenerator.generateMeetings();

    /**
     * Retrieves the list of meetings.
     *
     * @return the list of meetings.
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    /**
     * Deletes a meeting from the list.
     *
     * @param meeting The meeting to be deleted.
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);
    }

    /**
     * Adds a new meeting to the list.
     *
     * @param meeting The meeting to be added.
     */
    @Override
    public void addMeeting(Meeting meeting) {
        meetings.add(meeting);
    }
}

