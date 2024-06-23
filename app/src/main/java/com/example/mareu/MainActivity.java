package com.example.mareu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import com.example.mareu.databinding.ActivityMainBinding;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    List<Meeting> meetings = new ArrayList<>();
    MeetingAdapter adapter;
    MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewModelProvider viewModelProvider = new ViewModelProvider(this);
        viewModel = viewModelProvider.get(MainViewModel.class);
        binding.menuLinearLayout.setVisibility(View.INVISIBLE);
        meetings = viewModel.meetings;
        adapter = new MeetingAdapter(meetings, this);
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        clickOnAddButton();
        clickOnSortButton();
        clickOnSortByDateButton();
        clickOnSortPlaceButton();
        clickOnSortByTopicButton();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
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
    protected void onResume() {
        super.onResume();
        adapter.setMeetings(meetings);
        adapter.notifyDataSetChanged();
    }

    private void clickOnSortByDateButton() {
        binding.sortByDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(meetings, Comparator.comparing(Meeting::getDate));
                    binding.menuLinearLayout.setVisibility(View.INVISIBLE);
                    adapter.setMeetings(meetings);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void clickOnSortPlaceButton() {
        binding.sortByPlaceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(meetings, Comparator.comparing(Meeting::getPlace));
                    binding.menuLinearLayout.setVisibility(View.INVISIBLE);
                    adapter.setMeetings(meetings);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void clickOnSortByTopicButton() {
        binding.sortByTopicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Collections.sort(meetings, Comparator.comparing(Meeting::getTopic));
                    binding.menuLinearLayout.setVisibility(View.INVISIBLE);
                    adapter.setMeetings(meetings);
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void clickOnSortButton() {
        binding.sortButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    assert binding.menuLinearLayout != null;
                    if (binding.menuLinearLayout.getVisibility() == View.INVISIBLE) {
                        binding.menuLinearLayout.setVisibility(View.VISIBLE);
                    }else {
                        binding.menuLinearLayout.setVisibility(View.INVISIBLE);
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        Object tagObject = view.getTag();
        if (tagObject instanceof Integer) {
            int position = (int) tagObject;
              viewModel.deleteMeeting(meetings.get(position));
              adapter.notifyChanged(position);
        }
    }
}