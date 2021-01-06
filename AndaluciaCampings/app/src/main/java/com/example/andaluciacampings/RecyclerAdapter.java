package com.example.andaluciacampings;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.CampingViewHolder>{

    private List<Camping> campingList;

    public RecyclerAdapter (List<Camping> campingList){
        this.campingList = campingList;
    }

    @NonNull
    @Override
    public CampingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_list_item, parent, false);

        return new CampingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CampingViewHolder holder, int position) {

        Camping camping = campingList.get(position);
        holder.campingName.setText(camping.getCampingName());
        holder.campingLocation.setText(camping.getLocation());
        holder.campingPhone.setText(camping.getPhone_number());
        holder.campingImage.setImageResource(camping.getCampingImage());
    }

    @Override
    public int getItemCount() {
        return this.campingList.size();
    }

    public static class CampingViewHolder extends RecyclerView.ViewHolder
    {
        public TextView campingName;
        public TextView campingLocation;
        public TextView campingPhone;
        public CircleImageView campingImage;
        public CampingViewHolder(@NonNull View itemView) {
            super(itemView);
            campingName = itemView.findViewById(R.id.profile_name);
            campingLocation = itemView.findViewById(R.id.location);
            campingPhone = itemView.findViewById(R.id.phone_number);
            campingImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
