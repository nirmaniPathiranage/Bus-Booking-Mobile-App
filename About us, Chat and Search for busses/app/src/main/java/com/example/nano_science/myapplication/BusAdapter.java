package com.example.nano_science.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/*
 * RecyclerView.Adapter
 * RecyclerView.ViewHolder
 */

public class BusAdapter extends RecyclerView.Adapter<BusAdapter.MenuViewHolder> {

    private Context mCtx;
    private List<Bus> busList;

    public BusAdapter(Context mCtx, List<Bus> busList) {
        this.mCtx = mCtx;
        this.busList = busList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.bus_list_layout, null);
        MenuViewHolder holder = new MenuViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Bus menu = busList.get(i);

        menuViewHolder.textViewSource.setText(menu.getSource());
        menuViewHolder.textViewDestination.setText(menu.getDestination());
        menuViewHolder.textViewDate.setText(menu.getDate());
        menuViewHolder.textViewTime.setText(menu.getTime());
        menuViewHolder.textViewFacilities.setText(menu.getFacilities());
        menuViewHolder.textViewRouteNo.setText(menu.getRoutenumber());
        menuViewHolder.textViewTravelClass.setText(menu.getTravelclass());
        menuViewHolder.textViewExpressway.setText(menu.getExpressway());
        menuViewHolder.textViewBusType.setText(menu.getBustype());
        menuViewHolder.textViewDuration.setText(menu.getDuration());
        menuViewHolder.textViewBusPrice.setText(menu.getPrice());

        menuViewHolder.imageViewBus.setImageDrawable(mCtx.getResources().getDrawable(menu.getBusimage()));
        menuViewHolder.imageViewFacilities.setImageDrawable(mCtx.getResources().getDrawable(menu.getFacilityimage()));
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewBus, imageViewFacilities;
        TextView textViewSource, textViewDestination, textViewDate, textViewTime, textViewFacilities, textViewRouteNo, textViewTravelClass, textViewExpressway, textViewBusType, textViewDuration, textViewBusPrice;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewBus = itemView.findViewById(R.id.imageViewBus);
            textViewSource = itemView.findViewById(R.id.textViewSource);
            textViewDestination = itemView.findViewById(R.id.textViewDestination);
            textViewDate = itemView.findViewById(R.id.textViewDate);
            textViewTime = itemView.findViewById(R.id.textViewTime);
            textViewFacilities = itemView.findViewById(R.id.textViewFacilities);
            imageViewFacilities = itemView.findViewById(R.id.imageViewFacilities);
            textViewRouteNo = itemView.findViewById(R.id.textViewRouteNo);
            textViewTravelClass = itemView.findViewById(R.id.textViewTravelClass);
            textViewExpressway = itemView.findViewById(R.id.textViewExpressway);
            textViewBusType = itemView.findViewById(R.id.textViewBusType);
            textViewDuration = itemView.findViewById(R.id.textViewDuration);
            textViewBusPrice = itemView.findViewById(R.id.textViewBusPrice);
        }
    }
}