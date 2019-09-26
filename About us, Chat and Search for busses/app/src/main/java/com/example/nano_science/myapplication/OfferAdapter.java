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

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MenuViewHolder> {

    private Context mCtx;
    private List<Offer> offerList;

    public OfferAdapter(Context mCtx, List<Offer> offerList) {
        this.mCtx = mCtx;
        this.offerList = offerList;
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.offer_list_layout, null);
        MenuViewHolder holder = new MenuViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder menuViewHolder, int i) {

        Offer offer = offerList.get(i);

        menuViewHolder.textViewTitle.setText(offer.getTitle());
        menuViewHolder.textViewDesc.setText(offer.getDescription());

        menuViewHolder.imageView.setImageDrawable(mCtx.getResources().getDrawable(offer.getImage()));
    }

    @Override
    public int getItemCount() {
        return offerList.size();
    }

    class MenuViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView textViewTitle, textViewDesc;

        public MenuViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewrecycle);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDesc = itemView.findViewById(R.id.textViewShortDesc);

        }
    }
}