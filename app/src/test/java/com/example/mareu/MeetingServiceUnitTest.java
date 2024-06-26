package com.example.mareu;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

import com.example.mareu.DI.DI;
import com.example.mareu.service.DummyMeetingGenerator;
import com.example.mareu.service.MeetingAPIService;

import java.util.List;

/**
 * Unit tests for the MeetingAPIService.
 * These tests ensure that the MeetingAPIService methods work correctly.
 */
@RunWith(JUnit4.class)
public class MeetingServiceUnitTest {

    private MeetingAPIService service;

    /**
     * Sets up the test environment before each test.
     * Initializes the MeetingAPIService with a new instance.
     */
    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Tests that the getMeetings method returns the expected list of meetings.
     */
    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    /**
     * Tests that the deleteMeeting method successfully deletes a meeting.
     */
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    /**
     * Tests that the addMeeting method successfully adds a new meeting.
     */
    @Test
    public void addMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        int meetingsSize = meetings.size();
        Meeting newMeeting = meetings.get(0);
        service.addMeeting(newMeeting);
        int newMeetingsSize =  meetings.size();
        assert(newMeetingsSize == meetingsSize + 1);
    }
}