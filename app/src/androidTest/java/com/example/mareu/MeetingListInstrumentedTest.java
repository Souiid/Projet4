package com.example.mareu;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.assertThat;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static com.example.mareu.utils.RecyclerViewItemCountAssertion.withItemCount;

import android.content.Context;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewAssertion;
import androidx.test.espresso.contrib.PickerActions;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.*;

import com.example.mareu.utils.DeleteViewAction;
import com.example.mareu.utils.RecyclerViewItemCountAssertion;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MeetingListInstrumentedTest {

    private static int ITEMS_COUNT = 10;

    private MainActivity mActivity;

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule(MainActivity.class);

    @Before
    public void setUp() {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
    }

    @Test
    public void myMeetingList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingList_deleteAction_shouldRemoveItem() {
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check((ViewAssertion) withItemCount(ITEMS_COUNT));
        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(actionOnItemAtPosition(1, (ViewAction) new DeleteViewAction()));
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check((ViewAssertion) withItemCount(ITEMS_COUNT - 1));
    }

    @Test
    public void myAddButton_clickAction_shouldDisplayMeetingForm() {
        onView(allOf(withId(R.id.addButton), isDisplayed()))
                .perform(click());
        onView(withId(R.id.meeting_form)).check(matches(isDisplayed()));
    }

    @Test
    public void myMeetingList_ShowsAnotherMeetingAfterAdd() {
        onView(allOf(withId(R.id.addButton))).perform(click());
        onView(withId(R.id.topicET)).perform(typeText("Reunion Test"), closeSoftKeyboard());
        onView(withId(R.id.participantET)).perform(typeText("johndoe@mail.com"), closeSoftKeyboard());
        onView(allOf(withId(R.id.chooseDateButton))).perform(click());
        onView(isAssignableFrom(DatePicker.class)).perform(PickerActions.setDate(2024, 5, 20));
        onView(withId(android.R.id.button1)).perform(click());
        onView(isAssignableFrom(TimePicker.class)).perform(PickerActions.setTime(14, 30));
        onView(withId(android.R.id.button1)).perform(click());
        onView(allOf(withId(R.id.validateMeetingButton))).perform(click());
        onView(allOf(withId(R.id.recyclerView), isDisplayed())).check(RecyclerViewItemCountAssertion.withItemCount(ITEMS_COUNT + 1));
    }

    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.example.mareu", appContext.getPackageName());
    }
}