package com.example.mareu.DI;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingAPIService;

/**
 * Dependency injection.
 */
public class DI {

    private static final MeetingAPIService meetingAPIService = new DummyMeetingApiService();

    public static MeetingAPIService getMeetingApiService() {
        return meetingAPIService;
    }

    public static MeetingAPIService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
