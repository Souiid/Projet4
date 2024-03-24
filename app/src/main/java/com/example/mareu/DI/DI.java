package com.example.mareu.DI;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingAPIService;

public class DI {

    private static MeetingAPIService service = new DummyMeetingApiService();

    public static MeetingAPIService getMeetingApiService() {
        return service;
    }

    public static MeetingAPIService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
