package com.example.mareu.repositories;

import com.example.mareu.DI.DI;
import com.example.mareu.Meeting;
import com.example.mareu.service.MeetingAPIService;

import java.util.List;

/**
 * Repository class for managing meeting data.
 * This class acts as an intermediary between the data source (MeetingAPIService)
 * and the rest of the application, providing methods to interact with the data.
 *
 */
public class MeetingRepository {
    // The API service for managing meeting data
    MeetingAPIService apiService = DI.getMeetingApiService();

    // The list of meetings retrieved from the API service
    public List<Meeting> meetings = apiService.getMeetings();

    /**
     * Deletes a meeting from the repository.
     *
     * @param meeting The meeting to be deleted.
     */
    public void deleteMeeting(Meeting meeting) {
        apiService.deleteMeeting(meeting);
    }

    /**
     * Adds a new meeting to the repository.
     *
     * @param meeting The meeting to be added.
     */
    public void addMeeting(Meeting meeting) {
        apiService.addMeeting(meeting);
    }
}
