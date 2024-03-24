package com.example.mareu.service;

import com.example.mareu.Meeting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public abstract class DummyMeetingGenerator {

    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
                    new Meeting("Réunion A", generateDate(10, 0), "Peach", new ArrayList<>(Arrays.asList("alex@lamzone.fr", "maxime@lamzone.fr", "gerald@lamzone.fr"))),
       new Meeting("Réunion B",generateDate(10, 0),"Mario", new ArrayList<>(Arrays.asList("idriss@lamzone.fr", "sami@lamzone.fr", "remy@lamzone.fr"))),
       new Meeting("Réunion C", generateDate(11, 20), "Luigi", new ArrayList<>(Arrays.asList("theo@lamzone.fr", "guillaume@lamzone.fr"))),
       new Meeting("Réunion D", generateDate(9, 0), "Waluigi", new ArrayList<>(Arrays.asList("sacha@lamzone.fr", "mailys@lamzone.fr", "erwan@lamzone.fr"))),
       new Meeting("Réunion E", generateDate(8, 30), "Wario", new ArrayList<>(Arrays.asList("charlotte@lamzone.fr", "stan@lamzone.fr"))),
       new Meeting("Réunion F", generateDate(11, 30), "Bowser", new ArrayList<>(Arrays.asList("alex@lamzone.fr", "maxime@lamzone.fr"))),
       new Meeting("Réunion G", generateDate(13, 0), "Daisy", new ArrayList<>(Arrays.asList("livia@lamzone.fr", "david@lamzone.fr", "agathe@lamzone.fr"))),
       new Meeting("Réunion H", generateDate(10, 0), "Yoshi", new ArrayList<>(Arrays.asList("stephanie@lamzone.fr", "elisa@lamzone.fr", "jin@lamzone.fr"))),
       new Meeting("Réunion I", generateDate(10, 30), "Donkey Kong", new ArrayList<>(Arrays.asList("jimmy@lamzone.fr", "raphaelle@lamzone.fr"))),
       new Meeting("Réunion J", generateDate(9, 30), "Toad", new ArrayList<>(Arrays.asList("bilal@lamzone.fr", "quentin@lamzone.fr", "omar@lamzone.fr")))

    );

    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
    static Date generateDate(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2024, Calendar.APRIL, 20, hour, minute);
        return calendar.getTime();
    }

}

