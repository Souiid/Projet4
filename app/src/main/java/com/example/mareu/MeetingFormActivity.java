package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.mareu.databinding.ActivityMeetingFormBinding;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MeetingFormActivity extends AppCompatActivity {

    ActivityMeetingFormBinding binding;
    ArrayList<String> participants;

    Calendar calendar = Calendar.getInstance();
    Date date;
    List<Meeting> meetings = new ArrayList<>();
    Boolean isDateSelected = false;
    String selectedPlace;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meeting_form);
        binding = ActivityMeetingFormBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addParticipant();
        selectDate();
        participants = new ArrayList<>();
        setUpPlaces();
        validateMeeting();
    }

    private void setUpPlaces() {
      List<String> places = new ArrayList<String>(
                Arrays.asList("Mario", "Luigi", "Waluigi", "Wario", "Bowser", "Peach", "Daisy", "Yoshi", "Donkey Kong", "Toad")
        );
        Intent intent = getIntent();
        if(intent.hasExtra("meetings")) {
            meetings = (List<Meeting>) intent.getSerializableExtra("meetings");
            assert meetings != null;
            for (Meeting meeting: meetings) {
                places.remove(meeting.getPlace());
            }
        }
        setUpSpinner(places);
        selectedPlace = places.get(0);
    }

    private void setUpSpinner(List<String> places) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, places);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        binding.spinner.setAdapter(adapter);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPlace = parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }


    private void addParticipant() {
        binding.addParticipantButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (participants.size() < 3) {
                    String participantMail = binding.participantET.getText().toString();
                    if (!isMail(participantMail)) {
                        Toast.makeText(MeetingFormActivity.this, "Veuillez entrer un email valide", Toast.LENGTH_SHORT).show();
                    }else {
                        participants.add(participantMail);
                        binding.participantsTV.append("\n -" + participantMail);
                        binding.participantET.setText("");
                    }
                }else {
                    Toast.makeText(MeetingFormActivity.this, "Vous ne pouvez pas entrer plus de participants", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }



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
                                                // Mettez à jour la partie heure de la variable globale
                                                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                                                calendar.set(Calendar.MINUTE, minute);

                                                isDateSelected = true;
                                                date = calendar.getTime();

                                            }
                                        }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                                timePickerDialog.show();
                            }
                        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                datePickerDialog.show();
            }
        });
    }

    private void validateMeeting() {
        binding.validateMeetingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topic = binding.topicET.getText().toString();

                if (topic.trim().isEmpty()) {
                    Toast.makeText(MeetingFormActivity.this, "Le sujet est vide", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (participants.size() == 0) {
                    Toast.makeText(MeetingFormActivity.this, "Il faut au moins un participant", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!isDateSelected) {
                    Toast.makeText(MeetingFormActivity.this, "Il faut choisir une date", Toast.LENGTH_SHORT).show();
                    return;
                }

                Meeting meeting = new Meeting(topic, date, selectedPlace, participants);
                Intent returnIntent = new Intent();
                returnIntent.putExtra("newMeeting", meeting);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

    }



    private Boolean isMail(String email) {
        return email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$");
    }
}

