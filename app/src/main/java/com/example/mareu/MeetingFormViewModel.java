package com.example.mareu;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MeetingFormViewModel extends ViewModel {


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

     Boolean isMail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }

    List<String> setUpPlaces(List<Meeting> meetings, Date selectedDate) {
        List<String> places = new ArrayList<String>(
                Arrays.asList("Mario", "Luigi", "Waluigi", "Wario", "Bowser", "Peach", "Daisy", "Yoshi", "Donkey Kong", "Toad")
        );
        for (Meeting meeting : meetings) {
            long diffInMillies = Math.abs(meeting.getDate().getTime() - selectedDate.getTime());
            long diffInMinutes = diffInMillies / (60 * 1000);
            if (diffInMinutes < 45) {
                places.remove(meeting.getPlace());
            }
        }
        return places;
    }

}
