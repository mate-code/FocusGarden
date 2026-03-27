package com.matecode.focusgarden.Garden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.matecode.focusgarden.R;

import java.util.List;

public class GardenFragmentToGardenTileAdapter extends RecyclerView.Adapter<GardenFragmentToGardenTileAdapter.ViewHolder> {

    private List<String> data;

    public GardenFragmentToGardenTileAdapter(List<String> data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.garden_tile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.imageView.setImageResource(android.R.drawable.btn_star_big_on);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}