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
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(JUnit4.class)
public class MeetingServiceUnitTest {

    private MeetingAPIService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    @Test
    public void getMeetingsWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void deleteNeighbourWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }

    @Test
    public void addNeighbourWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        int meetingsSize = meetings.size();
        Meeting newMeeting = meetings.get(0);
        service.addMeeting(newMeeting);
        int newMeetingsSize =  meetings.size();
        assert(newMeetingsSize == meetingsSize + 1);
    }
}