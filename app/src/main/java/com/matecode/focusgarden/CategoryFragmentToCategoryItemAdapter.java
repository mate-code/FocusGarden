package com.matecode.focusgarden;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CategoryFragmentToCategoryItemAdapter extends RecyclerView.Adapter<CategoryFragmentToCategoryItemAdapter.ViewHolder> {

    private final List<String> localDataSet;
    private int selectedItem = -1;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private final RadioButton radioButton;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            textView = (TextView) view.findViewById(R.id.tvCategoryName);
            radioButton = (RadioButton) view.findViewById(R.id.rbtnCategory);
        }

        public TextView getTextView() { return textView; }
        public RadioButton getRadioButton() { return radioButton; }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param dataSet String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public CategoryFragmentToCategoryItemAdapter(List<String> dataSet) { localDataSet = dataSet; }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.category_item, viewGroup, false);

        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.getTextView().setText(localDataSet.get(position));
        viewHolder.getRadioButton().setChecked(position == selectedItem);

        viewHolder.getRadioButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int prevPosition = selectedItem;
                selectedItem = viewHolder.getBindingAdapterPosition();
                notifyItemChanged(prevPosition);
                notifyItemChanged(selectedItem);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() { return localDataSet.size(); }
    public int getSelectedItem() { return selectedItem; }
}