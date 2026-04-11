package com.matecode.focusgarden.Garden;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class GardenViewModelFactory implements ViewModelProvider.Factory {

    private final int rows;
    private final int cols;

    public GardenViewModelFactory(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(GardenViewModel.class)) {
            return (T) new GardenViewModel(rows, cols);
        }
        throw new IllegalArgumentException("Unknown ViewModel class");
    }
}
