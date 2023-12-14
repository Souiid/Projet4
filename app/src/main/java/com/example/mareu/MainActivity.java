package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.mareu.databinding.ActivityMainBinding;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    List<Meeting> meetings = new ArrayList<>();
    MeetingAdapter adapter = new MeetingAdapter(meetings, this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        generateMeetings();
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        clickOnAddButton();
    }

    private void clickOnAddButton() {
        binding.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MeetingFormActivity.class);
                intent.putExtra("meetings", (Serializable) meetings);
                startActivityForResult(intent, 0);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0 && resultCode == RESULT_OK) {
            // Les données sont renvoyées depuis l'activité B
            if (data != null && data.hasExtra("newMeeting")) { // Utilisez "newMeeting" ici
                Meeting meeting = (Meeting) data.getSerializableExtra("newMeeting"); // Utilisez "newMeeting" ici
                Log.d("Meeting", meeting.place);
                Log.d("Meeting", meeting.topic);
                Log.d("Meeting", meeting.participants.get(0));
                Log.d("Meeting", meeting.date.toString());
                meetings.add(meeting);
                adapter.setMeetings(meetings);
                adapter.notifyDataSetChanged();
            }
        }
    }

    private void generateMeetings() {
        // Ajoutez des éléments à la liste selon vos besoins
        List<String> participants1 = generateParticipants("maxime@lamzone.fr", "alex@lamzone.fr", "gerald@lamzone.fr");
        List<String> participants2 = generateParticipants("stanlay@lamzone.fr", "idriss@lamzone.fr", "david@lamzone.fr");
        List<String> participants3 = generateParticipants("theo@lamzone.fr", "sacha@lamzone.fr", "remy@lamzone.fr");

        Meeting meeting1 = new Meeting("Réunion A", new Date(), "Peach", participants1);
        Meeting meeting2 = new Meeting("Réunion B",new Date(),"Mario", participants2);
        Meeting meeting3 = new Meeting("Réunion C", new Date(), "Luigi", participants3);


        meetings.add(meeting1);
        meetings.add(meeting2);
        meetings.add(meeting3);
    }

    private List<String> generateParticipants(String mail1, String mail2, String mail3) {
        List<String> participants = new ArrayList<>();
        participants.add(mail1);
        participants.add(mail2);
        participants.add(mail3);
        return participants;
    }

    @Override
    public void onClick(View view) {
        Object tagObject = view.getTag();

        if (tagObject instanceof Integer) {
            int position = (int) tagObject;
            adapter.removeMeeting(position);
        }
    }
}