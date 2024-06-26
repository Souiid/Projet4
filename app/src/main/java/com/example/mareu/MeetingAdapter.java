package com.example.mareu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mareu.repositories.PlaceRepository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * MeetingAdapter is a RecyclerView.Adapter that binds meeting data to views that are displayed
 * within a RecyclerView.
 */
public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.ViewHolder>{

    private List<Meeting> meetings;
    private View.OnClickListener itemClickListener;

    /**
     * Constructs a new MeetingAdapter with the specified list of meetings and click listener.
     *
     * @param meetings The list of meetings to display.
     * @param itemClickListener The click listener for handling item clicks.
     */
    public MeetingAdapter(List<Meeting> meetings, View.OnClickListener itemClickListener) {
        this.meetings = meetings;
        this.itemClickListener = itemClickListener;
    }

    /**
     * ViewHolder is a nested class that holds the views for each item in the RecyclerView.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView imageView;
        TextView infosTV;
        TextView participantsTV;
        private Context context;


        /**
         * Constructs a new ViewHolder and sets up the click listener for the CardView.
         *
         * @param itemView The view of the item.
         */
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

    /**
     * Called when RecyclerView needs a new ViewHolder of the given type to represent an item.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds a View of the given view type.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.meeting_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     * This method should update the contents of the ViewHolder's itemView to reflect the item at the given position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position.
     * @param position The position of the item within the adapter's data set.
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Meeting meeting = meetings.get(position);
        String topic = meeting.topic;
        Date date = meeting.date;
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy 'à' HH'h'mm", Locale.FRENCH);
        String hour = dateFormat.format(date);
        String place = meeting.place;

        String participantsString = TextUtils.join(", ", meeting.participants);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(GradientDrawable.OVAL);
        int color = new PlaceRepository().getPlaceColor(place);
        gradientDrawable.setColor(ContextCompat.getColor(holder.context, color));
        holder.imageView.setBackground(gradientDrawable);
        holder.infosTV.setText(place + "-" + hour + "-" + topic);
        holder.participantsTV.setText(participantsString);
        holder.cardView.setTag(position);
        holder.cardView.setOnClickListener(itemClickListener);
    }

    /**
     * Sets the list of meetings for the adapter.
     *
     * @param meetings The new list of meetings.
     */
    public void setMeetings(List<Meeting> meetings) {
        this.meetings = meetings;
    }


    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
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

    /**
     * Notifies that an item has been removed and the range of items has changed.
     *
     * @param position The position of the item that was removed.
     */
    public void notifyChanged(int position) {
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
    }

}
