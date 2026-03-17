package com.matecode.focusgarden;

import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.matecode.focusgarden.databinding.FragmentGardenBinding;

public class GardenFragment extends Fragment {

    private FragmentGardenBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentGardenBinding.inflate(inflater, container, false);

        ConstraintLayout grid = binding.getRoot();
        int rowNum = 10;
        int colNum = 6;

        ImageView[][] ivg = new ImageView[rowNum][colNum];

        for (int row = 0; row < rowNum; row++) {
            for (int col = 0; col < colNum; col++){
                ImageView iv = new ImageView(requireContext());
                iv.setId(View.generateViewId());
                iv.setImageResource(R.drawable.ic_launcher_background);
                iv.setScaleType(ImageView.ScaleType.CENTER_CROP);

                int spacing = 10;
                ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(0,0);
                params.matchConstraintPercentWidth = (float) ((1f - 0.05) / colNum);
                params.dimensionRatio = "1:1"; // makes square
                params.setMargins(spacing, spacing, spacing, spacing);

                if (row == 0) { params.topToTop = ConstraintLayout.LayoutParams.PARENT_ID; }
                else { params.topToBottom = ivg[row - 1][col].getId(); }

                if (col == 0) { params.startToStart = ConstraintLayout.LayoutParams.PARENT_ID; }
                else { params.startToEnd = ivg[row][col - 1].getId(); }

                iv.setLayoutParams(params);
                ivg[row][col] = iv;
                grid.addView(iv);
            }
        }

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {

    }

}
