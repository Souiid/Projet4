package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMeetingFormBinding;
import com.example.mareu.service.MeetingAPIService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Activity to create a meeting..
 */
public class MeetingFormActivity extends AppCompatActivity {

    ActivityMeetingFormBinding binding;
    ArrayList<String> participants;
    Calendar calendar = Calendar.getInstance();
    Date date;
    List<Meeting> meetings = new ArrayList<>();
    Boolean isDateSelected = false;
    String selectedPlace;
    private MeetingFormViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_form);
        binding = ActivityMeetingFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MeetingFormViewModel.class);
        addParticipant();
        selectDate();
        participants = new ArrayList<>();
        validateMeeting();
        setButtonsVisibility(View.INVISIBLE);
        clickOnBackButton();
    }

    /**
     * Show availble rooms in spinner to create a meeting
     */
    private void setUpPlaces(Date selectedDate) {

        Intent intent = getIntent();
        if (intent.hasExtra("meetings")) {
            meetings = (List<Meeting>) intent.getSerializableExtra("meetings");
            assert meetings != null;
            List<String> places = viewModel.setUpPlaces(meetings, selectedDate);
            setUpSpinner(places);
            if (!places.isEmpty()) {
                selectedPlace = places.get(0);
                setButtonsVisibility(View.VISIBLE);
            } else {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(meetings, Comparator.comparing(Meeting::getDate));
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm", Locale.FRENCH);
                    String lastMeetingDateString = dateFormat.format(meetings.get(meetings.size() - 1).getDate());
                    showToast("Il n'y a plus de salle disponible à cette heure ci:" + lastMeetingDateString);
                    setButtonsVisibility(View.INVISIBLE);
                }
            }
        }
    }


    private void setButtonsVisibility(int visibility) {
        binding.spinner.setVisibility(visibility);
        binding.validateMeetingButton.setVisibility(visibility);
    }

    /**
     * Set up spinner with all rooms
     */
    private void setUpSpinner(List<String> places) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, places);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPlace = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    /**
     * Add participant to meeting and display it in textview
     */
    private void addParticipant() {
        binding.addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (participants.size() < 3) {
                    String participantMail = binding.participantET.getText().toString();
                    if (!viewModel.isMail(participantMail)) {
                        showToast("Veuillez entrer un email valide");
                    } else {
                        if (participants.contains(participantMail)) {
                            showToast("Ce participant est déjà dans la liste");
                            return;
                        }

                        participants.add(participantMail);
                        binding.participantsTV.append("\n -" + participantMail);
                        binding.participantET.setText("");
                    }
                } else {
                    showToast("Vous ne pouvez pas entrer plus de participants");
                }
            }
        });
    }

    /**
     * Display a dialog with date to set up it for the meeting creation
     */
    private void selectDate() {
        binding.chooseDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // DatePickerDialog
                DatePickerDialog datePickerDialog = new DatePickerDialog(MeetingFormActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                calendar.set(Calendar.YEAR, year);
                                calendar.set(Calendar.MONTH, month);
                                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                                // TimePickerDialog
                                TimePickerDialog timePickerDialog = new TimePickerDialog(MeetingFormActivity.this,
                                        new TimePickerDialog.OnTimeSetListener() {
                                            @Override
                                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                calendar.set(Calendar.MINUTE, minute);
                                                isDateSelected = true;
                                                date = calendar.getTime();
                                                setUpPlaces(date);
                                            }
                                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                                timePickerDialog.show();
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    /**
     * Validate a meeting to add it in the meeting list and come back
     */
    private void validateMeeting() {
        binding.validateMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = binding.topicET.getText().toString();
                String errorString = viewModel.getFormError(topic, participants, isDateSelected);

                if (errorString != null) {
                    showToast(errorString);
                } else {
                    Meeting meeting = new Meeting(topic, date, selectedPlace, participants);
                    viewModel.addMeeting(meeting);
                    finish();
                }
            }
        });

    }

    private void clickOnBackButton() {
        binding.backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void showToast(String message) {
        LayoutInflater inflater = LayoutInflater.from(MeetingFormActivity.this);
        View layout = inflater.inflate(R.layout.custom_toast, null);
        TextView text = layout.findViewById(R.id.text);
        text.setText(message);
        Toast toast = new Toast(MeetingFormActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.show();
    }
}

