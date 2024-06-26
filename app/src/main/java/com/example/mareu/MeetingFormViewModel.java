package com.example.mareu;

import androidx.lifecycle.ViewModel;

import com.example.mareu.repositories.MeetingRepository;
import com.example.mareu.repositories.PlaceRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * MeetingFormViewModel is a ViewModel class that provides data and handles actions
 * for the MeetingFormActivity.
 * It acts as an intermediary between the UI and the data layer, managing the data in a lifecycle-conscious way.
 */
public class MeetingFormViewModel extends ViewModel {

    MeetingRepository meetingRepository = new MeetingRepository();


    /**
     * Manages meeting form errors.
     *
     * @param topic The topic of the meeting.
     * @param participants The list of participants.
     * @param isDateSelected A boolean indicating whether a date has been selected.
     * @return A string representing the error message, or null if no errors are found.
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
     * Verifies if an entry is a valid email.
     *
     * @param email The email to be verified.
     * @return A boolean indicating whether the email is valid.
     */
     Boolean isMail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    /**
     * Sets up the available places for meetings based on the selected date.
     *
     * @param meetings The list of existing meetings.
     * @param selectedDate The selected date for the new meeting.
     * @return A list of available places.
     */
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
     * Adds a meeting to the list of meetings.
     *
     * @param meeting The meeting to be added.
     */
    void addMeeting(Meeting meeting) {
         meetingRepository.addMeeting(meeting);
    }

}
