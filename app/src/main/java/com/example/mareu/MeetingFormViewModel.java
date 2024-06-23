package com.example.mareu;

import androidx.lifecycle.ViewModel;

import com.example.mareu.repositories.MeetingRepository;
import com.example.mareu.repositories.PlaceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MeetingFormViewModel extends ViewModel {

    MeetingRepository meetingRepository = new MeetingRepository();

    /**
     * Manage meeting form error
     * @return an error string
     */
     String getFormError(String topic, List<String> participants, Boolean isDateSelected) {
         String message = null;

        if (topic.trim().isEmpty()) {
            message = "Le sujet est vide";
        }

        if (participants.isEmpty()) {
            message = "Il faut au moins un participant";
        }

        if (!isDateSelected) {
            message = "Il faut choisir une date";
        }
        return message;
    }

    /**
     * Verify if an entry is an email
     * @return a boolean
     */
     Boolean isMail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    List<String> setUpPlaces(List<Meeting> meetings, Date selectedDate) {
        List<String> places =  new PlaceRepository().places;
        for (Meeting meeting : meetings) {
            long diffInMillies = Math.abs(meeting.getDate().getTime() - selectedDate.getTime());
            long diffInMinutes = diffInMillies / (60 * 1000);
            if (diffInMinutes < 45) {
                places.remove(meeting.getPlace());
            }
        }
        return places;
    }

    /**
     * Add meeting to the meetings list
     */
    void addMeeting(Meeting meeting) {
         meetingRepository.addMeeting(meeting);
    }

}
