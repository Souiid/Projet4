package com.example.mareu.repositories;

import com.example.mareu.DI.DI;
import com.example.mareu.Meeting;
import com.example.mareu.service.MeetingAPIService;

import java.util.List;

public class MeetingRepository {
    MeetingAPIService apiService = DI.getMeetingApiService();
   public List<Meeting> meetings = apiService.getMeetings();

   public void deleteMeeting(Meeting meeting) {
       apiService.deleteMeeting(meeting);
   }

   public void addMeeting(Meeting meeting) {
       apiService.addMeeting(meeting);
   }
}
