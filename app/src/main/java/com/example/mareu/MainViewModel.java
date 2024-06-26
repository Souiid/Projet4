package com.example.mareu;

import androidx.lifecycle.ViewModel;

import com.example.mareu.repositories.MeetingRepository;
import com.example.mareu.service.MeetingAPIService;

import java.util.List;

/**
 * MainViewModel is a ViewModel class that provides data and handles actions
 * for the MainActivity.
 * It acts as an intermediary between the UI and the data layer,
 * managing the data in a lifecycle-conscious way.
 *
 */
public class MainViewModel extends ViewModel {

    MeetingRepository meetingRepository = new MeetingRepository();
    List<Meeting> meetings = meetingRepository.meetings;

    /**
     * Deletes a specified meeting.
     *
     * @param meeting The meeting to be deleted.
     */
    void deleteMeeting(Meeting meeting) {
        meetingRepository.deleteMeeting(meeting);
    }
}
