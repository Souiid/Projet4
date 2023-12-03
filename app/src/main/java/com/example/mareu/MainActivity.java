package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.mareu.databinding.ActivityMainBinding;

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
    }

    private void generateMeetings() {
        // Ajoutez des éléments à la liste selon vos besoins
        List<String> participants1 = generateParticipants("maxime@lamzone.fr", "alex@lamzone.fr", "gerald@lamzone.fr");
        List<String> participants2 = generateParticipants("stanlay@lamzone.fr", "idriss@lamzone.fr", "david@lamzone.fr");
        List<String> participants3 = generateParticipants("theo@lamzone.fr", "sacha@lamzone.fr", "remy@lamzone.fr");

        Meeting meeting1 = new Meeting();
        meeting1.topic = "Réunion A";
        meeting1.date = new Date();
        meeting1.place = "Peach";
        meeting1.participants = participants1;

        Meeting meeting2 = new Meeting();
        meeting2.place = "Réunion B";
        meeting2.date = new Date();
        meeting2.topic = "Mario";
        meeting2.participants = participants2;

        Meeting meeting3 = new Meeting();
        meeting3.place = "Réunion C";
        meeting3.date = new Date();
        meeting3.topic = "Luigi";
        meeting3.participants = participants3;

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