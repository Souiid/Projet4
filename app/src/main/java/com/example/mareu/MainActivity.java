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

/**
 * MainActivity is the main entry point of the application, displaying a list of meetings.
 * It provides functionality to add, sort, and manage meetings.
 * This activity uses ViewModel to manage UI-related data in a lifecycle-conscious way.
 *
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ActivityMainBinding binding;
    List<Meeting> meetings = new ArrayList<>();
    MeetingAdapter adapter;
    MainViewModel viewModel;


    /**
     * Called when the activity is first created.
     * Initializes the view binding, view model, RecyclerView, and sets up various UI components.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *                           previously being shut down then this Bundle contains the data it most
     *                           recently supplied in onSaveInstanceState(Bundle).
     *                           Note: Otherwise it is null.
     */
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

    /**
     * Called to initialize the contents of the Activity's standard options menu.
     *
     * @param menu The options menu in which you place your items.
     * @return true for the menu to be displayed; false if it should not be shown.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    /**
     * Sets up the click listener for the "Add" button to open the MeetingFormActivity.
     */
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

    /**
     * Called when the activity is resumed.
     * Updates the adapter with the current list of meetings.
     */
    @Override
    protected void onResume() {
        super.onResume();
        adapter.setMeetings(meetings);
        adapter.notifyDataSetChanged();
    }

    /**
     * Sets up the click listener for sorting meetings by date.
     */
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

    /**
     * Sets up the click listener for sorting meetings by place.
     */
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

    /**
     * Sets up the click listener for sorting meetings by topic.
     */
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

    /**
     * Sets up the click listener for displaying or hiding the sort menu.
     */
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

    /**
     * Handles click events for the views in the RecyclerView items.
     *
     * @param view The view that was clicked.
     */
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