package com.matecode.focusgarden.Garden;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.matecode.focusgarden.R;

import java.util.List;

public class GardenFragmentToGardenTileAdapter extends RecyclerView.Adapter<GardenFragmentToGardenTileAdapter.ViewHolder> {

    private final GardenViewModel data;

    public GardenFragmentToGardenTileAdapter(GardenViewModel data) {
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;

        public ViewHolder(View view) {
            super(view);
            imageView = view.findViewById(R.id.imageView);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.garden_tile_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Log.println(Log.ERROR, "sth", String.valueOf(position));

        GardenTileStatusEnum status = data.getGardenMap().get(position).getStatus();

        switch (status) {
            case EMPTY:
                break;
            case LIVE:
                holder.imageView.setImageResource(android.R.drawable.btn_star_big_on);
                break;
            case DEAD:
                holder.imageView.setImageResource(android.R.drawable.btn_star_big_off);
                break;
            default:
                holder.imageView.setImageResource(R.drawable.ic_launcher_foreground);
                break;
        }


    }

    @Override
    public int getItemCount() {
        return data.getSize();
    }
}