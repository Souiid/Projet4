package com.example.mareu.service;

import com.example.mareu.Meeting;

import java.util.List;

/**
 * MeetingAPIService is an interface that defines the operations for managing meetings.
 * Implementations of this interface should provide the functionality to retrieve, add, and delete meetings.
 */
public interface MeetingAPIService {

    /**
     * Retrieves the list of meetings.
     *
     * @return a list of Meeting objects.
     */
    List<Meeting> getMeetings();

    /**
     * Deletes a specified meeting.
     *
     * @param meeting The meeting to be deleted.
     */
    void deleteMeeting(Meeting meeting);

    /**
     * Adds a new meeting.
     *
     * @param meeting The meeting to be added.
     */
    void addMeeting(Meeting meeting);
}

