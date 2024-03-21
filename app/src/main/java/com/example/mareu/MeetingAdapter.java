package com.example.mareu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder>{

    private List<Meeting> meetings;
    private View.OnClickListener itemClickListener;

    public MeetingAdapter(List<Meeting> meetings, View.OnClickListener itemClickListener) {
        this.meetings = meetings;
        this.itemClickListener = itemClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView infosTV;
        TextView participantsTV;
        private Context context;


        public ViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardView);
            infosTV = itemView.findViewById(R.id.infosTV);
            imageView = itemView.findViewById(R.id.imageView);
            participantsTV =  itemView.findViewById(R.id.participantsTV);
            context = itemView.getContext();

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        itemClickListener.onClick(view);
                    }
                }
            });
        }
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.meeting_item, parent, false);
        return new ViewHolder(itemView);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        String topic = meeting.topic;
        Date date = meeting.date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM yyyy 'à' HH'h'mm", Locale.FRENCH);
        String hour = dateFormat.format(date);
        String place = meeting.place;

        String participantsString = TextUtils.join(", ", meeting.participants);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        gradientDrawable.setColor(ContextCompat.getColor(holder.context, setColorByPlace(place)));
        holder.imageView.setBackground(gradientDrawable);
        holder.infosTV.setText(place + "-" + hour + "-" + topic);
        holder.participantsTV.setText(participantsString);
        Log.d("TAG", "PARITICIPANTS: " + participantsString);
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(itemClickListener);
    }

    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }

    @Override
    public int getItemCount() {
        return meetings.size();
    }

    public void removeMeeting(int position) {
        if (position >= 0 && position < meetings.size()) {
            meetings.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, getItemCount());
        }
    }

    @SuppressLint("ResourceAsColor")
    private int setColorByPlace(String place) {
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
