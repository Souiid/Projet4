package com.example.mareu.repositories;

import android.annotation.SuppressLint;

import com.example.mareu.Meeting;
import com.example.mareu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaceRepository {
    List<String> places = new ArrayList<String>(
            Arrays.asList("Mario", "Luigi", "Waluigi", "Wario", "Bowser", "Peach", "Daisy", "Yoshi", "Donkey Kong", "Toad")
    );

    @SuppressLint("ResourceAsColor")
    public int setColorByPlace(String place) {
        int color;

        switch (place) {
            case "Mario":
                color = R.color.red;
                break;
            case "Luigi":
                color = R.color.green;
                break;
            case "Waluigi":
                color = R.color.purple;
                break;
            case "Wario":
                color = R.color.yellow;
                break;
            case "Bowser":
                color = R.color.black;
                break;
            case "Peach":
                color = R.color.pink;
                break;
            case "Daisy":
                color = R.color.blue;
                break;
            case "Yoshi":
                color = R.color.lightGray;
                break;
            case "Donkey Kong":
                color = R.color.darkBlue;
                break;
            case "Toad":
                color = R.color.orange;
                break;
            default:
                color = R.color.red;
        }

        return color;
    }

}
