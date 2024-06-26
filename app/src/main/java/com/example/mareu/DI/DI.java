package com.example.mareu.DI;

import com.example.mareu.service.DummyMeetingApiService;
import com.example.mareu.service.MeetingAPIService;

/**
 * Dependency injection class for providing instances of {@link MeetingAPIService}.
 * This class ensures that the same instance of the service can be reused across the application,
 * while also providing a method to create a new instance if needed.
 */
public class DI {

    // Singleton instance of MeetingAPIService
    private static final MeetingAPIService meetingAPIService = new DummyMeetingApiService();

    /**
     * Returns the singleton instance of the MeetingAPIService.
     *
     * @return the singleton instance of the MeetingAPIService.
     */
    public static MeetingAPIService getMeetingApiService() {
        return meetingAPIService;
    }


    /**
     * Returns a new instance of the MeetingAPIService.
     *
     * @return a new instance of the MeetingAPIService.
     */
    public static MeetingAPIService getNewInstanceApiService() {
        return new DummyMeetingApiService();
    }
}
