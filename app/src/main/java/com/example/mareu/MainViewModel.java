package com.example.mareu;

import androidx.lifecycle.ViewModel;

import com.example.mareu.repositories.MeetingRepository;
import com.example.mareu.service.MeetingAPIService;

import java.util.List;

public class MainViewModel extends ViewModel {

    MeetingRepository meetingRepository = new MeetingRepository();
    List<Meeting> meetings = meetingRepository.meetings;

    void deleteMeeting(Meeting meeting) {
        meetingRepository.deleteMeeting(meeting);
    }
}
